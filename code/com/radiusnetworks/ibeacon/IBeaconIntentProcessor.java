package com.radiusnetworks.ibeacon;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.radiusnetworks.ibeacon.service.IBeaconData;
import com.radiusnetworks.ibeacon.service.MonitoringData;
import com.radiusnetworks.ibeacon.service.RangingData;

public class IBeaconIntentProcessor
  extends IntentService
{
  private static final String TAG = "IBeaconIntentProcessor";
  private boolean initialized = false;
  
  public IBeaconIntentProcessor()
  {
    super("IBeaconIntentProcessor");
  }
  
  protected void onHandleIntent(Intent paramIntent)
  {
    if (IBeaconManager.LOG_DEBUG) {
      Log.d("IBeaconIntentProcessor", "got an intent to process");
    }
    Object localObject3 = null;
    Object localObject4 = null;
    Object localObject1 = localObject3;
    Object localObject2 = localObject4;
    if (paramIntent != null)
    {
      localObject1 = localObject3;
      localObject2 = localObject4;
      if (paramIntent.getExtras() != null)
      {
        localObject1 = (MonitoringData)paramIntent.getExtras().get("monitoringData");
        localObject2 = (RangingData)paramIntent.getExtras().get("rangingData");
      }
    }
    if (localObject2 != null)
    {
      if (IBeaconManager.LOG_DEBUG) {
        Log.d("IBeaconIntentProcessor", "got ranging data");
      }
      if (((RangingData)localObject2).getIBeacons() == null) {
        Log.w("IBeaconIntentProcessor", "Ranging data has a null iBeacons collection");
      }
      paramIntent = IBeaconManager.getInstanceForApplication(this).getRangingNotifier();
      if (paramIntent != null) {
        paramIntent.didRangeBeaconsInRegion(IBeaconData.fromIBeaconDatas(((RangingData)localObject2).getIBeacons()), ((RangingData)localObject2).getRegion());
      }
    }
    else if (localObject1 != null)
    {
      if (IBeaconManager.LOG_DEBUG) {
        Log.d("IBeaconIntentProcessor", "got monitoring data");
      }
      paramIntent = IBeaconManager.getInstanceForApplication(this).getMonitoringNotifier();
      if (paramIntent != null)
      {
        if (IBeaconManager.LOG_DEBUG) {
          Log.d("IBeaconIntentProcessor", "Calling monitoring notifier:" + paramIntent);
        }
        if (!((MonitoringData)localObject1).isInside()) {
          break label254;
        }
      }
    }
    label254:
    for (int i = 1;; i = 0)
    {
      paramIntent.didDetermineStateForRegion(i, ((MonitoringData)localObject1).getRegion());
      if (!((MonitoringData)localObject1).isInside()) {
        break label259;
      }
      paramIntent.didEnterRegion(((MonitoringData)localObject1).getRegion());
      return;
      if (!IBeaconManager.LOG_DEBUG) {
        break;
      }
      Log.d("IBeaconIntentProcessor", "but ranging notifier is null, so we're dropping it.");
      break;
    }
    label259:
    paramIntent.didExitRegion(((MonitoringData)localObject1).getRegion());
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\radiusnetworks\ibeacon\IBeaconIntentProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */