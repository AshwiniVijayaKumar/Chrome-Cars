package com.ooyala.android.ui;

import android.annotation.SuppressLint;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.os.Build.VERSION;
import android.text.format.DateUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import com.ooyala.android.LocalizationSupport;
import com.ooyala.android.OoyalaPlayer;
import com.ooyala.android.OoyalaPlayer.SeekStyle;
import com.ooyala.android.OoyalaPlayer.State;
import com.ooyala.android.OoyalaPlayerLayout;
import com.ooyala.android.configuration.ReadonlyOptionsInterface;
import com.ooyala.android.item.Video;
import com.ooyala.android.util.DebugMode;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

public class DefaultOoyalaPlayerInlineControls
  extends AbstractDefaultOoyalaPlayerControls
  implements View.OnClickListener, SeekBar.OnSeekBarChangeListener, Observer
{
  private static final String TAG = "DefaultOoyalaPlayerInlineControls";
  private LinearLayout _bottomBar = null;
  private AbstractDefaultOoyalaPlayerControls.ClosedCaptionsButton _closedCaptions = null;
  private TextView _currTime = null;
  private TextView _currTimeLive = null;
  private TextView _duration = null;
  private AbstractDefaultOoyalaPlayerControls.FullscreenButton _fullscreen = null;
  private boolean _fullscreenButtonShowing = true;
  private TextView _liveDVRIndicator = null;
  private TextView _liveIndicator = null;
  private LinearLayout _liveSliderModeWrapper = null;
  private LinearLayout _liveWrapper = null;
  private AbstractDefaultOoyalaPlayerControls.PlayPauseButton _playPause = null;
  private CuePointsSeekBar _seek = null;
  private CuePointsSeekBar _seekLive = null;
  private LinearLayout _seekWrapper = null;
  private boolean _seeking;
  private ProgressBar _spinner = null;
  
  public DefaultOoyalaPlayerInlineControls(OoyalaPlayer paramOoyalaPlayer, OoyalaPlayerLayout paramOoyalaPlayerLayout)
  {
    setParentLayout(paramOoyalaPlayerLayout);
    setOoyalaPlayer(paramOoyalaPlayer);
    setupControls();
  }
  
  public int bottomBarOffset()
  {
    int j = Images.dpToPixels(this._baseLayout.getContext(), 10);
    int i = j;
    if (isShowing())
    {
      i = j;
      if (this._bottomBar != null) {
        i = j + this._bottomBar.getHeight();
      }
    }
    return i;
  }
  
  public void onClick(View paramView)
  {
    if (paramView == this._playPause) {
      if (this._player.isPlaying())
      {
        this._player.pause();
        show();
      }
    }
    do
    {
      return;
      this._player.play();
      break;
      if ((paramView == this._fullscreen) && (this._isPlayerReady))
      {
        paramView = this._player;
        if (!this._player.isFullscreen()) {}
        for (boolean bool = true;; bool = false)
        {
          paramView.setFullscreen(bool);
          updateButtonStates();
          hide();
          return;
        }
      }
    } while (paramView != this._closedCaptions);
    this._layout.getLayoutController().showClosedCaptionsMenu();
  }
  
  public void onProgressChanged(SeekBar paramSeekBar, int paramInt, boolean paramBoolean)
  {
    if (this._seeking) {
      this._currTime.setText(DateUtils.formatElapsedTime((int)(paramSeekBar.getProgress() / 100.0F * this._player.getDuration() / 1000.0F)));
    }
    if ((paramBoolean) && (this._player.getSeekStyle() == OoyalaPlayer.SeekStyle.ENHANCED))
    {
      this._player.seekToPercent(paramInt);
      update(null, null);
    }
  }
  
  public void onStartTrackingTouch(SeekBar paramSeekBar)
  {
    this._seeking = true;
  }
  
  public void onStopTrackingTouch(SeekBar paramSeekBar)
  {
    DebugMode.logV("DefaultOoyalaPlayerInlineControls", "onStopTrackingTouch(): percent=" + paramSeekBar.getProgress());
    this._player.seekToPercent(paramSeekBar.getProgress());
    update(null, null);
    this._seeking = false;
  }
  
  public void setFullscreenButtonShowing(boolean paramBoolean)
  {
    this._fullscreenButtonShowing = paramBoolean;
  }
  
  protected void setupControls()
  {
    if (this._layout == null) {
      return;
    }
    this._baseLayout = new FrameLayout(this._layout.getContext());
    this._baseLayout.setBackgroundColor(0);
    this._bottomBar = new LinearLayout(this._baseLayout.getContext());
    this._bottomBar.setOrientation(0);
    this._bottomBar.setBackgroundDrawable(Images.gradientBackground(GradientDrawable.Orientation.BOTTOM_TOP));
    this._playPause = new AbstractDefaultOoyalaPlayerControls.PlayPauseButton(this, this._bottomBar.getContext());
    this._playPause.setPlaying(this._player.isPlaying());
    Object localObject = new ViewGroup.LayoutParams(Images.dpToPixels(this._baseLayout.getContext(), 40), Images.dpToPixels(this._baseLayout.getContext(), 35));
    this._playPause.setLayoutParams((ViewGroup.LayoutParams)localObject);
    this._playPause.setOnClickListener(this);
    this._seekWrapper = new LinearLayout(this._bottomBar.getContext());
    this._seekWrapper.setOrientation(0);
    this._currTime = new TextView(this._seekWrapper.getContext());
    this._currTime.setText("00:00:00");
    localObject = new LinearLayout.LayoutParams(-2, -2);
    ((LinearLayout.LayoutParams)localObject).gravity = 19;
    this._currTime.setLayoutParams((ViewGroup.LayoutParams)localObject);
    this._seek = new CuePointsSeekBar(this._seekWrapper.getContext());
    localObject = new LinearLayout.LayoutParams(0, -2, 1.0F);
    ((LinearLayout.LayoutParams)localObject).gravity = 17;
    ((LinearLayout.LayoutParams)localObject).leftMargin = Images.dpToPixels(this._baseLayout.getContext(), 5);
    ((LinearLayout.LayoutParams)localObject).rightMargin = Images.dpToPixels(this._baseLayout.getContext(), 5);
    this._seek.setLayoutParams((ViewGroup.LayoutParams)localObject);
    this._seek.setOnSeekBarChangeListener(this);
    this._duration = new TextView(this._seekWrapper.getContext());
    this._duration.setText("00:00:00");
    localObject = new LinearLayout.LayoutParams(-2, -2);
    ((LinearLayout.LayoutParams)localObject).gravity = 21;
    this._duration.setLayoutParams((ViewGroup.LayoutParams)localObject);
    this._seekWrapper.addView(this._currTime);
    this._seekWrapper.addView(this._seek);
    this._seekWrapper.addView(this._duration);
    localObject = new LinearLayout.LayoutParams(0, -2, 1.0F);
    ((LinearLayout.LayoutParams)localObject).gravity = 17;
    ((LinearLayout.LayoutParams)localObject).leftMargin = Images.dpToPixels(this._baseLayout.getContext(), 5);
    ((LinearLayout.LayoutParams)localObject).rightMargin = Images.dpToPixels(this._baseLayout.getContext(), 5);
    this._seekWrapper.setLayoutParams((ViewGroup.LayoutParams)localObject);
    this._liveWrapper = new LinearLayout(this._bottomBar.getContext());
    this._liveWrapper.setVisibility(8);
    this._liveWrapper.setOrientation(0);
    this._liveIndicator = new TextView(this._liveWrapper.getContext());
    this._liveIndicator.setText(LocalizationSupport.localizedStringFor("LIVE"));
    this._liveIndicator.setGravity(1);
    localObject = new LinearLayout.LayoutParams(-1, -2);
    ((LinearLayout.LayoutParams)localObject).gravity = 17;
    this._liveIndicator.setLayoutParams((ViewGroup.LayoutParams)localObject);
    this._liveWrapper.addView(this._liveIndicator);
    localObject = new LinearLayout.LayoutParams(0, -2, 1.0F);
    ((LinearLayout.LayoutParams)localObject).gravity = 17;
    ((LinearLayout.LayoutParams)localObject).leftMargin = Images.dpToPixels(this._baseLayout.getContext(), 5);
    ((LinearLayout.LayoutParams)localObject).rightMargin = Images.dpToPixels(this._baseLayout.getContext(), 5);
    this._liveWrapper.setLayoutParams((ViewGroup.LayoutParams)localObject);
    this._liveSliderModeWrapper = new LinearLayout(this._bottomBar.getContext());
    this._liveSliderModeWrapper.setOrientation(0);
    this._currTimeLive = new TextView(this._liveSliderModeWrapper.getContext());
    this._currTimeLive.setText("00:00:00");
    localObject = new LinearLayout.LayoutParams(-2, -2);
    ((LinearLayout.LayoutParams)localObject).gravity = 19;
    this._currTimeLive.setLayoutParams((ViewGroup.LayoutParams)localObject);
    this._seekLive = new CuePointsSeekBar(this._liveSliderModeWrapper.getContext());
    localObject = new LinearLayout.LayoutParams(0, -2, 1.0F);
    ((LinearLayout.LayoutParams)localObject).gravity = 17;
    ((LinearLayout.LayoutParams)localObject).leftMargin = Images.dpToPixels(this._baseLayout.getContext(), 5);
    ((LinearLayout.LayoutParams)localObject).rightMargin = Images.dpToPixels(this._baseLayout.getContext(), 5);
    this._seekLive.setLayoutParams((ViewGroup.LayoutParams)localObject);
    this._seekLive.setOnSeekBarChangeListener(this);
    this._liveDVRIndicator = new TextView(this._liveSliderModeWrapper.getContext());
    this._liveDVRIndicator.setText(LocalizationSupport.localizedStringFor("LIVE"));
    localObject = new LinearLayout.LayoutParams(-2, -2);
    ((LinearLayout.LayoutParams)localObject).gravity = 21;
    this._liveDVRIndicator.setLayoutParams((ViewGroup.LayoutParams)localObject);
    this._liveSliderModeWrapper.addView(this._currTimeLive);
    this._liveSliderModeWrapper.addView(this._seekLive);
    this._liveSliderModeWrapper.addView(this._liveDVRIndicator);
    localObject = new LinearLayout.LayoutParams(0, -2, 1.0F);
    ((LinearLayout.LayoutParams)localObject).gravity = 17;
    ((LinearLayout.LayoutParams)localObject).leftMargin = Images.dpToPixels(this._baseLayout.getContext(), 5);
    ((LinearLayout.LayoutParams)localObject).rightMargin = Images.dpToPixels(this._baseLayout.getContext(), 5);
    this._liveSliderModeWrapper.setLayoutParams((ViewGroup.LayoutParams)localObject);
    this._fullscreen = new AbstractDefaultOoyalaPlayerControls.FullscreenButton(this, this._bottomBar.getContext());
    this._fullscreen.setFullscreen(this._player.isFullscreen());
    localObject = new LinearLayout.LayoutParams(Images.dpToPixels(this._baseLayout.getContext(), 35), Images.dpToPixels(this._baseLayout.getContext(), 35));
    ((LinearLayout.LayoutParams)localObject).leftMargin = 2;
    ((LinearLayout.LayoutParams)localObject).rightMargin = 2;
    this._fullscreen.setLayoutParams((ViewGroup.LayoutParams)localObject);
    this._fullscreen.setOnClickListener(this);
    this._closedCaptions = new AbstractDefaultOoyalaPlayerControls.ClosedCaptionsButton(this, this._bottomBar.getContext());
    localObject = new ViewGroup.LayoutParams(Images.dpToPixels(this._baseLayout.getContext(), 40), Images.dpToPixels(this._baseLayout.getContext(), 35));
    this._closedCaptions.setLayoutParams((ViewGroup.LayoutParams)localObject);
    this._closedCaptions.setOnClickListener(this);
    this._bottomBar.addView(this._playPause);
    this._bottomBar.addView(this._seekWrapper);
    this._bottomBar.addView(this._liveWrapper);
    this._bottomBar.addView(this._liveSliderModeWrapper);
    this._bottomBar.addView(this._closedCaptions);
    this._bottomBar.addView(this._fullscreen);
    localObject = new FrameLayout.LayoutParams(-1, -2, 81);
    this._baseLayout.addView(this._bottomBar, (ViewGroup.LayoutParams)localObject);
    this._spinner = new ProgressBar(this._layout.getContext());
    this._spinner.setVisibility(4);
    localObject = new FrameLayout.LayoutParams(-2, -2, 17);
    this._layout.addView(this._spinner, (ViewGroup.LayoutParams)localObject);
    localObject = new FrameLayout.LayoutParams(-1, -1);
    this._layout.addView(this._baseLayout, (ViewGroup.LayoutParams)localObject);
    hide();
  }
  
  public void update(Observable paramObservable, Object paramObject)
  {
    if ((this._seek != null) && (!this._seeking))
    {
      this._seek.setProgress(this._player.getPlayheadPercentage());
      this._seek.setSecondaryProgress(this._player.getBufferPercentage());
      this._seek.setCuePoints(this._player.getCuePointsInPercentage());
    }
    if ((this._seekLive != null) && (!this._seeking))
    {
      this._seekLive.setProgress(this._player.getPlayheadPercentage());
      this._seekLive.setSecondaryProgress(this._player.getBufferPercentage());
      this._seekLive.setCuePoints(this._player.getCuePointsInPercentage());
    }
    if ((this._duration != null) && (this._currTime != null))
    {
      this._duration.setText(DateUtils.formatElapsedTime(this._player.getDuration() / 1000));
      this._currTime.setText(DateUtils.formatElapsedTime(this._player.getPlayheadTime() / 1000));
    }
    if (this._currTimeLive != null)
    {
      String str = DateUtils.formatElapsedTime(Math.abs(this._player.getPlayheadTime()) / 1000);
      paramObservable = str;
      if (this._player.getPlayheadTime() < 0) {
        paramObservable = "-" + str;
      }
      this._currTimeLive.setText(paramObservable);
    }
    if (paramObject == "adStarted")
    {
      this._isPlayerReady = true;
      if (!this._player.options().getShowAdsControls()) {
        hide();
      }
    }
    else
    {
      if ((paramObject == "adCompleted") || (paramObject == "adSkipped") || (paramObject == "adError"))
      {
        this._isPlayerReady = false;
        updateButtonStates();
      }
      if (paramObject == "stateChanged")
      {
        paramObservable = this._player.getState();
        updateButtonStates();
        if ((paramObservable != OoyalaPlayer.State.LOADING) || (!this._isVisible)) {
          break label445;
        }
        this._spinner.setVisibility(0);
      }
    }
    for (;;)
    {
      if ((paramObservable == OoyalaPlayer.State.READY) || (paramObservable == OoyalaPlayer.State.PLAYING) || (paramObservable == OoyalaPlayer.State.PAUSED)) {
        this._isPlayerReady = true;
      }
      if ((paramObservable == OoyalaPlayer.State.SUSPENDED) || (paramObservable == OoyalaPlayer.State.ERROR))
      {
        this._isPlayerReady = false;
        hide();
      }
      if ((!isShowing()) && (paramObservable != OoyalaPlayer.State.INIT) && (paramObservable != OoyalaPlayer.State.LOADING) && (paramObservable != OoyalaPlayer.State.ERROR) && (paramObservable != OoyalaPlayer.State.SUSPENDED) && (!this._player.isFullscreen())) {
        show();
      }
      return;
      updateButtonStates();
      break;
      label445:
      this._spinner.setVisibility(4);
    }
  }
  
  @SuppressLint({"NewApi"})
  protected void updateButtonStates()
  {
    float f2 = 0.4F;
    int j = 8;
    if (this._playPause != null) {
      this._playPause.setPlaying(this._player.isPlaying());
    }
    Object localObject;
    label163:
    float f1;
    if (this._fullscreen != null)
    {
      this._fullscreen.setFullscreen(this._player.isFullscreen());
      localObject = this._fullscreen;
      if (this._fullscreenButtonShowing)
      {
        i = 0;
        ((AbstractDefaultOoyalaPlayerControls.FullscreenButton)localObject).setVisibility(i);
      }
    }
    else
    {
      if ((this._seekWrapper != null) && (this._player.getCurrentItem() != null))
      {
        if (!this._player.getCurrentItem().isLive()) {
          break label358;
        }
        this._seekWrapper.setVisibility(8);
      }
      if ((this._liveWrapper != null) && (this._player.getCurrentItem() != null))
      {
        localObject = this._liveWrapper;
        if ((!this._player.getCurrentItem().isLive()) || (this._player.options().getShowLiveControls())) {
          break label401;
        }
        i = 0;
        ((LinearLayout)localObject).setVisibility(i);
        if (Build.VERSION.SDK_INT >= 11)
        {
          localObject = this._liveWrapper;
          if (!this._player.isShowingAd()) {
            break label407;
          }
          f1 = 0.4F;
          label197:
          ((LinearLayout)localObject).setAlpha(f1);
        }
      }
      if ((this._liveSliderModeWrapper != null) && (this._player.getCurrentItem() != null))
      {
        localObject = this._liveSliderModeWrapper;
        if ((!this._player.getCurrentItem().isLive()) || (!this._player.options().getShowLiveControls())) {
          break label412;
        }
        i = 0;
        label256:
        ((LinearLayout)localObject).setVisibility(i);
        if (Build.VERSION.SDK_INT >= 11)
        {
          localObject = this._liveSliderModeWrapper;
          if (!this._player.isShowingAd()) {
            break label418;
          }
          f1 = f2;
          label288:
          ((LinearLayout)localObject).setAlpha(f1);
        }
      }
      if ((this._closedCaptions == null) || (this._player.getCurrentItem() == null) || (this._player.isShowingAd())) {
        break label428;
      }
      localObject = this._closedCaptions;
      if (!this._player.getAvailableClosedCaptionsLanguages().isEmpty()) {
        break label423;
      }
    }
    label358:
    label401:
    label407:
    label412:
    label418:
    label423:
    for (int i = j;; i = 0)
    {
      ((AbstractDefaultOoyalaPlayerControls.ClosedCaptionsButton)localObject).setVisibility(i);
      return;
      i = 8;
      break;
      this._seekWrapper.setVisibility(0);
      localObject = this._seekWrapper;
      if (!this._player.isAdPlaying()) {}
      for (boolean bool = true;; bool = false)
      {
        ((LinearLayout)localObject).setEnabled(bool);
        break;
      }
      i = 8;
      break label163;
      f1 = 1.0F;
      break label197;
      i = 8;
      break label256;
      f1 = 1.0F;
      break label288;
    }
    label428:
    this._closedCaptions.setVisibility(8);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\ui\DefaultOoyalaPlayerInlineControls.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */