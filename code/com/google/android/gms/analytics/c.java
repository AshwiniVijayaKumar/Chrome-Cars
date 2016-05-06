package com.google.android.gms.analytics;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.internal.di;
import com.google.android.gms.internal.dj;
import com.google.android.gms.internal.dj.a;
import java.util.List;
import java.util.Map;

class c
  implements b
{
  private Context mContext;
  private ServiceConnection qM;
  private b qN;
  private c qO;
  private dj qP;
  
  public c(Context paramContext, b paramb, c paramc)
  {
    this.mContext = paramContext;
    if (paramb == null) {
      throw new IllegalArgumentException("onConnectedListener cannot be null");
    }
    this.qN = paramb;
    if (paramc == null) {
      throw new IllegalArgumentException("onConnectionFailedListener cannot be null");
    }
    this.qO = paramc;
  }
  
  private dj bl()
  {
    bm();
    return this.qP;
  }
  
  private void bn()
  {
    bo();
  }
  
  private void bo()
  {
    this.qN.onConnected();
  }
  
  public void a(Map<String, String> paramMap, long paramLong, String paramString, List<di> paramList)
  {
    try
    {
      bl().a(paramMap, paramLong, paramString, paramList);
      return;
    }
    catch (RemoteException paramMap)
    {
      aa.t("sendHit failed: " + paramMap);
    }
  }
  
  public void bk()
  {
    try
    {
      bl().bk();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      aa.t("clear hits failed: " + localRemoteException);
    }
  }
  
  protected void bm()
  {
    if (!isConnected()) {
      throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
    }
  }
  
  public void connect()
  {
    Intent localIntent = new Intent("com.google.android.gms.analytics.service.START");
    localIntent.setComponent(new ComponentName("com.google.android.gms", "com.google.android.gms.analytics.service.AnalyticsService"));
    localIntent.putExtra("app_package_name", this.mContext.getPackageName());
    if (this.qM != null) {
      aa.t("Calling connect() while still connected, missing disconnect().");
    }
    boolean bool;
    do
    {
      return;
      this.qM = new a();
      bool = this.mContext.bindService(localIntent, this.qM, 129);
      aa.v("connect: bindService returned " + bool + " for " + localIntent);
    } while (bool);
    this.qM = null;
    this.qO.a(1, null);
  }
  
  public void disconnect()
  {
    this.qP = null;
    if (this.qM != null) {}
    try
    {
      this.mContext.unbindService(this.qM);
      this.qM = null;
      this.qN.onDisconnected();
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      for (;;) {}
    }
    catch (IllegalStateException localIllegalStateException)
    {
      for (;;) {}
    }
  }
  
  public boolean isConnected()
  {
    return this.qP != null;
  }
  
  final class a
    implements ServiceConnection
  {
    a() {}
    
    public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
    {
      aa.v("service connected, binder: " + paramIBinder);
      try
      {
        if ("com.google.android.gms.analytics.internal.IAnalyticsService".equals(paramIBinder.getInterfaceDescriptor()))
        {
          aa.v("bound to service");
          c.a(c.this, dj.a.r(paramIBinder));
          c.a(c.this);
          return;
        }
      }
      catch (RemoteException paramComponentName)
      {
        c.b(c.this).unbindService(this);
        c.a(c.this, null);
        c.c(c.this).a(2, null);
      }
    }
    
    public void onServiceDisconnected(ComponentName paramComponentName)
    {
      aa.v("service disconnected: " + paramComponentName);
      c.a(c.this, null);
      c.d(c.this).onDisconnected();
    }
  }
  
  public static abstract interface b
  {
    public abstract void onConnected();
    
    public abstract void onDisconnected();
  }
  
  public static abstract interface c
  {
    public abstract void a(int paramInt, Intent paramIntent);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\analytics\c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */