package com.google.android.gms.common.api;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.internal.ee;
import com.google.android.gms.internal.er;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public abstract interface GoogleApiClient
{
  public abstract <C extends Api.a> C a(Api.b<C> paramb);
  
  public abstract <A extends Api.a, T extends a.a<? extends Result, A>> T a(T paramT);
  
  public abstract <A extends Api.a, T extends a.a<? extends Result, A>> T b(T paramT);
  
  public abstract ConnectionResult blockingConnect(long paramLong, TimeUnit paramTimeUnit);
  
  public abstract void connect();
  
  public abstract void disconnect();
  
  public abstract boolean isConnected();
  
  public abstract boolean isConnecting();
  
  public abstract boolean isConnectionCallbacksRegistered(ConnectionCallbacks paramConnectionCallbacks);
  
  public abstract boolean isConnectionFailedListenerRegistered(OnConnectionFailedListener paramOnConnectionFailedListener);
  
  public abstract void reconnect();
  
  public abstract void registerConnectionCallbacks(ConnectionCallbacks paramConnectionCallbacks);
  
  public abstract void registerConnectionFailedListener(OnConnectionFailedListener paramOnConnectionFailedListener);
  
  public abstract void unregisterConnectionCallbacks(ConnectionCallbacks paramConnectionCallbacks);
  
  public abstract void unregisterConnectionFailedListener(OnConnectionFailedListener paramOnConnectionFailedListener);
  
  public static abstract interface ApiOptions {}
  
  public static final class Builder
  {
    private final Context mContext;
    private String vi;
    private final Set<String> zn = new HashSet();
    private int zo;
    private View zp;
    private String zq;
    private final Map<Api, GoogleApiClient.ApiOptions> zr = new HashMap();
    private Looper zs;
    private final Set<GoogleApiClient.ConnectionCallbacks> zt = new HashSet();
    private final Set<GoogleApiClient.OnConnectionFailedListener> zu = new HashSet();
    
    public Builder(Context paramContext)
    {
      this.mContext = paramContext;
      this.zs = paramContext.getMainLooper();
      this.zq = paramContext.getPackageName();
    }
    
    public Builder(Context paramContext, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
    {
      this(paramContext);
      er.b(paramConnectionCallbacks, "Must provide a connected listener");
      this.zt.add(paramConnectionCallbacks);
      er.b(paramOnConnectionFailedListener, "Must provide a connection failed listener");
      this.zu.add(paramOnConnectionFailedListener);
    }
    
    public Builder addApi(Api paramApi)
    {
      return addApi(paramApi, null);
    }
    
    public Builder addApi(Api paramApi, GoogleApiClient.ApiOptions paramApiOptions)
    {
      this.zr.put(paramApi, paramApiOptions);
      paramApi = paramApi.dq();
      int j = paramApi.size();
      int i = 0;
      while (i < j)
      {
        this.zn.add(((Scope)paramApi.get(i)).dD());
        i += 1;
      }
      return this;
    }
    
    public Builder addConnectionCallbacks(GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks)
    {
      this.zt.add(paramConnectionCallbacks);
      return this;
    }
    
    public Builder addOnConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
    {
      this.zu.add(paramOnConnectionFailedListener);
      return this;
    }
    
    public Builder addScope(Scope paramScope)
    {
      this.zn.add(paramScope.dD());
      return this;
    }
    
    public GoogleApiClient build()
    {
      return new b(this.mContext, this.zs, dx(), this.zr, this.zt, this.zu);
    }
    
    public ee dx()
    {
      return new ee(this.vi, this.zn, this.zo, this.zp, this.zq);
    }
    
    public Builder setAccountName(String paramString)
    {
      this.vi = paramString;
      return this;
    }
    
    public Builder setGravityForPopups(int paramInt)
    {
      this.zo = paramInt;
      return this;
    }
    
    public Builder setHandler(Handler paramHandler)
    {
      er.b(paramHandler, "Handler must not be null");
      this.zs = paramHandler.getLooper();
      return this;
    }
    
    public Builder setViewForPopups(View paramView)
    {
      this.zp = paramView;
      return this;
    }
    
    public Builder useDefaultAccount()
    {
      return setAccountName("<<default account>>");
    }
  }
  
  public static abstract interface ConnectionCallbacks
  {
    public static final int CAUSE_NETWORK_LOST = 2;
    public static final int CAUSE_SERVICE_DISCONNECTED = 1;
    
    public abstract void onConnected(Bundle paramBundle);
    
    public abstract void onConnectionSuspended(int paramInt);
  }
  
  public static abstract interface OnConnectionFailedListener
    extends GooglePlayServicesClient.OnConnectionFailedListener
  {}
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\common\api\GoogleApiClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */