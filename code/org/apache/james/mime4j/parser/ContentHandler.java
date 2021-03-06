package org.apache.james.mime4j.parser;

import java.io.IOException;
import java.io.InputStream;
import org.apache.james.mime4j.MimeException;
import org.apache.james.mime4j.descriptor.BodyDescriptor;

public abstract interface ContentHandler
{
  public abstract void body(BodyDescriptor paramBodyDescriptor, InputStream paramInputStream)
    throws MimeException, IOException;
  
  public abstract void endBodyPart()
    throws MimeException;
  
  public abstract void endHeader()
    throws MimeException;
  
  public abstract void endMessage()
    throws MimeException;
  
  public abstract void endMultipart()
    throws MimeException;
  
  public abstract void epilogue(InputStream paramInputStream)
    throws MimeException, IOException;
  
  public abstract void field(Field paramField)
    throws MimeException;
  
  public abstract void preamble(InputStream paramInputStream)
    throws MimeException, IOException;
  
  public abstract void raw(InputStream paramInputStream)
    throws MimeException, IOException;
  
  public abstract void startBodyPart()
    throws MimeException;
  
  public abstract void startHeader()
    throws MimeException;
  
  public abstract void startMessage()
    throws MimeException;
  
  public abstract void startMultipart(BodyDescriptor paramBodyDescriptor)
    throws MimeException;
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\parser\ContentHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */