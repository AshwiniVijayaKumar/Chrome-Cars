package com.ooyala.android;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.ooyala.android.ui.LayoutController;

public class OoyalaPlayerLayout
  extends FrameLayout
{
  private LayoutController _controller = null;
  protected FrameLayout _playerFrame = null;
  
  public OoyalaPlayerLayout(Context paramContext)
  {
    super(paramContext);
    setupPlayerFrame();
  }
  
  public OoyalaPlayerLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    setupPlayerFrame();
  }
  
  public OoyalaPlayerLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    setupPlayerFrame();
  }
  
  private void setupPlayerFrame()
  {
    this._playerFrame = new FrameLayout(getContext());
    FrameLayout.LayoutParams localLayoutParams = new FrameLayout.LayoutParams(-1, -1);
    addView(this._playerFrame, localLayoutParams);
  }
  
  public LayoutController getLayoutController()
  {
    return this._controller;
  }
  
  public FrameLayout getPlayerFrame()
  {
    return this._playerFrame;
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (this._controller == null) {
      return false;
    }
    return this._controller.onTouchEvent(paramMotionEvent, this);
  }
  
  public void setLayoutController(LayoutController paramLayoutController)
  {
    this._controller = paramLayoutController;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\OoyalaPlayerLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */