package com.google.android.gms.ads;

import android.content.Context;
import com.google.android.gms.internal.al;

public final class InterstitialAd
{
  private final al kE;
  
  public InterstitialAd(Context paramContext)
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
  
  public boolean isLoaded()
  {
    return this.kE.isLoaded();
  }
  
  public void loadAd(AdRequest paramAdRequest)
  {
    this.kE.a(paramAdRequest.N());
  }
  
  public void setAdListener(AdListener paramAdListener)
  {
    this.kE.setAdListener(paramAdListener);
  }
  
  public void setAdUnitId(String paramString)
  {
    this.kE.setAdUnitId(paramString);
  }
  
  public void show()
  {
    this.kE.show();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\ads\InterstitialAd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */