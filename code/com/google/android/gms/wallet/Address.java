package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@Deprecated
public final class Address
  implements SafeParcelable
{
  public static final Parcelable.Creator<Address> CREATOR = new a();
  String KB;
  String KC;
  String KD;
  String KI;
  String KK;
  boolean KL;
  String KM;
  String Yd;
  String Ye;
  String name;
  String oQ;
  private final int wj;
  
  Address()
  {
    this.wj = 1;
  }
  
  Address(int paramInt, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, boolean paramBoolean, String paramString10)
  {
    this.wj = paramInt;
    this.name = paramString1;
    this.KB = paramString2;
    this.KC = paramString3;
    this.KD = paramString4;
    this.oQ = paramString5;
    this.Yd = paramString6;
    this.Ye = paramString7;
    this.KI = paramString8;
    this.KK = paramString9;
    this.KL = paramBoolean;
    this.KM = paramString10;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public String getAddress1()
  {
    return this.KB;
  }
  
  public String getAddress2()
  {
    return this.KC;
  }
  
  public String getAddress3()
  {
    return this.KD;
  }
  
  public String getCity()
  {
    return this.Yd;
  }
  
  public String getCompanyName()
  {
    return this.KM;
  }
  
  public String getCountryCode()
  {
    return this.oQ;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public String getPhoneNumber()
  {
    return this.KK;
  }
  
  public String getPostalCode()
  {
    return this.KI;
  }
  
  public String getState()
  {
    return this.Ye;
  }
  
  public int getVersionCode()
  {
    return this.wj;
  }
  
  public boolean isPostBox()
  {
    return this.KL;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    a.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\wallet\Address.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */