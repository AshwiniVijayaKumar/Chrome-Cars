package org.vudroid.core.events;

public abstract interface ZoomListener
{
  public abstract void commitZoom();
  
  public abstract void zoomChanged(float paramFloat1, float paramFloat2);
  
  public static class CommitZoomEvent
    extends SafeEvent<ZoomListener>
  {
    public void dispatchSafely(ZoomListener paramZoomListener)
    {
      paramZoomListener.commitZoom();
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\vudroid\core\events\ZoomListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */