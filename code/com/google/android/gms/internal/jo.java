package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class jo
  implements Parcelable.Creator<jl>
{
  static void a(jl paramjl, Parcel paramParcel, int paramInt)
  {
    int i = b.p(paramParcel);
    b.c(paramParcel, 1, paramjl.getVersionCode());
    b.a(paramParcel, 2, paramjl.label, false);
    b.a(paramParcel, 3, paramjl.ZD, paramInt, false);
    b.a(paramParcel, 4, paramjl.type, false);
    b.a(paramParcel, 5, paramjl.YM, paramInt, false);
    b.D(paramParcel, i);
  }
  
  public jl bj(Parcel paramParcel)
  {
    jr localjr = null;
    int j = a.o(paramParcel);
    int i = 0;
    String str1 = null;
    jm localjm = null;
    String str2 = null;
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
        str2 = a.m(paramParcel, k);
        break;
      case 3: 
        localjm = (jm)a.a(paramParcel, k, jm.CREATOR);
        break;
      case 4: 
        str1 = a.m(paramParcel, k);
        break;
      case 5: 
        localjr = (jr)a.a(paramParcel, k, jr.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new jl(i, str2, localjm, str1, localjr);
  }
  
  public jl[] cp(int paramInt)
  {
    return new jl[paramInt];
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\jo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */