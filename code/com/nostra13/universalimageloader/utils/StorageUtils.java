package com.nostra13.universalimageloader.utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.io.File;
import java.io.IOException;

public final class StorageUtils
{
  private static final String INDIVIDUAL_DIR_NAME = "uil-images";
  
  public static File getCacheDirectory(Context paramContext)
  {
    File localFile1 = null;
    if (Environment.getExternalStorageState().equals("mounted")) {
      localFile1 = getExternalCacheDir(paramContext);
    }
    File localFile2 = localFile1;
    if (localFile1 == null) {
      localFile2 = paramContext.getCacheDir();
    }
    return localFile2;
  }
  
  private static File getExternalCacheDir(Context paramContext)
  {
    File localFile2 = new File(new File(Environment.getExternalStorageDirectory(), "Android"), "data");
    File localFile1 = new File(new File(localFile2, paramContext.getPackageName()), "cache");
    paramContext = localFile1;
    if (!localFile1.exists()) {}
    try
    {
      new File(localFile2, ".nomedia").createNewFile();
      paramContext = localFile1;
      if (!localFile1.mkdirs())
      {
        Log.w(ImageLoader.TAG, "Unable to create external cache directory");
        paramContext = null;
      }
      return paramContext;
    }
    catch (IOException paramContext)
    {
      for (;;)
      {
        Log.w(ImageLoader.TAG, "Can't create \".nomedia\" file in application external cache directory", paramContext);
      }
    }
  }
  
  public static File getIndividualCacheDirectory(Context paramContext)
  {
    File localFile1 = getCacheDirectory(paramContext);
    File localFile2 = new File(localFile1, "uil-images");
    paramContext = localFile2;
    if (!localFile2.exists())
    {
      paramContext = localFile2;
      if (!localFile2.mkdir()) {
        paramContext = localFile1;
      }
    }
    return paramContext;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\nostra13\universalimageloader\utils\StorageUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */