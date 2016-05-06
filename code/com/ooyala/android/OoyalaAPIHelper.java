package com.ooyala.android;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

class OoyalaAPIHelper
{
  private static final String TAG = OoyalaAPIHelper.class.getName();
  public static Map<String, String> cookies = new HashMap();
  
  /* Error */
  private static String jsonForAPI(URL paramURL, int paramInt1, int paramInt2)
  {
    // Byte code:
    //   0: getstatic 19	com/ooyala/android/OoyalaAPIHelper:TAG	Ljava/lang/String;
    //   3: new 36	java/lang/StringBuilder
    //   6: dup
    //   7: invokespecial 37	java/lang/StringBuilder:<init>	()V
    //   10: ldc 39
    //   12: invokevirtual 43	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   15: aload_0
    //   16: invokevirtual 48	java/net/URL:toString	()Ljava/lang/String;
    //   19: invokevirtual 43	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   22: invokevirtual 49	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   25: invokestatic 55	com/ooyala/android/util/DebugMode:logD	(Ljava/lang/String;Ljava/lang/String;)V
    //   28: new 57	java/lang/StringBuffer
    //   31: dup
    //   32: invokespecial 58	java/lang/StringBuffer:<init>	()V
    //   35: astore 7
    //   37: aconst_null
    //   38: astore 6
    //   40: aconst_null
    //   41: astore 4
    //   43: aconst_null
    //   44: astore 5
    //   46: aload 4
    //   48: astore_3
    //   49: aload_0
    //   50: invokevirtual 62	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   53: astore 8
    //   55: aload 4
    //   57: astore_3
    //   58: aload 8
    //   60: iload_1
    //   61: invokevirtual 68	java/net/URLConnection:setConnectTimeout	(I)V
    //   64: aload 4
    //   66: astore_3
    //   67: aload 8
    //   69: iload_2
    //   70: invokevirtual 71	java/net/URLConnection:setReadTimeout	(I)V
    //   73: aload 4
    //   75: astore_3
    //   76: new 73	java/io/BufferedReader
    //   79: dup
    //   80: new 75	java/io/InputStreamReader
    //   83: dup
    //   84: aload 8
    //   86: invokevirtual 79	java/net/URLConnection:getInputStream	()Ljava/io/InputStream;
    //   89: invokespecial 82	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   92: sipush 8192
    //   95: invokespecial 85	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   98: astore 4
    //   100: aload 4
    //   102: invokevirtual 88	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   105: astore_3
    //   106: aload_3
    //   107: ifnull +66 -> 173
    //   110: aload 7
    //   112: aload_3
    //   113: invokevirtual 91	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   116: pop
    //   117: goto -17 -> 100
    //   120: astore_3
    //   121: aload 4
    //   123: astore_3
    //   124: getstatic 19	com/ooyala/android/OoyalaAPIHelper:TAG	Ljava/lang/String;
    //   127: new 36	java/lang/StringBuilder
    //   130: dup
    //   131: invokespecial 37	java/lang/StringBuilder:<init>	()V
    //   134: ldc 93
    //   136: invokevirtual 43	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   139: aload_0
    //   140: invokevirtual 48	java/net/URL:toString	()Ljava/lang/String;
    //   143: invokevirtual 43	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   146: ldc 95
    //   148: invokevirtual 43	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   151: invokevirtual 49	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   154: invokestatic 98	com/ooyala/android/util/DebugMode:logE	(Ljava/lang/String;Ljava/lang/String;)V
    //   157: aload 4
    //   159: ifnull +8 -> 167
    //   162: aload 4
    //   164: invokevirtual 101	java/io/BufferedReader:close	()V
    //   167: aload 7
    //   169: invokevirtual 102	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   172: areturn
    //   173: iconst_1
    //   174: istore_1
    //   175: aload 8
    //   177: iload_1
    //   178: invokevirtual 106	java/net/URLConnection:getHeaderFieldKey	(I)Ljava/lang/String;
    //   181: astore_3
    //   182: aload_3
    //   183: ifnull +122 -> 305
    //   186: aload_3
    //   187: ldc 108
    //   189: invokevirtual 114	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   192: ifeq +106 -> 298
    //   195: aload 8
    //   197: iload_1
    //   198: invokevirtual 117	java/net/URLConnection:getHeaderField	(I)Ljava/lang/String;
    //   201: astore_3
    //   202: ldc 2
    //   204: invokevirtual 17	java/lang/Class:getName	()Ljava/lang/String;
    //   207: new 36	java/lang/StringBuilder
    //   210: dup
    //   211: invokespecial 37	java/lang/StringBuilder:<init>	()V
    //   214: ldc 119
    //   216: invokevirtual 43	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   219: aload_3
    //   220: invokevirtual 43	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   223: invokevirtual 49	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   226: invokestatic 55	com/ooyala/android/util/DebugMode:logD	(Ljava/lang/String;Ljava/lang/String;)V
    //   229: aload_3
    //   230: ldc 121
    //   232: invokevirtual 125	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   235: ifle +63 -> 298
    //   238: aload_3
    //   239: iconst_0
    //   240: aload_3
    //   241: ldc 121
    //   243: invokevirtual 125	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   246: invokevirtual 129	java/lang/String:substring	(II)Ljava/lang/String;
    //   249: astore 5
    //   251: aload 5
    //   253: iconst_0
    //   254: aload 5
    //   256: ldc -125
    //   258: invokevirtual 125	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   261: invokevirtual 129	java/lang/String:substring	(II)Ljava/lang/String;
    //   264: astore_3
    //   265: aload 5
    //   267: aload 5
    //   269: ldc -125
    //   271: invokevirtual 125	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   274: iconst_1
    //   275: iadd
    //   276: aload 5
    //   278: invokevirtual 135	java/lang/String:length	()I
    //   281: invokevirtual 129	java/lang/String:substring	(II)Ljava/lang/String;
    //   284: astore 5
    //   286: getstatic 26	com/ooyala/android/OoyalaAPIHelper:cookies	Ljava/util/Map;
    //   289: aload_3
    //   290: aload 5
    //   292: invokeinterface 141 3 0
    //   297: pop
    //   298: iload_1
    //   299: iconst_1
    //   300: iadd
    //   301: istore_1
    //   302: goto -127 -> 175
    //   305: aload 4
    //   307: ifnull +127 -> 434
    //   310: aload 4
    //   312: invokevirtual 101	java/io/BufferedReader:close	()V
    //   315: goto -148 -> 167
    //   318: astore_0
    //   319: getstatic 19	com/ooyala/android/OoyalaAPIHelper:TAG	Ljava/lang/String;
    //   322: ldc -113
    //   324: aload_0
    //   325: invokestatic 146	com/ooyala/android/util/DebugMode:logE	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   328: goto -161 -> 167
    //   331: astore_0
    //   332: getstatic 19	com/ooyala/android/OoyalaAPIHelper:TAG	Ljava/lang/String;
    //   335: ldc -113
    //   337: aload_0
    //   338: invokestatic 146	com/ooyala/android/util/DebugMode:logE	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   341: goto -174 -> 167
    //   344: astore 4
    //   346: aload 6
    //   348: astore_0
    //   349: aload_0
    //   350: astore_3
    //   351: getstatic 19	com/ooyala/android/OoyalaAPIHelper:TAG	Ljava/lang/String;
    //   354: ldc -113
    //   356: aload 4
    //   358: invokestatic 146	com/ooyala/android/util/DebugMode:logE	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   361: aload_0
    //   362: ifnull -195 -> 167
    //   365: aload_0
    //   366: invokevirtual 101	java/io/BufferedReader:close	()V
    //   369: goto -202 -> 167
    //   372: astore_0
    //   373: getstatic 19	com/ooyala/android/OoyalaAPIHelper:TAG	Ljava/lang/String;
    //   376: ldc -113
    //   378: aload_0
    //   379: invokestatic 146	com/ooyala/android/util/DebugMode:logE	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   382: goto -215 -> 167
    //   385: astore_0
    //   386: aload_3
    //   387: ifnull +7 -> 394
    //   390: aload_3
    //   391: invokevirtual 101	java/io/BufferedReader:close	()V
    //   394: aload_0
    //   395: athrow
    //   396: astore_3
    //   397: getstatic 19	com/ooyala/android/OoyalaAPIHelper:TAG	Ljava/lang/String;
    //   400: ldc -113
    //   402: aload_3
    //   403: invokestatic 146	com/ooyala/android/util/DebugMode:logE	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   406: goto -12 -> 394
    //   409: astore_0
    //   410: aload 4
    //   412: astore_3
    //   413: goto -27 -> 386
    //   416: astore_3
    //   417: aload 4
    //   419: astore_0
    //   420: aload_3
    //   421: astore 4
    //   423: goto -74 -> 349
    //   426: astore_3
    //   427: aload 5
    //   429: astore 4
    //   431: goto -310 -> 121
    //   434: goto -267 -> 167
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	437	0	paramURL	URL
    //   0	437	1	paramInt1	int
    //   0	437	2	paramInt2	int
    //   48	65	3	localObject1	Object
    //   120	1	3	localSocketTimeoutException1	java.net.SocketTimeoutException
    //   123	268	3	localObject2	Object
    //   396	7	3	localIOException1	java.io.IOException
    //   412	1	3	localIOException2	java.io.IOException
    //   416	5	3	localIOException3	java.io.IOException
    //   426	1	3	localSocketTimeoutException2	java.net.SocketTimeoutException
    //   41	270	4	localBufferedReader	java.io.BufferedReader
    //   344	74	4	localIOException4	java.io.IOException
    //   421	9	4	localObject3	Object
    //   44	384	5	str	String
    //   38	309	6	localObject4	Object
    //   35	133	7	localStringBuffer	StringBuffer
    //   53	143	8	localURLConnection	java.net.URLConnection
    // Exception table:
    //   from	to	target	type
    //   100	106	120	java/net/SocketTimeoutException
    //   110	117	120	java/net/SocketTimeoutException
    //   175	182	120	java/net/SocketTimeoutException
    //   186	298	120	java/net/SocketTimeoutException
    //   310	315	318	java/io/IOException
    //   162	167	331	java/io/IOException
    //   49	55	344	java/io/IOException
    //   58	64	344	java/io/IOException
    //   67	73	344	java/io/IOException
    //   76	100	344	java/io/IOException
    //   365	369	372	java/io/IOException
    //   49	55	385	finally
    //   58	64	385	finally
    //   67	73	385	finally
    //   76	100	385	finally
    //   124	157	385	finally
    //   351	361	385	finally
    //   390	394	396	java/io/IOException
    //   100	106	409	finally
    //   110	117	409	finally
    //   175	182	409	finally
    //   186	298	409	finally
    //   100	106	416	java/io/IOException
    //   110	117	416	java/io/IOException
    //   175	182	416	java/io/IOException
    //   186	298	416	java/io/IOException
    //   49	55	426	java/net/SocketTimeoutException
    //   58	64	426	java/net/SocketTimeoutException
    //   67	73	426	java/net/SocketTimeoutException
    //   76	100	426	java/net/SocketTimeoutException
  }
  
  public static JSONObject objectForAPI(String paramString1, String paramString2, Map<String, String> paramMap, int paramInt1, int paramInt2)
  {
    paramString1 = Utils.makeURL(paramString1, paramString2, paramMap);
    if (paramString1 == null) {
      return null;
    }
    return objectForAPI(paramString1, paramInt1, paramInt2);
  }
  
  public static JSONObject objectForAPI(URL paramURL, int paramInt1, int paramInt2)
  {
    return Utils.objectFromJSON(jsonForAPI(paramURL, paramInt1, paramInt2));
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\OoyalaAPIHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */