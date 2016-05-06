package com.google.android.gms.internal;

import com.google.android.gms.ads.AdListener;

public final class x
  extends af.a
{
  private final AdListener lc;
  
  public x(AdListener paramAdListener)
  {
    this.lc = paramAdListener;
  }
  
  public void onAdClosed()
  {
    this.lc.onAdClosed();
  }
  
  public void onAdFailedToLoad(int paramInt)
  {
    this.lc.onAdFailedToLoad(paramInt);
  }
  
  public void onAdLeftApplication()
  {
    this.lc.onAdLeftApplication();
  }
  
  public void onAdLoaded()
  {
    this.lc.onAdLoaded();
  }
  
  public void onAdOpened()
  {
    this.lc.onAdOpened();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\x.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */