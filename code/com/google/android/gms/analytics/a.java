package com.google.android.gms.analytics;

import android.content.Context;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;

class a
  implements m
{
  private static Object qI = new Object();
  private static a qJ;
  private Context mContext;
  private AdvertisingIdClient.Info qK;
  private long qL;
  
  private a(Context paramContext)
  {
    this.mContext = paramContext;
  }
  
  private AdvertisingIdClient.Info bj()
  {
    try
    {
      AdvertisingIdClient.Info localInfo = AdvertisingIdClient.getAdvertisingIdInfo(this.mContext);
      return localInfo;
    }
    catch (IllegalStateException localIllegalStateException)
    {
      aa.w("IllegalStateException getting Ad Id Info");
      return null;
    }
    catch (GooglePlayServicesRepairableException localGooglePlayServicesRepairableException)
    {
      aa.w("GooglePlayServicesRepairableException getting Ad Id Info");
      return null;
    }
    catch (IOException localIOException)
    {
      aa.w("IOException getting Ad Id Info");
      return null;
    }
    catch (GooglePlayServicesNotAvailableException localGooglePlayServicesNotAvailableException)
    {
      aa.w("GooglePlayServicesNotAvailableException getting Ad Id Info");
      return null;
    }
    catch (Exception localException)
    {
      aa.w("Unknown exception. Could not get the ad Id.");
    }
    return null;
  }
  
  public static m m(Context paramContext)
  {
    if (qJ == null) {}
    synchronized (qI)
    {
      if (qJ == null) {
        qJ = new a(paramContext);
      }
      return qJ;
    }
  }
  
  public String getValue(String paramString)
  {
    long l = System.currentTimeMillis();
    if (l - this.qL > 1000L)
    {
      this.qK = bj();
      this.qL = l;
    }
    if (this.qK != null)
    {
      if ("&adid".equals(paramString)) {
        return this.qK.getId();
      }
      if ("&ate".equals(paramString))
      {
        if (this.qK.isLimitAdTrackingEnabled()) {
          return "1";
        }
        return "0";
      }
    }
    return null;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\analytics\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */