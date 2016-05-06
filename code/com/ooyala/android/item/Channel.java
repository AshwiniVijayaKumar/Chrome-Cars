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

public class Channel
  extends ContentItem
  implements PaginatedParentItem
{
  protected boolean _isFetchingMoreChildren = false;
  protected String _nextChildren = null;
  protected ChannelSet _parent = null;
  protected OrderedMap<String, Video> _videos = new OrderedMap();
  
  Channel() {}
  
  Channel(JSONObject paramJSONObject, String paramString, OoyalaAPIClient paramOoyalaAPIClient)
  {
    this(paramJSONObject, paramString, null, paramOoyalaAPIClient);
  }
  
  Channel(JSONObject paramJSONObject, String paramString, ChannelSet paramChannelSet, OoyalaAPIClient paramOoyalaAPIClient)
  {
    this._embedCode = paramString;
    this._api = paramOoyalaAPIClient;
    this._parent = paramChannelSet;
    update(paramJSONObject);
  }
  
  protected void addVideo(Video paramVideo)
  {
    this._videos.put(paramVideo.getEmbedCode(), paramVideo);
  }
  
  public int childrenCount()
  {
    return this._videos.size();
  }
  
  public List<String> embedCodesToAuthorize()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(this._embedCode);
    localArrayList.addAll(this._videos.keySet());
    return localArrayList;
  }
  
  public Video firstVideo()
  {
    if ((this._videos == null) || (this._videos.size() == 0)) {
      return null;
    }
    return (Video)this._videos.get(0);
  }
  
  public OrderedMap<String, Video> getAllAvailableChildren()
  {
    return this._videos;
  }
  
  public int getDuration()
  {
    int i = 0;
    Iterator localIterator = this._videos.iterator();
    while (localIterator.hasNext()) {
      i += ((Video)localIterator.next()).getDuration();
    }
    return i;
  }
  
  public String getNextChildren()
  {
    return this._nextChildren;
  }
  
  public ChannelSet getParent()
  {
    return this._parent;
  }
  
  public OrderedMap<String, Video> getVideos()
  {
    return getAllAvailableChildren();
  }
  
  public boolean hasMoreChildren()
  {
    return this._nextChildren != null;
  }
  
  public Video lastVideo()
  {
    if ((this._videos == null) || (this._videos.size() == 0)) {
      return null;
    }
    return (Video)this._videos.get(this._videos.size() - 1);
  }
  
  public Video nextVideo(Video paramVideo)
  {
    int i = this._videos.indexForValue(paramVideo);
    if (i >= 0)
    {
      i += 1;
      if (i < this._videos.size()) {}
    }
    else
    {
      if (this._parent == null) {
        return null;
      }
      return this._parent.nextVideo(this);
    }
    return (Video)this._videos.get(i);
  }
  
  public Video previousVideo(Video paramVideo)
  {
    int i = this._videos.indexForValue(paramVideo);
    if (i >= 0)
    {
      i -= 1;
      if (i >= 0) {}
    }
    else
    {
      if (this._parent == null) {
        return null;
      }
      return this._parent.previousVideo(this);
    }
    return (Video)this._videos.get(i);
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
            localObject1 = this._videos.iterator();
            if (!((Iterator)localObject1).hasNext()) {
              break label188;
            }
            ((Video)((Iterator)localObject1).next()).update(paramJSONObject);
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
        localObject1 = this._videos.iterator();
        if (((Iterator)localObject1).hasNext())
        {
          ((Video)((Iterator)localObject1).next()).update(paramJSONObject);
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
        localObject1 = this._videos.iterator();
        while (((Iterator)localObject1).hasNext()) {
          ((Video)((Iterator)localObject1).next()).update(paramJSONObject);
        }
        paramJSONObject = JSONUpdatableItem.ReturnState.STATE_MATCHED;
      }
      else if ((!((JSONObject)localObject1).isNull("content_type")) && (!((JSONObject)localObject1).getString("content_type").equals("Channel")))
      {
        System.out.println("ERROR: Attempted to initialize Channel with content_type: " + ((JSONObject)localObject1).getString("content_type"));
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
          System.out.println("ERROR: Attempted to initialize Channel with children == nil and next_children == nil: " + this._embedCode);
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
        if ((!localJSONObject.isNull("content_type")) && (localJSONObject.getString("content_type").equals("Video")))
        {
          Object localObject2 = new HashMap();
          localObject1 = localJSONObject.getString("embed_code");
          ((HashMap)localObject2).put(localObject1, localJSONObject);
          localJSONObject = new JSONObject((Map)localObject2);
          localObject2 = (Video)this._videos.get(localObject1);
          if (localObject2 == null) {
            addVideo(new Video(localJSONObject, (String)localObject1, this, this._api));
          } else {
            ((Video)localObject2).update(localJSONObject);
          }
        }
        else
        {
          System.out.println("ERROR: Invalid Video content_type: " + localJSONObject.getString("content_type"));
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
    return (Video)this._videos.get(paramString);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\item\Channel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */