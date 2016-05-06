package com.google.zxing.common.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;

public final class WhiteRectangleDetector
{
  private static final int CORR = 1;
  private static final int INIT_SIZE = 30;
  private final int downInit;
  private final int height;
  private final BitMatrix image;
  private final int leftInit;
  private final int rightInit;
  private final int upInit;
  private final int width;
  
  public WhiteRectangleDetector(BitMatrix paramBitMatrix)
    throws NotFoundException
  {
    this.image = paramBitMatrix;
    this.height = paramBitMatrix.getHeight();
    this.width = paramBitMatrix.getWidth();
    this.leftInit = (this.width - 30 >> 1);
    this.rightInit = (this.width + 30 >> 1);
    this.upInit = (this.height - 30 >> 1);
    this.downInit = (this.height + 30 >> 1);
    if ((this.upInit < 0) || (this.leftInit < 0) || (this.downInit >= this.height) || (this.rightInit >= this.width)) {
      throw NotFoundException.getNotFoundInstance();
    }
  }
  
  public WhiteRectangleDetector(BitMatrix paramBitMatrix, int paramInt1, int paramInt2, int paramInt3)
    throws NotFoundException
  {
    this.image = paramBitMatrix;
    this.height = paramBitMatrix.getHeight();
    this.width = paramBitMatrix.getWidth();
    paramInt1 >>= 1;
    this.leftInit = (paramInt2 - paramInt1);
    this.rightInit = (paramInt2 + paramInt1);
    this.upInit = (paramInt3 - paramInt1);
    this.downInit = (paramInt3 + paramInt1);
    if ((this.upInit < 0) || (this.leftInit < 0) || (this.downInit >= this.height) || (this.rightInit >= this.width)) {
      throw NotFoundException.getNotFoundInstance();
    }
  }
  
  private ResultPoint[] centerEdges(ResultPoint paramResultPoint1, ResultPoint paramResultPoint2, ResultPoint paramResultPoint3, ResultPoint paramResultPoint4)
  {
    float f1 = paramResultPoint1.getX();
    float f2 = paramResultPoint1.getY();
    float f3 = paramResultPoint2.getX();
    float f4 = paramResultPoint2.getY();
    float f5 = paramResultPoint3.getX();
    float f6 = paramResultPoint3.getY();
    float f7 = paramResultPoint4.getX();
    float f8 = paramResultPoint4.getY();
    if (f1 < this.width / 2) {
      return new ResultPoint[] { new ResultPoint(f7 - 1.0F, 1.0F + f8), new ResultPoint(1.0F + f3, 1.0F + f4), new ResultPoint(f5 - 1.0F, f6 - 1.0F), new ResultPoint(1.0F + f1, f2 - 1.0F) };
    }
    return new ResultPoint[] { new ResultPoint(1.0F + f7, 1.0F + f8), new ResultPoint(1.0F + f3, f4 - 1.0F), new ResultPoint(f5 - 1.0F, 1.0F + f6), new ResultPoint(f1 - 1.0F, f2 - 1.0F) };
  }
  
  private boolean containsBlackPoint(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
  {
    boolean bool = true;
    if (paramBoolean) {
      if (paramInt1 <= paramInt2) {}
    }
    label68:
    for (;;)
    {
      paramBoolean = false;
      do
      {
        return paramBoolean;
        paramBoolean = bool;
      } while (this.image.get(paramInt1, paramInt3));
      paramInt1 += 1;
      break;
      for (;;)
      {
        if (paramInt1 > paramInt2) {
          break label68;
        }
        paramBoolean = bool;
        if (this.image.get(paramInt3, paramInt1)) {
          break;
        }
        paramInt1 += 1;
      }
    }
  }
  
  private ResultPoint getBlackPointOnSegment(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    int j = MathUtils.round(MathUtils.distance(paramFloat1, paramFloat2, paramFloat3, paramFloat4));
    paramFloat3 = (paramFloat3 - paramFloat1) / j;
    paramFloat4 = (paramFloat4 - paramFloat2) / j;
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        return null;
      }
      int k = MathUtils.round(i * paramFloat3 + paramFloat1);
      int m = MathUtils.round(i * paramFloat4 + paramFloat2);
      if (this.image.get(k, m)) {
        return new ResultPoint(k, m);
      }
      i += 1;
    }
  }
  
  public ResultPoint[] detect()
    throws NotFoundException
  {
    int k = this.leftInit;
    int i2 = this.rightInit;
    int j = this.upInit;
    int m = this.downInit;
    int i7 = 0;
    int i6 = 1;
    int i5 = 0;
    int i;
    int n;
    Object localObject1;
    if (i6 == 0)
    {
      i = i7;
      if ((i == 0) && (i5 != 0))
      {
        n = i2 - k;
        localObject1 = null;
        i = 1;
      }
    }
    else
    {
      Object localObject2;
      for (;;)
      {
        if (i >= n) {
          localObject2 = localObject1;
        }
        label154:
        label240:
        label317:
        label399:
        do
        {
          if (localObject2 != null) {
            break label480;
          }
          throw NotFoundException.getNotFoundInstance();
          int i1 = 0;
          int i8 = 1;
          i = i2;
          boolean bool;
          for (;;)
          {
            if ((i8 == 0) || (i >= this.width))
            {
              if (i < this.width) {
                break label154;
              }
              n = 1;
              i2 = i;
              i = n;
              break;
            }
            bool = containsBlackPoint(j, m, i, false);
            i8 = bool;
            if (bool)
            {
              i += 1;
              i1 = 1;
              i8 = bool;
            }
          }
          i8 = 1;
          n = m;
          m = i1;
          for (;;)
          {
            if ((i8 == 0) || (n >= this.height))
            {
              if (n < this.height) {
                break label240;
              }
              i1 = 1;
              m = n;
              i2 = i;
              i = i1;
              break;
            }
            bool = containsBlackPoint(k, i, n, true);
            i8 = bool;
            if (bool)
            {
              n += 1;
              m = 1;
              i8 = bool;
            }
          }
          i8 = 1;
          i1 = k;
          for (;;)
          {
            if ((i8 == 0) || (i1 < 0))
            {
              if (i1 >= 0) {
                break label317;
              }
              i3 = 1;
              m = n;
              k = i1;
              i2 = i;
              i = i3;
              break;
            }
            bool = containsBlackPoint(j, n, i1, false);
            i8 = bool;
            if (bool)
            {
              i1 -= 1;
              m = 1;
              i8 = bool;
            }
          }
          i8 = 1;
          int i3 = j;
          int i4 = m;
          for (;;)
          {
            if ((i8 == 0) || (i3 < 0))
            {
              if (i3 >= 0) {
                break label399;
              }
              j = 1;
              m = n;
              k = i1;
              i2 = i;
              i = j;
              j = i3;
              break;
            }
            bool = containsBlackPoint(i1, i, i3, true);
            i8 = bool;
            if (bool)
            {
              i3 -= 1;
              i4 = 1;
              i8 = bool;
            }
          }
          i6 = i4;
          m = n;
          k = i1;
          i2 = i;
          j = i3;
          if (i4 == 0) {
            break;
          }
          i5 = 1;
          i6 = i4;
          m = n;
          k = i1;
          i2 = i;
          j = i3;
          break;
          localObject1 = getBlackPointOnSegment(k, m - i, k + i, m);
          localObject2 = localObject1;
        } while (localObject1 != null);
        i += 1;
      }
      label480:
      localObject1 = null;
      i = 1;
      Object localObject3;
      for (;;)
      {
        if (i >= n) {
          localObject3 = localObject1;
        }
        do
        {
          if (localObject3 != null) {
            break;
          }
          throw NotFoundException.getNotFoundInstance();
          localObject1 = getBlackPointOnSegment(k, j + i, k + i, j);
          localObject3 = localObject1;
        } while (localObject1 != null);
        i += 1;
      }
      localObject1 = null;
      i = 1;
      Object localObject4;
      for (;;)
      {
        if (i >= n) {
          localObject4 = localObject1;
        }
        do
        {
          if (localObject4 != null) {
            break;
          }
          throw NotFoundException.getNotFoundInstance();
          localObject1 = getBlackPointOnSegment(i2, j + i, i2 - i, j);
          localObject4 = localObject1;
        } while (localObject1 != null);
        i += 1;
      }
      localObject1 = null;
      i = 1;
      for (;;)
      {
        if (i >= n) {}
        ResultPoint localResultPoint;
        do
        {
          if (localObject1 != null) {
            break;
          }
          throw NotFoundException.getNotFoundInstance();
          localResultPoint = getBlackPointOnSegment(i2, m - i, i2 - i, m);
          localObject1 = localResultPoint;
        } while (localResultPoint != null);
        i += 1;
        localObject1 = localResultPoint;
      }
      return centerEdges((ResultPoint)localObject1, (ResultPoint)localObject2, (ResultPoint)localObject4, (ResultPoint)localObject3);
    }
    throw NotFoundException.getNotFoundInstance();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\common\detector\WhiteRectangleDetector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */