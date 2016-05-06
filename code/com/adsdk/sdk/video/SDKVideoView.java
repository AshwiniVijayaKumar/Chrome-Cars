package com.adsdk.sdk.video;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.net.Uri;
import android.os.ConditionVariable;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.widget.MediaController.MediaPlayerControl;
import com.adsdk.sdk.Log;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

public class SDKVideoView
  extends SurfaceView
  implements MediaController.MediaPlayerControl
{
  private static final int STATE_ERROR = -1;
  private static final int STATE_IDLE = 0;
  private static final int STATE_PAUSED = 4;
  private static final int STATE_PLAYBACK_COMPLETED = 5;
  private static final int STATE_PLAYING = 3;
  private static final int STATE_PREPARED = 2;
  private static final int STATE_PREPARING = 1;
  private MediaPlayer.OnBufferingUpdateListener mBufferingUpdateListener = new MediaPlayer.OnBufferingUpdateListener()
  {
    public void onBufferingUpdate(MediaPlayer paramAnonymousMediaPlayer, int paramAnonymousInt)
    {
      SDKVideoView.this.mCurrentBufferPercentage = paramAnonymousInt;
    }
  };
  private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener()
  {
    public void onCompletion(MediaPlayer paramAnonymousMediaPlayer)
    {
      SDKVideoView.this.mCurrentState = 5;
      SDKVideoView.this.mTargetState = 5;
      if (SDKVideoView.this.mMediaController != null) {
        SDKVideoView.this.mMediaController.show(0);
      }
      if (SDKVideoView.this.mOnCompletionListener != null) {
        SDKVideoView.this.mOnCompletionListener.onCompletion(SDKVideoView.this.mMediaPlayer);
      }
    }
  };
  private Context mContext;
  private int mCurrentBufferPercentage;
  private int mCurrentState = 0;
  private int mDisplayMode;
  private int mDuration;
  private MediaPlayer.OnErrorListener mErrorListener = new MediaPlayer.OnErrorListener()
  {
    public boolean onError(MediaPlayer paramAnonymousMediaPlayer, int paramAnonymousInt1, int paramAnonymousInt2)
    {
      Log.d("Error: " + paramAnonymousInt1 + "," + paramAnonymousInt2);
      SDKVideoView.this.mCurrentState = -1;
      SDKVideoView.this.mTargetState = -1;
      if (SDKVideoView.this.mMediaController != null) {
        SDKVideoView.this.mMediaController.hide();
      }
      if ((SDKVideoView.this.mOnErrorListener != null) && (SDKVideoView.this.mOnErrorListener.onError(SDKVideoView.this.mMediaPlayer, paramAnonymousInt1, paramAnonymousInt2))) {}
      return true;
    }
  };
  public Handler mHandler;
  private int mHeight;
  private MediaPlayer.OnInfoListener mInfoListener = new MediaPlayer.OnInfoListener()
  {
    public boolean onInfo(MediaPlayer paramAnonymousMediaPlayer, int paramAnonymousInt1, int paramAnonymousInt2)
    {
      Log.d("Info/Warning: " + paramAnonymousInt1 + "," + paramAnonymousInt2);
      if ((SDKVideoView.this.mOnInfoListener != null) && (SDKVideoView.this.mOnInfoListener.onInfo(SDKVideoView.this.mMediaPlayer, paramAnonymousInt1, paramAnonymousInt2))) {}
      return true;
    }
  };
  private MediaController mMediaController;
  private MediaPlayer mMediaPlayer = null;
  private MediaPlayer.OnCompletionListener mOnCompletionListener;
  private MediaPlayer.OnErrorListener mOnErrorListener;
  private MediaPlayer.OnInfoListener mOnInfoListener;
  private MediaPlayer.OnPreparedListener mOnPreparedListener;
  private OnStartListener mOnStartListener;
  private boolean mPlayWhenSurfaceReady;
  MediaPlayer.OnPreparedListener mPreparedListener = new MediaPlayer.OnPreparedListener()
  {
    public void onPrepared(MediaPlayer paramAnonymousMediaPlayer)
    {
      Log.d("SDKVideoView onPrepared");
      SDKVideoView.this.mCurrentState = 2;
      if (SDKVideoView.this.mOnPreparedListener != null) {
        SDKVideoView.this.mOnPreparedListener.onPrepared(SDKVideoView.this.mMediaPlayer);
      }
      if (SDKVideoView.this.mMediaController != null) {
        SDKVideoView.this.mMediaController.setEnabled(true);
      }
      int i = SDKVideoView.this.mSeekWhenPrepared;
      if (i != 0) {
        SDKVideoView.this.seekTo(i);
      }
      if (!SDKVideoView.this.mSurfaceReady) {
        Log.d("SDKVideoView onPrepared surface not ready yet");
      }
      do
      {
        return;
        SDKVideoView.this.setVideoDisplaySize();
        if (SDKVideoView.this.mTargetState == 3)
        {
          SDKVideoView.this.start();
          return;
        }
      } while ((SDKVideoView.this.isPlaying()) || ((i == 0) && (SDKVideoView.this.getCurrentPosition() <= 0)) || (SDKVideoView.this.mMediaController == null));
      SDKVideoView.this.mMediaController.show(0);
    }
  };
  SurfaceHolder.Callback mSHCallback = new SurfaceHolder.Callback()
  {
    public void surfaceChanged(SurfaceHolder paramAnonymousSurfaceHolder, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
    {
      Log.d("SDKVideoView surfaceChanged");
      SDKVideoView.this.mSurfaceWidth = paramAnonymousInt2;
      SDKVideoView.this.mSurfaceHeight = paramAnonymousInt3;
      SDKVideoView.this.setVideoDisplaySize();
    }
    
    public void surfaceCreated(SurfaceHolder paramAnonymousSurfaceHolder)
    {
      Log.d("Surface created");
      SDKVideoView.this.mSurfaceReady = true;
      if (SDKVideoView.this.mPlayWhenSurfaceReady) {
        SDKVideoView.this.openVideo();
      }
    }
    
    public void surfaceDestroyed(SurfaceHolder paramAnonymousSurfaceHolder)
    {
      Log.d("Surface destroyed");
      SDKVideoView.this.mSurfaceReady = false;
      if (SDKVideoView.this.mMediaController != null) {
        SDKVideoView.this.mMediaController.hide();
      }
      SDKVideoView.this.release(true);
    }
  };
  private int mSeekWhenPrepared;
  MediaPlayer.OnVideoSizeChangedListener mSizeChangedListener = new MediaPlayer.OnVideoSizeChangedListener()
  {
    public void onVideoSizeChanged(MediaPlayer paramAnonymousMediaPlayer, int paramAnonymousInt1, int paramAnonymousInt2)
    {
      Log.d("SDKVideoView OnVideoSizeChangedListener");
    }
  };
  private int mSurfaceHeight;
  private boolean mSurfaceReady = false;
  private int mSurfaceWidth;
  private int mTargetState = 0;
  private HashMap<Integer, Vector<OnTimeEventListener>> mTimeEventListeners = new HashMap();
  private Runnable mTimeEventRunnable;
  private Thread mTimeEventThread;
  private ConditionVariable mTimeEventThreadDone = new ConditionVariable(false);
  private Uri mUri;
  private int mVideoHeight;
  private int mVideoWidth;
  private int mWidth;
  
  public SDKVideoView(Context paramContext, int paramInt1, int paramInt2, int paramInt3)
  {
    super(paramContext);
    this.mContext = paramContext;
    this.mWidth = paramInt1;
    this.mHeight = paramInt2;
    this.mDisplayMode = paramInt3;
    initVideoView();
  }
  
  private void attachMediaController()
  {
    if ((this.mMediaPlayer != null) && (this.mMediaController != null))
    {
      this.mMediaController.setMediaPlayer(this);
      this.mMediaController.setEnabled(isInPlaybackState());
    }
  }
  
  private void initVideoView()
  {
    this.mHandler = new Handler();
    this.mVideoWidth = 0;
    this.mVideoHeight = 0;
    this.mSurfaceWidth = 0;
    this.mSurfaceHeight = 0;
    this.mSurfaceReady = false;
    setVisibility(0);
    getHolder().addCallback(this.mSHCallback);
    getHolder().setType(3);
    setFocusable(true);
    setFocusableInTouchMode(true);
    requestFocus();
    this.mCurrentState = 0;
    this.mTargetState = 0;
  }
  
  private boolean isInPlaybackState()
  {
    return (this.mMediaPlayer != null) && (this.mCurrentState != -1) && (this.mCurrentState != 0) && (this.mCurrentState != 1);
  }
  
  private void openVideo()
  {
    if (this.mUri == null) {
      return;
    }
    this.mPlayWhenSurfaceReady = false;
    if (!this.mSurfaceReady)
    {
      this.mPlayWhenSurfaceReady = true;
      Log.d("Open Video not starting until surface created");
      return;
    }
    release(false);
    try
    {
      this.mMediaPlayer = new MediaPlayer();
      this.mMediaPlayer.setDisplay(getHolder());
      this.mMediaPlayer.setOnPreparedListener(this.mPreparedListener);
      this.mMediaPlayer.setOnVideoSizeChangedListener(this.mSizeChangedListener);
      this.mDuration = -1;
      this.mMediaPlayer.setOnCompletionListener(this.mCompletionListener);
      this.mMediaPlayer.setOnErrorListener(this.mErrorListener);
      this.mMediaPlayer.setOnBufferingUpdateListener(this.mBufferingUpdateListener);
      this.mMediaPlayer.setOnInfoListener(this.mInfoListener);
      this.mCurrentBufferPercentage = 0;
      this.mMediaPlayer.setDataSource(this.mContext, this.mUri);
      this.mMediaPlayer.setAudioStreamType(3);
      this.mMediaPlayer.setScreenOnWhilePlaying(true);
      this.mMediaPlayer.prepareAsync();
      this.mTimeEventRunnable = new Runnable()
      {
        public void run()
        {
          Log.d("Time Event Thread started");
          if ((SDKVideoView.this.mMediaPlayer != null) && (SDKVideoView.this.mCurrentState == 3)) {}
          for (;;)
          {
            try
            {
              j = SDKVideoView.this.mMediaPlayer.getCurrentPosition() / 1000;
              localVector = (Vector)SDKVideoView.this.mTimeEventListeners.get(Integer.valueOf(j));
              if (localVector != null)
              {
                i = 0;
                if (i < localVector.size()) {
                  continue;
                }
                localVector.clear();
              }
            }
            catch (Exception localException)
            {
              final int j;
              Vector localVector;
              int i;
              final SDKVideoView.OnTimeEventListener localOnTimeEventListener;
              Log.e("Time Event Thread error" + localException, localException);
              continue;
            }
            if (!SDKVideoView.this.mTimeEventThreadDone.block(1000L)) {
              break;
            }
            Log.v("Time Event Thread stopped");
            return;
            localOnTimeEventListener = (SDKVideoView.OnTimeEventListener)localVector.elementAt(i);
            SDKVideoView.this.mHandler.post(new Runnable()
            {
              public void run()
              {
                localOnTimeEventListener.onTimeEvent(j);
              }
            });
            i += 1;
          }
        }
      };
      this.mTimeEventThread = new Thread(this.mTimeEventRunnable);
      this.mTimeEventThread.start();
      this.mCurrentState = 1;
      attachMediaController();
      return;
    }
    catch (IOException localIOException)
    {
      Log.w("ADSDK", "Unable to open content: " + this.mUri, localIOException);
      this.mCurrentState = -1;
      this.mTargetState = -1;
      this.mErrorListener.onError(this.mMediaPlayer, 1, 0);
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      Log.w("ADSDK", "Unable to open content: " + this.mUri, localIllegalArgumentException);
      this.mCurrentState = -1;
      this.mTargetState = -1;
      this.mErrorListener.onError(this.mMediaPlayer, 1, 0);
    }
  }
  
  private void release(boolean paramBoolean)
  {
    if (this.mMediaPlayer != null)
    {
      this.mCurrentState = 0;
      if (this.mTimeEventThread != null)
      {
        this.mTimeEventThreadDone.open();
        this.mTimeEventThread = null;
      }
      this.mMediaPlayer.reset();
      this.mMediaPlayer.release();
      this.mMediaPlayer = null;
      if (paramBoolean) {
        this.mTargetState = 0;
      }
    }
  }
  
  private void setVideoDisplaySize()
  {
    this.mVideoWidth = 0;
    this.mVideoHeight = 0;
    if (this.mMediaPlayer != null)
    {
      this.mVideoWidth = this.mMediaPlayer.getVideoWidth();
      this.mVideoHeight = this.mMediaPlayer.getVideoHeight();
    }
    Log.d("SDKVideoView setVideoDisplaySize View Size (" + this.mWidth + "," + this.mHeight + ") Video size (" + this.mVideoWidth + "," + this.mVideoHeight + ") surface:(" + this.mSurfaceWidth + "," + this.mSurfaceHeight + ")");
    if ((this.mSurfaceReady) && (this.mVideoWidth > 0) && (this.mVideoHeight > 0)) {
      if (this.mDisplayMode == 1)
      {
        if (this.mVideoWidth * this.mHeight <= this.mWidth * this.mVideoHeight) {
          break label236;
        }
        this.mHeight = (this.mWidth * this.mVideoHeight / this.mVideoWidth);
      }
    }
    for (;;)
    {
      getHolder().setFixedSize(this.mWidth, this.mHeight);
      getHolder().setFixedSize(this.mVideoWidth, this.mVideoHeight);
      return;
      label236:
      if (this.mVideoWidth * this.mHeight < this.mWidth * this.mVideoHeight) {
        this.mWidth = (this.mHeight * this.mVideoWidth / this.mVideoHeight);
      }
    }
  }
  
  private void toggleMediaControlsVisiblity()
  {
    if (this.mMediaController != null) {
      this.mMediaController.toggle();
    }
  }
  
  public boolean canPause()
  {
    return true;
  }
  
  public boolean canSeekBackward()
  {
    return false;
  }
  
  public boolean canSeekForward()
  {
    return true;
  }
  
  public void destroy()
  {
    this.mTimeEventThreadDone.open();
  }
  
  public int getBufferPercentage()
  {
    if (this.mMediaPlayer != null) {
      return this.mCurrentBufferPercentage;
    }
    return 0;
  }
  
  public int getCurrentPosition()
  {
    if (isInPlaybackState()) {
      return this.mMediaPlayer.getCurrentPosition();
    }
    return 0;
  }
  
  public int getDuration()
  {
    if (isInPlaybackState())
    {
      if (this.mDuration > 0) {
        return this.mDuration;
      }
      this.mDuration = this.mMediaPlayer.getDuration();
      return this.mDuration;
    }
    this.mDuration = -1;
    return this.mDuration;
  }
  
  public boolean isPlaying()
  {
    return (isInPlaybackState()) && (this.mMediaPlayer.isPlaying());
  }
  
  protected void onDetachedFromWindow()
  {
    Log.i("Video view detached from Window");
    super.onDetachedFromWindow();
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    int i;
    if ((paramInt != 4) && (paramInt != 24) && (paramInt != 25) && (paramInt != 82) && (paramInt != 5) && (paramInt != 6)) {
      i = 1;
    }
    while ((isInPlaybackState()) && (i != 0) && (this.mMediaController != null)) {
      if ((paramInt == 79) || (paramInt == 85))
      {
        if (this.mMediaPlayer.isPlaying())
        {
          pause();
          return true;
          i = 0;
        }
        else
        {
          start();
          return true;
        }
      }
      else
      {
        if ((paramInt != 86) || (!this.mMediaPlayer.isPlaying())) {
          break label120;
        }
        pause();
      }
    }
    for (;;)
    {
      return super.onKeyDown(paramInt, paramKeyEvent);
      label120:
      toggleMediaControlsVisiblity();
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int i = getDefaultSize(this.mVideoWidth, paramInt1);
    int j = getDefaultSize(this.mVideoHeight, paramInt2);
    paramInt1 = j;
    paramInt2 = i;
    if (this.mVideoWidth > 0)
    {
      paramInt1 = j;
      paramInt2 = i;
      if (this.mVideoHeight > 0)
      {
        if (this.mVideoWidth * j <= this.mVideoHeight * i) {
          break label168;
        }
        paramInt1 = this.mVideoHeight * i / this.mVideoWidth;
        paramInt2 = i;
      }
    }
    for (;;)
    {
      setMeasuredDimension(paramInt2, paramInt1);
      Log.d("SDKVideoView onMeasure video size (" + this.mVideoWidth + "," + this.mVideoHeight + ") surface:(" + this.mSurfaceWidth + "," + this.mSurfaceHeight + ") Setting size:(" + paramInt2 + "," + paramInt1 + ")");
      return;
      label168:
      paramInt1 = j;
      paramInt2 = i;
      if (this.mVideoWidth * j < this.mVideoHeight * i)
      {
        paramInt2 = this.mVideoWidth * j / this.mVideoHeight;
        paramInt1 = j;
      }
    }
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if ((isInPlaybackState()) && (this.mMediaController != null) && (paramMotionEvent.getAction() == 0)) {
      toggleMediaControlsVisiblity();
    }
    return false;
  }
  
  public boolean onTrackballEvent(MotionEvent paramMotionEvent)
  {
    if ((isInPlaybackState()) && (this.mMediaController != null)) {
      toggleMediaControlsVisiblity();
    }
    return false;
  }
  
  public void pause()
  {
    if ((isInPlaybackState()) && (this.mMediaPlayer.isPlaying()))
    {
      this.mMediaPlayer.pause();
      this.mCurrentState = 4;
      if (this.mMediaController != null) {
        this.mMediaController.onPause();
      }
    }
    this.mTargetState = 4;
  }
  
  public void seekTo(int paramInt)
  {
    if (isInPlaybackState())
    {
      this.mMediaPlayer.seekTo(paramInt);
      this.mSeekWhenPrepared = 0;
      return;
    }
    this.mSeekWhenPrepared = paramInt;
  }
  
  public void setMediaController(MediaController paramMediaController)
  {
    if (this.mMediaController != null) {
      this.mMediaController.hide();
    }
    this.mMediaController = paramMediaController;
    attachMediaController();
  }
  
  public void setOnCompletionListener(MediaPlayer.OnCompletionListener paramOnCompletionListener)
  {
    this.mOnCompletionListener = paramOnCompletionListener;
  }
  
  public void setOnErrorListener(MediaPlayer.OnErrorListener paramOnErrorListener)
  {
    this.mOnErrorListener = paramOnErrorListener;
  }
  
  public void setOnInfoListener(MediaPlayer.OnInfoListener paramOnInfoListener)
  {
    this.mOnInfoListener = paramOnInfoListener;
  }
  
  public void setOnPreparedListener(MediaPlayer.OnPreparedListener paramOnPreparedListener)
  {
    this.mOnPreparedListener = paramOnPreparedListener;
  }
  
  public void setOnStartListener(OnStartListener paramOnStartListener)
  {
    this.mOnStartListener = paramOnStartListener;
  }
  
  public void setOnTimeEventListener(int paramInt, OnTimeEventListener paramOnTimeEventListener)
  {
    Vector localVector2 = (Vector)this.mTimeEventListeners.get(Integer.valueOf(paramInt));
    Vector localVector1 = localVector2;
    if (localVector2 == null)
    {
      localVector1 = new Vector();
      this.mTimeEventListeners.put(Integer.valueOf(paramInt), localVector1);
    }
    localVector1.add(paramOnTimeEventListener);
  }
  
  public void setVideoPath(String paramString)
  {
    setVideoURI(Uri.parse(paramString));
  }
  
  public void setVideoURI(Uri paramUri)
  {
    this.mUri = paramUri;
    this.mSeekWhenPrepared = 0;
    openVideo();
  }
  
  public void start()
  {
    this.mTargetState = 3;
    if (isInPlaybackState())
    {
      localIntent = new Intent("com.android.music.musicservicecommand");
      localIntent.putExtra("command", "pause");
      this.mContext.sendBroadcast(localIntent);
      this.mMediaPlayer.start();
      if (this.mMediaController != null) {
        this.mMediaController.onStart();
      }
      if ((this.mCurrentState == 2) && (this.mOnStartListener != null)) {
        this.mOnStartListener.onVideoStart();
      }
      this.mCurrentState = 3;
    }
    while (this.mMediaPlayer != null)
    {
      Intent localIntent;
      return;
    }
    openVideo();
  }
  
  public void stopPlayback()
  {
    if (this.mMediaPlayer != null)
    {
      if (this.mMediaPlayer.isPlaying()) {
        this.mMediaPlayer.stop();
      }
      this.mMediaPlayer.release();
      this.mMediaPlayer = null;
      this.mCurrentState = 0;
      this.mTargetState = 0;
    }
  }
  
  public static abstract interface OnStartListener
  {
    public abstract void onVideoStart();
  }
  
  public static abstract interface OnTimeEventListener
  {
    public abstract void onTimeEvent(int paramInt);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\adsdk\sdk\video\SDKVideoView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */