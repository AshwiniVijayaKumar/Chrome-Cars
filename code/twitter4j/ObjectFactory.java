package twitter4j;

import java.io.Serializable;
import java.util.Map;
import twitter4j.api.HelpResources.Language;

abstract interface ObjectFactory
  extends Serializable
{
  public abstract UserList createAUserList(HttpResponse paramHttpResponse)
    throws TwitterException;
  
  public abstract UserList createAUserList(JSONObject paramJSONObject)
    throws TwitterException;
  
  public abstract AccountSettings createAccountSettings(HttpResponse paramHttpResponse)
    throws TwitterException;
  
  public abstract AccountTotals createAccountTotals(HttpResponse paramHttpResponse)
    throws TwitterException;
  
  public abstract ResponseList<Category> createCategoryList(HttpResponse paramHttpResponse)
    throws TwitterException;
  
  public abstract DirectMessage createDirectMessage(HttpResponse paramHttpResponse)
    throws TwitterException;
  
  public abstract ResponseList<DirectMessage> createDirectMessageList(HttpResponse paramHttpResponse)
    throws TwitterException;
  
  public abstract <T> ResponseList<T> createEmptyResponseList();
  
  public abstract ResponseList<Friendship> createFriendshipList(HttpResponse paramHttpResponse)
    throws TwitterException;
  
  public abstract IDs createIDs(HttpResponse paramHttpResponse)
    throws TwitterException;
  
  public abstract ResponseList<HelpResources.Language> createLanguageList(HttpResponse paramHttpResponse)
    throws TwitterException;
  
  public abstract ResponseList<Location> createLocationList(HttpResponse paramHttpResponse)
    throws TwitterException;
  
  public abstract OEmbed createOEmbed(HttpResponse paramHttpResponse)
    throws TwitterException;
  
  public abstract PagableResponseList<User> createPagableUserList(HttpResponse paramHttpResponse)
    throws TwitterException;
  
  public abstract PagableResponseList<UserList> createPagableUserListList(HttpResponse paramHttpResponse)
    throws TwitterException;
  
  public abstract Place createPlace(HttpResponse paramHttpResponse)
    throws TwitterException;
  
  public abstract ResponseList<Place> createPlaceList(HttpResponse paramHttpResponse)
    throws TwitterException;
  
  public abstract QueryResult createQueryResult(HttpResponse paramHttpResponse, Query paramQuery)
    throws TwitterException;
  
  public abstract Map<String, RateLimitStatus> createRateLimitStatuses(HttpResponse paramHttpResponse)
    throws TwitterException;
  
  public abstract Relationship createRelationship(HttpResponse paramHttpResponse)
    throws TwitterException;
  
  public abstract SavedSearch createSavedSearch(HttpResponse paramHttpResponse)
    throws TwitterException;
  
  public abstract ResponseList<SavedSearch> createSavedSearchList(HttpResponse paramHttpResponse)
    throws TwitterException;
  
  public abstract Status createStatus(HttpResponse paramHttpResponse)
    throws TwitterException;
  
  public abstract Status createStatus(JSONObject paramJSONObject)
    throws TwitterException;
  
  public abstract ResponseList<Status> createStatusList(HttpResponse paramHttpResponse)
    throws TwitterException;
  
  public abstract Trends createTrends(HttpResponse paramHttpResponse)
    throws TwitterException;
  
  public abstract TwitterAPIConfiguration createTwitterAPIConfiguration(HttpResponse paramHttpResponse)
    throws TwitterException;
  
  public abstract User createUser(HttpResponse paramHttpResponse)
    throws TwitterException;
  
  public abstract User createUser(JSONObject paramJSONObject)
    throws TwitterException;
  
  public abstract ResponseList<User> createUserList(HttpResponse paramHttpResponse)
    throws TwitterException;
  
  public abstract ResponseList<User> createUserListFromJSONArray(HttpResponse paramHttpResponse)
    throws TwitterException;
  
  public abstract ResponseList<User> createUserListFromJSONArray_Users(HttpResponse paramHttpResponse)
    throws TwitterException;
  
  public abstract ResponseList<UserList> createUserListList(HttpResponse paramHttpResponse)
    throws TwitterException;
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\twitter4j\ObjectFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */