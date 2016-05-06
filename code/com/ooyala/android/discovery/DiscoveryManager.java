package com.ooyala.android.discovery;

import android.os.AsyncTask;
import com.ooyala.android.EmbeddedSignatureGenerator;
import com.ooyala.android.OoyalaException;
import com.ooyala.android.OoyalaException.OoyalaErrorCode;
import com.ooyala.android.Utils;
import com.ooyala.android.util.DebugMode;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class DiscoveryManager
{
  private static final String DISCOVERY_FEEDBACK_CLICK = "/v2/discover/feedback/play";
  private static final String DISCOVERY_FEEDBACK_IMPRESSION = "/v2/discover/feedback/impression";
  private static final String DISCOVERY_HOST = "http://api.ooyala.com";
  private static final String DISCOVERY_MOMENTUM = "/v2/discover/trending/momentum";
  private static final String DISCOVERY_POPULAR = "/v2/discover/trending/top";
  private static final String DISCOVERY_SIMILAR_ASSETS = "/v2/discover/similar/assets/";
  private static final String KEY_BUCKET_INFO = "bucket_info";
  private static final String KEY_DEVICE_ID = "device_id";
  private static final String KEY_EXPIRES = "expires";
  private static final String KEY_LIMIT = "limit";
  private static final String KEY_MESSAGE = "message";
  private static final String KEY_PCODE = "pcode";
  private static final String KEY_RESULTS = "results";
  private static final String KEY_SIGNATURE = "signature";
  private static final String KEY_SIGN_VERSION = "sign_version";
  private static final int RESPONSE_LIFE_SECONDS = 300;
  private static final String TAG = DiscoveryManager.class.getName();
  private static final String VALUE_PLAYER = "player";
  
  private static String discoveryUri(DiscoveryOptions.Type paramType, String paramString)
  {
    switch (paramType)
    {
    default: 
      DebugMode.logE(TAG, "unknown discovery type" + paramType.toString());
    }
    do
    {
      return null;
      return "/v2/discover/trending/momentum";
      return "/v2/discover/trending/top";
    } while (paramString == null);
    return "/v2/discover/similar/assets/" + paramString;
  }
  
  private static String genStringToSignFromDict(Map<String, String> paramMap)
  {
    return Utils.getParamsString(paramMap, "", false);
  }
  
  public static void getResults(DiscoveryOptions paramDiscoveryOptions, String paramString1, String paramString2, String paramString3, Map<String, String> paramMap, Callback paramCallback)
  {
    if (paramCallback == null)
    {
      DebugMode.logE(TAG, "Discovery callback should not be null");
      return;
    }
    if ((paramString2 == null) || (paramString2.length() <= 0) || (paramString3 == null) || (paramString3.length() <= 0))
    {
      paramCallback.callback(null, new OoyalaException(OoyalaException.OoyalaErrorCode.ERROR_UNKNOWN, "missing pcode or deviceId"));
      return;
    }
    String str = discoveryUri(paramDiscoveryOptions.getType(), paramString1);
    if (str == null)
    {
      paramCallback.callback(null, new OoyalaException(OoyalaException.OoyalaErrorCode.ERROR_UNKNOWN, "failed to generate discovery uri"));
      return;
    }
    if (paramMap == null) {}
    for (paramString1 = new HashMap();; paramString1 = new HashMap(paramMap))
    {
      paramString1.put("pcode", paramString2);
      paramString1.put("limit", Long.toString(paramDiscoveryOptions.getLimit()));
      paramString1.put("device_id", paramString3);
      paramDiscoveryOptions = new DiscoveryTaskInfo(signedUrlForHost("http://api.ooyala.com", str, paramString1), false, null, paramDiscoveryOptions.getTimoutInMilliSeconds(), paramDiscoveryOptions.getTimoutInMilliSeconds());
      new DiscoveryTask(new DiscoveryResultsCallback()
      {
        public void callback(String paramAnonymousString, OoyalaException paramAnonymousOoyalaException)
        {
          if (paramAnonymousOoyalaException != null) {
            this.val$callback.callback(null, paramAnonymousOoyalaException);
          }
          try
          {
            paramAnonymousString = new JSONObject(paramAnonymousString);
            if (paramAnonymousString.has("results"))
            {
              paramAnonymousString = paramAnonymousString.getJSONArray("results");
              this.val$callback.callback(paramAnonymousString, null);
              return;
            }
            paramAnonymousString.getString("message");
            this.val$callback.callback(null, new OoyalaException(OoyalaException.OoyalaErrorCode.ERROR_DISCOVERY_GET_FAILURE, "discovery results failure"));
            return;
          }
          catch (JSONException paramAnonymousString)
          {
            this.val$callback.callback(null, new OoyalaException(OoyalaException.OoyalaErrorCode.ERROR_DISCOVERY_GET_FAILURE, "failed to parse json results", paramAnonymousString));
          }
        }
      }).execute(new DiscoveryTaskInfo[] { paramDiscoveryOptions });
      return;
    }
  }
  
  public static void sendClick(DiscoveryOptions paramDiscoveryOptions, String paramString1, String paramString2, String paramString3, Map<String, String> paramMap, Callback paramCallback)
  {
    sendFeedback(paramDiscoveryOptions, paramString1, paramString2, paramString3, paramMap, paramCallback, "/v2/discover/feedback/play");
  }
  
  private static void sendFeedback(DiscoveryOptions paramDiscoveryOptions, String paramString1, String paramString2, String paramString3, Map<String, String> paramMap, Callback paramCallback, String paramString4)
  {
    if (paramMap == null) {}
    for (paramMap = new HashMap();; paramMap = new HashMap(paramMap))
    {
      paramMap.put("bucket_info", paramString1);
      paramMap.put("device_id", paramString3);
      paramString1 = new HashMap();
      paramString1.put("pcode", paramString2);
      paramDiscoveryOptions = new DiscoveryTaskInfo(signedUrlForHost("http://api.ooyala.com", paramString4, paramString1), true, paramMap, paramDiscoveryOptions.getTimoutInMilliSeconds(), paramDiscoveryOptions.getTimoutInMilliSeconds());
      new DiscoveryTask(new DiscoveryResultsCallback()
      {
        public void callback(String paramAnonymousString, OoyalaException paramAnonymousOoyalaException)
        {
          paramAnonymousOoyalaException = null;
          Object localObject = null;
          if (paramAnonymousString.equals("OK")) {
            paramAnonymousOoyalaException = paramAnonymousString;
          }
          for (paramAnonymousString = (String)localObject;; paramAnonymousString = new OoyalaException(OoyalaException.OoyalaErrorCode.ERROR_DISCOVERY_POST_FAILURE, paramAnonymousString))
          {
            if (this.val$callback != null) {
              this.val$callback.callback(paramAnonymousOoyalaException, paramAnonymousString);
            }
            return;
          }
        }
      }).execute(new DiscoveryTaskInfo[] { paramDiscoveryOptions });
      return;
    }
  }
  
  public static void sendImpression(DiscoveryOptions paramDiscoveryOptions, String paramString1, String paramString2, String paramString3, Map<String, String> paramMap, Callback paramCallback)
  {
    sendFeedback(paramDiscoveryOptions, paramString1, paramString2, paramString3, paramMap, paramCallback, "/v2/discover/feedback/impression");
  }
  
  private static URL signedUrlForHost(String paramString1, String paramString2, Map<String, String> paramMap)
  {
    String str = (String)paramMap.get("pcode");
    paramMap.put("pcode", null);
    EmbeddedSignatureGenerator localEmbeddedSignatureGenerator = new EmbeddedSignatureGenerator(str);
    paramMap.put("expires", Long.toString(300L + new Date().getTime() / 1000L));
    paramMap.put("sign_version", "player");
    paramMap.put("signature", localEmbeddedSignatureGenerator.sign(genStringToSignFromDict(paramMap)));
    paramMap.put("pcode", str);
    return Utils.makeURL(paramString1, paramString2, paramMap);
  }
  
  public static abstract interface Callback
  {
    public abstract void callback(Object paramObject, OoyalaException paramOoyalaException);
  }
  
  private static abstract interface DiscoveryResultsCallback
  {
    public abstract void callback(String paramString, OoyalaException paramOoyalaException);
  }
  
  private static class DiscoveryTask
    extends AsyncTask<DiscoveryManager.DiscoveryTaskInfo, Integer, String>
  {
    protected DiscoveryManager.DiscoveryResultsCallback callback = null;
    protected OoyalaException error = null;
    
    public DiscoveryTask(DiscoveryManager.DiscoveryResultsCallback paramDiscoveryResultsCallback)
    {
      this.callback = paramDiscoveryResultsCallback;
    }
    
    protected String doInBackground(DiscoveryManager.DiscoveryTaskInfo... paramVarArgs)
    {
      if ((paramVarArgs.length == 0) || (paramVarArgs[0] == null) || (!(paramVarArgs[0] instanceof DiscoveryManager.DiscoveryTaskInfo))) {
        return null;
      }
      paramVarArgs = paramVarArgs[0];
      if (paramVarArgs.isPostMethod())
      {
        String str = Utils.mapToJsonString(paramVarArgs.getBody());
        return Utils.postUrl(paramVarArgs.getUrl(), str, (int)paramVarArgs.getConnectionTimeoutInMillisecond(), (int)paramVarArgs.getReadTimeoutInMillisecond());
      }
      return Utils.getUrlContent(paramVarArgs.getUrl(), (int)paramVarArgs.getConnectionTimeoutInMillisecond(), (int)paramVarArgs.getReadTimeoutInMillisecond());
    }
    
    protected void onPostExecute(String paramString)
    {
      if (this.callback != null) {
        this.callback.callback(paramString, null);
      }
    }
  }
  
  private static class DiscoveryTaskInfo
  {
    private Map<String, String> body;
    private long connectionTimeoutInMillisecond;
    private boolean postMethod;
    private long readTimeoutInMillisecond;
    private URL url;
    
    DiscoveryTaskInfo(URL paramURL, boolean paramBoolean, Map<String, String> paramMap, long paramLong1, long paramLong2)
    {
      this.url = paramURL;
      this.postMethod = paramBoolean;
      this.body = paramMap;
      this.connectionTimeoutInMillisecond = paramLong1;
      this.readTimeoutInMillisecond = paramLong2;
    }
    
    public Map<String, String> getBody()
    {
      return this.body;
    }
    
    public long getConnectionTimeoutInMillisecond()
    {
      return this.connectionTimeoutInMillisecond;
    }
    
    public long getReadTimeoutInMillisecond()
    {
      return this.readTimeoutInMillisecond;
    }
    
    public URL getUrl()
    {
      return this.url;
    }
    
    public boolean isPostMethod()
    {
      return this.postMethod;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\discovery\DiscoveryManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */