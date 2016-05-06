package com.radiusnetworks.ibeacon;

import com.radiusnetworks.ibeacon.client.DataProviderException;

public abstract interface IBeaconDataNotifier
{
  public abstract void iBeaconDataUpdate(IBeacon paramIBeacon, IBeaconData paramIBeaconData, DataProviderException paramDataProviderException);
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\radiusnetworks\ibeacon\IBeaconDataNotifier.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */