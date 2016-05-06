package org.apache.cordova.filetransfer;

import android.net.Uri;
import android.util.Log;
import android.webkit.CookieManager;
import java.io.Closeable;
import java.io.File;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.zip.GZIPInputStream;
import java.util.zip.Inflater;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaResourceApi;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginManager;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.apache.cordova.Whitelist;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FileTransfer
  extends CordovaPlugin
{
  public static int ABORTED_ERR = 0;
  private static final String BOUNDARY = "+++++";
  public static int CONNECTION_ERR = 0;
  private static final HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier()
  {
    public boolean verify(String paramAnonymousString, SSLSession paramAnonymousSSLSession)
    {
      return true;
    }
  };
  public static int FILE_NOT_FOUND_ERR = 1;
  public static int INVALID_URL_ERR = 2;
  private static final String LINE_END = "\r\n";
  private static final String LINE_START = "--";
  private static final String LOG_TAG = "FileTransfer";
  private static final int MAX_BUFFER_SIZE = 16384;
  public static int NOT_MODIFIED_ERR;
  private static HashMap<String, RequestContext> activeRequests;
  private static final TrustManager[] trustAllCerts = { new X509TrustManager()
  {
    public void checkClientTrusted(X509Certificate[] paramAnonymousArrayOfX509Certificate, String paramAnonymousString)
      throws CertificateException
    {}
    
    public void checkServerTrusted(X509Certificate[] paramAnonymousArrayOfX509Certificate, String paramAnonymousString)
      throws CertificateException
    {}
    
    public X509Certificate[] getAcceptedIssuers()
    {
      return new X509Certificate[0];
    }
  } };
  
  static
  {
    CONNECTION_ERR = 3;
    ABORTED_ERR = 4;
    NOT_MODIFIED_ERR = 5;
    activeRequests = new HashMap();
  }
  
  private void abort(final String paramString)
  {
    synchronized (activeRequests)
    {
      paramString = (RequestContext)activeRequests.remove(paramString);
      if (paramString != null) {
        this.cordova.getThreadPool().execute(new Runnable()
        {
          public void run()
          {
            synchronized (paramString)
            {
              Object localObject1 = paramString.targetFile;
              if (localObject1 != null) {
                ((File)localObject1).delete();
              }
              localObject1 = FileTransfer.createFileTransferError(FileTransfer.ABORTED_ERR, paramString.source, paramString.target, null, Integer.valueOf(-1), null);
              paramString.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, (JSONObject)localObject1));
              paramString.aborted = true;
              localObject1 = paramString.connection;
              if (localObject1 != null) {}
              try
              {
                paramString.connection.disconnect();
                return;
              }
              catch (Exception localException)
              {
                for (;;)
                {
                  Log.e("FileTransfer", "CB-8431 Catch workaround for fatal exception", localException);
                }
              }
            }
          }
        });
      }
      return;
    }
  }
  
  private static void addHeadersToRequest(URLConnection paramURLConnection, JSONObject paramJSONObject)
  {
    try
    {
      Iterator localIterator = paramJSONObject.keys();
      while (localIterator.hasNext())
      {
        String str1 = localIterator.next().toString();
        String str2 = str1.replaceAll("\\n", "").replaceAll("\\s+", "").replaceAll(":", "").replaceAll("[^\\x20-\\x7E]+", "");
        JSONArray localJSONArray2 = paramJSONObject.optJSONArray(str1);
        JSONArray localJSONArray1 = localJSONArray2;
        if (localJSONArray2 == null)
        {
          localJSONArray1 = new JSONArray();
          localJSONArray1.put(paramJSONObject.getString(str1).replaceAll("\\s+", " ").replaceAll("\\n", " ").replaceAll("[^\\x20-\\x7E]+", " "));
        }
        paramURLConnection.setRequestProperty(str2, localJSONArray1.getString(0));
        int i = 1;
        while (i < localJSONArray1.length())
        {
          paramURLConnection.addRequestProperty(str1, localJSONArray1.getString(i));
          i += 1;
        }
      }
      return;
    }
    catch (JSONException paramURLConnection) {}
  }
  
  private static JSONObject createFileTransferError(int paramInt, String paramString1, String paramString2, String paramString3, Integer paramInteger, Throwable paramThrowable)
  {
    Object localObject = null;
    try
    {
      localJSONObject = new JSONObject();
      Log.e("FileTransfer", paramString1.getMessage(), paramString1);
    }
    catch (JSONException paramString1)
    {
      try
      {
        localJSONObject.put("code", paramInt);
        localJSONObject.put("source", paramString1);
        localJSONObject.put("target", paramString2);
        if (paramString3 != null) {
          localJSONObject.put("body", paramString3);
        }
        if (paramInteger != null) {
          localJSONObject.put("http_status", paramInteger);
        }
        if (paramThrowable != null)
        {
          paramString2 = paramThrowable.getMessage();
          if (paramString2 != null)
          {
            paramString1 = paramString2;
            if (!"".equals(paramString2)) {}
          }
          else
          {
            paramString1 = paramThrowable.toString();
          }
          localJSONObject.put("exception", paramString1);
        }
        return localJSONObject;
      }
      catch (JSONException paramString1)
      {
        for (;;)
        {
          JSONObject localJSONObject;
          paramString2 = localJSONObject;
        }
      }
      paramString1 = paramString1;
      paramString2 = (String)localObject;
    }
    return paramString2;
  }
  
  /* Error */
  private static JSONObject createFileTransferError(int paramInt, String paramString1, String paramString2, URLConnection paramURLConnection, Throwable paramThrowable)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore 8
    //   3: iconst_0
    //   4: istore 7
    //   6: new 264	java/lang/StringBuilder
    //   9: dup
    //   10: invokespecial 265	java/lang/StringBuilder:<init>	()V
    //   13: astore 13
    //   15: aconst_null
    //   16: astore 12
    //   18: aconst_null
    //   19: astore 11
    //   21: aload 12
    //   23: astore 10
    //   25: iload 8
    //   27: istore 6
    //   29: aload_3
    //   30: ifnull +188 -> 218
    //   33: aload 11
    //   35: astore 9
    //   37: iload 7
    //   39: istore 5
    //   41: aload 12
    //   43: astore 10
    //   45: iload 8
    //   47: istore 6
    //   49: aload_3
    //   50: instanceof 267
    //   53: ifeq +165 -> 218
    //   56: aload 11
    //   58: astore 9
    //   60: iload 7
    //   62: istore 5
    //   64: aload_3
    //   65: checkcast 267	java/net/HttpURLConnection
    //   68: invokevirtual 270	java/net/HttpURLConnection:getResponseCode	()I
    //   71: istore 7
    //   73: aload 11
    //   75: astore 9
    //   77: iload 7
    //   79: istore 5
    //   81: aload_3
    //   82: checkcast 267	java/net/HttpURLConnection
    //   85: invokevirtual 274	java/net/HttpURLConnection:getErrorStream	()Ljava/io/InputStream;
    //   88: astore_3
    //   89: aload 12
    //   91: astore 10
    //   93: iload 7
    //   95: istore 6
    //   97: aload_3
    //   98: ifnull +120 -> 218
    //   101: aload 11
    //   103: astore 9
    //   105: iload 7
    //   107: istore 5
    //   109: new 276	java/io/BufferedReader
    //   112: dup
    //   113: new 278	java/io/InputStreamReader
    //   116: dup
    //   117: aload_3
    //   118: ldc_w 280
    //   121: invokespecial 283	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;Ljava/lang/String;)V
    //   124: invokespecial 286	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   127: astore 12
    //   129: aload 12
    //   131: invokevirtual 289	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   134: astore_3
    //   135: aload_3
    //   136: ifnull +98 -> 234
    //   139: aload 13
    //   141: aload_3
    //   142: invokevirtual 293	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   145: pop
    //   146: aload 12
    //   148: invokevirtual 289	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   151: astore 9
    //   153: aload 9
    //   155: astore_3
    //   156: aload 9
    //   158: ifnull -23 -> 135
    //   161: aload 13
    //   163: bipush 10
    //   165: invokevirtual 296	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
    //   168: pop
    //   169: aload 9
    //   171: astore_3
    //   172: goto -37 -> 135
    //   175: astore_3
    //   176: aload 11
    //   178: astore 9
    //   180: iload 7
    //   182: istore 5
    //   184: aload 12
    //   186: invokevirtual 299	java/io/BufferedReader:close	()V
    //   189: aload 11
    //   191: astore 9
    //   193: iload 7
    //   195: istore 5
    //   197: aload_3
    //   198: athrow
    //   199: astore_3
    //   200: ldc 50
    //   202: ldc_w 301
    //   205: aload_3
    //   206: invokestatic 304	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   209: pop
    //   210: iload 5
    //   212: istore 6
    //   214: aload 9
    //   216: astore 10
    //   218: iload_0
    //   219: aload_1
    //   220: aload_2
    //   221: aload 10
    //   223: iload 6
    //   225: invokestatic 310	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   228: aload 4
    //   230: invokestatic 154	org/apache/cordova/filetransfer/FileTransfer:createFileTransferError	(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Throwable;)Lorg/json/JSONObject;
    //   233: areturn
    //   234: aload 13
    //   236: invokevirtual 311	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   239: astore 10
    //   241: aload 10
    //   243: astore 9
    //   245: iload 7
    //   247: istore 5
    //   249: aload 12
    //   251: invokevirtual 299	java/io/BufferedReader:close	()V
    //   254: iload 7
    //   256: istore 6
    //   258: goto -40 -> 218
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	261	0	paramInt	int
    //   0	261	1	paramString1	String
    //   0	261	2	paramString2	String
    //   0	261	3	paramURLConnection	URLConnection
    //   0	261	4	paramThrowable	Throwable
    //   39	209	5	i	int
    //   27	230	6	j	int
    //   4	251	7	k	int
    //   1	45	8	m	int
    //   35	209	9	localObject1	Object
    //   23	219	10	localObject2	Object
    //   19	171	11	localObject3	Object
    //   16	234	12	localBufferedReader	java.io.BufferedReader
    //   13	222	13	localStringBuilder	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   129	135	175	finally
    //   139	153	175	finally
    //   161	169	175	finally
    //   234	241	175	finally
    //   49	56	199	java/lang/Throwable
    //   64	73	199	java/lang/Throwable
    //   81	89	199	java/lang/Throwable
    //   109	129	199	java/lang/Throwable
    //   184	189	199	java/lang/Throwable
    //   197	199	199	java/lang/Throwable
    //   249	254	199	java/lang/Throwable
  }
  
  private void download(final String paramString1, final String paramString2, JSONArray arg3, final CallbackContext paramCallbackContext)
    throws JSONException
  {
    Log.d("FileTransfer", "download " + paramString1 + " to " + paramString2);
    final CordovaResourceApi localCordovaResourceApi = this.webView.getResourceApi();
    final boolean bool3 = ???.optBoolean(2);
    final String str = ???.getString(3);
    final JSONObject localJSONObject = ???.optJSONObject(4);
    final Uri localUri1 = localCordovaResourceApi.remapUri(Uri.parse(paramString1));
    ??? = Uri.parse(paramString2);
    final Uri localUri2;
    int i;
    final boolean bool1;
    if (???.getScheme() != null)
    {
      localUri2 = localCordovaResourceApi.remapUri(???);
      i = CordovaResourceApi.getUriType(localUri1);
      if (i != 6) {
        break label210;
      }
      bool1 = true;
      label116:
      if ((bool1) || (i == 5)) {
        break label216;
      }
    }
    label210:
    label216:
    for (final boolean bool2 = true;; bool2 = false)
    {
      if (i != -1) {
        break label222;
      }
      paramString1 = createFileTransferError(INVALID_URL_ERR, paramString1, paramString2, null, Integer.valueOf(0), null);
      Log.e("FileTransfer", "Unsupported URI: " + localUri1);
      paramCallbackContext.sendPluginResult(new PluginResult(PluginResult.Status.IO_EXCEPTION, paramString1));
      return;
      ??? = Uri.fromFile(new File(paramString2));
      break;
      bool1 = false;
      break label116;
    }
    label222:
    Object localObject = null;
    if (bool2) {
      localObject = Boolean.valueOf(true);
    }
    ??? = (JSONArray)localObject;
    if (localObject == null) {}
    try
    {
      boolean bool4 = ((Whitelist)this.webView.getClass().getMethod("getWhitelist", new Class[0]).invoke(this.webView, new Object[0])).isUrlWhiteListed(paramString1);
      ??? = Boolean.valueOf(bool4);
    }
    catch (InvocationTargetException ???)
    {
      for (;;)
      {
        ??? = localJSONArray3;
      }
    }
    catch (IllegalAccessException ???)
    {
      for (;;)
      {
        ??? = localJSONArray3;
      }
    }
    catch (NoSuchMethodException ???)
    {
      for (;;)
      {
        JSONArray localJSONArray3;
        ??? = localJSONArray3;
      }
    }
    localObject = ???;
    if (??? == null) {}
    try
    {
      localObject = (PluginManager)this.webView.getClass().getMethod("getPluginManager", new Class[0]).invoke(this.webView, new Object[0]);
      localObject = (Boolean)localObject.getClass().getMethod("shouldAllowRequest", new Class[] { String.class }).invoke(localObject, new Object[] { paramString1 });
      if (!Boolean.TRUE.equals(localObject))
      {
        Log.w("FileTransfer", "Source URL is not in white list: '" + paramString1 + "'");
        paramString1 = createFileTransferError(CONNECTION_ERR, paramString1, paramString2, null, Integer.valueOf(401), null);
        paramCallbackContext.sendPluginResult(new PluginResult(PluginResult.Status.IO_EXCEPTION, paramString1));
        return;
      }
      paramCallbackContext = new RequestContext(paramString1, paramString2, paramCallbackContext);
      synchronized (activeRequests)
      {
        activeRequests.put(str, paramCallbackContext);
        this.cordova.getThreadPool().execute(new Runnable()
        {
          /* Error */
          public void run()
          {
            // Byte code:
            //   0: aload_0
            //   1: getfield 36	org/apache/cordova/filetransfer/FileTransfer$4:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
            //   4: getfield 82	org/apache/cordova/filetransfer/FileTransfer$RequestContext:aborted	Z
            //   7: ifeq +4 -> 11
            //   10: return
            //   11: aconst_null
            //   12: astore 13
            //   14: aconst_null
            //   15: astore 52
            //   17: aconst_null
            //   18: astore 53
            //   20: aconst_null
            //   21: astore 54
            //   23: aconst_null
            //   24: astore 55
            //   26: aconst_null
            //   27: astore 15
            //   29: aconst_null
            //   30: astore 50
            //   32: aconst_null
            //   33: astore 39
            //   35: aconst_null
            //   36: astore 40
            //   38: aconst_null
            //   39: astore 41
            //   41: aconst_null
            //   42: astore 42
            //   44: aconst_null
            //   45: astore 43
            //   47: aconst_null
            //   48: astore 14
            //   50: aconst_null
            //   51: astore 51
            //   53: aconst_null
            //   54: astore 45
            //   56: aconst_null
            //   57: astore 46
            //   59: aconst_null
            //   60: astore 47
            //   62: aconst_null
            //   63: astore 48
            //   65: aconst_null
            //   66: astore 49
            //   68: aconst_null
            //   69: astore 44
            //   71: aconst_null
            //   72: astore 28
            //   74: aconst_null
            //   75: astore 30
            //   77: aconst_null
            //   78: astore 32
            //   80: aconst_null
            //   81: astore 34
            //   83: aconst_null
            //   84: astore 27
            //   86: aconst_null
            //   87: astore 37
            //   89: aconst_null
            //   90: astore 38
            //   92: iconst_0
            //   93: istore 7
            //   95: iconst_0
            //   96: istore 8
            //   98: iconst_0
            //   99: istore 9
            //   101: iconst_0
            //   102: istore 10
            //   104: iconst_0
            //   105: istore 11
            //   107: iconst_0
            //   108: istore 6
            //   110: aconst_null
            //   111: astore 36
            //   113: iload 7
            //   115: istore_1
            //   116: aload 13
            //   118: astore 26
            //   120: aload 39
            //   122: astore 17
            //   124: aload 45
            //   126: astore 20
            //   128: iload 8
            //   130: istore_2
            //   131: aload 52
            //   133: astore 29
            //   135: aload 40
            //   137: astore 18
            //   139: aload 46
            //   141: astore 16
            //   143: iload 9
            //   145: istore_3
            //   146: aload 53
            //   148: astore 31
            //   150: aload 41
            //   152: astore 21
            //   154: aload 47
            //   156: astore 19
            //   158: iload 10
            //   160: istore 4
            //   162: aload 54
            //   164: astore 33
            //   166: aload 42
            //   168: astore 23
            //   170: aload 48
            //   172: astore 22
            //   174: iload 11
            //   176: istore 5
            //   178: aload 55
            //   180: astore 35
            //   182: aload 43
            //   184: astore 25
            //   186: aload 49
            //   188: astore 24
            //   190: aload_0
            //   191: getfield 38	org/apache/cordova/filetransfer/FileTransfer$4:val$resourceApi	Lorg/apache/cordova/CordovaResourceApi;
            //   194: aload_0
            //   195: getfield 40	org/apache/cordova/filetransfer/FileTransfer$4:val$targetUri	Landroid/net/Uri;
            //   198: invokevirtual 88	org/apache/cordova/CordovaResourceApi:mapUriToFile	(Landroid/net/Uri;)Ljava/io/File;
            //   201: astore 12
            //   203: iload 7
            //   205: istore_1
            //   206: aload 13
            //   208: astore 26
            //   210: aload 12
            //   212: astore 27
            //   214: aload 39
            //   216: astore 17
            //   218: aload 45
            //   220: astore 20
            //   222: iload 8
            //   224: istore_2
            //   225: aload 52
            //   227: astore 29
            //   229: aload 12
            //   231: astore 28
            //   233: aload 40
            //   235: astore 18
            //   237: aload 46
            //   239: astore 16
            //   241: iload 9
            //   243: istore_3
            //   244: aload 53
            //   246: astore 31
            //   248: aload 12
            //   250: astore 30
            //   252: aload 41
            //   254: astore 21
            //   256: aload 47
            //   258: astore 19
            //   260: iload 10
            //   262: istore 4
            //   264: aload 54
            //   266: astore 33
            //   268: aload 12
            //   270: astore 32
            //   272: aload 42
            //   274: astore 23
            //   276: aload 48
            //   278: astore 22
            //   280: iload 11
            //   282: istore 5
            //   284: aload 55
            //   286: astore 35
            //   288: aload 12
            //   290: astore 34
            //   292: aload 43
            //   294: astore 25
            //   296: aload 49
            //   298: astore 24
            //   300: aload_0
            //   301: getfield 36	org/apache/cordova/filetransfer/FileTransfer$4:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
            //   304: aload 12
            //   306: putfield 92	org/apache/cordova/filetransfer/FileTransfer$RequestContext:targetFile	Ljava/io/File;
            //   309: iload 7
            //   311: istore_1
            //   312: aload 13
            //   314: astore 26
            //   316: aload 12
            //   318: astore 27
            //   320: aload 39
            //   322: astore 17
            //   324: aload 45
            //   326: astore 20
            //   328: iload 8
            //   330: istore_2
            //   331: aload 52
            //   333: astore 29
            //   335: aload 12
            //   337: astore 28
            //   339: aload 40
            //   341: astore 18
            //   343: aload 46
            //   345: astore 16
            //   347: iload 9
            //   349: istore_3
            //   350: aload 53
            //   352: astore 31
            //   354: aload 12
            //   356: astore 30
            //   358: aload 41
            //   360: astore 21
            //   362: aload 47
            //   364: astore 19
            //   366: iload 10
            //   368: istore 4
            //   370: aload 54
            //   372: astore 33
            //   374: aload 12
            //   376: astore 32
            //   378: aload 42
            //   380: astore 23
            //   382: aload 48
            //   384: astore 22
            //   386: iload 11
            //   388: istore 5
            //   390: aload 55
            //   392: astore 35
            //   394: aload 12
            //   396: astore 34
            //   398: aload 43
            //   400: astore 25
            //   402: aload 49
            //   404: astore 24
            //   406: ldc 94
            //   408: new 96	java/lang/StringBuilder
            //   411: dup
            //   412: invokespecial 97	java/lang/StringBuilder:<init>	()V
            //   415: ldc 99
            //   417: invokevirtual 103	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   420: aload_0
            //   421: getfield 42	org/apache/cordova/filetransfer/FileTransfer$4:val$sourceUri	Landroid/net/Uri;
            //   424: invokevirtual 106	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
            //   427: invokevirtual 110	java/lang/StringBuilder:toString	()Ljava/lang/String;
            //   430: invokestatic 116	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
            //   433: pop
            //   434: iload 7
            //   436: istore_1
            //   437: aload 13
            //   439: astore 26
            //   441: aload 12
            //   443: astore 27
            //   445: aload 39
            //   447: astore 17
            //   449: aload 45
            //   451: astore 20
            //   453: iload 8
            //   455: istore_2
            //   456: aload 52
            //   458: astore 29
            //   460: aload 12
            //   462: astore 28
            //   464: aload 40
            //   466: astore 18
            //   468: aload 46
            //   470: astore 16
            //   472: iload 9
            //   474: istore_3
            //   475: aload 53
            //   477: astore 31
            //   479: aload 12
            //   481: astore 30
            //   483: aload 41
            //   485: astore 21
            //   487: aload 47
            //   489: astore 19
            //   491: iload 10
            //   493: istore 4
            //   495: aload 54
            //   497: astore 33
            //   499: aload 12
            //   501: astore 32
            //   503: aload 42
            //   505: astore 23
            //   507: aload 48
            //   509: astore 22
            //   511: iload 11
            //   513: istore 5
            //   515: aload 55
            //   517: astore 35
            //   519: aload 12
            //   521: astore 34
            //   523: aload 43
            //   525: astore 25
            //   527: aload 49
            //   529: astore 24
            //   531: new 118	org/apache/cordova/filetransfer/FileProgressResult
            //   534: dup
            //   535: invokespecial 119	org/apache/cordova/filetransfer/FileProgressResult:<init>	()V
            //   538: astore 56
            //   540: iload 7
            //   542: istore_1
            //   543: aload 13
            //   545: astore 26
            //   547: aload 12
            //   549: astore 27
            //   551: aload 39
            //   553: astore 17
            //   555: aload 45
            //   557: astore 20
            //   559: iload 8
            //   561: istore_2
            //   562: aload 52
            //   564: astore 29
            //   566: aload 12
            //   568: astore 28
            //   570: aload 40
            //   572: astore 18
            //   574: aload 46
            //   576: astore 16
            //   578: iload 9
            //   580: istore_3
            //   581: aload 53
            //   583: astore 31
            //   585: aload 12
            //   587: astore 30
            //   589: aload 41
            //   591: astore 21
            //   593: aload 47
            //   595: astore 19
            //   597: iload 10
            //   599: istore 4
            //   601: aload 54
            //   603: astore 33
            //   605: aload 12
            //   607: astore 32
            //   609: aload 42
            //   611: astore 23
            //   613: aload 48
            //   615: astore 22
            //   617: iload 11
            //   619: istore 5
            //   621: aload 55
            //   623: astore 35
            //   625: aload 12
            //   627: astore 34
            //   629: aload 43
            //   631: astore 25
            //   633: aload 49
            //   635: astore 24
            //   637: aload_0
            //   638: getfield 44	org/apache/cordova/filetransfer/FileTransfer$4:val$isLocalTransfer	Z
            //   641: ifeq +907 -> 1548
            //   644: iload 7
            //   646: istore_1
            //   647: aload 13
            //   649: astore 26
            //   651: aload 12
            //   653: astore 27
            //   655: aload 39
            //   657: astore 17
            //   659: aload 45
            //   661: astore 20
            //   663: iload 8
            //   665: istore_2
            //   666: aload 52
            //   668: astore 29
            //   670: aload 12
            //   672: astore 28
            //   674: aload 40
            //   676: astore 18
            //   678: aload 46
            //   680: astore 16
            //   682: iload 9
            //   684: istore_3
            //   685: aload 53
            //   687: astore 31
            //   689: aload 12
            //   691: astore 30
            //   693: aload 41
            //   695: astore 21
            //   697: aload 47
            //   699: astore 19
            //   701: iload 10
            //   703: istore 4
            //   705: aload 54
            //   707: astore 33
            //   709: aload 12
            //   711: astore 32
            //   713: aload 42
            //   715: astore 23
            //   717: aload 48
            //   719: astore 22
            //   721: iload 11
            //   723: istore 5
            //   725: aload 55
            //   727: astore 35
            //   729: aload 12
            //   731: astore 34
            //   733: aload 43
            //   735: astore 25
            //   737: aload 49
            //   739: astore 24
            //   741: aload_0
            //   742: getfield 38	org/apache/cordova/filetransfer/FileTransfer$4:val$resourceApi	Lorg/apache/cordova/CordovaResourceApi;
            //   745: aload_0
            //   746: getfield 42	org/apache/cordova/filetransfer/FileTransfer$4:val$sourceUri	Landroid/net/Uri;
            //   749: invokevirtual 123	org/apache/cordova/CordovaResourceApi:openForRead	(Landroid/net/Uri;)Lorg/apache/cordova/CordovaResourceApi$OpenForReadResult;
            //   752: astore 38
            //   754: iload 7
            //   756: istore_1
            //   757: aload 13
            //   759: astore 26
            //   761: aload 12
            //   763: astore 27
            //   765: aload 39
            //   767: astore 17
            //   769: aload 45
            //   771: astore 20
            //   773: iload 8
            //   775: istore_2
            //   776: aload 52
            //   778: astore 29
            //   780: aload 12
            //   782: astore 28
            //   784: aload 40
            //   786: astore 18
            //   788: aload 46
            //   790: astore 16
            //   792: iload 9
            //   794: istore_3
            //   795: aload 53
            //   797: astore 31
            //   799: aload 12
            //   801: astore 30
            //   803: aload 41
            //   805: astore 21
            //   807: aload 47
            //   809: astore 19
            //   811: iload 10
            //   813: istore 4
            //   815: aload 54
            //   817: astore 33
            //   819: aload 12
            //   821: astore 32
            //   823: aload 42
            //   825: astore 23
            //   827: aload 48
            //   829: astore 22
            //   831: iload 11
            //   833: istore 5
            //   835: aload 55
            //   837: astore 35
            //   839: aload 12
            //   841: astore 34
            //   843: aload 43
            //   845: astore 25
            //   847: aload 49
            //   849: astore 24
            //   851: aload 38
            //   853: getfield 129	org/apache/cordova/CordovaResourceApi$OpenForReadResult:length	J
            //   856: ldc2_w 130
            //   859: lcmp
            //   860: ifeq +213 -> 1073
            //   863: iload 7
            //   865: istore_1
            //   866: aload 13
            //   868: astore 26
            //   870: aload 12
            //   872: astore 27
            //   874: aload 39
            //   876: astore 17
            //   878: aload 45
            //   880: astore 20
            //   882: iload 8
            //   884: istore_2
            //   885: aload 52
            //   887: astore 29
            //   889: aload 12
            //   891: astore 28
            //   893: aload 40
            //   895: astore 18
            //   897: aload 46
            //   899: astore 16
            //   901: iload 9
            //   903: istore_3
            //   904: aload 53
            //   906: astore 31
            //   908: aload 12
            //   910: astore 30
            //   912: aload 41
            //   914: astore 21
            //   916: aload 47
            //   918: astore 19
            //   920: iload 10
            //   922: istore 4
            //   924: aload 54
            //   926: astore 33
            //   928: aload 12
            //   930: astore 32
            //   932: aload 42
            //   934: astore 23
            //   936: aload 48
            //   938: astore 22
            //   940: iload 11
            //   942: istore 5
            //   944: aload 55
            //   946: astore 35
            //   948: aload 12
            //   950: astore 34
            //   952: aload 43
            //   954: astore 25
            //   956: aload 49
            //   958: astore 24
            //   960: aload 56
            //   962: iconst_1
            //   963: invokevirtual 135	org/apache/cordova/filetransfer/FileProgressResult:setLengthComputable	(Z)V
            //   966: iload 7
            //   968: istore_1
            //   969: aload 13
            //   971: astore 26
            //   973: aload 12
            //   975: astore 27
            //   977: aload 39
            //   979: astore 17
            //   981: aload 45
            //   983: astore 20
            //   985: iload 8
            //   987: istore_2
            //   988: aload 52
            //   990: astore 29
            //   992: aload 12
            //   994: astore 28
            //   996: aload 40
            //   998: astore 18
            //   1000: aload 46
            //   1002: astore 16
            //   1004: iload 9
            //   1006: istore_3
            //   1007: aload 53
            //   1009: astore 31
            //   1011: aload 12
            //   1013: astore 30
            //   1015: aload 41
            //   1017: astore 21
            //   1019: aload 47
            //   1021: astore 19
            //   1023: iload 10
            //   1025: istore 4
            //   1027: aload 54
            //   1029: astore 33
            //   1031: aload 12
            //   1033: astore 32
            //   1035: aload 42
            //   1037: astore 23
            //   1039: aload 48
            //   1041: astore 22
            //   1043: iload 11
            //   1045: istore 5
            //   1047: aload 55
            //   1049: astore 35
            //   1051: aload 12
            //   1053: astore 34
            //   1055: aload 43
            //   1057: astore 25
            //   1059: aload 49
            //   1061: astore 24
            //   1063: aload 56
            //   1065: aload 38
            //   1067: getfield 129	org/apache/cordova/CordovaResourceApi$OpenForReadResult:length	J
            //   1070: invokevirtual 139	org/apache/cordova/filetransfer/FileProgressResult:setTotal	(J)V
            //   1073: iload 7
            //   1075: istore_1
            //   1076: aload 13
            //   1078: astore 26
            //   1080: aload 12
            //   1082: astore 27
            //   1084: aload 39
            //   1086: astore 17
            //   1088: aload 45
            //   1090: astore 20
            //   1092: iload 8
            //   1094: istore_2
            //   1095: aload 52
            //   1097: astore 29
            //   1099: aload 12
            //   1101: astore 28
            //   1103: aload 40
            //   1105: astore 18
            //   1107: aload 46
            //   1109: astore 16
            //   1111: iload 9
            //   1113: istore_3
            //   1114: aload 53
            //   1116: astore 31
            //   1118: aload 12
            //   1120: astore 30
            //   1122: aload 41
            //   1124: astore 21
            //   1126: aload 47
            //   1128: astore 19
            //   1130: iload 10
            //   1132: istore 4
            //   1134: aload 54
            //   1136: astore 33
            //   1138: aload 12
            //   1140: astore 32
            //   1142: aload 42
            //   1144: astore 23
            //   1146: aload 48
            //   1148: astore 22
            //   1150: iload 11
            //   1152: istore 5
            //   1154: aload 55
            //   1156: astore 35
            //   1158: aload 12
            //   1160: astore 34
            //   1162: aload 43
            //   1164: astore 25
            //   1166: aload 49
            //   1168: astore 24
            //   1170: new 141	org/apache/cordova/filetransfer/FileTransfer$SimpleTrackingInputStream
            //   1173: dup
            //   1174: aload 38
            //   1176: getfield 145	org/apache/cordova/CordovaResourceApi$OpenForReadResult:inputStream	Ljava/io/InputStream;
            //   1179: invokespecial 148	org/apache/cordova/filetransfer/FileTransfer$SimpleTrackingInputStream:<init>	(Ljava/io/InputStream;)V
            //   1182: astore 13
            //   1184: aload 13
            //   1186: astore 26
            //   1188: aconst_null
            //   1189: astore 13
            //   1191: aload 44
            //   1193: astore 17
            //   1195: iload 6
            //   1197: istore_1
            //   1198: iload_1
            //   1199: ifne +6067 -> 7266
            //   1202: aload 36
            //   1204: astore 27
            //   1206: aload_0
            //   1207: getfield 36	org/apache/cordova/filetransfer/FileTransfer$4:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
            //   1210: astore 16
            //   1212: aload 36
            //   1214: astore 27
            //   1216: aload 16
            //   1218: monitorenter
            //   1219: aload_0
            //   1220: getfield 36	org/apache/cordova/filetransfer/FileTransfer$4:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
            //   1223: getfield 82	org/apache/cordova/filetransfer/FileTransfer$RequestContext:aborted	Z
            //   1226: ifeq +3383 -> 4609
            //   1229: aload 16
            //   1231: monitorexit
            //   1232: aload 12
            //   1234: astore 22
            //   1236: aload 12
            //   1238: astore 23
            //   1240: aload 12
            //   1242: astore 24
            //   1244: aload 12
            //   1246: astore 25
            //   1248: iload_1
            //   1249: istore_2
            //   1250: aload 15
            //   1252: astore 20
            //   1254: aload 12
            //   1256: astore 16
            //   1258: aload 14
            //   1260: astore 19
            //   1262: aload 17
            //   1264: astore 18
            //   1266: aload 13
            //   1268: astore 21
            //   1270: aload_0
            //   1271: getfield 36	org/apache/cordova/filetransfer/FileTransfer$4:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
            //   1274: astore 27
            //   1276: aload 12
            //   1278: astore 22
            //   1280: aload 12
            //   1282: astore 23
            //   1284: aload 12
            //   1286: astore 24
            //   1288: aload 12
            //   1290: astore 25
            //   1292: iload_1
            //   1293: istore_2
            //   1294: aload 15
            //   1296: astore 20
            //   1298: aload 12
            //   1300: astore 16
            //   1302: aload 14
            //   1304: astore 19
            //   1306: aload 17
            //   1308: astore 18
            //   1310: aload 13
            //   1312: astore 21
            //   1314: aload 27
            //   1316: monitorenter
            //   1317: aload_0
            //   1318: getfield 36	org/apache/cordova/filetransfer/FileTransfer$4:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
            //   1321: aconst_null
            //   1322: putfield 152	org/apache/cordova/filetransfer/FileTransfer$RequestContext:connection	Ljava/net/HttpURLConnection;
            //   1325: aload 27
            //   1327: monitorexit
            //   1328: aload 12
            //   1330: astore 22
            //   1332: aload 12
            //   1334: astore 23
            //   1336: aload 12
            //   1338: astore 24
            //   1340: aload 12
            //   1342: astore 25
            //   1344: iload_1
            //   1345: istore_2
            //   1346: aload 15
            //   1348: astore 20
            //   1350: aload 12
            //   1352: astore 16
            //   1354: aload 14
            //   1356: astore 19
            //   1358: aload 17
            //   1360: astore 18
            //   1362: aload 13
            //   1364: astore 21
            //   1366: aload 26
            //   1368: invokestatic 156	org/apache/cordova/filetransfer/FileTransfer:access$400	(Ljava/io/Closeable;)V
            //   1371: aload 12
            //   1373: astore 22
            //   1375: aload 12
            //   1377: astore 23
            //   1379: aload 12
            //   1381: astore 24
            //   1383: aload 12
            //   1385: astore 25
            //   1387: iload_1
            //   1388: istore_2
            //   1389: aload 15
            //   1391: astore 20
            //   1393: aload 12
            //   1395: astore 16
            //   1397: aload 14
            //   1399: astore 19
            //   1401: aload 17
            //   1403: astore 18
            //   1405: aload 13
            //   1407: astore 21
            //   1409: aconst_null
            //   1410: invokestatic 156	org/apache/cordova/filetransfer/FileTransfer:access$400	(Ljava/io/Closeable;)V
            //   1413: invokestatic 160	org/apache/cordova/filetransfer/FileTransfer:access$700	()Ljava/util/HashMap;
            //   1416: astore 16
            //   1418: aload 16
            //   1420: monitorenter
            //   1421: invokestatic 160	org/apache/cordova/filetransfer/FileTransfer:access$700	()Ljava/util/HashMap;
            //   1424: aload_0
            //   1425: getfield 56	org/apache/cordova/filetransfer/FileTransfer$4:val$objectId	Ljava/lang/String;
            //   1428: invokevirtual 166	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
            //   1431: pop
            //   1432: aload 16
            //   1434: monitorexit
            //   1435: aload 15
            //   1437: ifnull +38 -> 1475
            //   1440: aload_0
            //   1441: getfield 48	org/apache/cordova/filetransfer/FileTransfer$4:val$trustEveryone	Z
            //   1444: ifeq +31 -> 1475
            //   1447: aload_0
            //   1448: getfield 46	org/apache/cordova/filetransfer/FileTransfer$4:val$useHttps	Z
            //   1451: ifeq +24 -> 1475
            //   1454: aload 15
            //   1456: checkcast 168	javax/net/ssl/HttpsURLConnection
            //   1459: astore 16
            //   1461: aload 16
            //   1463: aload 14
            //   1465: invokevirtual 172	javax/net/ssl/HttpsURLConnection:setHostnameVerifier	(Ljavax/net/ssl/HostnameVerifier;)V
            //   1468: aload 16
            //   1470: aload 17
            //   1472: invokevirtual 176	javax/net/ssl/HttpsURLConnection:setSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
            //   1475: aload 13
            //   1477: ifnonnull +5786 -> 7263
            //   1480: new 178	org/apache/cordova/PluginResult
            //   1483: dup
            //   1484: getstatic 184	org/apache/cordova/PluginResult$Status:ERROR	Lorg/apache/cordova/PluginResult$Status;
            //   1487: getstatic 188	org/apache/cordova/filetransfer/FileTransfer:CONNECTION_ERR	I
            //   1490: aload_0
            //   1491: getfield 52	org/apache/cordova/filetransfer/FileTransfer$4:val$source	Ljava/lang/String;
            //   1494: aload_0
            //   1495: getfield 54	org/apache/cordova/filetransfer/FileTransfer$4:val$target	Ljava/lang/String;
            //   1498: aload 15
            //   1500: aconst_null
            //   1501: invokestatic 192	org/apache/cordova/filetransfer/FileTransfer:access$600	(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;Ljava/lang/Throwable;)Lorg/json/JSONObject;
            //   1504: invokespecial 195	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V
            //   1507: astore 13
            //   1509: iload_1
            //   1510: ifne +28 -> 1538
            //   1513: aload 13
            //   1515: invokevirtual 199	org/apache/cordova/PluginResult:getStatus	()I
            //   1518: getstatic 202	org/apache/cordova/PluginResult$Status:OK	Lorg/apache/cordova/PluginResult$Status;
            //   1521: invokevirtual 205	org/apache/cordova/PluginResult$Status:ordinal	()I
            //   1524: if_icmpeq +14 -> 1538
            //   1527: aload 12
            //   1529: ifnull +9 -> 1538
            //   1532: aload 12
            //   1534: invokevirtual 211	java/io/File:delete	()Z
            //   1537: pop
            //   1538: aload_0
            //   1539: getfield 36	org/apache/cordova/filetransfer/FileTransfer$4:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
            //   1542: aload 13
            //   1544: invokevirtual 215	org/apache/cordova/filetransfer/FileTransfer$RequestContext:sendPluginResult	(Lorg/apache/cordova/PluginResult;)V
            //   1547: return
            //   1548: iload 7
            //   1550: istore_1
            //   1551: aload 13
            //   1553: astore 26
            //   1555: aload 12
            //   1557: astore 27
            //   1559: aload 39
            //   1561: astore 17
            //   1563: aload 45
            //   1565: astore 20
            //   1567: iload 8
            //   1569: istore_2
            //   1570: aload 52
            //   1572: astore 29
            //   1574: aload 12
            //   1576: astore 28
            //   1578: aload 40
            //   1580: astore 18
            //   1582: aload 46
            //   1584: astore 16
            //   1586: iload 9
            //   1588: istore_3
            //   1589: aload 53
            //   1591: astore 31
            //   1593: aload 12
            //   1595: astore 30
            //   1597: aload 41
            //   1599: astore 21
            //   1601: aload 47
            //   1603: astore 19
            //   1605: iload 10
            //   1607: istore 4
            //   1609: aload 54
            //   1611: astore 33
            //   1613: aload 12
            //   1615: astore 32
            //   1617: aload 42
            //   1619: astore 23
            //   1621: aload 48
            //   1623: astore 22
            //   1625: iload 11
            //   1627: istore 5
            //   1629: aload 55
            //   1631: astore 35
            //   1633: aload 12
            //   1635: astore 34
            //   1637: aload 43
            //   1639: astore 25
            //   1641: aload 49
            //   1643: astore 24
            //   1645: aload_0
            //   1646: getfield 38	org/apache/cordova/filetransfer/FileTransfer$4:val$resourceApi	Lorg/apache/cordova/CordovaResourceApi;
            //   1649: aload_0
            //   1650: getfield 42	org/apache/cordova/filetransfer/FileTransfer$4:val$sourceUri	Landroid/net/Uri;
            //   1653: invokevirtual 219	org/apache/cordova/CordovaResourceApi:createHttpConnection	(Landroid/net/Uri;)Ljava/net/HttpURLConnection;
            //   1656: astore 15
            //   1658: aload 50
            //   1660: astore 14
            //   1662: aload 51
            //   1664: astore 13
            //   1666: iload 7
            //   1668: istore_1
            //   1669: aload 15
            //   1671: astore 26
            //   1673: aload 12
            //   1675: astore 27
            //   1677: aload 39
            //   1679: astore 17
            //   1681: aload 45
            //   1683: astore 20
            //   1685: iload 8
            //   1687: istore_2
            //   1688: aload 15
            //   1690: astore 29
            //   1692: aload 12
            //   1694: astore 28
            //   1696: aload 40
            //   1698: astore 18
            //   1700: aload 46
            //   1702: astore 16
            //   1704: iload 9
            //   1706: istore_3
            //   1707: aload 15
            //   1709: astore 31
            //   1711: aload 12
            //   1713: astore 30
            //   1715: aload 41
            //   1717: astore 21
            //   1719: aload 47
            //   1721: astore 19
            //   1723: iload 10
            //   1725: istore 4
            //   1727: aload 15
            //   1729: astore 33
            //   1731: aload 12
            //   1733: astore 32
            //   1735: aload 42
            //   1737: astore 23
            //   1739: aload 48
            //   1741: astore 22
            //   1743: iload 11
            //   1745: istore 5
            //   1747: aload 15
            //   1749: astore 35
            //   1751: aload 12
            //   1753: astore 34
            //   1755: aload 43
            //   1757: astore 25
            //   1759: aload 49
            //   1761: astore 24
            //   1763: aload_0
            //   1764: getfield 46	org/apache/cordova/filetransfer/FileTransfer$4:val$useHttps	Z
            //   1767: ifeq +532 -> 2299
            //   1770: aload 50
            //   1772: astore 14
            //   1774: aload 51
            //   1776: astore 13
            //   1778: iload 7
            //   1780: istore_1
            //   1781: aload 15
            //   1783: astore 26
            //   1785: aload 12
            //   1787: astore 27
            //   1789: aload 39
            //   1791: astore 17
            //   1793: aload 45
            //   1795: astore 20
            //   1797: iload 8
            //   1799: istore_2
            //   1800: aload 15
            //   1802: astore 29
            //   1804: aload 12
            //   1806: astore 28
            //   1808: aload 40
            //   1810: astore 18
            //   1812: aload 46
            //   1814: astore 16
            //   1816: iload 9
            //   1818: istore_3
            //   1819: aload 15
            //   1821: astore 31
            //   1823: aload 12
            //   1825: astore 30
            //   1827: aload 41
            //   1829: astore 21
            //   1831: aload 47
            //   1833: astore 19
            //   1835: iload 10
            //   1837: istore 4
            //   1839: aload 15
            //   1841: astore 33
            //   1843: aload 12
            //   1845: astore 32
            //   1847: aload 42
            //   1849: astore 23
            //   1851: aload 48
            //   1853: astore 22
            //   1855: iload 11
            //   1857: istore 5
            //   1859: aload 15
            //   1861: astore 35
            //   1863: aload 12
            //   1865: astore 34
            //   1867: aload 43
            //   1869: astore 25
            //   1871: aload 49
            //   1873: astore 24
            //   1875: aload_0
            //   1876: getfield 48	org/apache/cordova/filetransfer/FileTransfer$4:val$trustEveryone	Z
            //   1879: ifeq +420 -> 2299
            //   1882: iload 7
            //   1884: istore_1
            //   1885: aload 15
            //   1887: astore 26
            //   1889: aload 12
            //   1891: astore 27
            //   1893: aload 39
            //   1895: astore 17
            //   1897: aload 45
            //   1899: astore 20
            //   1901: iload 8
            //   1903: istore_2
            //   1904: aload 15
            //   1906: astore 29
            //   1908: aload 12
            //   1910: astore 28
            //   1912: aload 40
            //   1914: astore 18
            //   1916: aload 46
            //   1918: astore 16
            //   1920: iload 9
            //   1922: istore_3
            //   1923: aload 15
            //   1925: astore 31
            //   1927: aload 12
            //   1929: astore 30
            //   1931: aload 41
            //   1933: astore 21
            //   1935: aload 47
            //   1937: astore 19
            //   1939: iload 10
            //   1941: istore 4
            //   1943: aload 15
            //   1945: astore 33
            //   1947: aload 12
            //   1949: astore 32
            //   1951: aload 42
            //   1953: astore 23
            //   1955: aload 48
            //   1957: astore 22
            //   1959: iload 11
            //   1961: istore 5
            //   1963: aload 15
            //   1965: astore 35
            //   1967: aload 12
            //   1969: astore 34
            //   1971: aload 43
            //   1973: astore 25
            //   1975: aload 49
            //   1977: astore 24
            //   1979: aload 15
            //   1981: checkcast 168	javax/net/ssl/HttpsURLConnection
            //   1984: astore 44
            //   1986: iload 7
            //   1988: istore_1
            //   1989: aload 15
            //   1991: astore 26
            //   1993: aload 12
            //   1995: astore 27
            //   1997: aload 39
            //   1999: astore 17
            //   2001: aload 45
            //   2003: astore 20
            //   2005: iload 8
            //   2007: istore_2
            //   2008: aload 15
            //   2010: astore 29
            //   2012: aload 12
            //   2014: astore 28
            //   2016: aload 40
            //   2018: astore 18
            //   2020: aload 46
            //   2022: astore 16
            //   2024: iload 9
            //   2026: istore_3
            //   2027: aload 15
            //   2029: astore 31
            //   2031: aload 12
            //   2033: astore 30
            //   2035: aload 41
            //   2037: astore 21
            //   2039: aload 47
            //   2041: astore 19
            //   2043: iload 10
            //   2045: istore 4
            //   2047: aload 15
            //   2049: astore 33
            //   2051: aload 12
            //   2053: astore 32
            //   2055: aload 42
            //   2057: astore 23
            //   2059: aload 48
            //   2061: astore 22
            //   2063: iload 11
            //   2065: istore 5
            //   2067: aload 15
            //   2069: astore 35
            //   2071: aload 12
            //   2073: astore 34
            //   2075: aload 43
            //   2077: astore 25
            //   2079: aload 49
            //   2081: astore 24
            //   2083: aload 44
            //   2085: invokestatic 223	org/apache/cordova/filetransfer/FileTransfer:access$000	(Ljavax/net/ssl/HttpsURLConnection;)Ljavax/net/ssl/SSLSocketFactory;
            //   2088: astore 13
            //   2090: iload 7
            //   2092: istore_1
            //   2093: aload 15
            //   2095: astore 26
            //   2097: aload 12
            //   2099: astore 27
            //   2101: aload 39
            //   2103: astore 17
            //   2105: aload 13
            //   2107: astore 20
            //   2109: iload 8
            //   2111: istore_2
            //   2112: aload 15
            //   2114: astore 29
            //   2116: aload 12
            //   2118: astore 28
            //   2120: aload 40
            //   2122: astore 18
            //   2124: aload 13
            //   2126: astore 16
            //   2128: iload 9
            //   2130: istore_3
            //   2131: aload 15
            //   2133: astore 31
            //   2135: aload 12
            //   2137: astore 30
            //   2139: aload 41
            //   2141: astore 21
            //   2143: aload 13
            //   2145: astore 19
            //   2147: iload 10
            //   2149: istore 4
            //   2151: aload 15
            //   2153: astore 33
            //   2155: aload 12
            //   2157: astore 32
            //   2159: aload 42
            //   2161: astore 23
            //   2163: aload 13
            //   2165: astore 22
            //   2167: iload 11
            //   2169: istore 5
            //   2171: aload 15
            //   2173: astore 35
            //   2175: aload 12
            //   2177: astore 34
            //   2179: aload 43
            //   2181: astore 25
            //   2183: aload 13
            //   2185: astore 24
            //   2187: aload 44
            //   2189: invokevirtual 227	javax/net/ssl/HttpsURLConnection:getHostnameVerifier	()Ljavax/net/ssl/HostnameVerifier;
            //   2192: astore 14
            //   2194: iload 7
            //   2196: istore_1
            //   2197: aload 15
            //   2199: astore 26
            //   2201: aload 12
            //   2203: astore 27
            //   2205: aload 14
            //   2207: astore 17
            //   2209: aload 13
            //   2211: astore 20
            //   2213: iload 8
            //   2215: istore_2
            //   2216: aload 15
            //   2218: astore 29
            //   2220: aload 12
            //   2222: astore 28
            //   2224: aload 14
            //   2226: astore 18
            //   2228: aload 13
            //   2230: astore 16
            //   2232: iload 9
            //   2234: istore_3
            //   2235: aload 15
            //   2237: astore 31
            //   2239: aload 12
            //   2241: astore 30
            //   2243: aload 14
            //   2245: astore 21
            //   2247: aload 13
            //   2249: astore 19
            //   2251: iload 10
            //   2253: istore 4
            //   2255: aload 15
            //   2257: astore 33
            //   2259: aload 12
            //   2261: astore 32
            //   2263: aload 14
            //   2265: astore 23
            //   2267: aload 13
            //   2269: astore 22
            //   2271: iload 11
            //   2273: istore 5
            //   2275: aload 15
            //   2277: astore 35
            //   2279: aload 12
            //   2281: astore 34
            //   2283: aload 14
            //   2285: astore 25
            //   2287: aload 13
            //   2289: astore 24
            //   2291: aload 44
            //   2293: invokestatic 230	org/apache/cordova/filetransfer/FileTransfer:access$100	()Ljavax/net/ssl/HostnameVerifier;
            //   2296: invokevirtual 172	javax/net/ssl/HttpsURLConnection:setHostnameVerifier	(Ljavax/net/ssl/HostnameVerifier;)V
            //   2299: iload 7
            //   2301: istore_1
            //   2302: aload 15
            //   2304: astore 26
            //   2306: aload 12
            //   2308: astore 27
            //   2310: aload 14
            //   2312: astore 17
            //   2314: aload 13
            //   2316: astore 20
            //   2318: iload 8
            //   2320: istore_2
            //   2321: aload 15
            //   2323: astore 29
            //   2325: aload 12
            //   2327: astore 28
            //   2329: aload 14
            //   2331: astore 18
            //   2333: aload 13
            //   2335: astore 16
            //   2337: iload 9
            //   2339: istore_3
            //   2340: aload 15
            //   2342: astore 31
            //   2344: aload 12
            //   2346: astore 30
            //   2348: aload 14
            //   2350: astore 21
            //   2352: aload 13
            //   2354: astore 19
            //   2356: iload 10
            //   2358: istore 4
            //   2360: aload 15
            //   2362: astore 33
            //   2364: aload 12
            //   2366: astore 32
            //   2368: aload 14
            //   2370: astore 23
            //   2372: aload 13
            //   2374: astore 22
            //   2376: iload 11
            //   2378: istore 5
            //   2380: aload 15
            //   2382: astore 35
            //   2384: aload 12
            //   2386: astore 34
            //   2388: aload 14
            //   2390: astore 25
            //   2392: aload 13
            //   2394: astore 24
            //   2396: aload 15
            //   2398: ldc -24
            //   2400: invokevirtual 238	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
            //   2403: iload 7
            //   2405: istore_1
            //   2406: aload 15
            //   2408: astore 26
            //   2410: aload 12
            //   2412: astore 27
            //   2414: aload 14
            //   2416: astore 17
            //   2418: aload 13
            //   2420: astore 20
            //   2422: iload 8
            //   2424: istore_2
            //   2425: aload 15
            //   2427: astore 29
            //   2429: aload 12
            //   2431: astore 28
            //   2433: aload 14
            //   2435: astore 18
            //   2437: aload 13
            //   2439: astore 16
            //   2441: iload 9
            //   2443: istore_3
            //   2444: aload 15
            //   2446: astore 31
            //   2448: aload 12
            //   2450: astore 30
            //   2452: aload 14
            //   2454: astore 21
            //   2456: aload 13
            //   2458: astore 19
            //   2460: iload 10
            //   2462: istore 4
            //   2464: aload 15
            //   2466: astore 33
            //   2468: aload 12
            //   2470: astore 32
            //   2472: aload 14
            //   2474: astore 23
            //   2476: aload 13
            //   2478: astore 22
            //   2480: iload 11
            //   2482: istore 5
            //   2484: aload 15
            //   2486: astore 35
            //   2488: aload 12
            //   2490: astore 34
            //   2492: aload 14
            //   2494: astore 25
            //   2496: aload 13
            //   2498: astore 24
            //   2500: aload_0
            //   2501: getfield 34	org/apache/cordova/filetransfer/FileTransfer$4:this$0	Lorg/apache/cordova/filetransfer/FileTransfer;
            //   2504: aload_0
            //   2505: getfield 42	org/apache/cordova/filetransfer/FileTransfer$4:val$sourceUri	Landroid/net/Uri;
            //   2508: invokevirtual 241	android/net/Uri:toString	()Ljava/lang/String;
            //   2511: invokestatic 245	org/apache/cordova/filetransfer/FileTransfer:access$200	(Lorg/apache/cordova/filetransfer/FileTransfer;Ljava/lang/String;)Ljava/lang/String;
            //   2514: astore 39
            //   2516: aload 39
            //   2518: ifnull +109 -> 2627
            //   2521: iload 7
            //   2523: istore_1
            //   2524: aload 15
            //   2526: astore 26
            //   2528: aload 12
            //   2530: astore 27
            //   2532: aload 14
            //   2534: astore 17
            //   2536: aload 13
            //   2538: astore 20
            //   2540: iload 8
            //   2542: istore_2
            //   2543: aload 15
            //   2545: astore 29
            //   2547: aload 12
            //   2549: astore 28
            //   2551: aload 14
            //   2553: astore 18
            //   2555: aload 13
            //   2557: astore 16
            //   2559: iload 9
            //   2561: istore_3
            //   2562: aload 15
            //   2564: astore 31
            //   2566: aload 12
            //   2568: astore 30
            //   2570: aload 14
            //   2572: astore 21
            //   2574: aload 13
            //   2576: astore 19
            //   2578: iload 10
            //   2580: istore 4
            //   2582: aload 15
            //   2584: astore 33
            //   2586: aload 12
            //   2588: astore 32
            //   2590: aload 14
            //   2592: astore 23
            //   2594: aload 13
            //   2596: astore 22
            //   2598: iload 11
            //   2600: istore 5
            //   2602: aload 15
            //   2604: astore 35
            //   2606: aload 12
            //   2608: astore 34
            //   2610: aload 14
            //   2612: astore 25
            //   2614: aload 13
            //   2616: astore 24
            //   2618: aload 15
            //   2620: ldc -9
            //   2622: aload 39
            //   2624: invokevirtual 251	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
            //   2627: iload 7
            //   2629: istore_1
            //   2630: aload 15
            //   2632: astore 26
            //   2634: aload 12
            //   2636: astore 27
            //   2638: aload 14
            //   2640: astore 17
            //   2642: aload 13
            //   2644: astore 20
            //   2646: iload 8
            //   2648: istore_2
            //   2649: aload 15
            //   2651: astore 29
            //   2653: aload 12
            //   2655: astore 28
            //   2657: aload 14
            //   2659: astore 18
            //   2661: aload 13
            //   2663: astore 16
            //   2665: iload 9
            //   2667: istore_3
            //   2668: aload 15
            //   2670: astore 31
            //   2672: aload 12
            //   2674: astore 30
            //   2676: aload 14
            //   2678: astore 21
            //   2680: aload 13
            //   2682: astore 19
            //   2684: iload 10
            //   2686: istore 4
            //   2688: aload 15
            //   2690: astore 33
            //   2692: aload 12
            //   2694: astore 32
            //   2696: aload 14
            //   2698: astore 23
            //   2700: aload 13
            //   2702: astore 22
            //   2704: iload 11
            //   2706: istore 5
            //   2708: aload 15
            //   2710: astore 35
            //   2712: aload 12
            //   2714: astore 34
            //   2716: aload 14
            //   2718: astore 25
            //   2720: aload 13
            //   2722: astore 24
            //   2724: aload 15
            //   2726: ldc -3
            //   2728: ldc -1
            //   2730: invokevirtual 251	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
            //   2733: iload 7
            //   2735: istore_1
            //   2736: aload 15
            //   2738: astore 26
            //   2740: aload 12
            //   2742: astore 27
            //   2744: aload 14
            //   2746: astore 17
            //   2748: aload 13
            //   2750: astore 20
            //   2752: iload 8
            //   2754: istore_2
            //   2755: aload 15
            //   2757: astore 29
            //   2759: aload 12
            //   2761: astore 28
            //   2763: aload 14
            //   2765: astore 18
            //   2767: aload 13
            //   2769: astore 16
            //   2771: iload 9
            //   2773: istore_3
            //   2774: aload 15
            //   2776: astore 31
            //   2778: aload 12
            //   2780: astore 30
            //   2782: aload 14
            //   2784: astore 21
            //   2786: aload 13
            //   2788: astore 19
            //   2790: iload 10
            //   2792: istore 4
            //   2794: aload 15
            //   2796: astore 33
            //   2798: aload 12
            //   2800: astore 32
            //   2802: aload 14
            //   2804: astore 23
            //   2806: aload 13
            //   2808: astore 22
            //   2810: iload 11
            //   2812: istore 5
            //   2814: aload 15
            //   2816: astore 35
            //   2818: aload 12
            //   2820: astore 34
            //   2822: aload 14
            //   2824: astore 25
            //   2826: aload 13
            //   2828: astore 24
            //   2830: aload_0
            //   2831: getfield 50	org/apache/cordova/filetransfer/FileTransfer$4:val$headers	Lorg/json/JSONObject;
            //   2834: ifnull +109 -> 2943
            //   2837: iload 7
            //   2839: istore_1
            //   2840: aload 15
            //   2842: astore 26
            //   2844: aload 12
            //   2846: astore 27
            //   2848: aload 14
            //   2850: astore 17
            //   2852: aload 13
            //   2854: astore 20
            //   2856: iload 8
            //   2858: istore_2
            //   2859: aload 15
            //   2861: astore 29
            //   2863: aload 12
            //   2865: astore 28
            //   2867: aload 14
            //   2869: astore 18
            //   2871: aload 13
            //   2873: astore 16
            //   2875: iload 9
            //   2877: istore_3
            //   2878: aload 15
            //   2880: astore 31
            //   2882: aload 12
            //   2884: astore 30
            //   2886: aload 14
            //   2888: astore 21
            //   2890: aload 13
            //   2892: astore 19
            //   2894: iload 10
            //   2896: istore 4
            //   2898: aload 15
            //   2900: astore 33
            //   2902: aload 12
            //   2904: astore 32
            //   2906: aload 14
            //   2908: astore 23
            //   2910: aload 13
            //   2912: astore 22
            //   2914: iload 11
            //   2916: istore 5
            //   2918: aload 15
            //   2920: astore 35
            //   2922: aload 12
            //   2924: astore 34
            //   2926: aload 14
            //   2928: astore 25
            //   2930: aload 13
            //   2932: astore 24
            //   2934: aload 15
            //   2936: aload_0
            //   2937: getfield 50	org/apache/cordova/filetransfer/FileTransfer$4:val$headers	Lorg/json/JSONObject;
            //   2940: invokestatic 259	org/apache/cordova/filetransfer/FileTransfer:access$300	(Ljava/net/URLConnection;Lorg/json/JSONObject;)V
            //   2943: iload 7
            //   2945: istore_1
            //   2946: aload 15
            //   2948: astore 26
            //   2950: aload 12
            //   2952: astore 27
            //   2954: aload 14
            //   2956: astore 17
            //   2958: aload 13
            //   2960: astore 20
            //   2962: iload 8
            //   2964: istore_2
            //   2965: aload 15
            //   2967: astore 29
            //   2969: aload 12
            //   2971: astore 28
            //   2973: aload 14
            //   2975: astore 18
            //   2977: aload 13
            //   2979: astore 16
            //   2981: iload 9
            //   2983: istore_3
            //   2984: aload 15
            //   2986: astore 31
            //   2988: aload 12
            //   2990: astore 30
            //   2992: aload 14
            //   2994: astore 21
            //   2996: aload 13
            //   2998: astore 19
            //   3000: iload 10
            //   3002: istore 4
            //   3004: aload 15
            //   3006: astore 33
            //   3008: aload 12
            //   3010: astore 32
            //   3012: aload 14
            //   3014: astore 23
            //   3016: aload 13
            //   3018: astore 22
            //   3020: iload 11
            //   3022: istore 5
            //   3024: aload 15
            //   3026: astore 35
            //   3028: aload 12
            //   3030: astore 34
            //   3032: aload 14
            //   3034: astore 25
            //   3036: aload 13
            //   3038: astore 24
            //   3040: aload 15
            //   3042: invokevirtual 262	java/net/HttpURLConnection:connect	()V
            //   3045: iload 7
            //   3047: istore_1
            //   3048: aload 15
            //   3050: astore 26
            //   3052: aload 12
            //   3054: astore 27
            //   3056: aload 14
            //   3058: astore 17
            //   3060: aload 13
            //   3062: astore 20
            //   3064: iload 8
            //   3066: istore_2
            //   3067: aload 15
            //   3069: astore 29
            //   3071: aload 12
            //   3073: astore 28
            //   3075: aload 14
            //   3077: astore 18
            //   3079: aload 13
            //   3081: astore 16
            //   3083: iload 9
            //   3085: istore_3
            //   3086: aload 15
            //   3088: astore 31
            //   3090: aload 12
            //   3092: astore 30
            //   3094: aload 14
            //   3096: astore 21
            //   3098: aload 13
            //   3100: astore 19
            //   3102: iload 10
            //   3104: istore 4
            //   3106: aload 15
            //   3108: astore 33
            //   3110: aload 12
            //   3112: astore 32
            //   3114: aload 14
            //   3116: astore 23
            //   3118: aload 13
            //   3120: astore 22
            //   3122: iload 11
            //   3124: istore 5
            //   3126: aload 15
            //   3128: astore 35
            //   3130: aload 12
            //   3132: astore 34
            //   3134: aload 14
            //   3136: astore 25
            //   3138: aload 13
            //   3140: astore 24
            //   3142: aload 15
            //   3144: invokevirtual 265	java/net/HttpURLConnection:getResponseCode	()I
            //   3147: sipush 304
            //   3150: if_icmpne +494 -> 3644
            //   3153: iconst_1
            //   3154: istore 7
            //   3156: iconst_1
            //   3157: istore 8
            //   3159: iconst_1
            //   3160: istore 9
            //   3162: iconst_1
            //   3163: istore 10
            //   3165: iconst_1
            //   3166: istore 11
            //   3168: iconst_1
            //   3169: istore 6
            //   3171: iload 7
            //   3173: istore_1
            //   3174: aload 15
            //   3176: astore 26
            //   3178: aload 12
            //   3180: astore 27
            //   3182: aload 14
            //   3184: astore 17
            //   3186: aload 13
            //   3188: astore 20
            //   3190: iload 8
            //   3192: istore_2
            //   3193: aload 15
            //   3195: astore 29
            //   3197: aload 12
            //   3199: astore 28
            //   3201: aload 14
            //   3203: astore 18
            //   3205: aload 13
            //   3207: astore 16
            //   3209: iload 9
            //   3211: istore_3
            //   3212: aload 15
            //   3214: astore 31
            //   3216: aload 12
            //   3218: astore 30
            //   3220: aload 14
            //   3222: astore 21
            //   3224: aload 13
            //   3226: astore 19
            //   3228: iload 10
            //   3230: istore 4
            //   3232: aload 15
            //   3234: astore 33
            //   3236: aload 12
            //   3238: astore 32
            //   3240: aload 14
            //   3242: astore 23
            //   3244: aload 13
            //   3246: astore 22
            //   3248: iload 11
            //   3250: istore 5
            //   3252: aload 15
            //   3254: astore 35
            //   3256: aload 12
            //   3258: astore 34
            //   3260: aload 14
            //   3262: astore 25
            //   3264: aload 13
            //   3266: astore 24
            //   3268: aload 15
            //   3270: invokevirtual 268	java/net/HttpURLConnection:disconnect	()V
            //   3273: iload 7
            //   3275: istore_1
            //   3276: aload 15
            //   3278: astore 26
            //   3280: aload 12
            //   3282: astore 27
            //   3284: aload 14
            //   3286: astore 17
            //   3288: aload 13
            //   3290: astore 20
            //   3292: iload 8
            //   3294: istore_2
            //   3295: aload 15
            //   3297: astore 29
            //   3299: aload 12
            //   3301: astore 28
            //   3303: aload 14
            //   3305: astore 18
            //   3307: aload 13
            //   3309: astore 16
            //   3311: iload 9
            //   3313: istore_3
            //   3314: aload 15
            //   3316: astore 31
            //   3318: aload 12
            //   3320: astore 30
            //   3322: aload 14
            //   3324: astore 21
            //   3326: aload 13
            //   3328: astore 19
            //   3330: iload 10
            //   3332: istore 4
            //   3334: aload 15
            //   3336: astore 33
            //   3338: aload 12
            //   3340: astore 32
            //   3342: aload 14
            //   3344: astore 23
            //   3346: aload 13
            //   3348: astore 22
            //   3350: iload 11
            //   3352: istore 5
            //   3354: aload 15
            //   3356: astore 35
            //   3358: aload 12
            //   3360: astore 34
            //   3362: aload 14
            //   3364: astore 25
            //   3366: aload 13
            //   3368: astore 24
            //   3370: ldc 94
            //   3372: new 96	java/lang/StringBuilder
            //   3375: dup
            //   3376: invokespecial 97	java/lang/StringBuilder:<init>	()V
            //   3379: ldc_w 270
            //   3382: invokevirtual 103	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   3385: aload_0
            //   3386: getfield 52	org/apache/cordova/filetransfer/FileTransfer$4:val$source	Ljava/lang/String;
            //   3389: invokevirtual 103	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   3392: invokevirtual 110	java/lang/StringBuilder:toString	()Ljava/lang/String;
            //   3395: invokestatic 116	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
            //   3398: pop
            //   3399: iload 7
            //   3401: istore_1
            //   3402: aload 15
            //   3404: astore 26
            //   3406: aload 12
            //   3408: astore 27
            //   3410: aload 14
            //   3412: astore 17
            //   3414: aload 13
            //   3416: astore 20
            //   3418: iload 8
            //   3420: istore_2
            //   3421: aload 15
            //   3423: astore 29
            //   3425: aload 12
            //   3427: astore 28
            //   3429: aload 14
            //   3431: astore 18
            //   3433: aload 13
            //   3435: astore 16
            //   3437: iload 9
            //   3439: istore_3
            //   3440: aload 15
            //   3442: astore 31
            //   3444: aload 12
            //   3446: astore 30
            //   3448: aload 14
            //   3450: astore 21
            //   3452: aload 13
            //   3454: astore 19
            //   3456: iload 10
            //   3458: istore 4
            //   3460: aload 15
            //   3462: astore 33
            //   3464: aload 12
            //   3466: astore 32
            //   3468: aload 14
            //   3470: astore 23
            //   3472: aload 13
            //   3474: astore 22
            //   3476: iload 11
            //   3478: istore 5
            //   3480: aload 15
            //   3482: astore 35
            //   3484: aload 12
            //   3486: astore 34
            //   3488: aload 14
            //   3490: astore 25
            //   3492: aload 13
            //   3494: astore 24
            //   3496: getstatic 273	org/apache/cordova/filetransfer/FileTransfer:NOT_MODIFIED_ERR	I
            //   3499: aload_0
            //   3500: getfield 52	org/apache/cordova/filetransfer/FileTransfer$4:val$source	Ljava/lang/String;
            //   3503: aload_0
            //   3504: getfield 54	org/apache/cordova/filetransfer/FileTransfer$4:val$target	Ljava/lang/String;
            //   3507: aload 15
            //   3509: aconst_null
            //   3510: invokestatic 192	org/apache/cordova/filetransfer/FileTransfer:access$600	(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;Ljava/lang/Throwable;)Lorg/json/JSONObject;
            //   3513: astore 39
            //   3515: iload 7
            //   3517: istore_1
            //   3518: aload 15
            //   3520: astore 26
            //   3522: aload 12
            //   3524: astore 27
            //   3526: aload 14
            //   3528: astore 17
            //   3530: aload 13
            //   3532: astore 20
            //   3534: iload 8
            //   3536: istore_2
            //   3537: aload 15
            //   3539: astore 29
            //   3541: aload 12
            //   3543: astore 28
            //   3545: aload 14
            //   3547: astore 18
            //   3549: aload 13
            //   3551: astore 16
            //   3553: iload 9
            //   3555: istore_3
            //   3556: aload 15
            //   3558: astore 31
            //   3560: aload 12
            //   3562: astore 30
            //   3564: aload 14
            //   3566: astore 21
            //   3568: aload 13
            //   3570: astore 19
            //   3572: iload 10
            //   3574: istore 4
            //   3576: aload 15
            //   3578: astore 33
            //   3580: aload 12
            //   3582: astore 32
            //   3584: aload 14
            //   3586: astore 23
            //   3588: aload 13
            //   3590: astore 22
            //   3592: iload 11
            //   3594: istore 5
            //   3596: aload 15
            //   3598: astore 35
            //   3600: aload 12
            //   3602: astore 34
            //   3604: aload 14
            //   3606: astore 25
            //   3608: aload 13
            //   3610: astore 24
            //   3612: new 178	org/apache/cordova/PluginResult
            //   3615: dup
            //   3616: getstatic 184	org/apache/cordova/PluginResult$Status:ERROR	Lorg/apache/cordova/PluginResult$Status;
            //   3619: aload 39
            //   3621: invokespecial 195	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V
            //   3624: astore 39
            //   3626: iload 6
            //   3628: istore_1
            //   3629: aload 38
            //   3631: astore 26
            //   3633: aload 13
            //   3635: astore 17
            //   3637: aload 39
            //   3639: astore 13
            //   3641: goto -2443 -> 1198
            //   3644: iload 7
            //   3646: istore_1
            //   3647: aload 15
            //   3649: astore 26
            //   3651: aload 12
            //   3653: astore 27
            //   3655: aload 14
            //   3657: astore 17
            //   3659: aload 13
            //   3661: astore 20
            //   3663: iload 8
            //   3665: istore_2
            //   3666: aload 15
            //   3668: astore 29
            //   3670: aload 12
            //   3672: astore 28
            //   3674: aload 14
            //   3676: astore 18
            //   3678: aload 13
            //   3680: astore 16
            //   3682: iload 9
            //   3684: istore_3
            //   3685: aload 15
            //   3687: astore 31
            //   3689: aload 12
            //   3691: astore 30
            //   3693: aload 14
            //   3695: astore 21
            //   3697: aload 13
            //   3699: astore 19
            //   3701: iload 10
            //   3703: istore 4
            //   3705: aload 15
            //   3707: astore 33
            //   3709: aload 12
            //   3711: astore 32
            //   3713: aload 14
            //   3715: astore 23
            //   3717: aload 13
            //   3719: astore 22
            //   3721: iload 11
            //   3723: istore 5
            //   3725: aload 15
            //   3727: astore 35
            //   3729: aload 12
            //   3731: astore 34
            //   3733: aload 14
            //   3735: astore 25
            //   3737: aload 13
            //   3739: astore 24
            //   3741: aload 15
            //   3743: invokevirtual 276	java/net/HttpURLConnection:getContentEncoding	()Ljava/lang/String;
            //   3746: ifnull +113 -> 3859
            //   3749: iload 7
            //   3751: istore_1
            //   3752: aload 15
            //   3754: astore 26
            //   3756: aload 12
            //   3758: astore 27
            //   3760: aload 14
            //   3762: astore 17
            //   3764: aload 13
            //   3766: astore 20
            //   3768: iload 8
            //   3770: istore_2
            //   3771: aload 15
            //   3773: astore 29
            //   3775: aload 12
            //   3777: astore 28
            //   3779: aload 14
            //   3781: astore 18
            //   3783: aload 13
            //   3785: astore 16
            //   3787: iload 9
            //   3789: istore_3
            //   3790: aload 15
            //   3792: astore 31
            //   3794: aload 12
            //   3796: astore 30
            //   3798: aload 14
            //   3800: astore 21
            //   3802: aload 13
            //   3804: astore 19
            //   3806: iload 10
            //   3808: istore 4
            //   3810: aload 15
            //   3812: astore 33
            //   3814: aload 12
            //   3816: astore 32
            //   3818: aload 14
            //   3820: astore 23
            //   3822: aload 13
            //   3824: astore 22
            //   3826: iload 11
            //   3828: istore 5
            //   3830: aload 15
            //   3832: astore 35
            //   3834: aload 12
            //   3836: astore 34
            //   3838: aload 14
            //   3840: astore 25
            //   3842: aload 13
            //   3844: astore 24
            //   3846: aload 15
            //   3848: invokevirtual 276	java/net/HttpURLConnection:getContentEncoding	()Ljava/lang/String;
            //   3851: ldc -1
            //   3853: invokevirtual 282	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
            //   3856: ifeq +320 -> 4176
            //   3859: iload 7
            //   3861: istore_1
            //   3862: aload 15
            //   3864: astore 26
            //   3866: aload 12
            //   3868: astore 27
            //   3870: aload 14
            //   3872: astore 17
            //   3874: aload 13
            //   3876: astore 20
            //   3878: iload 8
            //   3880: istore_2
            //   3881: aload 15
            //   3883: astore 29
            //   3885: aload 12
            //   3887: astore 28
            //   3889: aload 14
            //   3891: astore 18
            //   3893: aload 13
            //   3895: astore 16
            //   3897: iload 9
            //   3899: istore_3
            //   3900: aload 15
            //   3902: astore 31
            //   3904: aload 12
            //   3906: astore 30
            //   3908: aload 14
            //   3910: astore 21
            //   3912: aload 13
            //   3914: astore 19
            //   3916: iload 10
            //   3918: istore 4
            //   3920: aload 15
            //   3922: astore 33
            //   3924: aload 12
            //   3926: astore 32
            //   3928: aload 14
            //   3930: astore 23
            //   3932: aload 13
            //   3934: astore 22
            //   3936: iload 11
            //   3938: istore 5
            //   3940: aload 15
            //   3942: astore 35
            //   3944: aload 12
            //   3946: astore 34
            //   3948: aload 14
            //   3950: astore 25
            //   3952: aload 13
            //   3954: astore 24
            //   3956: aload 15
            //   3958: invokevirtual 285	java/net/HttpURLConnection:getContentLength	()I
            //   3961: iconst_m1
            //   3962: if_icmpeq +214 -> 4176
            //   3965: iload 7
            //   3967: istore_1
            //   3968: aload 15
            //   3970: astore 26
            //   3972: aload 12
            //   3974: astore 27
            //   3976: aload 14
            //   3978: astore 17
            //   3980: aload 13
            //   3982: astore 20
            //   3984: iload 8
            //   3986: istore_2
            //   3987: aload 15
            //   3989: astore 29
            //   3991: aload 12
            //   3993: astore 28
            //   3995: aload 14
            //   3997: astore 18
            //   3999: aload 13
            //   4001: astore 16
            //   4003: iload 9
            //   4005: istore_3
            //   4006: aload 15
            //   4008: astore 31
            //   4010: aload 12
            //   4012: astore 30
            //   4014: aload 14
            //   4016: astore 21
            //   4018: aload 13
            //   4020: astore 19
            //   4022: iload 10
            //   4024: istore 4
            //   4026: aload 15
            //   4028: astore 33
            //   4030: aload 12
            //   4032: astore 32
            //   4034: aload 14
            //   4036: astore 23
            //   4038: aload 13
            //   4040: astore 22
            //   4042: iload 11
            //   4044: istore 5
            //   4046: aload 15
            //   4048: astore 35
            //   4050: aload 12
            //   4052: astore 34
            //   4054: aload 14
            //   4056: astore 25
            //   4058: aload 13
            //   4060: astore 24
            //   4062: aload 56
            //   4064: iconst_1
            //   4065: invokevirtual 135	org/apache/cordova/filetransfer/FileProgressResult:setLengthComputable	(Z)V
            //   4068: iload 7
            //   4070: istore_1
            //   4071: aload 15
            //   4073: astore 26
            //   4075: aload 12
            //   4077: astore 27
            //   4079: aload 14
            //   4081: astore 17
            //   4083: aload 13
            //   4085: astore 20
            //   4087: iload 8
            //   4089: istore_2
            //   4090: aload 15
            //   4092: astore 29
            //   4094: aload 12
            //   4096: astore 28
            //   4098: aload 14
            //   4100: astore 18
            //   4102: aload 13
            //   4104: astore 16
            //   4106: iload 9
            //   4108: istore_3
            //   4109: aload 15
            //   4111: astore 31
            //   4113: aload 12
            //   4115: astore 30
            //   4117: aload 14
            //   4119: astore 21
            //   4121: aload 13
            //   4123: astore 19
            //   4125: iload 10
            //   4127: istore 4
            //   4129: aload 15
            //   4131: astore 33
            //   4133: aload 12
            //   4135: astore 32
            //   4137: aload 14
            //   4139: astore 23
            //   4141: aload 13
            //   4143: astore 22
            //   4145: iload 11
            //   4147: istore 5
            //   4149: aload 15
            //   4151: astore 35
            //   4153: aload 12
            //   4155: astore 34
            //   4157: aload 14
            //   4159: astore 25
            //   4161: aload 13
            //   4163: astore 24
            //   4165: aload 56
            //   4167: aload 15
            //   4169: invokevirtual 285	java/net/HttpURLConnection:getContentLength	()I
            //   4172: i2l
            //   4173: invokevirtual 139	org/apache/cordova/filetransfer/FileProgressResult:setTotal	(J)V
            //   4176: iload 7
            //   4178: istore_1
            //   4179: aload 15
            //   4181: astore 26
            //   4183: aload 12
            //   4185: astore 27
            //   4187: aload 14
            //   4189: astore 17
            //   4191: aload 13
            //   4193: astore 20
            //   4195: iload 8
            //   4197: istore_2
            //   4198: aload 15
            //   4200: astore 29
            //   4202: aload 12
            //   4204: astore 28
            //   4206: aload 14
            //   4208: astore 18
            //   4210: aload 13
            //   4212: astore 16
            //   4214: iload 9
            //   4216: istore_3
            //   4217: aload 15
            //   4219: astore 31
            //   4221: aload 12
            //   4223: astore 30
            //   4225: aload 14
            //   4227: astore 21
            //   4229: aload 13
            //   4231: astore 19
            //   4233: iload 10
            //   4235: istore 4
            //   4237: aload 15
            //   4239: astore 33
            //   4241: aload 12
            //   4243: astore 32
            //   4245: aload 14
            //   4247: astore 23
            //   4249: aload 13
            //   4251: astore 22
            //   4253: iload 11
            //   4255: istore 5
            //   4257: aload 15
            //   4259: astore 35
            //   4261: aload 12
            //   4263: astore 34
            //   4265: aload 14
            //   4267: astore 25
            //   4269: aload 13
            //   4271: astore 24
            //   4273: aload 15
            //   4275: invokestatic 289	org/apache/cordova/filetransfer/FileTransfer:access$500	(Ljava/net/URLConnection;)Lorg/apache/cordova/filetransfer/FileTransfer$TrackingInputStream;
            //   4278: astore 38
            //   4280: aload 38
            //   4282: astore 26
            //   4284: aconst_null
            //   4285: astore 16
            //   4287: iload 6
            //   4289: istore_1
            //   4290: aload 13
            //   4292: astore 17
            //   4294: aload 16
            //   4296: astore 13
            //   4298: goto -3100 -> 1198
            //   4301: astore 26
            //   4303: aload 27
            //   4305: monitorexit
            //   4306: aload 12
            //   4308: astore 22
            //   4310: aload 12
            //   4312: astore 23
            //   4314: aload 12
            //   4316: astore 24
            //   4318: aload 12
            //   4320: astore 25
            //   4322: iload_1
            //   4323: istore_2
            //   4324: aload 15
            //   4326: astore 20
            //   4328: aload 12
            //   4330: astore 16
            //   4332: aload 14
            //   4334: astore 19
            //   4336: aload 17
            //   4338: astore 18
            //   4340: aload 13
            //   4342: astore 21
            //   4344: aload 26
            //   4346: athrow
            //   4347: astore 12
            //   4349: iload_1
            //   4350: istore_2
            //   4351: aload 15
            //   4353: astore 20
            //   4355: aload 22
            //   4357: astore 16
            //   4359: aload 14
            //   4361: astore 19
            //   4363: aload 17
            //   4365: astore 18
            //   4367: aload 13
            //   4369: astore 21
            //   4371: getstatic 292	org/apache/cordova/filetransfer/FileTransfer:FILE_NOT_FOUND_ERR	I
            //   4374: aload_0
            //   4375: getfield 52	org/apache/cordova/filetransfer/FileTransfer$4:val$source	Ljava/lang/String;
            //   4378: aload_0
            //   4379: getfield 54	org/apache/cordova/filetransfer/FileTransfer$4:val$target	Ljava/lang/String;
            //   4382: aload 15
            //   4384: aload 12
            //   4386: invokestatic 192	org/apache/cordova/filetransfer/FileTransfer:access$600	(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;Ljava/lang/Throwable;)Lorg/json/JSONObject;
            //   4389: astore 23
            //   4391: iload_1
            //   4392: istore_2
            //   4393: aload 15
            //   4395: astore 20
            //   4397: aload 22
            //   4399: astore 16
            //   4401: aload 14
            //   4403: astore 19
            //   4405: aload 17
            //   4407: astore 18
            //   4409: aload 13
            //   4411: astore 21
            //   4413: ldc 94
            //   4415: aload 23
            //   4417: invokevirtual 295	org/json/JSONObject:toString	()Ljava/lang/String;
            //   4420: aload 12
            //   4422: invokestatic 299	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
            //   4425: pop
            //   4426: iload_1
            //   4427: istore_2
            //   4428: aload 15
            //   4430: astore 20
            //   4432: aload 22
            //   4434: astore 16
            //   4436: aload 14
            //   4438: astore 19
            //   4440: aload 17
            //   4442: astore 18
            //   4444: aload 13
            //   4446: astore 21
            //   4448: new 178	org/apache/cordova/PluginResult
            //   4451: dup
            //   4452: getstatic 302	org/apache/cordova/PluginResult$Status:IO_EXCEPTION	Lorg/apache/cordova/PluginResult$Status;
            //   4455: aload 23
            //   4457: invokespecial 195	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V
            //   4460: astore 13
            //   4462: invokestatic 160	org/apache/cordova/filetransfer/FileTransfer:access$700	()Ljava/util/HashMap;
            //   4465: astore 12
            //   4467: aload 12
            //   4469: monitorenter
            //   4470: invokestatic 160	org/apache/cordova/filetransfer/FileTransfer:access$700	()Ljava/util/HashMap;
            //   4473: aload_0
            //   4474: getfield 56	org/apache/cordova/filetransfer/FileTransfer$4:val$objectId	Ljava/lang/String;
            //   4477: invokevirtual 166	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
            //   4480: pop
            //   4481: aload 12
            //   4483: monitorexit
            //   4484: aload 15
            //   4486: ifnull +38 -> 4524
            //   4489: aload_0
            //   4490: getfield 48	org/apache/cordova/filetransfer/FileTransfer$4:val$trustEveryone	Z
            //   4493: ifeq +31 -> 4524
            //   4496: aload_0
            //   4497: getfield 46	org/apache/cordova/filetransfer/FileTransfer$4:val$useHttps	Z
            //   4500: ifeq +24 -> 4524
            //   4503: aload 15
            //   4505: checkcast 168	javax/net/ssl/HttpsURLConnection
            //   4508: astore 12
            //   4510: aload 12
            //   4512: aload 14
            //   4514: invokevirtual 172	javax/net/ssl/HttpsURLConnection:setHostnameVerifier	(Ljavax/net/ssl/HostnameVerifier;)V
            //   4517: aload 12
            //   4519: aload 17
            //   4521: invokevirtual 176	javax/net/ssl/HttpsURLConnection:setSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
            //   4524: aload 13
            //   4526: astore 12
            //   4528: aload 13
            //   4530: ifnonnull +32 -> 4562
            //   4533: new 178	org/apache/cordova/PluginResult
            //   4536: dup
            //   4537: getstatic 184	org/apache/cordova/PluginResult$Status:ERROR	Lorg/apache/cordova/PluginResult$Status;
            //   4540: getstatic 188	org/apache/cordova/filetransfer/FileTransfer:CONNECTION_ERR	I
            //   4543: aload_0
            //   4544: getfield 52	org/apache/cordova/filetransfer/FileTransfer$4:val$source	Ljava/lang/String;
            //   4547: aload_0
            //   4548: getfield 54	org/apache/cordova/filetransfer/FileTransfer$4:val$target	Ljava/lang/String;
            //   4551: aload 15
            //   4553: aconst_null
            //   4554: invokestatic 192	org/apache/cordova/filetransfer/FileTransfer:access$600	(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;Ljava/lang/Throwable;)Lorg/json/JSONObject;
            //   4557: invokespecial 195	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V
            //   4560: astore 12
            //   4562: iload_1
            //   4563: ifne +28 -> 4591
            //   4566: aload 12
            //   4568: invokevirtual 199	org/apache/cordova/PluginResult:getStatus	()I
            //   4571: getstatic 202	org/apache/cordova/PluginResult$Status:OK	Lorg/apache/cordova/PluginResult$Status;
            //   4574: invokevirtual 205	org/apache/cordova/PluginResult$Status:ordinal	()I
            //   4577: if_icmpeq +14 -> 4591
            //   4580: aload 22
            //   4582: ifnull +9 -> 4591
            //   4585: aload 22
            //   4587: invokevirtual 211	java/io/File:delete	()Z
            //   4590: pop
            //   4591: aload_0
            //   4592: getfield 36	org/apache/cordova/filetransfer/FileTransfer$4:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
            //   4595: aload 12
            //   4597: invokevirtual 215	org/apache/cordova/filetransfer/FileTransfer$RequestContext:sendPluginResult	(Lorg/apache/cordova/PluginResult;)V
            //   4600: return
            //   4601: astore 12
            //   4603: aload 16
            //   4605: monitorexit
            //   4606: aload 12
            //   4608: athrow
            //   4609: aload_0
            //   4610: getfield 36	org/apache/cordova/filetransfer/FileTransfer$4:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
            //   4613: aload 15
            //   4615: putfield 152	org/apache/cordova/filetransfer/FileTransfer$RequestContext:connection	Ljava/net/HttpURLConnection;
            //   4618: aload 16
            //   4620: monitorexit
            //   4621: aload 36
            //   4623: astore 27
            //   4625: sipush 16384
            //   4628: newarray <illegal type>
            //   4630: astore 16
            //   4632: aload 36
            //   4634: astore 27
            //   4636: aload_0
            //   4637: getfield 38	org/apache/cordova/filetransfer/FileTransfer$4:val$resourceApi	Lorg/apache/cordova/CordovaResourceApi;
            //   4640: aload_0
            //   4641: getfield 40	org/apache/cordova/filetransfer/FileTransfer$4:val$targetUri	Landroid/net/Uri;
            //   4644: invokevirtual 306	org/apache/cordova/CordovaResourceApi:openOutputStream	(Landroid/net/Uri;)Ljava/io/OutputStream;
            //   4647: astore 28
            //   4649: aload 28
            //   4651: astore 27
            //   4653: aload 26
            //   4655: aload 16
            //   4657: invokevirtual 312	org/apache/cordova/filetransfer/FileTransfer$TrackingInputStream:read	([B)I
            //   4660: istore_2
            //   4661: iload_2
            //   4662: ifle +572 -> 5234
            //   4665: aload 28
            //   4667: astore 27
            //   4669: aload 28
            //   4671: aload 16
            //   4673: iconst_0
            //   4674: iload_2
            //   4675: invokevirtual 318	java/io/OutputStream:write	([BII)V
            //   4678: aload 28
            //   4680: astore 27
            //   4682: aload 56
            //   4684: aload 26
            //   4686: invokevirtual 322	org/apache/cordova/filetransfer/FileTransfer$TrackingInputStream:getTotalRawBytesRead	()J
            //   4689: invokevirtual 325	org/apache/cordova/filetransfer/FileProgressResult:setLoaded	(J)V
            //   4692: aload 28
            //   4694: astore 27
            //   4696: new 178	org/apache/cordova/PluginResult
            //   4699: dup
            //   4700: getstatic 202	org/apache/cordova/PluginResult$Status:OK	Lorg/apache/cordova/PluginResult$Status;
            //   4703: aload 56
            //   4705: invokevirtual 329	org/apache/cordova/filetransfer/FileProgressResult:toJSONObject	()Lorg/json/JSONObject;
            //   4708: invokespecial 195	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V
            //   4711: astore 18
            //   4713: aload 28
            //   4715: astore 27
            //   4717: aload 18
            //   4719: iconst_1
            //   4720: invokevirtual 332	org/apache/cordova/PluginResult:setKeepCallback	(Z)V
            //   4723: aload 28
            //   4725: astore 27
            //   4727: aload_0
            //   4728: getfield 36	org/apache/cordova/filetransfer/FileTransfer$4:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
            //   4731: aload 18
            //   4733: invokevirtual 215	org/apache/cordova/filetransfer/FileTransfer$RequestContext:sendPluginResult	(Lorg/apache/cordova/PluginResult;)V
            //   4736: goto -87 -> 4649
            //   4739: astore 29
            //   4741: aload 12
            //   4743: astore 22
            //   4745: aload 12
            //   4747: astore 23
            //   4749: aload 12
            //   4751: astore 24
            //   4753: aload 12
            //   4755: astore 25
            //   4757: iload_1
            //   4758: istore_2
            //   4759: aload 15
            //   4761: astore 20
            //   4763: aload 12
            //   4765: astore 16
            //   4767: aload 14
            //   4769: astore 19
            //   4771: aload 17
            //   4773: astore 18
            //   4775: aload 13
            //   4777: astore 21
            //   4779: aload_0
            //   4780: getfield 36	org/apache/cordova/filetransfer/FileTransfer$4:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
            //   4783: astore 28
            //   4785: aload 12
            //   4787: astore 22
            //   4789: aload 12
            //   4791: astore 23
            //   4793: aload 12
            //   4795: astore 24
            //   4797: aload 12
            //   4799: astore 25
            //   4801: iload_1
            //   4802: istore_2
            //   4803: aload 15
            //   4805: astore 20
            //   4807: aload 12
            //   4809: astore 16
            //   4811: aload 14
            //   4813: astore 19
            //   4815: aload 17
            //   4817: astore 18
            //   4819: aload 13
            //   4821: astore 21
            //   4823: aload 28
            //   4825: monitorenter
            //   4826: aload_0
            //   4827: getfield 36	org/apache/cordova/filetransfer/FileTransfer$4:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
            //   4830: aconst_null
            //   4831: putfield 152	org/apache/cordova/filetransfer/FileTransfer$RequestContext:connection	Ljava/net/HttpURLConnection;
            //   4834: aload 28
            //   4836: monitorexit
            //   4837: aload 12
            //   4839: astore 22
            //   4841: aload 12
            //   4843: astore 23
            //   4845: aload 12
            //   4847: astore 24
            //   4849: aload 12
            //   4851: astore 25
            //   4853: iload_1
            //   4854: istore_2
            //   4855: aload 15
            //   4857: astore 20
            //   4859: aload 12
            //   4861: astore 16
            //   4863: aload 14
            //   4865: astore 19
            //   4867: aload 17
            //   4869: astore 18
            //   4871: aload 13
            //   4873: astore 21
            //   4875: aload 26
            //   4877: invokestatic 156	org/apache/cordova/filetransfer/FileTransfer:access$400	(Ljava/io/Closeable;)V
            //   4880: aload 12
            //   4882: astore 22
            //   4884: aload 12
            //   4886: astore 23
            //   4888: aload 12
            //   4890: astore 24
            //   4892: aload 12
            //   4894: astore 25
            //   4896: iload_1
            //   4897: istore_2
            //   4898: aload 15
            //   4900: astore 20
            //   4902: aload 12
            //   4904: astore 16
            //   4906: aload 14
            //   4908: astore 19
            //   4910: aload 17
            //   4912: astore 18
            //   4914: aload 13
            //   4916: astore 21
            //   4918: aload 27
            //   4920: invokestatic 156	org/apache/cordova/filetransfer/FileTransfer:access$400	(Ljava/io/Closeable;)V
            //   4923: aload 12
            //   4925: astore 22
            //   4927: aload 12
            //   4929: astore 23
            //   4931: aload 12
            //   4933: astore 24
            //   4935: aload 12
            //   4937: astore 25
            //   4939: iload_1
            //   4940: istore_2
            //   4941: aload 15
            //   4943: astore 20
            //   4945: aload 12
            //   4947: astore 16
            //   4949: aload 14
            //   4951: astore 19
            //   4953: aload 17
            //   4955: astore 18
            //   4957: aload 13
            //   4959: astore 21
            //   4961: aload 29
            //   4963: athrow
            //   4964: astore 12
            //   4966: aload 23
            //   4968: astore 32
            //   4970: iload_1
            //   4971: istore_2
            //   4972: aload 15
            //   4974: astore 20
            //   4976: aload 32
            //   4978: astore 16
            //   4980: aload 14
            //   4982: astore 19
            //   4984: aload 17
            //   4986: astore 18
            //   4988: aload 13
            //   4990: astore 21
            //   4992: getstatic 188	org/apache/cordova/filetransfer/FileTransfer:CONNECTION_ERR	I
            //   4995: aload_0
            //   4996: getfield 52	org/apache/cordova/filetransfer/FileTransfer$4:val$source	Ljava/lang/String;
            //   4999: aload_0
            //   5000: getfield 54	org/apache/cordova/filetransfer/FileTransfer$4:val$target	Ljava/lang/String;
            //   5003: aload 15
            //   5005: aload 12
            //   5007: invokestatic 192	org/apache/cordova/filetransfer/FileTransfer:access$600	(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;Ljava/lang/Throwable;)Lorg/json/JSONObject;
            //   5010: astore 22
            //   5012: iload_1
            //   5013: istore_2
            //   5014: aload 15
            //   5016: astore 20
            //   5018: aload 32
            //   5020: astore 16
            //   5022: aload 14
            //   5024: astore 19
            //   5026: aload 17
            //   5028: astore 18
            //   5030: aload 13
            //   5032: astore 21
            //   5034: ldc 94
            //   5036: aload 22
            //   5038: invokevirtual 295	org/json/JSONObject:toString	()Ljava/lang/String;
            //   5041: aload 12
            //   5043: invokestatic 299	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
            //   5046: pop
            //   5047: iload_1
            //   5048: istore_2
            //   5049: aload 15
            //   5051: astore 20
            //   5053: aload 32
            //   5055: astore 16
            //   5057: aload 14
            //   5059: astore 19
            //   5061: aload 17
            //   5063: astore 18
            //   5065: aload 13
            //   5067: astore 21
            //   5069: new 178	org/apache/cordova/PluginResult
            //   5072: dup
            //   5073: getstatic 302	org/apache/cordova/PluginResult$Status:IO_EXCEPTION	Lorg/apache/cordova/PluginResult$Status;
            //   5076: aload 22
            //   5078: invokespecial 195	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V
            //   5081: astore 13
            //   5083: invokestatic 160	org/apache/cordova/filetransfer/FileTransfer:access$700	()Ljava/util/HashMap;
            //   5086: astore 12
            //   5088: aload 12
            //   5090: monitorenter
            //   5091: invokestatic 160	org/apache/cordova/filetransfer/FileTransfer:access$700	()Ljava/util/HashMap;
            //   5094: aload_0
            //   5095: getfield 56	org/apache/cordova/filetransfer/FileTransfer$4:val$objectId	Ljava/lang/String;
            //   5098: invokevirtual 166	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
            //   5101: pop
            //   5102: aload 12
            //   5104: monitorexit
            //   5105: aload 15
            //   5107: ifnull +38 -> 5145
            //   5110: aload_0
            //   5111: getfield 48	org/apache/cordova/filetransfer/FileTransfer$4:val$trustEveryone	Z
            //   5114: ifeq +31 -> 5145
            //   5117: aload_0
            //   5118: getfield 46	org/apache/cordova/filetransfer/FileTransfer$4:val$useHttps	Z
            //   5121: ifeq +24 -> 5145
            //   5124: aload 15
            //   5126: checkcast 168	javax/net/ssl/HttpsURLConnection
            //   5129: astore 12
            //   5131: aload 12
            //   5133: aload 14
            //   5135: invokevirtual 172	javax/net/ssl/HttpsURLConnection:setHostnameVerifier	(Ljavax/net/ssl/HostnameVerifier;)V
            //   5138: aload 12
            //   5140: aload 17
            //   5142: invokevirtual 176	javax/net/ssl/HttpsURLConnection:setSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
            //   5145: aload 13
            //   5147: astore 12
            //   5149: aload 13
            //   5151: ifnonnull +32 -> 5183
            //   5154: new 178	org/apache/cordova/PluginResult
            //   5157: dup
            //   5158: getstatic 184	org/apache/cordova/PluginResult$Status:ERROR	Lorg/apache/cordova/PluginResult$Status;
            //   5161: getstatic 188	org/apache/cordova/filetransfer/FileTransfer:CONNECTION_ERR	I
            //   5164: aload_0
            //   5165: getfield 52	org/apache/cordova/filetransfer/FileTransfer$4:val$source	Ljava/lang/String;
            //   5168: aload_0
            //   5169: getfield 54	org/apache/cordova/filetransfer/FileTransfer$4:val$target	Ljava/lang/String;
            //   5172: aload 15
            //   5174: aconst_null
            //   5175: invokestatic 192	org/apache/cordova/filetransfer/FileTransfer:access$600	(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;Ljava/lang/Throwable;)Lorg/json/JSONObject;
            //   5178: invokespecial 195	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V
            //   5181: astore 12
            //   5183: iload_1
            //   5184: ifne +28 -> 5212
            //   5187: aload 12
            //   5189: invokevirtual 199	org/apache/cordova/PluginResult:getStatus	()I
            //   5192: getstatic 202	org/apache/cordova/PluginResult$Status:OK	Lorg/apache/cordova/PluginResult$Status;
            //   5195: invokevirtual 205	org/apache/cordova/PluginResult$Status:ordinal	()I
            //   5198: if_icmpeq +14 -> 5212
            //   5201: aload 32
            //   5203: ifnull +9 -> 5212
            //   5206: aload 32
            //   5208: invokevirtual 211	java/io/File:delete	()Z
            //   5211: pop
            //   5212: aload_0
            //   5213: getfield 36	org/apache/cordova/filetransfer/FileTransfer$4:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
            //   5216: aload 12
            //   5218: invokevirtual 215	org/apache/cordova/filetransfer/FileTransfer$RequestContext:sendPluginResult	(Lorg/apache/cordova/PluginResult;)V
            //   5221: return
            //   5222: astore 18
            //   5224: aload 16
            //   5226: monitorexit
            //   5227: aload 36
            //   5229: astore 27
            //   5231: aload 18
            //   5233: athrow
            //   5234: aload 12
            //   5236: astore 22
            //   5238: aload 12
            //   5240: astore 23
            //   5242: aload 12
            //   5244: astore 24
            //   5246: aload 12
            //   5248: astore 25
            //   5250: iload_1
            //   5251: istore_2
            //   5252: aload 15
            //   5254: astore 20
            //   5256: aload 12
            //   5258: astore 16
            //   5260: aload 14
            //   5262: astore 19
            //   5264: aload 17
            //   5266: astore 18
            //   5268: aload 13
            //   5270: astore 21
            //   5272: aload_0
            //   5273: getfield 36	org/apache/cordova/filetransfer/FileTransfer$4:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
            //   5276: astore 27
            //   5278: aload 12
            //   5280: astore 22
            //   5282: aload 12
            //   5284: astore 23
            //   5286: aload 12
            //   5288: astore 24
            //   5290: aload 12
            //   5292: astore 25
            //   5294: iload_1
            //   5295: istore_2
            //   5296: aload 15
            //   5298: astore 20
            //   5300: aload 12
            //   5302: astore 16
            //   5304: aload 14
            //   5306: astore 19
            //   5308: aload 17
            //   5310: astore 18
            //   5312: aload 13
            //   5314: astore 21
            //   5316: aload 27
            //   5318: monitorenter
            //   5319: aload_0
            //   5320: getfield 36	org/apache/cordova/filetransfer/FileTransfer$4:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
            //   5323: aconst_null
            //   5324: putfield 152	org/apache/cordova/filetransfer/FileTransfer$RequestContext:connection	Ljava/net/HttpURLConnection;
            //   5327: aload 27
            //   5329: monitorexit
            //   5330: aload 12
            //   5332: astore 22
            //   5334: aload 12
            //   5336: astore 23
            //   5338: aload 12
            //   5340: astore 24
            //   5342: aload 12
            //   5344: astore 25
            //   5346: iload_1
            //   5347: istore_2
            //   5348: aload 15
            //   5350: astore 20
            //   5352: aload 12
            //   5354: astore 16
            //   5356: aload 14
            //   5358: astore 19
            //   5360: aload 17
            //   5362: astore 18
            //   5364: aload 13
            //   5366: astore 21
            //   5368: aload 26
            //   5370: invokestatic 156	org/apache/cordova/filetransfer/FileTransfer:access$400	(Ljava/io/Closeable;)V
            //   5373: aload 12
            //   5375: astore 22
            //   5377: aload 12
            //   5379: astore 23
            //   5381: aload 12
            //   5383: astore 24
            //   5385: aload 12
            //   5387: astore 25
            //   5389: iload_1
            //   5390: istore_2
            //   5391: aload 15
            //   5393: astore 20
            //   5395: aload 12
            //   5397: astore 16
            //   5399: aload 14
            //   5401: astore 19
            //   5403: aload 17
            //   5405: astore 18
            //   5407: aload 13
            //   5409: astore 21
            //   5411: aload 28
            //   5413: invokestatic 156	org/apache/cordova/filetransfer/FileTransfer:access$400	(Ljava/io/Closeable;)V
            //   5416: aload 12
            //   5418: astore 22
            //   5420: aload 12
            //   5422: astore 23
            //   5424: aload 12
            //   5426: astore 24
            //   5428: aload 12
            //   5430: astore 25
            //   5432: iload_1
            //   5433: istore_2
            //   5434: aload 15
            //   5436: astore 20
            //   5438: aload 12
            //   5440: astore 16
            //   5442: aload 14
            //   5444: astore 19
            //   5446: aload 17
            //   5448: astore 18
            //   5450: aload 13
            //   5452: astore 21
            //   5454: ldc 94
            //   5456: new 96	java/lang/StringBuilder
            //   5459: dup
            //   5460: invokespecial 97	java/lang/StringBuilder:<init>	()V
            //   5463: ldc_w 334
            //   5466: invokevirtual 103	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   5469: aload_0
            //   5470: getfield 54	org/apache/cordova/filetransfer/FileTransfer$4:val$target	Ljava/lang/String;
            //   5473: invokevirtual 103	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   5476: invokevirtual 110	java/lang/StringBuilder:toString	()Ljava/lang/String;
            //   5479: invokestatic 116	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
            //   5482: pop
            //   5483: aload 12
            //   5485: astore 22
            //   5487: aload 12
            //   5489: astore 23
            //   5491: aload 12
            //   5493: astore 24
            //   5495: aload 12
            //   5497: astore 25
            //   5499: iload_1
            //   5500: istore_2
            //   5501: aload 15
            //   5503: astore 20
            //   5505: aload 12
            //   5507: astore 16
            //   5509: aload 14
            //   5511: astore 19
            //   5513: aload 17
            //   5515: astore 18
            //   5517: aload 13
            //   5519: astore 21
            //   5521: aload_0
            //   5522: getfield 34	org/apache/cordova/filetransfer/FileTransfer$4:this$0	Lorg/apache/cordova/filetransfer/FileTransfer;
            //   5525: getfield 338	org/apache/cordova/filetransfer/FileTransfer:webView	Lorg/apache/cordova/CordovaWebView;
            //   5528: invokevirtual 342	java/lang/Object:getClass	()Ljava/lang/Class;
            //   5531: astore 28
            //   5533: aconst_null
            //   5534: astore 26
            //   5536: aload 12
            //   5538: astore 22
            //   5540: aload 12
            //   5542: astore 23
            //   5544: aload 12
            //   5546: astore 24
            //   5548: aload 12
            //   5550: astore 25
            //   5552: iload_1
            //   5553: istore_2
            //   5554: aload 15
            //   5556: astore 20
            //   5558: aload 12
            //   5560: astore 16
            //   5562: aload 14
            //   5564: astore 19
            //   5566: aload 17
            //   5568: astore 18
            //   5570: aload 13
            //   5572: astore 21
            //   5574: aload 28
            //   5576: ldc_w 344
            //   5579: iconst_0
            //   5580: anewarray 346	java/lang/Class
            //   5583: invokevirtual 350	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
            //   5586: aload_0
            //   5587: getfield 34	org/apache/cordova/filetransfer/FileTransfer$4:this$0	Lorg/apache/cordova/filetransfer/FileTransfer;
            //   5590: getfield 338	org/apache/cordova/filetransfer/FileTransfer:webView	Lorg/apache/cordova/CordovaWebView;
            //   5593: iconst_0
            //   5594: anewarray 4	java/lang/Object
            //   5597: invokevirtual 356	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
            //   5600: checkcast 358	org/apache/cordova/PluginManager
            //   5603: astore 27
            //   5605: aload 27
            //   5607: astore 26
            //   5609: aload 26
            //   5611: astore 27
            //   5613: aload 26
            //   5615: ifnonnull +64 -> 5679
            //   5618: aload 12
            //   5620: astore 22
            //   5622: aload 12
            //   5624: astore 23
            //   5626: aload 12
            //   5628: astore 24
            //   5630: aload 12
            //   5632: astore 25
            //   5634: iload_1
            //   5635: istore_2
            //   5636: aload 15
            //   5638: astore 20
            //   5640: aload 12
            //   5642: astore 16
            //   5644: aload 14
            //   5646: astore 19
            //   5648: aload 17
            //   5650: astore 18
            //   5652: aload 13
            //   5654: astore 21
            //   5656: aload 28
            //   5658: ldc_w 360
            //   5661: invokevirtual 364	java/lang/Class:getField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
            //   5664: aload_0
            //   5665: getfield 34	org/apache/cordova/filetransfer/FileTransfer$4:this$0	Lorg/apache/cordova/filetransfer/FileTransfer;
            //   5668: getfield 338	org/apache/cordova/filetransfer/FileTransfer:webView	Lorg/apache/cordova/CordovaWebView;
            //   5671: invokevirtual 369	java/lang/reflect/Field:get	(Ljava/lang/Object;)Ljava/lang/Object;
            //   5674: checkcast 358	org/apache/cordova/PluginManager
            //   5677: astore 27
            //   5679: aload 12
            //   5681: astore 22
            //   5683: aload 12
            //   5685: astore 23
            //   5687: aload 12
            //   5689: astore 24
            //   5691: aload 12
            //   5693: astore 25
            //   5695: iload_1
            //   5696: istore_2
            //   5697: aload 15
            //   5699: astore 20
            //   5701: aload 12
            //   5703: astore 16
            //   5705: aload 14
            //   5707: astore 19
            //   5709: aload 17
            //   5711: astore 18
            //   5713: aload 13
            //   5715: astore 21
            //   5717: aload_0
            //   5718: getfield 38	org/apache/cordova/filetransfer/FileTransfer$4:val$resourceApi	Lorg/apache/cordova/CordovaResourceApi;
            //   5721: aload_0
            //   5722: getfield 40	org/apache/cordova/filetransfer/FileTransfer$4:val$targetUri	Landroid/net/Uri;
            //   5725: invokevirtual 88	org/apache/cordova/CordovaResourceApi:mapUriToFile	(Landroid/net/Uri;)Ljava/io/File;
            //   5728: astore 12
            //   5730: aload 12
            //   5732: astore 22
            //   5734: aload 12
            //   5736: astore 23
            //   5738: aload 12
            //   5740: astore 24
            //   5742: aload 12
            //   5744: astore 25
            //   5746: iload_1
            //   5747: istore_2
            //   5748: aload 15
            //   5750: astore 20
            //   5752: aload 12
            //   5754: astore 16
            //   5756: aload 14
            //   5758: astore 19
            //   5760: aload 17
            //   5762: astore 18
            //   5764: aload 13
            //   5766: astore 21
            //   5768: aload_0
            //   5769: getfield 36	org/apache/cordova/filetransfer/FileTransfer$4:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
            //   5772: aload 12
            //   5774: putfield 92	org/apache/cordova/filetransfer/FileTransfer$RequestContext:targetFile	Ljava/io/File;
            //   5777: aload 12
            //   5779: astore 22
            //   5781: aload 12
            //   5783: astore 23
            //   5785: aload 12
            //   5787: astore 24
            //   5789: aload 12
            //   5791: astore 25
            //   5793: iload_1
            //   5794: istore_2
            //   5795: aload 15
            //   5797: astore 20
            //   5799: aload 12
            //   5801: astore 16
            //   5803: aload 14
            //   5805: astore 19
            //   5807: aload 17
            //   5809: astore 18
            //   5811: aload 13
            //   5813: astore 21
            //   5815: aload 27
            //   5817: ldc_w 371
            //   5820: invokevirtual 375	org/apache/cordova/PluginManager:getPlugin	(Ljava/lang/String;)Lorg/apache/cordova/CordovaPlugin;
            //   5823: checkcast 377	org/apache/cordova/file/FileUtils
            //   5826: astore 26
            //   5828: aload 26
            //   5830: ifnull +969 -> 6799
            //   5833: aload 12
            //   5835: astore 22
            //   5837: aload 12
            //   5839: astore 23
            //   5841: aload 12
            //   5843: astore 24
            //   5845: aload 12
            //   5847: astore 25
            //   5849: iload_1
            //   5850: istore_2
            //   5851: aload 15
            //   5853: astore 20
            //   5855: aload 12
            //   5857: astore 16
            //   5859: aload 14
            //   5861: astore 19
            //   5863: aload 17
            //   5865: astore 18
            //   5867: aload 13
            //   5869: astore 21
            //   5871: aload 26
            //   5873: aload 12
            //   5875: invokevirtual 381	org/apache/cordova/file/FileUtils:getEntryForFile	(Ljava/io/File;)Lorg/json/JSONObject;
            //   5878: astore 26
            //   5880: aload 26
            //   5882: ifnull +754 -> 6636
            //   5885: aload 12
            //   5887: astore 22
            //   5889: aload 12
            //   5891: astore 23
            //   5893: aload 12
            //   5895: astore 24
            //   5897: aload 12
            //   5899: astore 25
            //   5901: iload_1
            //   5902: istore_2
            //   5903: aload 15
            //   5905: astore 20
            //   5907: aload 12
            //   5909: astore 16
            //   5911: aload 14
            //   5913: astore 19
            //   5915: aload 17
            //   5917: astore 18
            //   5919: aload 13
            //   5921: astore 21
            //   5923: new 178	org/apache/cordova/PluginResult
            //   5926: dup
            //   5927: getstatic 202	org/apache/cordova/PluginResult$Status:OK	Lorg/apache/cordova/PluginResult$Status;
            //   5930: aload 26
            //   5932: invokespecial 195	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V
            //   5935: astore 26
            //   5937: aload 26
            //   5939: astore 13
            //   5941: invokestatic 160	org/apache/cordova/filetransfer/FileTransfer:access$700	()Ljava/util/HashMap;
            //   5944: astore 16
            //   5946: aload 16
            //   5948: monitorenter
            //   5949: invokestatic 160	org/apache/cordova/filetransfer/FileTransfer:access$700	()Ljava/util/HashMap;
            //   5952: aload_0
            //   5953: getfield 56	org/apache/cordova/filetransfer/FileTransfer$4:val$objectId	Ljava/lang/String;
            //   5956: invokevirtual 166	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
            //   5959: pop
            //   5960: aload 16
            //   5962: monitorexit
            //   5963: aload 15
            //   5965: ifnull +38 -> 6003
            //   5968: aload_0
            //   5969: getfield 48	org/apache/cordova/filetransfer/FileTransfer$4:val$trustEveryone	Z
            //   5972: ifeq +31 -> 6003
            //   5975: aload_0
            //   5976: getfield 46	org/apache/cordova/filetransfer/FileTransfer$4:val$useHttps	Z
            //   5979: ifeq +24 -> 6003
            //   5982: aload 15
            //   5984: checkcast 168	javax/net/ssl/HttpsURLConnection
            //   5987: astore 16
            //   5989: aload 16
            //   5991: aload 14
            //   5993: invokevirtual 172	javax/net/ssl/HttpsURLConnection:setHostnameVerifier	(Ljavax/net/ssl/HostnameVerifier;)V
            //   5996: aload 16
            //   5998: aload 17
            //   6000: invokevirtual 176	javax/net/ssl/HttpsURLConnection:setSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
            //   6003: aload 13
            //   6005: astore 14
            //   6007: aload 13
            //   6009: ifnonnull +32 -> 6041
            //   6012: new 178	org/apache/cordova/PluginResult
            //   6015: dup
            //   6016: getstatic 184	org/apache/cordova/PluginResult$Status:ERROR	Lorg/apache/cordova/PluginResult$Status;
            //   6019: getstatic 188	org/apache/cordova/filetransfer/FileTransfer:CONNECTION_ERR	I
            //   6022: aload_0
            //   6023: getfield 52	org/apache/cordova/filetransfer/FileTransfer$4:val$source	Ljava/lang/String;
            //   6026: aload_0
            //   6027: getfield 54	org/apache/cordova/filetransfer/FileTransfer$4:val$target	Ljava/lang/String;
            //   6030: aload 15
            //   6032: aconst_null
            //   6033: invokestatic 192	org/apache/cordova/filetransfer/FileTransfer:access$600	(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;Ljava/lang/Throwable;)Lorg/json/JSONObject;
            //   6036: invokespecial 195	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V
            //   6039: astore 14
            //   6041: iload_1
            //   6042: ifne +28 -> 6070
            //   6045: aload 14
            //   6047: invokevirtual 199	org/apache/cordova/PluginResult:getStatus	()I
            //   6050: getstatic 202	org/apache/cordova/PluginResult$Status:OK	Lorg/apache/cordova/PluginResult$Status;
            //   6053: invokevirtual 205	org/apache/cordova/PluginResult$Status:ordinal	()I
            //   6056: if_icmpeq +14 -> 6070
            //   6059: aload 12
            //   6061: ifnull +9 -> 6070
            //   6064: aload 12
            //   6066: invokevirtual 211	java/io/File:delete	()Z
            //   6069: pop
            //   6070: aload_0
            //   6071: getfield 36	org/apache/cordova/filetransfer/FileTransfer$4:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
            //   6074: aload 14
            //   6076: invokevirtual 215	org/apache/cordova/filetransfer/FileTransfer$RequestContext:sendPluginResult	(Lorg/apache/cordova/PluginResult;)V
            //   6079: return
            //   6080: astore 26
            //   6082: aload 27
            //   6084: monitorexit
            //   6085: aload 12
            //   6087: astore 22
            //   6089: aload 12
            //   6091: astore 23
            //   6093: aload 12
            //   6095: astore 24
            //   6097: aload 12
            //   6099: astore 25
            //   6101: iload_1
            //   6102: istore_2
            //   6103: aload 15
            //   6105: astore 20
            //   6107: aload 12
            //   6109: astore 16
            //   6111: aload 14
            //   6113: astore 19
            //   6115: aload 17
            //   6117: astore 18
            //   6119: aload 13
            //   6121: astore 21
            //   6123: aload 26
            //   6125: athrow
            //   6126: astore 12
            //   6128: iload_1
            //   6129: istore_2
            //   6130: aload 15
            //   6132: astore 20
            //   6134: aload 24
            //   6136: astore 16
            //   6138: aload 14
            //   6140: astore 19
            //   6142: aload 17
            //   6144: astore 18
            //   6146: aload 13
            //   6148: astore 21
            //   6150: ldc 94
            //   6152: aload 12
            //   6154: invokevirtual 384	org/json/JSONException:getMessage	()Ljava/lang/String;
            //   6157: aload 12
            //   6159: invokestatic 299	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
            //   6162: pop
            //   6163: iload_1
            //   6164: istore_2
            //   6165: aload 15
            //   6167: astore 20
            //   6169: aload 24
            //   6171: astore 16
            //   6173: aload 14
            //   6175: astore 19
            //   6177: aload 17
            //   6179: astore 18
            //   6181: aload 13
            //   6183: astore 21
            //   6185: new 178	org/apache/cordova/PluginResult
            //   6188: dup
            //   6189: getstatic 387	org/apache/cordova/PluginResult$Status:JSON_EXCEPTION	Lorg/apache/cordova/PluginResult$Status;
            //   6192: invokespecial 390	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;)V
            //   6195: astore 13
            //   6197: invokestatic 160	org/apache/cordova/filetransfer/FileTransfer:access$700	()Ljava/util/HashMap;
            //   6200: astore 12
            //   6202: aload 12
            //   6204: monitorenter
            //   6205: invokestatic 160	org/apache/cordova/filetransfer/FileTransfer:access$700	()Ljava/util/HashMap;
            //   6208: aload_0
            //   6209: getfield 56	org/apache/cordova/filetransfer/FileTransfer$4:val$objectId	Ljava/lang/String;
            //   6212: invokevirtual 166	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
            //   6215: pop
            //   6216: aload 12
            //   6218: monitorexit
            //   6219: aload 15
            //   6221: ifnull +38 -> 6259
            //   6224: aload_0
            //   6225: getfield 48	org/apache/cordova/filetransfer/FileTransfer$4:val$trustEveryone	Z
            //   6228: ifeq +31 -> 6259
            //   6231: aload_0
            //   6232: getfield 46	org/apache/cordova/filetransfer/FileTransfer$4:val$useHttps	Z
            //   6235: ifeq +24 -> 6259
            //   6238: aload 15
            //   6240: checkcast 168	javax/net/ssl/HttpsURLConnection
            //   6243: astore 12
            //   6245: aload 12
            //   6247: aload 14
            //   6249: invokevirtual 172	javax/net/ssl/HttpsURLConnection:setHostnameVerifier	(Ljavax/net/ssl/HostnameVerifier;)V
            //   6252: aload 12
            //   6254: aload 17
            //   6256: invokevirtual 176	javax/net/ssl/HttpsURLConnection:setSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
            //   6259: aload 13
            //   6261: astore 12
            //   6263: aload 13
            //   6265: ifnonnull +32 -> 6297
            //   6268: new 178	org/apache/cordova/PluginResult
            //   6271: dup
            //   6272: getstatic 184	org/apache/cordova/PluginResult$Status:ERROR	Lorg/apache/cordova/PluginResult$Status;
            //   6275: getstatic 188	org/apache/cordova/filetransfer/FileTransfer:CONNECTION_ERR	I
            //   6278: aload_0
            //   6279: getfield 52	org/apache/cordova/filetransfer/FileTransfer$4:val$source	Ljava/lang/String;
            //   6282: aload_0
            //   6283: getfield 54	org/apache/cordova/filetransfer/FileTransfer$4:val$target	Ljava/lang/String;
            //   6286: aload 15
            //   6288: aconst_null
            //   6289: invokestatic 192	org/apache/cordova/filetransfer/FileTransfer:access$600	(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;Ljava/lang/Throwable;)Lorg/json/JSONObject;
            //   6292: invokespecial 195	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V
            //   6295: astore 12
            //   6297: iload_1
            //   6298: ifne +28 -> 6326
            //   6301: aload 12
            //   6303: invokevirtual 199	org/apache/cordova/PluginResult:getStatus	()I
            //   6306: getstatic 202	org/apache/cordova/PluginResult$Status:OK	Lorg/apache/cordova/PluginResult$Status;
            //   6309: invokevirtual 205	org/apache/cordova/PluginResult$Status:ordinal	()I
            //   6312: if_icmpeq +14 -> 6326
            //   6315: aload 24
            //   6317: ifnull +9 -> 6326
            //   6320: aload 24
            //   6322: invokevirtual 211	java/io/File:delete	()Z
            //   6325: pop
            //   6326: aload_0
            //   6327: getfield 36	org/apache/cordova/filetransfer/FileTransfer$4:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
            //   6330: aload 12
            //   6332: invokevirtual 215	org/apache/cordova/filetransfer/FileTransfer$RequestContext:sendPluginResult	(Lorg/apache/cordova/PluginResult;)V
            //   6335: return
            //   6336: astore 26
            //   6338: aload 28
            //   6340: monitorexit
            //   6341: aload 12
            //   6343: astore 22
            //   6345: aload 12
            //   6347: astore 23
            //   6349: aload 12
            //   6351: astore 24
            //   6353: aload 12
            //   6355: astore 25
            //   6357: iload_1
            //   6358: istore_2
            //   6359: aload 15
            //   6361: astore 20
            //   6363: aload 12
            //   6365: astore 16
            //   6367: aload 14
            //   6369: astore 19
            //   6371: aload 17
            //   6373: astore 18
            //   6375: aload 13
            //   6377: astore 21
            //   6379: aload 26
            //   6381: athrow
            //   6382: astore 12
            //   6384: iload_1
            //   6385: istore_2
            //   6386: aload 15
            //   6388: astore 20
            //   6390: aload 25
            //   6392: astore 16
            //   6394: aload 14
            //   6396: astore 19
            //   6398: aload 17
            //   6400: astore 18
            //   6402: aload 13
            //   6404: astore 21
            //   6406: getstatic 188	org/apache/cordova/filetransfer/FileTransfer:CONNECTION_ERR	I
            //   6409: aload_0
            //   6410: getfield 52	org/apache/cordova/filetransfer/FileTransfer$4:val$source	Ljava/lang/String;
            //   6413: aload_0
            //   6414: getfield 54	org/apache/cordova/filetransfer/FileTransfer$4:val$target	Ljava/lang/String;
            //   6417: aload 15
            //   6419: aload 12
            //   6421: invokestatic 192	org/apache/cordova/filetransfer/FileTransfer:access$600	(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;Ljava/lang/Throwable;)Lorg/json/JSONObject;
            //   6424: astore 22
            //   6426: iload_1
            //   6427: istore_2
            //   6428: aload 15
            //   6430: astore 20
            //   6432: aload 25
            //   6434: astore 16
            //   6436: aload 14
            //   6438: astore 19
            //   6440: aload 17
            //   6442: astore 18
            //   6444: aload 13
            //   6446: astore 21
            //   6448: ldc 94
            //   6450: aload 22
            //   6452: invokevirtual 295	org/json/JSONObject:toString	()Ljava/lang/String;
            //   6455: aload 12
            //   6457: invokestatic 299	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
            //   6460: pop
            //   6461: iload_1
            //   6462: istore_2
            //   6463: aload 15
            //   6465: astore 20
            //   6467: aload 25
            //   6469: astore 16
            //   6471: aload 14
            //   6473: astore 19
            //   6475: aload 17
            //   6477: astore 18
            //   6479: aload 13
            //   6481: astore 21
            //   6483: new 178	org/apache/cordova/PluginResult
            //   6486: dup
            //   6487: getstatic 302	org/apache/cordova/PluginResult$Status:IO_EXCEPTION	Lorg/apache/cordova/PluginResult$Status;
            //   6490: aload 22
            //   6492: invokespecial 195	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V
            //   6495: astore 13
            //   6497: invokestatic 160	org/apache/cordova/filetransfer/FileTransfer:access$700	()Ljava/util/HashMap;
            //   6500: astore 12
            //   6502: aload 12
            //   6504: monitorenter
            //   6505: invokestatic 160	org/apache/cordova/filetransfer/FileTransfer:access$700	()Ljava/util/HashMap;
            //   6508: aload_0
            //   6509: getfield 56	org/apache/cordova/filetransfer/FileTransfer$4:val$objectId	Ljava/lang/String;
            //   6512: invokevirtual 166	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
            //   6515: pop
            //   6516: aload 12
            //   6518: monitorexit
            //   6519: aload 15
            //   6521: ifnull +38 -> 6559
            //   6524: aload_0
            //   6525: getfield 48	org/apache/cordova/filetransfer/FileTransfer$4:val$trustEveryone	Z
            //   6528: ifeq +31 -> 6559
            //   6531: aload_0
            //   6532: getfield 46	org/apache/cordova/filetransfer/FileTransfer$4:val$useHttps	Z
            //   6535: ifeq +24 -> 6559
            //   6538: aload 15
            //   6540: checkcast 168	javax/net/ssl/HttpsURLConnection
            //   6543: astore 12
            //   6545: aload 12
            //   6547: aload 14
            //   6549: invokevirtual 172	javax/net/ssl/HttpsURLConnection:setHostnameVerifier	(Ljavax/net/ssl/HostnameVerifier;)V
            //   6552: aload 12
            //   6554: aload 17
            //   6556: invokevirtual 176	javax/net/ssl/HttpsURLConnection:setSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
            //   6559: aload 13
            //   6561: astore 12
            //   6563: aload 13
            //   6565: ifnonnull +32 -> 6597
            //   6568: new 178	org/apache/cordova/PluginResult
            //   6571: dup
            //   6572: getstatic 184	org/apache/cordova/PluginResult$Status:ERROR	Lorg/apache/cordova/PluginResult$Status;
            //   6575: getstatic 188	org/apache/cordova/filetransfer/FileTransfer:CONNECTION_ERR	I
            //   6578: aload_0
            //   6579: getfield 52	org/apache/cordova/filetransfer/FileTransfer$4:val$source	Ljava/lang/String;
            //   6582: aload_0
            //   6583: getfield 54	org/apache/cordova/filetransfer/FileTransfer$4:val$target	Ljava/lang/String;
            //   6586: aload 15
            //   6588: aconst_null
            //   6589: invokestatic 192	org/apache/cordova/filetransfer/FileTransfer:access$600	(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;Ljava/lang/Throwable;)Lorg/json/JSONObject;
            //   6592: invokespecial 195	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V
            //   6595: astore 12
            //   6597: iload_1
            //   6598: ifne +28 -> 6626
            //   6601: aload 12
            //   6603: invokevirtual 199	org/apache/cordova/PluginResult:getStatus	()I
            //   6606: getstatic 202	org/apache/cordova/PluginResult$Status:OK	Lorg/apache/cordova/PluginResult$Status;
            //   6609: invokevirtual 205	org/apache/cordova/PluginResult$Status:ordinal	()I
            //   6612: if_icmpeq +14 -> 6626
            //   6615: aload 25
            //   6617: ifnull +9 -> 6626
            //   6620: aload 25
            //   6622: invokevirtual 211	java/io/File:delete	()Z
            //   6625: pop
            //   6626: aload_0
            //   6627: getfield 36	org/apache/cordova/filetransfer/FileTransfer$4:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
            //   6630: aload 12
            //   6632: invokevirtual 215	org/apache/cordova/filetransfer/FileTransfer$RequestContext:sendPluginResult	(Lorg/apache/cordova/PluginResult;)V
            //   6635: return
            //   6636: aload 12
            //   6638: astore 22
            //   6640: aload 12
            //   6642: astore 23
            //   6644: aload 12
            //   6646: astore 24
            //   6648: aload 12
            //   6650: astore 25
            //   6652: iload_1
            //   6653: istore_2
            //   6654: aload 15
            //   6656: astore 20
            //   6658: aload 12
            //   6660: astore 16
            //   6662: aload 14
            //   6664: astore 19
            //   6666: aload 17
            //   6668: astore 18
            //   6670: aload 13
            //   6672: astore 21
            //   6674: getstatic 188	org/apache/cordova/filetransfer/FileTransfer:CONNECTION_ERR	I
            //   6677: aload_0
            //   6678: getfield 52	org/apache/cordova/filetransfer/FileTransfer$4:val$source	Ljava/lang/String;
            //   6681: aload_0
            //   6682: getfield 54	org/apache/cordova/filetransfer/FileTransfer$4:val$target	Ljava/lang/String;
            //   6685: aload 15
            //   6687: aconst_null
            //   6688: invokestatic 192	org/apache/cordova/filetransfer/FileTransfer:access$600	(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;Ljava/lang/Throwable;)Lorg/json/JSONObject;
            //   6691: astore 26
            //   6693: aload 12
            //   6695: astore 22
            //   6697: aload 12
            //   6699: astore 23
            //   6701: aload 12
            //   6703: astore 24
            //   6705: aload 12
            //   6707: astore 25
            //   6709: iload_1
            //   6710: istore_2
            //   6711: aload 15
            //   6713: astore 20
            //   6715: aload 12
            //   6717: astore 16
            //   6719: aload 14
            //   6721: astore 19
            //   6723: aload 17
            //   6725: astore 18
            //   6727: aload 13
            //   6729: astore 21
            //   6731: ldc 94
            //   6733: ldc_w 392
            //   6736: invokestatic 394	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
            //   6739: pop
            //   6740: aload 12
            //   6742: astore 22
            //   6744: aload 12
            //   6746: astore 23
            //   6748: aload 12
            //   6750: astore 24
            //   6752: aload 12
            //   6754: astore 25
            //   6756: iload_1
            //   6757: istore_2
            //   6758: aload 15
            //   6760: astore 20
            //   6762: aload 12
            //   6764: astore 16
            //   6766: aload 14
            //   6768: astore 19
            //   6770: aload 17
            //   6772: astore 18
            //   6774: aload 13
            //   6776: astore 21
            //   6778: new 178	org/apache/cordova/PluginResult
            //   6781: dup
            //   6782: getstatic 302	org/apache/cordova/PluginResult$Status:IO_EXCEPTION	Lorg/apache/cordova/PluginResult$Status;
            //   6785: aload 26
            //   6787: invokespecial 195	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V
            //   6790: astore 26
            //   6792: aload 26
            //   6794: astore 13
            //   6796: goto -855 -> 5941
            //   6799: aload 12
            //   6801: astore 22
            //   6803: aload 12
            //   6805: astore 23
            //   6807: aload 12
            //   6809: astore 24
            //   6811: aload 12
            //   6813: astore 25
            //   6815: iload_1
            //   6816: istore_2
            //   6817: aload 15
            //   6819: astore 20
            //   6821: aload 12
            //   6823: astore 16
            //   6825: aload 14
            //   6827: astore 19
            //   6829: aload 17
            //   6831: astore 18
            //   6833: aload 13
            //   6835: astore 21
            //   6837: ldc 94
            //   6839: ldc_w 396
            //   6842: invokestatic 394	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
            //   6845: pop
            //   6846: aload 12
            //   6848: astore 22
            //   6850: aload 12
            //   6852: astore 23
            //   6854: aload 12
            //   6856: astore 24
            //   6858: aload 12
            //   6860: astore 25
            //   6862: iload_1
            //   6863: istore_2
            //   6864: aload 15
            //   6866: astore 20
            //   6868: aload 12
            //   6870: astore 16
            //   6872: aload 14
            //   6874: astore 19
            //   6876: aload 17
            //   6878: astore 18
            //   6880: aload 13
            //   6882: astore 21
            //   6884: new 178	org/apache/cordova/PluginResult
            //   6887: dup
            //   6888: getstatic 184	org/apache/cordova/PluginResult$Status:ERROR	Lorg/apache/cordova/PluginResult$Status;
            //   6891: ldc_w 396
            //   6894: invokespecial 399	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;Ljava/lang/String;)V
            //   6897: astore 26
            //   6899: aload 26
            //   6901: astore 13
            //   6903: goto -962 -> 5941
            //   6906: astore 12
            //   6908: aload 16
            //   6910: monitorexit
            //   6911: aload 12
            //   6913: athrow
            //   6914: astore 13
            //   6916: aload 12
            //   6918: monitorexit
            //   6919: aload 13
            //   6921: athrow
            //   6922: astore 13
            //   6924: aload 12
            //   6926: monitorexit
            //   6927: aload 13
            //   6929: athrow
            //   6930: astore 13
            //   6932: aload 12
            //   6934: monitorexit
            //   6935: aload 13
            //   6937: athrow
            //   6938: astore 13
            //   6940: aload 12
            //   6942: monitorexit
            //   6943: aload 13
            //   6945: athrow
            //   6946: astore 13
            //   6948: aload 37
            //   6950: astore 12
            //   6952: aload 20
            //   6954: astore 18
            //   6956: aload 27
            //   6958: astore 16
            //   6960: aload 26
            //   6962: astore 20
            //   6964: invokestatic 160	org/apache/cordova/filetransfer/FileTransfer:access$700	()Ljava/util/HashMap;
            //   6967: astore 14
            //   6969: aload 14
            //   6971: monitorenter
            //   6972: invokestatic 160	org/apache/cordova/filetransfer/FileTransfer:access$700	()Ljava/util/HashMap;
            //   6975: aload_0
            //   6976: getfield 56	org/apache/cordova/filetransfer/FileTransfer$4:val$objectId	Ljava/lang/String;
            //   6979: invokevirtual 166	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
            //   6982: pop
            //   6983: aload 14
            //   6985: monitorexit
            //   6986: aload 20
            //   6988: ifnull +38 -> 7026
            //   6991: aload_0
            //   6992: getfield 48	org/apache/cordova/filetransfer/FileTransfer$4:val$trustEveryone	Z
            //   6995: ifeq +31 -> 7026
            //   6998: aload_0
            //   6999: getfield 46	org/apache/cordova/filetransfer/FileTransfer$4:val$useHttps	Z
            //   7002: ifeq +24 -> 7026
            //   7005: aload 20
            //   7007: checkcast 168	javax/net/ssl/HttpsURLConnection
            //   7010: astore 14
            //   7012: aload 14
            //   7014: aload 17
            //   7016: invokevirtual 172	javax/net/ssl/HttpsURLConnection:setHostnameVerifier	(Ljavax/net/ssl/HostnameVerifier;)V
            //   7019: aload 14
            //   7021: aload 18
            //   7023: invokevirtual 176	javax/net/ssl/HttpsURLConnection:setSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
            //   7026: aload 12
            //   7028: astore 14
            //   7030: aload 12
            //   7032: ifnonnull +32 -> 7064
            //   7035: new 178	org/apache/cordova/PluginResult
            //   7038: dup
            //   7039: getstatic 184	org/apache/cordova/PluginResult$Status:ERROR	Lorg/apache/cordova/PluginResult$Status;
            //   7042: getstatic 188	org/apache/cordova/filetransfer/FileTransfer:CONNECTION_ERR	I
            //   7045: aload_0
            //   7046: getfield 52	org/apache/cordova/filetransfer/FileTransfer$4:val$source	Ljava/lang/String;
            //   7049: aload_0
            //   7050: getfield 54	org/apache/cordova/filetransfer/FileTransfer$4:val$target	Ljava/lang/String;
            //   7053: aload 20
            //   7055: aconst_null
            //   7056: invokestatic 192	org/apache/cordova/filetransfer/FileTransfer:access$600	(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;Ljava/lang/Throwable;)Lorg/json/JSONObject;
            //   7059: invokespecial 195	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V
            //   7062: astore 14
            //   7064: iload_1
            //   7065: ifne +28 -> 7093
            //   7068: aload 14
            //   7070: invokevirtual 199	org/apache/cordova/PluginResult:getStatus	()I
            //   7073: getstatic 202	org/apache/cordova/PluginResult$Status:OK	Lorg/apache/cordova/PluginResult$Status;
            //   7076: invokevirtual 205	org/apache/cordova/PluginResult$Status:ordinal	()I
            //   7079: if_icmpeq +14 -> 7093
            //   7082: aload 16
            //   7084: ifnull +9 -> 7093
            //   7087: aload 16
            //   7089: invokevirtual 211	java/io/File:delete	()Z
            //   7092: pop
            //   7093: aload_0
            //   7094: getfield 36	org/apache/cordova/filetransfer/FileTransfer$4:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
            //   7097: aload 14
            //   7099: invokevirtual 215	org/apache/cordova/filetransfer/FileTransfer$RequestContext:sendPluginResult	(Lorg/apache/cordova/PluginResult;)V
            //   7102: aload 13
            //   7104: athrow
            //   7105: astore 12
            //   7107: aload 14
            //   7109: monitorexit
            //   7110: aload 12
            //   7112: athrow
            //   7113: astore 13
            //   7115: aload 21
            //   7117: astore 12
            //   7119: iload_2
            //   7120: istore_1
            //   7121: aload 19
            //   7123: astore 17
            //   7125: goto -161 -> 6964
            //   7128: astore 12
            //   7130: aconst_null
            //   7131: astore 13
            //   7133: iload_2
            //   7134: istore_1
            //   7135: aload 29
            //   7137: astore 15
            //   7139: aload 28
            //   7141: astore 25
            //   7143: aload 18
            //   7145: astore 14
            //   7147: aload 16
            //   7149: astore 17
            //   7151: goto -767 -> 6384
            //   7154: astore 12
            //   7156: aconst_null
            //   7157: astore 13
            //   7159: iload_3
            //   7160: istore_1
            //   7161: aload 31
            //   7163: astore 15
            //   7165: aload 30
            //   7167: astore 24
            //   7169: aload 21
            //   7171: astore 14
            //   7173: aload 19
            //   7175: astore 17
            //   7177: goto -1049 -> 6128
            //   7180: astore 12
            //   7182: aconst_null
            //   7183: astore 13
            //   7185: iload 4
            //   7187: istore_1
            //   7188: aload 33
            //   7190: astore 15
            //   7192: aload 23
            //   7194: astore 14
            //   7196: aload 22
            //   7198: astore 17
            //   7200: goto -2230 -> 4970
            //   7203: astore 12
            //   7205: aconst_null
            //   7206: astore 13
            //   7208: iload 5
            //   7210: istore_1
            //   7211: aload 35
            //   7213: astore 15
            //   7215: aload 34
            //   7217: astore 22
            //   7219: aload 25
            //   7221: astore 14
            //   7223: aload 24
            //   7225: astore 17
            //   7227: goto -2878 -> 4349
            //   7230: astore 16
            //   7232: aload 26
            //   7234: astore 27
            //   7236: goto -1557 -> 5679
            //   7239: astore 16
            //   7241: aload 26
            //   7243: astore 27
            //   7245: goto -1566 -> 5679
            //   7248: astore 16
            //   7250: goto -1641 -> 5609
            //   7253: astore 16
            //   7255: goto -1646 -> 5609
            //   7258: astore 16
            //   7260: goto -1651 -> 5609
            //   7263: goto -5754 -> 1509
            //   7266: goto -1325 -> 5941
            // Local variable table:
            //   start	length	slot	name	signature
            //   0	7269	0	this	4
            //   115	7096	1	i	int
            //   130	7004	2	j	int
            //   145	7015	3	k	int
            //   160	7026	4	m	int
            //   176	7033	5	n	int
            //   108	4180	6	i1	int
            //   93	4084	7	i2	int
            //   96	4100	8	i3	int
            //   99	4116	9	i4	int
            //   102	4132	10	i5	int
            //   105	4149	11	i6	int
            //   201	4128	12	localFile	File
            //   4347	74	12	localFileNotFoundException1	java.io.FileNotFoundException
            //   4465	131	12	localObject1	Object
            //   4601	345	12	localObject2	Object
            //   4964	78	12	localIOException1	IOException
            //   5086	1022	12	localObject3	Object
            //   6126	32	12	localJSONException1	JSONException
            //   6382	74	12	localThrowable1	Throwable
            //   6906	35	12	localObject6	Object
            //   6950	81	12	localObject7	Object
            //   7105	6	12	localObject8	Object
            //   7117	1	12	localObject9	Object
            //   7128	1	12	localThrowable2	Throwable
            //   7154	1	12	localJSONException2	JSONException
            //   7180	1	12	localIOException2	IOException
            //   7203	1	12	localFileNotFoundException2	java.io.FileNotFoundException
            //   12	6890	13	localObject10	Object
            //   6914	6	13	localObject11	Object
            //   6922	6	13	localObject12	Object
            //   6930	6	13	localObject13	Object
            //   6938	6	13	localObject14	Object
            //   6946	157	13	localObject15	Object
            //   7113	1	13	localObject16	Object
            //   7131	76	13	localObject17	Object
            //   27	7187	15	localObject19	Object
            //   7230	1	16	localIllegalAccessException1	IllegalAccessException
            //   7239	1	16	localNoSuchFieldException	NoSuchFieldException
            //   7248	1	16	localInvocationTargetException	InvocationTargetException
            //   7253	1	16	localIllegalAccessException2	IllegalAccessException
            //   7258	1	16	localNoSuchMethodException	NoSuchMethodException
            //   122	7104	17	localObject21	Object
            //   137	4927	18	localObject22	Object
            //   5222	10	18	localObject23	Object
            //   5266	1878	18	localObject24	Object
            //   156	7018	19	localObject25	Object
            //   126	6928	20	localObject26	Object
            //   152	7018	21	localObject27	Object
            //   172	7046	22	localObject28	Object
            //   168	7025	23	localObject29	Object
            //   188	7036	24	localObject30	Object
            //   184	7036	25	localObject31	Object
            //   118	4165	26	localObject32	Object
            //   4301	1068	26	localCloseable	Closeable
            //   5534	404	26	localObject33	Object
            //   6080	44	26	localObject34	Object
            //   6336	44	26	localObject35	Object
            //   6691	551	26	localObject36	Object
            //   84	7160	27	localObject37	Object
            //   72	7068	28	localObject38	Object
            //   133	4068	29	localObject39	Object
            //   4739	2397	29	localObject40	Object
            //   75	7091	30	localObject41	Object
            //   148	7014	31	localObject42	Object
            //   78	5129	32	localObject43	Object
            //   164	7025	33	localObject44	Object
            //   81	7135	34	localObject45	Object
            //   180	7032	35	localObject46	Object
            //   111	5117	36	localObject47	Object
            //   87	6862	37	localObject48	Object
            //   90	4191	38	localObject49	Object
            //   33	3605	39	localObject50	Object
            //   36	2085	40	localObject51	Object
            //   39	2101	41	localObject52	Object
            //   42	2118	42	localObject53	Object
            //   45	2135	43	localObject54	Object
            //   69	2223	44	localHttpsURLConnection	HttpsURLConnection
            //   54	1948	45	localObject55	Object
            //   57	1964	46	localObject56	Object
            //   60	1980	47	localObject57	Object
            //   63	1997	48	localObject58	Object
            //   66	2014	49	localObject59	Object
            //   30	1741	50	localObject60	Object
            //   51	1724	51	localObject61	Object
            //   15	1556	52	localObject62	Object
            //   18	1572	53	localObject63	Object
            //   21	1589	54	localObject64	Object
            //   24	1606	55	localObject65	Object
            //   538	4166	56	localFileProgressResult	FileProgressResult
            // Exception table:
            //   from	to	target	type
            //   1317	1328	4301	finally
            //   4303	4306	4301	finally
            //   1270	1276	4347	java/io/FileNotFoundException
            //   1314	1317	4347	java/io/FileNotFoundException
            //   1366	1371	4347	java/io/FileNotFoundException
            //   1409	1413	4347	java/io/FileNotFoundException
            //   4344	4347	4347	java/io/FileNotFoundException
            //   4779	4785	4347	java/io/FileNotFoundException
            //   4823	4826	4347	java/io/FileNotFoundException
            //   4875	4880	4347	java/io/FileNotFoundException
            //   4918	4923	4347	java/io/FileNotFoundException
            //   4961	4964	4347	java/io/FileNotFoundException
            //   5272	5278	4347	java/io/FileNotFoundException
            //   5316	5319	4347	java/io/FileNotFoundException
            //   5368	5373	4347	java/io/FileNotFoundException
            //   5411	5416	4347	java/io/FileNotFoundException
            //   5454	5483	4347	java/io/FileNotFoundException
            //   5521	5533	4347	java/io/FileNotFoundException
            //   5574	5605	4347	java/io/FileNotFoundException
            //   5656	5679	4347	java/io/FileNotFoundException
            //   5717	5730	4347	java/io/FileNotFoundException
            //   5768	5777	4347	java/io/FileNotFoundException
            //   5815	5828	4347	java/io/FileNotFoundException
            //   5871	5880	4347	java/io/FileNotFoundException
            //   5923	5937	4347	java/io/FileNotFoundException
            //   6123	6126	4347	java/io/FileNotFoundException
            //   6379	6382	4347	java/io/FileNotFoundException
            //   6674	6693	4347	java/io/FileNotFoundException
            //   6731	6740	4347	java/io/FileNotFoundException
            //   6778	6792	4347	java/io/FileNotFoundException
            //   6837	6846	4347	java/io/FileNotFoundException
            //   6884	6899	4347	java/io/FileNotFoundException
            //   1421	1435	4601	finally
            //   4603	4606	4601	finally
            //   1206	1212	4739	finally
            //   1216	1219	4739	finally
            //   4625	4632	4739	finally
            //   4636	4649	4739	finally
            //   4653	4661	4739	finally
            //   4669	4678	4739	finally
            //   4682	4692	4739	finally
            //   4696	4713	4739	finally
            //   4717	4723	4739	finally
            //   4727	4736	4739	finally
            //   5231	5234	4739	finally
            //   1270	1276	4964	java/io/IOException
            //   1314	1317	4964	java/io/IOException
            //   1366	1371	4964	java/io/IOException
            //   1409	1413	4964	java/io/IOException
            //   4344	4347	4964	java/io/IOException
            //   4779	4785	4964	java/io/IOException
            //   4823	4826	4964	java/io/IOException
            //   4875	4880	4964	java/io/IOException
            //   4918	4923	4964	java/io/IOException
            //   4961	4964	4964	java/io/IOException
            //   5272	5278	4964	java/io/IOException
            //   5316	5319	4964	java/io/IOException
            //   5368	5373	4964	java/io/IOException
            //   5411	5416	4964	java/io/IOException
            //   5454	5483	4964	java/io/IOException
            //   5521	5533	4964	java/io/IOException
            //   5574	5605	4964	java/io/IOException
            //   5656	5679	4964	java/io/IOException
            //   5717	5730	4964	java/io/IOException
            //   5768	5777	4964	java/io/IOException
            //   5815	5828	4964	java/io/IOException
            //   5871	5880	4964	java/io/IOException
            //   5923	5937	4964	java/io/IOException
            //   6123	6126	4964	java/io/IOException
            //   6379	6382	4964	java/io/IOException
            //   6674	6693	4964	java/io/IOException
            //   6731	6740	4964	java/io/IOException
            //   6778	6792	4964	java/io/IOException
            //   6837	6846	4964	java/io/IOException
            //   6884	6899	4964	java/io/IOException
            //   1219	1232	5222	finally
            //   4609	4621	5222	finally
            //   5224	5227	5222	finally
            //   5319	5330	6080	finally
            //   6082	6085	6080	finally
            //   1270	1276	6126	org/json/JSONException
            //   1314	1317	6126	org/json/JSONException
            //   1366	1371	6126	org/json/JSONException
            //   1409	1413	6126	org/json/JSONException
            //   4344	4347	6126	org/json/JSONException
            //   4779	4785	6126	org/json/JSONException
            //   4823	4826	6126	org/json/JSONException
            //   4875	4880	6126	org/json/JSONException
            //   4918	4923	6126	org/json/JSONException
            //   4961	4964	6126	org/json/JSONException
            //   5272	5278	6126	org/json/JSONException
            //   5316	5319	6126	org/json/JSONException
            //   5368	5373	6126	org/json/JSONException
            //   5411	5416	6126	org/json/JSONException
            //   5454	5483	6126	org/json/JSONException
            //   5521	5533	6126	org/json/JSONException
            //   5574	5605	6126	org/json/JSONException
            //   5656	5679	6126	org/json/JSONException
            //   5717	5730	6126	org/json/JSONException
            //   5768	5777	6126	org/json/JSONException
            //   5815	5828	6126	org/json/JSONException
            //   5871	5880	6126	org/json/JSONException
            //   5923	5937	6126	org/json/JSONException
            //   6123	6126	6126	org/json/JSONException
            //   6379	6382	6126	org/json/JSONException
            //   6674	6693	6126	org/json/JSONException
            //   6731	6740	6126	org/json/JSONException
            //   6778	6792	6126	org/json/JSONException
            //   6837	6846	6126	org/json/JSONException
            //   6884	6899	6126	org/json/JSONException
            //   4826	4837	6336	finally
            //   6338	6341	6336	finally
            //   1270	1276	6382	java/lang/Throwable
            //   1314	1317	6382	java/lang/Throwable
            //   1366	1371	6382	java/lang/Throwable
            //   1409	1413	6382	java/lang/Throwable
            //   4344	4347	6382	java/lang/Throwable
            //   4779	4785	6382	java/lang/Throwable
            //   4823	4826	6382	java/lang/Throwable
            //   4875	4880	6382	java/lang/Throwable
            //   4918	4923	6382	java/lang/Throwable
            //   4961	4964	6382	java/lang/Throwable
            //   5272	5278	6382	java/lang/Throwable
            //   5316	5319	6382	java/lang/Throwable
            //   5368	5373	6382	java/lang/Throwable
            //   5411	5416	6382	java/lang/Throwable
            //   5454	5483	6382	java/lang/Throwable
            //   5521	5533	6382	java/lang/Throwable
            //   5574	5605	6382	java/lang/Throwable
            //   5656	5679	6382	java/lang/Throwable
            //   5717	5730	6382	java/lang/Throwable
            //   5768	5777	6382	java/lang/Throwable
            //   5815	5828	6382	java/lang/Throwable
            //   5871	5880	6382	java/lang/Throwable
            //   5923	5937	6382	java/lang/Throwable
            //   6123	6126	6382	java/lang/Throwable
            //   6379	6382	6382	java/lang/Throwable
            //   6674	6693	6382	java/lang/Throwable
            //   6731	6740	6382	java/lang/Throwable
            //   6778	6792	6382	java/lang/Throwable
            //   6837	6846	6382	java/lang/Throwable
            //   6884	6899	6382	java/lang/Throwable
            //   5949	5963	6906	finally
            //   6908	6911	6906	finally
            //   4470	4484	6914	finally
            //   6916	6919	6914	finally
            //   5091	5105	6922	finally
            //   6924	6927	6922	finally
            //   6205	6219	6930	finally
            //   6932	6935	6930	finally
            //   6505	6519	6938	finally
            //   6940	6943	6938	finally
            //   190	203	6946	finally
            //   300	309	6946	finally
            //   406	434	6946	finally
            //   531	540	6946	finally
            //   637	644	6946	finally
            //   741	754	6946	finally
            //   851	863	6946	finally
            //   960	966	6946	finally
            //   1063	1073	6946	finally
            //   1170	1184	6946	finally
            //   1645	1658	6946	finally
            //   1763	1770	6946	finally
            //   1875	1882	6946	finally
            //   1979	1986	6946	finally
            //   2083	2090	6946	finally
            //   2187	2194	6946	finally
            //   2291	2299	6946	finally
            //   2396	2403	6946	finally
            //   2500	2516	6946	finally
            //   2618	2627	6946	finally
            //   2724	2733	6946	finally
            //   2830	2837	6946	finally
            //   2934	2943	6946	finally
            //   3040	3045	6946	finally
            //   3142	3153	6946	finally
            //   3268	3273	6946	finally
            //   3370	3399	6946	finally
            //   3496	3515	6946	finally
            //   3612	3626	6946	finally
            //   3741	3749	6946	finally
            //   3846	3859	6946	finally
            //   3956	3965	6946	finally
            //   4062	4068	6946	finally
            //   4165	4176	6946	finally
            //   4273	4280	6946	finally
            //   6972	6986	7105	finally
            //   7107	7110	7105	finally
            //   1270	1276	7113	finally
            //   1314	1317	7113	finally
            //   1366	1371	7113	finally
            //   1409	1413	7113	finally
            //   4344	4347	7113	finally
            //   4371	4391	7113	finally
            //   4413	4426	7113	finally
            //   4448	4462	7113	finally
            //   4779	4785	7113	finally
            //   4823	4826	7113	finally
            //   4875	4880	7113	finally
            //   4918	4923	7113	finally
            //   4961	4964	7113	finally
            //   4992	5012	7113	finally
            //   5034	5047	7113	finally
            //   5069	5083	7113	finally
            //   5272	5278	7113	finally
            //   5316	5319	7113	finally
            //   5368	5373	7113	finally
            //   5411	5416	7113	finally
            //   5454	5483	7113	finally
            //   5521	5533	7113	finally
            //   5574	5605	7113	finally
            //   5656	5679	7113	finally
            //   5717	5730	7113	finally
            //   5768	5777	7113	finally
            //   5815	5828	7113	finally
            //   5871	5880	7113	finally
            //   5923	5937	7113	finally
            //   6123	6126	7113	finally
            //   6150	6163	7113	finally
            //   6185	6197	7113	finally
            //   6379	6382	7113	finally
            //   6406	6426	7113	finally
            //   6448	6461	7113	finally
            //   6483	6497	7113	finally
            //   6674	6693	7113	finally
            //   6731	6740	7113	finally
            //   6778	6792	7113	finally
            //   6837	6846	7113	finally
            //   6884	6899	7113	finally
            //   190	203	7128	java/lang/Throwable
            //   300	309	7128	java/lang/Throwable
            //   406	434	7128	java/lang/Throwable
            //   531	540	7128	java/lang/Throwable
            //   637	644	7128	java/lang/Throwable
            //   741	754	7128	java/lang/Throwable
            //   851	863	7128	java/lang/Throwable
            //   960	966	7128	java/lang/Throwable
            //   1063	1073	7128	java/lang/Throwable
            //   1170	1184	7128	java/lang/Throwable
            //   1645	1658	7128	java/lang/Throwable
            //   1763	1770	7128	java/lang/Throwable
            //   1875	1882	7128	java/lang/Throwable
            //   1979	1986	7128	java/lang/Throwable
            //   2083	2090	7128	java/lang/Throwable
            //   2187	2194	7128	java/lang/Throwable
            //   2291	2299	7128	java/lang/Throwable
            //   2396	2403	7128	java/lang/Throwable
            //   2500	2516	7128	java/lang/Throwable
            //   2618	2627	7128	java/lang/Throwable
            //   2724	2733	7128	java/lang/Throwable
            //   2830	2837	7128	java/lang/Throwable
            //   2934	2943	7128	java/lang/Throwable
            //   3040	3045	7128	java/lang/Throwable
            //   3142	3153	7128	java/lang/Throwable
            //   3268	3273	7128	java/lang/Throwable
            //   3370	3399	7128	java/lang/Throwable
            //   3496	3515	7128	java/lang/Throwable
            //   3612	3626	7128	java/lang/Throwable
            //   3741	3749	7128	java/lang/Throwable
            //   3846	3859	7128	java/lang/Throwable
            //   3956	3965	7128	java/lang/Throwable
            //   4062	4068	7128	java/lang/Throwable
            //   4165	4176	7128	java/lang/Throwable
            //   4273	4280	7128	java/lang/Throwable
            //   190	203	7154	org/json/JSONException
            //   300	309	7154	org/json/JSONException
            //   406	434	7154	org/json/JSONException
            //   531	540	7154	org/json/JSONException
            //   637	644	7154	org/json/JSONException
            //   741	754	7154	org/json/JSONException
            //   851	863	7154	org/json/JSONException
            //   960	966	7154	org/json/JSONException
            //   1063	1073	7154	org/json/JSONException
            //   1170	1184	7154	org/json/JSONException
            //   1645	1658	7154	org/json/JSONException
            //   1763	1770	7154	org/json/JSONException
            //   1875	1882	7154	org/json/JSONException
            //   1979	1986	7154	org/json/JSONException
            //   2083	2090	7154	org/json/JSONException
            //   2187	2194	7154	org/json/JSONException
            //   2291	2299	7154	org/json/JSONException
            //   2396	2403	7154	org/json/JSONException
            //   2500	2516	7154	org/json/JSONException
            //   2618	2627	7154	org/json/JSONException
            //   2724	2733	7154	org/json/JSONException
            //   2830	2837	7154	org/json/JSONException
            //   2934	2943	7154	org/json/JSONException
            //   3040	3045	7154	org/json/JSONException
            //   3142	3153	7154	org/json/JSONException
            //   3268	3273	7154	org/json/JSONException
            //   3370	3399	7154	org/json/JSONException
            //   3496	3515	7154	org/json/JSONException
            //   3612	3626	7154	org/json/JSONException
            //   3741	3749	7154	org/json/JSONException
            //   3846	3859	7154	org/json/JSONException
            //   3956	3965	7154	org/json/JSONException
            //   4062	4068	7154	org/json/JSONException
            //   4165	4176	7154	org/json/JSONException
            //   4273	4280	7154	org/json/JSONException
            //   190	203	7180	java/io/IOException
            //   300	309	7180	java/io/IOException
            //   406	434	7180	java/io/IOException
            //   531	540	7180	java/io/IOException
            //   637	644	7180	java/io/IOException
            //   741	754	7180	java/io/IOException
            //   851	863	7180	java/io/IOException
            //   960	966	7180	java/io/IOException
            //   1063	1073	7180	java/io/IOException
            //   1170	1184	7180	java/io/IOException
            //   1645	1658	7180	java/io/IOException
            //   1763	1770	7180	java/io/IOException
            //   1875	1882	7180	java/io/IOException
            //   1979	1986	7180	java/io/IOException
            //   2083	2090	7180	java/io/IOException
            //   2187	2194	7180	java/io/IOException
            //   2291	2299	7180	java/io/IOException
            //   2396	2403	7180	java/io/IOException
            //   2500	2516	7180	java/io/IOException
            //   2618	2627	7180	java/io/IOException
            //   2724	2733	7180	java/io/IOException
            //   2830	2837	7180	java/io/IOException
            //   2934	2943	7180	java/io/IOException
            //   3040	3045	7180	java/io/IOException
            //   3142	3153	7180	java/io/IOException
            //   3268	3273	7180	java/io/IOException
            //   3370	3399	7180	java/io/IOException
            //   3496	3515	7180	java/io/IOException
            //   3612	3626	7180	java/io/IOException
            //   3741	3749	7180	java/io/IOException
            //   3846	3859	7180	java/io/IOException
            //   3956	3965	7180	java/io/IOException
            //   4062	4068	7180	java/io/IOException
            //   4165	4176	7180	java/io/IOException
            //   4273	4280	7180	java/io/IOException
            //   190	203	7203	java/io/FileNotFoundException
            //   300	309	7203	java/io/FileNotFoundException
            //   406	434	7203	java/io/FileNotFoundException
            //   531	540	7203	java/io/FileNotFoundException
            //   637	644	7203	java/io/FileNotFoundException
            //   741	754	7203	java/io/FileNotFoundException
            //   851	863	7203	java/io/FileNotFoundException
            //   960	966	7203	java/io/FileNotFoundException
            //   1063	1073	7203	java/io/FileNotFoundException
            //   1170	1184	7203	java/io/FileNotFoundException
            //   1645	1658	7203	java/io/FileNotFoundException
            //   1763	1770	7203	java/io/FileNotFoundException
            //   1875	1882	7203	java/io/FileNotFoundException
            //   1979	1986	7203	java/io/FileNotFoundException
            //   2083	2090	7203	java/io/FileNotFoundException
            //   2187	2194	7203	java/io/FileNotFoundException
            //   2291	2299	7203	java/io/FileNotFoundException
            //   2396	2403	7203	java/io/FileNotFoundException
            //   2500	2516	7203	java/io/FileNotFoundException
            //   2618	2627	7203	java/io/FileNotFoundException
            //   2724	2733	7203	java/io/FileNotFoundException
            //   2830	2837	7203	java/io/FileNotFoundException
            //   2934	2943	7203	java/io/FileNotFoundException
            //   3040	3045	7203	java/io/FileNotFoundException
            //   3142	3153	7203	java/io/FileNotFoundException
            //   3268	3273	7203	java/io/FileNotFoundException
            //   3370	3399	7203	java/io/FileNotFoundException
            //   3496	3515	7203	java/io/FileNotFoundException
            //   3612	3626	7203	java/io/FileNotFoundException
            //   3741	3749	7203	java/io/FileNotFoundException
            //   3846	3859	7203	java/io/FileNotFoundException
            //   3956	3965	7203	java/io/FileNotFoundException
            //   4062	4068	7203	java/io/FileNotFoundException
            //   4165	4176	7203	java/io/FileNotFoundException
            //   4273	4280	7203	java/io/FileNotFoundException
            //   5656	5679	7230	java/lang/IllegalAccessException
            //   5656	5679	7239	java/lang/NoSuchFieldException
            //   5574	5605	7248	java/lang/reflect/InvocationTargetException
            //   5574	5605	7253	java/lang/IllegalAccessException
            //   5574	5605	7258	java/lang/NoSuchMethodException
          }
        });
        return;
      }
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      for (;;)
      {
        JSONArray localJSONArray1 = ???;
      }
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      for (;;)
      {
        JSONArray localJSONArray2 = ???;
      }
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      for (;;)
      {
        localJSONArray3 = ???;
      }
    }
  }
  
  private static String getArgument(JSONArray paramJSONArray, int paramInt, String paramString)
  {
    Object localObject = paramString;
    if (paramJSONArray.length() > paramInt)
    {
      paramJSONArray = paramJSONArray.optString(paramInt);
      if (paramJSONArray != null)
      {
        localObject = paramJSONArray;
        if (!"null".equals(paramJSONArray)) {}
      }
      else
      {
        localObject = paramString;
      }
    }
    return (String)localObject;
  }
  
  private String getCookies(String paramString)
  {
    int i = 0;
    Object localObject1 = null;
    Object localObject2 = this.webView.getClass();
    try
    {
      localObject2 = ((Class)localObject2).getMethod("getCookieManager", new Class[0]);
      Class localClass = ((Method)localObject2).getReturnType();
      localObject2 = (String)localClass.getMethod("getCookie", new Class[] { String.class }).invoke(localClass.cast(((Method)localObject2).invoke(this.webView, new Object[0])), new Object[] { paramString });
      i = 1;
      localObject1 = localObject2;
    }
    catch (ClassCastException localClassCastException)
    {
      for (;;) {}
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      for (;;) {}
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      for (;;) {}
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      for (;;) {}
    }
    localObject2 = localObject1;
    if (i == 0)
    {
      localObject2 = localObject1;
      if (CookieManager.getInstance() != null) {
        localObject2 = CookieManager.getInstance().getCookie(paramString);
      }
    }
    return (String)localObject2;
  }
  
  private static TrackingInputStream getInputStream(URLConnection paramURLConnection)
    throws IOException
  {
    String str = paramURLConnection.getContentEncoding();
    if ((str != null) && (str.equalsIgnoreCase("gzip"))) {
      return new TrackingGZIPInputStream(new ExposedGZIPInputStream(paramURLConnection.getInputStream()));
    }
    return new SimpleTrackingInputStream(paramURLConnection.getInputStream());
  }
  
  private static void safeClose(Closeable paramCloseable)
  {
    if (paramCloseable != null) {}
    try
    {
      paramCloseable.close();
      return;
    }
    catch (IOException paramCloseable) {}
  }
  
  private static SSLSocketFactory trustAllHosts(HttpsURLConnection paramHttpsURLConnection)
  {
    SSLSocketFactory localSSLSocketFactory = paramHttpsURLConnection.getSSLSocketFactory();
    try
    {
      SSLContext localSSLContext = SSLContext.getInstance("TLS");
      localSSLContext.init(null, trustAllCerts, new SecureRandom());
      paramHttpsURLConnection.setSSLSocketFactory(localSSLContext.getSocketFactory());
      return localSSLSocketFactory;
    }
    catch (Exception paramHttpsURLConnection)
    {
      Log.e("FileTransfer", paramHttpsURLConnection.getMessage(), paramHttpsURLConnection);
    }
    return localSSLSocketFactory;
  }
  
  private void upload(final String paramString1, final String paramString2, final JSONArray paramJSONArray, CallbackContext arg4)
    throws JSONException
  {
    Log.d("FileTransfer", "upload " + paramString1 + " to " + paramString2);
    final String str1 = getArgument(paramJSONArray, 2, "file");
    final String str2 = getArgument(paramJSONArray, 3, "image.jpg");
    final String str3 = getArgument(paramJSONArray, 4, "image/jpeg");
    final JSONObject localJSONObject1;
    final boolean bool3;
    final boolean bool1;
    label112:
    final JSONObject localJSONObject2;
    label131:
    final String str4;
    final String str5;
    final CordovaResourceApi localCordovaResourceApi;
    final Uri localUri;
    label427:
    int i;
    if (paramJSONArray.optJSONObject(5) == null)
    {
      localJSONObject1 = new JSONObject();
      bool3 = paramJSONArray.optBoolean(6);
      if ((!paramJSONArray.optBoolean(7)) && (!paramJSONArray.isNull(7))) {
        break label531;
      }
      bool1 = true;
      if (paramJSONArray.optJSONObject(8) != null) {
        break label537;
      }
      localJSONObject2 = localJSONObject1.optJSONObject("headers");
      str4 = paramJSONArray.getString(9);
      str5 = getArgument(paramJSONArray, 10, "POST");
      localCordovaResourceApi = this.webView.getResourceApi();
      Log.d("FileTransfer", "fileKey: " + str1);
      Log.d("FileTransfer", "fileName: " + str2);
      Log.d("FileTransfer", "mimeType: " + str3);
      Log.d("FileTransfer", "params: " + localJSONObject1);
      Log.d("FileTransfer", "trustEveryone: " + bool3);
      Log.d("FileTransfer", "chunkedMode: " + bool1);
      Log.d("FileTransfer", "headers: " + localJSONObject2);
      Log.d("FileTransfer", "objectId: " + str4);
      Log.d("FileTransfer", "httpMethod: " + str5);
      localUri = localCordovaResourceApi.remapUri(Uri.parse(paramString2));
      paramJSONArray = Uri.parse(paramString1);
      if (paramJSONArray.getScheme() == null) {
        break label548;
      }
      paramJSONArray = localCordovaResourceApi.remapUri(paramJSONArray);
      i = CordovaResourceApi.getUriType(localUri);
      if (i != 6) {
        break label563;
      }
    }
    label531:
    label537:
    label548:
    label563:
    for (final boolean bool2 = true;; bool2 = false)
    {
      if ((i == 5) || (bool2)) {
        break label569;
      }
      paramString1 = createFileTransferError(INVALID_URL_ERR, paramString1, paramString2, null, Integer.valueOf(0), null);
      Log.e("FileTransfer", "Unsupported URI: " + localUri);
      ???.sendPluginResult(new PluginResult(PluginResult.Status.IO_EXCEPTION, paramString1));
      return;
      localJSONObject1 = paramJSONArray.optJSONObject(5);
      break;
      bool1 = false;
      break label112;
      localJSONObject2 = paramJSONArray.optJSONObject(8);
      break label131;
      paramJSONArray = Uri.fromFile(new File(paramString1));
      break label427;
    }
    label569:
    final RequestContext localRequestContext = new RequestContext(paramString1, paramString2, ???);
    synchronized (activeRequests)
    {
      activeRequests.put(str4, localRequestContext);
      this.cordova.getThreadPool().execute(new Runnable()
      {
        /* Error */
        public void run()
        {
          // Byte code:
          //   0: aload_0
          //   1: getfield 41	org/apache/cordova/filetransfer/FileTransfer$1:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   4: getfield 89	org/apache/cordova/filetransfer/FileTransfer$RequestContext:aborted	Z
          //   7: ifeq +4 -> 11
          //   10: return
          //   11: aconst_null
          //   12: astore 18
          //   14: aconst_null
          //   15: astore 20
          //   17: aconst_null
          //   18: astore 45
          //   20: aconst_null
          //   21: astore 46
          //   23: aconst_null
          //   24: astore 17
          //   26: aconst_null
          //   27: astore 33
          //   29: aconst_null
          //   30: astore 34
          //   32: aconst_null
          //   33: astore 35
          //   35: aconst_null
          //   36: astore 36
          //   38: aconst_null
          //   39: astore 37
          //   41: aconst_null
          //   42: astore 43
          //   44: aconst_null
          //   45: astore 38
          //   47: aconst_null
          //   48: astore 39
          //   50: aconst_null
          //   51: astore 40
          //   53: aconst_null
          //   54: astore 41
          //   56: aconst_null
          //   57: astore 42
          //   59: aconst_null
          //   60: astore 44
          //   62: iconst_0
          //   63: istore 7
          //   65: iconst_0
          //   66: istore 8
          //   68: iconst_0
          //   69: istore_2
          //   70: iconst_m1
          //   71: istore_1
          //   72: aload 17
          //   74: astore 29
          //   76: aload 33
          //   78: astore 21
          //   80: aload 38
          //   82: astore 22
          //   84: aload 18
          //   86: astore 30
          //   88: iload_1
          //   89: istore_3
          //   90: aload 34
          //   92: astore 23
          //   94: aload 39
          //   96: astore 24
          //   98: iload 8
          //   100: istore 5
          //   102: aload 20
          //   104: astore 32
          //   106: aload 35
          //   108: astore 27
          //   110: aload 40
          //   112: astore 28
          //   114: aload 45
          //   116: astore 31
          //   118: aload 36
          //   120: astore 25
          //   122: aload 41
          //   124: astore 26
          //   126: aload 46
          //   128: astore 19
          //   130: aload 37
          //   132: astore 15
          //   134: aload 42
          //   136: astore 16
          //   138: new 91	org/apache/cordova/filetransfer/FileUploadResult
          //   141: dup
          //   142: invokespecial 92	org/apache/cordova/filetransfer/FileUploadResult:<init>	()V
          //   145: astore 47
          //   147: aload 17
          //   149: astore 29
          //   151: aload 33
          //   153: astore 21
          //   155: aload 38
          //   157: astore 22
          //   159: aload 18
          //   161: astore 30
          //   163: iload_1
          //   164: istore_3
          //   165: aload 34
          //   167: astore 23
          //   169: aload 39
          //   171: astore 24
          //   173: iload 8
          //   175: istore 5
          //   177: aload 20
          //   179: astore 32
          //   181: aload 35
          //   183: astore 27
          //   185: aload 40
          //   187: astore 28
          //   189: aload 45
          //   191: astore 31
          //   193: aload 36
          //   195: astore 25
          //   197: aload 41
          //   199: astore 26
          //   201: aload 46
          //   203: astore 19
          //   205: aload 37
          //   207: astore 15
          //   209: aload 42
          //   211: astore 16
          //   213: new 94	org/apache/cordova/filetransfer/FileProgressResult
          //   216: dup
          //   217: invokespecial 95	org/apache/cordova/filetransfer/FileProgressResult:<init>	()V
          //   220: astore 48
          //   222: aload 17
          //   224: astore 29
          //   226: aload 33
          //   228: astore 21
          //   230: aload 38
          //   232: astore 22
          //   234: aload 18
          //   236: astore 30
          //   238: iload_1
          //   239: istore_3
          //   240: aload 34
          //   242: astore 23
          //   244: aload 39
          //   246: astore 24
          //   248: iload 8
          //   250: istore 5
          //   252: aload 20
          //   254: astore 32
          //   256: aload 35
          //   258: astore 27
          //   260: aload 40
          //   262: astore 28
          //   264: aload 45
          //   266: astore 31
          //   268: aload 36
          //   270: astore 25
          //   272: aload 41
          //   274: astore 26
          //   276: aload 46
          //   278: astore 19
          //   280: aload 37
          //   282: astore 15
          //   284: aload 42
          //   286: astore 16
          //   288: aload_0
          //   289: getfield 43	org/apache/cordova/filetransfer/FileTransfer$1:val$resourceApi	Lorg/apache/cordova/CordovaResourceApi;
          //   292: aload_0
          //   293: getfield 45	org/apache/cordova/filetransfer/FileTransfer$1:val$targetUri	Landroid/net/Uri;
          //   296: invokevirtual 101	org/apache/cordova/CordovaResourceApi:createHttpConnection	(Landroid/net/Uri;)Ljava/net/HttpURLConnection;
          //   299: astore 20
          //   301: aload 43
          //   303: astore 18
          //   305: aload 44
          //   307: astore 17
          //   309: aload 20
          //   311: astore 29
          //   313: aload 33
          //   315: astore 21
          //   317: aload 38
          //   319: astore 22
          //   321: aload 20
          //   323: astore 30
          //   325: iload_1
          //   326: istore_3
          //   327: aload 34
          //   329: astore 23
          //   331: aload 39
          //   333: astore 24
          //   335: iload 8
          //   337: istore 5
          //   339: aload 20
          //   341: astore 32
          //   343: aload 35
          //   345: astore 27
          //   347: aload 40
          //   349: astore 28
          //   351: aload 20
          //   353: astore 31
          //   355: aload 36
          //   357: astore 25
          //   359: aload 41
          //   361: astore 26
          //   363: aload 20
          //   365: astore 19
          //   367: aload 37
          //   369: astore 15
          //   371: aload 42
          //   373: astore 16
          //   375: aload_0
          //   376: getfield 47	org/apache/cordova/filetransfer/FileTransfer$1:val$useHttps	Z
          //   379: ifeq +377 -> 756
          //   382: aload 43
          //   384: astore 18
          //   386: aload 44
          //   388: astore 17
          //   390: aload 20
          //   392: astore 29
          //   394: aload 33
          //   396: astore 21
          //   398: aload 38
          //   400: astore 22
          //   402: aload 20
          //   404: astore 30
          //   406: iload_1
          //   407: istore_3
          //   408: aload 34
          //   410: astore 23
          //   412: aload 39
          //   414: astore 24
          //   416: iload 8
          //   418: istore 5
          //   420: aload 20
          //   422: astore 32
          //   424: aload 35
          //   426: astore 27
          //   428: aload 40
          //   430: astore 28
          //   432: aload 20
          //   434: astore 31
          //   436: aload 36
          //   438: astore 25
          //   440: aload 41
          //   442: astore 26
          //   444: aload 20
          //   446: astore 19
          //   448: aload 37
          //   450: astore 15
          //   452: aload 42
          //   454: astore 16
          //   456: aload_0
          //   457: getfield 49	org/apache/cordova/filetransfer/FileTransfer$1:val$trustEveryone	Z
          //   460: ifeq +296 -> 756
          //   463: aload 20
          //   465: astore 29
          //   467: aload 33
          //   469: astore 21
          //   471: aload 38
          //   473: astore 22
          //   475: aload 20
          //   477: astore 30
          //   479: iload_1
          //   480: istore_3
          //   481: aload 34
          //   483: astore 23
          //   485: aload 39
          //   487: astore 24
          //   489: iload 8
          //   491: istore 5
          //   493: aload 20
          //   495: astore 32
          //   497: aload 35
          //   499: astore 27
          //   501: aload 40
          //   503: astore 28
          //   505: aload 20
          //   507: astore 31
          //   509: aload 36
          //   511: astore 25
          //   513: aload 41
          //   515: astore 26
          //   517: aload 20
          //   519: astore 19
          //   521: aload 37
          //   523: astore 15
          //   525: aload 42
          //   527: astore 16
          //   529: aload 20
          //   531: checkcast 103	javax/net/ssl/HttpsURLConnection
          //   534: astore 43
          //   536: aload 20
          //   538: astore 29
          //   540: aload 33
          //   542: astore 21
          //   544: aload 38
          //   546: astore 22
          //   548: aload 20
          //   550: astore 30
          //   552: iload_1
          //   553: istore_3
          //   554: aload 34
          //   556: astore 23
          //   558: aload 39
          //   560: astore 24
          //   562: iload 8
          //   564: istore 5
          //   566: aload 20
          //   568: astore 32
          //   570: aload 35
          //   572: astore 27
          //   574: aload 40
          //   576: astore 28
          //   578: aload 20
          //   580: astore 31
          //   582: aload 36
          //   584: astore 25
          //   586: aload 41
          //   588: astore 26
          //   590: aload 20
          //   592: astore 19
          //   594: aload 37
          //   596: astore 15
          //   598: aload 42
          //   600: astore 16
          //   602: aload 43
          //   604: invokestatic 107	org/apache/cordova/filetransfer/FileTransfer:access$000	(Ljavax/net/ssl/HttpsURLConnection;)Ljavax/net/ssl/SSLSocketFactory;
          //   607: astore 17
          //   609: aload 20
          //   611: astore 29
          //   613: aload 33
          //   615: astore 21
          //   617: aload 17
          //   619: astore 22
          //   621: aload 20
          //   623: astore 30
          //   625: iload_1
          //   626: istore_3
          //   627: aload 34
          //   629: astore 23
          //   631: aload 17
          //   633: astore 24
          //   635: iload 8
          //   637: istore 5
          //   639: aload 20
          //   641: astore 32
          //   643: aload 35
          //   645: astore 27
          //   647: aload 17
          //   649: astore 28
          //   651: aload 20
          //   653: astore 31
          //   655: aload 36
          //   657: astore 25
          //   659: aload 17
          //   661: astore 26
          //   663: aload 20
          //   665: astore 19
          //   667: aload 37
          //   669: astore 15
          //   671: aload 17
          //   673: astore 16
          //   675: aload 43
          //   677: invokevirtual 111	javax/net/ssl/HttpsURLConnection:getHostnameVerifier	()Ljavax/net/ssl/HostnameVerifier;
          //   680: astore 18
          //   682: aload 20
          //   684: astore 29
          //   686: aload 18
          //   688: astore 21
          //   690: aload 17
          //   692: astore 22
          //   694: aload 20
          //   696: astore 30
          //   698: iload_1
          //   699: istore_3
          //   700: aload 18
          //   702: astore 23
          //   704: aload 17
          //   706: astore 24
          //   708: iload 8
          //   710: istore 5
          //   712: aload 20
          //   714: astore 32
          //   716: aload 18
          //   718: astore 27
          //   720: aload 17
          //   722: astore 28
          //   724: aload 20
          //   726: astore 31
          //   728: aload 18
          //   730: astore 25
          //   732: aload 17
          //   734: astore 26
          //   736: aload 20
          //   738: astore 19
          //   740: aload 18
          //   742: astore 15
          //   744: aload 17
          //   746: astore 16
          //   748: aload 43
          //   750: invokestatic 114	org/apache/cordova/filetransfer/FileTransfer:access$100	()Ljavax/net/ssl/HostnameVerifier;
          //   753: invokevirtual 118	javax/net/ssl/HttpsURLConnection:setHostnameVerifier	(Ljavax/net/ssl/HostnameVerifier;)V
          //   756: aload 20
          //   758: astore 29
          //   760: aload 18
          //   762: astore 21
          //   764: aload 17
          //   766: astore 22
          //   768: aload 20
          //   770: astore 30
          //   772: iload_1
          //   773: istore_3
          //   774: aload 18
          //   776: astore 23
          //   778: aload 17
          //   780: astore 24
          //   782: iload 8
          //   784: istore 5
          //   786: aload 20
          //   788: astore 32
          //   790: aload 18
          //   792: astore 27
          //   794: aload 17
          //   796: astore 28
          //   798: aload 20
          //   800: astore 31
          //   802: aload 18
          //   804: astore 25
          //   806: aload 17
          //   808: astore 26
          //   810: aload 20
          //   812: astore 19
          //   814: aload 18
          //   816: astore 15
          //   818: aload 17
          //   820: astore 16
          //   822: aload 20
          //   824: iconst_1
          //   825: invokevirtual 124	java/net/HttpURLConnection:setDoInput	(Z)V
          //   828: aload 20
          //   830: astore 29
          //   832: aload 18
          //   834: astore 21
          //   836: aload 17
          //   838: astore 22
          //   840: aload 20
          //   842: astore 30
          //   844: iload_1
          //   845: istore_3
          //   846: aload 18
          //   848: astore 23
          //   850: aload 17
          //   852: astore 24
          //   854: iload 8
          //   856: istore 5
          //   858: aload 20
          //   860: astore 32
          //   862: aload 18
          //   864: astore 27
          //   866: aload 17
          //   868: astore 28
          //   870: aload 20
          //   872: astore 31
          //   874: aload 18
          //   876: astore 25
          //   878: aload 17
          //   880: astore 26
          //   882: aload 20
          //   884: astore 19
          //   886: aload 18
          //   888: astore 15
          //   890: aload 17
          //   892: astore 16
          //   894: aload 20
          //   896: iconst_1
          //   897: invokevirtual 127	java/net/HttpURLConnection:setDoOutput	(Z)V
          //   900: aload 20
          //   902: astore 29
          //   904: aload 18
          //   906: astore 21
          //   908: aload 17
          //   910: astore 22
          //   912: aload 20
          //   914: astore 30
          //   916: iload_1
          //   917: istore_3
          //   918: aload 18
          //   920: astore 23
          //   922: aload 17
          //   924: astore 24
          //   926: iload 8
          //   928: istore 5
          //   930: aload 20
          //   932: astore 32
          //   934: aload 18
          //   936: astore 27
          //   938: aload 17
          //   940: astore 28
          //   942: aload 20
          //   944: astore 31
          //   946: aload 18
          //   948: astore 25
          //   950: aload 17
          //   952: astore 26
          //   954: aload 20
          //   956: astore 19
          //   958: aload 18
          //   960: astore 15
          //   962: aload 17
          //   964: astore 16
          //   966: aload 20
          //   968: iconst_0
          //   969: invokevirtual 130	java/net/HttpURLConnection:setUseCaches	(Z)V
          //   972: aload 20
          //   974: astore 29
          //   976: aload 18
          //   978: astore 21
          //   980: aload 17
          //   982: astore 22
          //   984: aload 20
          //   986: astore 30
          //   988: iload_1
          //   989: istore_3
          //   990: aload 18
          //   992: astore 23
          //   994: aload 17
          //   996: astore 24
          //   998: iload 8
          //   1000: istore 5
          //   1002: aload 20
          //   1004: astore 32
          //   1006: aload 18
          //   1008: astore 27
          //   1010: aload 17
          //   1012: astore 28
          //   1014: aload 20
          //   1016: astore 31
          //   1018: aload 18
          //   1020: astore 25
          //   1022: aload 17
          //   1024: astore 26
          //   1026: aload 20
          //   1028: astore 19
          //   1030: aload 18
          //   1032: astore 15
          //   1034: aload 17
          //   1036: astore 16
          //   1038: aload 20
          //   1040: aload_0
          //   1041: getfield 51	org/apache/cordova/filetransfer/FileTransfer$1:val$httpMethod	Ljava/lang/String;
          //   1044: invokevirtual 134	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
          //   1047: aload 20
          //   1049: astore 29
          //   1051: aload 18
          //   1053: astore 21
          //   1055: aload 17
          //   1057: astore 22
          //   1059: aload 20
          //   1061: astore 30
          //   1063: iload_1
          //   1064: istore_3
          //   1065: aload 18
          //   1067: astore 23
          //   1069: aload 17
          //   1071: astore 24
          //   1073: iload 8
          //   1075: istore 5
          //   1077: aload 20
          //   1079: astore 32
          //   1081: aload 18
          //   1083: astore 27
          //   1085: aload 17
          //   1087: astore 28
          //   1089: aload 20
          //   1091: astore 31
          //   1093: aload 18
          //   1095: astore 25
          //   1097: aload 17
          //   1099: astore 26
          //   1101: aload 20
          //   1103: astore 19
          //   1105: aload 18
          //   1107: astore 15
          //   1109: aload 17
          //   1111: astore 16
          //   1113: aload_0
          //   1114: getfield 53	org/apache/cordova/filetransfer/FileTransfer$1:val$headers	Lorg/json/JSONObject;
          //   1117: ifnull +6899 -> 8016
          //   1120: aload 20
          //   1122: astore 29
          //   1124: aload 18
          //   1126: astore 21
          //   1128: aload 17
          //   1130: astore 22
          //   1132: aload 20
          //   1134: astore 30
          //   1136: iload_1
          //   1137: istore_3
          //   1138: aload 18
          //   1140: astore 23
          //   1142: aload 17
          //   1144: astore 24
          //   1146: iload 8
          //   1148: istore 5
          //   1150: aload 20
          //   1152: astore 32
          //   1154: aload 18
          //   1156: astore 27
          //   1158: aload 17
          //   1160: astore 28
          //   1162: aload 20
          //   1164: astore 31
          //   1166: aload 18
          //   1168: astore 25
          //   1170: aload 17
          //   1172: astore 26
          //   1174: aload 20
          //   1176: astore 19
          //   1178: aload 18
          //   1180: astore 15
          //   1182: aload 17
          //   1184: astore 16
          //   1186: aload_0
          //   1187: getfield 53	org/apache/cordova/filetransfer/FileTransfer$1:val$headers	Lorg/json/JSONObject;
          //   1190: ldc -120
          //   1192: invokevirtual 142	org/json/JSONObject:has	(Ljava/lang/String;)Z
          //   1195: ifne +3008 -> 4203
          //   1198: goto +6818 -> 8016
          //   1201: iload 6
          //   1203: ifeq +78 -> 1281
          //   1206: aload 20
          //   1208: astore 29
          //   1210: aload 18
          //   1212: astore 21
          //   1214: aload 17
          //   1216: astore 22
          //   1218: aload 20
          //   1220: astore 30
          //   1222: iload_1
          //   1223: istore_3
          //   1224: aload 18
          //   1226: astore 23
          //   1228: aload 17
          //   1230: astore 24
          //   1232: iload 8
          //   1234: istore 5
          //   1236: aload 20
          //   1238: astore 32
          //   1240: aload 18
          //   1242: astore 27
          //   1244: aload 17
          //   1246: astore 28
          //   1248: aload 20
          //   1250: astore 31
          //   1252: aload 18
          //   1254: astore 25
          //   1256: aload 17
          //   1258: astore 26
          //   1260: aload 20
          //   1262: astore 19
          //   1264: aload 18
          //   1266: astore 15
          //   1268: aload 17
          //   1270: astore 16
          //   1272: aload 20
          //   1274: ldc -120
          //   1276: ldc -112
          //   1278: invokevirtual 148	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
          //   1281: aload 20
          //   1283: astore 29
          //   1285: aload 18
          //   1287: astore 21
          //   1289: aload 17
          //   1291: astore 22
          //   1293: aload 20
          //   1295: astore 30
          //   1297: iload_1
          //   1298: istore_3
          //   1299: aload 18
          //   1301: astore 23
          //   1303: aload 17
          //   1305: astore 24
          //   1307: iload 8
          //   1309: istore 5
          //   1311: aload 20
          //   1313: astore 32
          //   1315: aload 18
          //   1317: astore 27
          //   1319: aload 17
          //   1321: astore 28
          //   1323: aload 20
          //   1325: astore 31
          //   1327: aload 18
          //   1329: astore 25
          //   1331: aload 17
          //   1333: astore 26
          //   1335: aload 20
          //   1337: astore 19
          //   1339: aload 18
          //   1341: astore 15
          //   1343: aload 17
          //   1345: astore 16
          //   1347: aload_0
          //   1348: getfield 39	org/apache/cordova/filetransfer/FileTransfer$1:this$0	Lorg/apache/cordova/filetransfer/FileTransfer;
          //   1351: aload_0
          //   1352: getfield 55	org/apache/cordova/filetransfer/FileTransfer$1:val$target	Ljava/lang/String;
          //   1355: invokestatic 152	org/apache/cordova/filetransfer/FileTransfer:access$200	(Lorg/apache/cordova/filetransfer/FileTransfer;Ljava/lang/String;)Ljava/lang/String;
          //   1358: astore 33
          //   1360: aload 33
          //   1362: ifnull +78 -> 1440
          //   1365: aload 20
          //   1367: astore 29
          //   1369: aload 18
          //   1371: astore 21
          //   1373: aload 17
          //   1375: astore 22
          //   1377: aload 20
          //   1379: astore 30
          //   1381: iload_1
          //   1382: istore_3
          //   1383: aload 18
          //   1385: astore 23
          //   1387: aload 17
          //   1389: astore 24
          //   1391: iload 8
          //   1393: istore 5
          //   1395: aload 20
          //   1397: astore 32
          //   1399: aload 18
          //   1401: astore 27
          //   1403: aload 17
          //   1405: astore 28
          //   1407: aload 20
          //   1409: astore 31
          //   1411: aload 18
          //   1413: astore 25
          //   1415: aload 17
          //   1417: astore 26
          //   1419: aload 20
          //   1421: astore 19
          //   1423: aload 18
          //   1425: astore 15
          //   1427: aload 17
          //   1429: astore 16
          //   1431: aload 20
          //   1433: ldc -102
          //   1435: aload 33
          //   1437: invokevirtual 148	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
          //   1440: aload 20
          //   1442: astore 29
          //   1444: aload 18
          //   1446: astore 21
          //   1448: aload 17
          //   1450: astore 22
          //   1452: aload 20
          //   1454: astore 30
          //   1456: iload_1
          //   1457: istore_3
          //   1458: aload 18
          //   1460: astore 23
          //   1462: aload 17
          //   1464: astore 24
          //   1466: iload 8
          //   1468: istore 5
          //   1470: aload 20
          //   1472: astore 32
          //   1474: aload 18
          //   1476: astore 27
          //   1478: aload 17
          //   1480: astore 28
          //   1482: aload 20
          //   1484: astore 31
          //   1486: aload 18
          //   1488: astore 25
          //   1490: aload 17
          //   1492: astore 26
          //   1494: aload 20
          //   1496: astore 19
          //   1498: aload 18
          //   1500: astore 15
          //   1502: aload 17
          //   1504: astore 16
          //   1506: aload_0
          //   1507: getfield 53	org/apache/cordova/filetransfer/FileTransfer$1:val$headers	Lorg/json/JSONObject;
          //   1510: ifnull +78 -> 1588
          //   1513: aload 20
          //   1515: astore 29
          //   1517: aload 18
          //   1519: astore 21
          //   1521: aload 17
          //   1523: astore 22
          //   1525: aload 20
          //   1527: astore 30
          //   1529: iload_1
          //   1530: istore_3
          //   1531: aload 18
          //   1533: astore 23
          //   1535: aload 17
          //   1537: astore 24
          //   1539: iload 8
          //   1541: istore 5
          //   1543: aload 20
          //   1545: astore 32
          //   1547: aload 18
          //   1549: astore 27
          //   1551: aload 17
          //   1553: astore 28
          //   1555: aload 20
          //   1557: astore 31
          //   1559: aload 18
          //   1561: astore 25
          //   1563: aload 17
          //   1565: astore 26
          //   1567: aload 20
          //   1569: astore 19
          //   1571: aload 18
          //   1573: astore 15
          //   1575: aload 17
          //   1577: astore 16
          //   1579: aload 20
          //   1581: aload_0
          //   1582: getfield 53	org/apache/cordova/filetransfer/FileTransfer$1:val$headers	Lorg/json/JSONObject;
          //   1585: invokestatic 158	org/apache/cordova/filetransfer/FileTransfer:access$300	(Ljava/net/URLConnection;Lorg/json/JSONObject;)V
          //   1588: aload 20
          //   1590: astore 29
          //   1592: aload 18
          //   1594: astore 21
          //   1596: aload 17
          //   1598: astore 22
          //   1600: aload 20
          //   1602: astore 30
          //   1604: iload_1
          //   1605: istore_3
          //   1606: aload 18
          //   1608: astore 23
          //   1610: aload 17
          //   1612: astore 24
          //   1614: iload 8
          //   1616: istore 5
          //   1618: aload 20
          //   1620: astore 32
          //   1622: aload 18
          //   1624: astore 27
          //   1626: aload 17
          //   1628: astore 28
          //   1630: aload 20
          //   1632: astore 31
          //   1634: aload 18
          //   1636: astore 25
          //   1638: aload 17
          //   1640: astore 26
          //   1642: aload 20
          //   1644: astore 19
          //   1646: aload 18
          //   1648: astore 15
          //   1650: aload 17
          //   1652: astore 16
          //   1654: new 160	java/lang/StringBuilder
          //   1657: dup
          //   1658: invokespecial 161	java/lang/StringBuilder:<init>	()V
          //   1661: astore 33
          //   1663: aload 20
          //   1665: astore 29
          //   1667: aload 18
          //   1669: astore 21
          //   1671: aload 17
          //   1673: astore 22
          //   1675: aload 20
          //   1677: astore 30
          //   1679: iload_1
          //   1680: istore_3
          //   1681: aload 18
          //   1683: astore 23
          //   1685: aload 17
          //   1687: astore 24
          //   1689: iload 8
          //   1691: istore 5
          //   1693: aload 20
          //   1695: astore 31
          //   1697: aload 18
          //   1699: astore 25
          //   1701: aload 17
          //   1703: astore 26
          //   1705: aload 20
          //   1707: astore 19
          //   1709: aload 18
          //   1711: astore 15
          //   1713: aload 17
          //   1715: astore 16
          //   1717: aload_0
          //   1718: getfield 57	org/apache/cordova/filetransfer/FileTransfer$1:val$params	Lorg/json/JSONObject;
          //   1721: invokevirtual 165	org/json/JSONObject:keys	()Ljava/util/Iterator;
          //   1724: astore 27
          //   1726: aload 20
          //   1728: astore 29
          //   1730: aload 18
          //   1732: astore 21
          //   1734: aload 17
          //   1736: astore 22
          //   1738: aload 20
          //   1740: astore 30
          //   1742: iload_1
          //   1743: istore_3
          //   1744: aload 18
          //   1746: astore 23
          //   1748: aload 17
          //   1750: astore 24
          //   1752: iload 8
          //   1754: istore 5
          //   1756: aload 20
          //   1758: astore 31
          //   1760: aload 18
          //   1762: astore 25
          //   1764: aload 17
          //   1766: astore 26
          //   1768: aload 20
          //   1770: astore 19
          //   1772: aload 18
          //   1774: astore 15
          //   1776: aload 17
          //   1778: astore 16
          //   1780: aload 27
          //   1782: invokeinterface 171 1 0
          //   1787: ifeq +565 -> 2352
          //   1790: aload 20
          //   1792: astore 29
          //   1794: aload 18
          //   1796: astore 21
          //   1798: aload 17
          //   1800: astore 22
          //   1802: aload 20
          //   1804: astore 30
          //   1806: iload_1
          //   1807: istore_3
          //   1808: aload 18
          //   1810: astore 23
          //   1812: aload 17
          //   1814: astore 24
          //   1816: iload 8
          //   1818: istore 5
          //   1820: aload 20
          //   1822: astore 31
          //   1824: aload 18
          //   1826: astore 25
          //   1828: aload 17
          //   1830: astore 26
          //   1832: aload 20
          //   1834: astore 19
          //   1836: aload 18
          //   1838: astore 15
          //   1840: aload 17
          //   1842: astore 16
          //   1844: aload 27
          //   1846: invokeinterface 175 1 0
          //   1851: astore 28
          //   1853: aload 20
          //   1855: astore 29
          //   1857: aload 18
          //   1859: astore 21
          //   1861: aload 17
          //   1863: astore 22
          //   1865: aload 20
          //   1867: astore 30
          //   1869: iload_1
          //   1870: istore_3
          //   1871: aload 18
          //   1873: astore 23
          //   1875: aload 17
          //   1877: astore 24
          //   1879: iload 8
          //   1881: istore 5
          //   1883: aload 20
          //   1885: astore 31
          //   1887: aload 18
          //   1889: astore 25
          //   1891: aload 17
          //   1893: astore 26
          //   1895: aload 20
          //   1897: astore 19
          //   1899: aload 18
          //   1901: astore 15
          //   1903: aload 17
          //   1905: astore 16
          //   1907: aload 28
          //   1909: invokestatic 181	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
          //   1912: ldc -73
          //   1914: invokevirtual 187	java/lang/String:equals	(Ljava/lang/Object;)Z
          //   1917: ifne -191 -> 1726
          //   1920: aload 20
          //   1922: astore 29
          //   1924: aload 18
          //   1926: astore 21
          //   1928: aload 17
          //   1930: astore 22
          //   1932: aload 20
          //   1934: astore 30
          //   1936: iload_1
          //   1937: istore_3
          //   1938: aload 18
          //   1940: astore 23
          //   1942: aload 17
          //   1944: astore 24
          //   1946: iload 8
          //   1948: istore 5
          //   1950: aload 20
          //   1952: astore 31
          //   1954: aload 18
          //   1956: astore 25
          //   1958: aload 17
          //   1960: astore 26
          //   1962: aload 20
          //   1964: astore 19
          //   1966: aload 18
          //   1968: astore 15
          //   1970: aload 17
          //   1972: astore 16
          //   1974: aload 33
          //   1976: ldc -67
          //   1978: invokevirtual 193	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   1981: ldc -61
          //   1983: invokevirtual 193	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   1986: ldc -59
          //   1988: invokevirtual 193	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   1991: pop
          //   1992: aload 20
          //   1994: astore 29
          //   1996: aload 18
          //   1998: astore 21
          //   2000: aload 17
          //   2002: astore 22
          //   2004: aload 20
          //   2006: astore 30
          //   2008: iload_1
          //   2009: istore_3
          //   2010: aload 18
          //   2012: astore 23
          //   2014: aload 17
          //   2016: astore 24
          //   2018: iload 8
          //   2020: istore 5
          //   2022: aload 20
          //   2024: astore 31
          //   2026: aload 18
          //   2028: astore 25
          //   2030: aload 17
          //   2032: astore 26
          //   2034: aload 20
          //   2036: astore 19
          //   2038: aload 18
          //   2040: astore 15
          //   2042: aload 17
          //   2044: astore 16
          //   2046: aload 33
          //   2048: ldc -57
          //   2050: invokevirtual 193	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   2053: aload 28
          //   2055: invokevirtual 203	java/lang/Object:toString	()Ljava/lang/String;
          //   2058: invokevirtual 193	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   2061: bipush 34
          //   2063: invokevirtual 206	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
          //   2066: pop
          //   2067: aload 20
          //   2069: astore 29
          //   2071: aload 18
          //   2073: astore 21
          //   2075: aload 17
          //   2077: astore 22
          //   2079: aload 20
          //   2081: astore 30
          //   2083: iload_1
          //   2084: istore_3
          //   2085: aload 18
          //   2087: astore 23
          //   2089: aload 17
          //   2091: astore 24
          //   2093: iload 8
          //   2095: istore 5
          //   2097: aload 20
          //   2099: astore 31
          //   2101: aload 18
          //   2103: astore 25
          //   2105: aload 17
          //   2107: astore 26
          //   2109: aload 20
          //   2111: astore 19
          //   2113: aload 18
          //   2115: astore 15
          //   2117: aload 17
          //   2119: astore 16
          //   2121: aload 33
          //   2123: ldc -59
          //   2125: invokevirtual 193	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   2128: ldc -59
          //   2130: invokevirtual 193	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   2133: pop
          //   2134: aload 20
          //   2136: astore 29
          //   2138: aload 18
          //   2140: astore 21
          //   2142: aload 17
          //   2144: astore 22
          //   2146: aload 20
          //   2148: astore 30
          //   2150: iload_1
          //   2151: istore_3
          //   2152: aload 18
          //   2154: astore 23
          //   2156: aload 17
          //   2158: astore 24
          //   2160: iload 8
          //   2162: istore 5
          //   2164: aload 20
          //   2166: astore 31
          //   2168: aload 18
          //   2170: astore 25
          //   2172: aload 17
          //   2174: astore 26
          //   2176: aload 20
          //   2178: astore 19
          //   2180: aload 18
          //   2182: astore 15
          //   2184: aload 17
          //   2186: astore 16
          //   2188: aload 33
          //   2190: aload_0
          //   2191: getfield 57	org/apache/cordova/filetransfer/FileTransfer$1:val$params	Lorg/json/JSONObject;
          //   2194: aload 28
          //   2196: invokevirtual 203	java/lang/Object:toString	()Ljava/lang/String;
          //   2199: invokevirtual 210	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
          //   2202: invokevirtual 193	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   2205: pop
          //   2206: aload 20
          //   2208: astore 29
          //   2210: aload 18
          //   2212: astore 21
          //   2214: aload 17
          //   2216: astore 22
          //   2218: aload 20
          //   2220: astore 30
          //   2222: iload_1
          //   2223: istore_3
          //   2224: aload 18
          //   2226: astore 23
          //   2228: aload 17
          //   2230: astore 24
          //   2232: iload 8
          //   2234: istore 5
          //   2236: aload 20
          //   2238: astore 31
          //   2240: aload 18
          //   2242: astore 25
          //   2244: aload 17
          //   2246: astore 26
          //   2248: aload 20
          //   2250: astore 19
          //   2252: aload 18
          //   2254: astore 15
          //   2256: aload 17
          //   2258: astore 16
          //   2260: aload 33
          //   2262: ldc -59
          //   2264: invokevirtual 193	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   2267: pop
          //   2268: goto -542 -> 1726
          //   2271: astore 34
          //   2273: aload 20
          //   2275: astore 29
          //   2277: aload 18
          //   2279: astore 21
          //   2281: aload 17
          //   2283: astore 22
          //   2285: aload 20
          //   2287: astore 30
          //   2289: iload_1
          //   2290: istore_3
          //   2291: aload 18
          //   2293: astore 23
          //   2295: aload 17
          //   2297: astore 24
          //   2299: iload 8
          //   2301: istore 5
          //   2303: aload 20
          //   2305: astore 32
          //   2307: aload 18
          //   2309: astore 27
          //   2311: aload 17
          //   2313: astore 28
          //   2315: aload 20
          //   2317: astore 31
          //   2319: aload 18
          //   2321: astore 25
          //   2323: aload 17
          //   2325: astore 26
          //   2327: aload 20
          //   2329: astore 19
          //   2331: aload 18
          //   2333: astore 15
          //   2335: aload 17
          //   2337: astore 16
          //   2339: ldc -44
          //   2341: aload 34
          //   2343: invokevirtual 215	org/json/JSONException:getMessage	()Ljava/lang/String;
          //   2346: aload 34
          //   2348: invokestatic 221	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
          //   2351: pop
          //   2352: aload 20
          //   2354: astore 29
          //   2356: aload 18
          //   2358: astore 21
          //   2360: aload 17
          //   2362: astore 22
          //   2364: aload 20
          //   2366: astore 30
          //   2368: iload_1
          //   2369: istore_3
          //   2370: aload 18
          //   2372: astore 23
          //   2374: aload 17
          //   2376: astore 24
          //   2378: iload 8
          //   2380: istore 5
          //   2382: aload 20
          //   2384: astore 32
          //   2386: aload 18
          //   2388: astore 27
          //   2390: aload 17
          //   2392: astore 28
          //   2394: aload 20
          //   2396: astore 31
          //   2398: aload 18
          //   2400: astore 25
          //   2402: aload 17
          //   2404: astore 26
          //   2406: aload 20
          //   2408: astore 19
          //   2410: aload 18
          //   2412: astore 15
          //   2414: aload 17
          //   2416: astore 16
          //   2418: aload 33
          //   2420: ldc -67
          //   2422: invokevirtual 193	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   2425: ldc -61
          //   2427: invokevirtual 193	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   2430: ldc -59
          //   2432: invokevirtual 193	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   2435: pop
          //   2436: aload 20
          //   2438: astore 29
          //   2440: aload 18
          //   2442: astore 21
          //   2444: aload 17
          //   2446: astore 22
          //   2448: aload 20
          //   2450: astore 30
          //   2452: iload_1
          //   2453: istore_3
          //   2454: aload 18
          //   2456: astore 23
          //   2458: aload 17
          //   2460: astore 24
          //   2462: iload 8
          //   2464: istore 5
          //   2466: aload 20
          //   2468: astore 32
          //   2470: aload 18
          //   2472: astore 27
          //   2474: aload 17
          //   2476: astore 28
          //   2478: aload 20
          //   2480: astore 31
          //   2482: aload 18
          //   2484: astore 25
          //   2486: aload 17
          //   2488: astore 26
          //   2490: aload 20
          //   2492: astore 19
          //   2494: aload 18
          //   2496: astore 15
          //   2498: aload 17
          //   2500: astore 16
          //   2502: aload 33
          //   2504: ldc -57
          //   2506: invokevirtual 193	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   2509: aload_0
          //   2510: getfield 59	org/apache/cordova/filetransfer/FileTransfer$1:val$fileKey	Ljava/lang/String;
          //   2513: invokevirtual 193	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   2516: ldc -33
          //   2518: invokevirtual 193	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   2521: pop
          //   2522: aload 20
          //   2524: astore 29
          //   2526: aload 18
          //   2528: astore 21
          //   2530: aload 17
          //   2532: astore 22
          //   2534: aload 20
          //   2536: astore 30
          //   2538: iload_1
          //   2539: istore_3
          //   2540: aload 18
          //   2542: astore 23
          //   2544: aload 17
          //   2546: astore 24
          //   2548: iload 8
          //   2550: istore 5
          //   2552: aload 20
          //   2554: astore 32
          //   2556: aload 18
          //   2558: astore 27
          //   2560: aload 17
          //   2562: astore 28
          //   2564: aload 20
          //   2566: astore 31
          //   2568: aload 18
          //   2570: astore 25
          //   2572: aload 17
          //   2574: astore 26
          //   2576: aload 20
          //   2578: astore 19
          //   2580: aload 18
          //   2582: astore 15
          //   2584: aload 17
          //   2586: astore 16
          //   2588: aload 33
          //   2590: ldc -31
          //   2592: invokevirtual 193	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   2595: aload_0
          //   2596: getfield 61	org/apache/cordova/filetransfer/FileTransfer$1:val$fileName	Ljava/lang/String;
          //   2599: invokevirtual 193	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   2602: bipush 34
          //   2604: invokevirtual 206	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
          //   2607: ldc -59
          //   2609: invokevirtual 193	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   2612: pop
          //   2613: aload 20
          //   2615: astore 29
          //   2617: aload 18
          //   2619: astore 21
          //   2621: aload 17
          //   2623: astore 22
          //   2625: aload 20
          //   2627: astore 30
          //   2629: iload_1
          //   2630: istore_3
          //   2631: aload 18
          //   2633: astore 23
          //   2635: aload 17
          //   2637: astore 24
          //   2639: iload 8
          //   2641: istore 5
          //   2643: aload 20
          //   2645: astore 32
          //   2647: aload 18
          //   2649: astore 27
          //   2651: aload 17
          //   2653: astore 28
          //   2655: aload 20
          //   2657: astore 31
          //   2659: aload 18
          //   2661: astore 25
          //   2663: aload 17
          //   2665: astore 26
          //   2667: aload 20
          //   2669: astore 19
          //   2671: aload 18
          //   2673: astore 15
          //   2675: aload 17
          //   2677: astore 16
          //   2679: aload 33
          //   2681: ldc -29
          //   2683: invokevirtual 193	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   2686: aload_0
          //   2687: getfield 63	org/apache/cordova/filetransfer/FileTransfer$1:val$mimeType	Ljava/lang/String;
          //   2690: invokevirtual 193	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   2693: ldc -59
          //   2695: invokevirtual 193	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   2698: ldc -59
          //   2700: invokevirtual 193	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   2703: pop
          //   2704: aload 20
          //   2706: astore 29
          //   2708: aload 18
          //   2710: astore 21
          //   2712: aload 17
          //   2714: astore 22
          //   2716: aload 20
          //   2718: astore 30
          //   2720: iload_1
          //   2721: istore_3
          //   2722: aload 18
          //   2724: astore 23
          //   2726: aload 17
          //   2728: astore 24
          //   2730: iload 8
          //   2732: istore 5
          //   2734: aload 20
          //   2736: astore 32
          //   2738: aload 18
          //   2740: astore 27
          //   2742: aload 17
          //   2744: astore 28
          //   2746: aload 20
          //   2748: astore 31
          //   2750: aload 18
          //   2752: astore 25
          //   2754: aload 17
          //   2756: astore 26
          //   2758: aload 20
          //   2760: astore 19
          //   2762: aload 18
          //   2764: astore 15
          //   2766: aload 17
          //   2768: astore 16
          //   2770: aload 33
          //   2772: invokevirtual 228	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   2775: ldc -26
          //   2777: invokevirtual 234	java/lang/String:getBytes	(Ljava/lang/String;)[B
          //   2780: astore 37
          //   2782: aload 20
          //   2784: astore 29
          //   2786: aload 18
          //   2788: astore 21
          //   2790: aload 17
          //   2792: astore 22
          //   2794: aload 20
          //   2796: astore 30
          //   2798: iload_1
          //   2799: istore_3
          //   2800: aload 18
          //   2802: astore 23
          //   2804: aload 17
          //   2806: astore 24
          //   2808: iload 8
          //   2810: istore 5
          //   2812: aload 20
          //   2814: astore 32
          //   2816: aload 18
          //   2818: astore 27
          //   2820: aload 17
          //   2822: astore 28
          //   2824: aload 20
          //   2826: astore 31
          //   2828: aload 18
          //   2830: astore 25
          //   2832: aload 17
          //   2834: astore 26
          //   2836: aload 20
          //   2838: astore 19
          //   2840: aload 18
          //   2842: astore 15
          //   2844: aload 17
          //   2846: astore 16
          //   2848: ldc -20
          //   2850: ldc -26
          //   2852: invokevirtual 234	java/lang/String:getBytes	(Ljava/lang/String;)[B
          //   2855: astore 36
          //   2857: aload 20
          //   2859: astore 29
          //   2861: aload 18
          //   2863: astore 21
          //   2865: aload 17
          //   2867: astore 22
          //   2869: aload 20
          //   2871: astore 30
          //   2873: iload_1
          //   2874: istore_3
          //   2875: aload 18
          //   2877: astore 23
          //   2879: aload 17
          //   2881: astore 24
          //   2883: iload 8
          //   2885: istore 5
          //   2887: aload 20
          //   2889: astore 32
          //   2891: aload 18
          //   2893: astore 27
          //   2895: aload 17
          //   2897: astore 28
          //   2899: aload 20
          //   2901: astore 31
          //   2903: aload 18
          //   2905: astore 25
          //   2907: aload 17
          //   2909: astore 26
          //   2911: aload 20
          //   2913: astore 19
          //   2915: aload 18
          //   2917: astore 15
          //   2919: aload 17
          //   2921: astore 16
          //   2923: aload_0
          //   2924: getfield 43	org/apache/cordova/filetransfer/FileTransfer$1:val$resourceApi	Lorg/apache/cordova/CordovaResourceApi;
          //   2927: aload_0
          //   2928: getfield 65	org/apache/cordova/filetransfer/FileTransfer$1:val$sourceUri	Landroid/net/Uri;
          //   2931: invokevirtual 240	org/apache/cordova/CordovaResourceApi:openForRead	(Landroid/net/Uri;)Lorg/apache/cordova/CordovaResourceApi$OpenForReadResult;
          //   2934: astore 35
          //   2936: aload 20
          //   2938: astore 29
          //   2940: aload 18
          //   2942: astore 21
          //   2944: aload 17
          //   2946: astore 22
          //   2948: aload 20
          //   2950: astore 30
          //   2952: iload_1
          //   2953: istore_3
          //   2954: aload 18
          //   2956: astore 23
          //   2958: aload 17
          //   2960: astore 24
          //   2962: iload 8
          //   2964: istore 5
          //   2966: aload 20
          //   2968: astore 32
          //   2970: aload 18
          //   2972: astore 27
          //   2974: aload 17
          //   2976: astore 28
          //   2978: aload 20
          //   2980: astore 31
          //   2982: aload 18
          //   2984: astore 25
          //   2986: aload 17
          //   2988: astore 26
          //   2990: aload 20
          //   2992: astore 19
          //   2994: aload 18
          //   2996: astore 15
          //   2998: aload 17
          //   3000: astore 16
          //   3002: aload 37
          //   3004: arraylength
          //   3005: istore 9
          //   3007: aload 20
          //   3009: astore 29
          //   3011: aload 18
          //   3013: astore 21
          //   3015: aload 17
          //   3017: astore 22
          //   3019: aload 20
          //   3021: astore 30
          //   3023: iload_1
          //   3024: istore_3
          //   3025: aload 18
          //   3027: astore 23
          //   3029: aload 17
          //   3031: astore 24
          //   3033: iload 8
          //   3035: istore 5
          //   3037: aload 20
          //   3039: astore 32
          //   3041: aload 18
          //   3043: astore 27
          //   3045: aload 17
          //   3047: astore 28
          //   3049: aload 20
          //   3051: astore 31
          //   3053: aload 18
          //   3055: astore 25
          //   3057: aload 17
          //   3059: astore 26
          //   3061: aload 20
          //   3063: astore 19
          //   3065: aload 18
          //   3067: astore 15
          //   3069: aload 17
          //   3071: astore 16
          //   3073: aload 36
          //   3075: arraylength
          //   3076: istore 10
          //   3078: iload_1
          //   3079: istore 4
          //   3081: aload 20
          //   3083: astore 29
          //   3085: aload 18
          //   3087: astore 21
          //   3089: aload 17
          //   3091: astore 22
          //   3093: aload 20
          //   3095: astore 30
          //   3097: iload_1
          //   3098: istore_3
          //   3099: aload 18
          //   3101: astore 23
          //   3103: aload 17
          //   3105: astore 24
          //   3107: iload 8
          //   3109: istore 5
          //   3111: aload 20
          //   3113: astore 32
          //   3115: aload 18
          //   3117: astore 27
          //   3119: aload 17
          //   3121: astore 28
          //   3123: aload 20
          //   3125: astore 31
          //   3127: aload 18
          //   3129: astore 25
          //   3131: aload 17
          //   3133: astore 26
          //   3135: aload 20
          //   3137: astore 19
          //   3139: aload 18
          //   3141: astore 15
          //   3143: aload 17
          //   3145: astore 16
          //   3147: aload 35
          //   3149: getfield 246	org/apache/cordova/CordovaResourceApi$OpenForReadResult:length	J
          //   3152: lconst_0
          //   3153: lcmp
          //   3154: iflt +242 -> 3396
          //   3157: aload 20
          //   3159: astore 29
          //   3161: aload 18
          //   3163: astore 21
          //   3165: aload 17
          //   3167: astore 22
          //   3169: aload 20
          //   3171: astore 30
          //   3173: iload_1
          //   3174: istore_3
          //   3175: aload 18
          //   3177: astore 23
          //   3179: aload 17
          //   3181: astore 24
          //   3183: iload 8
          //   3185: istore 5
          //   3187: aload 20
          //   3189: astore 32
          //   3191: aload 18
          //   3193: astore 27
          //   3195: aload 17
          //   3197: astore 28
          //   3199: aload 20
          //   3201: astore 31
          //   3203: aload 18
          //   3205: astore 25
          //   3207: aload 17
          //   3209: astore 26
          //   3211: aload 20
          //   3213: astore 19
          //   3215: aload 18
          //   3217: astore 15
          //   3219: aload 17
          //   3221: astore 16
          //   3223: aload 35
          //   3225: getfield 246	org/apache/cordova/CordovaResourceApi$OpenForReadResult:length	J
          //   3228: l2i
          //   3229: istore 4
          //   3231: iload 4
          //   3233: istore_1
          //   3234: iload 6
          //   3236: ifeq +12 -> 3248
          //   3239: iload 4
          //   3241: iload 9
          //   3243: iload 10
          //   3245: iadd
          //   3246: iadd
          //   3247: istore_1
          //   3248: aload 20
          //   3250: astore 29
          //   3252: aload 18
          //   3254: astore 21
          //   3256: aload 17
          //   3258: astore 22
          //   3260: aload 20
          //   3262: astore 30
          //   3264: iload_1
          //   3265: istore_3
          //   3266: aload 18
          //   3268: astore 23
          //   3270: aload 17
          //   3272: astore 24
          //   3274: iload 8
          //   3276: istore 5
          //   3278: aload 20
          //   3280: astore 32
          //   3282: aload 18
          //   3284: astore 27
          //   3286: aload 17
          //   3288: astore 28
          //   3290: aload 20
          //   3292: astore 31
          //   3294: aload 18
          //   3296: astore 25
          //   3298: aload 17
          //   3300: astore 26
          //   3302: aload 20
          //   3304: astore 19
          //   3306: aload 18
          //   3308: astore 15
          //   3310: aload 17
          //   3312: astore 16
          //   3314: aload 48
          //   3316: iconst_1
          //   3317: invokevirtual 249	org/apache/cordova/filetransfer/FileProgressResult:setLengthComputable	(Z)V
          //   3320: aload 20
          //   3322: astore 29
          //   3324: aload 18
          //   3326: astore 21
          //   3328: aload 17
          //   3330: astore 22
          //   3332: aload 20
          //   3334: astore 30
          //   3336: iload_1
          //   3337: istore_3
          //   3338: aload 18
          //   3340: astore 23
          //   3342: aload 17
          //   3344: astore 24
          //   3346: iload 8
          //   3348: istore 5
          //   3350: aload 20
          //   3352: astore 32
          //   3354: aload 18
          //   3356: astore 27
          //   3358: aload 17
          //   3360: astore 28
          //   3362: aload 20
          //   3364: astore 31
          //   3366: aload 18
          //   3368: astore 25
          //   3370: aload 17
          //   3372: astore 26
          //   3374: aload 20
          //   3376: astore 19
          //   3378: aload 18
          //   3380: astore 15
          //   3382: aload 17
          //   3384: astore 16
          //   3386: aload 48
          //   3388: iload_1
          //   3389: i2l
          //   3390: invokevirtual 253	org/apache/cordova/filetransfer/FileProgressResult:setTotal	(J)V
          //   3393: iload_1
          //   3394: istore 4
          //   3396: aload 20
          //   3398: astore 29
          //   3400: aload 18
          //   3402: astore 21
          //   3404: aload 17
          //   3406: astore 22
          //   3408: aload 20
          //   3410: astore 30
          //   3412: iload 4
          //   3414: istore_3
          //   3415: aload 18
          //   3417: astore 23
          //   3419: aload 17
          //   3421: astore 24
          //   3423: iload 8
          //   3425: istore 5
          //   3427: aload 20
          //   3429: astore 32
          //   3431: aload 18
          //   3433: astore 27
          //   3435: aload 17
          //   3437: astore 28
          //   3439: aload 20
          //   3441: astore 31
          //   3443: aload 18
          //   3445: astore 25
          //   3447: aload 17
          //   3449: astore 26
          //   3451: aload 20
          //   3453: astore 19
          //   3455: aload 18
          //   3457: astore 15
          //   3459: aload 17
          //   3461: astore 16
          //   3463: ldc -44
          //   3465: new 160	java/lang/StringBuilder
          //   3468: dup
          //   3469: invokespecial 161	java/lang/StringBuilder:<init>	()V
          //   3472: ldc -1
          //   3474: invokevirtual 193	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   3477: iload 4
          //   3479: invokevirtual 258	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
          //   3482: invokevirtual 228	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   3485: invokestatic 262	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
          //   3488: pop
          //   3489: aload 20
          //   3491: astore 29
          //   3493: aload 18
          //   3495: astore 21
          //   3497: aload 17
          //   3499: astore 22
          //   3501: aload 20
          //   3503: astore 30
          //   3505: iload 4
          //   3507: istore_3
          //   3508: aload 18
          //   3510: astore 23
          //   3512: aload 17
          //   3514: astore 24
          //   3516: iload 8
          //   3518: istore 5
          //   3520: aload 20
          //   3522: astore 32
          //   3524: aload 18
          //   3526: astore 27
          //   3528: aload 17
          //   3530: astore 28
          //   3532: aload 20
          //   3534: astore 31
          //   3536: aload 18
          //   3538: astore 25
          //   3540: aload 17
          //   3542: astore 26
          //   3544: aload 20
          //   3546: astore 19
          //   3548: aload 18
          //   3550: astore 15
          //   3552: aload 17
          //   3554: astore 16
          //   3556: aload_0
          //   3557: getfield 67	org/apache/cordova/filetransfer/FileTransfer$1:val$chunkedMode	Z
          //   3560: ifeq +649 -> 4209
          //   3563: aload 20
          //   3565: astore 29
          //   3567: aload 18
          //   3569: astore 21
          //   3571: aload 17
          //   3573: astore 22
          //   3575: aload 20
          //   3577: astore 30
          //   3579: iload 4
          //   3581: istore_3
          //   3582: aload 18
          //   3584: astore 23
          //   3586: aload 17
          //   3588: astore 24
          //   3590: iload 8
          //   3592: istore 5
          //   3594: aload 20
          //   3596: astore 32
          //   3598: aload 18
          //   3600: astore 27
          //   3602: aload 17
          //   3604: astore 28
          //   3606: aload 20
          //   3608: astore 31
          //   3610: aload 18
          //   3612: astore 25
          //   3614: aload 17
          //   3616: astore 26
          //   3618: aload 20
          //   3620: astore 19
          //   3622: aload 18
          //   3624: astore 15
          //   3626: aload 17
          //   3628: astore 16
          //   3630: getstatic 268	android/os/Build$VERSION:SDK_INT	I
          //   3633: bipush 8
          //   3635: if_icmplt +4387 -> 8022
          //   3638: aload 20
          //   3640: astore 29
          //   3642: aload 18
          //   3644: astore 21
          //   3646: aload 17
          //   3648: astore 22
          //   3650: aload 20
          //   3652: astore 30
          //   3654: iload 4
          //   3656: istore_3
          //   3657: aload 18
          //   3659: astore 23
          //   3661: aload 17
          //   3663: astore 24
          //   3665: iload 8
          //   3667: istore 5
          //   3669: aload 20
          //   3671: astore 32
          //   3673: aload 18
          //   3675: astore 27
          //   3677: aload 17
          //   3679: astore 28
          //   3681: aload 20
          //   3683: astore 31
          //   3685: aload 18
          //   3687: astore 25
          //   3689: aload 17
          //   3691: astore 26
          //   3693: aload 20
          //   3695: astore 19
          //   3697: aload 18
          //   3699: astore 15
          //   3701: aload 17
          //   3703: astore 16
          //   3705: aload_0
          //   3706: getfield 47	org/apache/cordova/filetransfer/FileTransfer$1:val$useHttps	Z
          //   3709: ifeq +500 -> 4209
          //   3712: goto +4310 -> 8022
          //   3715: iload_1
          //   3716: ifeq +503 -> 4219
          //   3719: aload 20
          //   3721: astore 29
          //   3723: aload 18
          //   3725: astore 21
          //   3727: aload 17
          //   3729: astore 22
          //   3731: aload 20
          //   3733: astore 30
          //   3735: iload 4
          //   3737: istore_3
          //   3738: aload 18
          //   3740: astore 23
          //   3742: aload 17
          //   3744: astore 24
          //   3746: iload 8
          //   3748: istore 5
          //   3750: aload 20
          //   3752: astore 32
          //   3754: aload 18
          //   3756: astore 27
          //   3758: aload 17
          //   3760: astore 28
          //   3762: aload 20
          //   3764: astore 31
          //   3766: aload 18
          //   3768: astore 25
          //   3770: aload 17
          //   3772: astore 26
          //   3774: aload 20
          //   3776: astore 19
          //   3778: aload 18
          //   3780: astore 15
          //   3782: aload 17
          //   3784: astore 16
          //   3786: aload 20
          //   3788: sipush 16384
          //   3791: invokevirtual 272	java/net/HttpURLConnection:setChunkedStreamingMode	(I)V
          //   3794: aload 20
          //   3796: astore 29
          //   3798: aload 18
          //   3800: astore 21
          //   3802: aload 17
          //   3804: astore 22
          //   3806: aload 20
          //   3808: astore 30
          //   3810: iload 4
          //   3812: istore_3
          //   3813: aload 18
          //   3815: astore 23
          //   3817: aload 17
          //   3819: astore 24
          //   3821: iload 8
          //   3823: istore 5
          //   3825: aload 20
          //   3827: astore 32
          //   3829: aload 18
          //   3831: astore 27
          //   3833: aload 17
          //   3835: astore 28
          //   3837: aload 20
          //   3839: astore 31
          //   3841: aload 18
          //   3843: astore 25
          //   3845: aload 17
          //   3847: astore 26
          //   3849: aload 20
          //   3851: astore 19
          //   3853: aload 18
          //   3855: astore 15
          //   3857: aload 17
          //   3859: astore 16
          //   3861: aload 20
          //   3863: ldc_w 274
          //   3866: ldc_w 276
          //   3869: invokevirtual 148	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
          //   3872: aload 20
          //   3874: astore 29
          //   3876: aload 18
          //   3878: astore 21
          //   3880: aload 17
          //   3882: astore 22
          //   3884: aload 20
          //   3886: astore 30
          //   3888: iload 4
          //   3890: istore_3
          //   3891: aload 18
          //   3893: astore 23
          //   3895: aload 17
          //   3897: astore 24
          //   3899: iload 8
          //   3901: istore 5
          //   3903: aload 20
          //   3905: astore 32
          //   3907: aload 18
          //   3909: astore 27
          //   3911: aload 17
          //   3913: astore 28
          //   3915: aload 20
          //   3917: astore 31
          //   3919: aload 18
          //   3921: astore 25
          //   3923: aload 17
          //   3925: astore 26
          //   3927: aload 20
          //   3929: astore 19
          //   3931: aload 18
          //   3933: astore 15
          //   3935: aload 17
          //   3937: astore 16
          //   3939: aload 20
          //   3941: invokevirtual 279	java/net/HttpURLConnection:connect	()V
          //   3944: aconst_null
          //   3945: astore 33
          //   3947: iload 7
          //   3949: istore_1
          //   3950: aload 20
          //   3952: invokevirtual 283	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
          //   3955: astore 34
          //   3957: aload 34
          //   3959: astore 33
          //   3961: iload 7
          //   3963: istore_1
          //   3964: aload_0
          //   3965: getfield 41	org/apache/cordova/filetransfer/FileTransfer$1:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   3968: astore 15
          //   3970: aload 34
          //   3972: astore 33
          //   3974: iload 7
          //   3976: istore_1
          //   3977: aload 15
          //   3979: monitorenter
          //   3980: aload_0
          //   3981: getfield 41	org/apache/cordova/filetransfer/FileTransfer$1:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   3984: getfield 89	org/apache/cordova/filetransfer/FileTransfer$RequestContext:aborted	Z
          //   3987: ifeq +470 -> 4457
          //   3990: aload 15
          //   3992: monitorexit
          //   3993: aload 20
          //   3995: astore 29
          //   3997: aload 18
          //   3999: astore 21
          //   4001: aload 17
          //   4003: astore 22
          //   4005: aload 20
          //   4007: astore 30
          //   4009: iload 4
          //   4011: istore_3
          //   4012: aload 18
          //   4014: astore 23
          //   4016: aload 17
          //   4018: astore 24
          //   4020: iload 8
          //   4022: istore 5
          //   4024: aload 20
          //   4026: astore 32
          //   4028: aload 18
          //   4030: astore 27
          //   4032: aload 17
          //   4034: astore 28
          //   4036: aload 20
          //   4038: astore 31
          //   4040: aload 18
          //   4042: astore 25
          //   4044: aload 17
          //   4046: astore 26
          //   4048: aload 20
          //   4050: astore 19
          //   4052: aload 18
          //   4054: astore 15
          //   4056: aload 17
          //   4058: astore 16
          //   4060: aload 35
          //   4062: getfield 287	org/apache/cordova/CordovaResourceApi$OpenForReadResult:inputStream	Ljava/io/InputStream;
          //   4065: invokestatic 291	org/apache/cordova/filetransfer/FileTransfer:access$400	(Ljava/io/Closeable;)V
          //   4068: aload 20
          //   4070: astore 29
          //   4072: aload 18
          //   4074: astore 21
          //   4076: aload 17
          //   4078: astore 22
          //   4080: aload 20
          //   4082: astore 30
          //   4084: iload 4
          //   4086: istore_3
          //   4087: aload 18
          //   4089: astore 23
          //   4091: aload 17
          //   4093: astore 24
          //   4095: iload 8
          //   4097: istore 5
          //   4099: aload 20
          //   4101: astore 32
          //   4103: aload 18
          //   4105: astore 27
          //   4107: aload 17
          //   4109: astore 28
          //   4111: aload 20
          //   4113: astore 31
          //   4115: aload 18
          //   4117: astore 25
          //   4119: aload 17
          //   4121: astore 26
          //   4123: aload 20
          //   4125: astore 19
          //   4127: aload 18
          //   4129: astore 15
          //   4131: aload 17
          //   4133: astore 16
          //   4135: aload 34
          //   4137: invokestatic 291	org/apache/cordova/filetransfer/FileTransfer:access$400	(Ljava/io/Closeable;)V
          //   4140: invokestatic 295	org/apache/cordova/filetransfer/FileTransfer:access$700	()Ljava/util/HashMap;
          //   4143: astore 15
          //   4145: aload 15
          //   4147: monitorenter
          //   4148: invokestatic 295	org/apache/cordova/filetransfer/FileTransfer:access$700	()Ljava/util/HashMap;
          //   4151: aload_0
          //   4152: getfield 71	org/apache/cordova/filetransfer/FileTransfer$1:val$objectId	Ljava/lang/String;
          //   4155: invokevirtual 301	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
          //   4158: pop
          //   4159: aload 15
          //   4161: monitorexit
          //   4162: aload 20
          //   4164: ifnull -4154 -> 10
          //   4167: aload_0
          //   4168: getfield 49	org/apache/cordova/filetransfer/FileTransfer$1:val$trustEveryone	Z
          //   4171: ifeq -4161 -> 10
          //   4174: aload_0
          //   4175: getfield 47	org/apache/cordova/filetransfer/FileTransfer$1:val$useHttps	Z
          //   4178: ifeq -4168 -> 10
          //   4181: aload 20
          //   4183: checkcast 103	javax/net/ssl/HttpsURLConnection
          //   4186: astore 15
          //   4188: aload 15
          //   4190: aload 18
          //   4192: invokevirtual 118	javax/net/ssl/HttpsURLConnection:setHostnameVerifier	(Ljavax/net/ssl/HostnameVerifier;)V
          //   4195: aload 15
          //   4197: aload 17
          //   4199: invokevirtual 305	javax/net/ssl/HttpsURLConnection:setSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
          //   4202: return
          //   4203: iconst_0
          //   4204: istore 6
          //   4206: goto -3005 -> 1201
          //   4209: iconst_0
          //   4210: istore_1
          //   4211: goto +3813 -> 8024
          //   4214: iconst_0
          //   4215: istore_1
          //   4216: goto -501 -> 3715
          //   4219: aload 20
          //   4221: astore 29
          //   4223: aload 18
          //   4225: astore 21
          //   4227: aload 17
          //   4229: astore 22
          //   4231: aload 20
          //   4233: astore 30
          //   4235: iload 4
          //   4237: istore_3
          //   4238: aload 18
          //   4240: astore 23
          //   4242: aload 17
          //   4244: astore 24
          //   4246: iload 8
          //   4248: istore 5
          //   4250: aload 20
          //   4252: astore 32
          //   4254: aload 18
          //   4256: astore 27
          //   4258: aload 17
          //   4260: astore 28
          //   4262: aload 20
          //   4264: astore 31
          //   4266: aload 18
          //   4268: astore 25
          //   4270: aload 17
          //   4272: astore 26
          //   4274: aload 20
          //   4276: astore 19
          //   4278: aload 18
          //   4280: astore 15
          //   4282: aload 17
          //   4284: astore 16
          //   4286: aload 20
          //   4288: iload 4
          //   4290: invokevirtual 308	java/net/HttpURLConnection:setFixedLengthStreamingMode	(I)V
          //   4293: goto -421 -> 3872
          //   4296: astore 17
          //   4298: aload 29
          //   4300: astore 19
          //   4302: aload 21
          //   4304: astore 15
          //   4306: aload 22
          //   4308: astore 16
          //   4310: getstatic 311	org/apache/cordova/filetransfer/FileTransfer:FILE_NOT_FOUND_ERR	I
          //   4313: aload_0
          //   4314: getfield 69	org/apache/cordova/filetransfer/FileTransfer$1:val$source	Ljava/lang/String;
          //   4317: aload_0
          //   4318: getfield 55	org/apache/cordova/filetransfer/FileTransfer$1:val$target	Ljava/lang/String;
          //   4321: aload 29
          //   4323: aload 17
          //   4325: invokestatic 315	org/apache/cordova/filetransfer/FileTransfer:access$600	(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;Ljava/lang/Throwable;)Lorg/json/JSONObject;
          //   4328: astore 18
          //   4330: aload 29
          //   4332: astore 19
          //   4334: aload 21
          //   4336: astore 15
          //   4338: aload 22
          //   4340: astore 16
          //   4342: ldc -44
          //   4344: aload 18
          //   4346: invokevirtual 316	org/json/JSONObject:toString	()Ljava/lang/String;
          //   4349: aload 17
          //   4351: invokestatic 221	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
          //   4354: pop
          //   4355: aload 29
          //   4357: astore 19
          //   4359: aload 21
          //   4361: astore 15
          //   4363: aload 22
          //   4365: astore 16
          //   4367: aload_0
          //   4368: getfield 41	org/apache/cordova/filetransfer/FileTransfer$1:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   4371: new 318	org/apache/cordova/PluginResult
          //   4374: dup
          //   4375: getstatic 324	org/apache/cordova/PluginResult$Status:IO_EXCEPTION	Lorg/apache/cordova/PluginResult$Status;
          //   4378: aload 18
          //   4380: invokespecial 327	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V
          //   4383: invokevirtual 331	org/apache/cordova/filetransfer/FileTransfer$RequestContext:sendPluginResult	(Lorg/apache/cordova/PluginResult;)V
          //   4386: invokestatic 295	org/apache/cordova/filetransfer/FileTransfer:access$700	()Ljava/util/HashMap;
          //   4389: astore 15
          //   4391: aload 15
          //   4393: monitorenter
          //   4394: invokestatic 295	org/apache/cordova/filetransfer/FileTransfer:access$700	()Ljava/util/HashMap;
          //   4397: aload_0
          //   4398: getfield 71	org/apache/cordova/filetransfer/FileTransfer$1:val$objectId	Ljava/lang/String;
          //   4401: invokevirtual 301	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
          //   4404: pop
          //   4405: aload 15
          //   4407: monitorexit
          //   4408: aload 29
          //   4410: ifnull -4400 -> 10
          //   4413: aload_0
          //   4414: getfield 49	org/apache/cordova/filetransfer/FileTransfer$1:val$trustEveryone	Z
          //   4417: ifeq -4407 -> 10
          //   4420: aload_0
          //   4421: getfield 47	org/apache/cordova/filetransfer/FileTransfer$1:val$useHttps	Z
          //   4424: ifeq -4414 -> 10
          //   4427: aload 29
          //   4429: checkcast 103	javax/net/ssl/HttpsURLConnection
          //   4432: astore 15
          //   4434: aload 15
          //   4436: aload 21
          //   4438: invokevirtual 118	javax/net/ssl/HttpsURLConnection:setHostnameVerifier	(Ljavax/net/ssl/HostnameVerifier;)V
          //   4441: aload 15
          //   4443: aload 22
          //   4445: invokevirtual 305	javax/net/ssl/HttpsURLConnection:setSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
          //   4448: return
          //   4449: astore 16
          //   4451: aload 15
          //   4453: monitorexit
          //   4454: aload 16
          //   4456: athrow
          //   4457: aload_0
          //   4458: getfield 41	org/apache/cordova/filetransfer/FileTransfer$1:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   4461: aload 20
          //   4463: putfield 335	org/apache/cordova/filetransfer/FileTransfer$RequestContext:connection	Ljava/net/HttpURLConnection;
          //   4466: aload 15
          //   4468: monitorexit
          //   4469: iload 6
          //   4471: ifeq +30 -> 4501
          //   4474: aload 34
          //   4476: astore 33
          //   4478: iload 7
          //   4480: istore_1
          //   4481: aload 34
          //   4483: aload 37
          //   4485: invokevirtual 341	java/io/OutputStream:write	([B)V
          //   4488: aload 34
          //   4490: astore 33
          //   4492: iload 7
          //   4494: istore_1
          //   4495: iconst_0
          //   4496: aload 37
          //   4498: arraylength
          //   4499: iadd
          //   4500: istore_2
          //   4501: aload 34
          //   4503: astore 33
          //   4505: iload_2
          //   4506: istore_1
          //   4507: aload 35
          //   4509: getfield 287	org/apache/cordova/CordovaResourceApi$OpenForReadResult:inputStream	Ljava/io/InputStream;
          //   4512: invokevirtual 347	java/io/InputStream:available	()I
          //   4515: sipush 16384
          //   4518: invokestatic 353	java/lang/Math:min	(II)I
          //   4521: istore_3
          //   4522: aload 34
          //   4524: astore 33
          //   4526: iload_2
          //   4527: istore_1
          //   4528: iload_3
          //   4529: newarray <illegal type>
          //   4531: astore 15
          //   4533: aload 34
          //   4535: astore 33
          //   4537: iload_2
          //   4538: istore_1
          //   4539: aload 35
          //   4541: getfield 287	org/apache/cordova/CordovaResourceApi$OpenForReadResult:inputStream	Ljava/io/InputStream;
          //   4544: aload 15
          //   4546: iconst_0
          //   4547: iload_3
          //   4548: invokevirtual 357	java/io/InputStream:read	([BII)I
          //   4551: istore 5
          //   4553: lconst_0
          //   4554: lstore 11
          //   4556: iload_2
          //   4557: istore_3
          //   4558: iload 5
          //   4560: istore_2
          //   4561: iload_2
          //   4562: ifle +653 -> 5215
          //   4565: iload_3
          //   4566: iload_2
          //   4567: iadd
          //   4568: istore_3
          //   4569: aload 34
          //   4571: astore 33
          //   4573: iload_3
          //   4574: istore_1
          //   4575: aload 47
          //   4577: iload_3
          //   4578: i2l
          //   4579: invokevirtual 360	org/apache/cordova/filetransfer/FileUploadResult:setBytesSent	(J)V
          //   4582: aload 34
          //   4584: astore 33
          //   4586: iload_3
          //   4587: istore_1
          //   4588: aload 34
          //   4590: aload 15
          //   4592: iconst_0
          //   4593: iload_2
          //   4594: invokevirtual 363	java/io/OutputStream:write	([BII)V
          //   4597: lload 11
          //   4599: lstore 13
          //   4601: iload_3
          //   4602: i2l
          //   4603: ldc2_w 364
          //   4606: lload 11
          //   4608: ladd
          //   4609: lcmp
          //   4610: ifle +56 -> 4666
          //   4613: iload_3
          //   4614: i2l
          //   4615: lstore 13
          //   4617: aload 34
          //   4619: astore 33
          //   4621: iload_3
          //   4622: istore_1
          //   4623: ldc -44
          //   4625: new 160	java/lang/StringBuilder
          //   4628: dup
          //   4629: invokespecial 161	java/lang/StringBuilder:<init>	()V
          //   4632: ldc_w 367
          //   4635: invokevirtual 193	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   4638: iload_3
          //   4639: invokevirtual 258	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
          //   4642: ldc_w 369
          //   4645: invokevirtual 193	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   4648: iload 4
          //   4650: invokevirtual 258	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
          //   4653: ldc_w 371
          //   4656: invokevirtual 193	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   4659: invokevirtual 228	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   4662: invokestatic 262	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
          //   4665: pop
          //   4666: aload 34
          //   4668: astore 33
          //   4670: iload_3
          //   4671: istore_1
          //   4672: aload 35
          //   4674: getfield 287	org/apache/cordova/CordovaResourceApi$OpenForReadResult:inputStream	Ljava/io/InputStream;
          //   4677: invokevirtual 347	java/io/InputStream:available	()I
          //   4680: sipush 16384
          //   4683: invokestatic 353	java/lang/Math:min	(II)I
          //   4686: istore_2
          //   4687: aload 34
          //   4689: astore 33
          //   4691: iload_3
          //   4692: istore_1
          //   4693: aload 35
          //   4695: getfield 287	org/apache/cordova/CordovaResourceApi$OpenForReadResult:inputStream	Ljava/io/InputStream;
          //   4698: aload 15
          //   4700: iconst_0
          //   4701: iload_2
          //   4702: invokevirtual 357	java/io/InputStream:read	([BII)I
          //   4705: istore_2
          //   4706: aload 34
          //   4708: astore 33
          //   4710: iload_3
          //   4711: istore_1
          //   4712: aload 48
          //   4714: iload_3
          //   4715: i2l
          //   4716: invokevirtual 374	org/apache/cordova/filetransfer/FileProgressResult:setLoaded	(J)V
          //   4719: aload 34
          //   4721: astore 33
          //   4723: iload_3
          //   4724: istore_1
          //   4725: new 318	org/apache/cordova/PluginResult
          //   4728: dup
          //   4729: getstatic 377	org/apache/cordova/PluginResult$Status:OK	Lorg/apache/cordova/PluginResult$Status;
          //   4732: aload 48
          //   4734: invokevirtual 381	org/apache/cordova/filetransfer/FileProgressResult:toJSONObject	()Lorg/json/JSONObject;
          //   4737: invokespecial 327	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V
          //   4740: astore 16
          //   4742: aload 34
          //   4744: astore 33
          //   4746: iload_3
          //   4747: istore_1
          //   4748: aload 16
          //   4750: iconst_1
          //   4751: invokevirtual 384	org/apache/cordova/PluginResult:setKeepCallback	(Z)V
          //   4754: aload 34
          //   4756: astore 33
          //   4758: iload_3
          //   4759: istore_1
          //   4760: aload_0
          //   4761: getfield 41	org/apache/cordova/filetransfer/FileTransfer$1:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   4764: aload 16
          //   4766: invokevirtual 331	org/apache/cordova/filetransfer/FileTransfer$RequestContext:sendPluginResult	(Lorg/apache/cordova/PluginResult;)V
          //   4769: lload 13
          //   4771: lstore 11
          //   4773: goto -212 -> 4561
          //   4776: astore 34
          //   4778: aload 20
          //   4780: astore 29
          //   4782: aload 18
          //   4784: astore 21
          //   4786: aload 17
          //   4788: astore 22
          //   4790: aload 20
          //   4792: astore 30
          //   4794: iload 4
          //   4796: istore_3
          //   4797: aload 18
          //   4799: astore 23
          //   4801: aload 17
          //   4803: astore 24
          //   4805: iload_1
          //   4806: istore 5
          //   4808: aload 20
          //   4810: astore 32
          //   4812: aload 18
          //   4814: astore 27
          //   4816: aload 17
          //   4818: astore 28
          //   4820: aload 20
          //   4822: astore 31
          //   4824: aload 18
          //   4826: astore 25
          //   4828: aload 17
          //   4830: astore 26
          //   4832: aload 20
          //   4834: astore 19
          //   4836: aload 18
          //   4838: astore 15
          //   4840: aload 17
          //   4842: astore 16
          //   4844: aload 35
          //   4846: getfield 287	org/apache/cordova/CordovaResourceApi$OpenForReadResult:inputStream	Ljava/io/InputStream;
          //   4849: invokestatic 291	org/apache/cordova/filetransfer/FileTransfer:access$400	(Ljava/io/Closeable;)V
          //   4852: aload 20
          //   4854: astore 29
          //   4856: aload 18
          //   4858: astore 21
          //   4860: aload 17
          //   4862: astore 22
          //   4864: aload 20
          //   4866: astore 30
          //   4868: iload 4
          //   4870: istore_3
          //   4871: aload 18
          //   4873: astore 23
          //   4875: aload 17
          //   4877: astore 24
          //   4879: iload_1
          //   4880: istore 5
          //   4882: aload 20
          //   4884: astore 32
          //   4886: aload 18
          //   4888: astore 27
          //   4890: aload 17
          //   4892: astore 28
          //   4894: aload 20
          //   4896: astore 31
          //   4898: aload 18
          //   4900: astore 25
          //   4902: aload 17
          //   4904: astore 26
          //   4906: aload 20
          //   4908: astore 19
          //   4910: aload 18
          //   4912: astore 15
          //   4914: aload 17
          //   4916: astore 16
          //   4918: aload 33
          //   4920: invokestatic 291	org/apache/cordova/filetransfer/FileTransfer:access$400	(Ljava/io/Closeable;)V
          //   4923: aload 20
          //   4925: astore 29
          //   4927: aload 18
          //   4929: astore 21
          //   4931: aload 17
          //   4933: astore 22
          //   4935: aload 20
          //   4937: astore 30
          //   4939: iload 4
          //   4941: istore_3
          //   4942: aload 18
          //   4944: astore 23
          //   4946: aload 17
          //   4948: astore 24
          //   4950: iload_1
          //   4951: istore 5
          //   4953: aload 20
          //   4955: astore 32
          //   4957: aload 18
          //   4959: astore 27
          //   4961: aload 17
          //   4963: astore 28
          //   4965: aload 20
          //   4967: astore 31
          //   4969: aload 18
          //   4971: astore 25
          //   4973: aload 17
          //   4975: astore 26
          //   4977: aload 20
          //   4979: astore 19
          //   4981: aload 18
          //   4983: astore 15
          //   4985: aload 17
          //   4987: astore 16
          //   4989: aload 34
          //   4991: athrow
          //   4992: astore 17
          //   4994: aload 30
          //   4996: astore 19
          //   4998: aload 23
          //   5000: astore 15
          //   5002: aload 24
          //   5004: astore 16
          //   5006: getstatic 387	org/apache/cordova/filetransfer/FileTransfer:CONNECTION_ERR	I
          //   5009: aload_0
          //   5010: getfield 69	org/apache/cordova/filetransfer/FileTransfer$1:val$source	Ljava/lang/String;
          //   5013: aload_0
          //   5014: getfield 55	org/apache/cordova/filetransfer/FileTransfer$1:val$target	Ljava/lang/String;
          //   5017: aload 30
          //   5019: aload 17
          //   5021: invokestatic 315	org/apache/cordova/filetransfer/FileTransfer:access$600	(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;Ljava/lang/Throwable;)Lorg/json/JSONObject;
          //   5024: astore 18
          //   5026: aload 30
          //   5028: astore 19
          //   5030: aload 23
          //   5032: astore 15
          //   5034: aload 24
          //   5036: astore 16
          //   5038: ldc -44
          //   5040: aload 18
          //   5042: invokevirtual 316	org/json/JSONObject:toString	()Ljava/lang/String;
          //   5045: aload 17
          //   5047: invokestatic 221	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
          //   5050: pop
          //   5051: aload 30
          //   5053: astore 19
          //   5055: aload 23
          //   5057: astore 15
          //   5059: aload 24
          //   5061: astore 16
          //   5063: ldc -44
          //   5065: new 160	java/lang/StringBuilder
          //   5068: dup
          //   5069: invokespecial 161	java/lang/StringBuilder:<init>	()V
          //   5072: ldc_w 389
          //   5075: invokevirtual 193	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   5078: iload 5
          //   5080: invokevirtual 258	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
          //   5083: ldc_w 369
          //   5086: invokevirtual 193	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   5089: iload_3
          //   5090: invokevirtual 258	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
          //   5093: ldc_w 391
          //   5096: invokevirtual 193	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   5099: invokevirtual 228	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   5102: invokestatic 393	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
          //   5105: pop
          //   5106: aload 30
          //   5108: astore 19
          //   5110: aload 23
          //   5112: astore 15
          //   5114: aload 24
          //   5116: astore 16
          //   5118: aload_0
          //   5119: getfield 41	org/apache/cordova/filetransfer/FileTransfer$1:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   5122: new 318	org/apache/cordova/PluginResult
          //   5125: dup
          //   5126: getstatic 324	org/apache/cordova/PluginResult$Status:IO_EXCEPTION	Lorg/apache/cordova/PluginResult$Status;
          //   5129: aload 18
          //   5131: invokespecial 327	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V
          //   5134: invokevirtual 331	org/apache/cordova/filetransfer/FileTransfer$RequestContext:sendPluginResult	(Lorg/apache/cordova/PluginResult;)V
          //   5137: invokestatic 295	org/apache/cordova/filetransfer/FileTransfer:access$700	()Ljava/util/HashMap;
          //   5140: astore 15
          //   5142: aload 15
          //   5144: monitorenter
          //   5145: invokestatic 295	org/apache/cordova/filetransfer/FileTransfer:access$700	()Ljava/util/HashMap;
          //   5148: aload_0
          //   5149: getfield 71	org/apache/cordova/filetransfer/FileTransfer$1:val$objectId	Ljava/lang/String;
          //   5152: invokevirtual 301	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
          //   5155: pop
          //   5156: aload 15
          //   5158: monitorexit
          //   5159: aload 30
          //   5161: ifnull -5151 -> 10
          //   5164: aload_0
          //   5165: getfield 49	org/apache/cordova/filetransfer/FileTransfer$1:val$trustEveryone	Z
          //   5168: ifeq -5158 -> 10
          //   5171: aload_0
          //   5172: getfield 47	org/apache/cordova/filetransfer/FileTransfer$1:val$useHttps	Z
          //   5175: ifeq -5165 -> 10
          //   5178: aload 30
          //   5180: checkcast 103	javax/net/ssl/HttpsURLConnection
          //   5183: astore 15
          //   5185: aload 15
          //   5187: aload 23
          //   5189: invokevirtual 118	javax/net/ssl/HttpsURLConnection:setHostnameVerifier	(Ljavax/net/ssl/HostnameVerifier;)V
          //   5192: aload 15
          //   5194: aload 24
          //   5196: invokevirtual 305	javax/net/ssl/HttpsURLConnection:setSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
          //   5199: return
          //   5200: astore 16
          //   5202: aload 15
          //   5204: monitorexit
          //   5205: aload 34
          //   5207: astore 33
          //   5209: iload 7
          //   5211: istore_1
          //   5212: aload 16
          //   5214: athrow
          //   5215: iload_3
          //   5216: istore_2
          //   5217: iload 6
          //   5219: ifeq +28 -> 5247
          //   5222: aload 34
          //   5224: astore 33
          //   5226: iload_3
          //   5227: istore_1
          //   5228: aload 34
          //   5230: aload 36
          //   5232: invokevirtual 341	java/io/OutputStream:write	([B)V
          //   5235: aload 34
          //   5237: astore 33
          //   5239: iload_3
          //   5240: istore_1
          //   5241: iload_3
          //   5242: aload 36
          //   5244: arraylength
          //   5245: iadd
          //   5246: istore_2
          //   5247: aload 34
          //   5249: astore 33
          //   5251: iload_2
          //   5252: istore_1
          //   5253: aload 34
          //   5255: invokevirtual 396	java/io/OutputStream:flush	()V
          //   5258: aload 20
          //   5260: astore 29
          //   5262: aload 18
          //   5264: astore 21
          //   5266: aload 17
          //   5268: astore 22
          //   5270: aload 20
          //   5272: astore 30
          //   5274: iload 4
          //   5276: istore_3
          //   5277: aload 18
          //   5279: astore 23
          //   5281: aload 17
          //   5283: astore 24
          //   5285: iload_2
          //   5286: istore 5
          //   5288: aload 20
          //   5290: astore 32
          //   5292: aload 18
          //   5294: astore 27
          //   5296: aload 17
          //   5298: astore 28
          //   5300: aload 20
          //   5302: astore 31
          //   5304: aload 18
          //   5306: astore 25
          //   5308: aload 17
          //   5310: astore 26
          //   5312: aload 20
          //   5314: astore 19
          //   5316: aload 18
          //   5318: astore 15
          //   5320: aload 17
          //   5322: astore 16
          //   5324: aload 35
          //   5326: getfield 287	org/apache/cordova/CordovaResourceApi$OpenForReadResult:inputStream	Ljava/io/InputStream;
          //   5329: invokestatic 291	org/apache/cordova/filetransfer/FileTransfer:access$400	(Ljava/io/Closeable;)V
          //   5332: aload 20
          //   5334: astore 29
          //   5336: aload 18
          //   5338: astore 21
          //   5340: aload 17
          //   5342: astore 22
          //   5344: aload 20
          //   5346: astore 30
          //   5348: iload 4
          //   5350: istore_3
          //   5351: aload 18
          //   5353: astore 23
          //   5355: aload 17
          //   5357: astore 24
          //   5359: iload_2
          //   5360: istore 5
          //   5362: aload 20
          //   5364: astore 32
          //   5366: aload 18
          //   5368: astore 27
          //   5370: aload 17
          //   5372: astore 28
          //   5374: aload 20
          //   5376: astore 31
          //   5378: aload 18
          //   5380: astore 25
          //   5382: aload 17
          //   5384: astore 26
          //   5386: aload 20
          //   5388: astore 19
          //   5390: aload 18
          //   5392: astore 15
          //   5394: aload 17
          //   5396: astore 16
          //   5398: aload 34
          //   5400: invokestatic 291	org/apache/cordova/filetransfer/FileTransfer:access$400	(Ljava/io/Closeable;)V
          //   5403: aload 20
          //   5405: astore 29
          //   5407: aload 18
          //   5409: astore 21
          //   5411: aload 17
          //   5413: astore 22
          //   5415: aload 20
          //   5417: astore 30
          //   5419: iload 4
          //   5421: istore_3
          //   5422: aload 18
          //   5424: astore 23
          //   5426: aload 17
          //   5428: astore 24
          //   5430: iload_2
          //   5431: istore 5
          //   5433: aload 20
          //   5435: astore 32
          //   5437: aload 18
          //   5439: astore 27
          //   5441: aload 17
          //   5443: astore 28
          //   5445: aload 20
          //   5447: astore 31
          //   5449: aload 18
          //   5451: astore 25
          //   5453: aload 17
          //   5455: astore 26
          //   5457: aload 20
          //   5459: astore 19
          //   5461: aload 18
          //   5463: astore 15
          //   5465: aload 17
          //   5467: astore 16
          //   5469: aload_0
          //   5470: getfield 41	org/apache/cordova/filetransfer/FileTransfer$1:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   5473: astore 33
          //   5475: aload 20
          //   5477: astore 29
          //   5479: aload 18
          //   5481: astore 21
          //   5483: aload 17
          //   5485: astore 22
          //   5487: aload 20
          //   5489: astore 30
          //   5491: iload 4
          //   5493: istore_3
          //   5494: aload 18
          //   5496: astore 23
          //   5498: aload 17
          //   5500: astore 24
          //   5502: iload_2
          //   5503: istore 5
          //   5505: aload 20
          //   5507: astore 32
          //   5509: aload 18
          //   5511: astore 27
          //   5513: aload 17
          //   5515: astore 28
          //   5517: aload 20
          //   5519: astore 31
          //   5521: aload 18
          //   5523: astore 25
          //   5525: aload 17
          //   5527: astore 26
          //   5529: aload 20
          //   5531: astore 19
          //   5533: aload 18
          //   5535: astore 15
          //   5537: aload 17
          //   5539: astore 16
          //   5541: aload 33
          //   5543: monitorenter
          //   5544: aload_0
          //   5545: getfield 41	org/apache/cordova/filetransfer/FileTransfer$1:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   5548: aconst_null
          //   5549: putfield 335	org/apache/cordova/filetransfer/FileTransfer$RequestContext:connection	Ljava/net/HttpURLConnection;
          //   5552: aload 33
          //   5554: monitorexit
          //   5555: aload 20
          //   5557: astore 29
          //   5559: aload 18
          //   5561: astore 21
          //   5563: aload 17
          //   5565: astore 22
          //   5567: aload 20
          //   5569: astore 30
          //   5571: iload 4
          //   5573: istore_3
          //   5574: aload 18
          //   5576: astore 23
          //   5578: aload 17
          //   5580: astore 24
          //   5582: iload_2
          //   5583: istore 5
          //   5585: aload 20
          //   5587: astore 32
          //   5589: aload 18
          //   5591: astore 27
          //   5593: aload 17
          //   5595: astore 28
          //   5597: aload 20
          //   5599: astore 31
          //   5601: aload 18
          //   5603: astore 25
          //   5605: aload 17
          //   5607: astore 26
          //   5609: aload 20
          //   5611: astore 19
          //   5613: aload 18
          //   5615: astore 15
          //   5617: aload 17
          //   5619: astore 16
          //   5621: ldc -44
          //   5623: new 160	java/lang/StringBuilder
          //   5626: dup
          //   5627: invokespecial 161	java/lang/StringBuilder:<init>	()V
          //   5630: ldc_w 398
          //   5633: invokevirtual 193	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   5636: iload_2
          //   5637: invokevirtual 258	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
          //   5640: ldc_w 369
          //   5643: invokevirtual 193	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   5646: iload 4
          //   5648: invokevirtual 258	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
          //   5651: invokevirtual 228	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   5654: invokestatic 262	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
          //   5657: pop
          //   5658: aload 20
          //   5660: astore 29
          //   5662: aload 18
          //   5664: astore 21
          //   5666: aload 17
          //   5668: astore 22
          //   5670: aload 20
          //   5672: astore 30
          //   5674: iload 4
          //   5676: istore_3
          //   5677: aload 18
          //   5679: astore 23
          //   5681: aload 17
          //   5683: astore 24
          //   5685: iload_2
          //   5686: istore 5
          //   5688: aload 20
          //   5690: astore 32
          //   5692: aload 18
          //   5694: astore 27
          //   5696: aload 17
          //   5698: astore 28
          //   5700: aload 20
          //   5702: astore 31
          //   5704: aload 18
          //   5706: astore 25
          //   5708: aload 17
          //   5710: astore 26
          //   5712: aload 20
          //   5714: astore 19
          //   5716: aload 18
          //   5718: astore 15
          //   5720: aload 17
          //   5722: astore 16
          //   5724: aload 20
          //   5726: invokevirtual 401	java/net/HttpURLConnection:getResponseCode	()I
          //   5729: istore_1
          //   5730: aload 20
          //   5732: astore 29
          //   5734: aload 18
          //   5736: astore 21
          //   5738: aload 17
          //   5740: astore 22
          //   5742: aload 20
          //   5744: astore 30
          //   5746: iload 4
          //   5748: istore_3
          //   5749: aload 18
          //   5751: astore 23
          //   5753: aload 17
          //   5755: astore 24
          //   5757: iload_2
          //   5758: istore 5
          //   5760: aload 20
          //   5762: astore 32
          //   5764: aload 18
          //   5766: astore 27
          //   5768: aload 17
          //   5770: astore 28
          //   5772: aload 20
          //   5774: astore 31
          //   5776: aload 18
          //   5778: astore 25
          //   5780: aload 17
          //   5782: astore 26
          //   5784: aload 20
          //   5786: astore 19
          //   5788: aload 18
          //   5790: astore 15
          //   5792: aload 17
          //   5794: astore 16
          //   5796: ldc -44
          //   5798: new 160	java/lang/StringBuilder
          //   5801: dup
          //   5802: invokespecial 161	java/lang/StringBuilder:<init>	()V
          //   5805: ldc_w 403
          //   5808: invokevirtual 193	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   5811: iload_1
          //   5812: invokevirtual 258	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
          //   5815: invokevirtual 228	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   5818: invokestatic 262	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
          //   5821: pop
          //   5822: aload 20
          //   5824: astore 29
          //   5826: aload 18
          //   5828: astore 21
          //   5830: aload 17
          //   5832: astore 22
          //   5834: aload 20
          //   5836: astore 30
          //   5838: iload 4
          //   5840: istore_3
          //   5841: aload 18
          //   5843: astore 23
          //   5845: aload 17
          //   5847: astore 24
          //   5849: iload_2
          //   5850: istore 5
          //   5852: aload 20
          //   5854: astore 32
          //   5856: aload 18
          //   5858: astore 27
          //   5860: aload 17
          //   5862: astore 28
          //   5864: aload 20
          //   5866: astore 31
          //   5868: aload 18
          //   5870: astore 25
          //   5872: aload 17
          //   5874: astore 26
          //   5876: aload 20
          //   5878: astore 19
          //   5880: aload 18
          //   5882: astore 15
          //   5884: aload 17
          //   5886: astore 16
          //   5888: ldc -44
          //   5890: new 160	java/lang/StringBuilder
          //   5893: dup
          //   5894: invokespecial 161	java/lang/StringBuilder:<init>	()V
          //   5897: ldc_w 405
          //   5900: invokevirtual 193	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   5903: aload 20
          //   5905: invokevirtual 409	java/net/HttpURLConnection:getHeaderFields	()Ljava/util/Map;
          //   5908: invokevirtual 412	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
          //   5911: invokevirtual 228	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   5914: invokestatic 262	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
          //   5917: pop
          //   5918: aconst_null
          //   5919: astore 33
          //   5921: aload 20
          //   5923: invokestatic 416	org/apache/cordova/filetransfer/FileTransfer:access$500	(Ljava/net/URLConnection;)Lorg/apache/cordova/filetransfer/FileTransfer$TrackingInputStream;
          //   5926: astore 34
          //   5928: aload 34
          //   5930: astore 33
          //   5932: aload_0
          //   5933: getfield 41	org/apache/cordova/filetransfer/FileTransfer$1:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   5936: astore 15
          //   5938: aload 34
          //   5940: astore 33
          //   5942: aload 15
          //   5944: monitorenter
          //   5945: aload_0
          //   5946: getfield 41	org/apache/cordova/filetransfer/FileTransfer$1:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   5949: getfield 89	org/apache/cordova/filetransfer/FileTransfer$RequestContext:aborted	Z
          //   5952: ifeq +720 -> 6672
          //   5955: aload 15
          //   5957: monitorexit
          //   5958: aload 20
          //   5960: astore 29
          //   5962: aload 18
          //   5964: astore 21
          //   5966: aload 17
          //   5968: astore 22
          //   5970: aload 20
          //   5972: astore 30
          //   5974: iload 4
          //   5976: istore_3
          //   5977: aload 18
          //   5979: astore 23
          //   5981: aload 17
          //   5983: astore 24
          //   5985: iload_2
          //   5986: istore 5
          //   5988: aload 20
          //   5990: astore 32
          //   5992: aload 18
          //   5994: astore 27
          //   5996: aload 17
          //   5998: astore 28
          //   6000: aload 20
          //   6002: astore 31
          //   6004: aload 18
          //   6006: astore 25
          //   6008: aload 17
          //   6010: astore 26
          //   6012: aload 20
          //   6014: astore 19
          //   6016: aload 18
          //   6018: astore 15
          //   6020: aload 17
          //   6022: astore 16
          //   6024: aload_0
          //   6025: getfield 41	org/apache/cordova/filetransfer/FileTransfer$1:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   6028: astore 33
          //   6030: aload 20
          //   6032: astore 29
          //   6034: aload 18
          //   6036: astore 21
          //   6038: aload 17
          //   6040: astore 22
          //   6042: aload 20
          //   6044: astore 30
          //   6046: iload 4
          //   6048: istore_3
          //   6049: aload 18
          //   6051: astore 23
          //   6053: aload 17
          //   6055: astore 24
          //   6057: iload_2
          //   6058: istore 5
          //   6060: aload 20
          //   6062: astore 32
          //   6064: aload 18
          //   6066: astore 27
          //   6068: aload 17
          //   6070: astore 28
          //   6072: aload 20
          //   6074: astore 31
          //   6076: aload 18
          //   6078: astore 25
          //   6080: aload 17
          //   6082: astore 26
          //   6084: aload 20
          //   6086: astore 19
          //   6088: aload 18
          //   6090: astore 15
          //   6092: aload 17
          //   6094: astore 16
          //   6096: aload 33
          //   6098: monitorenter
          //   6099: aload_0
          //   6100: getfield 41	org/apache/cordova/filetransfer/FileTransfer$1:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   6103: aconst_null
          //   6104: putfield 335	org/apache/cordova/filetransfer/FileTransfer$RequestContext:connection	Ljava/net/HttpURLConnection;
          //   6107: aload 33
          //   6109: monitorexit
          //   6110: aload 20
          //   6112: astore 29
          //   6114: aload 18
          //   6116: astore 21
          //   6118: aload 17
          //   6120: astore 22
          //   6122: aload 20
          //   6124: astore 30
          //   6126: iload 4
          //   6128: istore_3
          //   6129: aload 18
          //   6131: astore 23
          //   6133: aload 17
          //   6135: astore 24
          //   6137: iload_2
          //   6138: istore 5
          //   6140: aload 20
          //   6142: astore 32
          //   6144: aload 18
          //   6146: astore 27
          //   6148: aload 17
          //   6150: astore 28
          //   6152: aload 20
          //   6154: astore 31
          //   6156: aload 18
          //   6158: astore 25
          //   6160: aload 17
          //   6162: astore 26
          //   6164: aload 20
          //   6166: astore 19
          //   6168: aload 18
          //   6170: astore 15
          //   6172: aload 17
          //   6174: astore 16
          //   6176: aload 34
          //   6178: invokestatic 291	org/apache/cordova/filetransfer/FileTransfer:access$400	(Ljava/io/Closeable;)V
          //   6181: invokestatic 295	org/apache/cordova/filetransfer/FileTransfer:access$700	()Ljava/util/HashMap;
          //   6184: astore 15
          //   6186: aload 15
          //   6188: monitorenter
          //   6189: invokestatic 295	org/apache/cordova/filetransfer/FileTransfer:access$700	()Ljava/util/HashMap;
          //   6192: aload_0
          //   6193: getfield 71	org/apache/cordova/filetransfer/FileTransfer$1:val$objectId	Ljava/lang/String;
          //   6196: invokevirtual 301	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
          //   6199: pop
          //   6200: aload 15
          //   6202: monitorexit
          //   6203: aload 20
          //   6205: ifnull -6195 -> 10
          //   6208: aload_0
          //   6209: getfield 49	org/apache/cordova/filetransfer/FileTransfer$1:val$trustEveryone	Z
          //   6212: ifeq -6202 -> 10
          //   6215: aload_0
          //   6216: getfield 47	org/apache/cordova/filetransfer/FileTransfer$1:val$useHttps	Z
          //   6219: ifeq -6209 -> 10
          //   6222: aload 20
          //   6224: checkcast 103	javax/net/ssl/HttpsURLConnection
          //   6227: astore 15
          //   6229: aload 15
          //   6231: aload 18
          //   6233: invokevirtual 118	javax/net/ssl/HttpsURLConnection:setHostnameVerifier	(Ljavax/net/ssl/HostnameVerifier;)V
          //   6236: aload 15
          //   6238: aload 17
          //   6240: invokevirtual 305	javax/net/ssl/HttpsURLConnection:setSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
          //   6243: return
          //   6244: astore 34
          //   6246: aload 33
          //   6248: monitorexit
          //   6249: aload 20
          //   6251: astore 29
          //   6253: aload 18
          //   6255: astore 21
          //   6257: aload 17
          //   6259: astore 22
          //   6261: aload 20
          //   6263: astore 30
          //   6265: iload 4
          //   6267: istore_3
          //   6268: aload 18
          //   6270: astore 23
          //   6272: aload 17
          //   6274: astore 24
          //   6276: iload_2
          //   6277: istore 5
          //   6279: aload 20
          //   6281: astore 32
          //   6283: aload 18
          //   6285: astore 27
          //   6287: aload 17
          //   6289: astore 28
          //   6291: aload 20
          //   6293: astore 31
          //   6295: aload 18
          //   6297: astore 25
          //   6299: aload 17
          //   6301: astore 26
          //   6303: aload 20
          //   6305: astore 19
          //   6307: aload 18
          //   6309: astore 15
          //   6311: aload 17
          //   6313: astore 16
          //   6315: aload 34
          //   6317: athrow
          //   6318: astore 17
          //   6320: aload 32
          //   6322: astore 19
          //   6324: aload 27
          //   6326: astore 15
          //   6328: aload 28
          //   6330: astore 16
          //   6332: ldc -44
          //   6334: aload 17
          //   6336: invokevirtual 215	org/json/JSONException:getMessage	()Ljava/lang/String;
          //   6339: aload 17
          //   6341: invokestatic 221	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
          //   6344: pop
          //   6345: aload 32
          //   6347: astore 19
          //   6349: aload 27
          //   6351: astore 15
          //   6353: aload 28
          //   6355: astore 16
          //   6357: aload_0
          //   6358: getfield 41	org/apache/cordova/filetransfer/FileTransfer$1:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   6361: new 318	org/apache/cordova/PluginResult
          //   6364: dup
          //   6365: getstatic 419	org/apache/cordova/PluginResult$Status:JSON_EXCEPTION	Lorg/apache/cordova/PluginResult$Status;
          //   6368: invokespecial 422	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;)V
          //   6371: invokevirtual 331	org/apache/cordova/filetransfer/FileTransfer$RequestContext:sendPluginResult	(Lorg/apache/cordova/PluginResult;)V
          //   6374: invokestatic 295	org/apache/cordova/filetransfer/FileTransfer:access$700	()Ljava/util/HashMap;
          //   6377: astore 15
          //   6379: aload 15
          //   6381: monitorenter
          //   6382: invokestatic 295	org/apache/cordova/filetransfer/FileTransfer:access$700	()Ljava/util/HashMap;
          //   6385: aload_0
          //   6386: getfield 71	org/apache/cordova/filetransfer/FileTransfer$1:val$objectId	Ljava/lang/String;
          //   6389: invokevirtual 301	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
          //   6392: pop
          //   6393: aload 15
          //   6395: monitorexit
          //   6396: aload 32
          //   6398: ifnull -6388 -> 10
          //   6401: aload_0
          //   6402: getfield 49	org/apache/cordova/filetransfer/FileTransfer$1:val$trustEveryone	Z
          //   6405: ifeq -6395 -> 10
          //   6408: aload_0
          //   6409: getfield 47	org/apache/cordova/filetransfer/FileTransfer$1:val$useHttps	Z
          //   6412: ifeq -6402 -> 10
          //   6415: aload 32
          //   6417: checkcast 103	javax/net/ssl/HttpsURLConnection
          //   6420: astore 15
          //   6422: aload 15
          //   6424: aload 27
          //   6426: invokevirtual 118	javax/net/ssl/HttpsURLConnection:setHostnameVerifier	(Ljavax/net/ssl/HostnameVerifier;)V
          //   6429: aload 15
          //   6431: aload 28
          //   6433: invokevirtual 305	javax/net/ssl/HttpsURLConnection:setSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
          //   6436: return
          //   6437: astore 34
          //   6439: aload 33
          //   6441: monitorexit
          //   6442: aload 20
          //   6444: astore 29
          //   6446: aload 18
          //   6448: astore 21
          //   6450: aload 17
          //   6452: astore 22
          //   6454: aload 20
          //   6456: astore 30
          //   6458: iload 4
          //   6460: istore_3
          //   6461: aload 18
          //   6463: astore 23
          //   6465: aload 17
          //   6467: astore 24
          //   6469: iload_2
          //   6470: istore 5
          //   6472: aload 20
          //   6474: astore 32
          //   6476: aload 18
          //   6478: astore 27
          //   6480: aload 17
          //   6482: astore 28
          //   6484: aload 20
          //   6486: astore 31
          //   6488: aload 18
          //   6490: astore 25
          //   6492: aload 17
          //   6494: astore 26
          //   6496: aload 20
          //   6498: astore 19
          //   6500: aload 18
          //   6502: astore 15
          //   6504: aload 17
          //   6506: astore 16
          //   6508: aload 34
          //   6510: athrow
          //   6511: astore 17
          //   6513: aload 31
          //   6515: astore 19
          //   6517: aload 25
          //   6519: astore 15
          //   6521: aload 26
          //   6523: astore 16
          //   6525: getstatic 387	org/apache/cordova/filetransfer/FileTransfer:CONNECTION_ERR	I
          //   6528: aload_0
          //   6529: getfield 69	org/apache/cordova/filetransfer/FileTransfer$1:val$source	Ljava/lang/String;
          //   6532: aload_0
          //   6533: getfield 55	org/apache/cordova/filetransfer/FileTransfer$1:val$target	Ljava/lang/String;
          //   6536: aload 31
          //   6538: aload 17
          //   6540: invokestatic 315	org/apache/cordova/filetransfer/FileTransfer:access$600	(ILjava/lang/String;Ljava/lang/String;Ljava/net/URLConnection;Ljava/lang/Throwable;)Lorg/json/JSONObject;
          //   6543: astore 18
          //   6545: aload 31
          //   6547: astore 19
          //   6549: aload 25
          //   6551: astore 15
          //   6553: aload 26
          //   6555: astore 16
          //   6557: ldc -44
          //   6559: aload 18
          //   6561: invokevirtual 316	org/json/JSONObject:toString	()Ljava/lang/String;
          //   6564: aload 17
          //   6566: invokestatic 221	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
          //   6569: pop
          //   6570: aload 31
          //   6572: astore 19
          //   6574: aload 25
          //   6576: astore 15
          //   6578: aload 26
          //   6580: astore 16
          //   6582: aload_0
          //   6583: getfield 41	org/apache/cordova/filetransfer/FileTransfer$1:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   6586: new 318	org/apache/cordova/PluginResult
          //   6589: dup
          //   6590: getstatic 324	org/apache/cordova/PluginResult$Status:IO_EXCEPTION	Lorg/apache/cordova/PluginResult$Status;
          //   6593: aload 18
          //   6595: invokespecial 327	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V
          //   6598: invokevirtual 331	org/apache/cordova/filetransfer/FileTransfer$RequestContext:sendPluginResult	(Lorg/apache/cordova/PluginResult;)V
          //   6601: invokestatic 295	org/apache/cordova/filetransfer/FileTransfer:access$700	()Ljava/util/HashMap;
          //   6604: astore 15
          //   6606: aload 15
          //   6608: monitorenter
          //   6609: invokestatic 295	org/apache/cordova/filetransfer/FileTransfer:access$700	()Ljava/util/HashMap;
          //   6612: aload_0
          //   6613: getfield 71	org/apache/cordova/filetransfer/FileTransfer$1:val$objectId	Ljava/lang/String;
          //   6616: invokevirtual 301	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
          //   6619: pop
          //   6620: aload 15
          //   6622: monitorexit
          //   6623: aload 31
          //   6625: ifnull -6615 -> 10
          //   6628: aload_0
          //   6629: getfield 49	org/apache/cordova/filetransfer/FileTransfer$1:val$trustEveryone	Z
          //   6632: ifeq -6622 -> 10
          //   6635: aload_0
          //   6636: getfield 47	org/apache/cordova/filetransfer/FileTransfer$1:val$useHttps	Z
          //   6639: ifeq -6629 -> 10
          //   6642: aload 31
          //   6644: checkcast 103	javax/net/ssl/HttpsURLConnection
          //   6647: astore 15
          //   6649: aload 15
          //   6651: aload 25
          //   6653: invokevirtual 118	javax/net/ssl/HttpsURLConnection:setHostnameVerifier	(Ljavax/net/ssl/HostnameVerifier;)V
          //   6656: aload 15
          //   6658: aload 26
          //   6660: invokevirtual 305	javax/net/ssl/HttpsURLConnection:setSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
          //   6663: return
          //   6664: astore 16
          //   6666: aload 15
          //   6668: monitorexit
          //   6669: aload 16
          //   6671: athrow
          //   6672: aload_0
          //   6673: getfield 41	org/apache/cordova/filetransfer/FileTransfer$1:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   6676: aload 20
          //   6678: putfield 335	org/apache/cordova/filetransfer/FileTransfer$RequestContext:connection	Ljava/net/HttpURLConnection;
          //   6681: aload 15
          //   6683: monitorexit
          //   6684: aload 34
          //   6686: astore 33
          //   6688: new 424	java/io/ByteArrayOutputStream
          //   6691: dup
          //   6692: sipush 1024
          //   6695: aload 20
          //   6697: invokevirtual 427	java/net/HttpURLConnection:getContentLength	()I
          //   6700: invokestatic 430	java/lang/Math:max	(II)I
          //   6703: invokespecial 432	java/io/ByteArrayOutputStream:<init>	(I)V
          //   6706: astore 15
          //   6708: aload 34
          //   6710: astore 33
          //   6712: sipush 1024
          //   6715: newarray <illegal type>
          //   6717: astore 16
          //   6719: aload 34
          //   6721: astore 33
          //   6723: aload 34
          //   6725: aload 16
          //   6727: invokevirtual 437	org/apache/cordova/filetransfer/FileTransfer$TrackingInputStream:read	([B)I
          //   6730: istore_3
          //   6731: iload_3
          //   6732: ifle +392 -> 7124
          //   6735: aload 34
          //   6737: astore 33
          //   6739: aload 15
          //   6741: aload 16
          //   6743: iconst_0
          //   6744: iload_3
          //   6745: invokevirtual 438	java/io/ByteArrayOutputStream:write	([BII)V
          //   6748: goto -29 -> 6719
          //   6751: astore 35
          //   6753: aload 20
          //   6755: astore 29
          //   6757: aload 18
          //   6759: astore 21
          //   6761: aload 17
          //   6763: astore 22
          //   6765: aload 20
          //   6767: astore 30
          //   6769: iload 4
          //   6771: istore_3
          //   6772: aload 18
          //   6774: astore 23
          //   6776: aload 17
          //   6778: astore 24
          //   6780: iload_2
          //   6781: istore 5
          //   6783: aload 20
          //   6785: astore 32
          //   6787: aload 18
          //   6789: astore 27
          //   6791: aload 17
          //   6793: astore 28
          //   6795: aload 20
          //   6797: astore 31
          //   6799: aload 18
          //   6801: astore 25
          //   6803: aload 17
          //   6805: astore 26
          //   6807: aload 20
          //   6809: astore 19
          //   6811: aload 18
          //   6813: astore 15
          //   6815: aload 17
          //   6817: astore 16
          //   6819: aload_0
          //   6820: getfield 41	org/apache/cordova/filetransfer/FileTransfer$1:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   6823: astore 34
          //   6825: aload 20
          //   6827: astore 29
          //   6829: aload 18
          //   6831: astore 21
          //   6833: aload 17
          //   6835: astore 22
          //   6837: aload 20
          //   6839: astore 30
          //   6841: iload 4
          //   6843: istore_3
          //   6844: aload 18
          //   6846: astore 23
          //   6848: aload 17
          //   6850: astore 24
          //   6852: iload_2
          //   6853: istore 5
          //   6855: aload 20
          //   6857: astore 32
          //   6859: aload 18
          //   6861: astore 27
          //   6863: aload 17
          //   6865: astore 28
          //   6867: aload 20
          //   6869: astore 31
          //   6871: aload 18
          //   6873: astore 25
          //   6875: aload 17
          //   6877: astore 26
          //   6879: aload 20
          //   6881: astore 19
          //   6883: aload 18
          //   6885: astore 15
          //   6887: aload 17
          //   6889: astore 16
          //   6891: aload 34
          //   6893: monitorenter
          //   6894: aload_0
          //   6895: getfield 41	org/apache/cordova/filetransfer/FileTransfer$1:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   6898: aconst_null
          //   6899: putfield 335	org/apache/cordova/filetransfer/FileTransfer$RequestContext:connection	Ljava/net/HttpURLConnection;
          //   6902: aload 34
          //   6904: monitorexit
          //   6905: aload 20
          //   6907: astore 29
          //   6909: aload 18
          //   6911: astore 21
          //   6913: aload 17
          //   6915: astore 22
          //   6917: aload 20
          //   6919: astore 30
          //   6921: iload 4
          //   6923: istore_3
          //   6924: aload 18
          //   6926: astore 23
          //   6928: aload 17
          //   6930: astore 24
          //   6932: iload_2
          //   6933: istore 5
          //   6935: aload 20
          //   6937: astore 32
          //   6939: aload 18
          //   6941: astore 27
          //   6943: aload 17
          //   6945: astore 28
          //   6947: aload 20
          //   6949: astore 31
          //   6951: aload 18
          //   6953: astore 25
          //   6955: aload 17
          //   6957: astore 26
          //   6959: aload 20
          //   6961: astore 19
          //   6963: aload 18
          //   6965: astore 15
          //   6967: aload 17
          //   6969: astore 16
          //   6971: aload 33
          //   6973: invokestatic 291	org/apache/cordova/filetransfer/FileTransfer:access$400	(Ljava/io/Closeable;)V
          //   6976: aload 20
          //   6978: astore 29
          //   6980: aload 18
          //   6982: astore 21
          //   6984: aload 17
          //   6986: astore 22
          //   6988: aload 20
          //   6990: astore 30
          //   6992: iload 4
          //   6994: istore_3
          //   6995: aload 18
          //   6997: astore 23
          //   6999: aload 17
          //   7001: astore 24
          //   7003: iload_2
          //   7004: istore 5
          //   7006: aload 20
          //   7008: astore 32
          //   7010: aload 18
          //   7012: astore 27
          //   7014: aload 17
          //   7016: astore 28
          //   7018: aload 20
          //   7020: astore 31
          //   7022: aload 18
          //   7024: astore 25
          //   7026: aload 17
          //   7028: astore 26
          //   7030: aload 20
          //   7032: astore 19
          //   7034: aload 18
          //   7036: astore 15
          //   7038: aload 17
          //   7040: astore 16
          //   7042: aload 35
          //   7044: athrow
          //   7045: astore 17
          //   7047: invokestatic 295	org/apache/cordova/filetransfer/FileTransfer:access$700	()Ljava/util/HashMap;
          //   7050: astore 18
          //   7052: aload 18
          //   7054: monitorenter
          //   7055: invokestatic 295	org/apache/cordova/filetransfer/FileTransfer:access$700	()Ljava/util/HashMap;
          //   7058: aload_0
          //   7059: getfield 71	org/apache/cordova/filetransfer/FileTransfer$1:val$objectId	Ljava/lang/String;
          //   7062: invokevirtual 301	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
          //   7065: pop
          //   7066: aload 18
          //   7068: monitorexit
          //   7069: aload 19
          //   7071: ifnull +38 -> 7109
          //   7074: aload_0
          //   7075: getfield 49	org/apache/cordova/filetransfer/FileTransfer$1:val$trustEveryone	Z
          //   7078: ifeq +31 -> 7109
          //   7081: aload_0
          //   7082: getfield 47	org/apache/cordova/filetransfer/FileTransfer$1:val$useHttps	Z
          //   7085: ifeq +24 -> 7109
          //   7088: aload 19
          //   7090: checkcast 103	javax/net/ssl/HttpsURLConnection
          //   7093: astore 18
          //   7095: aload 18
          //   7097: aload 15
          //   7099: invokevirtual 118	javax/net/ssl/HttpsURLConnection:setHostnameVerifier	(Ljavax/net/ssl/HostnameVerifier;)V
          //   7102: aload 18
          //   7104: aload 16
          //   7106: invokevirtual 305	javax/net/ssl/HttpsURLConnection:setSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
          //   7109: aload 17
          //   7111: athrow
          //   7112: astore 16
          //   7114: aload 15
          //   7116: monitorexit
          //   7117: aload 34
          //   7119: astore 33
          //   7121: aload 16
          //   7123: athrow
          //   7124: aload 34
          //   7126: astore 33
          //   7128: aload 15
          //   7130: ldc -26
          //   7132: invokevirtual 440	java/io/ByteArrayOutputStream:toString	(Ljava/lang/String;)Ljava/lang/String;
          //   7135: astore 35
          //   7137: aload 20
          //   7139: astore 29
          //   7141: aload 18
          //   7143: astore 21
          //   7145: aload 17
          //   7147: astore 22
          //   7149: aload 20
          //   7151: astore 30
          //   7153: iload 4
          //   7155: istore_3
          //   7156: aload 18
          //   7158: astore 23
          //   7160: aload 17
          //   7162: astore 24
          //   7164: iload_2
          //   7165: istore 5
          //   7167: aload 20
          //   7169: astore 32
          //   7171: aload 18
          //   7173: astore 27
          //   7175: aload 17
          //   7177: astore 28
          //   7179: aload 20
          //   7181: astore 31
          //   7183: aload 18
          //   7185: astore 25
          //   7187: aload 17
          //   7189: astore 26
          //   7191: aload 20
          //   7193: astore 19
          //   7195: aload 18
          //   7197: astore 15
          //   7199: aload 17
          //   7201: astore 16
          //   7203: aload_0
          //   7204: getfield 41	org/apache/cordova/filetransfer/FileTransfer$1:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   7207: astore 33
          //   7209: aload 20
          //   7211: astore 29
          //   7213: aload 18
          //   7215: astore 21
          //   7217: aload 17
          //   7219: astore 22
          //   7221: aload 20
          //   7223: astore 30
          //   7225: iload 4
          //   7227: istore_3
          //   7228: aload 18
          //   7230: astore 23
          //   7232: aload 17
          //   7234: astore 24
          //   7236: iload_2
          //   7237: istore 5
          //   7239: aload 20
          //   7241: astore 32
          //   7243: aload 18
          //   7245: astore 27
          //   7247: aload 17
          //   7249: astore 28
          //   7251: aload 20
          //   7253: astore 31
          //   7255: aload 18
          //   7257: astore 25
          //   7259: aload 17
          //   7261: astore 26
          //   7263: aload 20
          //   7265: astore 19
          //   7267: aload 18
          //   7269: astore 15
          //   7271: aload 17
          //   7273: astore 16
          //   7275: aload 33
          //   7277: monitorenter
          //   7278: aload_0
          //   7279: getfield 41	org/apache/cordova/filetransfer/FileTransfer$1:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   7282: aconst_null
          //   7283: putfield 335	org/apache/cordova/filetransfer/FileTransfer$RequestContext:connection	Ljava/net/HttpURLConnection;
          //   7286: aload 33
          //   7288: monitorexit
          //   7289: aload 20
          //   7291: astore 29
          //   7293: aload 18
          //   7295: astore 21
          //   7297: aload 17
          //   7299: astore 22
          //   7301: aload 20
          //   7303: astore 30
          //   7305: iload 4
          //   7307: istore_3
          //   7308: aload 18
          //   7310: astore 23
          //   7312: aload 17
          //   7314: astore 24
          //   7316: iload_2
          //   7317: istore 5
          //   7319: aload 20
          //   7321: astore 32
          //   7323: aload 18
          //   7325: astore 27
          //   7327: aload 17
          //   7329: astore 28
          //   7331: aload 20
          //   7333: astore 31
          //   7335: aload 18
          //   7337: astore 25
          //   7339: aload 17
          //   7341: astore 26
          //   7343: aload 20
          //   7345: astore 19
          //   7347: aload 18
          //   7349: astore 15
          //   7351: aload 17
          //   7353: astore 16
          //   7355: aload 34
          //   7357: invokestatic 291	org/apache/cordova/filetransfer/FileTransfer:access$400	(Ljava/io/Closeable;)V
          //   7360: aload 20
          //   7362: astore 29
          //   7364: aload 18
          //   7366: astore 21
          //   7368: aload 17
          //   7370: astore 22
          //   7372: aload 20
          //   7374: astore 30
          //   7376: iload 4
          //   7378: istore_3
          //   7379: aload 18
          //   7381: astore 23
          //   7383: aload 17
          //   7385: astore 24
          //   7387: iload_2
          //   7388: istore 5
          //   7390: aload 20
          //   7392: astore 32
          //   7394: aload 18
          //   7396: astore 27
          //   7398: aload 17
          //   7400: astore 28
          //   7402: aload 20
          //   7404: astore 31
          //   7406: aload 18
          //   7408: astore 25
          //   7410: aload 17
          //   7412: astore 26
          //   7414: aload 20
          //   7416: astore 19
          //   7418: aload 18
          //   7420: astore 15
          //   7422: aload 17
          //   7424: astore 16
          //   7426: ldc -44
          //   7428: ldc_w 442
          //   7431: invokestatic 262	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
          //   7434: pop
          //   7435: aload 20
          //   7437: astore 29
          //   7439: aload 18
          //   7441: astore 21
          //   7443: aload 17
          //   7445: astore 22
          //   7447: aload 20
          //   7449: astore 30
          //   7451: iload 4
          //   7453: istore_3
          //   7454: aload 18
          //   7456: astore 23
          //   7458: aload 17
          //   7460: astore 24
          //   7462: iload_2
          //   7463: istore 5
          //   7465: aload 20
          //   7467: astore 32
          //   7469: aload 18
          //   7471: astore 27
          //   7473: aload 17
          //   7475: astore 28
          //   7477: aload 20
          //   7479: astore 31
          //   7481: aload 18
          //   7483: astore 25
          //   7485: aload 17
          //   7487: astore 26
          //   7489: aload 20
          //   7491: astore 19
          //   7493: aload 18
          //   7495: astore 15
          //   7497: aload 17
          //   7499: astore 16
          //   7501: ldc -44
          //   7503: aload 35
          //   7505: iconst_0
          //   7506: sipush 256
          //   7509: aload 35
          //   7511: invokevirtual 444	java/lang/String:length	()I
          //   7514: invokestatic 353	java/lang/Math:min	(II)I
          //   7517: invokevirtual 448	java/lang/String:substring	(II)Ljava/lang/String;
          //   7520: invokestatic 262	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
          //   7523: pop
          //   7524: aload 20
          //   7526: astore 29
          //   7528: aload 18
          //   7530: astore 21
          //   7532: aload 17
          //   7534: astore 22
          //   7536: aload 20
          //   7538: astore 30
          //   7540: iload 4
          //   7542: istore_3
          //   7543: aload 18
          //   7545: astore 23
          //   7547: aload 17
          //   7549: astore 24
          //   7551: iload_2
          //   7552: istore 5
          //   7554: aload 20
          //   7556: astore 32
          //   7558: aload 18
          //   7560: astore 27
          //   7562: aload 17
          //   7564: astore 28
          //   7566: aload 20
          //   7568: astore 31
          //   7570: aload 18
          //   7572: astore 25
          //   7574: aload 17
          //   7576: astore 26
          //   7578: aload 20
          //   7580: astore 19
          //   7582: aload 18
          //   7584: astore 15
          //   7586: aload 17
          //   7588: astore 16
          //   7590: aload 47
          //   7592: iload_1
          //   7593: invokevirtual 451	org/apache/cordova/filetransfer/FileUploadResult:setResponseCode	(I)V
          //   7596: aload 20
          //   7598: astore 29
          //   7600: aload 18
          //   7602: astore 21
          //   7604: aload 17
          //   7606: astore 22
          //   7608: aload 20
          //   7610: astore 30
          //   7612: iload 4
          //   7614: istore_3
          //   7615: aload 18
          //   7617: astore 23
          //   7619: aload 17
          //   7621: astore 24
          //   7623: iload_2
          //   7624: istore 5
          //   7626: aload 20
          //   7628: astore 32
          //   7630: aload 18
          //   7632: astore 27
          //   7634: aload 17
          //   7636: astore 28
          //   7638: aload 20
          //   7640: astore 31
          //   7642: aload 18
          //   7644: astore 25
          //   7646: aload 17
          //   7648: astore 26
          //   7650: aload 20
          //   7652: astore 19
          //   7654: aload 18
          //   7656: astore 15
          //   7658: aload 17
          //   7660: astore 16
          //   7662: aload 47
          //   7664: aload 35
          //   7666: invokevirtual 454	org/apache/cordova/filetransfer/FileUploadResult:setResponse	(Ljava/lang/String;)V
          //   7669: aload 20
          //   7671: astore 29
          //   7673: aload 18
          //   7675: astore 21
          //   7677: aload 17
          //   7679: astore 22
          //   7681: aload 20
          //   7683: astore 30
          //   7685: iload 4
          //   7687: istore_3
          //   7688: aload 18
          //   7690: astore 23
          //   7692: aload 17
          //   7694: astore 24
          //   7696: iload_2
          //   7697: istore 5
          //   7699: aload 20
          //   7701: astore 32
          //   7703: aload 18
          //   7705: astore 27
          //   7707: aload 17
          //   7709: astore 28
          //   7711: aload 20
          //   7713: astore 31
          //   7715: aload 18
          //   7717: astore 25
          //   7719: aload 17
          //   7721: astore 26
          //   7723: aload 20
          //   7725: astore 19
          //   7727: aload 18
          //   7729: astore 15
          //   7731: aload 17
          //   7733: astore 16
          //   7735: aload_0
          //   7736: getfield 41	org/apache/cordova/filetransfer/FileTransfer$1:val$context	Lorg/apache/cordova/filetransfer/FileTransfer$RequestContext;
          //   7739: new 318	org/apache/cordova/PluginResult
          //   7742: dup
          //   7743: getstatic 377	org/apache/cordova/PluginResult$Status:OK	Lorg/apache/cordova/PluginResult$Status;
          //   7746: aload 47
          //   7748: invokevirtual 455	org/apache/cordova/filetransfer/FileUploadResult:toJSONObject	()Lorg/json/JSONObject;
          //   7751: invokespecial 327	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONObject;)V
          //   7754: invokevirtual 331	org/apache/cordova/filetransfer/FileTransfer$RequestContext:sendPluginResult	(Lorg/apache/cordova/PluginResult;)V
          //   7757: invokestatic 295	org/apache/cordova/filetransfer/FileTransfer:access$700	()Ljava/util/HashMap;
          //   7760: astore 15
          //   7762: aload 15
          //   7764: monitorenter
          //   7765: invokestatic 295	org/apache/cordova/filetransfer/FileTransfer:access$700	()Ljava/util/HashMap;
          //   7768: aload_0
          //   7769: getfield 71	org/apache/cordova/filetransfer/FileTransfer$1:val$objectId	Ljava/lang/String;
          //   7772: invokevirtual 301	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
          //   7775: pop
          //   7776: aload 15
          //   7778: monitorexit
          //   7779: aload 20
          //   7781: ifnull -7771 -> 10
          //   7784: aload_0
          //   7785: getfield 49	org/apache/cordova/filetransfer/FileTransfer$1:val$trustEveryone	Z
          //   7788: ifeq -7778 -> 10
          //   7791: aload_0
          //   7792: getfield 47	org/apache/cordova/filetransfer/FileTransfer$1:val$useHttps	Z
          //   7795: ifeq -7785 -> 10
          //   7798: aload 20
          //   7800: checkcast 103	javax/net/ssl/HttpsURLConnection
          //   7803: astore 15
          //   7805: aload 15
          //   7807: aload 18
          //   7809: invokevirtual 118	javax/net/ssl/HttpsURLConnection:setHostnameVerifier	(Ljavax/net/ssl/HostnameVerifier;)V
          //   7812: aload 15
          //   7814: aload 17
          //   7816: invokevirtual 305	javax/net/ssl/HttpsURLConnection:setSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
          //   7819: return
          //   7820: astore 34
          //   7822: aload 33
          //   7824: monitorexit
          //   7825: aload 20
          //   7827: astore 29
          //   7829: aload 18
          //   7831: astore 21
          //   7833: aload 17
          //   7835: astore 22
          //   7837: aload 20
          //   7839: astore 30
          //   7841: iload 4
          //   7843: istore_3
          //   7844: aload 18
          //   7846: astore 23
          //   7848: aload 17
          //   7850: astore 24
          //   7852: iload_2
          //   7853: istore 5
          //   7855: aload 20
          //   7857: astore 32
          //   7859: aload 18
          //   7861: astore 27
          //   7863: aload 17
          //   7865: astore 28
          //   7867: aload 20
          //   7869: astore 31
          //   7871: aload 18
          //   7873: astore 25
          //   7875: aload 17
          //   7877: astore 26
          //   7879: aload 20
          //   7881: astore 19
          //   7883: aload 18
          //   7885: astore 15
          //   7887: aload 17
          //   7889: astore 16
          //   7891: aload 34
          //   7893: athrow
          //   7894: astore 33
          //   7896: aload 34
          //   7898: monitorexit
          //   7899: aload 20
          //   7901: astore 29
          //   7903: aload 18
          //   7905: astore 21
          //   7907: aload 17
          //   7909: astore 22
          //   7911: aload 20
          //   7913: astore 30
          //   7915: iload 4
          //   7917: istore_3
          //   7918: aload 18
          //   7920: astore 23
          //   7922: aload 17
          //   7924: astore 24
          //   7926: iload_2
          //   7927: istore 5
          //   7929: aload 20
          //   7931: astore 32
          //   7933: aload 18
          //   7935: astore 27
          //   7937: aload 17
          //   7939: astore 28
          //   7941: aload 20
          //   7943: astore 31
          //   7945: aload 18
          //   7947: astore 25
          //   7949: aload 17
          //   7951: astore 26
          //   7953: aload 20
          //   7955: astore 19
          //   7957: aload 18
          //   7959: astore 15
          //   7961: aload 17
          //   7963: astore 16
          //   7965: aload 33
          //   7967: athrow
          //   7968: astore 16
          //   7970: aload 15
          //   7972: monitorexit
          //   7973: aload 16
          //   7975: athrow
          //   7976: astore 16
          //   7978: aload 15
          //   7980: monitorexit
          //   7981: aload 16
          //   7983: athrow
          //   7984: astore 16
          //   7986: aload 15
          //   7988: monitorexit
          //   7989: aload 16
          //   7991: athrow
          //   7992: astore 16
          //   7994: aload 15
          //   7996: monitorexit
          //   7997: aload 16
          //   7999: athrow
          //   8000: astore 16
          //   8002: aload 15
          //   8004: monitorexit
          //   8005: aload 16
          //   8007: athrow
          //   8008: astore 15
          //   8010: aload 18
          //   8012: monitorexit
          //   8013: aload 15
          //   8015: athrow
          //   8016: iconst_1
          //   8017: istore 6
          //   8019: goto -6818 -> 1201
          //   8022: iconst_1
          //   8023: istore_1
          //   8024: iload_1
          //   8025: ifne +9 -> 8034
          //   8028: iload 4
          //   8030: iconst_m1
          //   8031: if_icmpne -3817 -> 4214
          //   8034: iconst_1
          //   8035: istore_1
          //   8036: goto -4321 -> 3715
          // Local variable table:
          //   start	length	slot	name	signature
          //   0	8039	0	this	1
          //   71	7965	1	i	int
          //   69	7858	2	j	int
          //   89	7829	3	k	int
          //   3079	4953	4	m	int
          //   100	7828	5	n	int
          //   1201	6817	6	i1	int
          //   63	5147	7	i2	int
          //   66	4181	8	i3	int
          //   3005	241	9	i4	int
          //   3076	170	10	i5	int
          //   4554	218	11	l1	long
          //   4599	171	13	l2	long
          //   8008	6	15	localObject2	Object
          //   136	4230	16	localObject3	Object
          //   4449	6	16	localObject4	Object
          //   4740	377	16	localObject5	Object
          //   5200	13	16	localObject6	Object
          //   5322	1259	16	localObject7	Object
          //   6664	6	16	localObject8	Object
          //   6717	388	16	localObject9	Object
          //   7112	10	16	localObject10	Object
          //   7201	763	16	localSSLSocketFactory1	SSLSocketFactory
          //   7968	6	16	localObject11	Object
          //   7976	6	16	localObject12	Object
          //   7984	6	16	localObject13	Object
          //   7992	6	16	localObject14	Object
          //   8000	6	16	localObject15	Object
          //   24	4259	17	localObject16	Object
          //   4296	690	17	localFileNotFoundException	java.io.FileNotFoundException
          //   4992	1320	17	localIOException	IOException
          //   6318	187	17	localJSONException1	JSONException
          //   6511	528	17	localThrowable	Throwable
          //   7045	917	17	localSSLSocketFactory2	SSLSocketFactory
          //   12	7999	18	localObject17	Object
          //   128	7828	19	localObject18	Object
          //   15	7939	20	localHttpURLConnection1	HttpURLConnection
          //   78	7828	21	localObject19	Object
          //   82	7828	22	localObject20	Object
          //   92	7829	23	localObject21	Object
          //   96	7829	24	localObject22	Object
          //   120	7828	25	localObject23	Object
          //   124	7828	26	localObject24	Object
          //   108	7828	27	localObject25	Object
          //   112	7828	28	localObject26	Object
          //   74	7828	29	localObject27	Object
          //   86	7828	30	localObject28	Object
          //   116	7828	31	localObject29	Object
          //   104	7828	32	localHttpURLConnection2	HttpURLConnection
          //   27	7796	33	localObject30	Object
          //   7894	72	33	localObject31	Object
          //   30	598	34	localObject32	Object
          //   2271	76	34	localJSONException2	JSONException
          //   3955	800	34	localOutputStream	java.io.OutputStream
          //   4776	623	34	localCloseable	Closeable
          //   5926	251	34	localTrackingInputStream	FileTransfer.TrackingInputStream
          //   6244	72	34	localObject33	Object
          //   6437	299	34	localObject34	Object
          //   6823	533	34	localRequestContext	FileTransfer.RequestContext
          //   7820	77	34	localObject35	Object
          //   33	5292	35	localOpenForReadResult	org.apache.cordova.CordovaResourceApi.OpenForReadResult
          //   6751	292	35	localObject36	Object
          //   7135	530	35	str	String
          //   36	5207	36	arrayOfByte1	byte[]
          //   39	4458	37	arrayOfByte2	byte[]
          //   45	500	38	localObject37	Object
          //   48	511	39	localObject38	Object
          //   51	524	40	localObject39	Object
          //   54	533	41	localObject40	Object
          //   57	542	42	localObject41	Object
          //   42	707	43	localHttpsURLConnection	HttpsURLConnection
          //   60	327	44	localObject42	Object
          //   18	247	45	localObject43	Object
          //   21	256	46	localObject44	Object
          //   145	7602	47	localFileUploadResult	FileUploadResult
          //   220	4513	48	localFileProgressResult	FileProgressResult
          // Exception table:
          //   from	to	target	type
          //   1717	1726	2271	org/json/JSONException
          //   1780	1790	2271	org/json/JSONException
          //   1844	1853	2271	org/json/JSONException
          //   1907	1920	2271	org/json/JSONException
          //   1974	1992	2271	org/json/JSONException
          //   2046	2067	2271	org/json/JSONException
          //   2121	2134	2271	org/json/JSONException
          //   2188	2206	2271	org/json/JSONException
          //   2260	2268	2271	org/json/JSONException
          //   138	147	4296	java/io/FileNotFoundException
          //   213	222	4296	java/io/FileNotFoundException
          //   288	301	4296	java/io/FileNotFoundException
          //   375	382	4296	java/io/FileNotFoundException
          //   456	463	4296	java/io/FileNotFoundException
          //   529	536	4296	java/io/FileNotFoundException
          //   602	609	4296	java/io/FileNotFoundException
          //   675	682	4296	java/io/FileNotFoundException
          //   748	756	4296	java/io/FileNotFoundException
          //   822	828	4296	java/io/FileNotFoundException
          //   894	900	4296	java/io/FileNotFoundException
          //   966	972	4296	java/io/FileNotFoundException
          //   1038	1047	4296	java/io/FileNotFoundException
          //   1113	1120	4296	java/io/FileNotFoundException
          //   1186	1198	4296	java/io/FileNotFoundException
          //   1272	1281	4296	java/io/FileNotFoundException
          //   1347	1360	4296	java/io/FileNotFoundException
          //   1431	1440	4296	java/io/FileNotFoundException
          //   1506	1513	4296	java/io/FileNotFoundException
          //   1579	1588	4296	java/io/FileNotFoundException
          //   1654	1663	4296	java/io/FileNotFoundException
          //   1717	1726	4296	java/io/FileNotFoundException
          //   1780	1790	4296	java/io/FileNotFoundException
          //   1844	1853	4296	java/io/FileNotFoundException
          //   1907	1920	4296	java/io/FileNotFoundException
          //   1974	1992	4296	java/io/FileNotFoundException
          //   2046	2067	4296	java/io/FileNotFoundException
          //   2121	2134	4296	java/io/FileNotFoundException
          //   2188	2206	4296	java/io/FileNotFoundException
          //   2260	2268	4296	java/io/FileNotFoundException
          //   2339	2352	4296	java/io/FileNotFoundException
          //   2418	2436	4296	java/io/FileNotFoundException
          //   2502	2522	4296	java/io/FileNotFoundException
          //   2588	2613	4296	java/io/FileNotFoundException
          //   2679	2704	4296	java/io/FileNotFoundException
          //   2770	2782	4296	java/io/FileNotFoundException
          //   2848	2857	4296	java/io/FileNotFoundException
          //   2923	2936	4296	java/io/FileNotFoundException
          //   3002	3007	4296	java/io/FileNotFoundException
          //   3073	3078	4296	java/io/FileNotFoundException
          //   3147	3157	4296	java/io/FileNotFoundException
          //   3223	3231	4296	java/io/FileNotFoundException
          //   3314	3320	4296	java/io/FileNotFoundException
          //   3386	3393	4296	java/io/FileNotFoundException
          //   3463	3489	4296	java/io/FileNotFoundException
          //   3556	3563	4296	java/io/FileNotFoundException
          //   3630	3638	4296	java/io/FileNotFoundException
          //   3705	3712	4296	java/io/FileNotFoundException
          //   3786	3794	4296	java/io/FileNotFoundException
          //   3861	3872	4296	java/io/FileNotFoundException
          //   3939	3944	4296	java/io/FileNotFoundException
          //   4060	4068	4296	java/io/FileNotFoundException
          //   4135	4140	4296	java/io/FileNotFoundException
          //   4286	4293	4296	java/io/FileNotFoundException
          //   4844	4852	4296	java/io/FileNotFoundException
          //   4918	4923	4296	java/io/FileNotFoundException
          //   4989	4992	4296	java/io/FileNotFoundException
          //   5324	5332	4296	java/io/FileNotFoundException
          //   5398	5403	4296	java/io/FileNotFoundException
          //   5469	5475	4296	java/io/FileNotFoundException
          //   5541	5544	4296	java/io/FileNotFoundException
          //   5621	5658	4296	java/io/FileNotFoundException
          //   5724	5730	4296	java/io/FileNotFoundException
          //   5796	5822	4296	java/io/FileNotFoundException
          //   5888	5918	4296	java/io/FileNotFoundException
          //   6024	6030	4296	java/io/FileNotFoundException
          //   6096	6099	4296	java/io/FileNotFoundException
          //   6176	6181	4296	java/io/FileNotFoundException
          //   6315	6318	4296	java/io/FileNotFoundException
          //   6508	6511	4296	java/io/FileNotFoundException
          //   6819	6825	4296	java/io/FileNotFoundException
          //   6891	6894	4296	java/io/FileNotFoundException
          //   6971	6976	4296	java/io/FileNotFoundException
          //   7042	7045	4296	java/io/FileNotFoundException
          //   7203	7209	4296	java/io/FileNotFoundException
          //   7275	7278	4296	java/io/FileNotFoundException
          //   7355	7360	4296	java/io/FileNotFoundException
          //   7426	7435	4296	java/io/FileNotFoundException
          //   7501	7524	4296	java/io/FileNotFoundException
          //   7590	7596	4296	java/io/FileNotFoundException
          //   7662	7669	4296	java/io/FileNotFoundException
          //   7735	7757	4296	java/io/FileNotFoundException
          //   7891	7894	4296	java/io/FileNotFoundException
          //   7965	7968	4296	java/io/FileNotFoundException
          //   4148	4162	4449	finally
          //   4451	4454	4449	finally
          //   3950	3957	4776	finally
          //   3964	3970	4776	finally
          //   3977	3980	4776	finally
          //   4481	4488	4776	finally
          //   4495	4501	4776	finally
          //   4507	4522	4776	finally
          //   4528	4533	4776	finally
          //   4539	4553	4776	finally
          //   4575	4582	4776	finally
          //   4588	4597	4776	finally
          //   4623	4666	4776	finally
          //   4672	4687	4776	finally
          //   4693	4706	4776	finally
          //   4712	4719	4776	finally
          //   4725	4742	4776	finally
          //   4748	4754	4776	finally
          //   4760	4769	4776	finally
          //   5212	5215	4776	finally
          //   5228	5235	4776	finally
          //   5241	5247	4776	finally
          //   5253	5258	4776	finally
          //   138	147	4992	java/io/IOException
          //   213	222	4992	java/io/IOException
          //   288	301	4992	java/io/IOException
          //   375	382	4992	java/io/IOException
          //   456	463	4992	java/io/IOException
          //   529	536	4992	java/io/IOException
          //   602	609	4992	java/io/IOException
          //   675	682	4992	java/io/IOException
          //   748	756	4992	java/io/IOException
          //   822	828	4992	java/io/IOException
          //   894	900	4992	java/io/IOException
          //   966	972	4992	java/io/IOException
          //   1038	1047	4992	java/io/IOException
          //   1113	1120	4992	java/io/IOException
          //   1186	1198	4992	java/io/IOException
          //   1272	1281	4992	java/io/IOException
          //   1347	1360	4992	java/io/IOException
          //   1431	1440	4992	java/io/IOException
          //   1506	1513	4992	java/io/IOException
          //   1579	1588	4992	java/io/IOException
          //   1654	1663	4992	java/io/IOException
          //   1717	1726	4992	java/io/IOException
          //   1780	1790	4992	java/io/IOException
          //   1844	1853	4992	java/io/IOException
          //   1907	1920	4992	java/io/IOException
          //   1974	1992	4992	java/io/IOException
          //   2046	2067	4992	java/io/IOException
          //   2121	2134	4992	java/io/IOException
          //   2188	2206	4992	java/io/IOException
          //   2260	2268	4992	java/io/IOException
          //   2339	2352	4992	java/io/IOException
          //   2418	2436	4992	java/io/IOException
          //   2502	2522	4992	java/io/IOException
          //   2588	2613	4992	java/io/IOException
          //   2679	2704	4992	java/io/IOException
          //   2770	2782	4992	java/io/IOException
          //   2848	2857	4992	java/io/IOException
          //   2923	2936	4992	java/io/IOException
          //   3002	3007	4992	java/io/IOException
          //   3073	3078	4992	java/io/IOException
          //   3147	3157	4992	java/io/IOException
          //   3223	3231	4992	java/io/IOException
          //   3314	3320	4992	java/io/IOException
          //   3386	3393	4992	java/io/IOException
          //   3463	3489	4992	java/io/IOException
          //   3556	3563	4992	java/io/IOException
          //   3630	3638	4992	java/io/IOException
          //   3705	3712	4992	java/io/IOException
          //   3786	3794	4992	java/io/IOException
          //   3861	3872	4992	java/io/IOException
          //   3939	3944	4992	java/io/IOException
          //   4060	4068	4992	java/io/IOException
          //   4135	4140	4992	java/io/IOException
          //   4286	4293	4992	java/io/IOException
          //   4844	4852	4992	java/io/IOException
          //   4918	4923	4992	java/io/IOException
          //   4989	4992	4992	java/io/IOException
          //   5324	5332	4992	java/io/IOException
          //   5398	5403	4992	java/io/IOException
          //   5469	5475	4992	java/io/IOException
          //   5541	5544	4992	java/io/IOException
          //   5621	5658	4992	java/io/IOException
          //   5724	5730	4992	java/io/IOException
          //   5796	5822	4992	java/io/IOException
          //   5888	5918	4992	java/io/IOException
          //   6024	6030	4992	java/io/IOException
          //   6096	6099	4992	java/io/IOException
          //   6176	6181	4992	java/io/IOException
          //   6315	6318	4992	java/io/IOException
          //   6508	6511	4992	java/io/IOException
          //   6819	6825	4992	java/io/IOException
          //   6891	6894	4992	java/io/IOException
          //   6971	6976	4992	java/io/IOException
          //   7042	7045	4992	java/io/IOException
          //   7203	7209	4992	java/io/IOException
          //   7275	7278	4992	java/io/IOException
          //   7355	7360	4992	java/io/IOException
          //   7426	7435	4992	java/io/IOException
          //   7501	7524	4992	java/io/IOException
          //   7590	7596	4992	java/io/IOException
          //   7662	7669	4992	java/io/IOException
          //   7735	7757	4992	java/io/IOException
          //   7891	7894	4992	java/io/IOException
          //   7965	7968	4992	java/io/IOException
          //   3980	3993	5200	finally
          //   4457	4469	5200	finally
          //   5202	5205	5200	finally
          //   5544	5555	6244	finally
          //   6246	6249	6244	finally
          //   138	147	6318	org/json/JSONException
          //   213	222	6318	org/json/JSONException
          //   288	301	6318	org/json/JSONException
          //   375	382	6318	org/json/JSONException
          //   456	463	6318	org/json/JSONException
          //   529	536	6318	org/json/JSONException
          //   602	609	6318	org/json/JSONException
          //   675	682	6318	org/json/JSONException
          //   748	756	6318	org/json/JSONException
          //   822	828	6318	org/json/JSONException
          //   894	900	6318	org/json/JSONException
          //   966	972	6318	org/json/JSONException
          //   1038	1047	6318	org/json/JSONException
          //   1113	1120	6318	org/json/JSONException
          //   1186	1198	6318	org/json/JSONException
          //   1272	1281	6318	org/json/JSONException
          //   1347	1360	6318	org/json/JSONException
          //   1431	1440	6318	org/json/JSONException
          //   1506	1513	6318	org/json/JSONException
          //   1579	1588	6318	org/json/JSONException
          //   1654	1663	6318	org/json/JSONException
          //   2339	2352	6318	org/json/JSONException
          //   2418	2436	6318	org/json/JSONException
          //   2502	2522	6318	org/json/JSONException
          //   2588	2613	6318	org/json/JSONException
          //   2679	2704	6318	org/json/JSONException
          //   2770	2782	6318	org/json/JSONException
          //   2848	2857	6318	org/json/JSONException
          //   2923	2936	6318	org/json/JSONException
          //   3002	3007	6318	org/json/JSONException
          //   3073	3078	6318	org/json/JSONException
          //   3147	3157	6318	org/json/JSONException
          //   3223	3231	6318	org/json/JSONException
          //   3314	3320	6318	org/json/JSONException
          //   3386	3393	6318	org/json/JSONException
          //   3463	3489	6318	org/json/JSONException
          //   3556	3563	6318	org/json/JSONException
          //   3630	3638	6318	org/json/JSONException
          //   3705	3712	6318	org/json/JSONException
          //   3786	3794	6318	org/json/JSONException
          //   3861	3872	6318	org/json/JSONException
          //   3939	3944	6318	org/json/JSONException
          //   4060	4068	6318	org/json/JSONException
          //   4135	4140	6318	org/json/JSONException
          //   4286	4293	6318	org/json/JSONException
          //   4844	4852	6318	org/json/JSONException
          //   4918	4923	6318	org/json/JSONException
          //   4989	4992	6318	org/json/JSONException
          //   5324	5332	6318	org/json/JSONException
          //   5398	5403	6318	org/json/JSONException
          //   5469	5475	6318	org/json/JSONException
          //   5541	5544	6318	org/json/JSONException
          //   5621	5658	6318	org/json/JSONException
          //   5724	5730	6318	org/json/JSONException
          //   5796	5822	6318	org/json/JSONException
          //   5888	5918	6318	org/json/JSONException
          //   6024	6030	6318	org/json/JSONException
          //   6096	6099	6318	org/json/JSONException
          //   6176	6181	6318	org/json/JSONException
          //   6315	6318	6318	org/json/JSONException
          //   6508	6511	6318	org/json/JSONException
          //   6819	6825	6318	org/json/JSONException
          //   6891	6894	6318	org/json/JSONException
          //   6971	6976	6318	org/json/JSONException
          //   7042	7045	6318	org/json/JSONException
          //   7203	7209	6318	org/json/JSONException
          //   7275	7278	6318	org/json/JSONException
          //   7355	7360	6318	org/json/JSONException
          //   7426	7435	6318	org/json/JSONException
          //   7501	7524	6318	org/json/JSONException
          //   7590	7596	6318	org/json/JSONException
          //   7662	7669	6318	org/json/JSONException
          //   7735	7757	6318	org/json/JSONException
          //   7891	7894	6318	org/json/JSONException
          //   7965	7968	6318	org/json/JSONException
          //   6099	6110	6437	finally
          //   6439	6442	6437	finally
          //   138	147	6511	java/lang/Throwable
          //   213	222	6511	java/lang/Throwable
          //   288	301	6511	java/lang/Throwable
          //   375	382	6511	java/lang/Throwable
          //   456	463	6511	java/lang/Throwable
          //   529	536	6511	java/lang/Throwable
          //   602	609	6511	java/lang/Throwable
          //   675	682	6511	java/lang/Throwable
          //   748	756	6511	java/lang/Throwable
          //   822	828	6511	java/lang/Throwable
          //   894	900	6511	java/lang/Throwable
          //   966	972	6511	java/lang/Throwable
          //   1038	1047	6511	java/lang/Throwable
          //   1113	1120	6511	java/lang/Throwable
          //   1186	1198	6511	java/lang/Throwable
          //   1272	1281	6511	java/lang/Throwable
          //   1347	1360	6511	java/lang/Throwable
          //   1431	1440	6511	java/lang/Throwable
          //   1506	1513	6511	java/lang/Throwable
          //   1579	1588	6511	java/lang/Throwable
          //   1654	1663	6511	java/lang/Throwable
          //   1717	1726	6511	java/lang/Throwable
          //   1780	1790	6511	java/lang/Throwable
          //   1844	1853	6511	java/lang/Throwable
          //   1907	1920	6511	java/lang/Throwable
          //   1974	1992	6511	java/lang/Throwable
          //   2046	2067	6511	java/lang/Throwable
          //   2121	2134	6511	java/lang/Throwable
          //   2188	2206	6511	java/lang/Throwable
          //   2260	2268	6511	java/lang/Throwable
          //   2339	2352	6511	java/lang/Throwable
          //   2418	2436	6511	java/lang/Throwable
          //   2502	2522	6511	java/lang/Throwable
          //   2588	2613	6511	java/lang/Throwable
          //   2679	2704	6511	java/lang/Throwable
          //   2770	2782	6511	java/lang/Throwable
          //   2848	2857	6511	java/lang/Throwable
          //   2923	2936	6511	java/lang/Throwable
          //   3002	3007	6511	java/lang/Throwable
          //   3073	3078	6511	java/lang/Throwable
          //   3147	3157	6511	java/lang/Throwable
          //   3223	3231	6511	java/lang/Throwable
          //   3314	3320	6511	java/lang/Throwable
          //   3386	3393	6511	java/lang/Throwable
          //   3463	3489	6511	java/lang/Throwable
          //   3556	3563	6511	java/lang/Throwable
          //   3630	3638	6511	java/lang/Throwable
          //   3705	3712	6511	java/lang/Throwable
          //   3786	3794	6511	java/lang/Throwable
          //   3861	3872	6511	java/lang/Throwable
          //   3939	3944	6511	java/lang/Throwable
          //   4060	4068	6511	java/lang/Throwable
          //   4135	4140	6511	java/lang/Throwable
          //   4286	4293	6511	java/lang/Throwable
          //   4844	4852	6511	java/lang/Throwable
          //   4918	4923	6511	java/lang/Throwable
          //   4989	4992	6511	java/lang/Throwable
          //   5324	5332	6511	java/lang/Throwable
          //   5398	5403	6511	java/lang/Throwable
          //   5469	5475	6511	java/lang/Throwable
          //   5541	5544	6511	java/lang/Throwable
          //   5621	5658	6511	java/lang/Throwable
          //   5724	5730	6511	java/lang/Throwable
          //   5796	5822	6511	java/lang/Throwable
          //   5888	5918	6511	java/lang/Throwable
          //   6024	6030	6511	java/lang/Throwable
          //   6096	6099	6511	java/lang/Throwable
          //   6176	6181	6511	java/lang/Throwable
          //   6315	6318	6511	java/lang/Throwable
          //   6508	6511	6511	java/lang/Throwable
          //   6819	6825	6511	java/lang/Throwable
          //   6891	6894	6511	java/lang/Throwable
          //   6971	6976	6511	java/lang/Throwable
          //   7042	7045	6511	java/lang/Throwable
          //   7203	7209	6511	java/lang/Throwable
          //   7275	7278	6511	java/lang/Throwable
          //   7355	7360	6511	java/lang/Throwable
          //   7426	7435	6511	java/lang/Throwable
          //   7501	7524	6511	java/lang/Throwable
          //   7590	7596	6511	java/lang/Throwable
          //   7662	7669	6511	java/lang/Throwable
          //   7735	7757	6511	java/lang/Throwable
          //   7891	7894	6511	java/lang/Throwable
          //   7965	7968	6511	java/lang/Throwable
          //   6189	6203	6664	finally
          //   6666	6669	6664	finally
          //   5921	5928	6751	finally
          //   5932	5938	6751	finally
          //   5942	5945	6751	finally
          //   6688	6708	6751	finally
          //   6712	6719	6751	finally
          //   6723	6731	6751	finally
          //   6739	6748	6751	finally
          //   7121	7124	6751	finally
          //   7128	7137	6751	finally
          //   138	147	7045	finally
          //   213	222	7045	finally
          //   288	301	7045	finally
          //   375	382	7045	finally
          //   456	463	7045	finally
          //   529	536	7045	finally
          //   602	609	7045	finally
          //   675	682	7045	finally
          //   748	756	7045	finally
          //   822	828	7045	finally
          //   894	900	7045	finally
          //   966	972	7045	finally
          //   1038	1047	7045	finally
          //   1113	1120	7045	finally
          //   1186	1198	7045	finally
          //   1272	1281	7045	finally
          //   1347	1360	7045	finally
          //   1431	1440	7045	finally
          //   1506	1513	7045	finally
          //   1579	1588	7045	finally
          //   1654	1663	7045	finally
          //   1717	1726	7045	finally
          //   1780	1790	7045	finally
          //   1844	1853	7045	finally
          //   1907	1920	7045	finally
          //   1974	1992	7045	finally
          //   2046	2067	7045	finally
          //   2121	2134	7045	finally
          //   2188	2206	7045	finally
          //   2260	2268	7045	finally
          //   2339	2352	7045	finally
          //   2418	2436	7045	finally
          //   2502	2522	7045	finally
          //   2588	2613	7045	finally
          //   2679	2704	7045	finally
          //   2770	2782	7045	finally
          //   2848	2857	7045	finally
          //   2923	2936	7045	finally
          //   3002	3007	7045	finally
          //   3073	3078	7045	finally
          //   3147	3157	7045	finally
          //   3223	3231	7045	finally
          //   3314	3320	7045	finally
          //   3386	3393	7045	finally
          //   3463	3489	7045	finally
          //   3556	3563	7045	finally
          //   3630	3638	7045	finally
          //   3705	3712	7045	finally
          //   3786	3794	7045	finally
          //   3861	3872	7045	finally
          //   3939	3944	7045	finally
          //   4060	4068	7045	finally
          //   4135	4140	7045	finally
          //   4286	4293	7045	finally
          //   4310	4330	7045	finally
          //   4342	4355	7045	finally
          //   4367	4386	7045	finally
          //   4844	4852	7045	finally
          //   4918	4923	7045	finally
          //   4989	4992	7045	finally
          //   5006	5026	7045	finally
          //   5038	5051	7045	finally
          //   5063	5106	7045	finally
          //   5118	5137	7045	finally
          //   5324	5332	7045	finally
          //   5398	5403	7045	finally
          //   5469	5475	7045	finally
          //   5541	5544	7045	finally
          //   5621	5658	7045	finally
          //   5724	5730	7045	finally
          //   5796	5822	7045	finally
          //   5888	5918	7045	finally
          //   6024	6030	7045	finally
          //   6096	6099	7045	finally
          //   6176	6181	7045	finally
          //   6315	6318	7045	finally
          //   6332	6345	7045	finally
          //   6357	6374	7045	finally
          //   6508	6511	7045	finally
          //   6525	6545	7045	finally
          //   6557	6570	7045	finally
          //   6582	6601	7045	finally
          //   6819	6825	7045	finally
          //   6891	6894	7045	finally
          //   6971	6976	7045	finally
          //   7042	7045	7045	finally
          //   7203	7209	7045	finally
          //   7275	7278	7045	finally
          //   7355	7360	7045	finally
          //   7426	7435	7045	finally
          //   7501	7524	7045	finally
          //   7590	7596	7045	finally
          //   7662	7669	7045	finally
          //   7735	7757	7045	finally
          //   7891	7894	7045	finally
          //   7965	7968	7045	finally
          //   5945	5958	7112	finally
          //   6672	6684	7112	finally
          //   7114	7117	7112	finally
          //   7278	7289	7820	finally
          //   7822	7825	7820	finally
          //   6894	6905	7894	finally
          //   7896	7899	7894	finally
          //   7765	7779	7968	finally
          //   7970	7973	7968	finally
          //   4394	4408	7976	finally
          //   7978	7981	7976	finally
          //   5145	5159	7984	finally
          //   7986	7989	7984	finally
          //   6382	6396	7992	finally
          //   7994	7997	7992	finally
          //   6609	6623	8000	finally
          //   8002	8005	8000	finally
          //   7055	7069	8008	finally
          //   8010	8013	8008	finally
        }
      });
      return;
    }
  }
  
  public boolean execute(String paramString, JSONArray paramJSONArray, CallbackContext paramCallbackContext)
    throws JSONException
  {
    if ((paramString.equals("upload")) || (paramString.equals("download")))
    {
      String str1 = paramJSONArray.getString(0);
      String str2 = paramJSONArray.getString(1);
      if (paramString.equals("upload"))
      {
        upload(str1, str2, paramJSONArray, paramCallbackContext);
        return true;
      }
      download(str1, str2, paramJSONArray, paramCallbackContext);
      return true;
    }
    if (paramString.equals("abort"))
    {
      abort(paramJSONArray.getString(0));
      paramCallbackContext.success();
      return true;
    }
    return false;
  }
  
  private static class ExposedGZIPInputStream
    extends GZIPInputStream
  {
    public ExposedGZIPInputStream(InputStream paramInputStream)
      throws IOException
    {
      super();
    }
    
    public Inflater getInflater()
    {
      return this.inf;
    }
  }
  
  private static final class RequestContext
  {
    boolean aborted;
    CallbackContext callbackContext;
    HttpURLConnection connection;
    String source;
    String target;
    File targetFile;
    
    RequestContext(String paramString1, String paramString2, CallbackContext paramCallbackContext)
    {
      this.source = paramString1;
      this.target = paramString2;
      this.callbackContext = paramCallbackContext;
    }
    
    void sendPluginResult(PluginResult paramPluginResult)
    {
      try
      {
        if (!this.aborted) {
          this.callbackContext.sendPluginResult(paramPluginResult);
        }
        return;
      }
      finally {}
    }
  }
  
  private static class SimpleTrackingInputStream
    extends FileTransfer.TrackingInputStream
  {
    private long bytesRead = 0L;
    
    public SimpleTrackingInputStream(InputStream paramInputStream)
    {
      super();
    }
    
    private int updateBytesRead(int paramInt)
    {
      if (paramInt != -1) {
        this.bytesRead += paramInt;
      }
      return paramInt;
    }
    
    public long getTotalRawBytesRead()
    {
      return this.bytesRead;
    }
    
    public int read()
      throws IOException
    {
      return updateBytesRead(super.read());
    }
    
    public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      throws IOException
    {
      return updateBytesRead(super.read(paramArrayOfByte, paramInt1, paramInt2));
    }
  }
  
  private static class TrackingGZIPInputStream
    extends FileTransfer.TrackingInputStream
  {
    private FileTransfer.ExposedGZIPInputStream gzin;
    
    public TrackingGZIPInputStream(FileTransfer.ExposedGZIPInputStream paramExposedGZIPInputStream)
      throws IOException
    {
      super();
      this.gzin = paramExposedGZIPInputStream;
    }
    
    public long getTotalRawBytesRead()
    {
      return this.gzin.getInflater().getBytesRead();
    }
  }
  
  private static abstract class TrackingInputStream
    extends FilterInputStream
  {
    public TrackingInputStream(InputStream paramInputStream)
    {
      super();
    }
    
    public abstract long getTotalRawBytesRead();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\cordova\filetransfer\FileTransfer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */