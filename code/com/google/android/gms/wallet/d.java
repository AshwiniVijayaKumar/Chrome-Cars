package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class d
  implements SafeParcelable
{
  public static final Parcelable.Creator<d> CREATOR = new e();
  LoyaltyWalletObject Yj;
  private final int wj;
  
  d()
  {
    this.wj = 1;
  }
  
  d(int paramInt, LoyaltyWalletObject paramLoyaltyWalletObject)
  {
    this.wj = paramInt;
    this.Yj = paramLoyaltyWalletObject;
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
    e.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\wallet\d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */