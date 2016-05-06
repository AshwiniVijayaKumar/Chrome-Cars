package twitter4j.auth;

import twitter4j.TwitterException;

public abstract interface OAuth2Support
{
  public abstract OAuth2Token getOAuth2Token()
    throws TwitterException;
  
  public abstract void invalidateOAuth2Token()
    throws TwitterException;
  
  public abstract void setOAuth2Token(OAuth2Token paramOAuth2Token);
  
  public abstract void setOAuthConsumer(String paramString1, String paramString2);
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\twitter4j\auth\OAuth2Support.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */