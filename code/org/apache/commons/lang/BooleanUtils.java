package org.apache.commons.lang;

import org.apache.commons.lang.math.NumberUtils;

public class BooleanUtils
{
  public static boolean isFalse(Boolean paramBoolean)
  {
    if (paramBoolean == null) {}
    while (paramBoolean.booleanValue()) {
      return false;
    }
    return true;
  }
  
  public static boolean isNotFalse(Boolean paramBoolean)
  {
    return !isFalse(paramBoolean);
  }
  
  public static boolean isNotTrue(Boolean paramBoolean)
  {
    return !isTrue(paramBoolean);
  }
  
  public static boolean isTrue(Boolean paramBoolean)
  {
    if (paramBoolean == null) {}
    while (!paramBoolean.booleanValue()) {
      return false;
    }
    return true;
  }
  
  public static Boolean negate(Boolean paramBoolean)
  {
    if (paramBoolean == null) {
      return null;
    }
    if (paramBoolean.booleanValue()) {
      return Boolean.FALSE;
    }
    return Boolean.TRUE;
  }
  
  public static boolean toBoolean(int paramInt)
  {
    return paramInt != 0;
  }
  
  public static boolean toBoolean(int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramInt1 == paramInt2) {
      return true;
    }
    if (paramInt1 == paramInt3) {
      return false;
    }
    throw new IllegalArgumentException("The Integer did not match either specified value");
  }
  
  public static boolean toBoolean(Boolean paramBoolean)
  {
    if (paramBoolean == null) {}
    while (!paramBoolean.booleanValue()) {
      return false;
    }
    return true;
  }
  
  public static boolean toBoolean(Integer paramInteger1, Integer paramInteger2, Integer paramInteger3)
  {
    if (paramInteger1 == null) {
      if (paramInteger2 != null) {}
    }
    while (paramInteger1.equals(paramInteger2))
    {
      return true;
      if (paramInteger3 != null) {
        break;
      }
      return false;
    }
    if (paramInteger1.equals(paramInteger3)) {
      return false;
    }
    throw new IllegalArgumentException("The Integer did not match either specified value");
  }
  
  public static boolean toBoolean(String paramString)
  {
    return toBoolean(toBooleanObject(paramString));
  }
  
  public static boolean toBoolean(String paramString1, String paramString2, String paramString3)
  {
    if (paramString1 == null) {
      if (paramString2 != null) {}
    }
    while (paramString1.equals(paramString2))
    {
      return true;
      if (paramString3 != null) {
        break;
      }
      return false;
    }
    if (paramString1.equals(paramString3)) {
      return false;
    }
    throw new IllegalArgumentException("The String did not match either specified value");
  }
  
  public static boolean toBooleanDefaultIfNull(Boolean paramBoolean, boolean paramBoolean1)
  {
    if (paramBoolean == null) {
      return paramBoolean1;
    }
    if (paramBoolean.booleanValue()) {}
    for (paramBoolean1 = true;; paramBoolean1 = false) {
      return paramBoolean1;
    }
  }
  
  public static Boolean toBooleanObject(int paramInt)
  {
    if (paramInt == 0) {
      return Boolean.FALSE;
    }
    return Boolean.TRUE;
  }
  
  public static Boolean toBooleanObject(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (paramInt1 == paramInt2) {
      return Boolean.TRUE;
    }
    if (paramInt1 == paramInt3) {
      return Boolean.FALSE;
    }
    if (paramInt1 == paramInt4) {
      return null;
    }
    throw new IllegalArgumentException("The Integer did not match any specified value");
  }
  
  public static Boolean toBooleanObject(Integer paramInteger)
  {
    if (paramInteger == null) {
      return null;
    }
    if (paramInteger.intValue() == 0) {
      return Boolean.FALSE;
    }
    return Boolean.TRUE;
  }
  
  public static Boolean toBooleanObject(Integer paramInteger1, Integer paramInteger2, Integer paramInteger3, Integer paramInteger4)
  {
    Object localObject = null;
    if (paramInteger1 == null)
    {
      if (paramInteger2 == null) {
        paramInteger1 = Boolean.TRUE;
      }
      do
      {
        return paramInteger1;
        if (paramInteger3 == null) {
          return Boolean.FALSE;
        }
        paramInteger1 = (Integer)localObject;
      } while (paramInteger4 == null);
    }
    do
    {
      throw new IllegalArgumentException("The Integer did not match any specified value");
      if (paramInteger1.equals(paramInteger2)) {
        return Boolean.TRUE;
      }
      if (paramInteger1.equals(paramInteger3)) {
        return Boolean.FALSE;
      }
    } while (!paramInteger1.equals(paramInteger4));
    return null;
  }
  
  public static Boolean toBooleanObject(String paramString)
  {
    if (paramString == "true") {
      return Boolean.TRUE;
    }
    if (paramString == null) {
      return null;
    }
    switch (paramString.length())
    {
    }
    int i;
    int j;
    int k;
    int m;
    int n;
    do
    {
      do
      {
        do
        {
          do
          {
            do
            {
              return null;
              i = paramString.charAt(0);
              if ((i == 121) || (i == 89) || (i == 116) || (i == 84)) {
                return Boolean.TRUE;
              }
            } while ((i != 110) && (i != 78) && (i != 102) && (i != 70));
            return Boolean.FALSE;
            i = paramString.charAt(0);
            j = paramString.charAt(1);
            if (((i == 111) || (i == 79)) && ((j == 110) || (j == 78))) {
              return Boolean.TRUE;
            }
          } while (((i != 110) && (i != 78)) || ((j != 111) && (j != 79)));
          return Boolean.FALSE;
          i = paramString.charAt(0);
          j = paramString.charAt(1);
          k = paramString.charAt(2);
          if (((i == 121) || (i == 89)) && ((j == 101) || (j == 69)) && ((k == 115) || (k == 83))) {
            return Boolean.TRUE;
          }
        } while (((i != 111) && (i != 79)) || ((j != 102) && (j != 70)) || ((k != 102) && (k != 70)));
        return Boolean.FALSE;
        i = paramString.charAt(0);
        j = paramString.charAt(1);
        k = paramString.charAt(2);
        m = paramString.charAt(3);
      } while (((i != 116) && (i != 84)) || ((j != 114) && (j != 82)) || ((k != 117) && (k != 85)) || ((m != 101) && (m != 69)));
      return Boolean.TRUE;
      i = paramString.charAt(0);
      j = paramString.charAt(1);
      k = paramString.charAt(2);
      m = paramString.charAt(3);
      n = paramString.charAt(4);
    } while (((i != 102) && (i != 70)) || ((j != 97) && (j != 65)) || ((k != 108) && (k != 76)) || ((m != 115) && (m != 83)) || ((n != 101) && (n != 69)));
    return Boolean.FALSE;
  }
  
  public static Boolean toBooleanObject(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    Object localObject = null;
    if (paramString1 == null)
    {
      if (paramString2 == null) {
        paramString1 = Boolean.TRUE;
      }
      do
      {
        return paramString1;
        if (paramString3 == null) {
          return Boolean.FALSE;
        }
        paramString1 = (String)localObject;
      } while (paramString4 == null);
    }
    do
    {
      throw new IllegalArgumentException("The String did not match any specified value");
      if (paramString1.equals(paramString2)) {
        return Boolean.TRUE;
      }
      if (paramString1.equals(paramString3)) {
        return Boolean.FALSE;
      }
    } while (!paramString1.equals(paramString4));
    return null;
  }
  
  public static Boolean toBooleanObject(boolean paramBoolean)
  {
    if (paramBoolean) {
      return Boolean.TRUE;
    }
    return Boolean.FALSE;
  }
  
  public static int toInteger(Boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramBoolean == null) {
      return paramInt3;
    }
    if (paramBoolean.booleanValue()) {}
    for (;;)
    {
      return paramInt1;
      paramInt1 = paramInt2;
    }
  }
  
  public static int toInteger(boolean paramBoolean)
  {
    if (paramBoolean) {
      return 1;
    }
    return 0;
  }
  
  public static int toInteger(boolean paramBoolean, int paramInt1, int paramInt2)
  {
    if (paramBoolean) {
      return paramInt1;
    }
    return paramInt2;
  }
  
  public static Integer toIntegerObject(Boolean paramBoolean)
  {
    if (paramBoolean == null) {
      return null;
    }
    if (paramBoolean.booleanValue()) {
      return NumberUtils.INTEGER_ONE;
    }
    return NumberUtils.INTEGER_ZERO;
  }
  
  public static Integer toIntegerObject(Boolean paramBoolean, Integer paramInteger1, Integer paramInteger2, Integer paramInteger3)
  {
    if (paramBoolean == null) {
      return paramInteger3;
    }
    if (paramBoolean.booleanValue()) {}
    for (;;)
    {
      return paramInteger1;
      paramInteger1 = paramInteger2;
    }
  }
  
  public static Integer toIntegerObject(boolean paramBoolean)
  {
    if (paramBoolean) {
      return NumberUtils.INTEGER_ONE;
    }
    return NumberUtils.INTEGER_ZERO;
  }
  
  public static Integer toIntegerObject(boolean paramBoolean, Integer paramInteger1, Integer paramInteger2)
  {
    if (paramBoolean) {
      return paramInteger1;
    }
    return paramInteger2;
  }
  
  public static String toString(Boolean paramBoolean, String paramString1, String paramString2, String paramString3)
  {
    if (paramBoolean == null) {
      return paramString3;
    }
    if (paramBoolean.booleanValue()) {}
    for (;;)
    {
      return paramString1;
      paramString1 = paramString2;
    }
  }
  
  public static String toString(boolean paramBoolean, String paramString1, String paramString2)
  {
    if (paramBoolean) {
      return paramString1;
    }
    return paramString2;
  }
  
  public static String toStringOnOff(Boolean paramBoolean)
  {
    return toString(paramBoolean, "on", "off", null);
  }
  
  public static String toStringOnOff(boolean paramBoolean)
  {
    return toString(paramBoolean, "on", "off");
  }
  
  public static String toStringTrueFalse(Boolean paramBoolean)
  {
    return toString(paramBoolean, "true", "false", null);
  }
  
  public static String toStringTrueFalse(boolean paramBoolean)
  {
    return toString(paramBoolean, "true", "false");
  }
  
  public static String toStringYesNo(Boolean paramBoolean)
  {
    return toString(paramBoolean, "yes", "no", null);
  }
  
  public static String toStringYesNo(boolean paramBoolean)
  {
    return toString(paramBoolean, "yes", "no");
  }
  
  public static Boolean xor(Boolean[] paramArrayOfBoolean)
  {
    if (paramArrayOfBoolean == null) {
      throw new IllegalArgumentException("The Array must not be null");
    }
    if (paramArrayOfBoolean.length == 0) {
      throw new IllegalArgumentException("Array is empty");
    }
    try
    {
      paramArrayOfBoolean = ArrayUtils.toPrimitive(paramArrayOfBoolean);
      if (xor(paramArrayOfBoolean)) {
        return Boolean.TRUE;
      }
    }
    catch (NullPointerException paramArrayOfBoolean)
    {
      throw new IllegalArgumentException("The array must not contain any null elements");
    }
    return Boolean.FALSE;
  }
  
  public static boolean xor(boolean[] paramArrayOfBoolean)
  {
    boolean bool2 = false;
    boolean bool3 = true;
    if (paramArrayOfBoolean == null) {
      throw new IllegalArgumentException("The Array must not be null");
    }
    if (paramArrayOfBoolean.length == 0) {
      throw new IllegalArgumentException("Array is empty");
    }
    int j = 0;
    int i = 0;
    while (i < paramArrayOfBoolean.length)
    {
      int k = j;
      if (paramArrayOfBoolean[i] != 0)
      {
        bool1 = bool2;
        if (j >= 1) {
          break label84;
        }
        k = j + 1;
      }
      i += 1;
      j = k;
    }
    if (j == 1) {}
    for (boolean bool1 = bool3;; bool1 = false) {
      label84:
      return bool1;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\commons\lang\BooleanUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */