package com.nostra13.universalimageloader.cache.memory;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

public class FuzzyKeyMemoryCache<K, V>
  implements MemoryCacheAware<K, V>
{
  private MemoryCacheAware<K, V> cache;
  private Comparator<K> keyComparator;
  
  public FuzzyKeyMemoryCache(MemoryCacheAware<K, V> paramMemoryCacheAware, Comparator<K> paramComparator)
  {
    this.cache = paramMemoryCacheAware;
    this.keyComparator = paramComparator;
  }
  
  public void clear()
  {
    this.cache.clear();
  }
  
  public V get(K paramK)
  {
    return (V)this.cache.get(paramK);
  }
  
  public Collection<K> keys()
  {
    return this.cache.keys();
  }
  
  public boolean put(K paramK, V paramV)
  {
    Object localObject1 = null;
    synchronized (this.cache)
    {
      Iterator localIterator = this.cache.keys().iterator();
      while (localIterator.hasNext())
      {
        Object localObject2 = localIterator.next();
        if (this.keyComparator.compare(paramK, localObject2) == 0) {
          localObject1 = localObject2;
        }
      }
      this.cache.remove(localObject1);
      return this.cache.put(paramK, paramV);
    }
  }
  
  public void remove(K paramK)
  {
    this.cache.remove(paramK);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\nostra13\universalimageloader\cache\memory\FuzzyKeyMemoryCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */