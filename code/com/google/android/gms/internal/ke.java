package com.google.android.gms.internal;

import java.io.IOException;

public abstract class ke
{
  protected int DY = -1;
  
  public static final <T extends ke> T a(T paramT, byte[] paramArrayOfByte)
    throws kd
  {
    return b(paramT, paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  public static final void a(ke paramke, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    try
    {
      paramArrayOfByte = jz.b(paramArrayOfByte, paramInt1, paramInt2);
      paramke.a(paramArrayOfByte);
      paramArrayOfByte.kN();
      return;
    }
    catch (IOException paramke)
    {
      throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", paramke);
    }
  }
  
  public static final <T extends ke> T b(T paramT, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws kd
  {
    try
    {
      paramArrayOfByte = jy.a(paramArrayOfByte, paramInt1, paramInt2);
      paramT.b(paramArrayOfByte);
      paramArrayOfByte.cu(0);
      return paramT;
    }
    catch (kd paramT)
    {
      throw paramT;
    }
    catch (IOException paramT)
    {
      throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).");
    }
  }
  
  public static final byte[] d(ke paramke)
  {
    byte[] arrayOfByte = new byte[paramke.c()];
    a(paramke, arrayOfByte, 0, arrayOfByte.length);
    return arrayOfByte;
  }
  
  public void a(jz paramjz)
    throws IOException
  {}
  
  public abstract ke b(jy paramjy)
    throws IOException;
  
  public int c()
  {
    this.DY = 0;
    return 0;
  }
  
  public int eW()
  {
    if (this.DY < 0) {
      c();
    }
    return this.DY;
  }
  
  public String toString()
  {
    return kf.e(this);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\ke.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */