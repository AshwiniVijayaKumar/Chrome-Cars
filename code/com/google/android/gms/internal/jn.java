package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class jn
  implements Parcelable.Creator<jm>
{
  static void a(jm paramjm, Parcel paramParcel, int paramInt)
  {
    paramInt = b.p(paramParcel);
    b.c(paramParcel, 1, paramjm.getVersionCode());
    b.c(paramParcel, 2, paramjm.ZE);
    b.a(paramParcel, 3, paramjm.ZF, false);
    b.a(paramParcel, 4, paramjm.ZG);
    b.a(paramParcel, 5, paramjm.ZH, false);
    b.a(paramParcel, 6, paramjm.ZI);
    b.c(paramParcel, 7, paramjm.ZJ);
    b.D(paramParcel, paramInt);
  }
  
  public jm bi(Parcel paramParcel)
  {
    String str1 = null;
    int j = 0;
    int m = a.o(paramParcel);
    double d = 0.0D;
    long l = 0L;
    int i = -1;
    String str2 = null;
    int k = 0;
    while (paramParcel.dataPosition() < m)
    {
      int n = a.n(paramParcel);
      switch (a.S(n))
      {
      default: 
        a.b(paramParcel, n);
        break;
      case 1: 
        k = a.g(paramParcel, n);
        break;
      case 2: 
        j = a.g(paramParcel, n);
        break;
      case 3: 
        str2 = a.m(paramParcel, n);
        break;
      case 4: 
        d = a.k(paramParcel, n);
        break;
      case 5: 
        str1 = a.m(paramParcel, n);
        break;
      case 6: 
        l = a.h(paramParcel, n);
        break;
      case 7: 
        i = a.g(paramParcel, n);
      }
    }
    if (paramParcel.dataPosition() != m) {
      throw new a.a("Overread allowed size end=" + m, paramParcel);
    }
    return new jm(k, j, str2, d, str1, l, i);
  }
  
  public jm[] co(int paramInt)
  {
    return new jm[paramInt];
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\jn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */