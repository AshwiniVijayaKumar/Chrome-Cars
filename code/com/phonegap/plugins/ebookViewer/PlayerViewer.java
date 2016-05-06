package com.phonegap.plugins.ebookViewer;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import com.ons.musicplayer.MainActivity;
import com.ons.radio.PlayerActivity;
import java.io.PrintStream;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult.Status;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PlayerViewer
  extends CordovaPlugin
{
  public static boolean playercheck = false;
  
  private void doSendIntent(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6)
  {
    System.out.println("player imageurl----------url=" + paramString4);
    if (paramString2.contains("http://st2.webradioworld.net:2199/tunein/wecoam.pls")) {
      new AsynkTask(paramString4).execute(new String[] { paramString2, paramString3 });
    }
    for (;;)
    {
      return;
      if (paramString2.contains("http://109.169.26.216:8056"))
      {
        try
        {
          if (playercheck) {
            continue;
          }
          System.out.println("else");
          paramString5 = new Intent(this.cordova.getActivity(), MainActivity.class);
          paramString5.putExtra("songArray", paramString2);
          paramString5.putExtra("type", paramString3);
          paramString5.putExtra("imageurl", paramString4);
          paramString5.putExtra("plsTypeValue", paramString1);
          paramString5.putExtra("enableAutoPlay", paramString6);
          paramString5.setFlags(67108864);
          this.cordova.startActivityForResult(this, paramString5, 1);
          playercheck = true;
          return;
        }
        catch (Exception paramString1)
        {
          paramString1.printStackTrace();
          return;
        }
      }
      else
      {
        if ((paramString1.equals("shoutcast")) || (paramString1.equals("icecast"))) {
          try
          {
            paramString1 = new Intent(this.cordova.getActivity(), PlayerActivity.class);
            paramString1.putExtra("songArray", paramString2);
            paramString1.putExtra("imageurl", paramString4);
            paramString1.putExtra("channalName", paramString5);
            paramString1.putExtra("enableAutoPlay", paramString6);
            paramString1.setFlags(67108864);
            this.cordova.startActivityForResult(this, paramString1, 1);
            return;
          }
          catch (Exception paramString1)
          {
            paramString1.printStackTrace();
            return;
          }
        }
        try
        {
          if (!playercheck)
          {
            System.out.println("else");
            paramString5 = new Intent(this.cordova.getActivity(), MainActivity.class);
            paramString5.putExtra("songArray", paramString2);
            paramString5.putExtra("type", paramString3);
            paramString5.putExtra("imageurl", paramString4);
            paramString5.putExtra("plsTypeValue", paramString1);
            paramString5.putExtra("enableAutoPlay", paramString6);
            paramString5.setFlags(67108864);
            this.cordova.startActivityForResult(this, paramString5, 1);
            playercheck = true;
            return;
          }
        }
        catch (Exception paramString1)
        {
          paramString1.printStackTrace();
        }
      }
    }
  }
  
  private void doSendIntent(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7)
  {
    System.out.println("player imageurl----------url=" + paramString4);
    if ((paramString2.contains("http://st2.webradioworld.net:2199/tunein/wecoam.pls")) || (paramString2.contains("http://cast9.directhostingcenter.com:2199/tunein/qaqesyiz.pls"))) {
      new AsynkTask(paramString4).execute(new String[] { paramString2, paramString3 });
    }
    for (;;)
    {
      return;
      if ((paramString2.contains("http://109.169.26.216:8056")) || (paramString2.contains("http://199.189.111.149:8132/")))
      {
        try
        {
          if (playercheck) {
            continue;
          }
          System.out.println("else");
          paramString5 = new Intent(this.cordova.getActivity(), MainActivity.class);
          paramString5.putExtra("songArray", paramString2);
          paramString5.putExtra("type", paramString3);
          paramString5.putExtra("imageurl", paramString4);
          paramString5.putExtra("plsTypeValue", paramString1);
          paramString5.putExtra("enableAutoPlay", paramString7);
          paramString5.setFlags(67108864);
          this.cordova.startActivityForResult(this, paramString5, 1);
          playercheck = true;
          return;
        }
        catch (Exception paramString1)
        {
          paramString1.printStackTrace();
          return;
        }
      }
      else
      {
        if ((paramString1.equals("shoutcast")) || (paramString1.equals("icecast")))
        {
          if ((paramString1.equals("shoutcast")) && (paramString2.contains(".m3u8")))
          {
            System.out.println("else");
            paramString1 = new Intent(this.cordova.getActivity(), MainActivity.class);
            paramString1.putExtra("songArray", paramString2);
            paramString1.putExtra("type", "custom");
            paramString1.putExtra("imageurl", paramString4);
            paramString1.putExtra("plsTypeValue", "");
            paramString1.putExtra("enableAutoPlay", paramString7);
            paramString1.setFlags(67108864);
            this.cordova.startActivityForResult(this, paramString1, 1);
            playercheck = true;
            return;
          }
          try
          {
            paramString1 = new Intent(this.cordova.getActivity(), PlayerActivity.class);
            paramString1.putExtra("songArray", paramString2);
            paramString1.putExtra("imageurl", paramString4);
            paramString1.putExtra("channalName", paramString5);
            paramString1.putExtra("appcustumName", paramString6);
            paramString1.putExtra("enableAutoPlay", paramString7);
            paramString1.setFlags(67108864);
            this.cordova.startActivityForResult(this, paramString1, 1);
            return;
          }
          catch (Exception paramString1)
          {
            paramString1.printStackTrace();
            return;
          }
        }
        try
        {
          if (!playercheck)
          {
            System.out.println("else");
            paramString5 = new Intent(this.cordova.getActivity(), MainActivity.class);
            paramString5.putExtra("songArray", paramString2);
            paramString5.putExtra("type", paramString3);
            paramString5.putExtra("imageurl", paramString4);
            paramString5.putExtra("plsTypeValue", paramString1);
            paramString5.putExtra("enableAutoPlay", paramString7);
            paramString5.setFlags(67108864);
            this.cordova.startActivityForResult(this, paramString5, 1);
            playercheck = true;
            return;
          }
        }
        catch (Exception paramString1)
        {
          paramString1.printStackTrace();
        }
      }
    }
  }
  
  private void radioMethod(String paramString1, String paramString2)
  {
    try
    {
      if (!playercheck)
      {
        System.out.println("else");
        Intent localIntent = new Intent(this.cordova.getActivity(), MainActivity.class);
        localIntent.putExtra("songArray", paramString1);
        localIntent.putExtra("type", "custom");
        localIntent.putExtra("imageurl", paramString2);
        localIntent.putExtra("plsTypeValue", "");
        localIntent.setFlags(67108864);
        this.cordova.startActivityForResult(this, localIntent, 1);
        playercheck = true;
      }
      return;
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
  }
  
  public boolean execute(String paramString, JSONArray paramJSONArray, CallbackContext paramCallbackContext)
  {
    paramString = PluginResult.Status.OK;
    try
    {
      paramJSONArray = paramJSONArray.getJSONObject(0);
      if (paramJSONArray.has("appName")) {
        doSendIntent(paramJSONArray.getString("plsTypeValue"), paramJSONArray.getString("url"), paramJSONArray.getString("type"), paramJSONArray.getString("imageurl"), paramJSONArray.getString("channalNameValue"), paramJSONArray.getString("appName"), paramJSONArray.getString("enableautoplay"));
      }
      while (paramString == PluginResult.Status.OK)
      {
        return true;
        doSendIntent(paramJSONArray.getString("plsTypeValue"), paramJSONArray.getString("url"), paramJSONArray.getString("type"), paramJSONArray.getString("imageurl"), paramJSONArray.getString("channalNameValue"), paramJSONArray.getString("enableautoplay"));
      }
    }
    catch (JSONException paramString)
    {
      for (;;)
      {
        paramString = PluginResult.Status.JSON_EXCEPTION;
      }
    }
    return false;
  }
  
  public class AsynkTask
    extends AsyncTask<String, Void, String>
  {
    String baseUrl = "";
    String imageUrl;
    String responseData = "";
    
    public AsynkTask(String paramString)
    {
      this.imageUrl = paramString;
    }
    
    protected String doInBackground(String... paramVarArgs)
    {
      if (paramVarArgs[1].equals("customRadio")) {
        this.baseUrl = ("http://apps.appypie.com/mobile_api/pls.php?username=" + paramVarArgs[0].split(",")[0]);
      }
      try
      {
        for (;;)
        {
          paramVarArgs = new BasicHttpParams();
          HttpConnectionParams.setConnectionTimeout(paramVarArgs, 3000);
          this.responseData = EntityUtils.toString(new DefaultHttpClient(paramVarArgs).execute(new HttpPost(this.baseUrl)).getEntity());
          this.responseData = this.responseData.trim();
          if (this.responseData.length() > 0)
          {
            this.responseData += "\n";
            Log.e("rsponse >>>>>>>>>>>>>>>>>>>>>>", this.responseData);
            this.responseData = this.responseData.split("\n")[0];
          }
          return this.responseData;
          this.baseUrl = ("http://apps.appypie.com/mobile_api/m3u.php?username=" + paramVarArgs[0]);
        }
      }
      catch (Exception paramVarArgs)
      {
        for (;;)
        {
          paramVarArgs.printStackTrace();
        }
      }
    }
    
    protected void onPostExecute(String paramString)
    {
      super.onPostExecute(paramString);
      if ((paramString != null) && (paramString.length() > 0)) {
        PlayerViewer.this.radioMethod(paramString, this.imageUrl);
      }
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\phonegap\plugins\ebookViewer\PlayerViewer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */