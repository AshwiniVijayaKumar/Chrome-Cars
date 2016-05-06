package twitter4j;

import java.io.Serializable;
import java.net.URI;
import java.util.Date;

public abstract interface UserList
  extends Comparable<UserList>, TwitterResponse, Serializable
{
  public abstract Date getCreatedAt();
  
  public abstract String getDescription();
  
  public abstract String getFullName();
  
  public abstract long getId();
  
  public abstract int getMemberCount();
  
  public abstract String getName();
  
  public abstract String getSlug();
  
  public abstract int getSubscriberCount();
  
  public abstract URI getURI();
  
  public abstract User getUser();
  
  public abstract boolean isFollowing();
  
  public abstract boolean isPublic();
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\twitter4j\UserList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */