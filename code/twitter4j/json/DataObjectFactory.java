package twitter4j.json;

import java.util.Map;
import twitter4j.AccountTotals;
import twitter4j.Category;
import twitter4j.DirectMessage;
import twitter4j.IDs;
import twitter4j.Location;
import twitter4j.OEmbed;
import twitter4j.Place;
import twitter4j.RateLimitStatus;
import twitter4j.Relationship;
import twitter4j.SavedSearch;
import twitter4j.Status;
import twitter4j.Trend;
import twitter4j.Trends;
import twitter4j.TwitterException;
import twitter4j.TwitterObjectFactory;
import twitter4j.User;
import twitter4j.UserList;

public final class DataObjectFactory
{
  private DataObjectFactory()
  {
    throw new AssertionError("not intended to be instantiated.");
  }
  
  public static AccountTotals createAccountTotals(String paramString)
    throws TwitterException
  {
    return TwitterObjectFactory.createAccountTotals(paramString);
  }
  
  public static Category createCategory(String paramString)
    throws TwitterException
  {
    return TwitterObjectFactory.createCategory(paramString);
  }
  
  public static DirectMessage createDirectMessage(String paramString)
    throws TwitterException
  {
    return TwitterObjectFactory.createDirectMessage(paramString);
  }
  
  public static IDs createIDs(String paramString)
    throws TwitterException
  {
    return TwitterObjectFactory.createIDs(paramString);
  }
  
  public static Location createLocation(String paramString)
    throws TwitterException
  {
    return TwitterObjectFactory.createLocation(paramString);
  }
  
  public static OEmbed createOEmbed(String paramString)
    throws TwitterException
  {
    return TwitterObjectFactory.createOEmbed(paramString);
  }
  
  public static Object createObject(String paramString)
    throws TwitterException
  {
    return TwitterObjectFactory.createObject(paramString);
  }
  
  public static Place createPlace(String paramString)
    throws TwitterException
  {
    return TwitterObjectFactory.createPlace(paramString);
  }
  
  public static Map<String, RateLimitStatus> createRateLimitStatus(String paramString)
    throws TwitterException
  {
    return TwitterObjectFactory.createRateLimitStatus(paramString);
  }
  
  public static Relationship createRelationship(String paramString)
    throws TwitterException
  {
    return TwitterObjectFactory.createRelationship(paramString);
  }
  
  public static SavedSearch createSavedSearch(String paramString)
    throws TwitterException
  {
    return TwitterObjectFactory.createSavedSearch(paramString);
  }
  
  public static Status createStatus(String paramString)
    throws TwitterException
  {
    return TwitterObjectFactory.createStatus(paramString);
  }
  
  public static Trend createTrend(String paramString)
    throws TwitterException
  {
    return TwitterObjectFactory.createTrend(paramString);
  }
  
  public static Trends createTrends(String paramString)
    throws TwitterException
  {
    return TwitterObjectFactory.createTrends(paramString);
  }
  
  public static User createUser(String paramString)
    throws TwitterException
  {
    return TwitterObjectFactory.createUser(paramString);
  }
  
  public static UserList createUserList(String paramString)
    throws TwitterException
  {
    return TwitterObjectFactory.createUserList(paramString);
  }
  
  public static String getRawJSON(Object paramObject)
  {
    return TwitterObjectFactory.getRawJSON(paramObject);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\twitter4j\json\DataObjectFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */