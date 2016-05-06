package com.nostra13.universalimageloader.cache.disc;

import java.io.File;

public abstract interface DiscCacheAware
{
  public abstract void clear();
  
  public abstract File get(String paramString);
  
  public abstract void put(String paramString, File paramFile);
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\nostra13\universalimageloader\cache\disc\DiscCacheAware.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */