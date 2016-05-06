package org.apache.james.mime4j.parser;

import java.io.IOException;
import java.io.InputStream;
import org.apache.james.mime4j.MimeException;
import org.apache.james.mime4j.descriptor.BodyDescriptor;

public abstract interface EntityStateMachine
{
  public abstract EntityStateMachine advance()
    throws IOException, MimeException;
  
  public abstract BodyDescriptor getBodyDescriptor()
    throws IllegalStateException;
  
  public abstract InputStream getContentStream()
    throws IllegalStateException;
  
  public abstract Field getField()
    throws IllegalStateException;
  
  public abstract int getState();
  
  public abstract void setRecursionMode(int paramInt);
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\parser\EntityStateMachine.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */