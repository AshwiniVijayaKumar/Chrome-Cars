package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import java.util.ArrayList;

public class l
  implements Parcelable.Creator<MaskedWalletRequest>
{
  static void a(MaskedWalletRequest paramMaskedWalletRequest, Parcel paramParcel, int paramInt)
  {
    int i = b.p(paramParcel);
    b.c(paramParcel, 1, paramMaskedWalletRequest.getVersionCode());
    b.a(paramParcel, 2, paramMaskedWalletRequest.Yl, false);
    b.a(paramParcel, 3, paramMaskedWalletRequest.YY);
    b.a(paramParcel, 4, paramMaskedWalletRequest.YZ);
    b.a(paramParcel, 5, paramMaskedWalletRequest.Za);
    b.a(paramParcel, 6, paramMaskedWalletRequest.Zb, false);
    b.a(paramParcel, 7, paramMaskedWalletRequest.Yg, false);
    b.a(paramParcel, 8, paramMaskedWalletRequest.Zc, false);
    b.a(paramParcel, 9, paramMaskedWalletRequest.Yu, paramInt, false);
    b.a(paramParcel, 10, paramMaskedWalletRequest.Zd);
    b.a(paramParcel, 11, paramMaskedWalletRequest.Ze);
    b.a(paramParcel, 12, paramMaskedWalletRequest.Zf, paramInt, false);
    b.a(paramParcel, 13, paramMaskedWalletRequest.Zg);
    b.a(paramParcel, 14, paramMaskedWalletRequest.Zh);
    b.b(paramParcel, 15, paramMaskedWalletRequest.Zi, false);
    b.D(paramParcel, i);
  }
  
  public MaskedWalletRequest bc(Parcel paramParcel)
  {
    int j = a.o(paramParcel);
    int i = 0;
    String str4 = null;
    boolean bool7 = false;
    boolean bool6 = false;
    boolean bool5 = false;
    String str3 = null;
    String str2 = null;
    String str1 = null;
    Cart localCart = null;
    boolean bool4 = false;
    boolean bool3 = false;
    CountrySpecification[] arrayOfCountrySpecification = null;
    boolean bool2 = true;
    boolean bool1 = true;
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
        str4 = a.m(paramParcel, k);
        break;
      case 3: 
        bool7 = a.c(paramParcel, k);
        break;
      case 4: 
        bool6 = a.c(paramParcel, k);
        break;
      case 5: 
        bool5 = a.c(paramParcel, k);
        break;
      case 6: 
        str3 = a.m(paramParcel, k);
        break;
      case 7: 
        str2 = a.m(paramParcel, k);
        break;
      case 8: 
        str1 = a.m(paramParcel, k);
        break;
      case 9: 
        localCart = (Cart)a.a(paramParcel, k, Cart.CREATOR);
        break;
      case 10: 
        bool4 = a.c(paramParcel, k);
        break;
      case 11: 
        bool3 = a.c(paramParcel, k);
        break;
      case 12: 
        arrayOfCountrySpecification = (CountrySpecification[])a.b(paramParcel, k, CountrySpecification.CREATOR);
        break;
      case 13: 
        bool2 = a.c(paramParcel, k);
        break;
      case 14: 
        bool1 = a.c(paramParcel, k);
        break;
      case 15: 
        localArrayList = a.c(paramParcel, k, com.google.android.gms.identity.intents.model.CountrySpecification.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new MaskedWalletRequest(i, str4, bool7, bool6, bool5, str3, str2, str1, localCart, bool4, bool3, arrayOfCountrySpecification, bool2, bool1, localArrayList);
  }
  
  public MaskedWalletRequest[] ci(int paramInt)
  {
    return new MaskedWalletRequest[paramInt];
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\wallet\l.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */