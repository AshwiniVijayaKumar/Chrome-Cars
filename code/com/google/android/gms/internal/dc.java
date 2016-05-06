package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class dc
  implements Parcelable.Creator<db>
{
  static void a(db paramdb, Parcel paramParcel, int paramInt)
  {
    paramInt = b.p(paramParcel);
    b.c(paramParcel, 1, paramdb.versionCode);
    b.a(paramParcel, 2, paramdb.pU, false);
    b.c(paramParcel, 3, paramdb.pV);
    b.c(paramParcel, 4, paramdb.pW);
    b.a(paramParcel, 5, paramdb.pX);
    b.D(paramParcel, paramInt);
  }
  
  public db h(Parcel paramParcel)
  {
    boolean bool = false;
    int m = a.o(paramParcel);
    String str = null;
    int i = 0;
    int j = 0;
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
        str = a.m(paramParcel, n);
        break;
      case 3: 
        j = a.g(paramParcel, n);
        break;
      case 4: 
        i = a.g(paramParcel, n);
        break;
      case 5: 
        bool = a.c(paramParcel, n);
      }
    }
    if (paramParcel.dataPosition() != m) {
      throw new a.a("Overread allowed size end=" + m, paramParcel);
    }
    return new db(k, str, j, i, bool);
  }
  
  public db[] o(int paramInt)
  {
    return new db[paramInt];
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\dc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */