package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class js
  implements Parcelable.Creator<jr>
{
  static void a(jr paramjr, Parcel paramParcel, int paramInt)
  {
    paramInt = b.p(paramParcel);
    b.c(paramParcel, 1, paramjr.getVersionCode());
    b.a(paramParcel, 2, paramjr.ZL);
    b.a(paramParcel, 3, paramjr.ZM);
    b.D(paramParcel, paramInt);
  }
  
  public jr bl(Parcel paramParcel)
  {
    long l1 = 0L;
    int j = a.o(paramParcel);
    int i = 0;
    long l2 = 0L;
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
        l2 = a.h(paramParcel, k);
        break;
      case 3: 
        l1 = a.h(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new jr(i, l2, l1);
  }
  
  public jr[] cr(int paramInt)
  {
    return new jr[paramInt];
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\js.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */