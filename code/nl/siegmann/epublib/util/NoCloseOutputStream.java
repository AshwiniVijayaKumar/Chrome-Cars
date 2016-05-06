package nl.siegmann.epublib.util;

import java.io.IOException;
import java.io.OutputStream;

public class NoCloseOutputStream
  extends OutputStream
{
  private OutputStream outputStream;
  
  public NoCloseOutputStream(OutputStream paramOutputStream)
  {
    this.outputStream = paramOutputStream;
  }
  
  public void close() {}
  
  public void write(int paramInt)
    throws IOException
  {
    this.outputStream.write(paramInt);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\nl\siegmann\epublib\util\NoCloseOutputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */