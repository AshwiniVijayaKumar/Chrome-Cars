package com.ooyala.android;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import java.util.UUID;

public class ClientId
{
  private static final String CLIENT_ID_KEY = "clientId";
  private static final String TAG = ClientId.class.getName();
  private static String _clientId;
  
  public static String getId(Context paramContext)
  {
    if ((_clientId == null) || (_clientId.length() <= 0)) {
      _clientId = loadOrCreateId(paramContext);
    }
    return _clientId;
  }
  
  private static String loadOrCreateId(Context paramContext)
  {
    String str2 = paramContext.getSharedPreferences("com.ooyala.android_preferences", 4).getString("clientId", "");
    String str1 = str2;
    if (str2.length() <= 0)
    {
      str1 = UUID.randomUUID().toString();
      storeClientId(paramContext, str1);
    }
    return str1;
  }
  
  public static void resetId(Context paramContext)
  {
    _clientId = "";
    storeClientId(paramContext, _clientId);
  }
  
  public static void setId(String paramString)
  {
    _clientId = paramString;
  }
  
  private static void storeClientId(Context paramContext, String paramString)
  {
    paramContext = paramContext.getSharedPreferences("com.ooyala.android_preferences", 4).edit();
    paramContext.putString("clientId", paramString);
    paramContext.commit();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\ClientId.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */