package com.google.android.gms.drive.internal;

import com.google.android.gms.internal.jy;
import com.google.android.gms.internal.jz;
import com.google.android.gms.internal.kd;
import com.google.android.gms.internal.ke;
import com.google.android.gms.internal.kh;
import java.io.IOException;

public final class y
  extends ke
{
  public static final y[] DU = new y[0];
  public String DV = "";
  public long DW = -1L;
  public long DX = -1L;
  private int DY = -1;
  public int versionCode = 1;
  
  public static y g(byte[] paramArrayOfByte)
    throws kd
  {
    return (y)ke.a(new y(), paramArrayOfByte);
  }
  
  public void a(jz paramjz)
    throws IOException
  {
    paramjz.f(1, this.versionCode);
    paramjz.b(2, this.DV);
    paramjz.c(3, this.DW);
    paramjz.c(4, this.DX);
  }
  
  public int c()
  {
    int i = 0 + jz.g(1, this.versionCode) + jz.g(2, this.DV) + jz.e(3, this.DW) + jz.e(4, this.DX);
    this.DY = i;
    return i;
  }
  
  public int eW()
  {
    if (this.DY < 0) {
      c();
    }
    return this.DY;
  }
  
  public y m(jy paramjy)
    throws IOException
  {
    for (;;)
    {
      int i = paramjy.ky();
      switch (i)
      {
      default: 
        if (kh.b(paramjy, i)) {}
        break;
      case 0: 
        return this;
      case 8: 
        this.versionCode = paramjy.kB();
        break;
      case 18: 
        this.DV = paramjy.readString();
        break;
      case 24: 
        this.DW = paramjy.kD();
        break;
      case 32: 
        this.DX = paramjy.kD();
      }
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\drive\internal\y.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */