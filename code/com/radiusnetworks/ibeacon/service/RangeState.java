package com.radiusnetworks.ibeacon.service;

import com.radiusnetworks.ibeacon.IBeacon;
import java.util.HashSet;
import java.util.Set;

public class RangeState
{
  private Callback callback;
  private Set<IBeacon> iBeacons = new HashSet();
  
  public RangeState(Callback paramCallback)
  {
    this.callback = paramCallback;
  }
  
  public void addIBeacon(IBeacon paramIBeacon)
  {
    this.iBeacons.add(paramIBeacon);
  }
  
  public void clearIBeacons()
  {
    this.iBeacons.clear();
  }
  
  public Callback getCallback()
  {
    return this.callback;
  }
  
  public Set<IBeacon> getIBeacons()
  {
    return this.iBeacons;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\radiusnetworks\ibeacon\service\RangeState.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */