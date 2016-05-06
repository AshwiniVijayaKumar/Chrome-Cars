package org.apache.james.mime4j;

import java.io.IOException;

public class MimeIOException
  extends IOException
{
  private static final long serialVersionUID = 5393613459533735409L;
  
  public MimeIOException(String paramString)
  {
    this(new MimeException(paramString));
  }
  
  public MimeIOException(MimeException paramMimeException)
  {
    super(paramMimeException.getMessage());
    initCause(paramMimeException);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\MimeIOException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */