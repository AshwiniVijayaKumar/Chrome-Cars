package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.Map;

public final class Code39Reader
  extends OneDReader
{
  private static final char[] ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. *$/+%".toCharArray();
  static final String ALPHABET_STRING = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. *$/+%";
  private static final int ASTERISK_ENCODING = CHARACTER_ENCODINGS[39];
  static final int[] CHARACTER_ENCODINGS = { 52, 289, 97, 352, 49, 304, 112, 37, 292, 100, 265, 73, 328, 25, 280, 88, 13, 268, 76, 28, 259, 67, 322, 19, 274, 82, 7, 262, 70, 22, 385, 193, 448, 145, 400, 208, 133, 388, 196, 148, 168, 162, 138, 42 };
  private final boolean extendedMode;
  private final boolean usingCheckDigit;
  
  public Code39Reader()
  {
    this.usingCheckDigit = false;
    this.extendedMode = false;
  }
  
  public Code39Reader(boolean paramBoolean)
  {
    this.usingCheckDigit = paramBoolean;
    this.extendedMode = false;
  }
  
  public Code39Reader(boolean paramBoolean1, boolean paramBoolean2)
  {
    this.usingCheckDigit = paramBoolean1;
    this.extendedMode = paramBoolean2;
  }
  
  private static String decodeExtended(CharSequence paramCharSequence)
    throws FormatException
  {
    int j = paramCharSequence.length();
    StringBuilder localStringBuilder = new StringBuilder(j);
    int i = 0;
    if (i >= j) {
      return localStringBuilder.toString();
    }
    char c2 = paramCharSequence.charAt(i);
    int k;
    char c1;
    if ((c2 == '+') || (c2 == '$') || (c2 == '%') || (c2 == '/'))
    {
      k = paramCharSequence.charAt(i + 1);
      c1 = '\000';
      switch (c2)
      {
      default: 
        label120:
        localStringBuilder.append(c1);
        i += 1;
      }
    }
    for (;;)
    {
      i += 1;
      break;
      if ((k >= 65) && (k <= 90))
      {
        c1 = (char)(k + 32);
        break label120;
      }
      throw FormatException.getFormatInstance();
      if ((k >= 65) && (k <= 90))
      {
        c1 = (char)(k - 64);
        break label120;
      }
      throw FormatException.getFormatInstance();
      if ((k >= 65) && (k <= 69))
      {
        c1 = (char)(k - 38);
        break label120;
      }
      if ((k >= 70) && (k <= 87))
      {
        c1 = (char)(k - 11);
        break label120;
      }
      throw FormatException.getFormatInstance();
      if ((k >= 65) && (k <= 79))
      {
        c1 = (char)(k - 32);
        break label120;
      }
      if (k == 90)
      {
        c1 = ':';
        break label120;
      }
      throw FormatException.getFormatInstance();
      localStringBuilder.append(c2);
    }
  }
  
  private static int[] findAsteriskPattern(BitArray paramBitArray, int[] paramArrayOfInt)
    throws NotFoundException
  {
    int n = paramBitArray.getSize();
    int m = paramBitArray.getNextSet(0);
    int j = 0;
    int i = m;
    int k = 0;
    int i1 = paramArrayOfInt.length;
    for (;;)
    {
      if (m >= n) {
        throw NotFoundException.getNotFoundInstance();
      }
      if (!(paramBitArray.get(m) ^ k)) {
        break;
      }
      paramArrayOfInt[j] += 1;
      m += 1;
    }
    if (j == i1 - 1)
    {
      if ((toNarrowWidePattern(paramArrayOfInt) == ASTERISK_ENCODING) && (paramBitArray.isRange(Math.max(0, i - (m - i >> 1)), i, false))) {
        return new int[] { i, m };
      }
      i += paramArrayOfInt[0] + paramArrayOfInt[1];
      System.arraycopy(paramArrayOfInt, 2, paramArrayOfInt, 0, i1 - 2);
      paramArrayOfInt[(i1 - 2)] = 0;
      paramArrayOfInt[(i1 - 1)] = 0;
      j -= 1;
      label156:
      paramArrayOfInt[j] = 1;
      if (k == 0) {
        break label178;
      }
    }
    label178:
    for (k = 0;; k = 1)
    {
      break;
      j += 1;
      break label156;
    }
  }
  
  private static char patternToChar(int paramInt)
    throws NotFoundException
  {
    int i = 0;
    for (;;)
    {
      if (i >= CHARACTER_ENCODINGS.length) {
        throw NotFoundException.getNotFoundInstance();
      }
      if (CHARACTER_ENCODINGS[i] == paramInt) {
        return ALPHABET[i];
      }
      i += 1;
    }
  }
  
  private static int toNarrowWidePattern(int[] paramArrayOfInt)
  {
    int i4 = paramArrayOfInt.length;
    int j = 0;
    int i;
    label33:
    label105:
    do
    {
      i = Integer.MAX_VALUE;
      int i1 = paramArrayOfInt.length;
      int k = 0;
      int m;
      int n;
      if (k >= i1)
      {
        j = i;
        i = 0;
        k = 0;
        m = 0;
        n = 0;
        if (n < i4) {
          break label105;
        }
        if (i != 3) {
          continue;
        }
        i1 = 0;
        n = i;
        i = i1;
      }
      for (;;)
      {
        if ((i >= i4) || (n <= 0))
        {
          return m;
          n = paramArrayOfInt[k];
          m = i;
          if (n < i)
          {
            m = i;
            if (n > j) {
              m = n;
            }
          }
          k += 1;
          i = m;
          break;
          int i5 = paramArrayOfInt[n];
          int i3 = m;
          i2 = k;
          i1 = i;
          if (paramArrayOfInt[n] > j)
          {
            i3 = m | 1 << i4 - 1 - n;
            i1 = i + 1;
            i2 = k + i5;
          }
          n += 1;
          m = i3;
          k = i2;
          i = i1;
          break label33;
        }
        int i2 = paramArrayOfInt[i];
        i1 = n;
        if (paramArrayOfInt[i] > j)
        {
          i1 = n - 1;
          if (i2 << 1 >= k) {
            return -1;
          }
        }
        i += 1;
        n = i1;
      }
    } while (i > 3);
    return -1;
  }
  
  public Result decodeRow(int paramInt, BitArray paramBitArray, Map<DecodeHintType, ?> paramMap)
    throws NotFoundException, ChecksumException, FormatException
  {
    Object localObject1 = new int[9];
    paramMap = findAsteriskPattern(paramBitArray, (int[])localObject1);
    int i = paramBitArray.getNextSet(paramMap[1]);
    int n = paramBitArray.getSize();
    Object localObject2 = new StringBuilder(20);
    int j;
    char c;
    int m;
    do
    {
      j = i;
      recordPattern(paramBitArray, j, (int[])localObject1);
      i = toNarrowWidePattern((int[])localObject1);
      if (i < 0) {
        throw NotFoundException.getNotFoundInstance();
      }
      c = patternToChar(i);
      ((StringBuilder)localObject2).append(c);
      m = localObject1.length;
      k = 0;
      i = j;
      if (k < m) {
        break;
      }
      m = paramBitArray.getNextSet(i);
      i = m;
    } while (c != '*');
    ((StringBuilder)localObject2).setLength(((StringBuilder)localObject2).length() - 1);
    int k = 0;
    int i1 = localObject1.length;
    i = 0;
    for (;;)
    {
      if (i >= i1)
      {
        if ((m == n) || (m - j - k >> 1 >= k)) {
          break label214;
        }
        throw NotFoundException.getNotFoundInstance();
        i += localObject1[k];
        k += 1;
        break;
      }
      k += localObject1[i];
      i += 1;
    }
    label214:
    if (this.usingCheckDigit)
    {
      n = ((StringBuilder)localObject2).length() - 1;
      k = 0;
      i = 0;
      for (;;)
      {
        if (i >= n)
        {
          if (((StringBuilder)localObject2).charAt(n) == ALPHABET[(k % 43)]) {
            break;
          }
          throw ChecksumException.getChecksumInstance();
        }
        k += "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. *$/+%".indexOf(((StringBuilder)localObject2).charAt(i));
        i += 1;
      }
      ((StringBuilder)localObject2).setLength(n);
    }
    if (((StringBuilder)localObject2).length() == 0) {
      throw NotFoundException.getNotFoundInstance();
    }
    if (this.extendedMode) {}
    for (paramBitArray = decodeExtended((CharSequence)localObject2);; paramBitArray = ((StringBuilder)localObject2).toString())
    {
      float f1 = (paramMap[1] + paramMap[0]) / 2.0F;
      float f2 = (m + j) / 2.0F;
      paramMap = new ResultPoint(f1, paramInt);
      localObject1 = new ResultPoint(f2, paramInt);
      localObject2 = BarcodeFormat.CODE_39;
      return new Result(paramBitArray, null, new ResultPoint[] { paramMap, localObject1 }, (BarcodeFormat)localObject2);
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\oned\Code39Reader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */