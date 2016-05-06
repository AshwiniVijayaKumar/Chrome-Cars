package com.ooyala.android;

import com.ooyala.android.util.DebugMode;
import org.json.JSONException;
import org.json.JSONObject;

class UserInfo
{
  static final String KEY_USER_ACCOUNT_ID = "account_id";
  static final String KEY_USER_CONTINENT = "continent";
  static final String KEY_USER_COUNTRY = "country";
  static final String KEY_USER_DEVICE = "device";
  static final String KEY_USER_DOMAIN = "domain";
  static final String KEY_USER_IPADDRESS = "ip_address";
  static final String KEY_USER_LANGUAGE = "language";
  static final String KEY_USER_TIMEZONE = "timezone";
  private static final String TAG = UserInfo.class.getSimpleName();
  public String accountId;
  public String continent;
  public String country;
  public String device;
  public String domain;
  public String ipAddress;
  public String language;
  public String timezone;
  
  public UserInfo(JSONObject paramJSONObject)
  {
    try
    {
      if (!paramJSONObject.isNull("account_id")) {
        this.accountId = paramJSONObject.getString("account_id");
      }
      if (!paramJSONObject.isNull("continent")) {
        this.continent = paramJSONObject.getString("continent");
      }
      if (!paramJSONObject.isNull("country")) {
        this.country = paramJSONObject.getString("country");
      }
      if (!paramJSONObject.isNull("device")) {
        this.device = paramJSONObject.getString("device");
      }
      if (!paramJSONObject.isNull("domain")) {
        this.domain = paramJSONObject.getString("domain");
      }
      if (!paramJSONObject.isNull("language")) {
        this.ipAddress = paramJSONObject.getString("ip_address");
      }
      if (!paramJSONObject.isNull("language")) {
        this.language = paramJSONObject.getString("language");
      }
      if (!paramJSONObject.isNull("timezone")) {
        this.timezone = paramJSONObject.getString("timezone");
      }
      return;
    }
    catch (JSONException paramJSONObject)
    {
      DebugMode.logE(TAG, "Caught!", paramJSONObject);
    }
  }
  
  public String getAccountId()
  {
    return this.accountId;
  }
  
  public String getContient()
  {
    return this.continent;
  }
  
  public String getCountry()
  {
    return this.country;
  }
  
  public String getDevice()
  {
    return this.device;
  }
  
  public String getDomain()
  {
    return this.domain;
  }
  
  public String getIpAddress()
  {
    return this.ipAddress;
  }
  
  public String getLanguage()
  {
    return this.language;
  }
  
  public String getTimezone()
  {
    return this.timezone;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\UserInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */