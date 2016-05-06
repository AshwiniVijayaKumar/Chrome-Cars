package com.example.example75f1799f07eb;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.webkit.GeolocationPermissions.Callback;
import android.webkit.WebChromeClient;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import java.io.PrintStream;

public class HTML5WebViewRtl
  extends WebView
{
  static final FrameLayout.LayoutParams COVER_SCREEN_PARAMS = new FrameLayout.LayoutParams(-1, -1);
  static final String LOGTAG = "HTML5WebView";
  public static final String MyPREFERENCES = "MyPrefs";
  static SharedPreferences sharedpreferences;
  private FrameLayout mBrowserFrameLayout;
  private FrameLayout mContentView;
  private Context mContext;
  private View mCustomView;
  private WebChromeClient.CustomViewCallback mCustomViewCallback;
  private FrameLayout mCustomViewContainer;
  private FrameLayout mLayout;
  private MyWebChromeClient mWebChromeClient;
  
  public HTML5WebViewRtl(Context paramContext)
  {
    super(paramContext);
    init(paramContext);
  }
  
  public HTML5WebViewRtl(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext);
  }
  
  public HTML5WebViewRtl(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext);
  }
  
  private void init(Context paramContext)
  {
    this.mContext = paramContext;
    Activity localActivity = (Activity)this.mContext;
    this.mLayout = new FrameLayout(paramContext);
    this.mBrowserFrameLayout = ((FrameLayout)LayoutInflater.from(localActivity).inflate(2130903067, null));
    this.mContentView = ((FrameLayout)this.mBrowserFrameLayout.findViewById(2131558508));
    this.mCustomViewContainer = ((FrameLayout)this.mBrowserFrameLayout.findViewById(2131558506));
    this.mLayout.addView(this.mBrowserFrameLayout, COVER_SCREEN_PARAMS);
    paramContext = getSettings();
    paramContext.setBuiltInZoomControls(true);
    paramContext.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
    paramContext.setUseWideViewPort(true);
    paramContext.setLoadWithOverviewMode(true);
    paramContext.setSaveFormData(true);
    paramContext.setJavaScriptEnabled(true);
    this.mWebChromeClient = new MyWebChromeClient(null);
    setWebChromeClient(this.mWebChromeClient);
    setWebViewClient(new WebViewClient());
    setScrollBarStyle(0);
    paramContext.setDomStorageEnabled(true);
    this.mContentView.addView(this);
  }
  
  public FrameLayout getLayout()
  {
    return this.mLayout;
  }
  
  public void hideCustomView()
  {
    this.mWebChromeClient.onHideCustomView();
  }
  
  public boolean inCustomView()
  {
    return this.mCustomView != null;
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if ((paramInt == 4) && (this.mCustomView == null) && (canGoBack()))
    {
      goBack();
      return true;
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
  
  private class MyWebChromeClient
    extends WebChromeClient
  {
    private Bitmap mDefaultVideoPoster;
    private View mVideoProgressView;
    
    private MyWebChromeClient() {}
    
    public View getVideoLoadingProgressView()
    {
      if (this.mVideoProgressView == null) {
        this.mVideoProgressView = LayoutInflater.from(HTML5WebViewRtl.this.mContext).inflate(2130903157, null);
      }
      return this.mVideoProgressView;
    }
    
    public void onGeolocationPermissionsShowPrompt(String paramString, GeolocationPermissions.Callback paramCallback)
    {
      paramCallback.invoke(paramString, true, false);
    }
    
    public void onHideCustomView()
    {
      System.out.println("customview hideeeeeeeeeeeeeeeeeeeeeeeeeee");
      if (HTML5WebViewRtl.this.mCustomView == null) {
        return;
      }
      HTML5WebViewRtl.this.mCustomView.setVisibility(8);
      HTML5WebViewRtl.this.mCustomViewContainer.removeView(HTML5WebViewRtl.this.mCustomView);
      HTML5WebViewRtl.access$102(HTML5WebViewRtl.this, null);
      HTML5WebViewRtl.this.mCustomViewContainer.setVisibility(8);
      HTML5WebViewRtl.this.mCustomViewCallback.onCustomViewHidden();
      HTML5WebViewRtl.this.setVisibility(0);
      HTML5WebViewRtl.this.goBack();
    }
    
    public void onProgressChanged(WebView paramWebView, int paramInt)
    {
      ((Activity)HTML5WebViewRtl.this.mContext).getWindow().setFeatureInt(2, paramInt * 100);
    }
    
    public void onReceivedTitle(WebView paramWebView, String paramString)
    {
      ((Activity)HTML5WebViewRtl.this.mContext).setTitle(paramString);
    }
    
    public void onShowCustomView(View paramView, WebChromeClient.CustomViewCallback paramCustomViewCallback)
    {
      HTML5WebViewRtl.this.setVisibility(8);
      if (HTML5WebViewRtl.this.mCustomView != null)
      {
        paramCustomViewCallback.onCustomViewHidden();
        return;
      }
      HTML5WebViewRtl.this.mCustomViewContainer.addView(paramView);
      HTML5WebViewRtl.access$102(HTML5WebViewRtl.this, paramView);
      HTML5WebViewRtl.access$302(HTML5WebViewRtl.this, paramCustomViewCallback);
      HTML5WebViewRtl.this.mCustomViewContainer.setVisibility(0);
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\example\example75f1799f07eb\HTML5WebViewRtl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */