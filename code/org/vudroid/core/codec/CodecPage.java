package org.vudroid.core.codec;

import android.graphics.Bitmap;
import android.graphics.RectF;

public abstract interface CodecPage
{
  public abstract int getHeight();
  
  public abstract int getWidth();
  
  public abstract boolean isDecoding();
  
  public abstract void recycle();
  
  public abstract Bitmap renderBitmap(int paramInt1, int paramInt2, RectF paramRectF);
  
  public abstract void waitForDecode();
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\vudroid\core\codec\CodecPage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */