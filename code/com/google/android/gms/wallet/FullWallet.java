package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.identity.intents.model.UserAddress;

public final class FullWallet
  implements SafeParcelable
{
  public static final Parcelable.Creator<FullWallet> CREATOR = new f();
  String Yk;
  String Yl;
  ProxyCard Ym;
  String Yn;
  Address Yo;
  Address Yp;
  String[] Yq;
  UserAddress Yr;
  UserAddress Ys;
  InstrumentInfo[] Yt;
  private final int wj;
  
  private FullWallet()
  {
    this.wj = 1;
  }
  
  FullWallet(int paramInt, String paramString1, String paramString2, ProxyCard paramProxyCard, String paramString3, Address paramAddress1, Address paramAddress2, String[] paramArrayOfString, UserAddress paramUserAddress1, UserAddress paramUserAddress2, InstrumentInfo[] paramArrayOfInstrumentInfo)
  {
    this.wj = paramInt;
    this.Yk = paramString1;
    this.Yl = paramString2;
    this.Ym = paramProxyCard;
    this.Yn = paramString3;
    this.Yo = paramAddress1;
    this.Yp = paramAddress2;
    this.Yq = paramArrayOfString;
    this.Yr = paramUserAddress1;
    this.Ys = paramUserAddress2;
    this.Yt = paramArrayOfInstrumentInfo;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  @Deprecated
  public Address getBillingAddress()
  {
    return this.Yo;
  }
  
  public UserAddress getBuyerBillingAddress()
  {
    return this.Yr;
  }
  
  public UserAddress getBuyerShippingAddress()
  {
    return this.Ys;
  }
  
  public String getEmail()
  {
    return this.Yn;
  }
  
  public String getGoogleTransactionId()
  {
    return this.Yk;
  }
  
  public InstrumentInfo[] getInstrumentInfos()
  {
    return this.Yt;
  }
  
  public String getMerchantTransactionId()
  {
    return this.Yl;
  }
  
  public String[] getPaymentDescriptions()
  {
    return this.Yq;
  }
  
  public ProxyCard getProxyCard()
  {
    return this.Ym;
  }
  
  @Deprecated
  public Address getShippingAddress()
  {
    return this.Yp;
  }
  
  public int getVersionCode()
  {
    return this.wj;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    f.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\wallet\FullWallet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */