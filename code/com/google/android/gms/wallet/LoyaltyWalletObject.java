package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.fj;
import com.google.android.gms.internal.jj;
import com.google.android.gms.internal.jl;
import com.google.android.gms.internal.jp;
import com.google.android.gms.internal.jr;
import com.google.android.gms.internal.jt;
import com.google.android.gms.internal.jv;
import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;

public final class LoyaltyWalletObject
  implements SafeParcelable
{
  public static final Parcelable.Creator<LoyaltyWalletObject> CREATOR = new j();
  String YC;
  String YD;
  String YE;
  String YF;
  String YG;
  String YH;
  String YI;
  String YJ;
  String YK;
  ArrayList<jv> YL;
  jr YM;
  ArrayList<LatLng> YN;
  String YO;
  String YP;
  ArrayList<jj> YQ;
  boolean YR;
  ArrayList<jt> YS;
  ArrayList<jp> YT;
  ArrayList<jt> YU;
  jl YV;
  String eN;
  int state;
  private final int wj;
  
  LoyaltyWalletObject()
  {
    this.wj = 4;
    this.YL = fj.eH();
    this.YN = fj.eH();
    this.YQ = fj.eH();
    this.YS = fj.eH();
    this.YT = fj.eH();
    this.YU = fj.eH();
  }
  
  LoyaltyWalletObject(int paramInt1, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, String paramString10, int paramInt2, ArrayList<jv> paramArrayList, jr paramjr, ArrayList<LatLng> paramArrayList1, String paramString11, String paramString12, ArrayList<jj> paramArrayList2, boolean paramBoolean, ArrayList<jt> paramArrayList3, ArrayList<jp> paramArrayList4, ArrayList<jt> paramArrayList5, jl paramjl)
  {
    this.wj = paramInt1;
    this.eN = paramString1;
    this.YC = paramString2;
    this.YD = paramString3;
    this.YE = paramString4;
    this.YF = paramString5;
    this.YG = paramString6;
    this.YH = paramString7;
    this.YI = paramString8;
    this.YJ = paramString9;
    this.YK = paramString10;
    this.state = paramInt2;
    this.YL = paramArrayList;
    this.YM = paramjr;
    this.YN = paramArrayList1;
    this.YO = paramString11;
    this.YP = paramString12;
    this.YQ = paramArrayList2;
    this.YR = paramBoolean;
    this.YS = paramArrayList3;
    this.YT = paramArrayList4;
    this.YU = paramArrayList5;
    this.YV = paramjl;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public String getAccountId()
  {
    return this.YC;
  }
  
  public String getAccountName()
  {
    return this.YF;
  }
  
  public String getBarcodeAlternateText()
  {
    return this.YG;
  }
  
  public String getBarcodeType()
  {
    return this.YH;
  }
  
  public String getBarcodeValue()
  {
    return this.YI;
  }
  
  public String getId()
  {
    return this.eN;
  }
  
  public String getIssuerName()
  {
    return this.YD;
  }
  
  public String getProgramName()
  {
    return this.YE;
  }
  
  public int getVersionCode()
  {
    return this.wj;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    j.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\wallet\LoyaltyWalletObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */