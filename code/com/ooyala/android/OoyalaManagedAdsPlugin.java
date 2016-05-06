package com.ooyala.android;

import com.ooyala.android.item.AdSpotManager;
import com.ooyala.android.item.OoyalaManagedAdSpot;
import com.ooyala.android.item.Stream;
import com.ooyala.android.item.Video;
import com.ooyala.android.player.AdMoviePlayer;
import com.ooyala.android.player.Player;
import com.ooyala.android.player.PlayerInterface;
import com.ooyala.android.plugin.AdPluginInterface;
import com.ooyala.android.plugin.ManagedAdsPlugin;
import com.ooyala.android.util.DebugMode;

public class OoyalaManagedAdsPlugin
  extends ManagedAdsPlugin<OoyalaManagedAdSpot>
  implements AdPluginInterface, StateNotifierListener
{
  private static final String TAG = OoyalaManagedAdsPlugin.class.getName();
  private AdMoviePlayer _adPlayer;
  protected OoyalaPlayer _player;
  private boolean _seekable = false;
  private StateNotifier _stateNotifier;
  
  public OoyalaManagedAdsPlugin(OoyalaPlayer paramOoyalaPlayer)
  {
    this._player = paramOoyalaPlayer;
    this._stateNotifier = paramOoyalaPlayer.createStateNotifier();
    this._stateNotifier.addListener(this);
  }
  
  private void cleanupExistingAdPlayer()
  {
    if (this._adPlayer != null)
    {
      cleanupPlayer(this._adPlayer);
      this._adPlayer = null;
    }
  }
  
  private void cleanupPlayer(Player paramPlayer)
  {
    if (paramPlayer != null) {
      paramPlayer.destroy();
    }
  }
  
  private boolean initializeAd(OoyalaManagedAdSpot paramOoyalaManagedAdSpot)
  {
    DebugMode.logD(TAG, "Ooyala Player: Playing Ad");
    cleanupExistingAdPlayer();
    localObject4 = null;
    try
    {
      Class localClass = this._player.getAdPlayerClass(paramOoyalaManagedAdSpot);
      localObject1 = localObject4;
      if (localClass != null) {
        localObject1 = (AdMoviePlayer)localClass.newInstance();
      }
    }
    catch (InstantiationException localInstantiationException)
    {
      for (;;)
      {
        Object localObject1;
        DebugMode.assertFail(TAG, localInstantiationException.toString());
        Object localObject2 = localObject4;
      }
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      Object localObject3;
      for (;;)
      {
        DebugMode.assertFail(TAG, localIllegalAccessException.toString());
        localObject3 = localObject4;
      }
      this._adPlayer = ((AdMoviePlayer)localObject3);
      if (initializeAdPlayer((AdMoviePlayer)localObject3, paramOoyalaManagedAdSpot)) {
        break label99;
      }
      this._adPlayer = null;
      return false;
    }
    return localObject1 != null;
  }
  
  private boolean initializeAdPlayer(AdMoviePlayer paramAdMoviePlayer, OoyalaManagedAdSpot paramOoyalaManagedAdSpot)
  {
    if (paramAdMoviePlayer == null)
    {
      DebugMode.assertFail(TAG, "initializeAdPlayer when adPlayer is null");
      return false;
    }
    if (paramOoyalaManagedAdSpot == null)
    {
      DebugMode.assertFail(TAG, "initializeAdPlayer when ad is null");
      return false;
    }
    paramAdMoviePlayer.init(this._player, paramOoyalaManagedAdSpot, this._stateNotifier);
    paramAdMoviePlayer.setSeekable(this._seekable);
    return true;
  }
  
  public void destroy()
  {
    if (this._adPlayer != null) {
      this._adPlayer.destroy();
    }
  }
  
  public PlayerInterface getPlayerInterface()
  {
    return this._adPlayer;
  }
  
  public void insertAd(OoyalaManagedAdSpot paramOoyalaManagedAdSpot)
  {
    this._adSpotManager.insertAd(paramOoyalaManagedAdSpot);
  }
  
  public boolean onContentChanged()
  {
    super.onContentChanged();
    this._adSpotManager.insertAds(this._player.getCurrentItem().getAds());
    if (Stream.streamSetContainsDeliveryType(this._player.getCurrentItem().getStreams(), "hls")) {
      this._adSpotManager.setAlignment(10000);
    }
    return false;
  }
  
  public void onStateChange(StateNotifier paramStateNotifier)
  {
    if ((this._adPlayer == null) || (this._adPlayer.getNotifier() != paramStateNotifier)) {}
    do
    {
      return;
      switch (paramStateNotifier.getState())
      {
      default: 
        return;
      }
    } while (playAdsBeforeTime());
    cleanupPlayer(this._adPlayer);
    this._player.exitAdMode(this);
    return;
    DebugMode.logE(TAG, "Error recieved from Ad.  Cleaning up everything");
    cleanupPlayer(this._adPlayer);
    this._player.exitAdMode(this);
  }
  
  protected boolean playAd(OoyalaManagedAdSpot paramOoyalaManagedAdSpot)
  {
    if (!initializeAd(paramOoyalaManagedAdSpot)) {
      return false;
    }
    this._adPlayer.play();
    return true;
  }
  
  public void reset() {}
  
  public void resume()
  {
    if (this._adPlayer != null) {
      this._adPlayer.resume();
    }
  }
  
  public void resume(int paramInt, OoyalaPlayer.State paramState)
  {
    if (this._adPlayer != null) {
      this._adPlayer.resume(paramInt, paramState);
    }
  }
  
  public void setSeekable(boolean paramBoolean)
  {
    this._seekable = paramBoolean;
    if (this._adPlayer != null) {
      this._adPlayer.setSeekable(this._seekable);
    }
  }
  
  public void suspend()
  {
    if (this._adPlayer != null) {
      this._adPlayer.suspend();
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\OoyalaManagedAdsPlugin.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */