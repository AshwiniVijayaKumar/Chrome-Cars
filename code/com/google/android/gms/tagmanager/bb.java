package com.google.android.gms.tagmanager;

import android.util.LruCache;

class bb<K, V>
  implements k<K, V>
{
  private LruCache<K, V> Vw;
  
  bb(int paramInt, final l.a<K, V> parama)
  {
    this.Vw = new LruCache(paramInt)
    {
      protected int sizeOf(K paramAnonymousK, V paramAnonymousV)
      {
        return parama.sizeOf(paramAnonymousK, paramAnonymousV);
      }
    };
  }
  
  public void e(K paramK, V paramV)
  {
    this.Vw.put(paramK, paramV);
  }
  
  public V get(K paramK)
  {
    return (V)this.Vw.get(paramK);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\tagmanager\bb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */