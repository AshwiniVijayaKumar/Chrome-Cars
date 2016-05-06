package com.google.android.gms.internal;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public final class jz
{
  private final int aad;
  private final byte[] buffer;
  private int position;
  
  private jz(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    this.buffer = paramArrayOfByte;
    this.position = paramInt1;
    this.aad = (paramInt1 + paramInt2);
  }
  
  public static int A(long paramLong)
  {
    return C(D(paramLong));
  }
  
  public static int B(boolean paramBoolean)
  {
    return 1;
  }
  
  public static int C(long paramLong)
  {
    if ((0xFFFFFFFFFFFFFF80 & paramLong) == 0L) {
      return 1;
    }
    if ((0xFFFFFFFFFFFFC000 & paramLong) == 0L) {
      return 2;
    }
    if ((0xFFFFFFFFFFE00000 & paramLong) == 0L) {
      return 3;
    }
    if ((0xFFFFFFFFF0000000 & paramLong) == 0L) {
      return 4;
    }
    if ((0xFFFFFFF800000000 & paramLong) == 0L) {
      return 5;
    }
    if ((0xFFFFFC0000000000 & paramLong) == 0L) {
      return 6;
    }
    if ((0xFFFE000000000000 & paramLong) == 0L) {
      return 7;
    }
    if ((0xFF00000000000000 & paramLong) == 0L) {
      return 8;
    }
    if ((0x8000000000000000 & paramLong) == 0L) {
      return 9;
    }
    return 10;
  }
  
  public static long D(long paramLong)
  {
    return paramLong << 1 ^ paramLong >> 63;
  }
  
  public static int b(int paramInt, float paramFloat)
  {
    return cE(paramInt) + e(paramFloat);
  }
  
  public static int b(int paramInt, ke paramke)
  {
    return cE(paramInt) + c(paramke);
  }
  
  public static int b(int paramInt, boolean paramBoolean)
  {
    return cE(paramInt) + B(paramBoolean);
  }
  
  public static jz b(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    return new jz(paramArrayOfByte, paramInt1, paramInt2);
  }
  
  public static int bQ(String paramString)
  {
    try
    {
      paramString = paramString.getBytes("UTF-8");
      int i = cG(paramString.length);
      int j = paramString.length;
      return j + i;
    }
    catch (UnsupportedEncodingException paramString)
    {
      throw new RuntimeException("UTF-8 not supported.");
    }
  }
  
  public static int c(ke paramke)
  {
    int i = paramke.c();
    return i + cG(i);
  }
  
  public static int cC(int paramInt)
  {
    if (paramInt >= 0) {
      return cG(paramInt);
    }
    return 10;
  }
  
  public static int cE(int paramInt)
  {
    return cG(kh.i(paramInt, 0));
  }
  
  public static int cG(int paramInt)
  {
    if ((paramInt & 0xFFFFFF80) == 0) {
      return 1;
    }
    if ((paramInt & 0xC000) == 0) {
      return 2;
    }
    if ((0xFFE00000 & paramInt) == 0) {
      return 3;
    }
    if ((0xF0000000 & paramInt) == 0) {
      return 4;
    }
    return 5;
  }
  
  public static int d(int paramInt, long paramLong)
  {
    return cE(paramInt) + z(paramLong);
  }
  
  public static int e(float paramFloat)
  {
    return 4;
  }
  
  public static int e(int paramInt, long paramLong)
  {
    return cE(paramInt) + A(paramLong);
  }
  
  public static int g(int paramInt1, int paramInt2)
  {
    return cE(paramInt1) + cC(paramInt2);
  }
  
  public static int g(int paramInt, String paramString)
  {
    return cE(paramInt) + bQ(paramString);
  }
  
  public static jz o(byte[] paramArrayOfByte)
  {
    return b(paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  public static int z(long paramLong)
  {
    return C(paramLong);
  }
  
  public void A(boolean paramBoolean)
    throws IOException
  {
    if (paramBoolean) {}
    for (int i = 1;; i = 0)
    {
      cD(i);
      return;
    }
  }
  
  public void B(long paramLong)
    throws IOException
  {
    for (;;)
    {
      if ((0xFFFFFFFFFFFFFF80 & paramLong) == 0L)
      {
        cD((int)paramLong);
        return;
      }
      cD((int)paramLong & 0x7F | 0x80);
      paramLong >>>= 7;
    }
  }
  
  public void a(int paramInt, float paramFloat)
    throws IOException
  {
    h(paramInt, 5);
    d(paramFloat);
  }
  
  public void a(int paramInt, ke paramke)
    throws IOException
  {
    h(paramInt, 2);
    b(paramke);
  }
  
  public void a(int paramInt, boolean paramBoolean)
    throws IOException
  {
    h(paramInt, 0);
    A(paramBoolean);
  }
  
  public void b(byte paramByte)
    throws IOException
  {
    if (this.position == this.aad) {
      throw new a(this.position, this.aad);
    }
    byte[] arrayOfByte = this.buffer;
    int i = this.position;
    this.position = (i + 1);
    arrayOfByte[i] = paramByte;
  }
  
  public void b(int paramInt, long paramLong)
    throws IOException
  {
    h(paramInt, 0);
    x(paramLong);
  }
  
  public void b(int paramInt, String paramString)
    throws IOException
  {
    h(paramInt, 2);
    bP(paramString);
  }
  
  public void b(ke paramke)
    throws IOException
  {
    cF(paramke.eW());
    paramke.a(this);
  }
  
  public void bP(String paramString)
    throws IOException
  {
    paramString = paramString.getBytes("UTF-8");
    cF(paramString.length);
    p(paramString);
  }
  
  public void c(int paramInt, long paramLong)
    throws IOException
  {
    h(paramInt, 0);
    y(paramLong);
  }
  
  public void c(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (this.aad - this.position >= paramInt2)
    {
      System.arraycopy(paramArrayOfByte, paramInt1, this.buffer, this.position, paramInt2);
      this.position += paramInt2;
      return;
    }
    throw new a(this.position, this.aad);
  }
  
  public void cB(int paramInt)
    throws IOException
  {
    if (paramInt >= 0)
    {
      cF(paramInt);
      return;
    }
    B(paramInt);
  }
  
  public void cD(int paramInt)
    throws IOException
  {
    b((byte)paramInt);
  }
  
  public void cF(int paramInt)
    throws IOException
  {
    for (;;)
    {
      if ((paramInt & 0xFFFFFF80) == 0)
      {
        cD(paramInt);
        return;
      }
      cD(paramInt & 0x7F | 0x80);
      paramInt >>>= 7;
    }
  }
  
  public void cH(int paramInt)
    throws IOException
  {
    cD(paramInt & 0xFF);
    cD(paramInt >> 8 & 0xFF);
    cD(paramInt >> 16 & 0xFF);
    cD(paramInt >> 24 & 0xFF);
  }
  
  public void d(float paramFloat)
    throws IOException
  {
    cH(Float.floatToIntBits(paramFloat));
  }
  
  public void f(int paramInt1, int paramInt2)
    throws IOException
  {
    h(paramInt1, 0);
    cB(paramInt2);
  }
  
  public void h(int paramInt1, int paramInt2)
    throws IOException
  {
    cF(kh.i(paramInt1, paramInt2));
  }
  
  public int kM()
  {
    return this.aad - this.position;
  }
  
  public void kN()
  {
    if (kM() != 0) {
      throw new IllegalStateException("Did not write as much data as expected.");
    }
  }
  
  public void p(byte[] paramArrayOfByte)
    throws IOException
  {
    c(paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  public void x(long paramLong)
    throws IOException
  {
    B(paramLong);
  }
  
  public void y(long paramLong)
    throws IOException
  {
    B(D(paramLong));
  }
  
  public static class a
    extends IOException
  {
    a(int paramInt1, int paramInt2)
    {
      super();
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\jz.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */