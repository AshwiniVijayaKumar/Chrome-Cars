package com.spoledge.aacdecoder;

import android.util.Log;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class FlashAACInputStream
  extends InputStream
{
  private int _aacProfile;
  private int _channelConfig;
  private int _sampleRateIndex;
  private byte[] backBuffer = new byte[this.backBufferLen];
  private int backBufferLen = 65536;
  private int countInBackBuffer = 0;
  private DataInputStream dis = null;
  private byte[] readBuffer = new byte[this.readBufferLen];
  private int readBufferLen = 65536;
  
  public FlashAACInputStream(InputStream paramInputStream)
    throws IOException
  {
    this.dis = new DataInputStream(paramInputStream);
    if (((char)this.dis.readByte() != 'F') || ((char)this.dis.readByte() != 'L') || ((char)this.dis.readByte() != 'V')) {
      throw new IOException("The file is not a FLV file.");
    }
    this.dis.readByte();
    int i = this.dis.readByte();
    if ((i != 5) && (i != 4)) {
      throw new IOException("No Audio Stream");
    }
    this.dis.readInt();
  }
  
  protected static void dumpHeaders(URLConnection paramURLConnection)
  {
    paramURLConnection = paramURLConnection.getHeaderFields().entrySet().iterator();
    while (paramURLConnection.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)paramURLConnection.next();
      Iterator localIterator = ((List)localEntry.getValue()).iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        System.out.println("header: key=" + (String)localEntry.getKey() + ", val=" + str);
      }
    }
  }
  
  private int fillBuffer(byte[] paramArrayOfByte, int paramInt)
    throws IOException
  {
    if (readAACHeader()) {
      return 0;
    }
    paramInt -= 1;
    long l = writeBits(writeBits(writeBits(0L, 12, 4095), 3, 0), 1, 1);
    paramArrayOfByte[0] = ((byte)(int)(l >> 8));
    paramArrayOfByte[1] = ((byte)(int)l);
    l = writeBits(writeBits(writeBits(writeBits(writeBits(writeBits(0L, 2, this._aacProfile), 4, this._sampleRateIndex), 1, 0), 3, this._channelConfig), 4, 0), 2, paramInt + 7 & 0x1800);
    paramArrayOfByte[2] = ((byte)(int)(l >> 8));
    paramArrayOfByte[3] = ((byte)(int)l);
    l = writeBits(writeBits(writeBits(0L, 11, paramInt + 7 & 0x7FF), 11, 2047), 2, 0);
    paramArrayOfByte[4] = ((byte)(int)(l >> 16));
    paramArrayOfByte[5] = ((byte)(int)(l >> 8));
    paramArrayOfByte[6] = ((byte)(int)l);
    this.dis.readFully(paramArrayOfByte, 7, paramInt);
    paramArrayOfByte[(paramInt + 7)] = 0;
    return paramInt + 8;
  }
  
  public static void main(String[] paramArrayOfString)
  {
    try
    {
      paramArrayOfString = new URL("http://184.82.135.71/download.flv").openConnection();
      paramArrayOfString.connect();
      dumpHeaders(paramArrayOfString);
      paramArrayOfString = new FlashAACInputStream(paramArrayOfString.getInputStream());
      FileOutputStream localFileOutputStream = new FileOutputStream(new File("output.aac"));
      new DataOutputStream(localFileOutputStream);
      byte[] arrayOfByte = new byte['က'];
      int i = 0;
      while (i < 100)
      {
        int j = paramArrayOfString.readFrame(arrayOfByte);
        System.out.println("bytesRead = " + j);
        localFileOutputStream.write(arrayOfByte, 0, j);
        i += 1;
      }
      localFileOutputStream.close();
      return;
    }
    catch (Exception paramArrayOfString)
    {
      System.out.println(paramArrayOfString);
      paramArrayOfString.printStackTrace();
    }
  }
  
  private boolean readAACHeader()
    throws IOException
  {
    if (this.dis.readByte() != 0) {
      return false;
    }
    int i = (this.dis.readByte() & 0xFF) * 256 + (this.dis.readByte() & 0xFF) << 16;
    this._aacProfile = (readBits(i, 5) - 1);
    i <<= 5;
    this._sampleRateIndex = readBits(i, 4);
    this._channelConfig = readBits(i << 4, 4);
    Log.d("FlashAACInputStream", "aacProf = " + this._aacProfile);
    Log.d("FlashAACInputStream", "_sampleRateIndex = " + this._sampleRateIndex);
    Log.d("FlashAACInputStream", "_channelConfig = " + this._channelConfig);
    if ((this._aacProfile < 0) || (this._aacProfile > 3)) {
      throw new IOException("Unsupported AAC profile.");
    }
    if (this._sampleRateIndex > 12) {
      throw new IOException("Invalid AAC sample rate index.");
    }
    if (this._channelConfig > 6) {
      throw new IOException("Invalid AAC channel configuration.");
    }
    return true;
  }
  
  private int readBits(int paramInt1, int paramInt2)
  {
    return paramInt1 >> 32 - paramInt2;
  }
  
  private int readFrame(byte[] paramArrayOfByte)
    throws IOException
  {
    this.dis.readInt();
    for (int i = this.dis.readByte(); i != 8; i = this.dis.readByte())
    {
      l1 = readNext3Bytes();
      this.dis.skipBytes((int)(l1 + 11L));
    }
    long l1 = readNext3Bytes() - 1L;
    i = this.dis.readInt();
    long l2 = readNext3Bytes();
    int j = this.dis.readByte();
    Log.d("FlashAACInputStream", "dataSize = " + l1 + ", timestamps = " + i + ", streamId = " + l2 + ", audioHeader = " + j);
    return fillBuffer(paramArrayOfByte, (int)l1);
  }
  
  private long readNext3Bytes()
    throws IOException
  {
    return this.dis.readUnsignedByte() * 256 * 256 + this.dis.readUnsignedByte() * 256 + this.dis.readUnsignedByte();
  }
  
  public int read()
    throws IOException
  {
    byte[] arrayOfByte = new byte[1];
    read(arrayOfByte, 0, 1);
    return arrayOfByte[0] & 0xFF;
  }
  
  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if ((paramInt1 < 0) || (paramInt2 < 0) || (paramArrayOfByte.length - paramInt1 < paramInt2)) {
      throw new IndexOutOfBoundsException();
    }
    if (paramInt2 > this.readBufferLen) {
      throw new IndexOutOfBoundsException("len exceeds readBufferLen");
    }
    Log.d("FlashAACInputStream", "read: countInBackBuffer = " + this.countInBackBuffer);
    if (this.countInBackBuffer > 0)
    {
      if (this.countInBackBuffer >= paramInt2)
      {
        System.arraycopy(this.backBuffer, 0, paramArrayOfByte, paramInt1, paramInt2);
        if (this.countInBackBuffer > paramInt2) {
          System.arraycopy(this.backBuffer, paramInt2, this.backBuffer, 0, this.countInBackBuffer - paramInt2);
        }
        this.countInBackBuffer -= paramInt2;
        return paramInt2;
      }
      System.arraycopy(this.backBuffer, 0, paramArrayOfByte, paramInt1, this.countInBackBuffer);
    }
    int i = paramInt2 - this.countInBackBuffer;
    int j = paramInt1 + this.countInBackBuffer;
    this.countInBackBuffer = 0;
    paramInt1 = i;
    i = j;
    for (;;)
    {
      int k = readFrame(this.readBuffer);
      j = paramInt1 - k;
      if (j <= 0)
      {
        System.arraycopy(this.readBuffer, 0, paramArrayOfByte, i, k + j);
        if (j >= 0) {
          break;
        }
        System.arraycopy(this.readBuffer, k + j, this.backBuffer, 0, Math.abs(j));
        this.countInBackBuffer = Math.abs(j);
        return paramInt2;
      }
      paramInt1 = j;
      if (j > 0)
      {
        System.arraycopy(this.readBuffer, 0, paramArrayOfByte, i, k);
        i += k;
        paramInt1 = j;
      }
    }
  }
  
  public long writeBits(long paramLong, int paramInt1, int paramInt2)
  {
    return paramLong << paramInt1 | paramInt2 & 4294967295L >> 32 - paramInt1;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\spoledge\aacdecoder\FlashAACInputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */