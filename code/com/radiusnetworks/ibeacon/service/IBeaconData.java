package com.radiusnetworks.ibeacon.service;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.radiusnetworks.ibeacon.IBeacon;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class IBeaconData
  extends IBeacon
  implements Parcelable
{
  public static final Parcelable.Creator<IBeaconData> CREATOR = new Parcelable.Creator()
  {
    public IBeaconData createFromParcel(Parcel paramAnonymousParcel)
    {
      return new IBeaconData(paramAnonymousParcel, null);
    }
    
    public IBeaconData[] newArray(int paramAnonymousInt)
    {
      return new IBeaconData[paramAnonymousInt];
    }
  };
  
  private IBeaconData(Parcel paramParcel)
  {
    this.major = paramParcel.readInt();
    this.minor = paramParcel.readInt();
    this.proximityUuid = paramParcel.readString();
    this.proximity = Integer.valueOf(paramParcel.readInt());
    this.accuracy = Double.valueOf(paramParcel.readDouble());
    this.rssi = paramParcel.readInt();
    this.txPower = paramParcel.readInt();
  }
  
  public IBeaconData(IBeacon paramIBeacon)
  {
    super(paramIBeacon);
  }
  
  public static Collection<IBeacon> fromIBeaconDatas(Collection<IBeaconData> paramCollection)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramCollection != null)
    {
      paramCollection = paramCollection.iterator();
      while (paramCollection.hasNext()) {
        localArrayList.add(paramCollection.next());
      }
    }
    return localArrayList;
  }
  
  public static Collection<IBeaconData> fromIBeacons(Collection<IBeacon> paramCollection)
  {
    ArrayList localArrayList = new ArrayList();
    paramCollection = paramCollection.iterator();
    while (paramCollection.hasNext()) {
      localArrayList.add(new IBeaconData((IBeacon)paramCollection.next()));
    }
    return localArrayList;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeInt(this.major);
    paramParcel.writeInt(this.minor);
    paramParcel.writeString(this.proximityUuid);
    paramParcel.writeInt(getProximity());
    paramParcel.writeDouble(getAccuracy());
    paramParcel.writeInt(this.rssi);
    paramParcel.writeInt(this.txPower);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\radiusnetworks\ibeacon\service\IBeaconData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */