package com.google.android.gms.tagmanager;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class da<K, V>
  implements k<K, V>
{
  private final Map<K, V> Xs = new HashMap();
  private final int Xt;
  private final l.a<K, V> Xu;
  private int Xv;
  
  da(int paramInt, l.a<K, V> parama)
  {
    this.Xt = paramInt;
    this.Xu = parama;
  }
  
  public void e(K paramK, V paramV)
  {
    if ((paramK == null) || (paramV == null)) {
      try
      {
        throw new NullPointerException("key == null || value == null");
      }
      finally {}
    }
    this.Xv += this.Xu.sizeOf(paramK, paramV);
    if (this.Xv > this.Xt)
    {
      Iterator localIterator = this.Xs.entrySet().iterator();
      do
      {
        if (!localIterator.hasNext()) {
          break;
        }
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        this.Xv -= this.Xu.sizeOf(localEntry.getKey(), localEntry.getValue());
        localIterator.remove();
      } while (this.Xv > this.Xt);
    }
    this.Xs.put(paramK, paramV);
  }
  
  public V get(K paramK)
  {
    try
    {
      paramK = this.Xs.get(paramK);
      return paramK;
    }
    finally
    {
      paramK = finally;
      throw paramK;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\tagmanager\da.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */