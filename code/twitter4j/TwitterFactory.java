package twitter4j;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import twitter4j.auth.AccessToken;
import twitter4j.auth.Authorization;
import twitter4j.auth.AuthorizationFactory;
import twitter4j.auth.OAuthAuthorization;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationContext;

public final class TwitterFactory
  implements Serializable
{
  static final Authorization DEFAULT_AUTHORIZATION = AuthorizationFactory.getInstance(ConfigurationContext.getInstance());
  private static final Twitter SINGLETON;
  private static final Constructor<Twitter> TWITTER_CONSTRUCTOR;
  private static final long serialVersionUID = -563983536986910054L;
  private final Configuration conf;
  
  static
  {
    try
    {
      Class.forName("com.google.appengine.api.urlfetch.URLFetchService");
      i = 1;
    }
    catch (ClassNotFoundException localClassNotFoundException1)
    {
      for (;;)
      {
        try
        {
          Object localObject1 = Class.forName((String)localObject3).getDeclaredConstructor(new Class[] { Configuration.class, Authorization.class });
          TWITTER_CONSTRUCTOR = (Constructor)localObject1;
        }
        catch (NoSuchMethodException localNoSuchMethodException)
        {
          int i;
          throw new AssertionError(localNoSuchMethodException);
        }
        catch (ClassNotFoundException localClassNotFoundException2)
        {
          throw new AssertionError(localClassNotFoundException2);
        }
        try
        {
          SINGLETON = (Twitter)TWITTER_CONSTRUCTOR.newInstance(new Object[] { ConfigurationContext.getInstance(), DEFAULT_AUTHORIZATION });
          return;
        }
        catch (InstantiationException localInstantiationException)
        {
          throw new AssertionError(localInstantiationException);
        }
        catch (IllegalAccessException localIllegalAccessException)
        {
          throw new AssertionError(localIllegalAccessException);
        }
        catch (InvocationTargetException localInvocationTargetException)
        {
          throw new AssertionError(localInvocationTargetException);
        }
        localClassNotFoundException1 = localClassNotFoundException1;
        i = 0;
      }
    }
    localObject3 = null;
    localObject1 = localObject3;
    if (i != 0) {}
    try
    {
      Class.forName("twitter4j.AppEngineTwitterImpl");
      localObject1 = "twitter4j.AppEngineTwitterImpl";
    }
    catch (ClassNotFoundException localClassNotFoundException3)
    {
      for (;;)
      {
        Object localObject2 = localObject3;
      }
    }
    localObject3 = localObject1;
    if (localObject1 == null) {
      localObject3 = "twitter4j.TwitterImpl";
    }
  }
  
  public TwitterFactory()
  {
    this(ConfigurationContext.getInstance());
  }
  
  public TwitterFactory(String paramString)
  {
    this(ConfigurationContext.getInstance(paramString));
  }
  
  public TwitterFactory(Configuration paramConfiguration)
  {
    if (paramConfiguration == null) {
      throw new NullPointerException("configuration cannot be null");
    }
    this.conf = paramConfiguration;
  }
  
  public static Twitter getSingleton()
  {
    return SINGLETON;
  }
  
  public Twitter getInstance()
  {
    return getInstance(AuthorizationFactory.getInstance(this.conf));
  }
  
  public Twitter getInstance(AccessToken paramAccessToken)
  {
    Object localObject = this.conf.getOAuthConsumerKey();
    String str = this.conf.getOAuthConsumerSecret();
    if ((localObject == null) && (str == null)) {
      throw new IllegalStateException("Consumer key and Consumer secret not supplied.");
    }
    localObject = new OAuthAuthorization(this.conf);
    ((OAuthAuthorization)localObject).setOAuthAccessToken(paramAccessToken);
    return getInstance((Authorization)localObject);
  }
  
  public Twitter getInstance(Authorization paramAuthorization)
  {
    try
    {
      paramAuthorization = (Twitter)TWITTER_CONSTRUCTOR.newInstance(new Object[] { this.conf, paramAuthorization });
      return paramAuthorization;
    }
    catch (InstantiationException paramAuthorization)
    {
      throw new AssertionError(paramAuthorization);
    }
    catch (IllegalAccessException paramAuthorization)
    {
      throw new AssertionError(paramAuthorization);
    }
    catch (InvocationTargetException paramAuthorization)
    {
      throw new AssertionError(paramAuthorization);
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\twitter4j\TwitterFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */