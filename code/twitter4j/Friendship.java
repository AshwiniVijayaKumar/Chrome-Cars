package twitter4j;

import java.io.Serializable;

public abstract interface Friendship
  extends Serializable
{
  public abstract long getId();
  
  public abstract String getName();
  
  public abstract String getScreenName();
  
  public abstract boolean isFollowedBy();
  
  public abstract boolean isFollowing();
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\twitter4j\Friendship.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */