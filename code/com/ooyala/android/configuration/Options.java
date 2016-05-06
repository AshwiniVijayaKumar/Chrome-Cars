package com.ooyala.android.configuration;

public class Options
  implements ReadonlyOptionsInterface
{
  private final int connectionTimeoutInMillisecond;
  private final boolean preloadContent;
  private final boolean preventVideoViewSharing;
  private final int readTimeoutInMillisecond;
  private final boolean showAdsControls;
  private final boolean showCuePoints;
  private final boolean showLiveControls;
  private final boolean showPromoImage;
  private final FCCTVRatingConfiguration tvRatingConfiguration;
  private final VisualOnConfiguration visualOnConfiguration;
  
  private Options(FCCTVRatingConfiguration paramFCCTVRatingConfiguration, VisualOnConfiguration paramVisualOnConfiguration, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, boolean paramBoolean5, int paramInt1, int paramInt2, boolean paramBoolean6)
  {
    this.tvRatingConfiguration = paramFCCTVRatingConfiguration;
    this.visualOnConfiguration = paramVisualOnConfiguration;
    this.showCuePoints = paramBoolean1;
    this.showAdsControls = paramBoolean2;
    this.preloadContent = paramBoolean3;
    this.showPromoImage = paramBoolean4;
    this.showLiveControls = paramBoolean5;
    this.connectionTimeoutInMillisecond = paramInt1;
    this.readTimeoutInMillisecond = paramInt2;
    this.preventVideoViewSharing = paramBoolean6;
  }
  
  public int getConnectionTimeoutInMillisecond()
  {
    return this.connectionTimeoutInMillisecond;
  }
  
  public boolean getPreloadContent()
  {
    return this.preloadContent;
  }
  
  public boolean getPreventVideoViewSharing()
  {
    return this.preventVideoViewSharing;
  }
  
  public int getReadTimeoutInMillisecond()
  {
    return this.readTimeoutInMillisecond;
  }
  
  public boolean getShowAdsControls()
  {
    return this.showAdsControls;
  }
  
  public boolean getShowCuePoints()
  {
    return this.showCuePoints;
  }
  
  public boolean getShowLiveControls()
  {
    return this.showLiveControls;
  }
  
  public boolean getShowPromoImage()
  {
    return this.showPromoImage;
  }
  
  public FCCTVRatingConfiguration getTVRatingConfiguration()
  {
    return this.tvRatingConfiguration;
  }
  
  public VisualOnConfiguration getVisualOnConfiguration()
  {
    return this.visualOnConfiguration;
  }
  
  public static class Builder
  {
    private int connectionTimeoutInMillisecond = 0;
    private boolean preloadContent = true;
    private boolean preventVideoViewSharing = false;
    private int readTimeoutInMillisecond = 0;
    private boolean showAdsControls = true;
    private boolean showCuePoints = true;
    private boolean showLiveControls = true;
    private boolean showPromoImage = false;
    private FCCTVRatingConfiguration tvRatingConfiguration = FCCTVRatingConfiguration.s_getDefaultTVRatingConfiguration();
    private VisualOnConfiguration visualOnConfiguration = VisualOnConfiguration.s_getDefaultVisualOnConfiguration();
    
    public Options build()
    {
      return new Options(this.tvRatingConfiguration, this.visualOnConfiguration, this.showCuePoints, this.showAdsControls, this.preloadContent, this.showPromoImage, this.showLiveControls, this.connectionTimeoutInMillisecond, this.readTimeoutInMillisecond, this.preventVideoViewSharing, null);
    }
    
    public Builder setConnectionTimeout(int paramInt)
    {
      this.connectionTimeoutInMillisecond = paramInt;
      return this;
    }
    
    public Builder setPreloadContent(boolean paramBoolean)
    {
      this.preloadContent = paramBoolean;
      return this;
    }
    
    public Builder setPreventVideoViewSharing(boolean paramBoolean)
    {
      this.preventVideoViewSharing = paramBoolean;
      return this;
    }
    
    public Builder setReadTimeout(int paramInt)
    {
      this.readTimeoutInMillisecond = paramInt;
      return this;
    }
    
    public Builder setShowAdsControls(boolean paramBoolean)
    {
      this.showAdsControls = paramBoolean;
      return this;
    }
    
    public Builder setShowCuePoints(boolean paramBoolean)
    {
      this.showCuePoints = paramBoolean;
      return this;
    }
    
    public Builder setShowLiveControls(boolean paramBoolean)
    {
      this.showLiveControls = paramBoolean;
      return this;
    }
    
    public Builder setShowPromoImage(boolean paramBoolean)
    {
      this.showPromoImage = paramBoolean;
      return this;
    }
    
    public Builder setTVRatingConfiguration(FCCTVRatingConfiguration paramFCCTVRatingConfiguration)
    {
      this.tvRatingConfiguration = paramFCCTVRatingConfiguration;
      return this;
    }
    
    public Builder setVisualOnConfiguration(VisualOnConfiguration paramVisualOnConfiguration)
    {
      this.visualOnConfiguration = paramVisualOnConfiguration;
      return this;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\configuration\Options.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */