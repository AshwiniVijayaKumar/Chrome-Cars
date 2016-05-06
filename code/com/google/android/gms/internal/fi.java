package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class fi
  implements Parcelable.Creator<fh>
{
  static void a(fh paramfh, Parcel paramParcel, int paramInt)
  {
    int i = b.p(paramParcel);
    b.c(paramParcel, 1, paramfh.getVersionCode());
    b.a(paramParcel, 2, paramfh.eF(), false);
    b.a(paramParcel, 3, paramfh.eG(), paramInt, false);
    b.D(paramParcel, i);
  }
  
  public fh[] aa(int paramInt)
  {
    return new fh[paramInt];
  }
  
  public fh x(Parcel paramParcel)
  {
    fe localfe = null;
    int j = a.o(paramParcel);
    int i = 0;
    Parcel localParcel = null;
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
        localParcel = a.z(paramParcel, k);
        break;
      case 3: 
        localfe = (fe)a.a(paramParcel, k, fe.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new fh(i, localParcel, localfe);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\fi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */