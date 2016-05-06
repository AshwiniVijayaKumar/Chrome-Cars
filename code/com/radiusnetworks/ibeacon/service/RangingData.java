package com.radiusnetworks.ibeacon.service;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.Log;
import com.radiusnetworks.ibeacon.IBeacon;
import com.radiusnetworks.ibeacon.IBeaconManager;
import com.radiusnetworks.ibeacon.Region;
import java.util.ArrayList;
import java.util.Collection;

public class RangingData
  implements Parcelable
{
  public static final Parcelable.Creator<RangingData> CREATOR = new Parcelable.Creator()
  {
    public RangingData createFromParcel(Parcel paramAnonymousParcel)
    {
      return new RangingData(paramAnonymousParcel, null);
    }
    
    public RangingData[] newArray(int paramAnonymousInt)
    {
      return new RangingData[paramAnonymousInt];
    }
  };
  private static final String TAG = "RangingData";
  private Collection<IBeaconData> iBeaconDatas;
  private RegionData regionData;
  
  private RangingData(Parcel paramParcel)
  {
    if (IBeaconManager.LOG_DEBUG) {
      Log.d("RangingData", "parsing RangingData");
    }
    Parcelable[] arrayOfParcelable = paramParcel.readParcelableArray(getClass().getClassLoader());
    this.iBeaconDatas = new ArrayList(arrayOfParcelable.length);
    int i = 0;
    while (i < arrayOfParcelable.length)
    {
      this.iBeaconDatas.add((IBeaconData)arrayOfParcelable[i]);
      i += 1;
    }
    this.regionData = ((RegionData)paramParcel.readParcelable(getClass().getClassLoader()));
  }
  
  public RangingData(Collection<IBeacon> paramCollection, Region paramRegion)
  {
    this.iBeaconDatas = IBeaconData.fromIBeacons(paramCollection);
    this.regionData = new RegionData(paramRegion);
  }
  
  public RangingData(Collection<IBeaconData> paramCollection, RegionData paramRegionData)
  {
    this.iBeaconDatas = paramCollection;
    this.regionData = paramRegionData;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public Collection<IBeaconData> getIBeacons()
  {
    return this.iBeaconDatas;
  }
  
  public RegionData getRegion()
  {
    return this.regionData;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    if (IBeaconManager.LOG_DEBUG) {
      Log.d("RangingData", "writing RangingData");
    }
    paramParcel.writeParcelableArray((Parcelable[])this.iBeaconDatas.toArray(new Parcelable[0]), paramInt);
    paramParcel.writeParcelable(this.regionData, paramInt);
    if (IBeaconManager.LOG_DEBUG) {
      Log.d("RangingData", "done writing RangingData");
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\radiusnetworks\ibeacon\service\RangingData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */