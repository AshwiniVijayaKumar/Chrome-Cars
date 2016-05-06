package org.apache.james.mime4j.field;

import java.io.StringReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.james.mime4j.field.contenttype.parser.ContentTypeParser;
import org.apache.james.mime4j.field.contenttype.parser.ParseException;
import org.apache.james.mime4j.field.contenttype.parser.TokenMgrError;
import org.apache.james.mime4j.util.ByteSequence;

public class ContentTypeField
  extends AbstractField
{
  public static final String PARAM_BOUNDARY = "boundary";
  public static final String PARAM_CHARSET = "charset";
  static final FieldParser PARSER = new FieldParser()
  {
    public ParsedField parse(String paramAnonymousString1, String paramAnonymousString2, ByteSequence paramAnonymousByteSequence)
    {
      return new ContentTypeField(paramAnonymousString1, paramAnonymousString2, paramAnonymousByteSequence);
    }
  };
  public static final String TYPE_MESSAGE_RFC822 = "message/rfc822";
  public static final String TYPE_MULTIPART_DIGEST = "multipart/digest";
  public static final String TYPE_MULTIPART_PREFIX = "multipart/";
  public static final String TYPE_TEXT_PLAIN = "text/plain";
  private static Log log = LogFactory.getLog(ContentTypeField.class);
  private String mimeType = "";
  private Map<String, String> parameters = new HashMap();
  private ParseException parseException;
  private boolean parsed = false;
  
  ContentTypeField(String paramString1, String paramString2, ByteSequence paramByteSequence)
  {
    super(paramString1, paramString2, paramByteSequence);
  }
  
  public static String getCharset(ContentTypeField paramContentTypeField)
  {
    if (paramContentTypeField != null)
    {
      paramContentTypeField = paramContentTypeField.getCharset();
      if ((paramContentTypeField != null) && (paramContentTypeField.length() > 0)) {
        return paramContentTypeField;
      }
    }
    return "us-ascii";
  }
  
  public static String getMimeType(ContentTypeField paramContentTypeField1, ContentTypeField paramContentTypeField2)
  {
    if ((paramContentTypeField1 == null) || (paramContentTypeField1.getMimeType().length() == 0) || ((paramContentTypeField1.isMultipart()) && (paramContentTypeField1.getBoundary() == null)))
    {
      if ((paramContentTypeField2 != null) && (paramContentTypeField2.isMimeType("multipart/digest"))) {
        return "message/rfc822";
      }
      return "text/plain";
    }
    return paramContentTypeField1.getMimeType();
  }
  
  private void parse()
  {
    Object localObject2 = getBody();
    Object localObject1 = new ContentTypeParser(new StringReader((String)localObject2));
    try
    {
      ((ContentTypeParser)localObject1).parseAll();
      localObject2 = ((ContentTypeParser)localObject1).getType();
      String str1 = ((ContentTypeParser)localObject1).getSubType();
      if ((localObject2 != null) && (str1 != null))
      {
        this.mimeType = ((String)localObject2 + "/" + str1).toLowerCase();
        localObject2 = ((ContentTypeParser)localObject1).getParamNames();
        localObject1 = ((ContentTypeParser)localObject1).getParamValues();
        if ((localObject2 != null) && (localObject1 != null))
        {
          int j = Math.min(((List)localObject2).size(), ((List)localObject1).size());
          int i = 0;
          while (i < j)
          {
            str1 = ((String)((List)localObject2).get(i)).toLowerCase();
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
    catch (TokenMgrError localTokenMgrError)
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
  
  public String getBoundary()
  {
    return getParameter("boundary");
  }
  
  public String getCharset()
  {
    return getParameter("charset");
  }
  
  public String getMimeType()
  {
    if (!this.parsed) {
      parse();
    }
    return this.mimeType;
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
  
  public boolean isMimeType(String paramString)
  {
    if (!this.parsed) {
      parse();
    }
    return this.mimeType.equalsIgnoreCase(paramString);
  }
  
  public boolean isMultipart()
  {
    if (!this.parsed) {
      parse();
    }
    return this.mimeType.startsWith("multipart/");
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\field\ContentTypeField.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */