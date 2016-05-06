package io.vov.vitamio;

import android.content.Context;
import io.vov.vitamio.utils.ContextUtils;

public class Vitamio
{
  private static String vitamioLibraryPath;
  private static String vitamioPackage;
  
  public static final String getLibraryPath()
  {
    return vitamioLibraryPath;
  }
  
  public static String getVitamioPackage()
  {
    return vitamioPackage;
  }
  
  public static boolean isInitialized(Context paramContext)
  {
    vitamioPackage = paramContext.getPackageName();
    vitamioLibraryPath = ContextUtils.getDataDir(paramContext) + "lib/";
    return true;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\io\vov\vitamio\Vitamio.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */