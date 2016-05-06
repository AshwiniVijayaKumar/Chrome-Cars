package com.adsdk.sdk;

import android.content.Context;
import android.location.Location;
import android.os.Build.VERSION;
import android.os.Handler;
import com.adsdk.sdk.video.ResourceManager;
import com.adsdk.sdk.video.RichMediaAd;
import com.adsdk.sdk.video.TrackerService;
import java.io.InputStream;
import java.util.HashMap;

public class AdManager
{
  private static Context mContext;
  private static HashMap<Long, AdManager> sRunningAds = new HashMap();
  private boolean mEnabled = true;
  private Handler mHandler;
  private boolean mIncludeLocation;
  private AdListener mListener;
  private String mPublisherId;
  private AdRequest mRequest = null;
  private Thread mRequestThread;
  private RichMediaAd mResponse;
  private String mUniqueId1;
  private String mUniqueId2;
  private String mUserAgent;
  private String requestURL;
  
  public AdManager(Context paramContext, String paramString1, String paramString2, boolean paramBoolean)
    throws IllegalArgumentException
  {
    setmContext(paramContext);
    this.requestURL = paramString1;
    this.mPublisherId = paramString2;
    this.mIncludeLocation = paramBoolean;
    this.mRequestThread = null;
    this.mHandler = new Handler();
    initialize();
  }
  
  public static void closeRunningAd(RichMediaAd paramRichMediaAd, boolean paramBoolean)
  {
    AdManager localAdManager = (AdManager)sRunningAds.remove(Long.valueOf(paramRichMediaAd.getTimestamp()));
    if (localAdManager == null)
    {
      Log.d("Cannot find AdManager with running ad:" + paramRichMediaAd.getTimestamp());
      return;
    }
    Log.d("Notify closing event to AdManager with running ad:" + paramRichMediaAd.getTimestamp());
    localAdManager.notifyAdClose(paramRichMediaAd, paramBoolean);
  }
  
  public static AdManager getAdManager(RichMediaAd paramRichMediaAd)
  {
    AdManager localAdManager = (AdManager)sRunningAds.remove(Long.valueOf(paramRichMediaAd.getTimestamp()));
    if (localAdManager == null) {
      Log.d("Cannot find AdManager with running ad:" + paramRichMediaAd.getTimestamp());
    }
    return localAdManager;
  }
  
  private Context getContext()
  {
    return getmContext();
  }
  
  private AdRequest getRequest()
  {
    if (this.mRequest == null)
    {
      this.mRequest = new AdRequest();
      this.mRequest.setDeviceId(this.mUniqueId1);
      this.mRequest.setDeviceId2(this.mUniqueId2);
      this.mRequest.setPublisherId(this.mPublisherId);
      this.mRequest.setUserAgent(this.mUserAgent);
      this.mRequest.setUserAgent2(Util.buildUserAgent());
    }
    Location localLocation = null;
    if (this.mIncludeLocation) {
      localLocation = Util.getLocation(getContext());
    }
    if (localLocation != null)
    {
      Log.d("location is longitude: " + localLocation.getLongitude() + ", latitude: " + localLocation.getLatitude());
      this.mRequest.setLatitude(localLocation.getLatitude());
      this.mRequest.setLongitude(localLocation.getLongitude());
    }
    for (;;)
    {
      this.mRequest.setConnectionType(Util.getConnectionType(getContext()));
      this.mRequest.setIpAddress(Util.getLocalIpAddress());
      this.mRequest.setTimestamp(System.currentTimeMillis());
      this.mRequest.setType(1);
      this.mRequest.setRequestURL(this.requestURL);
      Log.d("Getting new request:" + this.mRequest.toString());
      return this.mRequest;
      this.mRequest.setLatitude(0.0D);
      this.mRequest.setLongitude(0.0D);
    }
  }
  
  private static Context getmContext()
  {
    return mContext;
  }
  
  private void initialize()
    throws IllegalArgumentException
  {
    this.mUserAgent = Util.getDefaultUserAgentString(getContext());
    Log.LOGGING_ENABLED = Log.isLoggingEnabled(getmContext());
    Log.d("Ad SDK Version:4.1.6");
    this.mUniqueId1 = Util.getTelephonyDeviceId(getContext());
    this.mUniqueId2 = Util.getDeviceId(getContext());
    if ((this.mPublisherId == null) || (this.mPublisherId.length() == 0))
    {
      Log.e("Publisher Id cannot be null or empty");
      throw new IllegalArgumentException("User Id cannot be null or empty");
    }
    if ((this.mUniqueId2 == null) || (this.mUniqueId2.length() == 0))
    {
      Log.e("Cannot get system device Id");
      throw new IllegalArgumentException("System Device Id cannot be null or empty");
    }
    Log.d("AdManager Publisher Id:" + this.mPublisherId + " Device Id:" + this.mUniqueId1 + " DeviceId2:" + this.mUniqueId2);
    if (Util.getMemoryClass(getContext()) > 16) {}
    for (boolean bool = true;; bool = false)
    {
      this.mEnabled = bool;
      Util.initializeAnimations(getContext());
      return;
    }
  }
  
  private void notifyAdClose(final RichMediaAd paramRichMediaAd, final boolean paramBoolean)
  {
    if (this.mListener != null)
    {
      Log.d("Ad Close. Result:" + paramBoolean);
      this.mHandler.post(new Runnable()
      {
        public void run()
        {
          AdManager.this.mListener.adClosed(paramRichMediaAd, paramBoolean);
        }
      });
    }
  }
  
  private void notifyAdShown(final RichMediaAd paramRichMediaAd, final boolean paramBoolean)
  {
    if (this.mListener != null)
    {
      Log.d("Ad Shown. Result:" + paramBoolean);
      this.mHandler.post(new Runnable()
      {
        public void run()
        {
          AdManager.this.mListener.adShown(paramRichMediaAd, paramBoolean);
        }
      });
    }
    this.mResponse = null;
  }
  
  private void notifyNoAdFound()
  {
    if (this.mListener != null)
    {
      Log.d("No ad found.");
      this.mHandler.post(new Runnable()
      {
        public void run()
        {
          AdManager.this.mListener.noAdFound();
        }
      });
    }
    this.mResponse = null;
  }
  
  private static void setmContext(Context paramContext)
  {
    mContext = paramContext;
  }
  
  public boolean isAdLoaded()
  {
    return this.mResponse != null;
  }
  
  public void release()
  {
    TrackerService.release();
    ResourceManager.cancel();
  }
  
  public void requestAd()
  {
    if (!this.mEnabled)
    {
      Log.w("Cannot request rich adds on low memory devices");
      return;
    }
    if (this.mRequestThread == null)
    {
      Log.d("Requesting Ad (v4.1.6-1.0)");
      this.mResponse = null;
      this.mRequestThread = new Thread(new Runnable()
      {
        public void run()
        {
          for (;;)
          {
            if (!ResourceManager.isDownloading()) {
              Log.d("starting request thread");
            }
            try
            {
              RequestRichMediaAd localRequestRichMediaAd = new RequestRichMediaAd();
              AdRequest localAdRequest = AdManager.this.getRequest();
              AdManager.this.mResponse = ((RichMediaAd)localRequestRichMediaAd.sendRequest(localAdRequest));
              if ((AdManager.this.mResponse.getVideo() != null) && (Build.VERSION.SDK_INT < 8))
              {
                Log.d("Not capable of video");
                AdManager.this.notifyNoAdFound();
              }
              for (;;)
              {
                Log.d("finishing ad request thread");
                AdManager.this.mRequestThread = null;
                return;
                try
                {
                  Thread.sleep(200L);
                }
                catch (InterruptedException localInterruptedException) {}
                break;
                if ((AdManager.this.mResponse.getType() != 3) && (AdManager.this.mResponse.getType() != 4) && (AdManager.this.mResponse.getType() != 5) && (AdManager.this.mResponse.getType() != 6)) {
                  break label277;
                }
                Log.d("response OK received");
                if (AdManager.this.mListener != null) {
                  AdManager.this.mHandler.post(new Runnable()
                  {
                    public void run()
                    {
                      AdManager.this.mListener.adLoadSucceeded(AdManager.this.mResponse);
                    }
                  });
                }
              }
            }
            catch (Throwable localThrowable)
            {
              for (;;)
              {
                AdManager.this.mResponse = new RichMediaAd();
                AdManager.this.mResponse.setType(-1);
                if (AdManager.this.mListener != null)
                {
                  Log.d("Ad Load failed. Reason:" + localThrowable);
                  localThrowable.printStackTrace();
                  AdManager.this.mHandler.post(new Runnable()
                  {
                    public void run()
                    {
                      AdManager.this.notifyNoAdFound();
                    }
                  });
                  continue;
                  label277:
                  if (AdManager.this.mResponse.getType() == 2)
                  {
                    Log.d("response NO AD received");
                    if (AdManager.this.mListener != null) {
                      AdManager.this.mHandler.post(new Runnable()
                      {
                        public void run()
                        {
                          AdManager.this.notifyNoAdFound();
                        }
                      });
                    }
                  }
                  else
                  {
                    Log.w("response BANNER received");
                    if (AdManager.this.mListener != null) {
                      AdManager.this.mHandler.post(new Runnable()
                      {
                        public void run()
                        {
                          AdManager.this.notifyNoAdFound();
                        }
                      });
                    }
                  }
                }
              }
            }
          }
        }
      });
      this.mRequestThread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler()
      {
        public void uncaughtException(Thread paramAnonymousThread, Throwable paramAnonymousThrowable)
        {
          AdManager.this.mResponse = new RichMediaAd();
          AdManager.this.mResponse.setType(-1);
          Log.e("Handling exception in ad request thread", paramAnonymousThrowable);
          AdManager.this.mRequestThread = null;
        }
      });
      this.mRequestThread.start();
      return;
    }
    Log.w("Request thread already running");
  }
  
  public void requestAd(final InputStream paramInputStream)
  {
    if (!this.mEnabled)
    {
      Log.w("Cannot request rich adds on low memory devices");
      return;
    }
    if (this.mRequestThread == null)
    {
      Log.d("Requesting Ad (v4.1.6-1.0)");
      this.mResponse = null;
      this.mRequestThread = new Thread(new Runnable()
      {
        public void run()
        {
          for (;;)
          {
            if (!ResourceManager.isDownloading()) {
              Log.d("starting request thread");
            }
            try
            {
              RequestRichMediaAd localRequestRichMediaAd = new RequestRichMediaAd(paramInputStream);
              AdRequest localAdRequest = AdManager.this.getRequest();
              AdManager.this.mResponse = ((RichMediaAd)localRequestRichMediaAd.sendRequest(localAdRequest));
              if (AdManager.this.mResponse.getType() != 2)
              {
                Log.d("response OK received");
                if (AdManager.this.mListener != null) {
                  AdManager.this.mHandler.post(new Runnable()
                  {
                    public void run()
                    {
                      AdManager.this.mListener.adLoadSucceeded(AdManager.this.mResponse);
                    }
                  });
                }
              }
              for (;;)
              {
                Log.d("finishing ad request thread");
                AdManager.this.mRequestThread = null;
                return;
                try
                {
                  Thread.sleep(200L);
                }
                catch (InterruptedException localInterruptedException) {}
                break;
                Log.d("response NO AD received");
                if (AdManager.this.mListener != null) {
                  AdManager.this.mHandler.post(new Runnable()
                  {
                    public void run()
                    {
                      AdManager.this.notifyNoAdFound();
                    }
                  });
                }
              }
            }
            catch (Throwable localThrowable)
            {
              for (;;)
              {
                AdManager.this.mResponse = new RichMediaAd();
                AdManager.this.mResponse.setType(-1);
                if (AdManager.this.mListener != null)
                {
                  Log.d("Ad Load failed. Reason:" + localThrowable);
                  localThrowable.printStackTrace();
                  AdManager.this.mHandler.post(new Runnable()
                  {
                    public void run()
                    {
                      AdManager.this.notifyNoAdFound();
                    }
                  });
                }
              }
            }
          }
        }
      });
      this.mRequestThread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler()
      {
        public void uncaughtException(Thread paramAnonymousThread, Throwable paramAnonymousThrowable)
        {
          AdManager.this.mResponse = new RichMediaAd();
          AdManager.this.mResponse.setType(-1);
          Log.e("Handling exception in ad request thread", paramAnonymousThrowable);
          AdManager.this.mRequestThread = null;
        }
      });
      this.mRequestThread.start();
      return;
    }
    Log.w("Request thread already running");
  }
  
  public void requestAdAndShow(long paramLong)
  {
    AdListener localAdListener = this.mListener;
    this.mListener = null;
    requestAd();
    long l2 = System.currentTimeMillis();
    long l1 = l2;
    for (;;)
    {
      if ((isAdLoaded()) || (l1 >= l2 + paramLong))
      {
        this.mListener = localAdListener;
        showAd();
        return;
      }
      try
      {
        Thread.sleep(200L);
        l1 = System.currentTimeMillis();
      }
      catch (InterruptedException localInterruptedException)
      {
        for (;;) {}
      }
    }
  }
  
  public void setListener(AdListener paramAdListener)
  {
    this.mListener = paramAdListener;
  }
  
  public void setRequestURL(String paramString)
  {
    this.requestURL = paramString;
  }
  
  /* Error */
  public void showAd()
  {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial 201	com/adsdk/sdk/AdManager:getContext	()Landroid/content/Context;
    //   4: checkcast 396	android/app/Activity
    //   7: astore 7
    //   9: aload_0
    //   10: getfield 105	com/adsdk/sdk/AdManager:mResponse	Lcom/adsdk/sdk/video/RichMediaAd;
    //   13: ifnull +25 -> 38
    //   16: aload_0
    //   17: getfield 105	com/adsdk/sdk/AdManager:mResponse	Lcom/adsdk/sdk/video/RichMediaAd;
    //   20: invokevirtual 399	com/adsdk/sdk/video/RichMediaAd:getType	()I
    //   23: iconst_2
    //   24: if_icmpeq +14 -> 38
    //   27: aload_0
    //   28: getfield 105	com/adsdk/sdk/AdManager:mResponse	Lcom/adsdk/sdk/video/RichMediaAd;
    //   31: invokevirtual 399	com/adsdk/sdk/video/RichMediaAd:getType	()I
    //   34: iconst_m1
    //   35: if_icmpne +13 -> 48
    //   38: aload_0
    //   39: aload_0
    //   40: getfield 105	com/adsdk/sdk/AdManager:mResponse	Lcom/adsdk/sdk/video/RichMediaAd;
    //   43: iconst_0
    //   44: invokespecial 401	com/adsdk/sdk/AdManager:notifyAdShown	(Lcom/adsdk/sdk/video/RichMediaAd;Z)V
    //   47: return
    //   48: aload_0
    //   49: getfield 105	com/adsdk/sdk/AdManager:mResponse	Lcom/adsdk/sdk/video/RichMediaAd;
    //   52: astore 6
    //   54: iconst_0
    //   55: istore 4
    //   57: iconst_0
    //   58: istore 5
    //   60: iconst_0
    //   61: istore_3
    //   62: iload 4
    //   64: istore_1
    //   65: iload 5
    //   67: istore_2
    //   68: aload_0
    //   69: invokespecial 201	com/adsdk/sdk/AdManager:getContext	()Landroid/content/Context;
    //   72: invokestatic 404	com/adsdk/sdk/Util:isNetworkAvailable	(Landroid/content/Context;)Z
    //   75: ifeq +154 -> 229
    //   78: iload 4
    //   80: istore_1
    //   81: iload 5
    //   83: istore_2
    //   84: aload 6
    //   86: invokestatic 249	java/lang/System:currentTimeMillis	()J
    //   89: invokevirtual 405	com/adsdk/sdk/video/RichMediaAd:setTimestamp	(J)V
    //   92: iload 4
    //   94: istore_1
    //   95: iload 5
    //   97: istore_2
    //   98: new 140	java/lang/StringBuilder
    //   101: dup
    //   102: ldc_w 407
    //   105: invokespecial 145	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   108: aload 6
    //   110: invokevirtual 410	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   113: invokevirtual 153	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   116: invokestatic 413	com/adsdk/sdk/Log:v	(Ljava/lang/String;)V
    //   119: iload 4
    //   121: istore_1
    //   122: iload 5
    //   124: istore_2
    //   125: new 415	android/content/Intent
    //   128: dup
    //   129: aload 7
    //   131: ldc_w 417
    //   134: invokespecial 420	android/content/Intent:<init>	(Landroid/content/Context;Ljava/lang/Class;)V
    //   137: astore 8
    //   139: iload 4
    //   141: istore_1
    //   142: iload 5
    //   144: istore_2
    //   145: aload 8
    //   147: ldc_w 422
    //   150: aload 6
    //   152: invokevirtual 426	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/io/Serializable;)Landroid/content/Intent;
    //   155: pop
    //   156: iload 4
    //   158: istore_1
    //   159: iload 5
    //   161: istore_2
    //   162: aload 7
    //   164: aload 8
    //   166: iconst_0
    //   167: invokevirtual 430	android/app/Activity:startActivityForResult	(Landroid/content/Intent;I)V
    //   170: iload 4
    //   172: istore_1
    //   173: iload 5
    //   175: istore_2
    //   176: aload 7
    //   178: aload 6
    //   180: invokevirtual 433	com/adsdk/sdk/video/RichMediaAd:getAnimation	()I
    //   183: invokestatic 437	com/adsdk/sdk/Util:getEnterAnimation	(I)I
    //   186: aload 6
    //   188: invokevirtual 433	com/adsdk/sdk/video/RichMediaAd:getAnimation	()I
    //   191: invokestatic 440	com/adsdk/sdk/Util:getExitAnimation	(I)I
    //   194: invokestatic 444	com/adsdk/sdk/video/RichMediaActivity:setActivityAnimation	(Landroid/app/Activity;II)V
    //   197: iconst_1
    //   198: istore_1
    //   199: iconst_1
    //   200: istore_2
    //   201: iconst_1
    //   202: istore_3
    //   203: getstatic 65	com/adsdk/sdk/AdManager:sRunningAds	Ljava/util/HashMap;
    //   206: aload 6
    //   208: invokevirtual 128	com/adsdk/sdk/video/RichMediaAd:getTimestamp	()J
    //   211: invokestatic 134	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   214: aload_0
    //   215: invokevirtual 448	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   218: pop
    //   219: iload_3
    //   220: istore_1
    //   221: aload_0
    //   222: aload 6
    //   224: iload_1
    //   225: invokespecial 401	com/adsdk/sdk/AdManager:notifyAdShown	(Lcom/adsdk/sdk/video/RichMediaAd;Z)V
    //   228: return
    //   229: iload 4
    //   231: istore_1
    //   232: iload 5
    //   234: istore_2
    //   235: ldc_w 450
    //   238: invokestatic 158	com/adsdk/sdk/Log:d	(Ljava/lang/String;)V
    //   241: iload_3
    //   242: istore_1
    //   243: goto -22 -> 221
    //   246: astore 7
    //   248: iload_1
    //   249: istore_2
    //   250: ldc_w 452
    //   253: aload 7
    //   255: invokestatic 455	com/adsdk/sdk/Log:e	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   258: aload_0
    //   259: aload 6
    //   261: iload_1
    //   262: invokespecial 401	com/adsdk/sdk/AdManager:notifyAdShown	(Lcom/adsdk/sdk/video/RichMediaAd;Z)V
    //   265: return
    //   266: astore 7
    //   268: aload_0
    //   269: aload 6
    //   271: iload_2
    //   272: invokespecial 401	com/adsdk/sdk/AdManager:notifyAdShown	(Lcom/adsdk/sdk/video/RichMediaAd;Z)V
    //   275: aload 7
    //   277: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	278	0	this	AdManager
    //   64	198	1	bool1	boolean
    //   67	205	2	bool2	boolean
    //   61	181	3	bool3	boolean
    //   55	175	4	bool4	boolean
    //   58	175	5	bool5	boolean
    //   52	218	6	localRichMediaAd	RichMediaAd
    //   7	170	7	localActivity	android.app.Activity
    //   246	8	7	localException	Exception
    //   266	10	7	localObject	Object
    //   137	28	8	localIntent	android.content.Intent
    // Exception table:
    //   from	to	target	type
    //   68	78	246	java/lang/Exception
    //   84	92	246	java/lang/Exception
    //   98	119	246	java/lang/Exception
    //   125	139	246	java/lang/Exception
    //   145	156	246	java/lang/Exception
    //   162	170	246	java/lang/Exception
    //   176	197	246	java/lang/Exception
    //   203	219	246	java/lang/Exception
    //   235	241	246	java/lang/Exception
    //   68	78	266	finally
    //   84	92	266	finally
    //   98	119	266	finally
    //   125	139	266	finally
    //   145	156	266	finally
    //   162	170	266	finally
    //   176	197	266	finally
    //   203	219	266	finally
    //   235	241	266	finally
    //   250	258	266	finally
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\adsdk\sdk\AdManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */