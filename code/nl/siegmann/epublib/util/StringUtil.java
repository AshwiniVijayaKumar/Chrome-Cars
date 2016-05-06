package nl.siegmann.epublib.util;

public class StringUtil
{
  public static String defaultIfNull(String paramString)
  {
    return defaultIfNull(paramString, "");
  }
  
  public static String defaultIfNull(String paramString1, String paramString2)
  {
    if (paramString1 == null) {
      return paramString2;
    }
    return paramString1;
  }
  
  public static boolean endsWithIgnoreCase(String paramString1, String paramString2)
  {
    boolean bool2 = false;
    boolean bool1;
    if (isEmpty(paramString2)) {
      bool1 = true;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (isEmpty(paramString1));
      bool1 = bool2;
    } while (paramString2.length() > paramString1.length());
    return paramString1.substring(paramString1.length() - paramString2.length()).toLowerCase().endsWith(paramString2.toLowerCase());
  }
  
  public static boolean equals(String paramString1, String paramString2)
  {
    if (paramString1 == null) {
      return paramString2 == null;
    }
    return paramString1.equals(paramString2);
  }
  
  public static int hashCode(String... paramVarArgs)
  {
    int j = 31;
    int i = 0;
    while (i < paramVarArgs.length)
    {
      j ^= String.valueOf(paramVarArgs[i]).hashCode();
      i += 1;
    }
    return j;
  }
  
  public static boolean isBlank(String paramString)
  {
    if (isEmpty(paramString)) {}
    for (;;)
    {
      return true;
      int i = 0;
      while (i < paramString.length())
      {
        if (!Character.isWhitespace(paramString.charAt(i))) {
          return false;
        }
        i += 1;
      }
    }
  }
  
  public static boolean isEmpty(String paramString)
  {
    return (paramString == null) || (paramString.length() == 0);
  }
  
  public static boolean isNotBlank(String paramString)
  {
    return !isBlank(paramString);
  }
  
  public static String substringAfter(String paramString, char paramChar)
  {
    if (isEmpty(paramString)) {
      return paramString;
    }
    paramChar = paramString.indexOf(paramChar);
    if (paramChar < 0) {
      return "";
    }
    return paramString.substring(paramChar + '\001');
  }
  
  public static String substringAfterLast(String paramString, char paramChar)
  {
    if (isEmpty(paramString)) {
      return paramString;
    }
    paramChar = paramString.lastIndexOf(paramChar);
    if (paramChar < 0) {
      return "";
    }
    return paramString.substring(paramChar + '\001');
  }
  
  public static String substringBefore(String paramString, char paramChar)
  {
    if (isEmpty(paramString)) {}
    do
    {
      return paramString;
      paramChar = paramString.indexOf(paramChar);
    } while (paramChar < 0);
    return paramString.substring(0, paramChar);
  }
  
  public static String substringBeforeLast(String paramString, char paramChar)
  {
    if (isEmpty(paramString)) {}
    do
    {
      return paramString;
      paramChar = paramString.lastIndexOf(paramChar);
    } while (paramChar < 0);
    return paramString.substring(0, paramChar);
  }
  
  public static String toString(Object... paramVarArgs)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append('[');
    int i = 0;
    if (i < paramVarArgs.length)
    {
      if (i > 0) {
        localStringBuilder.append(", ");
      }
      localStringBuilder.append(paramVarArgs[i]);
      localStringBuilder.append(": ");
      Object localObject = null;
      if (i + 1 < paramVarArgs.length) {
        localObject = paramVarArgs[(i + 1)];
      }
      if (localObject == null) {
        localStringBuilder.append("<null>");
      }
      for (;;)
      {
        i += 2;
        break;
        localStringBuilder.append('\'');
        localStringBuilder.append(localObject);
        localStringBuilder.append('\'');
      }
    }
    localStringBuilder.append(']');
    return localStringBuilder.toString();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\nl\siegmann\epublib\util\StringUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */