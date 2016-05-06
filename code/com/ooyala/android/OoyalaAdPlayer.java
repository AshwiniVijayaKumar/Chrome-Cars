package com.ooyala.android;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.FrameLayout;
import com.ooyala.android.apis.FetchPlaybackInfoCallback;
import com.ooyala.android.item.AdSpot;
import com.ooyala.android.item.ContentItem;
import com.ooyala.android.player.AdMoviePlayer;
import com.ooyala.android.player.StreamPlayer;
import com.ooyala.android.util.DebugMode;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

class OoyalaAdPlayer
  extends AdMoviePlayer
{
  private static String TAG = OoyalaAdPlayer.class.getName();
  private OoyalaAdSpot _ad;
  private Object _fetchTask;
  private AdsLearnMoreButton _learnMore;
  private boolean _playQueued = false;
  private FrameLayout _playerLayout;
  private int _topMargin;
  
  private void dequeuePlay()
  {
    if (this._playQueued)
    {
      this._playQueued = false;
      play();
    }
  }
  
  private void initAfterFetch(OoyalaPlayer paramOoyalaPlayer)
  {
    super.init(paramOoyalaPlayer, this._ad.getStreams());
    if ((this._learnMore == null) && (this._ad.getClickURL() != null))
    {
      this._playerLayout = paramOoyalaPlayer.getLayout();
      this._topMargin = paramOoyalaPlayer.getTopBarOffset();
      this._learnMore = new AdsLearnMoreButton(this._playerLayout.getContext(), this, this._topMargin);
      this._playerLayout.addView(this._learnMore);
    }
    if (this._ad.getTrackingURLs() != null)
    {
      paramOoyalaPlayer = this._ad.getTrackingURLs().iterator();
      while (paramOoyalaPlayer.hasNext()) {
        ping((URL)paramOoyalaPlayer.next());
      }
    }
    dequeuePlay();
  }
  
  private void queuePlay()
  {
    this._playQueued = true;
  }
  
  public void destroy()
  {
    if (this._learnMore != null)
    {
      this._playerLayout.removeView(this._learnMore);
      this._learnMore.destroy();
      this._learnMore = null;
    }
    if (this._fetchTask != null) {
      this._parent.getPlayerAPIClient().cancel(this._fetchTask);
    }
    super.destroy();
  }
  
  public OoyalaAdSpot getAd()
  {
    return this._ad;
  }
  
  public void init(final OoyalaPlayer paramOoyalaPlayer, AdSpot paramAdSpot, StateNotifier paramStateNotifier)
  {
    super.init(paramOoyalaPlayer, paramAdSpot, paramStateNotifier);
    if (!(paramAdSpot instanceof OoyalaAdSpot))
    {
      this._error = new OoyalaException(OoyalaException.OoyalaErrorCode.ERROR_PLAYBACK_FAILED, "Invalid Ad");
      setState(OoyalaPlayer.State.ERROR);
      return;
    }
    DebugMode.logD(TAG, "Ooyala Ad Player Loaded");
    this._seekable = false;
    this._ad = ((OoyalaAdSpot)paramAdSpot);
    if ((!this._ad.isAuthorized()) && (this._ad.getAuthCode() > 0))
    {
      this._error = new OoyalaException(OoyalaException.OoyalaErrorCode.ERROR_PLAYBACK_FAILED, "This ad was unauthorized to play: " + ContentItem.getAuthError(this._ad.getAuthCode()));
      setState(OoyalaPlayer.State.ERROR);
      return;
    }
    if ((this._ad.getStream() == null) || (getBasePlayer() != null))
    {
      if (this._fetchTask != null) {
        this._parent.getPlayerAPIClient().cancel(this._fetchTask);
      }
      if (getBasePlayer() != null) {}
      for (paramAdSpot = getBasePlayer().getPlayerInfo();; paramAdSpot = StreamPlayer.defaultPlayerInfo)
      {
        this._fetchTask = this._ad.fetchPlaybackInfo(paramAdSpot, new FetchPlaybackInfoCallback()
        {
          public void callback(boolean paramAnonymousBoolean)
          {
            if (!OoyalaAdPlayer.this._ad.isAuthorized())
            {
              OoyalaAdPlayer.access$102(OoyalaAdPlayer.this, new OoyalaException(OoyalaException.OoyalaErrorCode.ERROR_PLAYBACK_FAILED, "Error fetching VAST XML"));
              OoyalaAdPlayer.this.setState(OoyalaPlayer.State.ERROR);
              return;
            }
            OoyalaAdPlayer.this.initAfterFetch(paramOoyalaPlayer);
          }
        });
        return;
      }
    }
    initAfterFetch(paramOoyalaPlayer);
  }
  
  public void play()
  {
    if (getBasePlayer() == null)
    {
      queuePlay();
      return;
    }
    super.play();
  }
  
  public void processClickThrough()
  {
    String str = this._ad.getClickURL().toString();
    try
    {
      str = str.trim();
      Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse(str));
      this._playerLayout.getContext().startActivity(localIntent);
      DebugMode.logD(TAG, "Opening brower to " + str);
      return;
    }
    catch (Exception localException)
    {
      DebugMode.logE(TAG, "There was some exception on clickthrough!");
      DebugMode.logE(TAG, "Caught!", localException);
    }
  }
  
  public void resume()
  {
    super.resume();
    if (this._learnMore != null) {
      this._playerLayout.bringChildToFront(this._learnMore);
    }
  }
  
  public void updateLearnMoreButton(FrameLayout paramFrameLayout, int paramInt)
  {
    if (this._topMargin == paramInt) {}
    while (this._learnMore == null) {
      return;
    }
    this._playerLayout.removeView(this._learnMore);
    this._playerLayout = paramFrameLayout;
    this._learnMore.setTopMargin(paramInt);
    this._playerLayout.addView(this._learnMore);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\OoyalaAdPlayer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */