package twitter4j.api;

import twitter4j.GeoLocation;
import twitter4j.Location;
import twitter4j.ResponseList;
import twitter4j.Trends;
import twitter4j.TwitterException;

public abstract interface TrendsResources
{
  public abstract ResponseList<Location> getAvailableTrends()
    throws TwitterException;
  
  public abstract ResponseList<Location> getClosestTrends(GeoLocation paramGeoLocation)
    throws TwitterException;
  
  public abstract Trends getPlaceTrends(int paramInt)
    throws TwitterException;
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\twitter4j\api\TrendsResources.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */