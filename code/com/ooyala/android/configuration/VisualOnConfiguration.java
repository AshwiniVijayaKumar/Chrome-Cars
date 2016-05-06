package com.ooyala.android.configuration;

public class VisualOnConfiguration
{
  public static String PRODUCTION_PERSONALIZATION_SERVER_URL = "http://perso.purpledrm.com/PersoServer/Personalization";
  private boolean disableLibraryVersionChecks;
  private int initialBitrate;
  private int initialBufferingTime;
  private int lowerBitrateThreshold;
  private int maxBufferingTime;
  private String personalizationServerUrl;
  private int playbackBufferingTime;
  private int upperBitrateThreshold;
  
  private VisualOnConfiguration(boolean paramBoolean, String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
  {
    this.disableLibraryVersionChecks = paramBoolean;
    this.personalizationServerUrl = paramString;
    this.upperBitrateThreshold = paramInt1;
    this.lowerBitrateThreshold = paramInt2;
    this.initialBitrate = paramInt3;
    this.maxBufferingTime = paramInt4;
    this.initialBufferingTime = paramInt5;
    this.playbackBufferingTime = paramInt6;
  }
  
  public static final VisualOnConfiguration s_getDefaultVisualOnConfiguration()
  {
    return new Builder().build();
  }
  
  public boolean getDisableLibraryVersionChecks()
  {
    return this.disableLibraryVersionChecks;
  }
  
  public int getInitialBitrate()
  {
    return this.initialBitrate;
  }
  
  public int getInitialBufferingTime()
  {
    return this.initialBufferingTime;
  }
  
  public int getLowerBitrateThreshold()
  {
    return this.lowerBitrateThreshold;
  }
  
  public int getMaxBufferingTime()
  {
    return this.maxBufferingTime;
  }
  
  public String getPersonalizationServerUrl()
  {
    return this.personalizationServerUrl;
  }
  
  public int getPlaybackBufferingTime()
  {
    return this.playbackBufferingTime;
  }
  
  public int getUpperBitrateThreshold()
  {
    return this.upperBitrateThreshold;
  }
  
  public static class Builder
  {
    private boolean disableLibraryVersionChecks = false;
    private int initialBitrate = -1;
    private int initialBufferingTime = -1;
    private int lowerBitrateThreshold = -1;
    private int maxBufferingTime = -1;
    private String personalizationServerUrl = "http://persopp.purpledrm.com/PersoServer/Personalization";
    private int playbackBufferingTime = -1;
    private int upperBitrateThreshold = -1;
    
    public VisualOnConfiguration build()
    {
      return new VisualOnConfiguration(this.disableLibraryVersionChecks, this.personalizationServerUrl, this.upperBitrateThreshold, this.lowerBitrateThreshold, this.initialBitrate, this.maxBufferingTime, this.initialBufferingTime, this.playbackBufferingTime, null);
    }
    
    public Builder setDisableLibraryVersionChecks(boolean paramBoolean)
    {
      this.disableLibraryVersionChecks = paramBoolean;
      return this;
    }
    
    public Builder setInitialBitrate(int paramInt)
    {
      this.initialBitrate = paramInt;
      return this;
    }
    
    public Builder setInitialBufferingTime(int paramInt)
    {
      this.initialBufferingTime = paramInt;
      return this;
    }
    
    public Builder setLowerBitrateThreshold(int paramInt)
    {
      this.lowerBitrateThreshold = paramInt;
      return this;
    }
    
    public Builder setMaxBufferingTime(int paramInt)
    {
      this.maxBufferingTime = paramInt;
      return this;
    }
    
    public Builder setPersonalizationServerUrl(String paramString)
    {
      this.personalizationServerUrl = paramString;
      return this;
    }
    
    public Builder setPlaybackBufferingTime(int paramInt)
    {
      this.playbackBufferingTime = paramInt;
      return this;
    }
    
    public Builder setUpperBitrateThreshold(int paramInt)
    {
      this.upperBitrateThreshold = paramInt;
      return this;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\configuration\VisualOnConfiguration.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */