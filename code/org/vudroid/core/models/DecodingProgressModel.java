package org.vudroid.core.models;

import org.vudroid.core.events.DecodingProgressListener.DecodingProgressEvent;
import org.vudroid.core.events.EventDispatcher;

public class DecodingProgressModel
  extends EventDispatcher
{
  private int currentlyDecoding;
  
  private void dispatchChanged()
  {
    dispatch(new DecodingProgressListener.DecodingProgressEvent(this.currentlyDecoding));
  }
  
  public void decrease()
  {
    this.currentlyDecoding -= 1;
    dispatchChanged();
  }
  
  public void increase()
  {
    this.currentlyDecoding += 1;
    dispatchChanged();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\vudroid\core\models\DecodingProgressModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */