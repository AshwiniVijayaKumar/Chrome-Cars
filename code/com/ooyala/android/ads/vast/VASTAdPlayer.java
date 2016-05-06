package com.ooyala.android.ads.vast;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.FrameLayout;
import com.ooyala.android.AdsLearnMoreButton;
import com.ooyala.android.OoyalaAPIClient;
import com.ooyala.android.OoyalaException;
import com.ooyala.android.OoyalaException.OoyalaErrorCode;
import com.ooyala.android.OoyalaPlayer;
import com.ooyala.android.OoyalaPlayer.State;
import com.ooyala.android.StateNotifier;
import com.ooyala.android.apis.FetchPlaybackInfoCallback;
import com.ooyala.android.item.AdSpot;
import com.ooyala.android.player.AdMoviePlayer;
import com.ooyala.android.player.BaseStreamPlayer;
import com.ooyala.android.util.DebugMode;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Set;

public class VASTAdPlayer
  extends AdMoviePlayer
{
  private static String TAG = VASTAdPlayer.class.getName();
  private VASTAdSpot _ad;
  private int _adIndex;
  private Object _fetchTask;
  private boolean _firstQSent = false;
  private AdsLearnMoreButton _learnMore;
  private List<VASTLinearAd> _linearAdQueue = new ArrayList();
  private boolean _midSent = false;
  private boolean _playQueued = false;
  private FrameLayout _playerLayout;
  private boolean _startSent = false;
  private boolean _thirdQSent = false;
  private int _topMargin;
  
  private void addQuartileBoundaryObserver()
  {
    this._startSent = false;
    this._firstQSent = false;
    this._midSent = false;
    this._thirdQSent = false;
  }
  
  private VASTLinearAd currentLinearAd()
  {
    if (this._linearAdQueue.isEmpty()) {
      return null;
    }
    return (VASTLinearAd)this._linearAdQueue.get(0);
  }
  
  private void dequeuePlay()
  {
    if (this._playQueued)
    {
      this._playQueued = false;
      play();
    }
  }
  
  private List<String> impressionUrlForAdIndex(int paramInt, List<VASTAd> paramList)
  {
    return ((VASTAd)paramList.get(paramInt)).getImpressionURLs();
  }
  
  private boolean initAfterFetch(OoyalaPlayer paramOoyalaPlayer)
  {
    this._adIndex = 0;
    Iterator localIterator1 = this._ad.getAds().iterator();
    while (localIterator1.hasNext())
    {
      Iterator localIterator2 = ((VASTAd)localIterator1.next()).getSequence().iterator();
      while (localIterator2.hasNext())
      {
        VASTSequenceItem localVASTSequenceItem = (VASTSequenceItem)localIterator2.next();
        if ((localVASTSequenceItem.hasLinear()) && (localVASTSequenceItem.getLinear().getStream() != null)) {
          this._linearAdQueue.add(localVASTSequenceItem.getLinear());
        }
      }
    }
    if (this._linearAdQueue.isEmpty()) {
      return false;
    }
    if ((this._linearAdQueue.get(0) == null) || (((VASTLinearAd)this._linearAdQueue.get(0)).getStreams() == null)) {
      return false;
    }
    addQuartileBoundaryObserver();
    super.init(paramOoyalaPlayer, ((VASTLinearAd)this._linearAdQueue.get(0)).getStreams());
    this._playerLayout = paramOoyalaPlayer.getLayout();
    this._topMargin = paramOoyalaPlayer.getTopBarOffset();
    if ((currentLinearAd() != null) && (currentLinearAd().getClickThroughURL() != null))
    {
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
    return true;
  }
  
  private boolean isCurrentAdIFirstLinearForAdIndex(int paramInt, List<VASTAd> paramList)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramList != null)
    {
      bool1 = bool2;
      if (paramList.size() != 0) {
        bool1 = currentLinearAd().equals(vastLinearAdsForAdIndex(paramInt, paramList).get(0));
      }
    }
    return bool1;
  }
  
  private boolean isCurrentAdLastLinearForAdIndex(int paramInt, List<VASTAd> paramList)
  {
    if ((paramList != null) && (paramList.size() != 0)) {
      return currentLinearAd().equals(vastLinearAdsForAdIndex(paramInt, paramList).get(paramList.size() - 1));
    }
    return false;
  }
  
  private void queuePlay()
  {
    this._playQueued = true;
  }
  
  private void sendImpressionTrackingEvent(int paramInt, List<VASTAd> paramList)
  {
    paramList = impressionUrlForAdIndex(paramInt, paramList);
    if (paramList != null)
    {
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        URL localURL = VASTUtils.urlFromAdUrlString((String)paramList.next());
        DebugMode.logI(TAG, "Sending Impression Tracking Ping: " + localURL);
        ping(localURL);
      }
    }
  }
  
  private List<VASTLinearAd> vastLinearAdsForAdIndex(int paramInt, List<VASTAd> paramList)
  {
    ArrayList localArrayList = new ArrayList();
    paramList = ((VASTAd)paramList.get(paramInt)).getSequence().iterator();
    while (paramList.hasNext())
    {
      VASTSequenceItem localVASTSequenceItem = (VASTSequenceItem)paramList.next();
      if ((localVASTSequenceItem.hasLinear()) && (localVASTSequenceItem.getLinear().getStream() != null)) {
        localArrayList.add(localVASTSequenceItem.getLinear());
      }
    }
    return localArrayList;
  }
  
  public void destroy()
  {
    if (this._learnMore != null)
    {
      this._playerLayout.removeView(this._learnMore);
      this._learnMore.destroy();
      this._learnMore = null;
    }
    if ((this._fetchTask != null) && (this._parent != null)) {
      this._parent.getOoyalaAPIClient().cancel(this._fetchTask);
    }
    deleteObserver(this);
    super.destroy();
  }
  
  public VASTAdSpot getAd()
  {
    return this._ad;
  }
  
  public void init(final OoyalaPlayer paramOoyalaPlayer, AdSpot paramAdSpot, StateNotifier paramStateNotifier)
  {
    super.init(paramOoyalaPlayer, paramAdSpot, paramStateNotifier);
    if (!(paramAdSpot instanceof VASTAdSpot))
    {
      this._error = new OoyalaException(OoyalaException.OoyalaErrorCode.ERROR_PLAYBACK_FAILED, "Invalid Ad");
      setState(OoyalaPlayer.State.ERROR);
    }
    do
    {
      return;
      DebugMode.logD(TAG, "VAST Ad Player Loaded");
      this._seekable = false;
      this._ad = ((VASTAdSpot)paramAdSpot);
      if ((this._ad.getAds() == null) || (this._ad.getAds().isEmpty()))
      {
        if (this._fetchTask != null) {
          this._parent.getOoyalaAPIClient().cancel(this._fetchTask);
        }
        this._fetchTask = this._ad.fetchPlaybackInfo(new FetchPlaybackInfoCallback()
        {
          public void callback(boolean paramAnonymousBoolean)
          {
            if (!paramAnonymousBoolean)
            {
              VASTAdPlayer.access$002(VASTAdPlayer.this, new OoyalaException(OoyalaException.OoyalaErrorCode.ERROR_PLAYBACK_FAILED, "Could not fetch VAST Ad"));
              VASTAdPlayer.this.setState(OoyalaPlayer.State.ERROR);
            }
            while (VASTAdPlayer.this.initAfterFetch(paramOoyalaPlayer)) {
              return;
            }
            VASTAdPlayer.access$202(VASTAdPlayer.this, new OoyalaException(OoyalaException.OoyalaErrorCode.ERROR_PLAYBACK_FAILED, "Bad VAST Ad"));
            VASTAdPlayer.this.setState(OoyalaPlayer.State.ERROR);
          }
        });
        return;
      }
    } while (initAfterFetch(paramOoyalaPlayer));
    this._error = new OoyalaException(OoyalaException.OoyalaErrorCode.ERROR_PLAYBACK_FAILED, "Bad VAST Ad");
    setState(OoyalaPlayer.State.ERROR);
  }
  
  public void pause()
  {
    if (this._linearAdQueue.isEmpty())
    {
      setState(OoyalaPlayer.State.COMPLETED);
      return;
    }
    if (getState() != OoyalaPlayer.State.PLAYING) {
      sendTrackingEvent("pause");
    }
    super.pause();
  }
  
  public void play()
  {
    if (getBasePlayer() == null)
    {
      queuePlay();
      return;
    }
    if (this._linearAdQueue.isEmpty())
    {
      setState(OoyalaPlayer.State.COMPLETED);
      return;
    }
    if (currentTime() != 0) {
      sendTrackingEvent("resume");
    }
    super.play();
  }
  
  public void processClickThrough()
  {
    Object localObject2;
    if ((currentLinearAd() != null) && (currentLinearAd().getClickTrackingURLs() != null))
    {
      localObject1 = currentLinearAd().getClickTrackingURLs();
      if (localObject1 != null)
      {
        localObject1 = ((Set)localObject1).iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject2 = VASTUtils.urlFromAdUrlString((String)((Iterator)localObject1).next());
          DebugMode.logI(TAG, "Sending Click Tracking Ping: " + localObject2);
          ping((URL)localObject2);
        }
      }
    }
    Object localObject1 = currentLinearAd().getClickThroughURL();
    try
    {
      localObject1 = ((String)localObject1).trim();
      localObject2 = new Intent("android.intent.action.VIEW", Uri.parse((String)localObject1));
      this._playerLayout.getContext().startActivity((Intent)localObject2);
      DebugMode.logD(TAG, "Opening brower to " + (String)localObject1);
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
  
  public void sendTrackingEvent(String paramString)
  {
    if ((currentLinearAd() == null) || (currentLinearAd().getTrackingEvents() == null)) {}
    for (;;)
    {
      return;
      Object localObject = (Set)currentLinearAd().getTrackingEvents().get(paramString);
      if (localObject != null)
      {
        localObject = ((Set)localObject).iterator();
        while (((Iterator)localObject).hasNext())
        {
          URL localURL = VASTUtils.urlFromAdUrlString((String)((Iterator)localObject).next());
          DebugMode.logI(TAG, "Sending " + paramString + " Tracking Ping: " + localURL);
          ping(localURL);
        }
      }
    }
  }
  
  protected void setState(OoyalaPlayer.State paramState)
  {
    if (paramState == OoyalaPlayer.State.COMPLETED)
    {
      if (this._linearAdQueue.size() > 0) {
        this._linearAdQueue.remove(0);
      }
      sendTrackingEvent("complete");
      if (!this._linearAdQueue.isEmpty())
      {
        addQuartileBoundaryObserver();
        super.init(this._parent, ((VASTLinearAd)this._linearAdQueue.get(0)).getStreams());
        return;
      }
    }
    super.setState(paramState);
  }
  
  public void update(Observable paramObservable, Object paramObject)
  {
    if (paramObject == "timeChanged") {
      if ((!this._startSent) && (currentTime() > 0))
      {
        sendTrackingEvent("creativeView");
        sendTrackingEvent("start");
        this._startSent = true;
        if (isCurrentAdIFirstLinearForAdIndex(this._adIndex, this._ad.getAds())) {
          sendImpressionTrackingEvent(this._adIndex, this._ad.getAds());
        }
      }
    }
    for (;;)
    {
      super.update(paramObservable, paramObject);
      return;
      if ((!this._firstQSent) && (currentTime() > currentLinearAd().getDuration() * 1000.0D / 4.0D))
      {
        sendTrackingEvent("firstQuartile");
        this._firstQSent = true;
      }
      else if ((!this._midSent) && (currentTime() > currentLinearAd().getDuration() * 1000.0D / 2.0D))
      {
        sendTrackingEvent("midpoint");
        this._midSent = true;
      }
      else if ((!this._thirdQSent) && (currentTime() > 3.0D * currentLinearAd().getDuration() * 1000.0D / 4.0D))
      {
        sendTrackingEvent("thirdQuartile");
        this._thirdQSent = true;
        continue;
        if (paramObject == "stateChanged")
        {
          try
          {
            if (((BaseStreamPlayer)paramObservable).getState() != OoyalaPlayer.State.COMPLETED) {
              continue;
            }
            if (isCurrentAdLastLinearForAdIndex(this._adIndex, this._ad.getAds())) {
              this._adIndex += 1;
            }
            sendTrackingEvent("complete");
            if (this._linearAdQueue.size() > 0) {
              this._linearAdQueue.remove(0);
            }
            if (this._linearAdQueue.isEmpty()) {
              continue;
            }
            super.destroy();
            addQuartileBoundaryObserver();
            super.init(this._parent, ((VASTLinearAd)this._linearAdQueue.get(0)).getStreams());
            super.play();
            if ((currentLinearAd() == null) || (currentLinearAd().getClickThroughURL() == null)) {
              break label436;
            }
            if (this._learnMore != null) {
              break label422;
            }
            this._learnMore = new AdsLearnMoreButton(this._playerLayout.getContext(), this, this._topMargin);
            this._playerLayout.addView(this._learnMore);
          }
          catch (Exception localException)
          {
            DebugMode.logE(TAG, "arg0 should be a BaseStreamPlayer but is not!");
          }
          continue;
          label422:
          this._playerLayout.bringChildToFront(this._learnMore);
          continue;
          label436:
          if (this._learnMore != null)
          {
            this._playerLayout.removeView(this._learnMore);
            this._learnMore = null;
          }
        }
      }
    }
  }
  
  public void updateLearnMoreButton(FrameLayout paramFrameLayout, int paramInt)
  {
    if (this._topMargin == paramInt) {
      return;
    }
    if (this._learnMore != null)
    {
      this._playerLayout.removeView(this._learnMore);
      this._playerLayout = paramFrameLayout;
      this._topMargin = paramInt;
      this._learnMore.setTopMargin(this._topMargin);
      this._playerLayout.addView(this._learnMore);
      return;
    }
    this._playerLayout = paramFrameLayout;
    this._topMargin = paramInt;
  }
  
  private static abstract interface TrackingEvent
  {
    public static final String COMPLETE = "complete";
    public static final String CREATIVE_VIEW = "creativeView";
    public static final String FIRST_QUARTILE = "firstQuartile";
    public static final String MIDPOINT = "midpoint";
    public static final String PAUSE = "pause";
    public static final String RESUME = "resume";
    public static final String START = "start";
    public static final String THIRD_QUARTILE = "thirdQuartile";
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\ads\vast\VASTAdPlayer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */