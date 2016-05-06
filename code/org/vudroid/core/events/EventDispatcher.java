package org.vudroid.core.events;

import java.util.ArrayList;
import java.util.Iterator;

public class EventDispatcher
{
  private final ArrayList<Object> listeners = new ArrayList();
  
  public void addEventListener(Object paramObject)
  {
    this.listeners.add(paramObject);
  }
  
  public void dispatch(Event paramEvent)
  {
    Iterator localIterator = this.listeners.iterator();
    while (localIterator.hasNext()) {
      paramEvent.dispatchOn(localIterator.next());
    }
  }
  
  public void removeEventListener(Object paramObject)
  {
    this.listeners.remove(paramObject);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\vudroid\core\events\EventDispatcher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */