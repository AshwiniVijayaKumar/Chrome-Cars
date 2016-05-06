package org.vudroid.core.multitouch;

import android.view.MotionEvent;
import org.vudroid.core.models.ZoomModel;

public class MultiTouchZoomImpl
  implements MultiTouchZoom
{
  private float lastZoomDistance;
  private boolean resetLastPointAfterZoom;
  private final ZoomModel zoomModel;
  
  public MultiTouchZoomImpl(ZoomModel paramZoomModel)
  {
    this.zoomModel = paramZoomModel;
  }
  
  private float getZoomDistance(MotionEvent paramMotionEvent)
  {
    return (float)Math.sqrt(Math.pow(paramMotionEvent.getX(0) - paramMotionEvent.getX(1), 2.0D) + Math.pow(paramMotionEvent.getY(0) - paramMotionEvent.getY(1), 2.0D));
  }
  
  public boolean isResetLastPointAfterZoom()
  {
    return this.resetLastPointAfterZoom;
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if ((paramMotionEvent.getAction() & 0x5) == 5)
    {
      this.lastZoomDistance = getZoomDistance(paramMotionEvent);
      return true;
    }
    if ((paramMotionEvent.getAction() & 0x6) == 6)
    {
      this.lastZoomDistance = 0.0F;
      this.zoomModel.commit();
      this.resetLastPointAfterZoom = true;
      return true;
    }
    if ((paramMotionEvent.getAction() == 2) && (this.lastZoomDistance != 0.0F))
    {
      float f = getZoomDistance(paramMotionEvent);
      this.zoomModel.setZoom(this.zoomModel.getZoom() * f / this.lastZoomDistance);
      this.lastZoomDistance = f;
      return true;
    }
    return false;
  }
  
  public void setResetLastPointAfterZoom(boolean paramBoolean)
  {
    this.resetLastPointAfterZoom = paramBoolean;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\vudroid\core\multitouch\MultiTouchZoomImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */