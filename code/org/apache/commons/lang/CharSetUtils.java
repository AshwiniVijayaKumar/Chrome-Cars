package org.apache.commons.lang;

import org.apache.commons.lang.text.StrBuilder;

public class CharSetUtils
{
  public static int count(String paramString1, String paramString2)
  {
    if ((StringUtils.isEmpty(paramString1)) || (StringUtils.isEmpty(paramString2))) {
      return 0;
    }
    return count(paramString1, new String[] { paramString2 });
  }
  
  public static int count(String paramString, String[] paramArrayOfString)
  {
    int k;
    if ((StringUtils.isEmpty(paramString)) || (ArrayUtils.isEmpty(paramArrayOfString)))
    {
      k = 0;
      return k;
    }
    paramArrayOfString = CharSet.getInstance(paramArrayOfString);
    int i = 0;
    paramString = paramString.toCharArray();
    int m = paramString.length;
    int j = 0;
    for (;;)
    {
      k = i;
      if (j >= m) {
        break;
      }
      k = i;
      if (paramArrayOfString.contains(paramString[j])) {
        k = i + 1;
      }
      j += 1;
      i = k;
    }
  }
  
  public static String delete(String paramString1, String paramString2)
  {
    if ((StringUtils.isEmpty(paramString1)) || (StringUtils.isEmpty(paramString2))) {
      return paramString1;
    }
    return delete(paramString1, new String[] { paramString2 });
  }
  
  public static String delete(String paramString, String[] paramArrayOfString)
  {
    if ((StringUtils.isEmpty(paramString)) || (ArrayUtils.isEmpty(paramArrayOfString))) {
      return paramString;
    }
    return modify(paramString, paramArrayOfString, false);
  }
  
  public static CharSet evaluateSet(String[] paramArrayOfString)
  {
    if (paramArrayOfString == null) {
      return null;
    }
    return new CharSet(paramArrayOfString);
  }
  
  public static String keep(String paramString1, String paramString2)
  {
    if (paramString1 == null) {
      return null;
    }
    if ((paramString1.length() == 0) || (StringUtils.isEmpty(paramString2))) {
      return "";
    }
    return keep(paramString1, new String[] { paramString2 });
  }
  
  public static String keep(String paramString, String[] paramArrayOfString)
  {
    if (paramString == null) {
      return null;
    }
    if ((paramString.length() == 0) || (ArrayUtils.isEmpty(paramArrayOfString))) {
      return "";
    }
    return modify(paramString, paramArrayOfString, true);
  }
  
  private static String modify(String paramString, String[] paramArrayOfString, boolean paramBoolean)
  {
    paramArrayOfString = CharSet.getInstance(paramArrayOfString);
    StrBuilder localStrBuilder = new StrBuilder(paramString.length());
    paramString = paramString.toCharArray();
    int j = paramString.length;
    int i = 0;
    while (i < j)
    {
      if (paramArrayOfString.contains(paramString[i]) == paramBoolean) {
        localStrBuilder.append(paramString[i]);
      }
      i += 1;
    }
    return localStrBuilder.toString();
  }
  
  public static String squeeze(String paramString1, String paramString2)
  {
    if ((StringUtils.isEmpty(paramString1)) || (StringUtils.isEmpty(paramString2))) {
      return paramString1;
    }
    return squeeze(paramString1, new String[] { paramString2 });
  }
  
  public static String squeeze(String paramString, String[] paramArrayOfString)
  {
    if ((StringUtils.isEmpty(paramString)) || (ArrayUtils.isEmpty(paramArrayOfString))) {
      return paramString;
    }
    paramArrayOfString = CharSet.getInstance(paramArrayOfString);
    StrBuilder localStrBuilder = new StrBuilder(paramString.length());
    paramString = paramString.toCharArray();
    int j = paramString.length;
    char c2 = ' ';
    int i = 0;
    if (i < j)
    {
      char c1 = paramString[i];
      if ((paramArrayOfString.contains(c1)) && (c1 == c2) && (i != 0)) {}
      for (;;)
      {
        i += 1;
        break;
        localStrBuilder.append(c1);
        c2 = c1;
      }
    }
    return localStrBuilder.toString();
  }
  
  public static String translate(String paramString1, String paramString2, String paramString3)
  {
    if (StringUtils.isEmpty(paramString1)) {
      return paramString1;
    }
    StrBuilder localStrBuilder = new StrBuilder(paramString1.length());
    paramString1 = paramString1.toCharArray();
    char[] arrayOfChar = paramString3.toCharArray();
    int n = paramString1.length;
    int k = paramString3.length() - 1;
    int i = 0;
    if (i < n)
    {
      int m = paramString2.indexOf(paramString1[i]);
      if (m != -1)
      {
        int j = m;
        if (m > k) {
          j = k;
        }
        localStrBuilder.append(arrayOfChar[j]);
      }
      for (;;)
      {
        i += 1;
        break;
        localStrBuilder.append(paramString1[i]);
      }
    }
    return localStrBuilder.toString();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\commons\lang\CharSetUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */