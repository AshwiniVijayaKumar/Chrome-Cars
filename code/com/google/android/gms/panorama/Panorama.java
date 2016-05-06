package com.google.android.gms.panorama;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.b;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ApiOptions;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.a.a;
import com.google.android.gms.internal.ee;
import com.google.android.gms.internal.ih;

public final class Panorama
{
  public static final Api API = new Api(va, new Scope[0]);
  static final Api.b<ih> va = new Api.b()
  {
    public ih g(Context paramAnonymousContext, Looper paramAnonymousLooper, ee paramAnonymousee, GoogleApiClient.ApiOptions paramAnonymousApiOptions, GoogleApiClient.ConnectionCallbacks paramAnonymousConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramAnonymousOnConnectionFailedListener)
    {
      return new ih(paramAnonymousContext, paramAnonymousLooper, paramAnonymousConnectionCallbacks, paramAnonymousOnConnectionFailedListener);
    }
    
    public int getPriority()
    {
      return Integer.MAX_VALUE;
    }
  };
  
  public static PendingResult<PanoramaResult> loadPanoramaInfo(GoogleApiClient paramGoogleApiClient, Uri paramUri)
  {
    paramGoogleApiClient.a(new b()
    {
      protected void a(ih paramAnonymousih)
      {
        paramAnonymousih.a(this, this.Qz, false);
      }
    });
  }
  
  public static PendingResult<PanoramaResult> loadPanoramaInfoAndGrantAccess(GoogleApiClient paramGoogleApiClient, Uri paramUri)
  {
    paramGoogleApiClient.a(new b()
    {
      protected void a(ih paramAnonymousih)
      {
        paramAnonymousih.a(this, this.Qz, true);
      }
    });
  }
  
  public static abstract interface PanoramaResult
    extends Result
  {
    public abstract Intent getViewerIntent();
  }
  
  public static abstract interface a
    extends Panorama.PanoramaResult
  {}
  
  private static abstract class b
    extends a.a<Panorama.PanoramaResult, ih>
  {
    public b()
    {
      super();
    }
    
    public Panorama.PanoramaResult J(final Status paramStatus)
    {
      new Panorama.PanoramaResult()
      {
        public Status getStatus()
        {
          return paramStatus;
        }
        
        public Intent getViewerIntent()
        {
          return null;
        }
      };
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\panorama\Panorama.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */