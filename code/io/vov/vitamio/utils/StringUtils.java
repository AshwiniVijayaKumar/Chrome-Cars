package io.vov.vitamio.utils;

import java.util.Arrays;
import java.util.Iterator;

public class StringUtils
{
  public static int convertToInt(String paramString)
    throws NumberFormatException
  {
    int i = 0;
    label10:
    int j;
    if (i >= paramString.length()) {
      j = paramString.length();
    }
    for (;;)
    {
      if (j <= 0) {}
      while (Character.isDigit(paramString.charAt(j - 1)))
      {
        if (j <= i) {
          break label89;
        }
        try
        {
          i = Integer.parseInt(paramString.substring(i, j));
          return i;
        }
        catch (NumberFormatException paramString)
        {
          Log.e("convertToInt", paramString);
          throw new NumberFormatException();
        }
        if (Character.isDigit(paramString.charAt(i))) {
          break label10;
        }
        i += 1;
        break;
      }
      j -= 1;
    }
    label89:
    throw new NumberFormatException();
  }
  
  public static String fixLastSlash(String paramString)
  {
    if (paramString == null) {}
    for (paramString = "/";; paramString = paramString.trim() + "/")
    {
      String str = paramString;
      if (paramString.length() > 2)
      {
        str = paramString;
        if (paramString.charAt(paramString.length() - 2) == '/') {
          str = paramString.substring(0, paramString.length() - 1);
        }
      }
      return str;
    }
  }
  
  public static String generateTime(long paramLong)
  {
    int k = (int)(paramLong / 1000L);
    int i = k % 60;
    int j = k / 60 % 60;
    k /= 3600;
    if (k > 0) {
      return String.format("%02d:%02d:%02d", new Object[] { Integer.valueOf(k), Integer.valueOf(j), Integer.valueOf(i) });
    }
    return String.format("%02d:%02d", new Object[] { Integer.valueOf(j), Integer.valueOf(i) });
  }
  
  public static String join(Iterable<? extends Object> paramIterable, CharSequence paramCharSequence)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if (paramIterable != null)
    {
      paramIterable = paramIterable.iterator();
      if (paramIterable.hasNext()) {
        localStringBuilder.append(String.valueOf(paramIterable.next()));
      }
    }
    for (;;)
    {
      if (!paramIterable.hasNext()) {
        return localStringBuilder.toString();
      }
      localStringBuilder.append(paramCharSequence).append(String.valueOf(paramIterable.next()));
    }
  }
  
  public static String join(Object[] paramArrayOfObject, CharSequence paramCharSequence)
  {
    return join(Arrays.asList(paramArrayOfObject), paramCharSequence);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\io\vov\vitamio\utils\StringUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */