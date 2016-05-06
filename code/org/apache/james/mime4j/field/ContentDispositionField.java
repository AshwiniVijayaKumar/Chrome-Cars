package org.apache.james.mime4j.field;

import java.io.StringReader;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.james.mime4j.field.contentdisposition.parser.ContentDispositionParser;
import org.apache.james.mime4j.field.datetime.DateTime;
import org.apache.james.mime4j.field.datetime.parser.DateTimeParser;
import org.apache.james.mime4j.util.ByteSequence;

public class ContentDispositionField
  extends AbstractField
{
  public static final String DISPOSITION_TYPE_ATTACHMENT = "attachment";
  public static final String DISPOSITION_TYPE_INLINE = "inline";
  public static final String PARAM_CREATION_DATE = "creation-date";
  public static final String PARAM_FILENAME = "filename";
  public static final String PARAM_MODIFICATION_DATE = "modification-date";
  public static final String PARAM_READ_DATE = "read-date";
  public static final String PARAM_SIZE = "size";
  static final FieldParser PARSER = new FieldParser()
  {
    public ParsedField parse(String paramAnonymousString1, String paramAnonymousString2, ByteSequence paramAnonymousByteSequence)
    {
      return new ContentDispositionField(paramAnonymousString1, paramAnonymousString2, paramAnonymousByteSequence);
    }
  };
  private static Log log = LogFactory.getLog(ContentDispositionField.class);
  private Date creationDate;
  private boolean creationDateParsed;
  private String dispositionType = "";
  private Date modificationDate;
  private boolean modificationDateParsed;
  private Map<String, String> parameters = new HashMap();
  private ParseException parseException;
  private boolean parsed = false;
  private Date readDate;
  private boolean readDateParsed;
  
  ContentDispositionField(String paramString1, String paramString2, ByteSequence paramByteSequence)
  {
    super(paramString1, paramString2, paramByteSequence);
  }
  
  private void parse()
  {
    Object localObject2 = getBody();
    Object localObject1 = new ContentDispositionParser(new StringReader((String)localObject2));
    try
    {
      ((ContentDispositionParser)localObject1).parseAll();
      localObject2 = ((ContentDispositionParser)localObject1).getDispositionType();
      if (localObject2 != null)
      {
        this.dispositionType = ((String)localObject2).toLowerCase(Locale.US);
        localObject2 = ((ContentDispositionParser)localObject1).getParamNames();
        localObject1 = ((ContentDispositionParser)localObject1).getParamValues();
        if ((localObject2 != null) && (localObject1 != null))
        {
          int j = Math.min(((List)localObject2).size(), ((List)localObject1).size());
          int i = 0;
          while (i < j)
          {
            String str1 = ((String)((List)localObject2).get(i)).toLowerCase(Locale.US);
            String str2 = (String)((List)localObject1).get(i);
            this.parameters.put(str1, str2);
            i += 1;
          }
        }
      }
    }
    catch (ParseException localParseException)
    {
      for (;;)
      {
        if (log.isDebugEnabled()) {
          log.debug("Parsing value '" + (String)localObject2 + "': " + localParseException.getMessage());
        }
        this.parseException = localParseException;
      }
    }
    catch (org.apache.james.mime4j.field.contentdisposition.parser.TokenMgrError localTokenMgrError)
    {
      for (;;)
      {
        if (log.isDebugEnabled()) {
          log.debug("Parsing value '" + (String)localObject2 + "': " + localTokenMgrError.getMessage());
        }
        this.parseException = new ParseException(localTokenMgrError.getMessage());
      }
      this.parsed = true;
    }
  }
  
  private Date parseDate(String paramString)
  {
    String str = getParameter(paramString);
    if (str == null) {
      if (log.isDebugEnabled()) {
        log.debug("Parsing " + paramString + " null");
      }
    }
    do
    {
      for (;;)
      {
        return null;
        try
        {
          Date localDate = new DateTimeParser(new StringReader(str)).parseAll().getDate();
          return localDate;
        }
        catch (ParseException localParseException)
        {
          if (log.isDebugEnabled())
          {
            log.debug("Parsing " + paramString + " '" + str + "': " + localParseException.getMessage());
            return null;
          }
        }
        catch (org.apache.james.mime4j.field.datetime.parser.TokenMgrError localTokenMgrError) {}
      }
    } while (!log.isDebugEnabled());
    log.debug("Parsing " + paramString + " '" + str + "': " + localTokenMgrError.getMessage());
    return null;
  }
  
  public Date getCreationDate()
  {
    if (!this.creationDateParsed)
    {
      this.creationDate = parseDate("creation-date");
      this.creationDateParsed = true;
    }
    return this.creationDate;
  }
  
  public String getDispositionType()
  {
    if (!this.parsed) {
      parse();
    }
    return this.dispositionType;
  }
  
  public String getFilename()
  {
    return getParameter("filename");
  }
  
  public Date getModificationDate()
  {
    if (!this.modificationDateParsed)
    {
      this.modificationDate = parseDate("modification-date");
      this.modificationDateParsed = true;
    }
    return this.modificationDate;
  }
  
  public String getParameter(String paramString)
  {
    if (!this.parsed) {
      parse();
    }
    return (String)this.parameters.get(paramString.toLowerCase());
  }
  
  public Map<String, String> getParameters()
  {
    if (!this.parsed) {
      parse();
    }
    return Collections.unmodifiableMap(this.parameters);
  }
  
  public ParseException getParseException()
  {
    if (!this.parsed) {
      parse();
    }
    return this.parseException;
  }
  
  public Date getReadDate()
  {
    if (!this.readDateParsed)
    {
      this.readDate = parseDate("read-date");
      this.readDateParsed = true;
    }
    return this.readDate;
  }
  
  public long getSize()
  {
    String str = getParameter("size");
    if (str == null) {
      return -1L;
    }
    try
    {
      long l1 = Long.parseLong(str);
      long l2 = l1;
      if (l1 < 0L) {
        l2 = -1L;
      }
      return l2;
    }
    catch (NumberFormatException localNumberFormatException) {}
    return -1L;
  }
  
  public boolean isAttachment()
  {
    if (!this.parsed) {
      parse();
    }
    return this.dispositionType.equals("attachment");
  }
  
  public boolean isDispositionType(String paramString)
  {
    if (!this.parsed) {
      parse();
    }
    return this.dispositionType.equalsIgnoreCase(paramString);
  }
  
  public boolean isInline()
  {
    if (!this.parsed) {
      parse();
    }
    return this.dispositionType.equals("inline");
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\field\ContentDispositionField.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */