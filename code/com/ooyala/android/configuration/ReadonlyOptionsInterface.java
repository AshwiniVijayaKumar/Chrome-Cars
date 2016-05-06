package com.ooyala.android.configuration;

public abstract interface ReadonlyOptionsInterface
{
  public abstract int getConnectionTimeoutInMillisecond();
  
  public abstract boolean getPreloadContent();
  
  public abstract boolean getPreventVideoViewSharing();
  
  public abstract int getReadTimeoutInMillisecond();
  
  public abstract boolean getShowAdsControls();
  
  public abstract boolean getShowCuePoints();
  
  public abstract boolean getShowLiveControls();
  
  public abstract boolean getShowPromoImage();
  
  public abstract FCCTVRatingConfiguration getTVRatingConfiguration();
  
  public abstract VisualOnConfiguration getVisualOnConfiguration();
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\configuration\ReadonlyOptionsInterface.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */