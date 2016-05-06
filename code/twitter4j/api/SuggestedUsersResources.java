package twitter4j.api;

import twitter4j.Category;
import twitter4j.ResponseList;
import twitter4j.TwitterException;
import twitter4j.User;

public abstract interface SuggestedUsersResources
{
  public abstract ResponseList<User> getMemberSuggestions(String paramString)
    throws TwitterException;
  
  public abstract ResponseList<Category> getSuggestedUserCategories()
    throws TwitterException;
  
  public abstract ResponseList<User> getUserSuggestions(String paramString)
    throws TwitterException;
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\twitter4j\api\SuggestedUsersResources.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */