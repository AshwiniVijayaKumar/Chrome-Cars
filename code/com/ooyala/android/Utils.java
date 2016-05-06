package com.ooyala.android;

import android.os.Build.VERSION;
import android.util.Base64;
import com.ooyala.android.util.DebugMode;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReference;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Utils
{
  static final String CHARSET = "UTF-8";
  static final String DEVICE_ANDROID_HLS_SDK = "android_3plus_sdk";
  static final String DEVICE_ANDROID_SDK = "android_sdk";
  static final String DEVICE_IPAD = "ipad";
  static final String SEPARATOR_AMPERSAND = "&";
  static final String SEPARATOR_TIME = ":";
  private static final String TAG = Utils.class.getSimpleName();
  
  public static <T extends Comparable<? super T>> List<T> asSortedList(Collection<T> paramCollection)
  {
    paramCollection = new ArrayList(paramCollection);
    Collections.sort(paramCollection);
    return paramCollection;
  }
  
  public static String blockingGetEmbedTokenForEmbedCodes(EmbedTokenGenerator paramEmbedTokenGenerator, List<String> paramList)
  {
    if (paramEmbedTokenGenerator != null)
    {
      DebugMode.logD(TAG, "Requesting an OPT for Chromecast");
      final Semaphore localSemaphore = new Semaphore(0);
      AtomicReference localAtomicReference = new AtomicReference();
      paramEmbedTokenGenerator.getTokenForEmbedCodes(paramList, new EmbedTokenGeneratorCallback()
      {
        public void setEmbedToken(String paramAnonymousString)
        {
          this.val$tokenReference.set(paramAnonymousString);
          localSemaphore.release();
        }
      });
      try
      {
        localSemaphore.acquire();
        return (String)localAtomicReference.get();
      }
      catch (InterruptedException paramEmbedTokenGenerator)
      {
        DebugMode.logE(TAG, "Embed Token request was interrupted");
        return null;
      }
    }
    DebugMode.logD(TAG, "No embed token generator to get an OPT");
    return null;
  }
  
  public static String device()
  {
    if (OoyalaPlayer.enableHighResHLS) {
      return "ipad";
    }
    if ((OoyalaPlayer.enableHLS) || (Build.VERSION.SDK_INT >= 14)) {
      return "android_3plus_sdk";
    }
    return "android_sdk";
  }
  
  public static String encryptString(String paramString)
  {
    paramString = paramString.getBytes();
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("SHA-256");
      localMessageDigest.reset();
      return Base64.encodeToString(localMessageDigest.digest(paramString), 0);
    }
    catch (NoSuchAlgorithmException paramString)
    {
      DebugMode.logE(TAG, "Caught!", paramString);
    }
    return null;
  }
  
  public static Object getJSONValueOrElse(JSONObject paramJSONObject, String paramString, Object paramObject)
  {
    try
    {
      paramJSONObject = paramJSONObject.get(paramString);
      return paramJSONObject;
    }
    catch (JSONException paramJSONObject) {}
    return paramObject;
  }
  
  public static String getParamsString(Map<String, String> paramMap, String paramString, boolean paramBoolean)
  {
    if ((paramMap == null) || (paramMap.isEmpty())) {
      return "";
    }
    StringBuffer localStringBuffer = new StringBuffer();
    int i = 1;
    Iterator localIterator = asSortedList(paramMap.keySet()).iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      if ((str != null) && (paramMap.get(str) != null))
      {
        if (i != 0) {
          i = 0;
        }
        for (;;)
        {
          localStringBuffer.append(str);
          localStringBuffer.append("=");
          if (!paramBoolean) {
            break label176;
          }
          try
          {
            localStringBuffer.append(URLEncoder.encode((String)paramMap.get(str), "UTF-8"));
          }
          catch (UnsupportedEncodingException localUnsupportedEncodingException)
          {
            DebugMode.logE(Utils.class.getName(), "ERROR while trying to encode parameter", localUnsupportedEncodingException);
            localStringBuffer.append((String)paramMap.get(str));
          }
          break;
          localStringBuffer.append(paramString);
        }
        label176:
        localStringBuffer.append((String)paramMap.get(str));
      }
    }
    return localStringBuffer.toString();
  }
  
  public static <T> Set<T> getSubset(Map<String, T> paramMap, int paramInt1, int paramInt2)
  {
    if ((paramInt1 < 0) || (paramInt1 + paramInt2 > paramMap.size()))
    {
      paramMap = null;
      return paramMap;
    }
    Iterator localIterator = paramMap.values().iterator();
    int i = 0;
    while ((i < paramInt1) && (localIterator.hasNext()))
    {
      localIterator.next();
      i += 1;
    }
    HashSet localHashSet = new HashSet();
    paramInt1 = 0;
    for (;;)
    {
      paramMap = localHashSet;
      if (paramInt1 >= paramInt2) {
        break;
      }
      paramMap = localHashSet;
      if (!localIterator.hasNext()) {
        break;
      }
      localHashSet.add(localIterator.next());
      paramInt1 += 1;
    }
  }
  
  /* Error */
  public static String getUrlContent(URL paramURL, int paramInt1, int paramInt2)
  {
    // Byte code:
    //   0: getstatic 36	com/ooyala/android/Utils:TAG	Ljava/lang/String;
    //   3: new 250	java/lang/StringBuilder
    //   6: dup
    //   7: invokespecial 251	java/lang/StringBuilder:<init>	()V
    //   10: ldc -3
    //   12: invokevirtual 256	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   15: aload_0
    //   16: invokevirtual 259	java/net/URL:toString	()Ljava/lang/String;
    //   19: invokevirtual 256	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   22: invokevirtual 260	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   25: invokestatic 67	com/ooyala/android/util/DebugMode:logD	(Ljava/lang/String;Ljava/lang/String;)V
    //   28: new 174	java/lang/StringBuffer
    //   31: dup
    //   32: invokespecial 175	java/lang/StringBuffer:<init>	()V
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
    //   50: invokevirtual 264	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   53: astore 8
    //   55: aload 4
    //   57: astore_3
    //   58: aload 8
    //   60: iload_1
    //   61: invokevirtual 269	java/net/URLConnection:setConnectTimeout	(I)V
    //   64: aload 4
    //   66: astore_3
    //   67: aload 8
    //   69: iload_2
    //   70: invokevirtual 272	java/net/URLConnection:setReadTimeout	(I)V
    //   73: aload 4
    //   75: astore_3
    //   76: new 274	java/io/BufferedReader
    //   79: dup
    //   80: new 276	java/io/InputStreamReader
    //   83: dup
    //   84: aload 8
    //   86: invokevirtual 280	java/net/URLConnection:getInputStream	()Ljava/io/InputStream;
    //   89: invokespecial 283	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   92: sipush 8192
    //   95: invokespecial 286	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   98: astore 4
    //   100: aload 4
    //   102: invokevirtual 289	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   105: astore_3
    //   106: aload_3
    //   107: ifnull +68 -> 175
    //   110: aload 7
    //   112: aload_3
    //   113: invokevirtual 202	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   116: pop
    //   117: goto -17 -> 100
    //   120: astore_3
    //   121: aload 4
    //   123: astore_3
    //   124: getstatic 36	com/ooyala/android/Utils:TAG	Ljava/lang/String;
    //   127: new 250	java/lang/StringBuilder
    //   130: dup
    //   131: invokespecial 251	java/lang/StringBuilder:<init>	()V
    //   134: ldc_w 291
    //   137: invokevirtual 256	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   140: aload_0
    //   141: invokevirtual 259	java/net/URL:toString	()Ljava/lang/String;
    //   144: invokevirtual 256	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   147: ldc_w 293
    //   150: invokevirtual 256	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   153: invokevirtual 260	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   156: invokestatic 98	com/ooyala/android/util/DebugMode:logE	(Ljava/lang/String;Ljava/lang/String;)V
    //   159: aload 4
    //   161: ifnull +8 -> 169
    //   164: aload 4
    //   166: invokevirtual 296	java/io/BufferedReader:close	()V
    //   169: aload 7
    //   171: invokevirtual 218	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   174: areturn
    //   175: aload 4
    //   177: ifnull +127 -> 304
    //   180: aload 4
    //   182: invokevirtual 296	java/io/BufferedReader:close	()V
    //   185: goto -16 -> 169
    //   188: astore_0
    //   189: getstatic 36	com/ooyala/android/Utils:TAG	Ljava/lang/String;
    //   192: ldc -108
    //   194: aload_0
    //   195: invokestatic 151	com/ooyala/android/util/DebugMode:logE	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   198: goto -29 -> 169
    //   201: astore_0
    //   202: getstatic 36	com/ooyala/android/Utils:TAG	Ljava/lang/String;
    //   205: ldc -108
    //   207: aload_0
    //   208: invokestatic 151	com/ooyala/android/util/DebugMode:logE	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   211: goto -42 -> 169
    //   214: astore 4
    //   216: aload 6
    //   218: astore_0
    //   219: aload_0
    //   220: astore_3
    //   221: getstatic 36	com/ooyala/android/Utils:TAG	Ljava/lang/String;
    //   224: ldc -108
    //   226: aload 4
    //   228: invokestatic 151	com/ooyala/android/util/DebugMode:logE	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   231: aload_0
    //   232: ifnull -63 -> 169
    //   235: aload_0
    //   236: invokevirtual 296	java/io/BufferedReader:close	()V
    //   239: goto -70 -> 169
    //   242: astore_0
    //   243: getstatic 36	com/ooyala/android/Utils:TAG	Ljava/lang/String;
    //   246: ldc -108
    //   248: aload_0
    //   249: invokestatic 151	com/ooyala/android/util/DebugMode:logE	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   252: goto -83 -> 169
    //   255: astore_0
    //   256: aload_3
    //   257: ifnull +7 -> 264
    //   260: aload_3
    //   261: invokevirtual 296	java/io/BufferedReader:close	()V
    //   264: aload_0
    //   265: athrow
    //   266: astore_3
    //   267: getstatic 36	com/ooyala/android/Utils:TAG	Ljava/lang/String;
    //   270: ldc -108
    //   272: aload_3
    //   273: invokestatic 151	com/ooyala/android/util/DebugMode:logE	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   276: goto -12 -> 264
    //   279: astore_0
    //   280: aload 4
    //   282: astore_3
    //   283: goto -27 -> 256
    //   286: astore_3
    //   287: aload 4
    //   289: astore_0
    //   290: aload_3
    //   291: astore 4
    //   293: goto -74 -> 219
    //   296: astore_3
    //   297: aload 5
    //   299: astore 4
    //   301: goto -180 -> 121
    //   304: goto -135 -> 169
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	307	0	paramURL	URL
    //   0	307	1	paramInt1	int
    //   0	307	2	paramInt2	int
    //   48	65	3	localObject1	Object
    //   120	1	3	localSocketTimeoutException1	SocketTimeoutException
    //   123	138	3	localObject2	Object
    //   266	7	3	localIOException1	IOException
    //   282	1	3	localIOException2	IOException
    //   286	5	3	localIOException3	IOException
    //   296	1	3	localSocketTimeoutException2	SocketTimeoutException
    //   41	140	4	localBufferedReader	BufferedReader
    //   214	74	4	localIOException4	IOException
    //   291	9	4	localObject3	Object
    //   44	254	5	localObject4	Object
    //   38	179	6	localObject5	Object
    //   35	135	7	localStringBuffer	StringBuffer
    //   53	32	8	localURLConnection	java.net.URLConnection
    // Exception table:
    //   from	to	target	type
    //   100	106	120	java/net/SocketTimeoutException
    //   110	117	120	java/net/SocketTimeoutException
    //   180	185	188	java/io/IOException
    //   164	169	201	java/io/IOException
    //   49	55	214	java/io/IOException
    //   58	64	214	java/io/IOException
    //   67	73	214	java/io/IOException
    //   76	100	214	java/io/IOException
    //   235	239	242	java/io/IOException
    //   49	55	255	finally
    //   58	64	255	finally
    //   67	73	255	finally
    //   76	100	255	finally
    //   124	159	255	finally
    //   221	231	255	finally
    //   260	264	266	java/io/IOException
    //   100	106	279	finally
    //   110	117	279	finally
    //   100	106	286	java/io/IOException
    //   110	117	286	java/io/IOException
    //   49	55	296	java/net/SocketTimeoutException
    //   58	64	296	java/net/SocketTimeoutException
    //   67	73	296	java/net/SocketTimeoutException
    //   76	100	296	java/net/SocketTimeoutException
  }
  
  public static boolean isNullOrEmpty(String paramString)
  {
    return (paramString == null) || (paramString.equals(""));
  }
  
  public static String join(Collection<? extends Object> paramCollection, String paramString)
  {
    if (paramCollection == null) {
      return null;
    }
    StringBuffer localStringBuffer = new StringBuffer();
    paramCollection = paramCollection.iterator();
    while (paramCollection.hasNext())
    {
      localStringBuffer.append(paramCollection.next().toString());
      localStringBuffer.append(paramString);
    }
    paramCollection = localStringBuffer;
    if (localStringBuffer.length() > 0) {
      paramCollection = localStringBuffer.deleteCharAt(localStringBuffer.length() - 1);
    }
    return paramCollection.toString();
  }
  
  public static URL makeURL(String paramString1, String paramString2, String paramString3)
  {
    label83:
    for (;;)
    {
      try
      {
        paramString2 = new StringBuilder().append(paramString1).append(paramString2);
        if (paramString3 != null) {
          if (paramString3.length() < 1)
          {
            break label83;
            return new URL(paramString1);
          }
          else
          {
            paramString1 = "?" + paramString3;
            continue;
          }
        }
        paramString1 = "";
      }
      catch (MalformedURLException paramString1)
      {
        DebugMode.logE(TAG, "Caught!", paramString1);
        return null;
      }
    }
  }
  
  public static URL makeURL(String paramString1, String paramString2, Map<String, String> paramMap)
  {
    return makeURL(paramString1, paramString2, getParamsString(paramMap, "&", true));
  }
  
  public static String mapToJsonString(Map<String, String> paramMap)
  {
    if (paramMap == null) {
      return "";
    }
    return new JSONObject(paramMap).toString();
  }
  
  public static JSONObject objectFromJSON(String paramString)
  {
    try
    {
      paramString = (JSONObject)new JSONTokener(paramString).nextValue();
      return paramString;
    }
    catch (JSONException paramString)
    {
      System.out.println("JSONException: " + paramString);
      return null;
    }
    catch (ClassCastException paramString)
    {
      System.out.println("ClassCastException: " + paramString);
    }
    return null;
  }
  
  public static void overwriteJSONObject(JSONObject paramJSONObject1, JSONObject paramJSONObject2)
    throws JSONException
  {
    if ((paramJSONObject1 != null) && (paramJSONObject2 != null))
    {
      Iterator localIterator = paramJSONObject1.keys();
      while (localIterator.hasNext())
      {
        String str = String.valueOf(localIterator.next());
        paramJSONObject2.put(str, paramJSONObject1.get(str));
      }
    }
  }
  
  public static String postUrl(URL paramURL, String paramString, int paramInt1, int paramInt2)
  {
    String str3 = "";
    String str1 = str3;
    String str2 = str3;
    try
    {
      paramString = paramString.getBytes("utf-8");
      str1 = str3;
      str2 = str3;
      Object localObject = (HttpURLConnection)paramURL.openConnection();
      str1 = str3;
      str2 = str3;
      ((HttpURLConnection)localObject).setConnectTimeout(paramInt1);
      str1 = str3;
      str2 = str3;
      ((HttpURLConnection)localObject).setReadTimeout(paramInt2);
      str1 = str3;
      str2 = str3;
      ((HttpURLConnection)localObject).setDoOutput(true);
      str1 = str3;
      str2 = str3;
      ((HttpURLConnection)localObject).setRequestMethod("POST");
      str1 = str3;
      str2 = str3;
      ((HttpURLConnection)localObject).setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
      str1 = str3;
      str2 = str3;
      ((HttpURLConnection)localObject).setRequestProperty("Content-Length", Integer.toString(paramString.length));
      str1 = str3;
      str2 = str3;
      ((HttpURLConnection)localObject).setUseCaches(false);
      str1 = str3;
      str2 = str3;
      new DataOutputStream(((HttpURLConnection)localObject).getOutputStream()).write(paramString);
      str1 = str3;
      str2 = str3;
      paramInt1 = ((HttpURLConnection)localObject).getResponseCode();
      str1 = str3;
      str2 = str3;
      paramString = ((HttpURLConnection)localObject).getResponseMessage();
      if (paramInt1 != 200)
      {
        str1 = paramString;
        str2 = paramString;
        localObject = ((HttpURLConnection)localObject).getErrorStream();
        if (localObject != null)
        {
          str3 = "";
          str1 = paramString;
          str2 = paramString;
          BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader((InputStream)localObject));
          for (;;)
          {
            str1 = paramString;
            str2 = paramString;
            String str4 = localBufferedReader.readLine();
            if (str4 == null) {
              break;
            }
            str1 = paramString;
            str2 = paramString;
            str3 = str3 + str4;
          }
          str1 = paramString;
          str2 = paramString;
          ((InputStream)localObject).close();
          str1 = paramString;
          str2 = paramString;
          DebugMode.logE(TAG, "the http response for post method is" + Integer.toString(paramInt1) + " error:" + str3);
        }
      }
      return paramString;
    }
    catch (SocketTimeoutException paramString)
    {
      DebugMode.logE(TAG, "Connection to " + paramURL.toString() + " timed out.");
      return str1;
    }
    catch (IOException paramURL)
    {
      DebugMode.logE(TAG, "Caught!", paramURL);
    }
    return str2;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\Utils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */