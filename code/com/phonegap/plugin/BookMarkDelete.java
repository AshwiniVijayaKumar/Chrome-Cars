package com.phonegap.plugin;

import com.ons.bellareader.DatabaseHelperAdapterReader;
import java.io.PrintStream;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BookMarkDelete
  extends CordovaPlugin
{
  private void doSendIntent(String paramString)
  {
    System.out.println(paramString);
    new DatabaseHelperAdapterReader(this.cordova.getActivity()).deleteBookmarkByBookname(paramString);
  }
  
  public boolean execute(String paramString, JSONArray paramJSONArray, CallbackContext paramCallbackContext)
    throws JSONException
  {
    doSendIntent(paramJSONArray.getJSONObject(0).getString("bookname"));
    return true;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\phonegap\plugin\BookMarkDelete.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */