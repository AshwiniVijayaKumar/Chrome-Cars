package twitter4j;

import java.io.Serializable;

public abstract interface UserMentionEntity
  extends Serializable, TweetEntity
{
  public abstract int getEnd();
  
  public abstract long getId();
  
  public abstract String getName();
  
  public abstract String getScreenName();
  
  public abstract int getStart();
  
  public abstract String getText();
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\twitter4j\UserMentionEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */