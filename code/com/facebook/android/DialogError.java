package com.facebook.android;

public class DialogError
  extends Throwable
{
  private static final long serialVersionUID = 1L;
  private int mErrorCode;
  private String mFailingUrl;
  
  public DialogError(String paramString1, int paramInt, String paramString2)
  {
    super(paramString1);
    this.mErrorCode = paramInt;
    this.mFailingUrl = paramString2;
  }
  
  int getErrorCode()
  {
    return this.mErrorCode;
  }
  
  String getFailingUrl()
  {
    return this.mFailingUrl;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\facebook\android\DialogError.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */