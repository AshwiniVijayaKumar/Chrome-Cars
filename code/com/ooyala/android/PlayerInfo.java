package com.ooyala.android;

import java.util.Set;

public abstract interface PlayerInfo
{
  public abstract String getDevice();
  
  public abstract int getMaxBitrate();
  
  public abstract int getMaxHeight();
  
  public abstract int getMaxWidth();
  
  public abstract Set<String> getSupportedFormats();
  
  public abstract Set<String> getSupportedProfiles();
  
  public abstract String getUserAgent();
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\PlayerInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */