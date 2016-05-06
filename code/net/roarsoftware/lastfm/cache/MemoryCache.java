package net.roarsoftware.lastfm.cache;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MemoryCache
  extends Cache
{
  private Map<String, String> data = new HashMap();
  private Map<String, Long> expirations = new HashMap();
  
  public void clear()
  {
    this.data.clear();
    this.expirations.clear();
  }
  
  public boolean contains(String paramString)
  {
    boolean bool = this.data.containsKey(paramString);
    System.out.println("MemoryCache.contains: " + paramString + " ? " + bool);
    return bool;
  }
  
  public boolean isExpired(String paramString)
  {
    if (((Long)this.expirations.get(paramString)).longValue() < System.currentTimeMillis()) {}
    for (boolean bool = true;; bool = false)
    {
      System.out.println("MemoryCache.isExpired: " + paramString + " ? " + bool);
      return bool;
    }
  }
  
  public InputStream load(String paramString)
  {
    System.out.println("MemoryCache.load: " + paramString);
    try
    {
      paramString = new ByteArrayInputStream(((String)this.data.get(paramString)).getBytes("UTF-8"));
      return paramString;
    }
    catch (UnsupportedEncodingException paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
  
  public void remove(String paramString)
  {
    System.out.println("MemoryCache.remove: " + paramString);
    this.data.remove(paramString);
    this.expirations.remove(paramString);
  }
  
  public void store(String paramString, InputStream paramInputStream, long paramLong)
  {
    System.out.println("MemoryCache.store: " + paramString + " Expires at: " + new Date(paramLong));
    StringBuilder localStringBuilder = new StringBuilder();
    try
    {
      paramInputStream = new BufferedReader(new InputStreamReader(paramInputStream, "UTF-8"));
      for (;;)
      {
        String str = paramInputStream.readLine();
        if (str == null) {
          break;
        }
        localStringBuilder.append(str);
      }
      return;
    }
    catch (IOException paramInputStream)
    {
      paramInputStream.printStackTrace();
      this.data.put(paramString, localStringBuilder.toString());
      this.expirations.put(paramString, Long.valueOf(paramLong));
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\net\roarsoftware\lastfm\cache\MemoryCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */