package com.ooyala.android.item;

import com.ooyala.android.OoyalaAPIClient;
import com.ooyala.android.util.OrderedMap;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ChannelSet
  extends ContentItem
  implements PaginatedParentItem
{
  protected OrderedMap<String, Channel> _channels = new OrderedMap();
  protected boolean _isFetchingMoreChildren = false;
  protected String _nextChildren = null;
  
  ChannelSet() {}
  
  ChannelSet(JSONObject paramJSONObject, String paramString, OoyalaAPIClient paramOoyalaAPIClient)
  {
    this(paramJSONObject, paramString, null, paramOoyalaAPIClient);
  }
  
  ChannelSet(JSONObject paramJSONObject, String paramString, ChannelSet paramChannelSet, OoyalaAPIClient paramOoyalaAPIClient)
  {
    this._embedCode = paramString;
    this._api = paramOoyalaAPIClient;
    update(paramJSONObject);
  }
  
  protected void addChannel(Channel paramChannel)
  {
    this._channels.put(paramChannel.getEmbedCode(), paramChannel);
  }
  
  public int childrenCount()
  {
    return this._channels.size();
  }
  
  public List<String> embedCodesToAuthorize()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(this._embedCode);
    localArrayList.addAll(this._channels.keySet());
    return localArrayList;
  }
  
  public Video firstVideo()
  {
    if ((this._channels == null) || (this._channels.size() == 0)) {
      return null;
    }
    return ((Channel)this._channels.get(0)).firstVideo();
  }
  
  public OrderedMap<String, Channel> getAllAvailableChildren()
  {
    return this._channels;
  }
  
  public OrderedMap<String, Channel> getChannels()
  {
    return getAllAvailableChildren();
  }
  
  public int getDuration()
  {
    int i = 0;
    Iterator localIterator = this._channels.iterator();
    while (localIterator.hasNext()) {
      i += ((Channel)localIterator.next()).getDuration();
    }
    return i;
  }
  
  public String getNextChildren()
  {
    return this._nextChildren;
  }
  
  public boolean hasMoreChildren()
  {
    return this._nextChildren != null;
  }
  
  public Video nextVideo(Channel paramChannel)
  {
    int i = this._channels.indexForValue(paramChannel);
    if (i >= 0)
    {
      i += 1;
      if (i < this._channels.size()) {}
    }
    else
    {
      return null;
    }
    return ((Channel)this._channels.get(i)).firstVideo();
  }
  
  public Video previousVideo(Channel paramChannel)
  {
    int i = this._channels.indexForValue(paramChannel);
    if (i >= 0)
    {
      i -= 1;
      if (i >= 0) {}
    }
    else
    {
      return null;
    }
    return ((Channel)this._channels.get(i)).lastVideo();
  }
  
  public JSONUpdatableItem.ReturnState update(JSONObject paramJSONObject)
  {
    int i;
    Object localObject1;
    for (;;)
    {
      try
      {
        i = 1.$SwitchMap$com$ooyala$android$item$JSONUpdatableItem$ReturnState[super.update(paramJSONObject).ordinal()];
        switch (i)
        {
        default: 
          try
          {
            localObject1 = paramJSONObject.getJSONObject(this._embedCode);
            if ((((JSONObject)localObject1).isNull("authorized")) || (!((JSONObject)localObject1).getBoolean("authorized"))) {
              break label195;
            }
            localObject1 = this._channels.iterator();
            if (!((Iterator)localObject1).hasNext()) {
              break label188;
            }
            ((Channel)((Iterator)localObject1).next()).update(paramJSONObject);
            continue;
          }
          catch (JSONException paramJSONObject)
          {
            System.out.println("JSONException: " + paramJSONObject);
            paramJSONObject = JSONUpdatableItem.ReturnState.STATE_FAIL;
          }
          return paramJSONObject;
        case 1: 
          paramJSONObject = JSONUpdatableItem.ReturnState.STATE_FAIL;
          break;
        }
        localObject1 = this._channels.iterator();
        if (((Iterator)localObject1).hasNext())
        {
          ((Channel)((Iterator)localObject1).next()).update(paramJSONObject);
          continue;
        }
        paramJSONObject = JSONUpdatableItem.ReturnState.STATE_UNMATCHED;
      }
      finally {}
      continue;
      label188:
      paramJSONObject = JSONUpdatableItem.ReturnState.STATE_MATCHED;
      continue;
      label195:
      if (!((JSONObject)localObject1).isNull("base"))
      {
        localObject1 = this._channels.iterator();
        while (((Iterator)localObject1).hasNext()) {
          ((Channel)((Iterator)localObject1).next()).update(paramJSONObject);
        }
        paramJSONObject = JSONUpdatableItem.ReturnState.STATE_MATCHED;
      }
      else if ((!((JSONObject)localObject1).isNull("content_type")) && (!((JSONObject)localObject1).getString("content_type").equals("MultiChannel")))
      {
        System.out.println("ERROR: Attempted to initialize ChannelSet with content_type: " + ((JSONObject)localObject1).getString("content_type"));
        paramJSONObject = JSONUpdatableItem.ReturnState.STATE_FAIL;
      }
      else
      {
        if (((JSONObject)localObject1).isNull("next_children")) {}
        for (paramJSONObject = null;; paramJSONObject = ((JSONObject)localObject1).getString("next_children"))
        {
          this._nextChildren = paramJSONObject;
          if (!((JSONObject)localObject1).isNull("children")) {
            break label389;
          }
          if (this._nextChildren != null) {
            break label382;
          }
          System.out.println("ERROR: Attempted to initialize ChannelSet with children == nil and next_children == nil: " + this._embedCode);
          paramJSONObject = JSONUpdatableItem.ReturnState.STATE_FAIL;
          break;
        }
        label382:
        paramJSONObject = JSONUpdatableItem.ReturnState.STATE_MATCHED;
      }
    }
    label389:
    paramJSONObject = ((JSONObject)localObject1).getJSONArray("children");
    if (paramJSONObject.length() > 0) {
      i = 0;
    }
    for (;;)
    {
      if (i < paramJSONObject.length())
      {
        JSONObject localJSONObject = paramJSONObject.getJSONObject(i);
        if ((!localJSONObject.isNull("content_type")) && (localJSONObject.getString("content_type").equals("Channel")))
        {
          Object localObject2 = new HashMap();
          localObject1 = localJSONObject.getString("embed_code");
          ((HashMap)localObject2).put(localObject1, localJSONObject);
          localJSONObject = new JSONObject((Map)localObject2);
          localObject2 = (Channel)this._channels.get(localObject1);
          if (localObject2 == null) {
            addChannel(new Channel(localJSONObject, (String)localObject1, this, this._api));
          } else {
            ((Channel)localObject2).update(localJSONObject);
          }
        }
        else
        {
          System.out.println("ERROR: Invalid Channel content_type: " + localJSONObject.getString("content_type"));
        }
      }
      else
      {
        paramJSONObject = JSONUpdatableItem.ReturnState.STATE_MATCHED;
        break;
      }
      i += 1;
    }
  }
  
  public Video videoFromEmbedCode(String paramString, Video paramVideo)
  {
    if (paramVideo == null) {}
    int k;
    for (int i = 0;; i = this._channels.indexForValue(paramVideo.getParent()))
    {
      k = i;
      Video localVideo = ((Channel)this._channels.get(k)).videoFromEmbedCode(paramString, paramVideo);
      if (localVideo == null) {
        break;
      }
      return localVideo;
    }
    if (k >= this._channels.size()) {}
    for (int j = 0;; j = k + 1)
    {
      k = j;
      if (j != i) {
        break;
      }
      return null;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\item\ChannelSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */