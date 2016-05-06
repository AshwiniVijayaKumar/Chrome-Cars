package com.radiusnetworks.ibeacon.service;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.radiusnetworks.ibeacon.Region;

public class RegionData
  extends Region
  implements Parcelable
{
  public static final Parcelable.Creator<RegionData> CREATOR = new Parcelable.Creator()
  {
    public RegionData createFromParcel(Parcel paramAnonymousParcel)
    {
      return new RegionData(paramAnonymousParcel, null);
    }
    
    public RegionData[] newArray(int paramAnonymousInt)
    {
      return new RegionData[paramAnonymousInt];
    }
  };
  
  private RegionData(Parcel paramParcel)
  {
    this.major = Integer.valueOf(paramParcel.readInt());
    if (this.major.intValue() == -1) {
      this.major = null;
    }
    this.minor = Integer.valueOf(paramParcel.readInt());
    if (this.minor.intValue() == -1) {
      this.minor = null;
    }
    this.proximityUuid = paramParcel.readString();
    this.uniqueId = paramParcel.readString();
  }
  
  public RegionData(Region paramRegion)
  {
    super(paramRegion);
  }
  
  public RegionData(String paramString1, String paramString2, Integer paramInteger1, Integer paramInteger2)
  {
    super(paramString1, paramString2, paramInteger1, paramInteger2);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = -1;
    if (this.major == null)
    {
      paramInt = -1;
      paramParcel.writeInt(paramInt);
      if (this.minor != null) {
        break label58;
      }
    }
    label58:
    for (paramInt = i;; paramInt = this.minor.intValue())
    {
      paramParcel.writeInt(paramInt);
      paramParcel.writeString(this.proximityUuid);
      paramParcel.writeString(this.uniqueId);
      return;
      paramInt = this.major.intValue();
      break;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\radiusnetworks\ibeacon\service\RegionData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */