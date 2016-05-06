package com.radiusnetworks.ibeacon.service;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class StartRMData
  implements Parcelable
{
  public static final Parcelable.Creator<StartRMData> CREATOR = new Parcelable.Creator()
  {
    public StartRMData createFromParcel(Parcel paramAnonymousParcel)
    {
      return new StartRMData(paramAnonymousParcel, null);
    }
    
    public StartRMData[] newArray(int paramAnonymousInt)
    {
      return new StartRMData[paramAnonymousInt];
    }
  };
  private long betweenScanPeriod;
  private String callbackPackageName;
  private RegionData regionData;
  private long scanPeriod;
  
  public StartRMData(long paramLong1, long paramLong2)
  {
    this.scanPeriod = paramLong1;
    this.betweenScanPeriod = paramLong2;
  }
  
  private StartRMData(Parcel paramParcel)
  {
    this.regionData = ((RegionData)paramParcel.readParcelable(getClass().getClassLoader()));
    this.callbackPackageName = paramParcel.readString();
    this.scanPeriod = paramParcel.readLong();
    this.betweenScanPeriod = paramParcel.readLong();
  }
  
  public StartRMData(RegionData paramRegionData, String paramString)
  {
    this.regionData = paramRegionData;
    this.callbackPackageName = paramString;
  }
  
  public StartRMData(RegionData paramRegionData, String paramString, long paramLong1, long paramLong2)
  {
    this.scanPeriod = paramLong1;
    this.betweenScanPeriod = paramLong2;
    this.regionData = paramRegionData;
    this.callbackPackageName = paramString;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public long getBetweenScanPeriod()
  {
    return this.betweenScanPeriod;
  }
  
  public String getCallbackPackageName()
  {
    return this.callbackPackageName;
  }
  
  public RegionData getRegionData()
  {
    return this.regionData;
  }
  
  public long getScanPeriod()
  {
    return this.scanPeriod;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeParcelable(this.regionData, paramInt);
    paramParcel.writeString(this.callbackPackageName);
    paramParcel.writeLong(this.scanPeriod);
    paramParcel.writeLong(this.betweenScanPeriod);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\radiusnetworks\ibeacon\service\StartRMData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */