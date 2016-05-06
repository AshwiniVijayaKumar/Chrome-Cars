package com.adsdk.sdk.banner;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import com.adsdk.sdk.Log;

public class InAppWebView
  extends Activity
{
  public static final String URL_EXTRA = "extra_url";
  
  private void initializeWebView(Intent paramIntent)
  {
    WebView localWebView = new WebView(this);
    setContentView(localWebView);
    WebSettings localWebSettings = localWebView.getSettings();
    localWebSettings.setJavaScriptEnabled(true);
    localWebSettings.setSupportZoom(true);
    localWebSettings.setBuiltInZoomControls(true);
    localWebSettings.setUseWideViewPort(true);
    localWebView.setWebViewClient(new WebViewClient()
    {
      public void onReceivedError(WebView paramAnonymousWebView, int paramAnonymousInt, String paramAnonymousString1, String paramAnonymousString2)
      {
        Toast.makeText((Activity)paramAnonymousWebView.getContext(), "MRAID error: " + paramAnonymousString1, 0).show();
      }
      
      public boolean shouldOverrideUrlLoading(WebView paramAnonymousWebView, String paramAnonymousString)
      {
        if (paramAnonymousString == null) {
          return false;
        }
        String str = Uri.parse(paramAnonymousString).getHost();
        if (((paramAnonymousString.startsWith("http:")) || (paramAnonymousString.startsWith("https:"))) && (!"play.google.com".equals(str)) && (!"market.android.com".equals(str)))
        {
          paramAnonymousWebView.loadUrl(paramAnonymousString);
          return true;
        }
        try
        {
          InAppWebView.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(paramAnonymousString)));
          InAppWebView.this.finish();
          return true;
        }
        catch (ActivityNotFoundException paramAnonymousWebView)
        {
          for (;;)
          {
            Log.w("MoPub", "Unable to start activity for " + paramAnonymousString + ". " + "Ensure that your phone can handle this intent.");
          }
        }
      }
    });
    localWebView.setWebChromeClient(new WebChromeClient()
    {
      public void onProgressChanged(WebView paramAnonymousWebView, int paramAnonymousInt)
      {
        Activity localActivity = (Activity)paramAnonymousWebView.getContext();
        localActivity.setTitle("Loading...");
        localActivity.setProgress(paramAnonymousInt * 100);
        if (paramAnonymousInt == 100) {
          localActivity.setTitle(paramAnonymousWebView.getUrl());
        }
      }
    });
    localWebView.loadUrl(paramIntent.getStringExtra("REDIRECT_URI"));
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    getWindow().requestFeature(2);
    getWindow().setFeatureInt(2, -1);
    initializeWebView(getIntent());
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\adsdk\sdk\banner\InAppWebView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */