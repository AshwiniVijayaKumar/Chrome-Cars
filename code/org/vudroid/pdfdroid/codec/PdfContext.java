package org.vudroid.pdfdroid.codec;

import android.content.ContentResolver;
import org.vudroid.core.VuDroidLibraryLoader;
import org.vudroid.core.codec.CodecContext;
import org.vudroid.core.codec.CodecDocument;

public class PdfContext
  implements CodecContext
{
  static {}
  
  public CodecDocument openDocument(String paramString)
  {
    return PdfDocument.openDocument(paramString, "");
  }
  
  public void recycle() {}
  
  public void setContentResolver(ContentResolver paramContentResolver) {}
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\vudroid\pdfdroid\codec\PdfContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */