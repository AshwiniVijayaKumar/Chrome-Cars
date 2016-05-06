package com.appyjump.video.sdk;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.provider.Settings.Secure;
import android.util.DisplayMetrics;
import com.appyjump.video.sdk.data.Response;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class AppyJumpVideo
{
  static AdserveView mAdserveView;
  private String appId;
  private Context context;
  private int height;
  private String publisherId;
  private int width;
  
  public AppyJumpVideo(Context paramContext, String paramString1, String paramString2)
  {
    this.context = paramContext;
    this.publisherId = paramString1;
    this.appId = paramString2;
  }
  
  private String getApplicationName()
  {
    return (String)this.context.getApplicationInfo().loadLabel(this.context.getPackageManager());
  }
  
  private String getApplicationPackageName()
  {
    return this.context.getApplicationInfo().packageName;
  }
  
  private String getApplicationVersion()
  {
    try
    {
      String str = this.context.getPackageManager().getPackageInfo(this.context.getPackageName(), 0).versionName;
      return str;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      localNameNotFoundException.printStackTrace();
    }
    return null;
  }
  
  private String getDeviceHash(String paramString)
  {
    try
    {
      String str = getDeviceId();
      paramString = MessageDigest.getInstance(paramString);
      paramString.update(str.getBytes(), 0, str.length());
      paramString = new BigInteger(1, paramString.digest()).toString(16);
      return paramString;
    }
    catch (NoSuchAlgorithmException paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
  
  private String getDeviceId()
  {
    return Settings.Secure.getString(this.context.getContentResolver(), "android_id");
  }
  
  private String getIPAddress(boolean paramBoolean)
  {
    try
    {
      Iterator localIterator1 = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
      label12:
      if (localIterator1.hasNext())
      {
        Iterator localIterator2 = Collections.list(((NetworkInterface)localIterator1.next()).getInetAddresses()).iterator();
        for (;;)
        {
          if (!localIterator2.hasNext()) {
            break label12;
          }
          Object localObject = (InetAddress)localIterator2.next();
          if (((InetAddress)localObject).isLoopbackAddress()) {
            break;
          }
          localObject = ((InetAddress)localObject).getHostAddress();
          int i = ((String)localObject).indexOf(':');
          if (i < 0) {}
          for (i = 1; (paramBoolean) && (i != 0); i = 0) {
            return (String)localObject;
          }
        }
      }
      return "";
    }
    catch (Exception localException) {}
  }
  
  private void setWidthHeight()
  {
    DisplayMetrics localDisplayMetrics = this.context.getResources().getDisplayMetrics();
    this.width = localDisplayMetrics.widthPixels;
    this.height = localDisplayMetrics.heightPixels;
  }
  
  public void pause()
  {
    mAdserveView.pause();
  }
  
  public void resume()
  {
    mAdserveView.resume();
  }
  
  public void show()
  {
    setWidthHeight();
    int i = new Random().nextInt(1000);
    Object localObject = null;
    try
    {
      String str = "http://ssp.lkqd.net/ad?sid=" + this.appId + "&env=1" + "&format=1" + "&width=" + this.width + "&height=" + this.height + "&dnt=1" + "&output=vast" + "&ip=" + URLEncoder.encode(getIPAddress(true), "UTF-8") + "&rnd=" + i + "&idfa=" + getDeviceId() + "&idfamd5hex=" + getDeviceHash("MD5") + "&idfasha1hex=" + getDeviceHash("SHA-1") + "&aid=" + "&aidmd5hex=" + "&aidsha1hex=" + "&appstoreurl=" + "&appversion=" + URLEncoder.encode(getApplicationVersion(), "UTF-8") + "&appname=" + URLEncoder.encode(getApplicationName(), "UTF-8") + "&bundleid=" + URLEncoder.encode(getApplicationPackageName(), "UTF-8");
      localObject = str;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      for (;;)
      {
        localUnsupportedEncodingException.printStackTrace();
      }
    }
    mAdserveView = new AdserveView(this.context, this.publisherId, (String)localObject, true, false);
    mAdserveView.setBannerListener(new BannerListener()
    {
      public void adClicked() {}
      
      public void bannerLoadFailed(RequestException paramAnonymousRequestException) {}
      
      public void bannerLoadSucceeded()
      {
        if (Response.imageUrl == null) {
          return;
        }
        Intent localIntent = new Intent(AppyJumpVideo.this.context, Interstitial.class);
        localIntent.putExtra("videoURL", Response.imageUrl);
        localIntent.putExtra("clickURL", Response.clickUrl);
        AppyJumpVideo.this.context.startActivity(localIntent);
      }
      
      public void noAdFound() {}
    });
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\appyjump\video\sdk\AppyJumpVideo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */