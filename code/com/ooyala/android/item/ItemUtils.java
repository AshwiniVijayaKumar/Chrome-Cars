package com.ooyala.android.item;

import com.ooyala.android.util.DebugMode;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

class ItemUtils
{
  static final String SEPARATOR_TIME = ":";
  private static final String TAG = ItemUtils.class.getName();
  
  private static double bareMilliSecondsFromTimeString(String paramString)
  {
    int j = 0;
    int i = 0;
    if (i < paramString.length())
    {
      int k = paramString.charAt(i);
      if ((k >= 48) && (k <= 57)) {}
      do
      {
        i += 1;
        break;
        if (k != 46) {
          break label97;
        }
        j += 1;
      } while ((j <= 1) && (i != 0));
      DebugMode.assertFail(TAG, "bareMilliSecondsFromTimeString invalid format: " + paramString);
      return 0.0D;
    }
    label97:
    if (paramString.charAt(i - 1) == '.')
    {
      DebugMode.assertFail(TAG, "bareMilliSecondsFromTimeString invalid format: " + paramString);
      return 0.0D;
    }
    double d3 = Double.valueOf(paramString.substring(0, i)).doubleValue();
    double d2 = 1.0D;
    double d1 = d2;
    String str;
    if (i < paramString.length())
    {
      str = paramString.substring(i);
      if (!str.equals("h")) {
        break label192;
      }
      d1 = 3600.0D;
    }
    for (;;)
    {
      return d3 * d1;
      label192:
      if (str.equals("m"))
      {
        d1 = 60.0D;
      }
      else
      {
        d1 = d2;
        if (!str.equals("s")) {
          if (str.equals("ms"))
          {
            d1 = 0.001D;
          }
          else
          {
            if (!str.equals("f")) {
              break;
            }
            d1 = 0.03333333333333333D;
          }
        }
      }
    }
    DebugMode.assertFail(TAG, "invalid cc bare time string, unknown time metric: " + paramString);
    return 0.0D;
  }
  
  static boolean isNullOrEmpty(String paramString)
  {
    return (paramString == null) || (paramString.equals(""));
  }
  
  static Map<String, String> mapFromJSONObject(JSONObject paramJSONObject)
  {
    HashMap localHashMap = new HashMap();
    if (paramJSONObject == null) {}
    for (;;)
    {
      return localHashMap;
      Iterator localIterator = paramJSONObject.keys();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        try
        {
          localHashMap.put(str, paramJSONObject.getString(str));
        }
        catch (JSONException localJSONException) {}
      }
    }
  }
  
  static double secondsFromTimeString(String paramString)
  {
    if (paramString == null)
    {
      DebugMode.assertFail(TAG, "secondsFromTimeString the string is null");
      d3 = 0.0D;
    }
    String[] arrayOfString;
    double d1;
    int i;
    double d2;
    do
    {
      return d3;
      arrayOfString = paramString.split(":");
      d1 = 1.0D;
      d3 = 0.0D;
      switch (arrayOfString.length)
      {
      default: 
        DebugMode.assertFail(TAG, "invalid time format: " + paramString);
        return 0.0D;
      case 1: 
        return bareMilliSecondsFromTimeString(paramString);
      case 4: 
        d1 = 0.03333333333333333D;
      }
      i = arrayOfString.length - 1;
      d2 = d1;
      d1 = d3;
      d3 = d1;
    } while (i < 0);
    double d3 = d1;
    if (arrayOfString[i].length() > 0) {}
    for (;;)
    {
      try
      {
        d3 = Double.valueOf(arrayOfString[i]).doubleValue();
        d3 = d1 + d3 * d2;
        if (d2 < 1.0D)
        {
          d2 = 1.0D;
          i -= 1;
          d1 = d3;
        }
      }
      catch (NumberFormatException paramString)
      {
        DebugMode.assertFail(TAG, paramString.toString());
        return 0.0D;
      }
      d2 *= 60.0D;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\item\ItemUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */