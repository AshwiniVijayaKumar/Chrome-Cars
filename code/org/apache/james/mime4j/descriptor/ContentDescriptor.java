package org.apache.james.mime4j.descriptor;

import java.util.Map;

public abstract interface ContentDescriptor
{
  public abstract String getCharset();
  
  public abstract long getContentLength();
  
  public abstract Map<String, String> getContentTypeParameters();
  
  public abstract String getMediaType();
  
  public abstract String getMimeType();
  
  public abstract String getSubType();
  
  public abstract String getTransferEncoding();
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\descriptor\ContentDescriptor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */