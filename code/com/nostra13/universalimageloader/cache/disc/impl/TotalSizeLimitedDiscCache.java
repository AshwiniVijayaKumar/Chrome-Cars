package com.nostra13.universalimageloader.cache.disc.impl;

import com.nostra13.universalimageloader.cache.disc.LimitedDiscCache;
import java.io.File;

public class TotalSizeLimitedDiscCache
  extends LimitedDiscCache
{
  public TotalSizeLimitedDiscCache(File paramFile, int paramInt)
  {
    super(paramFile, paramInt);
  }
  
  protected int getSize(File paramFile)
  {
    return (int)paramFile.length();
  }
  
  protected String keyToFileName(String paramString)
  {
    return String.valueOf(paramString.hashCode());
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\nostra13\universalimageloader\cache\disc\impl\TotalSizeLimitedDiscCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */