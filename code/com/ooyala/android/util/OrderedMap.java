package com.ooyala.android.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class OrderedMap<K, V extends OrderedMapValue<K>>
  implements Iterable<V>
{
  private List<V> _array = new ArrayList();
  private Map<K, Integer> _keyToIndex = new HashMap();
  private Map<K, V> _map = new HashMap();
  
  private void recomputeIndicies()
  {
    int i = 0;
    this._keyToIndex.clear();
    Iterator localIterator = this._array.iterator();
    while (localIterator.hasNext())
    {
      OrderedMapValue localOrderedMapValue = (OrderedMapValue)localIterator.next();
      this._keyToIndex.put(localOrderedMapValue.getKey(), Integer.valueOf(i));
      i += 1;
    }
  }
  
  private int verifyIndex(int paramInt)
    throws ArrayIndexOutOfBoundsException
  {
    if ((paramInt >= this._array.size()) || (paramInt < 0)) {
      throw new ArrayIndexOutOfBoundsException(paramInt);
    }
    return paramInt;
  }
  
  public void add(int paramInt, V paramV)
  {
    this._array.add(paramInt, paramV);
    this._map.put(paramV.getKey(), paramV);
    recomputeIndicies();
  }
  
  public boolean add(V paramV)
  {
    this._map.put(paramV.getKey(), paramV);
    this._keyToIndex.put(paramV.getKey(), Integer.valueOf(this._array.size()));
    this._array.add(paramV);
    return true;
  }
  
  public boolean addAll(int paramInt, Collection<? extends V> paramCollection)
  {
    this._array.addAll(paramInt, paramCollection);
    paramCollection = paramCollection.iterator();
    while (paramCollection.hasNext())
    {
      OrderedMapValue localOrderedMapValue = (OrderedMapValue)paramCollection.next();
      this._map.put(localOrderedMapValue.getKey(), localOrderedMapValue);
    }
    recomputeIndicies();
    return true;
  }
  
  public boolean addAll(Collection<? extends V> paramCollection)
  {
    this._array.addAll(paramCollection);
    paramCollection = paramCollection.iterator();
    while (paramCollection.hasNext())
    {
      OrderedMapValue localOrderedMapValue = (OrderedMapValue)paramCollection.next();
      this._map.put(localOrderedMapValue.getKey(), localOrderedMapValue);
    }
    recomputeIndicies();
    return true;
  }
  
  public void clear()
  {
    this._map.clear();
    this._array.clear();
    this._keyToIndex.clear();
  }
  
  public boolean contains(V paramV)
  {
    return this._array.contains(paramV);
  }
  
  public boolean containsAll(Collection<V> paramCollection)
  {
    return this._array.containsAll(paramCollection);
  }
  
  public boolean containsKey(K paramK)
  {
    return this._map.containsKey(paramK);
  }
  
  public boolean containsValue(V paramV)
  {
    return this._map.containsValue(paramV);
  }
  
  public Set<Map.Entry<K, V>> entrySet()
  {
    return this._map.entrySet();
  }
  
  public V get(int paramInt)
  {
    return (OrderedMapValue)this._array.get(paramInt);
  }
  
  public V get(K paramK)
  {
    return (OrderedMapValue)this._map.get(paramK);
  }
  
  public int indexForKey(K paramK)
  {
    paramK = (Integer)this._keyToIndex.get(paramK);
    if (paramK == null) {
      return -1;
    }
    return paramK.intValue();
  }
  
  public int indexForValue(V paramV)
  {
    paramV = (Integer)this._keyToIndex.get(paramV.getKey());
    if (paramV == null) {
      return -1;
    }
    return paramV.intValue();
  }
  
  public int indexOf(V paramV)
  {
    return this._array.indexOf(paramV);
  }
  
  public boolean isEmpty()
  {
    return this._array.isEmpty();
  }
  
  public Iterator<V> iterator()
  {
    return this._array.iterator();
  }
  
  public Set<K> keySet()
  {
    return this._map.keySet();
  }
  
  public int lastIndexOf(V paramV)
  {
    return this._array.lastIndexOf(paramV);
  }
  
  public ListIterator<V> listIterator()
  {
    return this._array.listIterator();
  }
  
  public ListIterator<V> listIterator(int paramInt)
  {
    return this._array.listIterator(paramInt);
  }
  
  public V put(K paramK, V paramV)
  {
    this._keyToIndex.put(paramK, Integer.valueOf(this._array.size()));
    this._array.add(paramV);
    return (OrderedMapValue)this._map.put(paramK, paramV);
  }
  
  public V remove(int paramInt)
  {
    paramInt = verifyIndex(paramInt);
    OrderedMapValue localOrderedMapValue = (OrderedMapValue)this._array.remove(paramInt);
    this._map.remove(localOrderedMapValue.getKey());
    this._keyToIndex.remove(localOrderedMapValue.getKey());
    recomputeIndicies();
    return localOrderedMapValue;
  }
  
  public V remove(V paramV)
  {
    OrderedMapValue localOrderedMapValue = (OrderedMapValue)this._map.remove(paramV.getKey());
    this._array.remove(paramV);
    this._keyToIndex.remove(paramV.getKey());
    return localOrderedMapValue;
  }
  
  public V remove(K paramK)
  {
    OrderedMapValue localOrderedMapValue = (OrderedMapValue)this._map.remove(paramK);
    this._array.remove(localOrderedMapValue);
    this._keyToIndex.remove(paramK);
    recomputeIndicies();
    return localOrderedMapValue;
  }
  
  public V set(int paramInt, V paramV)
  {
    OrderedMapValue localOrderedMapValue = remove(paramInt);
    add(paramInt, paramV);
    return localOrderedMapValue;
  }
  
  public int size()
  {
    return this._array.size();
  }
  
  public List<V> subList(int paramInt1, int paramInt2)
  {
    return this._array.subList(paramInt1, paramInt2);
  }
  
  public Object[] toArray()
  {
    return this._array.toArray();
  }
  
  public <T> T[] toArray(T[] paramArrayOfT)
  {
    return this._array.toArray(paramArrayOfT);
  }
  
  public Collection<V> values()
  {
    return this._map.values();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\util\OrderedMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */