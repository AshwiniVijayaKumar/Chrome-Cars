package com.radiusnetworks.ibeacon.client;

import android.util.Log;
import com.radiusnetworks.ibeacon.IBeacon;
import com.radiusnetworks.ibeacon.IBeaconManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class RangingTracker
{
  private static String TAG = "RangingTracker";
  private Map<IBeacon, RangedIBeacon> rangedIBeacons = new HashMap();
  
  public void addIBeacon(IBeacon paramIBeacon)
  {
    if (this.rangedIBeacons.containsKey(paramIBeacon))
    {
      RangedIBeacon localRangedIBeacon = (RangedIBeacon)this.rangedIBeacons.get(paramIBeacon);
      if (IBeaconManager.LOG_DEBUG) {
        Log.d(TAG, "adding " + paramIBeacon.getProximityUuid() + " to existing range for: " + localRangedIBeacon.getProximityUuid());
      }
      localRangedIBeacon.addRangeMeasurement(Integer.valueOf(paramIBeacon.getRssi()));
      return;
    }
    if (IBeaconManager.LOG_DEBUG) {
      Log.d(TAG, "adding " + paramIBeacon.getProximityUuid() + " to new rangedIBeacon");
    }
    this.rangedIBeacons.put(paramIBeacon, new RangedIBeacon(paramIBeacon));
  }
  
  public Collection<IBeacon> getIBeacons()
  {
    try
    {
      ArrayList localArrayList = new ArrayList();
      Iterator localIterator = this.rangedIBeacons.values().iterator();
      while (localIterator.hasNext())
      {
        RangedIBeacon localRangedIBeacon = (RangedIBeacon)localIterator.next();
        if (!localRangedIBeacon.allMeasurementsExpired()) {
          localArrayList.add(localRangedIBeacon);
        }
      }
    }
    finally {}
    return localCollection;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\radiusnetworks\ibeacon\client\RangingTracker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */