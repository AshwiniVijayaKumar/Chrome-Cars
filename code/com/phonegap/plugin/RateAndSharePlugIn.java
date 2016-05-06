package com.phonegap.plugin;

import android.content.Intent;
import com.example.example75f1799f07eb.ReloadAppActivity;
import java.io.PrintStream;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RateAndSharePlugIn
  extends CordovaPlugin
{
  private void doSendIntent(String paramString)
  {
    Intent localIntent = new Intent(this.cordova.getActivity(), ReloadAppActivity.class);
    localIntent.putExtra("urlData", paramString);
    this.cordova.startActivityForResult(this, localIntent, 1);
  }
  
  public boolean execute(String paramString, JSONArray paramJSONArray, CallbackContext paramCallbackContext)
    throws JSONException
  {
    try
    {
      System.out.println("loading RateAndSharePlugIn plugins");
      paramString = paramJSONArray.getJSONObject(0);
      System.out.println("" + paramString.getString("rateandsharetxt"));
      doSendIntent(paramString.getString("rateandsharetxt"));
      System.out.println("" + paramString.getString("rateandsharetxt"));
      return true;
    }
    catch (JSONException paramString) {}
    return false;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\phonegap\plugin\RateAndSharePlugIn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */