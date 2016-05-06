package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.location.Geofence;
import java.util.Locale;

public class hj
  implements SafeParcelable, Geofence
{
  public static final hk CREATOR = new hk();
  private final String Hh;
  private final int KU;
  private final short KW;
  private final double KX;
  private final double KY;
  private final float KZ;
  private final int La;
  private final int Lb;
  private final long Lz;
  private final int wj;
  
  public hj(int paramInt1, String paramString, int paramInt2, short paramShort, double paramDouble1, double paramDouble2, float paramFloat, long paramLong, int paramInt3, int paramInt4)
  {
    aH(paramString);
    b(paramFloat);
    a(paramDouble1, paramDouble2);
    paramInt2 = bn(paramInt2);
    this.wj = paramInt1;
    this.KW = paramShort;
    this.Hh = paramString;
    this.KX = paramDouble1;
    this.KY = paramDouble2;
    this.KZ = paramFloat;
    this.Lz = paramLong;
    this.KU = paramInt2;
    this.La = paramInt3;
    this.Lb = paramInt4;
  }
  
  public hj(String paramString, int paramInt1, short paramShort, double paramDouble1, double paramDouble2, float paramFloat, long paramLong, int paramInt2, int paramInt3)
  {
    this(1, paramString, paramInt1, paramShort, paramDouble1, paramDouble2, paramFloat, paramLong, paramInt2, paramInt3);
  }
  
  private static void a(double paramDouble1, double paramDouble2)
  {
    if ((paramDouble1 > 90.0D) || (paramDouble1 < -90.0D)) {
      throw new IllegalArgumentException("invalid latitude: " + paramDouble1);
    }
    if ((paramDouble2 > 180.0D) || (paramDouble2 < -180.0D)) {
      throw new IllegalArgumentException("invalid longitude: " + paramDouble2);
    }
  }
  
  private static void aH(String paramString)
  {
    if ((paramString == null) || (paramString.length() > 100)) {
      throw new IllegalArgumentException("requestId is null or too long: " + paramString);
    }
  }
  
  private static void b(float paramFloat)
  {
    if (paramFloat <= 0.0F) {
      throw new IllegalArgumentException("invalid radius: " + paramFloat);
    }
  }
  
  private static int bn(int paramInt)
  {
    int i = paramInt & 0x7;
    if (i == 0) {
      throw new IllegalArgumentException("No supported transition specified: " + paramInt);
    }
    return i;
  }
  
  private static String bo(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return null;
    }
    return "CIRCLE";
  }
  
  public static hj h(byte[] paramArrayOfByte)
  {
    Parcel localParcel = Parcel.obtain();
    localParcel.unmarshall(paramArrayOfByte, 0, paramArrayOfByte.length);
    localParcel.setDataPosition(0);
    paramArrayOfByte = CREATOR.av(localParcel);
    localParcel.recycle();
    return paramArrayOfByte;
  }
  
  public int describeContents()
  {
    hk localhk = CREATOR;
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (paramObject == null) {
        return false;
      }
      if (!(paramObject instanceof hj)) {
        return false;
      }
      paramObject = (hj)paramObject;
      if (this.KZ != ((hj)paramObject).KZ) {
        return false;
      }
      if (this.KX != ((hj)paramObject).KX) {
        return false;
      }
      if (this.KY != ((hj)paramObject).KY) {
        return false;
      }
    } while (this.KW == ((hj)paramObject).KW);
    return false;
  }
  
  public long getExpirationTime()
  {
    return this.Lz;
  }
  
  public double getLatitude()
  {
    return this.KX;
  }
  
  public double getLongitude()
  {
    return this.KY;
  }
  
  public int getNotificationResponsiveness()
  {
    return this.La;
  }
  
  public String getRequestId()
  {
    return this.Hh;
  }
  
  public int getVersionCode()
  {
    return this.wj;
  }
  
  public short gn()
  {
    return this.KW;
  }
  
  public float go()
  {
    return this.KZ;
  }
  
  public int gp()
  {
    return this.KU;
  }
  
  public int gq()
  {
    return this.Lb;
  }
  
  public int hashCode()
  {
    long l = Double.doubleToLongBits(this.KX);
    int i = (int)(l ^ l >>> 32);
    l = Double.doubleToLongBits(this.KY);
    return ((((i + 31) * 31 + (int)(l ^ l >>> 32)) * 31 + Float.floatToIntBits(this.KZ)) * 31 + this.KW) * 31 + this.KU;
  }
  
  public String toString()
  {
    return String.format(Locale.US, "Geofence[%s id:%s transitions:%d %.6f, %.6f %.0fm, resp=%ds, dwell=%dms, @%d]", new Object[] { bo(this.KW), this.Hh, Integer.valueOf(this.KU), Double.valueOf(this.KX), Double.valueOf(this.KY), Float.valueOf(this.KZ), Integer.valueOf(this.La / 1000), Integer.valueOf(this.Lb), Long.valueOf(this.Lz) });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    hk localhk = CREATOR;
    hk.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\hj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */