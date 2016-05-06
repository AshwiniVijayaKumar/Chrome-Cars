package com.google.android.gms.internal;

import android.location.Location;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import java.util.ArrayList;

public class aa
  implements Parcelable.Creator<z>
{
  static void a(z paramz, Parcel paramParcel, int paramInt)
  {
    int i = b.p(paramParcel);
    b.c(paramParcel, 1, paramz.versionCode);
    b.a(paramParcel, 2, paramz.le);
    b.a(paramParcel, 3, paramz.extras, false);
    b.c(paramParcel, 4, paramz.lf);
    b.a(paramParcel, 5, paramz.lg, false);
    b.a(paramParcel, 6, paramz.lh);
    b.c(paramParcel, 7, paramz.tagForChildDirectedTreatment);
    b.a(paramParcel, 8, paramz.li);
    b.a(paramParcel, 9, paramz.lj, false);
    b.a(paramParcel, 10, paramz.lk, paramInt, false);
    b.a(paramParcel, 11, paramz.ll, paramInt, false);
    b.a(paramParcel, 12, paramz.lm, false);
    b.D(paramParcel, i);
  }
  
  public z a(Parcel paramParcel)
  {
    int m = a.o(paramParcel);
    int k = 0;
    long l = 0L;
    Bundle localBundle = null;
    int j = 0;
    ArrayList localArrayList = null;
    boolean bool2 = false;
    int i = 0;
    boolean bool1 = false;
    String str2 = null;
    am localam = null;
    Location localLocation = null;
    String str1 = null;
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
        l = a.h(paramParcel, n);
        break;
      case 3: 
        localBundle = a.o(paramParcel, n);
        break;
      case 4: 
        j = a.g(paramParcel, n);
        break;
      case 5: 
        localArrayList = a.y(paramParcel, n);
        break;
      case 6: 
        bool2 = a.c(paramParcel, n);
        break;
      case 7: 
        i = a.g(paramParcel, n);
        break;
      case 8: 
        bool1 = a.c(paramParcel, n);
        break;
      case 9: 
        str2 = a.m(paramParcel, n);
        break;
      case 10: 
        localam = (am)a.a(paramParcel, n, am.CREATOR);
        break;
      case 11: 
        localLocation = (Location)a.a(paramParcel, n, Location.CREATOR);
        break;
      case 12: 
        str1 = a.m(paramParcel, n);
      }
    }
    if (paramParcel.dataPosition() != m) {
      throw new a.a("Overread allowed size end=" + m, paramParcel);
    }
    return new z(k, l, localBundle, j, localArrayList, bool2, i, bool1, str2, localam, localLocation, str1);
  }
  
  public z[] b(int paramInt)
  {
    return new z[paramInt];
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\aa.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */