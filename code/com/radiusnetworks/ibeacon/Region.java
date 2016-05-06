package com.radiusnetworks.ibeacon;

import android.util.Log;

public class Region
{
  private static final String TAG = "Region";
  protected Integer major;
  protected Integer minor;
  protected String proximityUuid;
  protected String uniqueId;
  
  protected Region() {}
  
  protected Region(Region paramRegion)
  {
    this.major = paramRegion.major;
    this.minor = paramRegion.minor;
    this.proximityUuid = paramRegion.proximityUuid;
    this.uniqueId = paramRegion.uniqueId;
  }
  
  public Region(String paramString1, String paramString2, Integer paramInteger1, Integer paramInteger2)
  {
    this.major = paramInteger1;
    this.minor = paramInteger2;
    this.proximityUuid = normalizeProximityUuid(paramString2);
    this.uniqueId = paramString1;
    if (paramString1 == null) {
      throw new NullPointerException("uniqueId may not be null");
    }
  }
  
  public static String normalizeProximityUuid(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    String str = paramString.toLowerCase().replaceAll("[\\-\\s]", "");
    if (str.length() != 32) {
      throw new RuntimeException("UUID: " + paramString + " is too short.  Must contain exactly 32 hex digits, and there are this value has " + str.length() + " digits.");
    }
    if (!str.matches("^[a-fA-F0-9]*$")) {
      throw new RuntimeException("UUID: " + paramString + " contains invalid characters.  Must be dashes, a-f and 0-9 characters only.");
    }
    paramString = new StringBuilder();
    paramString.append(str.substring(0, 8));
    paramString.append('-');
    paramString.append(str.substring(8, 12));
    paramString.append('-');
    paramString.append(str.substring(12, 16));
    paramString.append('-');
    paramString.append(str.substring(16, 20));
    paramString.append('-');
    paramString.append(str.substring(20, 32));
    return paramString.toString();
  }
  
  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof Region)) {
      return ((Region)paramObject).uniqueId.equals(this.uniqueId);
    }
    return false;
  }
  
  public Integer getMajor()
  {
    return this.major;
  }
  
  public Integer getMinor()
  {
    return this.minor;
  }
  
  public String getProximityUuid()
  {
    return this.proximityUuid;
  }
  
  public String getUniqueId()
  {
    return this.uniqueId;
  }
  
  public int hashCode()
  {
    return this.uniqueId.hashCode();
  }
  
  public boolean matchesIBeacon(IBeacon paramIBeacon)
  {
    if ((this.proximityUuid != null) && (!paramIBeacon.getProximityUuid().equals(this.proximityUuid))) {
      if (IBeaconManager.LOG_DEBUG) {
        Log.d("Region", "unmatching proxmityUuids: " + paramIBeacon.getProximityUuid() + " != " + this.proximityUuid);
      }
    }
    do
    {
      do
      {
        return false;
        if ((this.major == null) || (paramIBeacon.getMajor() == this.major.intValue())) {
          break;
        }
      } while (!IBeaconManager.LOG_DEBUG);
      Log.d("Region", "unmatching major: " + paramIBeacon.getMajor() + " != " + this.major);
      return false;
      if ((this.minor == null) || (paramIBeacon.getMinor() == this.minor.intValue())) {
        break;
      }
    } while (!IBeaconManager.LOG_DEBUG);
    Log.d("Region", "unmatching minor: " + paramIBeacon.getMajor() + " != " + this.minor);
    return false;
    return true;
  }
  
  public String toString()
  {
    return "proximityUuid: " + this.proximityUuid + " major: " + this.major + " minor:" + this.minor;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\radiusnetworks\ibeacon\Region.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */