package com.google.android.gms.analytics;

import android.util.Log;

class l
  implements Logger
{
  private int rc = 1;
  
  private String z(String paramString)
  {
    return Thread.currentThread().toString() + ": " + paramString;
  }
  
  public void error(Exception paramException)
  {
    if (this.rc <= 3) {
      Log.e("GAV3", null, paramException);
    }
  }
  
  public void error(String paramString)
  {
    if (this.rc <= 3) {
      Log.e("GAV3", z(paramString));
    }
  }
  
  public int getLogLevel()
  {
    return this.rc;
  }
  
  public void info(String paramString)
  {
    if (this.rc <= 1) {
      Log.i("GAV3", z(paramString));
    }
  }
  
  public void setLogLevel(int paramInt)
  {
    this.rc = paramInt;
  }
  
  public void verbose(String paramString)
  {
    if (this.rc <= 0) {
      Log.v("GAV3", z(paramString));
    }
  }
  
  public void warn(String paramString)
  {
    if (this.rc <= 2) {
      Log.w("GAV3", z(paramString));
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\analytics\l.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */