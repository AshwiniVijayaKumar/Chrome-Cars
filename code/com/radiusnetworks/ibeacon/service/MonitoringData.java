package com.radiusnetworks.ibeacon.service;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.radiusnetworks.ibeacon.Region;

public class MonitoringData
  implements Parcelable
{
  public static final Parcelable.Creator<MonitoringData> CREATOR = new Parcelable.Creator()
  {
    public MonitoringData createFromParcel(Parcel paramAnonymousParcel)
    {
      return new MonitoringData(paramAnonymousParcel, null);
    }
    
    public MonitoringData[] newArray(int paramAnonymousInt)
    {
      return new MonitoringData[paramAnonymousInt];
    }
  };
  private static final String TAG = "MonitoringData";
  private boolean inside;
  private RegionData regionData;
  
  private MonitoringData(Parcel paramParcel)
  {
    if (paramParcel.readByte() == 1) {}
    for (;;)
    {
      this.inside = bool;
      this.regionData = ((RegionData)paramParcel.readParcelable(getClass().getClassLoader()));
      return;
      bool = false;
    }
  }
  
  public MonitoringData(boolean paramBoolean, Region paramRegion)
  {
    this.inside = paramBoolean;
    this.regionData = new RegionData(paramRegion);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public Region getRegion()
  {
    return this.regionData;
  }
  
  public boolean isInside()
  {
    return this.inside;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    if (this.inside) {}
    for (int i = 1;; i = 0)
    {
      paramParcel.writeByte((byte)i);
      paramParcel.writeParcelable(this.regionData, paramInt);
      return;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\radiusnetworks\ibeacon\service\MonitoringData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */