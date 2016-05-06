package com.ooyala.android.player;

import android.view.View;
import com.ooyala.android.OoyalaException;
import com.ooyala.android.OoyalaPlayer;
import com.ooyala.android.OoyalaPlayer.SeekStyle;
import com.ooyala.android.OoyalaPlayer.State;
import com.ooyala.android.item.Stream;
import com.ooyala.android.util.DebugMode;
import java.net.URL;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

public class MoviePlayer
  extends Player
  implements Observer
{
  protected static final String DRM_TENENT_PATH = "/sas/drm2/%s/%s/%s/%s";
  private static final String TAG = "MoviePlayer";
  static final String VISUALON_PLAYER = "com.ooyala.android.visualon.VisualOnStreamPlayer";
  private StreamPlayer _basePlayer;
  private boolean _live = false;
  private int _millisToResume = 0;
  protected boolean _seekable = true;
  private OoyalaPlayer.State _stateToResume = OoyalaPlayer.State.INIT;
  private Set<Stream> _streams;
  private boolean _suspended = true;
  
  private StreamPlayer getPlayerForStreams(Set<Stream> paramSet)
  {
    boolean bool1 = Stream.streamSetContainsDeliveryType(paramSet, "hls");
    boolean bool2;
    int i;
    label78:
    int k;
    if ((Stream.streamSetContainsDeliveryType(paramSet, "remote_asset")) && (Stream.getStreamWithDeliveryType(paramSet, "remote_asset").decodedURL().toString().contains("m3u8")))
    {
      j = 1;
      bool2 = Stream.streamSetContainsDeliveryType(paramSet, "smooth");
      if ((!Stream.streamSetContainsDeliveryType(paramSet, "remote_asset")) || (!Stream.getStreamWithDeliveryType(paramSet, "remote_asset").decodedURL().toString().contains(".ism"))) {
        break label157;
      }
      i = 1;
      if ((!OoyalaPlayer.enableCustomHLSPlayer) || ((!bool1) && (j == 0))) {
        break label162;
      }
      k = 1;
      label96:
      if ((!OoyalaPlayer.enableCustomPlayreadyPlayer) || ((!bool2) && (i == 0) && (!bool1) && (j == 0))) {
        break label168;
      }
    }
    label157:
    label162:
    label168:
    for (int j = 1;; j = 0)
    {
      if ((k == 0) && (j == 0)) {
        break label189;
      }
      try
      {
        paramSet = (StreamPlayer)getClass().getClassLoader().loadClass("com.ooyala.android.visualon.VisualOnStreamPlayer").newInstance();
        return paramSet;
      }
      catch (Exception paramSet)
      {
        DebugMode.logE("MoviePlayer", "Tried to load VisualOn Player but failed");
        return new BaseStreamPlayer();
      }
      j = 0;
      break;
      i = 0;
      break label78;
      k = 0;
      break label96;
    }
    label189:
    if ((bool2) || (i != 0)) {
      DebugMode.assertFail("MoviePlayer", "A Smooth stream is about to load on the base stream player.  Did you mean to set enableCustomSmoothPlayer?");
    }
    return new BaseStreamPlayer();
  }
  
  private void setStreams(Set<Stream> paramSet)
  {
    if (paramSet == null)
    {
      this._streams = new HashSet();
      return;
    }
    this._streams = paramSet;
  }
  
  public int buffer()
  {
    if (this._basePlayer != null) {
      return this._basePlayer.buffer();
    }
    return 0;
  }
  
  public int currentTime()
  {
    if (this._basePlayer != null) {
      return this._basePlayer.currentTime();
    }
    return 0;
  }
  
  public void destroy()
  {
    if (this._basePlayer != null)
    {
      this._basePlayer.deleteObserver(this);
      this._basePlayer.destroy();
      this._basePlayer = null;
    }
  }
  
  public int duration()
  {
    if (this._basePlayer != null) {
      return this._basePlayer.duration();
    }
    return 0;
  }
  
  public StreamPlayer getBasePlayer()
  {
    return this._basePlayer;
  }
  
  public int getBufferPercentage()
  {
    if (this._basePlayer != null) {
      return this._basePlayer.getBufferPercentage();
    }
    return 0;
  }
  
  public OoyalaException getError()
  {
    if (this._error != null) {
      return this._error;
    }
    return this._basePlayer.getError();
  }
  
  public OoyalaPlayer.SeekStyle getSeekStyle()
  {
    if (this._basePlayer != null) {
      return this._basePlayer.getSeekStyle();
    }
    return OoyalaPlayer.SeekStyle.BASIC;
  }
  
  public OoyalaPlayer.State getState()
  {
    if (this._basePlayer != null) {
      return this._basePlayer.getState();
    }
    return super.getState();
  }
  
  public View getView()
  {
    if (this._basePlayer != null) {
      return this._basePlayer.getView();
    }
    DebugMode.logE("MoviePlayer", "Trying to getView without a Base Player");
    return null;
  }
  
  public void init(OoyalaPlayer paramOoyalaPlayer, Set<Stream> paramSet)
  {
    setStreams(paramSet);
    this._parent = paramOoyalaPlayer;
    this._streams = paramSet;
    this._suspended = false;
    if (this._basePlayer == null) {
      this._basePlayer = getPlayerForStreams(paramSet);
    }
    this._basePlayer.addObserver(this);
    this._basePlayer.init(paramOoyalaPlayer, paramSet);
  }
  
  public boolean isLiveClosedCaptionsAvailable()
  {
    if (this._basePlayer != null) {
      return this._basePlayer.isLiveClosedCaptionsAvailable();
    }
    return false;
  }
  
  public boolean isPlaying()
  {
    if (this._basePlayer != null) {
      return this._basePlayer.isPlaying();
    }
    return false;
  }
  
  public int livePlayheadPercentage()
  {
    if (this._basePlayer != null) {
      return this._basePlayer.livePlayheadPercentage();
    }
    return 0;
  }
  
  public void pause()
  {
    if (this._basePlayer != null)
    {
      this._basePlayer.pause();
      return;
    }
    DebugMode.logE("MoviePlayer", "Trying to pause MoviePlayer without a Base Player");
  }
  
  public void play()
  {
    if (this._basePlayer != null)
    {
      DebugMode.logV("MoviePlayer", "play()");
      this._basePlayer.play();
      return;
    }
    DebugMode.logE("MoviePlayer", "Trying to play MoviePlayer without a Base Player");
  }
  
  public void reset()
  {
    this._suspended = false;
    if (this._basePlayer != null) {
      this._basePlayer.reset();
    }
  }
  
  public void resume()
  {
    resume(this._millisToResume, this._stateToResume);
    setState(getState());
  }
  
  public void resume(int paramInt, OoyalaPlayer.State paramState)
  {
    this._suspended = false;
    if (this._basePlayer != null)
    {
      this._basePlayer.init(this._parent, this._streams);
      DebugMode.logD(getClass().toString(), "Movie Player Resuming. ms to resume: " + paramInt + ". State to resume: " + paramState);
      this._basePlayer.resume(paramInt, paramState);
      return;
    }
    DebugMode.logE("MoviePlayer", "Trying to resume MoviePlayer without a base player!");
  }
  
  public void seekToPercentLive(int paramInt)
  {
    if (this._basePlayer != null) {
      this._basePlayer.seekToPercentLive(paramInt);
    }
  }
  
  public void seekToTime(int paramInt)
  {
    if (this._basePlayer != null)
    {
      if (this._seekable) {
        this._basePlayer.seekToTime(paramInt);
      }
      return;
    }
    DebugMode.logE("MoviePlayer", "Trying to seek MoviePlayer without a Base Player");
  }
  
  public boolean seekable()
  {
    return this._seekable;
  }
  
  public void setClosedCaptionsLanguage(String paramString)
  {
    if (this._basePlayer != null)
    {
      this._basePlayer.setClosedCaptionsLanguage(paramString);
      return;
    }
    DebugMode.logE("MoviePlayer", "Trying to setClosedCaptionsLanguage MoviePlayer without a Base Player");
  }
  
  public void setLive(boolean paramBoolean)
  {
    this._live = paramBoolean;
  }
  
  public void setParent(OoyalaPlayer paramOoyalaPlayer)
  {
    this._parent = paramOoyalaPlayer;
    if (this._basePlayer != null)
    {
      this._basePlayer.setParent(paramOoyalaPlayer);
      return;
    }
    DebugMode.logE("MoviePlayer", "Trying to setParent MoviePlayer without a Base Player");
  }
  
  public void setSeekable(boolean paramBoolean)
  {
    this._seekable = paramBoolean;
  }
  
  protected void setState(OoyalaPlayer.State paramState)
  {
    if (this._basePlayer != null)
    {
      this._basePlayer.setState(paramState);
      return;
    }
    super.setState(paramState);
  }
  
  public void stop()
  {
    if (this._basePlayer != null)
    {
      this._basePlayer.stop();
      return;
    }
    DebugMode.logE("MoviePlayer", "Trying to stop MoviePlayer without a Base Player");
  }
  
  public void suspend()
  {
    if (this._basePlayer != null)
    {
      suspend(this._basePlayer.currentTime(), this._basePlayer.getState());
      return;
    }
    suspend(0, OoyalaPlayer.State.INIT);
  }
  
  public void suspend(int paramInt, OoyalaPlayer.State paramState)
  {
    if (this._suspended)
    {
      DebugMode.logI(getClass().toString(), "Trying to suspend an already suspended MoviePlayer");
      return;
    }
    DebugMode.logD(getClass().toString(), "Movie Player Suspending. ms to resume: " + paramInt + ". State to resume: " + paramState);
    this._millisToResume = paramInt;
    this._stateToResume = paramState;
    if (this._basePlayer != null) {
      this._basePlayer.suspend();
    }
    this._suspended = true;
  }
  
  public int timeToResume()
  {
    return this._millisToResume;
  }
  
  public void update(Observable paramObservable, Object paramObject)
  {
    if (this._suspended) {
      return;
    }
    setChanged();
    notifyObservers(paramObject);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\player\MoviePlayer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */