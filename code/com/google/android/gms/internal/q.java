package com.google.android.gms.internal;

import java.io.IOException;

class q
  implements o
{
  private jz kv;
  private byte[] kw;
  private final int kx;
  
  public q(int paramInt)
  {
    this.kx = paramInt;
    reset();
  }
  
  public void b(int paramInt, long paramLong)
    throws IOException
  {
    this.kv.b(paramInt, paramLong);
  }
  
  public void b(int paramInt, String paramString)
    throws IOException
  {
    this.kv.b(paramInt, paramString);
  }
  
  public void reset()
  {
    this.kw = new byte[this.kx];
    this.kv = jz.o(this.kw);
  }
  
  public byte[] z()
    throws IOException
  {
    int i = this.kv.kM();
    if (i < 0) {
      throw new IOException();
    }
    if (i == 0) {
      return this.kw;
    }
    byte[] arrayOfByte = new byte[this.kw.length - i];
    System.arraycopy(this.kw, 0, arrayOfByte, 0, arrayOfByte.length);
    return arrayOfByte;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\q.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */