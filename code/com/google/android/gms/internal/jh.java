package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class jh
  implements SafeParcelable
{
  public static final Parcelable.Creator<jh> CREATOR = new ji();
  String label;
  String value;
  private final int wj;
  
  jh()
  {
    this.wj = 1;
  }
  
  jh(int paramInt, String paramString1, String paramString2)
  {
    this.wj = paramInt;
    this.label = paramString1;
    this.value = paramString2;
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
    ji.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\jh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */