package org.apache.james.mime4j.descriptor;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.james.mime4j.parser.Field;
import org.apache.james.mime4j.util.MimeUtil;

public class DefaultBodyDescriptor
  implements MutableBodyDescriptor
{
  private static final String DEFAULT_MEDIA_TYPE = "text";
  private static final String DEFAULT_MIME_TYPE = "text/plain";
  private static final String DEFAULT_SUB_TYPE = "plain";
  private static final String EMAIL_MESSAGE_MIME_TYPE = "message/rfc822";
  private static final String MEDIA_TYPE_MESSAGE = "message";
  private static final String MEDIA_TYPE_TEXT = "text";
  private static final String SUB_TYPE_EMAIL = "rfc822";
  private static final String US_ASCII = "us-ascii";
  private static Log log = LogFactory.getLog(DefaultBodyDescriptor.class);
  private String boundary = null;
  private String charset = "us-ascii";
  private long contentLength = -1L;
  private boolean contentTransferEncSet;
  private boolean contentTypeSet;
  private String mediaType = "text";
  private String mimeType = "text/plain";
  private Map<String, String> parameters = new HashMap();
  private String subType = "plain";
  private String transferEncoding = "7bit";
  
  public DefaultBodyDescriptor()
  {
    this(null);
  }
  
  public DefaultBodyDescriptor(BodyDescriptor paramBodyDescriptor)
  {
    if ((paramBodyDescriptor != null) && (MimeUtil.isSameMimeType("multipart/digest", paramBodyDescriptor.getMimeType())))
    {
      this.mimeType = "message/rfc822";
      this.subType = "rfc822";
      this.mediaType = "message";
      return;
    }
    this.mimeType = "text/plain";
    this.subType = "plain";
    this.mediaType = "text";
  }
  
  private void parseContentType(String paramString)
  {
    this.contentTypeSet = true;
    Map localMap = MimeUtil.getHeaderParams(paramString);
    String str3 = (String)localMap.get("");
    Object localObject2 = null;
    String str1 = null;
    Object localObject1 = null;
    String str2 = null;
    paramString = str3;
    if (str3 != null)
    {
      str3 = str3.toLowerCase().trim();
      int k = str3.indexOf('/');
      int j = 0;
      paramString = str3;
      localObject1 = str2;
      localObject2 = str1;
      int i = j;
      if (k != -1)
      {
        str1 = str3.substring(0, k).trim();
        str2 = str3.substring(k + 1).trim();
        paramString = str3;
        localObject1 = str2;
        localObject2 = str1;
        i = j;
        if (str1.length() > 0)
        {
          paramString = str3;
          localObject1 = str2;
          localObject2 = str1;
          i = j;
          if (str2.length() > 0)
          {
            paramString = str1 + "/" + str2;
            i = 1;
            localObject2 = str1;
            localObject1 = str2;
          }
        }
      }
      if (i == 0)
      {
        paramString = null;
        localObject2 = null;
        localObject1 = null;
      }
    }
    str1 = (String)localMap.get("boundary");
    if ((paramString != null) && (((paramString.startsWith("multipart/")) && (str1 != null)) || (!paramString.startsWith("multipart/"))))
    {
      this.mimeType = paramString;
      this.subType = ((String)localObject1);
      this.mediaType = ((String)localObject2);
    }
    if (MimeUtil.isMultipart(this.mimeType)) {
      this.boundary = str1;
    }
    paramString = (String)localMap.get("charset");
    this.charset = null;
    if (paramString != null)
    {
      paramString = paramString.trim();
      if (paramString.length() > 0) {
        this.charset = paramString.toLowerCase();
      }
    }
    if ((this.charset == null) && ("text".equals(this.mediaType))) {
      this.charset = "us-ascii";
    }
    this.parameters.putAll(localMap);
    this.parameters.remove("");
    this.parameters.remove("boundary");
    this.parameters.remove("charset");
  }
  
  public void addField(Field paramField)
  {
    String str = paramField.getName();
    paramField = paramField.getBody();
    str = str.trim().toLowerCase();
    if ((str.equals("content-transfer-encoding")) && (!this.contentTransferEncSet))
    {
      this.contentTransferEncSet = true;
      paramField = paramField.trim().toLowerCase();
      if (paramField.length() > 0) {
        this.transferEncoding = paramField;
      }
    }
    do
    {
      return;
      if ((str.equals("content-length")) && (this.contentLength == -1L)) {
        try
        {
          this.contentLength = Long.parseLong(paramField.trim());
          return;
        }
        catch (NumberFormatException localNumberFormatException)
        {
          log.error("Invalid content-length: " + paramField);
          return;
        }
      }
    } while ((!localNumberFormatException.equals("content-type")) || (this.contentTypeSet));
    parseContentType(paramField);
  }
  
  public String getBoundary()
  {
    return this.boundary;
  }
  
  public String getCharset()
  {
    return this.charset;
  }
  
  public long getContentLength()
  {
    return this.contentLength;
  }
  
  public Map<String, String> getContentTypeParameters()
  {
    return this.parameters;
  }
  
  public String getMediaType()
  {
    return this.mediaType;
  }
  
  public String getMimeType()
  {
    return this.mimeType;
  }
  
  public String getSubType()
  {
    return this.subType;
  }
  
  public String getTransferEncoding()
  {
    return this.transferEncoding;
  }
  
  public String toString()
  {
    return this.mimeType;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\descriptor\DefaultBodyDescriptor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */