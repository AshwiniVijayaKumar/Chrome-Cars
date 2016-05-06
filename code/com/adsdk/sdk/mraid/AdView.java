package com.adsdk.sdk.mraid;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout.LayoutParams;
import com.adsdk.sdk.BannerAd;
import com.adsdk.sdk.banner.InAppWebView;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;

public class AdView
  extends WebView
{
  public static final String AD_ORIENTATION_BOTH = "b";
  public static final String AD_ORIENTATION_LANDSCAPE_ONLY = "l";
  public static final String AD_ORIENTATION_PORTRAIT_ONLY = "p";
  public static final String DEVICE_ORIENTATION_LANDSCAPE = "l";
  public static final String DEVICE_ORIENTATION_PORTRAIT = "p";
  public static final String DEVICE_ORIENTATION_SQUARE = "s";
  public static final String DEVICE_ORIENTATION_UNKNOWN = "u";
  public static final String EXTRA_AD_CLICK_DATA = "com.mopub.intent.extra.AD_CLICK_DATA";
  private static final int MINIMUM_REFRESH_TIME_MILLISECONDS = 10000;
  private static final FrameLayout.LayoutParams WRAP_AND_CENTER_LAYOUT_PARAMS = new FrameLayout.LayoutParams(-2, -2, 17);
  private String mAdOrientation;
  private String mAdUnitId;
  private boolean mAutorefreshEnabled;
  private String mClickthroughUrl;
  private String mFailUrl;
  private final Handler mHandler = new Handler();
  private int mHeight;
  private String mImpressionUrl;
  private boolean mIsDestroyed;
  private boolean mIsLoading;
  private String mKeywords;
  private Location mLocation;
  protected MoPubView mMoPubView;
  private String mRedirectUrl;
  private Handler mRefreshHandler = new Handler();
  private Runnable mRefreshRunnable = new Runnable()
  {
    public void run()
    {
      AdView.this.loadAd();
    }
  };
  private int mRefreshTimeMilliseconds = 60000;
  private BannerAd mResponse;
  private String mResponseString;
  private String mUrl;
  private String mUserAgent;
  private int mWidth;
  
  public AdView(Context paramContext, MoPubView paramMoPubView, BannerAd paramBannerAd)
  {
    super(paramContext.getApplicationContext());
    this.mResponse = paramBannerAd;
    this.mMoPubView = paramMoPubView;
    this.mAutorefreshEnabled = true;
    this.mUserAgent = getSettings().getUserAgentString();
    disableScrollingAndZoom();
    getSettings().setJavaScriptEnabled(true);
    getSettings().setPluginsEnabled(true);
    setBackgroundColor(0);
    setWebViewClient(new AdWebViewClient(null));
    addMoPubUriJavascriptInterface();
  }
  
  private void adDidClose()
  {
    this.mMoPubView.adClosed();
  }
  
  private void adDidFail()
  {
    Log.i("MoPub", "Ad failed to load.");
    this.mIsLoading = false;
    scheduleRefreshTimerIfEnabled();
    this.mMoPubView.adFailed();
  }
  
  private void adDidLoad()
  {
    Log.i("MoPub", "Ad successfully loaded.");
    this.mIsLoading = false;
    scheduleRefreshTimerIfEnabled();
    setAdContentView(this, getHtmlAdLayoutParams());
    this.mMoPubView.adLoaded();
  }
  
  private String addKeyword(String paramString1, String paramString2)
  {
    String str;
    if ((paramString2 == null) || (paramString2.length() == 0)) {
      str = paramString1;
    }
    do
    {
      do
      {
        return str;
        str = paramString2;
      } while (paramString1 == null);
      str = paramString2;
    } while (paramString1.length() == 0);
    return paramString1 + "," + paramString2;
  }
  
  private void addMoPubUriJavascriptInterface()
  {
    addJavascriptInterface(new Object()
    {
      public boolean fireFinishLoad()
      {
        AdView.this.postHandlerRunnable(new Runnable()
        {
          public void run()
          {
            AdView.this.adDidLoad();
          }
        });
        return true;
      }
    }, "mopubUriInterface");
  }
  
  private void disableScrollingAndZoom()
  {
    setHorizontalScrollBarEnabled(false);
    setHorizontalScrollbarOverlay(false);
    setVerticalScrollBarEnabled(false);
    setVerticalScrollbarOverlay(false);
    getSettings().setSupportZoom(false);
  }
  
  private FrameLayout.LayoutParams getHtmlAdLayoutParams()
  {
    if ((this.mWidth > 0) && (this.mHeight > 0))
    {
      DisplayMetrics localDisplayMetrics = getContext().getResources().getDisplayMetrics();
      return new FrameLayout.LayoutParams((int)TypedValue.applyDimension(1, this.mWidth, localDisplayMetrics), (int)TypedValue.applyDimension(1, this.mHeight, localDisplayMetrics), 17);
    }
    return WRAP_AND_CENTER_LAYOUT_PARAMS;
  }
  
  private Location getLastKnownLocation()
  {
    MoPubView.LocationAwareness localLocationAwareness = this.mMoPubView.getLocationAwareness();
    int i = this.mMoPubView.getLocationPrecision();
    if (localLocationAwareness == MoPubView.LocationAwareness.LOCATION_AWARENESS_DISABLED) {
      return null;
    }
    Object localObject3 = (LocationManager)getContext().getSystemService("location");
    Object localObject1 = null;
    try
    {
      localObject2 = ((LocationManager)localObject3).getLastKnownLocation("gps");
      localObject1 = localObject2;
    }
    catch (SecurityException localSecurityException1)
    {
      for (;;)
      {
        Object localObject2;
        Log.d("MoPub", "Failed to retrieve GPS location: access appears to be disabled.");
      }
    }
    catch (IllegalArgumentException localIllegalArgumentException1)
    {
      for (;;)
      {
        Log.d("MoPub", "Failed to retrieve GPS location: device has no GPS provider.");
      }
    }
    localObject2 = null;
    try
    {
      localObject3 = ((LocationManager)localObject3).getLastKnownLocation("network");
      localObject2 = localObject3;
    }
    catch (SecurityException localSecurityException2)
    {
      for (;;)
      {
        Log.d("MoPub", "Failed to retrieve network location: access appears to be disabled.");
      }
    }
    catch (IllegalArgumentException localIllegalArgumentException2)
    {
      for (;;)
      {
        Log.d("MoPub", "Failed to retrieve network location: device has no network provider.");
      }
      if (localObject1 == null) {
        break label207;
      }
    }
    if ((localObject1 == null) && (localObject2 == null)) {
      return null;
    }
    if (localIllegalArgumentException1 != null) {
      if (((Location)localObject1).getTime() <= localIllegalArgumentException1.getTime()) {}
    }
    for (;;)
    {
      if (localLocationAwareness == MoPubView.LocationAwareness.LOCATION_AWARENESS_TRUNCATED)
      {
        ((Location)localObject1).setLatitude(BigDecimal.valueOf(((Location)localObject1).getLatitude()).setScale(i, 5).doubleValue());
        ((Location)localObject1).setLongitude(BigDecimal.valueOf(((Location)localObject1).getLongitude()).setScale(i, 5).doubleValue());
      }
      return (Location)localObject1;
      localObject1 = localIllegalArgumentException1;
      continue;
      label207:
      if (localObject1 == null) {
        localObject1 = localIllegalArgumentException1;
      }
    }
  }
  
  private String getTimeZoneOffsetString()
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("Z");
    localSimpleDateFormat.setTimeZone(TimeZone.getDefault());
    return localSimpleDateFormat.format(new Date());
  }
  
  private void handleCustomIntentFromUri(Uri paramUri)
  {
    registerClick();
    String str = paramUri.getQueryParameter("fnc");
    paramUri = paramUri.getQueryParameter("data");
    Intent localIntent = new Intent(str);
    localIntent.addFlags(268435456);
    if (paramUri != null) {
      localIntent.putExtra("com.mopub.intent.extra.AD_CLICK_DATA", paramUri);
    }
    try
    {
      getContext().startActivity(localIntent);
      return;
    }
    catch (ActivityNotFoundException paramUri)
    {
      Log.w("MoPub", "Could not handle custom intent: " + str + ". Is your intent spelled correctly?");
    }
  }
  
  private boolean isNetworkAvailable()
  {
    if (getContext().checkCallingPermission("android.permission.ACCESS_NETWORK_STATE") == -1) {}
    NetworkInfo localNetworkInfo;
    do
    {
      return true;
      localNetworkInfo = ((ConnectivityManager)getContext().getSystemService("connectivity")).getActiveNetworkInfo();
    } while ((localNetworkInfo != null) && (localNetworkInfo.isConnected()));
    return false;
  }
  
  private void postHandlerRunnable(Runnable paramRunnable)
  {
    this.mHandler.post(paramRunnable);
  }
  
  private void setAdContentView(View paramView, FrameLayout.LayoutParams paramLayoutParams)
  {
    this.mMoPubView.removeAllViews();
    this.mMoPubView.addView(paramView, paramLayoutParams);
  }
  
  private void setWebViewScrollingEnabled(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      setOnTouchListener(null);
      return;
    }
    setOnTouchListener(new View.OnTouchListener()
    {
      public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
      {
        return paramAnonymousMotionEvent.getAction() == 2;
      }
    });
  }
  
  private void showBrowserForUrl(String paramString)
  {
    if (isDestroyed()) {
      return;
    }
    String str;
    if (paramString != null)
    {
      str = paramString;
      if (!paramString.equals("")) {}
    }
    else
    {
      str = "about:blank";
    }
    Log.d("MoPub", "Final URI to show in browser: " + str);
    paramString = getContext();
    if (str.endsWith(".mp4"))
    {
      localIntent = new Intent("android.intent.action.VIEW");
      localIntent.setDataAndType(Uri.parse(str), "video/mp4");
      paramString.startActivity(localIntent);
      return;
    }
    Intent localIntent = new Intent(paramString, InAppWebView.class);
    localIntent.putExtra("REDIRECT_URI", str);
    localIntent.addFlags(268435456);
    try
    {
      paramString.startActivity(localIntent);
      return;
    }
    catch (ActivityNotFoundException paramString)
    {
      paramString = localIntent.getAction();
      Log.w("MoPub", "Could not handle intent action: " + paramString + ". Perhaps you forgot to declare com.adsdk.sdk.mraid.MraidBrowser" + " in your Android manifest file.");
      getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse("about:blank")).addFlags(268435456));
    }
  }
  
  protected void adAppeared()
  {
    loadUrl("javascript:webviewDidAppear();");
  }
  
  protected void cancelRefreshTimer()
  {
    this.mRefreshHandler.removeCallbacks(this.mRefreshRunnable);
  }
  
  protected void cleanup()
  {
    if (this.mIsDestroyed) {
      return;
    }
    setAutorefreshEnabled(false);
    cancelRefreshTimer();
    destroy();
    this.mResponseString = null;
    this.mMoPubView.removeView(this);
    this.mMoPubView = null;
    this.mIsDestroyed = true;
  }
  
  protected void configureAdViewUsingHeadersFromHttpResponse(HttpResponse paramHttpResponse)
  {
    Header localHeader1 = paramHttpResponse.getFirstHeader("X-Networktype");
    if (localHeader1 != null) {
      Log.i("MoPub", "Fetching ad network type: " + localHeader1.getValue());
    }
    localHeader1 = paramHttpResponse.getFirstHeader("X-Launchpage");
    if (localHeader1 != null)
    {
      this.mRedirectUrl = localHeader1.getValue();
      localHeader1 = paramHttpResponse.getFirstHeader("X-Clickthrough");
      if (localHeader1 == null) {
        break label321;
      }
      this.mClickthroughUrl = localHeader1.getValue();
      label90:
      localHeader1 = paramHttpResponse.getFirstHeader("X-Failurl");
      if (localHeader1 == null) {
        break label329;
      }
      this.mFailUrl = localHeader1.getValue();
      label114:
      localHeader1 = paramHttpResponse.getFirstHeader("X-Imptracker");
      if (localHeader1 == null) {
        break label337;
      }
      this.mImpressionUrl = localHeader1.getValue();
      label138:
      localHeader1 = paramHttpResponse.getFirstHeader("X-Scrollable");
      boolean bool = false;
      if (localHeader1 != null) {
        bool = localHeader1.getValue().equals("1");
      }
      setWebViewScrollingEnabled(bool);
      localHeader1 = paramHttpResponse.getFirstHeader("X-Width");
      Header localHeader2 = paramHttpResponse.getFirstHeader("X-Height");
      if ((localHeader1 == null) || (localHeader2 == null)) {
        break label345;
      }
      this.mWidth = Integer.parseInt(localHeader1.getValue().trim());
      this.mHeight = Integer.parseInt(localHeader2.getValue().trim());
      label235:
      localHeader1 = paramHttpResponse.getFirstHeader("X-Refreshtime");
      if (localHeader1 == null) {
        break label358;
      }
      this.mRefreshTimeMilliseconds = (Integer.valueOf(localHeader1.getValue()).intValue() * 1000);
      if (this.mRefreshTimeMilliseconds < 10000) {
        this.mRefreshTimeMilliseconds = 10000;
      }
      label286:
      paramHttpResponse = paramHttpResponse.getFirstHeader("X-Orientation");
      if (paramHttpResponse == null) {
        break label366;
      }
    }
    label321:
    label329:
    label337:
    label345:
    label358:
    label366:
    for (paramHttpResponse = paramHttpResponse.getValue();; paramHttpResponse = null)
    {
      this.mAdOrientation = paramHttpResponse;
      return;
      this.mRedirectUrl = null;
      break;
      this.mClickthroughUrl = null;
      break label90;
      this.mFailUrl = null;
      break label114;
      this.mImpressionUrl = null;
      break label138;
      this.mWidth = 0;
      this.mHeight = 0;
      break label235;
      this.mRefreshTimeMilliseconds = 0;
      break label286;
    }
  }
  
  public void customEventActionWillBegin()
  {
    registerClick();
  }
  
  public void customEventDidFailToLoadAd()
  {
    loadFailUrl();
  }
  
  public void customEventDidLoadAd()
  {
    this.mIsLoading = false;
    scheduleRefreshTimerIfEnabled();
  }
  
  public void forceRefresh()
  {
    this.mIsLoading = false;
    loadAd();
  }
  
  public int getAdHeight()
  {
    return this.mHeight;
  }
  
  public String getAdOrientation()
  {
    return this.mAdOrientation;
  }
  
  public String getAdUnitId()
  {
    return this.mAdUnitId;
  }
  
  public int getAdWidth()
  {
    return this.mWidth;
  }
  
  public boolean getAutorefreshEnabled()
  {
    return this.mAutorefreshEnabled;
  }
  
  public String getClickthroughUrl()
  {
    return this.mClickthroughUrl;
  }
  
  public String getKeywords()
  {
    return this.mKeywords;
  }
  
  public Location getLocation()
  {
    return this.mLocation;
  }
  
  public String getRedirectUrl()
  {
    return this.mRedirectUrl;
  }
  
  protected int getRefreshTimeMilliseconds()
  {
    return this.mRefreshTimeMilliseconds;
  }
  
  public String getResponseString()
  {
    return this.mResponseString;
  }
  
  protected boolean isDestroyed()
  {
    return this.mIsDestroyed;
  }
  
  public void loadAd()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("X-Adtype", "mraid");
    localHashMap.put("X-Nativeparams", this.mResponse.getText());
    this.mMoPubView.loadNativeSDK(localHashMap);
  }
  
  public void loadFailUrl()
  {
    this.mIsLoading = false;
    if (this.mFailUrl != null)
    {
      Log.d("MoPub", "Loading failover url: " + this.mFailUrl);
      loadUrl(this.mFailUrl);
      return;
    }
    adDidFail();
  }
  
  protected void registerClick()
  {
    if (this.mClickthroughUrl == null) {
      return;
    }
    new Thread(new Runnable()
    {
      public void run()
      {
        DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient();
        HttpGet localHttpGet = new HttpGet(AdView.this.mClickthroughUrl);
        localHttpGet.addHeader("User-Agent", AdView.this.mUserAgent);
        try
        {
          localDefaultHttpClient.execute(localHttpGet);
          return;
        }
        catch (ClientProtocolException localClientProtocolException)
        {
          Log.i("MoPub", "Click tracking failed: " + AdView.this.mClickthroughUrl);
          return;
        }
        catch (IOException localIOException)
        {
          Log.i("MoPub", "Click tracking failed: " + AdView.this.mClickthroughUrl);
          return;
        }
        finally
        {
          localDefaultHttpClient.getConnectionManager().shutdown();
        }
      }
    }).start();
  }
  
  public void reload()
  {
    Log.d("MoPub", "Reload ad: " + this.mUrl);
    loadUrl(this.mUrl);
  }
  
  protected void scheduleRefreshTimerIfEnabled()
  {
    cancelRefreshTimer();
    if ((!this.mAutorefreshEnabled) || (this.mRefreshTimeMilliseconds <= 0)) {
      return;
    }
    this.mRefreshHandler.postDelayed(this.mRefreshRunnable, this.mRefreshTimeMilliseconds);
  }
  
  public void setAdContentView(View paramView)
  {
    setAdContentView(paramView, WRAP_AND_CENTER_LAYOUT_PARAMS);
  }
  
  public void setAdUnitId(String paramString)
  {
    this.mAdUnitId = paramString;
  }
  
  public void setAutorefreshEnabled(boolean paramBoolean)
  {
    this.mAutorefreshEnabled = paramBoolean;
    Log.d("MoPub", "Automatic refresh for " + this.mAdUnitId + " set to: " + paramBoolean + ".");
    if (!this.mAutorefreshEnabled)
    {
      cancelRefreshTimer();
      return;
    }
    scheduleRefreshTimerIfEnabled();
  }
  
  public void setClickthroughUrl(String paramString)
  {
    this.mClickthroughUrl = paramString;
  }
  
  protected void setIsLoading(boolean paramBoolean)
  {
    this.mIsLoading = paramBoolean;
  }
  
  public void setKeywords(String paramString)
  {
    this.mKeywords = paramString;
  }
  
  public void setLocation(Location paramLocation)
  {
    this.mLocation = paramLocation;
  }
  
  protected void setRefreshTimeMilliseconds(int paramInt)
  {
    this.mRefreshTimeMilliseconds = paramInt;
  }
  
  protected void setResponseString(String paramString)
  {
    this.mResponseString = paramString;
  }
  
  private class AdWebViewClient
    extends WebViewClient
  {
    private AdWebViewClient() {}
    
    private String urlWithClickTrackingRedirect(AdView paramAdView, String paramString)
    {
      paramAdView = paramAdView.getClickthroughUrl();
      if (paramAdView == null) {
        return paramString;
      }
      paramString = Uri.encode(paramString);
      return paramAdView + "&r=" + paramString;
    }
    
    public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
    {
      paramBitmap = (AdView)paramWebView;
      String str = paramBitmap.getRedirectUrl();
      if ((str != null) && (paramString.startsWith(str)))
      {
        paramString = urlWithClickTrackingRedirect(paramBitmap, paramString);
        paramWebView.stopLoading();
        AdView.this.showBrowserForUrl(paramString);
      }
    }
    
    public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
    {
      int i = 0;
      paramWebView = (AdView)paramWebView;
      Object localObject;
      if (paramString.startsWith("mopub://"))
      {
        paramString = Uri.parse(paramString);
        localObject = paramString.getHost();
        if (((String)localObject).equals("finishLoad")) {
          paramWebView.adDidLoad();
        }
        do
        {
          return true;
          if (((String)localObject).equals("close"))
          {
            paramWebView.adDidClose();
            return true;
          }
          if (((String)localObject).equals("failLoad"))
          {
            paramWebView.loadFailUrl();
            return true;
          }
        } while (!((String)localObject).equals("custom"));
        paramWebView.handleCustomIntentFromUri(paramString);
        return true;
      }
      if ((paramString.startsWith("tel:")) || (paramString.startsWith("voicemail:")) || (paramString.startsWith("sms:")) || (paramString.startsWith("mailto:")) || (paramString.startsWith("geo:")) || (paramString.startsWith("google.streetview:")))
      {
        paramWebView = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
        paramWebView.addFlags(268435456);
        try
        {
          AdView.this.getContext().startActivity(paramWebView);
          AdView.this.registerClick();
          return true;
        }
        catch (ActivityNotFoundException paramWebView)
        {
          Log.w("MoPub", "Could not handle intent with URI: " + paramString + ". Is this intent unsupported on your phone?");
          return true;
        }
      }
      if (paramString.startsWith("market://"))
      {
        localObject = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
        if (AdView.this.getContext().getPackageManager().queryIntentActivities((Intent)localObject, 0).size() > 0) {
          i = 1;
        }
        if (i == 0)
        {
          Log.w("MoPub", "Could not handle market action: " + paramString + ". Perhaps you're running in the emulator, which does not have " + "the Android Market?");
          return true;
        }
      }
      paramWebView = urlWithClickTrackingRedirect(paramWebView, paramString);
      Log.d("MoPub", "Ad clicked. Click URL: " + paramWebView);
      AdView.this.mMoPubView.adClicked();
      AdView.this.showBrowserForUrl(paramWebView);
      return true;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\adsdk\sdk\mraid\AdView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */