package com.google.android.gms.ads.doubleclick;

import android.content.Context;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.internal.al;

public final class PublisherInterstitialAd
{
  private final al kE;
  
  public PublisherInterstitialAd(Context paramContext)
  {
    this.kE = new al(paramContext);
  }
  
  public AdListener getAdListener()
  {
    return this.kE.getAdListener();
  }
  
  public String getAdUnitId()
  {
    return this.kE.getAdUnitId();
  }
  
  public AppEventListener getAppEventListener()
  {
    return this.kE.getAppEventListener();
  }
  
  public boolean isLoaded()
  {
    return this.kE.isLoaded();
  }
  
  public void loadAd(PublisherAdRequest paramPublisherAdRequest)
  {
    this.kE.a(paramPublisherAdRequest.N());
  }
  
  public void setAdListener(AdListener paramAdListener)
  {
    this.kE.setAdListener(paramAdListener);
  }
  
  public void setAdUnitId(String paramString)
  {
    this.kE.setAdUnitId(paramString);
  }
  
  public void setAppEventListener(AppEventListener paramAppEventListener)
  {
    this.kE.setAppEventListener(paramAppEventListener);
  }
  
  public void show()
  {
    this.kE.show();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\ads\doubleclick\PublisherInterstitialAd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */