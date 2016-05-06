package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.Map;

public final class CodaBarReader
  extends OneDReader
{
  static final char[] ALPHABET = "0123456789-$:/.+ABCD".toCharArray();
  private static final String ALPHABET_STRING = "0123456789-$:/.+ABCD";
  static final int[] CHARACTER_ENCODINGS = { 3, 6, 9, 96, 18, 66, 33, 36, 48, 72, 12, 24, 69, 81, 84, 21, 26, 41, 11, 14 };
  private static final int MAX_ACCEPTABLE = 512;
  private static final int MIN_CHARACTER_LENGTH = 3;
  private static final int PADDING = 384;
  private static final char[] STARTEND_ENCODING = { 65, 66, 67, 68 };
  private int counterLength = 0;
  private int[] counters = new int[80];
  private final StringBuilder decodeRowResult = new StringBuilder(20);
  
  static boolean arrayContains(char[] paramArrayOfChar, char paramChar)
  {
    int j;
    int i;
    if (paramArrayOfChar != null)
    {
      j = paramArrayOfChar.length;
      i = 0;
    }
    for (;;)
    {
      if (i >= j) {
        return false;
      }
      if (paramArrayOfChar[i] == paramChar) {
        return true;
      }
      i += 1;
    }
  }
  
  private void counterAppend(int paramInt)
  {
    this.counters[this.counterLength] = paramInt;
    this.counterLength += 1;
    if (this.counterLength >= this.counters.length)
    {
      int[] arrayOfInt = new int[this.counterLength * 2];
      System.arraycopy(this.counters, 0, arrayOfInt, 0, this.counterLength);
      this.counters = arrayOfInt;
    }
  }
  
  private int findStartPattern()
    throws NotFoundException
  {
    int i = 1;
    for (;;)
    {
      if (i >= this.counterLength) {
        throw NotFoundException.getNotFoundInstance();
      }
      int j = toNarrowWidePattern(i);
      if ((j != -1) && (arrayContains(STARTEND_ENCODING, ALPHABET[j])))
      {
        int k = 0;
        j = i;
        for (;;)
        {
          if (j >= i + 7)
          {
            if ((i != 1) && (this.counters[(i - 1)] < k / 2)) {
              break;
            }
            return i;
          }
          k += this.counters[j];
          j += 1;
        }
      }
      i += 2;
    }
  }
  
  private void setCounters(BitArray paramBitArray)
    throws NotFoundException
  {
    this.counterLength = 0;
    int k = paramBitArray.getNextUnset(0);
    int n = paramBitArray.getSize();
    if (k >= n) {
      throw NotFoundException.getNotFoundInstance();
    }
    int i = 1;
    int m = 0;
    for (;;)
    {
      if (k >= n)
      {
        counterAppend(m);
        return;
      }
      if (!(paramBitArray.get(k) ^ i)) {
        break;
      }
      m += 1;
      j = i;
      i = m;
      k += 1;
      m = i;
      i = j;
    }
    counterAppend(m);
    m = 1;
    if (i != 0) {}
    for (int j = 0;; j = 1)
    {
      i = m;
      break;
    }
  }
  
  private int toNarrowWidePattern(int paramInt)
  {
    int k = paramInt + 7;
    int j;
    if (k >= this.counterLength)
    {
      j = -1;
      return j;
    }
    int[] arrayOfInt1 = new int[2];
    int[] arrayOfInt2 = new int[2];
    int[] tmp31_29 = arrayOfInt2;
    tmp31_29[0] = Integer.MAX_VALUE;
    int[] tmp36_31 = tmp31_29;
    tmp36_31[1] = Integer.MAX_VALUE;
    tmp36_31;
    int[] arrayOfInt3 = new int[2];
    int i = 0;
    int m;
    if (i >= 2)
    {
      m = 128;
      i = 0;
      j = 0;
      label63:
      if (j < 7) {
        break label166;
      }
      paramInt = 0;
    }
    for (;;)
    {
      if (paramInt >= CHARACTER_ENCODINGS.length)
      {
        return -1;
        j = paramInt + i;
        for (;;)
        {
          if (j >= k)
          {
            arrayOfInt3[i] = ((arrayOfInt2[i] + arrayOfInt1[i]) / 2);
            i += 1;
            break;
          }
          if (this.counters[j] < arrayOfInt2[i]) {
            arrayOfInt2[i] = this.counters[j];
          }
          if (this.counters[j] > arrayOfInt1[i]) {
            arrayOfInt1[i] = this.counters[j];
          }
          j += 2;
        }
        label166:
        m >>= 1;
        k = i;
        if (this.counters[(paramInt + j)] > arrayOfInt3[(j & 0x1)]) {
          k = i | m;
        }
        j += 1;
        i = k;
        break label63;
      }
      j = paramInt;
      if (CHARACTER_ENCODINGS[paramInt] == i) {
        break;
      }
      paramInt += 1;
    }
  }
  
  public Result decodeRow(int paramInt, BitArray paramBitArray, Map<DecodeHintType, ?> paramMap)
    throws NotFoundException
  {
    setCounters(paramBitArray);
    int j = findStartPattern();
    int i = j;
    this.decodeRowResult.setLength(0);
    int k = toNarrowWidePattern(i);
    if (k == -1) {
      throw NotFoundException.getNotFoundInstance();
    }
    this.decodeRowResult.append((char)k);
    int m = i + 8;
    label85:
    int n;
    if ((this.decodeRowResult.length() > 1) && (arrayContains(STARTEND_ENCODING, ALPHABET[k])))
    {
      n = this.counters[(m - 1)];
      k = 0;
      i = -8;
    }
    for (;;)
    {
      if (i >= -1)
      {
        if ((m >= this.counterLength) || (n >= k / 2)) {
          break label171;
        }
        throw NotFoundException.getNotFoundInstance();
        i = m;
        if (m < this.counterLength) {
          break;
        }
        break label85;
      }
      k += this.counters[(m + i)];
      i += 1;
    }
    label171:
    validatePattern(j);
    i = 0;
    for (;;)
    {
      if (i >= this.decodeRowResult.length())
      {
        c = this.decodeRowResult.charAt(0);
        if (arrayContains(STARTEND_ENCODING, c)) {
          break;
        }
        throw NotFoundException.getNotFoundInstance();
      }
      this.decodeRowResult.setCharAt(i, ALPHABET[this.decodeRowResult.charAt(i)]);
      i += 1;
    }
    char c = this.decodeRowResult.charAt(this.decodeRowResult.length() - 1);
    if (!arrayContains(STARTEND_ENCODING, c)) {
      throw NotFoundException.getNotFoundInstance();
    }
    if (this.decodeRowResult.length() <= 3) {
      throw NotFoundException.getNotFoundInstance();
    }
    this.decodeRowResult.deleteCharAt(this.decodeRowResult.length() - 1);
    this.decodeRowResult.deleteCharAt(0);
    i = 0;
    k = 0;
    float f1;
    if (k >= j) {
      f1 = i;
    }
    for (;;)
    {
      if (j >= m - 1)
      {
        float f2 = i;
        paramBitArray = this.decodeRowResult.toString();
        paramMap = new ResultPoint(f1, paramInt);
        ResultPoint localResultPoint = new ResultPoint(f2, paramInt);
        BarcodeFormat localBarcodeFormat = BarcodeFormat.CODABAR;
        return new Result(paramBitArray, null, new ResultPoint[] { paramMap, localResultPoint }, localBarcodeFormat);
        i += this.counters[k];
        k += 1;
        break;
      }
      i += this.counters[j];
      j += 1;
    }
  }
  
  void validatePattern(int paramInt)
    throws NotFoundException
  {
    int[] arrayOfInt1 = new int[4];
    int[] arrayOfInt2 = new int[4];
    int n = this.decodeRowResult.length() - 1;
    int j = paramInt;
    int i = 0;
    int m = CHARACTER_ENCODINGS[this.decodeRowResult.charAt(i)];
    int k = 6;
    label43:
    int[] arrayOfInt3;
    int[] arrayOfInt4;
    if (k < 0)
    {
      if (i < n) {
        break label165;
      }
      arrayOfInt3 = new int[4];
      arrayOfInt4 = new int[4];
      i = 0;
      label66:
      if (i < 2) {
        break label177;
      }
      j = 0;
      i = paramInt;
      paramInt = j;
    }
    for (;;)
    {
      k = CHARACTER_ENCODINGS[this.decodeRowResult.charAt(paramInt)];
      j = 6;
      for (;;)
      {
        if (j < 0)
        {
          if (paramInt < n) {
            break label325;
          }
          return;
          i1 = (k & 0x1) + (m & 0x1) * 2;
          arrayOfInt1[i1] += this.counters[(j + k)];
          arrayOfInt2[i1] += 1;
          m >>= 1;
          k -= 1;
          break label43;
          label165:
          j += 8;
          i += 1;
          break;
          label177:
          arrayOfInt4[i] = 0;
          arrayOfInt4[(i + 2)] = ((arrayOfInt1[i] << 8) / arrayOfInt2[i] + (arrayOfInt1[(i + 2)] << 8) / arrayOfInt2[(i + 2)] >> 1);
          arrayOfInt3[i] = arrayOfInt4[(i + 2)];
          arrayOfInt3[(i + 2)] = ((arrayOfInt1[(i + 2)] * 512 + 384) / arrayOfInt2[(i + 2)]);
          i += 1;
          break label66;
        }
        m = (j & 0x1) + (k & 0x1) * 2;
        int i1 = this.counters[(i + j)] << 8;
        if ((i1 < arrayOfInt4[m]) || (i1 > arrayOfInt3[m])) {
          throw NotFoundException.getNotFoundInstance();
        }
        k >>= 1;
        j -= 1;
      }
      label325:
      i += 8;
      paramInt += 1;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\oned\CodaBarReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */