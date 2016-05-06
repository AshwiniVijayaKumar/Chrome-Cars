package com.google.zxing.oned;

import java.util.Arrays;

public final class CodaBarWriter
  extends OneDimensionalCodeWriter
{
  private static final char[] END_CHARS = { 84, 78, 42, 69 };
  private static final char[] START_CHARS = { 65, 66, 67, 68 };
  
  public boolean[] encode(String paramString)
  {
    if (!CodaBarReader.arrayContains(START_CHARS, Character.toUpperCase(paramString.charAt(0)))) {
      throw new IllegalArgumentException("Codabar should start with one of the following: " + Arrays.toString(START_CHARS));
    }
    if (!CodaBarReader.arrayContains(END_CHARS, Character.toUpperCase(paramString.charAt(paramString.length() - 1)))) {
      throw new IllegalArgumentException("Codabar should end with one of the following: " + Arrays.toString(END_CHARS));
    }
    int i = 20;
    int j = 1;
    boolean[] arrayOfBoolean;
    int k;
    if (j >= paramString.length() - 1)
    {
      arrayOfBoolean = new boolean[i + (paramString.length() - 1)];
      j = 0;
      k = 0;
      if (k >= paramString.length()) {
        return arrayOfBoolean;
      }
    }
    else
    {
      if ((Character.isDigit(paramString.charAt(j))) || (paramString.charAt(j) == '-') || (paramString.charAt(j) == '$')) {
        i += 9;
      }
      for (;;)
      {
        j += 1;
        break;
        char c = paramString.charAt(j);
        if (!CodaBarReader.arrayContains(new char[] { 47, 58, 43, 46 }, c)) {
          break label237;
        }
        i += 10;
      }
      label237:
      throw new IllegalArgumentException("Cannot encode : '" + paramString.charAt(j) + '\'');
    }
    int m = Character.toUpperCase(paramString.charAt(k));
    i = m;
    if (k == paramString.length() - 1) {}
    label343:
    int n;
    label349:
    label362:
    int i1;
    switch (m)
    {
    default: 
      i = m;
      n = 0;
      m = 0;
      if (m >= CodaBarReader.ALPHABET.length)
      {
        m = n;
        i1 = 1;
        i = 0;
        n = 0;
      }
      break;
    }
    for (;;)
    {
      if (n >= 7)
      {
        i = j;
        if (k < paramString.length() - 1)
        {
          arrayOfBoolean[j] = false;
          i = j + 1;
        }
        k += 1;
        j = i;
        break;
        i = 65;
        break label343;
        i = 66;
        break label343;
        i = 67;
        break label343;
        i = 68;
        break label343;
        if (i == CodaBarReader.ALPHABET[m])
        {
          m = CodaBarReader.CHARACTER_ENCODINGS[m];
          break label362;
        }
        m += 1;
        break label349;
      }
      arrayOfBoolean[j] = i1;
      j += 1;
      if (((m >> 6 - n & 0x1) == 0) || (i == 1))
      {
        if (i1 != 0) {}
        for (i1 = 0;; i1 = 1)
        {
          n += 1;
          i = 0;
          break;
        }
      }
      i += 1;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\oned\CodaBarWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */