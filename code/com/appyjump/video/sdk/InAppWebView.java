package com.appyjump.video.sdk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class InAppWebView
  extends Activity
{
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    paramBundle = (String)getIntent().getExtras().get("REDIRECT_URI");
    if (paramBundle == null)
    {
      if (Log.isLoggable("mAdserve", 3)) {
        Log.d("mAdserve", "url is null so do not load anything");
      }
      return;
    }
    WebView localWebView = new WebView(this);
    localWebView.setWebViewClient(new WebViewClient()
    {
      public boolean shouldOverrideUrlLoading(WebView paramAnonymousWebView, String paramAnonymousString)
      {
        paramAnonymousWebView.loadUrl(paramAnonymousString);
        return false;
      }
    });
    localWebView.loadUrl(paramBundle);
    setContentView(localWebView);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\appyjump\video\sdk\InAppWebView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */