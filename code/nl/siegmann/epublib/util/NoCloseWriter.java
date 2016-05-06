package nl.siegmann.epublib.util;

import java.io.IOException;
import java.io.Writer;

public class NoCloseWriter
  extends Writer
{
  private Writer writer;
  
  public NoCloseWriter(Writer paramWriter)
  {
    this.writer = paramWriter;
  }
  
  public void close()
    throws IOException
  {}
  
  public void flush()
    throws IOException
  {
    this.writer.flush();
  }
  
  public void write(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws IOException
  {
    this.writer.write(paramArrayOfChar, paramInt1, paramInt2);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\nl\siegmann\epublib\util\NoCloseWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */