package com.adsdk.sdk;

import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.preference.PreferenceManager;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.webkit.WebSettings;
import android.webkit.WebView;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.MessageDigest;
import java.util.Enumeration;
import java.util.Locale;
import java.util.UUID;

public class Util
{
  private static int sFadeInAnimationId = 0;
  private static int sFadeOutAnimationId = 0;
  private static int sSlideInBottomAnimationId = 0;
  private static int sSlideInLeftAnimationId;
  private static int sSlideInRightAnimationId = 0;
  private static int sSlideInTopAnimationId;
  private static int sSlideOutBottomAnimationId = 0;
  private static int sSlideOutLeftAnimationId;
  private static int sSlideOutRightAnimationId = 0;
  private static int sSlideOutTopAnimationId;
  
  static
  {
    sSlideInLeftAnimationId = 0;
    sSlideOutLeftAnimationId = 0;
    sSlideInTopAnimationId = 0;
    sSlideOutTopAnimationId = 0;
  }
  
  public static String buildUserAgent()
  {
    String str3 = Build.VERSION.RELEASE;
    String str4 = Build.MODEL;
    String str5 = Build.ID;
    Object localObject = Locale.getDefault();
    String str2 = ((Locale)localObject).getLanguage();
    String str1 = "en";
    if (str2 != null)
    {
      str2 = str2.toLowerCase();
      localObject = ((Locale)localObject).getCountry();
      str1 = str2;
      if (localObject != null) {
        str1 = str2 + "-" + ((String)localObject).toLowerCase();
      }
    }
    return String.format("Mozilla/5.0 (Linux; U; Android %1$s; %2$s; %3$s Build/%4$s) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1", new Object[] { str3, str1, str4, str5 });
  }
  
  public static String getConnectionType(Context paramContext)
  {
    if (paramContext.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") == 0)
    {
      paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
      if (paramContext == null) {
        return "UNKNOWN";
      }
      int i = paramContext.getType();
      int j = paramContext.getSubtype();
      if (i == 1) {
        return "WIFI";
      }
      if (i == 6) {
        return "WIMAX";
      }
      if (i == 0)
      {
        switch (j)
        {
        default: 
          return "MOBILE";
        case 7: 
          return "1xRTT";
        case 4: 
          return "CDMA";
        case 2: 
          return "EDGE";
        case 5: 
          return "EVDO_0";
        case 6: 
          return "EVDO_A";
        case 1: 
          return "GPRS";
        case 3: 
          return "UMTS";
        case 14: 
          return "EHRPD";
        case 12: 
          return "EVDO_B";
        case 8: 
          return "HSDPA";
        case 10: 
          return "HSPA";
        case 15: 
          return "HSPAP";
        case 9: 
          return "HSUPA";
        case 11: 
          return "IDEN";
        }
        return "LTE";
      }
      return "UNKNOWN";
    }
    return "UNKNOWN";
  }
  
  public static String getDefaultUserAgentString(Context paramContext)
  {
    try
    {
      Constructor localConstructor = WebSettings.class.getDeclaredConstructor(new Class[] { Context.class, WebView.class });
      localConstructor.setAccessible(true);
      try
      {
        String str = ((WebSettings)localConstructor.newInstance(new Object[] { paramContext, null })).getUserAgentString();
        return str;
      }
      finally
      {
        localConstructor.setAccessible(false);
      }
      return new WebView(paramContext).getSettings().getUserAgentString();
    }
    catch (Exception localException) {}
  }
  
  public static String getDeviceId(Context paramContext)
  {
    Object localObject2 = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
    Object localObject1;
    if ((localObject2 != null) && (!((String)localObject2).equals("9774d56d682e549c")))
    {
      localObject1 = localObject2;
      if (!((String)localObject2).equals("0000000000000000")) {}
    }
    else
    {
      localObject2 = PreferenceManager.getDefaultSharedPreferences(paramContext);
      localObject1 = ((SharedPreferences)localObject2).getString("device_id", null);
      paramContext = (Context)localObject1;
      if (localObject1 != null) {}
    }
    try
    {
      paramContext = UUID.randomUUID().toString();
      localObject1 = MessageDigest.getInstance("MD5");
      ((MessageDigest)localObject1).update(paramContext.getBytes(), 0, paramContext.length());
      paramContext = String.format("%032X", new Object[] { new BigInteger(1, ((MessageDigest)localObject1).digest()) }).substring(0, 16);
      ((SharedPreferences)localObject2).edit().putString("device_id", paramContext).commit();
      Log.d("Unknown Android ID using pseudo unique id:" + paramContext);
      localObject1 = paramContext;
      return (String)localObject1;
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        Log.d("Could not generate pseudo unique id", paramContext);
        paramContext = "9774d56d682e549c";
      }
    }
  }
  
  public static int getEnterAnimation(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return 0;
    case 1: 
      return sFadeInAnimationId;
    case 6: 
      return sFadeInAnimationId;
    case 3: 
      return sSlideInBottomAnimationId;
    case 4: 
      return sSlideInLeftAnimationId;
    case 5: 
      return sSlideInRightAnimationId;
    }
    return sSlideInTopAnimationId;
  }
  
  public static AnimationSet getEnterAnimationSet(int paramInt)
  {
    AnimationSet localAnimationSet = new AnimationSet(false);
    Object localObject = new AlphaAnimation(0.0F, 1.0F);
    ((AlphaAnimation)localObject).setDuration(3000L);
    localAnimationSet.addAnimation((Animation)localObject);
    localObject = localAnimationSet;
    switch (paramInt)
    {
    default: 
      localObject = null;
    case 1: 
    case 6: 
      return (AnimationSet)localObject;
    case 3: 
      localObject = new TranslateAnimation(1, 0.0F, 1, 0.0F, 1, 1.0F, 1, 0.0F);
      ((TranslateAnimation)localObject).setDuration(1000L);
      localAnimationSet.addAnimation((Animation)localObject);
      return localAnimationSet;
    case 4: 
      localObject = new TranslateAnimation(1, -1.0F, 1, 0.0F, 1, 0.0F, 1, 0.0F);
      ((TranslateAnimation)localObject).setDuration(1000L);
      localAnimationSet.addAnimation((Animation)localObject);
      return localAnimationSet;
    case 5: 
      localObject = new TranslateAnimation(1, 1.0F, 1, 0.0F, 1, 0.0F, 1, 0.0F);
      ((TranslateAnimation)localObject).setDuration(1000L);
      localAnimationSet.addAnimation((Animation)localObject);
      return localAnimationSet;
    }
    localObject = new TranslateAnimation(1, 0.0F, 1, 0.0F, 1, -1.0F, 1, 0.0F);
    ((TranslateAnimation)localObject).setDuration(1000L);
    localAnimationSet.addAnimation((Animation)localObject);
    return localAnimationSet;
  }
  
  public static int getExitAnimation(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return 0;
    case 1: 
      return sFadeOutAnimationId;
    case 6: 
      return sFadeOutAnimationId;
    case 3: 
      return sSlideOutBottomAnimationId;
    case 4: 
      return sSlideOutLeftAnimationId;
    case 5: 
      return sSlideOutRightAnimationId;
    }
    return sSlideOutTopAnimationId;
  }
  
  public static AnimationSet getExitAnimationSet(int paramInt)
  {
    AnimationSet localAnimationSet = new AnimationSet(false);
    Object localObject = new AlphaAnimation(1.0F, 0.0F);
    ((AlphaAnimation)localObject).setDuration(3000L);
    localAnimationSet.addAnimation((Animation)localObject);
    localObject = localAnimationSet;
    switch (paramInt)
    {
    default: 
      localObject = null;
    case 1: 
    case 6: 
      return (AnimationSet)localObject;
    case 3: 
      localObject = new TranslateAnimation(1, 0.0F, 1, 0.0F, 1, 0.0F, 1, 1.0F);
      ((TranslateAnimation)localObject).setDuration(1000L);
      localAnimationSet.addAnimation((Animation)localObject);
      return localAnimationSet;
    case 4: 
      localObject = new TranslateAnimation(1, 0.0F, 1, -1.0F, 1, 0.0F, 1, 0.0F);
      ((TranslateAnimation)localObject).setDuration(1000L);
      localAnimationSet.addAnimation((Animation)localObject);
      return localAnimationSet;
    case 5: 
      localObject = new TranslateAnimation(1, 0.0F, 1, 1.0F, 1, 0.0F, 1, 0.0F);
      ((TranslateAnimation)localObject).setDuration(1000L);
      localAnimationSet.addAnimation((Animation)localObject);
      return localAnimationSet;
    }
    localObject = new TranslateAnimation(1, 0.0F, 1, 0.0F, 1, 0.0F, 1, -1.0F);
    ((TranslateAnimation)localObject).setDuration(1000L);
    localAnimationSet.addAnimation((Animation)localObject);
    return localAnimationSet;
  }
  
  public static String getLocalIpAddress()
  {
    try
    {
      InetAddress localInetAddress;
      do
      {
        localObject = NetworkInterface.getNetworkInterfaces();
        Enumeration localEnumeration;
        while (!localEnumeration.hasMoreElements())
        {
          if (!((Enumeration)localObject).hasMoreElements()) {
            break;
          }
          localEnumeration = ((NetworkInterface)((Enumeration)localObject).nextElement()).getInetAddresses();
        }
        localInetAddress = (InetAddress)localEnumeration.nextElement();
      } while (localInetAddress.isLoopbackAddress());
      Object localObject = localInetAddress.getHostAddress().toString();
      return (String)localObject;
    }
    catch (SocketException localSocketException)
    {
      Log.e(localSocketException.toString());
    }
    return null;
  }
  
  public static Location getLocation(Context paramContext)
  {
    int i = paramContext.checkCallingOrSelfPermission("android.permission.ACCESS_FINE_LOCATION");
    int j = paramContext.checkCallingOrSelfPermission("android.permission.ACCESS_COARSE_LOCATION");
    if ((i == 0) || (j == 0))
    {
      paramContext = (LocationManager)paramContext.getSystemService("location");
      if (paramContext != null)
      {
        if ((i == 0) && (paramContext.isProviderEnabled("gps"))) {
          return paramContext.getLastKnownLocation("gps");
        }
        if ((j == 0) && (paramContext.isProviderEnabled("network"))) {
          return paramContext.getLastKnownLocation("network");
        }
      }
    }
    return null;
  }
  
  public static int getMemoryClass(Context paramContext)
  {
    try
    {
      int i = ((Integer)ActivityManager.class.getMethod("getMemoryClass", new Class[0]).invoke((ActivityManager)paramContext.getSystemService("activity"), new Object[0])).intValue();
      return i;
    }
    catch (Exception paramContext) {}
    return 16;
  }
  
  public static String getTelephonyDeviceId(Context paramContext)
  {
    if (paramContext.checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") == 0) {
      return ((TelephonyManager)paramContext.getSystemService("phone")).getDeviceId();
    }
    return "";
  }
  
  public static void initializeAnimations(Context paramContext)
  {
    Resources localResources = paramContext.getResources();
    sFadeInAnimationId = localResources.getIdentifier("fade_in", "anim", paramContext.getPackageName());
    sFadeOutAnimationId = localResources.getIdentifier("fade_out", "anim", paramContext.getPackageName());
    sSlideInBottomAnimationId = localResources.getIdentifier("slide_bottom_in", "anim", paramContext.getPackageName());
    sSlideOutBottomAnimationId = localResources.getIdentifier("slide_bottom_out", "anim", paramContext.getPackageName());
    sSlideInTopAnimationId = localResources.getIdentifier("slide_top_in", "anim", paramContext.getPackageName());
    sSlideOutTopAnimationId = localResources.getIdentifier("slide_top_out", "anim", paramContext.getPackageName());
    sSlideInLeftAnimationId = localResources.getIdentifier("slide_left_in", "anim", paramContext.getPackageName());
    sSlideOutLeftAnimationId = localResources.getIdentifier("slide_left_out", "anim", paramContext.getPackageName());
    sSlideInRightAnimationId = localResources.getIdentifier("slide_right_in", "anim", paramContext.getPackageName());
    sSlideOutRightAnimationId = localResources.getIdentifier("slide_right_out", "anim", paramContext.getPackageName());
  }
  
  public static boolean isNetworkAvailable(Context paramContext)
  {
    if (paramContext.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") == 0)
    {
      paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
      if (paramContext == null) {}
      int i;
      do
      {
        return false;
        i = paramContext.getType();
      } while ((i != 1) && (i != 0));
      return paramContext.isConnected();
    }
    return true;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\adsdk\sdk\Util.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */