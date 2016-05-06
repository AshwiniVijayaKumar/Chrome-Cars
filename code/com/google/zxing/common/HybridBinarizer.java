package com.google.zxing.common;

import com.google.zxing.Binarizer;
import com.google.zxing.LuminanceSource;
import com.google.zxing.NotFoundException;
import java.lang.reflect.Array;

public final class HybridBinarizer
  extends GlobalHistogramBinarizer
{
  private static final int BLOCK_SIZE = 8;
  private static final int BLOCK_SIZE_MASK = 7;
  private static final int BLOCK_SIZE_POWER = 3;
  private static final int MINIMUM_DIMENSION = 40;
  private static final int MIN_DYNAMIC_RANGE = 24;
  private BitMatrix matrix;
  
  public HybridBinarizer(LuminanceSource paramLuminanceSource)
  {
    super(paramLuminanceSource);
  }
  
  private static int[][] calculateBlackPoints(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int[][] arrayOfInt = (int[][])Array.newInstance(Integer.TYPE, new int[] { paramInt2, paramInt1 });
    int n = 0;
    if (n >= paramInt2) {
      return arrayOfInt;
    }
    int i = n << 3;
    int j = paramInt4 - 8;
    int i1 = i;
    if (i > j) {
      i1 = j;
    }
    int i2 = 0;
    int i4;
    int i3;
    for (;;)
    {
      if (i2 >= paramInt1)
      {
        n += 1;
        break;
      }
      j = i2 << 3;
      k = paramInt3 - 8;
      i = j;
      if (j > k) {
        i = k;
      }
      j = 0;
      i4 = 255;
      i3 = 0;
      k = 0;
      m = i1 * paramInt3 + i;
      i = j;
      j = m;
      if (k < 8) {
        break label260;
      }
      i >>= 6;
      if (i3 - i4 <= 24)
      {
        j = i4 >> 1;
        i = j;
        if (n > 0)
        {
          i = j;
          if (i2 > 0)
          {
            k = arrayOfInt[(n - 1)][i2] + arrayOfInt[n][(i2 - 1)] * 2 + arrayOfInt[(n - 1)][(i2 - 1)] >> 2;
            i = j;
            if (i4 < k) {
              i = k;
            }
          }
        }
      }
      arrayOfInt[n][i2] = i;
      i2 += 1;
    }
    label260:
    int m = 0;
    for (;;)
    {
      if (m >= 8)
      {
        i6 = j;
        m = i;
        i5 = k;
        if (i3 - i4 > 24)
        {
          m = k + 1;
          k = j + paramInt3;
          j = m;
          m = i;
          i = k;
          if (j < 8) {
            break label421;
          }
          i5 = j;
          i6 = i;
        }
        k = i5 + 1;
        j = i6 + paramInt3;
        i = m;
        break;
      }
      int i5 = paramArrayOfByte[(j + m)] & 0xFF;
      int i6 = i + i5;
      i = i4;
      if (i5 < i4) {
        i = i5;
      }
      i4 = i3;
      if (i5 > i3) {
        i4 = i5;
      }
      m += 1;
      i3 = i4;
      i4 = i;
      i = i6;
    }
    label421:
    int k = 0;
    for (;;)
    {
      if (k >= 8)
      {
        j += 1;
        i += paramInt3;
        break;
      }
      m += (paramArrayOfByte[(i + k)] & 0xFF);
      k += 1;
    }
  }
  
  private static void calculateThresholdForBlock(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int[][] paramArrayOfInt, BitMatrix paramBitMatrix)
  {
    int i = 0;
    int k;
    int j;
    for (;;)
    {
      if (i >= paramInt2) {
        return;
      }
      k = i << 3;
      m = paramInt4 - 8;
      j = k;
      if (k > m) {
        j = m;
      }
      k = 0;
      if (k < paramInt1) {
        break;
      }
      i += 1;
    }
    int n = k << 3;
    int i1 = paramInt3 - 8;
    int m = n;
    if (n > i1) {
      m = i1;
    }
    int i2 = cap(k, 2, paramInt1 - 3);
    int i3 = cap(i, 2, paramInt2 - 3);
    i1 = 0;
    n = -2;
    for (;;)
    {
      if (n > 2)
      {
        thresholdBlock(paramArrayOfByte, m, j, i1 / 25, paramInt3, paramBitMatrix);
        k += 1;
        break;
      }
      int[] arrayOfInt = paramArrayOfInt[(i3 + n)];
      i1 += arrayOfInt[(i2 - 2)] + arrayOfInt[(i2 - 1)] + arrayOfInt[i2] + arrayOfInt[(i2 + 1)] + arrayOfInt[(i2 + 2)];
      n += 1;
    }
  }
  
  private static int cap(int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramInt1 < paramInt2) {
      return paramInt2;
    }
    if (paramInt1 > paramInt3) {
      return paramInt3;
    }
    return paramInt1;
  }
  
  private static void thresholdBlock(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3, int paramInt4, BitMatrix paramBitMatrix)
  {
    int j = 0;
    int i = paramInt2 * paramInt4 + paramInt1;
    if (j >= 8) {
      return;
    }
    int k = 0;
    for (;;)
    {
      if (k >= 8)
      {
        j += 1;
        i += paramInt4;
        break;
      }
      if ((paramArrayOfByte[(i + k)] & 0xFF) <= paramInt3) {
        paramBitMatrix.set(paramInt1 + k, paramInt2 + j);
      }
      k += 1;
    }
  }
  
  public Binarizer createBinarizer(LuminanceSource paramLuminanceSource)
  {
    return new HybridBinarizer(paramLuminanceSource);
  }
  
  public BitMatrix getBlackMatrix()
    throws NotFoundException
  {
    if (this.matrix != null) {
      return this.matrix;
    }
    Object localObject = getLuminanceSource();
    int m = ((LuminanceSource)localObject).getWidth();
    int n = ((LuminanceSource)localObject).getHeight();
    BitMatrix localBitMatrix;
    if ((m >= 40) && (n >= 40))
    {
      localObject = ((LuminanceSource)localObject).getMatrix();
      int j = m >> 3;
      int i = j;
      if ((m & 0x7) != 0) {
        i = j + 1;
      }
      int k = n >> 3;
      j = k;
      if ((n & 0x7) != 0) {
        j = k + 1;
      }
      int[][] arrayOfInt = calculateBlackPoints((byte[])localObject, i, j, m, n);
      localBitMatrix = new BitMatrix(m, n);
      calculateThresholdForBlock((byte[])localObject, i, j, m, n, arrayOfInt, localBitMatrix);
    }
    for (this.matrix = localBitMatrix;; this.matrix = super.getBlackMatrix()) {
      return this.matrix;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\common\HybridBinarizer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */