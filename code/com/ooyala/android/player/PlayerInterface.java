package com.ooyala.android.player;

import com.ooyala.android.OoyalaException;
import com.ooyala.android.OoyalaPlayer.State;

public abstract interface PlayerInterface
{
  public abstract int buffer();
  
  public abstract int currentTime();
  
  public abstract int duration();
  
  public abstract OoyalaException getError();
  
  public abstract OoyalaPlayer.State getState();
  
  public abstract boolean isLiveClosedCaptionsAvailable();
  
  public abstract int livePlayheadPercentage();
  
  public abstract void pause();
  
  public abstract void play();
  
  public abstract void seekToPercentLive(int paramInt);
  
  public abstract void seekToTime(int paramInt);
  
  public abstract boolean seekable();
  
  public abstract void setClosedCaptionsLanguage(String paramString);
  
  public abstract void stop();
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\player\PlayerInterface.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */