package org.apache.http.entity.mime.content;

import org.apache.james.mime4j.descriptor.ContentDescriptor;
import org.apache.james.mime4j.message.Body;

public abstract interface ContentBody
  extends ContentDescriptor, Body
{
  public abstract String getFilename();
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\http\entity\mime\content\ContentBody.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */