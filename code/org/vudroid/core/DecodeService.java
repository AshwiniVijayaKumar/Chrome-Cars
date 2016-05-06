package org.vudroid.core;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.net.Uri;
import android.view.View;
import org.vudroid.core.codec.CodecPage;

public abstract interface DecodeService
{
  public abstract void decodePage(Object paramObject, int paramInt, DecodeCallback paramDecodeCallback, float paramFloat, RectF paramRectF);
  
  public abstract int getEffectivePagesHeight();
  
  public abstract int getEffectivePagesWidth();
  
  public abstract CodecPage getPage(int paramInt);
  
  public abstract int getPageCount();
  
  public abstract int getPageHeight(int paramInt);
  
  public abstract int getPageWidth(int paramInt);
  
  public abstract void open(Uri paramUri);
  
  public abstract void recycle();
  
  public abstract void setContainerView(View paramView);
  
  public abstract void setContentResolver(ContentResolver paramContentResolver);
  
  public abstract void stopDecoding(Object paramObject);
  
  public static abstract interface DecodeCallback
  {
    public abstract void decodeComplete(Bitmap paramBitmap);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\vudroid\core\DecodeService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */