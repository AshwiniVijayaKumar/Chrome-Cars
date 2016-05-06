package com.ooyala.android.util;

import java.lang.ref.WeakReference;

public final class WeakReferencePassThroughEquals<T>
  extends WeakReference<T>
{
  static
  {
    if (!WeakReferencePassThroughEquals.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  public WeakReferencePassThroughEquals(T paramT)
  {
    super(paramT);
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = false;
    Object localObject;
    if ((paramObject instanceof WeakReferencePassThroughEquals))
    {
      localObject = get();
      paramObject = ((WeakReferencePassThroughEquals)paramObject).get();
      if (localObject != null) {
        bool = localObject.equals(paramObject);
      }
    }
    else
    {
      return bool;
    }
    if (paramObject != null) {
      return paramObject.equals(localObject);
    }
    assert (localObject == null);
    assert (paramObject == null);
    return true;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\util\WeakReferencePassThroughEquals.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */