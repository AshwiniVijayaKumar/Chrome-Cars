package org.slf4j.impl;

import android.util.Log;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import org.slf4j.ILoggerFactory;

public class AndroidLoggerFactory
  implements ILoggerFactory
{
  static final int TAG_MAX_LENGTH = 23;
  private final Map<String, AndroidLogger> loggerMap = new HashMap();
  
  private final String forceValidName(String paramString)
  {
    Object localObject = paramString;
    if (paramString != null)
    {
      localObject = paramString;
      if (paramString.length() > 23)
      {
        localObject = new StringTokenizer(paramString, ".");
        if (((StringTokenizer)localObject).hasMoreTokens()) {
          paramString = new StringBuilder();
        }
      }
    }
    for (;;)
    {
      String str = ((StringTokenizer)localObject).nextToken();
      if (str.length() == 1)
      {
        paramString.append(str);
        paramString.append('.');
      }
      while (!((StringTokenizer)localObject).hasMoreTokens())
      {
        paramString = paramString.toString();
        localObject = paramString;
        if (paramString.length() > 23) {
          localObject = paramString.substring(0, 22) + '*';
        }
        return (String)localObject;
        if (((StringTokenizer)localObject).hasMoreTokens())
        {
          paramString.append(str.charAt(0));
          paramString.append("*.");
        }
        else
        {
          paramString.append(str);
        }
      }
    }
  }
  
  public AndroidLogger getLogger(String paramString)
  {
    synchronized (???.forceValidName(paramString))
    {
      String str;
      AndroidLogger localAndroidLogger2 = (AndroidLogger)???.loggerMap.get(str);
      AndroidLogger localAndroidLogger1 = localAndroidLogger2;
      if (localAndroidLogger2 == null)
      {
        if (!str.equals(paramString)) {
          Log.i(AndroidLoggerFactory.class.getSimpleName(), "Logger name '" + paramString + "' exceeds maximum length of " + 23 + " characters, using '" + str + "' instead.");
        }
        localAndroidLogger1 = new AndroidLogger(str);
      }
      try
      {
        ???.loggerMap.put(str, localAndroidLogger1);
        return localAndroidLogger1;
      }
      finally {}
      throw paramString;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\slf4j\impl\AndroidLoggerFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */