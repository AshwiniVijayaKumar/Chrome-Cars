package com.adsdk.sdk.video;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.MediaController.MediaPlayerControl;
import android.widget.TextView;
import com.adsdk.sdk.Log;
import java.lang.ref.WeakReference;
import java.util.Formatter;
import java.util.Locale;
import java.util.Vector;

public class MediaController
  extends FrameLayout
{
  private static final int DEFAULT_TIMEOUT = 5000;
  private static final int FADE_OUT = 1;
  private static final int SHOW_PROGRESS = 2;
  private double buttonWidthPercent = 0.09D;
  private LinearLayout mBottomBar;
  private Context mContext;
  private boolean mFixed;
  StringBuilder mFormatBuilder;
  Formatter mFormatter;
  private ResourceHandler mHandler = new ResourceHandler(this);
  private TextView mLeftTime;
  private OnPauseListener mOnPauseListener;
  private OnReplayListener mOnReplayListener;
  private OnUnpauseListener mOnUnpauseListener;
  private AspectRatioImageViewWidth mPauseButton;
  private View.OnClickListener mPauseListener = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      MediaController.this.doPauseResume();
    }
  };
  private MediaController.MediaPlayerControl mPlayer;
  private AspectRatioImageViewWidth mReplayButton;
  private View.OnClickListener mReplayListener = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      MediaController.this.replay();
    }
  };
  private ResourceManager mResourceManager;
  private boolean mShowing;
  private LinearLayout mTopBar;
  private VideoData mVideoData;
  
  public MediaController(Context paramContext, VideoData paramVideoData)
  {
    super(paramContext);
    setVisibility(8);
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
    this.mShowing = false;
    this.mFixed = false;
    this.mContext = paramContext;
    this.mVideoData = paramVideoData;
    if (this.mVideoData == null) {
      throw new IllegalArgumentException("Video info cannot be null");
    }
    this.mFormatBuilder = new StringBuilder();
    this.mFormatter = new Formatter(this.mFormatBuilder, Locale.getDefault());
    this.mResourceManager = new ResourceManager(this.mContext, this.mHandler);
    buildNavigationBarView(localDisplayMetrics);
    Log.d("MediaController created");
  }
  
  private void doPauseResume()
  {
    if (this.mPlayer == null) {
      return;
    }
    if (this.mPlayer.isPlaying())
    {
      this.mPlayer.pause();
      if (this.mOnPauseListener != null) {
        this.mOnPauseListener.onVideoPause();
      }
    }
    for (;;)
    {
      updatePausePlay();
      return;
      this.mPlayer.start();
      if (this.mOnUnpauseListener != null) {
        this.mOnUnpauseListener.onVideoUnpause();
      }
    }
  }
  
  private void handleMessage(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    default: 
      return;
    case 1: 
      hide();
      return;
    case 2: 
      refreshProgress();
      return;
    }
    switch (paramMessage.arg1)
    {
    }
    for (;;)
    {
      requestLayout();
      return;
      if (this.mTopBar != null)
      {
        paramMessage = this.mResourceManager.getResource(this.mContext, -1);
        if (paramMessage != null)
        {
          this.mTopBar.setBackgroundDrawable(paramMessage);
          continue;
          if (this.mBottomBar != null)
          {
            paramMessage = this.mResourceManager.getResource(this.mContext, -2);
            if (paramMessage != null)
            {
              this.mBottomBar.setBackgroundDrawable(paramMessage);
              continue;
              if (this.mPauseButton != null)
              {
                updatePausePlay();
                continue;
                if (this.mPauseButton != null)
                {
                  updatePausePlay();
                  continue;
                  if (this.mReplayButton != null) {
                    updateReplay();
                  }
                }
              }
            }
          }
        }
      }
    }
  }
  
  private void initNavigationBarControllerView(int paramInt, DisplayMetrics paramDisplayMetrics)
  {
    int i = paramDisplayMetrics.widthPixels;
    if (!this.mVideoData.showBottomNavigationBar)
    {
      this.mBottomBar.setVisibility(8);
      if (this.mVideoData.showTopNavigationBar) {
        break label557;
      }
      this.mTopBar.setVisibility(8);
    }
    for (;;)
    {
      if (!this.mVideoData.showNavigationBars) {
        setVisibility(8);
      }
      return;
      this.mBottomBar.setVisibility(0);
      if ((this.mVideoData.bottomNavigationBarBackground != null) && (this.mVideoData.bottomNavigationBarBackground.length() > 0))
      {
        this.mResourceManager.fetchResource(this.mContext, this.mVideoData.bottomNavigationBarBackground, -2);
        label111:
        if (this.mPauseButton != null)
        {
          if ((this.mVideoData.pauseButtonImage == null) || (this.mVideoData.pauseButtonImage.length() <= 0)) {
            break label475;
          }
          this.mPauseButton.setBackgroundDrawable(null);
          this.mResourceManager.fetchResource(this.mContext, this.mVideoData.pauseButtonImage, -12);
          label169:
          if ((this.mVideoData.playButtonImage != null) && (this.mVideoData.playButtonImage.length() > 0)) {
            this.mResourceManager.fetchResource(this.mContext, this.mVideoData.playButtonImage, -11);
          }
          this.mPauseButton.setOnClickListener(this.mPauseListener);
          if (!this.mVideoData.showPauseButton) {
            break label498;
          }
          this.mPauseButton.setVisibility(0);
        }
        label241:
        if (this.mReplayButton != null)
        {
          if ((this.mVideoData.replayButtonImage == null) || (this.mVideoData.replayButtonImage.length() <= 0)) {
            break label510;
          }
          this.mReplayButton.setImageDrawable(null);
          this.mResourceManager.fetchResource(this.mContext, this.mVideoData.replayButtonImage, -13);
          label299:
          this.mReplayButton.setOnClickListener(this.mReplayListener);
          if (!this.mVideoData.showReplayButton) {
            break label533;
          }
          this.mReplayButton.setVisibility(0);
        }
        label328:
        if (this.mLeftTime != null)
        {
          if (!this.mVideoData.showTimer) {
            break label545;
          }
          this.mLeftTime.setVisibility(0);
        }
      }
      for (;;)
      {
        if (this.mVideoData.icons.isEmpty()) {
          break label555;
        }
        paramInt = 0;
        while (paramInt < this.mVideoData.icons.size())
        {
          paramDisplayMetrics = (NavIconData)this.mVideoData.icons.get(paramInt);
          paramDisplayMetrics = new NavIcon(this.mContext, paramDisplayMetrics);
          LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams((int)(i * this.buttonWidthPercent), (int)(i * this.buttonWidthPercent));
          this.mBottomBar.addView(paramDisplayMetrics, localLayoutParams);
          paramInt += 1;
        }
        break;
        this.mBottomBar.setBackgroundDrawable(this.mResourceManager.getResource(this.mContext, -2));
        break label111;
        label475:
        this.mPauseButton.setImageDrawable(this.mResourceManager.getResource(this.mContext, -12));
        break label169;
        label498:
        this.mPauseButton.setVisibility(8);
        break label241;
        label510:
        this.mReplayButton.setImageDrawable(this.mResourceManager.getResource(this.mContext, -13));
        break label299;
        label533:
        this.mReplayButton.setVisibility(8);
        break label328;
        label545:
        this.mLeftTime.setVisibility(8);
      }
      label555:
      break;
      label557:
      this.mTopBar.setVisibility(0);
      if ((this.mVideoData.topNavigationBarBackground != null) && (this.mVideoData.topNavigationBarBackground.length() > 0)) {
        this.mResourceManager.fetchResource(this.mContext, this.mVideoData.topNavigationBarBackground, -1);
      } else {
        this.mTopBar.setBackgroundDrawable(this.mResourceManager.getResource(this.mContext, -1));
      }
    }
  }
  
  private void refreshProgress()
  {
    if (this.mShowing)
    {
      updatePausePlay();
      int i = setProgress();
      if ((this.mPlayer != null) && (this.mPlayer.isPlaying()))
      {
        this.mHandler.removeMessages(2);
        Message localMessage = this.mHandler.obtainMessage(2);
        this.mHandler.sendMessageDelayed(localMessage, 1000 - i % 1000);
      }
    }
  }
  
  private int setProgress()
  {
    int i;
    if (this.mPlayer == null) {
      i = 0;
    }
    int j;
    int k;
    do
    {
      return i;
      j = this.mPlayer.getCurrentPosition();
      k = this.mPlayer.getDuration();
      i = j;
    } while (this.mLeftTime == null);
    this.mLeftTime.setText(stringForTime(k - j));
    return j;
  }
  
  private String stringForTime(int paramInt)
  {
    int j = paramInt / 1000;
    paramInt = j % 60;
    int i = j / 60 % 60;
    j /= 3600;
    this.mFormatBuilder.setLength(0);
    if (j > 0) {
      return this.mFormatter.format("%d:%02d:%02d", new Object[] { Integer.valueOf(j), Integer.valueOf(i), Integer.valueOf(paramInt) }).toString();
    }
    if (i > 0) {
      return this.mFormatter.format("%02d:%02d", new Object[] { Integer.valueOf(i), Integer.valueOf(paramInt) }).toString();
    }
    return this.mFormatter.format("0:%02d", new Object[] { Integer.valueOf(paramInt) }).toString();
  }
  
  private void updatePausePlay()
  {
    if (this.mPauseButton == null) {
      return;
    }
    if ((this.mPlayer != null) && (this.mPlayer.isPlaying()))
    {
      if (this.mResourceManager.containsResource(-12))
      {
        localDrawable = this.mResourceManager.getResource(this.mContext, -12);
        this.mPauseButton.setImageDrawable(localDrawable);
        return;
      }
      localDrawable = this.mResourceManager.getResource(this.mContext, -12);
      this.mPauseButton.setImageDrawable(localDrawable);
      return;
    }
    if (this.mResourceManager.containsResource(-11))
    {
      localDrawable = this.mResourceManager.getResource(this.mContext, -11);
      this.mPauseButton.setImageDrawable(localDrawable);
      return;
    }
    Drawable localDrawable = this.mResourceManager.getResource(this.mContext, -11);
    this.mPauseButton.setImageDrawable(localDrawable);
  }
  
  private void updateReplay()
  {
    if (this.mReplayButton == null) {
      return;
    }
    if (this.mResourceManager.containsResource(-13))
    {
      localDrawable = this.mResourceManager.getResource(this.mContext, -13);
      this.mReplayButton.setImageDrawable(localDrawable);
      return;
    }
    Drawable localDrawable = this.mResourceManager.getResource(this.mContext, -13);
    this.mReplayButton.setImageDrawable(localDrawable);
  }
  
  protected void buildNavigationBarView(DisplayMetrics paramDisplayMetrics)
  {
    int i = paramDisplayMetrics.widthPixels;
    setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
    this.mTopBar = new LinearLayout(this.mContext);
    this.mTopBar.setOrientation(0);
    this.mTopBar.setWeightSum(1.0F);
    this.mTopBar.setBackgroundColor(0);
    Object localObject = new FrameLayout.LayoutParams(-1, (int)(i * 0.119D));
    ((FrameLayout.LayoutParams)localObject).gravity = 55;
    this.mTopBar.setGravity(16);
    int j = (int)TypedValue.applyDimension(1, 5.0F, getResources().getDisplayMetrics());
    addView(this.mTopBar, (ViewGroup.LayoutParams)localObject);
    this.mBottomBar = new LinearLayout(this.mContext);
    this.mBottomBar.setOrientation(0);
    this.mBottomBar.setGravity(16);
    localObject = new FrameLayout.LayoutParams(-1, (int)(i * 0.119D));
    ((FrameLayout.LayoutParams)localObject).gravity = 80;
    this.mBottomBar.setWeightSum(1.0F);
    this.mBottomBar.setPadding(j, 0, j, 0);
    this.mBottomBar.setBackgroundColor(0);
    addView(this.mBottomBar, (ViewGroup.LayoutParams)localObject);
    localObject = new LinearLayout(this.mContext);
    new LinearLayout.LayoutParams(-2, -1).gravity = 3;
    ((LinearLayout)localObject).setOrientation(0);
    ((LinearLayout)localObject).setGravity(16);
    ((LinearLayout)localObject).setBackgroundColor(-16711936);
    this.mReplayButton = new AspectRatioImageViewWidth(this.mContext);
    localObject = new LinearLayout.LayoutParams((int)(i * this.buttonWidthPercent), (int)(i * this.buttonWidthPercent));
    ((LinearLayout.LayoutParams)localObject).gravity = 16;
    this.mReplayButton.setAdjustViewBounds(true);
    this.mReplayButton.setPadding(j, j, j, j);
    this.mBottomBar.addView(this.mReplayButton, (ViewGroup.LayoutParams)localObject);
    this.mPauseButton = new AspectRatioImageViewWidth(this.mContext);
    localObject = new LinearLayout.LayoutParams((int)(i * this.buttonWidthPercent), (int)(i * this.buttonWidthPercent));
    ((LinearLayout.LayoutParams)localObject).gravity = 16;
    this.mPauseButton.setPadding(j, j, j, j);
    this.mPauseButton.setAdjustViewBounds(true);
    this.mBottomBar.addView(this.mPauseButton, (ViewGroup.LayoutParams)localObject);
    this.mLeftTime = new AutoResizeTextView(this.mContext);
    localObject = new LinearLayout.LayoutParams(-2, -2);
    ((LinearLayout.LayoutParams)localObject).gravity = 16;
    this.mLeftTime.setTypeface(Typeface.defaultFromStyle(1));
    this.mLeftTime.setPadding(j, j, j, j);
    this.mLeftTime.setGravity(16);
    this.mLeftTime.setTextSize(23.0F);
    this.mBottomBar.addView(this.mLeftTime, (ViewGroup.LayoutParams)localObject);
    localObject = new View(this.mContext);
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(0, 0);
    localLayoutParams.weight = 1.0F;
    localLayoutParams.gravity = 16;
    this.mBottomBar.addView((View)localObject, localLayoutParams);
    initNavigationBarControllerView(j, paramDisplayMetrics);
  }
  
  public boolean canToggle()
  {
    return this.mVideoData.allowTapNavigationBars;
  }
  
  public void hide()
  {
    Log.d("HIDE");
    this.mFixed = false;
    if (canToggle())
    {
      Log.d("Hide can toggle");
      if (this.mShowing)
      {
        Log.d("Hide change visibility");
        this.mHandler.removeMessages(2);
        setVisibility(8);
        this.mShowing = false;
      }
    }
  }
  
  public boolean isShowing()
  {
    return this.mShowing;
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if ((paramInt != 4) && (paramInt != 24) && (paramInt != 25) && (paramInt != 82) && (paramInt != 5) && (paramInt != 6))
    {
      if ((paramInt == 79) || (paramInt == 85))
      {
        doPauseResume();
        return true;
      }
      if ((paramInt != 86) || (this.mPlayer == null) || (!this.mPlayer.isPlaying())) {
        break label109;
      }
      this.mPlayer.pause();
      if (this.mOnPauseListener != null) {
        this.mOnPauseListener.onVideoPause();
      }
    }
    for (;;)
    {
      return super.onKeyDown(paramInt, paramKeyEvent);
      label109:
      toggle();
    }
  }
  
  public void onPause()
  {
    show(0);
  }
  
  public void onStart()
  {
    refreshProgress();
  }
  
  public void replay()
  {
    if (this.mPlayer != null)
    {
      this.mPlayer.seekTo(0);
      this.mPlayer.start();
    }
    refreshProgress();
    if (this.mOnReplayListener != null) {
      this.mOnReplayListener.onVideoReplay();
    }
  }
  
  public void resizeTopBar(int paramInt)
  {
    if (paramInt <= 0) {}
    int i;
    do
    {
      return;
      i = (int)TypedValue.applyDimension(1, 4.0F, getResources().getDisplayMetrics());
    } while (this.mTopBar == null);
    ViewGroup.LayoutParams localLayoutParams = this.mTopBar.getLayoutParams();
    localLayoutParams.height = (paramInt + i);
    this.mTopBar.setLayoutParams(localLayoutParams);
  }
  
  public void setMediaPlayer(MediaController.MediaPlayerControl paramMediaPlayerControl)
  {
    this.mPlayer = paramMediaPlayerControl;
    updatePausePlay();
  }
  
  public void setOnPauseListener(OnPauseListener paramOnPauseListener)
  {
    this.mOnPauseListener = paramOnPauseListener;
  }
  
  public void setOnReplayListener(OnReplayListener paramOnReplayListener)
  {
    this.mOnReplayListener = paramOnReplayListener;
  }
  
  public void setOnUnpauseListener(OnUnpauseListener paramOnUnpauseListener)
  {
    this.mOnUnpauseListener = paramOnUnpauseListener;
  }
  
  public void show()
  {
    show(5000);
  }
  
  public void show(int paramInt)
  {
    Log.d("SHOW:" + paramInt);
    if (paramInt == 0) {
      this.mFixed = true;
    }
    if (!this.mShowing)
    {
      setVisibility(0);
      this.mShowing = true;
      Log.d("Change Visibility");
    }
    refreshProgress();
    this.mHandler.removeMessages(1);
    if ((paramInt != 0) && (!this.mFixed))
    {
      Message localMessage = this.mHandler.obtainMessage(1);
      this.mHandler.sendMessageDelayed(localMessage, paramInt);
    }
  }
  
  public void toggle()
  {
    if (canToggle())
    {
      if (this.mShowing) {
        hide();
      }
    }
    else {
      return;
    }
    show();
  }
  
  public static abstract interface OnPauseListener
  {
    public abstract void onVideoPause();
  }
  
  public static abstract interface OnReplayListener
  {
    public abstract void onVideoReplay();
  }
  
  public static abstract interface OnUnpauseListener
  {
    public abstract void onVideoUnpause();
  }
  
  private static class ResourceHandler
    extends Handler
  {
    private final WeakReference<MediaController> mController;
    
    public ResourceHandler(MediaController paramMediaController)
    {
      this.mController = new WeakReference(paramMediaController);
    }
    
    public void handleMessage(Message paramMessage)
    {
      MediaController localMediaController = (MediaController)this.mController.get();
      if (localMediaController != null) {
        localMediaController.handleMessage(paramMessage);
      }
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\adsdk\sdk\video\MediaController.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */