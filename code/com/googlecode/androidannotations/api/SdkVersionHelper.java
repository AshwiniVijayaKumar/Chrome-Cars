package com.googlecode.androidannotations.api;

import android.os.Build.VERSION;

public class SdkVersionHelper
{
  public static int getSdkInt()
  {
    if (Build.VERSION.RELEASE.startsWith("1.5")) {
      return 3;
    }
    return HelperInternal.access$000();
  }
  
  private static class HelperInternal
  {
    private static int getSdkIntInternal()
    {
      return Build.VERSION.SDK_INT;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\googlecode\androidannotations\api\SdkVersionHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */