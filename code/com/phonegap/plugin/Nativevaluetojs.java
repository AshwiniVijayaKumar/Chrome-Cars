package com.phonegap.plugin;

import com.example.example75f1799f07eb.DatabaseHelperAdapter;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Nativevaluetojs
  extends CordovaPlugin
{
  private CallbackContext callbackContext;
  
  public boolean execute(String paramString, JSONArray paramJSONArray, CallbackContext paramCallbackContext)
    throws JSONException
  {
    this.callbackContext = paramCallbackContext;
    try
    {
      paramCallbackContext = paramJSONArray.getJSONObject(0);
      paramString = "";
      DatabaseHelperAdapter localDatabaseHelperAdapter = new DatabaseHelperAdapter(this.cordova.getActivity());
      if (paramCallbackContext.getString("loginorlogout").equalsIgnoreCase("logout")) {
        localDatabaseHelperAdapter.logout(paramCallbackContext.getString("chk"));
      }
      for (;;)
      {
        this.callbackContext.success("" + paramString);
        return true;
        if (paramCallbackContext.getString("loginorlogout").equalsIgnoreCase("login"))
        {
          if (paramCallbackContext.getString("chk").equalsIgnoreCase("food"))
          {
            paramJSONArray = localDatabaseHelperAdapter.insertIntoFoodLoginTable(paramCallbackContext.getString("uName"), paramCallbackContext.getString("pass"));
            paramString = paramJSONArray;
            if (paramJSONArray.equalsIgnoreCase("")) {
              paramString = localDatabaseHelperAdapter.insertIntoFoodLoginTable(paramCallbackContext.getString("uName"), paramCallbackContext.getString("pass"));
            }
          }
          else if (paramCallbackContext.getString("chk").equalsIgnoreCase("ecomm"))
          {
            paramJSONArray = localDatabaseHelperAdapter.insertIntoEcommLoginTable(paramCallbackContext.getString("uName"), paramCallbackContext.getString("pass"));
            paramString = paramJSONArray;
            if (paramJSONArray.equalsIgnoreCase("")) {
              paramString = localDatabaseHelperAdapter.insertIntoEcommLoginTable(paramCallbackContext.getString("uName"), paramCallbackContext.getString("pass"));
            }
          }
        }
        else if (paramCallbackContext.getString("loginorlogout").equalsIgnoreCase("applogin"))
        {
          paramJSONArray = localDatabaseHelperAdapter.insertIntoAppLoginTable(paramCallbackContext.getString("uName"));
          paramString = paramJSONArray;
          if (paramJSONArray.equalsIgnoreCase("")) {
            paramString = localDatabaseHelperAdapter.insertIntoAppLoginTable(paramCallbackContext.getString("uName"));
          }
        }
      }
      return false;
    }
    catch (JSONException paramString)
    {
      this.callbackContext.success("failed");
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\phonegap\plugin\Nativevaluetojs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */