package com.google.android.gms.analytics;

import android.text.TextUtils;

class x
{
  private String tK;
  private final long tL;
  private final long tM;
  private String tN = "https:";
  
  x(String paramString, long paramLong1, long paramLong2)
  {
    this.tK = paramString;
    this.tL = paramLong1;
    this.tM = paramLong2;
  }
  
  void E(String paramString)
  {
    this.tK = paramString;
  }
  
  void F(String paramString)
  {
    if ((paramString == null) || (TextUtils.isEmpty(paramString.trim()))) {}
    while (!paramString.toLowerCase().startsWith("http:")) {
      return;
    }
    this.tN = "http:";
  }
  
  String ch()
  {
    return this.tK;
  }
  
  long ci()
  {
    return this.tL;
  }
  
  long cj()
  {
    return this.tM;
  }
  
  String ck()
  {
    return this.tN;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\analytics\x.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */