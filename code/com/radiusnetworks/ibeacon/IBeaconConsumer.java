package com.radiusnetworks.ibeacon;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;

public abstract interface IBeaconConsumer
{
  public abstract boolean bindService(Intent paramIntent, ServiceConnection paramServiceConnection, int paramInt);
  
  public abstract Context getApplicationContext();
  
  public abstract void onIBeaconServiceConnect();
  
  public abstract void unbindService(ServiceConnection paramServiceConnection);
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\radiusnetworks\ibeacon\IBeaconConsumer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */