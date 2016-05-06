package com.ooyala.android;

import com.ooyala.android.player.PlayerInterface;

public abstract interface CastManagerInterface
{
  public abstract void enterCastMode(CastModeOptions paramCastModeOptions);
  
  public abstract PlayerInterface getCastPlayer();
  
  public abstract boolean isConnectedToReceiverApp();
  
  public abstract boolean isInCastMode();
  
  public abstract void registerWithOoyalaPlayer(OoyalaPlayer paramOoyalaPlayer);
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\CastManagerInterface.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */