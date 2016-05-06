package org.apache.james.mime4j.util;

public final class ByteArrayBuffer
  implements ByteSequence
{
  private byte[] buffer;
  private int len;
  
  public ByteArrayBuffer(int paramInt)
  {
    if (paramInt < 0) {
      throw new IllegalArgumentException("Buffer capacity may not be negative");
    }
    this.buffer = new byte[paramInt];
  }
  
  public ByteArrayBuffer(byte[] paramArrayOfByte, int paramInt, boolean paramBoolean)
  {
    if (paramArrayOfByte == null) {
      throw new IllegalArgumentException();
    }
    if ((paramInt < 0) || (paramInt > paramArrayOfByte.length)) {
      throw new IllegalArgumentException();
    }
    if (paramBoolean) {
      this.buffer = paramArrayOfByte;
    }
    for (;;)
    {
      this.len = paramInt;
      return;
      this.buffer = new byte[paramInt];
      System.arraycopy(paramArrayOfByte, 0, this.buffer, 0, paramInt);
    }
  }
  
  public ByteArrayBuffer(byte[] paramArrayOfByte, boolean paramBoolean)
  {
    this(paramArrayOfByte, paramArrayOfByte.length, paramBoolean);
  }
  
  private void expand(int paramInt)
  {
    byte[] arrayOfByte = new byte[Math.max(this.buffer.length << 1, paramInt)];
    System.arraycopy(this.buffer, 0, arrayOfByte, 0, this.len);
    this.buffer = arrayOfByte;
  }
  
  public void append(int paramInt)
  {
    int i = this.len + 1;
    if (i > this.buffer.length) {
      expand(i);
    }
    this.buffer[this.len] = ((byte)paramInt);
    this.len = i;
  }
  
  public void append(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (paramArrayOfByte == null) {}
    do
    {
      return;
      if ((paramInt1 < 0) || (paramInt1 > paramArrayOfByte.length) || (paramInt2 < 0) || (paramInt1 + paramInt2 < 0) || (paramInt1 + paramInt2 > paramArrayOfByte.length)) {
        throw new IndexOutOfBoundsException();
      }
    } while (paramInt2 == 0);
    int i = this.len + paramInt2;
    if (i > this.buffer.length) {
      expand(i);
    }
    System.arraycopy(paramArrayOfByte, paramInt1, this.buffer, this.len, paramInt2);
    this.len = i;
  }
  
  public byte[] buffer()
  {
    return this.buffer;
  }
  
  public byte byteAt(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= this.len)) {
      throw new IndexOutOfBoundsException();
    }
    return this.buffer[paramInt];
  }
  
  public int capacity()
  {
    return this.buffer.length;
  }
  
  public void clear()
  {
    this.len = 0;
  }
  
  public int indexOf(byte paramByte)
  {
    return indexOf(paramByte, 0, this.len);
  }
  
  public int indexOf(byte paramByte, int paramInt1, int paramInt2)
  {
    int i = paramInt1;
    if (paramInt1 < 0) {
      i = 0;
    }
    int j = paramInt2;
    if (paramInt2 > this.len) {
      j = this.len;
    }
    if (i > j)
    {
      paramInt2 = -1;
      return paramInt2;
    }
    paramInt1 = i;
    for (;;)
    {
      if (paramInt1 >= j) {
        break label66;
      }
      paramInt2 = paramInt1;
      if (this.buffer[paramInt1] == paramByte) {
        break;
      }
      paramInt1 += 1;
    }
    label66:
    return -1;
  }
  
  public boolean isEmpty()
  {
    return this.len == 0;
  }
  
  public boolean isFull()
  {
    return this.len == this.buffer.length;
  }
  
  public int length()
  {
    return this.len;
  }
  
  public void setLength(int paramInt)
  {
    if ((paramInt < 0) || (paramInt > this.buffer.length)) {
      throw new IndexOutOfBoundsException();
    }
    this.len = paramInt;
  }
  
  public byte[] toByteArray()
  {
    byte[] arrayOfByte = new byte[this.len];
    if (this.len > 0) {
      System.arraycopy(this.buffer, 0, arrayOfByte, 0, this.len);
    }
    return arrayOfByte;
  }
  
  public String toString()
  {
    return new String(toByteArray());
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\util\ByteArrayBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */