package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.ads.search.SearchAdRequest;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class am
  implements SafeParcelable
{
  public static final an CREATOR = new an();
  public final int backgroundColor;
  public final int lI;
  public final int lJ;
  public final int lK;
  public final int lL;
  public final int lM;
  public final int lN;
  public final int lO;
  public final String lP;
  public final int lQ;
  public final String lR;
  public final int lS;
  public final int lT;
  public final String lU;
  public final int versionCode;
  
  am(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, String paramString1, int paramInt10, String paramString2, int paramInt11, int paramInt12, String paramString3)
  {
    this.versionCode = paramInt1;
    this.lI = paramInt2;
    this.backgroundColor = paramInt3;
    this.lJ = paramInt4;
    this.lK = paramInt5;
    this.lL = paramInt6;
    this.lM = paramInt7;
    this.lN = paramInt8;
    this.lO = paramInt9;
    this.lP = paramString1;
    this.lQ = paramInt10;
    this.lR = paramString2;
    this.lS = paramInt11;
    this.lT = paramInt12;
    this.lU = paramString3;
  }
  
  public am(SearchAdRequest paramSearchAdRequest)
  {
    this.versionCode = 1;
    this.lI = paramSearchAdRequest.getAnchorTextColor();
    this.backgroundColor = paramSearchAdRequest.getBackgroundColor();
    this.lJ = paramSearchAdRequest.getBackgroundGradientBottom();
    this.lK = paramSearchAdRequest.getBackgroundGradientTop();
    this.lL = paramSearchAdRequest.getBorderColor();
    this.lM = paramSearchAdRequest.getBorderThickness();
    this.lN = paramSearchAdRequest.getBorderType();
    this.lO = paramSearchAdRequest.getCallButtonColor();
    this.lP = paramSearchAdRequest.getCustomChannels();
    this.lQ = paramSearchAdRequest.getDescriptionTextColor();
    this.lR = paramSearchAdRequest.getFontFace();
    this.lS = paramSearchAdRequest.getHeaderTextColor();
    this.lT = paramSearchAdRequest.getHeaderTextSize();
    this.lU = paramSearchAdRequest.getQuery();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    an.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\am.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */