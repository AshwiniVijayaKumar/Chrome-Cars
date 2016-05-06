package com.ooyala.android.item;

import com.ooyala.android.FCCTVRating;
import com.ooyala.android.OoyalaAPIClient;
import com.ooyala.android.util.OrderedMapValue;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class ContentItem
  implements AuthorizableItem, OrderedMapValue<String>, JSONUpdatableItem
{
  protected static final String CONTENT_TYPE_CHANNEL = "Channel";
  protected static final String CONTENT_TYPE_CHANNEL_SET = "MultiChannel";
  protected static final String CONTENT_TYPE_LIVE_STREAM = "LiveStream";
  protected static final String CONTENT_TYPE_VIDEO = "Video";
  protected static final String KEY_ADS = "ads";
  protected static final String KEY_AUTHORIZED = "authorized";
  protected static final String KEY_CHILDREN = "children";
  protected static final String KEY_CLOSED_CAPTIONS = "closed_captions";
  protected static final String KEY_CODE = "code";
  protected static final String KEY_CONTENT_TOKEN = "content_token";
  protected static final String KEY_CONTENT_TYPE = "content_type";
  protected static final String KEY_DESCRIPTION = "description";
  protected static final String KEY_DURATION = "duration";
  protected static final String KEY_EMBED_CODE = "embed_code";
  protected static final String KEY_EXTERNAL_ID = "external_id";
  protected static final String KEY_METADATA = "metadata";
  protected static final String KEY_METADATA_BASE = "base";
  protected static final String KEY_METADATA_MODULES = "modules";
  protected static final String KEY_METADATA_MODULE_TYPE = "type";
  protected static final String KEY_METADATA_TVRATING_CLICKTHROUGH_URL = "tvratingsurl";
  protected static final String KEY_METADATA_TVRATING_RATING = "tvrating";
  protected static final String KEY_METADATA_TVRATING_SUBRATINGS = "tvsubratings";
  protected static final String KEY_NEXT_CHILDREN = "next_children";
  protected static final String KEY_PROMO_IMAGE = "promo_image";
  protected static final String KEY_REQUIRE_HEARTBEAT = "require_heartbeat";
  protected static final String KEY_STREAMS = "streams";
  protected static final String KEY_THUMBNAIL_IMAGE = "thumbnail_image";
  protected static final String KEY_TITLE = "title";
  protected OoyalaAPIClient _api;
  protected int _authCode = -1;
  protected boolean _authorized = false;
  protected String _contentToken = null;
  protected String _description = null;
  protected String _embedCode = null;
  protected String _externalId = null;
  protected boolean _heartbeatRequired;
  protected Map<String, String> _metadata;
  protected Map<String, ModuleData> _moduleData;
  protected String _promoImageURL = null;
  protected String _title = null;
  protected FCCTVRating _tvRating;
  
  ContentItem() {}
  
  ContentItem(String paramString1, String paramString2, String paramString3)
  {
    this(paramString1, null, paramString2, paramString3);
  }
  
  ContentItem(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    this._embedCode = paramString1;
    this._contentToken = paramString2;
    this._title = paramString3;
    this._description = paramString4;
  }
  
  ContentItem(JSONObject paramJSONObject, String paramString, OoyalaAPIClient paramOoyalaAPIClient)
  {
    this._embedCode = paramString;
    this._api = paramOoyalaAPIClient;
    update(paramJSONObject);
  }
  
  public static ContentItem create(JSONObject paramJSONObject, String paramString, OoyalaAPIClient paramOoyalaAPIClient)
  {
    if ((paramJSONObject == null) || (paramString == null) || (paramJSONObject.isNull(paramString))) {}
    Object localObject;
    for (;;)
    {
      return null;
      try
      {
        localObject = paramJSONObject.getJSONObject(paramString);
        if (!((JSONObject)localObject).isNull("content_type"))
        {
          localObject = ((JSONObject)localObject).getString("content_type");
          if (localObject != null) {
            if ((((String)localObject).equals("Video")) || (((String)localObject).equals("LiveStream"))) {
              return new Video(paramJSONObject, paramString, paramOoyalaAPIClient);
            }
          }
        }
      }
      catch (JSONException paramJSONObject)
      {
        System.out.println("Create failed due to JSONException: " + paramJSONObject);
        return null;
      }
    }
    if (((String)localObject).equals("Channel")) {
      return new Channel(paramJSONObject, paramString, paramOoyalaAPIClient);
    }
    if (((String)localObject).equals("MultiChannel")) {
      return new ChannelSet(paramJSONObject, paramString, paramOoyalaAPIClient);
    }
    System.out.println("Unknown content_type: " + (String)localObject);
    return null;
  }
  
  public static ContentItem create(JSONObject paramJSONObject, List<String> paramList, OoyalaAPIClient paramOoyalaAPIClient)
  {
    if ((paramJSONObject == null) || (paramList == null) || (paramList.size() == 0)) {
      return null;
    }
    if (paramList.size() == 1) {
      return create(paramJSONObject, (String)paramList.get(0), paramOoyalaAPIClient);
    }
    return new DynamicChannel(paramJSONObject, paramList, paramOoyalaAPIClient);
  }
  
  public static String getAuthError(int paramInt)
  {
    if (paramInt == 0) {
      return "Video is authorized!";
    }
    if ((paramInt < 0) || (paramInt >= AuthorizableItem.authCodeDescription.length)) {
      return "Invalid Authorization Error Code";
    }
    return AuthorizableItem.authCodeDescription[paramInt];
  }
  
  public static List<String> getEmbedCodes(List<? extends ContentItem> paramList)
  {
    if (paramList == null)
    {
      paramList = null;
      return paramList;
    }
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = paramList.iterator();
    for (;;)
    {
      paramList = localArrayList;
      if (!localIterator.hasNext()) {
        break;
      }
      localArrayList.add(((ContentItem)localIterator.next()).getEmbedCode());
    }
  }
  
  public List<String> embedCodesToAuthorize()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(this._embedCode);
    return localArrayList;
  }
  
  public abstract Video firstVideo();
  
  public int getAuthCode()
  {
    return this._authCode;
  }
  
  String getContentToken()
  {
    return this._contentToken;
  }
  
  public String getDescription()
  {
    return this._description;
  }
  
  public abstract int getDuration();
  
  public String getEmbedCode()
  {
    return this._embedCode;
  }
  
  public String getExternalId()
  {
    return this._externalId;
  }
  
  public String getKey()
  {
    return this._embedCode;
  }
  
  public Map<String, String> getMetadata()
  {
    return this._metadata;
  }
  
  public Map<String, ModuleData> getModuleData()
  {
    return this._moduleData;
  }
  
  public String getPromoImageURL(int paramInt1, int paramInt2)
  {
    return this._promoImageURL;
  }
  
  public FCCTVRating getTVRating()
  {
    return this._tvRating;
  }
  
  public String getTitle()
  {
    return this._title;
  }
  
  public boolean isAuthorized()
  {
    return this._authorized;
  }
  
  public boolean isHeartbeatRequired()
  {
    return this._heartbeatRequired;
  }
  
  public JSONUpdatableItem.ReturnState update(JSONObject paramJSONObject)
  {
    if (paramJSONObject == null) {
      return JSONUpdatableItem.ReturnState.STATE_FAIL;
    }
    if ((this._embedCode == null) || (paramJSONObject.isNull(this._embedCode))) {
      return JSONUpdatableItem.ReturnState.STATE_UNMATCHED;
    }
    try
    {
      paramJSONObject = paramJSONObject.getJSONObject(this._embedCode);
      if (!paramJSONObject.isNull("authorized"))
      {
        this._authorized = paramJSONObject.getBoolean("authorized");
        if (!paramJSONObject.isNull("code")) {
          this._authCode = paramJSONObject.getInt("code");
        }
        if (!paramJSONObject.isNull("require_heartbeat")) {
          this._heartbeatRequired = paramJSONObject.getBoolean("require_heartbeat");
        }
        return JSONUpdatableItem.ReturnState.STATE_MATCHED;
      }
      if ((this._embedCode != null) && (!paramJSONObject.isNull("embed_code")) && (!this._embedCode.equals(paramJSONObject.getString("embed_code")))) {
        return JSONUpdatableItem.ReturnState.STATE_FAIL;
      }
      if (!paramJSONObject.isNull("embed_code")) {
        this._embedCode = paramJSONObject.getString("embed_code");
      }
      if (!paramJSONObject.isNull("external_id")) {
        this._externalId = paramJSONObject.getString("external_id");
      }
      if (!paramJSONObject.isNull("content_token")) {
        this._contentToken = paramJSONObject.getString("content_token");
      }
      if (!paramJSONObject.isNull("title")) {
        this._title = paramJSONObject.getString("title");
      }
      if (!paramJSONObject.isNull("description")) {
        this._description = paramJSONObject.getString("description");
      }
      if (!paramJSONObject.isNull("promo_image")) {
        this._promoImageURL = paramJSONObject.getString("promo_image");
      }
      if (paramJSONObject.has("base")) {
        this._metadata = ItemUtils.mapFromJSONObject(paramJSONObject.getJSONObject("base"));
      }
      if (paramJSONObject.has("modules"))
      {
        this._moduleData = new HashMap();
        paramJSONObject = paramJSONObject.getJSONObject("modules");
        Iterator localIterator = paramJSONObject.keys();
        while (localIterator.hasNext())
        {
          String str1 = (String)localIterator.next();
          Object localObject = paramJSONObject.getJSONObject(str1);
          String str2 = ((JSONObject)localObject).optString("type");
          localObject = ItemUtils.mapFromJSONObject(((JSONObject)localObject).getJSONObject("metadata"));
          this._moduleData.put(str1, new ModuleData(str1, str2, (Map)localObject));
        }
      }
      if (this._metadata == null) {
        break label482;
      }
    }
    catch (JSONException paramJSONObject)
    {
      System.out.println("JSONException: " + paramJSONObject);
      return JSONUpdatableItem.ReturnState.STATE_FAIL;
    }
    if (this._metadata.containsKey("tvrating")) {
      this._tvRating = new FCCTVRating((String)this._metadata.get("tvrating"), (String)this._metadata.get("tvsubratings"), (String)this._metadata.get("tvratingsurl"));
    }
    label482:
    return JSONUpdatableItem.ReturnState.STATE_MATCHED;
  }
  
  public abstract Video videoFromEmbedCode(String paramString, Video paramVideo);
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\item\ContentItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */