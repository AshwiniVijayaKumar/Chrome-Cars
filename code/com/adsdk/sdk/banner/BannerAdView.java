package com.adsdk.sdk.banner;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.ViewFlipper;
import com.adsdk.sdk.AdListener;
import com.adsdk.sdk.BannerAd;
import com.adsdk.sdk.Log;
import com.adsdk.sdk.data.ClickType;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.MessageFormat;

public class BannerAdView
  extends RelativeLayout
{
  public static final int LIVE = 0;
  public static final int TEST = 1;
  private static Field mWebView_LAYER_TYPE_SOFTWARE;
  private static Method mWebView_SetLayerType;
  private AdListener adListener;
  private boolean animation;
  private Animation fadeInAnimation = null;
  private Animation fadeOutAnimation = null;
  private WebView firstWebView;
  private boolean isInternalBrowser = false;
  private Context mContext = null;
  protected boolean mIsInForeground;
  private BannerAd response;
  private WebView secondWebView;
  private boolean touchMove;
  private final Handler updateHandler = new Handler();
  private ViewFlipper viewFlipper;
  private WebSettings webSettings;
  private InputStream xml;
  
  static {}
  
  public BannerAdView(Context paramContext, BannerAd paramBannerAd, AdListener paramAdListener)
  {
    this(paramContext, paramBannerAd, false, paramAdListener);
  }
  
  public BannerAdView(Context paramContext, BannerAd paramBannerAd, boolean paramBoolean, AdListener paramAdListener)
  {
    super(paramContext);
    this.response = paramBannerAd;
    this.mContext = paramContext;
    this.animation = paramBoolean;
    this.adListener = paramAdListener;
    initialize(paramContext);
  }
  
  public BannerAdView(Context paramContext, InputStream paramInputStream, boolean paramBoolean)
  {
    super(paramContext);
    this.xml = paramInputStream;
    this.mContext = paramContext;
    this.animation = paramBoolean;
    initialize(paramContext);
  }
  
  private void buildBannerView()
  {
    this.firstWebView = createWebView(this.mContext);
    this.secondWebView = createWebView(this.mContext);
    Log.d("ADSDK", "Create view flipper");
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
    float f = this.mContext.getResources().getDisplayMetrics().density;
    setLayoutParams(new RelativeLayout.LayoutParams((int)(300.0F * f + 0.5F), (int)(50.0F * f + 0.5F)));
    Object localObject = new FrameLayout.LayoutParams(-1, -1);
    this.viewFlipper.addView(this.firstWebView, (ViewGroup.LayoutParams)localObject);
    this.viewFlipper.addView(this.secondWebView, (ViewGroup.LayoutParams)localObject);
    localObject = new RelativeLayout.LayoutParams(-1, -1);
    addView(this.viewFlipper, (ViewGroup.LayoutParams)localObject);
    Log.d("ADSDK", "animation: " + this.animation);
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
  
  private WebView createWebView(Context paramContext)
  {
    paramContext = new WebView(getContext())
    {
      public void draw(Canvas paramAnonymousCanvas)
      {
        if ((getWidth() > 0) && (getHeight() > 0)) {
          super.draw(paramAnonymousCanvas);
        }
      }
    };
    this.webSettings = paramContext.getSettings();
    this.webSettings.setJavaScriptEnabled(true);
    paramContext.setBackgroundColor(0);
    setLayer(paramContext);
    paramContext.setWebViewClient(new WebViewClient()
    {
      public boolean shouldOverrideUrlLoading(WebView paramAnonymousWebView, String paramAnonymousString)
      {
        if (BannerAdView.this.response.getSkipOverlay() == 1)
        {
          BannerAdView.this.doOpenUrl(paramAnonymousString);
          Log.i("TouchListener", "false");
          return true;
        }
        Log.i("TouchListener", "default");
        BannerAdView.this.openLink();
        return true;
      }
    });
    paramContext.setVerticalScrollBarEnabled(false);
    paramContext.setHorizontalScrollBarEnabled(false);
    return paramContext;
  }
  
  private void doOpenUrl(String paramString)
  {
    notifyAdClicked();
    if ((this.response.getClickType() != null) && (this.response.getClickType().equals(ClickType.INAPP)) && ((paramString.startsWith("http://")) || (paramString.startsWith("https://"))))
    {
      if (paramString.endsWith(".mp4"))
      {
        localIntent = new Intent("android.intent.action.VIEW");
        localIntent.setDataAndType(Uri.parse(paramString), "video/mp4");
        getContext().startActivity(localIntent);
        return;
      }
      Intent localIntent = new Intent(getContext(), InAppWebView.class);
      localIntent.putExtra("REDIRECT_URI", paramString);
      getContext().startActivity(localIntent);
      return;
    }
    paramString = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
    getContext().startActivity(paramString);
  }
  
  private static void initCompatibility()
  {
    for (;;)
    {
      int j;
      int i;
      try
      {
        arrayOfMethod = WebView.class.getMethods();
        j = arrayOfMethod.length;
        i = 0;
      }
      catch (SecurityException localSecurityException)
      {
        Method[] arrayOfMethod;
        Method localMethod;
        Log.v("SecurityException");
        return;
        i += 1;
      }
      catch (NoSuchFieldException localNoSuchFieldException)
      {
        Log.v("NoSuchFieldException");
        return;
      }
      Log.v("set layer " + mWebView_SetLayerType);
      mWebView_LAYER_TYPE_SOFTWARE = WebView.class.getField("LAYER_TYPE_SOFTWARE");
      Log.v("set1 layer " + mWebView_LAYER_TYPE_SOFTWARE);
      return;
      localMethod = arrayOfMethod[i];
      if (localMethod.getName().equals("setLayerType")) {
        mWebView_SetLayerType = localMethod;
      } else if (i < j) {}
    }
  }
  
  private void initialize(Context paramContext)
  {
    initCompatibility();
    buildBannerView();
    showContent();
  }
  
  private void notifyAdClicked()
  {
    this.updateHandler.post(new Runnable()
    {
      public void run()
      {
        if (BannerAdView.this.adListener != null)
        {
          Log.d("ADSDK", "notify bannerListener of ad clicked: " + BannerAdView.this.adListener.getClass().getName());
          BannerAdView.this.adListener.adClicked();
        }
      }
    });
  }
  
  private void notifyLoadAdSucceeded()
  {
    this.updateHandler.post(new Runnable()
    {
      public void run()
      {
        if (BannerAdView.this.adListener != null)
        {
          Log.d("ADSDK", "notify bannerListener of load succeeded: " + BannerAdView.this.adListener.getClass().getName());
          BannerAdView.this.adListener.adLoadSucceeded(null);
        }
      }
    });
  }
  
  private void openLink()
  {
    if ((this.response != null) && (this.response.getClickUrl() != null)) {
      doOpenUrl(this.response.getClickUrl());
    }
  }
  
  private static void setLayer(WebView paramWebView)
  {
    if ((mWebView_SetLayerType != null) && (mWebView_LAYER_TYPE_SOFTWARE != null)) {
      try
      {
        Log.v("Set Layer is supported");
        mWebView_SetLayerType.invoke(paramWebView, new Object[] { Integer.valueOf(mWebView_LAYER_TYPE_SOFTWARE.getInt(WebView.class)), null });
        return;
      }
      catch (InvocationTargetException paramWebView)
      {
        Log.v("Set InvocationTargetException");
        return;
      }
      catch (IllegalArgumentException paramWebView)
      {
        Log.v("Set IllegalArgumentException");
        return;
      }
      catch (IllegalAccessException paramWebView)
      {
        Log.v("Set IllegalAccessException");
        return;
      }
    }
    Log.v("Set Layer is not supported");
  }
  
  private void showContent()
  {
    try
    {
      WebView localWebView;
      String str;
      if (this.viewFlipper.getCurrentView() == this.firstWebView)
      {
        localWebView = this.secondWebView;
        if (this.response.getType() != 0) {
          break label170;
        }
        str = MessageFormat.format("<body style='\"'margin: 0px; padding: 0px; text-align:center;'\"'><img src='\"'{0}'\"' width='\"'{1}'dp\"' height='\"'{2}'dp\"'/></body>", new Object[] { this.response.getImageUrl(), Integer.valueOf(this.response.getBannerWidth()), Integer.valueOf(this.response.getBannerHeight()) });
        Log.d("ADSDK", "set image: " + str);
        localWebView.loadData(Uri.encode("<style>* { -webkit-tap-highlight-color: rgba(0,0,0,0);} img {width:100%;height:100%}</style>" + str), "text/html", "UTF-8");
        notifyLoadAdSucceeded();
      }
      for (;;)
      {
        if (this.viewFlipper.getCurrentView() != this.firstWebView) {
          break label259;
        }
        Log.d("ADSDK", "show next");
        this.viewFlipper.showNext();
        return;
        localWebView = this.firstWebView;
        break;
        label170:
        if (this.response.getType() == 1)
        {
          str = Uri.encode("<style>* { -webkit-tap-highlight-color: rgba(0,0,0,0);} img {width:100%;height:100%}</style>" + this.response.getText());
          Log.d("ADSDK", "set text: " + str);
          localWebView.loadData(str, "text/html", "UTF-8");
          notifyLoadAdSucceeded();
        }
      }
      Log.d("ADSDK", "show previous");
    }
    catch (Throwable localThrowable)
    {
      Log.e("ADSDK", "Exception in show content", localThrowable);
      return;
    }
    label259:
    this.viewFlipper.showPrevious();
  }
  
  public boolean isInternalBrowser()
  {
    return this.isInternalBrowser;
  }
  
  public void setAdListener(AdListener paramAdListener)
  {
    this.adListener = paramAdListener;
  }
  
  public void setHeight(int paramInt) {}
  
  public void setInternalBrowser(boolean paramBoolean)
  {
    this.isInternalBrowser = paramBoolean;
  }
  
  public void setWidth(int paramInt) {}
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\adsdk\sdk\banner\BannerAdView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */