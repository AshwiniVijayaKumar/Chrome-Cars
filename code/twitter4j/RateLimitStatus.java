package twitter4j;

import java.io.Serializable;

public abstract interface RateLimitStatus
  extends Serializable
{
  public abstract int getLimit();
  
  public abstract int getRemaining();
  
  public abstract int getResetTimeInSeconds();
  
  public abstract int getSecondsUntilReset();
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\twitter4j\RateLimitStatus.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */