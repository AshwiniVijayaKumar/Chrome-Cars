package com.spoledge.aacdecoder;

import android.util.Log;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class IcyInputStream
  extends FilterInputStream
{
  private static final String LOG = "IcyInputStream";
  protected String characterEncoding;
  protected byte[] mbuffer;
  protected int period;
  protected PlayerCallback playerCallback;
  protected int remaining;
  
  public IcyInputStream(InputStream paramInputStream, int paramInt)
  {
    this(paramInputStream, paramInt, null);
  }
  
  public IcyInputStream(InputStream paramInputStream, int paramInt, PlayerCallback paramPlayerCallback)
  {
    this(paramInputStream, paramInt, paramPlayerCallback, null);
  }
  
  public IcyInputStream(InputStream paramInputStream, int paramInt, PlayerCallback paramPlayerCallback, String paramString)
  {
    super(paramInputStream);
    this.period = paramInt;
    this.playerCallback = paramPlayerCallback;
    if (paramString != null) {}
    for (;;)
    {
      this.characterEncoding = paramString;
      this.remaining = paramInt;
      this.mbuffer = new byte[''];
      return;
      paramString = "UTF-8";
    }
  }
  
  protected void fetchMetadata()
    throws IOException
  {
    this.remaining = this.period;
    int i = this.in.read();
    if (i < 1) {
      return;
    }
    i <<= 4;
    if (this.mbuffer.length < i)
    {
      this.mbuffer = null;
      this.mbuffer = new byte[i];
      Log.d("IcyInputStream", "Enlarged metadata buffer to " + i + " bytes");
    }
    int k = readFully(this.mbuffer, 0, i);
    i = 0;
    for (;;)
    {
      int j = k;
      if (i < k)
      {
        if (this.mbuffer[i] != 0) {
          break label160;
        }
        j = i;
      }
      try
      {
        String str = new String(this.mbuffer, 0, j, this.characterEncoding);
        Log.d("IcyInputStream", "Metadata string: " + str);
        parseMetadata(str);
        return;
      }
      catch (Exception localException)
      {
        label160:
        Log.e("IcyInputStream", "Cannot convert bytes to String");
      }
      i += 1;
    }
  }
  
  public String getCharacterEncoding()
  {
    return this.characterEncoding;
  }
  
  protected void parseMetadata(String paramString)
  {
    String[] arrayOfString = paramString.split(";");
    int k = arrayOfString.length;
    int i = 0;
    if (i < k)
    {
      paramString = arrayOfString[i];
      int m = paramString.indexOf('=');
      if (m < 1) {}
      label88:
      label143:
      label170:
      for (;;)
      {
        i += 1;
        break;
        int j;
        String str;
        if ((m + 1 < paramString.length()) && (paramString.charAt(paramString.length() - 1) == '\'') && (paramString.charAt(m + 1) == '\''))
        {
          j = 1;
          str = paramString.substring(0, m);
          if (j == 0) {
            break label143;
          }
          paramString = paramString.substring(m + 2, paramString.length() - 1);
        }
        for (;;)
        {
          if (this.playerCallback == null) {
            break label170;
          }
          this.playerCallback.playerMetadata(str, paramString);
          break;
          j = 0;
          break label88;
          if (m + 1 < paramString.length()) {
            paramString = paramString.substring(m + 1);
          } else {
            paramString = "";
          }
        }
      }
    }
  }
  
  public int read()
    throws IOException
  {
    int i = super.read();
    int j = this.remaining - 1;
    this.remaining = j;
    if (j == 0) {
      fetchMetadata();
    }
    return i;
  }
  
  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    InputStream localInputStream = this.in;
    int i = paramInt2;
    if (this.remaining < paramInt2) {
      i = this.remaining;
    }
    paramInt1 = localInputStream.read(paramArrayOfByte, paramInt1, i);
    if (this.remaining == paramInt1)
    {
      fetchMetadata();
      return paramInt1;
    }
    this.remaining -= paramInt1;
    return paramInt1;
  }
  
  protected final int readFully(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    int i = paramInt2;
    paramInt2 = paramInt1;
    while (i > 0)
    {
      int j = this.in.read(paramArrayOfByte, paramInt2, i);
      if (j == -1) {
        break;
      }
      paramInt2 += j;
      i -= j;
    }
    return paramInt2 - paramInt1;
  }
  
  public void setCharacterEncoding(String paramString)
  {
    this.characterEncoding = paramString;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\spoledge\aacdecoder\IcyInputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */