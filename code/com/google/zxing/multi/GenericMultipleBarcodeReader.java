package com.google.zxing.multi;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class GenericMultipleBarcodeReader
  implements MultipleBarcodeReader
{
  private static final int MIN_DIMENSION_TO_RECUR = 100;
  private final Reader delegate;
  
  public GenericMultipleBarcodeReader(Reader paramReader)
  {
    this.delegate = paramReader;
  }
  
  private void doDecodeMultiple(BinaryBitmap paramBinaryBitmap, Map<DecodeHintType, ?> paramMap, List<Result> paramList, int paramInt1, int paramInt2)
  {
    Object localObject;
    Iterator localIterator;
    do
    {
      for (;;)
      {
        try
        {
          localObject = this.delegate.decode(paramBinaryBitmap, paramMap);
          i = 0;
          localIterator = paramList.iterator();
          if (!localIterator.hasNext())
          {
            if (i == 0) {
              break;
            }
            return;
          }
        }
        catch (ReaderException paramBinaryBitmap)
        {
          return;
        }
        if (((Result)localIterator.next()).getText().equals(((Result)localObject).getText())) {
          i = 1;
        }
      }
      paramList.add(translateResultPoints((Result)localObject, paramInt1, paramInt2));
      localObject = ((Result)localObject).getResultPoints();
    } while ((localObject == null) || (localObject.length == 0));
    int j = paramBinaryBitmap.getWidth();
    int k = paramBinaryBitmap.getHeight();
    float f8 = j;
    float f5 = k;
    float f4 = 0.0F;
    float f1 = 0.0F;
    int m = localObject.length;
    int i = 0;
    for (;;)
    {
      if (i >= m)
      {
        if (f8 > 100.0F) {
          doDecodeMultiple(paramBinaryBitmap.crop(0, 0, (int)f8, k), paramMap, paramList, paramInt1, paramInt2);
        }
        if (f5 > 100.0F) {
          doDecodeMultiple(paramBinaryBitmap.crop(0, 0, j, (int)f5), paramMap, paramList, paramInt1, paramInt2);
        }
        if (f4 < j - 100) {
          doDecodeMultiple(paramBinaryBitmap.crop((int)f4, 0, j - (int)f4, k), paramMap, paramList, paramInt1 + (int)f4, paramInt2);
        }
        if (f1 >= k - 100) {
          break;
        }
        doDecodeMultiple(paramBinaryBitmap.crop(0, (int)f1, j, k - (int)f1), paramMap, paramList, paramInt1, paramInt2 + (int)f1);
        return;
      }
      localIterator = localObject[i];
      float f7 = localIterator.getX();
      float f2 = localIterator.getY();
      float f3 = f8;
      if (f7 < f8) {
        f3 = f7;
      }
      float f6 = f5;
      if (f2 < f5) {
        f6 = f2;
      }
      f5 = f4;
      if (f7 > f4) {
        f5 = f7;
      }
      f7 = f1;
      if (f2 > f1) {
        f7 = f2;
      }
      i += 1;
      f4 = f5;
      f1 = f7;
      f8 = f3;
      f5 = f6;
    }
  }
  
  private static Result translateResultPoints(Result paramResult, int paramInt1, int paramInt2)
  {
    ResultPoint[] arrayOfResultPoint1 = paramResult.getResultPoints();
    if (arrayOfResultPoint1 == null) {
      return paramResult;
    }
    ResultPoint[] arrayOfResultPoint2 = new ResultPoint[arrayOfResultPoint1.length];
    int i = 0;
    for (;;)
    {
      if (i >= arrayOfResultPoint1.length) {
        return new Result(paramResult.getText(), paramResult.getRawBytes(), arrayOfResultPoint2, paramResult.getBarcodeFormat());
      }
      ResultPoint localResultPoint = arrayOfResultPoint1[i];
      arrayOfResultPoint2[i] = new ResultPoint(localResultPoint.getX() + paramInt1, localResultPoint.getY() + paramInt2);
      i += 1;
    }
  }
  
  public Result[] decodeMultiple(BinaryBitmap paramBinaryBitmap)
    throws NotFoundException
  {
    return decodeMultiple(paramBinaryBitmap, null);
  }
  
  public Result[] decodeMultiple(BinaryBitmap paramBinaryBitmap, Map<DecodeHintType, ?> paramMap)
    throws NotFoundException
  {
    ArrayList localArrayList = new ArrayList();
    doDecodeMultiple(paramBinaryBitmap, paramMap, localArrayList, 0, 0);
    if (localArrayList.isEmpty()) {
      throw NotFoundException.getNotFoundInstance();
    }
    return (Result[])localArrayList.toArray(new Result[localArrayList.size()]);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\multi\GenericMultipleBarcodeReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */