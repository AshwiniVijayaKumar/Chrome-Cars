package org.vudroid.core.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5StringUtil
{
  private static final MessageDigest digest;
  
  static
  {
    try
    {
      digest = MessageDigest.getInstance("MD5");
      return;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      throw new RuntimeException(localNoSuchAlgorithmException);
    }
  }
  
  public static String md5StringFor(String paramString)
  {
    paramString = digest.digest(paramString.getBytes());
    StringBuilder localStringBuilder = new StringBuilder();
    int j = paramString.length;
    int i = 0;
    while (i < j)
    {
      localStringBuilder.append(Integer.toString(paramString[i] & 0xFF, 16));
      i += 1;
    }
    return localStringBuilder.toString();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\vudroid\core\utils\MD5StringUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */