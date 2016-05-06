package org.vudroid.core.models;

import org.vudroid.core.events.BringUpZoomControlsEvent;
import org.vudroid.core.events.EventDispatcher;
import org.vudroid.core.events.ZoomChangedEvent;
import org.vudroid.core.events.ZoomListener.CommitZoomEvent;

public class ZoomModel
  extends EventDispatcher
{
  private static final float INCREMENT_DELTA = 0.05F;
  private boolean horizontalScrollEnabled;
  private boolean isCommited;
  private float zoom = 1.0F;
  
  public boolean canDecrement()
  {
    return this.zoom > 1.0F;
  }
  
  public void commit()
  {
    if (!this.isCommited)
    {
      this.isCommited = true;
      dispatch(new ZoomListener.CommitZoomEvent());
    }
  }
  
  public void decreaseZoom()
  {
    setZoom(getZoom() - 0.05F);
  }
  
  public float getZoom()
  {
    return this.zoom;
  }
  
  public void increaseZoom()
  {
    setZoom(getZoom() + 0.05F);
  }
  
  public boolean isHorizontalScrollEnabled()
  {
    return this.horizontalScrollEnabled;
  }
  
  public void setHorizontalScrollEnabled(boolean paramBoolean)
  {
    this.horizontalScrollEnabled = paramBoolean;
  }
  
  public void setZoom(float paramFloat)
  {
    paramFloat = Math.max(paramFloat, 1.0F);
    if (this.zoom != paramFloat)
    {
      float f = this.zoom;
      this.zoom = paramFloat;
      this.isCommited = false;
      dispatch(new ZoomChangedEvent(paramFloat, f));
    }
  }
  
  public void toggleZoomControls()
  {
    dispatch(new BringUpZoomControlsEvent());
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\vudroid\core\models\ZoomModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */