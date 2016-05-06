package com.radiusnetworks.ibeacon.client;

import com.radiusnetworks.ibeacon.IBeacon;
import com.radiusnetworks.ibeacon.IBeaconDataNotifier;

public abstract interface IBeaconDataFactory
{
  public abstract void requestIBeaconData(IBeacon paramIBeacon, IBeaconDataNotifier paramIBeaconDataNotifier);
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\radiusnetworks\ibeacon\client\IBeaconDataFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */