package org.apache.james.mime4j.codec;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Base64InputStream
  extends InputStream
{
  private static final int[] BASE64_DECODE;
  private static final byte BASE64_PAD = 61;
  private static final int ENCODED_BUFFER_SIZE = 1536;
  private static final int EOF = -1;
  private static Log log;
  private boolean closed = false;
  private final byte[] encoded = new byte['؀'];
  private boolean eof;
  private final InputStream in;
  private int position = 0;
  private final ByteQueue q = new ByteQueue();
  private final byte[] singleByte = new byte[1];
  private int size = 0;
  private boolean strict;
  
  static
  {
    if (!Base64InputStream.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      log = LogFactory.getLog(Base64InputStream.class);
      BASE64_DECODE = new int['Ā'];
      i = 0;
      while (i < 256)
      {
        BASE64_DECODE[i] = -1;
        i += 1;
      }
    }
    int i = 0;
    while (i < Base64OutputStream.BASE64_TABLE.length)
    {
      BASE64_DECODE[(Base64OutputStream.BASE64_TABLE[i] & 0xFF)] = i;
      i += 1;
    }
  }
  
  public Base64InputStream(InputStream paramInputStream)
  {
    this(paramInputStream, false);
  }
  
  public Base64InputStream(InputStream paramInputStream, boolean paramBoolean)
  {
    if (paramInputStream == null) {
      throw new IllegalArgumentException();
    }
    this.in = paramInputStream;
    this.strict = paramBoolean;
  }
  
  private int decodePad(int paramInt1, int paramInt2, byte[] paramArrayOfByte, int paramInt3, int paramInt4)
    throws IOException
  {
    this.eof = true;
    byte b1;
    if (paramInt2 == 2)
    {
      b1 = (byte)(paramInt1 >>> 4);
      if (paramInt3 < paramInt4)
      {
        paramArrayOfByte[paramInt3] = b1;
        return paramInt3 + 1;
      }
      this.q.enqueue(b1);
      return paramInt3;
    }
    if (paramInt2 == 3)
    {
      b1 = (byte)(paramInt1 >>> 10);
      byte b2 = (byte)(paramInt1 >>> 2 & 0xFF);
      if (paramInt3 < paramInt4 - 1)
      {
        paramInt1 = paramInt3 + 1;
        paramArrayOfByte[paramInt3] = b1;
        paramArrayOfByte[paramInt1] = b2;
        return paramInt1 + 1;
      }
      if (paramInt3 < paramInt4)
      {
        paramArrayOfByte[paramInt3] = b1;
        this.q.enqueue(b2);
        return paramInt3 + 1;
      }
      this.q.enqueue(b1);
      this.q.enqueue(b2);
      return paramInt3;
    }
    handleUnexpecedPad(paramInt2);
    return paramInt3;
  }
  
  private void handleUnexpecedPad(int paramInt)
    throws IOException
  {
    if (this.strict) {
      throw new IOException("unexpected padding character");
    }
    log.warn("unexpected padding character; dropping " + paramInt + " sextet(s)");
  }
  
  private void handleUnexpectedEof(int paramInt)
    throws IOException
  {
    if (this.strict) {
      throw new IOException("unexpected end of file");
    }
    log.warn("unexpected end of file; dropping " + paramInt + " sextet(s)");
  }
  
  private int read0(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    int j = this.q.count();
    int i = paramInt1;
    while ((j > 0) && (i < paramInt2))
    {
      paramArrayOfByte[i] = this.q.dequeue();
      j -= 1;
      i += 1;
    }
    if (this.eof)
    {
      if (i == paramInt1) {}
      for (paramInt1 = -1;; paramInt1 = i - paramInt1) {
        return paramInt1;
      }
    }
    int i1 = 0;
    int n = 0;
    int m = i;
    if (m < paramInt2)
    {
      int k;
      do
      {
        for (;;)
        {
          k = i1;
          j = n;
          i = m;
          if (this.position != this.size) {
            break label218;
          }
          i = this.in.read(this.encoded, 0, this.encoded.length);
          if (i == -1)
          {
            this.eof = true;
            if (n != 0) {
              handleUnexpectedEof(n);
            }
            if (m == paramInt1) {
              return -1;
            }
            return m - paramInt1;
          }
          if (i <= 0) {
            break;
          }
          this.position = 0;
          this.size = i;
        }
      } while (($assertionsDisabled) || (i == 0));
      throw new AssertionError();
      label205:
      m = BASE64_DECODE[m];
      if (m < 0) {}
      label218:
      byte b1;
      byte b2;
      byte b3;
      for (;;)
      {
        i1 = k;
        n = j;
        m = i;
        if (this.position >= this.size) {
          break;
        }
        i1 = k;
        n = j;
        m = i;
        if (i >= paramInt2) {
          break;
        }
        byte[] arrayOfByte = this.encoded;
        m = this.position;
        this.position = (m + 1);
        m = arrayOfByte[m] & 0xFF;
        if (m != 61) {
          break label205;
        }
        return decodePad(k, j, paramArrayOfByte, i, paramInt2) - paramInt1;
        m = k << 6 | m;
        n = j + 1;
        k = m;
        j = n;
        if (n == 4)
        {
          j = 0;
          b1 = (byte)(m >>> 16);
          b2 = (byte)(m >>> 8);
          b3 = (byte)m;
          if (i >= paramInt2 - 2) {
            break label417;
          }
          k = i + 1;
          paramArrayOfByte[i] = b1;
          i = k + 1;
          paramArrayOfByte[k] = b2;
          paramArrayOfByte[i] = b3;
          i += 1;
          k = m;
        }
      }
      label417:
      if (i < paramInt2 - 1)
      {
        j = i + 1;
        paramArrayOfByte[i] = b1;
        i = j + 1;
        paramArrayOfByte[j] = b2;
        this.q.enqueue(b3);
      }
      while ((!$assertionsDisabled) && (i != paramInt2))
      {
        throw new AssertionError();
        if (i < paramInt2)
        {
          paramArrayOfByte[i] = b1;
          this.q.enqueue(b2);
          this.q.enqueue(b3);
          i += 1;
        }
        else
        {
          this.q.enqueue(b1);
          this.q.enqueue(b2);
          this.q.enqueue(b3);
        }
      }
      return paramInt2 - paramInt1;
    }
    assert (n == 0);
    assert (m == paramInt2);
    return paramInt2 - paramInt1;
  }
  
  public void close()
    throws IOException
  {
    if (this.closed) {
      return;
    }
    this.closed = true;
  }
  
  public int read()
    throws IOException
  {
    if (this.closed) {
      throw new IOException("Base64InputStream has been closed");
    }
    int i;
    do
    {
      i = read0(this.singleByte, 0, 1);
      if (i == -1) {
        return -1;
      }
    } while (i != 1);
    return this.singleByte[0] & 0xFF;
  }
  
  public int read(byte[] paramArrayOfByte)
    throws IOException
  {
    if (this.closed) {
      throw new IOException("Base64InputStream has been closed");
    }
    if (paramArrayOfByte == null) {
      throw new NullPointerException();
    }
    if (paramArrayOfByte.length == 0) {
      return 0;
    }
    return read0(paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (this.closed) {
      throw new IOException("Base64InputStream has been closed");
    }
    if (paramArrayOfByte == null) {
      throw new NullPointerException();
    }
    if ((paramInt1 < 0) || (paramInt2 < 0) || (paramInt1 + paramInt2 > paramArrayOfByte.length)) {
      throw new IndexOutOfBoundsException();
    }
    if (paramInt2 == 0) {
      return 0;
    }
    return read0(paramArrayOfByte, paramInt1, paramInt1 + paramInt2);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\codec\Base64InputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */