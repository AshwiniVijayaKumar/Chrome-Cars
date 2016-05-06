package org.apache.commons.lang;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.lang.exception.CloneFailedException;
import org.apache.commons.lang.reflect.MethodUtils;

public class ObjectUtils
{
  public static final Null NULL = new Null();
  
  public static StringBuffer appendIdentityToString(StringBuffer paramStringBuffer, Object paramObject)
  {
    if (paramObject == null) {
      return null;
    }
    StringBuffer localStringBuffer = paramStringBuffer;
    if (paramStringBuffer == null) {
      localStringBuffer = new StringBuffer();
    }
    return localStringBuffer.append(paramObject.getClass().getName()).append('@').append(Integer.toHexString(System.identityHashCode(paramObject)));
  }
  
  public static Object clone(Object paramObject)
  {
    Object localObject1 = null;
    if ((paramObject instanceof Cloneable))
    {
      if (!paramObject.getClass().isArray()) {
        break label86;
      }
      localObject1 = paramObject.getClass().getComponentType();
      if (!((Class)localObject1).isPrimitive()) {
        localObject1 = ((Object[])paramObject).clone();
      }
    }
    else
    {
      return localObject1;
    }
    int i = Array.getLength(paramObject);
    Object localObject2 = Array.newInstance((Class)localObject1, i);
    for (;;)
    {
      int j = i - 1;
      localObject1 = localObject2;
      if (i <= 0) {
        break;
      }
      Array.set(localObject2, j, Array.get(paramObject, j));
      i = j;
    }
    try
    {
      label86:
      localObject1 = MethodUtils.invokeMethod(paramObject, "clone", null);
      return localObject1;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      throw new CloneFailedException("Cloneable type " + paramObject.getClass().getName() + " has no clone method", localNoSuchMethodException);
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw new CloneFailedException("Cannot clone Cloneable type " + paramObject.getClass().getName(), localIllegalAccessException);
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      throw new CloneFailedException("Exception cloning Cloneable type " + paramObject.getClass().getName(), localInvocationTargetException.getTargetException());
    }
  }
  
  public static Object cloneIfPossible(Object paramObject)
  {
    Object localObject = clone(paramObject);
    if (localObject == null) {
      return paramObject;
    }
    return localObject;
  }
  
  public static int compare(Comparable paramComparable1, Comparable paramComparable2)
  {
    return compare(paramComparable1, paramComparable2, false);
  }
  
  public static int compare(Comparable paramComparable1, Comparable paramComparable2, boolean paramBoolean)
  {
    int i = 1;
    int j = -1;
    if (paramComparable1 == paramComparable2) {
      i = 0;
    }
    do
    {
      return i;
      if (paramComparable1 != null) {
        break;
      }
    } while (paramBoolean);
    return -1;
    if (paramComparable2 == null)
    {
      if (paramBoolean) {}
      for (i = j;; i = 1) {
        return i;
      }
    }
    return paramComparable1.compareTo(paramComparable2);
  }
  
  public static Object defaultIfNull(Object paramObject1, Object paramObject2)
  {
    if (paramObject1 != null) {
      return paramObject1;
    }
    return paramObject2;
  }
  
  public static boolean equals(Object paramObject1, Object paramObject2)
  {
    if (paramObject1 == paramObject2) {
      return true;
    }
    if ((paramObject1 == null) || (paramObject2 == null)) {
      return false;
    }
    return paramObject1.equals(paramObject2);
  }
  
  public static int hashCode(Object paramObject)
  {
    if (paramObject == null) {
      return 0;
    }
    return paramObject.hashCode();
  }
  
  public static String identityToString(Object paramObject)
  {
    if (paramObject == null) {
      return null;
    }
    StringBuffer localStringBuffer = new StringBuffer();
    identityToString(localStringBuffer, paramObject);
    return localStringBuffer.toString();
  }
  
  public static void identityToString(StringBuffer paramStringBuffer, Object paramObject)
  {
    if (paramObject == null) {
      throw new NullPointerException("Cannot get the toString of a null identity");
    }
    paramStringBuffer.append(paramObject.getClass().getName()).append('@').append(Integer.toHexString(System.identityHashCode(paramObject)));
  }
  
  public static Object max(Comparable paramComparable1, Comparable paramComparable2)
  {
    if (compare(paramComparable1, paramComparable2, false) >= 0) {
      return paramComparable1;
    }
    return paramComparable2;
  }
  
  public static Object min(Comparable paramComparable1, Comparable paramComparable2)
  {
    if (compare(paramComparable1, paramComparable2, true) <= 0) {
      return paramComparable1;
    }
    return paramComparable2;
  }
  
  public static boolean notEqual(Object paramObject1, Object paramObject2)
  {
    return !equals(paramObject1, paramObject2);
  }
  
  public static String toString(Object paramObject)
  {
    if (paramObject == null) {
      return "";
    }
    return paramObject.toString();
  }
  
  public static String toString(Object paramObject, String paramString)
  {
    if (paramObject == null) {
      return paramString;
    }
    return paramObject.toString();
  }
  
  public static class Null
    implements Serializable
  {
    private static final long serialVersionUID = 7092611880189329093L;
    
    private Object readResolve()
    {
      return ObjectUtils.NULL;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\commons\lang\ObjectUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */