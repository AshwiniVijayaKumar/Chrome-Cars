package org.apache.commons.lang;

import org.apache.commons.lang.exception.NestableRuntimeException;

public class UnhandledException
  extends NestableRuntimeException
{
  private static final long serialVersionUID = 1832101364842773720L;
  
  public UnhandledException(String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
  }
  
  public UnhandledException(Throwable paramThrowable)
  {
    super(paramThrowable);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\commons\lang\UnhandledException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */