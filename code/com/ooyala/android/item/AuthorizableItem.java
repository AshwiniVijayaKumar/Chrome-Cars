package com.ooyala.android.item;

import java.util.List;
import org.json.JSONObject;

public abstract interface AuthorizableItem
  extends JSONUpdatableItem
{
  public static final String[] authCodeDescription = { "authorized", "unauthorized parent", "unauthorized domain", "unauthorized location", "current time is before the flight start time", "current time is after the flight end time", "current time is outside any availability period", "this is not a recognized embed code", "invalid signature", "missing parameters", "missing rule set", "unauthorized", "missing pcode", "unauthorized device", "invalid token", "movie expired", "unauthorized multi-synd group", "This provider was deleted", "Too many open videos. Close other videos on this account and try again in a few minutes", "Missing Account ID", "No Entitlements Found", "Non-entitled Device", "Non-registered Device", "unauthorized" };
  
  public abstract List<String> embedCodesToAuthorize();
  
  public abstract int getAuthCode();
  
  public abstract boolean isAuthorized();
  
  public abstract boolean isHeartbeatRequired();
  
  public abstract JSONUpdatableItem.ReturnState update(JSONObject paramJSONObject);
  
  public static abstract interface AuthCode
  {
    public static final int AFTER_FLIGHT_TIME = 5;
    public static final int AUTHORIZED = 0;
    public static final int BAD_EMBED_CODE = 7;
    public static final int BEFORE_FLIGHT_TIME = 4;
    public static final int INVALID_SIGNATURE = 8;
    public static final int INVALID_TOKEN = 14;
    public static final int MAX_AUTH_CODE = 23;
    public static final int MIN_AUTH_CODE = 0;
    public static final int MISSING_ACCOUNT_ID = 19;
    public static final int MISSING_PARAMS = 9;
    public static final int MISSING_PCODE = 12;
    public static final int MISSING_RULE_SET = 10;
    public static final int NON_ENTITLED_DEVICE = 21;
    public static final int NON_REGISTERED_DEVICE = 22;
    public static final int NOT_REQUESTED = -1;
    public static final int NO_ENTITLEMENTS_FOUND = 20;
    public static final int OUTSIDE_RECURRING_FLIGHT_TIMES = 6;
    public static final int PROVIDER_DELETED = 17;
    public static final int TOKEN_EXPIRED = 15;
    public static final int TOO_MANY_ACTIVE_STREAMS = 18;
    public static final int UNAUTHORIZED = 11;
    public static final int UNAUTHORIZED_DEVICE = 13;
    public static final int UNAUTHORIZED_DOMAIN = 2;
    public static final int UNAUTHORIZED_LOCATION = 3;
    public static final int UNAUTHORIZED_MULTI_SYND_GROUP = 16;
    public static final int UNAUTHORIZED_PARENT = 1;
    public static final int UNKNOWN = -2;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\item\AuthorizableItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */