package com.google.android.gms.analytics;

public class aa
{
  private static GoogleAnalytics tV;
  
  public static boolean cm()
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (getLogger() != null)
    {
      bool1 = bool2;
      if (getLogger().getLogLevel() == 0) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  private static Logger getLogger()
  {
    if (tV == null) {
      tV = GoogleAnalytics.cf();
    }
    if (tV != null) {
      return tV.getLogger();
    }
    return null;
  }
  
  public static void t(String paramString)
  {
    Logger localLogger = getLogger();
    if (localLogger != null) {
      localLogger.error(paramString);
    }
  }
  
  public static void u(String paramString)
  {
    Logger localLogger = getLogger();
    if (localLogger != null) {
      localLogger.info(paramString);
    }
  }
  
  public static void v(String paramString)
  {
    Logger localLogger = getLogger();
    if (localLogger != null) {
      localLogger.verbose(paramString);
    }
  }
  
  public static void w(String paramString)
  {
    Logger localLogger = getLogger();
    if (localLogger != null) {
      localLogger.warn(paramString);
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\analytics\aa.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */