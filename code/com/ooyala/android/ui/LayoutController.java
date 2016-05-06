package com.ooyala.android.ui;

import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import com.ooyala.android.OoyalaPlayerLayout;

public abstract interface LayoutController
{
  public abstract void addVideoView(View paramView);
  
  public abstract FrameLayout getLayout();
  
  public abstract boolean isFullscreen();
  
  public abstract boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent);
  
  public abstract boolean onTouchEvent(MotionEvent paramMotionEvent, OoyalaPlayerLayout paramOoyalaPlayerLayout);
  
  public abstract void removeVideoView();
  
  public abstract void reshowTVRating();
  
  public abstract void setFullscreen(boolean paramBoolean);
  
  public abstract void setFullscreenButtonShowing(boolean paramBoolean);
  
  public abstract void showClosedCaptionsMenu();
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\ui\LayoutController.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */