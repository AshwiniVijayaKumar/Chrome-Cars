package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.games.multiplayer.InvitationEntity;
import java.util.ArrayList;

public class gx
  implements Parcelable.Creator<gy>
{
  static void a(gy paramgy, Parcel paramParcel, int paramInt)
  {
    paramInt = b.p(paramParcel);
    b.b(paramParcel, 1, paramgy.fS(), false);
    b.c(paramParcel, 1000, paramgy.getVersionCode());
    b.D(paramParcel, paramInt);
  }
  
  public gy[] aX(int paramInt)
  {
    return new gy[paramInt];
  }
  
  public gy al(Parcel paramParcel)
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
        localArrayList = a.c(paramParcel, k, InvitationEntity.CREATOR);
        break;
      case 1000: 
        i = a.g(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new gy(i, localArrayList);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\gx.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */