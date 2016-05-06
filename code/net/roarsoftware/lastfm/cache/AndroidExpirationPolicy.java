package net.roarsoftware.lastfm.cache;

import java.util.Map;

public class AndroidExpirationPolicy
  implements ExpirationPolicy
{
  public long getExpirationTime(String paramString, Map<String, String> paramMap)
  {
    return 3600000L;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\net\roarsoftware\lastfm\cache\AndroidExpirationPolicy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */