package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.games.request.GameRequestEntity;
import java.util.ArrayList;

public class ha
  implements Parcelable.Creator<gz>
{
  static void a(gz paramgz, Parcel paramParcel, int paramInt)
  {
    paramInt = b.p(paramParcel);
    b.b(paramParcel, 1, paramgz.fT(), false);
    b.c(paramParcel, 1000, paramgz.getVersionCode());
    b.D(paramParcel, paramInt);
  }
  
  public gz[] aY(int paramInt)
  {
    return new gz[paramInt];
  }
  
  public gz am(Parcel paramParcel)
  {
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
        localArrayList = a.c(paramParcel, k, GameRequestEntity.CREATOR);
        break;
      case 1000: 
        i = a.g(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new gz(i, localArrayList);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\ha.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */