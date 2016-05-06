package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import java.util.ArrayList;

public class ho
  implements Parcelable.Creator<hn>
{
  static void a(hn paramhn, Parcel paramParcel, int paramInt)
  {
    paramInt = b.p(paramParcel);
    b.b(paramParcel, 1, paramhn.LA, false);
    b.c(paramParcel, 1000, paramhn.wj);
    b.a(paramParcel, 2, paramhn.gr(), false);
    b.a(paramParcel, 3, paramhn.gs());
    b.D(paramParcel, paramInt);
  }
  
  public hn aw(Parcel paramParcel)
  {
    String str = null;
    boolean bool = false;
    int j = a.o(paramParcel);
    ArrayList localArrayList = null;
    int i = 0;
    while (paramParcel.dataPosition() < j)
    {
      int k = a.n(paramParcel);
      switch (a.S(k))
      {
      default: 
        a.b(paramParcel, k);
        break;
      case 1: 
        localArrayList = a.c(paramParcel, k, ht.CREATOR);
        break;
      case 1000: 
        i = a.g(paramParcel, k);
        break;
      case 2: 
        str = a.m(paramParcel, k);
        break;
      case 3: 
        bool = a.c(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new hn(i, localArrayList, str, bool);
  }
  
  public hn[] bq(int paramInt)
  {
    return new hn[paramInt];
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\ho.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */