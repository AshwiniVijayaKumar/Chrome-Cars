package org.apache.james.mime4j.message;

import java.io.IOException;
import java.io.OutputStream;

public abstract class SingleBody
  implements Body
{
  private Entity parent = null;
  
  public SingleBody copy()
  {
    throw new UnsupportedOperationException();
  }
  
  public void dispose() {}
  
  public Entity getParent()
  {
    return this.parent;
  }
  
  public void setParent(Entity paramEntity)
  {
    this.parent = paramEntity;
  }
  
  public abstract void writeTo(OutputStream paramOutputStream)
    throws IOException;
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\message\SingleBody.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */