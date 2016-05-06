package com.ooyala.android;

public class CastModeOptions
{
  private String authToken;
  private String ccLanguage;
  private PlayerDomain domain;
  private String embedCode;
  private EmbedTokenGenerator generator;
  private boolean isPlaying;
  private String pcode;
  private int playheadTimeInMillis;
  
  public CastModeOptions(String paramString1, int paramInt, boolean paramBoolean, EmbedTokenGenerator paramEmbedTokenGenerator, String paramString2, String paramString3, String paramString4, PlayerDomain paramPlayerDomain)
  {
    this.embedCode = paramString1;
    this.playheadTimeInMillis = paramInt;
    this.isPlaying = paramBoolean;
    this.generator = paramEmbedTokenGenerator;
    this.ccLanguage = paramString2;
    this.authToken = paramString3;
    this.pcode = paramString4;
    this.domain = paramPlayerDomain;
  }
  
  public String getAuthToken()
  {
    return this.authToken;
  }
  
  public String getCCLanguage()
  {
    return this.ccLanguage;
  }
  
  public PlayerDomain getDomain()
  {
    return this.domain;
  }
  
  public String getEmbedCode()
  {
    return this.embedCode;
  }
  
  public EmbedTokenGenerator getGenerator()
  {
    return this.generator;
  }
  
  public String getPcode()
  {
    return this.pcode;
  }
  
  public int getPlayheadTimeInMillis()
  {
    return this.playheadTimeInMillis;
  }
  
  public boolean isPlaying()
  {
    return this.isPlaying;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\CastModeOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */