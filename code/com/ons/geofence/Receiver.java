package com.ons.geofence;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.ons.musicplayer.MainActivity;
import com.ons.radio.AudioSignal;
import com.ons.radio.PlayerActivity;
import java.io.PrintStream;

public class Receiver
  extends BroadcastReceiver
{
  Context context;
  
  public boolean isConnected()
  {
    NetworkInfo localNetworkInfo = ((ConnectivityManager)this.context.getSystemService("connectivity")).getActiveNetworkInfo();
    return (localNetworkInfo != null) && (localNetworkInfo.isConnected());
  }
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    String str = paramIntent.getAction();
    this.context = paramContext;
    if ("android.intent.action.BOOT_COMPLETED".equals(paramIntent.getAction()))
    {
      System.out.println("--??????????????" + paramIntent.getAction());
      paramIntent = new Intent(paramContext, MyService.class);
      paramContext.stopService(paramIntent);
      paramContext.startService(paramIntent);
    }
    if (("android.net.conn.CONNECTIVITY_CHANGE".equalsIgnoreCase(str)) || ("android.net.wifi.WIFI_STATE_CHANGED".equalsIgnoreCase(str)))
    {
      if (!isConnected()) {
        break label141;
      }
      if ((PlayerActivity.audioSignal != null) && (!PlayerActivity.audioSignal.isPlaying())) {
        PlayerActivity.audioSignal.play();
      }
      if ((MainActivity.mp != null) && (!MainActivity.mp.isPlaying())) {
        MainActivity.mp.start();
      }
    }
    label141:
    do
    {
      return;
      if ((PlayerActivity.audioSignal != null) && (PlayerActivity.audioSignal.isPlaying())) {
        PlayerActivity.audioSignal.stop();
      }
    } while ((MainActivity.mp == null) || (!MainActivity.mp.isPlaying()));
    MainActivity.mp.pause();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ons\geofence\Receiver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */