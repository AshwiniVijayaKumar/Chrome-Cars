package com.ooyala.android.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import com.ooyala.android.OoyalaPlayer;
import com.ooyala.android.OoyalaPlayerLayout;
import java.util.Timer;
import java.util.TimerTask;

public abstract class AbstractDefaultOoyalaPlayerControls
  implements OoyalaPlayerControls
{
  protected static final int BACKGROUND_COLOR = 0;
  protected static final int HIDE_AFTER_MILLIS = 5000;
  protected static final int MARGIN_SIZE_DP = 5;
  protected static final int PREFERRED_BUTTON_HEIGHT_DP = 35;
  protected static final int PREFERRED_BUTTON_WIDTH_DP = 40;
  protected static final int SOFT_WHITE_COLOR = Color.argb(245, 240, 240, 240);
  protected FrameLayout _baseLayout = null;
  protected final Handler _hideHandler = new Handler(new Handler.Callback()
  {
    public boolean handleMessage(Message paramAnonymousMessage)
    {
      if (AbstractDefaultOoyalaPlayerControls.this._player.isPlaying()) {
        AbstractDefaultOoyalaPlayerControls.this.hide();
      }
      return false;
    }
  });
  protected Timer _hideTimer = null;
  protected boolean _isPlayerReady = false;
  protected boolean _isVisible = true;
  protected OoyalaPlayerLayout _layout = null;
  protected OoyalaPlayer _player = null;
  protected AbstractOoyalaPlayerLayoutController _playerLayoutController = null;
  
  private void updateClosedCaptionsBottomMargin()
  {
    if ((this._playerLayoutController != null) && (this._isPlayerReady)) {
      this._playerLayoutController.setClosedCaptionsBottomMargin(bottomBarOffset());
    }
  }
  
  public int bottomBarOffset()
  {
    return 0;
  }
  
  public void hide()
  {
    if (this._hideTimer != null)
    {
      this._hideTimer.cancel();
      this._hideTimer = null;
    }
    this._baseLayout.setVisibility(8);
    updateClosedCaptionsBottomMargin();
  }
  
  public boolean isShowing()
  {
    return (this._baseLayout != null) && (this._baseLayout.getVisibility() == 0);
  }
  
  public void setOoyalaPlayer(OoyalaPlayer paramOoyalaPlayer)
  {
    this._player = paramOoyalaPlayer;
  }
  
  public void setParentLayout(OoyalaPlayerLayout paramOoyalaPlayerLayout)
  {
    this._layout = paramOoyalaPlayerLayout;
    this._playerLayoutController = null;
    if ((this._layout != null) && ((this._layout.getLayoutController() instanceof AbstractOoyalaPlayerLayoutController)))
    {
      this._playerLayoutController = ((AbstractOoyalaPlayerLayoutController)this._layout.getLayoutController());
      return;
    }
    this._playerLayoutController = null;
  }
  
  public void setVisible(boolean paramBoolean)
  {
    this._isVisible = paramBoolean;
    if (!paramBoolean) {
      hide();
    }
  }
  
  protected abstract void setupControls();
  
  public void show()
  {
    if ((!this._isVisible) || (this._player.showingAdWithHiddenControlls())) {
      return;
    }
    if (this._hideTimer != null)
    {
      this._hideTimer.cancel();
      this._hideTimer = null;
    }
    this._baseLayout.setVisibility(0);
    this._baseLayout.bringToFront();
    updateButtonStates();
    this._hideTimer = new Timer();
    this._hideTimer.schedule(new HideTimerTask(), 5000L);
    updateClosedCaptionsBottomMargin();
  }
  
  public int topBarOffset()
  {
    return 0;
  }
  
  protected abstract void updateButtonStates();
  
  protected class ClosedCaptionsButton
    extends AbstractDefaultOoyalaPlayerControls.TouchButton
  {
    public ClosedCaptionsButton(Context paramContext)
    {
      super(paramContext);
    }
    
    protected void onDraw(Canvas paramCanvas)
    {
      if (paramCanvas == null) {
        return;
      }
      Images.drawImage(6, getContext(), paramCanvas, 0, AbstractDefaultOoyalaPlayerControls.SOFT_WHITE_COLOR, getWidth(), getHeight(), 5, this._touching);
    }
  }
  
  protected class FullscreenButton
    extends AbstractDefaultOoyalaPlayerControls.TouchButton
  {
    private boolean _fullscreen = false;
    
    public FullscreenButton(Context paramContext)
    {
      super(paramContext);
    }
    
    protected void onDraw(Canvas paramCanvas)
    {
      if (paramCanvas == null) {
        return;
      }
      if (this._fullscreen)
      {
        Images.drawImage(3, getContext(), paramCanvas, 0, AbstractDefaultOoyalaPlayerControls.SOFT_WHITE_COLOR, getWidth(), getHeight(), 5, this._touching);
        return;
      }
      Images.drawImage(2, getContext(), paramCanvas, 0, AbstractDefaultOoyalaPlayerControls.SOFT_WHITE_COLOR, getWidth(), getHeight(), 5, this._touching);
    }
    
    public void setFullscreen(boolean paramBoolean)
    {
      this._fullscreen = paramBoolean;
      invalidate();
    }
  }
  
  protected class HideTimerTask
    extends TimerTask
  {
    protected HideTimerTask() {}
    
    public void run()
    {
      AbstractDefaultOoyalaPlayerControls.this._hideHandler.sendEmptyMessage(0);
    }
  }
  
  protected class NextButton
    extends AbstractDefaultOoyalaPlayerControls.TouchButton
  {
    public NextButton(Context paramContext)
    {
      super(paramContext);
    }
    
    protected void onDraw(Canvas paramCanvas)
    {
      if (paramCanvas == null) {
        return;
      }
      Images.drawImage(4, getContext(), paramCanvas, 0, AbstractDefaultOoyalaPlayerControls.SOFT_WHITE_COLOR, getWidth(), getHeight(), 5, this._touching);
    }
  }
  
  protected class PlayPauseButton
    extends AbstractDefaultOoyalaPlayerControls.TouchButton
  {
    private boolean _playing = false;
    
    public PlayPauseButton(Context paramContext)
    {
      super(paramContext);
    }
    
    protected void onDraw(Canvas paramCanvas)
    {
      if (paramCanvas == null) {
        return;
      }
      if (this._playing)
      {
        Images.drawImage(1, getContext(), paramCanvas, 0, AbstractDefaultOoyalaPlayerControls.SOFT_WHITE_COLOR, getWidth(), getHeight(), 5, this._touching);
        return;
      }
      Images.drawImage(0, getContext(), paramCanvas, 0, AbstractDefaultOoyalaPlayerControls.SOFT_WHITE_COLOR, getWidth(), getHeight(), 5, this._touching);
    }
    
    public void setPlaying(boolean paramBoolean)
    {
      this._playing = paramBoolean;
      invalidate();
    }
  }
  
  protected class PreviousButton
    extends AbstractDefaultOoyalaPlayerControls.TouchButton
  {
    public PreviousButton(Context paramContext)
    {
      super(paramContext);
    }
    
    protected void onDraw(Canvas paramCanvas)
    {
      if (paramCanvas == null) {
        return;
      }
      Images.drawImage(5, getContext(), paramCanvas, 0, AbstractDefaultOoyalaPlayerControls.SOFT_WHITE_COLOR, getWidth(), getHeight(), 5, this._touching);
    }
  }
  
  protected class TouchButton
    extends ImageButton
  {
    protected boolean _touching = false;
    
    public TouchButton(Context paramContext)
    {
      super();
      setBackgroundDrawable(null);
    }
    
    public boolean onTouchEvent(MotionEvent paramMotionEvent)
    {
      if (paramMotionEvent.getAction() == 0)
      {
        this._touching = true;
        invalidate();
      }
      for (;;)
      {
        return super.onTouchEvent(paramMotionEvent);
        if (paramMotionEvent.getAction() == 1)
        {
          this._touching = false;
          invalidate();
        }
      }
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\ui\AbstractDefaultOoyalaPlayerControls.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */