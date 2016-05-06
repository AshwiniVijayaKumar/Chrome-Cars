package com.google.android.gms.plus;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.b;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ApiOptions;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.a.a;
import com.google.android.gms.internal.ee;
import com.google.android.gms.internal.er;
import com.google.android.gms.internal.ii;
import com.google.android.gms.internal.ij;
import com.google.android.gms.internal.ik;
import com.google.android.gms.internal.il;
import com.google.android.gms.plus.internal.PlusCommonExtras;
import com.google.android.gms.plus.internal.e;
import com.google.android.gms.plus.internal.h;
import java.util.HashSet;
import java.util.Set;

public final class Plus
{
  public static final Api API;
  public static final Account AccountApi = new ii(va);
  public static final Moments MomentsApi;
  public static final People PeopleApi;
  public static final a QK = new ij(va);
  public static final Scope SCOPE_PLUS_LOGIN;
  public static final Scope SCOPE_PLUS_PROFILE;
  static final Api.b<e> va = new Api.b()
  {
    public int getPriority()
    {
      return 2;
    }
    
    public e h(Context paramAnonymousContext, Looper paramAnonymousLooper, ee paramAnonymousee, GoogleApiClient.ApiOptions paramAnonymousApiOptions, GoogleApiClient.ConnectionCallbacks paramAnonymousConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramAnonymousOnConnectionFailedListener)
    {
      Object localObject = new Plus.PlusOptions(null);
      if (paramAnonymousApiOptions != null) {
        er.b(paramAnonymousApiOptions instanceof Plus.PlusOptions, "Must provide valid PlusOptions!");
      }
      for (paramAnonymousApiOptions = (Plus.PlusOptions)paramAnonymousApiOptions;; paramAnonymousApiOptions = (GoogleApiClient.ApiOptions)localObject)
      {
        localObject = paramAnonymousee.dR();
        paramAnonymousee = paramAnonymousee.dU();
        paramAnonymousApiOptions = (String[])paramAnonymousApiOptions.QM.toArray(new String[0]);
        String str1 = paramAnonymousContext.getPackageName();
        String str2 = paramAnonymousContext.getPackageName();
        PlusCommonExtras localPlusCommonExtras = new PlusCommonExtras();
        return new e(paramAnonymousContext, paramAnonymousLooper, paramAnonymousConnectionCallbacks, paramAnonymousOnConnectionFailedListener, new h((String)localObject, paramAnonymousee, paramAnonymousApiOptions, new String[0], str1, str2, null, localPlusCommonExtras));
      }
    }
  };
  
  static
  {
    API = new Api(va, new Scope[0]);
    SCOPE_PLUS_LOGIN = new Scope("https://www.googleapis.com/auth/plus.login");
    SCOPE_PLUS_PROFILE = new Scope("https://www.googleapis.com/auth/plus.me");
    MomentsApi = new ik(va);
    PeopleApi = new il(va);
  }
  
  public static e a(GoogleApiClient paramGoogleApiClient, Api.b<e> paramb)
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
  
  public static final class PlusOptions
    implements GoogleApiClient.ApiOptions
  {
    final String QL;
    final Set<String> QM;
    
    private PlusOptions()
    {
      this.QL = null;
      this.QM = new HashSet();
    }
    
    private PlusOptions(Builder paramBuilder)
    {
      this.QL = paramBuilder.QL;
      this.QM = paramBuilder.QM;
    }
    
    public static Builder builder()
    {
      return new Builder();
    }
    
    public static final class Builder
    {
      String QL;
      final Set<String> QM = new HashSet();
      
      public Builder addActivityTypes(String... paramVarArgs)
      {
        er.b(paramVarArgs, "activityTypes may not be null.");
        int i = 0;
        while (i < paramVarArgs.length)
        {
          this.QM.add(paramVarArgs[i]);
          i += 1;
        }
        return this;
      }
      
      public Plus.PlusOptions build()
      {
        return new Plus.PlusOptions(this, null);
      }
      
      public Builder setServerClientId(String paramString)
      {
        this.QL = paramString;
        return this;
      }
    }
  }
  
  public static abstract class a<R extends Result>
    extends a.a<R, e>
  {
    public a(Api.b<e> paramb)
    {
      super();
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\plus\Plus.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */