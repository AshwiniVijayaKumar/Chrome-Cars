package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class jv
  implements SafeParcelable
{
  public static final Parcelable.Creator<jv> CREATOR = new jw();
  String ZK;
  jr ZO;
  jt ZP;
  jt ZQ;
  String oi;
  private final int wj;
  
  jv()
  {
    this.wj = 1;
  }
  
  jv(int paramInt, String paramString1, String paramString2, jr paramjr, jt paramjt1, jt paramjt2)
  {
    this.wj = paramInt;
    this.ZK = paramString1;
    this.oi = paramString2;
    this.ZO = paramjr;
    this.ZP = paramjt1;
    this.ZQ = paramjt2;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public int getVersionCode()
  {
    return this.wj;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    jw.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\jv.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */