package com.ons.radio;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.ons.musicplayer.MainActivity;

public class ServiceReceiver
  extends BroadcastReceiver
{
  Context mcontext;
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    paramIntent = new MyPhoneStateListener();
    this.mcontext = paramContext;
    ((TelephonyManager)paramContext.getSystemService("phone")).listen(paramIntent, 32);
  }
  
  public class MyPhoneStateListener
    extends PhoneStateListener
  {
    public MyPhoneStateListener() {}
    
    public void onCallStateChanged(int paramInt, String paramString)
    {
      switch (paramInt)
      {
      }
      for (;;)
      {
        return;
        Log.d("DEBUG", "IDLE new ");
        try
        {
          if ((PlayerActivity.audioSignal != null) && (!PlayerActivity.audioSignal.isPlaying()))
          {
            if (PlayerActivity.status == 1) {
              PlayerActivity.audioOncall = false;
            }
            if (PlayerActivity.status == 0) {
              PlayerActivity.audioOncall = true;
            }
            PlayerActivity.audioSignal.play();
            return;
          }
          if ((MainActivity.mp != null) && (!MainActivity.mp.isPlaying()))
          {
            MainActivity.mp.start();
            return;
            Log.d("DEBUG", "OFFHOOK new ");
          }
        }
        catch (Exception paramString)
        {
          try
          {
            if ((PlayerActivity.audioSignal != null) && (PlayerActivity.audioSignal.isPlaying()))
            {
              PlayerActivity.audioSignal.stop();
              return;
            }
            if ((MainActivity.mp == null) || (!MainActivity.mp.isPlaying())) {
              continue;
            }
            MainActivity.mp.pause();
            return;
          }
          catch (Exception paramString)
          {
            return;
          }
          try
          {
            if ((PlayerActivity.audioSignal != null) && (PlayerActivity.audioSignal.isPlaying()))
            {
              PlayerActivity.audioOncall = PlayerActivity.audioSignal.isPlaying();
              PlayerActivity.audioSignal.stop();
              return;
            }
            if ((MainActivity.mp == null) || (!MainActivity.mp.isPlaying())) {
              continue;
            }
            MainActivity.mp.pause();
            return;
          }
          catch (Exception paramString) {}
          paramString = paramString;
          return;
        }
      }
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ons\radio\ServiceReceiver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */