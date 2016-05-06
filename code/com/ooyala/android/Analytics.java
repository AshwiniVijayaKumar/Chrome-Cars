package com.ooyala.android;

import android.annotation.SuppressLint;
import android.content.Context;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.ooyala.android.util.DebugMode;
import com.ooyala.android.util.TemporaryInternalStorageFile;
import com.ooyala.android.util.TemporaryInternalStorageFileManager;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

@SuppressLint({"SetJavaScriptEnabled"})
public class Analytics
{
  private static final String EMBED_MODULEPARAMS_HTML = "<html><head><script src=\"_HOST__URI_\"></script><script>function _init() {reporter = new Ooyala.Reporter('_PCODE_',_MODULE_PARAMS_);console.log('...onLoad: domain='+document.domain);};</script></script></head><body onLoad=\"_init();\"></body></html>";
  private static final String JS_ANALYTICS_ACCOUNT_ID = "accountId";
  private static final String JS_ANALYTICS_DOCUMENT_URL = "documentUrl";
  private static final String JS_ANALYTICS_GUID = "guid";
  private static final String JS_ANALYTICS_URI = "/reporter.js";
  private static final String JS_ANALYTICS_USER_AGENT = "Ooyala Android SDK %s [%s]";
  private static final String TAG = "Analytics";
  private static final String TMP_EXT = ".html";
  private static final String TMP_PREFIX = "pb2823";
  private String _defaultUserAgent = "";
  private boolean _disabled = false;
  private boolean _failed;
  private boolean _initialPlay = true;
  private WebView _jsAnalytics;
  private List<String> _queue = new ArrayList();
  private boolean _ready;
  private boolean _shouldReportPlayRequest = false;
  private boolean _shouldReportPlayStart = false;
  private String _userAgent = "";
  private TemporaryInternalStorageFileManager tmpBootHtmlFileManager = new TemporaryInternalStorageFileManager();
  
  Analytics(Context paramContext, PlayerAPIClient paramPlayerAPIClient)
  {
    this(paramContext, generateEmbedHTML(paramPlayerAPIClient, paramContext), paramPlayerAPIClient.getDomain().toString());
  }
  
  @SuppressLint({"SetJavaScriptEnabled"})
  private Analytics(Context paramContext, String paramString1, String paramString2)
  {
    this._jsAnalytics = new WebView(paramContext);
    this._defaultUserAgent = String.format("Ooyala Android SDK %s [%s]", new Object[] { OoyalaPlayer.getVersion(), this._jsAnalytics.getSettings().getUserAgentString() });
    this._userAgent = this._defaultUserAgent;
    this._jsAnalytics.getSettings().setUserAgentString(this._defaultUserAgent);
    this._jsAnalytics.getSettings().setJavaScriptEnabled(true);
    setAllowUniversalAccessFromFileURLs(this._jsAnalytics.getSettings());
    this._jsAnalytics.setWebViewClient(new WebViewClient()
    {
      public void onPageFinished(WebView paramAnonymousWebView, String paramAnonymousString)
      {
        if ((!Analytics.this._ready) && (!Analytics.this._failed))
        {
          Analytics.access$002(Analytics.this, true);
          DebugMode.logD(getClass().getName(), "Initialized Analytics.");
          Analytics.this.performQueuedActions();
        }
      }
      
      public void onReceivedError(WebView paramAnonymousWebView, int paramAnonymousInt, String paramAnonymousString1, String paramAnonymousString2)
      {
        if (!Analytics.this._failed)
        {
          Analytics.access$002(Analytics.this, false);
          Analytics.access$102(Analytics.this, true);
          DebugMode.logE(getClass().getName(), "ERROR: Failed to load js Analytics!");
        }
      }
    });
    this._jsAnalytics.setWebChromeClient(new WebChromeClient()
    {
      public void onConsoleMessage(String paramAnonymousString1, int paramAnonymousInt, String paramAnonymousString2)
      {
        DebugMode.logV("Analytics", "javascript: " + paramAnonymousString2 + "@" + paramAnonymousInt + ": " + paramAnonymousString1);
      }
      
      public boolean onConsoleMessage(ConsoleMessage paramAnonymousConsoleMessage)
      {
        onConsoleMessage(paramAnonymousConsoleMessage.message(), paramAnonymousConsoleMessage.lineNumber(), paramAnonymousConsoleMessage.sourceId());
        return true;
      }
    });
    bootHtml(paramContext, paramString2, paramString1);
    DebugMode.logD("Analytics", "Initialized Analytics with user agent: " + this._jsAnalytics.getSettings().getUserAgentString());
    reportPlayerLoad();
  }
  
  private void bootHtml(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      paramContext = this.tmpBootHtmlFileManager.next(paramContext, "pb2823", ".html");
      paramContext.write(paramString2);
      loadTmpBootHtmlFile(paramContext);
      return;
    }
    catch (IOException paramContext)
    {
      DebugMode.logE("Analytics", "failed: " + paramContext.getStackTrace());
      return;
    }
    catch (IllegalArgumentException paramContext)
    {
      DebugMode.logE("Analytics", "failed: " + paramContext.getStackTrace());
    }
  }
  
  private static String generateEmbedHTML(PlayerAPIClient paramPlayerAPIClient, Context paramContext)
  {
    HashMap localHashMap = new HashMap();
    String str2 = paramPlayerAPIClient.getDomain().toString();
    String str1 = str2;
    if (str2.length() <= 0)
    {
      str1 = "http://www.ooyala.com/analytics.html";
      DebugMode.logE("Analytics", "falling back to default analytics URL " + "http://www.ooyala.com/analytics.html");
    }
    localHashMap.put("documentUrl", str1);
    if ((paramPlayerAPIClient.getUserInfo() != null) && (paramPlayerAPIClient.getUserInfo().getAccountId() != null)) {
      localHashMap.put("accountId", paramPlayerAPIClient.getUserInfo().getAccountId());
    }
    localHashMap.put("guid", Utils.encryptString(ClientId.getId(paramContext)));
    return "<html><head><script src=\"_HOST__URI_\"></script><script>function _init() {reporter = new Ooyala.Reporter('_PCODE_',_MODULE_PARAMS_);console.log('...onLoad: domain='+document.domain);};</script></script></head><body onLoad=\"_init();\"></body></html>".replaceAll("_HOST_", Environment.JS_ANALYTICS_HOST).replaceAll("_URI_", "/reporter.js").replaceAll("_PCODE_", paramPlayerAPIClient.getPcode()).replaceAll("_MODULE_PARAMS_", new JSONObject(localHashMap).toString());
  }
  
  /* Error */
  private void loadTmpBootHtmlFile(TemporaryInternalStorageFile paramTemporaryInternalStorageFile)
  {
    // Byte code:
    //   0: new 160	java/lang/StringBuilder
    //   3: dup
    //   4: invokespecial 161	java/lang/StringBuilder:<init>	()V
    //   7: ldc_w 292
    //   10: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   13: aload_1
    //   14: invokevirtual 295	com/ooyala/android/util/TemporaryInternalStorageFile:getAbsolutePath	()Ljava/lang/String;
    //   17: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   20: invokevirtual 168	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   23: astore_2
    //   24: ldc 33
    //   26: new 160	java/lang/StringBuilder
    //   29: dup
    //   30: invokespecial 161	java/lang/StringBuilder:<init>	()V
    //   33: ldc_w 297
    //   36: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   39: aload_2
    //   40: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   43: invokevirtual 168	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   46: invokestatic 174	com/ooyala/android/util/DebugMode:logD	(Ljava/lang/String;Ljava/lang/String;)V
    //   49: new 299	java/util/Scanner
    //   52: dup
    //   53: aload_1
    //   54: invokevirtual 303	com/ooyala/android/util/TemporaryInternalStorageFile:getFile	()Ljava/io/File;
    //   57: invokespecial 306	java/util/Scanner:<init>	(Ljava/io/File;)V
    //   60: astore_1
    //   61: ldc 33
    //   63: aload_1
    //   64: invokevirtual 309	java/util/Scanner:nextLine	()Ljava/lang/String;
    //   67: invokestatic 174	com/ooyala/android/util/DebugMode:logD	(Ljava/lang/String;Ljava/lang/String;)V
    //   70: goto -9 -> 61
    //   73: astore_3
    //   74: aload_1
    //   75: invokevirtual 312	java/util/Scanner:close	()V
    //   78: aload_0
    //   79: getfield 111	com/ooyala/android/Analytics:_jsAnalytics	Landroid/webkit/WebView;
    //   82: aload_2
    //   83: invokevirtual 315	android/webkit/WebView:loadUrl	(Ljava/lang/String;)V
    //   86: return
    //   87: astore_3
    //   88: aload_1
    //   89: invokevirtual 312	java/util/Scanner:close	()V
    //   92: aload_3
    //   93: athrow
    //   94: astore_1
    //   95: goto -17 -> 78
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	98	0	this	Analytics
    //   0	98	1	paramTemporaryInternalStorageFile	TemporaryInternalStorageFile
    //   23	60	2	str	String
    //   73	1	3	localNoSuchElementException	java.util.NoSuchElementException
    //   87	6	3	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   61	70	73	java/util/NoSuchElementException
    //   61	70	87	finally
    //   49	61	94	java/io/FileNotFoundException
    //   74	78	94	java/io/FileNotFoundException
    //   88	94	94	java/io/FileNotFoundException
  }
  
  private void performQueuedActions()
  {
    Iterator localIterator = this._queue.iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      DebugMode.logI("Analytics", "reporting:" + str);
      this._jsAnalytics.loadUrl(str);
    }
    this._queue.clear();
  }
  
  private void queue(String paramString)
  {
    this._queue.add(paramString);
  }
  
  private void report(String paramString)
  {
    if ((this._failed) || (this._disabled)) {
      return;
    }
    if (!this._ready)
    {
      queue(paramString);
      return;
    }
    this._jsAnalytics.loadUrl(paramString);
  }
  
  private void reportPlayerLoad()
  {
    report("javascript:reporter.reportPlayerLoad();");
  }
  
  private static void setAllowUniversalAccessFromFileURLs(WebSettings paramWebSettings)
  {
    int i = 0;
    Method[] arrayOfMethod = paramWebSettings.getClass().getMethods();
    int j = arrayOfMethod.length;
    for (;;)
    {
      Method localMethod;
      if (i < j)
      {
        localMethod = arrayOfMethod[i];
        if (!localMethod.getName().equals("setAllowUniversalAccessFromFileURLs")) {}
      }
      else
      {
        try
        {
          localMethod.invoke(paramWebSettings, new Object[] { Boolean.valueOf(true) });
          return;
        }
        catch (Exception paramWebSettings)
        {
          DebugMode.logD("Analytics", "failed: " + paramWebSettings.getStackTrace());
          return;
        }
      }
      i += 1;
    }
  }
  
  public void disable(boolean paramBoolean)
  {
    this._disabled = paramBoolean;
  }
  
  void initializeVideo(String paramString, double paramDouble)
  {
    paramString = "javascript:reporter.initializeVideo('" + paramString + "'," + paramDouble + ");";
    this._shouldReportPlayRequest = true;
    this._shouldReportPlayStart = true;
    report(paramString);
  }
  
  public boolean isDisabled()
  {
    return this._disabled;
  }
  
  void reportPlayRequested()
  {
    if (!this._shouldReportPlayRequest) {
      return;
    }
    this._initialPlay = false;
    this._shouldReportPlayRequest = false;
    report("javascript:reporter.reportPlayStarted();");
  }
  
  void reportPlayStarted()
  {
    if (!this._shouldReportPlayStart) {
      return;
    }
    this._shouldReportPlayStart = false;
    report("javascript:reporter.reportPlayStarted();");
  }
  
  void reportPlayheadUpdate(double paramDouble)
  {
    report("javascript:reporter.reportPlayheadUpdate(" + 1000.0D * paramDouble + ");");
  }
  
  void reportReplay()
  {
    report("javascript:reporter.reportReplay();");
  }
  
  void setTags(List<String> paramList)
  {
    report("javascript:reporter.setTags([\"" + Utils.join(paramList, "\",\"") + "\"]);");
  }
  
  public void setUserAgent(String paramString)
  {
    if (paramString != null) {}
    for (this._userAgent = paramString;; this._userAgent = this._defaultUserAgent)
    {
      this._jsAnalytics.getSettings().setUserAgentString(this._userAgent);
      return;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\Analytics.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */