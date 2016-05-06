package twitter4j.api;

import twitter4j.GeoLocation;
import twitter4j.GeoQuery;
import twitter4j.Place;
import twitter4j.ResponseList;
import twitter4j.TwitterException;

public abstract interface PlacesGeoResources
{
  public abstract Place getGeoDetails(String paramString)
    throws TwitterException;
  
  public abstract ResponseList<Place> getSimilarPlaces(GeoLocation paramGeoLocation, String paramString1, String paramString2, String paramString3)
    throws TwitterException;
  
  public abstract ResponseList<Place> reverseGeoCode(GeoQuery paramGeoQuery)
    throws TwitterException;
  
  public abstract ResponseList<Place> searchPlaces(GeoQuery paramGeoQuery)
    throws TwitterException;
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\twitter4j\api\PlacesGeoResources.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */