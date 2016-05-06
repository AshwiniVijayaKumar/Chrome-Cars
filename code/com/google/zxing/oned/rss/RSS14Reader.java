package com.google.zxing.oned.rss;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitArray;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class RSS14Reader
  extends AbstractRSSReader
{
  private static final int[][] FINDER_PATTERNS;
  private static final int[] INSIDE_GSUM;
  private static final int[] INSIDE_ODD_TOTAL_SUBSET;
  private static final int[] INSIDE_ODD_WIDEST;
  private static final int[] OUTSIDE_EVEN_TOTAL_SUBSET = { 1, 10, 34, 70, 126 };
  private static final int[] OUTSIDE_GSUM;
  private static final int[] OUTSIDE_ODD_WIDEST;
  private final List<Pair> possibleLeftPairs = new ArrayList();
  private final List<Pair> possibleRightPairs = new ArrayList();
  
  static
  {
    INSIDE_ODD_TOTAL_SUBSET = new int[] { 4, 20, 48, 81 };
    int[] arrayOfInt1 = new int[5];
    arrayOfInt1[1] = 161;
    arrayOfInt1[2] = 961;
    arrayOfInt1[3] = 2015;
    arrayOfInt1[4] = 2715;
    OUTSIDE_GSUM = arrayOfInt1;
    arrayOfInt1 = new int[4];
    arrayOfInt1[1] = 336;
    arrayOfInt1[2] = 1036;
    arrayOfInt1[3] = 1516;
    INSIDE_GSUM = arrayOfInt1;
    OUTSIDE_ODD_WIDEST = new int[] { 8, 6, 4, 3, 1 };
    INSIDE_ODD_WIDEST = new int[] { 2, 4, 6, 8 };
    arrayOfInt1 = new int[] { 2, 7, 4, 1 };
    int[] arrayOfInt2 = { 2, 5, 6, 1 };
    int[] arrayOfInt3 = { 1, 3, 9, 1 };
    FINDER_PATTERNS = new int[][] { { 3, 8, 2, 1 }, { 3, 5, 5, 1 }, { 3, 3, 7, 1 }, { 3, 1, 9, 1 }, arrayOfInt1, arrayOfInt2, { 2, 3, 8, 1 }, { 1, 5, 7, 1 }, arrayOfInt3 };
  }
  
  private static void addOrTally(Collection<Pair> paramCollection, Pair paramPair)
  {
    if (paramPair == null) {}
    label67:
    for (;;)
    {
      return;
      int i = 0;
      Iterator localIterator = paramCollection.iterator();
      if (!localIterator.hasNext()) {}
      for (;;)
      {
        if (i != 0) {
          break label67;
        }
        paramCollection.add(paramPair);
        return;
        Pair localPair = (Pair)localIterator.next();
        if (localPair.getValue() != paramPair.getValue()) {
          break;
        }
        localPair.incrementCount();
        i = 1;
      }
    }
  }
  
  private void adjustOddEvenCounts(boolean paramBoolean, int paramInt)
    throws NotFoundException
  {
    int i4 = count(getOddCounts());
    int i5 = count(getEvenCounts());
    int i6 = i4 + i5 - paramInt;
    int i2;
    label44:
    int i1;
    label55:
    int k;
    int n;
    int j;
    int i;
    int i3;
    int m;
    if (paramBoolean)
    {
      paramInt = 1;
      if ((i4 & 0x1) != paramInt) {
        break label127;
      }
      i2 = 1;
      if ((i5 & 0x1) != 1) {
        break label133;
      }
      i1 = 1;
      k = 0;
      n = 0;
      j = 0;
      paramInt = 0;
      i = 0;
      i3 = 0;
      if (!paramBoolean) {
        break label190;
      }
      if (i4 <= 12) {
        break label139;
      }
      m = 1;
      label85:
      if (i5 <= 12) {
        break label157;
      }
      paramInt = 1;
      k = n;
      j = m;
    }
    for (;;)
    {
      if (i6 == 1) {
        if (i2 != 0)
        {
          if (i1 != 0)
          {
            throw NotFoundException.getNotFoundInstance();
            paramInt = 0;
            break;
            label127:
            i2 = 0;
            break label44;
            label133:
            i1 = 0;
            break label55;
            label139:
            m = paramInt;
            if (i4 >= 4) {
              break label85;
            }
            n = 1;
            m = paramInt;
            break label85;
            label157:
            paramInt = i3;
            j = m;
            k = n;
            if (i5 >= 4) {
              continue;
            }
            i = 1;
            paramInt = i3;
            j = m;
            k = n;
            continue;
            label190:
            if (i4 > 11)
            {
              m = 1;
              n = k;
            }
            for (;;)
            {
              if (i5 <= 10) {
                break label248;
              }
              paramInt = 1;
              j = m;
              k = n;
              break;
              m = j;
              n = k;
              if (i4 < 5)
              {
                n = 1;
                m = j;
              }
            }
            label248:
            paramInt = i3;
            j = m;
            k = n;
            if (i5 >= 4) {
              continue;
            }
            i = 1;
            paramInt = i3;
            j = m;
            k = n;
            continue;
          }
          j = 1;
        }
      }
    }
    while (k != 0) {
      if (j != 0)
      {
        throw NotFoundException.getNotFoundInstance();
        if (i1 == 0) {
          throw NotFoundException.getNotFoundInstance();
        }
        paramInt = 1;
        continue;
        if (i6 == -1)
        {
          if (i2 != 0)
          {
            if (i1 != 0) {
              throw NotFoundException.getNotFoundInstance();
            }
            k = 1;
          }
          else
          {
            if (i1 == 0) {
              throw NotFoundException.getNotFoundInstance();
            }
            i = 1;
          }
        }
        else if (i6 == 0)
        {
          if (i2 != 0)
          {
            if (i1 == 0) {
              throw NotFoundException.getNotFoundInstance();
            }
            if (i4 < i5)
            {
              k = 1;
              paramInt = 1;
            }
            else
            {
              j = 1;
              i = 1;
            }
          }
          else if (i1 != 0)
          {
            throw NotFoundException.getNotFoundInstance();
          }
        }
        else {
          throw NotFoundException.getNotFoundInstance();
        }
      }
      else
      {
        increment(getOddCounts(), getOddRoundingErrors());
      }
    }
    if (j != 0) {
      decrement(getOddCounts(), getOddRoundingErrors());
    }
    if (i != 0)
    {
      if (paramInt != 0) {
        throw NotFoundException.getNotFoundInstance();
      }
      increment(getEvenCounts(), getOddRoundingErrors());
    }
    if (paramInt != 0) {
      decrement(getEvenCounts(), getEvenRoundingErrors());
    }
  }
  
  private static boolean checkChecksum(Pair paramPair1, Pair paramPair2)
  {
    int i = paramPair1.getFinderPattern().getValue();
    int j = paramPair2.getFinderPattern().getValue();
    if (((i != 0) || (j != 8)) && (i == 8)) {}
    int k = paramPair1.getChecksumPortion();
    int m = paramPair2.getChecksumPortion();
    j = paramPair1.getFinderPattern().getValue() * 9 + paramPair2.getFinderPattern().getValue();
    i = j;
    if (j > 72) {
      i = j - 1;
    }
    j = i;
    if (i > 8) {
      j = i - 1;
    }
    return (k + m * 16) % 79 == j;
  }
  
  private static Result constructResult(Pair paramPair1, Pair paramPair2)
  {
    Object localObject = String.valueOf(4537077L * paramPair1.getValue() + paramPair2.getValue());
    StringBuilder localStringBuilder = new StringBuilder(14);
    int i = 13 - ((String)localObject).length();
    int j;
    if (i <= 0)
    {
      localStringBuilder.append((String)localObject);
      j = 0;
      i = 0;
    }
    for (;;)
    {
      if (i >= 13)
      {
        j = 10 - j % 10;
        i = j;
        if (j == 10) {
          i = 0;
        }
        localStringBuilder.append(i);
        ResultPoint[] arrayOfResultPoint = paramPair1.getFinderPattern().getResultPoints();
        localObject = paramPair2.getFinderPattern().getResultPoints();
        paramPair1 = String.valueOf(localStringBuilder.toString());
        paramPair2 = arrayOfResultPoint[0];
        localStringBuilder = arrayOfResultPoint[1];
        arrayOfResultPoint = localObject[0];
        localObject = localObject[1];
        BarcodeFormat localBarcodeFormat = BarcodeFormat.RSS_14;
        return new Result(paramPair1, null, new ResultPoint[] { paramPair2, localStringBuilder, arrayOfResultPoint, localObject }, localBarcodeFormat);
        localStringBuilder.append('0');
        i -= 1;
        break;
      }
      int m = localStringBuilder.charAt(i) - '0';
      int k = m;
      if ((i & 0x1) == 0) {
        k = m * 3;
      }
      j += k;
      i += 1;
    }
  }
  
  private DataCharacter decodeDataCharacter(BitArray paramBitArray, FinderPattern paramFinderPattern, boolean paramBoolean)
    throws NotFoundException
  {
    int[] arrayOfInt = getDataCharacterCounters();
    arrayOfInt[0] = 0;
    arrayOfInt[1] = 0;
    arrayOfInt[2] = 0;
    arrayOfInt[3] = 0;
    arrayOfInt[4] = 0;
    arrayOfInt[5] = 0;
    arrayOfInt[6] = 0;
    arrayOfInt[7] = 0;
    int j;
    label72:
    float f1;
    float[] arrayOfFloat1;
    float[] arrayOfFloat2;
    label136:
    int n;
    if (paramBoolean)
    {
      recordPatternInReverse(paramBitArray, paramFinderPattern.getStartEnd()[0], arrayOfInt);
      if (!paramBoolean) {
        break label265;
      }
      j = 16;
      f1 = count(arrayOfInt) / j;
      paramBitArray = getOddCounts();
      paramFinderPattern = getEvenCounts();
      arrayOfFloat1 = getOddRoundingErrors();
      arrayOfFloat2 = getEvenRoundingErrors();
      k = 0;
      if (k < arrayOfInt.length) {
        break label272;
      }
      adjustOddEvenCounts(paramBoolean, j);
      i = 0;
      j = 0;
      k = paramBitArray.length - 1;
      if (k >= 0) {
        break label377;
      }
      n = 0;
      k = 0;
      m = paramFinderPattern.length - 1;
    }
    for (;;)
    {
      if (m < 0)
      {
        j += n * 3;
        if (!paramBoolean) {
          break label504;
        }
        if (((i & 0x1) == 0) && (i <= 12) && (i >= 4)) {
          break label437;
        }
        throw NotFoundException.getNotFoundInstance();
        recordPattern(paramBitArray, paramFinderPattern.getStartEnd()[1] + 1, arrayOfInt);
        j = 0;
        i = arrayOfInt.length - 1;
        while (j < i)
        {
          k = arrayOfInt[j];
          arrayOfInt[j] = arrayOfInt[i];
          arrayOfInt[i] = k;
          j += 1;
          i -= 1;
        }
        break;
        label265:
        j = 15;
        break label72;
        label272:
        float f2 = arrayOfInt[k] / f1;
        m = (int)(0.5F + f2);
        if (m < 1)
        {
          i = 1;
          label300:
          m = k >> 1;
          if ((k & 0x1) != 0) {
            break label357;
          }
          paramBitArray[m] = i;
          arrayOfFloat1[m] = (f2 - i);
        }
        for (;;)
        {
          k += 1;
          break;
          i = m;
          if (m <= 8) {
            break label300;
          }
          i = 8;
          break label300;
          label357:
          paramFinderPattern[m] = i;
          arrayOfFloat2[m] = (f2 - i);
        }
        label377:
        j = j * 9 + paramBitArray[k];
        i += paramBitArray[k];
        k -= 1;
        break label136;
      }
      n = n * 9 + paramFinderPattern[m];
      k += paramFinderPattern[m];
      m -= 1;
    }
    label437:
    int i = (12 - i) / 2;
    int m = OUTSIDE_ODD_WIDEST[i];
    int k = RSSUtils.getRSSvalue(paramBitArray, m, false);
    m = RSSUtils.getRSSvalue(paramFinderPattern, 9 - m, true);
    return new DataCharacter(k * OUTSIDE_EVEN_TOTAL_SUBSET[i] + m + OUTSIDE_GSUM[i], j);
    label504:
    if (((k & 0x1) != 0) || (k > 10) || (k < 4)) {
      throw NotFoundException.getNotFoundInstance();
    }
    i = (10 - k) / 2;
    k = INSIDE_ODD_WIDEST[i];
    m = RSSUtils.getRSSvalue(paramBitArray, k, true);
    return new DataCharacter(RSSUtils.getRSSvalue(paramFinderPattern, 9 - k, false) * INSIDE_ODD_TOTAL_SUBSET[i] + m + INSIDE_GSUM[i], j);
  }
  
  private Pair decodePair(BitArray paramBitArray, boolean paramBoolean, int paramInt, Map<DecodeHintType, ?> paramMap)
  {
    try
    {
      int[] arrayOfInt = findFinderPattern(paramBitArray, 0, paramBoolean);
      FinderPattern localFinderPattern = parseFoundFinderPattern(paramBitArray, paramInt, paramBoolean, arrayOfInt);
      if (paramMap == null) {}
      for (paramMap = null;; paramMap = (ResultPointCallback)paramMap.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK))
      {
        if (paramMap != null)
        {
          float f2 = (arrayOfInt[0] + arrayOfInt[1]) / 2.0F;
          float f1 = f2;
          if (paramBoolean) {
            f1 = paramBitArray.getSize() - 1 - f2;
          }
          paramMap.foundPossibleResultPoint(new ResultPoint(f1, paramInt));
        }
        paramMap = decodeDataCharacter(paramBitArray, localFinderPattern, true);
        paramBitArray = decodeDataCharacter(paramBitArray, localFinderPattern, false);
        return new Pair(paramMap.getValue() * 1597 + paramBitArray.getValue(), paramMap.getChecksumPortion() + paramBitArray.getChecksumPortion() * 4, localFinderPattern);
      }
      return null;
    }
    catch (NotFoundException paramBitArray) {}
  }
  
  private int[] findFinderPattern(BitArray paramBitArray, int paramInt, boolean paramBoolean)
    throws NotFoundException
  {
    int[] arrayOfInt = getDecodeFinderCounters();
    arrayOfInt[0] = 0;
    arrayOfInt[1] = 0;
    arrayOfInt[2] = 0;
    arrayOfInt[3] = 0;
    int m = paramBitArray.getSize();
    boolean bool1 = false;
    boolean bool2;
    label45:
    int i;
    int j;
    if (paramInt >= m)
    {
      bool2 = bool1;
      int k = 0;
      i = paramInt;
      j = paramInt;
      paramInt = i;
      i = k;
    }
    for (;;)
    {
      if (j >= m)
      {
        throw NotFoundException.getNotFoundInstance();
        if (paramBitArray.get(paramInt)) {}
        for (bool1 = false;; bool1 = true)
        {
          bool2 = bool1;
          if (paramBoolean == bool1) {
            break label45;
          }
          paramInt += 1;
          break;
        }
      }
      if (!(paramBitArray.get(j) ^ bool2)) {
        break label145;
      }
      arrayOfInt[i] += 1;
      paramBoolean = bool2;
      j += 1;
      bool2 = paramBoolean;
    }
    label145:
    if (i == 3)
    {
      if (isFinderPattern(arrayOfInt)) {
        return new int[] { paramInt, j };
      }
      paramInt += arrayOfInt[0] + arrayOfInt[1];
      arrayOfInt[0] = arrayOfInt[2];
      arrayOfInt[1] = arrayOfInt[3];
      arrayOfInt[2] = 0;
      arrayOfInt[3] = 0;
      i -= 1;
      label216:
      arrayOfInt[i] = 1;
      if (!bool2) {
        break label241;
      }
    }
    label241:
    for (paramBoolean = false;; paramBoolean = true)
    {
      break;
      i += 1;
      break label216;
    }
  }
  
  private FinderPattern parseFoundFinderPattern(BitArray paramBitArray, int paramInt, boolean paramBoolean, int[] paramArrayOfInt)
    throws NotFoundException
  {
    boolean bool = paramBitArray.get(paramArrayOfInt[0]);
    int i = paramArrayOfInt[0] - 1;
    for (;;)
    {
      if ((i < 0) || (!(paramBitArray.get(i) ^ bool)))
      {
        int m = i + 1;
        i = paramArrayOfInt[0];
        int[] arrayOfInt = getDecodeFinderCounters();
        System.arraycopy(arrayOfInt, 0, arrayOfInt, 1, arrayOfInt.length - 1);
        arrayOfInt[0] = (i - m);
        int i1 = parseFinderValue(arrayOfInt, FINDER_PATTERNS);
        int j = m;
        int n = paramArrayOfInt[1];
        int k = j;
        i = n;
        if (paramBoolean)
        {
          k = paramBitArray.getSize() - 1 - j;
          i = paramBitArray.getSize() - 1 - n;
        }
        return new FinderPattern(i1, new int[] { m, paramArrayOfInt[1] }, k, i, paramInt);
      }
      i -= 1;
    }
  }
  
  public Result decodeRow(int paramInt, BitArray paramBitArray, Map<DecodeHintType, ?> paramMap)
    throws NotFoundException
  {
    Object localObject = decodePair(paramBitArray, false, paramInt, paramMap);
    addOrTally(this.possibleLeftPairs, (Pair)localObject);
    paramBitArray.reverse();
    paramMap = decodePair(paramBitArray, true, paramInt, paramMap);
    addOrTally(this.possibleRightPairs, paramMap);
    paramBitArray.reverse();
    Pair localPair;
    do
    {
      paramBitArray = this.possibleLeftPairs.iterator();
      while (!((Iterator)localObject).hasNext())
      {
        do
        {
          if (!paramBitArray.hasNext()) {
            throw NotFoundException.getNotFoundInstance();
          }
          paramMap = (Pair)paramBitArray.next();
        } while (paramMap.getCount() <= 1);
        localObject = this.possibleRightPairs.iterator();
      }
      localPair = (Pair)((Iterator)localObject).next();
    } while ((localPair.getCount() <= 1) || (!checkChecksum(paramMap, localPair)));
    return constructResult(paramMap, localPair);
  }
  
  public void reset()
  {
    this.possibleLeftPairs.clear();
    this.possibleRightPairs.clear();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\oned\rss\RSS14Reader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */