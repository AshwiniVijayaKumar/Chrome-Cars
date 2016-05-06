package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.a.c;
import com.google.android.gms.panorama.Panorama.PanoramaResult;
import com.google.android.gms.panorama.Panorama.a;

public class ih
  extends eh<ig>
{
  public ih(Context paramContext, Looper paramLooper, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    super(paramContext, paramLooper, paramConnectionCallbacks, paramOnConnectionFailedListener, (String[])null);
  }
  
  @Deprecated
  public ih(Context paramContext, GooglePlayServicesClient.ConnectionCallbacks paramConnectionCallbacks, GooglePlayServicesClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    this(paramContext, paramContext.getMainLooper(), new eh.c(paramConnectionCallbacks), new eh.g(paramOnConnectionFailedListener));
  }
  
  public void a(a.c<Panorama.PanoramaResult> paramc, Uri paramUri, boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (Uri localUri = paramUri;; localUri = null)
    {
      a(new b(null, paramc, localUri), paramUri, null, paramBoolean);
      return;
    }
  }
  
  protected void a(en paramen, eh.e parame)
    throws RemoteException
  {
    Bundle localBundle = new Bundle();
    paramen.a(parame, 4323000, getContext().getPackageName(), localBundle);
  }
  
  public void a(b paramb, Uri paramUri, Bundle paramBundle, boolean paramBoolean)
  {
    bm();
    if (paramBoolean) {
      getContext().grantUriPermission("com.google.android.gms", paramUri, 1);
    }
    try
    {
      ((ig)eb()).a(paramb, paramUri, paramBundle, paramBoolean);
      return;
    }
    catch (RemoteException paramUri)
    {
      paramb.a(8, null, 0, null);
    }
  }
  
  protected String aF()
  {
    return "com.google.android.gms.panorama.service.START";
  }
  
  protected String aG()
  {
    return "com.google.android.gms.panorama.internal.IPanoramaService";
  }
  
  public ig ax(IBinder paramIBinder)
  {
    return ig.a.aw(paramIBinder);
  }
  
  final class a
    extends eh<ig>.b<a.c<Panorama.a>>
    implements Panorama.a
  {
    public final Status QE;
    public final Intent QF;
    public final int type;
    
    public a(Status paramStatus, int paramInt, Intent paramIntent)
    {
      super(paramStatus);
      this.QE = paramInt;
      this.type = paramIntent;
      Intent localIntent;
      this.QF = localIntent;
    }
    
    protected void c(a.c<Panorama.a> paramc)
    {
      paramc.b(this);
    }
    
    protected void cP() {}
    
    public Status getStatus()
    {
      return this.QE;
    }
    
    public Intent getViewerIntent()
    {
      return this.QF;
    }
  }
  
  final class b
    extends if.a
  {
    private final a.c<Panorama.a> QH;
    private final a.c<Panorama.PanoramaResult> QI;
    private final Uri QJ;
    
    public b(a.c<Panorama.PanoramaResult> paramc, Uri paramUri)
    {
      this.QH = paramc;
      this.QI = paramUri;
      Uri localUri;
      this.QJ = localUri;
    }
    
    public void a(int paramInt1, Bundle paramBundle, int paramInt2, Intent paramIntent)
    {
      if (this.QJ != null) {
        ih.this.getContext().revokeUriPermission(this.QJ, 1);
      }
      if (paramBundle != null) {}
      for (paramBundle = (PendingIntent)paramBundle.getParcelable("pendingIntent");; paramBundle = null)
      {
        paramBundle = new Status(paramInt1, null, paramBundle);
        if (this.QI != null) {
          ih.this.a(new ih.c(ih.this, this.QI, paramBundle, paramIntent));
        }
        while (this.QH == null) {
          return;
        }
        ih.this.a(new ih.a(ih.this, this.QH, paramBundle, paramInt2, paramIntent));
        return;
      }
    }
  }
  
  final class c
    extends eh<ig>.b<a.c<Panorama.PanoramaResult>>
    implements Panorama.PanoramaResult
  {
    private final Status QE;
    private final Intent QF;
    
    public c(Status paramStatus, Intent paramIntent)
    {
      super(paramStatus);
      this.QE = paramIntent;
      Intent localIntent;
      this.QF = localIntent;
    }
    
    protected void c(a.c<Panorama.PanoramaResult> paramc)
    {
      paramc.b(this);
    }
    
    protected void cP() {}
    
    public Status getStatus()
    {
      return this.QE;
    }
    
    public Intent getViewerIntent()
    {
      return this.QF;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\ih.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */