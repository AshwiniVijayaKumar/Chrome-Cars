package com.google.android.gms.internal;

import android.content.Context;
import android.webkit.WebSettings;

public final class cx
{
  public static void a(Context paramContext, WebSettings paramWebSettings)
  {
    cw.a(paramContext, paramWebSettings);
    paramWebSettings.setMediaPlaybackRequiresUserGesture(false);
  }
  
  public static String getDefaultUserAgent(Context paramContext)
  {
    return WebSettings.getDefaultUserAgent(paramContext);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\cx.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */