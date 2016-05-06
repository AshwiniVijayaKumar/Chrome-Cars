package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.appstate.AppState;
import com.google.android.gms.appstate.AppStateBuffer;
import com.google.android.gms.appstate.AppStateManager.StateConflictResult;
import com.google.android.gms.appstate.AppStateManager.StateDeletedResult;
import com.google.android.gms.appstate.AppStateManager.StateListResult;
import com.google.android.gms.appstate.AppStateManager.StateLoadedResult;
import com.google.android.gms.appstate.AppStateManager.StateResult;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.a.c;
import com.google.android.gms.common.data.DataHolder;

public final class dl
  extends eh<dn>
{
  private final String vi;
  
  public dl(Context paramContext, Looper paramLooper, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener, String paramString, String[] paramArrayOfString)
  {
    super(paramContext, paramLooper, paramConnectionCallbacks, paramOnConnectionFailedListener, paramArrayOfString);
    this.vi = ((String)er.f(paramString));
  }
  
  public void a(a.c<AppStateManager.StateListResult> paramc)
  {
    try
    {
      ((dn)eb()).a(new c(paramc));
      return;
    }
    catch (RemoteException paramc)
    {
      Log.w("AppStateClient", "service died");
    }
  }
  
  public void a(a.c<AppStateManager.StateDeletedResult> paramc, int paramInt)
  {
    try
    {
      ((dn)eb()).b(new a(paramc), paramInt);
      return;
    }
    catch (RemoteException paramc)
    {
      Log.w("AppStateClient", "service died");
    }
  }
  
  public void a(a.c<AppStateManager.StateResult> paramc, int paramInt, String paramString, byte[] paramArrayOfByte)
  {
    try
    {
      ((dn)eb()).a(new e(paramc), paramInt, paramString, paramArrayOfByte);
      return;
    }
    catch (RemoteException paramc)
    {
      Log.w("AppStateClient", "service died");
    }
  }
  
  public void a(a.c<AppStateManager.StateResult> paramc, int paramInt, byte[] paramArrayOfByte)
  {
    if (paramc == null) {}
    for (paramc = null;; paramc = new e(paramc)) {
      try
      {
        ((dn)eb()).a(paramc, paramInt, paramArrayOfByte);
        return;
      }
      catch (RemoteException paramc)
      {
        Log.w("AppStateClient", "service died");
      }
    }
  }
  
  protected void a(en paramen, eh.e parame)
    throws RemoteException
  {
    paramen.a(parame, 4323000, getContext().getPackageName(), this.vi, ea());
  }
  
  protected String aF()
  {
    return "com.google.android.gms.appstate.service.START";
  }
  
  protected String aG()
  {
    return "com.google.android.gms.appstate.internal.IAppStateService";
  }
  
  public void b(a.c<Status> paramc)
  {
    try
    {
      ((dn)eb()).b(new g(paramc));
      return;
    }
    catch (RemoteException paramc)
    {
      Log.w("AppStateClient", "service died");
    }
  }
  
  public void b(a.c<AppStateManager.StateResult> paramc, int paramInt)
  {
    try
    {
      ((dn)eb()).a(new e(paramc), paramInt);
      return;
    }
    catch (RemoteException paramc)
    {
      Log.w("AppStateClient", "service died");
    }
  }
  
  protected void b(String... paramVarArgs)
  {
    int i = 0;
    boolean bool = false;
    while (i < paramVarArgs.length)
    {
      if (paramVarArgs[i].equals("https://www.googleapis.com/auth/appstate")) {
        bool = true;
      }
      i += 1;
    }
    er.a(bool, String.format("App State APIs requires %s to function.", new Object[] { "https://www.googleapis.com/auth/appstate" }));
  }
  
  public int cN()
  {
    try
    {
      int i = ((dn)eb()).cN();
      return i;
    }
    catch (RemoteException localRemoteException)
    {
      Log.w("AppStateClient", "service died");
    }
    return 2;
  }
  
  public int cO()
  {
    try
    {
      int i = ((dn)eb()).cO();
      return i;
    }
    catch (RemoteException localRemoteException)
    {
      Log.w("AppStateClient", "service died");
    }
    return 2;
  }
  
  protected dn s(IBinder paramIBinder)
  {
    return dn.a.u(paramIBinder);
  }
  
  final class a
    extends dk
  {
    private final a.c<AppStateManager.StateDeletedResult> vj;
    
    public a()
    {
      Object localObject;
      this.vj = ((a.c)er.b(localObject, "Result holder must not be null"));
    }
    
    public void b(int paramInt1, int paramInt2)
    {
      Status localStatus = new Status(paramInt1);
      dl.this.a(new dl.b(dl.this, this.vj, localStatus, paramInt2));
    }
  }
  
  final class b
    extends eh<dn>.b<a.c<AppStateManager.StateDeletedResult>>
    implements AppStateManager.StateDeletedResult
  {
    private final Status vl;
    private final int vm;
    
    public b(Status paramStatus, int paramInt)
    {
      super(paramStatus);
      this.vl = paramInt;
      int i;
      this.vm = i;
    }
    
    public void c(a.c<AppStateManager.StateDeletedResult> paramc)
    {
      paramc.b(this);
    }
    
    protected void cP() {}
    
    public int getStateKey()
    {
      return this.vm;
    }
    
    public Status getStatus()
    {
      return this.vl;
    }
  }
  
  final class c
    extends dk
  {
    private final a.c<AppStateManager.StateListResult> vj;
    
    public c()
    {
      Object localObject;
      this.vj = ((a.c)er.b(localObject, "Result holder must not be null"));
    }
    
    public void a(DataHolder paramDataHolder)
    {
      Status localStatus = new Status(paramDataHolder.getStatusCode());
      dl.this.a(new dl.d(dl.this, this.vj, localStatus, paramDataHolder));
    }
  }
  
  final class d
    extends eh<dn>.d<a.c<AppStateManager.StateListResult>>
    implements AppStateManager.StateListResult
  {
    private final Status vl;
    private final AppStateBuffer vn;
    
    public d(Status paramStatus, DataHolder paramDataHolder)
    {
      super(paramStatus, localDataHolder);
      this.vl = paramDataHolder;
      this.vn = new AppStateBuffer(localDataHolder);
    }
    
    public void a(a.c<AppStateManager.StateListResult> paramc, DataHolder paramDataHolder)
    {
      paramc.b(this);
    }
    
    public AppStateBuffer getStateBuffer()
    {
      return this.vn;
    }
    
    public Status getStatus()
    {
      return this.vl;
    }
  }
  
  final class e
    extends dk
  {
    private final a.c<AppStateManager.StateResult> vj;
    
    public e()
    {
      Object localObject;
      this.vj = ((a.c)er.b(localObject, "Result holder must not be null"));
    }
    
    public void a(int paramInt, DataHolder paramDataHolder)
    {
      dl.this.a(new dl.f(dl.this, this.vj, paramInt, paramDataHolder));
    }
  }
  
  final class f
    extends eh<dn>.d<a.c<AppStateManager.StateResult>>
    implements AppStateManager.StateConflictResult, AppStateManager.StateLoadedResult, AppStateManager.StateResult
  {
    private final Status vl;
    private final int vm;
    private final AppStateBuffer vn;
    
    public f(int paramInt, DataHolder paramDataHolder)
    {
      super(paramInt, localDataHolder);
      this.vm = paramDataHolder;
      this.vl = new Status(localDataHolder.getStatusCode());
      this.vn = new AppStateBuffer(localDataHolder);
    }
    
    private boolean cQ()
    {
      return this.vl.getStatusCode() == 2000;
    }
    
    public void a(a.c<AppStateManager.StateResult> paramc, DataHolder paramDataHolder)
    {
      paramc.b(this);
    }
    
    public AppStateManager.StateConflictResult getConflictResult()
    {
      if (cQ()) {
        return this;
      }
      return null;
    }
    
    public AppStateManager.StateLoadedResult getLoadedResult()
    {
      f localf = this;
      if (cQ()) {
        localf = null;
      }
      return localf;
    }
    
    public byte[] getLocalData()
    {
      if (this.vn.getCount() == 0) {
        return null;
      }
      return this.vn.get(0).getLocalData();
    }
    
    public String getResolvedVersion()
    {
      if (this.vn.getCount() == 0) {
        return null;
      }
      return this.vn.get(0).getConflictVersion();
    }
    
    public byte[] getServerData()
    {
      if (this.vn.getCount() == 0) {
        return null;
      }
      return this.vn.get(0).getConflictData();
    }
    
    public int getStateKey()
    {
      return this.vm;
    }
    
    public Status getStatus()
    {
      return this.vl;
    }
    
    public void release()
    {
      this.vn.close();
    }
  }
  
  final class g
    extends dk
  {
    a.c<Status> vj;
    
    public g()
    {
      Object localObject;
      this.vj = ((a.c)er.b(localObject, "Holder must not be null"));
    }
    
    public void cM()
    {
      Status localStatus = new Status(0);
      dl.this.a(new dl.h(dl.this, this.vj, localStatus));
    }
  }
  
  final class h
    extends eh<dn>.b<a.c<Status>>
  {
    private final Status vl;
    
    public h(Status paramStatus)
    {
      super(paramStatus);
      Status localStatus;
      this.vl = localStatus;
    }
    
    public void c(a.c<Status> paramc)
    {
      paramc.b(this.vl);
    }
    
    protected void cP() {}
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\dl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */