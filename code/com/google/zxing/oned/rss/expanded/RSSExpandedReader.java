package com.google.zxing.oned.rss.expanded;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import com.google.zxing.oned.rss.AbstractRSSReader;
import com.google.zxing.oned.rss.DataCharacter;
import com.google.zxing.oned.rss.FinderPattern;
import com.google.zxing.oned.rss.RSSUtils;
import com.google.zxing.oned.rss.expanded.decoders.AbstractExpandedDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class RSSExpandedReader
  extends AbstractRSSReader
{
  private static final int[] EVEN_TOTAL_SUBSET;
  private static final int[][] FINDER_PATTERNS;
  private static final int[][] FINDER_PATTERN_SEQUENCES;
  private static final int FINDER_PAT_A = 0;
  private static final int FINDER_PAT_B = 1;
  private static final int FINDER_PAT_C = 2;
  private static final int FINDER_PAT_D = 3;
  private static final int FINDER_PAT_E = 4;
  private static final int FINDER_PAT_F = 5;
  private static final int[] GSUM;
  private static final int LONGEST_SEQUENCE_SIZE = FINDER_PATTERN_SEQUENCES[(FINDER_PATTERN_SEQUENCES.length - 1)].length;
  private static final int MAX_PAIRS = 11;
  private static final int[] SYMBOL_WIDEST = { 7, 5, 4, 3, 1 };
  private static final int[][] WEIGHTS;
  private final int[] currentSequence = new int[LONGEST_SEQUENCE_SIZE];
  private final List<ExpandedPair> pairs = new ArrayList(11);
  private final int[] startEnd = new int[2];
  
  static
  {
    EVEN_TOTAL_SUBSET = new int[] { 4, 20, 52, 104, 204 };
    int[] arrayOfInt1 = new int[5];
    arrayOfInt1[1] = 348;
    arrayOfInt1[2] = 1388;
    arrayOfInt1[3] = 2948;
    arrayOfInt1[4] = 3988;
    GSUM = arrayOfInt1;
    arrayOfInt1 = new int[] { 1, 8, 4, 1 };
    int[] arrayOfInt2 = { 2, 6, 5, 1 };
    FINDER_PATTERNS = new int[][] { arrayOfInt1, { 3, 6, 4, 1 }, { 3, 4, 6, 1 }, { 3, 2, 8, 1 }, arrayOfInt2, { 2, 2, 9, 1 } };
    arrayOfInt1 = new int[] { 189, 145, 13, 39, 117, 140, 209, 205 };
    arrayOfInt2 = new int[] { 193, 157, 49, 147, 19, 57, 171, 91 };
    int[] arrayOfInt3 = { 43, 129, 176, 106, 107, 110, 119, 146 };
    int[] arrayOfInt4 = { 109, 116, 137, 200, 178, 112, 125, 164 };
    int[] arrayOfInt5 = { 120, 149, 25, 75, 14, 42, 126, 167 };
    int[] arrayOfInt6 = { 79, 26, 78, 23, 69, 207, 199, 175 };
    int[] arrayOfInt7 = { 161, 61, 183, 127, 170, 88, 53, 159 };
    WEIGHTS = new int[][] { { 1, 3, 9, 27, 81, 32, 96, 77 }, { 20, 60, 180, 118, 143, 7, 21, 63 }, arrayOfInt1, arrayOfInt2, { 62, 186, 136, 197, 169, 85, 44, 132 }, { 185, 133, 188, 142, 4, 12, 36, 108 }, { 113, 128, 173, 97, 80, 29, 87, 50 }, { 150, 28, 84, 41, 123, 158, 52, 156 }, { 46, 138, 203, 187, 139, 206, 196, 166 }, { 76, 17, 51, 153, 37, 111, 122, 155 }, arrayOfInt3, { 16, 48, 144, 10, 30, 90, 59, 177 }, arrayOfInt4, { 70, 210, 208, 202, 184, 130, 179, 115 }, { 134, 191, 151, 31, 93, 68, 204, 190 }, { 148, 22, 66, 198, 172, 94, 71, 2 }, { 6, 18, 54, 162, 64, 192, 154, 40 }, arrayOfInt5, arrayOfInt6, { 103, 98, 83, 38, 114, 131, 182, 124 }, arrayOfInt7, { 55, 165, 73, 8, 24, 72, 5, 15 }, { 45, 135, 194, 160, 58, 174, 100, 89 } };
    arrayOfInt1 = new int[2];
    arrayOfInt2 = new int[3];
    arrayOfInt2[1] = 1;
    arrayOfInt2[2] = 1;
    arrayOfInt3 = new int[4];
    arrayOfInt3[1] = 2;
    arrayOfInt3[2] = 1;
    arrayOfInt3[3] = 3;
    arrayOfInt4 = new int[5];
    arrayOfInt4[1] = 4;
    arrayOfInt4[2] = 1;
    arrayOfInt4[3] = 3;
    arrayOfInt4[4] = 2;
    arrayOfInt5 = new int[6];
    arrayOfInt5[1] = 4;
    arrayOfInt5[2] = 1;
    arrayOfInt5[3] = 3;
    arrayOfInt5[4] = 3;
    arrayOfInt5[5] = 5;
    arrayOfInt6 = new int[7];
    arrayOfInt6[1] = 4;
    arrayOfInt6[2] = 1;
    arrayOfInt6[3] = 3;
    arrayOfInt6[4] = 4;
    arrayOfInt6[5] = 5;
    arrayOfInt6[6] = 5;
    arrayOfInt7 = new int[8];
    arrayOfInt7[2] = 1;
    arrayOfInt7[3] = 1;
    arrayOfInt7[4] = 2;
    arrayOfInt7[5] = 2;
    arrayOfInt7[6] = 3;
    arrayOfInt7[7] = 3;
    int[] arrayOfInt8 = new int[9];
    arrayOfInt8[2] = 1;
    arrayOfInt8[3] = 1;
    arrayOfInt8[4] = 2;
    arrayOfInt8[5] = 2;
    arrayOfInt8[6] = 3;
    arrayOfInt8[7] = 4;
    arrayOfInt8[8] = 4;
    int[] arrayOfInt9 = new int[10];
    arrayOfInt9[2] = 1;
    arrayOfInt9[3] = 1;
    arrayOfInt9[4] = 2;
    arrayOfInt9[5] = 2;
    arrayOfInt9[6] = 3;
    arrayOfInt9[7] = 4;
    arrayOfInt9[8] = 5;
    arrayOfInt9[9] = 5;
    int[] arrayOfInt10 = new int[11];
    arrayOfInt10[2] = 1;
    arrayOfInt10[3] = 1;
    arrayOfInt10[4] = 2;
    arrayOfInt10[5] = 3;
    arrayOfInt10[6] = 3;
    arrayOfInt10[7] = 4;
    arrayOfInt10[8] = 4;
    arrayOfInt10[9] = 5;
    arrayOfInt10[10] = 5;
    FINDER_PATTERN_SEQUENCES = new int[][] { arrayOfInt1, arrayOfInt2, arrayOfInt3, arrayOfInt4, arrayOfInt5, arrayOfInt6, arrayOfInt7, arrayOfInt8, arrayOfInt9, arrayOfInt10 };
  }
  
  private void adjustOddEvenCounts(int paramInt)
    throws NotFoundException
  {
    int m = 0;
    int i2 = count(getOddCounts());
    int i3 = count(getEvenCounts());
    int i4 = i2 + i3 - paramInt;
    int n;
    int j;
    int i;
    label64:
    int k;
    int i1;
    if ((i2 & 0x1) == 1)
    {
      n = 1;
      if ((i3 & 0x1) == 0) {
        m = 1;
      }
      paramInt = 0;
      j = 0;
      if (i2 <= 13) {
        break label105;
      }
      i = 1;
      k = 0;
      i1 = 0;
      if (i3 <= 13) {
        break label120;
      }
      j = 1;
    }
    for (;;)
    {
      if (i4 == 1) {
        if (n != 0)
        {
          if (m != 0)
          {
            throw NotFoundException.getNotFoundInstance();
            n = 0;
            break;
            label105:
            i = j;
            if (i2 >= 4) {
              break label64;
            }
            paramInt = 1;
            i = j;
            break label64;
            label120:
            j = i1;
            if (i3 >= 4) {
              continue;
            }
            k = 1;
            j = i1;
            continue;
          }
          i = 1;
        }
      }
    }
    while (paramInt != 0) {
      if (i != 0)
      {
        throw NotFoundException.getNotFoundInstance();
        if (m == 0) {
          throw NotFoundException.getNotFoundInstance();
        }
        j = 1;
        continue;
        if (i4 == -1)
        {
          if (n != 0)
          {
            if (m != 0) {
              throw NotFoundException.getNotFoundInstance();
            }
            paramInt = 1;
          }
          else
          {
            if (m == 0) {
              throw NotFoundException.getNotFoundInstance();
            }
            k = 1;
          }
        }
        else if (i4 == 0)
        {
          if (n != 0)
          {
            if (m == 0) {
              throw NotFoundException.getNotFoundInstance();
            }
            if (i2 < i3)
            {
              paramInt = 1;
              j = 1;
            }
            else
            {
              i = 1;
              k = 1;
            }
          }
          else if (m != 0)
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
    if (i != 0) {
      decrement(getOddCounts(), getOddRoundingErrors());
    }
    if (k != 0)
    {
      if (j != 0) {
        throw NotFoundException.getNotFoundInstance();
      }
      increment(getEvenCounts(), getOddRoundingErrors());
    }
    if (j != 0) {
      decrement(getEvenCounts(), getEvenRoundingErrors());
    }
  }
  
  private boolean checkChecksum()
  {
    boolean bool = false;
    Object localObject = (ExpandedPair)this.pairs.get(0);
    DataCharacter localDataCharacter = ((ExpandedPair)localObject).getLeftChar();
    int j = ((ExpandedPair)localObject).getRightChar().getChecksumPortion();
    int i = 2;
    int k = 1;
    for (;;)
    {
      if (k >= this.pairs.size())
      {
        if ((i - 4) * 211 + j % 211 == localDataCharacter.getValue()) {
          bool = true;
        }
        return bool;
      }
      localObject = (ExpandedPair)this.pairs.get(k);
      int m = j + ((ExpandedPair)localObject).getLeftChar().getChecksumPortion();
      int n = i + 1;
      localObject = ((ExpandedPair)localObject).getRightChar();
      j = m;
      i = n;
      if (localObject != null)
      {
        j = m + ((DataCharacter)localObject).getChecksumPortion();
        i = n + 1;
      }
      k += 1;
    }
  }
  
  private boolean checkPairSequence(List<ExpandedPair> paramList, FinderPattern paramFinderPattern)
    throws NotFoundException
  {
    int m = paramList.size() + 1;
    if (m > this.currentSequence.length) {
      throw NotFoundException.getNotFoundInstance();
    }
    int i = 0;
    int n;
    if (i >= paramList.size())
    {
      this.currentSequence[(m - 1)] = paramFinderPattern.getValue();
      paramList = FINDER_PATTERN_SEQUENCES;
      n = paramList.length;
      i = 0;
    }
    for (;;)
    {
      if (i >= n)
      {
        throw NotFoundException.getNotFoundInstance();
        this.currentSequence[i] = ((ExpandedPair)paramList.get(i)).getFinderPattern().getValue();
        i += 1;
        break;
      }
      paramFinderPattern = paramList[i];
      if (paramFinderPattern.length >= m)
      {
        int k = 1;
        int j = 0;
        for (;;)
        {
          if (j >= m) {}
          for (j = k;; j = 0)
          {
            if (j == 0) {
              break label171;
            }
            if (m != paramFinderPattern.length) {
              break label169;
            }
            return true;
            if (this.currentSequence[j] == paramFinderPattern[j]) {
              break;
            }
          }
          j += 1;
        }
        label169:
        return false;
      }
      label171:
      i += 1;
    }
  }
  
  private static Result constructResult(List<ExpandedPair> paramList)
    throws NotFoundException
  {
    String str = AbstractExpandedDecoder.createDecoder(BitArrayBuilder.buildBitArray(paramList)).parseInformation();
    Object localObject1 = ((ExpandedPair)paramList.get(0)).getFinderPattern().getResultPoints();
    Object localObject3 = ((ExpandedPair)paramList.get(paramList.size() - 1)).getFinderPattern().getResultPoints();
    paramList = localObject1[0];
    localObject1 = localObject1[1];
    Object localObject2 = localObject3[0];
    localObject3 = localObject3[1];
    BarcodeFormat localBarcodeFormat = BarcodeFormat.RSS_EXPANDED;
    return new Result(str, null, new ResultPoint[] { paramList, localObject1, localObject2, localObject3 }, localBarcodeFormat);
  }
  
  private void findNextPair(BitArray paramBitArray, List<ExpandedPair> paramList, int paramInt)
    throws NotFoundException
  {
    int[] arrayOfInt = getDecodeFinderCounters();
    arrayOfInt[0] = 0;
    arrayOfInt[1] = 0;
    arrayOfInt[2] = 0;
    arrayOfInt[3] = 0;
    int i1 = paramBitArray.getSize();
    int k;
    label50:
    int i;
    label63:
    int m;
    if (paramInt >= 0)
    {
      if (paramList.size() % 2 == 0) {
        break label133;
      }
      k = 1;
      i = 0;
      if (paramInt < i1) {
        break label139;
      }
      j = i;
      int n = 0;
      i = paramInt;
      m = paramInt;
      paramInt = i;
      i = n;
    }
    for (;;)
    {
      if (m >= i1)
      {
        throw NotFoundException.getNotFoundInstance();
        if (paramList.isEmpty())
        {
          paramInt = 0;
          break;
        }
        paramInt = ((ExpandedPair)paramList.get(paramList.size() - 1)).getFinderPattern().getStartEnd()[1];
        break;
        label133:
        k = 0;
        break label50;
        label139:
        if (paramBitArray.get(paramInt)) {}
        for (i = 0;; i = 1)
        {
          j = i;
          if (i == 0) {
            break label63;
          }
          paramInt += 1;
          break;
        }
      }
      if (!(paramBitArray.get(m) ^ j)) {
        break label205;
      }
      arrayOfInt[i] += 1;
      m += 1;
    }
    label205:
    if (i == 3)
    {
      if (k != 0) {
        reverseCounters(arrayOfInt);
      }
      if (isFinderPattern(arrayOfInt))
      {
        this.startEnd[0] = paramInt;
        this.startEnd[1] = m;
        return;
      }
      if (k != 0) {
        reverseCounters(arrayOfInt);
      }
      paramInt += arrayOfInt[0] + arrayOfInt[1];
      arrayOfInt[0] = arrayOfInt[2];
      arrayOfInt[1] = arrayOfInt[3];
      arrayOfInt[2] = 0;
      arrayOfInt[3] = 0;
      i -= 1;
      label299:
      arrayOfInt[i] = 1;
      if (j == 0) {
        break label325;
      }
    }
    label325:
    for (int j = 0;; j = 1)
    {
      break;
      i += 1;
      break label299;
    }
  }
  
  private static int getNextSecondBar(BitArray paramBitArray, int paramInt)
  {
    if (paramBitArray.get(paramInt)) {
      return paramBitArray.getNextSet(paramBitArray.getNextUnset(paramInt));
    }
    return paramBitArray.getNextUnset(paramBitArray.getNextSet(paramInt));
  }
  
  private static boolean isNotA1left(FinderPattern paramFinderPattern, boolean paramBoolean1, boolean paramBoolean2)
  {
    return (paramFinderPattern.getValue() != 0) || (!paramBoolean1) || (!paramBoolean2);
  }
  
  private FinderPattern parseFoundFinderPattern(BitArray paramBitArray, int paramInt, boolean paramBoolean)
  {
    int i;
    int k;
    int j;
    if (paramBoolean)
    {
      i = this.startEnd[0] - 1;
      if ((i < 0) || (paramBitArray.get(i)))
      {
        i += 1;
        k = this.startEnd[0] - i;
        j = this.startEnd[1];
      }
    }
    for (;;)
    {
      paramBitArray = getDecodeFinderCounters();
      System.arraycopy(paramBitArray, 0, paramBitArray, 1, paramBitArray.length - 1);
      paramBitArray[0] = k;
      try
      {
        k = parseFinderValue(paramBitArray, FINDER_PATTERNS);
        return new FinderPattern(k, new int[] { i, j }, i, j, paramInt);
      }
      catch (NotFoundException paramBitArray) {}
      i -= 1;
      break;
      i = this.startEnd[0];
      j = paramBitArray.getNextUnset(this.startEnd[1] + 1);
      k = j - this.startEnd[1];
    }
    return null;
  }
  
  private static void reverseCounters(int[] paramArrayOfInt)
  {
    int j = paramArrayOfInt.length;
    int i = 0;
    for (;;)
    {
      if (i >= j / 2) {
        return;
      }
      int k = paramArrayOfInt[i];
      paramArrayOfInt[i] = paramArrayOfInt[(j - i - 1)];
      paramArrayOfInt[(j - i - 1)] = k;
      i += 1;
    }
  }
  
  DataCharacter decodeDataCharacter(BitArray paramBitArray, FinderPattern paramFinderPattern, boolean paramBoolean1, boolean paramBoolean2)
    throws NotFoundException
  {
    int[] arrayOfInt1 = getDataCharacterCounters();
    arrayOfInt1[0] = 0;
    arrayOfInt1[1] = 0;
    arrayOfInt1[2] = 0;
    arrayOfInt1[3] = 0;
    arrayOfInt1[4] = 0;
    arrayOfInt1[5] = 0;
    arrayOfInt1[6] = 0;
    arrayOfInt1[7] = 0;
    float f1;
    int[] arrayOfInt2;
    float[] arrayOfFloat1;
    float[] arrayOfFloat2;
    int k;
    int i;
    label130:
    label138:
    int i2;
    if (paramBoolean2)
    {
      recordPatternInReverse(paramBitArray, paramFinderPattern.getStartEnd()[0], arrayOfInt1);
      f1 = count(arrayOfInt1) / 17;
      paramBitArray = getOddCounts();
      arrayOfInt2 = getEvenCounts();
      arrayOfFloat1 = getOddRoundingErrors();
      arrayOfFloat2 = getEvenRoundingErrors();
      j = 0;
      if (j < arrayOfInt1.length) {
        break label281;
      }
      adjustOddEvenCounts(17);
      k = paramFinderPattern.getValue();
      if (!paramBoolean1) {
        break label389;
      }
      i = 0;
      if (!paramBoolean2) {
        break label395;
      }
      j = 0;
      i2 = j + (k * 4 + i) - 1;
      j = 0;
      i = 0;
      k = paramBitArray.length - 1;
      label164:
      if (k >= 0) {
        break label401;
      }
      k = 0;
      n = 0;
      m = arrayOfInt2.length - 1;
    }
    for (;;)
    {
      if (m < 0)
      {
        if (((j & 0x1) == 0) && (j <= 13) && (j >= 4)) {
          break label527;
        }
        throw NotFoundException.getNotFoundInstance();
        recordPattern(paramBitArray, paramFinderPattern.getStartEnd()[1] + 1, arrayOfInt1);
        j = 0;
        i = arrayOfInt1.length - 1;
        while (j < i)
        {
          k = arrayOfInt1[j];
          arrayOfInt1[j] = arrayOfInt1[i];
          arrayOfInt1[i] = k;
          j += 1;
          i -= 1;
        }
        break;
        label281:
        float f2 = 1.0F * arrayOfInt1[j] / f1;
        k = (int)(0.5F + f2);
        if (k < 1)
        {
          i = 1;
          label311:
          k = j >> 1;
          if ((j & 0x1) != 0) {
            break label368;
          }
          paramBitArray[k] = i;
          arrayOfFloat1[k] = (f2 - i);
        }
        for (;;)
        {
          j += 1;
          break;
          i = k;
          if (k <= 8) {
            break label311;
          }
          i = 8;
          break label311;
          label368:
          arrayOfInt2[k] = i;
          arrayOfFloat2[k] = (f2 - i);
        }
        label389:
        i = 2;
        break label130;
        label395:
        j = 1;
        break label138;
        label401:
        m = i;
        if (isNotA1left(paramFinderPattern, paramBoolean1, paramBoolean2))
        {
          m = WEIGHTS[i2][(k * 2)];
          m = i + paramBitArray[k] * m;
        }
        j += paramBitArray[k];
        k -= 1;
        i = m;
        break label164;
      }
      int i1 = k;
      if (isNotA1left(paramFinderPattern, paramBoolean1, paramBoolean2))
      {
        i1 = WEIGHTS[i2][(m * 2 + 1)];
        i1 = k + arrayOfInt2[m] * i1;
      }
      n += arrayOfInt2[m];
      m -= 1;
      k = i1;
    }
    label527:
    int j = (13 - j) / 2;
    int n = SYMBOL_WIDEST[j];
    int m = RSSUtils.getRSSvalue(paramBitArray, n, true);
    n = RSSUtils.getRSSvalue(arrayOfInt2, 9 - n, false);
    return new DataCharacter(m * EVEN_TOTAL_SUBSET[j] + n + GSUM[j], i + k);
  }
  
  public Result decodeRow(int paramInt, BitArray paramBitArray, Map<DecodeHintType, ?> paramMap)
    throws NotFoundException
  {
    reset();
    decodeRow2pairs(paramInt, paramBitArray);
    return constructResult(this.pairs);
  }
  
  List<ExpandedPair> decodeRow2pairs(int paramInt, BitArray paramBitArray)
    throws NotFoundException
  {
    ExpandedPair localExpandedPair;
    do
    {
      do
      {
        localExpandedPair = retrieveNextPair(paramBitArray, this.pairs, paramInt);
        this.pairs.add(localExpandedPair);
      } while (!localExpandedPair.mayBeLast());
      if (checkChecksum()) {
        return this.pairs;
      }
    } while (!localExpandedPair.mustBeLast());
    throw NotFoundException.getNotFoundInstance();
  }
  
  public void reset()
  {
    this.pairs.clear();
  }
  
  ExpandedPair retrieveNextPair(BitArray paramBitArray, List<ExpandedPair> paramList, int paramInt)
    throws NotFoundException
  {
    boolean bool1;
    if (paramList.size() % 2 == 0) {
      bool1 = true;
    }
    for (;;)
    {
      int i = 1;
      int k = -1;
      FinderPattern localFinderPattern;
      int j;
      do
      {
        findNextPair(paramBitArray, paramList, k);
        localFinderPattern = parseFoundFinderPattern(paramBitArray, paramInt, bool1);
        if (localFinderPattern != null) {
          break;
        }
        k = getNextSecondBar(paramBitArray, this.startEnd[0]);
        j = i;
        i = j;
      } while (j != 0);
      boolean bool2 = checkPairSequence(paramList, localFinderPattern);
      paramList = decodeDataCharacter(paramBitArray, localFinderPattern, bool1, true);
      try
      {
        paramBitArray = decodeDataCharacter(paramBitArray, localFinderPattern, bool1, false);
        return new ExpandedPair(paramList, paramBitArray, localFinderPattern, bool2);
        bool1 = false;
        continue;
        j = 0;
      }
      catch (NotFoundException paramBitArray)
      {
        while (bool2) {
          paramBitArray = null;
        }
        throw paramBitArray;
      }
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\oned\rss\expanded\RSSExpandedReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */