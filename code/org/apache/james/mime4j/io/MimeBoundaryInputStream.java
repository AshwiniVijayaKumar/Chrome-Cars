package org.apache.james.mime4j.io;

import java.io.IOException;
import org.apache.james.mime4j.util.ByteArrayBuffer;

public class MimeBoundaryInputStream
  extends LineReaderInputStream
{
  private boolean atBoundary;
  private final byte[] boundary;
  private int boundaryLen;
  private BufferedLineReaderInputStream buffer;
  private boolean completed;
  private boolean eof;
  private boolean lastPart;
  private int limit;
  
  public MimeBoundaryInputStream(BufferedLineReaderInputStream paramBufferedLineReaderInputStream, String paramString)
    throws IOException
  {
    super(paramBufferedLineReaderInputStream);
    if (paramBufferedLineReaderInputStream.capacity() <= paramString.length()) {
      throw new IllegalArgumentException("Boundary is too long");
    }
    this.buffer = paramBufferedLineReaderInputStream;
    this.eof = false;
    this.limit = -1;
    this.atBoundary = false;
    this.boundaryLen = 0;
    this.lastPart = false;
    this.completed = false;
    this.boundary = new byte[paramString.length() + 2];
    this.boundary[0] = 45;
    this.boundary[1] = 45;
    int j = 0;
    while (j < paramString.length())
    {
      int i = (byte)paramString.charAt(j);
      if ((i == 13) || (i == 10)) {
        throw new IllegalArgumentException("Boundary may not contain CR or LF");
      }
      this.boundary[(j + 2)] = i;
      j += 1;
    }
    fillBuffer();
  }
  
  private void calculateBoundaryLen()
    throws IOException
  {
    this.boundaryLen = this.boundary.length;
    int i = this.limit - this.buffer.pos();
    if ((i > 0) && (this.buffer.charAt(this.limit - 1) == 10))
    {
      this.boundaryLen += 1;
      this.limit -= 1;
    }
    if ((i > 1) && (this.buffer.charAt(this.limit - 1) == 13))
    {
      this.boundaryLen += 1;
      this.limit -= 1;
    }
  }
  
  private boolean endOfStream()
  {
    return (this.eof) || (this.atBoundary);
  }
  
  private int fillBuffer()
    throws IOException
  {
    if (this.eof) {
      return -1;
    }
    int i;
    if (!hasData())
    {
      i = this.buffer.fillBuffer();
      if (i != -1) {
        break label105;
      }
    }
    int j;
    label105:
    for (boolean bool = true;; bool = false)
    {
      this.eof = bool;
      for (j = this.buffer.indexOf(this.boundary); (j > 0) && (this.buffer.charAt(j - 1) != 10); j = this.buffer.indexOf(this.boundary, j, this.buffer.limit() - j)) {
        j += this.boundary.length;
      }
      i = 0;
      break;
    }
    if (j != -1)
    {
      this.limit = j;
      this.atBoundary = true;
      calculateBoundaryLen();
      return i;
    }
    if (this.eof)
    {
      this.limit = this.buffer.limit();
      return i;
    }
    this.limit = (this.buffer.limit() - (this.boundary.length + 1));
    return i;
  }
  
  private boolean hasData()
  {
    return (this.limit > this.buffer.pos()) && (this.limit <= this.buffer.limit());
  }
  
  private void skipBoundary()
    throws IOException
  {
    int i;
    if (!this.completed)
    {
      this.completed = true;
      this.buffer.skip(this.boundaryLen);
      i = 1;
    }
    for (;;)
    {
      if (this.buffer.length() > 1)
      {
        j = this.buffer.charAt(this.buffer.pos());
        k = this.buffer.charAt(this.buffer.pos() + 1);
        if ((i != 0) && (j == 45) && (k == 45))
        {
          this.lastPart = true;
          this.buffer.skip(2);
          i = 0;
          continue;
        }
        if ((j == 13) && (k == 10)) {
          this.buffer.skip(2);
        }
      }
      while (this.eof)
      {
        int j;
        int k;
        return;
        if (j == 10)
        {
          this.buffer.skip(1);
          return;
        }
        this.buffer.skip(1);
        break;
      }
      fillBuffer();
    }
  }
  
  public void close()
    throws IOException
  {}
  
  public boolean eof()
  {
    return (this.eof) && (!this.buffer.hasBufferedData());
  }
  
  public boolean isLastPart()
  {
    return this.lastPart;
  }
  
  public boolean markSupported()
  {
    return false;
  }
  
  public int read()
    throws IOException
  {
    if (this.completed) {
      return -1;
    }
    if ((endOfStream()) && (!hasData()))
    {
      skipBoundary();
      return -1;
    }
    do
    {
      fillBuffer();
      if (hasData()) {
        return this.buffer.read();
      }
    } while (!endOfStream());
    skipBoundary();
    return -1;
  }
  
  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (this.completed) {
      return -1;
    }
    if ((endOfStream()) && (!hasData()))
    {
      skipBoundary();
      return -1;
    }
    fillBuffer();
    if (!hasData()) {
      return read(paramArrayOfByte, paramInt1, paramInt2);
    }
    paramInt2 = Math.min(paramInt2, this.limit - this.buffer.pos());
    return this.buffer.read(paramArrayOfByte, paramInt1, paramInt2);
  }
  
  public int readLine(ByteArrayBuffer paramByteArrayBuffer)
    throws IOException
  {
    if (paramByteArrayBuffer == null) {
      throw new IllegalArgumentException("Destination buffer may not be null");
    }
    if (this.completed) {
      j = -1;
    }
    int i;
    int k;
    int m;
    do
    {
      do
      {
        return j;
        if ((endOfStream()) && (!hasData()))
        {
          skipBoundary();
          return -1;
        }
        i = 0;
        k = 0;
        j = 0;
        m = j;
        if (k == 0)
        {
          m = j;
          if (hasData()) {
            break;
          }
          j = fillBuffer();
          m = j;
          if (hasData()) {
            break;
          }
          m = j;
          if (!endOfStream()) {
            break;
          }
          skipBoundary();
          m = -1;
        }
        j = i;
      } while (i != 0);
      j = i;
    } while (m != -1);
    return -1;
    int i1 = this.limit - this.buffer.pos();
    int j = this.buffer.indexOf((byte)10, this.buffer.pos(), i1);
    int n;
    if (j != -1)
    {
      n = 1;
      i1 = j + 1 - this.buffer.pos();
    }
    for (;;)
    {
      j = m;
      k = n;
      if (i1 <= 0) {
        break;
      }
      paramByteArrayBuffer.append(this.buffer.buf(), this.buffer.pos(), i1);
      this.buffer.skip(i1);
      i += i1;
      j = m;
      k = n;
      break;
      n = k;
    }
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("MimeBoundaryInputStream, boundary ");
    byte[] arrayOfByte = this.boundary;
    int j = arrayOfByte.length;
    int i = 0;
    while (i < j)
    {
      localStringBuilder.append((char)arrayOfByte[i]);
      i += 1;
    }
    return localStringBuilder.toString();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\io\MimeBoundaryInputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */