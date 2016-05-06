package com.google.android.gms.identity.intents.model;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class UserAddress
  implements SafeParcelable
{
  public static final Parcelable.Creator<UserAddress> CREATOR = new b();
  String KB;
  String KC;
  String KD;
  String KE;
  String KF;
  String KG;
  String KH;
  String KI;
  String KJ;
  String KK;
  boolean KL;
  String KM;
  String KN;
  String name;
  String oQ;
  private final int wj;
  
  UserAddress()
  {
    this.wj = 1;
  }
  
  UserAddress(int paramInt, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, String paramString10, String paramString11, String paramString12, boolean paramBoolean, String paramString13, String paramString14)
  {
    this.wj = paramInt;
    this.name = paramString1;
    this.KB = paramString2;
    this.KC = paramString3;
    this.KD = paramString4;
    this.KE = paramString5;
    this.KF = paramString6;
    this.KG = paramString7;
    this.KH = paramString8;
    this.oQ = paramString9;
    this.KI = paramString10;
    this.KJ = paramString11;
    this.KK = paramString12;
    this.KL = paramBoolean;
    this.KM = paramString13;
    this.KN = paramString14;
  }
  
  public static UserAddress fromIntent(Intent paramIntent)
  {
    if ((paramIntent == null) || (!paramIntent.hasExtra("com.google.android.gms.identity.intents.EXTRA_ADDRESS"))) {
      return null;
    }
    return (UserAddress)paramIntent.getParcelableExtra("com.google.android.gms.identity.intents.EXTRA_ADDRESS");
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
  
  public String getAddress4()
  {
    return this.KE;
  }
  
  public String getAddress5()
  {
    return this.KF;
  }
  
  public String getAdministrativeArea()
  {
    return this.KG;
  }
  
  public String getCompanyName()
  {
    return this.KM;
  }
  
  public String getCountryCode()
  {
    return this.oQ;
  }
  
  public String getEmailAddress()
  {
    return this.KN;
  }
  
  public String getLocality()
  {
    return this.KH;
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
  
  public String getSortingCode()
  {
    return this.KJ;
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
    b.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\identity\intents\model\UserAddress.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */