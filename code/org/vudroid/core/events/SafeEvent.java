package org.vudroid.core.events;

import java.lang.reflect.Method;

public abstract class SafeEvent<T>
  implements Event<T>
{
  private final Class<?> listenerType = getListenerType();
  
  private Class<?> getListenerType()
  {
    Method[] arrayOfMethod = getClass().getMethods();
    int j = arrayOfMethod.length;
    int i = 0;
    while (i < j)
    {
      Method localMethod = arrayOfMethod[i];
      if (("dispatchSafely".equals(localMethod.getName())) && (!localMethod.isSynthetic())) {
        return localMethod.getParameterTypes()[0];
      }
      i += 1;
    }
    throw new RuntimeException("Couldn't find dispatchSafely method");
  }
  
  public final void dispatchOn(Object paramObject)
  {
    if (this.listenerType.isAssignableFrom(paramObject.getClass())) {
      dispatchSafely(paramObject);
    }
  }
  
  public abstract void dispatchSafely(T paramT);
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\vudroid\core\events\SafeEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */