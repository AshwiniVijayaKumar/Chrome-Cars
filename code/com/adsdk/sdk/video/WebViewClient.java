package com.adsdk.sdk.video;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.webkit.WebView;
import com.adsdk.sdk.Log;
import java.lang.reflect.Method;

public class WebViewClient
  extends android.webkit.WebViewClient
{
  private Activity mActivity;
  private boolean mAllowNavigation = false;
  private String mAllowedUrl;
  private long mFinishedLoadingTime;
  private OnPageLoadedListener mOnPageLoadedListener;
  
  public WebViewClient(Activity paramActivity, boolean paramBoolean)
  {
    this.mActivity = paramActivity;
    this.mAllowNavigation = paramBoolean;
    this.mFinishedLoadingTime = 0L;
  }
  
  public String getAllowedUrl()
  {
    return this.mAllowedUrl;
  }
  
  public long getFinishedLoadingTime()
  {
    return this.mFinishedLoadingTime;
  }
  
  public void onPageFinished(WebView paramWebView, String paramString)
  {
    super.onPageFinished(paramWebView, paramString);
    Log.d("onPageFinished:" + paramString + " mAllowedUrl:" + this.mAllowedUrl);
    if ((this.mAllowedUrl == null) || (paramString.equals(this.mAllowedUrl)))
    {
      if (this.mFinishedLoadingTime == 0L) {
        this.mFinishedLoadingTime = System.currentTimeMillis();
      }
      if (this.mOnPageLoadedListener != null) {
        this.mOnPageLoadedListener.onPageLoaded();
      }
    }
  }
  
  public void setAllowedUrl(String paramString)
  {
    this.mFinishedLoadingTime = 0L;
    this.mAllowedUrl = paramString;
    if (this.mAllowedUrl != null)
    {
      paramString = Uri.parse(this.mAllowedUrl).getPath();
      if ((paramString == null) || (paramString.length() == 0)) {
        this.mAllowedUrl += "/";
      }
    }
  }
  
  public void setOnPageLoadedListener(OnPageLoadedListener paramOnPageLoadedListener)
  {
    this.mOnPageLoadedListener = paramOnPageLoadedListener;
  }
  
  public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
  {
    Log.d("Loading url:" + paramString);
    if ((paramString.startsWith("market:")) || (paramString.startsWith("http://market.android.com")) || (paramString.startsWith("sms:")) || (paramString.startsWith("tel:")) || (paramString.startsWith("mailto:")) || (paramString.startsWith("voicemail:")) || (paramString.startsWith("geo:")) || (paramString.startsWith("google.streetview:")))
    {
      paramWebView = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
      try
      {
        this.mActivity.startActivity(paramWebView);
        return true;
      }
      catch (ActivityNotFoundException paramWebView)
      {
        Log.w("Could open URL: " + paramString);
        return true;
      }
    }
    if (paramString.startsWith("mfox:external:"))
    {
      paramWebView = new Intent("android.intent.action.VIEW", Uri.parse(paramString.substring(14)));
      this.mActivity.startActivity(paramWebView);
      return true;
    }
    if (paramString.startsWith("mfox:replayvideo")) {
      try
      {
        this.mActivity.getClass().getMethod("replayVideo", new Class[0]).invoke(this.mActivity, new Object[0]);
        return true;
      }
      catch (NoSuchMethodException paramWebView)
      {
        Log.d("Your activity class has no replayVideo method");
        return true;
      }
      catch (Exception paramWebView)
      {
        Log.d("Couldn't run replayVideo method in your Activity");
        return true;
      }
    }
    if (paramString.startsWith("mfox:playvideo")) {
      try
      {
        this.mActivity.getClass().getMethod("playVideo", new Class[0]).invoke(this.mActivity, new Object[0]);
        return true;
      }
      catch (NoSuchMethodException paramWebView)
      {
        Log.d("Your activity class has no playVideo method");
        return true;
      }
      catch (Exception paramWebView)
      {
        Log.d("Couldn't run replayVideo method in your Activity");
        return true;
      }
    }
    if (paramString.startsWith("mfox:skip"))
    {
      this.mActivity.finish();
      return true;
    }
    if ((this.mAllowNavigation) || (paramString.equals(this.mAllowedUrl)))
    {
      paramWebView.loadUrl(paramString);
      return true;
    }
    paramWebView = new Intent(this.mActivity, RichMediaActivity.class);
    paramWebView.setData(Uri.parse(paramString));
    this.mActivity.startActivity(paramWebView);
    return true;
  }
  
  public static abstract interface OnPageLoadedListener
  {
    public abstract void onPageLoaded();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\adsdk\sdk\video\WebViewClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */