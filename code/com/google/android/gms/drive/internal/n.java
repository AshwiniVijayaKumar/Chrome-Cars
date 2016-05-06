package com.google.android.gms.drive.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.events.DriveEvent;
import com.google.android.gms.drive.events.DriveEvent.Listener;
import com.google.android.gms.drive.events.c;
import com.google.android.gms.internal.ee;
import com.google.android.gms.internal.eh;
import com.google.android.gms.internal.eh.e;
import com.google.android.gms.internal.en;
import com.google.android.gms.internal.er;
import java.util.HashMap;
import java.util.Map;

public class n
  extends eh<u>
{
  private DriveId DA;
  final GoogleApiClient.ConnectionCallbacks DB;
  Map<DriveId, Map<DriveEvent.Listener<?>, s<?>>> DC = new HashMap();
  private DriveId Dz;
  private final String vi;
  
  public n(Context paramContext, Looper paramLooper, ee paramee, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener, String[] paramArrayOfString)
  {
    super(paramContext, paramLooper, paramConnectionCallbacks, paramOnConnectionFailedListener, paramArrayOfString);
    this.vi = ((String)er.b(paramee.dR(), "Must call Api.ClientBuilder.setAccountName()"));
    this.DB = paramConnectionCallbacks;
  }
  
  protected u C(IBinder paramIBinder)
  {
    return u.a.D(paramIBinder);
  }
  
  <C extends DriveEvent> PendingResult<Status> a(GoogleApiClient paramGoogleApiClient, final DriveId paramDriveId, final int paramInt, DriveEvent.Listener<C> paramListener)
  {
    er.b(c.a(paramInt, paramDriveId), "id");
    er.b(paramListener, "listener");
    er.a(isConnected(), "Client must be connected");
    synchronized (this.DC)
    {
      final Object localObject2 = (Map)this.DC.get(paramDriveId);
      Object localObject1 = localObject2;
      if (localObject2 == null)
      {
        localObject1 = new HashMap();
        this.DC.put(paramDriveId, localObject1);
      }
      if (((Map)localObject1).containsKey(paramListener))
      {
        paramGoogleApiClient = new l.k(Status.zQ);
        return paramGoogleApiClient;
      }
      localObject2 = new s(getLooper(), paramInt, paramListener);
      ((Map)localObject1).put(paramListener, localObject2);
      paramGoogleApiClient = paramGoogleApiClient.b(new l.j()
      {
        protected void a(n paramAnonymousn)
          throws RemoteException
        {
          paramAnonymousn.eT().a(new AddEventListenerRequest(paramDriveId, paramInt), localObject2, null, new ak(this));
        }
      });
      return paramGoogleApiClient;
    }
  }
  
  protected void a(int paramInt, IBinder paramIBinder, Bundle paramBundle)
  {
    if (paramBundle != null)
    {
      paramBundle.setClassLoader(getClass().getClassLoader());
      this.Dz = ((DriveId)paramBundle.getParcelable("com.google.android.gms.drive.root_id"));
      this.DA = ((DriveId)paramBundle.getParcelable("com.google.android.gms.drive.appdata_id"));
    }
    super.a(paramInt, paramIBinder, paramBundle);
  }
  
  protected void a(en paramen, eh.e parame)
    throws RemoteException
  {
    String str = getContext().getPackageName();
    er.f(parame);
    er.f(str);
    er.f(ea());
    paramen.a(parame, 4323000, str, ea(), this.vi, new Bundle());
  }
  
  protected String aF()
  {
    return "com.google.android.gms.drive.ApiService.START";
  }
  
  protected String aG()
  {
    return "com.google.android.gms.drive.internal.IDriveService";
  }
  
  PendingResult<Status> b(GoogleApiClient paramGoogleApiClient, final DriveId paramDriveId, final int paramInt, final DriveEvent.Listener<?> paramListener)
  {
    er.b(c.a(paramInt, paramDriveId), "id");
    er.b(paramListener, "listener");
    er.a(isConnected(), "Client must be connected");
    Map localMap2;
    synchronized (this.DC)
    {
      localMap2 = (Map)this.DC.get(paramDriveId);
      if (localMap2 == null)
      {
        paramGoogleApiClient = new l.k(Status.zQ);
        return paramGoogleApiClient;
      }
      paramListener = (s)localMap2.remove(paramListener);
      if (paramListener == null)
      {
        paramGoogleApiClient = new l.k(Status.zQ);
        return paramGoogleApiClient;
      }
    }
    if (localMap2.isEmpty()) {
      this.DC.remove(paramDriveId);
    }
    paramGoogleApiClient = paramGoogleApiClient.b(new l.j()
    {
      protected void a(n paramAnonymousn)
        throws RemoteException
      {
        paramAnonymousn.eT().a(new RemoveEventListenerRequest(paramDriveId, paramInt), paramListener, null, new ak(this));
      }
    });
    return paramGoogleApiClient;
  }
  
  public void disconnect()
  {
    u localu = (u)eb();
    if (localu != null) {}
    try
    {
      localu.a(new DisconnectRequest());
      super.disconnect();
      this.DC.clear();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;) {}
    }
  }
  
  public u eT()
  {
    return (u)eb();
  }
  
  public DriveId eU()
  {
    return this.Dz;
  }
  
  public DriveId eV()
  {
    return this.DA;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\drive\internal\n.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */