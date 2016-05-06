package net.roarsoftware.lastfm.cache;

import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import net.roarsoftware.util.StringUtilities;

public abstract class Cache
{
  private static boolean hashCacheEntryNames = true;
  private ExpirationPolicy expirationPolicy = new AndroidExpirationPolicy();
  
  public static String createCacheEntryName(String paramString, Map<String, String> paramMap)
  {
    Object localObject = paramMap;
    if (!(paramMap instanceof SortedMap)) {
      localObject = new TreeMap(paramMap);
    }
    paramMap = new StringBuilder(100);
    paramMap.append(paramString.toLowerCase());
    paramMap.append('.');
    paramString = ((Map)localObject).entrySet().iterator();
    while (paramString.hasNext())
    {
      localObject = (Map.Entry)paramString.next();
      paramMap.append((String)((Map.Entry)localObject).getKey());
      paramMap.append((String)((Map.Entry)localObject).getValue());
    }
    paramString = paramMap.toString();
    if (hashCacheEntryNames) {
      return StringUtilities.md5(paramString);
    }
    return StringUtilities.cleanUp(paramString);
  }
  
  public static void setHashCacheEntryNames(boolean paramBoolean)
  {
    hashCacheEntryNames = paramBoolean;
  }
  
  public abstract void clear();
  
  public abstract boolean contains(String paramString);
  
  public ExpirationPolicy getExpirationPolicy()
  {
    return this.expirationPolicy;
  }
  
  public abstract boolean isExpired(String paramString);
  
  public abstract InputStream load(String paramString);
  
  public abstract void remove(String paramString);
  
  public void setExpirationPolicy(ExpirationPolicy paramExpirationPolicy)
  {
    if (paramExpirationPolicy == null) {
      throw new NullPointerException("policy == null");
    }
    this.expirationPolicy = paramExpirationPolicy;
  }
  
  public abstract void store(String paramString, InputStream paramInputStream, long paramLong);
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\net\roarsoftware\lastfm\cache\Cache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */