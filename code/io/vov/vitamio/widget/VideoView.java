package io.vov.vitamio.widget;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.Pair;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import io.vov.vitamio.MediaFormat;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.MediaPlayer.OnBufferingUpdateListener;
import io.vov.vitamio.MediaPlayer.OnCompletionListener;
import io.vov.vitamio.MediaPlayer.OnErrorListener;
import io.vov.vitamio.MediaPlayer.OnInfoListener;
import io.vov.vitamio.MediaPlayer.OnPreparedListener;
import io.vov.vitamio.MediaPlayer.OnSeekCompleteListener;
import io.vov.vitamio.MediaPlayer.OnTimedTextListener;
import io.vov.vitamio.MediaPlayer.OnVideoSizeChangedListener;
import io.vov.vitamio.utils.Log;
import io.vov.vitamio.utils.ScreenResolution;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VideoView
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
  private static final int STATE_RESUME = 7;
  private static final int STATE_SUSPEND = 6;
  private static final int STATE_SUSPEND_UNSUPPORTED = 8;
  public static final int VIDEO_LAYOUT_FIT_PARENT = 4;
  public static final int VIDEO_LAYOUT_ORIGIN = 0;
  public static final int VIDEO_LAYOUT_SCALE = 1;
  public static final int VIDEO_LAYOUT_STRETCH = 2;
  public static final int VIDEO_LAYOUT_ZOOM = 3;
  private boolean isLandScape = false;
  private boolean isPartrait = false;
  private float mAspectRatio = 0.0F;
  private int mBufSize;
  private MediaPlayer.OnBufferingUpdateListener mBufferingUpdateListener = new MediaPlayer.OnBufferingUpdateListener()
  {
    public void onBufferingUpdate(MediaPlayer paramAnonymousMediaPlayer, int paramAnonymousInt)
    {
      VideoView.this.mCurrentBufferPercentage = paramAnonymousInt;
      if (VideoView.this.mOnBufferingUpdateListener != null) {
        VideoView.this.mOnBufferingUpdateListener.onBufferingUpdate(paramAnonymousMediaPlayer, paramAnonymousInt);
      }
    }
  };
  private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener()
  {
    public void onCompletion(MediaPlayer paramAnonymousMediaPlayer)
    {
      Log.d("onCompletion", new Object[0]);
      VideoView.this.mCurrentState = 5;
      VideoView.this.mTargetState = 5;
      if (VideoView.this.mMediaController != null) {
        VideoView.this.mMediaController.hide();
      }
      if (VideoView.this.mOnCompletionListener != null) {
        VideoView.this.mOnCompletionListener.onCompletion(VideoView.this.mMediaPlayer);
      }
    }
  };
  private Context mContext;
  private int mCurrentBufferPercentage;
  private int mCurrentState = 0;
  private long mDuration;
  private MediaPlayer.OnErrorListener mErrorListener = new MediaPlayer.OnErrorListener()
  {
    public boolean onError(MediaPlayer paramAnonymousMediaPlayer, int paramAnonymousInt1, int paramAnonymousInt2)
    {
      Log.d("Error: %d, %d", new Object[] { Integer.valueOf(paramAnonymousInt1), Integer.valueOf(paramAnonymousInt2) });
      VideoView.this.mCurrentState = -1;
      VideoView.this.mTargetState = -1;
      if (VideoView.this.mMediaController != null) {
        VideoView.this.mMediaController.hide();
      }
      if ((VideoView.this.mOnErrorListener != null) && (VideoView.this.mOnErrorListener.onError(VideoView.this.mMediaPlayer, paramAnonymousInt1, paramAnonymousInt2))) {}
      while (VideoView.this.getWindowToken() == null) {
        return true;
      }
      if (paramAnonymousInt1 == 200) {}
      for (paramAnonymousInt1 = VideoView.this.getResources().getIdentifier("VideoView_error_text_invalid_progressive_playback", "string", VideoView.this.mContext.getPackageName());; paramAnonymousInt1 = VideoView.this.getResources().getIdentifier("VideoView_error_text_unknown", "string", VideoView.this.mContext.getPackageName()))
      {
        new AlertDialog.Builder(VideoView.this.mContext).setTitle(VideoView.this.getResources().getIdentifier("VideoView_error_title", "string", VideoView.this.mContext.getPackageName())).setMessage(paramAnonymousInt1).setPositiveButton(VideoView.this.getResources().getIdentifier("VideoView_error_button", "string", VideoView.this.mContext.getPackageName()), new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            if (VideoView.this.mOnCompletionListener != null) {
              VideoView.this.mOnCompletionListener.onCompletion(VideoView.this.mMediaPlayer);
            }
          }
        }).setCancelable(false).show();
        return true;
      }
    }
  };
  private boolean mHardwareDecoder = false;
  private Map<String, String> mHeaders;
  private MediaPlayer.OnInfoListener mInfoListener = new MediaPlayer.OnInfoListener()
  {
    public boolean onInfo(MediaPlayer paramAnonymousMediaPlayer, int paramAnonymousInt1, int paramAnonymousInt2)
    {
      Log.d("onInfo: (%d, %d)", new Object[] { Integer.valueOf(paramAnonymousInt1), Integer.valueOf(paramAnonymousInt2) });
      if (1001 == paramAnonymousInt1) {
        Log.e(" VITAMIO--TYPE_CHECK  stype  not include  onInfo mediaplayer unknow type ", new Object[0]);
      }
      if (704 == paramAnonymousInt1)
      {
        long l = VideoView.this.mMediaPlayer.audioTrackInit();
        VideoView.this.mMediaPlayer.audioInitedOk(l);
      }
      Log.d("onInfo: (%d, %d)", new Object[] { Integer.valueOf(paramAnonymousInt1), Integer.valueOf(paramAnonymousInt2) });
      if (VideoView.this.mOnInfoListener != null) {
        VideoView.this.mOnInfoListener.onInfo(paramAnonymousMediaPlayer, paramAnonymousInt1, paramAnonymousInt2);
      }
      do
      {
        do
        {
          do
          {
            do
            {
              return true;
            } while (VideoView.this.mMediaPlayer == null);
            if (paramAnonymousInt1 != 701) {
              break;
            }
            VideoView.this.mMediaPlayer.pause();
          } while (VideoView.this.mMediaBufferingIndicator == null);
          VideoView.this.mMediaBufferingIndicator.setVisibility(0);
          return true;
        } while (paramAnonymousInt1 != 702);
        VideoView.this.mMediaPlayer.start();
      } while (VideoView.this.mMediaBufferingIndicator == null);
      VideoView.this.mMediaBufferingIndicator.setVisibility(8);
      return true;
    }
  };
  private View mMediaBufferingIndicator;
  private MediaController mMediaController;
  private MediaPlayer mMediaPlayer = null;
  private MediaPlayer.OnBufferingUpdateListener mOnBufferingUpdateListener;
  private MediaPlayer.OnCompletionListener mOnCompletionListener;
  private MediaPlayer.OnErrorListener mOnErrorListener;
  private MediaPlayer.OnInfoListener mOnInfoListener;
  private MediaPlayer.OnPreparedListener mOnPreparedListener;
  private MediaPlayer.OnSeekCompleteListener mOnSeekCompleteListener;
  private MediaPlayer.OnTimedTextListener mOnTimedTextListener;
  MediaPlayer.OnPreparedListener mPreparedListener = new MediaPlayer.OnPreparedListener()
  {
    public void onPrepared(MediaPlayer paramAnonymousMediaPlayer)
    {
      Log.d("onPrepared", new Object[0]);
      VideoView.this.mCurrentState = 2;
      if (VideoView.this.mOnPreparedListener != null) {
        VideoView.this.mOnPreparedListener.onPrepared(VideoView.this.mMediaPlayer);
      }
      if (VideoView.this.mMediaController != null) {
        VideoView.this.mMediaController.setEnabled(true);
      }
      VideoView.this.mVideoWidth = paramAnonymousMediaPlayer.getVideoWidth();
      VideoView.this.mVideoHeight = paramAnonymousMediaPlayer.getVideoHeight();
      VideoView.this.mVideoAspectRatio = paramAnonymousMediaPlayer.getVideoAspectRatio();
      long l = VideoView.this.mSeekWhenPrepared;
      if (l != 0L) {
        VideoView.this.seekTo(l);
      }
      if ((VideoView.this.mVideoWidth != 0) && (VideoView.this.mVideoHeight != 0))
      {
        VideoView.this.setVideoLayout(VideoView.this.mVideoLayout, VideoView.this.mAspectRatio);
        if ((VideoView.this.mSurfaceWidth == VideoView.this.mVideoWidth) && (VideoView.this.mSurfaceHeight == VideoView.this.mVideoHeight))
        {
          if (VideoView.this.mTargetState != 3) {
            break label236;
          }
          VideoView.this.start();
          if (VideoView.this.mMediaController != null) {
            VideoView.this.mMediaController.show();
          }
        }
      }
      label236:
      while (VideoView.this.mTargetState != 3)
      {
        do
        {
          return;
        } while ((VideoView.this.isPlaying()) || ((l == 0L) && (VideoView.this.getCurrentPosition() <= 0L)) || (VideoView.this.mMediaController == null));
        VideoView.this.mMediaController.show(0);
        return;
      }
      VideoView.this.start();
    }
  };
  SurfaceHolder.Callback mSHCallback = new SurfaceHolder.Callback()
  {
    public void surfaceChanged(SurfaceHolder paramAnonymousSurfaceHolder, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
    {
      VideoView.this.mSurfaceWidth = paramAnonymousInt2;
      VideoView.this.mSurfaceHeight = paramAnonymousInt3;
      if (VideoView.this.mTargetState == 3)
      {
        paramAnonymousInt1 = 1;
        if ((VideoView.this.mVideoWidth != paramAnonymousInt2) || (VideoView.this.mVideoHeight != paramAnonymousInt3)) {
          break label165;
        }
      }
      label165:
      for (paramAnonymousInt2 = 1;; paramAnonymousInt2 = 0)
      {
        if ((VideoView.this.mMediaPlayer != null) && (paramAnonymousInt1 != 0) && (paramAnonymousInt2 != 0))
        {
          if (VideoView.this.mSeekWhenPrepared != 0L) {
            VideoView.this.seekTo(VideoView.this.mSeekWhenPrepared);
          }
          VideoView.this.start();
          if (VideoView.this.mMediaController != null)
          {
            if (VideoView.this.mMediaController.isShowing()) {
              VideoView.this.mMediaController.hide();
            }
            if (!VideoView.this.isPartrait) {
              VideoView.this.mMediaController.show();
            }
          }
        }
        return;
        paramAnonymousInt1 = 0;
        break;
      }
    }
    
    public void surfaceCreated(SurfaceHolder paramAnonymousSurfaceHolder)
    {
      VideoView.this.mSurfaceHolder = paramAnonymousSurfaceHolder;
      if ((VideoView.this.mMediaPlayer != null) && (VideoView.this.mCurrentState == 6) && (VideoView.this.mTargetState == 7))
      {
        VideoView.this.mMediaPlayer.setDisplay(VideoView.this.mSurfaceHolder);
        VideoView.this.resume();
        return;
      }
      VideoView.this.openVideo();
    }
    
    public void surfaceDestroyed(SurfaceHolder paramAnonymousSurfaceHolder)
    {
      VideoView.this.mSurfaceHolder = null;
      if (VideoView.this.mMediaController != null) {
        VideoView.this.mMediaController.hide();
      }
      VideoView.this.release(true);
    }
  };
  private MediaPlayer.OnSeekCompleteListener mSeekCompleteListener = new MediaPlayer.OnSeekCompleteListener()
  {
    public void onSeekComplete(MediaPlayer paramAnonymousMediaPlayer)
    {
      Log.d("onSeekComplete", new Object[0]);
      if (VideoView.this.mOnSeekCompleteListener != null) {
        VideoView.this.mOnSeekCompleteListener.onSeekComplete(paramAnonymousMediaPlayer);
      }
    }
  };
  private long mSeekWhenPrepared;
  MediaPlayer.OnVideoSizeChangedListener mSizeChangedListener = new MediaPlayer.OnVideoSizeChangedListener()
  {
    public void onVideoSizeChanged(MediaPlayer paramAnonymousMediaPlayer, int paramAnonymousInt1, int paramAnonymousInt2)
    {
      Log.d("onVideoSizeChanged: (%dx%d)", new Object[] { Integer.valueOf(paramAnonymousInt1), Integer.valueOf(paramAnonymousInt2) });
      VideoView.this.mVideoWidth = paramAnonymousMediaPlayer.getVideoWidth();
      VideoView.this.mVideoHeight = paramAnonymousMediaPlayer.getVideoHeight();
      VideoView.this.mVideoAspectRatio = paramAnonymousMediaPlayer.getVideoAspectRatio();
      if ((VideoView.this.mVideoWidth != 0) && (VideoView.this.mVideoHeight != 0)) {
        VideoView.this.setVideoLayout(VideoView.this.mVideoLayout, VideoView.this.mAspectRatio);
      }
    }
  };
  private int mSurfaceHeight;
  private SurfaceHolder mSurfaceHolder = null;
  private int mSurfaceWidth;
  private int mTargetState = 0;
  private MediaPlayer.OnTimedTextListener mTimedTextListener = new MediaPlayer.OnTimedTextListener()
  {
    public void onTimedText(String paramAnonymousString)
    {
      Log.i("onSubtitleUpdate: %s", new Object[] { paramAnonymousString });
      if (VideoView.this.mOnTimedTextListener != null) {
        VideoView.this.mOnTimedTextListener.onTimedText(paramAnonymousString);
      }
    }
    
    public void onTimedTextUpdate(byte[] paramAnonymousArrayOfByte, int paramAnonymousInt1, int paramAnonymousInt2)
    {
      Log.i("onSubtitleUpdate: bitmap subtitle, %dx%d", new Object[] { Integer.valueOf(paramAnonymousInt1), Integer.valueOf(paramAnonymousInt2) });
      if (VideoView.this.mOnTimedTextListener != null) {
        VideoView.this.mOnTimedTextListener.onTimedTextUpdate(paramAnonymousArrayOfByte, paramAnonymousInt1, paramAnonymousInt2);
      }
    }
  };
  private Uri mUri;
  private float mVideoAspectRatio;
  private int mVideoChroma = 1;
  private int mVideoHeight;
  private int mVideoLayout = 1;
  private int mVideoWidth;
  
  public VideoView(Context paramContext)
  {
    super(paramContext);
    initVideoView(paramContext);
  }
  
  public VideoView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
    initVideoView(paramContext);
  }
  
  public VideoView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    initVideoView(paramContext);
  }
  
  private void attachMediaController()
  {
    if ((this.mMediaPlayer != null) && (this.mMediaController != null))
    {
      this.mMediaController.setMediaPlayer(this);
      if (!(getParent() instanceof View)) {
        break label100;
      }
      localObject = (View)getParent();
      this.mMediaController.setAnchorView((View)localObject);
      this.mMediaController.setEnabled(isInPlaybackState());
      if (this.mUri != null)
      {
        localObject = this.mUri.getPathSegments();
        if ((localObject != null) && (!((List)localObject).isEmpty())) {
          break label105;
        }
      }
    }
    label100:
    label105:
    for (Object localObject = "null";; localObject = (String)((List)localObject).get(((List)localObject).size() - 1))
    {
      this.mMediaController.setFileName((String)localObject);
      return;
      localObject = this;
      break;
    }
  }
  
  private void initVideoView(Context paramContext)
  {
    this.mContext = paramContext;
    this.mVideoWidth = 0;
    this.mVideoHeight = 0;
    getHolder().setFormat(1);
    getHolder().addCallback(this.mSHCallback);
    if ((Build.VERSION.SDK_INT < 11) && (this.mHardwareDecoder)) {
      getHolder().setType(3);
    }
    setFocusable(true);
    setFocusableInTouchMode(true);
    requestFocus();
    this.mCurrentState = 0;
    this.mTargetState = 0;
    if ((paramContext instanceof Activity)) {
      ((Activity)paramContext).setVolumeControlStream(3);
    }
  }
  
  private void openVideo()
  {
    if ((this.mUri == null) || (this.mSurfaceHolder == null)) {
      return;
    }
    Object localObject = new Intent("com.android.music.musicservicecommand");
    ((Intent)localObject).putExtra("command", "pause");
    this.mContext.sendBroadcast((Intent)localObject);
    release(false);
    try
    {
      this.mDuration = -1L;
      this.mCurrentBufferPercentage = 0;
      this.mMediaPlayer = new MediaPlayer(this.mContext, this.mHardwareDecoder);
      this.mMediaPlayer.setOnPreparedListener(this.mPreparedListener);
      this.mMediaPlayer.setOnVideoSizeChangedListener(this.mSizeChangedListener);
      this.mMediaPlayer.setOnCompletionListener(this.mCompletionListener);
      this.mMediaPlayer.setOnErrorListener(this.mErrorListener);
      this.mMediaPlayer.setOnBufferingUpdateListener(this.mBufferingUpdateListener);
      this.mMediaPlayer.setOnInfoListener(this.mInfoListener);
      this.mMediaPlayer.setOnSeekCompleteListener(this.mSeekCompleteListener);
      this.mMediaPlayer.setOnTimedTextListener(this.mTimedTextListener);
      Log.d(" set user optional --------  ", new Object[0]);
      localObject = new HashMap();
      ((HashMap)localObject).put("rtsp_transport", "tcp");
      ((HashMap)localObject).put("analyzeduration", "1000000");
      this.mMediaPlayer.setDataSource(this.mContext, this.mUri, (Map)localObject);
      this.mMediaPlayer.setDisplay(this.mSurfaceHolder);
      this.mMediaPlayer.setBufferSize(this.mBufSize);
      localObject = this.mMediaPlayer;
      if (this.mVideoChroma != 0) {
        break label344;
      }
      i = 0;
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        Log.e("Unable to open content: " + this.mUri, localIOException);
        this.mCurrentState = -1;
        this.mTargetState = -1;
        this.mErrorListener.onError(this.mMediaPlayer, 1, 0);
        return;
        int i = 1;
      }
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      Log.e("Unable to open content: " + this.mUri, localIllegalArgumentException);
      this.mCurrentState = -1;
      this.mTargetState = -1;
      this.mErrorListener.onError(this.mMediaPlayer, 1, 0);
    }
    ((MediaPlayer)localObject).setVideoChroma(i);
    this.mMediaPlayer.setScreenOnWhilePlaying(true);
    this.mMediaPlayer.prepareAsync();
    this.mCurrentState = 1;
    attachMediaController();
    return;
    label344:
  }
  
  private void release(boolean paramBoolean)
  {
    if (this.mMediaPlayer != null)
    {
      this.mMediaPlayer.reset();
      this.mMediaPlayer.release();
      this.mMediaPlayer = null;
      this.mCurrentState = 0;
      if (paramBoolean) {
        this.mTargetState = 0;
      }
    }
  }
  
  private void toggleMediaControlsVisiblity()
  {
    if (this.mMediaController.isShowing())
    {
      this.mMediaController.hide();
      return;
    }
    this.mMediaController.show();
  }
  
  public void addTimedTextSource(String paramString)
  {
    if (this.mMediaPlayer != null) {
      this.mMediaPlayer.addTimedTextSource(paramString);
    }
  }
  
  public int getAudioTrack()
  {
    if (this.mMediaPlayer != null) {
      return this.mMediaPlayer.getAudioTrack();
    }
    return -1;
  }
  
  public SparseArray<MediaFormat> getAudioTrackMap(String paramString)
  {
    if (this.mMediaPlayer != null) {
      return this.mMediaPlayer.findTrackFromTrackInfo(2, this.mMediaPlayer.getTrackInfo(paramString));
    }
    return null;
  }
  
  public int getBufferPercentage()
  {
    if (this.mMediaPlayer != null) {
      return this.mCurrentBufferPercentage;
    }
    return 0;
  }
  
  public long getCurrentPosition()
  {
    if (isInPlaybackState()) {
      return this.mMediaPlayer.getCurrentPosition();
    }
    return 0L;
  }
  
  public long getDuration()
  {
    if (isInPlaybackState())
    {
      if (this.mDuration > 0L) {
        return this.mDuration;
      }
      this.mDuration = this.mMediaPlayer.getDuration();
      return this.mDuration;
    }
    this.mDuration = -1L;
    return this.mDuration;
  }
  
  public String getMetaEncoding()
  {
    if (this.mMediaPlayer != null) {
      return this.mMediaPlayer.getMetaEncoding();
    }
    return null;
  }
  
  public SparseArray<MediaFormat> getSubTrackMap(String paramString)
  {
    if (this.mMediaPlayer != null) {
      return this.mMediaPlayer.findTrackFromTrackInfo(3, this.mMediaPlayer.getTrackInfo(paramString));
    }
    return null;
  }
  
  public int getTimedTextLocation()
  {
    if (this.mMediaPlayer != null) {
      return this.mMediaPlayer.getTimedTextLocation();
    }
    return -1;
  }
  
  public String getTimedTextPath()
  {
    if (this.mMediaPlayer != null) {
      return this.mMediaPlayer.getTimedTextPath();
    }
    return null;
  }
  
  public int getTimedTextTrack()
  {
    if (this.mMediaPlayer != null) {
      return this.mMediaPlayer.getTimedTextTrack();
    }
    return -1;
  }
  
  public float getVideoAspectRatio()
  {
    return this.mVideoAspectRatio;
  }
  
  public int getVideoHeight()
  {
    return this.mVideoHeight;
  }
  
  public int getVideoWidth()
  {
    return this.mVideoWidth;
  }
  
  public boolean isBuffering()
  {
    if (this.mMediaPlayer != null) {
      return this.mMediaPlayer.isBuffering();
    }
    return false;
  }
  
  protected boolean isInPlaybackState()
  {
    return (this.mMediaPlayer != null) && (this.mCurrentState != -1) && (this.mCurrentState != 0) && (this.mCurrentState != 1);
  }
  
  public void isLandScape(boolean paramBoolean)
  {
    this.isLandScape = this.isLandScape;
  }
  
  public boolean isPlaying()
  {
    return (isInPlaybackState()) && (this.mMediaPlayer.isPlaying());
  }
  
  public void isPortrait(boolean paramBoolean)
  {
    this.isPartrait = paramBoolean;
  }
  
  public boolean isValid()
  {
    return (this.mSurfaceHolder != null) && (this.mSurfaceHolder.getSurface().isValid());
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    int i;
    if ((paramInt != 4) && (paramInt != 24) && (paramInt != 25) && (paramInt != 82) && (paramInt != 5) && (paramInt != 6))
    {
      i = 1;
      if ((!isInPlaybackState()) || (i == 0) || (this.mMediaController == null)) {
        break label181;
      }
      if ((paramInt != 79) && (paramInt != 85) && (paramInt != 62)) {
        break label113;
      }
      if (!this.mMediaPlayer.isPlaying()) {
        break label100;
      }
      pause();
      this.mMediaController.show();
    }
    label100:
    label113:
    label142:
    do
    {
      do
      {
        return true;
        i = 0;
        break;
        start();
        this.mMediaController.hide();
        return true;
        if (paramInt != 126) {
          break label142;
        }
      } while (this.mMediaPlayer.isPlaying());
      start();
      this.mMediaController.hide();
      return true;
      if ((paramInt != 86) && (paramInt != 127)) {
        break label177;
      }
    } while (!this.mMediaPlayer.isPlaying());
    pause();
    this.mMediaController.show();
    return true;
    label177:
    toggleMediaControlsVisiblity();
    label181:
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    setMeasuredDimension(getDefaultSize(this.mVideoWidth, paramInt1), getDefaultSize(this.mVideoHeight, paramInt2));
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if ((isInPlaybackState()) && (this.mMediaController != null)) {
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
    }
    this.mTargetState = 4;
  }
  
  public void resume()
  {
    if ((this.mSurfaceHolder == null) && (this.mCurrentState == 6)) {
      this.mTargetState = 7;
    }
    while (this.mCurrentState != 8) {
      return;
    }
    openVideo();
  }
  
  public void seekTo(long paramLong)
  {
    if (isInPlaybackState())
    {
      this.mMediaPlayer.seekTo(paramLong);
      this.mSeekWhenPrepared = 0L;
      return;
    }
    this.mSeekWhenPrepared = paramLong;
  }
  
  public void setAudioTrack(int paramInt)
  {
    if (this.mMediaPlayer != null) {
      this.mMediaPlayer.selectTrack(paramInt);
    }
  }
  
  public void setBufferSize(int paramInt)
  {
    this.mBufSize = paramInt;
  }
  
  public void setHardwareDecoder(boolean paramBoolean)
  {
    this.mHardwareDecoder = paramBoolean;
  }
  
  public void setMediaBufferingIndicator(View paramView)
  {
    if (this.mMediaBufferingIndicator != null) {
      this.mMediaBufferingIndicator.setVisibility(8);
    }
    this.mMediaBufferingIndicator = paramView;
  }
  
  public void setMediaController(MediaController paramMediaController)
  {
    if (this.mMediaController != null) {
      this.mMediaController.hide();
    }
    this.mMediaController = paramMediaController;
    attachMediaController();
  }
  
  public void setMetaEncoding(String paramString)
  {
    if (this.mMediaPlayer != null) {
      this.mMediaPlayer.setMetaEncoding(paramString);
    }
  }
  
  public void setOnBufferingUpdateListener(MediaPlayer.OnBufferingUpdateListener paramOnBufferingUpdateListener)
  {
    this.mOnBufferingUpdateListener = paramOnBufferingUpdateListener;
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
  
  public void setOnSeekCompleteListener(MediaPlayer.OnSeekCompleteListener paramOnSeekCompleteListener)
  {
    this.mOnSeekCompleteListener = paramOnSeekCompleteListener;
  }
  
  public void setOnTimedTextListener(MediaPlayer.OnTimedTextListener paramOnTimedTextListener)
  {
    this.mOnTimedTextListener = paramOnTimedTextListener;
  }
  
  public void setSubTrack(int paramInt)
  {
    if (this.mMediaPlayer != null) {
      this.mMediaPlayer.selectTrack(paramInt);
    }
  }
  
  public void setTimedTextEncoding(String paramString)
  {
    if (this.mMediaPlayer != null) {
      this.mMediaPlayer.setTimedTextEncoding(paramString);
    }
  }
  
  public void setTimedTextShown(boolean paramBoolean)
  {
    if (this.mMediaPlayer != null) {
      this.mMediaPlayer.setTimedTextShown(paramBoolean);
    }
  }
  
  public void setVideoChroma(int paramInt)
  {
    SurfaceHolder localSurfaceHolder = getHolder();
    if (paramInt == 0) {}
    for (int i = 4;; i = 1)
    {
      localSurfaceHolder.setFormat(i);
      this.mVideoChroma = paramInt;
      return;
    }
  }
  
  public void setVideoLayout(int paramInt, float paramFloat)
  {
    ViewGroup.LayoutParams localLayoutParams = getLayoutParams();
    Object localObject = ScreenResolution.getResolution(this.mContext);
    int i = ((Integer)((Pair)localObject).first).intValue();
    int j = ((Integer)((Pair)localObject).second).intValue();
    float f2 = i / j;
    if (paramFloat <= 0.01F) {}
    for (float f1 = this.mVideoAspectRatio;; f1 = paramFloat)
    {
      this.mSurfaceHeight = this.mVideoHeight;
      this.mSurfaceWidth = this.mVideoWidth;
      if ((paramInt != 0) || (this.mSurfaceWidth >= i) || (this.mSurfaceHeight >= j)) {
        break;
      }
      localLayoutParams.width = ((int)(this.mSurfaceHeight * f1));
      localLayoutParams.height = this.mSurfaceHeight;
      setLayoutParams(localLayoutParams);
      getHolder().setFixedSize(this.mSurfaceWidth, this.mSurfaceHeight);
      Log.d("VIDEO: %dx%dx%f, Surface: %dx%d, LP: %dx%d, Window: %dx%dx%f", new Object[] { Integer.valueOf(this.mVideoWidth), Integer.valueOf(this.mVideoHeight), Float.valueOf(this.mVideoAspectRatio), Integer.valueOf(this.mSurfaceWidth), Integer.valueOf(this.mSurfaceHeight), Integer.valueOf(localLayoutParams.width), Integer.valueOf(localLayoutParams.height), Integer.valueOf(i), Integer.valueOf(j), Float.valueOf(f2) });
      this.mVideoLayout = paramInt;
      this.mAspectRatio = paramFloat;
      return;
    }
    if (paramInt == 3)
    {
      if (f2 > f1)
      {
        k = i;
        label289:
        localLayoutParams.width = k;
        if (f2 >= f1) {
          break label328;
        }
      }
      label328:
      for (k = j;; k = (int)(i / f1))
      {
        localLayoutParams.height = k;
        break;
        k = (int)(j * f1);
        break label289;
      }
    }
    if (paramInt == 4)
    {
      localObject = (ViewGroup)getParent();
      float f3 = ((ViewGroup)localObject).getWidth() / ((ViewGroup)localObject).getHeight();
      if (f3 < f1)
      {
        k = ((ViewGroup)localObject).getWidth();
        label382:
        localLayoutParams.width = k;
        if (f3 <= f1) {
          break label429;
        }
      }
      label429:
      for (k = ((ViewGroup)localObject).getHeight();; k = Math.round(((ViewGroup)localObject).getWidth() / f1))
      {
        localLayoutParams.height = k;
        break;
        k = Math.round(((ViewGroup)localObject).getHeight() * f1);
        break label382;
      }
    }
    label453:
    int m;
    if (paramInt == 2)
    {
      k = 1;
      if ((k == 0) && (f2 >= f1)) {
        break label508;
      }
      m = i;
      label469:
      localLayoutParams.width = m;
      if ((k == 0) && (f2 <= f1)) {
        break label519;
      }
    }
    label508:
    label519:
    for (int k = j;; k = (int)(i / f1))
    {
      localLayoutParams.height = k;
      break;
      k = 0;
      break label453;
      m = (int)(j * f1);
      break label469;
    }
  }
  
  public void setVideoPath(String paramString)
  {
    setVideoURI(Uri.parse(paramString));
  }
  
  public void setVideoQuality(int paramInt)
  {
    if (this.mMediaPlayer != null) {
      this.mMediaPlayer.setVideoQuality(paramInt);
    }
  }
  
  public void setVideoURI(Uri paramUri)
  {
    setVideoURI(paramUri, null);
  }
  
  public void setVideoURI(Uri paramUri, Map<String, String> paramMap)
  {
    this.mUri = paramUri;
    this.mHeaders = paramMap;
    this.mSeekWhenPrepared = 0L;
    openVideo();
    requestLayout();
    invalidate();
  }
  
  public void setVolume(float paramFloat1, float paramFloat2)
  {
    if (this.mMediaPlayer != null) {
      this.mMediaPlayer.setVolume(paramFloat1, paramFloat2);
    }
  }
  
  public void showMediaController()
  {
    this.mMediaController.show();
  }
  
  public void start()
  {
    if (isInPlaybackState())
    {
      this.mMediaPlayer.start();
      this.mCurrentState = 3;
    }
    this.mTargetState = 3;
  }
  
  public void stopPlayback()
  {
    if (this.mMediaPlayer != null)
    {
      this.mMediaPlayer.stop();
      this.mMediaPlayer.release();
      this.mMediaPlayer = null;
      this.mCurrentState = 0;
      this.mTargetState = 0;
    }
  }
  
  public void suspend()
  {
    if (isInPlaybackState())
    {
      release(false);
      this.mCurrentState = 8;
      Log.d("Unable to suspend video. Release MediaPlayer.", new Object[0]);
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\io\vov\vitamio\widget\VideoView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */