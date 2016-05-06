package com.facebook.internal;

public class InternalSettings
{
  private static volatile String mCustomUserAgent;
  
  public static String getCustomUserAgent()
  {
    return mCustomUserAgent;
  }
  
  public static void setCustomUserAgent(String paramString)
  {
    mCustomUserAgent = paramString;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\facebook\internal\InternalSettings.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */