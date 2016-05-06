package org.apache.james.mime4j.io;

import java.io.IOException;
import java.io.InputStream;
import org.apache.james.mime4j.util.ByteArrayBuffer;

public class LineReaderInputStreamAdaptor
  extends LineReaderInputStream
{
  private final LineReaderInputStream bis;
  private boolean eof = false;
  private final int maxLineLen;
  private boolean used = false;
  
  public LineReaderInputStreamAdaptor(InputStream paramInputStream)
  {
    this(paramInputStream, -1);
  }
  
  public LineReaderInputStreamAdaptor(InputStream paramInputStream, int paramInt)
  {
    super(paramInputStream);
    if ((paramInputStream instanceof LineReaderInputStream)) {}
    for (this.bis = ((LineReaderInputStream)paramInputStream);; this.bis = null)
    {
      this.maxLineLen = paramInt;
      return;
    }
  }
  
  private int doReadLine(ByteArrayBuffer paramByteArrayBuffer)
    throws IOException
  {
    int i = 0;
    int k;
    int j;
    do
    {
      k = this.in.read();
      j = i;
      if (k == -1) {
        break;
      }
      paramByteArrayBuffer.append(k);
      j = i + 1;
      if ((this.maxLineLen > 0) && (paramByteArrayBuffer.length() >= this.maxLineLen)) {
        throw new MaxLineLimitException("Maximum line length limit exceeded");
      }
      i = j;
    } while (k != 10);
    i = j;
    if (j == 0)
    {
      i = j;
      if (k == -1) {
        i = -1;
      }
    }
    return i;
  }
  
  public boolean eof()
  {
    return this.eof;
  }
  
  public boolean isUsed()
  {
    return this.used;
  }
  
  public int read()
    throws IOException
  {
    int i = this.in.read();
    if (i == -1) {}
    for (boolean bool = true;; bool = false)
    {
      this.eof = bool;
      this.used = true;
      return i;
    }
  }
  
  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    paramInt1 = this.in.read(paramArrayOfByte, paramInt1, paramInt2);
    if (paramInt1 == -1) {}
    for (boolean bool = true;; bool = false)
    {
      this.eof = bool;
      this.used = true;
      return paramInt1;
    }
  }
  
  public int readLine(ByteArrayBuffer paramByteArrayBuffer)
    throws IOException
  {
    int i;
    if (this.bis != null)
    {
      i = this.bis.readLine(paramByteArrayBuffer);
      if (i != -1) {
        break label44;
      }
    }
    label44:
    for (boolean bool = true;; bool = false)
    {
      this.eof = bool;
      this.used = true;
      return i;
      i = doReadLine(paramByteArrayBuffer);
      break;
    }
  }
  
  public String toString()
  {
    return "[LineReaderInputStreamAdaptor: " + this.bis + "]";
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\io\LineReaderInputStreamAdaptor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */