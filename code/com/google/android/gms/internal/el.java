package com.google.android.gms.internal;

import android.util.Log;

public final class el
{
  private final String Cd;
  
  public el(String paramString)
  {
    this.Cd = ((String)er.f(paramString));
  }
  
  public boolean Q(int paramInt)
  {
    return Log.isLoggable(this.Cd, paramInt);
  }
  
  public void a(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (Q(6)) {
      Log.e(paramString1, paramString2, paramThrowable);
    }
  }
  
  public void f(String paramString1, String paramString2)
  {
    if (Q(2)) {
      Log.v(paramString1, paramString2);
    }
  }
  
  public void g(String paramString1, String paramString2)
  {
    if (Q(5)) {
      Log.w(paramString1, paramString2);
    }
  }
  
  public void h(String paramString1, String paramString2)
  {
    if (Q(6)) {
      Log.e(paramString1, paramString2);
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\el.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */