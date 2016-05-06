package com.google.android.gms.tagmanager;

import android.content.Context;
import android.os.Process;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.internal.fl;
import com.google.android.gms.internal.fn;
import java.io.IOException;

class a
{
  private static a TA;
  private static Object qI = new Object();
  private volatile long Tv = 900000L;
  private volatile long Tw = 30000L;
  private volatile long Tx;
  private final fl Ty;
  private a Tz = new a()
  {
    public AdvertisingIdClient.Info ix()
    {
      try
      {
        AdvertisingIdClient.Info localInfo = AdvertisingIdClient.getAdvertisingIdInfo(a.a(a.this));
        return localInfo;
      }
      catch (IllegalStateException localIllegalStateException)
      {
        bh.w("IllegalStateException getting Advertising Id Info");
        return null;
      }
      catch (GooglePlayServicesRepairableException localGooglePlayServicesRepairableException)
      {
        bh.w("GooglePlayServicesRepairableException getting Advertising Id Info");
        return null;
      }
      catch (IOException localIOException)
      {
        bh.w("IOException getting Ad Id Info");
        return null;
      }
      catch (GooglePlayServicesNotAvailableException localGooglePlayServicesNotAvailableException)
      {
        bh.w("GooglePlayServicesNotAvailableException getting Advertising Id Info");
        return null;
      }
      catch (Exception localException)
      {
        bh.w("Unknown exception. Could not get the Advertising Id Info.");
      }
      return null;
    }
  };
  private volatile boolean mClosed = false;
  private final Context mContext;
  private final Thread pI;
  private volatile AdvertisingIdClient.Info qK;
  
  private a(Context paramContext)
  {
    this(paramContext, null, fn.eI());
  }
  
  a(Context paramContext, a parama, fl paramfl)
  {
    this.Ty = paramfl;
    if (paramContext != null) {}
    for (this.mContext = paramContext.getApplicationContext();; this.mContext = paramContext)
    {
      if (parama != null) {
        this.Tz = parama;
      }
      this.pI = new Thread(new Runnable()
      {
        public void run()
        {
          a.b(a.this);
        }
      });
      return;
    }
  }
  
  static a E(Context paramContext)
  {
    if (TA == null) {}
    synchronized (qI)
    {
      if (TA == null)
      {
        TA = new a(paramContext);
        TA.start();
      }
      return TA;
    }
  }
  
  private void iv()
  {
    Process.setThreadPriority(10);
    while (!this.mClosed) {
      try
      {
        this.qK = this.Tz.ix();
        Thread.sleep(this.Tv);
      }
      catch (InterruptedException localInterruptedException)
      {
        bh.u("sleep interrupted in AdvertiserDataPoller thread; continuing");
      }
    }
  }
  
  private void iw()
  {
    if (this.Ty.currentTimeMillis() - this.Tx < this.Tw) {
      return;
    }
    interrupt();
    this.Tx = this.Ty.currentTimeMillis();
  }
  
  void interrupt()
  {
    this.pI.interrupt();
  }
  
  public boolean isLimitAdTrackingEnabled()
  {
    iw();
    if (this.qK == null) {
      return true;
    }
    return this.qK.isLimitAdTrackingEnabled();
  }
  
  public String iu()
  {
    iw();
    if (this.qK == null) {
      return null;
    }
    return this.qK.getId();
  }
  
  void start()
  {
    this.pI.start();
  }
  
  public static abstract interface a
  {
    public abstract AdvertisingIdClient.Info ix();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\tagmanager\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */