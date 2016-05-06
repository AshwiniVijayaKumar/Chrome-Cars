package com.google.android.gms.common;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class a
  implements ServiceConnection
{
  boolean yG = false;
  private final BlockingQueue<IBinder> yH = new LinkedBlockingQueue();
  
  public IBinder dm()
    throws InterruptedException
  {
    if (this.yG) {
      throw new IllegalStateException();
    }
    this.yG = true;
    return (IBinder)this.yH.take();
  }
  
  public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
  {
    try
    {
      this.yH.put(paramIBinder);
      return;
    }
    catch (InterruptedException paramComponentName) {}
  }
  
  public void onServiceDisconnected(ComponentName paramComponentName) {}
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\common\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */