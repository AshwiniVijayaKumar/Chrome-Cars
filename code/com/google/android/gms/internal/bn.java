package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class bn
  implements SafeParcelable
{
  public static final bm CREATOR = new bm();
  public final String mY;
  public final String mZ;
  public final String mimeType;
  public final String na;
  public final String nb;
  public final String nc;
  public final String packageName;
  public final int versionCode;
  
  public bn(int paramInt, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7)
  {
    this.versionCode = paramInt;
    this.mY = paramString1;
    this.mZ = paramString2;
    this.mimeType = paramString3;
    this.packageName = paramString4;
    this.na = paramString5;
    this.nb = paramString6;
    this.nc = paramString7;
  }
  
  public bn(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7)
  {
    this(1, paramString1, paramString2, paramString3, paramString4, paramString5, paramString6, paramString7);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    bm.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\bn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */