package com.appyjump.video.sdk;

import android.content.Context;
import android.webkit.WebView;
import android.webkit.WebViewClient;

class AdserveWebViewClient
  extends WebViewClient
{
  private final AdserveView adServerViewCore;
  private Context context;
  
  public AdserveWebViewClient(AdserveView paramAdserveView, Context paramContext)
  {
    this.adServerViewCore = paramAdserveView;
    this.context = paramContext;
  }
  
  public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
  {
    return true;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\appyjump\video\sdk\AdserveWebViewClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */