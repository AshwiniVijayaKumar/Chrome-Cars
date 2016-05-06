package com.google.zxing.aztec.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.aztec.AztecDetectorResult;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.GridSampler;
import com.google.zxing.common.detector.MathUtils;
import com.google.zxing.common.detector.WhiteRectangleDetector;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonDecoder;
import com.google.zxing.common.reedsolomon.ReedSolomonException;

public final class Detector
{
  private boolean compact;
  private final BitMatrix image;
  private int nbCenterLayers;
  private int nbDataBlocks;
  private int nbLayers;
  private int shift;
  
  public Detector(BitMatrix paramBitMatrix)
  {
    this.image = paramBitMatrix;
  }
  
  private static void correctParameterData(boolean[] paramArrayOfBoolean, boolean paramBoolean)
    throws NotFoundException
  {
    int j;
    int i;
    int[] arrayOfInt;
    int k;
    if (paramBoolean)
    {
      j = 7;
      i = 2;
      arrayOfInt = new int[j];
      k = 0;
      label17:
      if (k < j) {
        break label57;
      }
    }
    label57:
    int m;
    for (;;)
    {
      try
      {
        new ReedSolomonDecoder(GenericGF.AZTEC_PARAM).decode(arrayOfInt, j - i);
        j = 0;
        if (j < i) {
          break label125;
        }
        return;
      }
      catch (ReedSolomonException paramArrayOfBoolean)
      {
        int n;
        throw NotFoundException.getNotFoundInstance();
      }
      j = 10;
      i = 4;
      break;
      n = 1;
      m = 1;
      if (m > 4)
      {
        k += 1;
        break label17;
      }
      if (paramArrayOfBoolean[(4 * k + 4 - m)] != 0) {
        arrayOfInt[k] += n;
      }
      n <<= 1;
      m += 1;
      continue;
      label125:
      m = 1;
      k = 1;
      if (k <= 4) {
        break label144;
      }
      j += 1;
    }
    label144:
    if ((arrayOfInt[j] & m) == m) {}
    for (paramBoolean = true;; paramBoolean = false)
    {
      paramArrayOfBoolean[(j * 4 + 4 - k)] = paramBoolean;
      m <<= 1;
      k += 1;
      break;
    }
  }
  
  private static float distance(Point paramPoint1, Point paramPoint2)
  {
    return MathUtils.distance(paramPoint1.x, paramPoint1.y, paramPoint2.x, paramPoint2.y);
  }
  
  private void extractParameters(Point[] paramArrayOfPoint)
    throws NotFoundException
  {
    boolean[] arrayOfBoolean1 = sampleLine(paramArrayOfPoint[0], paramArrayOfPoint[1], this.nbCenterLayers * 2 + 1);
    boolean[] arrayOfBoolean2 = sampleLine(paramArrayOfPoint[1], paramArrayOfPoint[2], this.nbCenterLayers * 2 + 1);
    boolean[] arrayOfBoolean3 = sampleLine(paramArrayOfPoint[2], paramArrayOfPoint[3], this.nbCenterLayers * 2 + 1);
    paramArrayOfPoint = sampleLine(paramArrayOfPoint[3], paramArrayOfPoint[0], this.nbCenterLayers * 2 + 1);
    if ((arrayOfBoolean1[0] != 0) && (arrayOfBoolean1[(this.nbCenterLayers * 2)] != 0))
    {
      this.shift = 0;
      if (!this.compact) {
        break label310;
      }
      arrayOfBoolean4 = new boolean[28];
      i = 0;
      label115:
      if (i < 7) {
        break label231;
      }
      paramArrayOfPoint = new boolean[28];
      i = 0;
    }
    for (;;)
    {
      if (i >= 28)
      {
        correctParameterData(paramArrayOfPoint, this.compact);
        getParameters(paramArrayOfPoint);
        return;
        if ((arrayOfBoolean2[0] != 0) && (arrayOfBoolean2[(this.nbCenterLayers * 2)] != 0))
        {
          this.shift = 1;
          break;
        }
        if ((arrayOfBoolean3[0] != 0) && (arrayOfBoolean3[(this.nbCenterLayers * 2)] != 0))
        {
          this.shift = 2;
          break;
        }
        if ((paramArrayOfPoint[0] != 0) && (paramArrayOfPoint[(this.nbCenterLayers * 2)] != 0))
        {
          this.shift = 3;
          break;
        }
        throw NotFoundException.getNotFoundInstance();
        label231:
        arrayOfBoolean4[i] = arrayOfBoolean1[(i + 2)];
        arrayOfBoolean4[(i + 7)] = arrayOfBoolean2[(i + 2)];
        arrayOfBoolean4[(i + 14)] = arrayOfBoolean3[(i + 2)];
        arrayOfBoolean4[(i + 21)] = paramArrayOfPoint[(i + 2)];
        i += 1;
        break label115;
      }
      paramArrayOfPoint[i] = arrayOfBoolean4[((this.shift * 7 + i) % 28)];
      i += 1;
    }
    label310:
    boolean[] arrayOfBoolean4 = new boolean[40];
    int i = 0;
    for (;;)
    {
      if (i >= 11)
      {
        arrayOfBoolean1 = new boolean[40];
        i = 0;
        for (;;)
        {
          paramArrayOfPoint = arrayOfBoolean1;
          if (i >= 40) {
            break;
          }
          arrayOfBoolean1[i] = arrayOfBoolean4[((this.shift * 10 + i) % 40)];
          i += 1;
        }
      }
      if (i < 5)
      {
        arrayOfBoolean4[i] = arrayOfBoolean1[(i + 2)];
        arrayOfBoolean4[(i + 10)] = arrayOfBoolean2[(i + 2)];
        arrayOfBoolean4[(i + 20)] = arrayOfBoolean3[(i + 2)];
        arrayOfBoolean4[(i + 30)] = paramArrayOfPoint[(i + 2)];
      }
      if (i > 5)
      {
        arrayOfBoolean4[(i - 1)] = arrayOfBoolean1[(i + 2)];
        arrayOfBoolean4[(i + 10 - 1)] = arrayOfBoolean2[(i + 2)];
        arrayOfBoolean4[(i + 20 - 1)] = arrayOfBoolean3[(i + 2)];
        arrayOfBoolean4[(i + 30 - 1)] = paramArrayOfPoint[(i + 2)];
      }
      i += 1;
    }
  }
  
  private Point[] getBullEyeCornerPoints(Point paramPoint)
    throws NotFoundException
  {
    Point localPoint2 = paramPoint;
    Object localObject1 = paramPoint;
    Object localObject2 = paramPoint;
    boolean bool = true;
    this.nbCenterLayers = 1;
    Point localPoint1 = paramPoint;
    paramPoint = localPoint2;
    if (this.nbCenterLayers >= 9) {}
    float f;
    while ((this.nbCenterLayers != 5) && (this.nbCenterLayers != 7))
    {
      throw NotFoundException.getNotFoundInstance();
      Point localPoint5 = getFirstDifferent(paramPoint, bool, 1, -1);
      Point localPoint4 = getFirstDifferent((Point)localObject1, bool, 1, 1);
      Point localPoint3 = getFirstDifferent((Point)localObject2, bool, -1, 1);
      localPoint2 = getFirstDifferent(localPoint1, bool, -1, -1);
      if (this.nbCenterLayers > 2)
      {
        f = distance(localPoint2, localPoint5) * this.nbCenterLayers / (distance(localPoint1, paramPoint) * (this.nbCenterLayers + 2));
        if ((f < 0.75D) || (f > 1.25D) || (!isWhiteOrBlackRectangle(localPoint5, localPoint4, localPoint3, localPoint2))) {
          break;
        }
      }
      else
      {
        paramPoint = localPoint5;
        localObject1 = localPoint4;
        localObject2 = localPoint3;
        localPoint1 = localPoint2;
        if (bool) {}
        for (bool = false;; bool = true)
        {
          this.nbCenterLayers += 1;
          break;
        }
      }
    }
    if (this.nbCenterLayers == 5) {}
    int k;
    int m;
    int i;
    int j;
    int i3;
    int i2;
    int n;
    int i1;
    for (bool = true;; bool = false)
    {
      this.compact = bool;
      f = 1.5F / (this.nbCenterLayers * 2 - 3);
      k = paramPoint.x - ((Point)localObject2).x;
      m = paramPoint.y - ((Point)localObject2).y;
      i = MathUtils.round(((Point)localObject2).x - k * f);
      j = MathUtils.round(((Point)localObject2).y - m * f);
      k = MathUtils.round(paramPoint.x + k * f);
      m = MathUtils.round(paramPoint.y + m * f);
      i3 = ((Point)localObject1).x - localPoint1.x;
      i2 = ((Point)localObject1).y - localPoint1.y;
      n = MathUtils.round(localPoint1.x - i3 * f);
      i1 = MathUtils.round(localPoint1.y - i2 * f);
      i3 = MathUtils.round(((Point)localObject1).x + i3 * f);
      i2 = MathUtils.round(((Point)localObject1).y + i2 * f);
      if ((isValid(k, m)) && (isValid(i3, i2)) && (isValid(i, j)) && (isValid(n, i1))) {
        break;
      }
      throw NotFoundException.getNotFoundInstance();
    }
    return new Point[] { new Point(k, m, null), new Point(i3, i2, null), new Point(i, j, null), new Point(n, i1, null) };
  }
  
  private int getColor(Point paramPoint1, Point paramPoint2)
  {
    float f3 = distance(paramPoint1, paramPoint2);
    float f4 = (paramPoint2.x - paramPoint1.x) / f3;
    float f5 = (paramPoint2.y - paramPoint1.y) / f3;
    int j = 0;
    float f2 = paramPoint1.x;
    float f1 = paramPoint1.y;
    boolean bool = this.image.get(paramPoint1.x, paramPoint1.y);
    int i = 0;
    for (;;)
    {
      if (i >= f3)
      {
        f1 = j / f3;
        if ((f1 <= 0.1D) || (f1 >= 0.9D)) {
          break;
        }
        return 0;
      }
      f2 += f4;
      f1 += f5;
      int k = j;
      if (this.image.get(MathUtils.round(f2), MathUtils.round(f1)) != bool) {
        k = j + 1;
      }
      i += 1;
      j = k;
    }
    if (f1 <= 0.1D)
    {
      if (bool) {
        return 1;
      }
      return -1;
    }
    if (bool) {
      return -1;
    }
    return 1;
  }
  
  private Point getFirstDifferent(Point paramPoint, boolean paramBoolean, int paramInt1, int paramInt2)
  {
    int j = paramPoint.x + paramInt1;
    int i = paramPoint.y + paramInt2;
    if ((!isValid(j, i)) || (this.image.get(j, i) != paramBoolean))
    {
      int k = j - paramInt1;
      j = i - paramInt2;
      i = k;
      label60:
      if ((isValid(i, j)) && (this.image.get(i, j) == paramBoolean)) {
        break label150;
      }
      i -= paramInt1;
      paramInt1 = j;
    }
    for (;;)
    {
      if ((!isValid(i, paramInt1)) || (this.image.get(i, paramInt1) != paramBoolean))
      {
        return new Point(i, paramInt1 - paramInt2, null);
        j += paramInt1;
        i += paramInt2;
        break;
        label150:
        i += paramInt1;
        break label60;
      }
      paramInt1 += paramInt2;
    }
  }
  
  private Point getMatrixCenter()
  {
    try
    {
      localObject2 = new WhiteRectangleDetector(this.image).detect();
      localObject1 = localObject2[0];
      localResultPoint3 = localObject2[1];
      localResultPoint4 = localObject2[2];
      localObject2 = localObject2[3];
    }
    catch (NotFoundException localNotFoundException1)
    {
      for (;;)
      {
        Object localObject1;
        i = this.image.getWidth() / 2;
        j = this.image.getHeight() / 2;
        localResultPoint1 = getFirstDifferent(new Point(i + 7, j - 7, null), false, 1, -1).toResultPoint();
        localResultPoint3 = getFirstDifferent(new Point(i + 7, j + 7, null), false, 1, 1).toResultPoint();
        localResultPoint4 = getFirstDifferent(new Point(i - 7, j + 7, null), false, -1, 1).toResultPoint();
        localObject2 = getFirstDifferent(new Point(i - 7, j - 7, null), false, -1, -1).toResultPoint();
      }
    }
    i = MathUtils.round((((ResultPoint)localObject1).getX() + ((ResultPoint)localObject2).getX() + localResultPoint3.getX() + localResultPoint4.getX()) / 4.0F);
    j = MathUtils.round((((ResultPoint)localObject1).getY() + ((ResultPoint)localObject2).getY() + localResultPoint3.getY() + localResultPoint4.getY()) / 4.0F);
    try
    {
      localObject2 = new WhiteRectangleDetector(this.image, 15, i, j).detect();
      localObject1 = localObject2[0];
      localResultPoint3 = localObject2[1];
      localResultPoint4 = localObject2[2];
      localObject2 = localObject2[3];
    }
    catch (NotFoundException localNotFoundException2)
    {
      for (;;)
      {
        ResultPoint localResultPoint1;
        ResultPoint localResultPoint2 = getFirstDifferent(new Point(i + 7, j - 7, null), false, 1, -1).toResultPoint();
        localResultPoint3 = getFirstDifferent(new Point(i + 7, j + 7, null), false, 1, 1).toResultPoint();
        localResultPoint4 = getFirstDifferent(new Point(i - 7, j + 7, null), false, -1, 1).toResultPoint();
        localObject2 = getFirstDifferent(new Point(i - 7, j - 7, null), false, -1, -1).toResultPoint();
      }
    }
    return new Point(MathUtils.round((((ResultPoint)localObject1).getX() + ((ResultPoint)localObject2).getX() + localResultPoint3.getX() + localResultPoint4.getX()) / 4.0F), MathUtils.round((((ResultPoint)localObject1).getY() + ((ResultPoint)localObject2).getY() + localResultPoint3.getY() + localResultPoint4.getY()) / 4.0F), null);
  }
  
  private ResultPoint[] getMatrixCornerPoints(Point[] paramArrayOfPoint)
    throws NotFoundException
  {
    int j = this.nbLayers;
    float f;
    label64:
    int m;
    label92:
    int k;
    int n;
    int i1;
    label189:
    int i2;
    if (this.nbLayers > 4)
    {
      i = 1;
      f = (i + j * 2 + (this.nbLayers - 4) / 8) / (2.0F * this.nbCenterLayers);
      j = paramArrayOfPoint[0].x - paramArrayOfPoint[2].x;
      if (j <= 0) {
        break label346;
      }
      i = 1;
      m = j + i;
      j = paramArrayOfPoint[0].y - paramArrayOfPoint[2].y;
      if (j <= 0) {
        break label351;
      }
      i = 1;
      i = j + i;
      j = MathUtils.round(paramArrayOfPoint[2].x - m * f);
      k = MathUtils.round(paramArrayOfPoint[2].y - i * f);
      m = MathUtils.round(paramArrayOfPoint[0].x + m * f);
      n = MathUtils.round(paramArrayOfPoint[0].y + i * f);
      i1 = paramArrayOfPoint[1].x - paramArrayOfPoint[3].x;
      if (i1 <= 0) {
        break label356;
      }
      i = 1;
      i1 += i;
      i2 = paramArrayOfPoint[1].y - paramArrayOfPoint[3].y;
      if (i2 <= 0) {
        break label361;
      }
    }
    int i3;
    label346:
    label351:
    label356:
    label361:
    for (int i = 1;; i = -1)
    {
      i3 = i2 + i;
      i = MathUtils.round(paramArrayOfPoint[3].x - i1 * f);
      i2 = MathUtils.round(paramArrayOfPoint[3].y - i3 * f);
      i1 = MathUtils.round(paramArrayOfPoint[1].x + i1 * f);
      i3 = MathUtils.round(paramArrayOfPoint[1].y + i3 * f);
      if ((isValid(m, n)) && (isValid(i1, i3)) && (isValid(j, k)) && (isValid(i, i2))) {
        break label366;
      }
      throw NotFoundException.getNotFoundInstance();
      i = 0;
      break;
      i = -1;
      break label64;
      i = -1;
      break label92;
      i = -1;
      break label189;
    }
    label366:
    return new ResultPoint[] { new ResultPoint(m, n), new ResultPoint(i1, i3), new ResultPoint(j, k), new ResultPoint(i, i2) };
  }
  
  private void getParameters(boolean[] paramArrayOfBoolean)
  {
    int i;
    int j;
    int k;
    if (this.compact)
    {
      i = 2;
      j = 6;
      k = 0;
      label15:
      if (k < i) {
        break label61;
      }
      k = i;
    }
    for (;;)
    {
      if (k >= i + j)
      {
        this.nbLayers += 1;
        this.nbDataBlocks += 1;
        return;
        i = 5;
        j = 11;
        break;
        label61:
        this.nbLayers <<= 1;
        if (paramArrayOfBoolean[k] != 0) {
          this.nbLayers += 1;
        }
        k += 1;
        break label15;
      }
      this.nbDataBlocks <<= 1;
      if (paramArrayOfBoolean[k] != 0) {
        this.nbDataBlocks += 1;
      }
      k += 1;
    }
  }
  
  private boolean isValid(int paramInt1, int paramInt2)
  {
    return (paramInt1 >= 0) && (paramInt1 < this.image.getWidth()) && (paramInt2 > 0) && (paramInt2 < this.image.getHeight());
  }
  
  private boolean isWhiteOrBlackRectangle(Point paramPoint1, Point paramPoint2, Point paramPoint3, Point paramPoint4)
  {
    paramPoint1 = new Point(paramPoint1.x - 3, paramPoint1.y + 3, null);
    paramPoint2 = new Point(paramPoint2.x - 3, paramPoint2.y - 3, null);
    paramPoint3 = new Point(paramPoint3.x + 3, paramPoint3.y - 3, null);
    paramPoint4 = new Point(paramPoint4.x + 3, paramPoint4.y + 3, null);
    int i = getColor(paramPoint4, paramPoint1);
    if (i == 0) {}
    while ((getColor(paramPoint1, paramPoint2) != i) || (getColor(paramPoint2, paramPoint3) != i) || (getColor(paramPoint3, paramPoint4) != i)) {
      return false;
    }
    return true;
  }
  
  private BitMatrix sampleGrid(BitMatrix paramBitMatrix, ResultPoint paramResultPoint1, ResultPoint paramResultPoint2, ResultPoint paramResultPoint3, ResultPoint paramResultPoint4)
    throws NotFoundException
  {
    int i;
    if (this.compact) {
      i = this.nbLayers * 4 + 11;
    }
    for (;;)
    {
      return GridSampler.getInstance().sampleGrid(paramBitMatrix, i, i, 0.5F, 0.5F, i - 0.5F, 0.5F, i - 0.5F, i - 0.5F, 0.5F, i - 0.5F, paramResultPoint1.getX(), paramResultPoint1.getY(), paramResultPoint4.getX(), paramResultPoint4.getY(), paramResultPoint3.getX(), paramResultPoint3.getY(), paramResultPoint2.getX(), paramResultPoint2.getY());
      if (this.nbLayers <= 4) {
        i = this.nbLayers * 4 + 15;
      } else {
        i = this.nbLayers * 4 + ((this.nbLayers - 4) / 8 + 1) * 2 + 15;
      }
    }
  }
  
  private boolean[] sampleLine(Point paramPoint1, Point paramPoint2, int paramInt)
  {
    boolean[] arrayOfBoolean = new boolean[paramInt];
    float f1 = distance(paramPoint1, paramPoint2);
    float f2 = f1 / (paramInt - 1);
    float f3 = (paramPoint2.x - paramPoint1.x) * f2 / f1;
    float f4 = (paramPoint2.y - paramPoint1.y) * f2 / f1;
    f2 = paramPoint1.x;
    f1 = paramPoint1.y;
    int i = 0;
    for (;;)
    {
      if (i >= paramInt) {
        return arrayOfBoolean;
      }
      arrayOfBoolean[i] = this.image.get(MathUtils.round(f2), MathUtils.round(f1));
      f2 += f3;
      f1 += f4;
      i += 1;
    }
  }
  
  public AztecDetectorResult detect()
    throws NotFoundException
  {
    Object localObject = getBullEyeCornerPoints(getMatrixCenter());
    extractParameters((Point[])localObject);
    localObject = getMatrixCornerPoints((Point[])localObject);
    return new AztecDetectorResult(sampleGrid(this.image, localObject[(this.shift % 4)], localObject[((this.shift + 3) % 4)], localObject[((this.shift + 2) % 4)], localObject[((this.shift + 1) % 4)]), (ResultPoint[])localObject, this.compact, this.nbDataBlocks, this.nbLayers);
  }
  
  private static final class Point
  {
    public final int x;
    public final int y;
    
    private Point(int paramInt1, int paramInt2)
    {
      this.x = paramInt1;
      this.y = paramInt2;
    }
    
    public ResultPoint toResultPoint()
    {
      return new ResultPoint(this.x, this.y);
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\aztec\detector\Detector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */