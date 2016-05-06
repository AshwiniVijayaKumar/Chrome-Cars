package org.vudroid.core.codec;

import android.content.ContentResolver;

public abstract interface CodecContext
{
  public abstract CodecDocument openDocument(String paramString);
  
  public abstract void recycle();
  
  public abstract void setContentResolver(ContentResolver paramContentResolver);
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\vudroid\core\codec\CodecContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */