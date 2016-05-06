package com.ooyala.android.item;

import com.ooyala.android.OoyalaAPIClient;
import com.ooyala.android.util.OrderedMap;
import java.io.PrintStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class DynamicChannel
  extends Channel
{
  protected List<String> _embedCodes = null;
  
  DynamicChannel() {}
  
  DynamicChannel(JSONObject paramJSONObject, List<String> paramList, OoyalaAPIClient paramOoyalaAPIClient)
  {
    this(paramJSONObject, paramList, null, paramOoyalaAPIClient);
  }
  
  DynamicChannel(JSONObject paramJSONObject, List<String> paramList, ChannelSet paramChannelSet, OoyalaAPIClient paramOoyalaAPIClient)
  {
    this._authorized = true;
    this._authCode = 0;
    this._parent = paramChannelSet;
    this._embedCode = null;
    this._embedCodes = paramList;
    this._api = paramOoyalaAPIClient;
    update(paramJSONObject);
  }
  
  public List<String> embedCodesToAuthorize()
  {
    return this._embedCodes;
  }
  
  public List<String> getEmbedCodes()
  {
    return this._embedCodes;
  }
  
  public JSONUpdatableItem.ReturnState update(JSONObject paramJSONObject)
  {
    for (;;)
    {
      Iterator localIterator;
      try
      {
        switch (super.update(paramJSONObject))
        {
        case ???: 
          localIterator = this._videos.values().iterator();
          if (localIterator.hasNext())
          {
            ((Video)localIterator.next()).update(paramJSONObject);
            continue;
            paramJSONObject = JSONUpdatableItem.ReturnState.STATE_FAIL;
          }
          break;
        }
      }
      finally {}
      for (;;)
      {
        return paramJSONObject;
        for (;;)
        {
          String str;
          try
          {
            localIterator = this._embedCodes.iterator();
            if (!localIterator.hasNext()) {
              break label283;
            }
            str = (String)localIterator.next();
            if (paramJSONObject.isNull(str))
            {
              this._embedCodes.remove(str);
              continue;
            }
          }
          catch (JSONException paramJSONObject)
          {
            System.out.println("JSONException: " + paramJSONObject);
            paramJSONObject = JSONUpdatableItem.ReturnState.STATE_FAIL;
          }
          Object localObject = paramJSONObject.getJSONObject(str);
          if (!((JSONObject)localObject).isNull("content_type")) {
            if (((JSONObject)localObject).getString("content_type").equals("Video"))
            {
              localObject = (Video)this._videos.get(str);
              if (localObject == null) {
                addVideo(new Video(paramJSONObject, str, this, this._api));
              } else {
                ((Video)localObject).update(paramJSONObject);
              }
            }
            else
            {
              System.out.println("ERROR: Invalid Video(DynamicChannel) content_type: " + ((JSONObject)localObject).getString("content_type"));
            }
          }
        }
        label283:
        paramJSONObject = JSONUpdatableItem.ReturnState.STATE_MATCHED;
      }
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\item\DynamicChannel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */