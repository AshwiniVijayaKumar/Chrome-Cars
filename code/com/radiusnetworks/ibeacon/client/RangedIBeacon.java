package com.radiusnetworks.ibeacon.client;

import android.util.Log;
import com.radiusnetworks.ibeacon.IBeacon;
import com.radiusnetworks.ibeacon.IBeaconManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;

public class RangedIBeacon
  extends IBeacon
{
  public static long DEFAULT_SAMPLE_EXPIRATION_MILLISECONDS = 5000L;
  private static String TAG = "RangedIBeacon";
  private ArrayList<Measurement> measurements = new ArrayList();
  private long sampleExpirationMilliseconds = DEFAULT_SAMPLE_EXPIRATION_MILLISECONDS;
  
  public RangedIBeacon(IBeacon paramIBeacon)
  {
    super(paramIBeacon);
    addMeasurement(Integer.valueOf(this.rssi));
  }
  
  private double calculateRunningAverage()
  {
    refreshMeasurements();
    int n = this.measurements.size();
    int i = 0;
    int j = n - 1;
    if (n > 2)
    {
      i = n / 10 + 1;
      j = n - n / 10 - 2;
    }
    int m = 0;
    int k = i;
    while (k <= j)
    {
      m += ((Measurement)this.measurements.get(k)).rssi.intValue();
      k += 1;
    }
    double d = m / (j - i + 1);
    if (IBeaconManager.LOG_DEBUG) {
      Log.d(TAG, "Running average rssi based on " + n + " measurements: " + d);
    }
    return d;
  }
  
  private void refreshMeasurements()
  {
    try
    {
      Date localDate = new Date();
      ArrayList localArrayList = new ArrayList();
      Iterator localIterator = this.measurements.iterator();
      while (localIterator.hasNext())
      {
        Measurement localMeasurement = (Measurement)localIterator.next();
        if (localDate.getTime() - localMeasurement.timestamp < this.sampleExpirationMilliseconds) {
          localArrayList.add(localMeasurement);
        }
      }
      this.measurements = localArrayList;
    }
    finally {}
    Collections.sort(this.measurements);
  }
  
  public void addMeasurement(Integer paramInteger)
  {
    Measurement localMeasurement = new Measurement(null);
    localMeasurement.rssi = paramInteger;
    localMeasurement.timestamp = new Date().getTime();
    this.measurements.add(localMeasurement);
  }
  
  protected void addRangeMeasurement(Integer paramInteger)
  {
    this.rssi = paramInteger.intValue();
    addMeasurement(paramInteger);
    if (IBeaconManager.LOG_DEBUG) {
      Log.d(TAG, "calculating new range measurement with new rssi measurement:" + paramInteger);
    }
    this.runningAverageRssi = Double.valueOf(calculateRunningAverage());
    this.accuracy = null;
    this.proximity = null;
  }
  
  public boolean allMeasurementsExpired()
  {
    refreshMeasurements();
    return this.measurements.size() == 0;
  }
  
  public void setSampleExpirationMilliseconds(long paramLong)
  {
    this.sampleExpirationMilliseconds = paramLong;
  }
  
  private class Measurement
    implements Comparable<Measurement>
  {
    Integer rssi;
    long timestamp;
    
    private Measurement() {}
    
    public int compareTo(Measurement paramMeasurement)
    {
      return this.rssi.compareTo(paramMeasurement.rssi);
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\radiusnetworks\ibeacon\client\RangedIBeacon.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */