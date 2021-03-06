package twitter4j.auth;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import twitter4j.BASE64Encoder;
import twitter4j.HttpClient;
import twitter4j.HttpClientFactory;
import twitter4j.HttpParameter;
import twitter4j.HttpRequest;
import twitter4j.HttpResponse;
import twitter4j.TwitterException;
import twitter4j.conf.Configuration;

public class OAuth2Authorization
  implements Serializable, Authorization, OAuth2Support
{
  private static final long serialVersionUID = -2895232598422218647L;
  private final Configuration conf;
  private String consumerKey;
  private String consumerSecret;
  private final HttpClient http;
  private OAuth2Token token;
  
  public OAuth2Authorization(Configuration paramConfiguration)
  {
    this.conf = paramConfiguration;
    setOAuthConsumer(paramConfiguration.getOAuthConsumerKey(), paramConfiguration.getOAuthConsumerSecret());
    this.http = HttpClientFactory.getInstance(paramConfiguration.getHttpClientConfiguration());
  }
  
  public boolean equals(Object paramObject)
  {
    if ((paramObject == null) || (!(paramObject instanceof OAuth2Authorization))) {}
    label60:
    do
    {
      do
      {
        do
        {
          return false;
          paramObject = (OAuth2Authorization)paramObject;
          if (this.consumerKey == null) {
            break;
          }
        } while (!this.consumerKey.equals(((OAuth2Authorization)paramObject).consumerKey));
        if (this.consumerSecret == null) {
          break label92;
        }
      } while (!this.consumerSecret.equals(((OAuth2Authorization)paramObject).consumerSecret));
      if (this.token == null) {
        break label101;
      }
    } while (!this.token.equals(((OAuth2Authorization)paramObject).token));
    label92:
    label101:
    while (((OAuth2Authorization)paramObject).token == null)
    {
      return true;
      if (((OAuth2Authorization)paramObject).consumerKey == null) {
        break;
      }
      return false;
      if (((OAuth2Authorization)paramObject).consumerSecret == null) {
        break label60;
      }
      return false;
    }
    return false;
  }
  
  public String getAuthorizationHeader(HttpRequest paramHttpRequest)
  {
    if (this.token == null) {
      paramHttpRequest = "";
    }
    try
    {
      String str = URLEncoder.encode(this.consumerKey, "UTF-8") + ":" + URLEncoder.encode(this.consumerSecret, "UTF-8");
      paramHttpRequest = str;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      for (;;) {}
    }
    return "Basic " + BASE64Encoder.encode(paramHttpRequest.getBytes());
    return this.token.generateAuthorizationHeader();
  }
  
  public OAuth2Token getOAuth2Token()
    throws TwitterException
  {
    if (this.token != null) {
      throw new IllegalStateException("OAuth 2 Bearer Token is already available.");
    }
    if (this.conf.getOAuth2Scope() == null) {}
    Object localObject;
    for (int i = 1;; i = 2)
    {
      localObject = new HttpParameter[i];
      localObject[0] = new HttpParameter("grant_type", "client_credentials");
      if (this.conf.getOAuth2Scope() != null) {
        localObject[1] = new HttpParameter("scope", this.conf.getOAuth2Scope());
      }
      localObject = this.http.post(this.conf.getOAuth2TokenURL(), (HttpParameter[])localObject, this, null);
      if (((HttpResponse)localObject).getStatusCode() == 200) {
        break;
      }
      throw new TwitterException("Obtaining OAuth 2 Bearer Token failed.", (HttpResponse)localObject);
    }
    this.token = new OAuth2Token((HttpResponse)localObject);
    return this.token;
  }
  
  public int hashCode()
  {
    int k = 0;
    int i;
    if (this.consumerKey != null)
    {
      i = this.consumerKey.hashCode();
      if (this.consumerSecret == null) {
        break label64;
      }
    }
    label64:
    for (int j = this.consumerSecret.hashCode();; j = 0)
    {
      if (this.token != null) {
        k = this.token.hashCode();
      }
      return (i * 31 + j) * 31 + k;
      i = 0;
      break;
    }
  }
  
  public void invalidateOAuth2Token()
    throws TwitterException
  {
    if (this.token == null) {
      throw new IllegalStateException("OAuth 2 Bearer Token is not available.");
    }
    Object localObject1 = new HttpParameter("access_token", this.token.getAccessToken());
    OAuth2Token localOAuth2Token = this.token;
    try
    {
      this.token = null;
      localObject1 = this.http.post(this.conf.getOAuth2InvalidateTokenURL(), new HttpParameter[] { localObject1 }, this, null);
      if (((HttpResponse)localObject1).getStatusCode() != 200) {
        throw new TwitterException("Invalidating OAuth 2 Bearer Token failed.", (HttpResponse)localObject1);
      }
    }
    finally
    {
      if (0 == 0) {
        this.token = localOAuth2Token;
      }
    }
    if (1 == 0) {
      this.token = localOAuth2Token;
    }
  }
  
  public boolean isEnabled()
  {
    return this.token != null;
  }
  
  public void setOAuth2Token(OAuth2Token paramOAuth2Token)
  {
    this.token = paramOAuth2Token;
  }
  
  public void setOAuthConsumer(String paramString1, String paramString2)
  {
    if (paramString1 != null)
    {
      this.consumerKey = paramString1;
      if (paramString2 == null) {
        break label25;
      }
    }
    for (;;)
    {
      this.consumerSecret = paramString2;
      return;
      paramString1 = "";
      break;
      label25:
      paramString2 = "";
    }
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder().append("OAuth2Authorization{consumerKey='").append(this.consumerKey).append('\'').append(", consumerSecret='******************************************'").append(", token=");
    if (this.token == null) {}
    for (String str = "null";; str = this.token.toString()) {
      return str + '}';
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\twitter4j\auth\OAuth2Authorization.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */