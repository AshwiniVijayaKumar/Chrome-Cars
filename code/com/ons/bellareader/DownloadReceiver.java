package com.ons.bellareader;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import java.io.PrintStream;

public class DownloadReceiver
  extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    Toast.makeText(paramContext, "??????????????????????????????????????????", 1).show();
    String str = paramIntent.getAction();
    Toast.makeText(paramContext, "action=" + str, 1).show();
    if ("android.intent.action.DOWNLOAD_COMPLETE".equals(str))
    {
      paramIntent = paramIntent.getStringExtra("uri");
      System.out.println(paramIntent);
      Toast.makeText(paramContext, "" + paramIntent, 1).show();
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ons\bellareader\DownloadReceiver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */