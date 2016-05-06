package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;

public final class jj
  implements SafeParcelable
{
  public static final Parcelable.Creator<jj> CREATOR = new jk();
  String ZA;
  String ZB;
  ArrayList<jh> ZC;
  private final int wj;
  
  jj()
  {
    this.wj = 1;
    this.ZC = fj.eH();
  }
  
  jj(int paramInt, String paramString1, String paramString2, ArrayList<jh> paramArrayList)
  {
    this.wj = paramInt;
    this.ZA = paramString1;
    this.ZB = paramString2;
    this.ZC = paramArrayList;
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
    jk.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\jj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */