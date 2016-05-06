package com.google.android.gms.internal;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class kb<M extends ka<M>, T>
{
  protected final Class<T> aaf;
  protected final boolean aag;
  protected final int tag;
  protected final int type;
  
  private kb(int paramInt1, Class<T> paramClass, int paramInt2, boolean paramBoolean)
  {
    this.type = paramInt1;
    this.aaf = paramClass;
    this.tag = paramInt2;
    this.aag = paramBoolean;
  }
  
  public static <M extends ka<M>, T extends ke> kb<M, T> a(int paramInt1, Class<T> paramClass, int paramInt2)
  {
    return new kb(paramInt1, paramClass, paramInt2, false);
  }
  
  protected void a(kg paramkg, List<Object> paramList)
  {
    paramList.add(o(jy.n(paramkg.aai)));
  }
  
  protected boolean cI(int paramInt)
  {
    return paramInt == this.tag;
  }
  
  final T g(List<kg> paramList)
  {
    int j = 0;
    if (paramList == null)
    {
      paramList = null;
      return paramList;
    }
    Object localObject2;
    if (this.aag)
    {
      localObject2 = new ArrayList();
      i = 0;
      while (i < paramList.size())
      {
        localObject1 = (kg)paramList.get(i);
        if ((cI(((kg)localObject1).tag)) && (((kg)localObject1).aai.length != 0)) {
          a((kg)localObject1, (List)localObject2);
        }
        i += 1;
      }
      int k = ((List)localObject2).size();
      if (k == 0) {
        return null;
      }
      localObject1 = this.aaf.cast(Array.newInstance(this.aaf.getComponentType(), k));
      i = j;
      for (;;)
      {
        paramList = (List<kg>)localObject1;
        if (i >= k) {
          break;
        }
        Array.set(localObject1, i, ((List)localObject2).get(i));
        i += 1;
      }
    }
    int i = paramList.size() - 1;
    Object localObject1 = null;
    if ((localObject1 == null) && (i >= 0))
    {
      localObject2 = (kg)paramList.get(i);
      if ((!cI(((kg)localObject2).tag)) || (((kg)localObject2).aai.length == 0)) {
        break label247;
      }
      localObject1 = localObject2;
    }
    label247:
    for (;;)
    {
      i -= 1;
      break;
      if (localObject1 == null) {
        return null;
      }
      return (T)this.aaf.cast(o(jy.n(((kg)localObject1).aai)));
    }
  }
  
  protected Object o(jy paramjy)
  {
    Class localClass;
    if (this.aag) {
      localClass = this.aaf.getComponentType();
    }
    for (;;)
    {
      try
      {
        switch (this.type)
        {
        case 10: 
          throw new IllegalArgumentException("Unknown type " + this.type);
        }
      }
      catch (InstantiationException paramjy)
      {
        throw new IllegalArgumentException("Error creating instance of class " + localClass, paramjy);
        localClass = this.aaf;
        continue;
        ke localke = (ke)localClass.newInstance();
        paramjy.a(localke, kh.cK(this.tag));
        return localke;
        localke = (ke)localClass.newInstance();
        paramjy.a(localke);
        return localke;
      }
      catch (IllegalAccessException paramjy)
      {
        throw new IllegalArgumentException("Error creating instance of class " + localClass, paramjy);
      }
      catch (IOException paramjy)
      {
        throw new IllegalArgumentException("Error reading extension field", paramjy);
      }
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\kb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */