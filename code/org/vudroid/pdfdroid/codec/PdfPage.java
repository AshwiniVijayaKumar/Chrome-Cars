package org.vudroid.pdfdroid.codec;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import java.nio.ByteBuffer;
import org.vudroid.core.codec.CodecPage;

public class PdfPage
  implements CodecPage
{
  private long docHandle;
  private long pageHandle;
  
  private PdfPage(long paramLong1, long paramLong2)
  {
    this.pageHandle = paramLong1;
    this.docHandle = paramLong2;
  }
  
  static PdfPage createPage(long paramLong, int paramInt)
  {
    return new PdfPage(open(paramLong, paramInt), paramLong);
  }
  
  private static native void free(long paramLong);
  
  private RectF getMediaBox()
  {
    float[] arrayOfFloat = new float[4];
    getMediaBox(this.pageHandle, arrayOfFloat);
    return new RectF(arrayOfFloat[0], arrayOfFloat[1], arrayOfFloat[2], arrayOfFloat[3]);
  }
  
  private static native void getMediaBox(long paramLong, float[] paramArrayOfFloat);
  
  private native void nativeCreateView(long paramLong1, long paramLong2, int[] paramArrayOfInt1, float[] paramArrayOfFloat, int[] paramArrayOfInt2);
  
  private static native long open(long paramLong, int paramInt);
  
  private static native void render(long paramLong1, long paramLong2, int[] paramArrayOfInt, float[] paramArrayOfFloat, ByteBuffer paramByteBuffer1, ByteBuffer paramByteBuffer2);
  
  protected void finalize()
    throws Throwable
  {
    recycle();
    super.finalize();
  }
  
  public int getHeight()
  {
    return (int)getMediaBox().height();
  }
  
  public int getWidth()
  {
    return (int)getMediaBox().width();
  }
  
  public boolean isDecoding()
  {
    return false;
  }
  
  public void recycle()
  {
    try
    {
      if (this.pageHandle != 0L)
      {
        free(this.pageHandle);
        this.pageHandle = 0L;
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public Bitmap render(Rect paramRect, Matrix paramMatrix)
  {
    int i = paramRect.left;
    int j = paramRect.top;
    int k = paramRect.right;
    int m = paramRect.bottom;
    float[] arrayOfFloat = new float[9];
    paramMatrix.getValues(arrayOfFloat);
    float f1 = arrayOfFloat[0];
    float f2 = arrayOfFloat[3];
    float f3 = arrayOfFloat[1];
    float f4 = arrayOfFloat[4];
    float f5 = arrayOfFloat[2];
    float f6 = arrayOfFloat[5];
    int n = paramRect.width();
    int i1 = paramRect.height();
    paramRect = new int[n * i1];
    nativeCreateView(this.docHandle, this.pageHandle, new int[] { i, j, k, m }, new float[] { f1, f2, f3, f4, f5, f6 }, paramRect);
    return Bitmap.createBitmap(paramRect, n, i1, Bitmap.Config.RGB_565);
  }
  
  public Bitmap renderBitmap(int paramInt1, int paramInt2, RectF paramRectF)
  {
    Matrix localMatrix = new Matrix();
    localMatrix.postScale(paramInt1 / getMediaBox().width(), -paramInt2 / getMediaBox().height());
    localMatrix.postTranslate(0.0F, paramInt2);
    localMatrix.postTranslate(-paramRectF.left * paramInt1, -paramRectF.top * paramInt2);
    localMatrix.postScale(1.0F / paramRectF.width(), 1.0F / paramRectF.height());
    return render(new Rect(0, 0, paramInt1, paramInt2), localMatrix);
  }
  
  public void waitForDecode() {}
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\vudroid\pdfdroid\codec\PdfPage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */