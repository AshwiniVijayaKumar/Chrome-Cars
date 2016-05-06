package org.apache.cordova;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.TelephonyManager;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class CoreAndroid
  extends CordovaPlugin
{
  public static final String PLUGIN_NAME = "CoreAndroid";
  protected static final String TAG = "CordovaApp";
  private CallbackContext messageChannel;
  private final Object messageChannelLock = new Object();
  private PluginResult pendingResume;
  private BroadcastReceiver telephonyReceiver;
  
  private void initTelephonyReceiver()
  {
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("android.intent.action.PHONE_STATE");
    this.telephonyReceiver = new BroadcastReceiver()
    {
      public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
      {
        if ((paramAnonymousIntent != null) && (paramAnonymousIntent.getAction().equals("android.intent.action.PHONE_STATE")) && (paramAnonymousIntent.hasExtra("state")))
        {
          paramAnonymousContext = paramAnonymousIntent.getStringExtra("state");
          if (!paramAnonymousContext.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
            break label70;
          }
          LOG.i("CordovaApp", "Telephone RINGING");
          CoreAndroid.this.webView.getPluginManager().postMessage("telephone", "ringing");
        }
        label70:
        do
        {
          return;
          if (paramAnonymousContext.equals(TelephonyManager.EXTRA_STATE_OFFHOOK))
          {
            LOG.i("CordovaApp", "Telephone OFFHOOK");
            CoreAndroid.this.webView.getPluginManager().postMessage("telephone", "offhook");
            return;
          }
        } while (!paramAnonymousContext.equals(TelephonyManager.EXTRA_STATE_IDLE));
        LOG.i("CordovaApp", "Telephone IDLE");
        CoreAndroid.this.webView.getPluginManager().postMessage("telephone", "idle");
      }
    };
    this.webView.getContext().registerReceiver(this.telephonyReceiver, localIntentFilter);
  }
  
  private void sendEventMessage(String paramString)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("action", paramString);
      sendEventMessage(new PluginResult(PluginResult.Status.OK, localJSONObject));
      return;
    }
    catch (JSONException paramString)
    {
      for (;;)
      {
        LOG.e("CordovaApp", "Failed to create event message", paramString);
      }
    }
  }
  
  private void sendEventMessage(PluginResult paramPluginResult)
  {
    paramPluginResult.setKeepCallback(true);
    if (this.messageChannel != null) {
      this.messageChannel.sendPluginResult(paramPluginResult);
    }
  }
  
  public void backHistory()
  {
    this.cordova.getActivity().runOnUiThread(new Runnable()
    {
      public void run()
      {
        CoreAndroid.this.webView.backHistory();
      }
    });
  }
  
  public void clearCache()
  {
    this.cordova.getActivity().runOnUiThread(new Runnable()
    {
      public void run()
      {
        CoreAndroid.this.webView.clearCache(true);
      }
    });
  }
  
  public void clearHistory()
  {
    this.cordova.getActivity().runOnUiThread(new Runnable()
    {
      public void run()
      {
        CoreAndroid.this.webView.clearHistory();
      }
    });
  }
  
  public boolean execute(String arg1, JSONArray paramJSONArray, CallbackContext paramCallbackContext)
    throws JSONException
  {
    PluginResult.Status localStatus = PluginResult.Status.OK;
    label110:
    do
    {
      for (;;)
      {
        try
        {
          if (???.equals("clearCache"))
          {
            clearCache();
            paramCallbackContext.sendPluginResult(new PluginResult(localStatus, ""));
            return true;
          }
          if (???.equals("show"))
          {
            this.cordova.getActivity().runOnUiThread(new Runnable()
            {
              public void run()
              {
                CoreAndroid.this.webView.getPluginManager().postMessage("spinner", "stop");
              }
            });
            continue;
          }
          if (!???.equals("loadUrl")) {
            break label110;
          }
        }
        catch (JSONException ???)
        {
          paramCallbackContext.sendPluginResult(new PluginResult(PluginResult.Status.JSON_EXCEPTION));
          return false;
        }
        loadUrl(paramJSONArray.getString(0), paramJSONArray.optJSONObject(1));
        continue;
        if (!???.equals("cancelLoadUrl")) {
          if (???.equals("clearHistory"))
          {
            clearHistory();
          }
          else if (???.equals("backHistory"))
          {
            backHistory();
          }
          else if (???.equals("overrideButton"))
          {
            overrideButton(paramJSONArray.getString(0), paramJSONArray.getBoolean(1));
          }
          else if (???.equals("overrideBackbutton"))
          {
            overrideBackbutton(paramJSONArray.getBoolean(0));
          }
          else
          {
            if (!???.equals("exitApp")) {
              break;
            }
            exitApp();
          }
        }
      }
    } while (!???.equals("messageChannel"));
    synchronized (this.messageChannelLock)
    {
      this.messageChannel = paramCallbackContext;
      if (this.pendingResume != null)
      {
        sendEventMessage(this.pendingResume);
        this.pendingResume = null;
      }
      return true;
    }
  }
  
  public void exitApp()
  {
    this.webView.getPluginManager().postMessage("exit", null);
  }
  
  public void fireJavascriptEvent(String paramString)
  {
    sendEventMessage(paramString);
  }
  
  public boolean isBackbuttonOverridden()
  {
    return this.webView.isButtonPlumbedToJs(4);
  }
  
  public void loadUrl(String paramString, JSONObject paramJSONObject)
    throws JSONException
  {
    LOG.d("App", "App.loadUrl(" + paramString + "," + paramJSONObject + ")");
    int i = 0;
    int j = 0;
    boolean bool2 = false;
    boolean bool4 = false;
    boolean bool1 = false;
    boolean bool3 = false;
    HashMap localHashMap = new HashMap();
    JSONArray localJSONArray;
    int k;
    if (paramJSONObject != null)
    {
      localJSONArray = paramJSONObject.names();
      k = 0;
      i = j;
      bool2 = bool4;
      j = k;
      bool1 = bool3;
      if (j < localJSONArray.length()) {}
    }
    else if (i <= 0) {}
    for (;;)
    {
      try
      {
        l = i;
      }
      catch (InterruptedException paramJSONObject)
      {
        long l;
        String str;
        paramJSONObject.printStackTrace();
        continue;
      }
      try
      {
        wait(l);
        this.webView.showWebPage(paramString, bool2, bool1, localHashMap);
        return;
      }
      finally {}
    }
    str = localJSONArray.getString(j);
    if (str.equals("wait"))
    {
      k = paramJSONObject.getInt(str);
      bool4 = bool2;
      bool3 = bool1;
    }
    for (;;)
    {
      j += 1;
      bool1 = bool3;
      bool2 = bool4;
      i = k;
      break;
      if (str.equalsIgnoreCase("openexternal"))
      {
        bool4 = paramJSONObject.getBoolean(str);
        bool3 = bool1;
        k = i;
      }
      else if (str.equalsIgnoreCase("clearhistory"))
      {
        bool3 = paramJSONObject.getBoolean(str);
        bool4 = bool2;
        k = i;
      }
      else
      {
        Object localObject = paramJSONObject.get(str);
        bool3 = bool1;
        bool4 = bool2;
        k = i;
        if (localObject != null) {
          if (localObject.getClass().equals(String.class))
          {
            localHashMap.put(str, (String)localObject);
            bool3 = bool1;
            bool4 = bool2;
            k = i;
          }
          else if (localObject.getClass().equals(Boolean.class))
          {
            localHashMap.put(str, (Boolean)localObject);
            bool3 = bool1;
            bool4 = bool2;
            k = i;
          }
          else
          {
            bool3 = bool1;
            bool4 = bool2;
            k = i;
            if (localObject.getClass().equals(Integer.class))
            {
              localHashMap.put(str, (Integer)localObject);
              bool3 = bool1;
              bool4 = bool2;
              k = i;
            }
          }
        }
      }
    }
  }
  
  public void onDestroy()
  {
    this.webView.getContext().unregisterReceiver(this.telephonyReceiver);
  }
  
  public void overrideBackbutton(boolean paramBoolean)
  {
    LOG.i("App", "WARNING: Back Button Default Behavior will be overridden.  The backbutton event will be fired!");
    this.webView.setButtonPlumbedToJs(4, paramBoolean);
  }
  
  public void overrideButton(String paramString, boolean paramBoolean)
  {
    LOG.i("App", "WARNING: Volume Button Default Behavior will be overridden.  The volume event will be fired!");
    if (paramString.equals("volumeup")) {
      this.webView.setButtonPlumbedToJs(24, paramBoolean);
    }
    do
    {
      return;
      if (paramString.equals("volumedown"))
      {
        this.webView.setButtonPlumbedToJs(25, paramBoolean);
        return;
      }
    } while (!paramString.equals("menubutton"));
    this.webView.setButtonPlumbedToJs(82, paramBoolean);
  }
  
  public void pluginInitialize()
  {
    initTelephonyReceiver();
  }
  
  public void sendResumeEvent(PluginResult paramPluginResult)
  {
    synchronized (this.messageChannelLock)
    {
      if (this.messageChannel != null)
      {
        sendEventMessage(paramPluginResult);
        return;
      }
      this.pendingResume = paramPluginResult;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\cordova\CoreAndroid.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */