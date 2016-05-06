package org.apache.cordova;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import java.io.PrintStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import org.json.JSONException;

public class PluginManager
{
  private static final int SLOW_EXEC_WARNING_THRESHOLD;
  private static String TAG = "PluginManager";
  private final CordovaWebView app;
  private final CordovaInterface ctx;
  private final LinkedHashMap<String, PluginEntry> entryMap = new LinkedHashMap();
  private boolean isInitialized;
  private CordovaPlugin permissionRequester;
  private final LinkedHashMap<String, CordovaPlugin> pluginMap = new LinkedHashMap();
  
  static
  {
    if (Debug.isDebuggerConnected()) {}
    for (int i = 60;; i = 16)
    {
      SLOW_EXEC_WARNING_THRESHOLD = i;
      return;
    }
  }
  
  public PluginManager(CordovaWebView paramCordovaWebView, CordovaInterface paramCordovaInterface, Collection<PluginEntry> paramCollection)
  {
    this.ctx = paramCordovaInterface;
    this.app = paramCordovaWebView;
    setPluginEntries(paramCollection);
  }
  
  private CordovaPlugin instantiatePlugin(String paramString)
  {
    CordovaPlugin localCordovaPlugin = null;
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (paramString != null) {
      localObject1 = localObject2;
    }
    for (;;)
    {
      try
      {
        if ("".equals(paramString)) {
          break label95;
        }
        localObject1 = Class.forName(paramString);
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        System.out.println("Error adding plugin " + paramString + ".");
        return null;
      }
      if ((i & CordovaPlugin.class.isAssignableFrom((Class)localObject1)) != 0) {
        localCordovaPlugin = (CordovaPlugin)((Class)localObject1).newInstance();
      }
      return localCordovaPlugin;
      int i = 0;
      continue;
      label95:
      if (localException != null) {
        i = 1;
      }
    }
  }
  
  private void startupPlugins()
  {
    Iterator localIterator = this.entryMap.values().iterator();
    for (;;)
    {
      if (!localIterator.hasNext()) {
        return;
      }
      PluginEntry localPluginEntry = (PluginEntry)localIterator.next();
      if (localPluginEntry.onload) {
        getPlugin(localPluginEntry.service);
      } else {
        this.pluginMap.put(localPluginEntry.service, null);
      }
    }
  }
  
  public void addService(String paramString1, String paramString2)
  {
    addService(new PluginEntry(paramString1, paramString2, false));
  }
  
  public void addService(PluginEntry paramPluginEntry)
  {
    this.entryMap.put(paramPluginEntry.service, paramPluginEntry);
    if (paramPluginEntry.plugin != null)
    {
      paramPluginEntry.plugin.privateInitialize(paramPluginEntry.service, this.ctx, this.app, this.app.getPreferences());
      this.pluginMap.put(paramPluginEntry.service, paramPluginEntry.plugin);
    }
  }
  
  public void exec(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    CordovaPlugin localCordovaPlugin = getPlugin(paramString1);
    if (localCordovaPlugin == null)
    {
      Log.d(TAG, "exec() call to unknown plugin: " + paramString1);
      paramString1 = new PluginResult(PluginResult.Status.CLASS_NOT_FOUND_EXCEPTION);
      this.app.sendPluginResult(paramString1, paramString3);
    }
    for (;;)
    {
      return;
      paramString3 = new CallbackContext(paramString3, this.app);
      try
      {
        long l = System.currentTimeMillis();
        boolean bool = localCordovaPlugin.execute(paramString2, paramString4, paramString3);
        l = System.currentTimeMillis() - l;
        if (l > SLOW_EXEC_WARNING_THRESHOLD) {
          Log.w(TAG, "THREAD WARNING: exec() call to " + paramString1 + "." + paramString2 + " blocked the main thread for " + l + "ms. Plugin should use CordovaInterface.getThreadPool().");
        }
        if (!bool)
        {
          paramString3.sendPluginResult(new PluginResult(PluginResult.Status.INVALID_ACTION));
          return;
        }
      }
      catch (JSONException paramString1)
      {
        paramString3.sendPluginResult(new PluginResult(PluginResult.Status.JSON_EXCEPTION));
        return;
      }
      catch (Exception paramString1)
      {
        Log.e(TAG, "Uncaught exception from plugin", paramString1);
        paramString3.error(paramString1.getMessage());
      }
    }
  }
  
  public CordovaPlugin getPlugin(String paramString)
  {
    CordovaPlugin localCordovaPlugin = (CordovaPlugin)this.pluginMap.get(paramString);
    Object localObject = localCordovaPlugin;
    if (localCordovaPlugin == null)
    {
      localObject = (PluginEntry)this.entryMap.get(paramString);
      if (localObject == null) {
        return null;
      }
      if (((PluginEntry)localObject).plugin == null) {
        break label82;
      }
    }
    label82:
    for (localObject = ((PluginEntry)localObject).plugin;; localObject = instantiatePlugin(((PluginEntry)localObject).pluginClass))
    {
      ((CordovaPlugin)localObject).privateInitialize(paramString, this.ctx, this.app, this.app.getPreferences());
      this.pluginMap.put(paramString, localObject);
      return (CordovaPlugin)localObject;
    }
  }
  
  public Collection<PluginEntry> getPluginEntries()
  {
    return this.entryMap.values();
  }
  
  public void init()
  {
    LOG.d(TAG, "init()");
    this.isInitialized = true;
    onPause(false);
    onDestroy();
    this.pluginMap.clear();
    startupPlugins();
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    Iterator localIterator = this.pluginMap.values().iterator();
    for (;;)
    {
      if (!localIterator.hasNext()) {
        return;
      }
      CordovaPlugin localCordovaPlugin = (CordovaPlugin)localIterator.next();
      if (localCordovaPlugin != null) {
        localCordovaPlugin.onConfigurationChanged(paramConfiguration);
      }
    }
  }
  
  public void onDestroy()
  {
    Iterator localIterator = this.pluginMap.values().iterator();
    for (;;)
    {
      if (!localIterator.hasNext()) {
        return;
      }
      CordovaPlugin localCordovaPlugin = (CordovaPlugin)localIterator.next();
      if (localCordovaPlugin != null) {
        localCordovaPlugin.onDestroy();
      }
    }
  }
  
  public void onNewIntent(Intent paramIntent)
  {
    Iterator localIterator = this.pluginMap.values().iterator();
    for (;;)
    {
      if (!localIterator.hasNext()) {
        return;
      }
      CordovaPlugin localCordovaPlugin = (CordovaPlugin)localIterator.next();
      if (localCordovaPlugin != null) {
        localCordovaPlugin.onNewIntent(paramIntent);
      }
    }
  }
  
  public boolean onOverrideUrlLoading(String paramString)
  {
    Iterator localIterator = this.entryMap.values().iterator();
    Object localObject;
    do
    {
      if (!localIterator.hasNext()) {
        return false;
      }
      localObject = (PluginEntry)localIterator.next();
      localObject = (CordovaPlugin)this.pluginMap.get(((PluginEntry)localObject).service);
    } while ((localObject == null) || (!((CordovaPlugin)localObject).onOverrideUrlLoading(paramString)));
    return true;
  }
  
  public void onPause(boolean paramBoolean)
  {
    Iterator localIterator = this.pluginMap.values().iterator();
    for (;;)
    {
      if (!localIterator.hasNext()) {
        return;
      }
      CordovaPlugin localCordovaPlugin = (CordovaPlugin)localIterator.next();
      if (localCordovaPlugin != null) {
        localCordovaPlugin.onPause(paramBoolean);
      }
    }
  }
  
  public boolean onReceivedClientCertRequest(CordovaWebView paramCordovaWebView, ICordovaClientCertRequest paramICordovaClientCertRequest)
  {
    paramCordovaWebView = this.pluginMap.values().iterator();
    CordovaPlugin localCordovaPlugin;
    do
    {
      if (!paramCordovaWebView.hasNext()) {
        return false;
      }
      localCordovaPlugin = (CordovaPlugin)paramCordovaWebView.next();
    } while ((localCordovaPlugin == null) || (!localCordovaPlugin.onReceivedClientCertRequest(this.app, paramICordovaClientCertRequest)));
    return true;
  }
  
  public boolean onReceivedHttpAuthRequest(CordovaWebView paramCordovaWebView, ICordovaHttpAuthHandler paramICordovaHttpAuthHandler, String paramString1, String paramString2)
  {
    paramCordovaWebView = this.pluginMap.values().iterator();
    CordovaPlugin localCordovaPlugin;
    do
    {
      if (!paramCordovaWebView.hasNext()) {
        return false;
      }
      localCordovaPlugin = (CordovaPlugin)paramCordovaWebView.next();
    } while ((localCordovaPlugin == null) || (!localCordovaPlugin.onReceivedHttpAuthRequest(this.app, paramICordovaHttpAuthHandler, paramString1, paramString2)));
    return true;
  }
  
  public void onReset()
  {
    Iterator localIterator = this.pluginMap.values().iterator();
    for (;;)
    {
      if (!localIterator.hasNext()) {
        return;
      }
      CordovaPlugin localCordovaPlugin = (CordovaPlugin)localIterator.next();
      if (localCordovaPlugin != null) {
        localCordovaPlugin.onReset();
      }
    }
  }
  
  public void onResume(boolean paramBoolean)
  {
    Iterator localIterator = this.pluginMap.values().iterator();
    for (;;)
    {
      if (!localIterator.hasNext()) {
        return;
      }
      CordovaPlugin localCordovaPlugin = (CordovaPlugin)localIterator.next();
      if (localCordovaPlugin != null) {
        localCordovaPlugin.onResume(paramBoolean);
      }
    }
  }
  
  public Bundle onSaveInstanceState()
  {
    Bundle localBundle1 = new Bundle();
    Iterator localIterator = this.pluginMap.values().iterator();
    for (;;)
    {
      if (!localIterator.hasNext()) {
        return localBundle1;
      }
      CordovaPlugin localCordovaPlugin = (CordovaPlugin)localIterator.next();
      if (localCordovaPlugin != null)
      {
        Bundle localBundle2 = localCordovaPlugin.onSaveInstanceState();
        if (localBundle2 != null) {
          localBundle1.putBundle(localCordovaPlugin.getServiceName(), localBundle2);
        }
      }
    }
  }
  
  public void onStart()
  {
    Iterator localIterator = this.pluginMap.values().iterator();
    for (;;)
    {
      if (!localIterator.hasNext()) {
        return;
      }
      CordovaPlugin localCordovaPlugin = (CordovaPlugin)localIterator.next();
      if (localCordovaPlugin != null) {
        localCordovaPlugin.onStart();
      }
    }
  }
  
  public void onStop()
  {
    Iterator localIterator = this.pluginMap.values().iterator();
    for (;;)
    {
      if (!localIterator.hasNext()) {
        return;
      }
      CordovaPlugin localCordovaPlugin = (CordovaPlugin)localIterator.next();
      if (localCordovaPlugin != null) {
        localCordovaPlugin.onStop();
      }
    }
  }
  
  public Object postMessage(String paramString, Object paramObject)
  {
    Iterator localIterator = this.pluginMap.values().iterator();
    Object localObject;
    do
    {
      do
      {
        if (!localIterator.hasNext()) {
          return this.ctx.onMessage(paramString, paramObject);
        }
        localObject = (CordovaPlugin)localIterator.next();
      } while (localObject == null);
      localObject = ((CordovaPlugin)localObject).onMessage(paramString, paramObject);
    } while (localObject == null);
    return localObject;
  }
  
  Uri remapUri(Uri paramUri)
  {
    Iterator localIterator = this.pluginMap.values().iterator();
    Object localObject;
    do
    {
      do
      {
        if (!localIterator.hasNext()) {
          return null;
        }
        localObject = (CordovaPlugin)localIterator.next();
      } while (localObject == null);
      localObject = ((CordovaPlugin)localObject).remapUri(paramUri);
    } while (localObject == null);
    return (Uri)localObject;
  }
  
  public void setPluginEntries(Collection<PluginEntry> paramCollection)
  {
    if (this.isInitialized)
    {
      onPause(false);
      onDestroy();
      this.pluginMap.clear();
      this.entryMap.clear();
    }
    paramCollection = paramCollection.iterator();
    for (;;)
    {
      if (!paramCollection.hasNext())
      {
        if (this.isInitialized) {
          startupPlugins();
        }
        return;
      }
      addService((PluginEntry)paramCollection.next());
    }
  }
  
  public boolean shouldAllowBridgeAccess(String paramString)
  {
    Iterator localIterator = this.entryMap.values().iterator();
    Object localObject;
    do
    {
      do
      {
        if (!localIterator.hasNext()) {
          return paramString.startsWith("file://");
        }
        localObject = (PluginEntry)localIterator.next();
        localObject = (CordovaPlugin)this.pluginMap.get(((PluginEntry)localObject).service);
      } while (localObject == null);
      localObject = ((CordovaPlugin)localObject).shouldAllowBridgeAccess(paramString);
    } while (localObject == null);
    return ((Boolean)localObject).booleanValue();
  }
  
  public boolean shouldAllowNavigation(String paramString)
  {
    Iterator localIterator = this.entryMap.values().iterator();
    Object localObject;
    do
    {
      do
      {
        if (!localIterator.hasNext())
        {
          if ((paramString.startsWith("file://")) || (paramString.startsWith("about:blank"))) {
            break;
          }
          return false;
        }
        localObject = (PluginEntry)localIterator.next();
        localObject = (CordovaPlugin)this.pluginMap.get(((PluginEntry)localObject).service);
      } while (localObject == null);
      localObject = ((CordovaPlugin)localObject).shouldAllowNavigation(paramString);
    } while (localObject == null);
    return ((Boolean)localObject).booleanValue();
    return true;
  }
  
  public boolean shouldAllowRequest(String paramString)
  {
    boolean bool = false;
    Iterator localIterator = this.entryMap.values().iterator();
    if (!localIterator.hasNext()) {
      if ((!paramString.startsWith("blob:")) && (!paramString.startsWith("data:")) && (!paramString.startsWith("about:blank"))) {
        break label108;
      }
    }
    label108:
    while (paramString.startsWith("https://ssl.gstatic.com/accessibility/javascript/android/"))
    {
      return true;
      Object localObject = (PluginEntry)localIterator.next();
      localObject = (CordovaPlugin)this.pluginMap.get(((PluginEntry)localObject).service);
      if (localObject == null) {
        break;
      }
      localObject = ((CordovaPlugin)localObject).shouldAllowRequest(paramString);
      if (localObject == null) {
        break;
      }
      return ((Boolean)localObject).booleanValue();
    }
    if (paramString.startsWith("file://"))
    {
      if (paramString.contains("/app_webview/")) {}
      for (;;)
      {
        return bool;
        bool = true;
      }
    }
    return false;
  }
  
  public Boolean shouldOpenExternalUrl(String paramString)
  {
    Iterator localIterator = this.entryMap.values().iterator();
    Object localObject;
    do
    {
      do
      {
        if (!localIterator.hasNext()) {
          return Boolean.valueOf(false);
        }
        localObject = (PluginEntry)localIterator.next();
        localObject = (CordovaPlugin)this.pluginMap.get(((PluginEntry)localObject).service);
      } while (localObject == null);
      localObject = ((CordovaPlugin)localObject).shouldOpenExternalUrl(paramString);
    } while (localObject == null);
    return (Boolean)localObject;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\cordova\PluginManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */