package org.vudroid.pdfdroid.codec;

import org.vudroid.core.codec.CodecDocument;
import org.vudroid.core.codec.CodecPage;

public class PdfDocument
  implements CodecDocument
{
  private static final int FITZMEMORY = 524288;
  private long docHandle;
  
  private PdfDocument(long paramLong)
  {
    this.docHandle = paramLong;
  }
  
  private static native void free(long paramLong);
  
  private static native int getPageCount(long paramLong);
  
  private static native long open(int paramInt, String paramString1, String paramString2);
  
  static PdfDocument openDocument(String paramString1, String paramString2)
  {
    return new PdfDocument(open(524288, paramString1, paramString2));
  }
  
  protected void finalize()
    throws Throwable
  {
    recycle();
    super.finalize();
  }
  
  public CodecPage getPage(int paramInt)
  {
    return PdfPage.createPage(this.docHandle, paramInt + 1);
  }
  
  public int getPageCount()
  {
    return getPageCount(this.docHandle);
  }
  
  public void recycle()
  {
    try
    {
      if (this.docHandle != 0L)
      {
        free(this.docHandle);
        this.docHandle = 0L;
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\vudroid\pdfdroid\codec\PdfDocument.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */