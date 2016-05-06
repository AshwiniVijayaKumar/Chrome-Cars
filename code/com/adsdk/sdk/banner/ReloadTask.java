package com.adsdk.sdk.banner;

import java.util.TimerTask;

class ReloadTask
  extends TimerTask
{
  private final AdView mWebView;
  
  public ReloadTask(AdView paramAdView)
  {
    this.mWebView = paramAdView;
  }
  
  public void run()
  {
    this.mWebView.loadNextAd();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\adsdk\sdk\banner\ReloadTask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */