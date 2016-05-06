package nl.siegmann.epublib.browsersupport;

import java.util.ArrayList;
import java.util.List;
import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Resource;

public class NavigationHistory
  implements NavigationEventListener
{
  private static final long DEFAULT_HISTORY_WAIT_TIME = 1000L;
  public static final int DEFAULT_MAX_HISTORY_SIZE = 1000;
  private int currentPos = -1;
  private int currentSize = 0;
  private long historyWaitTime = 1000L;
  private long lastUpdateTime = 0L;
  private List<Location> locations = new ArrayList();
  private int maxHistorySize = 1000;
  private Navigator navigator;
  
  public NavigationHistory(Navigator paramNavigator)
  {
    this.navigator = paramNavigator;
    paramNavigator.addNavigationEventListener(this);
    initBook(paramNavigator.getBook());
  }
  
  private void checkHistorySize()
  {
    while (this.locations.size() > this.maxHistorySize)
    {
      this.locations.remove(0);
      this.currentSize -= 1;
      this.currentPos -= 1;
    }
  }
  
  private String getLocationHref(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= this.locations.size())) {
      return null;
    }
    return ((Location)this.locations.get(this.currentPos)).getHref();
  }
  
  public void addLocation(String paramString)
  {
    addLocation(new Location(paramString));
  }
  
  public void addLocation(Location paramLocation)
  {
    if ((!this.locations.isEmpty()) && (paramLocation.getHref().equals(((Location)this.locations.get(this.currentPos)).getHref()))) {
      return;
    }
    this.currentPos += 1;
    if (this.currentPos != this.currentSize) {
      this.locations.set(this.currentPos, paramLocation);
    }
    for (;;)
    {
      this.currentSize = (this.currentPos + 1);
      return;
      this.locations.add(paramLocation);
      checkHistorySize();
    }
  }
  
  public void addLocation(Resource paramResource)
  {
    if (paramResource == null) {
      return;
    }
    addLocation(paramResource.getHref());
  }
  
  public String getCurrentHref()
  {
    if ((this.currentPos < 0) || (this.currentPos >= this.locations.size())) {
      return null;
    }
    return ((Location)this.locations.get(this.currentPos)).getHref();
  }
  
  public int getCurrentPos()
  {
    return this.currentPos;
  }
  
  public int getCurrentSize()
  {
    return this.currentSize;
  }
  
  public long getHistoryWaitTime()
  {
    return this.historyWaitTime;
  }
  
  public int getMaxHistorySize()
  {
    return this.maxHistorySize;
  }
  
  public void initBook(Book paramBook)
  {
    if (paramBook == null) {}
    do
    {
      return;
      this.locations = new ArrayList();
      this.currentPos = -1;
      this.currentSize = 0;
    } while (this.navigator.getCurrentResource() == null);
    addLocation(this.navigator.getCurrentResource().getHref());
  }
  
  public boolean move(int paramInt)
  {
    if ((this.currentPos + paramInt < 0) || (this.currentPos + paramInt >= this.currentSize)) {
      return false;
    }
    this.currentPos += paramInt;
    this.navigator.gotoResource(getLocationHref(this.currentPos), this);
    return true;
  }
  
  public void navigationPerformed(NavigationEvent paramNavigationEvent)
  {
    if (this == paramNavigationEvent.getSource()) {}
    while (paramNavigationEvent.getCurrentResource() == null) {
      return;
    }
    if (System.currentTimeMillis() - this.lastUpdateTime > this.historyWaitTime)
    {
      addLocation(paramNavigationEvent.getOldResource());
      addLocation(paramNavigationEvent.getCurrentResource().getHref());
    }
    this.lastUpdateTime = System.currentTimeMillis();
  }
  
  public void setHistoryWaitTime(long paramLong)
  {
    this.historyWaitTime = paramLong;
  }
  
  public void setMaxHistorySize(int paramInt)
  {
    this.maxHistorySize = paramInt;
  }
  
  private static class Location
  {
    private String href;
    
    public Location(String paramString)
    {
      this.href = paramString;
    }
    
    public String getHref()
    {
      return this.href;
    }
    
    public void setHref(String paramString)
    {
      this.href = paramString;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\nl\siegmann\epublib\browsersupport\NavigationHistory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */