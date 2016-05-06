package net.roarsoftware.lastfm.cache;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DefaultExpirationPolicy
  implements ExpirationPolicy
{
  protected static final long ONE_DAY = 86400000L;
  protected static final long ONE_WEEK = 604800000L;
  protected static final Set<String> ONE_WEEK_METHODS = new HashSet();
  protected long cacheRecentWeeklyCharts = 604800000L;
  
  static
  {
    ONE_WEEK_METHODS.add("artist.getsimilar");
    ONE_WEEK_METHODS.add("tag.getsimilar");
    ONE_WEEK_METHODS.add("track.getsimilar");
    ONE_WEEK_METHODS.add("artist.gettopalbums");
    ONE_WEEK_METHODS.add("artist.gettoptracks");
    ONE_WEEK_METHODS.add("geo.gettopartists");
    ONE_WEEK_METHODS.add("geo.gettoptracks");
    ONE_WEEK_METHODS.add("tag.gettopalbums");
    ONE_WEEK_METHODS.add("tag.gettopartists");
    ONE_WEEK_METHODS.add("tag.gettoptags");
    ONE_WEEK_METHODS.add("tag.gettoptracks");
    ONE_WEEK_METHODS.add("user.gettopalbums");
    ONE_WEEK_METHODS.add("user.gettopartists");
    ONE_WEEK_METHODS.add("user.gettoptracks");
    ONE_WEEK_METHODS.add("user.gettoptags");
  }
  
  public long getExpirationTime(String paramString, Map<String, String> paramMap)
  {
    paramString = paramString.toLowerCase();
    if (paramString.contains("weekly"))
    {
      if (!paramString.contains("list"))
      {
        if ((paramMap.containsKey("to")) && (paramMap.containsKey("from"))) {
          return Long.MAX_VALUE;
        }
        return this.cacheRecentWeeklyCharts;
      }
      return this.cacheRecentWeeklyCharts;
    }
    if (ONE_WEEK_METHODS.contains(paramString)) {
      return 604800000L;
    }
    return -1L;
  }
  
  public void setCacheRecentWeeklyCharts(long paramLong)
  {
    this.cacheRecentWeeklyCharts = paramLong;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\net\roarsoftware\lastfm\cache\DefaultExpirationPolicy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */