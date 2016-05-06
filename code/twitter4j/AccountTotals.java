package twitter4j;

import java.io.Serializable;

public abstract interface AccountTotals
  extends Serializable, TwitterResponse
{
  public abstract int getFavorites();
  
  public abstract int getFollowers();
  
  public abstract int getFriends();
  
  public abstract int getUpdates();
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\twitter4j\AccountTotals.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */