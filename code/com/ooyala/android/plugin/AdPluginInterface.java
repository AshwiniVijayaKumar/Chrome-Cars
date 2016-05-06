package com.ooyala.android.plugin;

import com.ooyala.android.player.PlayerInterface;
import java.util.Set;

public abstract interface AdPluginInterface
  extends LifeCycleInterface
{
  public abstract Set<Integer> getCuePointsInMilliSeconds();
  
  public abstract PlayerInterface getPlayerInterface();
  
  public abstract void onAdModeEntered();
  
  public abstract boolean onContentChanged();
  
  public abstract boolean onContentError(int paramInt);
  
  public abstract boolean onContentFinished();
  
  public abstract boolean onCuePoint(int paramInt);
  
  public abstract boolean onInitialPlay();
  
  public abstract boolean onPlayheadUpdate(int paramInt);
  
  public abstract void resetAds();
  
  public abstract void skipAd();
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\plugin\AdPluginInterface.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */