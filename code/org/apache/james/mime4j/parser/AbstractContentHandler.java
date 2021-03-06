package org.apache.james.mime4j.parser;

import java.io.IOException;
import java.io.InputStream;
import org.apache.james.mime4j.MimeException;
import org.apache.james.mime4j.descriptor.BodyDescriptor;

public abstract class AbstractContentHandler
  implements ContentHandler
{
  public void body(BodyDescriptor paramBodyDescriptor, InputStream paramInputStream)
    throws MimeException, IOException
  {}
  
  public void endBodyPart()
    throws MimeException
  {}
  
  public void endHeader()
    throws MimeException
  {}
  
  public void endMessage()
    throws MimeException
  {}
  
  public void endMultipart()
    throws MimeException
  {}
  
  public void epilogue(InputStream paramInputStream)
    throws MimeException, IOException
  {}
  
  public void field(Field paramField)
    throws MimeException
  {}
  
  public void preamble(InputStream paramInputStream)
    throws MimeException, IOException
  {}
  
  public void raw(InputStream paramInputStream)
    throws MimeException, IOException
  {}
  
  public void startBodyPart()
    throws MimeException
  {}
  
  public void startHeader()
    throws MimeException
  {}
  
  public void startMessage()
    throws MimeException
  {}
  
  public void startMultipart(BodyDescriptor paramBodyDescriptor)
    throws MimeException
  {}
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\parser\AbstractContentHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */