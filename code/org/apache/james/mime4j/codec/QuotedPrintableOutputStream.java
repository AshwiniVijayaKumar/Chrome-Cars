package org.apache.james.mime4j.codec;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class QuotedPrintableOutputStream
  extends FilterOutputStream
{
  private boolean closed = false;
  private QuotedPrintableEncoder encoder;
  
  public QuotedPrintableOutputStream(OutputStream paramOutputStream, boolean paramBoolean)
  {
    super(paramOutputStream);
    this.encoder = new QuotedPrintableEncoder(1024, paramBoolean);
    this.encoder.initEncoding(paramOutputStream);
  }
  
  public void close()
    throws IOException
  {
    if (this.closed) {
      return;
    }
    try
    {
      this.encoder.completeEncoding();
      return;
    }
    finally
    {
      this.closed = true;
    }
  }
  
  public void flush()
    throws IOException
  {
    this.encoder.flushOutput();
  }
  
  public void write(int paramInt)
    throws IOException
  {
    write(new byte[] { (byte)paramInt }, 0, 1);
  }
  
  public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (this.closed) {
      throw new IOException("QuotedPrintableOutputStream has been closed");
    }
    this.encoder.encodeChunk(paramArrayOfByte, paramInt1, paramInt2);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\codec\QuotedPrintableOutputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */