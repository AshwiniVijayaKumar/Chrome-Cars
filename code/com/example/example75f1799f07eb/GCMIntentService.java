package com.example.example75f1799f07eb;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.Notification.BigPictureStyle;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.RemoteViews;
import com.google.android.gcm.GCMBaseIntentService;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

public class GCMIntentService
  extends GCMBaseIntentService
{
  private static final String TAG = "GCMIntentService";
  static int count_notification = 0;
  
  public GCMIntentService()
  {
    super(new String[] { "755323621607" });
  }
  
  private static void generateNotification(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, Bitmap paramBitmap)
  {
    count_notification += 1;
    System.out.println("notification count>>>>>" + count_notification);
    System.currentTimeMillis();
    NotificationManager localNotificationManager = (NotificationManager)paramContext.getSystemService("notification");
    MyPhoneGapActivity.lastNotification = paramString1;
    System.out.println("Set Notification Message=" + paramString1);
    RemoteViews localRemoteViews = new RemoteViews(paramContext.getPackageName(), 2130903112);
    String str = paramContext.getString(2131230720);
    Object localObject1 = new Intent("android.intent.action.VIEW");
    ((Intent)localObject1).setData(Uri.parse(paramString3));
    localObject1 = PendingIntent.getActivity(paramContext, 0, (Intent)localObject1, 0);
    Object localObject2 = new Intent(paramContext, MyPhoneGapActivity.class);
    ((Intent)localObject2).putExtra("frompushnotification", "true");
    if ((paramString3.equalsIgnoreCase("noUrl")) && (!paramString4.equalsIgnoreCase("noPageId"))) {
      ((Intent)localObject2).putExtra("SendTo", paramString4);
    }
    ((Intent)localObject2).setFlags(603979776);
    localObject2 = PendingIntent.getActivity(paramContext, 0, (Intent)localObject2, 134217728);
    paramString4 = new Notification.Builder(paramContext);
    paramString4.setSmallIcon(2130837602);
    paramString4.setAutoCancel(true);
    paramString4.setContentTitle(str);
    paramString4.setContentText(paramString1);
    paramString4.setContentIntent((PendingIntent)localObject2);
    if (!paramString3.equals("noUrl"))
    {
      System.out.println("weburl to display>>>>>>>>>>>>" + paramString3);
      paramString4.setContent(localRemoteViews);
      localRemoteViews.setTextViewText(2131558516, Html.fromHtml("<a href='" + paramString3 + "'>" + paramString3 + "</a>"));
      localRemoteViews.setImageViewResource(2131558513, 2130837602);
      localRemoteViews.setTextViewText(2131558514, str);
      localRemoteViews.setTextViewText(2131558515, paramString1);
      if (paramBitmap != null)
      {
        localRemoteViews.setImageViewBitmap(2131558517, paramBitmap);
        paramString4.setSmallIcon(2130837602);
        paramString4.setStyle(new Notification.BigPictureStyle().bigPicture(paramBitmap));
      }
      localRemoteViews.setOnClickPendingIntent(2131558516, (PendingIntent)localObject1);
      if ((paramString2.length() <= 0) || (paramString2.equals("default"))) {
        break label537;
      }
      int i = getId(paramString2.split("\\.")[0], R.raw.class);
      paramString4.setSound(Uri.parse("android.resource://" + paramContext.getPackageName() + "/" + i));
    }
    for (;;)
    {
      MyApplicationName.Notification_Count += 1;
      localNotificationManager.notify(0, paramString4.build());
      return;
      if (paramBitmap == null) {
        break;
      }
      paramString4.setSmallIcon(2130837602);
      paramString4.setStyle(new Notification.BigPictureStyle().bigPicture(paramBitmap));
      break;
      label537:
      paramContext = (AudioManager)paramContext.getSystemService("audio");
      if (paramContext.getRingerMode() == 0) {
        paramString4.setDefaults(4);
      }
      if (paramContext.getRingerMode() == 1) {
        paramString4.setDefaults(2);
      }
      if (paramContext.getRingerMode() == 2) {
        paramString4.setDefaults(1);
      }
    }
  }
  
  public static int getId(String paramString, Class<?> paramClass)
  {
    try
    {
      Field localField = paramClass.getDeclaredField(paramString);
      int i = localField.getInt(localField);
      return i;
    }
    catch (Exception localException)
    {
      throw new RuntimeException("No resource ID found for: " + paramString + " / " + paramClass, localException);
    }
  }
  
  private boolean isAppIsInBackground(Context paramContext)
  {
    Iterator localIterator = ((ActivityManager)paramContext.getSystemService("activity")).getRunningTasks(Integer.MAX_VALUE).iterator();
    while (localIterator.hasNext())
    {
      ActivityManager.RunningTaskInfo localRunningTaskInfo = (ActivityManager.RunningTaskInfo)localIterator.next();
      if (paramContext.getPackageName().equalsIgnoreCase(localRunningTaskInfo.baseActivity.getPackageName())) {
        return true;
      }
    }
    return false;
  }
  
  protected void onDeletedMessages(Context paramContext, int paramInt)
  {
    Log.i("GCMIntentService", "Received deleted messages notification");
  }
  
  public void onError(Context paramContext, String paramString)
  {
    Log.i("GCMIntentService", "Received error: " + paramString);
  }
  
  protected void onMessage(Context paramContext, Intent paramIntent)
  {
    Log.i("GCMIntentService", "Received message");
    String str1 = paramIntent.getExtras().getString("price");
    MyApplicationName.pushmessage = str1;
    paramIntent = paramIntent.getExtras().getString("settings").split("#pushAttr#");
    Log.e("krishna data==", str1);
    String str2 = paramIntent[0];
    Object localObject = paramIntent[1];
    String str3 = paramIntent[2];
    String str4 = paramIntent[3];
    try
    {
      System.out.println("message>>>" + str1);
      System.out.println("alertSound>>>>" + paramIntent[0]);
      System.out.println("imageUrl>>>" + paramIntent[1]);
      System.out.println("webUrl>>>" + paramIntent[2]);
      System.out.println("internalUrl>>>" + paramIntent[3]);
      if (isAppIsInBackground(paramContext))
      {
        if ((localObject == null) || (((String)localObject).equals("noImage")))
        {
          CommonUtilities.displayMessage(paramContext, str1);
          return;
        }
        new sendNotification(paramContext, str2, str1, str3, str4).execute(new String[] { localObject });
        return;
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      return;
    }
    if ((localObject != null) && (!((String)localObject).equals("noImage")))
    {
      new sendNotification(paramContext, str2, str1, str3, str4).execute(new String[] { localObject });
      return;
    }
    generateNotification(paramContext, str1, str2, str3, str4, null);
  }
  
  protected boolean onRecoverableError(Context paramContext, String paramString)
  {
    Log.i("GCMIntentService", "Received recoverable error: " + paramString);
    return super.onRecoverableError(paramContext, paramString);
  }
  
  protected void onRegistered(Context paramContext, String paramString)
  {
    ServerUtilities.register(paramContext, MyPhoneGapActivity.AppId, paramString, "", "");
  }
  
  protected void onUnregistered(Context paramContext, String paramString)
  {
    Log.i("GCMIntentService", "Device unregistered");
    CommonUtilities.displayMessage(paramContext, getString(2131231062));
    ServerUtilities.unregister(paramContext, paramString);
  }
  
  private class sendNotification
    extends AsyncTask<String, Void, Bitmap>
  {
    String alertSound;
    Context ctx;
    String imageUrl;
    String internalUrl;
    String message;
    String webUrl;
    
    public sendNotification(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4)
    {
      System.out.println("message11>>>" + paramString2);
      System.out.println("alertSound11>>>>" + paramString1);
      System.out.println("webUrl11>>>" + paramString3);
      System.out.println("internalUrl11>>>" + paramString4);
      this.ctx = paramContext;
      this.alertSound = paramString1;
      this.message = paramString2;
      this.webUrl = paramString3;
      this.internalUrl = paramString4;
    }
    
    protected Bitmap doInBackground(String... paramVarArgs)
    {
      try
      {
        paramVarArgs = (HttpURLConnection)new URL(paramVarArgs[0]).openConnection();
        paramVarArgs.setDoInput(true);
        paramVarArgs.connect();
        paramVarArgs = BitmapFactory.decodeStream(paramVarArgs.getInputStream());
        return paramVarArgs;
      }
      catch (MalformedURLException paramVarArgs)
      {
        paramVarArgs.printStackTrace();
        return null;
      }
      catch (IOException paramVarArgs)
      {
        for (;;)
        {
          paramVarArgs.printStackTrace();
        }
      }
    }
    
    protected void onPostExecute(Bitmap paramBitmap)
    {
      super.onPostExecute(paramBitmap);
      GCMIntentService.generateNotification(this.ctx, this.message, this.alertSound, this.webUrl, this.internalUrl, paramBitmap);
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\example\example75f1799f07eb\GCMIntentService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */