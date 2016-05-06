package io.vov.vitamio;

import android.app.Activity;
import android.content.Intent;

public final class LibsChecker
{
  public static final String FROM_ME = "fromVitamioInitActivity";
  
  public static final boolean checkVitamioLibs(Activity paramActivity)
  {
    try
    {
      if ((!Vitamio.isInitialized(paramActivity)) && (!paramActivity.getIntent().getBooleanExtra("fromVitamioInitActivity", false)))
      {
        Intent localIntent = new Intent();
        localIntent.setClassName(Vitamio.getVitamioPackage(), "io.vov.vitamio.activity.InitActivity");
        localIntent.putExtras(paramActivity.getIntent());
        localIntent.setData(paramActivity.getIntent().getData());
        localIntent.putExtra("package", paramActivity.getPackageName());
        localIntent.putExtra("className", paramActivity.getClass().getName());
        paramActivity.startActivity(localIntent);
        paramActivity.finish();
        return false;
      }
    }
    catch (Exception paramActivity) {}
    return true;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\io\vov\vitamio\LibsChecker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */