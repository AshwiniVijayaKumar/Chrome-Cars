package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import java.util.ArrayList;

public class jk
  implements Parcelable.Creator<jj>
{
  static void a(jj paramjj, Parcel paramParcel, int paramInt)
  {
    paramInt = b.p(paramParcel);
    b.c(paramParcel, 1, paramjj.getVersionCode());
    b.a(paramParcel, 2, paramjj.ZA, false);
    b.a(paramParcel, 3, paramjj.ZB, false);
    b.b(paramParcel, 4, paramjj.ZC, false);
    b.D(paramParcel, paramInt);
  }
  
  public jj bh(Parcel paramParcel)
  {
    String str2 = null;
    int j = a.o(paramParcel);
    int i = 0;
    ArrayList localArrayList = fj.eH();
    String str1 = null;
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
        str1 = a.m(paramParcel, k);
        break;
      case 3: 
        str2 = a.m(paramParcel, k);
        break;
      case 4: 
        localArrayList = a.c(paramParcel, k, jh.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new jj(i, str1, str2, localArrayList);
  }
  
  public jj[] cn(int paramInt)
  {
    return new jj[paramInt];
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\jk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */