package com.phonegap.plugin;

import android.os.Environment;
import com.ons.bellareader.EpubManipulator;
import java.io.File;
import java.io.PrintStream;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DownloadUnzip
  extends CordovaPlugin
{
  File file;
  String filePath;
  
  private void doSendIntent(String paramString1, String paramString2)
  {
    System.out.println("unzip----------url=" + paramString1 + "----info=" + paramString2);
    paramString1 = Environment.getExternalStorageDirectory() + "/Downloads/" + paramString2;
    try
    {
      new EpubManipulator(paramString1, paramString2);
      return;
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
  }
  
  public boolean execute(String paramString, JSONArray paramJSONArray, CallbackContext paramCallbackContext)
    throws JSONException
  {
    try
    {
      paramString = paramJSONArray.getJSONObject(0);
      doSendIntent(paramString.getString("url"), paramString.getString("info"));
      return true;
    }
    catch (JSONException paramString) {}
    return false;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\phonegap\plugin\DownloadUnzip.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */