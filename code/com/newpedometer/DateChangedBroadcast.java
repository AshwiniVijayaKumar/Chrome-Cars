package com.newpedometer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateChangedBroadcast
  extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    paramIntent = new Pedometer();
    Object localObject = paramContext.getSharedPreferences("state", 0);
    ((SharedPreferences)localObject).edit();
    int i = ((SharedPreferences)localObject).getInt("steps", 0);
    ((SharedPreferences)localObject).getFloat("distance", 0.0F);
    localObject = Calendar.getInstance();
    ((Calendar)localObject).add(5, -1);
    localObject = new SimpleDateFormat("dd/MMM/yyyy").format(((Calendar)localObject).getTime());
    new PedoMeterData(paramContext).insertIntoChatTable((String)localObject, String.valueOf(i));
    paramIntent.resetvaluefromreciever(paramContext, true);
    paramIntent = new Intent();
    paramIntent.setAction("Com.android.reset.value.broadcast");
    paramContext.sendBroadcast(paramIntent);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\newpedometer\DateChangedBroadcast.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */