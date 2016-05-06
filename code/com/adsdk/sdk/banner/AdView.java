package com.adsdk.sdk.banner;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.location.Location;
import android.location.LocationManager;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.adsdk.sdk.AdListener;
import com.adsdk.sdk.AdRequest;
import com.adsdk.sdk.BannerAd;
import com.adsdk.sdk.Log;
import com.adsdk.sdk.RequestBannerAd;
import com.adsdk.sdk.Util;
import com.adsdk.sdk.mraid.MoPubView;
import java.io.InputStream;
import java.util.Timer;

public class AdView
  extends FrameLayout
{
  public static final int LIVE = 0;
  public static final int TEST = 1;
  private AdListener adListener;
  private boolean animation;
  private boolean includeLocation = false;
  private int isAccessCoarseLocation;
  private int isAccessFineLocation;
  private boolean isInternalBrowser = false;
  private Thread loadContentThread;
  private LocationManager locationManager;
  private BannerAdView mBannerView;
  private Context mContext = null;
  protected boolean mIsInForeground;
  private MoPubView mMoPubview;
  private BroadcastReceiver mScreenStateReceiver;
  private String mUserAgent;
  private String publisherId;
  private Timer reloadTimer;
  private AdRequest request;
  private String requestURL = null;
  private BannerAd response;
  private final Runnable showContent = new Runnable()
  {
    public void run()
    {
      AdView.this.showContent();
    }
  };
  private int telephonyPermission;
  private final Handler updateHandler = new Handler();
  private InputStream xml;
  
  public AdView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mContext = paramContext;
    int i;
    if (paramAttributeSet != null)
    {
      int j = paramAttributeSet.getAttributeCount();
      i = 0;
      if (i < j) {}
    }
    else
    {
      initialize(paramContext);
      return;
    }
    String str = paramAttributeSet.getAttributeName(i);
    if (str.equals("publisherId")) {
      this.publisherId = paramAttributeSet.getAttributeValue(i);
    }
    for (;;)
    {
      i += 1;
      break;
      if (str.equals("request_url")) {
        this.requestURL = paramAttributeSet.getAttributeValue(i);
      } else if (str.equals("animation")) {
        this.animation = paramAttributeSet.getAttributeBooleanValue(i, false);
      } else if (str.equals("includeLocation")) {
        this.includeLocation = paramAttributeSet.getAttributeBooleanValue(i, false);
      }
    }
  }
  
  public AdView(Context paramContext, InputStream paramInputStream, String paramString1, String paramString2, boolean paramBoolean1, boolean paramBoolean2)
  {
    super(paramContext);
    this.xml = paramInputStream;
    this.requestURL = paramString1;
    this.mContext = paramContext;
    this.publisherId = paramString2;
    this.includeLocation = paramBoolean1;
    this.animation = paramBoolean2;
    initialize(paramContext);
  }
  
  public AdView(Context paramContext, String paramString1, InputStream paramInputStream, String paramString2, boolean paramBoolean1, boolean paramBoolean2)
  {
    this(paramContext, paramInputStream, paramString1, paramString2, paramBoolean1, paramBoolean2);
  }
  
  public AdView(Context paramContext, String paramString1, String paramString2)
  {
    this(paramContext, paramString1, paramString2, false, false);
  }
  
  public AdView(Context paramContext, String paramString1, String paramString2, boolean paramBoolean1, boolean paramBoolean2)
  {
    this(paramContext, paramString1, paramString2, paramBoolean1, paramBoolean2, null);
  }
  
  public AdView(Context paramContext, String paramString1, String paramString2, boolean paramBoolean1, boolean paramBoolean2, AdListener paramAdListener)
  {
    super(paramContext);
    this.requestURL = paramString1;
    this.mContext = paramContext;
    this.publisherId = paramString2;
    this.includeLocation = paramBoolean1;
    this.animation = paramBoolean2;
    this.adListener = paramAdListener;
    paramString1 = new StringBuilder("AdListener: ");
    paramBoolean1 = bool;
    if (this.adListener == null) {
      paramBoolean1 = true;
    }
    Log.d(paramBoolean1);
    initialize(paramContext);
  }
  
  private Location getLocation()
  {
    if (this.locationManager != null)
    {
      if ((this.isAccessFineLocation == 0) && (this.locationManager.isProviderEnabled("gps"))) {
        return this.locationManager.getLastKnownLocation("gps");
      }
      if ((this.isAccessCoarseLocation == 0) && (this.locationManager.isProviderEnabled("network"))) {
        return this.locationManager.getLastKnownLocation("network");
      }
    }
    return null;
  }
  
  private AdRequest getRequest()
  {
    Object localObject;
    if (this.request == null)
    {
      this.request = new AdRequest();
      if (this.telephonyPermission == 0)
      {
        localObject = (TelephonyManager)getContext().getSystemService("phone");
        this.request.setDeviceId(((TelephonyManager)localObject).getDeviceId());
        this.request.setPublisherId(this.publisherId);
        this.request.setUserAgent(this.mUserAgent);
        this.request.setUserAgent2(Util.buildUserAgent());
        Log.d("ADSDK", "WebKit UserAgent:" + this.request.getUserAgent());
        Log.d("ADSDK", "SDK built UserAgent:" + this.request.getUserAgent2());
      }
    }
    else
    {
      localObject = null;
      if (this.includeLocation) {
        localObject = getLocation();
      }
      if (localObject == null) {
        break label259;
      }
      Log.d("ADSDK", "location is longitude: " + ((Location)localObject).getLongitude() + ", latitude: " + ((Location)localObject).getLatitude());
      this.request.setLatitude(((Location)localObject).getLatitude());
      this.request.setLongitude(((Location)localObject).getLongitude());
    }
    for (;;)
    {
      this.request.setType(0);
      this.request.setRequestURL(this.requestURL);
      return this.request;
      this.request.setDeviceId(Util.getDeviceId(this.mContext));
      break;
      label259:
      this.request.setLatitude(0.0D);
      this.request.setLongitude(0.0D);
    }
  }
  
  private void initialize(Context paramContext)
  {
    this.mUserAgent = Util.getDefaultUserAgentString(getContext());
    Log.LOGGING_ENABLED = Log.isLoggingEnabled(this.mContext);
    Log.d("ADSDK", "SDK Version:4.1.6");
    registerScreenStateBroadcastReceiver();
    this.locationManager = null;
    this.telephonyPermission = paramContext.checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE");
    this.isAccessFineLocation = paramContext.checkCallingOrSelfPermission("android.permission.ACCESS_FINE_LOCATION");
    this.isAccessCoarseLocation = paramContext.checkCallingOrSelfPermission("android.permission.ACCESS_COARSE_LOCATION");
    if ((this.isAccessFineLocation == 0) || (this.isAccessCoarseLocation == 0)) {
      this.locationManager = ((LocationManager)getContext().getSystemService("location"));
    }
  }
  
  private void loadContent()
  {
    Log.d("ADSDK", "load content");
    if (this.loadContentThread == null)
    {
      this.loadContentThread = new Thread(new Runnable()
      {
        public void run()
        {
          Log.d("ADSDK", "starting request thread");
          RequestBannerAd localRequestBannerAd;
          if (AdView.this.xml == null) {
            localRequestBannerAd = new RequestBannerAd();
          }
          try
          {
            for (;;)
            {
              AdView.this.response = ((BannerAd)localRequestBannerAd.sendRequest(AdView.this.getRequest()));
              if (AdView.this.response != null)
              {
                Log.d("ADSDK", "response received");
                Log.d("ADSDK", "getVisibility: " + AdView.this.getVisibility());
                AdView.this.updateHandler.post(AdView.this.showContent);
              }
              AdView.this.loadContentThread = null;
              Log.d("ADSDK", "finishing request thread");
              return;
              localRequestBannerAd = new RequestBannerAd(AdView.this.xml);
            }
          }
          catch (Throwable localThrowable)
          {
            for (;;)
            {
              AdView.this.notifyLoadAdFailed(localThrowable);
            }
          }
        }
      });
      this.loadContentThread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler()
      {
        public void uncaughtException(Thread paramAnonymousThread, Throwable paramAnonymousThrowable)
        {
          Log.e("ADSDK", "Exception in request thread", paramAnonymousThrowable);
          AdView.this.loadContentThread = null;
        }
      });
      this.loadContentThread.start();
    }
  }
  
  private void notifyLoadAdFailed(final Throwable paramThrowable)
  {
    this.updateHandler.post(new Runnable()
    {
      public void run()
      {
        Log.e("ADSDK", "Exception in request thread", paramThrowable);
        if (AdView.this.adListener != null)
        {
          Log.d("ADSDK", "notify bannerListener: " + AdView.this.adListener.getClass().getName());
          AdView.this.adListener.noAdFound();
        }
      }
    });
  }
  
  private void notifyNoAd()
  {
    this.updateHandler.post(new Runnable()
    {
      public void run()
      {
        Log.d("ADSDK", "No Ad");
        if (AdView.this.adListener != null) {
          AdView.this.adListener.noAdFound();
        }
      }
    });
  }
  
  private void registerScreenStateBroadcastReceiver()
  {
    this.mScreenStateReceiver = new BroadcastReceiver()
    {
      public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
      {
        if (paramAnonymousIntent.getAction().equals("android.intent.action.SCREEN_OFF")) {
          if (AdView.this.mIsInForeground)
          {
            Log.d("ADSDK", "Screen sleep with ad in foreground, disable refresh");
            AdView.this.pause();
          }
        }
        while (!paramAnonymousIntent.getAction().equals("android.intent.action.USER_PRESENT"))
        {
          return;
          Log.d("ADSDK", "Screen sleep but ad in background; refresh should already be disabled");
          return;
        }
        if (AdView.this.mIsInForeground)
        {
          AdView.this.resume();
          Log.d("ADSDK", "Screen wake / ad in foreground, reset refresh");
          return;
        }
        Log.d("ADSDK", "Screen wake but ad in background; don't enable refresh");
      }
    };
    IntentFilter localIntentFilter = new IntentFilter("android.intent.action.SCREEN_OFF");
    localIntentFilter.addAction("android.intent.action.USER_PRESENT");
    this.mContext.registerReceiver(this.mScreenStateReceiver, localIntentFilter);
  }
  
  private void showContent()
  {
    if (this.mMoPubview != null)
    {
      this.mMoPubview.destroy();
      removeView(this.mMoPubview);
    }
    if (this.mBannerView != null) {
      removeView(this.mBannerView);
    }
    if ((this.response.getType() == 1) || (this.response.getType() == 0))
    {
      this.mBannerView = new BannerAdView(this.mContext, this.response, this.animation, this.adListener);
      addView(this.mBannerView);
    }
    if (this.response.getType() == 7)
    {
      this.mMoPubview = new MoPubView(this.mContext);
      float f = this.mContext.getResources().getDisplayMetrics().density;
      addView(this.mMoPubview, new FrameLayout.LayoutParams(-2, (int)(50.0F * f + 0.5F)));
      com.adsdk.sdk.mraid.AdView localAdView = new com.adsdk.sdk.mraid.AdView(this.mContext, this.mMoPubview, this.response);
      this.mMoPubview.setAdListener(this.adListener);
      localAdView.setAdUnitId("");
      localAdView.loadAd();
    }
    if (this.response.getType() == 2) {
      notifyNoAd();
    }
    startReloadTimer();
  }
  
  private void startReloadTimer()
  {
    Log.d("ADSDK", "start reload timer");
    if ((this.reloadTimer == null) || (this.response.getRefresh() <= 0)) {
      return;
    }
    int i = this.response.getRefresh() * 1000;
    Log.d("ADSDK", "set timer: " + i);
    ReloadTask localReloadTask = new ReloadTask(this);
    this.reloadTimer.schedule(localReloadTask, i);
  }
  
  private void unregisterScreenStateBroadcastReceiver()
  {
    try
    {
      this.mContext.unregisterReceiver(this.mScreenStateReceiver);
      return;
    }
    catch (Exception localException)
    {
      Log.d("Failed to unregister screen state broadcast receiver (never registered).");
    }
  }
  
  public int getRefreshRate()
  {
    if (this.response != null) {
      return this.response.getRefresh();
    }
    return -1;
  }
  
  public boolean isInternalBrowser()
  {
    return this.isInternalBrowser;
  }
  
  public void loadNextAd()
  {
    Log.d("ADSDK", "load next ad");
    loadContent();
  }
  
  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    IntentFilter localIntentFilter = new IntentFilter("android.intent.action.SCREEN_OFF");
    localIntentFilter.addAction("android.intent.action.USER_PRESENT");
    this.mContext.registerReceiver(this.mScreenStateReceiver, localIntentFilter);
    Log.v("ADSDK", "onAttachedToWindow");
  }
  
  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    unregisterScreenStateBroadcastReceiver();
    Log.v("ADSDK", "onDetachedFromWindow");
  }
  
  protected void onWindowVisibilityChanged(int paramInt)
  {
    super.onWindowVisibilityChanged(paramInt);
    if (paramInt == 0)
    {
      this.mIsInForeground = true;
      resume();
    }
    for (;;)
    {
      Log.d("ADSDK", "onWindowVisibilityChanged: " + paramInt);
      return;
      this.mIsInForeground = false;
      pause();
    }
  }
  
  public void pause()
  {
    if (this.reloadTimer != null) {}
    try
    {
      Log.d("ADSDK", "cancel reload timer");
      this.reloadTimer.cancel();
      this.reloadTimer = null;
      return;
    }
    catch (Exception localException)
    {
      Log.e("ADSDK", "unable to cancel reloadTimer", localException);
    }
  }
  
  public void release()
  {
    unregisterScreenStateBroadcastReceiver();
    if (this.mMoPubview != null) {
      this.mMoPubview.destroy();
    }
  }
  
  public void resume()
  {
    if (this.reloadTimer != null)
    {
      this.reloadTimer.cancel();
      this.reloadTimer = null;
    }
    this.reloadTimer = new Timer();
    Log.d("ADSDK", "response: " + this.response);
    if ((this.response != null) && (this.response.getRefresh() > 0)) {
      startReloadTimer();
    }
    while ((this.response != null) && ((this.mMoPubview != null) || (this.mBannerView != null))) {
      return;
    }
    loadContent();
  }
  
  public void setAdListener(AdListener paramAdListener)
  {
    this.adListener = paramAdListener;
    if (this.mMoPubview != null) {
      this.mMoPubview.setAdListener(paramAdListener);
    }
    if (this.mBannerView != null) {
      this.mBannerView.setAdListener(paramAdListener);
    }
  }
  
  public void setHeight(int paramInt) {}
  
  public void setInternalBrowser(boolean paramBoolean)
  {
    this.isInternalBrowser = paramBoolean;
  }
  
  public void setWidth(int paramInt) {}
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\adsdk\sdk\banner\AdView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */