package org.apache.http.entity.mime.content;

import java.util.Collections;
import java.util.Map;
import org.apache.http.annotation.NotThreadSafe;
import org.apache.james.mime4j.message.Entity;
import org.apache.james.mime4j.message.SingleBody;

@NotThreadSafe
public abstract class AbstractContentBody
  extends SingleBody
  implements ContentBody
{
  private final String mediaType;
  private final String mimeType;
  private Entity parent = null;
  private final String subType;
  
  public AbstractContentBody(String paramString)
  {
    if (paramString == null) {
      throw new IllegalArgumentException("MIME type may not be null");
    }
    this.mimeType = paramString;
    int i = paramString.indexOf('/');
    if (i != -1)
    {
      this.mediaType = paramString.substring(0, i);
      this.subType = paramString.substring(i + 1);
      return;
    }
    this.mediaType = paramString;
    this.subType = null;
  }
  
  public void dispose() {}
  
  public Map<String, String> getContentTypeParameters()
  {
    return Collections.emptyMap();
  }
  
  public String getMediaType()
  {
    return this.mediaType;
  }
  
  public String getMimeType()
  {
    return this.mimeType;
  }
  
  public Entity getParent()
  {
    return this.parent;
  }
  
  public String getSubType()
  {
    return this.subType;
  }
  
  public void setParent(Entity paramEntity)
  {
    this.parent = paramEntity;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\http\entity\mime\content\AbstractContentBody.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */