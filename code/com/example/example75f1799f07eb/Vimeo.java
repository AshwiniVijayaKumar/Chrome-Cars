package com.example.example75f1799f07eb;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import java.io.PrintStream;

public class Vimeo
  extends Activity
{
  public static final String MyPREFERENCES = "MyPrefs";
  static SharedPreferences sharedpreferences;
  TextView appName;
  HTML5WebView mWebView;
  HTML5WebViewRtl mWebViewRtl;
  Preferences mpreferences;
  private String typeLanguage;
  String vimeoId;
  
  public void close(View paramView)
  {
    finish();
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    try
    {
      sharedpreferences = getSharedPreferences("MyPrefs", 0);
      this.mpreferences = new Preferences(getApplicationContext());
      this.typeLanguage = sharedpreferences.getString("appLanguage", "");
      System.out.println("===== type language is in vimeo : " + this.typeLanguage);
      if (this.typeLanguage.equalsIgnoreCase("en"))
      {
        this.mWebView = new HTML5WebView(this);
        this.vimeoId = getIntent().getStringExtra("vimeoid");
        System.out.println("===== vimoe id is in :" + this.vimeoId);
        if (paramBundle != null)
        {
          this.mWebView.restoreState(paramBundle);
          return;
        }
        if (this.vimeoId.contains("http"))
        {
          this.mWebView.loadUrl("" + this.vimeoId);
          setContentView(this.mWebView.getLayout());
          return;
        }
        this.mWebView.loadUrl("http://player.vimeo.com/video/" + this.vimeoId + "");
        setContentView(this.mWebView.getLayout());
        return;
      }
      this.mWebViewRtl = new HTML5WebViewRtl(this);
      this.vimeoId = getIntent().getStringExtra("vimeoid");
      System.out.println("===== vimoe id is in :" + this.vimeoId);
      if (paramBundle != null)
      {
        this.mWebViewRtl.restoreState(paramBundle);
        return;
      }
      if (this.vimeoId.contains("http"))
      {
        this.mWebViewRtl.loadUrl("" + this.vimeoId);
        setContentView(this.mWebViewRtl.getLayout());
        return;
      }
      this.mWebViewRtl.loadUrl("http://player.vimeo.com/video/" + this.vimeoId + "");
      setContentView(this.mWebViewRtl.getLayout());
      return;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (this.typeLanguage.equalsIgnoreCase("en"))
    {
      if ((paramInt == 4) && (this.mWebView.inCustomView()))
      {
        this.mWebView.hideCustomView();
        return true;
      }
    }
    else if ((paramInt == 4) && (this.mWebViewRtl.inCustomView()))
    {
      this.mWebViewRtl.hideCustomView();
      return true;
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (paramMenuItem.getItemId() == 16908332) {
      finish();
    }
    return super.onOptionsItemSelected(paramMenuItem);
  }
  
  public void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    if (this.typeLanguage.equalsIgnoreCase("en"))
    {
      this.mWebView.saveState(paramBundle);
      return;
    }
    this.mWebViewRtl.saveState(paramBundle);
  }
  
  public void onStop()
  {
    super.onStop();
    if (this.typeLanguage.equalsIgnoreCase("en"))
    {
      this.mWebView.stopLoading();
      if (this.vimeoId.contains("http")) {
        finish();
      }
    }
    do
    {
      return;
      this.mWebViewRtl.stopLoading();
    } while (!this.vimeoId.contains("http"));
    finish();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\example\example75f1799f07eb\Vimeo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */