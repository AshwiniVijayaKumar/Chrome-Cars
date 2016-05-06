package io.vov.vitamio.utils;

import android.net.Uri;
import android.text.TextUtils;
import java.io.File;
import java.io.IOException;

public class FileUtils
{
  private static final String FILE_NAME_RESERVED = "|\\?*<\":>+[]/'";
  
  public static void deleteDir(File paramFile)
  {
    File[] arrayOfFile;
    int j;
    int i;
    if ((paramFile.exists()) && (paramFile.isDirectory()))
    {
      arrayOfFile = paramFile.listFiles();
      j = arrayOfFile.length;
      i = 0;
    }
    for (;;)
    {
      if (i >= j)
      {
        paramFile.delete();
        return;
      }
      File localFile = arrayOfFile[i];
      if (localFile.isDirectory()) {
        deleteDir(localFile);
      }
      localFile.delete();
      i += 1;
    }
  }
  
  public static String getCanonical(File paramFile)
  {
    if (paramFile == null) {
      return null;
    }
    try
    {
      String str = paramFile.getCanonicalPath();
      return str;
    }
    catch (IOException localIOException) {}
    return paramFile.getAbsolutePath();
  }
  
  public static String getName(String paramString)
  {
    paramString = getPath(paramString);
    if (paramString != null) {
      return new File(paramString).getName();
    }
    return null;
  }
  
  public static String getPath(String paramString)
  {
    Log.i("FileUtils#getPath(%s)", new Object[] { paramString });
    if (TextUtils.isEmpty(paramString)) {
      return null;
    }
    if ((paramString.startsWith("file://")) && (paramString.length() > 7)) {
      return Uri.decode(paramString.substring(7));
    }
    return Uri.decode(paramString);
  }
  
  public static String getUniqueFileName(String paramString1, String paramString2)
  {
    Object localObject = new StringBuilder();
    paramString1 = paramString1.toCharArray();
    int j = paramString1.length;
    int i = 0;
    for (;;)
    {
      if (i >= j)
      {
        localObject = ((StringBuilder)localObject).toString();
        paramString1 = (String)localObject;
        if (((String)localObject).length() > 16) {
          paramString1 = ((String)localObject).substring(0, 16);
        }
        paramString2 = Crypto.md5(paramString2);
        paramString1 = paramString1 + paramString2;
      }
      try
      {
        localObject = File.createTempFile(paramString1, null);
        if (!((File)localObject).exists()) {
          break;
        }
        ((File)localObject).delete();
        return paramString1;
      }
      catch (IOException paramString1) {}
      Character localCharacter = Character.valueOf(paramString1[i]);
      if ("|\\?*<\":>+[]/'".indexOf(localCharacter.charValue()) == -1) {
        ((StringBuilder)localObject).append(localCharacter);
      }
      i += 1;
    }
    return paramString2;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\io\vov\vitamio\utils\FileUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */