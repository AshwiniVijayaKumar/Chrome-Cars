package com.google.zxing.client.android;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.util.Log;
import com.google.zxing.client.android.common.executor.AsyncTaskExecInterface;
import com.google.zxing.client.android.common.executor.AsyncTaskExecManager;

final class InactivityTimer
{
  private static final long INACTIVITY_DELAY_MS = 300000L;
  private static final String TAG = InactivityTimer.class.getSimpleName();
  private final Activity activity;
  private InactivityAsyncTask inactivityTask;
  private final BroadcastReceiver powerStatusReceiver;
  private final AsyncTaskExecInterface taskExec;
  
  InactivityTimer(Activity paramActivity)
  {
    this.activity = paramActivity;
    this.taskExec = ((AsyncTaskExecInterface)new AsyncTaskExecManager().build());
    this.powerStatusReceiver = new PowerStatusReceiver(null);
    onActivity();
  }
  
  private void cancel()
  {
    try
    {
      InactivityAsyncTask localInactivityAsyncTask = this.inactivityTask;
      if (localInactivityAsyncTask != null)
      {
        localInactivityAsyncTask.cancel(true);
        this.inactivityTask = null;
      }
      return;
    }
    finally {}
  }
  
  void onActivity()
  {
    try
    {
      cancel();
      this.inactivityTask = new InactivityAsyncTask(null);
      this.taskExec.execute(this.inactivityTask, new Object[0]);
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void onPause()
  {
    cancel();
    this.activity.unregisterReceiver(this.powerStatusReceiver);
  }
  
  public void onResume()
  {
    this.activity.registerReceiver(this.powerStatusReceiver, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
    onActivity();
  }
  
  void shutdown()
  {
    cancel();
  }
  
  private final class InactivityAsyncTask
    extends AsyncTask<Object, Object, Object>
  {
    private InactivityAsyncTask() {}
    
    protected Object doInBackground(Object... paramVarArgs)
    {
      try
      {
        Thread.sleep(300000L);
        Log.i(InactivityTimer.TAG, "Finishing activity due to inactivity");
        InactivityTimer.this.activity.finish();
        return null;
      }
      catch (InterruptedException paramVarArgs)
      {
        for (;;) {}
      }
    }
  }
  
  private final class PowerStatusReceiver
    extends BroadcastReceiver
  {
    private PowerStatusReceiver() {}
    
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      if ("android.intent.action.BATTERY_CHANGED".equals(paramIntent.getAction())) {
        if (paramIntent.getIntExtra("plugged", -1) > 0) {
          break label36;
        }
      }
      label36:
      for (int i = 1; i != 0; i = 0)
      {
        InactivityTimer.this.onActivity();
        return;
      }
      InactivityTimer.this.cancel();
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\InactivityTimer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */