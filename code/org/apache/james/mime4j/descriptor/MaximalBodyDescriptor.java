package org.apache.james.mime4j.descriptor;

import java.io.StringReader;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.apache.james.mime4j.MimeException;
import org.apache.james.mime4j.field.datetime.DateTime;
import org.apache.james.mime4j.field.datetime.parser.DateTimeParser;
import org.apache.james.mime4j.field.datetime.parser.ParseException;
import org.apache.james.mime4j.field.language.parser.ContentLanguageParser;
import org.apache.james.mime4j.field.mimeversion.parser.MimeVersionParser;
import org.apache.james.mime4j.field.structured.parser.StructuredFieldParser;
import org.apache.james.mime4j.parser.Field;
import org.apache.james.mime4j.util.MimeUtil;

public class MaximalBodyDescriptor
  extends DefaultBodyDescriptor
{
  private static final int DEFAULT_MAJOR_VERSION = 1;
  private static final int DEFAULT_MINOR_VERSION = 0;
  private String contentDescription = null;
  private DateTime contentDispositionCreationDate = null;
  private MimeException contentDispositionCreationDateParseException = null;
  private DateTime contentDispositionModificationDate = null;
  private MimeException contentDispositionModificationDateParseException = null;
  private Map<String, String> contentDispositionParameters = Collections.emptyMap();
  private DateTime contentDispositionReadDate = null;
  private MimeException contentDispositionReadDateParseException = null;
  private long contentDispositionSize = -1L;
  private MimeException contentDispositionSizeParseException = null;
  private String contentDispositionType = null;
  private String contentId = null;
  private List<String> contentLanguage = null;
  private MimeException contentLanguageParseException = null;
  private String contentLocation = null;
  private MimeException contentLocationParseException = null;
  private String contentMD5Raw = null;
  private boolean isContentDescriptionSet = false;
  private boolean isContentDispositionSet = false;
  private boolean isContentIdSet = false;
  private boolean isContentLanguageSet;
  private boolean isContentLocationSet = false;
  private boolean isContentMD5Set = false;
  private boolean isMimeVersionSet = false;
  private int mimeMajorVersion = 1;
  private int mimeMinorVersion = 0;
  private MimeException mimeVersionException;
  
  protected MaximalBodyDescriptor()
  {
    this(null);
  }
  
  public MaximalBodyDescriptor(BodyDescriptor paramBodyDescriptor)
  {
    super(paramBodyDescriptor);
  }
  
  private void parseContentDescription(String paramString)
  {
    if (paramString == null) {}
    for (this.contentDescription = "";; this.contentDescription = paramString.trim())
    {
      this.isContentDescriptionSet = true;
      return;
    }
  }
  
  private void parseContentDisposition(String paramString)
  {
    this.isContentDispositionSet = true;
    this.contentDispositionParameters = MimeUtil.getHeaderParams(paramString);
    this.contentDispositionType = ((String)this.contentDispositionParameters.get(""));
    paramString = (String)this.contentDispositionParameters.get("modification-date");
    if (paramString != null) {}
    try
    {
      this.contentDispositionModificationDate = parseDate(paramString);
      paramString = (String)this.contentDispositionParameters.get("creation-date");
      if (paramString == null) {}
    }
    catch (ParseException paramString)
    {
      try
      {
        this.contentDispositionCreationDate = parseDate(paramString);
        paramString = (String)this.contentDispositionParameters.get("read-date");
        if (paramString == null) {}
      }
      catch (ParseException paramString)
      {
        try
        {
          this.contentDispositionReadDate = parseDate(paramString);
          paramString = (String)this.contentDispositionParameters.get("size");
          if (paramString == null) {}
        }
        catch (ParseException paramString)
        {
          try
          {
            for (;;)
            {
              this.contentDispositionSize = Long.parseLong(paramString);
              this.contentDispositionParameters.remove("");
              return;
              paramString = paramString;
              this.contentDispositionModificationDateParseException = paramString;
              continue;
              paramString = paramString;
              this.contentDispositionCreationDateParseException = paramString;
            }
            paramString = paramString;
            this.contentDispositionReadDateParseException = paramString;
          }
          catch (NumberFormatException paramString)
          {
            for (;;)
            {
              this.contentDispositionSizeParseException = ((MimeException)new MimeException(paramString.getMessage(), paramString).fillInStackTrace());
            }
          }
        }
      }
    }
  }
  
  private void parseContentId(String paramString)
  {
    if (paramString == null) {}
    for (this.contentId = "";; this.contentId = paramString.trim())
    {
      this.isContentIdSet = true;
      return;
    }
  }
  
  private DateTime parseDate(String paramString)
    throws ParseException
  {
    return new DateTimeParser(new StringReader(paramString)).date_time();
  }
  
  private void parseLanguage(String paramString)
  {
    this.isContentLanguageSet = true;
    if (paramString != null) {}
    try
    {
      this.contentLanguage = new ContentLanguageParser(new StringReader(paramString)).parse();
      return;
    }
    catch (MimeException paramString)
    {
      this.contentLanguageParseException = paramString;
    }
  }
  
  private void parseLocation(String paramString)
  {
    this.isContentLocationSet = true;
    if (paramString != null)
    {
      paramString = new StructuredFieldParser(new StringReader(paramString));
      paramString.setFoldingPreserved(false);
    }
    try
    {
      this.contentLocation = paramString.parse();
      return;
    }
    catch (MimeException paramString)
    {
      this.contentLocationParseException = paramString;
    }
  }
  
  private void parseMD5(String paramString)
  {
    this.isContentMD5Set = true;
    if (paramString != null) {
      this.contentMD5Raw = paramString.trim();
    }
  }
  
  private void parseMimeVersion(String paramString)
  {
    paramString = new MimeVersionParser(new StringReader(paramString));
    try
    {
      paramString.parse();
      int i = paramString.getMajorVersion();
      if (i != -1) {
        this.mimeMajorVersion = i;
      }
      i = paramString.getMinorVersion();
      if (i != -1) {
        this.mimeMinorVersion = i;
      }
    }
    catch (MimeException paramString)
    {
      for (;;)
      {
        this.mimeVersionException = paramString;
      }
    }
    this.isMimeVersionSet = true;
  }
  
  public void addField(Field paramField)
  {
    String str2 = paramField.getName();
    String str1 = paramField.getBody();
    str2 = str2.trim().toLowerCase();
    if (("mime-version".equals(str2)) && (!this.isMimeVersionSet))
    {
      parseMimeVersion(str1);
      return;
    }
    if (("content-id".equals(str2)) && (!this.isContentIdSet))
    {
      parseContentId(str1);
      return;
    }
    if (("content-description".equals(str2)) && (!this.isContentDescriptionSet))
    {
      parseContentDescription(str1);
      return;
    }
    if (("content-disposition".equals(str2)) && (!this.isContentDispositionSet))
    {
      parseContentDisposition(str1);
      return;
    }
    if (("content-language".equals(str2)) && (!this.isContentLanguageSet))
    {
      parseLanguage(str1);
      return;
    }
    if (("content-location".equals(str2)) && (!this.isContentLocationSet))
    {
      parseLocation(str1);
      return;
    }
    if (("content-md5".equals(str2)) && (!this.isContentMD5Set))
    {
      parseMD5(str1);
      return;
    }
    super.addField(paramField);
  }
  
  public String getContentDescription()
  {
    return this.contentDescription;
  }
  
  public DateTime getContentDispositionCreationDate()
  {
    return this.contentDispositionCreationDate;
  }
  
  public MimeException getContentDispositionCreationDateParseException()
  {
    return this.contentDispositionCreationDateParseException;
  }
  
  public String getContentDispositionFilename()
  {
    return (String)this.contentDispositionParameters.get("filename");
  }
  
  public DateTime getContentDispositionModificationDate()
  {
    return this.contentDispositionModificationDate;
  }
  
  public MimeException getContentDispositionModificationDateParseException()
  {
    return this.contentDispositionModificationDateParseException;
  }
  
  public Map<String, String> getContentDispositionParameters()
  {
    return this.contentDispositionParameters;
  }
  
  public DateTime getContentDispositionReadDate()
  {
    return this.contentDispositionReadDate;
  }
  
  public MimeException getContentDispositionReadDateParseException()
  {
    return this.contentDispositionReadDateParseException;
  }
  
  public long getContentDispositionSize()
  {
    return this.contentDispositionSize;
  }
  
  public MimeException getContentDispositionSizeParseException()
  {
    return this.contentDispositionSizeParseException;
  }
  
  public String getContentDispositionType()
  {
    return this.contentDispositionType;
  }
  
  public String getContentId()
  {
    return this.contentId;
  }
  
  public List<String> getContentLanguage()
  {
    return this.contentLanguage;
  }
  
  public MimeException getContentLanguageParseException()
  {
    return this.contentLanguageParseException;
  }
  
  public String getContentLocation()
  {
    return this.contentLocation;
  }
  
  public MimeException getContentLocationParseException()
  {
    return this.contentLocationParseException;
  }
  
  public String getContentMD5Raw()
  {
    return this.contentMD5Raw;
  }
  
  public int getMimeMajorVersion()
  {
    return this.mimeMajorVersion;
  }
  
  public int getMimeMinorVersion()
  {
    return this.mimeMinorVersion;
  }
  
  public MimeException getMimeVersionParseException()
  {
    return this.mimeVersionException;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\descriptor\MaximalBodyDescriptor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */