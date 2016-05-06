package com.google.zxing.client.android;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public final class HelpActivity
  extends Activity
{
  private static final String BASE_URL = "file:///android_asset/html-" + LocaleManager.getTranslatedAssetLanguage() + '/';
  public static final String DEFAULT_PAGE = "index.html";
  public static final String REQUESTED_PAGE_KEY = "requested_page_key";
  private static final String WEBVIEW_STATE_PRESENT = "webview_state_present";
  public static final String WHATS_NEW_PAGE = "whatsnew.html";
  private Button backButton;
  private final View.OnClickListener backListener = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      HelpActivity.this.webView.goBack();
    }
  };
  private final View.OnClickListener doneListener = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      HelpActivity.this.finish();
    }
  };
  private WebView webView;
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.help);
    this.webView = ((WebView)findViewById(R.id.help_contents));
    this.webView.setWebViewClient(new HelpClient(null));
    Intent localIntent = getIntent();
    if ((paramBundle != null) && (paramBundle.getBoolean("webview_state_present", false))) {
      this.webView.restoreState(paramBundle);
    }
    for (;;)
    {
      this.backButton = ((Button)findViewById(R.id.back_button));
      this.backButton.setOnClickListener(this.backListener);
      findViewById(R.id.done_button).setOnClickListener(this.doneListener);
      return;
      if (localIntent != null)
      {
        paramBundle = localIntent.getStringExtra("requested_page_key");
        if ((paramBundle != null) && (paramBundle.length() > 0)) {
          this.webView.loadUrl(BASE_URL + paramBundle);
        } else {
          this.webView.loadUrl(BASE_URL + "index.html");
        }
      }
      else
      {
        this.webView.loadUrl(BASE_URL + "index.html");
      }
    }
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if ((paramInt == 4) && (this.webView.canGoBack()))
    {
      this.webView.goBack();
      return true;
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
  
  protected void onSaveInstanceState(Bundle paramBundle)
  {
    String str = this.webView.getUrl();
    if ((str != null) && (str.length() > 0))
    {
      this.webView.saveState(paramBundle);
      paramBundle.putBoolean("webview_state_present", true);
    }
  }
  
  private final class HelpClient
    extends WebViewClient
  {
    private HelpClient() {}
    
    public void onPageFinished(WebView paramWebView, String paramString)
    {
      HelpActivity.this.setTitle(paramWebView.getTitle());
      HelpActivity.this.backButton.setEnabled(paramWebView.canGoBack());
    }
    
    public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
    {
      if (paramString.startsWith("file")) {
        return false;
      }
      HelpActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(paramString)));
      return true;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\HelpActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */