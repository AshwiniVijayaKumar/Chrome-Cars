package twitter4j;

import java.io.Serializable;
import twitter4j.conf.Configuration;

class AccountSettingsJSONImpl
  extends TwitterResponseImpl
  implements Serializable, AccountSettings
{
  private static final long serialVersionUID = 603189815663175766L;
  private final boolean ALWAYS_USE_HTTPS;
  private final boolean DISCOVERABLE_BY_EMAIL;
  private final boolean GEO_ENABLED;
  private final String LANGUAGE;
  private final String SCREEN_NAME;
  private final String SLEEP_END_TIME;
  private final String SLEEP_START_TIME;
  private final boolean SLEEP_TIME_ENABLED;
  private final TimeZone TIMEZONE;
  private final Location[] TREND_LOCATION;
  
  /* Error */
  private AccountSettingsJSONImpl(HttpResponse paramHttpResponse, JSONObject paramJSONObject)
    throws TwitterException
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: invokespecial 35	twitter4j/TwitterResponseImpl:<init>	(Ltwitter4j/HttpResponse;)V
    //   5: aload_2
    //   6: ldc 37
    //   8: invokevirtual 43	twitter4j/JSONObject:getJSONObject	(Ljava/lang/String;)Ltwitter4j/JSONObject;
    //   11: astore_1
    //   12: aload_0
    //   13: ldc 45
    //   15: aload_1
    //   16: invokestatic 51	twitter4j/ParseUtil:getBoolean	(Ljava/lang/String;Ltwitter4j/JSONObject;)Z
    //   19: putfield 53	twitter4j/AccountSettingsJSONImpl:SLEEP_TIME_ENABLED	Z
    //   22: aload_0
    //   23: aload_1
    //   24: ldc 55
    //   26: invokevirtual 59	twitter4j/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   29: putfield 61	twitter4j/AccountSettingsJSONImpl:SLEEP_START_TIME	Ljava/lang/String;
    //   32: aload_0
    //   33: aload_1
    //   34: ldc 63
    //   36: invokevirtual 59	twitter4j/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   39: putfield 65	twitter4j/AccountSettingsJSONImpl:SLEEP_END_TIME	Ljava/lang/String;
    //   42: aload_2
    //   43: ldc 67
    //   45: invokevirtual 71	twitter4j/JSONObject:isNull	(Ljava/lang/String;)Z
    //   48: ifeq +76 -> 124
    //   51: aload_0
    //   52: iconst_0
    //   53: anewarray 73	twitter4j/Location
    //   56: putfield 75	twitter4j/AccountSettingsJSONImpl:TREND_LOCATION	[Ltwitter4j/Location;
    //   59: aload_0
    //   60: ldc 77
    //   62: aload_2
    //   63: invokestatic 51	twitter4j/ParseUtil:getBoolean	(Ljava/lang/String;Ltwitter4j/JSONObject;)Z
    //   66: putfield 79	twitter4j/AccountSettingsJSONImpl:GEO_ENABLED	Z
    //   69: aload_0
    //   70: aload_2
    //   71: ldc 81
    //   73: invokevirtual 59	twitter4j/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   76: putfield 83	twitter4j/AccountSettingsJSONImpl:LANGUAGE	Ljava/lang/String;
    //   79: aload_0
    //   80: ldc 85
    //   82: aload_2
    //   83: invokestatic 51	twitter4j/ParseUtil:getBoolean	(Ljava/lang/String;Ltwitter4j/JSONObject;)Z
    //   86: putfield 87	twitter4j/AccountSettingsJSONImpl:ALWAYS_USE_HTTPS	Z
    //   89: aload_0
    //   90: ldc 89
    //   92: aload_2
    //   93: invokestatic 51	twitter4j/ParseUtil:getBoolean	(Ljava/lang/String;Ltwitter4j/JSONObject;)Z
    //   96: putfield 91	twitter4j/AccountSettingsJSONImpl:DISCOVERABLE_BY_EMAIL	Z
    //   99: aload_2
    //   100: ldc 93
    //   102: invokevirtual 71	twitter4j/JSONObject:isNull	(Ljava/lang/String;)Z
    //   105: ifeq +72 -> 177
    //   108: aload_0
    //   109: aconst_null
    //   110: putfield 95	twitter4j/AccountSettingsJSONImpl:TIMEZONE	Ltwitter4j/TimeZone;
    //   113: aload_0
    //   114: aload_2
    //   115: ldc 97
    //   117: invokevirtual 59	twitter4j/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   120: putfield 99	twitter4j/AccountSettingsJSONImpl:SCREEN_NAME	Ljava/lang/String;
    //   123: return
    //   124: aload_2
    //   125: ldc 67
    //   127: invokevirtual 103	twitter4j/JSONObject:getJSONArray	(Ljava/lang/String;)Ltwitter4j/JSONArray;
    //   130: astore_1
    //   131: aload_0
    //   132: aload_1
    //   133: invokevirtual 109	twitter4j/JSONArray:length	()I
    //   136: anewarray 73	twitter4j/Location
    //   139: putfield 75	twitter4j/AccountSettingsJSONImpl:TREND_LOCATION	[Ltwitter4j/Location;
    //   142: iconst_0
    //   143: istore_3
    //   144: iload_3
    //   145: aload_1
    //   146: invokevirtual 109	twitter4j/JSONArray:length	()I
    //   149: if_icmpge -90 -> 59
    //   152: aload_0
    //   153: getfield 75	twitter4j/AccountSettingsJSONImpl:TREND_LOCATION	[Ltwitter4j/Location;
    //   156: iload_3
    //   157: new 111	twitter4j/LocationJSONImpl
    //   160: dup
    //   161: aload_1
    //   162: iload_3
    //   163: invokevirtual 114	twitter4j/JSONArray:getJSONObject	(I)Ltwitter4j/JSONObject;
    //   166: invokespecial 117	twitter4j/LocationJSONImpl:<init>	(Ltwitter4j/JSONObject;)V
    //   169: aastore
    //   170: iload_3
    //   171: iconst_1
    //   172: iadd
    //   173: istore_3
    //   174: goto -30 -> 144
    //   177: aload_0
    //   178: new 119	twitter4j/TimeZoneJSONImpl
    //   181: dup
    //   182: aload_2
    //   183: ldc 93
    //   185: invokevirtual 43	twitter4j/JSONObject:getJSONObject	(Ljava/lang/String;)Ltwitter4j/JSONObject;
    //   188: invokespecial 120	twitter4j/TimeZoneJSONImpl:<init>	(Ltwitter4j/JSONObject;)V
    //   191: putfield 95	twitter4j/AccountSettingsJSONImpl:TIMEZONE	Ltwitter4j/TimeZone;
    //   194: goto -81 -> 113
    //   197: astore_1
    //   198: new 30	twitter4j/TwitterException
    //   201: dup
    //   202: aload_1
    //   203: invokespecial 123	twitter4j/TwitterException:<init>	(Ljava/lang/Exception;)V
    //   206: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	207	0	this	AccountSettingsJSONImpl
    //   0	207	1	paramHttpResponse	HttpResponse
    //   0	207	2	paramJSONObject	JSONObject
    //   143	31	3	i	int
    // Exception table:
    //   from	to	target	type
    //   5	59	197	twitter4j/JSONException
    //   59	113	197	twitter4j/JSONException
    //   113	123	197	twitter4j/JSONException
    //   124	142	197	twitter4j/JSONException
    //   144	170	197	twitter4j/JSONException
    //   177	194	197	twitter4j/JSONException
  }
  
  AccountSettingsJSONImpl(HttpResponse paramHttpResponse, Configuration paramConfiguration)
    throws TwitterException
  {
    this(paramHttpResponse, paramHttpResponse.asJSONObject());
    if (paramConfiguration.isJSONStoreEnabled())
    {
      TwitterObjectFactory.clearThreadLocalMap();
      TwitterObjectFactory.registerJSONObject(this, paramHttpResponse.asJSONObject());
    }
  }
  
  AccountSettingsJSONImpl(JSONObject paramJSONObject)
    throws TwitterException
  {
    this(null, paramJSONObject);
  }
  
  public String getLanguage()
  {
    return this.LANGUAGE;
  }
  
  public String getScreenName()
  {
    return this.SCREEN_NAME;
  }
  
  public String getSleepEndTime()
  {
    return this.SLEEP_END_TIME;
  }
  
  public String getSleepStartTime()
  {
    return this.SLEEP_START_TIME;
  }
  
  public TimeZone getTimeZone()
  {
    return this.TIMEZONE;
  }
  
  public Location[] getTrendLocations()
  {
    return this.TREND_LOCATION;
  }
  
  public boolean isAlwaysUseHttps()
  {
    return this.ALWAYS_USE_HTTPS;
  }
  
  public boolean isDiscoverableByEmail()
  {
    return this.DISCOVERABLE_BY_EMAIL;
  }
  
  public boolean isGeoEnabled()
  {
    return this.GEO_ENABLED;
  }
  
  public boolean isSleepTimeEnabled()
  {
    return this.SLEEP_TIME_ENABLED;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\twitter4j\AccountSettingsJSONImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */