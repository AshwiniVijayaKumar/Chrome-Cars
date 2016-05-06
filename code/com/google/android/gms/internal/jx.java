package com.google.android.gms.internal;

public class jx
{
  private final byte[] ZR = new byte['Ā'];
  private int ZS;
  private int ZT;
  
  public jx(byte[] paramArrayOfByte)
  {
    int j = 0;
    while (j < 256)
    {
      this.ZR[j] = ((byte)j);
      j += 1;
    }
    int k = 0;
    j = 0;
    while (j < 256)
    {
      k = k + this.ZR[j] + paramArrayOfByte[(j % paramArrayOfByte.length)] & 0xFF;
      int i = this.ZR[j];
      this.ZR[j] = this.ZR[k];
      this.ZR[k] = i;
      j += 1;
    }
    this.ZS = 0;
    this.ZT = 0;
  }
  
  public void m(byte[] paramArrayOfByte)
  {
    int m = this.ZS;
    int k = this.ZT;
    int j = 0;
    while (j < paramArrayOfByte.length)
    {
      m = m + 1 & 0xFF;
      k = k + this.ZR[m] & 0xFF;
      int i = this.ZR[m];
      this.ZR[m] = this.ZR[k];
      this.ZR[k] = i;
      paramArrayOfByte[j] = ((byte)(paramArrayOfByte[j] ^ this.ZR[(this.ZR[m] + this.ZR[k] & 0xFF)]));
      j += 1;
    }
    this.ZS = m;
    this.ZT = k;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\jx.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */