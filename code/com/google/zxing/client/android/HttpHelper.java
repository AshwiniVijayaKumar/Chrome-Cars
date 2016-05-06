package com.google.zxing.client.android;

import java.io.IOException;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

public final class HttpHelper
{
  private static final Collection<String> REDIRECTOR_DOMAINS = new HashSet(Arrays.asList(new String[] { "amzn.to", "bit.ly", "bitly.com", "fb.me", "goo.gl", "is.gd", "j.mp", "lnkd.in", "ow.ly", "R.BEETAGG.COM", "r.beetagg.com", "SCN.BY", "su.pr", "t.co", "tinyurl.com", "tr.im" }));
  private static final String TAG = HttpHelper.class.getSimpleName();
  
  /* Error */
  private static CharSequence consume(URLConnection paramURLConnection, int paramInt)
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 85	com/google/zxing/client/android/HttpHelper:getEncoding	(Ljava/net/URLConnection;)Ljava/lang/String;
    //   4: astore 5
    //   6: new 87	java/lang/StringBuilder
    //   9: dup
    //   10: invokespecial 88	java/lang/StringBuilder:<init>	()V
    //   13: astore 4
    //   15: aconst_null
    //   16: astore_3
    //   17: new 90	java/io/InputStreamReader
    //   20: dup
    //   21: aload_0
    //   22: invokevirtual 96	java/net/URLConnection:getInputStream	()Ljava/io/InputStream;
    //   25: aload 5
    //   27: invokespecial 99	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;Ljava/lang/String;)V
    //   30: astore_0
    //   31: sipush 1024
    //   34: newarray <illegal type>
    //   36: astore_3
    //   37: aload 4
    //   39: invokevirtual 103	java/lang/StringBuilder:length	()I
    //   42: iload_1
    //   43: if_icmpge +42 -> 85
    //   46: aload_0
    //   47: aload_3
    //   48: invokevirtual 109	java/io/Reader:read	([C)I
    //   51: istore_2
    //   52: iload_2
    //   53: ifle +32 -> 85
    //   56: aload 4
    //   58: aload_3
    //   59: iconst_0
    //   60: iload_2
    //   61: invokevirtual 113	java/lang/StringBuilder:append	([CII)Ljava/lang/StringBuilder;
    //   64: pop
    //   65: goto -28 -> 37
    //   68: astore 4
    //   70: aload_0
    //   71: astore_3
    //   72: aload 4
    //   74: astore_0
    //   75: aload_3
    //   76: ifnull +7 -> 83
    //   79: aload_3
    //   80: invokevirtual 116	java/io/Reader:close	()V
    //   83: aload_0
    //   84: athrow
    //   85: aload_0
    //   86: ifnull +7 -> 93
    //   89: aload_0
    //   90: invokevirtual 116	java/io/Reader:close	()V
    //   93: aload 4
    //   95: areturn
    //   96: astore_0
    //   97: aload 4
    //   99: areturn
    //   100: astore_0
    //   101: aload 4
    //   103: areturn
    //   104: astore_3
    //   105: goto -22 -> 83
    //   108: astore_3
    //   109: goto -26 -> 83
    //   112: astore_0
    //   113: goto -38 -> 75
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	116	0	paramURLConnection	URLConnection
    //   0	116	1	paramInt	int
    //   51	10	2	i	int
    //   16	64	3	localObject	Object
    //   104	1	3	localIOException	IOException
    //   108	1	3	localNullPointerException	NullPointerException
    //   13	44	4	localStringBuilder	StringBuilder
    //   68	34	4	localCharSequence	CharSequence
    //   4	22	5	str	String
    // Exception table:
    //   from	to	target	type
    //   31	37	68	finally
    //   37	52	68	finally
    //   56	65	68	finally
    //   89	93	96	java/io/IOException
    //   89	93	100	java/lang/NullPointerException
    //   79	83	104	java/io/IOException
    //   79	83	108	java/lang/NullPointerException
    //   17	31	112	finally
  }
  
  public static CharSequence downloadViaHttp(String paramString, ContentType paramContentType)
    throws IOException
  {
    return downloadViaHttp(paramString, paramContentType, Integer.MAX_VALUE);
  }
  
  public static CharSequence downloadViaHttp(String paramString, ContentType paramContentType, int paramInt)
    throws IOException
  {
    switch (paramContentType)
    {
    default: 
      paramContentType = "text/*,*/*";
    }
    for (;;)
    {
      return downloadViaHttp(paramString, paramContentType, paramInt);
      paramContentType = "application/xhtml+xml,text/html,text/*,*/*";
      continue;
      paramContentType = "application/json,text/*,*/*";
    }
  }
  
  /* Error */
  private static CharSequence downloadViaHttp(String paramString1, String paramString2, int paramInt)
    throws IOException
  {
    // Byte code:
    //   0: getstatic 24	com/google/zxing/client/android/HttpHelper:TAG	Ljava/lang/String;
    //   3: new 87	java/lang/StringBuilder
    //   6: dup
    //   7: invokespecial 88	java/lang/StringBuilder:<init>	()V
    //   10: ldc -113
    //   12: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   15: aload_0
    //   16: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   19: invokevirtual 149	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   22: invokestatic 155	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   25: pop
    //   26: new 157	java/net/URL
    //   29: dup
    //   30: aload_0
    //   31: invokespecial 160	java/net/URL:<init>	(Ljava/lang/String;)V
    //   34: invokevirtual 164	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   37: astore 4
    //   39: aload 4
    //   41: instanceof 166
    //   44: ifne +11 -> 55
    //   47: new 79	java/io/IOException
    //   50: dup
    //   51: invokespecial 167	java/io/IOException:<init>	()V
    //   54: athrow
    //   55: aload 4
    //   57: checkcast 166	java/net/HttpURLConnection
    //   60: astore 4
    //   62: aload 4
    //   64: ldc -87
    //   66: aload_1
    //   67: invokevirtual 173	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   70: aload 4
    //   72: ldc -81
    //   74: ldc -79
    //   76: invokevirtual 173	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   79: aload 4
    //   81: ldc -77
    //   83: ldc -75
    //   85: invokevirtual 173	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   88: aload 4
    //   90: invokevirtual 184	java/net/HttpURLConnection:connect	()V
    //   93: aload 4
    //   95: invokevirtual 187	java/net/HttpURLConnection:getResponseCode	()I
    //   98: istore_3
    //   99: iload_3
    //   100: sipush 200
    //   103: if_icmpeq +146 -> 249
    //   106: new 79	java/io/IOException
    //   109: dup
    //   110: new 87	java/lang/StringBuilder
    //   113: dup
    //   114: invokespecial 88	java/lang/StringBuilder:<init>	()V
    //   117: ldc -67
    //   119: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   122: iload_3
    //   123: invokevirtual 192	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   126: invokevirtual 149	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   129: invokespecial 193	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   132: athrow
    //   133: astore_0
    //   134: aload 4
    //   136: invokevirtual 196	java/net/HttpURLConnection:disconnect	()V
    //   139: aload_0
    //   140: athrow
    //   141: astore_1
    //   142: getstatic 24	com/google/zxing/client/android/HttpHelper:TAG	Ljava/lang/String;
    //   145: new 87	java/lang/StringBuilder
    //   148: dup
    //   149: invokespecial 88	java/lang/StringBuilder:<init>	()V
    //   152: ldc -58
    //   154: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   157: aload_0
    //   158: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   161: invokevirtual 149	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   164: invokestatic 201	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   167: pop
    //   168: new 79	java/io/IOException
    //   171: dup
    //   172: aload_1
    //   173: invokespecial 204	java/io/IOException:<init>	(Ljava/lang/Throwable;)V
    //   176: athrow
    //   177: astore_1
    //   178: getstatic 24	com/google/zxing/client/android/HttpHelper:TAG	Ljava/lang/String;
    //   181: new 87	java/lang/StringBuilder
    //   184: dup
    //   185: invokespecial 88	java/lang/StringBuilder:<init>	()V
    //   188: ldc -58
    //   190: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   193: aload_0
    //   194: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   197: invokevirtual 149	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   200: invokestatic 201	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   203: pop
    //   204: new 79	java/io/IOException
    //   207: dup
    //   208: aload_1
    //   209: invokespecial 204	java/io/IOException:<init>	(Ljava/lang/Throwable;)V
    //   212: athrow
    //   213: astore_1
    //   214: getstatic 24	com/google/zxing/client/android/HttpHelper:TAG	Ljava/lang/String;
    //   217: new 87	java/lang/StringBuilder
    //   220: dup
    //   221: invokespecial 88	java/lang/StringBuilder:<init>	()V
    //   224: ldc -58
    //   226: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   229: aload_0
    //   230: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   233: invokevirtual 149	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   236: invokestatic 201	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   239: pop
    //   240: new 79	java/io/IOException
    //   243: dup
    //   244: aload_1
    //   245: invokespecial 204	java/io/IOException:<init>	(Ljava/lang/Throwable;)V
    //   248: athrow
    //   249: getstatic 24	com/google/zxing/client/android/HttpHelper:TAG	Ljava/lang/String;
    //   252: new 87	java/lang/StringBuilder
    //   255: dup
    //   256: invokespecial 88	java/lang/StringBuilder:<init>	()V
    //   259: ldc -50
    //   261: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   264: aload_0
    //   265: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   268: invokevirtual 149	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   271: invokestatic 155	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   274: pop
    //   275: aload 4
    //   277: iload_2
    //   278: invokestatic 208	com/google/zxing/client/android/HttpHelper:consume	(Ljava/net/URLConnection;I)Ljava/lang/CharSequence;
    //   281: astore_0
    //   282: aload 4
    //   284: invokevirtual 196	java/net/HttpURLConnection:disconnect	()V
    //   287: aload_0
    //   288: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	289	0	paramString1	String
    //   0	289	1	paramString2	String
    //   0	289	2	paramInt	int
    //   98	25	3	i	int
    //   37	246	4	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   88	93	133	finally
    //   93	99	133	finally
    //   106	133	133	finally
    //   142	177	133	finally
    //   178	213	133	finally
    //   214	249	133	finally
    //   249	282	133	finally
    //   88	93	141	java/lang/NullPointerException
    //   88	93	177	java/lang/IllegalArgumentException
    //   93	99	213	java/lang/NullPointerException
  }
  
  private static String getEncoding(URLConnection paramURLConnection)
  {
    paramURLConnection = paramURLConnection.getHeaderField("Content-Type");
    if (paramURLConnection != null)
    {
      int i = paramURLConnection.indexOf("charset=");
      if (i >= 0) {
        return paramURLConnection.substring("charset=".length() + i);
      }
    }
    return "UTF-8";
  }
  
  /* Error */
  public static java.net.URI unredirect(java.net.URI paramURI)
    throws IOException
  {
    // Byte code:
    //   0: getstatic 72	com/google/zxing/client/android/HttpHelper:REDIRECTOR_DOMAINS	Ljava/util/Collection;
    //   3: aload_0
    //   4: invokevirtual 236	java/net/URI:getHost	()Ljava/lang/String;
    //   7: invokeinterface 242 2 0
    //   12: ifne +5 -> 17
    //   15: aload_0
    //   16: areturn
    //   17: aload_0
    //   18: invokevirtual 246	java/net/URI:toURL	()Ljava/net/URL;
    //   21: invokevirtual 164	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   24: astore_2
    //   25: aload_2
    //   26: instanceof 166
    //   29: ifne +11 -> 40
    //   32: new 79	java/io/IOException
    //   35: dup
    //   36: invokespecial 167	java/io/IOException:<init>	()V
    //   39: athrow
    //   40: aload_2
    //   41: checkcast 166	java/net/HttpURLConnection
    //   44: astore_2
    //   45: aload_2
    //   46: iconst_0
    //   47: invokevirtual 250	java/net/HttpURLConnection:setInstanceFollowRedirects	(Z)V
    //   50: aload_2
    //   51: iconst_0
    //   52: invokevirtual 253	java/net/HttpURLConnection:setDoInput	(Z)V
    //   55: aload_2
    //   56: ldc -1
    //   58: invokevirtual 258	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
    //   61: aload_2
    //   62: ldc -77
    //   64: ldc -75
    //   66: invokevirtual 173	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   69: aload_2
    //   70: invokevirtual 184	java/net/HttpURLConnection:connect	()V
    //   73: aload_2
    //   74: invokevirtual 187	java/net/HttpURLConnection:getResponseCode	()I
    //   77: istore_1
    //   78: iload_1
    //   79: tableswitch	default:+45->124, 300:+166->245, 301:+166->245, 302:+166->245, 303:+166->245, 304:+45->124, 305:+45->124, 306:+45->124, 307:+166->245
    //   124: aload_2
    //   125: invokevirtual 196	java/net/HttpURLConnection:disconnect	()V
    //   128: aload_0
    //   129: areturn
    //   130: astore_3
    //   131: getstatic 24	com/google/zxing/client/android/HttpHelper:TAG	Ljava/lang/String;
    //   134: new 87	java/lang/StringBuilder
    //   137: dup
    //   138: invokespecial 88	java/lang/StringBuilder:<init>	()V
    //   141: ldc -58
    //   143: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   146: aload_0
    //   147: invokevirtual 261	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   150: invokevirtual 149	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   153: invokestatic 201	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   156: pop
    //   157: new 79	java/io/IOException
    //   160: dup
    //   161: aload_3
    //   162: invokespecial 204	java/io/IOException:<init>	(Ljava/lang/Throwable;)V
    //   165: athrow
    //   166: astore_0
    //   167: aload_2
    //   168: invokevirtual 196	java/net/HttpURLConnection:disconnect	()V
    //   171: aload_0
    //   172: athrow
    //   173: astore_3
    //   174: getstatic 24	com/google/zxing/client/android/HttpHelper:TAG	Ljava/lang/String;
    //   177: new 87	java/lang/StringBuilder
    //   180: dup
    //   181: invokespecial 88	java/lang/StringBuilder:<init>	()V
    //   184: ldc -58
    //   186: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   189: aload_0
    //   190: invokevirtual 261	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   193: invokevirtual 149	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   196: invokestatic 201	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   199: pop
    //   200: new 79	java/io/IOException
    //   203: dup
    //   204: aload_3
    //   205: invokespecial 204	java/io/IOException:<init>	(Ljava/lang/Throwable;)V
    //   208: athrow
    //   209: astore_3
    //   210: getstatic 24	com/google/zxing/client/android/HttpHelper:TAG	Ljava/lang/String;
    //   213: new 87	java/lang/StringBuilder
    //   216: dup
    //   217: invokespecial 88	java/lang/StringBuilder:<init>	()V
    //   220: ldc -58
    //   222: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   225: aload_0
    //   226: invokevirtual 261	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   229: invokevirtual 149	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   232: invokestatic 201	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   235: pop
    //   236: new 79	java/io/IOException
    //   239: dup
    //   240: aload_3
    //   241: invokespecial 204	java/io/IOException:<init>	(Ljava/lang/Throwable;)V
    //   244: athrow
    //   245: aload_2
    //   246: ldc_w 263
    //   249: invokevirtual 264	java/net/HttpURLConnection:getHeaderField	(Ljava/lang/String;)Ljava/lang/String;
    //   252: astore_3
    //   253: aload_3
    //   254: ifnull -130 -> 124
    //   257: new 233	java/net/URI
    //   260: dup
    //   261: aload_3
    //   262: invokespecial 265	java/net/URI:<init>	(Ljava/lang/String;)V
    //   265: astore_3
    //   266: aload_2
    //   267: invokevirtual 196	java/net/HttpURLConnection:disconnect	()V
    //   270: aload_3
    //   271: areturn
    //   272: astore_3
    //   273: goto -149 -> 124
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	276	0	paramURI	java.net.URI
    //   77	2	1	i	int
    //   24	243	2	localObject1	Object
    //   130	32	3	localNullPointerException1	NullPointerException
    //   173	32	3	localIllegalArgumentException	IllegalArgumentException
    //   209	32	3	localNullPointerException2	NullPointerException
    //   252	19	3	localObject2	Object
    //   272	1	3	localURISyntaxException	java.net.URISyntaxException
    // Exception table:
    //   from	to	target	type
    //   69	73	130	java/lang/NullPointerException
    //   69	73	166	finally
    //   73	78	166	finally
    //   131	166	166	finally
    //   174	209	166	finally
    //   210	245	166	finally
    //   245	253	166	finally
    //   257	266	166	finally
    //   69	73	173	java/lang/IllegalArgumentException
    //   73	78	209	java/lang/NullPointerException
    //   257	266	272	java/net/URISyntaxException
  }
  
  public static enum ContentType
  {
    HTML,  JSON,  TEXT;
    
    private ContentType() {}
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\HttpHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */