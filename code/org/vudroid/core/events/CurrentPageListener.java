package org.vudroid.core.events;

public abstract interface CurrentPageListener
{
  public abstract void currentPageChanged(int paramInt);
  
  public static class CurrentPageChangedEvent
    extends SafeEvent<CurrentPageListener>
  {
    private final int pageIndex;
    
    public CurrentPageChangedEvent(int paramInt)
    {
      this.pageIndex = paramInt;
    }
    
    public void dispatchSafely(CurrentPageListener paramCurrentPageListener)
    {
      paramCurrentPageListener.currentPageChanged(this.pageIndex);
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\vudroid\core\events\CurrentPageListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */