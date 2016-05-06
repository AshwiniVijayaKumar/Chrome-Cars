package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import java.util.ArrayList;

public class eq
  implements Parcelable.Creator<ee.a>
{
  static void a(ee.a parama, Parcel paramParcel, int paramInt)
  {
    paramInt = b.p(paramParcel);
    b.a(paramParcel, 1, parama.getAccountName(), false);
    b.c(paramParcel, 1000, parama.getVersionCode());
    b.a(paramParcel, 2, parama.dT(), false);
    b.c(paramParcel, 3, parama.dS());
    b.a(paramParcel, 4, parama.dV(), false);
    b.D(paramParcel, paramInt);
  }
  
  public ee.a[] R(int paramInt)
  {
    return new ee.a[paramInt];
  }
  
  public ee.a m(Parcel paramParcel)
  {
    int i = 0;
    String str1 = null;
    int k = a.o(paramParcel);
    ArrayList localArrayList = null;
    String str2 = null;
    int j = 0;
    while (paramParcel.dataPosition() < k)
    {
      int m = a.n(paramParcel);
      switch (a.S(m))
      {
      default: 
        a.b(paramParcel, m);
        break;
      case 1: 
        str2 = a.m(paramParcel, m);
        break;
      case 1000: 
        j = a.g(paramParcel, m);
        break;
      case 2: 
        localArrayList = a.y(paramParcel, m);
        break;
      case 3: 
        i = a.g(paramParcel, m);
        break;
      case 4: 
        str1 = a.m(paramParcel, m);
      }
    }
    if (paramParcel.dataPosition() != k) {
      throw new a.a("Overread allowed size end=" + k, paramParcel);
    }
    return new ee.a(j, str2, localArrayList, i, str1);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\eq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */