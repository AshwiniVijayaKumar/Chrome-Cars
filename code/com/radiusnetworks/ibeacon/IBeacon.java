package com.radiusnetworks.ibeacon;

import android.util.Log;
import com.radiusnetworks.ibeacon.client.IBeaconDataFactory;
import com.radiusnetworks.ibeacon.client.NullIBeaconDataFactory;

public class IBeacon
{
  public static final int PROXIMITY_FAR = 3;
  public static final int PROXIMITY_IMMEDIATE = 1;
  public static final int PROXIMITY_NEAR = 2;
  public static final int PROXIMITY_UNKNOWN = 0;
  private static final String TAG = "IBeacon";
  private static final char[] hexArray = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102 };
  protected static IBeaconDataFactory iBeaconDataFactory = new NullIBeaconDataFactory();
  protected Double accuracy;
  protected int major;
  protected int minor;
  protected Integer proximity;
  protected String proximityUuid;
  protected int rssi;
  protected Double runningAverageRssi = null;
  protected int txPower;
  
  protected IBeacon() {}
  
  protected IBeacon(IBeacon paramIBeacon)
  {
    this.major = paramIBeacon.major;
    this.minor = paramIBeacon.minor;
    this.accuracy = paramIBeacon.accuracy;
    this.proximity = paramIBeacon.proximity;
    this.rssi = paramIBeacon.rssi;
    this.proximityUuid = paramIBeacon.proximityUuid;
    this.txPower = paramIBeacon.txPower;
  }
  
  public IBeacon(String paramString, int paramInt1, int paramInt2)
  {
    this.proximityUuid = paramString;
    this.major = paramInt1;
    this.minor = paramInt2;
    this.rssi = this.rssi;
    this.txPower = -59;
    this.rssi = 0;
  }
  
  protected IBeacon(String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.proximityUuid = paramString;
    this.major = paramInt1;
    this.minor = paramInt2;
    this.rssi = paramInt4;
    this.txPower = paramInt3;
  }
  
  private static String bytesToHex(byte[] paramArrayOfByte)
  {
    char[] arrayOfChar = new char[paramArrayOfByte.length * 2];
    int i = 0;
    while (i < paramArrayOfByte.length)
    {
      int j = paramArrayOfByte[i] & 0xFF;
      arrayOfChar[(i * 2)] = hexArray[(j >>> 4)];
      arrayOfChar[(i * 2 + 1)] = hexArray[(j & 0xF)];
      i += 1;
    }
    return new String(arrayOfChar);
  }
  
  protected static double calculateAccuracy(int paramInt, double paramDouble)
  {
    double d1;
    if (paramDouble == 0.0D) {
      d1 = -1.0D;
    }
    double d2;
    do
    {
      return d1;
      if (IBeaconManager.LOG_DEBUG) {
        Log.d("IBeacon", "calculating accuracy based on rssi of " + paramDouble);
      }
      d1 = paramDouble * 1.0D / paramInt;
      if (d1 < 1.0D) {
        return Math.pow(d1, 10.0D);
      }
      d2 = 0.89976D * Math.pow(d1, 7.7095D) + 0.111D;
      d1 = d2;
    } while (!IBeaconManager.LOG_DEBUG);
    Log.d("IBeacon", " avg rssi: " + paramDouble + " accuracy: " + d2);
    return d2;
  }
  
  protected static int calculateProximity(double paramDouble)
  {
    if (paramDouble < 0.0D) {
      return 0;
    }
    if (paramDouble < 0.5D) {
      return 1;
    }
    if (paramDouble <= 4.0D) {
      return 2;
    }
    return 3;
  }
  
  public static IBeacon fromScanData(byte[] paramArrayOfByte, int paramInt)
  {
    int i = 2;
    int k = 0;
    for (;;)
    {
      int j = k;
      if (i <= 5)
      {
        if (((paramArrayOfByte[i] & 0xFF) == 76) && ((paramArrayOfByte[(i + 1)] & 0xFF) == 0) && ((paramArrayOfByte[(i + 2)] & 0xFF) == 2) && ((paramArrayOfByte[(i + 3)] & 0xFF) == 21)) {
          j = 1;
        }
      }
      else
      {
        if (j != 0) {
          break;
        }
        if (IBeaconManager.LOG_DEBUG) {
          Log.d("IBeacon", "This is not an iBeacon advertisment (no 4c000215 seen in bytes 2-5).  The bytes I see are: " + bytesToHex(paramArrayOfByte));
        }
        return null;
      }
      if (((paramArrayOfByte[i] & 0xFF) == 45) && ((paramArrayOfByte[(i + 1)] & 0xFF) == 36) && ((paramArrayOfByte[(i + 2)] & 0xFF) == 191) && ((paramArrayOfByte[(i + 3)] & 0xFF) == 22))
      {
        paramArrayOfByte = new IBeacon();
        paramArrayOfByte.major = 0;
        paramArrayOfByte.minor = 0;
        paramArrayOfByte.proximityUuid = "00000000-0000-0000-0000-000000000000";
        paramArrayOfByte.txPower = -55;
        return paramArrayOfByte;
      }
      i += 1;
    }
    IBeacon localIBeacon = new IBeacon();
    localIBeacon.major = ((paramArrayOfByte[(i + 20)] & 0xFF) * 256 + (paramArrayOfByte[(i + 21)] & 0xFF));
    localIBeacon.minor = ((paramArrayOfByte[(i + 22)] & 0xFF) * 256 + (paramArrayOfByte[(i + 23)] & 0xFF));
    localIBeacon.txPower = paramArrayOfByte[(i + 24)];
    localIBeacon.rssi = paramInt;
    Object localObject = new byte[16];
    System.arraycopy(paramArrayOfByte, i + 4, localObject, 0, 16);
    paramArrayOfByte = bytesToHex((byte[])localObject);
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append(paramArrayOfByte.substring(0, 8));
    ((StringBuilder)localObject).append("-");
    ((StringBuilder)localObject).append(paramArrayOfByte.substring(8, 12));
    ((StringBuilder)localObject).append("-");
    ((StringBuilder)localObject).append(paramArrayOfByte.substring(12, 16));
    ((StringBuilder)localObject).append("-");
    ((StringBuilder)localObject).append(paramArrayOfByte.substring(16, 20));
    ((StringBuilder)localObject).append("-");
    ((StringBuilder)localObject).append(paramArrayOfByte.substring(20, 32));
    localIBeacon.proximityUuid = ((StringBuilder)localObject).toString();
    return localIBeacon;
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof IBeacon)) {}
    do
    {
      return false;
      paramObject = (IBeacon)paramObject;
    } while ((((IBeacon)paramObject).getMajor() != getMajor()) || (((IBeacon)paramObject).getMinor() != getMinor()) || (!((IBeacon)paramObject).getProximityUuid().equals(getProximityUuid())));
    return true;
  }
  
  public double getAccuracy()
  {
    int i;
    if (this.accuracy == null)
    {
      i = this.txPower;
      if (this.runningAverageRssi == null) {
        break label47;
      }
    }
    label47:
    for (double d = this.runningAverageRssi.doubleValue();; d = this.rssi)
    {
      this.accuracy = Double.valueOf(calculateAccuracy(i, d));
      return this.accuracy.doubleValue();
    }
  }
  
  public int getMajor()
  {
    return this.major;
  }
  
  public int getMinor()
  {
    return this.minor;
  }
  
  public int getProximity()
  {
    if (this.proximity == null) {
      this.proximity = Integer.valueOf(calculateProximity(getAccuracy()));
    }
    return this.proximity.intValue();
  }
  
  public String getProximityUuid()
  {
    return this.proximityUuid;
  }
  
  public int getRssi()
  {
    return this.rssi;
  }
  
  public int getTxPower()
  {
    return this.txPower;
  }
  
  public int hashCode()
  {
    return this.minor;
  }
  
  public void requestData(IBeaconDataNotifier paramIBeaconDataNotifier)
  {
    iBeaconDataFactory.requestIBeaconData(this, paramIBeaconDataNotifier);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\radiusnetworks\ibeacon\IBeacon.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */