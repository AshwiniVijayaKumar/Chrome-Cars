package com.ooyala.android;

import android.os.AsyncTask;
import com.ooyala.android.apis.FetchPlaybackInfoCallback;
import com.ooyala.android.item.AuthorizableItem;
import com.ooyala.android.item.JSONUpdatableItem.ReturnState;
import com.ooyala.android.item.OoyalaManagedAdSpot;
import com.ooyala.android.item.PlayableItem;
import com.ooyala.android.item.Stream;
import com.ooyala.android.player.StreamPlayer;
import com.ooyala.android.util.DebugMode;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OoyalaAdSpot
  extends OoyalaManagedAdSpot
  implements AuthorizableItem, PlayableItem
{
  static final String KEY_AD_EMBED_CODE = "ad_embed_code";
  static final String KEY_AUTHORIZED = "authorized";
  static final String KEY_CODE = "code";
  static final String KEY_STREAMS = "streams";
  private static final String TAG = OoyalaAdSpot.class.getSimpleName();
  protected OoyalaAPIClient _api;
  protected int _authCode = -1;
  protected boolean _authorized = false;
  protected String _embedCode = null;
  protected Set<Stream> _streams = new HashSet();
  
  public OoyalaAdSpot(int paramInt, URL paramURL, List<URL> paramList, String paramString, OoyalaAPIClient paramOoyalaAPIClient)
  {
    super(paramInt, paramURL, paramList);
    this._embedCode = paramString;
    this._api = paramOoyalaAPIClient;
  }
  
  public OoyalaAdSpot(JSONObject paramJSONObject, OoyalaAPIClient paramOoyalaAPIClient)
  {
    this._api = paramOoyalaAPIClient;
    update(paramJSONObject);
  }
  
  public List<String> embedCodesToAuthorize()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(this._embedCode);
    return localArrayList;
  }
  
  public Object fetchPlaybackInfo(PlayerInfo paramPlayerInfo, FetchPlaybackInfoCallback paramFetchPlaybackInfoCallback)
  {
    paramPlayerInfo = new FetchPlaybackInfoTask(paramPlayerInfo, paramFetchPlaybackInfoCallback);
    paramPlayerInfo.execute(new Void[0]);
    return paramPlayerInfo;
  }
  
  public boolean fetchPlaybackInfo()
  {
    return fetchPlaybackInfo(StreamPlayer.defaultPlayerInfo);
  }
  
  public boolean fetchPlaybackInfo(PlayerInfo paramPlayerInfo)
  {
    if (this._authCode != -1) {
      return true;
    }
    try
    {
      boolean bool = this._api.authorize(this, paramPlayerInfo);
      return bool;
    }
    catch (OoyalaException paramPlayerInfo)
    {
      DebugMode.logE(TAG, "Unable to fetch playback info: " + paramPlayerInfo.getMessage());
    }
    return false;
  }
  
  public int getAuthCode()
  {
    return this._authCode;
  }
  
  public String getEmbedCode()
  {
    return this._embedCode;
  }
  
  public Stream getStream()
  {
    return Stream.bestStream(this._streams, false);
  }
  
  public Set<Stream> getStreams()
  {
    return this._streams;
  }
  
  public boolean isAuthorized()
  {
    return this._authorized;
  }
  
  public boolean isHeartbeatRequired()
  {
    return false;
  }
  
  public JSONUpdatableItem.ReturnState update(JSONObject paramJSONObject)
  {
    switch (super.update(paramJSONObject))
    {
    }
    try
    {
      if ((this._embedCode != null) && (!paramJSONObject.isNull(this._embedCode)))
      {
        paramJSONObject = paramJSONObject.getJSONObject(this._embedCode);
        if (!paramJSONObject.isNull("authorized"))
        {
          this._authorized = paramJSONObject.getBoolean("authorized");
          if (!paramJSONObject.isNull("code")) {
            this._authCode = paramJSONObject.getInt("code");
          }
          if ((this._authorized) && (!paramJSONObject.isNull("streams")))
          {
            paramJSONObject = paramJSONObject.getJSONArray("streams");
            if (paramJSONObject.length() > 0)
            {
              this._streams.clear();
              int i = 0;
              while (i < paramJSONObject.length())
              {
                Stream localStream = new Stream(paramJSONObject.getJSONObject(i));
                if (localStream != null) {
                  this._streams.add(localStream);
                }
                i += 1;
                continue;
                return JSONUpdatableItem.ReturnState.STATE_FAIL;
                return JSONUpdatableItem.ReturnState.STATE_UNMATCHED;
              }
            }
          }
        }
        return JSONUpdatableItem.ReturnState.STATE_MATCHED;
      }
      if (paramJSONObject.isNull("ad_embed_code"))
      {
        System.out.println("ERROR: Fail to update OoyalaAdSpot with dictionary because no ad embed code exists!");
        return JSONUpdatableItem.ReturnState.STATE_FAIL;
      }
      this._embedCode = paramJSONObject.getString("ad_embed_code");
      paramJSONObject = JSONUpdatableItem.ReturnState.STATE_MATCHED;
      return paramJSONObject;
    }
    catch (JSONException paramJSONObject)
    {
      System.out.println("JSONException: " + paramJSONObject);
    }
    return JSONUpdatableItem.ReturnState.STATE_FAIL;
  }
  
  private class FetchPlaybackInfoTask
    extends AsyncTask<Void, Integer, Boolean>
  {
    protected FetchPlaybackInfoCallback _callback = null;
    protected PlayerInfo _info = null;
    
    public FetchPlaybackInfoTask(PlayerInfo paramPlayerInfo, FetchPlaybackInfoCallback paramFetchPlaybackInfoCallback)
    {
      this._callback = paramFetchPlaybackInfoCallback;
      this._info = paramPlayerInfo;
    }
    
    protected Boolean doInBackground(Void... paramVarArgs)
    {
      return Boolean.valueOf(OoyalaAdSpot.this.fetchPlaybackInfo(this._info));
    }
    
    protected void onPostExecute(Boolean paramBoolean)
    {
      this._callback.callback(paramBoolean.booleanValue());
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\OoyalaAdSpot.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */