package com.phonegap.plugin;

import android.content.Intent;
import com.ons.bellareader.Bookmark;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BookmarkSave
  extends CordovaPlugin
{
  private void doSendIntent(String paramString1, String paramString2)
  {
    Intent localIntent = new Intent(this.cordova.getActivity(), Bookmark.class);
    localIntent.setFlags(67108864);
    localIntent.putExtra("videoId", paramString1);
    localIntent.putExtra("videoInfo", paramString2);
    this.cordova.startActivityForResult(this, localIntent, 1);
  }
  
  public boolean execute(String paramString, JSONArray paramJSONArray, CallbackContext paramCallbackContext)
    throws JSONException
  {
    try
    {
      paramString = paramJSONArray.getJSONObject(0);
      doSendIntent(paramString.getString("videoid"), paramString.getString("videoInfo"));
      return true;
    }
    catch (JSONException paramString) {}
    return false;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\phonegap\plugin\BookmarkSave.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */