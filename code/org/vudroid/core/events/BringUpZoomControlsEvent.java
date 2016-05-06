package org.vudroid.core.events;

public class BringUpZoomControlsEvent
  extends SafeEvent<BringUpZoomControlsListener>
{
  public void dispatchSafely(BringUpZoomControlsListener paramBringUpZoomControlsListener)
  {
    paramBringUpZoomControlsListener.toggleZoomControls();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\vudroid\core\events\BringUpZoomControlsEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */