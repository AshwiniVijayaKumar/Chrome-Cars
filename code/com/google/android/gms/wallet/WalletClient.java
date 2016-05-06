package com.google.android.gms.wallet;

import android.app.Activity;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.internal.jg;

@Deprecated
public class WalletClient
  implements GooglePlayServicesClient
{
  private final jg Zx;
  
  public WalletClient(Activity paramActivity, int paramInt1, String paramString, int paramInt2, GooglePlayServicesClient.ConnectionCallbacks paramConnectionCallbacks, GooglePlayServicesClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    this.Zx = new jg(paramActivity, paramConnectionCallbacks, paramOnConnectionFailedListener, paramInt1, paramString, paramInt2);
  }
  
  public WalletClient(Activity paramActivity, int paramInt, String paramString, GooglePlayServicesClient.ConnectionCallbacks paramConnectionCallbacks, GooglePlayServicesClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    this(paramActivity, paramInt, paramString, 0, paramConnectionCallbacks, paramOnConnectionFailedListener);
  }
  
  public void changeMaskedWallet(String paramString1, String paramString2, int paramInt)
  {
    this.Zx.changeMaskedWallet(paramString1, paramString2, paramInt);
  }
  
  public void checkForPreAuthorization(int paramInt)
  {
    this.Zx.checkForPreAuthorization(paramInt);
  }
  
  public void connect()
  {
    this.Zx.connect();
  }
  
  public void disconnect()
  {
    this.Zx.disconnect();
  }
  
  public boolean isConnected()
  {
    return this.Zx.isConnected();
  }
  
  public boolean isConnecting()
  {
    return this.Zx.isConnecting();
  }
  
  public boolean isConnectionCallbacksRegistered(GooglePlayServicesClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    return this.Zx.isConnectionCallbacksRegistered(paramConnectionCallbacks);
  }
  
  public boolean isConnectionFailedListenerRegistered(GooglePlayServicesClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    return this.Zx.isConnectionFailedListenerRegistered(paramOnConnectionFailedListener);
  }
  
  public void loadFullWallet(FullWalletRequest paramFullWalletRequest, int paramInt)
  {
    this.Zx.loadFullWallet(paramFullWalletRequest, paramInt);
  }
  
  public void loadMaskedWallet(MaskedWalletRequest paramMaskedWalletRequest, int paramInt)
  {
    this.Zx.loadMaskedWallet(paramMaskedWalletRequest, paramInt);
  }
  
  public void notifyTransactionStatus(NotifyTransactionStatusRequest paramNotifyTransactionStatusRequest)
  {
    this.Zx.notifyTransactionStatus(paramNotifyTransactionStatusRequest);
  }
  
  public void registerConnectionCallbacks(GooglePlayServicesClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    this.Zx.registerConnectionCallbacks(paramConnectionCallbacks);
  }
  
  public void registerConnectionFailedListener(GooglePlayServicesClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    this.Zx.registerConnectionFailedListener(paramOnConnectionFailedListener);
  }
  
  public void unregisterConnectionCallbacks(GooglePlayServicesClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    this.Zx.unregisterConnectionCallbacks(paramConnectionCallbacks);
  }
  
  public void unregisterConnectionFailedListener(GooglePlayServicesClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    this.Zx.unregisterConnectionFailedListener(paramOnConnectionFailedListener);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\wallet\WalletClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */