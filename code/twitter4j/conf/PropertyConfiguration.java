package twitter4j.conf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public final class PropertyConfiguration
  extends ConfigurationBase
  implements Serializable
{
  private static final String APPLICATION_ONLY_AUTH_ENABLED = "enableApplicationOnlyAuth";
  private static final String ASYNC_DAEMON_ENABLED = "async.daemonEnabled";
  private static final String ASYNC_DISPATCHER_IMPL = "async.dispatcherImpl";
  private static final String ASYNC_NUM_THREADS = "async.numThreads";
  private static final String CONTRIBUTING_TO = "contributingTo";
  private static final String DEBUG = "debug";
  private static final String HTTP_CONNECTION_TIMEOUT = "http.connectionTimeout";
  private static final String HTTP_GZIP = "http.gzip";
  private static final String HTTP_PRETTY_DEBUG = "http.prettyDebug";
  private static final String HTTP_PROXY_HOST = "http.proxyHost";
  private static final String HTTP_PROXY_HOST_FALLBACK = "http.proxyHost";
  private static final String HTTP_PROXY_PASSWORD = "http.proxyPassword";
  private static final String HTTP_PROXY_PORT = "http.proxyPort";
  private static final String HTTP_PROXY_PORT_FALLBACK = "http.proxyPort";
  private static final String HTTP_PROXY_USER = "http.proxyUser";
  private static final String HTTP_READ_TIMEOUT = "http.readTimeout";
  private static final String HTTP_RETRY_COUNT = "http.retryCount";
  private static final String HTTP_RETRY_INTERVAL_SECS = "http.retryIntervalSecs";
  private static final String HTTP_STREAMING_READ_TIMEOUT = "http.streamingReadTimeout";
  private static final String INCLUDE_ENTITIES = "includeEntities";
  private static final String INCLUDE_MY_RETWEET = "includeMyRetweet";
  private static final String JSON_STORE_ENABLED = "jsonStoreEnabled";
  private static final String LOGGER_FACTORY = "loggerFactory";
  private static final String MBEAN_ENABLED = "mbeanEnabled";
  private static final String MEDIA_PROVIDER = "media.provider";
  private static final String MEDIA_PROVIDER_API_KEY = "media.providerAPIKey";
  private static final String MEDIA_PROVIDER_PARAMETERS = "media.providerParameters";
  private static final String OAUTH2_ACCESS_TOKEN = "oauth2.accessToken";
  private static final String OAUTH2_INVALIDATE_TOKEN_URL = "oauth2.invalidateTokenURL";
  private static final String OAUTH2_SCOPE = "oauth2.scope";
  private static final String OAUTH2_TOKEN_TYPE = "oauth2.tokenType";
  private static final String OAUTH2_TOKEN_URL = "oauth2.tokenURL";
  private static final String OAUTH_ACCESS_TOKEN = "oauth.accessToken";
  private static final String OAUTH_ACCESS_TOKEN_SECRET = "oauth.accessTokenSecret";
  private static final String OAUTH_ACCESS_TOKEN_URL = "oauth.accessTokenURL";
  private static final String OAUTH_AUTHENTICATION_URL = "oauth.authenticationURL";
  private static final String OAUTH_AUTHORIZATION_URL = "oauth.authorizationURL";
  private static final String OAUTH_CONSUMER_KEY = "oauth.consumerKey";
  private static final String OAUTH_CONSUMER_SECRET = "oauth.consumerSecret";
  private static final String OAUTH_REQUEST_TOKEN_URL = "oauth.requestTokenURL";
  private static final String PASSWORD = "password";
  private static final String REST_BASE_URL = "restBaseURL";
  private static final String SITE_STREAM_BASE_URL = "siteStreamBaseURL";
  private static final String STREAM_BASE_URL = "streamBaseURL";
  private static final String STREAM_STALL_WARNINGS_ENABLED = "stream.enableStallWarnings";
  private static final String STREAM_USER_REPLIES_ALL = "stream.user.repliesAll";
  private static final String STREAM_USER_WITH_FOLLOWINGS = "stream.user.withFollowings";
  private static final String USER = "user";
  private static final String USER_STREAM_BASE_URL = "userStreamBaseURL";
  private static final long serialVersionUID = -7262615247923693252L;
  private String OAuth2Scope;
  
  PropertyConfiguration()
  {
    this("/");
  }
  
  public PropertyConfiguration(InputStream paramInputStream)
  {
    Properties localProperties = new Properties();
    loadProperties(localProperties, paramInputStream);
    setFieldsWithTreePath(localProperties, "/");
  }
  
  PropertyConfiguration(String paramString)
  {
    try
    {
      localProperties1 = (Properties)System.getProperties().clone();
      try
      {
        Map localMap = System.getenv();
        Iterator localIterator = localMap.keySet().iterator();
        while (localIterator.hasNext())
        {
          String str = (String)localIterator.next();
          localProperties1.setProperty(str, (String)localMap.get(str));
        }
        loadProperties(localProperties1, "." + File.separatorChar + "twitter4j.properties");
      }
      catch (SecurityException localSecurityException2)
      {
        normalize(localProperties1);
      }
    }
    catch (SecurityException localSecurityException1)
    {
      try
      {
        Properties localProperties1;
        loadProperties(localProperties1, new FileInputStream("WEB-INF/twitter4j.properties"));
        setFieldsWithTreePath(localProperties1, paramString);
        return;
        localSecurityException1 = localSecurityException1;
        Properties localProperties2 = new Properties();
      }
      catch (FileNotFoundException localFileNotFoundException)
      {
        for (;;) {}
      }
      catch (SecurityException localSecurityException3)
      {
        for (;;) {}
      }
    }
    loadProperties(localProperties1, Configuration.class.getResourceAsStream("/twitter4j.properties"));
    loadProperties(localProperties1, Configuration.class.getResourceAsStream("/WEB-INF/twitter4j.properties"));
  }
  
  public PropertyConfiguration(Properties paramProperties)
  {
    this(paramProperties, "/");
  }
  
  public PropertyConfiguration(Properties paramProperties, String paramString)
  {
    setFieldsWithTreePath(paramProperties, paramString);
  }
  
  private boolean loadProperties(Properties paramProperties, InputStream paramInputStream)
  {
    try
    {
      paramProperties.load(paramInputStream);
      normalize(paramProperties);
      return true;
    }
    catch (Exception paramProperties) {}
    return false;
  }
  
  /* Error */
  private boolean loadProperties(Properties paramProperties, String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aconst_null
    //   3: astore 4
    //   5: new 241	java/io/File
    //   8: dup
    //   9: aload_2
    //   10: invokespecial 284	java/io/File:<init>	(Ljava/lang/String;)V
    //   13: astore_2
    //   14: aload_2
    //   15: invokevirtual 287	java/io/File:exists	()Z
    //   18: ifeq +39 -> 57
    //   21: aload_2
    //   22: invokevirtual 290	java/io/File:isFile	()Z
    //   25: ifeq +32 -> 57
    //   28: new 271	java/io/FileInputStream
    //   31: dup
    //   32: aload_2
    //   33: invokespecial 293	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   36: astore_2
    //   37: aload_1
    //   38: aload_2
    //   39: invokevirtual 281	java/util/Properties:load	(Ljava/io/InputStream;)V
    //   42: aload_0
    //   43: aload_1
    //   44: invokespecial 230	twitter4j/conf/PropertyConfiguration:normalize	(Ljava/util/Properties;)V
    //   47: aload_2
    //   48: ifnull +7 -> 55
    //   51: aload_2
    //   52: invokevirtual 296	java/io/FileInputStream:close	()V
    //   55: iconst_1
    //   56: ireturn
    //   57: iconst_0
    //   58: ifeq +11 -> 69
    //   61: new 298	java/lang/NullPointerException
    //   64: dup
    //   65: invokespecial 299	java/lang/NullPointerException:<init>	()V
    //   68: athrow
    //   69: iconst_0
    //   70: ireturn
    //   71: astore_1
    //   72: aload 4
    //   74: astore_2
    //   75: aload_2
    //   76: ifnull -7 -> 69
    //   79: aload_2
    //   80: invokevirtual 296	java/io/FileInputStream:close	()V
    //   83: goto -14 -> 69
    //   86: astore_1
    //   87: goto -18 -> 69
    //   90: astore_1
    //   91: aload_3
    //   92: astore_2
    //   93: aload_2
    //   94: ifnull +7 -> 101
    //   97: aload_2
    //   98: invokevirtual 296	java/io/FileInputStream:close	()V
    //   101: aload_1
    //   102: athrow
    //   103: astore_1
    //   104: goto -49 -> 55
    //   107: astore_1
    //   108: goto -39 -> 69
    //   111: astore_2
    //   112: goto -11 -> 101
    //   115: astore_1
    //   116: goto -23 -> 93
    //   119: astore_1
    //   120: goto -45 -> 75
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	123	0	this	PropertyConfiguration
    //   0	123	1	paramProperties	Properties
    //   0	123	2	paramString	String
    //   1	91	3	localObject1	Object
    //   3	70	4	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   5	37	71	java/lang/Exception
    //   79	83	86	java/io/IOException
    //   5	37	90	finally
    //   51	55	103	java/io/IOException
    //   61	69	107	java/io/IOException
    //   97	101	111	java/io/IOException
    //   37	47	115	finally
    //   37	47	119	java/lang/Exception
  }
  
  private void normalize(Properties paramProperties)
  {
    Object localObject2 = paramProperties.keySet();
    Object localObject1 = new ArrayList(10);
    localObject2 = ((Set)localObject2).iterator();
    String str;
    while (((Iterator)localObject2).hasNext())
    {
      str = (String)((Iterator)localObject2).next();
      if (-1 != str.indexOf("twitter4j.")) {
        ((ArrayList)localObject1).add(str);
      }
    }
    localObject1 = ((ArrayList)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (String)((Iterator)localObject1).next();
      str = paramProperties.getProperty((String)localObject2);
      int i = ((String)localObject2).indexOf("twitter4j.");
      paramProperties.setProperty(((String)localObject2).substring(0, i) + ((String)localObject2).substring(i + 10), str);
    }
  }
  
  private boolean notNull(Properties paramProperties, String paramString1, String paramString2)
  {
    return paramProperties.getProperty(paramString1 + paramString2) != null;
  }
  
  private void setFieldsWithPrefix(Properties paramProperties, String paramString)
  {
    if (notNull(paramProperties, paramString, "debug")) {
      setDebug(getBoolean(paramProperties, paramString, "debug"));
    }
    if (notNull(paramProperties, paramString, "user")) {
      setUser(getString(paramProperties, paramString, "user"));
    }
    if (notNull(paramProperties, paramString, "password")) {
      setPassword(getString(paramProperties, paramString, "password"));
    }
    if (notNull(paramProperties, paramString, "http.prettyDebug")) {
      setPrettyDebugEnabled(getBoolean(paramProperties, paramString, "http.prettyDebug"));
    }
    if (notNull(paramProperties, paramString, "http.gzip")) {
      setGZIPEnabled(getBoolean(paramProperties, paramString, "http.gzip"));
    }
    if (notNull(paramProperties, paramString, "http.proxyHost"))
    {
      setHttpProxyHost(getString(paramProperties, paramString, "http.proxyHost"));
      if (notNull(paramProperties, paramString, "http.proxyUser")) {
        setHttpProxyUser(getString(paramProperties, paramString, "http.proxyUser"));
      }
      if (notNull(paramProperties, paramString, "http.proxyPassword")) {
        setHttpProxyPassword(getString(paramProperties, paramString, "http.proxyPassword"));
      }
      if (!notNull(paramProperties, paramString, "http.proxyPort")) {
        break label1161;
      }
      setHttpProxyPort(getIntProperty(paramProperties, paramString, "http.proxyPort"));
    }
    for (;;)
    {
      if (notNull(paramProperties, paramString, "http.connectionTimeout")) {
        setHttpConnectionTimeout(getIntProperty(paramProperties, paramString, "http.connectionTimeout"));
      }
      if (notNull(paramProperties, paramString, "http.readTimeout")) {
        setHttpReadTimeout(getIntProperty(paramProperties, paramString, "http.readTimeout"));
      }
      if (notNull(paramProperties, paramString, "http.streamingReadTimeout")) {
        setHttpStreamingReadTimeout(getIntProperty(paramProperties, paramString, "http.streamingReadTimeout"));
      }
      if (notNull(paramProperties, paramString, "http.retryCount")) {
        setHttpRetryCount(getIntProperty(paramProperties, paramString, "http.retryCount"));
      }
      if (notNull(paramProperties, paramString, "http.retryIntervalSecs")) {
        setHttpRetryIntervalSeconds(getIntProperty(paramProperties, paramString, "http.retryIntervalSecs"));
      }
      if (notNull(paramProperties, paramString, "oauth.consumerKey")) {
        setOAuthConsumerKey(getString(paramProperties, paramString, "oauth.consumerKey"));
      }
      if (notNull(paramProperties, paramString, "oauth.consumerSecret")) {
        setOAuthConsumerSecret(getString(paramProperties, paramString, "oauth.consumerSecret"));
      }
      if (notNull(paramProperties, paramString, "oauth.accessToken")) {
        setOAuthAccessToken(getString(paramProperties, paramString, "oauth.accessToken"));
      }
      if (notNull(paramProperties, paramString, "oauth.accessTokenSecret")) {
        setOAuthAccessTokenSecret(getString(paramProperties, paramString, "oauth.accessTokenSecret"));
      }
      if (notNull(paramProperties, paramString, "oauth2.tokenType")) {
        setOAuth2TokenType(getString(paramProperties, paramString, "oauth2.tokenType"));
      }
      if (notNull(paramProperties, paramString, "oauth2.accessToken")) {
        setOAuth2AccessToken(getString(paramProperties, paramString, "oauth2.accessToken"));
      }
      if (notNull(paramProperties, paramString, "oauth2.scope")) {
        setOAuth2Scope(getString(paramProperties, paramString, "oauth2.scope"));
      }
      if (notNull(paramProperties, paramString, "async.numThreads")) {
        setAsyncNumThreads(getIntProperty(paramProperties, paramString, "async.numThreads"));
      }
      if (notNull(paramProperties, paramString, "async.daemonEnabled")) {
        setDaemonEnabled(getBoolean(paramProperties, paramString, "async.daemonEnabled"));
      }
      if (notNull(paramProperties, paramString, "contributingTo")) {
        setContributingTo(getLongProperty(paramProperties, paramString, "contributingTo"));
      }
      if (notNull(paramProperties, paramString, "async.dispatcherImpl")) {
        setDispatcherImpl(getString(paramProperties, paramString, "async.dispatcherImpl"));
      }
      if (notNull(paramProperties, paramString, "oauth.requestTokenURL")) {
        setOAuthRequestTokenURL(getString(paramProperties, paramString, "oauth.requestTokenURL"));
      }
      if (notNull(paramProperties, paramString, "oauth.authorizationURL")) {
        setOAuthAuthorizationURL(getString(paramProperties, paramString, "oauth.authorizationURL"));
      }
      if (notNull(paramProperties, paramString, "oauth.accessTokenURL")) {
        setOAuthAccessTokenURL(getString(paramProperties, paramString, "oauth.accessTokenURL"));
      }
      if (notNull(paramProperties, paramString, "oauth.authenticationURL")) {
        setOAuthAuthenticationURL(getString(paramProperties, paramString, "oauth.authenticationURL"));
      }
      if (notNull(paramProperties, paramString, "oauth2.tokenURL")) {
        setOAuth2TokenURL(getString(paramProperties, paramString, "oauth2.tokenURL"));
      }
      if (notNull(paramProperties, paramString, "oauth2.invalidateTokenURL")) {
        setOAuth2InvalidateTokenURL(getString(paramProperties, paramString, "oauth2.invalidateTokenURL"));
      }
      if (notNull(paramProperties, paramString, "restBaseURL")) {
        setRestBaseURL(getString(paramProperties, paramString, "restBaseURL"));
      }
      if (notNull(paramProperties, paramString, "streamBaseURL")) {
        setStreamBaseURL(getString(paramProperties, paramString, "streamBaseURL"));
      }
      if (notNull(paramProperties, paramString, "userStreamBaseURL")) {
        setUserStreamBaseURL(getString(paramProperties, paramString, "userStreamBaseURL"));
      }
      if (notNull(paramProperties, paramString, "siteStreamBaseURL")) {
        setSiteStreamBaseURL(getString(paramProperties, paramString, "siteStreamBaseURL"));
      }
      if (notNull(paramProperties, paramString, "includeMyRetweet")) {
        setIncludeMyRetweetEnabled(getBoolean(paramProperties, paramString, "includeMyRetweet"));
      }
      if (notNull(paramProperties, paramString, "includeEntities")) {
        setIncludeEntitiesEnabled(getBoolean(paramProperties, paramString, "includeEntities"));
      }
      if (notNull(paramProperties, paramString, "loggerFactory")) {
        setLoggerFactory(getString(paramProperties, paramString, "loggerFactory"));
      }
      if (notNull(paramProperties, paramString, "jsonStoreEnabled")) {
        setJSONStoreEnabled(getBoolean(paramProperties, paramString, "jsonStoreEnabled"));
      }
      if (notNull(paramProperties, paramString, "mbeanEnabled")) {
        setMBeanEnabled(getBoolean(paramProperties, paramString, "mbeanEnabled"));
      }
      if (notNull(paramProperties, paramString, "stream.user.repliesAll")) {
        setUserStreamRepliesAllEnabled(getBoolean(paramProperties, paramString, "stream.user.repliesAll"));
      }
      if (notNull(paramProperties, paramString, "stream.user.withFollowings")) {
        setUserStreamWithFollowingsEnabled(getBoolean(paramProperties, paramString, "stream.user.withFollowings"));
      }
      if (notNull(paramProperties, paramString, "stream.enableStallWarnings")) {
        setStallWarningsEnabled(getBoolean(paramProperties, paramString, "stream.enableStallWarnings"));
      }
      if (notNull(paramProperties, paramString, "enableApplicationOnlyAuth")) {
        setApplicationOnlyAuthEnabled(getBoolean(paramProperties, paramString, "enableApplicationOnlyAuth"));
      }
      if (notNull(paramProperties, paramString, "media.provider")) {
        setMediaProvider(getString(paramProperties, paramString, "media.provider"));
      }
      if (notNull(paramProperties, paramString, "media.providerAPIKey")) {
        setMediaProviderAPIKey(getString(paramProperties, paramString, "media.providerAPIKey"));
      }
      if (!notNull(paramProperties, paramString, "media.providerParameters")) {
        break label1192;
      }
      paramProperties = getString(paramProperties, paramString, "media.providerParameters").split("&");
      paramString = new Properties();
      int j = paramProperties.length;
      int i = 0;
      while (i < j)
      {
        String[] arrayOfString = paramProperties[i].split("=");
        paramString.setProperty(arrayOfString[0], arrayOfString[1]);
        i += 1;
      }
      if (!notNull(paramProperties, paramString, "http.proxyHost")) {
        break;
      }
      setHttpProxyHost(getString(paramProperties, paramString, "http.proxyHost"));
      break;
      label1161:
      if (notNull(paramProperties, paramString, "http.proxyPort")) {
        setHttpProxyPort(getIntProperty(paramProperties, paramString, "http.proxyPort"));
      }
    }
    setMediaProviderParameters(paramString);
    label1192:
    cacheInstance();
  }
  
  private void setFieldsWithTreePath(Properties paramProperties, String paramString)
  {
    setFieldsWithPrefix(paramProperties, "");
    String[] arrayOfString = paramString.split("/");
    paramString = null;
    int j = arrayOfString.length;
    int i = 0;
    if (i < j)
    {
      String str2 = arrayOfString[i];
      String str1 = paramString;
      if (!"".equals(str2)) {
        if (paramString != null) {
          break label95;
        }
      }
      label95:
      for (paramString = str2 + ".";; paramString = paramString + str2 + ".")
      {
        setFieldsWithPrefix(paramProperties, paramString);
        str1 = paramString;
        i += 1;
        paramString = str1;
        break;
      }
    }
  }
  
  boolean getBoolean(Properties paramProperties, String paramString1, String paramString2)
  {
    return Boolean.valueOf(paramProperties.getProperty(paramString1 + paramString2)).booleanValue();
  }
  
  int getIntProperty(Properties paramProperties, String paramString1, String paramString2)
  {
    paramProperties = paramProperties.getProperty(paramString1 + paramString2);
    try
    {
      int i = Integer.parseInt(paramProperties);
      return i;
    }
    catch (NumberFormatException paramProperties) {}
    return -1;
  }
  
  long getLongProperty(Properties paramProperties, String paramString1, String paramString2)
  {
    paramProperties = paramProperties.getProperty(paramString1 + paramString2);
    try
    {
      long l = Long.parseLong(paramProperties);
      return l;
    }
    catch (NumberFormatException paramProperties) {}
    return -1L;
  }
  
  String getString(Properties paramProperties, String paramString1, String paramString2)
  {
    return paramProperties.getProperty(paramString1 + paramString2);
  }
  
  protected Object readResolve()
    throws ObjectStreamException
  {
    return super.readResolve();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\twitter4j\conf\PropertyConfiguration.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */