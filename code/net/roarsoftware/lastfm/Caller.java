package net.roarsoftware.lastfm;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import net.roarsoftware.lastfm.cache.Cache;
import net.roarsoftware.lastfm.cache.MemoryCache;
import net.roarsoftware.util.StringUtilities;

public class Caller
{
  private static final String DEFAULT_API_ROOT = "http://ws.audioscrobbler.com/2.0/";
  private static final String PARAM_API_KEY = "api_key";
  private static final String PARAM_METHOD = "method";
  private static final Caller instance = new Caller();
  private String apiRootUrl = "http://ws.audioscrobbler.com/2.0/";
  private Cache cache = new MemoryCache();
  private boolean debugMode = true;
  private Result lastResult;
  private Proxy proxy;
  private String userAgent = "tst";
  
  private String buildParameterQueue(String paramString, Map<String, String> paramMap, String... paramVarArgs)
  {
    StringBuilder localStringBuilder = new StringBuilder(100);
    localStringBuilder.append("method=");
    localStringBuilder.append(paramString);
    localStringBuilder.append('&');
    paramString = paramMap.entrySet().iterator();
    while (paramString.hasNext())
    {
      paramMap = (Map.Entry)paramString.next();
      localStringBuilder.append((String)paramMap.getKey());
      localStringBuilder.append('=');
      localStringBuilder.append(StringUtilities.encode((String)paramMap.getValue()));
      if ((paramString.hasNext()) || (paramVarArgs.length > 0)) {
        localStringBuilder.append('&');
      }
    }
    int j = 0;
    int k = paramVarArgs.length;
    int i = 0;
    if (i < k)
    {
      paramString = paramVarArgs[i];
      if (j % 2 == 0)
      {
        label160:
        localStringBuilder.append(paramString);
        j += 1;
        if (j != paramVarArgs.length)
        {
          if (j % 2 != 0) {
            break label212;
          }
          localStringBuilder.append('&');
        }
      }
      for (;;)
      {
        i += 1;
        break;
        paramString = StringUtilities.encode(paramString);
        break label160;
        label212:
        localStringBuilder.append('=');
      }
    }
    return localStringBuilder.toString();
  }
  
  /* Error */
  private Result call(String paramString1, String paramString2, Map<String, String> paramMap, Session paramSession)
  {
    // Byte code:
    //   0: new 116	java/util/HashMap
    //   3: dup
    //   4: aload_3
    //   5: invokespecial 119	java/util/HashMap:<init>	(Ljava/util/Map;)V
    //   8: astore 14
    //   10: aconst_null
    //   11: astore_3
    //   12: aload_1
    //   13: aload 14
    //   15: invokestatic 125	net/roarsoftware/lastfm/cache/Cache:createCacheEntryName	(Ljava/lang/String;Ljava/util/Map;)Ljava/lang/String;
    //   18: astore 13
    //   20: aload_3
    //   21: astore 12
    //   23: aload 4
    //   25: ifnonnull +54 -> 79
    //   28: aload_3
    //   29: astore 12
    //   31: aload_0
    //   32: getfield 48	net/roarsoftware/lastfm/Caller:cache	Lnet/roarsoftware/lastfm/cache/Cache;
    //   35: ifnull +44 -> 79
    //   38: aload_3
    //   39: astore 12
    //   41: aload_0
    //   42: getfield 48	net/roarsoftware/lastfm/Caller:cache	Lnet/roarsoftware/lastfm/cache/Cache;
    //   45: aload 13
    //   47: invokevirtual 129	net/roarsoftware/lastfm/cache/Cache:contains	(Ljava/lang/String;)Z
    //   50: ifeq +29 -> 79
    //   53: aload_3
    //   54: astore 12
    //   56: aload_0
    //   57: getfield 48	net/roarsoftware/lastfm/Caller:cache	Lnet/roarsoftware/lastfm/cache/Cache;
    //   60: aload 13
    //   62: invokevirtual 132	net/roarsoftware/lastfm/cache/Cache:isExpired	(Ljava/lang/String;)Z
    //   65: ifne +14 -> 79
    //   68: aload_0
    //   69: getfield 48	net/roarsoftware/lastfm/Caller:cache	Lnet/roarsoftware/lastfm/cache/Cache;
    //   72: aload 13
    //   74: invokevirtual 136	net/roarsoftware/lastfm/cache/Cache:load	(Ljava/lang/String;)Ljava/io/InputStream;
    //   77: astore 12
    //   79: aload 12
    //   81: astore_3
    //   82: aload 12
    //   84: ifnonnull +180 -> 264
    //   87: aload 14
    //   89: ldc 11
    //   91: aload_2
    //   92: invokeinterface 140 3 0
    //   97: pop
    //   98: aload 4
    //   100: ifnull +39 -> 139
    //   103: aload 14
    //   105: ldc -114
    //   107: aload 4
    //   109: invokevirtual 146	net/roarsoftware/lastfm/Session:getKey	()Ljava/lang/String;
    //   112: invokeinterface 140 3 0
    //   117: pop
    //   118: aload 14
    //   120: ldc -108
    //   122: aload_1
    //   123: aload 14
    //   125: aload 4
    //   127: invokevirtual 151	net/roarsoftware/lastfm/Session:getSecret	()Ljava/lang/String;
    //   130: invokestatic 157	net/roarsoftware/lastfm/Authenticator:createSignature	(Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;)Ljava/lang/String;
    //   133: invokeinterface 140 3 0
    //   138: pop
    //   139: aload_0
    //   140: aload_0
    //   141: getfield 37	net/roarsoftware/lastfm/Caller:apiRootUrl	Ljava/lang/String;
    //   144: invokevirtual 161	net/roarsoftware/lastfm/Caller:openConnection	(Ljava/lang/String;)Ljava/net/HttpURLConnection;
    //   147: astore 4
    //   149: aload 4
    //   151: ldc -93
    //   153: invokevirtual 169	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
    //   156: aload 4
    //   158: iconst_1
    //   159: invokevirtual 173	java/net/HttpURLConnection:setDoOutput	(Z)V
    //   162: new 175	java/io/BufferedWriter
    //   165: dup
    //   166: new 177	java/io/OutputStreamWriter
    //   169: dup
    //   170: aload 4
    //   172: invokevirtual 181	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
    //   175: invokespecial 184	java/io/OutputStreamWriter:<init>	(Ljava/io/OutputStream;)V
    //   178: invokespecial 187	java/io/BufferedWriter:<init>	(Ljava/io/Writer;)V
    //   181: astore_2
    //   182: aload_0
    //   183: aload_1
    //   184: aload 14
    //   186: iconst_0
    //   187: anewarray 93	java/lang/String
    //   190: invokespecial 189	net/roarsoftware/lastfm/Caller:buildParameterQueue	(Ljava/lang/String;Ljava/util/Map;[Ljava/lang/String;)Ljava/lang/String;
    //   193: astore_3
    //   194: aload_0
    //   195: getfield 43	net/roarsoftware/lastfm/Caller:debugMode	Z
    //   198: ifeq +28 -> 226
    //   201: getstatic 195	java/lang/System:out	Ljava/io/PrintStream;
    //   204: new 52	java/lang/StringBuilder
    //   207: dup
    //   208: invokespecial 196	java/lang/StringBuilder:<init>	()V
    //   211: ldc -58
    //   213: invokevirtual 61	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   216: aload_3
    //   217: invokevirtual 61	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   220: invokevirtual 106	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   223: invokevirtual 203	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   226: aload_2
    //   227: aload_3
    //   228: invokevirtual 206	java/io/BufferedWriter:write	(Ljava/lang/String;)V
    //   231: aload_2
    //   232: invokevirtual 209	java/io/BufferedWriter:close	()V
    //   235: aload 4
    //   237: invokevirtual 213	java/net/HttpURLConnection:getResponseCode	()I
    //   240: istore 5
    //   242: iload 5
    //   244: sipush 403
    //   247: if_icmpeq +11 -> 258
    //   250: iload 5
    //   252: sipush 400
    //   255: if_icmpne +174 -> 429
    //   258: aload 4
    //   260: invokevirtual 217	java/net/HttpURLConnection:getErrorStream	()Ljava/io/InputStream;
    //   263: astore_3
    //   264: aload_0
    //   265: invokespecial 221	net/roarsoftware/lastfm/Caller:newDocumentBuilder	()Ljavax/xml/parsers/DocumentBuilder;
    //   268: new 223	org/xml/sax/InputSource
    //   271: dup
    //   272: new 225	java/io/InputStreamReader
    //   275: dup
    //   276: aload_3
    //   277: ldc -29
    //   279: invokespecial 230	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;Ljava/lang/String;)V
    //   282: invokespecial 233	org/xml/sax/InputSource:<init>	(Ljava/io/Reader;)V
    //   285: invokevirtual 239	javax/xml/parsers/DocumentBuilder:parse	(Lorg/xml/sax/InputSource;)Lorg/w3c/dom/Document;
    //   288: astore_2
    //   289: aload_2
    //   290: invokeinterface 245 1 0
    //   295: astore_3
    //   296: ldc -9
    //   298: aload_3
    //   299: ldc -7
    //   301: invokeinterface 254 2 0
    //   306: invokevirtual 258	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   309: ifeq +305 -> 614
    //   312: getstatic 264	net/roarsoftware/lastfm/Result$Status:OK	Lnet/roarsoftware/lastfm/Result$Status;
    //   315: astore_1
    //   316: aload_1
    //   317: getstatic 267	net/roarsoftware/lastfm/Result$Status:FAILED	Lnet/roarsoftware/lastfm/Result$Status;
    //   320: if_acmpne +301 -> 621
    //   323: aload_0
    //   324: getfield 48	net/roarsoftware/lastfm/Caller:cache	Lnet/roarsoftware/lastfm/cache/Cache;
    //   327: ifnull +12 -> 339
    //   330: aload_0
    //   331: getfield 48	net/roarsoftware/lastfm/Caller:cache	Lnet/roarsoftware/lastfm/cache/Cache;
    //   334: aload 13
    //   336: invokevirtual 270	net/roarsoftware/lastfm/cache/Cache:remove	(Ljava/lang/String;)V
    //   339: aload_3
    //   340: ldc_w 272
    //   343: invokeinterface 276 2 0
    //   348: iconst_0
    //   349: invokeinterface 282 2 0
    //   354: checkcast 251	org/w3c/dom/Element
    //   357: astore_1
    //   358: aload_1
    //   359: ldc_w 284
    //   362: invokeinterface 254 2 0
    //   367: invokestatic 290	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   370: istore 5
    //   372: aload_1
    //   373: invokeinterface 293 1 0
    //   378: astore_1
    //   379: aload_0
    //   380: getfield 43	net/roarsoftware/lastfm/Caller:debugMode	Z
    //   383: ifeq +29 -> 412
    //   386: getstatic 296	java/lang/System:err	Ljava/io/PrintStream;
    //   389: ldc_w 298
    //   392: iconst_2
    //   393: anewarray 4	java/lang/Object
    //   396: dup
    //   397: iconst_0
    //   398: iload 5
    //   400: invokestatic 302	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   403: aastore
    //   404: dup
    //   405: iconst_1
    //   406: aload_1
    //   407: aastore
    //   408: invokevirtual 306	java/io/PrintStream:printf	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/io/PrintStream;
    //   411: pop
    //   412: aload_0
    //   413: iload 5
    //   415: aload_1
    //   416: invokestatic 312	net/roarsoftware/lastfm/Result:createRestErrorResult	(ILjava/lang/String;)Lnet/roarsoftware/lastfm/Result;
    //   419: putfield 314	net/roarsoftware/lastfm/Caller:lastResult	Lnet/roarsoftware/lastfm/Result;
    //   422: aload_0
    //   423: getfield 314	net/roarsoftware/lastfm/Caller:lastResult	Lnet/roarsoftware/lastfm/Result;
    //   426: astore_1
    //   427: aload_1
    //   428: areturn
    //   429: iload 5
    //   431: sipush 200
    //   434: if_icmpeq +22 -> 456
    //   437: aload_0
    //   438: iload 5
    //   440: aload 4
    //   442: invokevirtual 317	java/net/HttpURLConnection:getResponseMessage	()Ljava/lang/String;
    //   445: invokestatic 320	net/roarsoftware/lastfm/Result:createHttpErrorResult	(ILjava/lang/String;)Lnet/roarsoftware/lastfm/Result;
    //   448: putfield 314	net/roarsoftware/lastfm/Caller:lastResult	Lnet/roarsoftware/lastfm/Result;
    //   451: aload_0
    //   452: getfield 314	net/roarsoftware/lastfm/Caller:lastResult	Lnet/roarsoftware/lastfm/Result;
    //   455: areturn
    //   456: aload 4
    //   458: invokevirtual 323	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   461: astore_2
    //   462: aload_2
    //   463: astore_3
    //   464: aload_0
    //   465: getfield 48	net/roarsoftware/lastfm/Caller:cache	Lnet/roarsoftware/lastfm/cache/Cache;
    //   468: ifnull -204 -> 264
    //   471: aload 4
    //   473: ldc_w 325
    //   476: ldc2_w 326
    //   479: invokevirtual 331	java/net/HttpURLConnection:getHeaderFieldDate	(Ljava/lang/String;J)J
    //   482: lstore 8
    //   484: lload 8
    //   486: lstore 6
    //   488: lload 8
    //   490: ldc2_w 326
    //   493: lcmp
    //   494: ifne +45 -> 539
    //   497: aload_0
    //   498: getfield 48	net/roarsoftware/lastfm/Caller:cache	Lnet/roarsoftware/lastfm/cache/Cache;
    //   501: invokevirtual 335	net/roarsoftware/lastfm/cache/Cache:getExpirationPolicy	()Lnet/roarsoftware/lastfm/cache/ExpirationPolicy;
    //   504: aload_1
    //   505: aload 14
    //   507: invokeinterface 341 3 0
    //   512: lstore 10
    //   514: lload 8
    //   516: lstore 6
    //   518: lload 10
    //   520: lconst_0
    //   521: lcmp
    //   522: ifle +17 -> 539
    //   525: lload 10
    //   527: ldc2_w 342
    //   530: lcmp
    //   531: ifne +68 -> 599
    //   534: ldc2_w 342
    //   537: lstore 6
    //   539: aload_2
    //   540: astore_3
    //   541: lload 6
    //   543: ldc2_w 326
    //   546: lcmp
    //   547: ifeq -283 -> 264
    //   550: aload_0
    //   551: getfield 48	net/roarsoftware/lastfm/Caller:cache	Lnet/roarsoftware/lastfm/cache/Cache;
    //   554: aload 13
    //   556: aload_2
    //   557: lload 6
    //   559: invokevirtual 347	net/roarsoftware/lastfm/cache/Cache:store	(Ljava/lang/String;Ljava/io/InputStream;J)V
    //   562: aload_0
    //   563: getfield 48	net/roarsoftware/lastfm/Caller:cache	Lnet/roarsoftware/lastfm/cache/Cache;
    //   566: aload 13
    //   568: invokevirtual 136	net/roarsoftware/lastfm/cache/Cache:load	(Ljava/lang/String;)Ljava/io/InputStream;
    //   571: astore_1
    //   572: aload_1
    //   573: astore_3
    //   574: aload_1
    //   575: ifnonnull -311 -> 264
    //   578: new 349	net/roarsoftware/lastfm/CallException
    //   581: dup
    //   582: ldc_w 351
    //   585: invokespecial 353	net/roarsoftware/lastfm/CallException:<init>	(Ljava/lang/String;)V
    //   588: athrow
    //   589: astore_1
    //   590: new 349	net/roarsoftware/lastfm/CallException
    //   593: dup
    //   594: aload_1
    //   595: invokespecial 356	net/roarsoftware/lastfm/CallException:<init>	(Ljava/lang/Throwable;)V
    //   598: athrow
    //   599: invokestatic 360	java/lang/System:currentTimeMillis	()J
    //   602: lstore 6
    //   604: lload 6
    //   606: lload 10
    //   608: ladd
    //   609: lstore 6
    //   611: goto -72 -> 539
    //   614: getstatic 267	net/roarsoftware/lastfm/Result$Status:FAILED	Lnet/roarsoftware/lastfm/Result$Status;
    //   617: astore_1
    //   618: goto -302 -> 316
    //   621: aload_0
    //   622: aload_2
    //   623: invokestatic 364	net/roarsoftware/lastfm/Result:createOkResult	(Lorg/w3c/dom/Document;)Lnet/roarsoftware/lastfm/Result;
    //   626: putfield 314	net/roarsoftware/lastfm/Caller:lastResult	Lnet/roarsoftware/lastfm/Result;
    //   629: goto -207 -> 422
    //   632: astore_1
    //   633: new 349	net/roarsoftware/lastfm/CallException
    //   636: dup
    //   637: aload_1
    //   638: invokespecial 356	net/roarsoftware/lastfm/CallException:<init>	(Ljava/lang/Throwable;)V
    //   641: athrow
    //   642: astore_1
    //   643: new 349	net/roarsoftware/lastfm/CallException
    //   646: dup
    //   647: aload_1
    //   648: invokespecial 356	net/roarsoftware/lastfm/CallException:<init>	(Ljava/lang/Throwable;)V
    //   651: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	652	0	this	Caller
    //   0	652	1	paramString1	String
    //   0	652	2	paramString2	String
    //   0	652	3	paramMap	Map<String, String>
    //   0	652	4	paramSession	Session
    //   240	199	5	i	int
    //   486	124	6	l1	long
    //   482	33	8	l2	long
    //   512	95	10	l3	long
    //   21	62	12	localObject	Object
    //   18	549	13	str	String
    //   8	498	14	localHashMap	java.util.HashMap
    // Exception table:
    //   from	to	target	type
    //   139	226	589	java/io/IOException
    //   226	242	589	java/io/IOException
    //   258	264	589	java/io/IOException
    //   437	456	589	java/io/IOException
    //   456	462	589	java/io/IOException
    //   464	484	589	java/io/IOException
    //   497	514	589	java/io/IOException
    //   550	572	589	java/io/IOException
    //   578	589	589	java/io/IOException
    //   599	604	589	java/io/IOException
    //   264	316	632	java/io/IOException
    //   316	339	632	java/io/IOException
    //   339	412	632	java/io/IOException
    //   412	422	632	java/io/IOException
    //   422	427	632	java/io/IOException
    //   614	618	632	java/io/IOException
    //   621	629	632	java/io/IOException
    //   264	316	642	org/xml/sax/SAXException
    //   316	339	642	org/xml/sax/SAXException
    //   339	412	642	org/xml/sax/SAXException
    //   412	422	642	org/xml/sax/SAXException
    //   422	427	642	org/xml/sax/SAXException
    //   614	618	642	org/xml/sax/SAXException
    //   621	629	642	org/xml/sax/SAXException
  }
  
  private String createSignature(Map<String, String> paramMap, String paramString)
  {
    Object localObject = new TreeSet(paramMap.keySet());
    StringBuilder localStringBuilder = new StringBuilder(50);
    localObject = ((Set)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      String str = (String)((Iterator)localObject).next();
      localStringBuilder.append(str);
      localStringBuilder.append(StringUtilities.encode((String)paramMap.get(str)));
    }
    localStringBuilder.append(paramString);
    return StringUtilities.md5(localStringBuilder.toString());
  }
  
  public static Caller getInstance()
  {
    return instance;
  }
  
  private DocumentBuilder newDocumentBuilder()
  {
    try
    {
      DocumentBuilder localDocumentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
      return localDocumentBuilder;
    }
    catch (ParserConfigurationException localParserConfigurationException)
    {
      throw new RuntimeException(localParserConfigurationException);
    }
  }
  
  public Result call(String paramString1, String paramString2, Map<String, String> paramMap)
    throws CallException
  {
    return call(paramString1, paramString2, paramMap, null);
  }
  
  public Result call(String paramString1, String paramString2, String... paramVarArgs)
    throws CallException
  {
    return call(paramString1, paramString2, StringUtilities.map(paramVarArgs));
  }
  
  public Result call(String paramString, Session paramSession, Map<String, String> paramMap)
  {
    return call(paramString, paramSession.getApiKey(), paramMap, paramSession);
  }
  
  public Result call(String paramString, Session paramSession, String... paramVarArgs)
  {
    return call(paramString, paramSession.getApiKey(), StringUtilities.map(paramVarArgs), paramSession);
  }
  
  public Cache getCache()
  {
    return this.cache;
  }
  
  public Result getLastResult()
  {
    return this.lastResult;
  }
  
  public Proxy getProxy()
  {
    return this.proxy;
  }
  
  public String getUserAgent()
  {
    return this.userAgent;
  }
  
  public boolean isDebugMode()
  {
    return this.debugMode;
  }
  
  public HttpURLConnection openConnection(String paramString)
    throws IOException
  {
    if (isDebugMode()) {
      System.out.println("open: " + paramString);
    }
    paramString = new URL(paramString);
    if (this.proxy != null) {}
    for (paramString = (HttpURLConnection)paramString.openConnection(this.proxy);; paramString = (HttpURLConnection)paramString.openConnection())
    {
      paramString.setRequestProperty("User-Agent", this.userAgent);
      return paramString;
    }
  }
  
  public void setApiRootUrl(String paramString)
  {
    this.apiRootUrl = paramString;
  }
  
  public void setCache(Cache paramCache)
  {
    this.cache = paramCache;
  }
  
  public void setDebugMode(boolean paramBoolean)
  {
    this.debugMode = paramBoolean;
  }
  
  public void setProxy(Proxy paramProxy)
  {
    this.proxy = paramProxy;
  }
  
  public void setUserAgent(String paramString)
  {
    this.userAgent = paramString;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\net\roarsoftware\lastfm\Caller.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */