package com.ooyala.android.item;

import android.os.AsyncTask;
import com.ooyala.android.OoyalaAPIClient;
import com.ooyala.android.apis.FetchPlaybackInfoCallback;
import com.ooyala.android.util.DebugMode;
import com.ooyala.android.util.IMatchObjectPredicate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Video
  extends ContentItem
  implements PlayableItem
{
  private static final String TAG = Video.class.getName();
  protected List<OoyalaManagedAdSpot> _ads = new ArrayList();
  protected ClosedCaptions _closedCaptions = null;
  protected int _duration = 0;
  protected boolean _live = false;
  protected Channel _parent = null;
  protected Set<Stream> _streams = new HashSet();
  
  Video() {}
  
  Video(JSONObject paramJSONObject, String paramString, OoyalaAPIClient paramOoyalaAPIClient)
  {
    this(paramJSONObject, paramString, null, paramOoyalaAPIClient);
  }
  
  Video(JSONObject paramJSONObject, String paramString, Channel paramChannel, OoyalaAPIClient paramOoyalaAPIClient)
  {
    this._embedCode = paramString;
    this._api = paramOoyalaAPIClient;
    this._parent = paramChannel;
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
    if (hasAds())
    {
      Iterator localIterator = this._ads.iterator();
      while (localIterator.hasNext()) {
        ((OoyalaManagedAdSpot)localIterator.next()).fetchPlaybackInfo();
      }
    }
    if (this._closedCaptions != null) {
      this._closedCaptions.fetchClosedCaptionsInfo();
    }
    return true;
  }
  
  public void filterAds(IMatchObjectPredicate<OoyalaManagedAdSpot> paramIMatchObjectPredicate)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this._ads.iterator();
    while (localIterator.hasNext())
    {
      OoyalaManagedAdSpot localOoyalaManagedAdSpot = (OoyalaManagedAdSpot)localIterator.next();
      if (paramIMatchObjectPredicate.matches(localOoyalaManagedAdSpot)) {
        localArrayList.add(localOoyalaManagedAdSpot);
      }
    }
    this._ads = localArrayList;
  }
  
  public Video firstVideo()
  {
    return this;
  }
  
  public List<OoyalaManagedAdSpot> getAds()
  {
    return this._ads;
  }
  
  public ClosedCaptions getClosedCaptions()
  {
    return this._closedCaptions;
  }
  
  public int getDuration()
  {
    return this._duration;
  }
  
  public Channel getParent()
  {
    return this._parent;
  }
  
  public Stream getStream()
  {
    return Stream.bestStream(this._streams, false);
  }
  
  public Set<Stream> getStreams()
  {
    return this._streams;
  }
  
  public boolean hasAds()
  {
    return (this._ads != null) && (this._ads.size() > 0);
  }
  
  public boolean hasClosedCaptions()
  {
    return (this._closedCaptions != null) && (this._closedCaptions.getLanguages().size() > 0);
  }
  
  public void insertAd(OoyalaManagedAdSpot paramOoyalaManagedAdSpot)
  {
    if (this._ads != null) {}
    for (boolean bool = true;; bool = false)
    {
      DebugMode.assertCondition(bool, TAG, "ads is null");
      this._ads.add(paramOoyalaManagedAdSpot);
      Collections.sort(this._ads);
      return;
    }
  }
  
  public boolean isLive()
  {
    return this._live;
  }
  
  public Video nextVideo()
  {
    if (this._parent == null) {
      return null;
    }
    return this._parent.nextVideo(this);
  }
  
  public Video previousVideo()
  {
    if (this._parent == null) {
      return null;
    }
    return this._parent.previousVideo(this);
  }
  
  public void setClosedCaptions(ClosedCaptions paramClosedCaptions)
  {
    this._closedCaptions = paramClosedCaptions;
  }
  
  public JSONUpdatableItem.ReturnState update(JSONObject paramJSONObject)
  {
    switch (super.update(paramJSONObject))
    {
    }
    for (;;)
    {
      int i;
      try
      {
        paramJSONObject = paramJSONObject.getJSONObject(this._embedCode);
        if (!paramJSONObject.isNull("duration")) {
          this._duration = paramJSONObject.getInt("duration");
        }
        if (!paramJSONObject.isNull("content_type")) {
          this._live = paramJSONObject.getString("content_type").equals("LiveStream");
        }
        Object localObject;
        if ((!paramJSONObject.isNull("authorized")) && (paramJSONObject.getBoolean("authorized")) && (!paramJSONObject.isNull("streams")))
        {
          paramJSONObject = paramJSONObject.getJSONArray("streams");
          if (paramJSONObject.length() > 0)
          {
            this._streams.clear();
            i = 0;
            if (i < paramJSONObject.length())
            {
              localObject = new Stream(paramJSONObject.getJSONObject(i));
              if (this._live) {
                break label418;
              }
              if (((Stream)localObject).isLiveStream())
              {
                break label418;
                this._live = bool;
                if (localObject != null) {
                  this._streams.add(localObject);
                }
                i += 1;
                continue;
                return JSONUpdatableItem.ReturnState.STATE_FAIL;
                return JSONUpdatableItem.ReturnState.STATE_UNMATCHED;
              }
              bool = false;
              continue;
            }
          }
          return JSONUpdatableItem.ReturnState.STATE_MATCHED;
        }
        if (!paramJSONObject.isNull("ads"))
        {
          localObject = paramJSONObject.getJSONArray("ads");
          if (((JSONArray)localObject).length() > 0)
          {
            this._ads.clear();
            i = 0;
            if (i < ((JSONArray)localObject).length())
            {
              OoyalaManagedAdSpot localOoyalaManagedAdSpot = OoyalaManagedAdSpot.create(((JSONArray)localObject).getJSONObject(i), this._api);
              localOoyalaManagedAdSpot.setPriority(i);
              if (localOoyalaManagedAdSpot != null) {
                this._ads.add(localOoyalaManagedAdSpot);
              } else {
                DebugMode.logE(getClass().getName(), "Unable to create ad.");
              }
            }
          }
        }
      }
      catch (JSONException paramJSONObject)
      {
        DebugMode.logE(getClass().getName(), "JSONException: " + paramJSONObject);
        return JSONUpdatableItem.ReturnState.STATE_FAIL;
      }
      if (!paramJSONObject.isNull("closed_captions"))
      {
        this._closedCaptions = null;
        paramJSONObject = paramJSONObject.getJSONArray("closed_captions");
        if (paramJSONObject.length() > 0) {
          this._closedCaptions = new ClosedCaptions((JSONObject)paramJSONObject.get(0));
        }
      }
      return JSONUpdatableItem.ReturnState.STATE_MATCHED;
      label418:
      boolean bool = true;
      continue;
      i += 1;
    }
  }
  
  public Video videoFromEmbedCode(String paramString, Video paramVideo)
  {
    if (this._embedCode.equals(paramString)) {
      return this;
    }
    return null;
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
      return Boolean.valueOf(Video.this.fetchPlaybackInfo());
    }
    
    protected void onPostExecute(Boolean paramBoolean)
    {
      this._callback.callback(paramBoolean.booleanValue());
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\item\Video.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */