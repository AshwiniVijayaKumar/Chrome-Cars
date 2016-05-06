package com.ooyala.android.plugin;

import com.ooyala.android.item.AdSpot;
import com.ooyala.android.item.AdSpotManager;
import com.ooyala.android.util.DebugMode;
import java.util.Set;

public abstract class ManagedAdsPlugin<T extends AdSpot>
  implements AdPluginInterface
{
  protected static final int CONTENT_CHANGED = -1;
  protected static final int PLUGIN_INIT = -2;
  private static final String TAG = ManagedAdsPlugin.class.getName();
  protected AdSpotManager<T> _adSpotManager = new AdSpotManager();
  private int _lastAdModeTime = -2;
  
  public Set<Integer> getCuePointsInMilliSeconds()
  {
    return this._adSpotManager.getCuePointsInMilliSeconds();
  }
  
  protected int getLastAdModeTime()
  {
    return this._lastAdModeTime;
  }
  
  public void onAdModeEntered()
  {
    playAdsBeforeTime();
  }
  
  public boolean onContentChanged()
  {
    this._lastAdModeTime = -1;
    this._adSpotManager.clear();
    return false;
  }
  
  public boolean onContentError(int paramInt)
  {
    return false;
  }
  
  public boolean onContentFinished()
  {
    this._lastAdModeTime = Integer.MAX_VALUE;
    return this._adSpotManager.adBeforeTime(this._lastAdModeTime) != null;
  }
  
  public boolean onCuePoint(int paramInt)
  {
    return false;
  }
  
  public boolean onInitialPlay()
  {
    boolean bool = false;
    DebugMode.logD(TAG, "onInitialPlay");
    this._lastAdModeTime = 0;
    if (this._adSpotManager.adBeforeTime(this._lastAdModeTime) != null) {
      bool = true;
    }
    return bool;
  }
  
  public boolean onPlayheadUpdate(int paramInt)
  {
    this._lastAdModeTime = paramInt;
    return this._adSpotManager.adBeforeTime(this._lastAdModeTime) != null;
  }
  
  protected abstract boolean playAd(T paramT);
  
  protected boolean playAdsBeforeTime()
  {
    AdSpot localAdSpot = this._adSpotManager.adBeforeTime(this._lastAdModeTime);
    if (localAdSpot == null) {
      return false;
    }
    this._adSpotManager.markAsPlayed(localAdSpot);
    return playAd(localAdSpot);
  }
  
  public void resetAds()
  {
    this._adSpotManager.resetAds();
  }
  
  public void skipAd()
  {
    playAdsBeforeTime();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\plugin\ManagedAdsPlugin.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */