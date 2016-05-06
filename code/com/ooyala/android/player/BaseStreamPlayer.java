package com.ooyala.android.player;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnSeekCompleteListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.widget.FrameLayout;
import com.ooyala.android.OoyalaException;
import com.ooyala.android.OoyalaException.OoyalaErrorCode;
import com.ooyala.android.OoyalaPlayer;
import com.ooyala.android.OoyalaPlayer.SeekStyle;
import com.ooyala.android.OoyalaPlayer.State;
import com.ooyala.android.configuration.ReadonlyOptionsInterface;
import com.ooyala.android.item.Stream;
import com.ooyala.android.util.DebugMode;
import java.net.URL;
import java.util.Set;

public class BaseStreamPlayer
  extends StreamPlayer
  implements MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnInfoListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnSeekCompleteListener, MediaPlayer.OnVideoSizeChangedListener, SurfaceHolder.Callback
{
  private static final String TAG = BaseStreamPlayer.class.getName();
  private boolean _completedQueued = false;
  protected int _height = 0;
  protected SurfaceHolder _holder = null;
  private boolean _playQueued = false;
  protected MediaPlayer _player = null;
  private boolean _playerPrepared = false;
  private OoyalaPlayer.State _stateBeforeSuspend = OoyalaPlayer.State.INIT;
  protected String _streamUrl = "";
  private int _timeBeforeSuspend = -1;
  protected int _width = 0;
  Stream stream = null;
  
  private void createView(boolean paramBoolean, Context paramContext)
  {
    this._view = new MovieView(paramBoolean, paramContext);
    this._view.setBackgroundColor(-16777216);
  }
  
  private boolean dequeueCompleted()
  {
    boolean bool = false;
    if (this._completedQueued)
    {
      this._playQueued = false;
      this._completedQueued = false;
      bool = true;
    }
    return bool;
  }
  
  private void dequeuePlay()
  {
    if (this._playQueued) {}
    switch (getState())
    {
    default: 
      return;
    }
    this._playQueued = false;
    play();
  }
  
  private boolean isSeekAllowed()
  {
    return (getState() == OoyalaPlayer.State.PAUSED) || (getState() == OoyalaPlayer.State.READY) || (getState() == OoyalaPlayer.State.COMPLETED) || (getState() == OoyalaPlayer.State.PLAYING);
  }
  
  private void queueCompleted()
  {
    this._completedQueued = true;
  }
  
  private void queuePlay()
  {
    this._playQueued = true;
  }
  
  private void removeView()
  {
    this._parent.removeVideoView();
    if (this._holder != null) {
      this._holder.removeCallback(this);
    }
    this._view = null;
    this._holder = null;
  }
  
  private void seekToTimeOnPrepared(int paramInt)
  {
    if (this._player != null) {
      this._player.seekTo(paramInt);
    }
  }
  
  private void setVideoSize(int paramInt1, int paramInt2)
  {
    this._width = paramInt1;
    this._height = paramInt2;
    ((MovieView)this._view).setAspectRatio(this._width / this._height);
  }
  
  private void setupView()
  {
    createView(this._parent.getOptions().getPreventVideoViewSharing(), this._parent.getLayout().getContext());
    this._parent.addVideoView(this._view);
    if ((this.stream.getWidth() > 0) && (this.stream.getHeight() > 0)) {
      setVideoSize(this.stream.getWidth(), this.stream.getHeight());
    }
    for (;;)
    {
      this._holder = this._view.getHolder();
      this._holder.addCallback(this);
      this._holder.setType(3);
      return;
      setVideoSize(16, 9);
    }
  }
  
  private void suspend(int paramInt, OoyalaPlayer.State paramState)
  {
    DebugMode.logD(TAG, "suspend with time " + paramInt + "state" + paramState.toString());
    if (getState() == OoyalaPlayer.State.SUSPENDED)
    {
      DebugMode.logD(TAG, "Suspending an already suspended player");
      return;
    }
    if (this._player == null)
    {
      DebugMode.logD(TAG, "Suspending with a null player");
      return;
    }
    if (paramInt >= 0)
    {
      this._timeBeforeSuspend = paramInt;
      this._stateBeforeSuspend = paramState;
    }
    if (this._player != null) {
      stop();
    }
    removeView();
    this._width = 0;
    this._height = 0;
    this._buffer = 0;
    setState(OoyalaPlayer.State.SUSPENDED);
  }
  
  public int buffer()
  {
    return this._buffer;
  }
  
  protected void createMediaPlayer()
  {
    try
    {
      if (this._player != null)
      {
        DebugMode.logD(TAG, "createMediaPlayer: reset the existing mediaplayer");
        this._playerPrepared = false;
        this._player.reset();
        if (Build.VERSION.SDK_INT < 14) {
          break label174;
        }
        this._player.setDataSource(this._parent.getLayout().getContext(), Uri.parse(this._streamUrl));
      }
      for (;;)
      {
        this._player.setDisplay(this._holder);
        this._player.setAudioStreamType(3);
        this._player.setScreenOnWhilePlaying(true);
        this._player.setOnPreparedListener(this);
        this._player.setOnCompletionListener(this);
        this._player.setOnBufferingUpdateListener(this);
        this._player.setOnErrorListener(this);
        this._player.setOnInfoListener(this);
        this._player.setOnSeekCompleteListener(this);
        this._player.setOnVideoSizeChangedListener(this);
        this._player.prepareAsync();
        return;
        DebugMode.logD(TAG, "createMediaPlayer: creating a new mediaplayer");
        this._player = new MediaPlayer();
        break;
        label174:
        this._player.setDataSource(this._streamUrl);
      }
      return;
    }
    catch (Throwable localThrowable) {}
  }
  
  protected void currentItemCompleted()
  {
    stopPlayheadTimer();
    setState(OoyalaPlayer.State.COMPLETED);
  }
  
  public int currentTime()
  {
    int j = 0;
    if (getState() == OoyalaPlayer.State.SUSPENDED) {
      i = this._timeBeforeSuspend;
    }
    do
    {
      return i;
      i = j;
    } while (this._player == null);
    int i = j;
    switch (getState())
    {
    }
    return this._player.getCurrentPosition();
  }
  
  public void destroy()
  {
    if (this._player != null) {
      stop();
    }
    removeView();
    this._parent = null;
    this._width = 0;
    this._height = 0;
    this._buffer = 0;
    this._playQueued = false;
    this._timeBeforeSuspend = -1;
    setState(OoyalaPlayer.State.INIT);
  }
  
  public int duration()
  {
    if (this._player == null) {
      return 0;
    }
    if (!this._playerPrepared)
    {
      DebugMode.logE(TAG, "Trying to getDuration without MediaPlayer");
      return 0;
    }
    switch (getState())
    {
    }
    return this._player.getDuration();
  }
  
  public OoyalaPlayer.SeekStyle getSeekStyle()
  {
    return OoyalaPlayer.SeekStyle.BASIC;
  }
  
  public void init(OoyalaPlayer paramOoyalaPlayer, Set<Stream> paramSet)
  {
    this.stream = Stream.bestStream(paramSet, ((WifiManager)paramOoyalaPlayer.getLayout().getContext().getSystemService("wifi")).isWifiEnabled());
    if (this.stream == null)
    {
      DebugMode.logE(TAG, "ERROR: Invalid Stream (no valid stream available)");
      this._error = new OoyalaException(OoyalaException.OoyalaErrorCode.ERROR_PLAYBACK_FAILED, "Invalid Stream");
      setState(OoyalaPlayer.State.ERROR);
      return;
    }
    if (paramOoyalaPlayer == null)
    {
      this._error = new OoyalaException(OoyalaException.OoyalaErrorCode.ERROR_PLAYBACK_FAILED, "Invalid Parent");
      setState(OoyalaPlayer.State.ERROR);
      return;
    }
    setState(OoyalaPlayer.State.LOADING);
    if (this.stream.getUrlFormat().equals("encoded")) {}
    for (paramSet = this.stream.decodedURL().toString().trim();; paramSet = this.stream.getUrl().trim())
    {
      this._streamUrl = paramSet;
      setParent(paramOoyalaPlayer);
      setupView();
      if (this._player == null) {
        break;
      }
      this._player.reset();
      return;
    }
  }
  
  public boolean isPlaying()
  {
    return (this._player != null) && (this._player.isPlaying());
  }
  
  public void onBufferingUpdate(MediaPlayer paramMediaPlayer, int paramInt)
  {
    this._buffer = paramInt;
    setChanged();
    notifyObservers("bufferChanged");
  }
  
  public void onCompletion(MediaPlayer paramMediaPlayer)
  {
    currentItemCompleted();
  }
  
  public boolean onError(MediaPlayer paramMediaPlayer, int paramInt1, int paramInt2)
  {
    this._error = new OoyalaException(OoyalaException.OoyalaErrorCode.ERROR_PLAYBACK_FAILED, "MediaPlayer Error: " + paramInt1 + " " + paramInt2);
    if ((paramInt1 == -10) && (paramInt2 == -10)) {
      DebugMode.logE(TAG, "Unsupported video type given to base media player");
    }
    if (paramInt1 == 100) {
      stop();
    }
    setState(OoyalaPlayer.State.ERROR);
    return false;
  }
  
  public boolean onInfo(MediaPlayer paramMediaPlayer, int paramInt1, int paramInt2)
  {
    if (paramInt1 == 3) {
      this._view.setBackgroundColor(0);
    }
    for (;;)
    {
      return true;
      if (paramInt1 == 701)
      {
        DebugMode.logD(TAG, "onInfo: Buffering Starting! " + paramInt1 + ", extra: " + paramInt2);
        setState(OoyalaPlayer.State.LOADING);
      }
      else if (paramInt1 == 702)
      {
        DebugMode.logD(TAG, "onInfo: Buffering Done! " + paramInt1 + ", extra: " + paramInt2);
        if (this._player.isPlaying()) {
          setState(OoyalaPlayer.State.PLAYING);
        } else {
          setState(OoyalaPlayer.State.PAUSED);
        }
      }
    }
  }
  
  public void onPrepared(MediaPlayer paramMediaPlayer)
  {
    DebugMode.logD(TAG, "MediaPlayer is prepared.");
    if ((this._width == 0) && (this._height == 0) && (paramMediaPlayer.getVideoHeight() > 0) && (paramMediaPlayer.getVideoWidth() > 0)) {
      setVideoSize(paramMediaPlayer.getVideoWidth(), paramMediaPlayer.getVideoHeight());
    }
    if (this._timeBeforeSuspend > 0) {
      seekToTimeOnPrepared(this._timeBeforeSuspend);
    }
    this._playerPrepared = true;
    setState(OoyalaPlayer.State.READY);
  }
  
  public void onSeekComplete(MediaPlayer paramMediaPlayer)
  {
    setChanged();
    notifyObservers("seekCompleted");
    if ((this._timeBeforeSuspend >= 0) && (Math.abs(this._player.getCurrentPosition() - this._timeBeforeSuspend) > 3000)) {
      DebugMode.logI(getClass().getName(), "Seek failed. currentPos: " + this._player.getCurrentPosition() + ", timeBefore" + this._timeBeforeSuspend + "duration: " + this._player.getDuration());
    }
    try
    {
      Thread.sleep(500L);
      this._player.seekTo(this._timeBeforeSuspend);
      if (this._player.getDuration() != 0) {
        this._timeBeforeSuspend = -1;
      }
      dequeuePlay();
      return;
    }
    catch (InterruptedException paramMediaPlayer)
    {
      for (;;)
      {
        DebugMode.logE(TAG, "Caught!", paramMediaPlayer);
      }
    }
  }
  
  public void onVideoSizeChanged(MediaPlayer paramMediaPlayer, int paramInt1, int paramInt2)
  {
    setVideoSize(paramInt1, paramInt2);
  }
  
  public void pause()
  {
    this._playQueued = false;
    switch (getState())
    {
    default: 
      return;
    }
    stopPlayheadTimer();
    this._player.pause();
    setState(OoyalaPlayer.State.PAUSED);
  }
  
  public void play()
  {
    this._playQueued = false;
    switch (getState())
    {
    default: 
      return;
    case ???: 
    case ???: 
      queuePlay();
      return;
    }
    DebugMode.logD(TAG, "BaseStreamPlayer.play() has been called when _playerPrepared = " + this._playerPrepared);
    if (this._playerPrepared)
    {
      this._player.start();
      this._view.setBackgroundColor(0);
      setState(OoyalaPlayer.State.PLAYING);
      startPlayheadTimer();
      return;
    }
    queuePlay();
  }
  
  public void reset()
  {
    suspend(0, OoyalaPlayer.State.PAUSED);
    setState(OoyalaPlayer.State.LOADING);
    setupView();
    resume();
  }
  
  public void resume()
  {
    resume(this._timeBeforeSuspend, this._stateBeforeSuspend);
  }
  
  public void resume(int paramInt, OoyalaPlayer.State paramState)
  {
    DebugMode.logD(TAG, "Resuming. time to resume: " + paramInt + ", state to resume: " + paramState);
    this._timeBeforeSuspend = paramInt;
    if (paramState == OoyalaPlayer.State.PLAYING) {
      queuePlay();
    }
    while (paramState != OoyalaPlayer.State.COMPLETED) {
      return;
    }
    queueCompleted();
  }
  
  public void seekToTime(int paramInt)
  {
    this._timeBeforeSuspend = paramInt;
    if ((this._player != null) && (isSeekAllowed())) {
      this._player.seekTo(paramInt);
    }
  }
  
  public void setParent(OoyalaPlayer paramOoyalaPlayer)
  {
    super.setParent(paramOoyalaPlayer);
  }
  
  protected void setState(OoyalaPlayer.State paramState)
  {
    if (dequeueCompleted())
    {
      super.setState(OoyalaPlayer.State.COMPLETED);
      return;
    }
    super.setState(paramState);
    dequeuePlay();
  }
  
  public void stop()
  {
    stopPlayheadTimer();
    this._playQueued = false;
    if (this._playerPrepared) {
      this._player.stop();
    }
    this._player.release();
    this._player = null;
    this._playerPrepared = false;
  }
  
  public void surfaceChanged(SurfaceHolder paramSurfaceHolder, int paramInt1, int paramInt2, int paramInt3) {}
  
  public void surfaceCreated(SurfaceHolder paramSurfaceHolder)
  {
    DebugMode.logI(TAG, "Surface Created");
    createMediaPlayer();
  }
  
  public void surfaceDestroyed(SurfaceHolder paramSurfaceHolder)
  {
    DebugMode.logI(TAG, "Surface Destroyed");
  }
  
  public void suspend()
  {
    int j = -1;
    int i = j;
    if (this._player != null)
    {
      i = j;
      if (this._playerPrepared) {
        i = this._player.getCurrentPosition();
      }
    }
    suspend(i, getState());
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\player\BaseStreamPlayer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */