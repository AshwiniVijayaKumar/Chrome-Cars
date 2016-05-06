package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class jr
  implements SafeParcelable
{
  public static final Parcelable.Creator<jr> CREATOR = new js();
  long ZL;
  long ZM;
  private final int wj;
  
  jr()
  {
    this.wj = 1;
  }
  
  jr(int paramInt, long paramLong1, long paramLong2)
  {
    this.wj = paramInt;
    this.ZL = paramLong1;
    this.ZM = paramLong2;
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
    js.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\jr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */