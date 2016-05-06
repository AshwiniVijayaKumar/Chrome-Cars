package com.google.android.gms.internal;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import java.util.Locale;

public final class cm
{
  public final int oM;
  public final boolean oN;
  public final boolean oO;
  public final String oP;
  public final String oQ;
  public final boolean oR;
  public final boolean oS;
  public final boolean oT;
  public final String oU;
  public final String oV;
  public final int oW;
  public final int oX;
  public final int oY;
  public final int oZ;
  public final int pa;
  public final int pb;
  public final float pc;
  public final int pd;
  public final int pe;
  
  public cm(Context paramContext)
  {
    AudioManager localAudioManager = (AudioManager)paramContext.getSystemService("audio");
    ConnectivityManager localConnectivityManager = (ConnectivityManager)paramContext.getSystemService("connectivity");
    DisplayMetrics localDisplayMetrics = paramContext.getResources().getDisplayMetrics();
    Locale localLocale = Locale.getDefault();
    PackageManager localPackageManager = paramContext.getPackageManager();
    TelephonyManager localTelephonyManager = (TelephonyManager)paramContext.getSystemService("phone");
    this.oM = localAudioManager.getMode();
    if (a(localPackageManager, "geo:0,0?q=donuts") != null)
    {
      bool1 = true;
      this.oN = bool1;
      if (a(localPackageManager, "http://www.google.com") == null) {
        break label255;
      }
    }
    label255:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      this.oO = bool1;
      this.oP = localTelephonyManager.getNetworkOperator();
      this.oQ = localLocale.getCountry();
      this.oR = cz.aW();
      this.oS = localAudioManager.isMusicActive();
      this.oT = localAudioManager.isSpeakerphoneOn();
      this.oU = localLocale.getLanguage();
      this.oV = a(localPackageManager);
      this.oW = localAudioManager.getStreamVolume(3);
      this.oX = a(paramContext, localConnectivityManager, localPackageManager);
      this.oY = localTelephonyManager.getNetworkType();
      this.oZ = localTelephonyManager.getPhoneType();
      this.pa = localAudioManager.getRingerMode();
      this.pb = localAudioManager.getStreamVolume(2);
      this.pc = localDisplayMetrics.density;
      this.pd = localDisplayMetrics.widthPixels;
      this.pe = localDisplayMetrics.heightPixels;
      return;
      bool1 = false;
      break;
    }
  }
  
  private static int a(Context paramContext, ConnectivityManager paramConnectivityManager, PackageManager paramPackageManager)
  {
    int i = -2;
    if (cv.a(paramPackageManager, paramContext.getPackageName(), "android.permission.ACCESS_NETWORK_STATE"))
    {
      paramContext = paramConnectivityManager.getActiveNetworkInfo();
      if (paramContext != null) {
        i = paramContext.getType();
      }
    }
    else
    {
      return i;
    }
    return -1;
  }
  
  private static ResolveInfo a(PackageManager paramPackageManager, String paramString)
  {
    return paramPackageManager.resolveActivity(new Intent("android.intent.action.VIEW", Uri.parse(paramString)), 65536);
  }
  
  private static String a(PackageManager paramPackageManager)
  {
    Object localObject = a(paramPackageManager, "market://details?id=com.google.android.gms.ads");
    if (localObject == null) {}
    for (;;)
    {
      return null;
      localObject = ((ResolveInfo)localObject).activityInfo;
      if (localObject != null) {
        try
        {
          paramPackageManager = paramPackageManager.getPackageInfo(((ActivityInfo)localObject).packageName, 0);
          if (paramPackageManager != null)
          {
            paramPackageManager = paramPackageManager.versionCode + "." + ((ActivityInfo)localObject).packageName;
            return paramPackageManager;
          }
        }
        catch (PackageManager.NameNotFoundException paramPackageManager) {}
      }
    }
    return null;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\cm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */