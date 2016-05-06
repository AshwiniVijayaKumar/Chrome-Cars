package com.google.zxing.client.android.common;

import android.os.Build.VERSION;
import android.util.Log;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public abstract class PlatformSupportManager<T>
{
  private static final String TAG = PlatformSupportManager.class.getSimpleName();
  private final T defaultImplementation;
  private final SortedMap<Integer, String> implementations;
  private final Class<T> managedInterface;
  
  protected PlatformSupportManager(Class<T> paramClass, T paramT)
  {
    if (!paramClass.isInterface()) {
      throw new IllegalArgumentException();
    }
    if (!paramClass.isInstance(paramT)) {
      throw new IllegalArgumentException();
    }
    this.managedInterface = paramClass;
    this.defaultImplementation = paramT;
    this.implementations = new TreeMap(Collections.reverseOrder());
  }
  
  protected void addImplementationClass(int paramInt, String paramString)
  {
    this.implementations.put(Integer.valueOf(paramInt), paramString);
  }
  
  public T build()
  {
    Iterator localIterator = this.implementations.keySet().iterator();
    while (localIterator.hasNext())
    {
      Object localObject1 = (Integer)localIterator.next();
      if (Build.VERSION.SDK_INT >= ((Integer)localObject1).intValue())
      {
        Object localObject2 = (String)this.implementations.get(localObject1);
        try
        {
          localObject2 = Class.forName((String)localObject2).asSubclass(this.managedInterface);
          Log.i(TAG, "Using implementation " + localObject2 + " of " + this.managedInterface + " for SDK " + localObject1);
          localObject1 = ((Class)localObject2).getConstructor(new Class[0]).newInstance(new Object[0]);
          return (T)localObject1;
        }
        catch (ClassNotFoundException localClassNotFoundException)
        {
          Log.w(TAG, localClassNotFoundException);
        }
        catch (IllegalAccessException localIllegalAccessException)
        {
          Log.w(TAG, localIllegalAccessException);
        }
        catch (InstantiationException localInstantiationException)
        {
          Log.w(TAG, localInstantiationException);
        }
        catch (NoSuchMethodException localNoSuchMethodException)
        {
          Log.w(TAG, localNoSuchMethodException);
        }
        catch (InvocationTargetException localInvocationTargetException)
        {
          Log.w(TAG, localInvocationTargetException);
        }
      }
    }
    Log.i(TAG, "Using default implementation " + this.defaultImplementation.getClass() + " of " + this.managedInterface);
    return (T)this.defaultImplementation;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\common\PlatformSupportManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */