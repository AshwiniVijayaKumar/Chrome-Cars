package com.google.android.gms.drive;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.b;
import com.google.android.gms.common.api.GoogleApiClient.ApiOptions;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.drive.internal.l;
import com.google.android.gms.drive.internal.n;
import com.google.android.gms.drive.internal.p;
import com.google.android.gms.internal.ee;
import java.util.List;

public final class Drive
{
  public static final Api API;
  public static final Scope Da;
  public static final c Db = new p();
  public static final DriveApi DriveApi;
  public static final Scope SCOPE_APPFOLDER;
  public static final Scope SCOPE_FILE;
  public static final Api.b<n> va = new Api.b()
  {
    public n d(Context paramAnonymousContext, Looper paramAnonymousLooper, ee paramAnonymousee, GoogleApiClient.ApiOptions paramAnonymousApiOptions, GoogleApiClient.ConnectionCallbacks paramAnonymousConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramAnonymousOnConnectionFailedListener)
    {
      paramAnonymousApiOptions = paramAnonymousee.dT();
      return new n(paramAnonymousContext, paramAnonymousLooper, paramAnonymousee, paramAnonymousConnectionCallbacks, paramAnonymousOnConnectionFailedListener, (String[])paramAnonymousApiOptions.toArray(new String[paramAnonymousApiOptions.size()]));
    }
    
    public int getPriority()
    {
      return Integer.MAX_VALUE;
    }
  };
  
  static
  {
    SCOPE_FILE = new Scope("https://www.googleapis.com/auth/drive.file");
    SCOPE_APPFOLDER = new Scope("https://www.googleapis.com/auth/drive.appdata");
    Da = new Scope("https://www.googleapis.com/auth/drive");
    API = new Api(va, new Scope[0]);
    DriveApi = new l();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\drive\Drive.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */