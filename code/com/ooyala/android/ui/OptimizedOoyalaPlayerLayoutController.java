package com.ooyala.android.ui;

import android.widget.FrameLayout.LayoutParams;
import com.ooyala.android.OoyalaPlayer;
import com.ooyala.android.OoyalaPlayerLayout;

public class OptimizedOoyalaPlayerLayoutController
  extends AbstractOoyalaPlayerLayoutController
{
  private boolean _fullscreen = false;
  private FrameLayout.LayoutParams _fullscreenLP = new FrameLayout.LayoutParams(-1, -1, 119);
  private FrameLayout.LayoutParams _inlineLP = null;
  
  public OptimizedOoyalaPlayerLayoutController(OoyalaPlayerLayout paramOoyalaPlayerLayout, OoyalaPlayer paramOoyalaPlayer)
  {
    this(paramOoyalaPlayerLayout, paramOoyalaPlayer, AbstractOoyalaPlayerLayoutController.DefaultControlStyle.AUTO);
  }
  
  public OptimizedOoyalaPlayerLayoutController(OoyalaPlayerLayout paramOoyalaPlayerLayout, OoyalaPlayer paramOoyalaPlayer, AbstractOoyalaPlayerLayoutController.DefaultControlStyle paramDefaultControlStyle)
  {
    super(paramOoyalaPlayerLayout, paramOoyalaPlayer, paramDefaultControlStyle);
    extraInit(paramDefaultControlStyle);
    this._layout.setBackgroundColor(-16777216);
  }
  
  private void extraInit(AbstractOoyalaPlayerLayoutController.DefaultControlStyle paramDefaultControlStyle)
  {
    if (paramDefaultControlStyle == AbstractOoyalaPlayerLayoutController.DefaultControlStyle.AUTO)
    {
      this._fullscreenControls = this._inlineControls;
      this._fullscreenOverlay = this._inlineOverlay;
    }
    this._inlineLP = ((FrameLayout.LayoutParams)this._layout.getLayoutParams());
    this._fullscreenLayout = this._layout;
  }
  
  protected void doFullscreenChange(boolean paramBoolean)
  {
    if ((isFullscreen()) && (!paramBoolean))
    {
      this._fullscreen = paramBoolean;
      this._layout.setLayoutParams(this._inlineLP);
    }
    while ((isFullscreen()) || (!paramBoolean)) {
      return;
    }
    this._fullscreen = paramBoolean;
    this._layout.setLayoutParams(this._fullscreenLP);
    this._layout.bringToFront();
  }
  
  public boolean isFullscreen()
  {
    return this._fullscreen;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\ui\OptimizedOoyalaPlayerLayoutController.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */