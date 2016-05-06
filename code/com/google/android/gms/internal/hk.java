package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class hk
  implements Parcelable.Creator<hj>
{
  static void a(hj paramhj, Parcel paramParcel, int paramInt)
  {
    paramInt = b.p(paramParcel);
    b.a(paramParcel, 1, paramhj.getRequestId(), false);
    b.c(paramParcel, 1000, paramhj.getVersionCode());
    b.a(paramParcel, 2, paramhj.getExpirationTime());
    b.a(paramParcel, 3, paramhj.gn());
    b.a(paramParcel, 4, paramhj.getLatitude());
    b.a(paramParcel, 5, paramhj.getLongitude());
    b.a(paramParcel, 6, paramhj.go());
    b.c(paramParcel, 7, paramhj.gp());
    b.c(paramParcel, 8, paramhj.getNotificationResponsiveness());
    b.c(paramParcel, 9, paramhj.gq());
    b.D(paramParcel, paramInt);
  }
  
  public hj av(Parcel paramParcel)
  {
    int n = a.o(paramParcel);
    int m = 0;
    String str = null;
    int k = 0;
    short s = 0;
    double d2 = 0.0D;
    double d1 = 0.0D;
    float f = 0.0F;
    long l = 0L;
    int j = 0;
    int i = -1;
    while (paramParcel.dataPosition() < n)
    {
      int i1 = a.n(paramParcel);
      switch (a.S(i1))
      {
      default: 
        a.b(paramParcel, i1);
        break;
      case 1: 
        str = a.m(paramParcel, i1);
        break;
      case 1000: 
        m = a.g(paramParcel, i1);
        break;
      case 2: 
        l = a.h(paramParcel, i1);
        break;
      case 3: 
        s = a.f(paramParcel, i1);
        break;
      case 4: 
        d2 = a.k(paramParcel, i1);
        break;
      case 5: 
        d1 = a.k(paramParcel, i1);
        break;
      case 6: 
        f = a.j(paramParcel, i1);
        break;
      case 7: 
        k = a.g(paramParcel, i1);
        break;
      case 8: 
        j = a.g(paramParcel, i1);
        break;
      case 9: 
        i = a.g(paramParcel, i1);
      }
    }
    if (paramParcel.dataPosition() != n) {
      throw new a.a("Overread allowed size end=" + n, paramParcel);
    }
    return new hj(m, str, k, s, d2, d1, f, l, j, i);
  }
  
  public hj[] bp(int paramInt)
  {
    return new hj[paramInt];
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\hk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */