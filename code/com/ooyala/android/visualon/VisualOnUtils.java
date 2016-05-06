package com.ooyala.android.visualon;

import android.content.Context;
import android.content.res.AssetManager;
import com.ooyala.android.util.DebugMode;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

class VisualOnUtils
{
  public static final int BUFFER_SIZE = 4096;
  public static final int CONNECTION_TIMEOUT = 60000;
  public static final int READ_DATA_TIMEOUT = 60000;
  private static final String TAG = VisualOnUtils.class.getName();
  
  public static void DownloadFile(String paramString1, String paramString2)
    throws IOException
  {
    DownloadFile(paramString1, paramString2, -1);
  }
  
  public static void DownloadFile(String paramString1, String paramString2, int paramInt)
    throws IOException
  {
    DebugMode.logI(TAG, "Downloading url: " + paramString1 + ", dest: " + paramString2);
    byte[] arrayOfByte = null;
    for (;;)
    {
      try
      {
        paramString2 = new FileOutputStream(paramString2);
      }
      finally
      {
        int i;
        int j;
        paramString1 = (String)localObject;
        continue;
        if ((i == 0) && (j >= paramInt)) {
          continue;
        }
        int k = paramInt - j;
        if ((i != 0) || (k >= 4096)) {
          continue;
        }
      }
      try
      {
        arrayOfByte = new byte['က'];
        if (paramInt == -1)
        {
          i = 1;
          paramString1 = new URL(paramString1).openConnection();
          paramString1.setConnectTimeout(60000);
          paramString1.setReadTimeout(60000);
          paramString1 = paramString1.getInputStream();
          j = 0;
          continue;
          k = paramString1.read(arrayOfByte, 0, k);
          if (k == -1)
          {
            DebugMode.logI("DownloadFile", "Downloading complete: " + j + " bytes downloaded");
            if (paramString2 != null) {
              paramString2.close();
            }
          }
        }
        else
        {
          i = 0;
          continue;
          k = 4096;
          continue;
        }
        j += k;
        paramString2.write(arrayOfByte, 0, k);
      }
      finally
      {
        paramString1 = paramString2;
        paramString2 = (String)localObject;
      }
    }
    if (paramString1 != null) {
      paramString1.close();
    }
    throw paramString2;
  }
  
  public static boolean checkFileExists(String paramString)
  {
    paramString = new File(paramString);
    return (paramString != null) && (paramString.exists());
  }
  
  public static void cleanupLocalFiles(Context paramContext)
  {
    paramContext = new File(getLocalFileDir(paramContext));
    if (paramContext.listFiles() != null)
    {
      paramContext = paramContext.listFiles();
      int j = paramContext.length;
      int i = 0;
      while (i < j)
      {
        paramContext[i].delete();
        i += 1;
      }
    }
  }
  
  public static void copyFile(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      paramString1 = paramContext.getAssets().open(paramString1);
      paramContext = new File(paramContext.getFilesDir().getParentFile().getPath() + "/" + paramString2);
      paramContext.createNewFile();
      paramContext = new FileOutputStream(paramContext);
      paramString2 = new byte['က'];
      for (;;)
      {
        int i = paramString1.read(paramString2);
        if (i == -1) {
          break;
        }
        paramContext.write(paramString2, 0, i);
      }
      paramContext.flush();
    }
    catch (IOException paramContext)
    {
      DebugMode.logE(TAG, "Caught!", paramContext);
      return;
    }
    paramContext.close();
  }
  
  public static String getLocalFileDir(Context paramContext)
  {
    return paramContext.getFilesDir().getAbsolutePath() + "/Ooyala_VO";
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\visualon\VisualOnUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */