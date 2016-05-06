package com.example.example75f1799f07eb;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.facebook.android.Facebook;

public class SessionStore
{
  private static final String Appypie_EXPIRES = "expires_in";
  private static final String Appypie_KEY = "facebook-session";
  private static final String Appypie_TOKEN = "access_token";
  
  public static void clear(Context paramContext)
  {
    paramContext = paramContext.getSharedPreferences("facebook-session", 0).edit();
    paramContext.clear();
    paramContext.commit();
  }
  
  public static boolean restore(Facebook paramFacebook, Context paramContext)
  {
    paramContext = paramContext.getSharedPreferences("facebook-session", 0);
    paramFacebook.setAccessToken(paramContext.getString("access_token", null));
    paramFacebook.setAccessExpires(paramContext.getLong("expires_in", 0L));
    return paramFacebook.isSessionValid();
  }
  
  public static boolean save(Facebook paramFacebook, Context paramContext)
  {
    paramContext = paramContext.getSharedPreferences("facebook-session", 0).edit();
    paramContext.putString("access_token", paramFacebook.getAccessToken());
    paramContext.putLong("expires_in", paramFacebook.getAccessExpires());
    return paramContext.commit();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\example\example75f1799f07eb\SessionStore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */