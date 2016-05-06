package com.joanzapata.pdfview.util;

import android.content.Context;
import android.content.res.AssetManager;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtils
{
  public static void copy(InputStream paramInputStream, File paramFile)
    throws IOException
  {
    byte[] arrayOfByte = null;
    for (;;)
    {
      try
      {
        paramFile = new FileOutputStream(paramFile);
        try
        {
          arrayOfByte = new byte['Ѐ'];
          int i = paramInputStream.read(arrayOfByte);
          if (i != -1)
          {
            paramFile.write(arrayOfByte, 0, i);
            continue;
            if (paramInputStream == null) {}
          }
        }
        finally {}
      }
      finally
      {
        paramFile = (File)localObject1;
        Object localObject2 = localObject3;
      }
      try
      {
        paramInputStream.close();
        if (paramFile != null) {
          paramFile.close();
        }
        throw ((Throwable)localObject1);
      }
      finally
      {
        if (paramFile == null) {
          continue;
        }
        paramFile.close();
      }
    }
    if (paramInputStream != null) {}
    try
    {
      paramInputStream.close();
      return;
    }
    finally
    {
      if (paramFile != null) {
        paramFile.close();
      }
    }
  }
  
  public static File fileFromAsset(Context paramContext, String paramString)
    throws IOException
  {
    File localFile = new File(paramContext.getCacheDir(), paramString + "-pdfview.pdf");
    copy(paramContext.getAssets().open(paramString), localFile);
    return localFile;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\joanzapata\pdfview\util\FileUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */