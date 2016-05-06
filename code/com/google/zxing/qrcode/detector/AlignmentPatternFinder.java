package com.google.zxing.qrcode.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitMatrix;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

final class AlignmentPatternFinder
{
  private final int[] crossCheckStateCount;
  private final int height;
  private final BitMatrix image;
  private final float moduleSize;
  private final List<AlignmentPattern> possibleCenters;
  private final ResultPointCallback resultPointCallback;
  private final int startX;
  private final int startY;
  private final int width;
  
  AlignmentPatternFinder(BitMatrix paramBitMatrix, int paramInt1, int paramInt2, int paramInt3, int paramInt4, float paramFloat, ResultPointCallback paramResultPointCallback)
  {
    this.image = paramBitMatrix;
    this.possibleCenters = new ArrayList(5);
    this.startX = paramInt1;
    this.startY = paramInt2;
    this.width = paramInt3;
    this.height = paramInt4;
    this.moduleSize = paramFloat;
    this.crossCheckStateCount = new int[3];
    this.resultPointCallback = paramResultPointCallback;
  }
  
  private static float centerFromEnd(int[] paramArrayOfInt, int paramInt)
  {
    return paramInt - paramArrayOfInt[2] - paramArrayOfInt[1] / 2.0F;
  }
  
  private float crossCheckVertical(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    BitMatrix localBitMatrix = this.image;
    int j = localBitMatrix.getHeight();
    int[] arrayOfInt = this.crossCheckStateCount;
    arrayOfInt[0] = 0;
    arrayOfInt[1] = 0;
    arrayOfInt[2] = 0;
    int i = paramInt1;
    if ((i < 0) || (!localBitMatrix.get(paramInt2, i)) || (arrayOfInt[1] > paramInt3)) {
      if ((i >= 0) && (arrayOfInt[1] <= paramInt3)) {
        break label112;
      }
    }
    label112:
    label148:
    do
    {
      do
      {
        return NaN.0F;
        arrayOfInt[1] += 1;
        i -= 1;
        break;
        do
        {
          arrayOfInt[0] += 1;
          i -= 1;
        } while ((i >= 0) && (!localBitMatrix.get(paramInt2, i)) && (arrayOfInt[0] <= paramInt3));
      } while (arrayOfInt[0] > paramInt3);
      paramInt1 += 1;
      if ((paramInt1 < j) && (localBitMatrix.get(paramInt2, paramInt1)) && (arrayOfInt[1] <= paramInt3)) {
        break label263;
      }
    } while ((paramInt1 == j) || (arrayOfInt[1] > paramInt3));
    for (;;)
    {
      if ((paramInt1 >= j) || (localBitMatrix.get(paramInt2, paramInt1)) || (arrayOfInt[2] > paramInt3))
      {
        if ((arrayOfInt[2] > paramInt3) || (Math.abs(arrayOfInt[0] + arrayOfInt[1] + arrayOfInt[2] - paramInt4) * 5 >= paramInt4 * 2) || (!foundPatternCross(arrayOfInt))) {
          break;
        }
        return centerFromEnd(arrayOfInt, paramInt1);
        label263:
        arrayOfInt[1] += 1;
        paramInt1 += 1;
        break label148;
      }
      arrayOfInt[2] += 1;
      paramInt1 += 1;
    }
  }
  
  private boolean foundPatternCross(int[] paramArrayOfInt)
  {
    float f1 = this.moduleSize;
    float f2 = f1 / 2.0F;
    int i = 0;
    for (;;)
    {
      if (i >= 3) {
        return true;
      }
      if (Math.abs(f1 - paramArrayOfInt[i]) >= f2) {
        return false;
      }
      i += 1;
    }
  }
  
  private AlignmentPattern handlePossibleCenter(int[] paramArrayOfInt, int paramInt1, int paramInt2)
  {
    int i = paramArrayOfInt[0];
    int j = paramArrayOfInt[1];
    int k = paramArrayOfInt[2];
    float f1 = centerFromEnd(paramArrayOfInt, paramInt2);
    float f2 = crossCheckVertical(paramInt1, (int)f1, paramArrayOfInt[1] * 2, i + j + k);
    float f3;
    if (!Float.isNaN(f2))
    {
      f3 = (paramArrayOfInt[0] + paramArrayOfInt[1] + paramArrayOfInt[2]) / 3.0F;
      paramArrayOfInt = this.possibleCenters.iterator();
    }
    AlignmentPattern localAlignmentPattern;
    do
    {
      if (!paramArrayOfInt.hasNext())
      {
        paramArrayOfInt = new AlignmentPattern(f1, f2, f3);
        this.possibleCenters.add(paramArrayOfInt);
        if (this.resultPointCallback != null) {
          this.resultPointCallback.foundPossibleResultPoint(paramArrayOfInt);
        }
        return null;
      }
      localAlignmentPattern = (AlignmentPattern)paramArrayOfInt.next();
    } while (!localAlignmentPattern.aboutEquals(f3, f2, f1));
    return localAlignmentPattern.combineEstimate(f2, f1, f3);
  }
  
  AlignmentPattern find()
    throws NotFoundException
  {
    int n = this.startX;
    int i1 = this.height;
    int i2 = n + this.width;
    int i3 = this.startY;
    int[] arrayOfInt = new int[3];
    int k = 0;
    Object localObject;
    if (k >= i1)
    {
      if (!this.possibleCenters.isEmpty())
      {
        localObject = (AlignmentPattern)this.possibleCenters.get(0);
        label67:
        return (AlignmentPattern)localObject;
      }
    }
    else
    {
      int i;
      label82:
      int i4;
      if ((k & 0x1) == 0)
      {
        i = k + 1 >> 1;
        i4 = i3 + (i1 >> 1) + i;
        arrayOfInt[0] = 0;
        arrayOfInt[1] = 0;
        arrayOfInt[2] = 0;
        i = n;
      }
      int j;
      int m;
      label137:
      AlignmentPattern localAlignmentPattern;
      for (;;)
      {
        if ((i >= i2) || (this.image.get(i, i4)))
        {
          j = 0;
          m = i;
          i = j;
          if (m < i2) {
            break label198;
          }
          if (foundPatternCross(arrayOfInt))
          {
            localAlignmentPattern = handlePossibleCenter(arrayOfInt, i4, i2);
            localObject = localAlignmentPattern;
            if (localAlignmentPattern != null) {
              break label67;
            }
          }
          k += 1;
          break;
          i = -(k + 1 >> 1);
          break label82;
        }
        i += 1;
      }
      label198:
      if (this.image.get(m, i4)) {
        if (i == 1) {
          arrayOfInt[i] += 1;
        }
      }
      for (;;)
      {
        m += 1;
        break label137;
        if (i == 2)
        {
          if (foundPatternCross(arrayOfInt))
          {
            localAlignmentPattern = handlePossibleCenter(arrayOfInt, i4, m);
            localObject = localAlignmentPattern;
            if (localAlignmentPattern != null) {
              break;
            }
          }
          arrayOfInt[0] = arrayOfInt[2];
          arrayOfInt[1] = 1;
          arrayOfInt[2] = 0;
          i = 1;
          continue;
        }
        i += 1;
        arrayOfInt[i] += 1;
        continue;
        j = i;
        if (i == 1) {
          j = i + 1;
        }
        arrayOfInt[j] += 1;
        i = j;
      }
    }
    throw NotFoundException.getNotFoundInstance();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\qrcode\detector\AlignmentPatternFinder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */