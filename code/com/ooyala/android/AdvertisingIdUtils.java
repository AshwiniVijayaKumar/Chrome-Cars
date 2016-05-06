package com.ooyala.android;

import android.content.Context;
import android.os.Handler;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.ooyala.android.util.DebugMode;
import java.io.IOException;

public class AdvertisingIdUtils
{
  private static final String TAG = "AdvertisingIdUtils";
  private static String s_advertisingId;
  
  public static String getAdvertisingId()
  {
    try
    {
      String str = s_advertisingId;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public static void getAndSetAdvertisingId(Context paramContext, final IAdvertisingIdListener paramIAdvertisingIdListener)
  {
    final Handler localHandler = new Handler(paramContext.getMainLooper());
    paramContext = new Thread(new Runnable()
    {
      public void run()
      {
        try
        {
          AdvertisingIdClient.Info localInfo = AdvertisingIdClient.getAdvertisingIdInfo(this.val$context);
          AdvertisingIdUtils.postAdvertisingIdSuccess(localHandler, localInfo.getId(), paramIAdvertisingIdListener);
          return;
        }
        catch (IllegalStateException localIllegalStateException)
        {
          AdvertisingIdUtils.postAdvertisingIdError(localHandler, localIllegalStateException, paramIAdvertisingIdListener);
          return;
        }
        catch (GooglePlayServicesRepairableException localGooglePlayServicesRepairableException)
        {
          AdvertisingIdUtils.postAdvertisingIdError(localHandler, localGooglePlayServicesRepairableException, paramIAdvertisingIdListener);
          return;
        }
        catch (IOException localIOException)
        {
          AdvertisingIdUtils.postAdvertisingIdError(localHandler, localIOException, paramIAdvertisingIdListener);
          return;
        }
        catch (GooglePlayServicesNotAvailableException localGooglePlayServicesNotAvailableException)
        {
          AdvertisingIdUtils.postAdvertisingIdError(localHandler, localGooglePlayServicesNotAvailableException, paramIAdvertisingIdListener);
        }
      }
    }, "getAndSetAdvertisingId");
    paramContext.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler()
    {
      public void uncaughtException(Thread paramAnonymousThread, Throwable paramAnonymousThrowable)
      {
        if ((paramAnonymousThrowable instanceof Exception)) {
          AdvertisingIdUtils.postAdvertisingIdError(this.val$h, (Exception)paramAnonymousThrowable, paramIAdvertisingIdListener);
        }
      }
    });
    paramContext.start();
  }
  
  private static void postAdvertisingIdError(Handler paramHandler, Exception paramException, IAdvertisingIdListener paramIAdvertisingIdListener)
  {
    DebugMode.logE("AdvertisingIdUtils", "postAdvertisingIdError", paramException);
    paramHandler.post(new Runnable()
    {
      public void run()
      {
        this.val$listener.onAdvertisingIdError(this.val$oe);
      }
    });
  }
  
  private static void postAdvertisingIdSuccess(Handler paramHandler, final String paramString, IAdvertisingIdListener paramIAdvertisingIdListener)
  {
    DebugMode.logV("AdvertisingIdUtils", "postAdvertisingIdSuccess" + paramString);
    paramHandler.post(new Runnable()
    {
      public void run()
      {
        this.val$listener.onAdvertisingIdSuccess(paramString);
      }
    });
  }
  
  public static void setAdvertisingId(String paramString)
  {
    try
    {
      s_advertisingId = paramString;
      DebugMode.logD("AdvertisingIdUtils", "s_advertisingId = " + s_advertisingId);
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public static abstract interface IAdvertisingIdListener
  {
    public abstract void onAdvertisingIdError(OoyalaException paramOoyalaException);
    
    public abstract void onAdvertisingIdSuccess(String paramString);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\AdvertisingIdUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */