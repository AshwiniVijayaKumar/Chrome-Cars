package org.vudroid.core.models;

import org.vudroid.core.events.CurrentPageListener.CurrentPageChangedEvent;
import org.vudroid.core.events.EventDispatcher;

public class CurrentPageModel
  extends EventDispatcher
{
  private int currentPageIndex;
  
  public void setCurrentPageIndex(int paramInt)
  {
    if (this.currentPageIndex != paramInt)
    {
      this.currentPageIndex = paramInt;
      dispatch(new CurrentPageListener.CurrentPageChangedEvent(paramInt));
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\vudroid\core\models\CurrentPageModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */