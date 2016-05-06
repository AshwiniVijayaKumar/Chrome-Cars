package com.google.zxing.oned.rss;

import com.google.zxing.NotFoundException;
import com.google.zxing.oned.OneDReader;

public abstract class AbstractRSSReader
  extends OneDReader
{
  private static final int MAX_AVG_VARIANCE = 51;
  private static final float MAX_FINDER_PATTERN_RATIO = 0.89285713F;
  private static final int MAX_INDIVIDUAL_VARIANCE = 102;
  private static final float MIN_FINDER_PATTERN_RATIO = 0.7916667F;
  private final int[] dataCharacterCounters = new int[8];
  private final int[] decodeFinderCounters = new int[4];
  private final int[] evenCounts = new int[this.dataCharacterCounters.length / 2];
  private final float[] evenRoundingErrors = new float[4];
  private final int[] oddCounts = new int[this.dataCharacterCounters.length / 2];
  private final float[] oddRoundingErrors = new float[4];
  
  protected static int count(int[] paramArrayOfInt)
  {
    int j = 0;
    int k = paramArrayOfInt.length;
    int i = 0;
    for (;;)
    {
      if (i >= k) {
        return j;
      }
      j += paramArrayOfInt[i];
      i += 1;
    }
  }
  
  protected static void decrement(int[] paramArrayOfInt, float[] paramArrayOfFloat)
  {
    int j = 0;
    float f1 = paramArrayOfFloat[0];
    int i = 1;
    for (;;)
    {
      if (i >= paramArrayOfInt.length)
      {
        paramArrayOfInt[j] -= 1;
        return;
      }
      float f2 = f1;
      if (paramArrayOfFloat[i] < f1)
      {
        f2 = paramArrayOfFloat[i];
        j = i;
      }
      i += 1;
      f1 = f2;
    }
  }
  
  protected static void increment(int[] paramArrayOfInt, float[] paramArrayOfFloat)
  {
    int j = 0;
    float f1 = paramArrayOfFloat[0];
    int i = 1;
    for (;;)
    {
      if (i >= paramArrayOfInt.length)
      {
        paramArrayOfInt[j] += 1;
        return;
      }
      float f2 = f1;
      if (paramArrayOfFloat[i] > f1)
      {
        f2 = paramArrayOfFloat[i];
        j = i;
      }
      i += 1;
      f1 = f2;
    }
  }
  
  protected static boolean isFinderPattern(int[] paramArrayOfInt)
  {
    int i = paramArrayOfInt[0] + paramArrayOfInt[1];
    int j = paramArrayOfInt[2];
    int k = paramArrayOfInt[3];
    float f = i / (j + i + k);
    if ((f >= 0.7916667F) && (f <= 0.89285713F))
    {
      j = Integer.MAX_VALUE;
      int m = Integer.MIN_VALUE;
      int i2 = paramArrayOfInt.length;
      i = 0;
      for (;;)
      {
        if (i >= i2)
        {
          if (m >= j * 10) {
            break;
          }
          return true;
        }
        int n = paramArrayOfInt[i];
        k = m;
        if (n > m) {
          k = n;
        }
        int i1 = j;
        if (n < j) {
          i1 = n;
        }
        i += 1;
        m = k;
        j = i1;
      }
      return false;
    }
    return false;
  }
  
  protected static int parseFinderValue(int[] paramArrayOfInt, int[][] paramArrayOfInt1)
    throws NotFoundException
  {
    int i = 0;
    for (;;)
    {
      if (i >= paramArrayOfInt1.length) {
        throw NotFoundException.getNotFoundInstance();
      }
      if (patternMatchVariance(paramArrayOfInt, paramArrayOfInt1[i], 102) < 51) {
        return i;
      }
      i += 1;
    }
  }
  
  protected final int[] getDataCharacterCounters()
  {
    return this.dataCharacterCounters;
  }
  
  protected final int[] getDecodeFinderCounters()
  {
    return this.decodeFinderCounters;
  }
  
  protected final int[] getEvenCounts()
  {
    return this.evenCounts;
  }
  
  protected final float[] getEvenRoundingErrors()
  {
    return this.evenRoundingErrors;
  }
  
  protected final int[] getOddCounts()
  {
    return this.oddCounts;
  }
  
  protected final float[] getOddRoundingErrors()
  {
    return this.oddRoundingErrors;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\oned\rss\AbstractRSSReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */