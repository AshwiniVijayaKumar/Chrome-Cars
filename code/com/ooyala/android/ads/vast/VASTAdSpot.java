package com.ooyala.android.ads.vast;

import android.os.AsyncTask;
import com.ooyala.android.apis.FetchPlaybackInfoCallback;
import com.ooyala.android.item.JSONUpdatableItem.ReturnState;
import com.ooyala.android.item.OoyalaManagedAdSpot;
import com.ooyala.android.util.DebugMode;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class VASTAdSpot
  extends OoyalaManagedAdSpot
{
  static final String KEY_EXPIRES = "expires";
  static final String KEY_SIGNATURE = "signature";
  static final String KEY_URL = "url";
  protected List<VASTAd> _ads = new ArrayList();
  protected long _expires;
  protected String _signature;
  protected URL _vastURL;
  
  public VASTAdSpot(int paramInt, URL paramURL1, List<URL> paramList, URL paramURL2)
  {
    super(paramInt, paramURL1, paramList);
    this._vastURL = VASTUtils.urlFromAdUrlString(paramURL2.toString());
  }
  
  public VASTAdSpot(JSONObject paramJSONObject)
  {
    update(paramJSONObject);
  }
  
  public Object fetchPlaybackInfo(FetchPlaybackInfoCallback paramFetchPlaybackInfoCallback)
  {
    paramFetchPlaybackInfoCallback = new FetchPlaybackInfoTask(paramFetchPlaybackInfoCallback);
    paramFetchPlaybackInfoCallback.execute(new Void[0]);
    return paramFetchPlaybackInfoCallback;
  }
  
  public boolean fetchPlaybackInfo()
  {
    if (this._vastURL == null) {
      return false;
    }
    try
    {
      Object localObject = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(this._vastURL.toString()).getDocumentElement();
      if (!((Element)localObject).getTagName().equals("VAST")) {
        return false;
      }
      if (Double.parseDouble(((Element)localObject).getAttribute("version")) < 2.0D) {
        return false;
      }
      localObject = ((Element)localObject).getFirstChild();
      while (localObject != null) {
        if ((!(localObject instanceof Element)) || (!((Element)localObject).getTagName().equals("Ad")))
        {
          localObject = ((Node)localObject).getNextSibling();
        }
        else
        {
          VASTAd localVASTAd = new VASTAd((Element)localObject);
          if (localVASTAd != null) {
            this._ads.add(localVASTAd);
          }
          localObject = ((Node)localObject).getNextSibling();
        }
      }
      return true;
    }
    catch (Exception localException)
    {
      System.out.println("ERROR: Unable to fetch VAST ad tag info: " + localException);
      return false;
    }
  }
  
  public List<VASTAd> getAds()
  {
    return this._ads;
  }
  
  public URL getVASTURL()
  {
    return this._vastURL;
  }
  
  public JSONUpdatableItem.ReturnState update(JSONObject paramJSONObject)
  {
    switch (super.update(paramJSONObject))
    {
    default: 
      if (paramJSONObject.isNull("signature"))
      {
        DebugMode.logE(getClass().getName(), "ERROR: Fail to update VASTAd with dictionary because no signature exists!");
        return JSONUpdatableItem.ReturnState.STATE_FAIL;
      }
      break;
    case ???: 
      return JSONUpdatableItem.ReturnState.STATE_FAIL;
    case ???: 
      return JSONUpdatableItem.ReturnState.STATE_UNMATCHED;
    }
    if (paramJSONObject.isNull("expires"))
    {
      DebugMode.logE(getClass().getName(), "ERROR: Fail to update VASTAd with dictionary because no expires exists!");
      return JSONUpdatableItem.ReturnState.STATE_FAIL;
    }
    if (paramJSONObject.isNull("url"))
    {
      DebugMode.logE(getClass().getName(), "ERROR: Fail to update VASTAd with dictionary because no url exists!");
      return JSONUpdatableItem.ReturnState.STATE_FAIL;
    }
    try
    {
      this._signature = paramJSONObject.getString("signature");
      this._expires = paramJSONObject.getInt("expires");
      this._vastURL = VASTUtils.urlFromAdUrlString(paramJSONObject.getString("url"));
      if (this._vastURL == null)
      {
        paramJSONObject = JSONUpdatableItem.ReturnState.STATE_FAIL;
        return paramJSONObject;
      }
    }
    catch (JSONException paramJSONObject)
    {
      DebugMode.logD(getClass().getName(), "JSONException: " + paramJSONObject);
      return JSONUpdatableItem.ReturnState.STATE_FAIL;
    }
    return JSONUpdatableItem.ReturnState.STATE_MATCHED;
  }
  
  private class FetchPlaybackInfoTask
    extends AsyncTask<Void, Integer, Boolean>
  {
    protected FetchPlaybackInfoCallback _callback = null;
    
    public FetchPlaybackInfoTask(FetchPlaybackInfoCallback paramFetchPlaybackInfoCallback)
    {
      this._callback = paramFetchPlaybackInfoCallback;
    }
    
    protected Boolean doInBackground(Void... paramVarArgs)
    {
      return Boolean.valueOf(VASTAdSpot.this.fetchPlaybackInfo());
    }
    
    protected void onPostExecute(Boolean paramBoolean)
    {
      this._callback.callback(paramBoolean.booleanValue());
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\ads\vast\VASTAdSpot.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */