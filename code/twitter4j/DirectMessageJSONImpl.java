package twitter4j;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import twitter4j.conf.Configuration;

final class DirectMessageJSONImpl
  extends TwitterResponseImpl
  implements Serializable, DirectMessage
{
  private static final long serialVersionUID = 7092906238192790921L;
  private Date createdAt;
  private MediaEntity[] extendedMediaEntities;
  private HashtagEntity[] hashtagEntities;
  private long id;
  private MediaEntity[] mediaEntities;
  private User recipient;
  private long recipientId;
  private String recipientScreenName;
  private User sender;
  private long senderId;
  private String senderScreenName;
  private SymbolEntity[] symbolEntities;
  private String text;
  private URLEntity[] urlEntities;
  private UserMentionEntity[] userMentionEntities;
  
  DirectMessageJSONImpl(HttpResponse paramHttpResponse, Configuration paramConfiguration)
    throws TwitterException
  {
    super(paramHttpResponse);
    paramHttpResponse = paramHttpResponse.asJSONObject();
    init(paramHttpResponse);
    if (paramConfiguration.isJSONStoreEnabled())
    {
      TwitterObjectFactory.clearThreadLocalMap();
      TwitterObjectFactory.registerJSONObject(this, paramHttpResponse);
    }
  }
  
  DirectMessageJSONImpl(JSONObject paramJSONObject)
    throws TwitterException
  {
    init(paramJSONObject);
  }
  
  static ResponseList<DirectMessage> createDirectMessageList(HttpResponse paramHttpResponse, Configuration paramConfiguration)
    throws TwitterException
  {
    for (;;)
    {
      int i;
      try
      {
        if (paramConfiguration.isJSONStoreEnabled()) {
          TwitterObjectFactory.clearThreadLocalMap();
        }
        JSONArray localJSONArray = paramHttpResponse.asJSONArray();
        int j = localJSONArray.length();
        paramHttpResponse = new ResponseListImpl(j, paramHttpResponse);
        i = 0;
        if (i < j)
        {
          JSONObject localJSONObject = localJSONArray.getJSONObject(i);
          DirectMessageJSONImpl localDirectMessageJSONImpl = new DirectMessageJSONImpl(localJSONObject);
          paramHttpResponse.add(localDirectMessageJSONImpl);
          if (paramConfiguration.isJSONStoreEnabled()) {
            TwitterObjectFactory.registerJSONObject(localDirectMessageJSONImpl, localJSONObject);
          }
        }
        else
        {
          if (paramConfiguration.isJSONStoreEnabled()) {
            TwitterObjectFactory.registerJSONObject(paramHttpResponse, localJSONArray);
          }
          return paramHttpResponse;
        }
      }
      catch (JSONException paramHttpResponse)
      {
        throw new TwitterException(paramHttpResponse);
      }
      i += 1;
    }
  }
  
  /* Error */
  private void init(JSONObject paramJSONObject)
    throws TwitterException
  {
    // Byte code:
    //   0: aload_0
    //   1: ldc 109
    //   3: aload_1
    //   4: invokestatic 115	twitter4j/ParseUtil:getLong	(Ljava/lang/String;Ltwitter4j/JSONObject;)J
    //   7: putfield 117	twitter4j/DirectMessageJSONImpl:id	J
    //   10: aload_0
    //   11: ldc 119
    //   13: aload_1
    //   14: invokestatic 115	twitter4j/ParseUtil:getLong	(Ljava/lang/String;Ltwitter4j/JSONObject;)J
    //   17: putfield 121	twitter4j/DirectMessageJSONImpl:senderId	J
    //   20: aload_0
    //   21: ldc 123
    //   23: aload_1
    //   24: invokestatic 115	twitter4j/ParseUtil:getLong	(Ljava/lang/String;Ltwitter4j/JSONObject;)J
    //   27: putfield 125	twitter4j/DirectMessageJSONImpl:recipientId	J
    //   30: aload_0
    //   31: ldc 127
    //   33: aload_1
    //   34: invokestatic 131	twitter4j/ParseUtil:getDate	(Ljava/lang/String;Ltwitter4j/JSONObject;)Ljava/util/Date;
    //   37: putfield 133	twitter4j/DirectMessageJSONImpl:createdAt	Ljava/util/Date;
    //   40: aload_0
    //   41: ldc -121
    //   43: aload_1
    //   44: invokestatic 139	twitter4j/ParseUtil:getUnescapedString	(Ljava/lang/String;Ltwitter4j/JSONObject;)Ljava/lang/String;
    //   47: putfield 141	twitter4j/DirectMessageJSONImpl:senderScreenName	Ljava/lang/String;
    //   50: aload_0
    //   51: ldc -113
    //   53: aload_1
    //   54: invokestatic 139	twitter4j/ParseUtil:getUnescapedString	(Ljava/lang/String;Ltwitter4j/JSONObject;)Ljava/lang/String;
    //   57: putfield 145	twitter4j/DirectMessageJSONImpl:recipientScreenName	Ljava/lang/String;
    //   60: aload_0
    //   61: new 147	twitter4j/UserJSONImpl
    //   64: dup
    //   65: aload_1
    //   66: ldc -108
    //   68: invokevirtual 153	twitter4j/JSONObject:getJSONObject	(Ljava/lang/String;)Ltwitter4j/JSONObject;
    //   71: invokespecial 154	twitter4j/UserJSONImpl:<init>	(Ltwitter4j/JSONObject;)V
    //   74: putfield 156	twitter4j/DirectMessageJSONImpl:sender	Ltwitter4j/User;
    //   77: aload_0
    //   78: new 147	twitter4j/UserJSONImpl
    //   81: dup
    //   82: aload_1
    //   83: ldc -99
    //   85: invokevirtual 153	twitter4j/JSONObject:getJSONObject	(Ljava/lang/String;)Ltwitter4j/JSONObject;
    //   88: invokespecial 154	twitter4j/UserJSONImpl:<init>	(Ltwitter4j/JSONObject;)V
    //   91: putfield 159	twitter4j/DirectMessageJSONImpl:recipient	Ltwitter4j/User;
    //   94: aload_1
    //   95: ldc -95
    //   97: invokevirtual 165	twitter4j/JSONObject:isNull	(Ljava/lang/String;)Z
    //   100: ifne +341 -> 441
    //   103: aload_1
    //   104: ldc -95
    //   106: invokevirtual 153	twitter4j/JSONObject:getJSONObject	(Ljava/lang/String;)Ltwitter4j/JSONObject;
    //   109: astore 4
    //   111: aload 4
    //   113: ldc -89
    //   115: invokevirtual 165	twitter4j/JSONObject:isNull	(Ljava/lang/String;)Z
    //   118: ifne +59 -> 177
    //   121: aload 4
    //   123: ldc -89
    //   125: invokevirtual 171	twitter4j/JSONObject:getJSONArray	(Ljava/lang/String;)Ltwitter4j/JSONArray;
    //   128: astore 5
    //   130: aload 5
    //   132: invokevirtual 86	twitter4j/JSONArray:length	()I
    //   135: istore_3
    //   136: aload_0
    //   137: iload_3
    //   138: anewarray 173	twitter4j/UserMentionEntity
    //   141: putfield 175	twitter4j/DirectMessageJSONImpl:userMentionEntities	[Ltwitter4j/UserMentionEntity;
    //   144: iconst_0
    //   145: istore_2
    //   146: iload_2
    //   147: iload_3
    //   148: if_icmpge +29 -> 177
    //   151: aload_0
    //   152: getfield 175	twitter4j/DirectMessageJSONImpl:userMentionEntities	[Ltwitter4j/UserMentionEntity;
    //   155: iload_2
    //   156: new 177	twitter4j/UserMentionEntityJSONImpl
    //   159: dup
    //   160: aload 5
    //   162: iload_2
    //   163: invokevirtual 95	twitter4j/JSONArray:getJSONObject	(I)Ltwitter4j/JSONObject;
    //   166: invokespecial 178	twitter4j/UserMentionEntityJSONImpl:<init>	(Ltwitter4j/JSONObject;)V
    //   169: aastore
    //   170: iload_2
    //   171: iconst_1
    //   172: iadd
    //   173: istore_2
    //   174: goto -28 -> 146
    //   177: aload 4
    //   179: ldc -76
    //   181: invokevirtual 165	twitter4j/JSONObject:isNull	(Ljava/lang/String;)Z
    //   184: ifne +59 -> 243
    //   187: aload 4
    //   189: ldc -76
    //   191: invokevirtual 171	twitter4j/JSONObject:getJSONArray	(Ljava/lang/String;)Ltwitter4j/JSONArray;
    //   194: astore 5
    //   196: aload 5
    //   198: invokevirtual 86	twitter4j/JSONArray:length	()I
    //   201: istore_3
    //   202: aload_0
    //   203: iload_3
    //   204: anewarray 182	twitter4j/URLEntity
    //   207: putfield 184	twitter4j/DirectMessageJSONImpl:urlEntities	[Ltwitter4j/URLEntity;
    //   210: iconst_0
    //   211: istore_2
    //   212: iload_2
    //   213: iload_3
    //   214: if_icmpge +29 -> 243
    //   217: aload_0
    //   218: getfield 184	twitter4j/DirectMessageJSONImpl:urlEntities	[Ltwitter4j/URLEntity;
    //   221: iload_2
    //   222: new 186	twitter4j/URLEntityJSONImpl
    //   225: dup
    //   226: aload 5
    //   228: iload_2
    //   229: invokevirtual 95	twitter4j/JSONArray:getJSONObject	(I)Ltwitter4j/JSONObject;
    //   232: invokespecial 187	twitter4j/URLEntityJSONImpl:<init>	(Ltwitter4j/JSONObject;)V
    //   235: aastore
    //   236: iload_2
    //   237: iconst_1
    //   238: iadd
    //   239: istore_2
    //   240: goto -28 -> 212
    //   243: aload 4
    //   245: ldc -67
    //   247: invokevirtual 165	twitter4j/JSONObject:isNull	(Ljava/lang/String;)Z
    //   250: ifne +59 -> 309
    //   253: aload 4
    //   255: ldc -67
    //   257: invokevirtual 171	twitter4j/JSONObject:getJSONArray	(Ljava/lang/String;)Ltwitter4j/JSONArray;
    //   260: astore 5
    //   262: aload 5
    //   264: invokevirtual 86	twitter4j/JSONArray:length	()I
    //   267: istore_3
    //   268: aload_0
    //   269: iload_3
    //   270: anewarray 191	twitter4j/HashtagEntity
    //   273: putfield 193	twitter4j/DirectMessageJSONImpl:hashtagEntities	[Ltwitter4j/HashtagEntity;
    //   276: iconst_0
    //   277: istore_2
    //   278: iload_2
    //   279: iload_3
    //   280: if_icmpge +29 -> 309
    //   283: aload_0
    //   284: getfield 193	twitter4j/DirectMessageJSONImpl:hashtagEntities	[Ltwitter4j/HashtagEntity;
    //   287: iload_2
    //   288: new 195	twitter4j/HashtagEntityJSONImpl
    //   291: dup
    //   292: aload 5
    //   294: iload_2
    //   295: invokevirtual 95	twitter4j/JSONArray:getJSONObject	(I)Ltwitter4j/JSONObject;
    //   298: invokespecial 196	twitter4j/HashtagEntityJSONImpl:<init>	(Ltwitter4j/JSONObject;)V
    //   301: aastore
    //   302: iload_2
    //   303: iconst_1
    //   304: iadd
    //   305: istore_2
    //   306: goto -28 -> 278
    //   309: aload 4
    //   311: ldc -58
    //   313: invokevirtual 165	twitter4j/JSONObject:isNull	(Ljava/lang/String;)Z
    //   316: ifne +59 -> 375
    //   319: aload 4
    //   321: ldc -58
    //   323: invokevirtual 171	twitter4j/JSONObject:getJSONArray	(Ljava/lang/String;)Ltwitter4j/JSONArray;
    //   326: astore 5
    //   328: aload 5
    //   330: invokevirtual 86	twitter4j/JSONArray:length	()I
    //   333: istore_3
    //   334: aload_0
    //   335: iload_3
    //   336: anewarray 200	twitter4j/SymbolEntity
    //   339: putfield 202	twitter4j/DirectMessageJSONImpl:symbolEntities	[Ltwitter4j/SymbolEntity;
    //   342: iconst_0
    //   343: istore_2
    //   344: iload_2
    //   345: iload_3
    //   346: if_icmpge +29 -> 375
    //   349: aload_0
    //   350: getfield 202	twitter4j/DirectMessageJSONImpl:symbolEntities	[Ltwitter4j/SymbolEntity;
    //   353: iload_2
    //   354: new 195	twitter4j/HashtagEntityJSONImpl
    //   357: dup
    //   358: aload 5
    //   360: iload_2
    //   361: invokevirtual 95	twitter4j/JSONArray:getJSONObject	(I)Ltwitter4j/JSONObject;
    //   364: invokespecial 196	twitter4j/HashtagEntityJSONImpl:<init>	(Ltwitter4j/JSONObject;)V
    //   367: aastore
    //   368: iload_2
    //   369: iconst_1
    //   370: iadd
    //   371: istore_2
    //   372: goto -28 -> 344
    //   375: aload 4
    //   377: ldc -52
    //   379: invokevirtual 165	twitter4j/JSONObject:isNull	(Ljava/lang/String;)Z
    //   382: ifne +59 -> 441
    //   385: aload 4
    //   387: ldc -52
    //   389: invokevirtual 171	twitter4j/JSONObject:getJSONArray	(Ljava/lang/String;)Ltwitter4j/JSONArray;
    //   392: astore 4
    //   394: aload 4
    //   396: invokevirtual 86	twitter4j/JSONArray:length	()I
    //   399: istore_3
    //   400: aload_0
    //   401: iload_3
    //   402: anewarray 206	twitter4j/MediaEntity
    //   405: putfield 208	twitter4j/DirectMessageJSONImpl:mediaEntities	[Ltwitter4j/MediaEntity;
    //   408: iconst_0
    //   409: istore_2
    //   410: iload_2
    //   411: iload_3
    //   412: if_icmpge +29 -> 441
    //   415: aload_0
    //   416: getfield 208	twitter4j/DirectMessageJSONImpl:mediaEntities	[Ltwitter4j/MediaEntity;
    //   419: iload_2
    //   420: new 210	twitter4j/MediaEntityJSONImpl
    //   423: dup
    //   424: aload 4
    //   426: iload_2
    //   427: invokevirtual 95	twitter4j/JSONArray:getJSONObject	(I)Ltwitter4j/JSONObject;
    //   430: invokespecial 211	twitter4j/MediaEntityJSONImpl:<init>	(Ltwitter4j/JSONObject;)V
    //   433: aastore
    //   434: iload_2
    //   435: iconst_1
    //   436: iadd
    //   437: istore_2
    //   438: goto -28 -> 410
    //   441: aload_0
    //   442: getfield 175	twitter4j/DirectMessageJSONImpl:userMentionEntities	[Ltwitter4j/UserMentionEntity;
    //   445: ifnonnull +140 -> 585
    //   448: iconst_0
    //   449: anewarray 173	twitter4j/UserMentionEntity
    //   452: astore 4
    //   454: aload_0
    //   455: aload 4
    //   457: putfield 175	twitter4j/DirectMessageJSONImpl:userMentionEntities	[Ltwitter4j/UserMentionEntity;
    //   460: aload_0
    //   461: getfield 184	twitter4j/DirectMessageJSONImpl:urlEntities	[Ltwitter4j/URLEntity;
    //   464: ifnonnull +130 -> 594
    //   467: iconst_0
    //   468: anewarray 182	twitter4j/URLEntity
    //   471: astore 4
    //   473: aload_0
    //   474: aload 4
    //   476: putfield 184	twitter4j/DirectMessageJSONImpl:urlEntities	[Ltwitter4j/URLEntity;
    //   479: aload_0
    //   480: getfield 193	twitter4j/DirectMessageJSONImpl:hashtagEntities	[Ltwitter4j/HashtagEntity;
    //   483: ifnonnull +120 -> 603
    //   486: iconst_0
    //   487: anewarray 191	twitter4j/HashtagEntity
    //   490: astore 4
    //   492: aload_0
    //   493: aload 4
    //   495: putfield 193	twitter4j/DirectMessageJSONImpl:hashtagEntities	[Ltwitter4j/HashtagEntity;
    //   498: aload_0
    //   499: getfield 202	twitter4j/DirectMessageJSONImpl:symbolEntities	[Ltwitter4j/SymbolEntity;
    //   502: ifnonnull +110 -> 612
    //   505: iconst_0
    //   506: anewarray 200	twitter4j/SymbolEntity
    //   509: astore 4
    //   511: aload_0
    //   512: aload 4
    //   514: putfield 202	twitter4j/DirectMessageJSONImpl:symbolEntities	[Ltwitter4j/SymbolEntity;
    //   517: aload_0
    //   518: getfield 208	twitter4j/DirectMessageJSONImpl:mediaEntities	[Ltwitter4j/MediaEntity;
    //   521: ifnonnull +100 -> 621
    //   524: iconst_0
    //   525: anewarray 206	twitter4j/MediaEntity
    //   528: astore 4
    //   530: aload_0
    //   531: aload 4
    //   533: putfield 208	twitter4j/DirectMessageJSONImpl:mediaEntities	[Ltwitter4j/MediaEntity;
    //   536: aload_0
    //   537: getfield 213	twitter4j/DirectMessageJSONImpl:extendedMediaEntities	[Ltwitter4j/MediaEntity;
    //   540: ifnonnull +90 -> 630
    //   543: iconst_0
    //   544: anewarray 206	twitter4j/MediaEntity
    //   547: astore 4
    //   549: aload_0
    //   550: aload 4
    //   552: putfield 213	twitter4j/DirectMessageJSONImpl:extendedMediaEntities	[Ltwitter4j/MediaEntity;
    //   555: aload_0
    //   556: aload_1
    //   557: ldc -42
    //   559: invokevirtual 218	twitter4j/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   562: aload_0
    //   563: getfield 175	twitter4j/DirectMessageJSONImpl:userMentionEntities	[Ltwitter4j/UserMentionEntity;
    //   566: aload_0
    //   567: getfield 184	twitter4j/DirectMessageJSONImpl:urlEntities	[Ltwitter4j/URLEntity;
    //   570: aload_0
    //   571: getfield 193	twitter4j/DirectMessageJSONImpl:hashtagEntities	[Ltwitter4j/HashtagEntity;
    //   574: aload_0
    //   575: getfield 208	twitter4j/DirectMessageJSONImpl:mediaEntities	[Ltwitter4j/MediaEntity;
    //   578: invokestatic 224	twitter4j/HTMLEntity:unescapeAndSlideEntityIncdices	(Ljava/lang/String;[Ltwitter4j/UserMentionEntity;[Ltwitter4j/URLEntity;[Ltwitter4j/HashtagEntity;[Ltwitter4j/MediaEntity;)Ljava/lang/String;
    //   581: putfield 226	twitter4j/DirectMessageJSONImpl:text	Ljava/lang/String;
    //   584: return
    //   585: aload_0
    //   586: getfield 175	twitter4j/DirectMessageJSONImpl:userMentionEntities	[Ltwitter4j/UserMentionEntity;
    //   589: astore 4
    //   591: goto -137 -> 454
    //   594: aload_0
    //   595: getfield 184	twitter4j/DirectMessageJSONImpl:urlEntities	[Ltwitter4j/URLEntity;
    //   598: astore 4
    //   600: goto -127 -> 473
    //   603: aload_0
    //   604: getfield 193	twitter4j/DirectMessageJSONImpl:hashtagEntities	[Ltwitter4j/HashtagEntity;
    //   607: astore 4
    //   609: goto -117 -> 492
    //   612: aload_0
    //   613: getfield 202	twitter4j/DirectMessageJSONImpl:symbolEntities	[Ltwitter4j/SymbolEntity;
    //   616: astore 4
    //   618: goto -107 -> 511
    //   621: aload_0
    //   622: getfield 208	twitter4j/DirectMessageJSONImpl:mediaEntities	[Ltwitter4j/MediaEntity;
    //   625: astore 4
    //   627: goto -97 -> 530
    //   630: aload_0
    //   631: getfield 213	twitter4j/DirectMessageJSONImpl:extendedMediaEntities	[Ltwitter4j/MediaEntity;
    //   634: astore 4
    //   636: goto -87 -> 549
    //   639: astore_1
    //   640: new 39	twitter4j/TwitterException
    //   643: dup
    //   644: aload_1
    //   645: invokespecial 106	twitter4j/TwitterException:<init>	(Ljava/lang/Exception;)V
    //   648: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	649	0	this	DirectMessageJSONImpl
    //   0	649	1	paramJSONObject	JSONObject
    //   145	293	2	i	int
    //   135	278	3	j	int
    //   109	526	4	localObject	Object
    //   128	231	5	localJSONArray	JSONArray
    // Exception table:
    //   from	to	target	type
    //   60	144	639	twitter4j/JSONException
    //   151	170	639	twitter4j/JSONException
    //   177	210	639	twitter4j/JSONException
    //   217	236	639	twitter4j/JSONException
    //   243	276	639	twitter4j/JSONException
    //   283	302	639	twitter4j/JSONException
    //   309	342	639	twitter4j/JSONException
    //   349	368	639	twitter4j/JSONException
    //   375	408	639	twitter4j/JSONException
    //   415	434	639	twitter4j/JSONException
    //   441	454	639	twitter4j/JSONException
    //   454	473	639	twitter4j/JSONException
    //   473	492	639	twitter4j/JSONException
    //   492	511	639	twitter4j/JSONException
    //   511	530	639	twitter4j/JSONException
    //   530	549	639	twitter4j/JSONException
    //   549	584	639	twitter4j/JSONException
    //   585	591	639	twitter4j/JSONException
    //   594	600	639	twitter4j/JSONException
    //   603	609	639	twitter4j/JSONException
    //   612	618	639	twitter4j/JSONException
    //   621	627	639	twitter4j/JSONException
    //   630	636	639	twitter4j/JSONException
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = true;
    boolean bool1;
    if (paramObject == null) {
      bool1 = false;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (this == paramObject);
      if (!(paramObject instanceof DirectMessage)) {
        break;
      }
      bool1 = bool2;
    } while (((DirectMessage)paramObject).getId() == this.id);
    return false;
  }
  
  public Date getCreatedAt()
  {
    return this.createdAt;
  }
  
  public MediaEntity[] getExtendedMediaEntities()
  {
    return this.extendedMediaEntities;
  }
  
  public HashtagEntity[] getHashtagEntities()
  {
    return this.hashtagEntities;
  }
  
  public long getId()
  {
    return this.id;
  }
  
  public MediaEntity[] getMediaEntities()
  {
    return this.mediaEntities;
  }
  
  public User getRecipient()
  {
    return this.recipient;
  }
  
  public long getRecipientId()
  {
    return this.recipientId;
  }
  
  public String getRecipientScreenName()
  {
    return this.recipientScreenName;
  }
  
  public User getSender()
  {
    return this.sender;
  }
  
  public long getSenderId()
  {
    return this.senderId;
  }
  
  public String getSenderScreenName()
  {
    return this.senderScreenName;
  }
  
  public SymbolEntity[] getSymbolEntities()
  {
    return this.symbolEntities;
  }
  
  public String getText()
  {
    return this.text;
  }
  
  public URLEntity[] getURLEntities()
  {
    return this.urlEntities;
  }
  
  public UserMentionEntity[] getUserMentionEntities()
  {
    return this.userMentionEntities;
  }
  
  public int hashCode()
  {
    return (int)this.id;
  }
  
  public String toString()
  {
    Object localObject2 = null;
    StringBuilder localStringBuilder = new StringBuilder().append("DirectMessageJSONImpl{id=").append(this.id).append(", text='").append(this.text).append('\'').append(", sender_id=").append(this.senderId).append(", recipient_id=").append(this.recipientId).append(", created_at=").append(this.createdAt).append(", userMentionEntities=");
    if (this.userMentionEntities == null)
    {
      localObject1 = null;
      localStringBuilder = localStringBuilder.append(localObject1).append(", urlEntities=");
      if (this.urlEntities != null) {
        break label287;
      }
      localObject1 = null;
      label116:
      localStringBuilder = localStringBuilder.append(localObject1).append(", hashtagEntities=");
      if (this.hashtagEntities != null) {
        break label298;
      }
      localObject1 = null;
      label137:
      localStringBuilder = localStringBuilder.append(localObject1).append(", sender_screen_name='").append(this.senderScreenName).append('\'').append(", recipient_screen_name='").append(this.recipientScreenName).append('\'').append(", sender=").append(this.sender).append(", recipient=").append(this.recipient).append(", userMentionEntities=");
      if (this.userMentionEntities != null) {
        break label309;
      }
      localObject1 = null;
      label220:
      localStringBuilder = localStringBuilder.append(localObject1).append(", urlEntities=");
      if (this.urlEntities != null) {
        break label320;
      }
      localObject1 = null;
      label241:
      localStringBuilder = localStringBuilder.append(localObject1).append(", hashtagEntities=");
      if (this.hashtagEntities != null) {
        break label331;
      }
    }
    label287:
    label298:
    label309:
    label320:
    label331:
    for (Object localObject1 = localObject2;; localObject1 = Arrays.asList(this.hashtagEntities))
    {
      return localObject1 + '}';
      localObject1 = Arrays.asList(this.userMentionEntities);
      break;
      localObject1 = Arrays.asList(this.urlEntities);
      break label116;
      localObject1 = Arrays.asList(this.hashtagEntities);
      break label137;
      localObject1 = Arrays.asList(this.userMentionEntities);
      break label220;
      localObject1 = Arrays.asList(this.urlEntities);
      break label241;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\twitter4j\DirectMessageJSONImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */