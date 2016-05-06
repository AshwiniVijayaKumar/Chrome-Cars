package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

public final class EAN8Reader
  extends UPCEANReader
{
  private final int[] decodeMiddleCounters = new int[4];
  
  protected int decodeMiddle(BitArray paramBitArray, int[] paramArrayOfInt, StringBuilder paramStringBuilder)
    throws NotFoundException
  {
    int[] arrayOfInt = this.decodeMiddleCounters;
    arrayOfInt[0] = 0;
    arrayOfInt[1] = 0;
    arrayOfInt[2] = 0;
    arrayOfInt[3] = 0;
    int m = paramBitArray.getSize();
    int j = paramArrayOfInt[1];
    int i = 0;
    if ((i >= 4) || (j >= m))
    {
      j = findGuardPattern(paramBitArray, j, true, MIDDLE_PATTERN)[1];
      i = 0;
      if ((i >= 4) || (j >= m)) {
        return j;
      }
    }
    else
    {
      paramStringBuilder.append((char)(decodeDigit(paramBitArray, arrayOfInt, j, L_PATTERNS) + 48));
      n = arrayOfInt.length;
      k = 0;
      for (;;)
      {
        if (k >= n)
        {
          i += 1;
          break;
        }
        j += arrayOfInt[k];
        k += 1;
      }
    }
    paramStringBuilder.append((char)(decodeDigit(paramBitArray, arrayOfInt, j, L_PATTERNS) + 48));
    int n = arrayOfInt.length;
    int k = 0;
    for (;;)
    {
      if (k >= n)
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
    return BarcodeFormat.EAN_8;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\oned\EAN8Reader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */