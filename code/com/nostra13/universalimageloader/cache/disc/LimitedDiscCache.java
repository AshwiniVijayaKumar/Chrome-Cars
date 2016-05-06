package com.nostra13.universalimageloader.cache.disc;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public abstract class LimitedDiscCache
  extends BaseDiscCache
{
  private int cacheSize = 0;
  private final Map<File, Long> lastUsageDates = Collections.synchronizedMap(new HashMap());
  private int sizeLimit;
  
  public LimitedDiscCache(File paramFile, int paramInt)
  {
    super(paramFile);
    this.sizeLimit = paramInt;
    calculateCacheSizeAndFillUsageMap();
  }
  
  private void calculateCacheSizeAndFillUsageMap()
  {
    int j = 0;
    File[] arrayOfFile = getCacheDir().listFiles();
    int k = arrayOfFile.length;
    int i = 0;
    while (i < k)
    {
      File localFile = arrayOfFile[i];
      j += getSize(localFile);
      this.lastUsageDates.put(localFile, Long.valueOf(localFile.lastModified()));
      i += 1;
    }
    this.cacheSize = j;
  }
  
  private int removeNext()
  {
    Object localObject2 = null;
    File localFile = null;
    Object localObject3 = this.lastUsageDates.entrySet();
    synchronized (this.lastUsageDates)
    {
      Iterator localIterator = ((Set)localObject3).iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        if (localFile == null)
        {
          localFile = (File)localEntry.getKey();
          localObject2 = (Long)localEntry.getValue();
        }
        else
        {
          localObject3 = (Long)localEntry.getValue();
          if (((Long)localObject3).longValue() < ((Long)localObject2).longValue())
          {
            localObject2 = localObject3;
            localFile = (File)localEntry.getKey();
          }
        }
      }
      int i = getSize(localFile);
      this.lastUsageDates.remove(localFile);
      return i;
    }
  }
  
  public void clear()
  {
    super.clear();
    this.lastUsageDates.clear();
  }
  
  public File get(String paramString)
  {
    paramString = super.get(paramString);
    Long localLong = Long.valueOf(System.currentTimeMillis());
    paramString.setLastModified(localLong.longValue());
    this.lastUsageDates.put(paramString, localLong);
    return paramString;
  }
  
  protected abstract int getSize(File paramFile);
  
  public void put(String paramString, File paramFile)
  {
    int i = getSize(paramFile);
    if (i < this.sizeLimit)
    {
      while (this.cacheSize + i > this.sizeLimit)
      {
        int j = removeNext();
        this.cacheSize -= j;
      }
      this.cacheSize += i;
    }
    paramString = Long.valueOf(System.currentTimeMillis());
    paramFile.setLastModified(paramString.longValue());
    this.lastUsageDates.put(paramFile, paramString);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\nostra13\universalimageloader\cache\disc\LimitedDiscCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */