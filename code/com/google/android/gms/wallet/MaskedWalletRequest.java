package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.Collection;

public final class MaskedWalletRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<MaskedWalletRequest> CREATOR = new l();
  boolean YY;
  boolean YZ;
  String Yg;
  String Yl;
  Cart Yu;
  boolean Za;
  String Zb;
  String Zc;
  boolean Zd;
  boolean Ze;
  CountrySpecification[] Zf;
  boolean Zg;
  boolean Zh;
  ArrayList<com.google.android.gms.identity.intents.model.CountrySpecification> Zi;
  private final int wj;
  
  MaskedWalletRequest()
  {
    this.wj = 3;
    this.Zg = true;
    this.Zh = true;
  }
  
  MaskedWalletRequest(int paramInt, String paramString1, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, String paramString2, String paramString3, String paramString4, Cart paramCart, boolean paramBoolean4, boolean paramBoolean5, CountrySpecification[] paramArrayOfCountrySpecification, boolean paramBoolean6, boolean paramBoolean7, ArrayList<com.google.android.gms.identity.intents.model.CountrySpecification> paramArrayList)
  {
    this.wj = paramInt;
    this.Yl = paramString1;
    this.YY = paramBoolean1;
    this.YZ = paramBoolean2;
    this.Za = paramBoolean3;
    this.Zb = paramString2;
    this.Yg = paramString3;
    this.Zc = paramString4;
    this.Yu = paramCart;
    this.Zd = paramBoolean4;
    this.Ze = paramBoolean5;
    this.Zf = paramArrayOfCountrySpecification;
    this.Zg = paramBoolean6;
    this.Zh = paramBoolean7;
    this.Zi = paramArrayList;
  }
  
  public static Builder newBuilder()
  {
    MaskedWalletRequest localMaskedWalletRequest = new MaskedWalletRequest();
    localMaskedWalletRequest.getClass();
    return new Builder(null);
  }
  
  public boolean allowDebitCard()
  {
    return this.Zh;
  }
  
  public boolean allowPrepaidCard()
  {
    return this.Zg;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public ArrayList<com.google.android.gms.identity.intents.model.CountrySpecification> getAllowedCountrySpecificationsForShipping()
  {
    return this.Zi;
  }
  
  public CountrySpecification[] getAllowedShippingCountrySpecifications()
  {
    return this.Zf;
  }
  
  public Cart getCart()
  {
    return this.Yu;
  }
  
  public String getCurrencyCode()
  {
    return this.Yg;
  }
  
  public String getEstimatedTotalPrice()
  {
    return this.Zb;
  }
  
  public String getMerchantName()
  {
    return this.Zc;
  }
  
  public String getMerchantTransactionId()
  {
    return this.Yl;
  }
  
  public int getVersionCode()
  {
    return this.wj;
  }
  
  public boolean isBillingAgreement()
  {
    return this.Ze;
  }
  
  public boolean isPhoneNumberRequired()
  {
    return this.YY;
  }
  
  public boolean isShippingAddressRequired()
  {
    return this.YZ;
  }
  
  public boolean shouldRetrieveWalletObjects()
  {
    return this.Zd;
  }
  
  public boolean useMinimalBillingAddress()
  {
    return this.Za;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    l.a(this, paramParcel, paramInt);
  }
  
  public final class Builder
  {
    private Builder() {}
    
    public Builder addAllowedCountrySpecificationForShipping(com.google.android.gms.identity.intents.model.CountrySpecification paramCountrySpecification)
    {
      if (MaskedWalletRequest.this.Zi == null) {
        MaskedWalletRequest.this.Zi = new ArrayList();
      }
      MaskedWalletRequest.this.Zi.add(paramCountrySpecification);
      return this;
    }
    
    public Builder addAllowedCountrySpecificationsForShipping(Collection<com.google.android.gms.identity.intents.model.CountrySpecification> paramCollection)
    {
      if (paramCollection != null)
      {
        if (MaskedWalletRequest.this.Zi == null) {
          MaskedWalletRequest.this.Zi = new ArrayList();
        }
        MaskedWalletRequest.this.Zi.addAll(paramCollection);
      }
      return this;
    }
    
    public MaskedWalletRequest build()
    {
      return MaskedWalletRequest.this;
    }
    
    public Builder setAllowDebitCard(boolean paramBoolean)
    {
      MaskedWalletRequest.this.Zh = paramBoolean;
      return this;
    }
    
    public Builder setAllowPrepaidCard(boolean paramBoolean)
    {
      MaskedWalletRequest.this.Zg = paramBoolean;
      return this;
    }
    
    public Builder setCart(Cart paramCart)
    {
      MaskedWalletRequest.this.Yu = paramCart;
      return this;
    }
    
    public Builder setCurrencyCode(String paramString)
    {
      MaskedWalletRequest.this.Yg = paramString;
      return this;
    }
    
    public Builder setEstimatedTotalPrice(String paramString)
    {
      MaskedWalletRequest.this.Zb = paramString;
      return this;
    }
    
    public Builder setIsBillingAgreement(boolean paramBoolean)
    {
      MaskedWalletRequest.this.Ze = paramBoolean;
      return this;
    }
    
    public Builder setMerchantName(String paramString)
    {
      MaskedWalletRequest.this.Zc = paramString;
      return this;
    }
    
    public Builder setMerchantTransactionId(String paramString)
    {
      MaskedWalletRequest.this.Yl = paramString;
      return this;
    }
    
    public Builder setPhoneNumberRequired(boolean paramBoolean)
    {
      MaskedWalletRequest.this.YY = paramBoolean;
      return this;
    }
    
    public Builder setShippingAddressRequired(boolean paramBoolean)
    {
      MaskedWalletRequest.this.YZ = paramBoolean;
      return this;
    }
    
    public Builder setShouldRetrieveWalletObjects(boolean paramBoolean)
    {
      MaskedWalletRequest.this.Zd = paramBoolean;
      return this;
    }
    
    public Builder setUseMinimalBillingAddress(boolean paramBoolean)
    {
      MaskedWalletRequest.this.Za = paramBoolean;
      return this;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\wallet\MaskedWalletRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */