package com.radiusnetworks.ibeacon.client;

import android.os.Handler;
import com.radiusnetworks.ibeacon.IBeacon;
import com.radiusnetworks.ibeacon.IBeaconDataNotifier;

public class NullIBeaconDataFactory
  implements IBeaconDataFactory
{
  public void requestIBeaconData(IBeacon paramIBeacon, final IBeaconDataNotifier paramIBeaconDataNotifier)
  {
    new Handler().post(new Runnable()
    {
      public void run()
      {
        paramIBeaconDataNotifier.iBeaconDataUpdate(null, null, new DataProviderException("Please upgrade to the Pro version of the Android iBeacon Library."));
      }
    });
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\radiusnetworks\ibeacon\client\NullIBeaconDataFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */