package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

public final class EAN13Reader
  extends UPCEANReader
{
  static final int[] FIRST_DIGIT_ENCODINGS;
  private final int[] decodeMiddleCounters = new int[4];
  
  static
  {
    int[] arrayOfInt = new int[10];
    arrayOfInt[1] = 11;
    arrayOfInt[2] = 13;
    arrayOfInt[3] = 14;
    arrayOfInt[4] = 19;
    arrayOfInt[5] = 25;
    arrayOfInt[6] = 28;
    arrayOfInt[7] = 21;
    arrayOfInt[8] = 22;
    arrayOfInt[9] = 26;
    FIRST_DIGIT_ENCODINGS = arrayOfInt;
  }
  
  private static void determineFirstDigit(StringBuilder paramStringBuilder, int paramInt)
    throws NotFoundException
  {
    int i = 0;
    for (;;)
    {
      if (i >= 10) {
        throw NotFoundException.getNotFoundInstance();
      }
      if (paramInt == FIRST_DIGIT_ENCODINGS[i])
      {
        paramStringBuilder.insert(0, (char)(i + 48));
        return;
      }
      i += 1;
    }
  }
  
  protected int decodeMiddle(BitArray paramBitArray, int[] paramArrayOfInt, StringBuilder paramStringBuilder)
    throws NotFoundException
  {
    int[] arrayOfInt = this.decodeMiddleCounters;
    arrayOfInt[0] = 0;
    arrayOfInt[1] = 0;
    arrayOfInt[2] = 0;
    arrayOfInt[3] = 0;
    int n = paramBitArray.getSize();
    int k = paramArrayOfInt[1];
    int j = 0;
    int i = 0;
    if ((i >= 6) || (k >= n))
    {
      determineFirstDigit(paramStringBuilder, j);
      j = findGuardPattern(paramBitArray, k, true, MIDDLE_PATTERN)[1];
      i = 0;
      if ((i >= 6) || (j >= n)) {
        return j;
      }
    }
    else
    {
      int i1 = decodeDigit(paramBitArray, arrayOfInt, k, L_AND_G_PATTERNS);
      paramStringBuilder.append((char)(i1 % 10 + 48));
      int i2 = arrayOfInt.length;
      m = 0;
      for (;;)
      {
        if (m >= i2)
        {
          m = j;
          if (i1 >= 10) {
            m = j | 1 << 5 - i;
          }
          i += 1;
          j = m;
          break;
        }
        k += arrayOfInt[m];
        m += 1;
      }
    }
    paramStringBuilder.append((char)(decodeDigit(paramBitArray, arrayOfInt, j, L_PATTERNS) + 48));
    int m = arrayOfInt.length;
    k = 0;
    for (;;)
    {
      if (k >= m)
      {
        i += 1;
        break;
      }
      j += arrayOfInt[k];
      k += 1;
    }
  }
  
  BarcodeFormat getBarcodeFormat()
  {
    return BarcodeFormat.EAN_13;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\oned\EAN13Reader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */