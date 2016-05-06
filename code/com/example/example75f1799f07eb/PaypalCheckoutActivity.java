package com.example.example75f1799f07eb;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.io.PrintStream;

public class PaypalCheckoutActivity
  extends Activity
{
  TextView appName;
  ProgressBar pb;
  private String resellerValue;
  private String webviewdata;
  WebView wv_book;
  
  public void close(View paramView)
  {
    finish();
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(7);
    setContentView(2130903119);
    getWindow().setFeatureInt(7, 2130903063);
    paramBundle = getResources().getString(2131230720);
    this.appName = ((TextView)findViewById(2131558500));
    this.appName.setText(paramBundle);
    this.webviewdata = getIntent().getStringExtra("webviewdata");
    this.resellerValue = getIntent().getStringExtra("resellerValue");
    this.pb = ((ProgressBar)findViewById(2131558642));
    this.pb.setVisibility(0);
    ((ImageButton)findViewById(2131558452)).setVisibility(8);
    this.wv_book = ((WebView)findViewById(2131558641));
    paramBundle = this.wv_book.getSettings();
    paramBundle.setJavaScriptEnabled(true);
    paramBundle.setJavaScriptCanOpenWindowsAutomatically(true);
    this.wv_book.getSettings().setLoadWithOverviewMode(true);
    this.wv_book.getSettings().setUseWideViewPort(true);
    this.wv_book.getSettings().setSupportZoom(true);
    this.wv_book.getSettings().setBuiltInZoomControls(true);
    this.wv_book.setInitialScale(1);
    this.wv_book.requestFocus();
    this.wv_book.requestFocusFromTouch();
    this.wv_book.loadData(this.webviewdata, "text/html", "UTF-8");
    this.wv_book.setWebViewClient(new WebViewClient()
    {
      public void onPageFinished(WebView paramAnonymousWebView, String paramAnonymousString)
      {
        super.onPageFinished(paramAnonymousWebView, paramAnonymousString);
        System.out.println("paypal url22>>>>>>>>>" + paramAnonymousString);
        System.out.println("paypal getTitle22>>>>>>>>>" + PaypalCheckoutActivity.this.wv_book.getTitle());
        PaypalCheckoutActivity.this.pb.setVisibility(8);
        if (PaypalCheckoutActivity.this.wv_book.getTitle().equals("Your purchase was successful - PayPal"))
        {
          MyPhoneGapActivity.updateFoodPaypalStatusForSignup("success");
          PaypalCheckoutActivity.this.finish();
        }
      }
      
      public boolean shouldOverrideUrlLoading(WebView paramAnonymousWebView, String paramAnonymousString)
      {
        System.out.println("paypal url>>>>>>>>>" + paramAnonymousString);
        if (paramAnonymousString.contains("http://" + MyPhoneGapActivity.appDomainValue + "/paypalmobile/cancel"))
        {
          MyPhoneGapActivity.updateFoodPaypalStatus("cancel");
          PaypalCheckoutActivity.this.finish();
        }
        for (;;)
        {
          paramAnonymousWebView.loadUrl(paramAnonymousString);
          return false;
          if (paramAnonymousString.contains("http://" + MyPhoneGapActivity.appDomainValue + "/paypalmobile/successformbuilder"))
          {
            MyPhoneGapActivity.updateFoodPaypalStatusForFormbuilder("success");
            PaypalCheckoutActivity.this.finish();
          }
          else if (paramAnonymousString.contains("http://" + MyPhoneGapActivity.appDomainValue + "/paypalmobile/cancelformbuilder"))
          {
            MyPhoneGapActivity.updateFoodPaypalStatusForFormbuilder("failed");
            PaypalCheckoutActivity.this.finish();
          }
          else if (paramAnonymousString.contains("http://" + MyPhoneGapActivity.appDomainValue + "/paypalmobile/success"))
          {
            MyPhoneGapActivity.updateFoodPaypalStatus("Success");
            PaypalCheckoutActivity.this.finish();
          }
          else if (paramAnonymousString.contains("http://" + MyPhoneGapActivity.appDomainValue + "/paypalmobile/newsstand-success"))
          {
            MyPhoneGapActivity.updateFoodPaypalStatusForNewsStand("success");
            PaypalCheckoutActivity.this.finish();
          }
          else if (paramAnonymousString.contains("http://" + MyPhoneGapActivity.appDomainValue + "/paypalmobile/newsstand-cancel"))
          {
            MyPhoneGapActivity.updateFoodPaypalStatusForNewsStand("failed");
            PaypalCheckoutActivity.this.finish();
          }
          else if (paramAnonymousString.contains("signup-success"))
          {
            MyPhoneGapActivity.updateFoodPaypalStatusForSignup("success");
            PaypalCheckoutActivity.this.finish();
          }
          else if (paramAnonymousString.contains("signup-cancel"))
          {
            MyPhoneGapActivity.updateFoodPaypalStatusForSignup("failed");
            PaypalCheckoutActivity.this.finish();
          }
        }
      }
    });
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\example\example75f1799f07eb\PaypalCheckoutActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */