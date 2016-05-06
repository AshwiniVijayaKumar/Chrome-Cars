package com.adsdk.sdk;

public class RequestException
  extends Exception
{
  private static final long serialVersionUID = 1L;
  
  public RequestException() {}
  
  public RequestException(String paramString)
  {
    super(paramString);
  }
  
  public RequestException(String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
  }
  
  public RequestException(Throwable paramThrowable)
  {
    super(paramThrowable);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\adsdk\sdk\RequestException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */