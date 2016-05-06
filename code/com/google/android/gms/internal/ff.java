package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import java.util.ArrayList;

public class ff
  implements Parcelable.Creator<fe>
{
  static void a(fe paramfe, Parcel paramParcel, int paramInt)
  {
    paramInt = b.p(paramParcel);
    b.c(paramParcel, 1, paramfe.getVersionCode());
    b.b(paramParcel, 2, paramfe.eC(), false);
    b.a(paramParcel, 3, paramfe.eD(), false);
    b.D(paramParcel, paramInt);
  }
  
  public fe[] Y(int paramInt)
  {
    return new fe[paramInt];
  }
  
  public fe v(Parcel paramParcel)
  {
    String str = null;
    int j = a.o(paramParcel);
    int i = 0;
    ArrayList localArrayList = null;
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
        localArrayList = a.c(paramParcel, k, fe.a.CREATOR);
        break;
      case 3: 
        str = a.m(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new fe(i, localArrayList, str);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\ff.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */