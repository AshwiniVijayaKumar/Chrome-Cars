package net.roarsoftware.lastfm.cache;

import java.util.Map;

public abstract interface ExpirationPolicy
{
  public abstract long getExpirationTime(String paramString, Map<String, String> paramMap);
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\net\roarsoftware\lastfm\cache\ExpirationPolicy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */