package com.ooyala.android.item;

import com.ooyala.android.OoyalaAPIClient;
import com.ooyala.android.OoyalaAdSpot;
import com.ooyala.android.ads.vast.VASTAdSpot;
import com.ooyala.android.util.DebugMode;
import java.net.URL;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class OoyalaManagedAdSpot
  extends AdSpot
  implements JSONUpdatableItem
{
  protected static final String AD_TYPE_OOYALA = "ooyala";
  protected static final String AD_TYPE_VAST = "vast";
  static final int DEFAULT_AD_TIME_SECONDS = 0;
  protected static final String KEY_CLICK_URL = "click_url";
  protected static final String KEY_TIME = "time";
  protected static final String KEY_TRACKING_URL = "tracking_url";
  protected static final String KEY_TYPE = "type";
  protected URL _clickURL = null;
  protected int _priority = 0;
  protected int _time = -1;
  protected List<URL> _trackingURLs = null;
  
  protected OoyalaManagedAdSpot() {}
  
  protected OoyalaManagedAdSpot(int paramInt, URL paramURL, List<URL> paramList)
  {
    this._time = paramInt;
    this._clickURL = paramURL;
    this._trackingURLs = paramList;
  }
  
  protected OoyalaManagedAdSpot(JSONObject paramJSONObject)
  {
    update(paramJSONObject);
  }
  
  public static OoyalaManagedAdSpot create(JSONObject paramJSONObject, OoyalaAPIClient paramOoyalaAPIClient)
  {
    if ((paramJSONObject == null) || (paramJSONObject.isNull("type"))) {}
    String str;
    for (;;)
    {
      return null;
      try
      {
        str = paramJSONObject.getString("type");
        if (str != null) {
          if (str.equals("ooyala")) {
            return new OoyalaAdSpot(paramJSONObject, paramOoyalaAPIClient);
          }
        }
      }
      catch (JSONException paramJSONObject)
      {
        DebugMode.logD(OoyalaManagedAdSpot.class.getName(), "Ad create failed due to JSONException: " + paramJSONObject);
        return null;
      }
    }
    if (str.equals("vast")) {
      return new VASTAdSpot(paramJSONObject);
    }
    DebugMode.logD(OoyalaManagedAdSpot.class.getName(), "Unknown ad type: " + str);
    return null;
  }
  
  public abstract boolean fetchPlaybackInfo();
  
  public URL getClickURL()
  {
    return this._clickURL;
  }
  
  public int getPriority()
  {
    return this._priority;
  }
  
  public int getTime()
  {
    return this._time;
  }
  
  public List<URL> getTrackingURLs()
  {
    return this._trackingURLs;
  }
  
  public void setPriority(int paramInt)
  {
    this._priority = paramInt;
  }
  
  /* Error */
  protected JSONUpdatableItem.ReturnState update(JSONObject paramJSONObject)
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnonnull +7 -> 8
    //   4: getstatic 134	com/ooyala/android/item/JSONUpdatableItem$ReturnState:STATE_FAIL	Lcom/ooyala/android/item/JSONUpdatableItem$ReturnState;
    //   7: areturn
    //   8: aload_1
    //   9: ldc 22
    //   11: invokevirtual 66	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   14: ifne +119 -> 133
    //   17: aload_0
    //   18: aload_1
    //   19: ldc 22
    //   21: invokevirtual 138	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   24: putfield 41	com/ooyala/android/item/OoyalaManagedAdSpot:_time	I
    //   27: aload_1
    //   28: ldc 19
    //   30: invokevirtual 66	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   33: istore 4
    //   35: iload 4
    //   37: ifne +20 -> 57
    //   40: aload_0
    //   41: new 140	java/net/URL
    //   44: dup
    //   45: aload_1
    //   46: ldc 19
    //   48: invokevirtual 70	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   51: invokespecial 143	java/net/URL:<init>	(Ljava/lang/String;)V
    //   54: putfield 45	com/ooyala/android/item/OoyalaManagedAdSpot:_clickURL	Ljava/net/URL;
    //   57: aload_1
    //   58: ldc 25
    //   60: invokevirtual 66	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   63: ifne +202 -> 265
    //   66: aload_1
    //   67: ldc 25
    //   69: invokevirtual 147	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   72: astore 5
    //   74: aload_0
    //   75: new 149	java/util/ArrayList
    //   78: dup
    //   79: aload 5
    //   81: invokevirtual 154	org/json/JSONArray:length	()I
    //   84: invokespecial 156	java/util/ArrayList:<init>	(I)V
    //   87: putfield 47	com/ooyala/android/item/OoyalaManagedAdSpot:_trackingURLs	Ljava/util/List;
    //   90: iconst_0
    //   91: istore_2
    //   92: aload 5
    //   94: invokevirtual 154	org/json/JSONArray:length	()I
    //   97: istore_3
    //   98: iload_2
    //   99: iload_3
    //   100: if_icmpge +165 -> 265
    //   103: aload_0
    //   104: getfield 47	com/ooyala/android/item/OoyalaManagedAdSpot:_trackingURLs	Ljava/util/List;
    //   107: new 140	java/net/URL
    //   110: dup
    //   111: aload 5
    //   113: iload_2
    //   114: invokevirtual 159	org/json/JSONArray:getString	(I)Ljava/lang/String;
    //   117: invokespecial 143	java/net/URL:<init>	(Ljava/lang/String;)V
    //   120: invokeinterface 164 2 0
    //   125: pop
    //   126: iload_2
    //   127: iconst_1
    //   128: iadd
    //   129: istore_2
    //   130: goto -38 -> 92
    //   133: aload_0
    //   134: getfield 41	com/ooyala/android/item/OoyalaManagedAdSpot:_time	I
    //   137: ifge -110 -> 27
    //   140: aload_0
    //   141: iconst_0
    //   142: putfield 41	com/ooyala/android/item/OoyalaManagedAdSpot:_time	I
    //   145: goto -118 -> 27
    //   148: astore_1
    //   149: aload_0
    //   150: invokevirtual 170	java/lang/Object:getClass	()Ljava/lang/Class;
    //   153: invokevirtual 87	java/lang/Class:getName	()Ljava/lang/String;
    //   156: new 89	java/lang/StringBuilder
    //   159: dup
    //   160: invokespecial 90	java/lang/StringBuilder:<init>	()V
    //   163: ldc -84
    //   165: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   168: aload_1
    //   169: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   172: invokevirtual 102	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   175: invokestatic 108	com/ooyala/android/util/DebugMode:logD	(Ljava/lang/String;Ljava/lang/String;)V
    //   178: getstatic 134	com/ooyala/android/item/JSONUpdatableItem$ReturnState:STATE_FAIL	Lcom/ooyala/android/item/JSONUpdatableItem$ReturnState;
    //   181: areturn
    //   182: astore 5
    //   184: aload_0
    //   185: invokevirtual 170	java/lang/Object:getClass	()Ljava/lang/Class;
    //   188: invokevirtual 87	java/lang/Class:getName	()Ljava/lang/String;
    //   191: new 89	java/lang/StringBuilder
    //   194: dup
    //   195: invokespecial 90	java/lang/StringBuilder:<init>	()V
    //   198: ldc -82
    //   200: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   203: aload_1
    //   204: ldc 19
    //   206: invokevirtual 70	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   209: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   212: invokevirtual 102	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   215: invokestatic 108	com/ooyala/android/util/DebugMode:logD	(Ljava/lang/String;Ljava/lang/String;)V
    //   218: aload_0
    //   219: aconst_null
    //   220: putfield 45	com/ooyala/android/item/OoyalaManagedAdSpot:_clickURL	Ljava/net/URL;
    //   223: goto -166 -> 57
    //   226: astore 6
    //   228: aload_0
    //   229: invokevirtual 170	java/lang/Object:getClass	()Ljava/lang/Class;
    //   232: invokevirtual 87	java/lang/Class:getName	()Ljava/lang/String;
    //   235: new 89	java/lang/StringBuilder
    //   238: dup
    //   239: invokespecial 90	java/lang/StringBuilder:<init>	()V
    //   242: ldc -80
    //   244: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   247: aload_1
    //   248: ldc 25
    //   250: invokevirtual 70	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   253: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   256: invokevirtual 102	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   259: invokestatic 108	com/ooyala/android/util/DebugMode:logD	(Ljava/lang/String;Ljava/lang/String;)V
    //   262: goto -136 -> 126
    //   265: getstatic 179	com/ooyala/android/item/JSONUpdatableItem$ReturnState:STATE_MATCHED	Lcom/ooyala/android/item/JSONUpdatableItem$ReturnState;
    //   268: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	269	0	this	OoyalaManagedAdSpot
    //   0	269	1	paramJSONObject	JSONObject
    //   91	39	2	i	int
    //   97	4	3	j	int
    //   33	3	4	bool	boolean
    //   72	40	5	localJSONArray	org.json.JSONArray
    //   182	1	5	localMalformedURLException1	java.net.MalformedURLException
    //   226	1	6	localMalformedURLException2	java.net.MalformedURLException
    // Exception table:
    //   from	to	target	type
    //   8	27	148	org/json/JSONException
    //   27	35	148	org/json/JSONException
    //   40	57	148	org/json/JSONException
    //   57	90	148	org/json/JSONException
    //   92	98	148	org/json/JSONException
    //   103	126	148	org/json/JSONException
    //   133	145	148	org/json/JSONException
    //   184	223	148	org/json/JSONException
    //   228	262	148	org/json/JSONException
    //   40	57	182	java/net/MalformedURLException
    //   103	126	226	java/net/MalformedURLException
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\item\OoyalaManagedAdSpot.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */