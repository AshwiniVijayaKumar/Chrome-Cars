package com.google.android.gms.analytics;

final class o
{
  private static String b(String paramString, int paramInt)
  {
    if (paramInt < 1)
    {
      aa.t("index out of range for " + paramString + " (" + paramInt + ")");
      return "";
    }
    return paramString + paramInt;
  }
  
  static String q(int paramInt)
  {
    return b("&cd", paramInt);
  }
  
  static String r(int paramInt)
  {
    return b("&cm", paramInt);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\analytics\o.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */