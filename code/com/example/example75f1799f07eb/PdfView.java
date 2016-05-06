package com.example.example75f1799f07eb;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import java.io.PrintStream;

public class PdfView
  extends Activity
{
  public static final String MyPREFERENCES = "MyPrefs";
  private static SharedPreferences sharedpreferences;
  TextView appName;
  WebView mWebView;
  Preferences mpreferences;
  
  public void close(View paramView)
  {
    finish();
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    sharedpreferences = getSharedPreferences("MyPrefs", 0);
    this.mpreferences = new Preferences(getApplicationContext());
    paramBundle = sharedpreferences.getString("appLanguage", "");
    System.out.println("===== type language is in PDFVIEW : " + paramBundle);
    requestWindowFeature(1);
    if (paramBundle.equalsIgnoreCase("en")) {
      setContentView(2130903096);
    }
    for (;;)
    {
      paramBundle = getIntent().getStringExtra("pdfurl");
      this.mWebView = ((WebView)findViewById(2131558575));
      String str = getResources().getString(2131230720);
      this.appName = ((TextView)findViewById(2131558451));
      this.appName.setText(str);
      this.mWebView.getSettings().setJavaScriptEnabled(true);
      this.mWebView.loadUrl(paramBundle);
      this.mWebView.setWebViewClient(new WebViewClient()
      {
        public boolean shouldOverrideUrlLoading(WebView paramAnonymousWebView, String paramAnonymousString)
        {
          PdfView.this.mWebView.loadUrl(paramAnonymousString);
          return true;
        }
      });
      return;
      setContentView(2130903097);
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\example\example75f1799f07eb\PdfView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */