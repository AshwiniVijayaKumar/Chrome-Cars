package com.adsdk.sdk.video;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;
import com.adsdk.sdk.AdManager;
import com.adsdk.sdk.Const;
import com.adsdk.sdk.Log;
import com.adsdk.sdk.Util;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

public class RichMediaActivity
  extends Activity
{
  public static final int TYPE_BROWSER = 0;
  public static final int TYPE_INTERSTITIAL = 2;
  public static final int TYPE_UNKNOWN = -1;
  public static final int TYPE_VIDEO = 1;
  private RichMediaAd mAd;
  private boolean mCanClose;
  private Runnable mCheckProgressTask = new Runnable()
  {
    public void run()
    {
      Log.w("Video playback is being checked");
      if (RichMediaActivity.this.mVideoView.getCurrentPosition() - RichMediaActivity.this.mTimeTest <= 1)
      {
        Log.w("Video playback too slow. Ending");
        RichMediaActivity.this.finish();
        return;
      }
      Log.w("Video playback has restarted");
    }
  };
  private VideoView mCustomVideoView;
  private FrameLayout mCustomView;
  private WebChromeClient.CustomViewCallback mCustomViewCallback;
  private int mEnterAnim;
  private int mExitAnim;
  private ResourceHandler mHandler;
  protected boolean mInterstitialAutocloseReset;
  private Timer mInterstitialAutocloseTimer;
  private Timer mInterstitialCanCloseTimer;
  private final View.OnClickListener mInterstitialClickListener = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      Log.d("ADSDK", "RichMediaActivity mInterstitialClickListener");
      if (RichMediaActivity.this.mInterstitialController != null)
      {
        RichMediaActivity.this.mInterstitialController.toggle();
        RichMediaActivity.this.mInterstitialController.resetAutoclose();
      }
    }
  };
  private InterstitialController mInterstitialController;
  private InterstitialData mInterstitialData;
  private Timer mInterstitialLoadingTimer;
  private WebFrame mInterstitialView;
  private FrameLayout mLoadingView;
  private MediaController mMediaController;
  WebViewClient.OnPageLoadedListener mOnInterstitialLoadedListener = new WebViewClient.OnPageLoadedListener()
  {
    public void onPageLoaded()
    {
      Log.v("onPageLoaded");
      Object localObject;
      if ((RichMediaActivity.this.mInterstitialData != null) && (RichMediaActivity.this.mInterstitialData.autoclose > 0) && (RichMediaActivity.this.mInterstitialAutocloseTimer == null) && (!RichMediaActivity.this.mInterstitialAutocloseReset))
      {
        localObject = new RichMediaActivity.InterstitialAutocloseTask(RichMediaActivity.this, RichMediaActivity.this);
        RichMediaActivity.this.mInterstitialAutocloseTimer = new Timer();
        RichMediaActivity.this.mInterstitialAutocloseTimer.schedule((TimerTask)localObject, RichMediaActivity.this.mInterstitialData.autoclose * 1000);
        Log.v("onPageLoaded mInterstitialAutocloseTimer");
      }
      if ((RichMediaActivity.this.mInterstitialData != null) && (RichMediaActivity.this.mInterstitialData.showSkipButtonAfter > 0)) {
        if (RichMediaActivity.this.mInterstitialCanCloseTimer == null)
        {
          localObject = new RichMediaActivity.CanSkipTask(RichMediaActivity.this, RichMediaActivity.this);
          RichMediaActivity.this.mInterstitialCanCloseTimer = new Timer();
          RichMediaActivity.this.mInterstitialCanCloseTimer.schedule((TimerTask)localObject, RichMediaActivity.this.mInterstitialData.showSkipButtonAfter * 1000);
          Log.v("onPageLoaded mInterstitialCanCloseTimer");
        }
      }
      for (;;)
      {
        if (RichMediaActivity.this.mInterstitialLoadingTimer != null)
        {
          RichMediaActivity.this.mInterstitialLoadingTimer.cancel();
          RichMediaActivity.this.mInterstitialLoadingTimer = null;
        }
        RichMediaActivity.this.mPageLoaded = true;
        RichMediaActivity.this.mInterstitialController.pageLoaded();
        return;
        RichMediaActivity.this.mCanClose = true;
      }
    }
  };
  View.OnClickListener mOnInterstitialSkipListener = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      Log.v("###########TRACKING SKIP INTERSTITIAL");
      RichMediaActivity.this.mResult = true;
      RichMediaActivity.this.setResult(-1);
      RichMediaActivity.this.finish();
    }
  };
  InterstitialController.OnResetAutocloseListener mOnResetAutocloseListener = new InterstitialController.OnResetAutocloseListener()
  {
    public void onResetAutoclose()
    {
      Log.v("###########RESET AUTOCLOSE INTERSTITIAL");
      RichMediaActivity.this.mInterstitialAutocloseReset = true;
      if (RichMediaActivity.this.mInterstitialAutocloseTimer != null)
      {
        RichMediaActivity.this.mInterstitialAutocloseTimer.cancel();
        RichMediaActivity.this.mInterstitialAutocloseTimer = null;
      }
    }
  };
  SDKVideoView.OnTimeEventListener mOnVideoCanCloseEventListener = new SDKVideoView.OnTimeEventListener()
  {
    public void onTimeEvent(int paramAnonymousInt)
    {
      Log.d("###########CAN CLOSE VIDEO:" + paramAnonymousInt);
      RichMediaActivity.this.mCanClose = true;
      if ((RichMediaActivity.this.mVideoData.showSkipButton) && (RichMediaActivity.this.mSkipButton != null))
      {
        RichMediaActivity.this.mSkipButton.setImageDrawable(RichMediaActivity.this.mResourceManager.getResource(RichMediaActivity.this, -18));
        RichMediaActivity.this.mSkipButton.setVisibility(0);
      }
    }
  };
  MediaPlayer.OnCompletionListener mOnVideoCompletionListener = new MediaPlayer.OnCompletionListener()
  {
    public void onCompletion(MediaPlayer paramAnonymousMediaPlayer)
    {
      Log.d("###########TRACKING END VIDEO");
      paramAnonymousMediaPlayer = RichMediaActivity.this.mVideoData.completeEvents;
      int i = 0;
      for (;;)
      {
        if (i >= paramAnonymousMediaPlayer.size()) {
          if ((RichMediaActivity.this.mType == 1) && (RichMediaActivity.this.mAd.getType() == 3))
          {
            paramAnonymousMediaPlayer = new Intent(RichMediaActivity.this, RichMediaActivity.class);
            paramAnonymousMediaPlayer.putExtra("RICH_AD_DATA", RichMediaActivity.this.mAd);
            paramAnonymousMediaPlayer.putExtra("RICH_AD_TYPE", 2);
          }
        }
        try
        {
          RichMediaActivity.this.startActivity(paramAnonymousMediaPlayer);
          RichMediaActivity.setActivityAnimation(RichMediaActivity.this, RichMediaActivity.this.mEnterAnim, RichMediaActivity.this.mExitAnim);
          RichMediaActivity.this.mResult = true;
          RichMediaActivity.this.setResult(-1);
          RichMediaActivity.this.finish();
          return;
          Log.d("Track url:" + (String)paramAnonymousMediaPlayer.get(i));
          TrackEvent localTrackEvent = new TrackEvent();
          localTrackEvent.url = ((String)paramAnonymousMediaPlayer.get(i));
          localTrackEvent.timestamp = System.currentTimeMillis();
          TrackerService.requestTrack(localTrackEvent);
          i += 1;
        }
        catch (Exception paramAnonymousMediaPlayer)
        {
          for (;;)
          {
            Log.e("Cannot start Rich Ad activity:" + paramAnonymousMediaPlayer, paramAnonymousMediaPlayer);
          }
        }
      }
    }
  };
  MediaPlayer.OnErrorListener mOnVideoErrorListener = new MediaPlayer.OnErrorListener()
  {
    public boolean onError(MediaPlayer paramAnonymousMediaPlayer, int paramAnonymousInt1, int paramAnonymousInt2)
    {
      Log.w("Cannot play video/ Error: " + paramAnonymousInt1 + " Extra: " + paramAnonymousInt2);
      RichMediaActivity.this.finish();
      return false;
    }
  };
  MediaPlayer.OnInfoListener mOnVideoInfoListener = new MediaPlayer.OnInfoListener()
  {
    public boolean onInfo(MediaPlayer paramAnonymousMediaPlayer, int paramAnonymousInt1, int paramAnonymousInt2)
    {
      Log.i("Info: " + paramAnonymousInt1 + " Extra: " + paramAnonymousInt2);
      if (paramAnonymousInt1 == 703)
      {
        RichMediaActivity.this.mTimeTest = RichMediaActivity.this.mVideoView.getCurrentPosition();
        new Handler().postDelayed(RichMediaActivity.this.mCheckProgressTask, 5000L);
      }
      return false;
    }
  };
  MediaController.OnPauseListener mOnVideoPauseListener = new MediaController.OnPauseListener()
  {
    public void onVideoPause()
    {
      Log.d("###########TRACKING PAUSE VIDEO");
      Vector localVector = RichMediaActivity.this.mVideoData.pauseEvents;
      int i = 0;
      for (;;)
      {
        if (i >= localVector.size()) {
          return;
        }
        Log.d("Track url:" + (String)localVector.get(i));
        TrackEvent localTrackEvent = new TrackEvent();
        localTrackEvent.url = ((String)localVector.get(i));
        localTrackEvent.timestamp = System.currentTimeMillis();
        TrackerService.requestTrack(localTrackEvent);
        i += 1;
      }
    }
  };
  MediaPlayer.OnPreparedListener mOnVideoPreparedListener = new MediaPlayer.OnPreparedListener()
  {
    public void onPrepared(MediaPlayer paramAnonymousMediaPlayer)
    {
      Log.d("ADSDK", "RichMediaActivity onPrepared MediaPlayer");
      if (RichMediaActivity.this.mVideoTimeoutTimer != null)
      {
        RichMediaActivity.this.mVideoTimeoutTimer.cancel();
        RichMediaActivity.this.mVideoTimeoutTimer = null;
      }
      if (RichMediaActivity.this.mLoadingView != null) {
        RichMediaActivity.this.mLoadingView.setVisibility(8);
      }
      if (RichMediaActivity.this.mVideoData.showNavigationBars) {
        RichMediaActivity.this.mMediaController.setVisibility(0);
      }
      RichMediaActivity.this.mVideoView.requestFocus();
    }
  };
  MediaController.OnReplayListener mOnVideoReplayListener = new MediaController.OnReplayListener()
  {
    public void onVideoReplay()
    {
      Log.d("###########TRACKING REPLAY VIDEO");
      Vector localVector = RichMediaActivity.this.mVideoData.replayEvents;
      int i = 0;
      for (;;)
      {
        if (i >= localVector.size()) {
          return;
        }
        Log.d("Track url:" + (String)localVector.get(i));
        TrackEvent localTrackEvent = new TrackEvent();
        localTrackEvent.url = ((String)localVector.get(i));
        localTrackEvent.timestamp = System.currentTimeMillis();
        TrackerService.requestTrack(localTrackEvent);
        i += 1;
      }
    }
  };
  View.OnClickListener mOnVideoSkipListener = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      Log.v("###########TRACKING SKIP VIDEO");
      paramAnonymousView = RichMediaActivity.this.mVideoData.skipEvents;
      int i = 0;
      for (;;)
      {
        if (i >= paramAnonymousView.size())
        {
          RichMediaActivity.this.mResult = true;
          RichMediaActivity.this.setResult(-1);
          RichMediaActivity.this.finish();
          return;
        }
        Log.d("Track url:" + (String)paramAnonymousView.get(i));
        TrackEvent localTrackEvent = new TrackEvent();
        localTrackEvent.url = ((String)paramAnonymousView.get(i));
        localTrackEvent.timestamp = System.currentTimeMillis();
        TrackerService.requestTrack(localTrackEvent);
        i += 1;
      }
    }
  };
  SDKVideoView.OnStartListener mOnVideoStartListener = new SDKVideoView.OnStartListener()
  {
    public void onVideoStart()
    {
      Log.d("###########TRACKING START VIDEO");
      Vector localVector = RichMediaActivity.this.mVideoData.startEvents;
      int i = 0;
      for (;;)
      {
        if (i >= localVector.size()) {
          return;
        }
        Log.d("Track url:" + (String)localVector.get(i));
        TrackEvent localTrackEvent = new TrackEvent();
        localTrackEvent.url = ((String)localVector.get(i));
        localTrackEvent.timestamp = System.currentTimeMillis();
        TrackerService.requestTrack(localTrackEvent);
        i += 1;
      }
    }
  };
  SDKVideoView.OnTimeEventListener mOnVideoTimeEventListener = new SDKVideoView.OnTimeEventListener()
  {
    public void onTimeEvent(int paramAnonymousInt)
    {
      Log.d("###########TRACKING TIME VIDEO:" + paramAnonymousInt);
      Vector localVector = (Vector)RichMediaActivity.this.mVideoData.timeTrackingEvents.get(Integer.valueOf(paramAnonymousInt));
      if (localVector != null) {
        paramAnonymousInt = 0;
      }
      for (;;)
      {
        if (paramAnonymousInt >= localVector.size()) {
          return;
        }
        Log.d("Track url:" + (String)localVector.get(paramAnonymousInt));
        TrackEvent localTrackEvent = new TrackEvent();
        localTrackEvent.url = ((String)localVector.get(paramAnonymousInt));
        localTrackEvent.timestamp = System.currentTimeMillis();
        TrackerService.requestTrack(localTrackEvent);
        paramAnonymousInt += 1;
      }
    }
  };
  MediaController.OnUnpauseListener mOnVideoUnpauseListener = new MediaController.OnUnpauseListener()
  {
    public void onVideoUnpause()
    {
      Log.d("###########TRACKING UNPAUSE VIDEO");
      Vector localVector = RichMediaActivity.this.mVideoData.unpauseEvents;
      int i = 0;
      for (;;)
      {
        if (i >= localVector.size()) {
          return;
        }
        Log.d("Track url:" + (String)localVector.get(i));
        TrackEvent localTrackEvent = new TrackEvent();
        localTrackEvent.url = ((String)localVector.get(i));
        localTrackEvent.timestamp = System.currentTimeMillis();
        TrackerService.requestTrack(localTrackEvent);
        i += 1;
      }
    }
  };
  WebViewClient.OnPageLoadedListener mOnWebBrowserLoadedListener = new WebViewClient.OnPageLoadedListener()
  {
    public void onPageLoaded()
    {
      RichMediaActivity.this.mPageLoaded = true;
    }
  };
  private final View.OnClickListener mOverlayClickListener = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      Log.d("ADSDK", "RichMediaActivity mOverlayClickListener");
      if (RichMediaActivity.this.mMediaController != null) {
        RichMediaActivity.this.mMediaController.toggle();
      }
    }
  };
  private final SDKVideoView.OnTimeEventListener mOverlayShowListener = new SDKVideoView.OnTimeEventListener()
  {
    public void onTimeEvent(int paramAnonymousInt)
    {
      Log.d("ADSDK", "RichMediaActivity mOverlayShowListener show after:" + paramAnonymousInt);
      if (RichMediaActivity.this.mOverlayView != null)
      {
        RichMediaActivity.this.mOverlayView.setVisibility(0);
        RichMediaActivity.this.mOverlayView.requestLayout();
      }
    }
  };
  private WebFrame mOverlayView;
  private boolean mPageLoaded = false;
  private ResourceManager mResourceManager;
  private boolean mResult;
  private FrameLayout mRootLayout;
  private ImageView mSkipButton;
  protected int mTimeTest;
  private int mType;
  private VideoData mVideoData;
  private int mVideoHeight;
  private int mVideoLastPosition;
  private FrameLayout mVideoLayout;
  private Timer mVideoTimeoutTimer;
  private SDKVideoView mVideoView;
  private int mVideoWidth;
  private WebFrame mWebBrowserView;
  private int mWindowHeight;
  private int mWindowWidth;
  int marginArg = 8;
  DisplayMetrics metrics;
  int paddingArg = 5;
  int skipButtonSizeLand = 50;
  int skipButtonSizePort = 40;
  private Uri uri;
  
  private void initInterstitialView()
  {
    this.mInterstitialData = this.mAd.getInterstitial();
    this.mInterstitialAutocloseReset = false;
    setRequestedOrientation(this.mInterstitialData.orientation);
    FrameLayout localFrameLayout = new FrameLayout(this);
    this.mInterstitialView = new WebFrame(this, true, false, false);
    this.mInterstitialView.setBackgroundColor(0);
    this.mInterstitialView.setOnPageLoadedListener(this.mOnInterstitialLoadedListener);
    this.mInterstitialController = new InterstitialController(this, this.mInterstitialData);
    this.mInterstitialController.setBrowser(this.mInterstitialView);
    this.mInterstitialController.setBrowserView(this.mInterstitialView);
    this.mInterstitialController.setOnResetAutocloseListener(this.mOnResetAutocloseListener);
    localFrameLayout.addView(this.mInterstitialController, new FrameLayout.LayoutParams(-1, -1, 17));
    if (this.mInterstitialData.showNavigationBars) {
      this.mInterstitialController.show(0);
    }
    int i;
    FrameLayout.LayoutParams localLayoutParams;
    if (this.mInterstitialData.showSkipButton)
    {
      this.mSkipButton = new ImageView(this);
      this.mSkipButton.setAdjustViewBounds(false);
      i = (int)TypedValue.applyDimension(1, this.skipButtonSizeLand, getResources().getDisplayMetrics());
      i = (int)(Math.min(getResources().getDisplayMetrics().widthPixels, getResources().getDisplayMetrics().heightPixels) * 0.1D);
      localLayoutParams = new FrameLayout.LayoutParams(i, i, 53);
      if (this.mInterstitialData.orientation == 1)
      {
        i = (int)TypedValue.applyDimension(1, 8.0F, getResources().getDisplayMetrics());
        localLayoutParams.topMargin = i;
        localLayoutParams.rightMargin = i;
        if ((this.mInterstitialData.skipButtonImage == null) || (this.mInterstitialData.skipButtonImage.length() <= 0)) {
          break label510;
        }
        this.mSkipButton.setBackgroundDrawable(null);
        this.mResourceManager.fetchResource(this, this.mInterstitialData.skipButtonImage, -18);
        label331:
        this.mSkipButton.setOnClickListener(this.mOnInterstitialSkipListener);
        if (this.mInterstitialData.showSkipButtonAfter <= 0) {
          break label530;
        }
        this.mCanClose = false;
        this.mSkipButton.setVisibility(8);
        if (this.mInterstitialLoadingTimer == null)
        {
          InterstitialLoadingTimeoutTask localInterstitialLoadingTimeoutTask = new InterstitialLoadingTimeoutTask();
          this.mInterstitialLoadingTimer = new Timer();
          this.mInterstitialLoadingTimer.schedule(localInterstitialLoadingTimeoutTask, 10000L);
        }
        label406:
        localFrameLayout.addView(this.mSkipButton, localLayoutParams);
        label415:
        this.mInterstitialView.setOnClickListener(this.mInterstitialClickListener);
        this.mRootLayout.addView(localFrameLayout);
        switch (this.mInterstitialData.interstitialType)
        {
        }
      }
    }
    for (;;)
    {
      Log.i(this.mInterstitialView.getWebView().getSettings().getUserAgentString());
      return;
      i = (int)TypedValue.applyDimension(1, 10.0F, getResources().getDisplayMetrics());
      localLayoutParams.topMargin = i;
      localLayoutParams.rightMargin = i;
      break;
      label510:
      this.mSkipButton.setImageDrawable(this.mResourceManager.getResource(this, -18));
      break label331;
      label530:
      this.mCanClose = true;
      this.mSkipButton.setVisibility(0);
      break label406;
      this.mCanClose = false;
      break label415;
      this.mInterstitialView.setMarkup(this.mInterstitialData.interstitialMarkup);
      continue;
      this.mInterstitialView.loadUrl(this.mInterstitialData.interstitialUrl);
    }
  }
  
  private void initRootLayout()
  {
    this.mRootLayout = new FrameLayout(this);
    this.mRootLayout.setBackgroundColor(-16777216);
  }
  
  private void initVideoView()
  {
    this.mVideoData = this.mAd.getVideo();
    setRequestedOrientation(this.mVideoData.orientation);
    int i;
    label106:
    label322:
    Object localObject;
    if (this.mVideoData.orientation == 0)
    {
      if (this.mWindowWidth < this.mWindowHeight)
      {
        i = this.mWindowWidth;
        this.mWindowWidth = this.mWindowHeight;
        this.mWindowHeight = i;
      }
      this.mVideoWidth = this.mVideoData.width;
      this.mVideoHeight = this.mVideoData.height;
      if (this.mVideoWidth > 0) {
        break label1011;
      }
      this.mVideoWidth = this.mWindowWidth;
      this.mVideoHeight = this.mWindowHeight;
      Log.d("Video size (" + this.mVideoWidth + "," + this.mVideoHeight + ")");
      this.mVideoLayout = new FrameLayout(this);
      this.mVideoView = new SDKVideoView(this, this.mVideoWidth, this.mVideoHeight, this.mVideoData.display);
      this.mVideoLayout.addView(this.mVideoView, new FrameLayout.LayoutParams(-1, -1, 17));
      if (this.mVideoData.showHtmlOverlay)
      {
        this.mOverlayView = new WebFrame(this, false, false, false);
        this.mOverlayView.setEnableZoom(false);
        this.mOverlayView.setOnClickListener(this.mOverlayClickListener);
        this.mOverlayView.setBackgroundColor(0);
        if (this.mVideoData.showHtmlOverlayAfter > 0)
        {
          this.mOverlayView.setVisibility(8);
          this.mVideoView.setOnTimeEventListener(this.mVideoData.showHtmlOverlayAfter, this.mOverlayShowListener);
        }
        if (this.mVideoData.htmlOverlayType != 0) {
          break label1090;
        }
        this.mOverlayView.loadUrl(this.mVideoData.htmlOverlayUrl);
        localObject = new FrameLayout.LayoutParams(-1, -1);
        if ((!this.mVideoData.showBottomNavigationBar) || (!this.mVideoData.showTopNavigationBar)) {
          break label1107;
        }
        ((FrameLayout.LayoutParams)localObject).bottomMargin = ((int)(this.mWindowWidth * 0.11875D));
        ((FrameLayout.LayoutParams)localObject).topMargin = ((int)(this.mWindowWidth * 0.11875D));
        ((FrameLayout.LayoutParams)localObject).gravity = 17;
        label386:
        this.mVideoLayout.addView(this.mOverlayView, (ViewGroup.LayoutParams)localObject);
      }
      this.mMediaController = new MediaController(this, this.mVideoData);
      this.mVideoView.setMediaController(this.mMediaController);
      if (this.mVideoData.showNavigationBars) {
        this.mMediaController.toggle();
      }
      if (!this.mVideoData.pauseEvents.isEmpty()) {
        this.mMediaController.setOnPauseListener(this.mOnVideoPauseListener);
      }
      if (!this.mVideoData.unpauseEvents.isEmpty()) {
        this.mMediaController.setOnUnpauseListener(this.mOnVideoUnpauseListener);
      }
      if (!this.mVideoData.replayEvents.isEmpty()) {
        this.mMediaController.setOnReplayListener(this.mOnVideoReplayListener);
      }
      this.mVideoLayout.addView(this.mMediaController, new FrameLayout.LayoutParams(-1, -1, 7));
      if (!this.mVideoData.showSkipButton) {
        break label1258;
      }
      this.mSkipButton = new ImageView(this);
      this.mSkipButton.setAdjustViewBounds(false);
      i = (int)TypedValue.applyDimension(1, this.skipButtonSizeLand, getResources().getDisplayMetrics());
      i = (int)(Math.min(getResources().getDisplayMetrics().widthPixels, getResources().getDisplayMetrics().heightPixels) * 0.09D);
      localObject = new FrameLayout.LayoutParams(i, i, 53);
      if (this.mVideoData.orientation != 1) {
        break label1193;
      }
      i = (int)TypedValue.applyDimension(1, 8.0F, getResources().getDisplayMetrics());
      ((FrameLayout.LayoutParams)localObject).topMargin = i;
      ((FrameLayout.LayoutParams)localObject).rightMargin = i;
      label663:
      if ((this.mVideoData.skipButtonImage == null) || (this.mVideoData.skipButtonImage.length() <= 0)) {
        break label1222;
      }
      this.mResourceManager.fetchResource(this, this.mVideoData.skipButtonImage, -18);
      label703:
      this.mSkipButton.setOnClickListener(this.mOnVideoSkipListener);
      if (this.mVideoData.showSkipButtonAfter <= 0) {
        break label1242;
      }
      this.mCanClose = false;
      this.mSkipButton.setVisibility(8);
      label738:
      this.mVideoLayout.addView(this.mSkipButton, (ViewGroup.LayoutParams)localObject);
      label750:
      if (this.mVideoData.showSkipButtonAfter > 0) {
        this.mVideoView.setOnTimeEventListener(this.mVideoData.showSkipButtonAfter, this.mOnVideoCanCloseEventListener);
      }
      localObject = new FrameLayout.LayoutParams(-2, -2, 17);
      this.mLoadingView = new FrameLayout(this);
      TextView localTextView = new TextView(this);
      localTextView.setText(Const.LOADING);
      this.mLoadingView.addView(localTextView, (ViewGroup.LayoutParams)localObject);
      this.mVideoLayout.addView(this.mLoadingView, new FrameLayout.LayoutParams(-1, -1, 17));
      this.mVideoView.setOnPreparedListener(this.mOnVideoPreparedListener);
      this.mVideoView.setOnCompletionListener(this.mOnVideoCompletionListener);
      this.mVideoView.setOnErrorListener(this.mOnVideoErrorListener);
      this.mVideoView.setOnInfoListener(this.mOnVideoInfoListener);
      if (!this.mVideoData.startEvents.isEmpty()) {
        this.mVideoView.setOnStartListener(this.mOnVideoStartListener);
      }
      if (!this.mVideoData.timeTrackingEvents.isEmpty()) {
        localObject = this.mVideoData.timeTrackingEvents.keySet().iterator();
      }
    }
    for (;;)
    {
      if (!((Iterator)localObject).hasNext())
      {
        this.mVideoLastPosition = 0;
        localObject = this.mVideoData.videoUrl;
        this.mVideoView.setVideoPath((String)localObject);
        return;
        if (this.mWindowHeight >= this.mWindowWidth) {
          break;
        }
        i = this.mWindowHeight;
        this.mWindowHeight = this.mWindowWidth;
        this.mWindowWidth = i;
        break;
        label1011:
        localObject = getResources().getDisplayMetrics();
        this.mVideoWidth = ((int)TypedValue.applyDimension(1, this.mVideoWidth, (DisplayMetrics)localObject));
        this.mVideoHeight = ((int)TypedValue.applyDimension(1, this.mVideoHeight, (DisplayMetrics)localObject));
        if (this.mVideoWidth > this.mWindowWidth) {
          this.mVideoWidth = this.mWindowWidth;
        }
        if (this.mVideoHeight <= this.mWindowHeight) {
          break label106;
        }
        this.mVideoHeight = this.mWindowHeight;
        break label106;
        label1090:
        this.mOverlayView.setMarkup(this.mVideoData.htmlOverlayMarkup);
        break label322;
        label1107:
        if ((this.mVideoData.showBottomNavigationBar) && (!this.mVideoData.showTopNavigationBar))
        {
          ((FrameLayout.LayoutParams)localObject).bottomMargin = ((int)(this.mWindowWidth * 0.11875D));
          ((FrameLayout.LayoutParams)localObject).gravity = 48;
          break label386;
        }
        if ((!this.mVideoData.showTopNavigationBar) || (this.mVideoData.showBottomNavigationBar)) {
          break label386;
        }
        ((FrameLayout.LayoutParams)localObject).topMargin = ((int)(this.mWindowWidth * 0.11875D));
        ((FrameLayout.LayoutParams)localObject).gravity = 80;
        break label386;
        label1193:
        i = (int)TypedValue.applyDimension(1, 10.0F, getResources().getDisplayMetrics());
        ((FrameLayout.LayoutParams)localObject).topMargin = i;
        ((FrameLayout.LayoutParams)localObject).rightMargin = i;
        break label663;
        label1222:
        this.mSkipButton.setImageDrawable(this.mResourceManager.getResource(this, -18));
        break label703;
        label1242:
        this.mCanClose = true;
        this.mSkipButton.setVisibility(0);
        break label738;
        label1258:
        this.mCanClose = false;
        break label750;
      }
      i = ((Integer)((Iterator)localObject).next()).intValue();
      this.mVideoView.setOnTimeEventListener(i, this.mOnVideoTimeEventListener);
    }
  }
  
  private void initWebBrowserView(boolean paramBoolean)
  {
    this.mWebBrowserView = new WebFrame(this, true, true, paramBoolean);
    this.mWebBrowserView.setOnPageLoadedListener(this.mOnWebBrowserLoadedListener);
    this.mRootLayout.addView(this.mWebBrowserView);
  }
  
  public static void setActivityAnimation(Activity paramActivity, int paramInt1, int paramInt2)
  {
    try
    {
      paramActivity.overridePendingTransition(paramInt1, paramInt2);
      return;
    }
    catch (Exception paramActivity) {}
  }
  
  public void finish()
  {
    if (this.mAd != null)
    {
      Log.d("Finish Activity type:" + this.mType + " ad Type:" + this.mAd.getType());
      switch (this.mType)
      {
      }
    }
    for (;;)
    {
      super.finish();
      setActivityAnimation(this, this.mEnterAnim, this.mExitAnim);
      return;
      if (this.mAd.getType() == 5)
      {
        AdManager.closeRunningAd(this.mAd, this.mResult);
      }
      else if ((this.mAd.getType() == 3) && (!this.mResult))
      {
        AdManager.closeRunningAd(this.mAd, this.mResult);
        continue;
        if ((this.mAd.getType() == 6) || (this.mAd.getType() == 3) || (this.mAd.getType() == 4)) {
          AdManager.closeRunningAd(this.mAd, this.mResult);
        }
      }
    }
  }
  
  public int getDipSize(int paramInt)
  {
    return (int)TypedValue.applyDimension(1, paramInt, getResources().getDisplayMetrics());
  }
  
  public FrameLayout getRootLayout()
  {
    return this.mRootLayout;
  }
  
  public void goBack()
  {
    if (this.mCustomView != null)
    {
      Log.d("Closing custom view on back key pressed");
      onHideCustomView();
    }
    do
    {
      do
      {
        return;
        switch (this.mType)
        {
        default: 
          return;
        case 0: 
          if (!this.mWebBrowserView.canGoBack()) {
            break label119;
          }
          this.mWebBrowserView.goBack();
          return;
        }
      } while (!this.mCanClose);
      finish();
      return;
      if (this.mInterstitialView.canGoBack())
      {
        this.mInterstitialView.goBack();
        return;
      }
    } while (!this.mCanClose);
    this.mResult = true;
    setResult(-1);
    finish();
    return;
    label119:
    finish();
  }
  
  public void handleMessage(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    }
    do
    {
      return;
      switch (paramMessage.arg1)
      {
      default: 
        return;
      }
    } while (this.mSkipButton == null);
    if (this.mResourceManager.containsResource(-18))
    {
      this.mSkipButton.setImageDrawable(this.mResourceManager.getResource(this, -18));
      return;
    }
    this.mSkipButton.setImageDrawable(this.mResourceManager.getResource(this, -18));
  }
  
  public void navigate(String paramString)
  {
    switch (this.mType)
    {
    case 1: 
    default: 
      Intent localIntent = new Intent(this, RichMediaActivity.class);
      localIntent.setData(Uri.parse(paramString));
      startActivity(localIntent);
      return;
    case 0: 
      this.mWebBrowserView.loadUrl(paramString);
      return;
    }
    this.mInterstitialView.loadUrl(paramString);
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    Log.d("RichMediaActivity onConfigurationChanged");
  }
  
  public void onCreate(Bundle paramBundle)
  {
    Log.d("RichMediaActivity onCreate");
    super.onCreate(paramBundle);
    this.mResult = false;
    this.mPageLoaded = false;
    setResult(0);
    paramBundle = getWindow();
    paramBundle.setFlags(1024, 1024);
    requestWindowFeature(1);
    paramBundle.addFlags(512);
    Object localObject = getWindowManager().getDefaultDisplay();
    this.metrics = new DisplayMetrics();
    ((WindowManager)getSystemService("window")).getDefaultDisplay().getMetrics(this.metrics);
    this.mWindowWidth = ((Display)localObject).getWidth();
    this.mWindowHeight = ((Display)localObject).getHeight();
    paramBundle.clearFlags(512);
    Log.d("RichMediaActivity Window Size:(" + this.mWindowWidth + "," + this.mWindowHeight + ")");
    setVolumeControlStream(3);
    this.mType = -1;
    paramBundle = getIntent();
    localObject = paramBundle.getExtras();
    if ((localObject == null) || (((Bundle)localObject).getSerializable("RICH_AD_DATA") == null))
    {
      this.uri = paramBundle.getData();
      if (this.uri == null)
      {
        Log.d("url is null so do not load anything");
        finish();
        return;
      }
      this.mType = 0;
      this.mHandler = new ResourceHandler(this);
      this.mResourceManager = new ResourceManager(this, this.mHandler);
      initRootLayout();
      if (this.mType != 0) {
        break label325;
      }
      initWebBrowserView(true);
      this.mWebBrowserView.loadUrl(this.uri.toString());
      this.mEnterAnim = Util.getEnterAnimation(1);
      this.mExitAnim = Util.getExitAnimation(1);
    }
    for (;;)
    {
      setContentView(this.mRootLayout);
      Log.d("RichMediaActivity onCreate done");
      return;
      requestWindowFeature(1);
      break;
      label325:
      this.mAd = ((RichMediaAd)((Bundle)localObject).getSerializable("RICH_AD_DATA"));
      this.mEnterAnim = Util.getEnterAnimation(this.mAd.getAnimation());
      this.mExitAnim = Util.getExitAnimation(this.mAd.getAnimation());
      this.mCanClose = false;
      this.mType = ((Bundle)localObject).getInt("RICH_AD_TYPE", -1);
      if (this.mType == -1) {
        switch (this.mAd.getType())
        {
        }
      }
      for (;;)
      {
        switch (this.mType)
        {
        default: 
          break;
        case 1: 
          Log.v("Type video");
          initVideoView();
          break;
          this.mType = 1;
          continue;
          this.mType = 2;
        }
      }
      Log.v("Type interstitial");
      initInterstitialView();
    }
  }
  
  protected void onDestroy()
  {
    this.mMediaController = null;
    this.mResourceManager.releaseInstance();
    if (this.mVideoView != null) {
      this.mVideoView.destroy();
    }
    Log.d("RichMediaActivity onDestroy");
    super.onDestroy();
    Log.d("RichMediaActivity onDestroy done");
  }
  
  public void onHideCustomView()
  {
    Log.d("onHideCustomView Hidding Custom View");
    if (this.mCustomView != null)
    {
      this.mCustomView.setVisibility(8);
      this.mCustomView = null;
      if (this.mCustomVideoView == null) {}
    }
    try
    {
      Log.d("onHideCustomView stop video");
      this.mCustomVideoView.stopPlayback();
      this.mCustomVideoView = null;
      Log.d("onHideCustomView calling callback");
      this.mCustomViewCallback.onCustomViewHidden();
      this.mRootLayout.setVisibility(0);
      setContentView(this.mRootLayout);
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Log.d("Couldn't stop custom video view");
      }
    }
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
    {
      goBack();
      return true;
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
  
  protected void onPause()
  {
    Log.d("RichMediaActivity onPause");
    super.onPause();
    switch (this.mType)
    {
    }
    for (;;)
    {
      Log.d("RichMediaActivity onPause done");
      return;
      this.mVideoLastPosition = this.mVideoView.getCurrentPosition();
      this.mVideoView.stopPlayback();
      this.mRootLayout.removeView(this.mVideoLayout);
      if (this.mVideoTimeoutTimer != null)
      {
        this.mVideoTimeoutTimer.cancel();
        this.mVideoTimeoutTimer = null;
        continue;
        if (this.mInterstitialLoadingTimer != null)
        {
          this.mInterstitialLoadingTimer.cancel();
          this.mInterstitialLoadingTimer = null;
        }
        if (this.mInterstitialAutocloseTimer != null)
        {
          this.mInterstitialAutocloseTimer.cancel();
          this.mInterstitialAutocloseTimer = null;
        }
        if (this.mInterstitialCanCloseTimer != null)
        {
          this.mInterstitialCanCloseTimer.cancel();
          this.mInterstitialCanCloseTimer = null;
        }
      }
    }
  }
  
  protected void onResume()
  {
    Log.d("RichMediaActivity onResume");
    super.onResume();
    switch (this.mType)
    {
    }
    for (;;)
    {
      Log.d("RichMediaActivity onResume done");
      return;
      this.mRootLayout.addView(this.mVideoLayout);
      this.mVideoView.seekTo(this.mVideoLastPosition);
      this.mVideoView.start();
      if (this.mVideoTimeoutTimer == null)
      {
        VideoTimeoutTask localVideoTimeoutTask = new VideoTimeoutTask(this);
        this.mVideoTimeoutTimer = new Timer();
        this.mVideoTimeoutTimer.schedule(localVideoTimeoutTask, 1200000L);
        continue;
        switch (this.mInterstitialData.interstitialType)
        {
        default: 
          break;
        case 0: 
          if (!this.mPageLoaded) {
            this.mInterstitialView.loadUrl(this.mInterstitialData.interstitialUrl);
          }
          break;
        case 1: 
          if (!this.mPageLoaded) {
            this.mInterstitialView.setMarkup(this.mInterstitialData.interstitialMarkup);
          }
          break;
        }
      }
    }
  }
  
  public void onShowCustomView(View paramView, WebChromeClient.CustomViewCallback paramCustomViewCallback)
  {
    Log.d(" onShowCustomView");
    if ((paramView instanceof FrameLayout))
    {
      this.mCustomView = ((FrameLayout)paramView);
      this.mCustomViewCallback = paramCustomViewCallback;
      if ((this.mCustomView.getFocusedChild() instanceof VideoView))
      {
        Log.d(" onShowCustomView Starting Video View");
        this.mCustomVideoView = ((VideoView)this.mCustomView.getFocusedChild());
        this.mCustomVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
        {
          public void onCompletion(MediaPlayer paramAnonymousMediaPlayer)
          {
            Log.d(" onCompletion Video");
            RichMediaActivity.this.onHideCustomView();
          }
        });
        this.mCustomVideoView.start();
      }
      this.mRootLayout.setVisibility(8);
      this.mCustomView.setVisibility(0);
      setContentView(this.mCustomView);
    }
  }
  
  public void playVideo()
  {
    Log.d("RichMediaActivity play video:" + this.mType);
    switch (this.mType)
    {
    }
    do
    {
      do
      {
        return;
      } while (this.mMediaController == null);
      this.mMediaController.replay();
      return;
    } while (this.mAd.getType() != 4);
    Log.d("RichMediaActivity launch video");
    Intent localIntent = new Intent(this, RichMediaActivity.class);
    localIntent.putExtra("RICH_AD_DATA", this.mAd);
    localIntent.putExtra("RICH_AD_TYPE", 1);
    try
    {
      startActivity(localIntent);
      setActivityAnimation(this, this.mEnterAnim, this.mExitAnim);
      this.mResult = true;
      setResult(-1);
      return;
    }
    catch (Exception localException)
    {
      Log.e("ADSDK", "Cannot start Rich Ad activity:" + localException, localException);
    }
  }
  
  public void replayVideo()
  {
    if (this.mMediaController != null) {
      this.mMediaController.replay();
    }
  }
  
  class CanSkipTask
    extends TimerTask
  {
    private final RichMediaActivity mActivity;
    
    public CanSkipTask(RichMediaActivity paramRichMediaActivity)
    {
      this.mActivity = paramRichMediaActivity;
    }
    
    public void run()
    {
      Log.v("###########TRACKING CAN CLOSE INTERSTITIAL");
      this.mActivity.mCanClose = true;
      if (this.mActivity.mSkipButton != null) {
        this.mActivity.runOnUiThread(new Runnable()
        {
          public void run()
          {
            RichMediaActivity.this.mSkipButton.setVisibility(0);
          }
        });
      }
    }
  }
  
  class InterstitialAutocloseTask
    extends TimerTask
  {
    private final Activity mActivity;
    
    public InterstitialAutocloseTask(Activity paramActivity)
    {
      this.mActivity = paramActivity;
    }
    
    public void run()
    {
      Log.v("###########TRACKING INTERSTITIAL AUTOCLOSE");
      RichMediaActivity.this.mResult = true;
      this.mActivity.runOnUiThread(new Runnable()
      {
        public void run()
        {
          RichMediaActivity.this.setResult(-1);
          RichMediaActivity.this.finish();
        }
      });
    }
  }
  
  class InterstitialLoadingTimeoutTask
    extends TimerTask
  {
    InterstitialLoadingTimeoutTask() {}
    
    public void run()
    {
      Log.v("ADSDK", "###########TRACKING INTERSTITIAL LOADING TIMEOUT");
      RichMediaActivity.this.mCanClose = true;
      RichMediaActivity.this.mInterstitialController.pageLoaded();
    }
  }
  
  static class ResourceHandler
    extends Handler
  {
    WeakReference<RichMediaActivity> richMediaActivity;
    
    public ResourceHandler(RichMediaActivity paramRichMediaActivity)
    {
      this.richMediaActivity = new WeakReference(paramRichMediaActivity);
    }
    
    public void handleMessage(Message paramMessage)
    {
      RichMediaActivity localRichMediaActivity = (RichMediaActivity)this.richMediaActivity.get();
      if (localRichMediaActivity != null) {
        localRichMediaActivity.handleMessage(paramMessage);
      }
    }
  }
  
  class VideoTimeoutTask
    extends TimerTask
  {
    private final Activity mActivity;
    
    public VideoTimeoutTask(Activity paramActivity)
    {
      this.mActivity = paramActivity;
    }
    
    public void run()
    {
      Log.v("###########TRACKING VIDEO TIMEOUT");
      this.mActivity.runOnUiThread(new Runnable()
      {
        public void run()
        {
          RichMediaActivity.VideoTimeoutTask.this.mActivity.finish();
        }
      });
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\adsdk\sdk\video\RichMediaActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */