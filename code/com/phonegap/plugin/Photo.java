package com.phonegap.plugin;

import android.content.Intent;
import com.example.example75f1799f07eb.ImageGalleryActivity;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Photo
  extends CordovaPlugin
{
  private void doSendIntent(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6)
  {
    Intent localIntent = new Intent(this.cordova.getActivity(), ImageGalleryActivity.class);
    localIntent.setFlags(67108864);
    localIntent.putExtra("position", paramString1);
    localIntent.putExtra("bigImageUrls", paramString2);
    localIntent.putExtra("pictext", paramString3);
    localIntent.putExtra("piclikes", paramString4);
    localIntent.putExtra("piccomments", paramString5);
    localIntent.putExtra("photoShare", paramString6);
    this.cordova.startActivityForResult(this, localIntent, 1);
  }
  
  public boolean execute(String paramString, JSONArray paramJSONArray, CallbackContext paramCallbackContext)
    throws JSONException
  {
    try
    {
      paramString = paramJSONArray.getJSONObject(0);
      doSendIntent(paramString.getString("videoid"), paramString.getString("videoInfo"), paramString.optString("pictext", ""), paramString.optString("piclikes", ""), paramString.optString("piccomments", ""), paramString.optString("photoShare", ""));
      return true;
    }
    catch (JSONException paramString) {}
    return false;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\phonegap\plugin\Photo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */