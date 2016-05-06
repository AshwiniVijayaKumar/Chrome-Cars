package com.google.zxing.common;

import com.google.zxing.FormatException;
import java.util.HashMap;
import java.util.Map;

public enum CharacterSetECI
{
  private static final Map<String, CharacterSetECI> NAME_TO_ECI;
  private static final Map<Integer, CharacterSetECI> VALUE_TO_ECI;
  private final String[] otherEncodingNames;
  private final int[] values;
  
  static
  {
    Object localObject1 = new int[2];
    localObject1[1] = 2;
    Cp437 = new CharacterSetECI("Cp437", 0, (int[])localObject1, new String[0]);
    ISO8859_1 = new CharacterSetECI("ISO8859_1", 1, new int[] { 1, 3 }, new String[] { "ISO-8859-1" });
    ISO8859_2 = new CharacterSetECI("ISO8859_2", 2, 4, new String[] { "ISO-8859-2" });
    ISO8859_3 = new CharacterSetECI("ISO8859_3", 3, 5, new String[] { "ISO-8859-3" });
    ISO8859_4 = new CharacterSetECI("ISO8859_4", 4, 6, new String[] { "ISO-8859-4" });
    ISO8859_5 = new CharacterSetECI("ISO8859_5", 5, 7, new String[] { "ISO-8859-5" });
    ISO8859_6 = new CharacterSetECI("ISO8859_6", 6, 8, new String[] { "ISO-8859-6" });
    ISO8859_7 = new CharacterSetECI("ISO8859_7", 7, 9, new String[] { "ISO-8859-7" });
    ISO8859_8 = new CharacterSetECI("ISO8859_8", 8, 10, new String[] { "ISO-8859-8" });
    ISO8859_9 = new CharacterSetECI("ISO8859_9", 9, 11, new String[] { "ISO-8859-9" });
    ISO8859_10 = new CharacterSetECI("ISO8859_10", 10, 12, new String[] { "ISO-8859-10" });
    ISO8859_11 = new CharacterSetECI("ISO8859_11", 11, 13, new String[] { "ISO-8859-11" });
    ISO8859_13 = new CharacterSetECI("ISO8859_13", 12, 15, new String[] { "ISO-8859-13" });
    ISO8859_14 = new CharacterSetECI("ISO8859_14", 13, 16, new String[] { "ISO-8859-14" });
    ISO8859_15 = new CharacterSetECI("ISO8859_15", 14, 17, new String[] { "ISO-8859-15" });
    ISO8859_16 = new CharacterSetECI("ISO8859_16", 15, 18, new String[] { "ISO-8859-16" });
    SJIS = new CharacterSetECI("SJIS", 16, 20, new String[] { "Shift_JIS" });
    Cp1250 = new CharacterSetECI("Cp1250", 17, 21, new String[] { "windows-1250" });
    Cp1251 = new CharacterSetECI("Cp1251", 18, 22, new String[] { "windows-1251" });
    Cp1252 = new CharacterSetECI("Cp1252", 19, 23, new String[] { "windows-1252" });
    Cp1256 = new CharacterSetECI("Cp1256", 20, 24, new String[] { "windows-1256" });
    UnicodeBigUnmarked = new CharacterSetECI("UnicodeBigUnmarked", 21, 25, new String[] { "UTF-16BE", "UnicodeBig" });
    UTF8 = new CharacterSetECI("UTF8", 22, 26, new String[] { "UTF-8" });
    ASCII = new CharacterSetECI("ASCII", 23, new int[] { 27, 170 }, new String[] { "US-ASCII" });
    Big5 = new CharacterSetECI("Big5", 24, 28);
    GB18030 = new CharacterSetECI("GB18030", 25, 29, new String[] { "GB2312", "EUC_CN", "GBK" });
    EUC_KR = new CharacterSetECI("EUC_KR", 26, 30, new String[] { "EUC-KR" });
    ENUM$VALUES = new CharacterSetECI[] { Cp437, ISO8859_1, ISO8859_2, ISO8859_3, ISO8859_4, ISO8859_5, ISO8859_6, ISO8859_7, ISO8859_8, ISO8859_9, ISO8859_10, ISO8859_11, ISO8859_13, ISO8859_14, ISO8859_15, ISO8859_16, SJIS, Cp1250, Cp1251, Cp1252, Cp1256, UnicodeBigUnmarked, UTF8, ASCII, Big5, GB18030, EUC_KR };
    VALUE_TO_ECI = new HashMap();
    NAME_TO_ECI = new HashMap();
    localObject1 = values();
    int k = localObject1.length;
    int i = 0;
    if (i >= k) {
      return;
    }
    Object localObject2 = localObject1[i];
    Object localObject3 = ((CharacterSetECI)localObject2).values;
    int m = localObject3.length;
    int j = 0;
    label946:
    if (j >= m)
    {
      NAME_TO_ECI.put(((CharacterSetECI)localObject2).name(), localObject2);
      localObject3 = ((CharacterSetECI)localObject2).otherEncodingNames;
      m = localObject3.length;
      j = 0;
    }
    for (;;)
    {
      if (j >= m)
      {
        i += 1;
        break;
        int n = localObject3[j];
        VALUE_TO_ECI.put(Integer.valueOf(n), localObject2);
        j += 1;
        break label946;
      }
      Object localObject4 = localObject3[j];
      NAME_TO_ECI.put(localObject4, localObject2);
      j += 1;
    }
  }
  
  private CharacterSetECI(int paramInt1)
  {
    this(new int[] { paramInt1 }, new String[0]);
  }
  
  private CharacterSetECI(int paramInt1, String... paramVarArgs)
  {
    this.values = new int[] { paramInt1 };
    this.otherEncodingNames = paramVarArgs;
  }
  
  private CharacterSetECI(int[] paramArrayOfInt, String... paramVarArgs)
  {
    this.values = paramArrayOfInt;
    this.otherEncodingNames = paramVarArgs;
  }
  
  public static CharacterSetECI getCharacterSetECIByName(String paramString)
  {
    return (CharacterSetECI)NAME_TO_ECI.get(paramString);
  }
  
  public static CharacterSetECI getCharacterSetECIByValue(int paramInt)
    throws FormatException
  {
    if ((paramInt < 0) || (paramInt >= 900)) {
      throw FormatException.getFormatInstance();
    }
    return (CharacterSetECI)VALUE_TO_ECI.get(Integer.valueOf(paramInt));
  }
  
  public int getValue()
  {
    return this.values[0];
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\common\CharacterSetECI.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */