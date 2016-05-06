package com.adsdk.sdk.mraid;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils
{
  public static String sha1(String paramString)
  {
    try
    {
      Object localObject = MessageDigest.getInstance("SHA-1");
      ((MessageDigest)localObject).update(paramString.getBytes());
      paramString = ((MessageDigest)localObject).digest();
      localObject = new StringBuffer();
      int i = 0;
      for (;;)
      {
        if (i >= paramString.length) {
          return ((StringBuffer)localObject).toString();
        }
        ((StringBuffer)localObject).append(Integer.toHexString(paramString[i] & 0xFF | 0x100).substring(1));
        i += 1;
      }
      return "";
    }
    catch (NoSuchAlgorithmException paramString) {}
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\adsdk\sdk\mraid\Utils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */