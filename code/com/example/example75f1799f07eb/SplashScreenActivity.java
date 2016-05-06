package com.example.example75f1799f07eb;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import java.io.PrintStream;
import java.util.Timer;
import java.util.TimerTask;

public class SplashScreenActivity
  extends Activity
{
  protected static int TIMER_RUNTIME = 10;
  int i = 0;
  private ImageView image;
  RelativeLayout img;
  private Thread mSplashThread;
  int maxtime = 10;
  final Handler myHandler = new Handler();
  final Runnable myRunnable = new Runnable()
  {
    public void run()
    {
      if (SplashScreenActivity.this.oriantationstatus == 0)
      {
        SplashScreenActivity.this.img.setBackgroundResource(0);
        SplashScreenActivity.this.img.setBackgroundResource(2130837708);
        return;
      }
      SplashScreenActivity.this.img.setBackgroundResource(0);
      SplashScreenActivity.this.img.setBackgroundResource(2130837708);
    }
  };
  final Timer myTimer = new Timer();
  int oriantationstatus = 0;
  ProgressBar pb;
  
  private void UpdateGUI()
  {
    this.i += 1;
    this.myHandler.post(this.myRunnable);
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    setContentView(2130903049);
    this.pb = ((ProgressBar)findViewById(2131558458));
    this.img = ((RelativeLayout)findViewById(2131558456));
    this.myTimer.schedule(new TimerTask()
    {
      public void run()
      {
        if ((SplashScreenActivity.TIMER_RUNTIME == 0) || (SplashScreenActivity.this.i > 13))
        {
          SplashScreenActivity.this.myTimer.cancel();
          SplashScreenActivity.this.finish();
          return;
        }
        SplashScreenActivity.this.UpdateGUI();
      }
    }, 0L, 1000L);
    try
    {
      if ((!MyPhoneGapActivity.sharedpreferences.getString("appPlane", "").equalsIgnoreCase("platinum")) && (MyPhoneGapActivity.resellerValue.equalsIgnoreCase("appypie.com")))
      {
        System.out.println("showing branding of appypie");
        ((ImageView)findViewById(2131558457)).setVisibility(0);
        return;
      }
      if ((MyPhoneGapActivity.sharedpreferences.getString("appPlane", "").equalsIgnoreCase("free")) && (MyPhoneGapActivity.resellerValue.equalsIgnoreCase("coolapp.jp")))
      {
        System.out.println("showing branding of coolapp");
        paramBundle = (ImageView)findViewById(2131558457);
        paramBundle.setImageResource(2130837630);
        paramBundle.setVisibility(0);
        return;
      }
    }
    catch (Exception paramBundle)
    {
      this.pb.setVisibility(0);
      return;
    }
    this.pb.setVisibility(0);
    System.out.println("showing loader");
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    try
    {
      this.myTimer.cancel();
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  protected void onRestoreInstanceState(Bundle paramBundle)
  {
    this.i = paramBundle.getInt("index");
    this.oriantationstatus = paramBundle.getInt("oriantationstatus");
    super.onRestoreInstanceState(paramBundle);
  }
  
  protected void onSaveInstanceState(Bundle paramBundle)
  {
    this.myTimer.cancel();
    paramBundle.putInt("index", this.i);
    if (this.oriantationstatus == 1) {
      paramBundle.putInt("oriantationstatus", 0);
    }
    for (;;)
    {
      super.onSaveInstanceState(paramBundle);
      return;
      paramBundle.putInt("oriantationstatus", 1);
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\example\example75f1799f07eb\SplashScreenActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */