package com.google.android.gms.analytics;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Locale;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;

class ah
  implements n
{
  private final Context mContext;
  private GoogleAnalytics rA;
  private final String ul;
  private final HttpClient um;
  private URL un;
  
  ah(HttpClient paramHttpClient, Context paramContext)
  {
    this(paramHttpClient, GoogleAnalytics.getInstance(paramContext), paramContext);
  }
  
  ah(HttpClient paramHttpClient, GoogleAnalytics paramGoogleAnalytics, Context paramContext)
  {
    this.mContext = paramContext.getApplicationContext();
    this.ul = a("GoogleAnalytics", "3.0", Build.VERSION.RELEASE, ak.a(Locale.getDefault()), Build.MODEL, Build.ID);
    this.um = paramHttpClient;
    this.rA = paramGoogleAnalytics;
  }
  
  /* Error */
  private void a(ab paramab, URL paramURL, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual 90	com/google/android/gms/analytics/ab:cn	()Ljava/lang/String;
    //   4: invokestatic 96	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   7: ifeq +4 -> 11
    //   10: return
    //   11: aload_2
    //   12: ifnonnull +231 -> 243
    //   15: aload_0
    //   16: getfield 98	com/google/android/gms/analytics/ah:un	Ljava/net/URL;
    //   19: ifnull +172 -> 191
    //   22: aload_0
    //   23: getfield 98	com/google/android/gms/analytics/ah:un	Ljava/net/URL;
    //   26: astore_2
    //   27: new 100	org/apache/http/HttpHost
    //   30: dup
    //   31: aload_2
    //   32: invokevirtual 105	java/net/URL:getHost	()Ljava/lang/String;
    //   35: aload_2
    //   36: invokevirtual 109	java/net/URL:getPort	()I
    //   39: aload_2
    //   40: invokevirtual 112	java/net/URL:getProtocol	()Ljava/lang/String;
    //   43: invokespecial 115	org/apache/http/HttpHost:<init>	(Ljava/lang/String;ILjava/lang/String;)V
    //   46: astore 5
    //   48: aload_0
    //   49: aload_1
    //   50: invokevirtual 90	com/google/android/gms/analytics/ab:cn	()Ljava/lang/String;
    //   53: aload_2
    //   54: invokevirtual 118	java/net/URL:getPath	()Ljava/lang/String;
    //   57: invokespecial 122	com/google/android/gms/analytics/ah:c	(Ljava/lang/String;Ljava/lang/String;)Lorg/apache/http/HttpEntityEnclosingRequest;
    //   60: astore_1
    //   61: aload_1
    //   62: ifnull -52 -> 10
    //   65: aload_1
    //   66: ldc 124
    //   68: aload 5
    //   70: invokevirtual 127	org/apache/http/HttpHost:toHostString	()Ljava/lang/String;
    //   73: invokeinterface 133 3 0
    //   78: invokestatic 139	com/google/android/gms/analytics/aa:cm	()Z
    //   81: ifeq +8 -> 89
    //   84: aload_0
    //   85: aload_1
    //   86: invokespecial 142	com/google/android/gms/analytics/ah:a	(Lorg/apache/http/HttpEntityEnclosingRequest;)V
    //   89: iload_3
    //   90: ifeq +10 -> 100
    //   93: aload_0
    //   94: getfield 39	com/google/android/gms/analytics/ah:mContext	Landroid/content/Context;
    //   97: invokestatic 148	com/google/android/gms/analytics/q:p	(Landroid/content/Context;)V
    //   100: aload_0
    //   101: getfield 75	com/google/android/gms/analytics/ah:um	Lorg/apache/http/client/HttpClient;
    //   104: aload 5
    //   106: aload_1
    //   107: invokeinterface 154 3 0
    //   112: astore_1
    //   113: aload_1
    //   114: invokeinterface 160 1 0
    //   119: invokeinterface 165 1 0
    //   124: istore 4
    //   126: aload_1
    //   127: invokeinterface 169 1 0
    //   132: astore_2
    //   133: aload_2
    //   134: ifnull +9 -> 143
    //   137: aload_2
    //   138: invokeinterface 174 1 0
    //   143: iload 4
    //   145: sipush 200
    //   148: if_icmpeq -138 -> 10
    //   151: new 176	java/lang/StringBuilder
    //   154: dup
    //   155: invokespecial 177	java/lang/StringBuilder:<init>	()V
    //   158: ldc -77
    //   160: invokevirtual 183	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   163: aload_1
    //   164: invokeinterface 160 1 0
    //   169: invokeinterface 165 1 0
    //   174: invokevirtual 186	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   177: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   180: invokestatic 193	com/google/android/gms/analytics/aa:w	(Ljava/lang/String;)V
    //   183: return
    //   184: astore_1
    //   185: ldc -61
    //   187: invokestatic 193	com/google/android/gms/analytics/aa:w	(Ljava/lang/String;)V
    //   190: return
    //   191: new 102	java/net/URL
    //   194: dup
    //   195: ldc -59
    //   197: invokespecial 199	java/net/URL:<init>	(Ljava/lang/String;)V
    //   200: astore_2
    //   201: goto -174 -> 27
    //   204: astore_1
    //   205: return
    //   206: astore_1
    //   207: new 176	java/lang/StringBuilder
    //   210: dup
    //   211: invokespecial 177	java/lang/StringBuilder:<init>	()V
    //   214: ldc -55
    //   216: invokevirtual 183	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   219: aload_1
    //   220: invokevirtual 205	java/lang/Object:getClass	()Ljava/lang/Class;
    //   223: invokevirtual 210	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   226: invokevirtual 183	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   229: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   232: invokestatic 193	com/google/android/gms/analytics/aa:w	(Ljava/lang/String;)V
    //   235: aload_1
    //   236: invokevirtual 213	java/io/IOException:getMessage	()Ljava/lang/String;
    //   239: invokestatic 193	com/google/android/gms/analytics/aa:w	(Ljava/lang/String;)V
    //   242: return
    //   243: goto -216 -> 27
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	246	0	this	ah
    //   0	246	1	paramab	ab
    //   0	246	2	paramURL	URL
    //   0	246	3	paramBoolean	boolean
    //   124	25	4	i	int
    //   46	59	5	localHttpHost	HttpHost
    // Exception table:
    //   from	to	target	type
    //   48	61	184	org/apache/http/client/ClientProtocolException
    //   65	89	184	org/apache/http/client/ClientProtocolException
    //   93	100	184	org/apache/http/client/ClientProtocolException
    //   100	133	184	org/apache/http/client/ClientProtocolException
    //   137	143	184	org/apache/http/client/ClientProtocolException
    //   151	183	184	org/apache/http/client/ClientProtocolException
    //   15	27	204	java/net/MalformedURLException
    //   191	201	204	java/net/MalformedURLException
    //   48	61	206	java/io/IOException
    //   65	89	206	java/io/IOException
    //   93	100	206	java/io/IOException
    //   100	133	206	java/io/IOException
    //   137	143	206	java/io/IOException
    //   151	183	206	java/io/IOException
  }
  
  private void a(HttpEntityEnclosingRequest paramHttpEntityEnclosingRequest)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    Object localObject = paramHttpEntityEnclosingRequest.getAllHeaders();
    int j = localObject.length;
    int i = 0;
    while (i < j)
    {
      localStringBuffer.append(localObject[i].toString()).append("\n");
      i += 1;
    }
    localStringBuffer.append(paramHttpEntityEnclosingRequest.getRequestLine().toString()).append("\n");
    if (paramHttpEntityEnclosingRequest.getEntity() != null) {}
    try
    {
      paramHttpEntityEnclosingRequest = paramHttpEntityEnclosingRequest.getEntity().getContent();
      if (paramHttpEntityEnclosingRequest != null)
      {
        i = paramHttpEntityEnclosingRequest.available();
        if (i > 0)
        {
          localObject = new byte[i];
          paramHttpEntityEnclosingRequest.read((byte[])localObject);
          localStringBuffer.append("POST:\n");
          localStringBuffer.append(new String((byte[])localObject)).append("\n");
        }
      }
    }
    catch (IOException paramHttpEntityEnclosingRequest)
    {
      for (;;)
      {
        aa.v("Error Writing hit to log...");
      }
    }
    aa.v(localStringBuffer.toString());
  }
  
  private HttpEntityEnclosingRequest c(String paramString1, String paramString2)
  {
    if (TextUtils.isEmpty(paramString1))
    {
      aa.w("Empty hit, discarding.");
      return null;
    }
    String str = paramString2 + "?" + paramString1;
    if (str.length() < 2036) {
      paramString1 = new BasicHttpEntityEnclosingRequest("GET", str);
    }
    for (;;)
    {
      paramString1.addHeader("User-Agent", this.ul);
      return paramString1;
      paramString2 = new BasicHttpEntityEnclosingRequest("POST", paramString2);
      try
      {
        paramString2.setEntity(new StringEntity(paramString1));
        paramString1 = paramString2;
      }
      catch (UnsupportedEncodingException paramString1)
      {
        aa.w("Encoding error, discarding hit");
      }
    }
    return null;
  }
  
  public void A(String paramString)
  {
    try
    {
      this.un = new URL(paramString);
      return;
    }
    catch (MalformedURLException paramString)
    {
      this.un = null;
    }
  }
  
  public int a(List<x> paramList, ab paramab, boolean paramBoolean)
  {
    int m = 0;
    int n = Math.min(paramList.size(), 40);
    paramab.c("_hr", paramList.size());
    int i = 0;
    Object localObject1 = null;
    boolean bool1 = true;
    int k = 0;
    if (k < n)
    {
      x localx = (x)paramList.get(k);
      URL localURL = a(localx);
      localx.ch().contains("_t=flow");
      int j;
      if (localURL == null)
      {
        if (aa.cm()) {
          aa.w("No destination: discarding hit: " + localx.ch());
        }
        for (;;)
        {
          i += 1;
          j = m + 1;
          k += 1;
          m = j;
          break;
          aa.w("No destination: discarding hit.");
        }
      }
      Object localObject2 = new HttpHost(localURL.getHost(), localURL.getPort(), localURL.getProtocol());
      Object localObject3 = localURL.getPath();
      if (TextUtils.isEmpty(localx.ch())) {}
      for (localObject1 = "";; localObject1 = y.a(localx, System.currentTimeMillis()))
      {
        localObject3 = c((String)localObject1, (String)localObject3);
        if (localObject3 != null) {
          break label247;
        }
        i += 1;
        j = m + 1;
        localObject1 = localURL;
        break;
      }
      label247:
      ((HttpEntityEnclosingRequest)localObject3).addHeader("Host", ((HttpHost)localObject2).toHostString());
      if (aa.cm()) {
        a((HttpEntityEnclosingRequest)localObject3);
      }
      if (((String)localObject1).length() > 8192)
      {
        aa.w("Hit too long (> 8192 bytes)--not sent");
        j = i + 1;
      }
      for (;;)
      {
        paramab.c("_td", ((String)localObject1).getBytes().length);
        m += 1;
        localObject1 = localURL;
        i = j;
        j = m;
        break;
        if (this.rA.isDryRunEnabled())
        {
          aa.u("Dry run enabled. Hit not actually sent.");
          j = i;
        }
        else
        {
          boolean bool2 = bool1;
          boolean bool3;
          if (bool1) {
            bool3 = bool1;
          }
          try
          {
            q.p(this.mContext);
            bool2 = false;
            bool3 = bool2;
            bool1 = bool2;
            localObject2 = this.um.execute((HttpHost)localObject2, (HttpRequest)localObject3);
            bool3 = bool2;
            bool1 = bool2;
            int i1 = ((HttpResponse)localObject2).getStatusLine().getStatusCode();
            bool3 = bool2;
            bool1 = bool2;
            localObject3 = ((HttpResponse)localObject2).getEntity();
            if (localObject3 != null)
            {
              bool3 = bool2;
              bool1 = bool2;
              ((HttpEntity)localObject3).consumeContent();
            }
            bool1 = bool2;
            j = i;
            if (i1 != 200)
            {
              bool3 = bool2;
              bool1 = bool2;
              aa.w("Bad response: " + ((HttpResponse)localObject2).getStatusLine().getStatusCode());
              bool1 = bool2;
              j = i;
            }
          }
          catch (ClientProtocolException localClientProtocolException)
          {
            aa.w("ClientProtocolException sending hit; discarding hit...");
            paramab.c("_hd", i);
            bool1 = bool3;
            j = i;
          }
          catch (IOException paramList)
          {
            aa.w("Exception sending hit: " + paramList.getClass().getSimpleName());
            aa.w(paramList.getMessage());
            paramab.c("_de", 1);
            paramab.c("_hd", i);
            paramab.c("_hs", m);
            a(paramab, localURL, bool1);
            return m;
          }
        }
      }
    }
    paramab.c("_hd", i);
    paramab.c("_hs", m);
    if (paramBoolean) {
      a(paramab, (URL)localObject1, bool1);
    }
    return m;
  }
  
  String a(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6)
  {
    return String.format("%s/%s (Linux; U; Android %s; %s; %s Build/%s)", new Object[] { paramString1, paramString2, paramString3, paramString4, paramString5, paramString6 });
  }
  
  URL a(x paramx)
  {
    if (this.un != null) {
      return this.un;
    }
    for (paramx = paramx.ck();; paramx = "https://ssl.google-analytics.com/collect") {
      try
      {
        if ("http:".equals(paramx))
        {
          paramx = "http://www.google-analytics.com/collect";
          paramx = new URL(paramx);
          return paramx;
        }
      }
      catch (MalformedURLException paramx)
      {
        aa.t("Error trying to parse the hardcoded host url. This really shouldn't happen.");
        return null;
      }
    }
  }
  
  public boolean bA()
  {
    NetworkInfo localNetworkInfo = ((ConnectivityManager)this.mContext.getSystemService("connectivity")).getActiveNetworkInfo();
    if ((localNetworkInfo == null) || (!localNetworkInfo.isConnected()))
    {
      aa.v("...no network connectivity");
      return false;
    }
    return true;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\analytics\ah.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */