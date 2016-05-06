package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import java.util.ArrayList;

public class ia
  implements Parcelable.Creator<hz>
{
  static void a(hz paramhz, Parcel paramParcel, int paramInt)
  {
    paramInt = b.p(paramParcel);
    b.a(paramParcel, 1, paramhz.name, false);
    b.c(paramParcel, 1000, paramhz.versionCode);
    b.a(paramParcel, 2, paramhz.Ov, false);
    b.a(paramParcel, 3, paramhz.Ow, false);
    b.a(paramParcel, 4, paramhz.Ox, false);
    b.a(paramParcel, 5, paramhz.Oy, false);
    b.D(paramParcel, paramInt);
  }
  
  public hz aC(Parcel paramParcel)
  {
    ArrayList localArrayList = null;
    int j = a.o(paramParcel);
    int i = 0;
    String str1 = null;
    String str2 = null;
    String str3 = null;
    String str4 = null;
    while (paramParcel.dataPosition() < j)
    {
      int k = a.n(paramParcel);
      switch (a.S(k))
      {
      default: 
        a.b(paramParcel, k);
        break;
      case 1: 
        str4 = a.m(paramParcel, k);
        break;
      case 1000: 
        i = a.g(paramParcel, k);
        break;
      case 2: 
        str3 = a.m(paramParcel, k);
        break;
      case 3: 
        str2 = a.m(paramParcel, k);
        break;
      case 4: 
        str1 = a.m(paramParcel, k);
        break;
      case 5: 
        localArrayList = a.y(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new hz(i, str4, str3, str2, str1, localArrayList);
  }
  
  public hz[] bx(int paramInt)
  {
    return new hz[paramInt];
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\ia.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */