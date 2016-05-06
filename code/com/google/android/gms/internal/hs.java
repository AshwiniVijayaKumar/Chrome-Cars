package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.location.LocationRequest;

public class hs
  implements Parcelable.Creator<hr>
{
  static void a(hr paramhr, Parcel paramParcel, int paramInt)
  {
    int i = b.p(paramParcel);
    b.a(paramParcel, 1, paramhr.gu(), paramInt, false);
    b.c(paramParcel, 1000, paramhr.wj);
    b.a(paramParcel, 2, paramhr.gv(), paramInt, false);
    b.D(paramParcel, i);
  }
  
  public hr ay(Parcel paramParcel)
  {
    hn localhn = null;
    int j = a.o(paramParcel);
    int i = 0;
    LocationRequest localLocationRequest = null;
    if (paramParcel.dataPosition() < j)
    {
      int k = a.n(paramParcel);
      switch (a.S(k))
      {
      default: 
        a.b(paramParcel, k);
      }
      for (;;)
      {
        break;
        localLocationRequest = (LocationRequest)a.a(paramParcel, k, LocationRequest.CREATOR);
        continue;
        i = a.g(paramParcel, k);
        continue;
        localhn = (hn)a.a(paramParcel, k, hn.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new hr(i, localLocationRequest, localhn);
  }
  
  public hr[] bs(int paramInt)
  {
    return new hr[paramInt];
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\hs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */