package org.apache.james.mime4j;

public class MimeException
  extends Exception
{
  private static final long serialVersionUID = 8352821278714188542L;
  
  public MimeException(String paramString)
  {
    super(paramString);
  }
  
  public MimeException(String paramString, Throwable paramThrowable)
  {
    super(paramString);
    initCause(paramThrowable);
  }
  
  public MimeException(Throwable paramThrowable)
  {
    super(paramThrowable);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\MimeException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */