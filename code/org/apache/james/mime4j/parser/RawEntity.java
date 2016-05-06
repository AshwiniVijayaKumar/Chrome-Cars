package org.apache.james.mime4j.parser;

import java.io.InputStream;
import org.apache.james.mime4j.descriptor.BodyDescriptor;

public class RawEntity
  implements EntityStateMachine
{
  private int state;
  private final InputStream stream;
  
  RawEntity(InputStream paramInputStream)
  {
    this.stream = paramInputStream;
    this.state = 2;
  }
  
  public EntityStateMachine advance()
  {
    this.state = -1;
    return null;
  }
  
  public BodyDescriptor getBodyDescriptor()
  {
    return null;
  }
  
  public InputStream getContentStream()
  {
    return this.stream;
  }
  
  public Field getField()
  {
    return null;
  }
  
  public String getFieldName()
  {
    return null;
  }
  
  public String getFieldValue()
  {
    return null;
  }
  
  public int getState()
  {
    return this.state;
  }
  
  public void setRecursionMode(int paramInt) {}
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\parser\RawEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */