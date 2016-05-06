package twitter4j;

import java.io.Serializable;
import java.util.Date;

public abstract interface Trends
  extends TwitterResponse, Comparable<Trends>, Serializable
{
  public abstract Date getAsOf();
  
  public abstract Location getLocation();
  
  public abstract Date getTrendAt();
  
  public abstract Trend[] getTrends();
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\twitter4j\Trends.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */