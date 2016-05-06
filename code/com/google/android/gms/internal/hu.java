package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class hu
  implements Parcelable.Creator<ht>
{
  static void a(ht paramht, Parcel paramParcel, int paramInt)
  {
    paramInt = b.p(paramParcel);
    b.a(paramParcel, 1, paramht.Oc, false);
    b.c(paramParcel, 1000, paramht.wj);
    b.D(paramParcel, paramInt);
  }
  
  public ht az(Parcel paramParcel)
  {
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
        str = a.m(paramParcel, k);
        break;
      case 1000: 
        i = a.g(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new ht(i, str);
  }
  
  public ht[] bt(int paramInt)
  {
    return new ht[paramInt];
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\hu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */