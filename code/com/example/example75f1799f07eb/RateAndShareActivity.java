package com.example.example75f1799f07eb;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import java.io.PrintStream;

public class RateAndShareActivity
  extends Activity
{
  TextView b1;
  TextView b2;
  TextView b3;
  String shareUrl = "";
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903132);
    this.b1 = ((TextView)findViewById(2131558445));
    this.b2 = ((TextView)findViewById(2131558490));
    this.b3 = ((TextView)findViewById(2131558711));
    String str2 = getApplicationContext().getPackageName();
    String str1 = getIntent().getStringExtra("shareStr");
    System.out.println("krshna>>>>>>>>>>>>>>>>>>>>" + str1.length());
    paramBundle = str1;
    if (str1.trim().equalsIgnoreCase("")) {
      paramBundle = "";
    }
    if ((paramBundle.equalsIgnoreCase("")) || (paramBundle == null) || (paramBundle.equals("")) || (paramBundle.equals(null)) || ((paramBundle.contains("_DEVICE_")) && (paramBundle.contains("_APPNAME_")))) {}
    for (paramBundle = "Download our app exclusively from the Android Apps Store by searching " + getResources().getString(2131230720) + "  or by visiting the below link. ";; paramBundle = paramBundle + " ")
    {
      this.shareUrl = ("" + paramBundle + "https://play.google.com/store/apps/details?id=" + str2);
      this.b1.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          RateAndShareActivity.this.rateApp();
        }
      });
      this.b2.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          RateAndShareActivity.this.shareApp();
        }
      });
      this.b3.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          RateAndShareActivity.this.finish();
        }
      });
      return;
    }
  }
  
  public void rateApp()
  {
    startActivity(new Intent("android.intent.action.VIEW", Uri.parse(this.shareUrl)));
    finish();
  }
  
  public void shareApp()
  {
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.setType("text/plain");
    localIntent.putExtra("android.intent.extra.SUBJECT", getResources().getString(2131230720) + " (Open it in Google Play Store to Download the Application)");
    localIntent.putExtra("android.intent.extra.TEXT", this.shareUrl);
    startActivity(Intent.createChooser(localIntent, "Share via"));
    finish();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\example\example75f1799f07eb\RateAndShareActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */