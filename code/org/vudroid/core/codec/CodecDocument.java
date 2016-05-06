package org.vudroid.core.codec;

public abstract interface CodecDocument
{
  public abstract CodecPage getPage(int paramInt);
  
  public abstract int getPageCount();
  
  public abstract void recycle();
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\vudroid\core\codec\CodecDocument.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */