package twitter4j;

import java.util.List;

public abstract interface ResponseList<T>
  extends TwitterResponse, List<T>
{
  public abstract RateLimitStatus getRateLimitStatus();
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\twitter4j\ResponseList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */