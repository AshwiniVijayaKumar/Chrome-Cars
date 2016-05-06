package com.nostra13.universalimageloader.cache.disc.impl;

import com.nostra13.universalimageloader.cache.disc.BaseDiscCache;
import java.io.File;

public class UnlimitedDiscCache
  extends BaseDiscCache
{
  public UnlimitedDiscCache(File paramFile)
  {
    super(paramFile);
  }
  
  protected String keyToFileName(String paramString)
  {
    return String.valueOf(paramString.hashCode());
  }
  
  public void put(String paramString, File paramFile) {}
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\nostra13\universalimageloader\cache\disc\impl\UnlimitedDiscCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */