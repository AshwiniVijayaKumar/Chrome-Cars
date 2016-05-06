package com.google.android.gms.wallet;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
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
import com.google.android.gms.internal.jg;

public final class Wallet
{
  public static final Api API = new Api(va, new Scope[0]);
  static final Api.b<jg> va = new Api.b()
  {
    public int getPriority()
    {
      return Integer.MAX_VALUE;
    }
    
    public jg i(Context paramAnonymousContext, Looper paramAnonymousLooper, ee paramAnonymousee, GoogleApiClient.ApiOptions paramAnonymousApiOptions, GoogleApiClient.ConnectionCallbacks paramAnonymousConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramAnonymousOnConnectionFailedListener)
    {
      er.b(paramAnonymousContext instanceof Activity, "An Activity must be used for Wallet APIs");
      Activity localActivity = (Activity)paramAnonymousContext;
      boolean bool;
      if ((paramAnonymousApiOptions == null) || ((paramAnonymousApiOptions instanceof Wallet.WalletOptions)))
      {
        bool = true;
        er.b(bool, "WalletOptions must be used for Wallet APIs");
        if (paramAnonymousApiOptions == null) {
          break label82;
        }
      }
      label82:
      for (paramAnonymousContext = (Wallet.WalletOptions)paramAnonymousApiOptions;; paramAnonymousContext = new Wallet.WalletOptions(null))
      {
        return new jg(localActivity, paramAnonymousLooper, paramAnonymousConnectionCallbacks, paramAnonymousOnConnectionFailedListener, paramAnonymousContext.environment, paramAnonymousee.getAccountName(), paramAnonymousContext.theme);
        bool = false;
        break;
      }
    }
  };
  
  public static void changeMaskedWallet(GoogleApiClient paramGoogleApiClient, String paramString1, final String paramString2, final int paramInt)
  {
    paramGoogleApiClient.a(new a()
    {
      protected void a(jg paramAnonymousjg)
      {
        paramAnonymousjg.changeMaskedWallet(this.Zt, paramString2, paramInt);
        a(Status.zQ);
      }
    });
  }
  
  public static void checkForPreAuthorization(GoogleApiClient paramGoogleApiClient, int paramInt)
  {
    paramGoogleApiClient.a(new a()
    {
      protected void a(jg paramAnonymousjg)
      {
        paramAnonymousjg.checkForPreAuthorization(this.Kx);
        a(Status.zQ);
      }
    });
  }
  
  public static void loadFullWallet(GoogleApiClient paramGoogleApiClient, FullWalletRequest paramFullWalletRequest, final int paramInt)
  {
    paramGoogleApiClient.a(new a()
    {
      protected void a(jg paramAnonymousjg)
      {
        paramAnonymousjg.loadFullWallet(this.Zs, paramInt);
        a(Status.zQ);
      }
    });
  }
  
  public static void loadMaskedWallet(GoogleApiClient paramGoogleApiClient, MaskedWalletRequest paramMaskedWalletRequest, final int paramInt)
  {
    paramGoogleApiClient.a(new a()
    {
      protected void a(jg paramAnonymousjg)
      {
        paramAnonymousjg.loadMaskedWallet(this.Zr, paramInt);
        a(Status.zQ);
      }
    });
  }
  
  public static void notifyTransactionStatus(GoogleApiClient paramGoogleApiClient, NotifyTransactionStatusRequest paramNotifyTransactionStatusRequest)
  {
    paramGoogleApiClient.a(new a()
    {
      protected void a(jg paramAnonymousjg)
      {
        paramAnonymousjg.notifyTransactionStatus(this.Zv);
        a(Status.zQ);
      }
    });
  }
  
  public static final class WalletOptions
    implements GoogleApiClient.ApiOptions
  {
    public final int environment;
    public final int theme;
    
    private WalletOptions()
    {
      this(new Builder());
    }
    
    private WalletOptions(Builder paramBuilder)
    {
      this.environment = Builder.a(paramBuilder);
      this.theme = Builder.b(paramBuilder);
    }
    
    public static final class Builder
    {
      private int Zw = 0;
      private int mTheme = 0;
      
      public Wallet.WalletOptions build()
      {
        return new Wallet.WalletOptions(this, null);
      }
      
      public Builder setEnvironment(int paramInt)
      {
        if ((paramInt == 0) || (paramInt == 2) || (paramInt == 1))
        {
          this.Zw = paramInt;
          return this;
        }
        throw new IllegalArgumentException(String.format("Invalid environment value %d", new Object[] { Integer.valueOf(paramInt) }));
      }
      
      public Builder setTheme(int paramInt)
      {
        if ((paramInt == 0) || (paramInt == 1))
        {
          this.mTheme = paramInt;
          return this;
        }
        throw new IllegalArgumentException(String.format("Invalid theme value %d", new Object[] { Integer.valueOf(paramInt) }));
      }
    }
  }
  
  private static abstract class a
    extends a.a<Status, jg>
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


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\wallet\Wallet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */