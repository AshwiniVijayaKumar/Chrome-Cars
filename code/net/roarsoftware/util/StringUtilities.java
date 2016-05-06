package net.roarsoftware.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtilities
{
  private static Pattern MBID_PATTERN = Pattern.compile("^[0-9a-f]{8}\\-[0-9a-f]{4}\\-[0-9a-f]{4}\\-[0-9a-f]{4}\\-[0-9a-f]{12}$", 2);
  private static final Pattern MD5_PATTERN = Pattern.compile("[a-zA-Z0-9]{32}");
  private static MessageDigest digest;
  
  static
  {
    try
    {
      digest = MessageDigest.getInstance("MD5");
      return;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException) {}
  }
  
  public static String cleanUp(String paramString)
  {
    return paramString.replaceAll("[*:/\\\\?|<>\"]", "-");
  }
  
  public static String decode(String paramString)
  {
    try
    {
      paramString = URLDecoder.decode(paramString, "UTF-8");
      return paramString;
    }
    catch (UnsupportedEncodingException paramString) {}
    return null;
  }
  
  public static String encode(String paramString)
  {
    try
    {
      paramString = URLEncoder.encode(paramString, "UTF-8");
      return paramString;
    }
    catch (UnsupportedEncodingException paramString) {}
    return null;
  }
  
  public static boolean isMD5(String paramString)
  {
    return (paramString.length() == 32) && (MD5_PATTERN.matcher(paramString).matches());
  }
  
  public static boolean isMbid(String paramString)
  {
    return (paramString.length() == 36) && (MBID_PATTERN.matcher(paramString).matches());
  }
  
  public static Map<String, String> map(String... paramVarArgs)
  {
    if (paramVarArgs.length % 2 != 0) {
      throw new IllegalArgumentException("strings.length % 2 != 0");
    }
    HashMap localHashMap = new HashMap();
    int i = 0;
    while (i < paramVarArgs.length)
    {
      localHashMap.put(paramVarArgs[i], paramVarArgs[(i + 1)]);
      i += 2;
    }
    return localHashMap;
  }
  
  public static String md5(String paramString)
  {
    try
    {
      paramString = digest.digest(paramString.getBytes("UTF-8"));
      StringBuilder localStringBuilder = new StringBuilder(32);
      int j = paramString.length;
      int i = 0;
      while (i < j)
      {
        String str = Integer.toHexString(paramString[i] & 0xFF);
        if (str.length() == 1) {
          localStringBuilder.append('0');
        }
        localStringBuilder.append(str);
        i += 1;
      }
      paramString = localStringBuilder.toString();
      return paramString;
    }
    catch (UnsupportedEncodingException paramString) {}
    return null;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\net\roarsoftware\util\StringUtilities.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */