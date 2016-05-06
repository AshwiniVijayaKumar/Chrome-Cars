package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import java.util.ArrayList;

public final class ei
{
  private final b BJ;
  private ArrayList<GoogleApiClient.ConnectionCallbacks> BK = new ArrayList();
  final ArrayList<GoogleApiClient.ConnectionCallbacks> BL = new ArrayList();
  private boolean BM = false;
  private ArrayList<GooglePlayServicesClient.OnConnectionFailedListener> BN = new ArrayList();
  private boolean BO = false;
  private final Handler mHandler;
  
  public ei(Context paramContext, Looper paramLooper, b paramb)
  {
    this.BJ = paramb;
    this.mHandler = new a(paramLooper);
  }
  
  public void P(int paramInt)
  {
    this.mHandler.removeMessages(1);
    for (;;)
    {
      int i;
      synchronized (this.BK)
      {
        this.BM = true;
        ArrayList localArrayList2 = this.BK;
        int j = localArrayList2.size();
        i = 0;
        if ((i >= j) || (!this.BJ.dC()))
        {
          this.BM = false;
          return;
        }
        if (this.BK.contains(localArrayList2.get(i))) {
          ((GoogleApiClient.ConnectionCallbacks)localArrayList2.get(i)).onConnectionSuspended(paramInt);
        }
      }
      i += 1;
    }
  }
  
  public void a(ConnectionResult paramConnectionResult)
  {
    this.mHandler.removeMessages(1);
    for (;;)
    {
      int i;
      synchronized (this.BN)
      {
        this.BO = true;
        ArrayList localArrayList2 = this.BN;
        int j = localArrayList2.size();
        i = 0;
        if (i < j)
        {
          if (!this.BJ.dC()) {
            return;
          }
          if (this.BN.contains(localArrayList2.get(i))) {
            ((GooglePlayServicesClient.OnConnectionFailedListener)localArrayList2.get(i)).onConnectionFailed(paramConnectionResult);
          }
        }
        else
        {
          this.BO = false;
          return;
        }
      }
      i += 1;
    }
  }
  
  public void b(Bundle paramBundle)
  {
    boolean bool2 = true;
    for (;;)
    {
      int i;
      synchronized (this.BK)
      {
        if (!this.BM)
        {
          bool1 = true;
          er.v(bool1);
          this.mHandler.removeMessages(1);
          this.BM = true;
          if (this.BL.size() != 0) {
            break label172;
          }
          bool1 = bool2;
          er.v(bool1);
          ArrayList localArrayList2 = this.BK;
          int j = localArrayList2.size();
          i = 0;
          if ((i >= j) || (!this.BJ.dC()) || (!this.BJ.isConnected()))
          {
            this.BL.clear();
            this.BM = false;
            return;
          }
          this.BL.size();
          if (this.BL.contains(localArrayList2.get(i))) {
            break label178;
          }
          ((GoogleApiClient.ConnectionCallbacks)localArrayList2.get(i)).onConnected(paramBundle);
        }
      }
      boolean bool1 = false;
      continue;
      label172:
      bool1 = false;
      continue;
      label178:
      i += 1;
    }
  }
  
  protected void bo()
  {
    synchronized (this.BK)
    {
      b(this.BJ.cY());
      return;
    }
  }
  
  public boolean isConnectionCallbacksRegistered(GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    er.f(paramConnectionCallbacks);
    synchronized (this.BK)
    {
      boolean bool = this.BK.contains(paramConnectionCallbacks);
      return bool;
    }
  }
  
  public boolean isConnectionFailedListenerRegistered(GooglePlayServicesClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    er.f(paramOnConnectionFailedListener);
    synchronized (this.BN)
    {
      boolean bool = this.BN.contains(paramOnConnectionFailedListener);
      return bool;
    }
  }
  
  public void registerConnectionCallbacks(GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    er.f(paramConnectionCallbacks);
    synchronized (this.BK)
    {
      if (this.BK.contains(paramConnectionCallbacks))
      {
        Log.w("GmsClientEvents", "registerConnectionCallbacks(): listener " + paramConnectionCallbacks + " is already registered");
        if (this.BJ.isConnected()) {
          this.mHandler.sendMessage(this.mHandler.obtainMessage(1, paramConnectionCallbacks));
        }
        return;
      }
      if (this.BM) {
        this.BK = new ArrayList(this.BK);
      }
      this.BK.add(paramConnectionCallbacks);
    }
  }
  
  public void registerConnectionFailedListener(GooglePlayServicesClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    er.f(paramOnConnectionFailedListener);
    synchronized (this.BN)
    {
      if (this.BN.contains(paramOnConnectionFailedListener))
      {
        Log.w("GmsClientEvents", "registerConnectionFailedListener(): listener " + paramOnConnectionFailedListener + " is already registered");
        return;
      }
      if (this.BO) {
        this.BN = new ArrayList(this.BN);
      }
      this.BN.add(paramOnConnectionFailedListener);
    }
  }
  
  public void unregisterConnectionCallbacks(GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    er.f(paramConnectionCallbacks);
    synchronized (this.BK)
    {
      if (this.BK != null)
      {
        if (this.BM) {
          this.BK = new ArrayList(this.BK);
        }
        if (this.BK.remove(paramConnectionCallbacks)) {
          break label85;
        }
        Log.w("GmsClientEvents", "unregisterConnectionCallbacks(): listener " + paramConnectionCallbacks + " not found");
      }
      label85:
      while ((!this.BM) || (this.BL.contains(paramConnectionCallbacks))) {
        return;
      }
      this.BL.add(paramConnectionCallbacks);
    }
  }
  
  public void unregisterConnectionFailedListener(GooglePlayServicesClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    er.f(paramOnConnectionFailedListener);
    synchronized (this.BN)
    {
      if (this.BN != null)
      {
        if (this.BO) {
          this.BN = new ArrayList(this.BN);
        }
        if (!this.BN.remove(paramOnConnectionFailedListener)) {
          Log.w("GmsClientEvents", "unregisterConnectionFailedListener(): listener " + paramOnConnectionFailedListener + " not found");
        }
      }
      return;
    }
  }
  
  final class a
    extends Handler
  {
    public a(Looper paramLooper)
    {
      super();
    }
    
    public void handleMessage(Message paramMessage)
    {
      if (paramMessage.what == 1) {
        synchronized (ei.a(ei.this))
        {
          if ((ei.b(ei.this).dC()) && (ei.b(ei.this).isConnected()) && (ei.a(ei.this).contains(paramMessage.obj)))
          {
            Bundle localBundle = ei.b(ei.this).cY();
            ((GoogleApiClient.ConnectionCallbacks)paramMessage.obj).onConnected(localBundle);
          }
          return;
        }
      }
      Log.wtf("GmsClientEvents", "Don't know how to handle this message.");
    }
  }
  
  public static abstract interface b
  {
    public abstract Bundle cY();
    
    public abstract boolean dC();
    
    public abstract boolean isConnected();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\ei.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */