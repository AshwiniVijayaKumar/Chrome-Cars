package com.google.android.gms.identity.intents;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.b;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ApiOptions;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.a.a;
import com.google.android.gms.internal.ee;
import com.google.android.gms.internal.er;
import com.google.android.gms.internal.hc;

public final class Address
{
  public static final Api API = new Api(va, new Scope[0]);
  static final Api.b<hc> va = new Api.b()
  {
    public hc f(Context paramAnonymousContext, Looper paramAnonymousLooper, ee paramAnonymousee, GoogleApiClient.ApiOptions paramAnonymousApiOptions, GoogleApiClient.ConnectionCallbacks paramAnonymousConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramAnonymousOnConnectionFailedListener)
    {
      er.b(paramAnonymousContext instanceof Activity, "An Activity must be used for Address APIs");
      Address.AddressOptions localAddressOptions = new Address.AddressOptions();
      if (paramAnonymousApiOptions != null) {
        er.b(paramAnonymousApiOptions instanceof Address.AddressOptions, "Must use AddressOptions with Address API");
      }
      for (paramAnonymousApiOptions = (Address.AddressOptions)paramAnonymousApiOptions;; paramAnonymousApiOptions = localAddressOptions) {
        return new hc((Activity)paramAnonymousContext, paramAnonymousLooper, paramAnonymousConnectionCallbacks, paramAnonymousOnConnectionFailedListener, paramAnonymousee.getAccountName(), paramAnonymousApiOptions.theme);
      }
    }
    
    public int getPriority()
    {
      return Integer.MAX_VALUE;
    }
  };
  
  public static void requestUserAddress(GoogleApiClient paramGoogleApiClient, UserAddressRequest paramUserAddressRequest, final int paramInt)
  {
    paramGoogleApiClient.a(new a()
    {
      protected void a(hc paramAnonymoushc)
        throws RemoteException
      {
        paramAnonymoushc.a(this.Kw, paramInt);
        a(Status.zQ);
      }
    });
  }
  
  public static final class AddressOptions
    implements GoogleApiClient.ApiOptions
  {
    public final int theme;
    
    public AddressOptions()
    {
      this.theme = 0;
    }
    
    public AddressOptions(int paramInt)
    {
      this.theme = paramInt;
    }
  }
  
  private static abstract class a
    extends a.a<Status, hc>
  {
    public a()
    {
      super();
    }
    
    public Status f(Status paramStatus)
    {
      return paramStatus;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\identity\intents\Address.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */