package org.apache.james.mime4j.codec;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Set;

public class Base64OutputStream
  extends FilterOutputStream
{
  private static final Set<Byte> BASE64_CHARS;
  private static final byte BASE64_PAD = 61;
  static final byte[] BASE64_TABLE;
  private static final byte[] CRLF_SEPARATOR;
  private static final int DEFAULT_LINE_LENGTH = 76;
  private static final int ENCODED_BUFFER_SIZE = 2048;
  private static final int MASK_6BITS = 63;
  private boolean closed = false;
  private int data = 0;
  private final byte[] encoded;
  private final int lineLength;
  private int linePosition = 0;
  private final byte[] lineSeparator;
  private int modulus = 0;
  private int position = 0;
  private final byte[] singleByte = new byte[1];
  
  static
  {
    if (!Base64OutputStream.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      CRLF_SEPARATOR = new byte[] { 13, 10 };
      BASE64_TABLE = new byte[] { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47 };
      BASE64_CHARS = new HashSet();
      byte[] arrayOfByte = BASE64_TABLE;
      int j = arrayOfByte.length;
      int i = 0;
      while (i < j)
      {
        byte b = arrayOfByte[i];
        BASE64_CHARS.add(Byte.valueOf(b));
        i += 1;
      }
    }
    BASE64_CHARS.add(Byte.valueOf((byte)61));
  }
  
  public Base64OutputStream(OutputStream paramOutputStream)
  {
    this(paramOutputStream, 76, CRLF_SEPARATOR);
  }
  
  public Base64OutputStream(OutputStream paramOutputStream, int paramInt)
  {
    this(paramOutputStream, paramInt, CRLF_SEPARATOR);
  }
  
  public Base64OutputStream(OutputStream paramOutputStream, int paramInt, byte[] paramArrayOfByte)
  {
    super(paramOutputStream);
    if (paramOutputStream == null) {
      throw new IllegalArgumentException();
    }
    if (paramInt < 0) {
      throw new IllegalArgumentException();
    }
    checkLineSeparator(paramArrayOfByte);
    this.lineLength = paramInt;
    this.lineSeparator = new byte[paramArrayOfByte.length];
    System.arraycopy(paramArrayOfByte, 0, this.lineSeparator, 0, paramArrayOfByte.length);
    this.encoded = new byte['ࠀ'];
  }
  
  private void checkLineSeparator(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte.length > 2048) {
      throw new IllegalArgumentException("line separator length exceeds 2048");
    }
    int k = paramArrayOfByte.length;
    int j = 0;
    while (j < k)
    {
      int i = paramArrayOfByte[j];
      if (BASE64_CHARS.contains(Byte.valueOf(i))) {
        throw new IllegalArgumentException("line separator must not contain base64 character '" + (char)(i & 0xFF) + "'");
      }
      j += 1;
    }
  }
  
  private void close0()
    throws IOException
  {
    if (this.modulus != 0) {
      writePad();
    }
    if ((this.lineLength > 0) && (this.linePosition > 0)) {
      writeLineSeparator();
    }
    flush0();
  }
  
  private void flush0()
    throws IOException
  {
    if (this.position > 0)
    {
      this.out.write(this.encoded, 0, this.position);
      this.position = 0;
    }
  }
  
  private void write0(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    while (paramInt1 < paramInt2)
    {
      this.data = (this.data << 8 | paramArrayOfByte[paramInt1] & 0xFF);
      int j = this.modulus + 1;
      this.modulus = j;
      if (j == 3)
      {
        this.modulus = 0;
        if ((this.lineLength > 0) && (this.linePosition >= this.lineLength))
        {
          this.linePosition = 0;
          if (this.encoded.length - this.position < this.lineSeparator.length) {
            flush0();
          }
          arrayOfByte1 = this.lineSeparator;
          int k = arrayOfByte1.length;
          j = 0;
          while (j < k)
          {
            int i = arrayOfByte1[j];
            byte[] arrayOfByte2 = this.encoded;
            int m = this.position;
            this.position = (m + 1);
            arrayOfByte2[m] = i;
            j += 1;
          }
        }
        if (this.encoded.length - this.position < 4) {
          flush0();
        }
        byte[] arrayOfByte1 = this.encoded;
        j = this.position;
        this.position = (j + 1);
        arrayOfByte1[j] = BASE64_TABLE[(this.data >> 18 & 0x3F)];
        arrayOfByte1 = this.encoded;
        j = this.position;
        this.position = (j + 1);
        arrayOfByte1[j] = BASE64_TABLE[(this.data >> 12 & 0x3F)];
        arrayOfByte1 = this.encoded;
        j = this.position;
        this.position = (j + 1);
        arrayOfByte1[j] = BASE64_TABLE[(this.data >> 6 & 0x3F)];
        arrayOfByte1 = this.encoded;
        j = this.position;
        this.position = (j + 1);
        arrayOfByte1[j] = BASE64_TABLE[(this.data & 0x3F)];
        this.linePosition += 4;
      }
      paramInt1 += 1;
    }
  }
  
  private void writeLineSeparator()
    throws IOException
  {
    this.linePosition = 0;
    if (this.encoded.length - this.position < this.lineSeparator.length) {
      flush0();
    }
    byte[] arrayOfByte1 = this.lineSeparator;
    int k = arrayOfByte1.length;
    int j = 0;
    while (j < k)
    {
      int i = arrayOfByte1[j];
      byte[] arrayOfByte2 = this.encoded;
      int m = this.position;
      this.position = (m + 1);
      arrayOfByte2[m] = i;
      j += 1;
    }
  }
  
  private void writePad()
    throws IOException
  {
    if ((this.lineLength > 0) && (this.linePosition >= this.lineLength)) {
      writeLineSeparator();
    }
    if (this.encoded.length - this.position < 4) {
      flush0();
    }
    byte[] arrayOfByte;
    int i;
    if (this.modulus == 1)
    {
      arrayOfByte = this.encoded;
      i = this.position;
      this.position = (i + 1);
      arrayOfByte[i] = BASE64_TABLE[(this.data >> 2 & 0x3F)];
      arrayOfByte = this.encoded;
      i = this.position;
      this.position = (i + 1);
      arrayOfByte[i] = BASE64_TABLE[(this.data << 4 & 0x3F)];
      arrayOfByte = this.encoded;
      i = this.position;
      this.position = (i + 1);
      arrayOfByte[i] = 61;
      arrayOfByte = this.encoded;
      i = this.position;
      this.position = (i + 1);
      arrayOfByte[i] = 61;
    }
    for (;;)
    {
      this.linePosition += 4;
      return;
      assert (this.modulus == 2);
      arrayOfByte = this.encoded;
      i = this.position;
      this.position = (i + 1);
      arrayOfByte[i] = BASE64_TABLE[(this.data >> 10 & 0x3F)];
      arrayOfByte = this.encoded;
      i = this.position;
      this.position = (i + 1);
      arrayOfByte[i] = BASE64_TABLE[(this.data >> 4 & 0x3F)];
      arrayOfByte = this.encoded;
      i = this.position;
      this.position = (i + 1);
      arrayOfByte[i] = BASE64_TABLE[(this.data << 2 & 0x3F)];
      arrayOfByte = this.encoded;
      i = this.position;
      this.position = (i + 1);
      arrayOfByte[i] = 61;
    }
  }
  
  public void close()
    throws IOException
  {
    if (this.closed) {
      return;
    }
    this.closed = true;
    close0();
  }
  
  public void flush()
    throws IOException
  {
    if (this.closed) {
      throw new IOException("Base64OutputStream has been closed");
    }
    flush0();
  }
  
  public final void write(int paramInt)
    throws IOException
  {
    if (this.closed) {
      throw new IOException("Base64OutputStream has been closed");
    }
    this.singleByte[0] = ((byte)paramInt);
    write0(this.singleByte, 0, 1);
  }
  
  public final void write(byte[] paramArrayOfByte)
    throws IOException
  {
    if (this.closed) {
      throw new IOException("Base64OutputStream has been closed");
    }
    if (paramArrayOfByte == null) {
      throw new NullPointerException();
    }
    if (paramArrayOfByte.length == 0) {
      return;
    }
    write0(paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  public final void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (this.closed) {
      throw new IOException("Base64OutputStream has been closed");
    }
    if (paramArrayOfByte == null) {
      throw new NullPointerException();
    }
    if ((paramInt1 < 0) || (paramInt2 < 0) || (paramInt1 + paramInt2 > paramArrayOfByte.length)) {
      throw new IndexOutOfBoundsException();
    }
    if (paramInt2 == 0) {
      return;
    }
    write0(paramArrayOfByte, paramInt1, paramInt1 + paramInt2);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\codec\Base64OutputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */