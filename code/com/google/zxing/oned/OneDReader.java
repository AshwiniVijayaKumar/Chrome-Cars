package com.google.zxing.oned;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public abstract class OneDReader
  implements Reader
{
  protected static final int INTEGER_MATH_SHIFT = 8;
  protected static final int PATTERN_MATCH_RESULT_SCALE_FACTOR = 256;
  
  private Result doDecode(BinaryBitmap paramBinaryBitmap, Map<DecodeHintType, ?> paramMap)
    throws NotFoundException
  {
    i1 = paramBinaryBitmap.getWidth();
    int m = paramBinaryBitmap.getHeight();
    localObject1 = new BitArray(i1);
    int j;
    label49:
    int i2;
    if ((paramMap != null) && (paramMap.containsKey(DecodeHintType.TRY_HARDER)))
    {
      i = 1;
      if (i == 0) {
        break label85;
      }
      j = 8;
      i2 = Math.max(1, m >> j);
      if (i == 0) {
        break label91;
      }
    }
    label76:
    label85:
    label91:
    for (int i = m;; i = 15)
    {
      j = 0;
      if (j < i) {
        break label97;
      }
      throw NotFoundException.getNotFoundInstance();
      i = 0;
      break;
      j = 5;
      break label49;
    }
    label97:
    n = j + 1 >> 1;
    if ((j & 0x1) == 0)
    {
      k = 1;
      label115:
      if (k == 0) {
        break label186;
      }
    }
    label186:
    for (k = n;; k = -n)
    {
      n = (m >> 1) + i2 * k;
      if ((n < 0) || (n >= m)) {
        break label76;
      }
      try
      {
        BitArray localBitArray = paramBinaryBitmap.getBlackRow(n, (BitArray)localObject1);
        localObject1 = localBitArray;
        k = 0;
      }
      catch (NotFoundException localNotFoundException)
      {
        for (;;)
        {
          continue;
          Object localObject2 = paramMap;
          if (k == 1)
          {
            ((BitArray)localObject1).reverse();
            localObject2 = paramMap;
            if (paramMap != null)
            {
              localObject2 = paramMap;
              if (paramMap.containsKey(DecodeHintType.NEED_RESULT_POINT_CALLBACK))
              {
                localObject2 = new EnumMap(DecodeHintType.class);
                ((Map)localObject2).putAll(paramMap);
                ((Map)localObject2).remove(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
              }
            }
          }
          try
          {
            paramMap = decodeRow(n, (BitArray)localObject1, (Map)localObject2);
            if (k == 1)
            {
              paramMap.putMetadata(ResultMetadataType.ORIENTATION, Integer.valueOf(180));
              ResultPoint[] arrayOfResultPoint = paramMap.getResultPoints();
              if (arrayOfResultPoint != null)
              {
                arrayOfResultPoint[0] = new ResultPoint(i1 - arrayOfResultPoint[0].getX() - 1.0F, arrayOfResultPoint[0].getY());
                arrayOfResultPoint[1] = new ResultPoint(i1 - arrayOfResultPoint[1].getX() - 1.0F, arrayOfResultPoint[1].getY());
              }
            }
            return paramMap;
          }
          catch (ReaderException paramMap)
          {
            k += 1;
            paramMap = (Map<DecodeHintType, ?>)localObject2;
          }
        }
      }
      if (k < 2) {
        break label199;
      }
      j += 1;
      break;
      k = 0;
      break label115;
    }
  }
  
  protected static int patternMatchVariance(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt)
  {
    int n = paramArrayOfInt1.length;
    int i = 0;
    int k = 0;
    int j = 0;
    if (j >= n) {
      if (i >= k) {
        break label53;
      }
    }
    label53:
    label148:
    for (;;)
    {
      return Integer.MAX_VALUE;
      i += paramArrayOfInt1[j];
      k += paramArrayOfInt2[j];
      j += 1;
      break;
      int i1 = (i << 8) / k;
      k = 0;
      j = 0;
      if (j >= n) {
        return k / i;
      }
      int m = paramArrayOfInt1[j] << 8;
      int i2 = paramArrayOfInt2[j] * i1;
      if (m > i2) {
        m -= i2;
      }
      for (;;)
      {
        if (m > paramInt * i1 >> 8) {
          break label148;
        }
        k += m;
        j += 1;
        break;
        m = i2 - m;
      }
    }
  }
  
  protected static void recordPattern(BitArray paramBitArray, int paramInt, int[] paramArrayOfInt)
    throws NotFoundException
  {
    int m = paramArrayOfInt.length;
    Arrays.fill(paramArrayOfInt, 0, m, 0);
    int n = paramBitArray.getSize();
    if (paramInt >= n) {
      throw NotFoundException.getNotFoundInstance();
    }
    int i;
    int k;
    int j;
    if (paramBitArray.get(paramInt))
    {
      i = 0;
      k = 0;
      j = i;
      i = paramInt;
      paramInt = k;
      label49:
      if (i < n) {
        break label84;
      }
    }
    for (;;)
    {
      if ((paramInt != m) && ((paramInt != m - 1) || (i != n)))
      {
        throw NotFoundException.getNotFoundInstance();
        i = 1;
        break;
        label84:
        if ((paramBitArray.get(i) ^ j))
        {
          paramArrayOfInt[paramInt] += 1;
          k = paramInt;
          i += 1;
          paramInt = k;
          break label49;
        }
        k = paramInt + 1;
        paramInt = k;
        if (k != m)
        {
          paramArrayOfInt[k] = 1;
          if (j != 0) {}
          for (paramInt = 0;; paramInt = 1)
          {
            j = paramInt;
            break;
          }
        }
      }
    }
  }
  
  protected static void recordPatternInReverse(BitArray paramBitArray, int paramInt, int[] paramArrayOfInt)
    throws NotFoundException
  {
    int i = paramArrayOfInt.length;
    boolean bool = paramBitArray.get(paramInt);
    int j;
    do
    {
      if ((paramInt <= 0) || (i < 0))
      {
        if (i < 0) {
          break;
        }
        throw NotFoundException.getNotFoundInstance();
      }
      j = paramInt - 1;
      paramInt = j;
    } while (paramBitArray.get(j) == bool);
    i -= 1;
    if (bool) {}
    for (bool = false;; bool = true)
    {
      paramInt = j;
      break;
    }
    recordPattern(paramBitArray, paramInt + 1, paramArrayOfInt);
  }
  
  public Result decode(BinaryBitmap paramBinaryBitmap)
    throws NotFoundException, FormatException
  {
    return decode(paramBinaryBitmap, null);
  }
  
  public Result decode(BinaryBitmap paramBinaryBitmap, Map<DecodeHintType, ?> paramMap)
    throws NotFoundException, FormatException
  {
    try
    {
      Result localResult = doDecode(paramBinaryBitmap, paramMap);
      paramBinaryBitmap = localResult;
    }
    catch (NotFoundException localNotFoundException)
    {
      if ((paramMap == null) || (!paramMap.containsKey(DecodeHintType.TRY_HARDER))) {
        break label194;
      }
      BinaryBitmap localBinaryBitmap;
      label194:
      for (int i = 1;; i = 0)
      {
        if ((i == 0) || (!paramBinaryBitmap.isRotateSupported())) {
          break label199;
        }
        localBinaryBitmap = paramBinaryBitmap.rotateCounterClockwise();
        paramMap = doDecode(localBinaryBitmap, paramMap);
        paramBinaryBitmap = paramMap.getResultMetadata();
        int j = 270;
        i = j;
        if (paramBinaryBitmap != null)
        {
          i = j;
          if (paramBinaryBitmap.containsKey(ResultMetadataType.ORIENTATION)) {
            i = (((Integer)paramBinaryBitmap.get(ResultMetadataType.ORIENTATION)).intValue() + 270) % 360;
          }
        }
        paramMap.putMetadata(ResultMetadataType.ORIENTATION, Integer.valueOf(i));
        ResultPoint[] arrayOfResultPoint = paramMap.getResultPoints();
        paramBinaryBitmap = paramMap;
        if (arrayOfResultPoint == null) {
          break;
        }
        j = localBinaryBitmap.getHeight();
        i = 0;
        for (;;)
        {
          paramBinaryBitmap = paramMap;
          if (i >= arrayOfResultPoint.length) {
            break;
          }
          arrayOfResultPoint[i] = new ResultPoint(j - arrayOfResultPoint[i].getY() - 1.0F, arrayOfResultPoint[i].getX());
          i += 1;
        }
      }
      label199:
      throw localBinaryBitmap;
    }
    return paramBinaryBitmap;
  }
  
  public abstract Result decodeRow(int paramInt, BitArray paramBitArray, Map<DecodeHintType, ?> paramMap)
    throws NotFoundException, ChecksumException, FormatException;
  
  public void reset() {}
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\oned\OneDReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */