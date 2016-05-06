package com.nostra13.universalimageloader.core;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

final class ImageDecoder
{
  private DecodingType decodingType;
  private URL imageUrl;
  private ImageSize targetSize;
  
  ImageDecoder(URL paramURL, ImageSize paramImageSize, DecodingType paramDecodingType)
  {
    this.imageUrl = paramURL;
    this.targetSize = paramImageSize;
    this.decodingType = paramDecodingType;
  }
  
  private int computeImageScale(InputStream paramInputStream)
  {
    int n = this.targetSize.width;
    int i1 = this.targetSize.height;
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    localOptions.inJustDecodeBounds = true;
    BitmapFactory.decodeStream(paramInputStream, null, localOptions);
    int m = 1;
    int i = 1;
    int k;
    int j;
    switch (this.decodingType)
    {
    default: 
      k = localOptions.outWidth;
      j = localOptions.outHeight;
      m = i;
      if (k / 2 >= n)
      {
        if (j / 2 >= i1) {
          break label115;
        }
        m = i;
      }
      break;
    }
    label115:
    do
    {
      return m;
      k /= 2;
      j /= 2;
      i *= 2;
      break;
      i = Math.min((int)Math.floor(localOptions.outWidth / n), (int)Math.floor(localOptions.outHeight / i1));
    } while (i <= 1);
    return i;
  }
  
  private BitmapFactory.Options getBitmapOptionsForImageDecoding()
    throws IOException
  {
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    InputStream localInputStream = this.imageUrl.openStream();
    try
    {
      localOptions.inSampleSize = computeImageScale(localInputStream);
      return localOptions;
    }
    finally
    {
      localInputStream.close();
    }
  }
  
  public Bitmap decodeFile()
    throws IOException
  {
    Object localObject1 = getBitmapOptionsForImageDecoding();
    InputStream localInputStream = this.imageUrl.openStream();
    try
    {
      localObject1 = BitmapFactory.decodeStream(localInputStream, null, (BitmapFactory.Options)localObject1);
      return (Bitmap)localObject1;
    }
    finally
    {
      localInputStream.close();
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\nostra13\universalimageloader\core\ImageDecoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */