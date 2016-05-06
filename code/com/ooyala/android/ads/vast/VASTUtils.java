package com.ooyala.android.ads.vast;

import com.ooyala.android.AdvertisingIdUtils;
import com.ooyala.android.util.DebugMode;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

class VASTUtils
{
  private static final List<String> DEVICEID_MACROS_TO_REPLACE = Arrays.asList(new String[] { "%5BLR_DEVICEID%5D", "[LR_DEVICEID]" });
  private static final String SEPARATOR_TIME = ":";
  private static final String TAG = "VASTUtils";
  private static final List<String> TIMESTAMP_MACROS_TO_REPLACE = Arrays.asList(new String[] { "%5BPlace_Random_Number_Here%5D", "[Place_Random_Number_Here]", "%3Cnow%3E", "%3Crand-num%3E", "[TIMESTAMP]", "%5BTIMESTAMP%5E", "[timestamp]", "%5Btimestamp%5E" });
  
  public static boolean isNullOrEmpty(String paramString)
  {
    return (paramString == null) || (paramString.equals(""));
  }
  
  private static String replaceAdIdMacros(String paramString)
  {
    String str2 = AdvertisingIdUtils.getAdvertisingId();
    String str1 = paramString;
    if (str2 != null)
    {
      Iterator localIterator = DEVICEID_MACROS_TO_REPLACE.iterator();
      for (;;)
      {
        str1 = paramString;
        if (!localIterator.hasNext()) {
          break;
        }
        paramString = paramString.replace((String)localIterator.next(), str2);
      }
    }
    return str1;
  }
  
  private static String replaceTimestampMacros(String paramString)
  {
    String str = "" + System.currentTimeMillis() / 1000L;
    Iterator localIterator = TIMESTAMP_MACROS_TO_REPLACE.iterator();
    while (localIterator.hasNext()) {
      paramString = paramString.replace((String)localIterator.next(), str);
    }
    return paramString;
  }
  
  public static double secondsFromTimeString(String paramString)
  {
    paramString = paramString.split(":");
    double d1 = 1.0D;
    double d2 = 0.0D;
    int i = paramString.length - 1;
    while (i >= 0)
    {
      d2 += Double.parseDouble(paramString[i]) * d1;
      d1 *= 60.0D;
      i -= 1;
    }
    return d2;
  }
  
  public static URL urlFromAdUrlString(String paramString)
  {
    paramString = replaceAdIdMacros(replaceTimestampMacros(paramString));
    try
    {
      paramString = new URL(paramString);
      return paramString;
    }
    catch (MalformedURLException paramString)
    {
      DebugMode.logE("VASTUtils", "Malformed VAST URL: " + null);
    }
    return null;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\ads\vast\VASTUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */