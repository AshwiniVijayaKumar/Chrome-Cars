package twitter4j;

import java.io.Serializable;

public abstract interface TwitterResponse
  extends Serializable
{
  public static final int NONE = 0;
  public static final int READ = 1;
  public static final int READ_WRITE = 2;
  public static final int READ_WRITE_DIRECTMESSAGES = 3;
  
  public abstract int getAccessLevel();
  
  public abstract RateLimitStatus getRateLimitStatus();
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\twitter4j\TwitterResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */