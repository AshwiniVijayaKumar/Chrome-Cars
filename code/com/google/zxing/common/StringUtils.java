package com.google.zxing.common;

import com.google.zxing.DecodeHintType;
import java.util.Map;

public final class StringUtils
{
  private static final boolean ASSUME_SHIFT_JIS;
  private static final String EUC_JP = "EUC_JP";
  public static final String GB2312 = "GB2312";
  private static final String ISO88591 = "ISO8859_1";
  private static final String PLATFORM_DEFAULT_ENCODING = System.getProperty("file.encoding");
  public static final String SHIFT_JIS = "SJIS";
  private static final String UTF8 = "UTF8";
  
  static
  {
    if ((!"SJIS".equalsIgnoreCase(PLATFORM_DEFAULT_ENCODING)) && (!"EUC_JP".equalsIgnoreCase(PLATFORM_DEFAULT_ENCODING))) {}
    for (boolean bool = false;; bool = true)
    {
      ASSUME_SHIFT_JIS = bool;
      return;
    }
  }
  
  public static String guessEncoding(byte[] paramArrayOfByte, Map<DecodeHintType, ?> paramMap)
  {
    if (paramMap != null)
    {
      paramMap = (String)paramMap.get(DecodeHintType.CHARACTER_SET);
      if (paramMap != null) {
        return paramMap;
      }
    }
    int i21 = paramArrayOfByte.length;
    int i15 = 1;
    int n = 1;
    int i1 = 1;
    int i16 = 0;
    int i2 = 0;
    int m = 0;
    int k = 0;
    int i8 = 0;
    int i6 = 0;
    int i9 = 0;
    int i10 = 0;
    int i4 = 0;
    int i5 = 0;
    int i14 = 0;
    if ((paramArrayOfByte.length > 3) && (paramArrayOfByte[0] == -17) && (paramArrayOfByte[1] == -69) && (paramArrayOfByte[2] == -65)) {}
    int i7;
    for (int i3 = 1;; i3 = 0)
    {
      i7 = 0;
      if ((i7 < i21) && ((i15 != 0) || (n != 0) || (i1 != 0))) {
        break;
      }
      i = i1;
      if (i1 != 0)
      {
        i = i1;
        if (i16 > 0) {
          i = 0;
        }
      }
      j = n;
      if (n != 0)
      {
        j = n;
        if (i8 > 0) {
          j = 0;
        }
      }
      if ((i == 0) || ((i3 == 0) && (i2 + m + k <= 0))) {
        break label995;
      }
      return "UTF8";
    }
    int i22 = paramArrayOfByte[i7] & 0xFF;
    int j = i1;
    int i11 = i2;
    int i12 = m;
    int i13 = k;
    int i = i16;
    label256:
    int i17;
    label291:
    int i18;
    int i19;
    int i20;
    if (i1 != 0)
    {
      if (i16 <= 0) {
        break label464;
      }
      if ((i22 & 0x80) == 0)
      {
        j = 0;
        i = i16;
        i13 = k;
        i12 = m;
        i11 = i2;
      }
    }
    else
    {
      i17 = i15;
      i16 = i14;
      if (i15 != 0)
      {
        if ((i22 <= 127) || (i22 >= 160)) {
          break label633;
        }
        i17 = 0;
        i16 = i14;
      }
      i1 = n;
      i2 = i8;
      k = i10;
      m = i9;
      i18 = i6;
      i19 = i5;
      i20 = i4;
      if (n != 0)
      {
        if (i8 <= 0) {
          break label727;
        }
        if ((i22 >= 64) && (i22 != 127) && (i22 <= 252)) {
          break label694;
        }
        i1 = 0;
        i20 = i4;
        i19 = i5;
        i18 = i6;
        m = i9;
        k = i10;
        i2 = i8;
      }
    }
    for (;;)
    {
      i7 += 1;
      i15 = i17;
      n = i1;
      i1 = j;
      i14 = i16;
      i8 = i2;
      i10 = k;
      i9 = m;
      i6 = i18;
      i5 = i19;
      i4 = i20;
      i2 = i11;
      m = i12;
      k = i13;
      i16 = i;
      break;
      i = i16 - 1;
      j = i1;
      i11 = i2;
      i12 = m;
      i13 = k;
      break label256;
      label464:
      j = i1;
      i11 = i2;
      i12 = m;
      i13 = k;
      i = i16;
      if ((i22 & 0x80) == 0) {
        break label256;
      }
      if ((i22 & 0x40) == 0)
      {
        j = 0;
        i11 = i2;
        i12 = m;
        i13 = k;
        i = i16;
        break label256;
      }
      i = i16 + 1;
      if ((i22 & 0x20) == 0)
      {
        i11 = i2 + 1;
        j = i1;
        i12 = m;
        i13 = k;
        break label256;
      }
      i += 1;
      if ((i22 & 0x10) == 0)
      {
        i12 = m + 1;
        j = i1;
        i11 = i2;
        i13 = k;
        break label256;
      }
      i += 1;
      if ((i22 & 0x8) == 0)
      {
        i13 = k + 1;
        j = i1;
        i11 = i2;
        i12 = m;
        break label256;
      }
      j = 0;
      i11 = i2;
      i12 = m;
      i13 = k;
      break label256;
      label633:
      i17 = i15;
      i16 = i14;
      if (i22 <= 159) {
        break label291;
      }
      if ((i22 >= 192) && (i22 != 215))
      {
        i17 = i15;
        i16 = i14;
        if (i22 != 247) {
          break label291;
        }
      }
      i16 = i14 + 1;
      i17 = i15;
      break label291;
      label694:
      i2 = i8 - 1;
      i1 = n;
      k = i10;
      m = i9;
      i18 = i6;
      i19 = i5;
      i20 = i4;
      continue;
      label727:
      if ((i22 == 128) || (i22 == 160) || (i22 > 239))
      {
        i1 = 0;
        i2 = i8;
        k = i10;
        m = i9;
        i18 = i6;
        i19 = i5;
        i20 = i4;
      }
      else if ((i22 > 160) && (i22 < 224))
      {
        i10 = i6 + 1;
        i14 = 0;
        i6 = i9 + 1;
        i1 = n;
        i2 = i8;
        k = i14;
        m = i6;
        i18 = i10;
        i19 = i5;
        i20 = i4;
        if (i6 > i4)
        {
          i20 = i6;
          i1 = n;
          i2 = i8;
          k = i14;
          m = i6;
          i18 = i10;
          i19 = i5;
        }
      }
      else if (i22 > 127)
      {
        i9 = i8 + 1;
        i14 = 0;
        i8 = i10 + 1;
        i1 = n;
        i2 = i9;
        k = i8;
        m = i14;
        i18 = i6;
        i19 = i5;
        i20 = i4;
        if (i8 > i5)
        {
          i19 = i8;
          i1 = n;
          i2 = i9;
          k = i8;
          m = i14;
          i18 = i6;
          i20 = i4;
        }
      }
      else
      {
        m = 0;
        k = 0;
        i1 = n;
        i2 = i8;
        i18 = i6;
        i19 = i5;
        i20 = i4;
      }
    }
    label995:
    if ((j != 0) && ((ASSUME_SHIFT_JIS) || (i4 >= 3) || (i5 >= 3))) {
      return "SJIS";
    }
    if ((i15 != 0) && (j != 0))
    {
      if (((i4 == 2) && (i6 == 2)) || (i14 * 10 >= i21)) {}
      for (paramArrayOfByte = "SJIS";; paramArrayOfByte = "ISO8859_1") {
        return paramArrayOfByte;
      }
    }
    if (i15 != 0) {
      return "ISO8859_1";
    }
    if (j != 0) {
      return "SJIS";
    }
    if (i != 0) {
      return "UTF8";
    }
    return PLATFORM_DEFAULT_ENCODING;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\common\StringUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */