package org.apache.james.mime4j.io;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class PositionInputStream
  extends FilterInputStream
{
  private long markedPosition = 0L;
  protected long position = 0L;
  
  public PositionInputStream(InputStream paramInputStream)
  {
    super(paramInputStream);
  }
  
  public int available()
    throws IOException
  {
    return this.in.available();
  }
  
  public void close()
    throws IOException
  {
    this.in.close();
  }
  
  public long getPosition()
  {
    return this.position;
  }
  
  public void mark(int paramInt)
  {
    this.in.mark(paramInt);
    this.markedPosition = this.position;
  }
  
  public boolean markSupported()
  {
    return this.in.markSupported();
  }
  
  public int read()
    throws IOException
  {
    int i = this.in.read();
    if (i != -1) {
      this.position += 1L;
    }
    return i;
  }
  
  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    paramInt1 = this.in.read(paramArrayOfByte, paramInt1, paramInt2);
    if (paramInt1 > 0) {
      this.position += paramInt1;
    }
    return paramInt1;
  }
  
  public void reset()
    throws IOException
  {
    this.in.reset();
    this.position = this.markedPosition;
  }
  
  public long skip(long paramLong)
    throws IOException
  {
    paramLong = this.in.skip(paramLong);
    if (paramLong > 0L) {
      this.position += paramLong;
    }
    return paramLong;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\io\PositionInputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */