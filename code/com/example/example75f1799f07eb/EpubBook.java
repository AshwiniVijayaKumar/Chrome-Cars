package com.example.example75f1799f07eb;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.widget.Toast;
import java.util.Timer;
import java.util.TimerTask;
import org.apache.cordova.CordovaActivity;
import org.apache.cordova.CordovaWebView;

@SuppressLint({"SetJavaScriptEnabled"})
public class EpubBook
  extends CordovaActivity
{
  MediaPlayer mp;
  protected int screenHeight;
  protected int screenWidth;
  protected String swipeLeftOrRight = "";
  protected float swipeOriginX;
  protected float swipeOriginY;
  Timer timer;
  
  @JavascriptInterface
  public void close()
  {
    finish();
  }
  
  public void errorMessage(String paramString)
  {
    Toast.makeText(getApplicationContext(), paramString, 0).show();
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt2 == -1)
    {
      paramIntent = paramIntent.getStringExtra("text");
      this.appView.loadUrl("javascript:loadChapter('" + paramIntent + "')");
    }
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    super.init();
  }
  
  protected void swipePage(View paramView, MotionEvent paramMotionEvent)
  {
    switch (MotionEventCompat.getActionMasked(paramMotionEvent))
    {
    }
    int i;
    float f1;
    float f3;
    float f2;
    do
    {
      return;
      this.swipeOriginX = paramMotionEvent.getX();
      this.swipeOriginY = paramMotionEvent.getY();
      return;
      i = (int)(this.screenWidth * 0.5D);
      f1 = this.swipeOriginX - paramMotionEvent.getX();
      f3 = this.swipeOriginY;
      float f4 = paramMotionEvent.getY();
      f2 = Math.abs(f1);
      f3 = Math.abs(f3 - f4);
      if ((f1 > i) && (f2 > f3)) {
        try
        {
          this.swipeLeftOrRight = "right";
          this.appView.loadUrl("javascript:nextPage()");
          this.timer.schedule(new SleepTask(), 500L);
          return;
        }
        catch (Exception paramView)
        {
          errorMessage("Cannot turn page!");
          return;
        }
      }
    } while ((f1 >= -i) || (f2 <= f3));
    try
    {
      this.swipeLeftOrRight = "left";
      this.appView.loadUrl("javascript:prePage()");
      this.timer.schedule(new SleepTask(), 500L);
      return;
    }
    catch (Exception paramView)
    {
      errorMessage("Cannot turn page!");
    }
  }
  
  @JavascriptInterface
  public void tableofContent(String paramString)
  {
    Intent localIntent = new Intent(this, TOC.class);
    localIntent.putExtra("data", paramString);
    startActivityForResult(localIntent, 111);
  }
  
  class SleepTask
    extends TimerTask
  {
    SleepTask() {}
    
    public void run()
    {
      EpubBook.this.mp.start();
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\example\example75f1799f07eb\EpubBook.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */