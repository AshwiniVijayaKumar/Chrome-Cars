package com.google.android.gms.common.data;

import java.util.ArrayList;

public final class FreezableUtils
{
  public static <T, E extends Freezable<T>> ArrayList<T> freeze(ArrayList<E> paramArrayList)
  {
    ArrayList localArrayList = new ArrayList(paramArrayList.size());
    int j = paramArrayList.size();
    int i = 0;
    while (i < j)
    {
      localArrayList.add(((Freezable)paramArrayList.get(i)).freeze());
      i += 1;
    }
    return localArrayList;
  }
  
  public static <T, E extends Freezable<T>> ArrayList<T> freeze(E[] paramArrayOfE)
  {
    ArrayList localArrayList = new ArrayList(paramArrayOfE.length);
    int i = 0;
    while (i < paramArrayOfE.length)
    {
      localArrayList.add(paramArrayOfE[i].freeze());
      i += 1;
    }
    return localArrayList;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\common\data\FreezableUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */