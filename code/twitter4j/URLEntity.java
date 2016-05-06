package twitter4j;

import java.io.Serializable;

public abstract interface URLEntity
  extends Serializable, TweetEntity
{
  public abstract String getDisplayURL();
  
  public abstract int getEnd();
  
  public abstract String getExpandedURL();
  
  public abstract int getStart();
  
  public abstract String getText();
  
  public abstract String getURL();
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\twitter4j\URLEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */