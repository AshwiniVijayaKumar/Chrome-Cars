package com.google.android.gms.analytics;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

class g
  implements m
{
  private static Object qI = new Object();
  private static g qV;
  protected String qR;
  protected String qS;
  protected String qT;
  protected String qU;
  
  protected g() {}
  
  private g(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    this.qT = paramContext.getPackageName();
    this.qU = localPackageManager.getInstallerPackageName(this.qT);
    String str = this.qT;
    localObject2 = null;
    localObject1 = str;
    try
    {
      PackageInfo localPackageInfo = localPackageManager.getPackageInfo(paramContext.getPackageName(), 0);
      paramContext = (Context)localObject2;
      localObject1 = str;
      if (localPackageInfo != null)
      {
        localObject1 = str;
        paramContext = localPackageManager.getApplicationLabel(localPackageInfo.applicationInfo).toString();
        localObject1 = paramContext;
        str = localPackageInfo.versionName;
        localObject1 = paramContext;
        paramContext = str;
      }
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;)
      {
        aa.t("Error retrieving package info: appName set to " + (String)localObject1);
        paramContext = (Context)localObject2;
      }
    }
    this.qR = ((String)localObject1);
    this.qS = paramContext;
  }
  
  public static g bt()
  {
    return qV;
  }
  
  public static void n(Context paramContext)
  {
    synchronized (qI)
    {
      if (qV == null) {
        qV = new g(paramContext);
      }
      return;
    }
  }
  
  public String getValue(String paramString)
  {
    if (paramString == null) {}
    do
    {
      return null;
      if (paramString.equals("&an")) {
        return this.qR;
      }
      if (paramString.equals("&av")) {
        return this.qS;
      }
      if (paramString.equals("&aid")) {
        return this.qT;
      }
    } while (!paramString.equals("&aiid"));
    return this.qU;
  }
  
  public boolean x(String paramString)
  {
    return ("&an".equals(paramString)) || ("&av".equals(paramString)) || ("&aid".equals(paramString)) || ("&aiid".equals(paramString));
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\analytics\g.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */