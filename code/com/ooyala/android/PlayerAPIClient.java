package com.ooyala.android;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import com.ooyala.android.apis.AuthorizeCallback;
import com.ooyala.android.apis.ContentTreeCallback;
import com.ooyala.android.apis.ContentTreeNextCallback;
import com.ooyala.android.apis.MetadataFetchedCallback;
import com.ooyala.android.configuration.Options;
import com.ooyala.android.item.AuthorizableItem;
import com.ooyala.android.item.ContentItem;
import com.ooyala.android.item.PaginatedParentItem;
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

class PlayerAPIClient
{
  protected static final String AUTHORIZE_CONTENT_ID_URI = "/sas/player_api/v%s/authorization/content_id/%s/%s";
  protected static final String AUTHORIZE_EMBED_CODE_URI = "/sas/player_api/v%s/authorization/embed_code/%s/%s";
  protected static final String AUTHORIZE_HEARTBEAT_URI = "/sas/player_api/v%s/auth_heartbeat/pcode/%s/auth_token/%s";
  protected static final String AUTHORIZE_PUBLIC_KEY_B64 = "MCgCIQD1PX86jvLr5bB3b5IFEze7TiWGEaRSHl5Ls7/3AKO5IwIDAQAB";
  protected static final String AUTHORIZE_PUBLIC_KEY_NAME = "sas_public_key";
  protected static final int AUTHORIZE_SIGNATURE_DIGEST_LENGTH = 20;
  protected static final String BACKLOT_URI_PREFIX = "/v2";
  protected static final String CONTENT_TREE_BY_EXTERNAL_ID_URI = "/player_api/v%s/content_tree/external_id/%s/%s";
  protected static final String CONTENT_TREE_NEXT_URI = "/player_api/v%s/content_tree/next/%s/%s";
  protected static final String CONTENT_TREE_URI = "/player_api/v%s/content_tree/embed_code/%s/%s";
  public static final String HOOK = "-hook";
  protected static final String KEY_AD_SET_CODE = "adSetCode";
  protected static final String KEY_AUTHORIZATION_DATA = "authorization_data";
  protected static final String KEY_AUTHORIZED = "authorized";
  protected static final String KEY_AUTH_TOKEN = "auth_token";
  protected static final String KEY_CHILDREN = "children";
  protected static final String KEY_CODE = "code";
  protected static final String KEY_CONTENT_TREE = "content_tree";
  protected static final String KEY_DEVICE = "device";
  protected static final String KEY_DOMAIN = "domain";
  protected static final String KEY_EMBED_CODE = "embed_code";
  protected static final String KEY_ERRORS = "errors";
  protected static final String KEY_EXPIRES = "expires";
  protected static final String KEY_HEARTBEAT_DATA = "heartbeat_data";
  protected static final String KEY_HEARTBEAT_INTERVAL = "heartbeat_interval";
  protected static final String KEY_HEIGHT = "height";
  protected static final String KEY_MESSAGE = "message";
  protected static final String KEY_METADATA = "metadata";
  protected static final String KEY_USER_INFO = "user_info";
  protected static final String KEY_WIDTH = "width";
  protected static final String METADATA_EMBED_CODE_URI = "/player_api/v%s/metadata/embed_code/%s/%s";
  protected static final String SEPARATOR_URL_IDS = ",";
  private String _authToken = null;
  private int _connectionTimeoutInMillisecond = 0;
  protected Context _context;
  protected PlayerDomain _domain = null;
  protected EmbedTokenGenerator _embedTokenGenerator;
  protected int _heartbeatInterval = 300;
  protected int _height = -1;
  private boolean _isFetchingMoreChildren = false;
  private boolean _isHook;
  protected String _pcode = null;
  private int _readTimeoutInMillisecond = 0;
  private UserInfo _userInfo;
  protected int _width = -1;
  
  public PlayerAPIClient() {}
  
  public PlayerAPIClient(String paramString, PlayerDomain paramPlayerDomain, EmbedTokenGenerator paramEmbedTokenGenerator)
  {
    this(paramString, paramPlayerDomain, paramEmbedTokenGenerator, null);
  }
  
  public PlayerAPIClient(String paramString, PlayerDomain paramPlayerDomain, EmbedTokenGenerator paramEmbedTokenGenerator, Options paramOptions)
  {
    this._pcode = paramString;
    this._domain = paramPlayerDomain;
    this._embedTokenGenerator = paramEmbedTokenGenerator;
    if (paramOptions != null)
    {
      this._connectionTimeoutInMillisecond = paramOptions.getConnectionTimeoutInMillisecond();
      this._readTimeoutInMillisecond = paramOptions.getReadTimeoutInMillisecond();
    }
  }
  
  private Map<String, String> authorizeParams(List<String> paramList)
  {
    HashMap localHashMap = new HashMap();
    StringBuilder localStringBuilder = new StringBuilder().append(Utils.device());
    if (this._isHook) {}
    for (String str = "-hook";; str = "")
    {
      localHashMap.put("device", str);
      localHashMap.put("domain", this._domain.toString());
      if (getAuthToken().length() > 0) {
        localHashMap.put("auth_token", getAuthToken());
      }
      if (this._embedTokenGenerator != null) {
        localHashMap.put("embedToken", Utils.blockingGetEmbedTokenForEmbedCodes(this._embedTokenGenerator, paramList));
      }
      return localHashMap;
    }
  }
  
  private Map<String, String> contentTreeParams(Map<String, String> paramMap)
  {
    HashMap localHashMap = new HashMap();
    if (paramMap != null) {
      localHashMap.putAll(paramMap);
    }
    StringBuilder localStringBuilder = new StringBuilder().append(Utils.device());
    if (this._isHook) {}
    for (paramMap = "-hook";; paramMap = "")
    {
      localHashMap.put("device", paramMap);
      if ((this._height > 0) && (this._width > 0))
      {
        localHashMap.put("width", Integer.toString(this._width));
        localHashMap.put("height", Integer.toString(this._height));
      }
      return localHashMap;
    }
  }
  
  private JSONObject getContentTreeData(JSONObject paramJSONObject)
    throws OoyalaException
  {
    if (paramJSONObject == null) {
      throw new OoyalaException(OoyalaException.OoyalaErrorCode.ERROR_CONTENT_TREE_INVALID, "Content Tree response invalid (nil).");
    }
    for (;;)
    {
      JSONObject localJSONObject;
      try
      {
        if (paramJSONObject.isNull("errors")) {
          break;
        }
        localJSONObject = paramJSONObject.getJSONObject("errors");
        if ((localJSONObject.isNull("code")) || (localJSONObject.getInt("code") == 0)) {
          break;
        }
        OoyalaException.OoyalaErrorCode localOoyalaErrorCode = OoyalaException.OoyalaErrorCode.ERROR_CONTENT_TREE_INVALID;
        if (localJSONObject.isNull("message"))
        {
          paramJSONObject = "";
          throw new OoyalaException(localOoyalaErrorCode, paramJSONObject);
        }
      }
      catch (JSONException paramJSONObject)
      {
        System.out.println("JSONException: " + paramJSONObject);
        throw new OoyalaException(OoyalaException.OoyalaErrorCode.ERROR_CONTENT_TREE_INVALID, "Content tree response invalid (exception).");
      }
      paramJSONObject = localJSONObject.getString("message");
    }
    if (paramJSONObject.isNull("content_tree")) {
      throw new OoyalaException(OoyalaException.OoyalaErrorCode.ERROR_CONTENT_TREE_INVALID, "Content tree data does not exist.");
    }
    paramJSONObject = paramJSONObject.getJSONObject("content_tree");
    return paramJSONObject;
  }
  
  private JSONObject verifyAuthorizeHeartbeatJSON(JSONObject paramJSONObject)
    throws OoyalaException
  {
    if (paramJSONObject == null) {
      throw new OoyalaException(OoyalaException.OoyalaErrorCode.ERROR_AUTHORIZATION_HEARTBEAT_FAILED, "response invalid (nil).");
    }
    if (paramJSONObject.isNull("message")) {
      throw new OoyalaException(OoyalaException.OoyalaErrorCode.ERROR_AUTHORIZATION_HEARTBEAT_FAILED, "response invalid (nil).");
    }
    try
    {
      if (!paramJSONObject.getString("message").equals("OK")) {
        throw new OoyalaException(OoyalaException.OoyalaErrorCode.ERROR_AUTHORIZATION_HEARTBEAT_FAILED, "response code (" + paramJSONObject.getString("message") + ").");
      }
    }
    catch (JSONException paramJSONObject)
    {
      throw new OoyalaException(OoyalaException.OoyalaErrorCode.ERROR_AUTHORIZATION_HEARTBEAT_FAILED, "response invalid (error).");
    }
    if (paramJSONObject.getInt("expires") < System.currentTimeMillis() / 1000L) {
      throw new OoyalaException(OoyalaException.OoyalaErrorCode.ERROR_AUTHORIZATION_HEARTBEAT_FAILED, "response expired.");
    }
    if (!paramJSONObject.isNull("auth_token")) {
      setAuthToken(paramJSONObject.getString("auth_token"));
    }
    return paramJSONObject;
  }
  
  private JSONObject verifyAuthorizeJSON(JSONObject paramJSONObject, List<String> paramList)
    throws OoyalaException
  {
    if (paramJSONObject == null) {
      throw new OoyalaException(OoyalaException.OoyalaErrorCode.ERROR_AUTHORIZATION_INVALID, "Authorization response invalid (nil).");
    }
    Object localObject;
    for (;;)
    {
      try
      {
        if (paramJSONObject.isNull("errors")) {
          break;
        }
        localObject = paramJSONObject.getJSONObject("errors");
        if ((((JSONObject)localObject).isNull("code")) || (((JSONObject)localObject).getInt("code") == 0)) {
          break;
        }
        paramList = OoyalaException.OoyalaErrorCode.ERROR_AUTHORIZATION_INVALID;
        if (((JSONObject)localObject).isNull("message"))
        {
          paramJSONObject = "";
          throw new OoyalaException(paramList, paramJSONObject);
        }
      }
      catch (JSONException paramJSONObject)
      {
        System.out.println("JSONException: " + paramJSONObject);
        throw new OoyalaException(OoyalaException.OoyalaErrorCode.ERROR_AUTHORIZATION_INVALID, "Authorization response invalid (exception).");
      }
      paramJSONObject = ((JSONObject)localObject).getString("message");
    }
    if (paramJSONObject.isNull("user_info")) {
      throw new OoyalaException(OoyalaException.OoyalaErrorCode.ERROR_AUTHORIZATION_INVALID, "User info data does not exist.");
    }
    if (paramJSONObject.isNull("authorization_data")) {
      throw new OoyalaException(OoyalaException.OoyalaErrorCode.ERROR_AUTHORIZATION_INVALID, "Authorization data does not exist.");
    }
    paramJSONObject = paramJSONObject.getJSONObject("authorization_data");
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      localObject = (String)paramList.next();
      if ((paramJSONObject.isNull((String)localObject)) || (paramJSONObject.getJSONObject((String)localObject).isNull("authorized"))) {
        throw new OoyalaException(OoyalaException.OoyalaErrorCode.ERROR_AUTHORIZATION_INVALID, "Authorization invalid for embed code: " + (String)localObject);
      }
    }
    return paramJSONObject;
  }
  
  private JSONObject verifyContentTreeObject(JSONObject paramJSONObject, List<String> paramList)
    throws OoyalaException
  {
    paramJSONObject = getContentTreeData(paramJSONObject);
    if ((paramJSONObject != null) && (paramList != null))
    {
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        String str = (String)paramList.next();
        if (paramJSONObject.isNull(str)) {
          throw new OoyalaException(OoyalaException.OoyalaErrorCode.ERROR_CONTENT_TREE_INVALID, "Content Tree response invalid (no key for: " + str + ").");
        }
      }
    }
    return paramJSONObject;
  }
  
  private JSONObject verifyContentTreeObject(JSONObject paramJSONObject, List<String> paramList1, List<String> paramList2)
    throws OoyalaException
  {
    paramJSONObject = getContentTreeData(paramJSONObject);
    if ((paramJSONObject != null) && (paramList1 != null))
    {
      JSONArray localJSONArray = paramJSONObject.names();
      if (((localJSONArray == null) || (localJSONArray.length() == 0)) && (paramList1.size() > 0)) {
        throw new OoyalaException(OoyalaException.OoyalaErrorCode.ERROR_CONTENT_TREE_INVALID, "Content Tree response did not contain any values.  Expected: " + paramList1.size());
      }
      int i = 0;
      try
      {
        while (i < localJSONArray.length())
        {
          paramList2.add(localJSONArray.getString(i));
          i += 1;
        }
        if (paramList2.size() == paramList1.size()) {
          break label221;
        }
      }
      catch (JSONException paramJSONObject)
      {
        System.out.println("JSONException: " + paramJSONObject);
        throw new OoyalaException(OoyalaException.OoyalaErrorCode.ERROR_CONTENT_TREE_INVALID, "Content tree response invalid (exception casting embedCode to String)");
      }
      throw new OoyalaException(OoyalaException.OoyalaErrorCode.ERROR_CONTENT_TREE_INVALID, "Content Tree response did not contain values for all external IDs. Found " + paramList2.size() + " of " + paramList1.size());
      label221:
      paramList1 = paramList2.iterator();
      while (paramList1.hasNext())
      {
        paramList2 = (String)paramList1.next();
        if (paramJSONObject.isNull(paramList2)) {
          throw new OoyalaException(OoyalaException.OoyalaErrorCode.ERROR_CONTENT_TREE_INVALID, "Content Tree response invalid (no key for: " + paramList2 + ").");
        }
      }
    }
    return paramJSONObject;
  }
  
  public Object authorize(AuthorizableItem paramAuthorizableItem, PlayerInfo paramPlayerInfo, AuthorizeCallback paramAuthorizeCallback)
  {
    return authorizeEmbedCodes(paramAuthorizableItem.embedCodesToAuthorize(), paramAuthorizableItem, paramPlayerInfo, paramAuthorizeCallback);
  }
  
  public Object authorize(AuthorizableItem paramAuthorizableItem, AuthorizeCallback paramAuthorizeCallback)
  {
    return authorizeEmbedCodes(paramAuthorizableItem.embedCodesToAuthorize(), paramAuthorizableItem, paramAuthorizeCallback);
  }
  
  public boolean authorize(AuthorizableItem paramAuthorizableItem)
    throws OoyalaException
  {
    return authorizeEmbedCodes(paramAuthorizableItem.embedCodesToAuthorize(), paramAuthorizableItem);
  }
  
  public boolean authorize(AuthorizableItem paramAuthorizableItem, PlayerInfo paramPlayerInfo)
    throws OoyalaException
  {
    return authorizeEmbedCodes(paramAuthorizableItem.embedCodesToAuthorize(), paramAuthorizableItem, paramPlayerInfo);
  }
  
  public Object authorizeEmbedCodes(List<String> paramList, AuthorizableItem paramAuthorizableItem, PlayerInfo paramPlayerInfo, AuthorizeCallback paramAuthorizeCallback)
  {
    paramAuthorizeCallback = new AuthorizeTask(paramAuthorizeCallback);
    paramAuthorizeCallback.execute(new Object[] { paramList, paramAuthorizableItem, paramPlayerInfo });
    return paramAuthorizeCallback;
  }
  
  public Object authorizeEmbedCodes(List<String> paramList, AuthorizableItem paramAuthorizableItem, AuthorizeCallback paramAuthorizeCallback)
  {
    paramAuthorizeCallback = new AuthorizeTask(paramAuthorizeCallback);
    paramAuthorizeCallback.execute(new Object[] { paramList, paramAuthorizableItem });
    return paramAuthorizeCallback;
  }
  
  public boolean authorizeEmbedCodes(List<String> paramList, AuthorizableItem paramAuthorizableItem)
    throws OoyalaException
  {
    return authorizeEmbedCodes(paramList, paramAuthorizableItem, new DefaultPlayerInfo());
  }
  
  public boolean authorizeEmbedCodes(List<String> paramList, AuthorizableItem paramAuthorizableItem, PlayerInfo paramPlayerInfo)
    throws OoyalaException
  {
    String str = String.format("/sas/player_api/v%s/authorization/embed_code/%s/%s", new Object[] { "1", this._pcode, Utils.join(paramList, ",") });
    Map localMap = authorizeParams(paramList);
    StringBuilder localStringBuilder = new StringBuilder().append(paramPlayerInfo.getDevice());
    if (this._isHook) {}
    for (Object localObject = "-hook";; localObject = "")
    {
      localMap.put("device", (String)localObject);
      if (paramPlayerInfo != null)
      {
        if (paramPlayerInfo.getSupportedFormats() != null) {
          localMap.put("supportedFormats", Utils.join(paramPlayerInfo.getSupportedFormats(), ","));
        }
        if (paramPlayerInfo.getSupportedProfiles() != null) {
          localMap.put("profiles", Utils.join(paramPlayerInfo.getSupportedProfiles(), ","));
        }
        if (paramPlayerInfo.getMaxHeight() > 0) {
          localMap.put("maxHeight", Integer.toString(paramPlayerInfo.getMaxHeight()));
        }
        if (paramPlayerInfo.getMaxWidth() > 0) {
          localMap.put("maxWidth", Integer.toString(paramPlayerInfo.getMaxWidth()));
        }
        if (paramPlayerInfo.getMaxBitrate() > 0) {
          localMap.put("br", Integer.toString(paramPlayerInfo.getMaxBitrate()));
        }
      }
      paramPlayerInfo = OoyalaAPIHelper.objectForAPI(Environment.AUTHORIZE_HOST, str, localMap, this._connectionTimeoutInMillisecond, this._readTimeoutInMillisecond);
      if (paramPlayerInfo != null) {
        break;
      }
      throw new OoyalaException(OoyalaException.OoyalaErrorCode.ERROR_AUTHORIZATION_FAILED, "Authorization connection timed out.");
    }
    try
    {
      paramList = verifyAuthorizeJSON(paramPlayerInfo, paramList);
      if (!paramPlayerInfo.isNull("auth_token")) {
        setAuthToken(paramPlayerInfo.getString("auth_token"));
      }
      if (!paramPlayerInfo.isNull("heartbeat_data"))
      {
        localObject = paramPlayerInfo.getJSONObject("heartbeat_data");
        if (!((JSONObject)localObject).isNull("heartbeat_interval")) {
          this._heartbeatInterval = ((JSONObject)localObject).getInt("heartbeat_interval");
        }
      }
      if (!paramPlayerInfo.isNull("user_info")) {
        this._userInfo = new UserInfo(paramPlayerInfo.getJSONObject("user_info"));
      }
      if (paramAuthorizableItem != null) {
        paramAuthorizableItem.update(paramList);
      }
      return true;
    }
    catch (OoyalaException paramList)
    {
      System.out.println("Unable to authorize: " + paramList);
      throw paramList;
    }
    catch (JSONException paramList)
    {
      System.out.println("JSONException: " + paramList);
      throw new OoyalaException(OoyalaException.OoyalaErrorCode.ERROR_AUTHORIZATION_INVALID, "Authorization response invalid (exception).");
    }
  }
  
  public boolean authorizeHeartbeat(String paramString)
    throws OoyalaException
  {
    String str = String.format("/sas/player_api/v%s/auth_heartbeat/pcode/%s/auth_token/%s", new Object[] { "1", this._pcode, getAuthToken() });
    HashMap localHashMap = new HashMap();
    localHashMap.put("embed_code", paramString);
    paramString = OoyalaAPIHelper.objectForAPI(Environment.AUTHORIZE_HOST, str, localHashMap, this._connectionTimeoutInMillisecond, this._readTimeoutInMillisecond);
    try
    {
      paramString = verifyAuthorizeHeartbeatJSON(paramString);
      return paramString != null;
    }
    catch (OoyalaException paramString)
    {
      System.out.println("Unable to authorize: " + paramString);
      throw paramString;
    }
  }
  
  public void cancel(Object paramObject)
  {
    ((AsyncTask)paramObject).cancel(true);
  }
  
  public ContentItem contentTree(List<String> paramList)
    throws OoyalaException
  {
    return contentTreeWithAdSet(paramList, null);
  }
  
  public Object contentTree(List<String> paramList, ContentTreeCallback paramContentTreeCallback)
  {
    return contentTreeWithAdSet(paramList, null, paramContentTreeCallback);
  }
  
  public ContentItem contentTreeByExternalIds(List<String> paramList)
    throws OoyalaException
  {
    Object localObject1 = null;
    Object localObject2 = String.format("/player_api/v%s/content_tree/external_id/%s/%s", new Object[] { "1", this._pcode, Utils.join(paramList, ",") });
    localObject2 = OoyalaAPIHelper.objectForAPI(Environment.CONTENT_TREE_HOST, (String)localObject2, contentTreeParams(null), this._connectionTimeoutInMillisecond, this._readTimeoutInMillisecond);
    if (localObject2 == null) {
      paramList = (List<String>)localObject1;
    }
    for (;;)
    {
      return paramList;
      localObject1 = new ArrayList();
      try
      {
        paramList = verifyContentTreeObject((JSONObject)localObject2, paramList, (List)localObject1);
        localObject1 = ContentItem.create(paramList, (List)localObject1, new OoyalaAPIClient(this));
        paramList = (List<String>)localObject1;
        if (localObject1 != null) {
          continue;
        }
        throw new OoyalaException(OoyalaException.OoyalaErrorCode.ERROR_CONTENT_TREE_INVALID, "Unknown Content Type");
      }
      catch (OoyalaException paramList)
      {
        System.out.println("Unable to create externalId objects: " + paramList);
        throw paramList;
      }
    }
  }
  
  public Object contentTreeByExternalIds(List<String> paramList, ContentTreeCallback paramContentTreeCallback)
  {
    return contentTreeByExternalIdsWithAdSetCode(paramList, null, paramContentTreeCallback);
  }
  
  public Object contentTreeByExternalIdsWithAdSetCode(List<String> paramList, String paramString, ContentTreeCallback paramContentTreeCallback)
  {
    paramString = new ContentTreeByExternalIdsTask(paramContentTreeCallback);
    paramContentTreeCallback = new ContentTreeTaskParam(null);
    paramContentTreeCallback.idList = paramList;
    paramString.execute(new ContentTreeTaskParam[] { paramContentTreeCallback });
    return paramString;
  }
  
  public PaginatedItemResponse contentTreeNext(PaginatedParentItem paramPaginatedParentItem)
  {
    int i = 0;
    if (!paramPaginatedParentItem.hasMoreChildren()) {}
    Object localObject1;
    do
    {
      return null;
      localObject1 = String.format("/player_api/v%s/content_tree/next/%s/%s", new Object[] { "1", this._pcode, paramPaginatedParentItem.getNextChildren() });
      localObject1 = OoyalaAPIHelper.objectForAPI(Environment.CONTENT_TREE_HOST, (String)localObject1, contentTreeParams(null), this._connectionTimeoutInMillisecond, this._readTimeoutInMillisecond);
    } while (localObject1 == null);
    Object localObject2 = new ArrayList();
    ((List)localObject2).add(paramPaginatedParentItem.getNextChildren());
    try
    {
      localObject1 = verifyContentTreeObject((JSONObject)localObject1, (List)localObject2);
      if (((JSONObject)localObject1).isNull(paramPaginatedParentItem.getNextChildren()))
      {
        System.out.println("Could not find token in content_tree_next response.");
        return null;
      }
    }
    catch (Exception paramPaginatedParentItem)
    {
      System.out.println("Unable to create next objects: " + paramPaginatedParentItem);
      return null;
    }
    try
    {
      localObject1 = ((JSONObject)localObject1).getJSONObject(paramPaginatedParentItem.getNextChildren());
      localObject2 = new JSONObject();
      ((JSONObject)localObject2).put(paramPaginatedParentItem.getEmbedCode(), localObject1);
      int j = paramPaginatedParentItem.childrenCount();
      paramPaginatedParentItem.update((JSONObject)localObject2);
      if (((JSONObject)localObject1).isNull("children")) {}
      for (;;)
      {
        return new PaginatedItemResponse(j, i);
        i = ((JSONObject)localObject1).getJSONArray("children").length();
      }
      return null;
    }
    catch (JSONException paramPaginatedParentItem)
    {
      System.out.println("Unable to create next objects due to JSON Exception: " + paramPaginatedParentItem);
    }
  }
  
  public Object contentTreeNext(PaginatedParentItem paramPaginatedParentItem, ContentTreeNextCallback paramContentTreeNextCallback)
  {
    paramContentTreeNextCallback = new ContentTreeNextTask(paramContentTreeNextCallback);
    paramContentTreeNextCallback.execute(new Object[] { paramPaginatedParentItem });
    return paramContentTreeNextCallback;
  }
  
  public ContentItem contentTreeWithAdSet(List<String> paramList, String paramString)
    throws OoyalaException
  {
    HashMap localHashMap = null;
    if (paramString != null)
    {
      localHashMap = new HashMap(1);
      localHashMap.put("adSetCode", paramString);
    }
    paramString = String.format("/player_api/v%s/content_tree/embed_code/%s/%s", new Object[] { "1", this._pcode, Utils.join(paramList, ",") });
    paramString = OoyalaAPIHelper.objectForAPI(Environment.CONTENT_TREE_HOST, paramString, contentTreeParams(localHashMap), this._connectionTimeoutInMillisecond, this._readTimeoutInMillisecond);
    try
    {
      paramString = verifyContentTreeObject(paramString, paramList);
      paramList = ContentItem.create(paramString, paramList, new OoyalaAPIClient(this));
      if (paramList == null) {
        throw new OoyalaException(OoyalaException.OoyalaErrorCode.ERROR_CONTENT_TREE_INVALID, "Unknown Content Type");
      }
    }
    catch (OoyalaException paramList)
    {
      System.out.println("Unable to create objects: " + paramList);
      throw paramList;
    }
    return paramList;
  }
  
  public Object contentTreeWithAdSet(List<String> paramList, String paramString, ContentTreeCallback paramContentTreeCallback)
  {
    paramContentTreeCallback = new ContentTreeTask(paramContentTreeCallback);
    ContentTreeTaskParam localContentTreeTaskParam = new ContentTreeTaskParam(null);
    localContentTreeTaskParam.idList = paramList;
    localContentTreeTaskParam.adSetCode = paramString;
    paramContentTreeCallback.execute(new ContentTreeTaskParam[] { localContentTreeTaskParam });
    return paramContentTreeCallback;
  }
  
  public boolean fetchMetadata(ContentItem paramContentItem, String paramString)
    throws OoyalaException
  {
    return fetchMetadataForEmbedCodesWithAdSet(paramContentItem.embedCodesToAuthorize(), paramString, paramContentItem);
  }
  
  public boolean fetchMetadataForEmbedCodesWithAdSet(List<String> paramList, String paramString, AuthorizableItem paramAuthorizableItem)
    throws OoyalaException
  {
    HashMap localHashMap = null;
    if (paramString != null)
    {
      localHashMap = new HashMap(1);
      localHashMap.put("adSetCode", paramString);
    }
    paramList = String.format("/player_api/v%s/metadata/embed_code/%s/%s", new Object[] { "1", this._pcode, Utils.join(paramList, ",") });
    paramList = OoyalaAPIHelper.objectForAPI(Environment.METADATA_HOST, paramList, contentTreeParams(localHashMap), this._connectionTimeoutInMillisecond, this._readTimeoutInMillisecond);
    if (paramList == null) {
      throw new OoyalaException(OoyalaException.OoyalaErrorCode.ERROR_METADATA_FETCH_FAILED, "Empty metadata response");
    }
    try
    {
      if (paramList.getJSONObject("errors").getInt("code") != 0) {
        throw new OoyalaException(OoyalaException.OoyalaErrorCode.ERROR_METADATA_FETCH_FAILED, "Non-zero metadata response code");
      }
    }
    catch (JSONException paramList)
    {
      throw new OoyalaException(OoyalaException.OoyalaErrorCode.ERROR_METADATA_FETCH_FAILED, "Failed to parse metadata");
    }
    ((ContentItem)paramAuthorizableItem).update(paramList.getJSONObject("metadata"));
    return true;
  }
  
  public boolean fetchMoreChildrenForPaginatedParentItem(PaginatedParentItem paramPaginatedParentItem, PaginatedItemListener paramPaginatedItemListener)
  {
    if ((!paramPaginatedParentItem.hasMoreChildren()) || (this._isFetchingMoreChildren)) {
      return false;
    }
    this._isFetchingMoreChildren = true;
    new Thread(new NextChildrenRunner(paramPaginatedParentItem, paramPaginatedItemListener)).start();
    return true;
  }
  
  public String getAuthToken()
  {
    if (this._authToken == null) {
      if (this._context == null) {
        break label44;
      }
    }
    label44:
    for (this._authToken = this._context.getSharedPreferences("com.ooyala.android_preferences", 4).getString("authToken", "");; this._authToken = "") {
      return this._authToken;
    }
  }
  
  public int getConnectionTimeoutInMillisecond()
  {
    return this._connectionTimeoutInMillisecond;
  }
  
  public PlayerDomain getDomain()
  {
    return this._domain;
  }
  
  public int getHeartbeatInterval()
  {
    return this._heartbeatInterval;
  }
  
  public String getPcode()
  {
    return this._pcode;
  }
  
  public int getReadTimeoutInMillisecond()
  {
    return this._readTimeoutInMillisecond;
  }
  
  public UserInfo getUserInfo()
  {
    return this._userInfo;
  }
  
  public Object metadata(ContentItem paramContentItem, String paramString, MetadataFetchedCallback paramMetadataFetchedCallback)
  {
    paramMetadataFetchedCallback = new MetadataFetchTask(paramMetadataFetchedCallback);
    paramMetadataFetchedCallback.execute(new MetadataFetchTaskParam[] { new MetadataFetchTaskParam(paramContentItem, paramString, null) });
    return paramMetadataFetchedCallback;
  }
  
  void setAuthToken(String paramString)
  {
    this._authToken = paramString;
    if (this._context != null)
    {
      SharedPreferences.Editor localEditor = this._context.getSharedPreferences("com.ooyala.android_preferences", 4).edit();
      localEditor.putString("authToken", paramString);
      localEditor.commit();
    }
  }
  
  public void setContext(Context paramContext)
  {
    this._context = paramContext;
  }
  
  public void setHook()
  {
    this._isHook = true;
  }
  
  private class AuthorizeTask
    extends AsyncTask<Object, Integer, Boolean>
  {
    protected AuthorizeCallback _callback = null;
    protected OoyalaException _error = null;
    
    public AuthorizeTask(AuthorizeCallback paramAuthorizeCallback)
    {
      this._callback = paramAuthorizeCallback;
    }
    
    protected Boolean doInBackground(Object... paramVarArgs)
    {
      if (paramVarArgs.length < 2) {
        return Boolean.valueOf(false);
      }
      if (!(paramVarArgs[0] instanceof List)) {
        return Boolean.valueOf(false);
      }
      List localList = (List)paramVarArgs[0];
      if ((paramVarArgs[1] instanceof AuthorizableItem)) {}
      for (AuthorizableItem localAuthorizableItem = (AuthorizableItem)paramVarArgs[1];; localAuthorizableItem = null) {
        switch (paramVarArgs.length)
        {
        default: 
          return Boolean.valueOf(false);
        }
      }
      boolean bool;
      try
      {
        bool = PlayerAPIClient.this.authorizeEmbedCodes(localList, localAuthorizableItem);
        return Boolean.valueOf(bool);
      }
      catch (OoyalaException paramVarArgs)
      {
        this._error = paramVarArgs;
        return Boolean.valueOf(false);
      }
      if ((paramVarArgs[2] instanceof PlayerInfo)) {}
      for (paramVarArgs = (PlayerInfo)paramVarArgs[2];; paramVarArgs = null) {
        try
        {
          bool = PlayerAPIClient.this.authorizeEmbedCodes(localList, localAuthorizableItem, paramVarArgs);
          return Boolean.valueOf(bool);
        }
        catch (OoyalaException paramVarArgs)
        {
          this._error = paramVarArgs;
        }
      }
      return Boolean.valueOf(false);
    }
    
    protected void onPostExecute(Boolean paramBoolean)
    {
      this._callback.callback(paramBoolean.booleanValue(), this._error);
    }
  }
  
  private class ContentTreeByExternalIdsTask
    extends PlayerAPIClient.ContentTreeTask
  {
    public ContentTreeByExternalIdsTask(ContentTreeCallback paramContentTreeCallback)
    {
      super(paramContentTreeCallback);
    }
    
    protected ContentItem doInBackground(PlayerAPIClient.ContentTreeTaskParam... paramVarArgs)
    {
      if ((paramVarArgs.length == 0) || (paramVarArgs[0] == null) || (paramVarArgs[0].idList == null) || (paramVarArgs[0].idList.isEmpty())) {
        return null;
      }
      try
      {
        paramVarArgs = PlayerAPIClient.this.contentTreeByExternalIds(paramVarArgs[0].idList);
        return paramVarArgs;
      }
      catch (OoyalaException paramVarArgs)
      {
        this._error = paramVarArgs;
      }
      return null;
    }
  }
  
  private class ContentTreeNextTask
    extends AsyncTask<Object, Integer, PaginatedItemResponse>
  {
    protected ContentTreeNextCallback _callback = null;
    protected OoyalaException _error = null;
    
    public ContentTreeNextTask(ContentTreeNextCallback paramContentTreeNextCallback)
    {
      this._callback = paramContentTreeNextCallback;
    }
    
    protected PaginatedItemResponse doInBackground(Object... paramVarArgs)
    {
      if ((paramVarArgs.length < 1) || (paramVarArgs[0] == null) || (!(paramVarArgs[0] instanceof PaginatedParentItem))) {
        return null;
      }
      return PlayerAPIClient.this.contentTreeNext((PaginatedParentItem)paramVarArgs[1]);
    }
    
    protected void onPostExecute(PaginatedItemResponse paramPaginatedItemResponse)
    {
      this._callback.callback(paramPaginatedItemResponse, this._error);
    }
  }
  
  private class ContentTreeTask
    extends AsyncTask<PlayerAPIClient.ContentTreeTaskParam, Integer, ContentItem>
  {
    protected ContentTreeCallback _callback = null;
    protected OoyalaException _error = null;
    
    public ContentTreeTask(ContentTreeCallback paramContentTreeCallback)
    {
      this._callback = paramContentTreeCallback;
    }
    
    protected ContentItem doInBackground(PlayerAPIClient.ContentTreeTaskParam... paramVarArgs)
    {
      if ((paramVarArgs.length == 0) || (paramVarArgs[0] == null) || (paramVarArgs[0].idList == null) || (paramVarArgs[0].idList.isEmpty())) {
        return null;
      }
      try
      {
        paramVarArgs = PlayerAPIClient.this.contentTreeWithAdSet(paramVarArgs[0].idList, paramVarArgs[0].adSetCode);
        return paramVarArgs;
      }
      catch (OoyalaException paramVarArgs)
      {
        this._error = paramVarArgs;
      }
      return null;
    }
    
    protected void onPostExecute(ContentItem paramContentItem)
    {
      this._callback.callback(paramContentItem, this._error);
    }
  }
  
  private class ContentTreeTaskParam
  {
    public String adSetCode;
    public List<String> idList;
    
    private ContentTreeTaskParam() {}
  }
  
  private class MetadataFetchTask
    extends AsyncTask<PlayerAPIClient.MetadataFetchTaskParam, Integer, Boolean>
  {
    protected MetadataFetchedCallback _callback = null;
    protected OoyalaException _error = null;
    
    public MetadataFetchTask(MetadataFetchedCallback paramMetadataFetchedCallback)
    {
      this._callback = paramMetadataFetchedCallback;
    }
    
    protected Boolean doInBackground(PlayerAPIClient.MetadataFetchTaskParam... paramVarArgs)
    {
      if ((paramVarArgs.length == 0) || (paramVarArgs[0] == null) || (paramVarArgs[0].item == null)) {
        return Boolean.valueOf(false);
      }
      try
      {
        boolean bool = PlayerAPIClient.this.fetchMetadata(paramVarArgs[0].item, paramVarArgs[0].adSetCode);
        return Boolean.valueOf(bool);
      }
      catch (OoyalaException paramVarArgs)
      {
        this._error = paramVarArgs;
      }
      return Boolean.valueOf(false);
    }
    
    protected void onPostExecute(Boolean paramBoolean)
    {
      if (this._callback != null) {
        this._callback.callback(paramBoolean.booleanValue(), this._error);
      }
    }
  }
  
  private class MetadataFetchTaskParam
  {
    public final String adSetCode;
    public final ContentItem item;
    
    private MetadataFetchTaskParam(ContentItem paramContentItem, String paramString)
    {
      this.item = paramContentItem;
      this.adSetCode = paramString;
    }
  }
  
  private class NextChildrenRunner
    implements Runnable
  {
    private PaginatedItemListener _listener = null;
    private PaginatedParentItem _parent = null;
    
    public NextChildrenRunner(PaginatedParentItem paramPaginatedParentItem, PaginatedItemListener paramPaginatedItemListener)
    {
      this._parent = paramPaginatedParentItem;
      this._listener = paramPaginatedItemListener;
    }
    
    public void run()
    {
      PaginatedItemResponse localPaginatedItemResponse = PlayerAPIClient.this.contentTreeNext(this._parent);
      if (localPaginatedItemResponse == null)
      {
        this._listener.onItemsFetched(-1, 0, new OoyalaException(OoyalaException.OoyalaErrorCode.ERROR_CONTENT_TREE_NEXT_FAILED, "Null response"));
        PlayerAPIClient.access$202(PlayerAPIClient.this, false);
        return;
      }
      if (localPaginatedItemResponse.firstIndex < 0)
      {
        this._listener.onItemsFetched(localPaginatedItemResponse.firstIndex, localPaginatedItemResponse.count, new OoyalaException(OoyalaException.OoyalaErrorCode.ERROR_CONTENT_TREE_NEXT_FAILED, "No additional children found"));
        PlayerAPIClient.access$202(PlayerAPIClient.this, false);
        return;
      }
      List localList = ContentItem.getEmbedCodes(this._parent.getAllAvailableChildren().subList(localPaginatedItemResponse.firstIndex, localPaginatedItemResponse.firstIndex + localPaginatedItemResponse.count));
      try
      {
        if ((PlayerAPIClient.this.authorizeEmbedCodes(localList, (ContentItem)this._parent)) && (PlayerAPIClient.this.fetchMetadataForEmbedCodesWithAdSet(localList, null, (ContentItem)this._parent))) {
          this._listener.onItemsFetched(localPaginatedItemResponse.firstIndex, localPaginatedItemResponse.count, null);
        }
        for (;;)
        {
          PlayerAPIClient.access$202(PlayerAPIClient.this, false);
          return;
          this._listener.onItemsFetched(localPaginatedItemResponse.firstIndex, localPaginatedItemResponse.count, new OoyalaException(OoyalaException.OoyalaErrorCode.ERROR_AUTHORIZATION_FAILED, "Additional child authorization failed"));
        }
      }
      catch (OoyalaException localOoyalaException)
      {
        for (;;)
        {
          this._listener.onItemsFetched(localPaginatedItemResponse.firstIndex, localPaginatedItemResponse.count, localOoyalaException);
        }
      }
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\PlayerAPIClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */