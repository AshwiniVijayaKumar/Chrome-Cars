package com.google.android.gms.internal;

import java.io.IOException;

public final class jy
{
  private int ZU;
  private int ZV;
  private int ZW;
  private int ZX;
  private int ZY;
  private int ZZ = Integer.MAX_VALUE;
  private int aaa;
  private int aab = 64;
  private int aac = 67108864;
  private final byte[] buffer;
  
  private jy(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    this.buffer = paramArrayOfByte;
    this.ZU = paramInt1;
    this.ZV = (paramInt1 + paramInt2);
    this.ZX = paramInt1;
  }
  
  public static jy a(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    return new jy(paramArrayOfByte, paramInt1, paramInt2);
  }
  
  private void kI()
  {
    this.ZV += this.ZW;
    int i = this.ZV;
    if (i > this.ZZ)
    {
      this.ZW = (i - this.ZZ);
      this.ZV -= this.ZW;
      return;
    }
    this.ZW = 0;
  }
  
  public static jy n(byte[] paramArrayOfByte)
  {
    return a(paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  public static long w(long paramLong)
  {
    return paramLong >>> 1 ^ -(1L & paramLong);
  }
  
  public void a(ke paramke)
    throws IOException
  {
    int i = kE();
    if (this.aaa >= this.aab) {
      throw kd.kU();
    }
    i = cw(i);
    this.aaa += 1;
    paramke.b(this);
    cu(0);
    this.aaa -= 1;
    cx(i);
  }
  
  public void a(ke paramke, int paramInt)
    throws IOException
  {
    if (this.aaa >= this.aab) {
      throw kd.kU();
    }
    this.aaa += 1;
    paramke.b(this);
    cu(kh.i(paramInt, 4));
    this.aaa -= 1;
  }
  
  public void cA(int paramInt)
    throws IOException
  {
    if (paramInt < 0) {
      throw kd.kP();
    }
    if (this.ZX + paramInt > this.ZZ)
    {
      cA(this.ZZ - this.ZX);
      throw kd.kO();
    }
    if (paramInt <= this.ZV - this.ZX)
    {
      this.ZX += paramInt;
      return;
    }
    throw kd.kO();
  }
  
  public void cu(int paramInt)
    throws kd
  {
    if (this.ZY != paramInt) {
      throw kd.kS();
    }
  }
  
  public boolean cv(int paramInt)
    throws IOException
  {
    switch (kh.cJ(paramInt))
    {
    default: 
      throw kd.kT();
    case 0: 
      kB();
      return true;
    case 1: 
      kH();
      return true;
    case 2: 
      cA(kE());
      return true;
    case 3: 
      kz();
      cu(kh.i(kh.cK(paramInt), 4));
      return true;
    case 4: 
      return false;
    }
    kG();
    return true;
  }
  
  public int cw(int paramInt)
    throws kd
  {
    if (paramInt < 0) {
      throw kd.kP();
    }
    paramInt = this.ZX + paramInt;
    int i = this.ZZ;
    if (paramInt > i) {
      throw kd.kO();
    }
    this.ZZ = paramInt;
    kI();
    return i;
  }
  
  public void cx(int paramInt)
  {
    this.ZZ = paramInt;
    kI();
  }
  
  public void cy(int paramInt)
  {
    if (paramInt > this.ZX - this.ZU) {
      throw new IllegalArgumentException("Position " + paramInt + " is beyond current " + (this.ZX - this.ZU));
    }
    if (paramInt < 0) {
      throw new IllegalArgumentException("Bad position " + paramInt);
    }
    this.ZX = (this.ZU + paramInt);
  }
  
  public byte[] cz(int paramInt)
    throws IOException
  {
    if (paramInt < 0) {
      throw kd.kP();
    }
    if (this.ZX + paramInt > this.ZZ)
    {
      cA(this.ZZ - this.ZX);
      throw kd.kO();
    }
    if (paramInt <= this.ZV - this.ZX)
    {
      byte[] arrayOfByte = new byte[paramInt];
      System.arraycopy(this.buffer, this.ZX, arrayOfByte, 0, paramInt);
      this.ZX += paramInt;
      return arrayOfByte;
    }
    throw kd.kO();
  }
  
  public byte[] e(int paramInt1, int paramInt2)
  {
    if (paramInt2 == 0) {
      return kh.aaq;
    }
    byte[] arrayOfByte = new byte[paramInt2];
    int i = this.ZU;
    System.arraycopy(this.buffer, i + paramInt1, arrayOfByte, 0, paramInt2);
    return arrayOfByte;
  }
  
  public int getPosition()
  {
    return this.ZX - this.ZU;
  }
  
  public long kA()
    throws IOException
  {
    return kF();
  }
  
  public int kB()
    throws IOException
  {
    return kE();
  }
  
  public boolean kC()
    throws IOException
  {
    return kE() != 0;
  }
  
  public long kD()
    throws IOException
  {
    return w(kF());
  }
  
  public int kE()
    throws IOException
  {
    int i = kL();
    if (i >= 0) {}
    int k;
    do
    {
      return i;
      i &= 0x7F;
      j = kL();
      if (j >= 0) {
        return i | j << 7;
      }
      i |= (j & 0x7F) << 7;
      j = kL();
      if (j >= 0) {
        return i | j << 14;
      }
      i |= (j & 0x7F) << 14;
      k = kL();
      if (k >= 0) {
        return i | k << 21;
      }
      j = kL();
      k = i | (k & 0x7F) << 21 | j << 28;
      i = k;
    } while (j >= 0);
    int j = 0;
    for (;;)
    {
      if (j >= 5) {
        break label133;
      }
      i = k;
      if (kL() >= 0) {
        break;
      }
      j += 1;
    }
    label133:
    throw kd.kQ();
  }
  
  public long kF()
    throws IOException
  {
    int i = 0;
    long l = 0L;
    while (i < 64)
    {
      int j = kL();
      l |= (j & 0x7F) << i;
      if ((j & 0x80) == 0) {
        return l;
      }
      i += 7;
    }
    throw kd.kQ();
  }
  
  public int kG()
    throws IOException
  {
    return kL() & 0xFF | (kL() & 0xFF) << 8 | (kL() & 0xFF) << 16 | (kL() & 0xFF) << 24;
  }
  
  public long kH()
    throws IOException
  {
    int i = kL();
    int j = kL();
    int k = kL();
    int m = kL();
    int n = kL();
    int i1 = kL();
    int i2 = kL();
    int i3 = kL();
    long l = i;
    return (j & 0xFF) << 8 | l & 0xFF | (k & 0xFF) << 16 | (m & 0xFF) << 24 | (n & 0xFF) << 32 | (i1 & 0xFF) << 40 | (i2 & 0xFF) << 48 | (i3 & 0xFF) << 56;
  }
  
  public int kJ()
  {
    if (this.ZZ == Integer.MAX_VALUE) {
      return -1;
    }
    int i = this.ZX;
    return this.ZZ - i;
  }
  
  public boolean kK()
  {
    return this.ZX == this.ZV;
  }
  
  public byte kL()
    throws IOException
  {
    if (this.ZX == this.ZV) {
      throw kd.kO();
    }
    byte[] arrayOfByte = this.buffer;
    int i = this.ZX;
    this.ZX = (i + 1);
    return arrayOfByte[i];
  }
  
  public int ky()
    throws IOException
  {
    if (kK())
    {
      this.ZY = 0;
      return 0;
    }
    this.ZY = kE();
    if (this.ZY == 0) {
      throw kd.kR();
    }
    return this.ZY;
  }
  
  public void kz()
    throws IOException
  {
    int i;
    do
    {
      i = ky();
    } while ((i != 0) && (cv(i)));
  }
  
  public float readFloat()
    throws IOException
  {
    return Float.intBitsToFloat(kG());
  }
  
  public String readString()
    throws IOException
  {
    int i = kE();
    if ((i <= this.ZV - this.ZX) && (i > 0))
    {
      String str = new String(this.buffer, this.ZX, i, "UTF-8");
      this.ZX = (i + this.ZX);
      return str;
    }
    return new String(cz(i), "UTF-8");
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\jy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */