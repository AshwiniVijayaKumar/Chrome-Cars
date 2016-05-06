package com.nostra13.universalimageloader.cache.disc;

import java.io.File;

public abstract class BaseDiscCache
  implements DiscCacheAware
{
  private File cacheDir;
  
  public BaseDiscCache(File paramFile)
  {
    this.cacheDir = paramFile;
  }
  
  public void clear()
  {
    File[] arrayOfFile = this.cacheDir.listFiles();
    if (arrayOfFile != null)
    {
      int j = arrayOfFile.length;
      int i = 0;
      while (i < j)
      {
        arrayOfFile[i].delete();
        i += 1;
      }
    }
  }
  
  public File get(String paramString)
  {
    paramString = keyToFileName(paramString);
    return new File(this.cacheDir, paramString);
  }
  
  protected File getCacheDir()
  {
    return this.cacheDir;
  }
  
  protected abstract String keyToFileName(String paramString);
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\nostra13\universalimageloader\cache\disc\BaseDiscCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */