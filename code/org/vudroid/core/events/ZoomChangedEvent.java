package org.vudroid.core.events;

public class ZoomChangedEvent
  extends SafeEvent<ZoomListener>
{
  private final float newZoom;
  private final float oldZoom;
  
  public ZoomChangedEvent(float paramFloat1, float paramFloat2)
  {
    this.newZoom = paramFloat1;
    this.oldZoom = paramFloat2;
  }
  
  public void dispatchSafely(ZoomListener paramZoomListener)
  {
    paramZoomListener.zoomChanged(this.newZoom, this.oldZoom);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\vudroid\core\events\ZoomChangedEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */