package io.vov.vitamio.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class ContextUtils
{
  public static String fixLastSlash(String paramString)
  {
    if (paramString == null) {}
    for (paramString = "/";; paramString = paramString.trim() + "/")
    {
      String str = paramString;
      if (paramString.length() > 2)
      {
        str = paramString;
        if (paramString.charAt(paramString.length() - 2) == '/') {
          str = paramString.substring(0, paramString.length() - 1);
        }
      }
      return str;
    }
  }
  
  public static String getDataDir(Context paramContext)
  {
    paramContext = paramContext.getApplicationInfo();
    if (paramContext.dataDir != null) {
      return fixLastSlash(paramContext.dataDir);
    }
    return "/data/data/" + paramContext.packageName + "/";
  }
  
  public static int getVersionCode(Context paramContext)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramContext.getApplicationInfo().packageName, 0).versionCode;
      return i;
    }
    catch (Exception paramContext)
    {
      Log.e("getVersionInt", paramContext);
    }
    return 0;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\io\vov\vitamio\utils\ContextUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */