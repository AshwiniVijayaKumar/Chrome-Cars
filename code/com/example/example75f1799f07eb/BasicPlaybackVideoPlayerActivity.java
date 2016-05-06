package com.example.example75f1799f07eb;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import com.ooyala.android.OoyalaPlayer;
import com.ooyala.android.OoyalaPlayerLayout;
import com.ooyala.android.PlayerDomain;
import com.ooyala.android.ui.OoyalaPlayerLayoutController;
import java.lang.reflect.Field;
import java.util.Observable;
import java.util.Observer;

public class BasicPlaybackVideoPlayerActivity
  extends Activity
  implements Observer
{
  final String DOMAIN = "http://ooyala.com";
  String EMBED = null;
  String PCODE = "V1ZWYyOsYHtVanW5hci1etWR33GW";
  final String TAG = getClass().toString();
  protected OoyalaPlayer player;
  protected OoyalaPlayerLayoutController playerLayoutController;
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(8);
    paramBundle = getActionBar();
    paramBundle.setDisplayHomeAsUpEnabled(true);
    paramBundle.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#33b5e5")));
    paramBundle = (ViewGroup)getWindow().getDecorView().findViewById(getResources().getIdentifier("action_bar", "id", "android"));
    try
    {
      Field localField = paramBundle.getClass().getSuperclass().getDeclaredField("mContentHeight");
      localField.setAccessible(true);
      localField.set(paramBundle, Integer.valueOf(120));
      setContentView(2130903124);
      this.EMBED = getIntent().getExtras().getString("embed_code");
      this.PCODE = getIntent().getExtras().getString("pcode");
      paramBundle = (OoyalaPlayerLayout)findViewById(2131558683);
      this.player = new OoyalaPlayer(this.PCODE, new PlayerDomain("http://ooyala.com"));
      this.playerLayoutController = new OoyalaPlayerLayoutController(paramBundle, this.player);
      this.player.addObserver(this);
      if (this.player.setEmbedCode(this.EMBED))
      {
        this.player.play();
        return;
      }
      Log.e(this.TAG, "Asset Failure");
      return;
    }
    catch (IllegalAccessException paramBundle)
    {
      for (;;) {}
    }
    catch (NoSuchFieldException paramBundle)
    {
      for (;;) {}
    }
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (paramMenuItem.getItemId() == 16908332) {
      finish();
    }
    return super.onOptionsItemSelected(paramMenuItem);
  }
  
  protected void onRestart()
  {
    super.onRestart();
    Log.d(this.TAG, "Player Activity Restarted");
    if (this.player != null) {
      this.player.resume();
    }
  }
  
  protected void onStop()
  {
    super.onStop();
    Log.d(this.TAG, "Player Activity Stopped");
    if (this.player != null) {
      this.player.suspend();
    }
  }
  
  public void update(Observable paramObservable, Object paramObject)
  {
    if (paramObservable != this.player) {}
    while (paramObject == "timeChanged") {
      return;
    }
    if (paramObject == "error")
    {
      if ((this.player != null) && (this.player.getError() != null))
      {
        Log.e(this.TAG, "Error Event Received", this.player.getError());
        return;
      }
      Log.e(this.TAG, "Error Event Received");
      return;
    }
    Log.d(this.TAG, "Notification Received: " + paramObject + " - state: " + this.player.getState());
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\example\example75f1799f07eb\BasicPlaybackVideoPlayerActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */