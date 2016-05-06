package com.example.example75f1799f07eb;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.os.Handler;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.io.File;
import java.io.PrintStream;

public class HTML5WebView
  extends WebView
{
  static final FrameLayout.LayoutParams COVER_SCREEN_PARAMS = new FrameLayout.LayoutParams(-1, -1);
  static final String LOGTAG = "HTML5WebView";
  public static final String MyPREFERENCES = "MyPrefs";
  static SharedPreferences sharedpreferences;
  String Device_Oriantation = "";
  String HeaderBarbackgroundColor;
  String HeaderbarTextColor;
  String ImgURl_Land;
  String ImgURl_Port;
  int Oriantation_ID;
  File SDCardRoot;
  String applicationName;
  File file;
  String foldername = "";
  RelativeLayout headerText;
  private FrameLayout mBrowserFrameLayout;
  private FrameLayout mContentView;
  private Context mContext;
  private View mCustomView;
  private WebChromeClient.CustomViewCallback mCustomViewCallback;
  private FrameLayout mCustomViewContainer;
  private Handler mHandler;
  private FrameLayout mLayout;
  private MyWebChromeClient mWebChromeClient;
  Preferences mpreferences;
  String navigationBarType;
  TextView titletxtview;
  TextView updateText;
  
  public HTML5WebView(Context paramContext)
  {
    super(paramContext);
    init(paramContext);
  }
  
  public HTML5WebView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext);
  }
  
  public HTML5WebView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext);
  }
  
  private void Check_Device_Oriantation()
  {
    this.Oriantation_ID = getResources().getConfiguration().orientation;
    switch (this.Oriantation_ID)
    {
    default: 
      this.Device_Oriantation = "";
      Setheaderimage();
      return;
    case 2: 
      this.Device_Oriantation = "LANDSCAPE";
      Setheaderimage();
      return;
    case 1: 
      this.Device_Oriantation = "PORTRAIT";
      Setheaderimage();
      return;
    }
    this.Device_Oriantation = "UNDEFINED";
    Setheaderimage();
  }
  
  private void Setheaderimage()
  {
    try
    {
      Object localObject1 = getApplicationName();
      sharedpreferences = this.mContext.getSharedPreferences("MyPrefs", 0);
      this.navigationBarType = sharedpreferences.getString("navigationBarType", "");
      this.HeaderBarbackgroundColor = sharedpreferences.getString("HeaderBarbackgroundColor", "");
      this.HeaderbarTextColor = sharedpreferences.getString("HeaderbarTextColor", "");
      this.ImgURl_Port = sharedpreferences.getString("ImgURl_Port", "");
      this.ImgURl_Land = sharedpreferences.getString("ImgURl_Land", "");
      System.out.println("navigationBarType " + this.navigationBarType + " , HeaderBarbackgroundColor " + this.HeaderBarbackgroundColor + " , HeaderbarTextColor " + this.HeaderbarTextColor);
      if (this.navigationBarType.equals("image"))
      {
        this.SDCardRoot = new File(Environment.getExternalStorageDirectory(), this.foldername + (String)localObject1);
        if (this.Device_Oriantation.equals("PORTRAIT"))
        {
          this.file = new File(this.SDCardRoot, "header_port_img.jpg");
          if (!this.file.exists()) {
            return;
          }
          localObject1 = BitmapFactory.decodeFile(this.file.getAbsolutePath());
          localObject1 = new BitmapDrawable(getResources(), (Bitmap)localObject1);
          this.headerText.setBackground((Drawable)localObject1);
          this.titletxtview.setText("");
          return;
        }
        this.file = new File(this.SDCardRoot, "header_land_img.jpg");
        if (!this.file.exists()) {
          return;
        }
        localObject1 = BitmapFactory.decodeFile(this.file.getAbsolutePath());
        localObject1 = new BitmapDrawable(getResources(), (Bitmap)localObject1);
        this.headerText.setBackground((Drawable)localObject1);
        this.titletxtview.setText("");
        return;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      return;
    }
    System.out.println("Headerbar color code : HeaderBarbackgroundColor  " + this.HeaderBarbackgroundColor + "  ,  HeaderbarTextColor  " + this.HeaderbarTextColor);
    this.titletxtview.setTextColor(Color.parseColor(this.HeaderbarTextColor));
    Object localObject2 = this.mContext.getSharedPreferences("MyPrefs", 0).getString("HeaderBarbackgroundColor", "");
    System.out.println("krishna header color>>>" + (String)localObject2);
    if (!((String)localObject2).equals(""))
    {
      Object localObject3;
      String str;
      if (((String)localObject2).contains("rgba"))
      {
        localObject3 = ((String)localObject2).split(",");
        localObject3[0] = localObject3[0].split("\\(")[1];
        localObject3[3] = localObject3[3].split("\\)")[0];
        Integer.toHexString(Integer.parseInt(localObject3[3]));
        localObject2 = Integer.toHexString(Integer.parseInt(localObject3[0]));
        str = Integer.toHexString(Integer.parseInt(localObject3[1]));
        localObject3 = Integer.toHexString(Integer.parseInt(localObject3[2]));
        str = "#" + (String)localObject2 + str + (String)localObject3;
        localObject2 = str;
        if (str.equals("#000")) {
          localObject2 = "#000000";
        }
        this.headerText.setBackgroundColor(Color.parseColor((String)localObject2));
        this.titletxtview.setBackgroundColor(Color.parseColor((String)localObject2));
        return;
      }
      if (((String)localObject2).contains("rgb"))
      {
        localObject3 = ((String)localObject2).split(",");
        localObject3[0] = localObject3[0].split("\\(")[1];
        localObject3[2] = localObject3[2].split("\\)")[0];
        localObject2 = Integer.toHexString(Integer.parseInt(localObject3[0]));
        str = Integer.toHexString(Integer.parseInt(localObject3[1]));
        localObject3 = Integer.toHexString(Integer.parseInt(localObject3[2]));
        str = "#" + (String)localObject2 + str + (String)localObject3;
        localObject2 = str;
        if (str.equals("#000")) {
          localObject2 = "#000000";
        }
        this.headerText.setBackgroundColor(Color.parseColor((String)localObject2));
        return;
      }
      this.headerText.setBackgroundColor(Color.parseColor((String)localObject2));
      return;
    }
    this.headerText.setBackgroundColor(Color.parseColor("#33b5e5"));
  }
  
  private void init(Context paramContext)
  {
    this.mContext = paramContext;
    Object localObject = (Activity)this.mContext;
    this.mLayout = new FrameLayout(paramContext);
    paramContext = (FrameLayout)LayoutInflater.from((Context)localObject).inflate(2130903066, null);
    this.applicationName = getResources().getString(2131230720);
    System.out.println("===== setName is in html5webview : " + this.applicationName);
    this.mBrowserFrameLayout = ((FrameLayout)LayoutInflater.from((Context)localObject).inflate(2130903066, null));
    this.mContentView = ((FrameLayout)this.mBrowserFrameLayout.findViewById(2131558508));
    paramContext = MyPhoneGapActivity.sharedpreferences.getString("headerBarFont", "georgia");
    this.mCustomViewContainer = ((FrameLayout)this.mBrowserFrameLayout.findViewById(2131558506));
    this.titletxtview = ((TextView)this.mBrowserFrameLayout.findViewById(2131558510));
    this.headerText = ((RelativeLayout)this.mBrowserFrameLayout.findViewById(2131558509));
    this.mLayout.addView(this.mBrowserFrameLayout, COVER_SCREEN_PARAMS);
    for (;;)
    {
      try
      {
        sharedpreferences = this.mContext.getSharedPreferences("MyPrefs", 0);
        this.mpreferences = new Preferences(this.mContext);
        localObject = sharedpreferences.getString("headerBarTitle", "");
        if ((localObject != null) && (!((String)localObject).equals(""))) {
          this.titletxtview.setText((CharSequence)localObject);
        }
      }
      catch (Exception paramContext)
      {
        continue;
      }
      try
      {
        paramContext = Typeface.createFromAsset(this.mContext.getAssets(), "www/fonts/" + paramContext + ".ttf");
        this.titletxtview.setTypeface(paramContext);
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
        Check_Device_Oriantation();
        Setheaderimage();
        return;
      }
      catch (Exception paramContext)
      {
        System.out.println("............." + paramContext.getMessage());
      }
    }
  }
  
  public String getApplicationName()
  {
    int i = this.mContext.getApplicationInfo().labelRes;
    return this.mContext.getString(i);
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
        this.mVideoProgressView = LayoutInflater.from(HTML5WebView.this.mContext).inflate(2130903157, null);
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
      if (HTML5WebView.this.mCustomView == null) {
        return;
      }
      HTML5WebView.this.mCustomView.setVisibility(8);
      HTML5WebView.this.mCustomViewContainer.removeView(HTML5WebView.this.mCustomView);
      HTML5WebView.access$102(HTML5WebView.this, null);
      HTML5WebView.this.mCustomViewContainer.setVisibility(8);
      HTML5WebView.this.mCustomViewCallback.onCustomViewHidden();
      HTML5WebView.this.setVisibility(0);
      HTML5WebView.this.goBack();
    }
    
    public void onProgressChanged(WebView paramWebView, int paramInt)
    {
      ((Activity)HTML5WebView.this.mContext).getWindow().setFeatureInt(2, paramInt * 100);
    }
    
    public void onReceivedTitle(WebView paramWebView, String paramString)
    {
      ((Activity)HTML5WebView.this.mContext).setTitle(paramString);
    }
    
    public void onShowCustomView(View paramView, WebChromeClient.CustomViewCallback paramCustomViewCallback)
    {
      HTML5WebView.this.setVisibility(8);
      if (HTML5WebView.this.mCustomView != null)
      {
        paramCustomViewCallback.onCustomViewHidden();
        return;
      }
      HTML5WebView.this.mCustomViewContainer.addView(paramView);
      HTML5WebView.access$102(HTML5WebView.this, paramView);
      HTML5WebView.access$302(HTML5WebView.this, paramCustomViewCallback);
      HTML5WebView.this.mCustomViewContainer.setVisibility(0);
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\example\example75f1799f07eb\HTML5WebView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */