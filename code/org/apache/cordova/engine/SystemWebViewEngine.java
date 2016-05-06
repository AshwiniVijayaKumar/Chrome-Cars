package org.apache.cordova.engine;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.apache.cordova.CordovaBridge;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPreferences;
import org.apache.cordova.CordovaResourceApi;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CordovaWebViewEngine;
import org.apache.cordova.CordovaWebViewEngine.Client;
import org.apache.cordova.ICordovaCookieManager;
import org.apache.cordova.NativeToJsMessageQueue;
import org.apache.cordova.NativeToJsMessageQueue.OnlineEventsBridgeMode;
import org.apache.cordova.NativeToJsMessageQueue.OnlineEventsBridgeMode.OnlineEventsBridgeModeDelegate;
import org.apache.cordova.PluginManager;

public class SystemWebViewEngine
  implements CordovaWebViewEngine
{
  public static final String TAG = "SystemWebViewEngine";
  protected CordovaBridge bridge;
  protected CordovaWebViewEngine.Client client;
  protected final SystemCookieManager cookieManager;
  protected CordovaInterface cordova;
  protected NativeToJsMessageQueue nativeToJsMessageQueue;
  protected CordovaWebView parentWebView;
  protected PluginManager pluginManager;
  protected CordovaPreferences preferences;
  private BroadcastReceiver receiver;
  protected CordovaResourceApi resourceApi;
  protected final SystemWebView webView;
  
  public SystemWebViewEngine(Context paramContext, CordovaPreferences paramCordovaPreferences)
  {
    this(new SystemWebView(paramContext), paramCordovaPreferences);
  }
  
  public SystemWebViewEngine(SystemWebView paramSystemWebView)
  {
    this(paramSystemWebView, null);
  }
  
  public SystemWebViewEngine(SystemWebView paramSystemWebView, CordovaPreferences paramCordovaPreferences)
  {
    this.preferences = paramCordovaPreferences;
    this.webView = paramSystemWebView;
    this.cookieManager = new SystemCookieManager(paramSystemWebView);
  }
  
  @TargetApi(19)
  private void enableRemoteDebugging()
  {
    try
    {
      WebView.setWebContentsDebuggingEnabled(true);
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      Log.d("SystemWebViewEngine", "You have one job! To turn on Remote Web Debugging! YOU HAVE FAILED! ");
      localIllegalArgumentException.printStackTrace();
    }
  }
  
  private static void exposeJsInterface(WebView paramWebView, CordovaBridge paramCordovaBridge)
  {
    if (Build.VERSION.SDK_INT < 17)
    {
      Log.i("SystemWebViewEngine", "Disabled addJavascriptInterface() bridge since Android version is old.");
      return;
    }
    paramWebView.addJavascriptInterface(new SystemExposedJsApi(paramCordovaBridge), "_cordovaNative");
  }
  
  @SuppressLint({"NewApi", "SetJavaScriptEnabled"})
  private void initWebViewSettings()
  {
    this.webView.setInitialScale(0);
    this.webView.setVerticalScrollBarEnabled(false);
    final WebSettings localWebSettings = this.webView.getSettings();
    localWebSettings.setJavaScriptEnabled(true);
    localWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
    localWebSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
    try
    {
      Object localObject = WebSettings.class.getMethod("setNavDump", new Class[] { Boolean.TYPE });
      str = Build.MANUFACTURER;
      Log.d("SystemWebViewEngine", "CordovaWebView is running on device made by: " + str);
      if ((Build.VERSION.SDK_INT < 11) && (Build.MANUFACTURER.contains("HTC"))) {
        ((Method)localObject).invoke(localWebSettings, new Object[] { Boolean.valueOf(true) });
      }
      localWebSettings.setSaveFormData(false);
      localWebSettings.setSavePassword(false);
      if (Build.VERSION.SDK_INT >= 16) {
        localWebSettings.setAllowUniversalAccessFromFileURLs(true);
      }
      if (Build.VERSION.SDK_INT >= 17) {
        localWebSettings.setMediaPlaybackRequiresUserGesture(false);
      }
      localObject = this.webView.getContext().getApplicationContext().getDir("database", 0).getPath();
      localWebSettings.setDatabaseEnabled(true);
      localWebSettings.setDatabasePath((String)localObject);
      if (((this.webView.getContext().getApplicationContext().getApplicationInfo().flags & 0x2) != 0) && (Build.VERSION.SDK_INT >= 19)) {
        enableRemoteDebugging();
      }
      localWebSettings.setGeolocationDatabasePath((String)localObject);
      localWebSettings.setDomStorageEnabled(true);
      localWebSettings.setGeolocationEnabled(true);
      localWebSettings.setAppCacheMaxSize(5242880L);
      localWebSettings.setAppCachePath((String)localObject);
      localWebSettings.setAppCacheEnabled(true);
      localObject = localWebSettings.getUserAgentString();
      str = this.preferences.getString("OverrideUserAgent", null);
      if (str != null)
      {
        localWebSettings.setUserAgentString(str);
        localObject = new IntentFilter();
        ((IntentFilter)localObject).addAction("android.intent.action.CONFIGURATION_CHANGED");
        if (this.receiver == null)
        {
          this.receiver = new BroadcastReceiver()
          {
            public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
            {
              localWebSettings.getUserAgentString();
            }
          };
          this.webView.getContext().registerReceiver(this.receiver, (IntentFilter)localObject);
        }
        return;
      }
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      for (;;)
      {
        Log.d("SystemWebViewEngine", "We are on a modern version of Android, we will deprecate HTC 2.3 devices in 2.8");
      }
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      for (;;)
      {
        Log.d("SystemWebViewEngine", "Doing the NavDump failed with bad arguments");
      }
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      for (;;)
      {
        Log.d("SystemWebViewEngine", "This should never happen: IllegalAccessException means this isn't Android anymore");
      }
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      for (;;)
      {
        Log.d("SystemWebViewEngine", "This should never happen: InvocationTargetException means this isn't Android anymore.");
        continue;
        String str = this.preferences.getString("AppendUserAgent", null);
        if (str != null) {
          localWebSettings.setUserAgentString(localInvocationTargetException + " " + str);
        }
      }
    }
  }
  
  public boolean canGoBack()
  {
    return this.webView.canGoBack();
  }
  
  public void clearCache()
  {
    this.webView.clearCache(true);
  }
  
  public void clearHistory()
  {
    this.webView.clearHistory();
  }
  
  public void destroy()
  {
    this.webView.chromeClient.destroyLastDialog();
    this.webView.destroy();
    if (this.receiver != null) {}
    try
    {
      this.webView.getContext().unregisterReceiver(this.receiver);
      return;
    }
    catch (Exception localException)
    {
      Log.e("SystemWebViewEngine", "Error unregistering configuration receiver: " + localException.getMessage(), localException);
    }
  }
  
  public ICordovaCookieManager getCookieManager()
  {
    return this.cookieManager;
  }
  
  public CordovaWebView getCordovaWebView()
  {
    return this.parentWebView;
  }
  
  public String getUrl()
  {
    return this.webView.getUrl();
  }
  
  public View getView()
  {
    return this.webView;
  }
  
  public boolean goBack()
  {
    if (this.webView.canGoBack())
    {
      this.webView.goBack();
      return true;
    }
    return false;
  }
  
  public void init(CordovaWebView paramCordovaWebView, CordovaInterface paramCordovaInterface, CordovaWebViewEngine.Client paramClient, CordovaResourceApi paramCordovaResourceApi, PluginManager paramPluginManager, NativeToJsMessageQueue paramNativeToJsMessageQueue)
  {
    if (this.cordova != null) {
      throw new IllegalStateException();
    }
    if (this.preferences == null) {
      this.preferences = paramCordovaWebView.getPreferences();
    }
    this.parentWebView = paramCordovaWebView;
    this.cordova = paramCordovaInterface;
    this.client = paramClient;
    this.resourceApi = paramCordovaResourceApi;
    this.pluginManager = paramPluginManager;
    this.nativeToJsMessageQueue = paramNativeToJsMessageQueue;
    this.webView.init(this, paramCordovaInterface);
    initWebViewSettings();
    paramNativeToJsMessageQueue.addBridgeMode(new NativeToJsMessageQueue.OnlineEventsBridgeMode(new NativeToJsMessageQueue.OnlineEventsBridgeMode.OnlineEventsBridgeModeDelegate()
    {
      public void runOnUiThread(Runnable paramAnonymousRunnable)
      {
        SystemWebViewEngine.this.cordova.getActivity().runOnUiThread(paramAnonymousRunnable);
      }
      
      public void setNetworkAvailable(boolean paramAnonymousBoolean)
      {
        SystemWebViewEngine.this.webView.setNetworkAvailable(paramAnonymousBoolean);
      }
    }));
    this.bridge = new CordovaBridge(paramPluginManager, paramNativeToJsMessageQueue);
    exposeJsInterface(this.webView, this.bridge);
  }
  
  public void loadUrl(String paramString, boolean paramBoolean)
  {
    this.webView.loadUrl(paramString);
  }
  
  public void setPaused(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.webView.pauseTimers();
      return;
    }
    this.webView.resumeTimers();
  }
  
  public void stopLoading()
  {
    this.webView.stopLoading();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\cordova\engine\SystemWebViewEngine.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */