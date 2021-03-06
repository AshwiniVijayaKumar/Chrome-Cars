package twitter4j;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import twitter4j.conf.Configuration;

final class RateLimitStatusJSONImpl
  implements Serializable, RateLimitStatus
{
  private static final long serialVersionUID = 7790337632915862445L;
  private int limit;
  private int remaining;
  private int resetTimeInSeconds;
  private int secondsUntilReset;
  
  private RateLimitStatusJSONImpl(int paramInt1, int paramInt2, int paramInt3)
  {
    this.limit = paramInt1;
    this.remaining = paramInt2;
    this.resetTimeInSeconds = paramInt3;
    this.secondsUntilReset = ((int)((paramInt3 * 1000L - System.currentTimeMillis()) / 1000L));
  }
  
  RateLimitStatusJSONImpl(JSONObject paramJSONObject)
    throws TwitterException
  {
    init(paramJSONObject);
  }
  
  static RateLimitStatus createFromResponseHeader(HttpResponse paramHttpResponse)
  {
    if (paramHttpResponse == null) {}
    int i;
    int j;
    do
    {
      String str;
      do
      {
        do
        {
          return null;
          str = paramHttpResponse.getResponseHeader("X-Rate-Limit-Limit");
        } while (str == null);
        i = Integer.parseInt(str);
        str = paramHttpResponse.getResponseHeader("X-Rate-Limit-Remaining");
      } while (str == null);
      j = Integer.parseInt(str);
      paramHttpResponse = paramHttpResponse.getResponseHeader("X-Rate-Limit-Reset");
    } while (paramHttpResponse == null);
    return new RateLimitStatusJSONImpl(i, j, (int)Long.parseLong(paramHttpResponse));
  }
  
  static Map<String, RateLimitStatus> createRateLimitStatuses(HttpResponse paramHttpResponse, Configuration paramConfiguration)
    throws TwitterException
  {
    paramHttpResponse = paramHttpResponse.asJSONObject();
    Map localMap = createRateLimitStatuses(paramHttpResponse);
    if (paramConfiguration.isJSONStoreEnabled())
    {
      TwitterObjectFactory.clearThreadLocalMap();
      TwitterObjectFactory.registerJSONObject(localMap, paramHttpResponse);
    }
    return localMap;
  }
  
  static Map<String, RateLimitStatus> createRateLimitStatuses(JSONObject paramJSONObject)
    throws TwitterException
  {
    HashMap localHashMap = new HashMap();
    try
    {
      paramJSONObject = paramJSONObject.getJSONObject("resources");
      Iterator localIterator1 = paramJSONObject.keys();
      while (localIterator1.hasNext())
      {
        JSONObject localJSONObject = paramJSONObject.getJSONObject((String)localIterator1.next());
        Iterator localIterator2 = localJSONObject.keys();
        while (localIterator2.hasNext())
        {
          String str = (String)localIterator2.next();
          localHashMap.put(str, new RateLimitStatusJSONImpl(localJSONObject.getJSONObject(str)));
        }
      }
      paramJSONObject = Collections.unmodifiableMap(localHashMap);
    }
    catch (JSONException paramJSONObject)
    {
      throw new TwitterException(paramJSONObject);
    }
    return paramJSONObject;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if ((paramObject == null) || (getClass() != paramObject.getClass())) {
        return false;
      }
      paramObject = (RateLimitStatusJSONImpl)paramObject;
      if (this.limit != ((RateLimitStatusJSONImpl)paramObject).limit) {
        return false;
      }
      if (this.remaining != ((RateLimitStatusJSONImpl)paramObject).remaining) {
        return false;
      }
      if (this.resetTimeInSeconds != ((RateLimitStatusJSONImpl)paramObject).resetTimeInSeconds) {
        return false;
      }
    } while (this.secondsUntilReset == ((RateLimitStatusJSONImpl)paramObject).secondsUntilReset);
    return false;
  }
  
  public int getLimit()
  {
    return this.limit;
  }
  
  public int getRemaining()
  {
    return this.remaining;
  }
  
  public int getResetTimeInSeconds()
  {
    return this.resetTimeInSeconds;
  }
  
  public int getSecondsUntilReset()
  {
    return this.secondsUntilReset;
  }
  
  public int hashCode()
  {
    return ((this.remaining * 31 + this.limit) * 31 + this.resetTimeInSeconds) * 31 + this.secondsUntilReset;
  }
  
  void init(JSONObject paramJSONObject)
    throws TwitterException
  {
    this.limit = ParseUtil.getInt("limit", paramJSONObject);
    this.remaining = ParseUtil.getInt("remaining", paramJSONObject);
    this.resetTimeInSeconds = ParseUtil.getInt("reset", paramJSONObject);
    this.secondsUntilReset = ((int)((this.resetTimeInSeconds * 1000L - System.currentTimeMillis()) / 1000L));
  }
  
  public String toString()
  {
    return "RateLimitStatusJSONImpl{remaining=" + this.remaining + ", limit=" + this.limit + ", resetTimeInSeconds=" + this.resetTimeInSeconds + ", secondsUntilReset=" + this.secondsUntilReset + '}';
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\twitter4j\RateLimitStatusJSONImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */