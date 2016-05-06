package com.appyjump.video.sdk;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.ViewFlipper;
import com.appyjump.video.sdk.data.ClickType;
import com.appyjump.video.sdk.data.Request;
import com.appyjump.video.sdk.data.Response;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.MessageFormat;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.UUID;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

public class AdserveView
  extends RelativeLayout
{
  private boolean animation;
  private BannerListener bannerListener;
  int count = 0;
  private Animation fadeInAnimation = null;
  private Animation fadeOutAnimation = null;
  private WebView firstWebView;
  private boolean includeLocation = false;
  private int isAccessCoarseLocation;
  private int isAccessFineLocation;
  boolean isInternalBrowser = false;
  private Thread loadContentThread;
  private LocationManager locationManager;
  private Mode mode;
  private View.OnTouchListener onTouchListener = new View.OnTouchListener()
  {
    private float distanceX;
    private float distanceY;
    
    public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
    {
      try
      {
        if (paramAnonymousMotionEvent.getAction() == 0)
        {
          AdserveView.access$102(AdserveView.this, false);
          this.distanceX = paramAnonymousMotionEvent.getX();
          this.distanceY = paramAnonymousMotionEvent.getY();
        }
        if (paramAnonymousMotionEvent.getAction() == 2)
        {
          if (Math.abs(this.distanceX - paramAnonymousMotionEvent.getX()) > 30.0F) {
            AdserveView.access$102(AdserveView.this, true);
          }
          if (Math.abs(this.distanceY - paramAnonymousMotionEvent.getY()) > 30.0F) {
            AdserveView.access$102(AdserveView.this, true);
          }
          if (!Log.isLoggable("mAdserve", 3)) {
            break label275;
          }
          Log.d("mAdserve", "touchMove: " + AdserveView.this.touchMove);
          return true;
        }
        if (paramAnonymousMotionEvent.getAction() == 1)
        {
          if (Log.isLoggable("mAdserve", 3))
          {
            Log.d("mAdserve", "size x: " + paramAnonymousMotionEvent.getX());
            Log.d("mAdserve", "getHistorySize: " + paramAnonymousMotionEvent.getHistorySize());
          }
          if ((AdserveView.this.response != null) && (!AdserveView.this.touchMove))
          {
            AdserveView.this.openLink();
            if (AdserveView.this.bannerListener != null) {
              AdserveView.this.bannerListener.adClicked();
            }
          }
        }
      }
      catch (Throwable paramAnonymousView)
      {
        for (;;)
        {
          paramAnonymousView.printStackTrace();
        }
      }
      return AdserveView.this.onTouchEvent(paramAnonymousMotionEvent);
      label275:
      return true;
    }
  };
  private String publisherId;
  private Timer reloadTimer;
  private Request request;
  private Response response;
  private WebView secondWebView;
  private String serverUrl;
  final Runnable showContent = new Runnable()
  {
    public void run()
    {
      AdserveView.this.showContent();
    }
  };
  private int telephonyPermission;
  private boolean touchMove;
  final Handler updateHandler = new Handler();
  private ViewFlipper viewFlipper;
  private WebSettings webSettings;
  
  public AdserveView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    if (paramAttributeSet != null)
    {
      int j = paramAttributeSet.getAttributeCount();
      int i = 0;
      if (i < j)
      {
        String str1 = paramAttributeSet.getAttributeName(i);
        int k;
        if (str1.equals("publisherId"))
        {
          k = paramAttributeSet.getAttributeResourceValue(i, 0);
          if (k == 0) {}
        }
        for (;;)
        {
          try
          {
            this.publisherId = paramContext.getString(k);
            if (Log.isLoggable("mAdserve", 3)) {
              Log.d("mAdserve", "mAdserve Publisher Id:" + this.publisherId);
            }
            i += 1;
          }
          catch (Exception localException1)
          {
            this.publisherId = paramAttributeSet.getAttributeValue(i);
            continue;
          }
          this.publisherId = paramAttributeSet.getAttributeValue(i);
          continue;
          if (localException1.equals("serverUrl"))
          {
            k = paramAttributeSet.getAttributeResourceValue(i, 0);
            if (k != 0) {}
            for (;;)
            {
              try
              {
                this.serverUrl = paramContext.getString(k);
                if (!Log.isLoggable("mAdserve", 3)) {
                  break;
                }
                Log.d("mAdserve", "mAdserve url:" + this.serverUrl);
              }
              catch (Exception localException2)
              {
                this.serverUrl = paramAttributeSet.getAttributeValue(i);
                continue;
              }
              this.serverUrl = paramAttributeSet.getAttributeValue(i);
            }
          }
          String str2;
          if (localException2.equals("mode"))
          {
            str2 = paramAttributeSet.getAttributeValue(i);
            if ((str2 != null) && (str2.equalsIgnoreCase("test"))) {
              this.mode = Mode.TEST;
            }
            if (Log.isLoggable("mAdserve", 3)) {
              Log.d("mAdserve", "mAdserve Mode:" + str2);
            }
          }
          else if (str2.equals("animation"))
          {
            this.animation = paramAttributeSet.getAttributeBooleanValue(i, false);
            if (Log.isLoggable("mAdserve", 3)) {
              Log.d("mAdserve", "mAdserve Animation:" + this.animation);
            }
          }
          else if (str2.equals("includeLocation"))
          {
            this.includeLocation = paramAttributeSet.getAttributeBooleanValue(i, false);
            if (Log.isLoggable("mAdserve", 3)) {
              Log.d("mAdserve", "mAdserve includeLocation:" + this.includeLocation);
            }
          }
        }
      }
    }
    initialize(paramContext);
  }
  
  public AdserveView(Context paramContext, String paramString1, String paramString2, Mode paramMode, boolean paramBoolean1, boolean paramBoolean2)
  {
    super(paramContext);
    this.publisherId = paramString1;
    this.serverUrl = paramString2;
    this.includeLocation = paramBoolean1;
    this.mode = paramMode;
    this.animation = paramBoolean2;
    initialize(paramContext);
  }
  
  public AdserveView(Context paramContext, String paramString1, String paramString2, boolean paramBoolean1, boolean paramBoolean2)
  {
    this(paramContext, paramString1, paramString2, Mode.LIVE, paramBoolean1, paramBoolean2);
  }
  
  private WebView createWebView(Context paramContext)
  {
    WebView localWebView = new WebView(getContext());
    this.webSettings = localWebView.getSettings();
    this.webSettings.setJavaScriptEnabled(true);
    localWebView.setBackgroundColor(0);
    localWebView.setWebViewClient(new AdserveWebViewClient(this, paramContext));
    localWebView.setVerticalScrollBarEnabled(false);
    localWebView.setHorizontalScrollBarEnabled(false);
    return localWebView;
  }
  
  private void doOpenUrl(String paramString)
  {
    if ((this.response.getClickType() != null) && (this.response.getClickType().equals(ClickType.INAPP)))
    {
      paramString = new Intent(getContext(), InAppWebView.class);
      paramString.putExtra("REDIRECT_URI", this.response.getClickUrl());
      getContext().startActivity(paramString);
      return;
    }
    paramString = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
    getContext().startActivity(paramString);
  }
  
  private String getDeviceId()
  {
    Object localObject1 = Settings.Secure.getString(getContext().getContentResolver(), "android_id");
    Object localObject2;
    SharedPreferences localSharedPreferences;
    if ((localObject1 != null) && (!((String)localObject1).equals("9774d56d682e549c")))
    {
      localObject2 = localObject1;
      if (!((String)localObject1).equals("0000000000000000")) {}
    }
    else
    {
      localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
      localObject2 = localSharedPreferences.getString("madserve_device_id", null);
      localObject1 = localObject2;
      if (localObject2 != null) {}
    }
    try
    {
      localObject1 = UUID.randomUUID().toString();
      localObject2 = MessageDigest.getInstance("MD5");
      ((MessageDigest)localObject2).update(((String)localObject1).getBytes(), 0, ((String)localObject1).length());
      localObject1 = String.format("%032X", new Object[] { new BigInteger(1, ((MessageDigest)localObject2).digest()) }).substring(0, 16);
      localSharedPreferences.edit().putString("madserve_device_id", (String)localObject1).commit();
      localObject2 = localObject1;
      if (Log.isLoggable("mAdserve", 3))
      {
        Log.d("mAdserve", "Unknown Android ID using pseudo unique id:" + (String)localObject1);
        localObject2 = localObject1;
      }
      return (String)localObject2;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Log.d("mAdserve", "Could not generate pseudo unique id", localException);
        String str = "9774d56d682e549c";
      }
    }
  }
  
  private Location getLocation()
  {
    Object localObject2 = null;
    Object localObject3 = null;
    if (this.locationManager != null)
    {
      Object localObject1 = localObject3;
      if (this.isAccessFineLocation == 0)
      {
        localObject1 = localObject3;
        if (this.locationManager.isProviderEnabled("gps")) {
          localObject1 = this.locationManager.getLastKnownLocation("gps");
        }
      }
      localObject2 = localObject1;
      if (this.isAccessCoarseLocation == 0)
      {
        localObject2 = localObject1;
        if (this.locationManager.isProviderEnabled("network"))
        {
          localObject2 = localObject1;
          if (localObject1 == null) {
            localObject2 = this.locationManager.getLastKnownLocation("network");
          }
        }
      }
    }
    return (Location)localObject2;
  }
  
  private Request getRequest()
  {
    Object localObject;
    if (this.request == null)
    {
      this.request = new Request();
      if (this.telephonyPermission == 0)
      {
        localObject = (TelephonyManager)getContext().getSystemService("phone");
        this.request.setDeviceId(((TelephonyManager)localObject).getDeviceId());
        this.request.setProtocolVersion("3");
        this.request.setMode(this.mode);
        this.request.setPublisherId(this.publisherId);
        this.request.setServerUrl(this.serverUrl);
        this.request.setUserAgent(this.webSettings.getUserAgentString());
      }
    }
    else
    {
      localObject = null;
      if (this.includeLocation) {
        localObject = getLocation();
      }
      if (localObject == null) {
        break label342;
      }
      if (Log.isLoggable("mAdserve", 3)) {
        Log.d("mAdserve", "location is longitude: " + ((Location)localObject).getLongitude() + ", latitude: " + ((Location)localObject).getLatitude());
      }
      this.request.setLatitude(((Location)localObject).getLatitude());
      this.request.setLongitude(((Location)localObject).getLongitude());
      label198:
      localObject = new Geocoder(getContext(), Locale.getDefault());
      if (!Geocoder.isPresent()) {}
    }
    for (;;)
    {
      try
      {
        localObject = ((Geocoder)localObject).getFromLocation(this.request.getLatitude(), this.request.getLongitude(), 1);
        if ((localObject != null) && (((List)localObject).size() > 0))
        {
          Address localAddress = (Address)((List)localObject).get(0);
          if (localAddress.getMaxAddressLineIndex() <= 0) {
            continue;
          }
          localObject = localAddress.getAddressLine(0);
          localObject = String.format("%s, %s, %s", new Object[] { localObject, localAddress.getLocality(), localAddress.getCountryName() });
          this.request.setLocation((String)localObject);
        }
      }
      catch (IOException localIOException)
      {
        label342:
        localIOException.printStackTrace();
        continue;
      }
      return this.request;
      this.request.setDeviceId(getDeviceId());
      this.request.setProtocolVersion("N3");
      break;
      this.request.setLatitude(0.0D);
      this.request.setLongitude(0.0D);
      break label198;
      localObject = "";
    }
  }
  
  private void initialize(Context paramContext)
  {
    if (Log.isLoggable("mAdserve", 3)) {
      Log.d("mAdserve", "mAdserve SDK Version:1.2");
    }
    this.locationManager = null;
    this.telephonyPermission = paramContext.checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE");
    this.isAccessFineLocation = paramContext.checkCallingOrSelfPermission("android.permission.ACCESS_FINE_LOCATION");
    this.isAccessCoarseLocation = paramContext.checkCallingOrSelfPermission("android.permission.ACCESS_COARSE_LOCATION");
    if ((this.isAccessFineLocation == 0) || (this.isAccessCoarseLocation == 0)) {
      this.locationManager = ((LocationManager)getContext().getSystemService("location"));
    }
    this.firstWebView = createWebView(paramContext);
    this.secondWebView = createWebView(paramContext);
    if (Log.isLoggable("mAdserve", 3)) {
      Log.d("mAdserve", "Create view flipper");
    }
    this.viewFlipper = new ViewFlipper(getContext())
    {
      protected void onDetachedFromWindow()
      {
        try
        {
          super.onDetachedFromWindow();
          return;
        }
        catch (IllegalArgumentException localIllegalArgumentException)
        {
          stopFlipping();
        }
      }
    };
    this.viewFlipper.addView(this.firstWebView);
    this.viewFlipper.addView(this.secondWebView);
    paramContext = new RelativeLayout.LayoutParams(-1, -1);
    addView(this.viewFlipper, paramContext);
    this.firstWebView.setOnTouchListener(this.onTouchListener);
    this.secondWebView.setOnTouchListener(this.onTouchListener);
    if (Log.isLoggable("mAdserve", 3)) {
      Log.d("mAdserve", "animation: " + this.animation);
    }
    if (this.animation)
    {
      this.fadeInAnimation = new TranslateAnimation(2, 0.0F, 2, 0.0F, 2, 1.0F, 2, 0.0F);
      this.fadeInAnimation.setDuration(1000L);
      this.fadeOutAnimation = new TranslateAnimation(2, 0.0F, 2, 0.0F, 2, 0.0F, 2, -1.0F);
      this.fadeOutAnimation.setDuration(1000L);
      this.viewFlipper.setInAnimation(this.fadeInAnimation);
      this.viewFlipper.setOutAnimation(this.fadeOutAnimation);
    }
  }
  
  private void loadContent()
  {
    if (Log.isLoggable("mAdserve", 3)) {
      Log.d("mAdserve", "load content");
    }
    if (this.loadContentThread == null)
    {
      this.loadContentThread = new Thread(new Runnable()
      {
        public void run()
        {
          if (Log.isLoggable("mAdserve", 3)) {
            Log.d("mAdserve", "starting request thread");
          }
          Object localObject = new RequestAd();
          try
          {
            AdserveView.access$202(AdserveView.this, ((RequestAd)localObject).sendRequest(AdserveView.this.getRequest()));
            if (AdserveView.this.response != null)
            {
              if (Log.isLoggable("mAdserve", 3)) {
                Log.d("mAdserve", "response received");
              }
              if (Log.isLoggable("mAdserve", 3)) {
                Log.d("mAdserve", "getVisibility: " + AdserveView.this.getVisibility());
              }
              AdserveView.this.updateHandler.post(AdserveView.this.showContent);
            }
            AdserveView.access$502(AdserveView.this, null);
            if (Log.isLoggable("mAdserve", 3)) {
              Log.d("mAdserve", "finishing request thread");
            }
            return;
          }
          catch (Throwable localThrowable)
          {
            if (Log.isLoggable("mAdserve", 6)) {
              Log.e("mAdserve", "Uncaught exception in request thread", localThrowable);
            }
            if (AdserveView.this.bannerListener == null) {
              break label248;
            }
          }
          Log.d("mAdserve", "notify bannerListener: " + AdserveView.this.bannerListener.getClass().getName());
          if ((localThrowable instanceof RequestException)) {}
          for (localObject = (RequestException)localThrowable;; localObject = new RequestException(localThrowable))
          {
            AdserveView.this.bannerListener.bannerLoadFailed((RequestException)localObject);
            label248:
            Log.e("mAdserve", localThrowable.getMessage(), localThrowable);
            break;
          }
        }
      });
      this.loadContentThread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler()
      {
        public void uncaughtException(Thread paramAnonymousThread, Throwable paramAnonymousThrowable)
        {
          if (Log.isLoggable("mAdserve", 6)) {
            Log.e("mAdserve", "Exception in request thread detected. Closing request thread.", paramAnonymousThrowable);
          }
          AdserveView.access$502(AdserveView.this, null);
        }
      });
      this.loadContentThread.start();
    }
  }
  
  private void showContent()
  {
    for (;;)
    {
      try
      {
        if (this.viewFlipper.getCurrentView() != this.firstWebView) {
          continue;
        }
        localWebView = this.secondWebView;
        if (this.response.getType() != AdType.VIDEO) {
          continue;
        }
        localWebView.loadData(Uri.encode("<body> </body>"), "text/html", "UTF-8");
        if (this.bannerListener != null)
        {
          if (Log.isLoggable("mAdserve", 3)) {
            Log.d("mAdserve", "notify bannerListener of load succeeded: " + this.bannerListener.getClass().getName());
          }
          this.bannerListener.bannerLoadSucceeded();
        }
      }
      catch (Throwable localThrowable)
      {
        WebView localWebView;
        if (!Log.isLoggable("mAdserve", 6)) {
          return;
        }
        Log.e("mAdserve", "Uncaught exception in show content", localThrowable);
        return;
        if (this.response.getType() != AdType.TEXT) {
          continue;
        }
        String str = Uri.encode("<style>* { -webkit-tap-highlight-color: rgba(0,0,0,0) }</style>" + this.response.getText());
        if (!Log.isLoggable("mAdserve", 3)) {
          continue;
        }
        Log.d("mAdserve", "set text: " + str);
        localThrowable.loadData(str, "text/html", "UTF-8");
        if (this.bannerListener == null) {
          continue;
        }
        if (!Log.isLoggable("mAdserve", 3)) {
          continue;
        }
        Log.d("mAdserve", "notify bannerListener of load succeeded: " + this.bannerListener.getClass().getName());
        this.bannerListener.bannerLoadSucceeded();
        continue;
        if (!Log.isLoggable("mAdserve", 3)) {
          continue;
        }
        Log.d("mAdserve", "No Ad");
        if (this.bannerListener == null) {
          return;
        }
        this.bannerListener.noAdFound();
        return;
        if (!Log.isLoggable("mAdserve", 3)) {
          continue;
        }
        Log.d("mAdserve", "show previous");
        this.viewFlipper.showPrevious();
        continue;
      }
      if (this.viewFlipper.getCurrentView() != this.firstWebView) {
        continue;
      }
      if (Log.isLoggable("mAdserve", 3)) {
        Log.d("mAdserve", "show next");
      }
      this.viewFlipper.showNext();
      startReloadTimer();
      return;
      localWebView = this.firstWebView;
      continue;
      if (this.response.getType() != AdType.IMAGE) {
        continue;
      }
      str = MessageFormat.format("<body style='\"'margin: 0px; padding: 0px; text-align:center;'\"'><img src='\"'{0}'\"' width='\"'{1}'dp\"' height='\"'{2}'dp\"'/></body>", new Object[] { this.response.getImageUrl(), Integer.valueOf(this.response.getBannerWidth()), Integer.valueOf(this.response.getBannerHeight()) });
      if (Log.isLoggable("mAdserve", 3)) {
        Log.d("mAdserve", "set image: " + str);
      }
      localWebView.loadData(Uri.encode("<style>* { -webkit-tap-highlight-color: rgba(0,0,0,0) }</style>" + str), "text/html", "UTF-8");
      if (this.bannerListener != null)
      {
        if (Log.isLoggable("mAdserve", 3)) {
          Log.d("mAdserve", "notify bannerListener of load succeeded: " + this.bannerListener.getClass().getName());
        }
        this.bannerListener.bannerLoadSucceeded();
      }
    }
  }
  
  private void startReloadTimer()
  {
    if (Log.isLoggable("mAdserve", 3)) {
      Log.d("mAdserve", "start reload timer");
    }
    if (this.reloadTimer == null) {
      return;
    }
    int i = this.response.getRefresh() * 1000;
    if (Log.isLoggable("mAdserve", 3)) {
      Log.d("mAdserve", "set timer: " + i);
    }
    ReloadTask localReloadTask = new ReloadTask(this);
    this.reloadTimer.schedule(localReloadTask, i);
  }
  
  public boolean isInternalBrowser()
  {
    return this.isInternalBrowser;
  }
  
  public void loadNextAd()
  {
    if (Log.isLoggable("mAdserve", 3)) {
      Log.d("mAdserve", "load next ad");
    }
    loadContent();
  }
  
  protected void onWindowVisibilityChanged(int paramInt)
  {
    super.onWindowVisibilityChanged(paramInt);
    if (paramInt == 0) {
      resume();
    }
    for (;;)
    {
      if (Log.isLoggable("mAdserve", 3)) {
        Log.d("mAdserve", "onWindowVisibilityChanged: " + paramInt);
      }
      return;
      pause();
    }
  }
  
  public void openLink()
  {
    if ((this.response != null) && (this.response.getClickUrl() != null))
    {
      if (this.response.isSkipPreflight()) {
        doOpenUrl(this.response.getClickUrl());
      }
    }
    else {
      return;
    }
    if (Log.isLoggable("mAdserve", 3)) {
      Log.d("mAdserve", "prefetch url: " + this.response.getClickUrl());
    }
    new RetrieveFeedTask().execute(new String[] { this.response.getClickUrl() });
  }
  
  public void pause()
  {
    if (this.reloadTimer != null) {}
    try
    {
      if (Log.isLoggable("mAdserve", 3)) {
        Log.d("mAdserve", "cancel reload timer");
      }
      this.reloadTimer.cancel();
      this.reloadTimer = null;
      return;
    }
    catch (Exception localException)
    {
      Log.e("mAdserve", "unable to cancel reloadTimer", localException);
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
    if (Log.isLoggable("mAdserve", 3)) {
      Log.d("mAdserve", "response: " + this.response);
    }
    if ((this.response != null) && (this.response.getRefresh() > 0))
    {
      startReloadTimer();
      return;
    }
    loadContent();
  }
  
  public void setBannerListener(BannerListener paramBannerListener)
  {
    this.bannerListener = paramBannerListener;
  }
  
  public void setInternalBrowser(boolean paramBoolean)
  {
    this.isInternalBrowser = paramBoolean;
  }
  
  class RetrieveFeedTask
    extends AsyncTask<String, Void, String>
  {
    String currentUrl;
    
    RetrieveFeedTask() {}
    
    protected String doInBackground(String... paramVarArgs)
    {
      Object localObject = new DefaultHttpClient();
      HttpConnectionParams.setSoTimeout(((DefaultHttpClient)localObject).getParams(), 15000);
      HttpConnectionParams.setConnectionTimeout(((DefaultHttpClient)localObject).getParams(), 15000);
      HttpGet localHttpGet = new HttpGet(AdserveView.this.response.getClickUrl());
      paramVarArgs = new BasicHttpContext();
      try
      {
        localObject = ((DefaultHttpClient)localObject).execute(localHttpGet, paramVarArgs);
        if (((HttpResponse)localObject).getStatusLine().getStatusCode() != 200) {
          throw new IOException(((HttpResponse)localObject).getStatusLine().toString());
        }
      }
      catch (ClientProtocolException paramVarArgs)
      {
        Log.e("mAdserve", "Error in HTTP request", paramVarArgs);
        for (;;)
        {
          return this.currentUrl;
          localObject = (HttpUriRequest)paramVarArgs.getAttribute("http.request");
          paramVarArgs = (HttpHost)paramVarArgs.getAttribute("http.target_host");
          this.currentUrl = (paramVarArgs.toURI() + ((HttpUriRequest)localObject).getURI());
        }
      }
      catch (IOException paramVarArgs)
      {
        for (;;)
        {
          Log.e("mAdserve", "Error in HTTP request", paramVarArgs);
        }
      }
      catch (Throwable paramVarArgs)
      {
        for (;;)
        {
          Log.e("mAdserve", "Error in HTTP request", paramVarArgs);
        }
      }
    }
    
    protected void onPostExecute(String paramString)
    {
      AdserveView.this.doOpenUrl(paramString);
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\appyjump\video\sdk\AdserveView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */