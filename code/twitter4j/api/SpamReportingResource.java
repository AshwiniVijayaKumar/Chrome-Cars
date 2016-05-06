package twitter4j.api;

import twitter4j.TwitterException;
import twitter4j.User;

public abstract interface SpamReportingResource
{
  public abstract User reportSpam(long paramLong)
    throws TwitterException;
  
  public abstract User reportSpam(String paramString)
    throws TwitterException;
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\twitter4j\api\SpamReportingResource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */