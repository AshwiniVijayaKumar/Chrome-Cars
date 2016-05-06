package twitter4j;

import java.util.HashMap;
import java.util.Map;

public final class TwitterObjectFactory
{
  private static final ThreadLocal<Map> rawJsonMap = new ThreadLocal()
  {
    protected Map initialValue()
    {
      return new HashMap();
    }
  };
  private static boolean registeredAtleastOnce = false;
  
  private TwitterObjectFactory()
  {
    throw new AssertionError("not intended to be instantiated.");
  }
  
  static void clearThreadLocalMap()
  {
    ((Map)rawJsonMap.get()).clear();
  }
  
  public static AccountTotals createAccountTotals(String paramString)
    throws TwitterException
  {
    try
    {
      paramString = new AccountTotalsJSONImpl(new JSONObject(paramString));
      return paramString;
    }
    catch (JSONException paramString)
    {
      throw new TwitterException(paramString);
    }
  }
  
  public static Category createCategory(String paramString)
    throws TwitterException
  {
    try
    {
      paramString = new CategoryJSONImpl(new JSONObject(paramString));
      return paramString;
    }
    catch (JSONException paramString)
    {
      throw new TwitterException(paramString);
    }
  }
  
  public static DirectMessage createDirectMessage(String paramString)
    throws TwitterException
  {
    try
    {
      paramString = new DirectMessageJSONImpl(new JSONObject(paramString));
      return paramString;
    }
    catch (JSONException paramString)
    {
      throw new TwitterException(paramString);
    }
  }
  
  public static IDs createIDs(String paramString)
    throws TwitterException
  {
    return new IDsJSONImpl(paramString);
  }
  
  public static Location createLocation(String paramString)
    throws TwitterException
  {
    try
    {
      paramString = new LocationJSONImpl(new JSONObject(paramString));
      return paramString;
    }
    catch (JSONException paramString)
    {
      throw new TwitterException(paramString);
    }
  }
  
  public static OEmbed createOEmbed(String paramString)
    throws TwitterException
  {
    try
    {
      paramString = new OEmbedJSONImpl(new JSONObject(paramString));
      return paramString;
    }
    catch (JSONException paramString)
    {
      throw new TwitterException(paramString);
    }
  }
  
  public static Object createObject(String paramString)
    throws TwitterException
  {
    try
    {
      paramString = new JSONObject(paramString);
      JSONObjectType.Type localType = JSONObjectType.determine(paramString);
      switch (localType)
      {
      case ???: 
        return registerJSONObject(new DirectMessageJSONImpl(paramString.getJSONObject("direct_message")), paramString);
      }
    }
    catch (JSONException paramString)
    {
      throw new TwitterException(paramString);
    }
    return registerJSONObject(new StatusJSONImpl(paramString), paramString);
    return registerJSONObject(new DirectMessageJSONImpl(paramString.getJSONObject("direct_message")), paramString);
    paramString = registerJSONObject(new StatusDeletionNoticeImpl(paramString.getJSONObject("delete").getJSONObject("status")), paramString);
    return paramString;
    return paramString;
  }
  
  public static Place createPlace(String paramString)
    throws TwitterException
  {
    try
    {
      paramString = new PlaceJSONImpl(new JSONObject(paramString));
      return paramString;
    }
    catch (JSONException paramString)
    {
      throw new TwitterException(paramString);
    }
  }
  
  public static Map<String, RateLimitStatus> createRateLimitStatus(String paramString)
    throws TwitterException
  {
    try
    {
      paramString = RateLimitStatusJSONImpl.createRateLimitStatuses(new JSONObject(paramString));
      return paramString;
    }
    catch (JSONException paramString)
    {
      throw new TwitterException(paramString);
    }
  }
  
  public static Relationship createRelationship(String paramString)
    throws TwitterException
  {
    try
    {
      paramString = new RelationshipJSONImpl(new JSONObject(paramString));
      return paramString;
    }
    catch (JSONException paramString)
    {
      throw new TwitterException(paramString);
    }
  }
  
  public static SavedSearch createSavedSearch(String paramString)
    throws TwitterException
  {
    try
    {
      paramString = new SavedSearchJSONImpl(new JSONObject(paramString));
      return paramString;
    }
    catch (JSONException paramString)
    {
      throw new TwitterException(paramString);
    }
  }
  
  public static Status createStatus(String paramString)
    throws TwitterException
  {
    try
    {
      paramString = new StatusJSONImpl(new JSONObject(paramString));
      return paramString;
    }
    catch (JSONException paramString)
    {
      throw new TwitterException(paramString);
    }
  }
  
  public static Trend createTrend(String paramString)
    throws TwitterException
  {
    try
    {
      paramString = new TrendJSONImpl(new JSONObject(paramString));
      return paramString;
    }
    catch (JSONException paramString)
    {
      throw new TwitterException(paramString);
    }
  }
  
  public static Trends createTrends(String paramString)
    throws TwitterException
  {
    return new TrendsJSONImpl(paramString);
  }
  
  public static User createUser(String paramString)
    throws TwitterException
  {
    try
    {
      paramString = new UserJSONImpl(new JSONObject(paramString));
      return paramString;
    }
    catch (JSONException paramString)
    {
      throw new TwitterException(paramString);
    }
  }
  
  public static UserList createUserList(String paramString)
    throws TwitterException
  {
    try
    {
      paramString = new UserListJSONImpl(new JSONObject(paramString));
      return paramString;
    }
    catch (JSONException paramString)
    {
      throw new TwitterException(paramString);
    }
  }
  
  public static String getRawJSON(Object paramObject)
  {
    if (!registeredAtleastOnce) {
      throw new IllegalStateException("Apparently jsonStoreEnabled is not set to true.");
    }
    paramObject = ((Map)rawJsonMap.get()).get(paramObject);
    if ((paramObject instanceof String)) {
      return (String)paramObject;
    }
    if (paramObject != null) {
      return paramObject.toString();
    }
    return null;
  }
  
  static <T> T registerJSONObject(T paramT, Object paramObject)
  {
    registeredAtleastOnce = true;
    ((Map)rawJsonMap.get()).put(paramT, paramObject);
    return paramT;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\twitter4j\TwitterObjectFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */