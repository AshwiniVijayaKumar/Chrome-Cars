package com.radiusnetworks.ibeacon;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import com.radiusnetworks.ibeacon.client.RangingTracker;
import com.radiusnetworks.ibeacon.service.IBeaconService;
import com.radiusnetworks.ibeacon.service.RegionData;
import com.radiusnetworks.ibeacon.service.StartRMData;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class IBeaconManager
{
  public static final long DEFAULT_BACKGROUND_BETWEEN_SCAN_PERIOD = 300000L;
  public static final long DEFAULT_BACKGROUND_SCAN_PERIOD = 10000L;
  public static final long DEFAULT_FOREGROUND_BETWEEN_SCAN_PERIOD = 0L;
  public static final long DEFAULT_FOREGROUND_SCAN_PERIOD = 1100L;
  public static boolean LOG_DEBUG = false;
  private static final String TAG = "IBeaconManager";
  protected static IBeaconManager client = null;
  private long backgroundBetweenScanPeriod = 300000L;
  private long backgroundScanPeriod = 10000L;
  private Map<IBeaconConsumer, ConsumerInfo> consumers = new HashMap();
  private Context context;
  private long foregroundBetweenScanPeriod = 0L;
  private long foregroundScanPeriod = 1100L;
  private ServiceConnection iBeaconServiceConnection = new ServiceConnection()
  {
    public void onServiceConnected(ComponentName paramAnonymousComponentName, IBinder paramAnonymousIBinder)
    {
      Log.d("IBeaconManager", "we have a connection to the service now");
      IBeaconManager.access$102(IBeaconManager.this, new Messenger(paramAnonymousIBinder));
      paramAnonymousComponentName = IBeaconManager.this.consumers.keySet().iterator();
      while (paramAnonymousComponentName.hasNext())
      {
        paramAnonymousIBinder = (IBeaconConsumer)paramAnonymousComponentName.next();
        if (!Boolean.valueOf(((IBeaconManager.ConsumerInfo)IBeaconManager.this.consumers.get(paramAnonymousIBinder)).isConnected).booleanValue())
        {
          paramAnonymousIBinder.onIBeaconServiceConnect();
          IBeaconManager.ConsumerInfo localConsumerInfo = (IBeaconManager.ConsumerInfo)IBeaconManager.this.consumers.get(paramAnonymousIBinder);
          localConsumerInfo.isConnected = true;
          IBeaconManager.this.consumers.put(paramAnonymousIBinder, localConsumerInfo);
        }
      }
    }
    
    public void onServiceDisconnected(ComponentName paramAnonymousComponentName)
    {
      Log.e("IBeaconManager", "onServiceDisconnected");
    }
  };
  protected MonitorNotifier monitorNotifier = null;
  protected RangeNotifier rangeNotifier = null;
  protected RangingTracker rangingTracker = new RangingTracker();
  private Messenger serviceMessenger = null;
  
  protected IBeaconManager(Context paramContext)
  {
    this.context = paramContext;
  }
  
  private String callbackPackageName()
  {
    String str = this.context.getPackageName();
    if (LOG_DEBUG) {
      Log.d("IBeaconManager", "callback packageName: " + str);
    }
    return str;
  }
  
  private long getBetweenScanPeriod()
  {
    if (isInBackground()) {
      return this.backgroundBetweenScanPeriod;
    }
    return this.foregroundBetweenScanPeriod;
  }
  
  public static IBeaconManager getInstanceForApplication(Context paramContext)
  {
    if (client == null)
    {
      if (LOG_DEBUG) {
        Log.d("IBeaconManager", "IBeaconManager instance creation");
      }
      client = new IBeaconManager(paramContext);
    }
    return client;
  }
  
  private long getScanPeriod()
  {
    if (isInBackground()) {
      return this.backgroundScanPeriod;
    }
    return this.foregroundScanPeriod;
  }
  
  private boolean isInBackground()
  {
    boolean bool = true;
    Iterator localIterator = this.consumers.keySet().iterator();
    while (localIterator.hasNext())
    {
      IBeaconConsumer localIBeaconConsumer = (IBeaconConsumer)localIterator.next();
      if (!((ConsumerInfo)this.consumers.get(localIBeaconConsumer)).isInBackground) {
        bool = false;
      }
    }
    return bool;
  }
  
  public void bind(IBeaconConsumer paramIBeaconConsumer)
  {
    if (this.consumers.keySet().contains(paramIBeaconConsumer)) {
      if (LOG_DEBUG) {
        Log.d("IBeaconManager", "This consumer is already bound");
      }
    }
    do
    {
      return;
      Log.d("IBeaconManager", "This consumer is not bound.  binding: " + paramIBeaconConsumer);
      this.consumers.put(paramIBeaconConsumer, new ConsumerInfo(null));
      paramIBeaconConsumer.bindService(new Intent(paramIBeaconConsumer.getApplicationContext(), IBeaconService.class), this.iBeaconServiceConnection, 1);
      if (LOG_DEBUG) {
        Log.d("IBeaconManager", "consumer count is now:" + this.consumers.size());
      }
    } while (this.serviceMessenger == null);
    setBackgroundMode(paramIBeaconConsumer, false);
  }
  
  public boolean checkAvailability()
  {
    if (!this.context.getPackageManager().hasSystemFeature("android.hardware.bluetooth_le")) {
      throw new BleNotAvailableException("Bluetooth LE not supported by this device");
    }
    return ((BluetoothManager)this.context.getSystemService("bluetooth")).getAdapter().isEnabled();
  }
  
  public MonitorNotifier getMonitoringNotifier()
  {
    return this.monitorNotifier;
  }
  
  public RangeNotifier getRangingNotifier()
  {
    return this.rangeNotifier;
  }
  
  public boolean isBound(IBeaconConsumer paramIBeaconConsumer)
  {
    return (this.consumers.keySet().contains(paramIBeaconConsumer)) && (this.serviceMessenger != null);
  }
  
  public void setBackgroundBetweenScanPeriod(long paramLong)
  {
    this.backgroundBetweenScanPeriod = paramLong;
  }
  
  public boolean setBackgroundMode(IBeaconConsumer paramIBeaconConsumer, boolean paramBoolean)
  {
    try
    {
      ConsumerInfo localConsumerInfo = (ConsumerInfo)this.consumers.get(paramIBeaconConsumer);
      localConsumerInfo.isInBackground = paramBoolean;
      this.consumers.put(paramIBeaconConsumer, localConsumerInfo);
      setScanPeriods();
      return true;
    }
    catch (RemoteException paramIBeaconConsumer)
    {
      Log.e("IBeaconManager", "Failed to set background mode", paramIBeaconConsumer);
    }
    return false;
  }
  
  public void setBackgroundScanPeriod(long paramLong)
  {
    this.backgroundScanPeriod = paramLong;
  }
  
  public void setForegroundBetweenScanPeriod(long paramLong)
  {
    this.foregroundBetweenScanPeriod = paramLong;
  }
  
  public void setForegroundScanPeriod(long paramLong)
  {
    this.foregroundBetweenScanPeriod = paramLong;
  }
  
  public void setMonitorNotifier(MonitorNotifier paramMonitorNotifier)
  {
    this.monitorNotifier = paramMonitorNotifier;
  }
  
  public void setRangeNotifier(RangeNotifier paramRangeNotifier)
  {
    this.rangeNotifier = paramRangeNotifier;
  }
  
  public void setScanPeriods()
    throws RemoteException
  {
    if (this.serviceMessenger == null) {
      throw new RemoteException("The IBeaconManager is not bound to the service.  Call iBeaconManager.bind(IBeaconConsumer consumer) and wait for a callback to onIBeaconServiceConnect()");
    }
    Message localMessage = Message.obtain(null, 6, 0, 0);
    localMessage.obj = new StartRMData(getScanPeriod(), getBetweenScanPeriod());
    this.serviceMessenger.send(localMessage);
  }
  
  public void startMonitoringBeaconsInRegion(Region paramRegion)
    throws RemoteException
  {
    if (this.serviceMessenger == null) {
      throw new RemoteException("The IBeaconManager is not bound to the service.  Call iBeaconManager.bind(IBeaconConsumer consumer) and wait for a callback to onIBeaconServiceConnect()");
    }
    Message localMessage = Message.obtain(null, 4, 0, 0);
    localMessage.obj = new StartRMData(new RegionData(paramRegion), callbackPackageName(), getScanPeriod(), getBetweenScanPeriod());
    this.serviceMessenger.send(localMessage);
  }
  
  public void startRangingBeaconsInRegion(Region paramRegion)
    throws RemoteException
  {
    if (this.serviceMessenger == null) {
      throw new RemoteException("The IBeaconManager is not bound to the service.  Call iBeaconManager.bind(IBeaconConsumer consumer) and wait for a callback to onIBeaconServiceConnect()");
    }
    Message localMessage = Message.obtain(null, 2, 0, 0);
    localMessage.obj = new StartRMData(new RegionData(paramRegion), callbackPackageName(), getScanPeriod(), getBetweenScanPeriod());
    this.serviceMessenger.send(localMessage);
  }
  
  public void stopMonitoringBeaconsInRegion(Region paramRegion)
    throws RemoteException
  {
    if (this.serviceMessenger == null) {
      throw new RemoteException("The IBeaconManager is not bound to the service.  Call iBeaconManager.bind(IBeaconConsumer consumer) and wait for a callback to onIBeaconServiceConnect()");
    }
    Message localMessage = Message.obtain(null, 5, 0, 0);
    localMessage.obj = new StartRMData(new RegionData(paramRegion), callbackPackageName(), getScanPeriod(), getBetweenScanPeriod());
    this.serviceMessenger.send(localMessage);
  }
  
  public void stopRangingBeaconsInRegion(Region paramRegion)
    throws RemoteException
  {
    if (this.serviceMessenger == null) {
      throw new RemoteException("The IBeaconManager is not bound to the service.  Call iBeaconManager.bind(IBeaconConsumer consumer) and wait for a callback to onIBeaconServiceConnect()");
    }
    Message localMessage = Message.obtain(null, 3, 0, 0);
    localMessage.obj = new StartRMData(new RegionData(paramRegion), callbackPackageName(), getScanPeriod(), getBetweenScanPeriod());
    this.serviceMessenger.send(localMessage);
  }
  
  public void unBind(IBeaconConsumer paramIBeaconConsumer)
  {
    if (this.consumers.keySet().contains(paramIBeaconConsumer))
    {
      Log.d("IBeaconManager", "Unbinding");
      paramIBeaconConsumer.unbindService(this.iBeaconServiceConnection);
      this.consumers.remove(paramIBeaconConsumer);
    }
    for (;;)
    {
      return;
      if (LOG_DEBUG) {
        Log.d("IBeaconManager", "This consumer is not bound to: " + paramIBeaconConsumer);
      }
      if (LOG_DEBUG) {
        Log.d("IBeaconManager", "Bound consumers: ");
      }
      int i = 0;
      while (i < this.consumers.size())
      {
        Log.i("IBeaconManager", " " + this.consumers.get(Integer.valueOf(i)));
        i += 1;
      }
    }
  }
  
  private class ConsumerInfo
  {
    public boolean isConnected = false;
    public boolean isInBackground = false;
    
    private ConsumerInfo() {}
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\radiusnetworks\ibeacon\IBeaconManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */