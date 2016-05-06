package org.vudroid.core.multitouch;

import android.view.MotionEvent;

public abstract interface MultiTouchZoom
{
  public abstract boolean isResetLastPointAfterZoom();
  
  public abstract boolean onTouchEvent(MotionEvent paramMotionEvent);
  
  public abstract void setResetLastPointAfterZoom(boolean paramBoolean);
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\vudroid\core\multitouch\MultiTouchZoom.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */