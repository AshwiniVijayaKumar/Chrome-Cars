package org.apache.james.mime4j.io;

import java.io.IOException;
import java.io.InputStream;
import org.apache.james.mime4j.util.ByteArrayBuffer;

public class BufferedLineReaderInputStream
  extends LineReaderInputStream
{
  private byte[] buffer;
  private int buflen;
  private int bufpos;
  private final int maxLineLen;
  private boolean truncated;
  
  public BufferedLineReaderInputStream(InputStream paramInputStream, int paramInt)
  {
    this(paramInputStream, paramInt, -1);
  }
  
  public BufferedLineReaderInputStream(InputStream paramInputStream, int paramInt1, int paramInt2)
  {
    super(paramInputStream);
    if (paramInputStream == null) {
      throw new IllegalArgumentException("Input stream may not be null");
    }
    if (paramInt1 <= 0) {
      throw new IllegalArgumentException("Buffer size may not be negative or zero");
    }
    this.buffer = new byte[paramInt1];
    this.bufpos = 0;
    this.buflen = 0;
    this.maxLineLen = paramInt2;
    this.truncated = false;
  }
  
  private void expand(int paramInt)
  {
    byte[] arrayOfByte = new byte[paramInt];
    paramInt = this.buflen - this.bufpos;
    if (paramInt > 0) {
      System.arraycopy(this.buffer, this.bufpos, arrayOfByte, this.bufpos, paramInt);
    }
    this.buffer = arrayOfByte;
  }
  
  public byte[] buf()
  {
    return this.buffer;
  }
  
  public int capacity()
  {
    return this.buffer.length;
  }
  
  public byte charAt(int paramInt)
  {
    if ((paramInt < this.bufpos) || (paramInt > this.buflen)) {
      throw new IndexOutOfBoundsException();
    }
    return this.buffer[paramInt];
  }
  
  public void clear()
  {
    this.bufpos = 0;
    this.buflen = 0;
  }
  
  public void ensureCapacity(int paramInt)
  {
    if (paramInt > this.buffer.length) {
      expand(paramInt);
    }
  }
  
  public int fillBuffer()
    throws IOException
  {
    if (this.bufpos > 0)
    {
      i = this.buflen - this.bufpos;
      if (i > 0) {
        System.arraycopy(this.buffer, this.bufpos, this.buffer, 0, i);
      }
      this.bufpos = 0;
      this.buflen = i;
    }
    int i = this.buflen;
    int j = this.buffer.length;
    j = this.in.read(this.buffer, i, j - i);
    if (j == -1) {
      return -1;
    }
    this.buflen = (i + j);
    return j;
  }
  
  public boolean hasBufferedData()
  {
    return this.bufpos < this.buflen;
  }
  
  public int indexOf(byte paramByte)
  {
    return indexOf(paramByte, this.bufpos, this.buflen - this.bufpos);
  }
  
  public int indexOf(byte paramByte, int paramInt1, int paramInt2)
  {
    if ((paramInt1 < this.bufpos) || (paramInt2 < 0) || (paramInt1 + paramInt2 > this.buflen)) {
      throw new IndexOutOfBoundsException();
    }
    int i = paramInt1;
    while (i < paramInt1 + paramInt2)
    {
      if (this.buffer[i] == paramByte) {
        return i;
      }
      i += 1;
    }
    return -1;
  }
  
  public int indexOf(byte[] paramArrayOfByte)
  {
    return indexOf(paramArrayOfByte, this.bufpos, this.buflen - this.bufpos);
  }
  
  public int indexOf(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (paramArrayOfByte == null) {
      throw new IllegalArgumentException("Pattern may not be null");
    }
    if ((paramInt1 < this.bufpos) || (paramInt2 < 0) || (paramInt1 + paramInt2 > this.buflen)) {
      throw new IndexOutOfBoundsException();
    }
    int k;
    if (paramInt2 < paramArrayOfByte.length)
    {
      k = -1;
      return k;
    }
    int[] arrayOfInt = new int['Ā'];
    int i = 0;
    while (i < arrayOfInt.length)
    {
      arrayOfInt[i] = (paramArrayOfByte.length + 1);
      i += 1;
    }
    i = 0;
    while (i < paramArrayOfByte.length)
    {
      arrayOfInt[(paramArrayOfByte[i] & 0xFF)] = (paramArrayOfByte.length - i);
      i += 1;
    }
    i = 0;
    for (;;)
    {
      int m;
      int n;
      if (i <= paramInt2 - paramArrayOfByte.length)
      {
        m = paramInt1 + i;
        n = 1;
        k = 0;
      }
      int j;
      for (;;)
      {
        j = n;
        if (k < paramArrayOfByte.length)
        {
          if (this.buffer[(m + k)] != paramArrayOfByte[k]) {
            j = 0;
          }
        }
        else
        {
          k = m;
          if (j != 0) {
            break;
          }
          j = m + paramArrayOfByte.length;
          if (j < this.buffer.length) {
            break label219;
          }
          return -1;
        }
        k += 1;
      }
      label219:
      i += arrayOfInt[(this.buffer[j] & 0xFF)];
    }
  }
  
  public int length()
  {
    return this.buflen - this.bufpos;
  }
  
  public int limit()
  {
    return this.buflen;
  }
  
  public boolean markSupported()
  {
    return false;
  }
  
  public int pos()
  {
    return this.bufpos;
  }
  
  public int read()
    throws IOException
  {
    if (this.truncated) {
      return -1;
    }
    while (!hasBufferedData()) {
      if (fillBuffer() == -1) {
        return -1;
      }
    }
    byte[] arrayOfByte = this.buffer;
    int i = this.bufpos;
    this.bufpos = (i + 1);
    return arrayOfByte[i] & 0xFF;
  }
  
  public int read(byte[] paramArrayOfByte)
    throws IOException
  {
    int i = 0;
    if (this.truncated) {
      i = -1;
    }
    while (paramArrayOfByte == null) {
      return i;
    }
    return read(paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (this.truncated) {
      return -1;
    }
    if (paramArrayOfByte == null) {
      return 0;
    }
    while (!hasBufferedData()) {
      if (fillBuffer() == -1) {
        return -1;
      }
    }
    int j = this.buflen - this.bufpos;
    int i = j;
    if (j > paramInt2) {
      i = paramInt2;
    }
    System.arraycopy(this.buffer, this.bufpos, paramArrayOfByte, paramInt1, i);
    this.bufpos += i;
    return i;
  }
  
  public int readLine(ByteArrayBuffer paramByteArrayBuffer)
    throws IOException
  {
    if (paramByteArrayBuffer == null) {
      throw new IllegalArgumentException("Buffer may not be null");
    }
    if (this.truncated) {
      j = -1;
    }
    int i;
    int m;
    int k;
    do
    {
      do
      {
        return j;
        i = 0;
        m = 0;
        j = 0;
        k = j;
        if (m == 0)
        {
          k = j;
          if (hasBufferedData()) {
            break;
          }
          j = fillBuffer();
          k = j;
          if (j != -1) {
            break;
          }
          k = j;
        }
        j = i;
      } while (i != 0);
      j = i;
    } while (k != -1);
    return -1;
    int j = indexOf((byte)10);
    int n;
    if (j != -1)
    {
      n = 1;
      j = j + 1 - pos();
    }
    for (;;)
    {
      int i1 = i;
      if (j > 0)
      {
        paramByteArrayBuffer.append(buf(), pos(), j);
        skip(j);
        i1 = i + j;
      }
      j = k;
      m = n;
      i = i1;
      if (this.maxLineLen <= 0) {
        break;
      }
      j = k;
      m = n;
      i = i1;
      if (paramByteArrayBuffer.length() < this.maxLineLen) {
        break;
      }
      throw new MaxLineLimitException("Maximum line length limit exceeded");
      j = length();
      n = m;
    }
  }
  
  public int skip(int paramInt)
  {
    paramInt = Math.min(paramInt, this.buflen - this.bufpos);
    this.bufpos += paramInt;
    return paramInt;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("[pos: ");
    localStringBuilder.append(this.bufpos);
    localStringBuilder.append("]");
    localStringBuilder.append("[limit: ");
    localStringBuilder.append(this.buflen);
    localStringBuilder.append("]");
    localStringBuilder.append("[");
    int i = this.bufpos;
    while (i < this.buflen)
    {
      localStringBuilder.append((char)this.buffer[i]);
      i += 1;
    }
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
  
  public void truncate()
  {
    clear();
    this.truncated = true;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\io\BufferedLineReaderInputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */