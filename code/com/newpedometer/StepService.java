package com.newpedometer;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Binder;
import android.os.IBinder;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;

public class StepService
  extends Service
{
  private static final String TAG = "name.bagi.levente.pedometer.StepService";
  private final IBinder mBinder = new StepBinder();
  private ICallback mCallback;
  private float mDistance;
  private DistanceNotifier.Listener mDistanceListener = new DistanceNotifier.Listener()
  {
    public void passValue()
    {
      if (StepService.this.mCallback != null) {
        StepService.this.mCallback.distanceChanged(StepService.this.mDistance);
      }
    }
    
    public void valueChanged(float paramAnonymousFloat)
    {
      StepService.access$202(StepService.this, paramAnonymousFloat);
      passValue();
    }
  };
  private DistanceNotifier mDistanceNotifier;
  private PedometerSettings mPedometerSettings;
  private BroadcastReceiver mReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      if (paramAnonymousIntent.getAction().equals("android.intent.action.SCREEN_OFF"))
      {
        StepService.this.unregisterDetector();
        StepService.this.registerDetector();
        if (StepService.this.mPedometerSettings.wakeAggressively())
        {
          StepService.this.wakeLock.release();
          StepService.this.acquireWakeLock();
        }
      }
    }
  };
  private Sensor mSensor;
  private SensorManager mSensorManager;
  private SharedPreferences mSettings;
  private SharedPreferences mState;
  private SharedPreferences.Editor mStateEditor;
  private StepDetector mStepDetector;
  private StepDisplayer mStepDisplayer;
  private StepDisplayer.Listener mStepListener = new StepDisplayer.Listener()
  {
    public void passValue()
    {
      if (StepService.this.mCallback != null) {
        StepService.this.mCallback.stepsChanged(StepService.this.mSteps);
      }
    }
    
    public void stepsChanged(int paramAnonymousInt)
    {
      StepService.access$002(StepService.this, paramAnonymousInt);
      passValue();
    }
  };
  private int mSteps;
  private Utils mUtils;
  private PowerManager.WakeLock wakeLock;
  
  private void acquireWakeLock()
  {
    PowerManager localPowerManager = (PowerManager)getSystemService("power");
    int i;
    if (this.mPedometerSettings.wakeAggressively()) {
      i = 268435462;
    }
    for (;;)
    {
      this.wakeLock = localPowerManager.newWakeLock(i, "name.bagi.levente.pedometer.StepService");
      this.wakeLock.acquire();
      return;
      if (this.mPedometerSettings.keepScreenOn()) {
        i = 6;
      } else {
        i = 1;
      }
    }
  }
  
  private void registerDetector()
  {
    this.mSensor = this.mSensorManager.getDefaultSensor(1);
    this.mSensorManager.registerListener(this.mStepDetector, this.mSensor, 0);
  }
  
  private void unregisterDetector()
  {
    this.mSensorManager.unregisterListener(this.mStepDetector);
  }
  
  public IBinder onBind(Intent paramIntent)
  {
    Log.i("name.bagi.levente.pedometer.StepService", "[SERVICE] onBind");
    return this.mBinder;
  }
  
  public void onCreate()
  {
    Log.i("name.bagi.levente.pedometer.StepService", "[SERVICE] onCreate");
    super.onCreate();
    this.mSettings = getSharedPreferences("Pedometerpreference", 0);
    this.mPedometerSettings = new PedometerSettings(this.mSettings);
    this.mState = getSharedPreferences("state", 0);
    acquireWakeLock();
    this.mStepDetector = new StepDetector();
    this.mSensorManager = ((SensorManager)getSystemService("sensor"));
    registerDetector();
    Object localObject = new IntentFilter("android.intent.action.SCREEN_OFF");
    registerReceiver(this.mReceiver, (IntentFilter)localObject);
    this.mStepDisplayer = new StepDisplayer(this.mPedometerSettings, this.mUtils);
    localObject = this.mStepDisplayer;
    int i = this.mState.getInt("steps", 0);
    this.mSteps = i;
    ((StepDisplayer)localObject).setSteps(i);
    this.mStepDisplayer.addListener(this.mStepListener);
    this.mStepDetector.addStepListener(this.mStepDisplayer);
    this.mDistanceNotifier = new DistanceNotifier(this.mDistanceListener, this.mPedometerSettings, this.mUtils);
    localObject = this.mDistanceNotifier;
    float f = this.mState.getFloat("distance", 0.0F);
    this.mDistance = f;
    ((DistanceNotifier)localObject).setDistance(f);
    this.mStepDetector.addStepListener(this.mDistanceNotifier);
    reloadSettings();
  }
  
  public void onDestroy()
  {
    Log.i("name.bagi.levente.pedometer.StepService", "[SERVICE] onDestroy");
    unregisterReceiver(this.mReceiver);
    unregisterDetector();
    this.mStateEditor = this.mState.edit();
    this.mStateEditor.putInt("steps", this.mSteps);
    this.mStateEditor.putFloat("distance", this.mDistance);
    this.mStateEditor.commit();
    this.wakeLock.release();
    super.onDestroy();
    this.mSensorManager.unregisterListener(this.mStepDetector);
  }
  
  public void onStart(Intent paramIntent, int paramInt)
  {
    Log.i("name.bagi.levente.pedometer.StepService", "[SERVICE] onStart");
    super.onStart(paramIntent, paramInt);
  }
  
  public void registerCallback(ICallback paramICallback)
  {
    this.mCallback = paramICallback;
  }
  
  public void reloadSettings()
  {
    this.mSettings = getSharedPreferences("Pedometerpreference", 0);
    if (this.mStepDetector != null) {
      this.mStepDetector.setSensitivity(this.mPedometerSettings.getSensitivity());
    }
    if (this.mStepDisplayer != null) {
      this.mStepDisplayer.reloadSettings();
    }
    if (this.mDistanceNotifier != null) {
      this.mDistanceNotifier.reloadSettings();
    }
  }
  
  public void resetValues()
  {
    this.mStepDisplayer.setSteps(0);
    this.mDistanceNotifier.setDistance(0.0F);
  }
  
  public void resetmilesOrKiloMeter()
  {
    if (this.mStepDisplayer != null) {
      this.mStepDisplayer.reloadSettings();
    }
    if (this.mDistanceNotifier != null) {
      this.mDistanceNotifier.reloadSettings();
    }
  }
  
  public void showNotificationOnGoal()
  {
    NotificationManager localNotificationManager = (NotificationManager)getSystemService("notification");
    PendingIntent localPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, Pedometer.class), 134217728);
    Notification localNotification = new Notification(2130837596, "You Achived The Goal", System.currentTimeMillis());
    localNotification.flags = 21;
    localNotification.setLatestEventInfo(this, "Goal Achived", "You Have Complited your Goal Steps", localPendingIntent);
    localNotificationManager.notify(0, localNotification);
  }
  
  public static abstract interface ICallback
  {
    public abstract void distanceChanged(float paramFloat);
    
    public abstract void stepsChanged(int paramInt);
  }
  
  public class StepBinder
    extends Binder
  {
    public StepBinder() {}
    
    StepService getService()
    {
      return StepService.this;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\newpedometer\StepService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */