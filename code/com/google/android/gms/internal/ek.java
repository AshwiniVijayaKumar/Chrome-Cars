package com.google.android.gms.internal;

import android.content.Intent;
import android.net.Uri;
import android.net.Uri.Builder;

public class ek
{
  private static final Uri Cb = Uri.parse("http://plus.google.com/");
  private static final Uri Cc = Cb.buildUpon().appendPath("circles").appendPath("find").build();
  
  public static Intent af(String paramString)
  {
    paramString = Uri.fromParts("package", paramString, null);
    Intent localIntent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
    localIntent.setData(paramString);
    return localIntent;
  }
  
  private static Uri ag(String paramString)
  {
    return Uri.parse("market://details").buildUpon().appendQueryParameter("id", paramString).build();
  }
  
  public static Intent ah(String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setData(ag(paramString));
    localIntent.setPackage("com.android.vending");
    localIntent.addFlags(524288);
    return localIntent;
  }
  
  public static Intent ai(String paramString)
  {
    paramString = Uri.parse("bazaar://search?q=pname:" + paramString);
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setData(paramString);
    localIntent.setFlags(524288);
    return localIntent;
  }
  
  public static Intent eh()
  {
    return new Intent("android.settings.DATE_SETTINGS");
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\ek.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */