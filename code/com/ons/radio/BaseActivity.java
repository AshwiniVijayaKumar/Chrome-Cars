package com.ons.radio;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;

public class BaseActivity
  extends Activity
{
  private AudioSignal audioSignal;
  private Intent bindIntent;
  private final ServiceConnection radioConnection = new ServiceConnection()
  {
    public void onServiceConnected(ComponentName paramAnonymousComponentName, IBinder paramAnonymousIBinder)
    {
      BaseActivity.access$002(BaseActivity.this, ((AudioSignal.RadioBinder)paramAnonymousIBinder).getService());
    }
    
    public void onServiceDisconnected(ComponentName paramAnonymousComponentName)
    {
      BaseActivity.access$002(BaseActivity.this, null);
    }
  };
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.bindIntent = new Intent(this, AudioSignal.class);
    bindService(this.bindIntent, this.radioConnection, 1);
    setVolumeControlStream(3);
  }
  
  protected void onResume()
  {
    super.onResume();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ons\radio\BaseActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */