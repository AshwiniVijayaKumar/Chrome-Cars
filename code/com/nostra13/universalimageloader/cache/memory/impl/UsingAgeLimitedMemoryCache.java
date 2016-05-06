package com.nostra13.universalimageloader.cache.memory.impl;

import android.graphics.Bitmap;
import com.nostra13.universalimageloader.cache.memory.LimitedMemoryCache;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class UsingAgeLimitedMemoryCache
  extends LimitedMemoryCache<String, Bitmap>
{
  private final Map<Bitmap, Long> lastUsageDates = Collections.synchronizedMap(new HashMap());
  
  public UsingAgeLimitedMemoryCache(int paramInt)
  {
    super(paramInt);
  }
  
  public void clear()
  {
    this.lastUsageDates.clear();
    super.clear();
  }
  
  protected Reference<Bitmap> createReference(Bitmap paramBitmap)
  {
    return new WeakReference(paramBitmap);
  }
  
  public Bitmap get(String paramString)
  {
    paramString = (Bitmap)super.get(paramString);
    if ((paramString != null) && ((Long)this.lastUsageDates.get(paramString) != null)) {
      this.lastUsageDates.put(paramString, Long.valueOf(System.currentTimeMillis()));
    }
    return paramString;
  }
  
  protected int getSize(Bitmap paramBitmap)
  {
    return paramBitmap.getRowBytes() * paramBitmap.getHeight();
  }
  
  public boolean put(String paramString, Bitmap paramBitmap)
  {
    if (super.put(paramString, paramBitmap))
    {
      this.lastUsageDates.put(paramBitmap, Long.valueOf(System.currentTimeMillis()));
      return true;
    }
    return false;
  }
  
  public void remove(String paramString)
  {
    Bitmap localBitmap = (Bitmap)super.get(paramString);
    if (localBitmap != null) {
      this.lastUsageDates.remove(localBitmap);
    }
    super.remove(paramString);
  }
  
  protected Bitmap removeNext()
  {
    Object localObject2 = null;
    Bitmap localBitmap = null;
    Object localObject3 = this.lastUsageDates.entrySet();
    synchronized (this.lastUsageDates)
    {
      Iterator localIterator = ((Set)localObject3).iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        if (localBitmap == null)
        {
          localBitmap = (Bitmap)localEntry.getKey();
          localObject2 = (Long)localEntry.getValue();
        }
        else
        {
          localObject3 = (Long)localEntry.getValue();
          if (((Long)localObject3).longValue() < ((Long)localObject2).longValue())
          {
            localObject2 = localObject3;
            localBitmap = (Bitmap)localEntry.getKey();
          }
        }
      }
      this.lastUsageDates.remove(localBitmap);
      return localBitmap;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\nostra13\universalimageloader\cache\memory\impl\UsingAgeLimitedMemoryCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */