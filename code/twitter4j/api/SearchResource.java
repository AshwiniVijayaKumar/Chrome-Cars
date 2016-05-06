package twitter4j.api;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.TwitterException;

public abstract interface SearchResource
{
  public abstract QueryResult search(Query paramQuery)
    throws TwitterException;
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\twitter4j\api\SearchResource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */