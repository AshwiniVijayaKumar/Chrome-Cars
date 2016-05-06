package com.radiusnetworks.ibeacon.service;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothAdapter.LeScanCallback;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import com.radiusnetworks.ibeacon.IBeacon;
import com.radiusnetworks.ibeacon.IBeaconManager;
import com.radiusnetworks.ibeacon.Region;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

@TargetApi(18)
public class IBeaconService
  extends Service
{
  public static final int MSG_SET_SCAN_PERIODS = 6;
  public static final int MSG_START_MONITORING = 4;
  public static final int MSG_START_RANGING = 2;
  public static final int MSG_STOP_MONITORING = 5;
  public static final int MSG_STOP_RANGING = 3;
  public static final String TAG = "IBeaconService";
  private long betweenScanPeriod = 0L;
  private int bindCount = 0;
  private BluetoothAdapter bluetoothAdapter;
  private Handler handler = new Handler();
  private Date lastIBeaconDetectionTime = new Date();
  private long lastScanEndTime = 0L;
  private long lastScanStartTime = 0L;
  private BluetoothAdapter.LeScanCallback leScanCallback = new BluetoothAdapter.LeScanCallback()
  {
    public void onLeScan(BluetoothDevice paramAnonymousBluetoothDevice, int paramAnonymousInt, byte[] paramAnonymousArrayOfByte)
    {
      if (IBeaconManager.LOG_DEBUG) {
        Log.d("IBeaconService", "got record");
      }
      new IBeaconService.ScanProcessor(IBeaconService.this, null).execute(new IBeaconService.ScanData[] { new IBeaconService.ScanData(IBeaconService.this, paramAnonymousBluetoothDevice, paramAnonymousInt, paramAnonymousArrayOfByte) });
    }
  };
  final Messenger mMessenger = new Messenger(new IncomingHandler(this));
  private Map<Region, MonitorState> monitoredRegionState = new HashMap();
  private long nextScanStartTime = 0L;
  private int ongoing_notification_id = 1;
  private Map<Region, RangeState> rangedRegionState = new HashMap();
  private long scanPeriod = 1100L;
  private long scanStopTime = 0L;
  private boolean scanning;
  private boolean scanningPaused;
  private List<IBeacon> simulatedScanData = null;
  private HashSet<IBeacon> trackedBeacons;
  
  private boolean anyRangingOrMonitoringRegionsActive()
  {
    return this.rangedRegionState.size() + this.monitoredRegionState.size() > 0;
  }
  
  private void finishScanCycle()
  {
    if (IBeaconManager.LOG_DEBUG) {
      Log.d("IBeaconService", "Done with scan cycle");
    }
    processExpiredMonitors();
    if (this.scanning == true)
    {
      if (anyRangingOrMonitoringRegionsActive()) {
        break label48;
      }
      if (IBeaconManager.LOG_DEBUG) {
        Log.d("IBeaconService", "Not starting scan because no monitoring or ranging regions are defined.");
      }
    }
    return;
    label48:
    processRangeData();
    if (IBeaconManager.LOG_DEBUG) {
      Log.d("IBeaconService", "Restarting scan.  Unique beacons seen last cycle: " + this.trackedBeacons.size());
    }
    if (getBluetoothAdapter() != null)
    {
      if (!getBluetoothAdapter().isEnabled()) {
        break label199;
      }
      getBluetoothAdapter().stopLeScan(this.leScanCallback);
      this.lastScanEndTime = new Date().getTime();
    }
    for (;;)
    {
      this.scanningPaused = true;
      if (this.simulatedScanData == null) {
        break label218;
      }
      Object localObject = getApplicationInfo();
      int i = ((ApplicationInfo)localObject).flags & 0x2;
      ((ApplicationInfo)localObject).flags = i;
      if (i == 0) {
        break;
      }
      localObject = this.simulatedScanData.iterator();
      while (((Iterator)localObject).hasNext()) {
        processIBeaconFromScan((IBeacon)((Iterator)localObject).next());
      }
      label199:
      Log.w("IBeaconService", "Bluetooth is disabled.  Cannot scan for iBeacons.");
    }
    Log.w("IBeaconService", "Simulated scan data provided, but ignored because we are not running in debug mode.  Please remove simulated scan data for production.");
    label218:
    this.nextScanStartTime = (new Date().getTime() + this.betweenScanPeriod);
    scanLeDevice(Boolean.valueOf(true));
  }
  
  @TargetApi(18)
  private BluetoothAdapter getBluetoothAdapter()
  {
    if (this.bluetoothAdapter == null) {
      this.bluetoothAdapter = ((BluetoothManager)getApplicationContext().getSystemService("bluetooth")).getAdapter();
    }
    return this.bluetoothAdapter;
  }
  
  private boolean isInBackground()
  {
    if (IBeaconManager.LOG_DEBUG) {
      Log.d("IBeaconService", "bound client count:" + this.bindCount);
    }
    return this.bindCount == 0;
  }
  
  private List<Region> matchingRegions(IBeacon paramIBeacon, Collection<Region> paramCollection)
  {
    ArrayList localArrayList = new ArrayList();
    paramCollection = paramCollection.iterator();
    while (paramCollection.hasNext())
    {
      Region localRegion = (Region)paramCollection.next();
      if (localRegion.matchesIBeacon(paramIBeacon)) {
        localArrayList.add(localRegion);
      } else if (IBeaconManager.LOG_DEBUG) {
        Log.d("IBeaconService", "This region does not match: " + localRegion);
      }
    }
    return localArrayList;
  }
  
  private void processExpiredMonitors()
  {
    Iterator localIterator = this.monitoredRegionState.keySet().iterator();
    while (localIterator.hasNext())
    {
      Region localRegion = (Region)localIterator.next();
      MonitorState localMonitorState = (MonitorState)this.monitoredRegionState.get(localRegion);
      if (localMonitorState.isNewlyOutside())
      {
        if (IBeaconManager.LOG_DEBUG) {
          Log.d("IBeaconService", "found a monitor that expired: " + localRegion);
        }
        localMonitorState.getCallback().call(this, "monitoringData", new MonitoringData(localMonitorState.isInside(), localRegion));
      }
    }
  }
  
  private void processIBeaconFromScan(IBeacon paramIBeacon)
  {
    this.lastIBeaconDetectionTime = new Date();
    this.trackedBeacons.add(paramIBeacon);
    if (IBeaconManager.LOG_DEBUG) {
      Log.d("IBeaconService", "iBeacon detected :" + paramIBeacon.getProximityUuid() + " " + paramIBeacon.getMajor() + " " + paramIBeacon.getMinor() + " accuracy: " + paramIBeacon.getAccuracy() + " proximity: " + paramIBeacon.getProximity());
    }
    Iterator localIterator = matchingRegions(paramIBeacon, this.monitoredRegionState.keySet()).iterator();
    Region localRegion;
    while (localIterator.hasNext())
    {
      localRegion = (Region)localIterator.next();
      MonitorState localMonitorState = (MonitorState)this.monitoredRegionState.get(localRegion);
      if (localMonitorState.markInside()) {
        localMonitorState.getCallback().call(this, "monitoringData", new MonitoringData(localMonitorState.isInside(), localRegion));
      }
    }
    if (IBeaconManager.LOG_DEBUG) {
      Log.d("IBeaconService", "looking for ranging region matches for this ibeacon");
    }
    localIterator = matchingRegions(paramIBeacon, this.rangedRegionState.keySet()).iterator();
    while (localIterator.hasNext())
    {
      localRegion = (Region)localIterator.next();
      if (IBeaconManager.LOG_DEBUG) {
        Log.d("IBeaconService", "matches ranging region: " + localRegion);
      }
      ((RangeState)this.rangedRegionState.get(localRegion)).addIBeacon(paramIBeacon);
    }
  }
  
  private void processRangeData()
  {
    Iterator localIterator = this.rangedRegionState.keySet().iterator();
    while (localIterator.hasNext())
    {
      Region localRegion = (Region)localIterator.next();
      RangeState localRangeState = (RangeState)this.rangedRegionState.get(localRegion);
      if (IBeaconManager.LOG_DEBUG) {
        Log.d("IBeaconService", "Calling ranging callback with " + localRangeState.getIBeacons().size() + " iBeacons");
      }
      localRangeState.getCallback().call(this, "rangingData", new RangingData(localRangeState.getIBeacons(), localRegion));
      localRangeState.clearIBeacons();
    }
  }
  
  @SuppressLint({"NewApi"})
  private void scanLeDevice(Boolean paramBoolean)
  {
    if (getBluetoothAdapter() == null)
    {
      Log.e("IBeaconService", "No bluetooth adapter.  iBeaconService cannot scan.");
      if (this.simulatedScanData == null) {
        Log.w("IBeaconService", "exiting");
      }
    }
    do
    {
      return;
      Log.w("IBeaconService", "proceeding with simulated scan data");
      if (paramBoolean.booleanValue())
      {
        long l2 = this.nextScanStartTime - new Date().getTime();
        if (l2 > 0L)
        {
          if (IBeaconManager.LOG_DEBUG) {
            Log.d("IBeaconService", "Waiting to start next bluetooth scan for another " + l2 + " milliseconds");
          }
          paramBoolean = this.handler;
          Runnable local1 = new Runnable()
          {
            public void run()
            {
              IBeaconService.this.scanLeDevice(Boolean.valueOf(true));
            }
          };
          long l1 = l2;
          if (l2 > 1000L) {
            l1 = 1000L;
          }
          paramBoolean.postDelayed(local1, l1);
          return;
        }
        this.trackedBeacons = new HashSet();
        if ((!this.scanning) || (this.scanningPaused == true))
        {
          this.scanning = true;
          this.scanningPaused = false;
        }
        for (;;)
        {
          try
          {
            if (getBluetoothAdapter() != null)
            {
              if (getBluetoothAdapter().isEnabled())
              {
                getBluetoothAdapter().startLeScan(this.leScanCallback);
                this.lastScanStartTime = new Date().getTime();
              }
            }
            else
            {
              this.scanStopTime = (new Date().getTime() + this.scanPeriod);
              scheduleScanStop();
              if (!IBeaconManager.LOG_DEBUG) {
                break;
              }
              Log.d("IBeaconService", "Scan started");
              return;
            }
            Log.w("IBeaconService", "Bluetooth is disabled.  Cannot scan for iBeacons.");
            continue;
          }
          catch (Exception paramBoolean)
          {
            Log.e("TAG", "Exception starting bluetooth scan.  Perhaps bluetooth is disabled or unavailable?");
            continue;
          }
          if (IBeaconManager.LOG_DEBUG) {
            Log.d("IBeaconService", "We are already scanning");
          }
        }
      }
      if (IBeaconManager.LOG_DEBUG) {
        Log.d("IBeaconService", "disabling scan");
      }
      this.scanning = false;
    } while (getBluetoothAdapter() == null);
    getBluetoothAdapter().stopLeScan(this.leScanCallback);
    this.lastScanEndTime = new Date().getTime();
  }
  
  private void scheduleScanStop()
  {
    long l2 = this.scanStopTime - new Date().getTime();
    if (l2 > 0L)
    {
      if (IBeaconManager.LOG_DEBUG) {
        Log.d("IBeaconService", "Waiting to stop scan for another " + l2 + " milliseconds");
      }
      Handler localHandler = this.handler;
      Runnable local2 = new Runnable()
      {
        public void run()
        {
          IBeaconService.this.scheduleScanStop();
        }
      };
      long l1 = l2;
      if (l2 > 1000L) {
        l1 = 1000L;
      }
      localHandler.postDelayed(local2, l1);
      return;
    }
    finishScanCycle();
  }
  
  public IBinder onBind(Intent paramIntent)
  {
    Log.i("IBeaconService", "binding");
    this.bindCount += 1;
    return this.mMessenger.getBinder();
  }
  
  public void onCreate()
  {
    Log.i("IBeaconService", "iBeaconService is starting up");
    getBluetoothAdapter();
    try
    {
      this.simulatedScanData = ((List)Class.forName("com.radiusnetworks.ibeacon.SimulatedScanData").getField("iBeacons").get(null));
      return;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      while (!IBeaconManager.LOG_DEBUG) {}
      Log.d("IBeaconService", "No com.radiusnetworks.ibeacon.SimulatedScanData class exists.");
      return;
    }
    catch (Exception localException)
    {
      Log.e("IBeaconService", "Cannot get simulated Scan data.  Make sure your com.radiusnetworks.ibeacon.SimulatedScanData class defines a field with the signature 'public static List<IBeacon> iBeacons'", localException);
    }
  }
  
  public void onDestroy()
  {
    Log.i("IBeaconService", "onDestory called.  stopping scanning");
    scanLeDevice(Boolean.valueOf(false));
    if (this.bluetoothAdapter != null)
    {
      this.bluetoothAdapter.stopLeScan(this.leScanCallback);
      this.lastScanEndTime = new Date().getTime();
    }
  }
  
  public boolean onUnbind(Intent paramIntent)
  {
    Log.i("IBeaconService", "unbinding");
    this.bindCount -= 1;
    return false;
  }
  
  public void setScanPeriods(long paramLong1, long paramLong2)
  {
    this.scanPeriod = paramLong1;
    this.betweenScanPeriod = paramLong2;
    long l = new Date().getTime();
    if (this.nextScanStartTime > l)
    {
      paramLong2 = this.lastScanEndTime + paramLong2;
      if (paramLong2 < this.nextScanStartTime)
      {
        this.nextScanStartTime = paramLong2;
        Log.i("IBeaconService", "Adjusted nextScanStartTime to be " + new Date(this.nextScanStartTime));
      }
    }
    if (this.scanStopTime > l)
    {
      paramLong1 = this.lastScanStartTime + paramLong1;
      if (paramLong1 < this.scanStopTime)
      {
        this.scanStopTime = paramLong1;
        Log.i("IBeaconService", "Adjusted scanStopTime to be " + new Date(this.scanStopTime));
      }
    }
  }
  
  public void startMonitoringBeaconsInRegion(Region paramRegion, Callback paramCallback)
  {
    if (IBeaconManager.LOG_DEBUG) {
      Log.d("IBeaconService", "startMonitoring called");
    }
    if (this.monitoredRegionState.containsKey(paramRegion))
    {
      Log.i("IBeaconService", "Already monitoring that region -- will replace existing region monitor.");
      this.monitoredRegionState.remove(paramRegion);
    }
    this.monitoredRegionState.put(paramRegion, new MonitorState(paramCallback));
    if (IBeaconManager.LOG_DEBUG) {
      Log.d("IBeaconService", "Currently monitoring " + this.monitoredRegionState.size() + " regions.");
    }
    if (!this.scanning) {
      scanLeDevice(Boolean.valueOf(true));
    }
  }
  
  public void startRangingBeaconsInRegion(Region paramRegion, Callback paramCallback)
  {
    if (this.rangedRegionState.containsKey(paramRegion))
    {
      Log.i("IBeaconService", "Already ranging that region -- will replace existing region.");
      this.rangedRegionState.remove(paramRegion);
    }
    this.rangedRegionState.put(paramRegion, new RangeState(paramCallback));
    if (!this.scanning) {
      scanLeDevice(Boolean.valueOf(true));
    }
  }
  
  public void stopMonitoringBeaconsInRegion(Region paramRegion)
  {
    if (IBeaconManager.LOG_DEBUG) {
      Log.d("IBeaconService", "stopMonitoring called");
    }
    this.monitoredRegionState.remove(paramRegion);
    if (IBeaconManager.LOG_DEBUG) {
      Log.d("IBeaconService", "Currently monitoring " + this.monitoredRegionState.size() + " regions.");
    }
    if ((this.scanning) && (this.rangedRegionState.size() == 0) && (this.monitoredRegionState.size() == 0)) {
      scanLeDevice(Boolean.valueOf(false));
    }
  }
  
  public void stopRangingBeaconsInRegion(Region paramRegion)
  {
    this.rangedRegionState.remove(paramRegion);
    if ((this.scanning) && (this.rangedRegionState.size() == 0) && (this.monitoredRegionState.size() == 0)) {
      scanLeDevice(Boolean.valueOf(false));
    }
  }
  
  public class IBeaconBinder
    extends Binder
  {
    public IBeaconBinder() {}
    
    public IBeaconService getService()
    {
      Log.i("IBeaconService", "getService of IBeaconBinder called");
      return IBeaconService.this;
    }
  }
  
  static class IncomingHandler
    extends Handler
  {
    private final WeakReference<IBeaconService> mService;
    
    IncomingHandler(IBeaconService paramIBeaconService)
    {
      this.mService = new WeakReference(paramIBeaconService);
    }
    
    public void handleMessage(Message paramMessage)
    {
      IBeaconService localIBeaconService = (IBeaconService)this.mService.get();
      StartRMData localStartRMData = (StartRMData)paramMessage.obj;
      if (localIBeaconService != null) {}
      switch (paramMessage.what)
      {
      default: 
        super.handleMessage(paramMessage);
        return;
      case 2: 
        Log.i("IBeaconService", "start ranging received");
        localIBeaconService.startRangingBeaconsInRegion(localStartRMData.getRegionData(), new Callback(localStartRMData.getCallbackPackageName()));
        localIBeaconService.setScanPeriods(localStartRMData.getScanPeriod(), localStartRMData.getBetweenScanPeriod());
        return;
      case 3: 
        Log.i("IBeaconService", "stop ranging received");
        localIBeaconService.stopRangingBeaconsInRegion(localStartRMData.getRegionData());
        localIBeaconService.setScanPeriods(localStartRMData.getScanPeriod(), localStartRMData.getBetweenScanPeriod());
        return;
      case 4: 
        Log.i("IBeaconService", "start monitoring received");
        localIBeaconService.startMonitoringBeaconsInRegion(localStartRMData.getRegionData(), new Callback(localStartRMData.getCallbackPackageName()));
        localIBeaconService.setScanPeriods(localStartRMData.getScanPeriod(), localStartRMData.getBetweenScanPeriod());
        return;
      case 5: 
        Log.i("IBeaconService", "stop monitoring received");
        localIBeaconService.stopMonitoringBeaconsInRegion(localStartRMData.getRegionData());
        localIBeaconService.setScanPeriods(localStartRMData.getScanPeriod(), localStartRMData.getBetweenScanPeriod());
        return;
      }
      Log.i("IBeaconService", "set scan intervals received");
      localIBeaconService.setScanPeriods(localStartRMData.getScanPeriod(), localStartRMData.getBetweenScanPeriod());
    }
  }
  
  private class ScanData
  {
    public BluetoothDevice device;
    public int rssi;
    public byte[] scanRecord;
    
    public ScanData(BluetoothDevice paramBluetoothDevice, int paramInt, byte[] paramArrayOfByte)
    {
      this.device = paramBluetoothDevice;
      this.rssi = paramInt;
      this.scanRecord = paramArrayOfByte;
    }
  }
  
  private class ScanProcessor
    extends AsyncTask<IBeaconService.ScanData, Void, Void>
  {
    private ScanProcessor() {}
    
    protected Void doInBackground(IBeaconService.ScanData... paramVarArgs)
    {
      paramVarArgs = paramVarArgs[0];
      paramVarArgs = IBeacon.fromScanData(paramVarArgs.scanRecord, paramVarArgs.rssi);
      if (paramVarArgs != null) {
        IBeaconService.this.processIBeaconFromScan(paramVarArgs);
      }
      return null;
    }
    
    protected void onPostExecute(Void paramVoid) {}
    
    protected void onPreExecute() {}
    
    protected void onProgressUpdate(Void... paramVarArgs) {}
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\radiusnetworks\ibeacon\service\IBeaconService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */