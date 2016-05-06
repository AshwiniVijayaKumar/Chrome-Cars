package twitter4j.api;

import twitter4j.ResponseList;
import twitter4j.SavedSearch;
import twitter4j.TwitterException;

public abstract interface SavedSearchesResources
{
  public abstract SavedSearch createSavedSearch(String paramString)
    throws TwitterException;
  
  public abstract SavedSearch destroySavedSearch(int paramInt)
    throws TwitterException;
  
  public abstract ResponseList<SavedSearch> getSavedSearches()
    throws TwitterException;
  
  public abstract SavedSearch showSavedSearch(int paramInt)
    throws TwitterException;
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\twitter4j\api\SavedSearchesResources.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */