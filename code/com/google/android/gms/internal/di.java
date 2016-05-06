package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class di
  implements Parcelable
{
  @Deprecated
  public static final Parcelable.Creator<di> CREATOR = new Parcelable.Creator()
  {
    @Deprecated
    public di i(Parcel paramAnonymousParcel)
    {
      return new di(paramAnonymousParcel);
    }
    
    @Deprecated
    public di[] u(int paramAnonymousInt)
    {
      return new di[paramAnonymousInt];
    }
  };
  private String mValue;
  private String uS;
  private String uT;
  
  @Deprecated
  public di() {}
  
  @Deprecated
  di(Parcel paramParcel)
  {
    readFromParcel(paramParcel);
  }
  
  public di(String paramString1, String paramString2, String paramString3)
  {
    this.uS = paramString1;
    this.uT = paramString2;
    this.mValue = paramString3;
  }
  
  @Deprecated
  private void readFromParcel(Parcel paramParcel)
  {
    this.uS = paramParcel.readString();
    this.uT = paramParcel.readString();
    this.mValue = paramParcel.readString();
  }
  
  @Deprecated
  public int describeContents()
  {
    return 0;
  }
  
  public String getId()
  {
    return this.uS;
  }
  
  public String getValue()
  {
    return this.mValue;
  }
  
  @Deprecated
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(this.uS);
    paramParcel.writeString(this.uT);
    paramParcel.writeString(this.mValue);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\di.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */