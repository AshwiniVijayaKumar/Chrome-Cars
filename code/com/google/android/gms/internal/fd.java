package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class fd
  implements Parcelable.Creator<fe.b>
{
  static void a(fe.b paramb, Parcel paramParcel, int paramInt)
  {
    int i = b.p(paramParcel);
    b.c(paramParcel, 1, paramb.versionCode);
    b.a(paramParcel, 2, paramb.eX, false);
    b.a(paramParcel, 3, paramb.CI, paramInt, false);
    b.D(paramParcel, i);
  }
  
  public fe.b[] X(int paramInt)
  {
    return new fe.b[paramInt];
  }
  
  public fe.b u(Parcel paramParcel)
  {
    fb.a locala = null;
    int j = a.o(paramParcel);
    int i = 0;
    String str = null;
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
        str = a.m(paramParcel, k);
        break;
      case 3: 
        locala = (fb.a)a.a(paramParcel, k, fb.a.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new fe.b(i, str, locala);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\fd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */