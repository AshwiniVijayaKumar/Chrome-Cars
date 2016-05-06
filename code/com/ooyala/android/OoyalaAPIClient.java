package com.ooyala.android;

import android.os.AsyncTask;
import com.ooyala.android.apis.ContentTreeCallback;
import com.ooyala.android.apis.ContentTreeNextCallback;
import com.ooyala.android.configuration.Options;
import com.ooyala.android.item.AuthorizableItem;
import com.ooyala.android.item.ContentItem;
import com.ooyala.android.item.PaginatedParentItem;
import com.ooyala.android.util.DebugMode;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public class OoyalaAPIClient
{
  private PlayerAPIClient _playerAPI = null;
  private SecureURLGenerator _secureUrlGenerator = null;
  
  OoyalaAPIClient(PlayerAPIClient paramPlayerAPIClient)
  {
    this._playerAPI = paramPlayerAPIClient;
  }
  
  public OoyalaAPIClient(SecureURLGenerator paramSecureURLGenerator, String paramString, PlayerDomain paramPlayerDomain, Options paramOptions)
  {
    this(paramString, paramPlayerDomain, paramOptions);
    this._secureUrlGenerator = paramSecureURLGenerator;
  }
  
  public OoyalaAPIClient(String paramString, PlayerDomain paramPlayerDomain, Options paramOptions)
  {
    this(new PlayerAPIClient(paramString, paramPlayerDomain, null, paramOptions));
  }
  
  public OoyalaAPIClient(String paramString1, SignatureGenerator paramSignatureGenerator, String paramString2, PlayerDomain paramPlayerDomain, Options paramOptions)
  {
    this(new EmbeddedSecureURLGenerator(paramString1, paramSignatureGenerator), paramString2, paramPlayerDomain, paramOptions);
  }
  
  public OoyalaAPIClient(String paramString1, String paramString2, String paramString3, PlayerDomain paramPlayerDomain)
  {
    this(new EmbeddedSecureURLGenerator(paramString1, paramString2), paramString3, paramPlayerDomain, null);
  }
  
  public OoyalaAPIClient(String paramString1, String paramString2, String paramString3, PlayerDomain paramPlayerDomain, Options paramOptions)
  {
    this(new EmbeddedSecureURLGenerator(paramString1, paramString2), paramString3, paramPlayerDomain, paramOptions);
  }
  
  public boolean authorize(AuthorizableItem paramAuthorizableItem, PlayerInfo paramPlayerInfo)
    throws OoyalaException
  {
    return this._playerAPI.authorize(paramAuthorizableItem, paramPlayerInfo);
  }
  
  public void cancel(Object paramObject)
  {
    this._playerAPI.cancel(paramObject);
  }
  
  public ContentItem contentTree(List<String> paramList)
    throws OoyalaException
  {
    return this._playerAPI.contentTree(paramList);
  }
  
  public Object contentTree(List<String> paramList, ContentTreeCallback paramContentTreeCallback)
  {
    return this._playerAPI.contentTree(paramList, paramContentTreeCallback);
  }
  
  public ContentItem contentTreeByExternalIds(List<String> paramList)
    throws OoyalaException
  {
    return this._playerAPI.contentTreeByExternalIds(paramList);
  }
  
  public Object contentTreeByExternalIds(List<String> paramList, ContentTreeCallback paramContentTreeCallback)
  {
    return this._playerAPI.contentTreeByExternalIds(paramList, paramContentTreeCallback);
  }
  
  public PaginatedItemResponse contentTreeNext(PaginatedParentItem paramPaginatedParentItem)
  {
    return this._playerAPI.contentTreeNext(paramPaginatedParentItem);
  }
  
  public Object contentTreeNext(PaginatedParentItem paramPaginatedParentItem, ContentTreeNextCallback paramContentTreeNextCallback)
  {
    return this._playerAPI.contentTreeNext(paramPaginatedParentItem, paramContentTreeNextCallback);
  }
  
  public ContentItem contentTreeWithAdSet(List<String> paramList, String paramString)
    throws OoyalaException
  {
    return this._playerAPI.contentTreeWithAdSet(paramList, paramString);
  }
  
  public Object contentTreeWithAdSet(List<String> paramList, String paramString, ContentTreeCallback paramContentTreeCallback)
  {
    return this._playerAPI.contentTreeWithAdSet(paramList, paramString, paramContentTreeCallback);
  }
  
  PlayerDomain getDomain()
  {
    return this._playerAPI.getDomain();
  }
  
  public String getPcode()
  {
    return this._playerAPI.getPcode();
  }
  
  SecureURLGenerator getSecureURLGenerator()
  {
    return this._secureUrlGenerator;
  }
  
  public Object objectFromBacklotAPI(String paramString, Map<String, String> paramMap, ObjectFromBacklotAPICallback paramObjectFromBacklotAPICallback)
  {
    paramObjectFromBacklotAPICallback = new ObjectFromBacklotAPITask(paramObjectFromBacklotAPICallback);
    paramObjectFromBacklotAPICallback.execute(new Object[] { paramString, paramMap });
    return paramObjectFromBacklotAPICallback;
  }
  
  public JSONObject objectFromBacklotAPI(String paramString, Map<String, String> paramMap)
  {
    if (this._secureUrlGenerator == null)
    {
      DebugMode.logD(getClass().getName(), "Backlot APIs are not supported without a SecureURLGenerator or apikey/secret");
      return null;
    }
    return OoyalaAPIHelper.objectForAPI(this._secureUrlGenerator.secureURL(Environment.BACKLOT_HOST, "/v2" + paramString, paramMap), this._playerAPI.getConnectionTimeoutInMillisecond(), this._playerAPI.getReadTimeoutInMillisecond());
  }
  
  private class ObjectFromBacklotAPITask
    extends AsyncTask<Object, Integer, JSONObject>
  {
    protected ObjectFromBacklotAPICallback _callback = null;
    
    public ObjectFromBacklotAPITask(ObjectFromBacklotAPICallback paramObjectFromBacklotAPICallback)
    {
      this._callback = paramObjectFromBacklotAPICallback;
    }
    
    protected JSONObject doInBackground(Object... paramVarArgs)
    {
      if ((paramVarArgs == null) || (paramVarArgs.length < 2) || (!(paramVarArgs[0] instanceof String)) || (!(paramVarArgs[0] instanceof Map))) {
        return null;
      }
      String str = (String)paramVarArgs[0];
      paramVarArgs = (Map)paramVarArgs[1];
      return OoyalaAPIClient.this.objectFromBacklotAPI(str, paramVarArgs);
    }
    
    protected void onPostExecute(JSONObject paramJSONObject)
    {
      this._callback.callback(paramJSONObject);
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\OoyalaAPIClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */