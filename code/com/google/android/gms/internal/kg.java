package com.google.android.gms.internal;

import java.util.Arrays;

public final class kg
{
  final byte[] aai;
  final int tag;
  
  kg(int paramInt, byte[] paramArrayOfByte)
  {
    this.tag = paramInt;
    this.aai = paramArrayOfByte;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == this) {}
    do
    {
      return true;
      if (!(paramObject instanceof kg)) {
        return false;
      }
      paramObject = (kg)paramObject;
    } while ((this.tag == ((kg)paramObject).tag) && (Arrays.equals(this.aai, ((kg)paramObject).aai)));
    return false;
  }
  
  public int hashCode()
  {
    return (this.tag + 527) * 31 + Arrays.hashCode(this.aai);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\kg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */