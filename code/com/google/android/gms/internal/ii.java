package com.google.android.gms.internal;

import com.google.android.gms.common.api.Api.b;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.plus.Account;
import com.google.android.gms.plus.Plus.a;
import com.google.android.gms.plus.internal.e;

public final class ii
  implements Account
{
  private final Api.b<e> Rw;
  
  public ii(Api.b<e> paramb)
  {
    this.Rw = paramb;
  }
  
  private static e a(GoogleApiClient paramGoogleApiClient, Api.b<e> paramb)
  {
    boolean bool2 = true;
    if (paramGoogleApiClient != null)
    {
      bool1 = true;
      er.b(bool1, "GoogleApiClient parameter is required.");
      er.a(paramGoogleApiClient.isConnected(), "GoogleApiClient must be connected.");
      paramGoogleApiClient = (e)paramGoogleApiClient.a(paramb);
      if (paramGoogleApiClient == null) {
        break label55;
      }
    }
    label55:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      er.a(bool1, "GoogleApiClient is not configured to use the Plus.API Api. Pass this into GoogleApiClient.Builder#addApi() to use this feature.");
      return paramGoogleApiClient;
      bool1 = false;
      break;
    }
  }
  
  public void clearDefaultAccount(GoogleApiClient paramGoogleApiClient)
  {
    a(paramGoogleApiClient, this.Rw).clearDefaultAccount();
  }
  
  public String getAccountName(GoogleApiClient paramGoogleApiClient)
  {
    return a(paramGoogleApiClient, this.Rw).getAccountName();
  }
  
  public PendingResult<Status> revokeAccessAndDisconnect(GoogleApiClient paramGoogleApiClient)
  {
    paramGoogleApiClient.b(new a(this.Rw)
    {
      protected void a(e paramAnonymouse)
      {
        paramAnonymouse.k(this);
      }
    });
  }
  
  private static abstract class a
    extends Plus.a<Status>
  {
    a(Api.b<e> paramb)
    {
      super();
    }
    
    public Status f(Status paramStatus)
    {
      return paramStatus;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\ii.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */