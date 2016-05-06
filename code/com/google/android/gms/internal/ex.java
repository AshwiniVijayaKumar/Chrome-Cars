package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class ex
  implements Parcelable.Creator<ew>
{
  static void a(ew paramew, Parcel paramParcel, int paramInt)
  {
    int i = b.p(paramParcel);
    b.c(paramParcel, 1, paramew.getVersionCode());
    b.a(paramParcel, 2, paramew.ei(), paramInt, false);
    b.D(paramParcel, i);
  }
  
  public ew[] T(int paramInt)
  {
    return new ew[paramInt];
  }
  
  public ew q(Parcel paramParcel)
  {
    int j = a.o(paramParcel);
    int i = 0;
    ey localey = null;
    while (paramParcel.dataPosition() < j)
    {
      int k = a.n(paramParcel);
      switch (a.S(k))
      {
      default: 
        a.b(paramParcel, k);
        break;
      case 1: 
        i = a.g(paramParcel, k);
        break;
      case 2: 
        localey = (ey)a.a(paramParcel, k, ey.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new ew(i, localey);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\ex.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */