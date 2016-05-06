package com.ons.geofence;

import android.app.Notification.BigPictureStyle;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.media.AudioManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Process;
import android.text.Html;
import android.util.Log;
import android.widget.RemoteViews;
import com.example.example75f1799f07eb.MyApplicationName;
import com.example.example75f1799f07eb.MyPhoneGapActivity;
import com.example.example75f1799f07eb.R.raw;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MyService
  extends Service
  implements LocationListener
{
  static int count_notification = 0;
  public static int status = 1;
  Calendar c = null;
  SimpleDateFormat date = null;
  String day = "";
  String[] days;
  SharedPreferences.Editor editor = null;
  String fenceMessage = "";
  String fenceMsgImage = "";
  String fenceMsgInternalUrl = "";
  String fenceMsgSound = "";
  String fenceMsgUrl = "";
  String formatteddate = null;
  String formattedtime = null;
  String geofanceBeginTime = "";
  String geofanceEndTime = "";
  GPSTracker gps;
  Handler h = null;
  String indexval = "";
  String latitudeAmrit = "";
  double latitudeAmritesh = 0.0D;
  String longitudeAmrit = "";
  double longitudeAmritesh = 0.0D;
  Polygon polygon = null;
  SharedPreferences pref = null;
  String pushScheduleDate = "";
  String pushScheduleFri = "";
  String pushScheduleMon = "";
  String pushScheduleSat = "";
  String pushScheduleSun = "";
  String pushScheduleThur = "";
  String pushScheduleTue = "";
  String pushScheduleWed = "";
  String pushTimeZone = "";
  Runnable r = null;
  RemoteViews remoteViews;
  int[] statusFence;
  SimpleDateFormat time = null;
  
  private static void generateNotification(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, Bitmap paramBitmap)
  {
    count_notification += 1;
    System.out.println("notification count>>>>>" + count_notification);
    System.currentTimeMillis();
    NotificationManager localNotificationManager = (NotificationManager)paramContext.getSystemService("notification");
    System.out.println("Set Notification Message=" + paramString1);
    RemoteViews localRemoteViews = new RemoteViews(paramContext.getPackageName(), 2130903068);
    String str = paramContext.getString(2131230720);
    Intent localIntent = new Intent(paramContext, MyPhoneGapActivity.class);
    localIntent.putExtra("message", paramString1);
    localIntent.putExtra("webUrl", paramString3);
    localIntent.putExtra("geofance", "geofanceAmritesh");
    localIntent.putExtra("fenceMsgImage", paramString5);
    if (paramString3.trim().length() == 0) {
      localIntent.putExtra("internalUrl", paramString4);
    }
    localIntent.putExtra("frompushnotification", "true");
    localIntent.setFlags(603979776);
    paramString5 = PendingIntent.getActivity(paramContext, 0, localIntent, 134217728);
    paramString4 = new Notification.Builder(paramContext);
    paramString4.setSmallIcon(2130837602);
    paramString4.setAutoCancel(true);
    paramString4.setContentTitle(str);
    paramString4.setContentText(paramString1);
    paramString4.setContentIntent(paramString5);
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
        paramString4.setLargeIcon(paramBitmap);
        paramString4.setSmallIcon(2130837602);
        paramString4.setStyle(new Notification.BigPictureStyle().bigPicture(paramBitmap));
      }
      if ((paramString2.trim().length() <= 0) || (paramString2.equals("default"))) {
        break label560;
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
      paramString4.setLargeIcon(paramBitmap);
      paramString4.setStyle(new Notification.BigPictureStyle().bigPicture(paramBitmap));
      break;
      label560:
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
  
  public void getCurrentLocation(final String paramString, final Context paramContext)
  {
    Log.v("amriteshlatlong", "getCurrentLocation");
    paramString = getApplicationContext().getSharedPreferences("MyPrefs", 0);
    this.statusFence = new int[Integer.parseInt(paramString.getString("datalength", ""))];
    int i = 0;
    while (i < this.statusFence.length)
    {
      this.statusFence[i] = 1;
      i += 1;
    }
    try
    {
      Log.v("amriteshgeogance", "geofanceCounter : before ");
      new Thread()
      {
        /* Error */
        public void run()
        {
          // Byte code:
          //   0: ldc 32
          //   2: ldc 34
          //   4: invokestatic 40	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
          //   7: pop
          //   8: aload_0
          //   9: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   12: invokestatic 46	java/util/Calendar:getInstance	()Ljava/util/Calendar;
          //   15: putfield 50	com/ons/geofence/MyService:c	Ljava/util/Calendar;
          //   18: aload_0
          //   19: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   22: new 52	java/text/SimpleDateFormat
          //   25: dup
          //   26: ldc 54
          //   28: invokespecial 57	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;)V
          //   31: putfield 61	com/ons/geofence/MyService:time	Ljava/text/SimpleDateFormat;
          //   34: aload_0
          //   35: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   38: new 52	java/text/SimpleDateFormat
          //   41: dup
          //   42: ldc 63
          //   44: invokespecial 57	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;)V
          //   47: putfield 66	com/ons/geofence/MyService:date	Ljava/text/SimpleDateFormat;
          //   50: aload_0
          //   51: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   54: aload_0
          //   55: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   58: getfield 61	com/ons/geofence/MyService:time	Ljava/text/SimpleDateFormat;
          //   61: aload_0
          //   62: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   65: getfield 50	com/ons/geofence/MyService:c	Ljava/util/Calendar;
          //   68: invokevirtual 70	java/util/Calendar:getTime	()Ljava/util/Date;
          //   71: invokevirtual 74	java/text/SimpleDateFormat:format	(Ljava/util/Date;)Ljava/lang/String;
          //   74: putfield 78	com/ons/geofence/MyService:formattedtime	Ljava/lang/String;
          //   77: aload_0
          //   78: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   81: aload_0
          //   82: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   85: getfield 66	com/ons/geofence/MyService:date	Ljava/text/SimpleDateFormat;
          //   88: aload_0
          //   89: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   92: getfield 50	com/ons/geofence/MyService:c	Ljava/util/Calendar;
          //   95: invokevirtual 70	java/util/Calendar:getTime	()Ljava/util/Date;
          //   98: invokevirtual 74	java/text/SimpleDateFormat:format	(Ljava/util/Date;)Ljava/lang/String;
          //   101: putfield 81	com/ons/geofence/MyService:formatteddate	Ljava/lang/String;
          //   104: ldc 32
          //   106: ldc 83
          //   108: invokestatic 40	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
          //   111: pop
          //   112: aload_0
          //   113: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   116: getfield 61	com/ons/geofence/MyService:time	Ljava/text/SimpleDateFormat;
          //   119: aload_0
          //   120: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   123: getfield 78	com/ons/geofence/MyService:formattedtime	Ljava/lang/String;
          //   126: invokevirtual 87	java/text/SimpleDateFormat:parse	(Ljava/lang/String;)Ljava/util/Date;
          //   129: astore_2
          //   130: aload_0
          //   131: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   134: getfield 66	com/ons/geofence/MyService:date	Ljava/text/SimpleDateFormat;
          //   137: aload_0
          //   138: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   141: getfield 81	com/ons/geofence/MyService:formatteddate	Ljava/lang/String;
          //   144: invokevirtual 87	java/text/SimpleDateFormat:parse	(Ljava/lang/String;)Ljava/util/Date;
          //   147: astore_3
          //   148: aload_0
          //   149: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   152: getfield 66	com/ons/geofence/MyService:date	Ljava/text/SimpleDateFormat;
          //   155: ldc 89
          //   157: invokevirtual 87	java/text/SimpleDateFormat:parse	(Ljava/lang/String;)Ljava/util/Date;
          //   160: pop
          //   161: ldc 32
          //   163: ldc 91
          //   165: invokestatic 40	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
          //   168: pop
          //   169: aload_0
          //   170: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   173: ldc 93
          //   175: aload_3
          //   176: invokestatic 98	android/text/format/DateFormat:format	(Ljava/lang/CharSequence;Ljava/util/Date;)Ljava/lang/CharSequence;
          //   179: checkcast 100	java/lang/String
          //   182: putfield 103	com/ons/geofence/MyService:day	Ljava/lang/String;
          //   185: ldc 32
          //   187: new 105	java/lang/StringBuilder
          //   190: dup
          //   191: invokespecial 106	java/lang/StringBuilder:<init>	()V
          //   194: ldc 108
          //   196: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   199: aload_0
          //   200: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   203: getfield 103	com/ons/geofence/MyService:day	Ljava/lang/String;
          //   206: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   209: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   212: invokestatic 40	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
          //   215: pop
          //   216: ldc 32
          //   218: new 105	java/lang/StringBuilder
          //   221: dup
          //   222: invokespecial 106	java/lang/StringBuilder:<init>	()V
          //   225: ldc 118
          //   227: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   230: aload_2
          //   231: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
          //   234: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   237: invokestatic 40	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
          //   240: pop
          //   241: ldc 32
          //   243: new 105	java/lang/StringBuilder
          //   246: dup
          //   247: invokespecial 106	java/lang/StringBuilder:<init>	()V
          //   250: ldc 123
          //   252: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   255: aload_3
          //   256: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
          //   259: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   262: invokestatic 40	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
          //   265: pop
          //   266: aload_0
          //   267: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   270: aload_0
          //   271: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   274: invokevirtual 127	com/ons/geofence/MyService:getApplicationContext	()Landroid/content/Context;
          //   277: ldc -127
          //   279: iconst_0
          //   280: invokevirtual 135	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
          //   283: putfield 138	com/ons/geofence/MyService:pref	Landroid/content/SharedPreferences;
          //   286: aload_0
          //   287: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   290: getfield 138	com/ons/geofence/MyService:pref	Landroid/content/SharedPreferences;
          //   293: ldc -117
          //   295: ldc -115
          //   297: invokeinterface 147 3 0
          //   302: ldc -115
          //   304: if_acmpne +52 -> 356
          //   307: aload_0
          //   308: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   311: aload_0
          //   312: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   315: getfield 138	com/ons/geofence/MyService:pref	Landroid/content/SharedPreferences;
          //   318: invokeinterface 151 1 0
          //   323: putfield 155	com/ons/geofence/MyService:editor	Landroid/content/SharedPreferences$Editor;
          //   326: aload_0
          //   327: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   330: getfield 155	com/ons/geofence/MyService:editor	Landroid/content/SharedPreferences$Editor;
          //   333: ldc -117
          //   335: ldc 89
          //   337: invokeinterface 161 3 0
          //   342: pop
          //   343: aload_0
          //   344: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   347: getfield 155	com/ons/geofence/MyService:editor	Landroid/content/SharedPreferences$Editor;
          //   350: invokeinterface 165 1 0
          //   355: pop
          //   356: aload_0
          //   357: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   360: getfield 138	com/ons/geofence/MyService:pref	Landroid/content/SharedPreferences;
          //   363: ldc -117
          //   365: ldc -115
          //   367: invokeinterface 147 3 0
          //   372: astore 4
          //   374: ldc 32
          //   376: new 105	java/lang/StringBuilder
          //   379: dup
          //   380: invokespecial 106	java/lang/StringBuilder:<init>	()V
          //   383: ldc -89
          //   385: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   388: aload 4
          //   390: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   393: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   396: invokestatic 40	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
          //   399: pop
          //   400: new 169	org/json/JSONArray
          //   403: dup
          //   404: aload_0
          //   405: getfield 21	com/ons/geofence/MyService$2:val$mySharedPreferences	Landroid/content/SharedPreferences;
          //   408: ldc -85
          //   410: ldc -115
          //   412: invokeinterface 147 3 0
          //   417: invokespecial 172	org/json/JSONArray:<init>	(Ljava/lang/String;)V
          //   420: astore 5
          //   422: iconst_0
          //   423: istore_1
          //   424: iload_1
          //   425: aload 5
          //   427: invokevirtual 176	org/json/JSONArray:length	()I
          //   430: if_icmpge +1203 -> 1633
          //   433: invokestatic 182	com/ons/geofence/Polygon:Builder	()Lcom/ons/geofence/Polygon$Builder;
          //   436: astore 6
          //   438: aload 6
          //   440: new 184	com/ons/geofence/Point
          //   443: dup
          //   444: aload 5
          //   446: iload_1
          //   447: invokevirtual 188	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
          //   450: ldc -66
          //   452: invokevirtual 195	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
          //   455: invokestatic 201	java/lang/Double:parseDouble	(Ljava/lang/String;)D
          //   458: aload 5
          //   460: iload_1
          //   461: invokevirtual 188	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
          //   464: ldc -53
          //   466: invokevirtual 195	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
          //   469: invokestatic 201	java/lang/Double:parseDouble	(Ljava/lang/String;)D
          //   472: invokespecial 206	com/ons/geofence/Point:<init>	(DD)V
          //   475: invokevirtual 212	com/ons/geofence/Polygon$Builder:addVertex	(Lcom/ons/geofence/Point;)Lcom/ons/geofence/Polygon$Builder;
          //   478: pop
          //   479: aload 6
          //   481: new 184	com/ons/geofence/Point
          //   484: dup
          //   485: aload 5
          //   487: iload_1
          //   488: invokevirtual 188	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
          //   491: ldc -42
          //   493: invokevirtual 195	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
          //   496: invokestatic 201	java/lang/Double:parseDouble	(Ljava/lang/String;)D
          //   499: aload 5
          //   501: iload_1
          //   502: invokevirtual 188	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
          //   505: ldc -40
          //   507: invokevirtual 195	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
          //   510: invokestatic 201	java/lang/Double:parseDouble	(Ljava/lang/String;)D
          //   513: invokespecial 206	com/ons/geofence/Point:<init>	(DD)V
          //   516: invokevirtual 212	com/ons/geofence/Polygon$Builder:addVertex	(Lcom/ons/geofence/Point;)Lcom/ons/geofence/Polygon$Builder;
          //   519: pop
          //   520: aload 6
          //   522: new 184	com/ons/geofence/Point
          //   525: dup
          //   526: aload 5
          //   528: iload_1
          //   529: invokevirtual 188	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
          //   532: ldc -38
          //   534: invokevirtual 195	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
          //   537: invokestatic 201	java/lang/Double:parseDouble	(Ljava/lang/String;)D
          //   540: aload 5
          //   542: iload_1
          //   543: invokevirtual 188	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
          //   546: ldc -36
          //   548: invokevirtual 195	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
          //   551: invokestatic 201	java/lang/Double:parseDouble	(Ljava/lang/String;)D
          //   554: invokespecial 206	com/ons/geofence/Point:<init>	(DD)V
          //   557: invokevirtual 212	com/ons/geofence/Polygon$Builder:addVertex	(Lcom/ons/geofence/Point;)Lcom/ons/geofence/Polygon$Builder;
          //   560: pop
          //   561: aload_0
          //   562: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   565: aload 6
          //   567: invokevirtual 224	com/ons/geofence/Polygon$Builder:build	()Lcom/ons/geofence/Polygon;
          //   570: putfield 228	com/ons/geofence/MyService:polygon	Lcom/ons/geofence/Polygon;
          //   573: aload_0
          //   574: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   577: aload 5
          //   579: iload_1
          //   580: invokevirtual 188	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
          //   583: ldc -26
          //   585: invokevirtual 195	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
          //   588: putfield 232	com/ons/geofence/MyService:fenceMessage	Ljava/lang/String;
          //   591: aload_0
          //   592: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   595: aload 5
          //   597: iload_1
          //   598: invokevirtual 188	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
          //   601: ldc -22
          //   603: invokevirtual 195	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
          //   606: putfield 236	com/ons/geofence/MyService:fenceMsgUrl	Ljava/lang/String;
          //   609: aload_0
          //   610: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   613: aload 5
          //   615: iload_1
          //   616: invokevirtual 188	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
          //   619: ldc -18
          //   621: invokevirtual 195	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
          //   624: putfield 240	com/ons/geofence/MyService:fenceMsgInternalUrl	Ljava/lang/String;
          //   627: aload_0
          //   628: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   631: aload 5
          //   633: iload_1
          //   634: invokevirtual 188	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
          //   637: ldc -14
          //   639: invokevirtual 195	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
          //   642: putfield 244	com/ons/geofence/MyService:fenceMsgImage	Ljava/lang/String;
          //   645: aload_0
          //   646: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   649: aload 5
          //   651: iload_1
          //   652: invokevirtual 188	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
          //   655: ldc -10
          //   657: invokevirtual 195	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
          //   660: putfield 248	com/ons/geofence/MyService:fenceMsgSound	Ljava/lang/String;
          //   663: aload_0
          //   664: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   667: aload 5
          //   669: iload_1
          //   670: invokevirtual 188	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
          //   673: ldc -6
          //   675: invokevirtual 195	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
          //   678: putfield 252	com/ons/geofence/MyService:pushTimeZone	Ljava/lang/String;
          //   681: aload_0
          //   682: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   685: aload 5
          //   687: iload_1
          //   688: invokevirtual 188	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
          //   691: ldc -2
          //   693: invokevirtual 195	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
          //   696: putfield 256	com/ons/geofence/MyService:pushScheduleDate	Ljava/lang/String;
          //   699: aload_0
          //   700: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   703: aload 5
          //   705: iload_1
          //   706: invokevirtual 188	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
          //   709: ldc_w 258
          //   712: invokevirtual 195	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
          //   715: putfield 260	com/ons/geofence/MyService:pushScheduleMon	Ljava/lang/String;
          //   718: aload_0
          //   719: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   722: aload 5
          //   724: iload_1
          //   725: invokevirtual 188	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
          //   728: ldc_w 262
          //   731: invokevirtual 195	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
          //   734: putfield 264	com/ons/geofence/MyService:pushScheduleTue	Ljava/lang/String;
          //   737: aload_0
          //   738: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   741: aload 5
          //   743: iload_1
          //   744: invokevirtual 188	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
          //   747: ldc_w 266
          //   750: invokevirtual 195	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
          //   753: putfield 268	com/ons/geofence/MyService:pushScheduleWed	Ljava/lang/String;
          //   756: aload_0
          //   757: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   760: aload 5
          //   762: iload_1
          //   763: invokevirtual 188	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
          //   766: ldc_w 270
          //   769: invokevirtual 195	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
          //   772: putfield 272	com/ons/geofence/MyService:pushScheduleThur	Ljava/lang/String;
          //   775: aload_0
          //   776: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   779: aload 5
          //   781: iload_1
          //   782: invokevirtual 188	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
          //   785: ldc_w 274
          //   788: invokevirtual 195	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
          //   791: putfield 276	com/ons/geofence/MyService:pushScheduleFri	Ljava/lang/String;
          //   794: aload_0
          //   795: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   798: aload 5
          //   800: iload_1
          //   801: invokevirtual 188	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
          //   804: ldc_w 278
          //   807: invokevirtual 195	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
          //   810: putfield 280	com/ons/geofence/MyService:pushScheduleSat	Ljava/lang/String;
          //   813: aload_0
          //   814: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   817: aload 5
          //   819: iload_1
          //   820: invokevirtual 188	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
          //   823: ldc_w 282
          //   826: invokevirtual 195	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
          //   829: putfield 284	com/ons/geofence/MyService:pushScheduleSun	Ljava/lang/String;
          //   832: aload_0
          //   833: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   836: getfield 228	com/ons/geofence/MyService:polygon	Lcom/ons/geofence/Polygon;
          //   839: new 184	com/ons/geofence/Point
          //   842: dup
          //   843: aload_0
          //   844: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   847: getfield 288	com/ons/geofence/MyService:latitudeAmritesh	D
          //   850: aload_0
          //   851: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   854: getfield 291	com/ons/geofence/MyService:longitudeAmritesh	D
          //   857: invokespecial 206	com/ons/geofence/Point:<init>	(DD)V
          //   860: invokevirtual 295	com/ons/geofence/Polygon:contains	(Lcom/ons/geofence/Point;)Z
          //   863: iconst_1
          //   864: if_icmpne +4918 -> 5782
          //   867: ldc_w 297
          //   870: ldc_w 299
          //   873: invokestatic 40	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
          //   876: pop
          //   877: ldc_w 297
          //   880: new 105	java/lang/StringBuilder
          //   883: dup
          //   884: invokespecial 106	java/lang/StringBuilder:<init>	()V
          //   887: ldc -115
          //   889: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   892: aload_0
          //   893: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   896: getfield 232	com/ons/geofence/MyService:fenceMessage	Ljava/lang/String;
          //   899: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   902: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   905: invokestatic 40	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
          //   908: pop
          //   909: ldc_w 297
          //   912: new 105	java/lang/StringBuilder
          //   915: dup
          //   916: invokespecial 106	java/lang/StringBuilder:<init>	()V
          //   919: ldc_w 301
          //   922: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   925: aload 5
          //   927: iload_1
          //   928: invokevirtual 188	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
          //   931: ldc -26
          //   933: invokevirtual 195	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
          //   936: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   939: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   942: invokestatic 40	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
          //   945: pop
          //   946: aload_0
          //   947: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   950: getfield 305	com/ons/geofence/MyService:statusFence	[I
          //   953: iload_1
          //   954: iaload
          //   955: iconst_1
          //   956: if_icmpne +27 -> 983
          //   959: aload_0
          //   960: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   963: aload_0
          //   964: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   967: getfield 138	com/ons/geofence/MyService:pref	Landroid/content/SharedPreferences;
          //   970: ldc_w 307
          //   973: ldc -115
          //   975: invokeinterface 147 3 0
          //   980: putfield 310	com/ons/geofence/MyService:indexval	Ljava/lang/String;
          //   983: ldc_w 312
          //   986: aload_0
          //   987: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   990: getfield 310	com/ons/geofence/MyService:indexval	Ljava/lang/String;
          //   993: invokestatic 40	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
          //   996: pop
          //   997: aload 4
          //   999: aload_0
          //   1000: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   1003: getfield 81	com/ons/geofence/MyService:formatteddate	Ljava/lang/String;
          //   1006: invokevirtual 316	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
          //   1009: ifeq +69 -> 1078
          //   1012: aload_0
          //   1013: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   1016: getfield 310	com/ons/geofence/MyService:indexval	Ljava/lang/String;
          //   1019: new 105	java/lang/StringBuilder
          //   1022: dup
          //   1023: invokespecial 106	java/lang/StringBuilder:<init>	()V
          //   1026: ldc -115
          //   1028: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   1031: iload_1
          //   1032: invokevirtual 319	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
          //   1035: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   1038: invokevirtual 316	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
          //   1041: ifeq +37 -> 1078
          //   1044: ldc 32
          //   1046: new 105	java/lang/StringBuilder
          //   1049: dup
          //   1050: invokespecial 106	java/lang/StringBuilder:<init>	()V
          //   1053: ldc_w 321
          //   1056: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   1059: aload 4
          //   1061: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   1064: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   1067: invokestatic 40	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
          //   1070: pop
          //   1071: iload_1
          //   1072: iconst_1
          //   1073: iadd
          //   1074: istore_1
          //   1075: goto -651 -> 424
          //   1078: ldc 32
          //   1080: new 105	java/lang/StringBuilder
          //   1083: dup
          //   1084: invokespecial 106	java/lang/StringBuilder:<init>	()V
          //   1087: ldc_w 323
          //   1090: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   1093: aload 4
          //   1095: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   1098: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   1101: invokestatic 40	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
          //   1104: pop
          //   1105: aload_0
          //   1106: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   1109: aload_0
          //   1110: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   1113: getfield 138	com/ons/geofence/MyService:pref	Landroid/content/SharedPreferences;
          //   1116: invokeinterface 151 1 0
          //   1121: putfield 155	com/ons/geofence/MyService:editor	Landroid/content/SharedPreferences$Editor;
          //   1124: aload_0
          //   1125: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   1128: getfield 155	com/ons/geofence/MyService:editor	Landroid/content/SharedPreferences$Editor;
          //   1131: ldc -117
          //   1133: invokeinterface 327 2 0
          //   1138: pop
          //   1139: aload_0
          //   1140: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   1143: getfield 155	com/ons/geofence/MyService:editor	Landroid/content/SharedPreferences$Editor;
          //   1146: ldc -117
          //   1148: aload_0
          //   1149: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   1152: getfield 81	com/ons/geofence/MyService:formatteddate	Ljava/lang/String;
          //   1155: invokeinterface 161 3 0
          //   1160: pop
          //   1161: aload_0
          //   1162: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   1165: getfield 155	com/ons/geofence/MyService:editor	Landroid/content/SharedPreferences$Editor;
          //   1168: invokeinterface 165 1 0
          //   1173: pop
          //   1174: ldc 32
          //   1176: new 105	java/lang/StringBuilder
          //   1179: dup
          //   1180: invokespecial 106	java/lang/StringBuilder:<init>	()V
          //   1183: ldc_w 323
          //   1186: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   1189: aload 4
          //   1191: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   1194: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   1197: invokestatic 40	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
          //   1200: pop
          //   1201: aload_0
          //   1202: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   1205: getfield 256	com/ons/geofence/MyService:pushScheduleDate	Ljava/lang/String;
          //   1208: ifnull +482 -> 1690
          //   1211: aload_0
          //   1212: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   1215: getfield 256	com/ons/geofence/MyService:pushScheduleDate	Ljava/lang/String;
          //   1218: ldc -115
          //   1220: if_acmpeq +470 -> 1690
          //   1223: aload_0
          //   1224: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   1227: getfield 256	com/ons/geofence/MyService:pushScheduleDate	Ljava/lang/String;
          //   1230: invokevirtual 330	java/lang/String:trim	()Ljava/lang/String;
          //   1233: invokevirtual 331	java/lang/String:length	()I
          //   1236: ifle +454 -> 1690
          //   1239: ldc 32
          //   1241: new 105	java/lang/StringBuilder
          //   1244: dup
          //   1245: invokespecial 106	java/lang/StringBuilder:<init>	()V
          //   1248: ldc_w 333
          //   1251: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   1254: aload_0
          //   1255: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   1258: getfield 256	com/ons/geofence/MyService:pushScheduleDate	Ljava/lang/String;
          //   1261: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   1264: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   1267: invokestatic 40	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
          //   1270: pop
          //   1271: ldc_w 334
          //   1274: new 105	java/lang/StringBuilder
          //   1277: dup
          //   1278: invokespecial 106	java/lang/StringBuilder:<init>	()V
          //   1281: ldc_w 333
          //   1284: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   1287: aload_0
          //   1288: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   1291: getfield 256	com/ons/geofence/MyService:pushScheduleDate	Ljava/lang/String;
          //   1294: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   1297: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   1300: invokestatic 40	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
          //   1303: pop
          //   1304: aload_0
          //   1305: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   1308: getfield 256	com/ons/geofence/MyService:pushScheduleDate	Ljava/lang/String;
          //   1311: ldc_w 336
          //   1314: invokevirtual 340	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
          //   1317: iconst_0
          //   1318: aaload
          //   1319: astore 7
          //   1321: aload_0
          //   1322: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   1325: invokestatic 46	java/util/Calendar:getInstance	()Ljava/util/Calendar;
          //   1328: putfield 50	com/ons/geofence/MyService:c	Ljava/util/Calendar;
          //   1331: aload_0
          //   1332: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   1335: new 52	java/text/SimpleDateFormat
          //   1338: dup
          //   1339: ldc 63
          //   1341: invokespecial 57	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;)V
          //   1344: putfield 66	com/ons/geofence/MyService:date	Ljava/text/SimpleDateFormat;
          //   1347: aload_0
          //   1348: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   1351: getfield 66	com/ons/geofence/MyService:date	Ljava/text/SimpleDateFormat;
          //   1354: aload 7
          //   1356: invokevirtual 87	java/text/SimpleDateFormat:parse	(Ljava/lang/String;)Ljava/util/Date;
          //   1359: astore 6
          //   1361: ldc_w 334
          //   1364: new 105	java/lang/StringBuilder
          //   1367: dup
          //   1368: invokespecial 106	java/lang/StringBuilder:<init>	()V
          //   1371: ldc_w 342
          //   1374: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   1377: aload 7
          //   1379: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   1382: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   1385: invokestatic 40	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
          //   1388: pop
          //   1389: ldc_w 334
          //   1392: new 105	java/lang/StringBuilder
          //   1395: dup
          //   1396: invokespecial 106	java/lang/StringBuilder:<init>	()V
          //   1399: ldc_w 344
          //   1402: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   1405: aload 6
          //   1407: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
          //   1410: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   1413: invokestatic 40	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
          //   1416: pop
          //   1417: aload_0
          //   1418: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   1421: aload_0
          //   1422: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   1425: getfield 138	com/ons/geofence/MyService:pref	Landroid/content/SharedPreferences;
          //   1428: invokeinterface 151 1 0
          //   1433: putfield 155	com/ons/geofence/MyService:editor	Landroid/content/SharedPreferences$Editor;
          //   1436: aload_0
          //   1437: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   1440: getfield 155	com/ons/geofence/MyService:editor	Landroid/content/SharedPreferences$Editor;
          //   1443: ldc_w 307
          //   1446: new 105	java/lang/StringBuilder
          //   1449: dup
          //   1450: invokespecial 106	java/lang/StringBuilder:<init>	()V
          //   1453: ldc -115
          //   1455: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   1458: iload_1
          //   1459: invokevirtual 319	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
          //   1462: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   1465: invokeinterface 161 3 0
          //   1470: pop
          //   1471: aload_0
          //   1472: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   1475: getfield 155	com/ons/geofence/MyService:editor	Landroid/content/SharedPreferences$Editor;
          //   1478: invokeinterface 165 1 0
          //   1483: pop
          //   1484: aload_3
          //   1485: aload 6
          //   1487: invokevirtual 350	java/util/Date:after	(Ljava/util/Date;)Z
          //   1490: ifne -419 -> 1071
          //   1493: aload_3
          //   1494: aload 6
          //   1496: invokevirtual 353	java/util/Date:before	(Ljava/util/Date;)Z
          //   1499: ifne -428 -> 1071
          //   1502: ldc 32
          //   1504: ldc_w 355
          //   1507: invokestatic 40	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
          //   1510: pop
          //   1511: aload_0
          //   1512: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   1515: getfield 244	com/ons/geofence/MyService:fenceMsgImage	Ljava/lang/String;
          //   1518: ifnull +126 -> 1644
          //   1521: aload_0
          //   1522: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   1525: getfield 244	com/ons/geofence/MyService:fenceMsgImage	Ljava/lang/String;
          //   1528: ldc_w 357
          //   1531: invokevirtual 361	java/lang/String:equals	(Ljava/lang/Object;)Z
          //   1534: ifne +110 -> 1644
          //   1537: new 363	com/ons/geofence/MyService$sendNotification
          //   1540: dup
          //   1541: aload_0
          //   1542: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   1545: aload_0
          //   1546: getfield 23	com/ons/geofence/MyService$2:val$con	Landroid/content/Context;
          //   1549: aload_0
          //   1550: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   1553: getfield 248	com/ons/geofence/MyService:fenceMsgSound	Ljava/lang/String;
          //   1556: aload_0
          //   1557: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   1560: getfield 232	com/ons/geofence/MyService:fenceMessage	Ljava/lang/String;
          //   1563: aload_0
          //   1564: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   1567: getfield 236	com/ons/geofence/MyService:fenceMsgUrl	Ljava/lang/String;
          //   1570: aload_0
          //   1571: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   1574: getfield 240	com/ons/geofence/MyService:fenceMsgInternalUrl	Ljava/lang/String;
          //   1577: aload_0
          //   1578: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   1581: getfield 244	com/ons/geofence/MyService:fenceMsgImage	Ljava/lang/String;
          //   1584: invokespecial 366	com/ons/geofence/MyService$sendNotification:<init>	(Lcom/ons/geofence/MyService;Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
          //   1587: iconst_1
          //   1588: anewarray 100	java/lang/String
          //   1591: dup
          //   1592: iconst_0
          //   1593: aload_0
          //   1594: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   1597: getfield 244	com/ons/geofence/MyService:fenceMsgImage	Ljava/lang/String;
          //   1600: aastore
          //   1601: invokevirtual 370	com/ons/geofence/MyService$sendNotification:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
          //   1604: pop
          //   1605: goto -534 -> 1071
          //   1608: astore 6
          //   1610: aload 6
          //   1612: invokevirtual 373	java/lang/Exception:printStackTrace	()V
          //   1615: goto -544 -> 1071
          //   1618: astore 6
          //   1620: aload 6
          //   1622: invokevirtual 373	java/lang/Exception:printStackTrace	()V
          //   1625: goto -554 -> 1071
          //   1628: astore_2
          //   1629: aload_2
          //   1630: invokevirtual 373	java/lang/Exception:printStackTrace	()V
          //   1633: return
          //   1634: astore 7
          //   1636: aload 7
          //   1638: invokevirtual 373	java/lang/Exception:printStackTrace	()V
          //   1641: goto -157 -> 1484
          //   1644: aload_0
          //   1645: getfield 23	com/ons/geofence/MyService$2:val$con	Landroid/content/Context;
          //   1648: aload_0
          //   1649: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   1652: getfield 232	com/ons/geofence/MyService:fenceMessage	Ljava/lang/String;
          //   1655: aload_0
          //   1656: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   1659: getfield 248	com/ons/geofence/MyService:fenceMsgSound	Ljava/lang/String;
          //   1662: aload_0
          //   1663: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   1666: getfield 236	com/ons/geofence/MyService:fenceMsgUrl	Ljava/lang/String;
          //   1669: aload_0
          //   1670: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   1673: getfield 240	com/ons/geofence/MyService:fenceMsgInternalUrl	Ljava/lang/String;
          //   1676: aload_0
          //   1677: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   1680: getfield 244	com/ons/geofence/MyService:fenceMsgImage	Ljava/lang/String;
          //   1683: aconst_null
          //   1684: invokestatic 377	com/ons/geofence/MyService:access$000	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Landroid/graphics/Bitmap;)V
          //   1687: goto -616 -> 1071
          //   1690: aload_0
          //   1691: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   1694: getfield 103	com/ons/geofence/MyService:day	Ljava/lang/String;
          //   1697: ldc_w 379
          //   1700: invokevirtual 316	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
          //   1703: ifeq +536 -> 2239
          //   1706: aload_0
          //   1707: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   1710: getfield 260	com/ons/geofence/MyService:pushScheduleMon	Ljava/lang/String;
          //   1713: ldc -115
          //   1715: if_acmpeq +524 -> 2239
          //   1718: aload_0
          //   1719: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   1722: getfield 260	com/ons/geofence/MyService:pushScheduleMon	Ljava/lang/String;
          //   1725: invokevirtual 330	java/lang/String:trim	()Ljava/lang/String;
          //   1728: invokevirtual 331	java/lang/String:length	()I
          //   1731: ifle +508 -> 2239
          //   1734: ldc 32
          //   1736: ldc_w 381
          //   1739: invokestatic 40	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
          //   1742: pop
          //   1743: aload_0
          //   1744: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   1747: getfield 260	com/ons/geofence/MyService:pushScheduleMon	Ljava/lang/String;
          //   1750: ldc_w 383
          //   1753: invokevirtual 386	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
          //   1756: ifeq -685 -> 1071
          //   1759: aload_0
          //   1760: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   1763: invokestatic 46	java/util/Calendar:getInstance	()Ljava/util/Calendar;
          //   1766: putfield 50	com/ons/geofence/MyService:c	Ljava/util/Calendar;
          //   1769: aload_0
          //   1770: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   1773: new 52	java/text/SimpleDateFormat
          //   1776: dup
          //   1777: ldc 54
          //   1779: invokespecial 57	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;)V
          //   1782: putfield 61	com/ons/geofence/MyService:time	Ljava/text/SimpleDateFormat;
          //   1785: aload_0
          //   1786: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   1789: getfield 260	com/ons/geofence/MyService:pushScheduleMon	Ljava/lang/String;
          //   1792: ldc_w 383
          //   1795: invokevirtual 340	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
          //   1798: astore 6
          //   1800: aload_0
          //   1801: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   1804: aload 6
          //   1806: iconst_0
          //   1807: aaload
          //   1808: putfield 389	com/ons/geofence/MyService:geofanceBeginTime	Ljava/lang/String;
          //   1811: aload_0
          //   1812: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   1815: aload 6
          //   1817: iconst_1
          //   1818: aaload
          //   1819: putfield 392	com/ons/geofence/MyService:geofanceEndTime	Ljava/lang/String;
          //   1822: ldc_w 334
          //   1825: new 105	java/lang/StringBuilder
          //   1828: dup
          //   1829: invokespecial 106	java/lang/StringBuilder:<init>	()V
          //   1832: ldc_w 394
          //   1835: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   1838: aload_0
          //   1839: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   1842: getfield 389	com/ons/geofence/MyService:geofanceBeginTime	Ljava/lang/String;
          //   1845: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   1848: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   1851: invokestatic 40	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
          //   1854: pop
          //   1855: ldc_w 334
          //   1858: new 105	java/lang/StringBuilder
          //   1861: dup
          //   1862: invokespecial 106	java/lang/StringBuilder:<init>	()V
          //   1865: ldc_w 396
          //   1868: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   1871: aload_0
          //   1872: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   1875: getfield 392	com/ons/geofence/MyService:geofanceEndTime	Ljava/lang/String;
          //   1878: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   1881: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   1884: invokestatic 40	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
          //   1887: pop
          //   1888: aload_0
          //   1889: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   1892: getfield 61	com/ons/geofence/MyService:time	Ljava/text/SimpleDateFormat;
          //   1895: aload_0
          //   1896: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   1899: getfield 389	com/ons/geofence/MyService:geofanceBeginTime	Ljava/lang/String;
          //   1902: invokevirtual 87	java/text/SimpleDateFormat:parse	(Ljava/lang/String;)Ljava/util/Date;
          //   1905: astore 6
          //   1907: aload_0
          //   1908: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   1911: getfield 61	com/ons/geofence/MyService:time	Ljava/text/SimpleDateFormat;
          //   1914: aload_0
          //   1915: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   1918: getfield 392	com/ons/geofence/MyService:geofanceEndTime	Ljava/lang/String;
          //   1921: invokevirtual 87	java/text/SimpleDateFormat:parse	(Ljava/lang/String;)Ljava/util/Date;
          //   1924: astore 7
          //   1926: ldc_w 334
          //   1929: new 105	java/lang/StringBuilder
          //   1932: dup
          //   1933: invokespecial 106	java/lang/StringBuilder:<init>	()V
          //   1936: ldc_w 398
          //   1939: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   1942: aload 6
          //   1944: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
          //   1947: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   1950: invokestatic 40	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
          //   1953: pop
          //   1954: ldc_w 334
          //   1957: new 105	java/lang/StringBuilder
          //   1960: dup
          //   1961: invokespecial 106	java/lang/StringBuilder:<init>	()V
          //   1964: ldc_w 400
          //   1967: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   1970: aload 7
          //   1972: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
          //   1975: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   1978: invokestatic 40	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
          //   1981: pop
          //   1982: aload_0
          //   1983: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   1986: aload_0
          //   1987: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   1990: getfield 138	com/ons/geofence/MyService:pref	Landroid/content/SharedPreferences;
          //   1993: invokeinterface 151 1 0
          //   1998: putfield 155	com/ons/geofence/MyService:editor	Landroid/content/SharedPreferences$Editor;
          //   2001: aload_0
          //   2002: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   2005: getfield 155	com/ons/geofence/MyService:editor	Landroid/content/SharedPreferences$Editor;
          //   2008: ldc_w 307
          //   2011: new 105	java/lang/StringBuilder
          //   2014: dup
          //   2015: invokespecial 106	java/lang/StringBuilder:<init>	()V
          //   2018: ldc -115
          //   2020: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   2023: iload_1
          //   2024: invokevirtual 319	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
          //   2027: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   2030: invokeinterface 161 3 0
          //   2035: pop
          //   2036: aload_0
          //   2037: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   2040: getfield 155	com/ons/geofence/MyService:editor	Landroid/content/SharedPreferences$Editor;
          //   2043: invokeinterface 165 1 0
          //   2048: pop
          //   2049: aload_2
          //   2050: aload 6
          //   2052: invokevirtual 350	java/util/Date:after	(Ljava/util/Date;)Z
          //   2055: ifeq -984 -> 1071
          //   2058: aload_2
          //   2059: aload 7
          //   2061: invokevirtual 353	java/util/Date:before	(Ljava/util/Date;)Z
          //   2064: ifeq -993 -> 1071
          //   2067: ldc 32
          //   2069: ldc_w 402
          //   2072: invokestatic 40	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
          //   2075: pop
          //   2076: aload_0
          //   2077: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   2080: getfield 244	com/ons/geofence/MyService:fenceMsgImage	Ljava/lang/String;
          //   2083: ifnull +110 -> 2193
          //   2086: aload_0
          //   2087: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   2090: getfield 244	com/ons/geofence/MyService:fenceMsgImage	Ljava/lang/String;
          //   2093: ldc_w 357
          //   2096: invokevirtual 361	java/lang/String:equals	(Ljava/lang/Object;)Z
          //   2099: ifne +94 -> 2193
          //   2102: new 363	com/ons/geofence/MyService$sendNotification
          //   2105: dup
          //   2106: aload_0
          //   2107: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   2110: aload_0
          //   2111: getfield 23	com/ons/geofence/MyService$2:val$con	Landroid/content/Context;
          //   2114: aload_0
          //   2115: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   2118: getfield 248	com/ons/geofence/MyService:fenceMsgSound	Ljava/lang/String;
          //   2121: aload_0
          //   2122: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   2125: getfield 232	com/ons/geofence/MyService:fenceMessage	Ljava/lang/String;
          //   2128: aload_0
          //   2129: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   2132: getfield 236	com/ons/geofence/MyService:fenceMsgUrl	Ljava/lang/String;
          //   2135: aload_0
          //   2136: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   2139: getfield 240	com/ons/geofence/MyService:fenceMsgInternalUrl	Ljava/lang/String;
          //   2142: aload_0
          //   2143: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   2146: getfield 244	com/ons/geofence/MyService:fenceMsgImage	Ljava/lang/String;
          //   2149: invokespecial 366	com/ons/geofence/MyService$sendNotification:<init>	(Lcom/ons/geofence/MyService;Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
          //   2152: iconst_1
          //   2153: anewarray 100	java/lang/String
          //   2156: dup
          //   2157: iconst_0
          //   2158: aload_0
          //   2159: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   2162: getfield 244	com/ons/geofence/MyService:fenceMsgImage	Ljava/lang/String;
          //   2165: aastore
          //   2166: invokevirtual 370	com/ons/geofence/MyService$sendNotification:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
          //   2169: pop
          //   2170: goto -1099 -> 1071
          //   2173: astore 6
          //   2175: aload 6
          //   2177: invokevirtual 373	java/lang/Exception:printStackTrace	()V
          //   2180: goto -1109 -> 1071
          //   2183: astore 8
          //   2185: aload 8
          //   2187: invokevirtual 373	java/lang/Exception:printStackTrace	()V
          //   2190: goto -141 -> 2049
          //   2193: aload_0
          //   2194: getfield 23	com/ons/geofence/MyService$2:val$con	Landroid/content/Context;
          //   2197: aload_0
          //   2198: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   2201: getfield 232	com/ons/geofence/MyService:fenceMessage	Ljava/lang/String;
          //   2204: aload_0
          //   2205: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   2208: getfield 248	com/ons/geofence/MyService:fenceMsgSound	Ljava/lang/String;
          //   2211: aload_0
          //   2212: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   2215: getfield 236	com/ons/geofence/MyService:fenceMsgUrl	Ljava/lang/String;
          //   2218: aload_0
          //   2219: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   2222: getfield 240	com/ons/geofence/MyService:fenceMsgInternalUrl	Ljava/lang/String;
          //   2225: aload_0
          //   2226: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   2229: getfield 244	com/ons/geofence/MyService:fenceMsgImage	Ljava/lang/String;
          //   2232: aconst_null
          //   2233: invokestatic 377	com/ons/geofence/MyService:access$000	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Landroid/graphics/Bitmap;)V
          //   2236: goto -1165 -> 1071
          //   2239: aload_0
          //   2240: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   2243: getfield 103	com/ons/geofence/MyService:day	Ljava/lang/String;
          //   2246: ldc_w 404
          //   2249: invokevirtual 316	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
          //   2252: ifeq +569 -> 2821
          //   2255: aload_0
          //   2256: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   2259: getfield 264	com/ons/geofence/MyService:pushScheduleTue	Ljava/lang/String;
          //   2262: ldc -115
          //   2264: if_acmpeq +557 -> 2821
          //   2267: aload_0
          //   2268: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   2271: getfield 264	com/ons/geofence/MyService:pushScheduleTue	Ljava/lang/String;
          //   2274: invokevirtual 330	java/lang/String:trim	()Ljava/lang/String;
          //   2277: invokevirtual 331	java/lang/String:length	()I
          //   2280: ifle +541 -> 2821
          //   2283: ldc 32
          //   2285: ldc_w 406
          //   2288: invokestatic 40	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
          //   2291: pop
          //   2292: aload_0
          //   2293: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   2296: getfield 264	com/ons/geofence/MyService:pushScheduleTue	Ljava/lang/String;
          //   2299: ldc_w 383
          //   2302: invokevirtual 386	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
          //   2305: ifeq -1234 -> 1071
          //   2308: ldc_w 334
          //   2311: new 105	java/lang/StringBuilder
          //   2314: dup
          //   2315: invokespecial 106	java/lang/StringBuilder:<init>	()V
          //   2318: ldc_w 408
          //   2321: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   2324: aload_0
          //   2325: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   2328: getfield 276	com/ons/geofence/MyService:pushScheduleFri	Ljava/lang/String;
          //   2331: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   2334: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   2337: invokestatic 40	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
          //   2340: pop
          //   2341: aload_0
          //   2342: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   2345: invokestatic 46	java/util/Calendar:getInstance	()Ljava/util/Calendar;
          //   2348: putfield 50	com/ons/geofence/MyService:c	Ljava/util/Calendar;
          //   2351: aload_0
          //   2352: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   2355: new 52	java/text/SimpleDateFormat
          //   2358: dup
          //   2359: ldc 54
          //   2361: invokespecial 57	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;)V
          //   2364: putfield 61	com/ons/geofence/MyService:time	Ljava/text/SimpleDateFormat;
          //   2367: aload_0
          //   2368: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   2371: getfield 264	com/ons/geofence/MyService:pushScheduleTue	Ljava/lang/String;
          //   2374: ldc_w 383
          //   2377: invokevirtual 340	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
          //   2380: astore 6
          //   2382: aload_0
          //   2383: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   2386: aload 6
          //   2388: iconst_0
          //   2389: aaload
          //   2390: putfield 389	com/ons/geofence/MyService:geofanceBeginTime	Ljava/lang/String;
          //   2393: aload_0
          //   2394: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   2397: aload 6
          //   2399: iconst_1
          //   2400: aaload
          //   2401: putfield 392	com/ons/geofence/MyService:geofanceEndTime	Ljava/lang/String;
          //   2404: ldc_w 334
          //   2407: new 105	java/lang/StringBuilder
          //   2410: dup
          //   2411: invokespecial 106	java/lang/StringBuilder:<init>	()V
          //   2414: ldc_w 394
          //   2417: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   2420: aload_0
          //   2421: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   2424: getfield 389	com/ons/geofence/MyService:geofanceBeginTime	Ljava/lang/String;
          //   2427: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   2430: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   2433: invokestatic 40	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
          //   2436: pop
          //   2437: ldc_w 334
          //   2440: new 105	java/lang/StringBuilder
          //   2443: dup
          //   2444: invokespecial 106	java/lang/StringBuilder:<init>	()V
          //   2447: ldc_w 396
          //   2450: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   2453: aload_0
          //   2454: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   2457: getfield 392	com/ons/geofence/MyService:geofanceEndTime	Ljava/lang/String;
          //   2460: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   2463: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   2466: invokestatic 40	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
          //   2469: pop
          //   2470: aload_0
          //   2471: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   2474: getfield 61	com/ons/geofence/MyService:time	Ljava/text/SimpleDateFormat;
          //   2477: aload_0
          //   2478: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   2481: getfield 389	com/ons/geofence/MyService:geofanceBeginTime	Ljava/lang/String;
          //   2484: invokevirtual 87	java/text/SimpleDateFormat:parse	(Ljava/lang/String;)Ljava/util/Date;
          //   2487: astore 6
          //   2489: aload_0
          //   2490: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   2493: getfield 61	com/ons/geofence/MyService:time	Ljava/text/SimpleDateFormat;
          //   2496: aload_0
          //   2497: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   2500: getfield 392	com/ons/geofence/MyService:geofanceEndTime	Ljava/lang/String;
          //   2503: invokevirtual 87	java/text/SimpleDateFormat:parse	(Ljava/lang/String;)Ljava/util/Date;
          //   2506: astore 7
          //   2508: ldc_w 334
          //   2511: new 105	java/lang/StringBuilder
          //   2514: dup
          //   2515: invokespecial 106	java/lang/StringBuilder:<init>	()V
          //   2518: ldc_w 398
          //   2521: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   2524: aload 6
          //   2526: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
          //   2529: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   2532: invokestatic 40	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
          //   2535: pop
          //   2536: ldc_w 334
          //   2539: new 105	java/lang/StringBuilder
          //   2542: dup
          //   2543: invokespecial 106	java/lang/StringBuilder:<init>	()V
          //   2546: ldc_w 400
          //   2549: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   2552: aload 7
          //   2554: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
          //   2557: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   2560: invokestatic 40	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
          //   2563: pop
          //   2564: aload_0
          //   2565: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   2568: aload_0
          //   2569: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   2572: getfield 138	com/ons/geofence/MyService:pref	Landroid/content/SharedPreferences;
          //   2575: invokeinterface 151 1 0
          //   2580: putfield 155	com/ons/geofence/MyService:editor	Landroid/content/SharedPreferences$Editor;
          //   2583: aload_0
          //   2584: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   2587: getfield 155	com/ons/geofence/MyService:editor	Landroid/content/SharedPreferences$Editor;
          //   2590: ldc_w 307
          //   2593: new 105	java/lang/StringBuilder
          //   2596: dup
          //   2597: invokespecial 106	java/lang/StringBuilder:<init>	()V
          //   2600: ldc -115
          //   2602: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   2605: iload_1
          //   2606: invokevirtual 319	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
          //   2609: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   2612: invokeinterface 161 3 0
          //   2617: pop
          //   2618: aload_0
          //   2619: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   2622: getfield 155	com/ons/geofence/MyService:editor	Landroid/content/SharedPreferences$Editor;
          //   2625: invokeinterface 165 1 0
          //   2630: pop
          //   2631: aload_2
          //   2632: aload 6
          //   2634: invokevirtual 350	java/util/Date:after	(Ljava/util/Date;)Z
          //   2637: ifeq -1566 -> 1071
          //   2640: aload_2
          //   2641: aload 7
          //   2643: invokevirtual 353	java/util/Date:before	(Ljava/util/Date;)Z
          //   2646: ifeq -1575 -> 1071
          //   2649: ldc 32
          //   2651: ldc_w 410
          //   2654: invokestatic 40	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
          //   2657: pop
          //   2658: aload_0
          //   2659: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   2662: getfield 244	com/ons/geofence/MyService:fenceMsgImage	Ljava/lang/String;
          //   2665: ifnull +110 -> 2775
          //   2668: aload_0
          //   2669: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   2672: getfield 244	com/ons/geofence/MyService:fenceMsgImage	Ljava/lang/String;
          //   2675: ldc_w 357
          //   2678: invokevirtual 361	java/lang/String:equals	(Ljava/lang/Object;)Z
          //   2681: ifne +94 -> 2775
          //   2684: new 363	com/ons/geofence/MyService$sendNotification
          //   2687: dup
          //   2688: aload_0
          //   2689: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   2692: aload_0
          //   2693: getfield 23	com/ons/geofence/MyService$2:val$con	Landroid/content/Context;
          //   2696: aload_0
          //   2697: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   2700: getfield 248	com/ons/geofence/MyService:fenceMsgSound	Ljava/lang/String;
          //   2703: aload_0
          //   2704: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   2707: getfield 232	com/ons/geofence/MyService:fenceMessage	Ljava/lang/String;
          //   2710: aload_0
          //   2711: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   2714: getfield 236	com/ons/geofence/MyService:fenceMsgUrl	Ljava/lang/String;
          //   2717: aload_0
          //   2718: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   2721: getfield 240	com/ons/geofence/MyService:fenceMsgInternalUrl	Ljava/lang/String;
          //   2724: aload_0
          //   2725: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   2728: getfield 244	com/ons/geofence/MyService:fenceMsgImage	Ljava/lang/String;
          //   2731: invokespecial 366	com/ons/geofence/MyService$sendNotification:<init>	(Lcom/ons/geofence/MyService;Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
          //   2734: iconst_1
          //   2735: anewarray 100	java/lang/String
          //   2738: dup
          //   2739: iconst_0
          //   2740: aload_0
          //   2741: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   2744: getfield 244	com/ons/geofence/MyService:fenceMsgImage	Ljava/lang/String;
          //   2747: aastore
          //   2748: invokevirtual 370	com/ons/geofence/MyService$sendNotification:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
          //   2751: pop
          //   2752: goto -1681 -> 1071
          //   2755: astore 6
          //   2757: aload 6
          //   2759: invokevirtual 373	java/lang/Exception:printStackTrace	()V
          //   2762: goto -1691 -> 1071
          //   2765: astore 8
          //   2767: aload 8
          //   2769: invokevirtual 373	java/lang/Exception:printStackTrace	()V
          //   2772: goto -141 -> 2631
          //   2775: aload_0
          //   2776: getfield 23	com/ons/geofence/MyService$2:val$con	Landroid/content/Context;
          //   2779: aload_0
          //   2780: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   2783: getfield 232	com/ons/geofence/MyService:fenceMessage	Ljava/lang/String;
          //   2786: aload_0
          //   2787: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   2790: getfield 248	com/ons/geofence/MyService:fenceMsgSound	Ljava/lang/String;
          //   2793: aload_0
          //   2794: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   2797: getfield 236	com/ons/geofence/MyService:fenceMsgUrl	Ljava/lang/String;
          //   2800: aload_0
          //   2801: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   2804: getfield 240	com/ons/geofence/MyService:fenceMsgInternalUrl	Ljava/lang/String;
          //   2807: aload_0
          //   2808: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   2811: getfield 244	com/ons/geofence/MyService:fenceMsgImage	Ljava/lang/String;
          //   2814: aconst_null
          //   2815: invokestatic 377	com/ons/geofence/MyService:access$000	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Landroid/graphics/Bitmap;)V
          //   2818: goto -1747 -> 1071
          //   2821: aload_0
          //   2822: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   2825: getfield 103	com/ons/geofence/MyService:day	Ljava/lang/String;
          //   2828: ldc_w 412
          //   2831: invokevirtual 316	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
          //   2834: ifeq +569 -> 3403
          //   2837: aload_0
          //   2838: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   2841: getfield 268	com/ons/geofence/MyService:pushScheduleWed	Ljava/lang/String;
          //   2844: ldc -115
          //   2846: if_acmpeq +557 -> 3403
          //   2849: aload_0
          //   2850: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   2853: getfield 268	com/ons/geofence/MyService:pushScheduleWed	Ljava/lang/String;
          //   2856: invokevirtual 330	java/lang/String:trim	()Ljava/lang/String;
          //   2859: invokevirtual 331	java/lang/String:length	()I
          //   2862: ifle +541 -> 3403
          //   2865: ldc 32
          //   2867: ldc_w 414
          //   2870: invokestatic 40	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
          //   2873: pop
          //   2874: aload_0
          //   2875: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   2878: getfield 268	com/ons/geofence/MyService:pushScheduleWed	Ljava/lang/String;
          //   2881: ldc_w 383
          //   2884: invokevirtual 386	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
          //   2887: ifeq -1816 -> 1071
          //   2890: ldc_w 334
          //   2893: new 105	java/lang/StringBuilder
          //   2896: dup
          //   2897: invokespecial 106	java/lang/StringBuilder:<init>	()V
          //   2900: ldc_w 408
          //   2903: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   2906: aload_0
          //   2907: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   2910: getfield 276	com/ons/geofence/MyService:pushScheduleFri	Ljava/lang/String;
          //   2913: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   2916: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   2919: invokestatic 40	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
          //   2922: pop
          //   2923: aload_0
          //   2924: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   2927: invokestatic 46	java/util/Calendar:getInstance	()Ljava/util/Calendar;
          //   2930: putfield 50	com/ons/geofence/MyService:c	Ljava/util/Calendar;
          //   2933: aload_0
          //   2934: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   2937: new 52	java/text/SimpleDateFormat
          //   2940: dup
          //   2941: ldc 54
          //   2943: invokespecial 57	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;)V
          //   2946: putfield 61	com/ons/geofence/MyService:time	Ljava/text/SimpleDateFormat;
          //   2949: aload_0
          //   2950: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   2953: getfield 268	com/ons/geofence/MyService:pushScheduleWed	Ljava/lang/String;
          //   2956: ldc_w 383
          //   2959: invokevirtual 340	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
          //   2962: astore 6
          //   2964: aload_0
          //   2965: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   2968: aload 6
          //   2970: iconst_0
          //   2971: aaload
          //   2972: putfield 389	com/ons/geofence/MyService:geofanceBeginTime	Ljava/lang/String;
          //   2975: aload_0
          //   2976: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   2979: aload 6
          //   2981: iconst_1
          //   2982: aaload
          //   2983: putfield 392	com/ons/geofence/MyService:geofanceEndTime	Ljava/lang/String;
          //   2986: ldc_w 334
          //   2989: new 105	java/lang/StringBuilder
          //   2992: dup
          //   2993: invokespecial 106	java/lang/StringBuilder:<init>	()V
          //   2996: ldc_w 394
          //   2999: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   3002: aload_0
          //   3003: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   3006: getfield 389	com/ons/geofence/MyService:geofanceBeginTime	Ljava/lang/String;
          //   3009: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   3012: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   3015: invokestatic 40	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
          //   3018: pop
          //   3019: ldc_w 334
          //   3022: new 105	java/lang/StringBuilder
          //   3025: dup
          //   3026: invokespecial 106	java/lang/StringBuilder:<init>	()V
          //   3029: ldc_w 396
          //   3032: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   3035: aload_0
          //   3036: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   3039: getfield 392	com/ons/geofence/MyService:geofanceEndTime	Ljava/lang/String;
          //   3042: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   3045: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   3048: invokestatic 40	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
          //   3051: pop
          //   3052: aload_0
          //   3053: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   3056: getfield 61	com/ons/geofence/MyService:time	Ljava/text/SimpleDateFormat;
          //   3059: aload_0
          //   3060: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   3063: getfield 389	com/ons/geofence/MyService:geofanceBeginTime	Ljava/lang/String;
          //   3066: invokevirtual 87	java/text/SimpleDateFormat:parse	(Ljava/lang/String;)Ljava/util/Date;
          //   3069: astore 6
          //   3071: aload_0
          //   3072: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   3075: getfield 61	com/ons/geofence/MyService:time	Ljava/text/SimpleDateFormat;
          //   3078: aload_0
          //   3079: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   3082: getfield 392	com/ons/geofence/MyService:geofanceEndTime	Ljava/lang/String;
          //   3085: invokevirtual 87	java/text/SimpleDateFormat:parse	(Ljava/lang/String;)Ljava/util/Date;
          //   3088: astore 7
          //   3090: ldc_w 334
          //   3093: new 105	java/lang/StringBuilder
          //   3096: dup
          //   3097: invokespecial 106	java/lang/StringBuilder:<init>	()V
          //   3100: ldc_w 398
          //   3103: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   3106: aload 6
          //   3108: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
          //   3111: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   3114: invokestatic 40	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
          //   3117: pop
          //   3118: ldc_w 334
          //   3121: new 105	java/lang/StringBuilder
          //   3124: dup
          //   3125: invokespecial 106	java/lang/StringBuilder:<init>	()V
          //   3128: ldc_w 400
          //   3131: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   3134: aload 7
          //   3136: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
          //   3139: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   3142: invokestatic 40	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
          //   3145: pop
          //   3146: aload_0
          //   3147: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   3150: aload_0
          //   3151: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   3154: getfield 138	com/ons/geofence/MyService:pref	Landroid/content/SharedPreferences;
          //   3157: invokeinterface 151 1 0
          //   3162: putfield 155	com/ons/geofence/MyService:editor	Landroid/content/SharedPreferences$Editor;
          //   3165: aload_0
          //   3166: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   3169: getfield 155	com/ons/geofence/MyService:editor	Landroid/content/SharedPreferences$Editor;
          //   3172: ldc_w 307
          //   3175: new 105	java/lang/StringBuilder
          //   3178: dup
          //   3179: invokespecial 106	java/lang/StringBuilder:<init>	()V
          //   3182: ldc -115
          //   3184: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   3187: iload_1
          //   3188: invokevirtual 319	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
          //   3191: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   3194: invokeinterface 161 3 0
          //   3199: pop
          //   3200: aload_0
          //   3201: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   3204: getfield 155	com/ons/geofence/MyService:editor	Landroid/content/SharedPreferences$Editor;
          //   3207: invokeinterface 165 1 0
          //   3212: pop
          //   3213: aload_2
          //   3214: aload 6
          //   3216: invokevirtual 350	java/util/Date:after	(Ljava/util/Date;)Z
          //   3219: ifeq -2148 -> 1071
          //   3222: aload_2
          //   3223: aload 7
          //   3225: invokevirtual 353	java/util/Date:before	(Ljava/util/Date;)Z
          //   3228: ifeq -2157 -> 1071
          //   3231: ldc 32
          //   3233: ldc_w 416
          //   3236: invokestatic 40	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
          //   3239: pop
          //   3240: aload_0
          //   3241: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   3244: getfield 244	com/ons/geofence/MyService:fenceMsgImage	Ljava/lang/String;
          //   3247: ifnull +110 -> 3357
          //   3250: aload_0
          //   3251: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   3254: getfield 244	com/ons/geofence/MyService:fenceMsgImage	Ljava/lang/String;
          //   3257: ldc_w 357
          //   3260: invokevirtual 361	java/lang/String:equals	(Ljava/lang/Object;)Z
          //   3263: ifne +94 -> 3357
          //   3266: new 363	com/ons/geofence/MyService$sendNotification
          //   3269: dup
          //   3270: aload_0
          //   3271: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   3274: aload_0
          //   3275: getfield 23	com/ons/geofence/MyService$2:val$con	Landroid/content/Context;
          //   3278: aload_0
          //   3279: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   3282: getfield 248	com/ons/geofence/MyService:fenceMsgSound	Ljava/lang/String;
          //   3285: aload_0
          //   3286: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   3289: getfield 232	com/ons/geofence/MyService:fenceMessage	Ljava/lang/String;
          //   3292: aload_0
          //   3293: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   3296: getfield 236	com/ons/geofence/MyService:fenceMsgUrl	Ljava/lang/String;
          //   3299: aload_0
          //   3300: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   3303: getfield 240	com/ons/geofence/MyService:fenceMsgInternalUrl	Ljava/lang/String;
          //   3306: aload_0
          //   3307: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   3310: getfield 244	com/ons/geofence/MyService:fenceMsgImage	Ljava/lang/String;
          //   3313: invokespecial 366	com/ons/geofence/MyService$sendNotification:<init>	(Lcom/ons/geofence/MyService;Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
          //   3316: iconst_1
          //   3317: anewarray 100	java/lang/String
          //   3320: dup
          //   3321: iconst_0
          //   3322: aload_0
          //   3323: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   3326: getfield 244	com/ons/geofence/MyService:fenceMsgImage	Ljava/lang/String;
          //   3329: aastore
          //   3330: invokevirtual 370	com/ons/geofence/MyService$sendNotification:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
          //   3333: pop
          //   3334: goto -2263 -> 1071
          //   3337: astore 6
          //   3339: aload 6
          //   3341: invokevirtual 373	java/lang/Exception:printStackTrace	()V
          //   3344: goto -2273 -> 1071
          //   3347: astore 8
          //   3349: aload 8
          //   3351: invokevirtual 373	java/lang/Exception:printStackTrace	()V
          //   3354: goto -141 -> 3213
          //   3357: aload_0
          //   3358: getfield 23	com/ons/geofence/MyService$2:val$con	Landroid/content/Context;
          //   3361: aload_0
          //   3362: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   3365: getfield 232	com/ons/geofence/MyService:fenceMessage	Ljava/lang/String;
          //   3368: aload_0
          //   3369: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   3372: getfield 248	com/ons/geofence/MyService:fenceMsgSound	Ljava/lang/String;
          //   3375: aload_0
          //   3376: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   3379: getfield 236	com/ons/geofence/MyService:fenceMsgUrl	Ljava/lang/String;
          //   3382: aload_0
          //   3383: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   3386: getfield 240	com/ons/geofence/MyService:fenceMsgInternalUrl	Ljava/lang/String;
          //   3389: aload_0
          //   3390: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   3393: getfield 244	com/ons/geofence/MyService:fenceMsgImage	Ljava/lang/String;
          //   3396: aconst_null
          //   3397: invokestatic 377	com/ons/geofence/MyService:access$000	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Landroid/graphics/Bitmap;)V
          //   3400: goto -2329 -> 1071
          //   3403: aload_0
          //   3404: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   3407: getfield 103	com/ons/geofence/MyService:day	Ljava/lang/String;
          //   3410: ldc_w 418
          //   3413: invokevirtual 316	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
          //   3416: ifeq +569 -> 3985
          //   3419: aload_0
          //   3420: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   3423: getfield 272	com/ons/geofence/MyService:pushScheduleThur	Ljava/lang/String;
          //   3426: ldc -115
          //   3428: if_acmpeq +557 -> 3985
          //   3431: aload_0
          //   3432: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   3435: getfield 272	com/ons/geofence/MyService:pushScheduleThur	Ljava/lang/String;
          //   3438: invokevirtual 330	java/lang/String:trim	()Ljava/lang/String;
          //   3441: invokevirtual 331	java/lang/String:length	()I
          //   3444: ifle +541 -> 3985
          //   3447: ldc 32
          //   3449: ldc_w 420
          //   3452: invokestatic 40	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
          //   3455: pop
          //   3456: aload_0
          //   3457: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   3460: getfield 272	com/ons/geofence/MyService:pushScheduleThur	Ljava/lang/String;
          //   3463: ldc_w 383
          //   3466: invokevirtual 386	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
          //   3469: ifeq -2398 -> 1071
          //   3472: ldc_w 334
          //   3475: new 105	java/lang/StringBuilder
          //   3478: dup
          //   3479: invokespecial 106	java/lang/StringBuilder:<init>	()V
          //   3482: ldc_w 408
          //   3485: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   3488: aload_0
          //   3489: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   3492: getfield 276	com/ons/geofence/MyService:pushScheduleFri	Ljava/lang/String;
          //   3495: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   3498: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   3501: invokestatic 40	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
          //   3504: pop
          //   3505: aload_0
          //   3506: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   3509: invokestatic 46	java/util/Calendar:getInstance	()Ljava/util/Calendar;
          //   3512: putfield 50	com/ons/geofence/MyService:c	Ljava/util/Calendar;
          //   3515: aload_0
          //   3516: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   3519: new 52	java/text/SimpleDateFormat
          //   3522: dup
          //   3523: ldc 54
          //   3525: invokespecial 57	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;)V
          //   3528: putfield 61	com/ons/geofence/MyService:time	Ljava/text/SimpleDateFormat;
          //   3531: aload_0
          //   3532: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   3535: getfield 272	com/ons/geofence/MyService:pushScheduleThur	Ljava/lang/String;
          //   3538: ldc_w 383
          //   3541: invokevirtual 340	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
          //   3544: astore 6
          //   3546: aload_0
          //   3547: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   3550: aload 6
          //   3552: iconst_0
          //   3553: aaload
          //   3554: putfield 389	com/ons/geofence/MyService:geofanceBeginTime	Ljava/lang/String;
          //   3557: aload_0
          //   3558: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   3561: aload 6
          //   3563: iconst_1
          //   3564: aaload
          //   3565: putfield 392	com/ons/geofence/MyService:geofanceEndTime	Ljava/lang/String;
          //   3568: ldc_w 334
          //   3571: new 105	java/lang/StringBuilder
          //   3574: dup
          //   3575: invokespecial 106	java/lang/StringBuilder:<init>	()V
          //   3578: ldc_w 394
          //   3581: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   3584: aload_0
          //   3585: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   3588: getfield 389	com/ons/geofence/MyService:geofanceBeginTime	Ljava/lang/String;
          //   3591: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   3594: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   3597: invokestatic 40	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
          //   3600: pop
          //   3601: ldc_w 334
          //   3604: new 105	java/lang/StringBuilder
          //   3607: dup
          //   3608: invokespecial 106	java/lang/StringBuilder:<init>	()V
          //   3611: ldc_w 396
          //   3614: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   3617: aload_0
          //   3618: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   3621: getfield 392	com/ons/geofence/MyService:geofanceEndTime	Ljava/lang/String;
          //   3624: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   3627: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   3630: invokestatic 40	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
          //   3633: pop
          //   3634: aload_0
          //   3635: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   3638: getfield 61	com/ons/geofence/MyService:time	Ljava/text/SimpleDateFormat;
          //   3641: aload_0
          //   3642: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   3645: getfield 389	com/ons/geofence/MyService:geofanceBeginTime	Ljava/lang/String;
          //   3648: invokevirtual 87	java/text/SimpleDateFormat:parse	(Ljava/lang/String;)Ljava/util/Date;
          //   3651: astore 6
          //   3653: aload_0
          //   3654: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   3657: getfield 61	com/ons/geofence/MyService:time	Ljava/text/SimpleDateFormat;
          //   3660: aload_0
          //   3661: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   3664: getfield 392	com/ons/geofence/MyService:geofanceEndTime	Ljava/lang/String;
          //   3667: invokevirtual 87	java/text/SimpleDateFormat:parse	(Ljava/lang/String;)Ljava/util/Date;
          //   3670: astore 7
          //   3672: ldc_w 334
          //   3675: new 105	java/lang/StringBuilder
          //   3678: dup
          //   3679: invokespecial 106	java/lang/StringBuilder:<init>	()V
          //   3682: ldc_w 398
          //   3685: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   3688: aload 6
          //   3690: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
          //   3693: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   3696: invokestatic 40	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
          //   3699: pop
          //   3700: ldc_w 334
          //   3703: new 105	java/lang/StringBuilder
          //   3706: dup
          //   3707: invokespecial 106	java/lang/StringBuilder:<init>	()V
          //   3710: ldc_w 400
          //   3713: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   3716: aload 7
          //   3718: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
          //   3721: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   3724: invokestatic 40	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
          //   3727: pop
          //   3728: aload_0
          //   3729: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   3732: aload_0
          //   3733: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   3736: getfield 138	com/ons/geofence/MyService:pref	Landroid/content/SharedPreferences;
          //   3739: invokeinterface 151 1 0
          //   3744: putfield 155	com/ons/geofence/MyService:editor	Landroid/content/SharedPreferences$Editor;
          //   3747: aload_0
          //   3748: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   3751: getfield 155	com/ons/geofence/MyService:editor	Landroid/content/SharedPreferences$Editor;
          //   3754: ldc_w 307
          //   3757: new 105	java/lang/StringBuilder
          //   3760: dup
          //   3761: invokespecial 106	java/lang/StringBuilder:<init>	()V
          //   3764: ldc -115
          //   3766: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   3769: iload_1
          //   3770: invokevirtual 319	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
          //   3773: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   3776: invokeinterface 161 3 0
          //   3781: pop
          //   3782: aload_0
          //   3783: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   3786: getfield 155	com/ons/geofence/MyService:editor	Landroid/content/SharedPreferences$Editor;
          //   3789: invokeinterface 165 1 0
          //   3794: pop
          //   3795: aload_2
          //   3796: aload 6
          //   3798: invokevirtual 350	java/util/Date:after	(Ljava/util/Date;)Z
          //   3801: ifeq -2730 -> 1071
          //   3804: aload_2
          //   3805: aload 7
          //   3807: invokevirtual 353	java/util/Date:before	(Ljava/util/Date;)Z
          //   3810: ifeq -2739 -> 1071
          //   3813: ldc 32
          //   3815: ldc_w 422
          //   3818: invokestatic 40	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
          //   3821: pop
          //   3822: aload_0
          //   3823: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   3826: getfield 244	com/ons/geofence/MyService:fenceMsgImage	Ljava/lang/String;
          //   3829: ifnull +110 -> 3939
          //   3832: aload_0
          //   3833: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   3836: getfield 244	com/ons/geofence/MyService:fenceMsgImage	Ljava/lang/String;
          //   3839: ldc_w 357
          //   3842: invokevirtual 361	java/lang/String:equals	(Ljava/lang/Object;)Z
          //   3845: ifne +94 -> 3939
          //   3848: new 363	com/ons/geofence/MyService$sendNotification
          //   3851: dup
          //   3852: aload_0
          //   3853: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   3856: aload_0
          //   3857: getfield 23	com/ons/geofence/MyService$2:val$con	Landroid/content/Context;
          //   3860: aload_0
          //   3861: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   3864: getfield 248	com/ons/geofence/MyService:fenceMsgSound	Ljava/lang/String;
          //   3867: aload_0
          //   3868: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   3871: getfield 232	com/ons/geofence/MyService:fenceMessage	Ljava/lang/String;
          //   3874: aload_0
          //   3875: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   3878: getfield 236	com/ons/geofence/MyService:fenceMsgUrl	Ljava/lang/String;
          //   3881: aload_0
          //   3882: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   3885: getfield 240	com/ons/geofence/MyService:fenceMsgInternalUrl	Ljava/lang/String;
          //   3888: aload_0
          //   3889: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   3892: getfield 244	com/ons/geofence/MyService:fenceMsgImage	Ljava/lang/String;
          //   3895: invokespecial 366	com/ons/geofence/MyService$sendNotification:<init>	(Lcom/ons/geofence/MyService;Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
          //   3898: iconst_1
          //   3899: anewarray 100	java/lang/String
          //   3902: dup
          //   3903: iconst_0
          //   3904: aload_0
          //   3905: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   3908: getfield 244	com/ons/geofence/MyService:fenceMsgImage	Ljava/lang/String;
          //   3911: aastore
          //   3912: invokevirtual 370	com/ons/geofence/MyService$sendNotification:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
          //   3915: pop
          //   3916: goto -2845 -> 1071
          //   3919: astore 6
          //   3921: aload 6
          //   3923: invokevirtual 373	java/lang/Exception:printStackTrace	()V
          //   3926: goto -2855 -> 1071
          //   3929: astore 8
          //   3931: aload 8
          //   3933: invokevirtual 373	java/lang/Exception:printStackTrace	()V
          //   3936: goto -141 -> 3795
          //   3939: aload_0
          //   3940: getfield 23	com/ons/geofence/MyService$2:val$con	Landroid/content/Context;
          //   3943: aload_0
          //   3944: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   3947: getfield 232	com/ons/geofence/MyService:fenceMessage	Ljava/lang/String;
          //   3950: aload_0
          //   3951: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   3954: getfield 248	com/ons/geofence/MyService:fenceMsgSound	Ljava/lang/String;
          //   3957: aload_0
          //   3958: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   3961: getfield 236	com/ons/geofence/MyService:fenceMsgUrl	Ljava/lang/String;
          //   3964: aload_0
          //   3965: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   3968: getfield 240	com/ons/geofence/MyService:fenceMsgInternalUrl	Ljava/lang/String;
          //   3971: aload_0
          //   3972: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   3975: getfield 244	com/ons/geofence/MyService:fenceMsgImage	Ljava/lang/String;
          //   3978: aconst_null
          //   3979: invokestatic 377	com/ons/geofence/MyService:access$000	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Landroid/graphics/Bitmap;)V
          //   3982: goto -2911 -> 1071
          //   3985: aload_0
          //   3986: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   3989: getfield 103	com/ons/geofence/MyService:day	Ljava/lang/String;
          //   3992: ldc_w 424
          //   3995: invokevirtual 316	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
          //   3998: ifeq +620 -> 4618
          //   4001: aload_0
          //   4002: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   4005: getfield 276	com/ons/geofence/MyService:pushScheduleFri	Ljava/lang/String;
          //   4008: ldc -115
          //   4010: if_acmpeq +608 -> 4618
          //   4013: aload_0
          //   4014: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   4017: getfield 276	com/ons/geofence/MyService:pushScheduleFri	Ljava/lang/String;
          //   4020: invokevirtual 330	java/lang/String:trim	()Ljava/lang/String;
          //   4023: invokevirtual 331	java/lang/String:length	()I
          //   4026: ifle +592 -> 4618
          //   4029: ldc 32
          //   4031: ldc_w 426
          //   4034: invokestatic 40	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
          //   4037: pop
          //   4038: ldc_w 334
          //   4041: new 105	java/lang/StringBuilder
          //   4044: dup
          //   4045: invokespecial 106	java/lang/StringBuilder:<init>	()V
          //   4048: ldc_w 426
          //   4051: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   4054: aload_0
          //   4055: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   4058: getfield 276	com/ons/geofence/MyService:pushScheduleFri	Ljava/lang/String;
          //   4061: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   4064: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   4067: invokestatic 40	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
          //   4070: pop
          //   4071: aload_0
          //   4072: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   4075: getfield 276	com/ons/geofence/MyService:pushScheduleFri	Ljava/lang/String;
          //   4078: ldc_w 383
          //   4081: invokevirtual 386	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
          //   4084: ifeq -3013 -> 1071
          //   4087: ldc_w 334
          //   4090: new 105	java/lang/StringBuilder
          //   4093: dup
          //   4094: invokespecial 106	java/lang/StringBuilder:<init>	()V
          //   4097: ldc_w 408
          //   4100: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   4103: aload_0
          //   4104: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   4107: getfield 276	com/ons/geofence/MyService:pushScheduleFri	Ljava/lang/String;
          //   4110: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   4113: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   4116: invokestatic 40	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
          //   4119: pop
          //   4120: aload_0
          //   4121: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   4124: invokestatic 46	java/util/Calendar:getInstance	()Ljava/util/Calendar;
          //   4127: putfield 50	com/ons/geofence/MyService:c	Ljava/util/Calendar;
          //   4130: aload_0
          //   4131: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   4134: new 52	java/text/SimpleDateFormat
          //   4137: dup
          //   4138: ldc 54
          //   4140: invokespecial 57	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;)V
          //   4143: putfield 61	com/ons/geofence/MyService:time	Ljava/text/SimpleDateFormat;
          //   4146: aload_0
          //   4147: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   4150: getfield 276	com/ons/geofence/MyService:pushScheduleFri	Ljava/lang/String;
          //   4153: ldc_w 383
          //   4156: invokevirtual 340	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
          //   4159: astore 6
          //   4161: aload_0
          //   4162: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   4165: aload 6
          //   4167: iconst_0
          //   4168: aaload
          //   4169: putfield 389	com/ons/geofence/MyService:geofanceBeginTime	Ljava/lang/String;
          //   4172: aload_0
          //   4173: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   4176: aload 6
          //   4178: iconst_1
          //   4179: aaload
          //   4180: putfield 392	com/ons/geofence/MyService:geofanceEndTime	Ljava/lang/String;
          //   4183: ldc_w 334
          //   4186: new 105	java/lang/StringBuilder
          //   4189: dup
          //   4190: invokespecial 106	java/lang/StringBuilder:<init>	()V
          //   4193: ldc_w 394
          //   4196: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   4199: aload_0
          //   4200: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   4203: getfield 389	com/ons/geofence/MyService:geofanceBeginTime	Ljava/lang/String;
          //   4206: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   4209: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   4212: invokestatic 40	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
          //   4215: pop
          //   4216: ldc_w 334
          //   4219: new 105	java/lang/StringBuilder
          //   4222: dup
          //   4223: invokespecial 106	java/lang/StringBuilder:<init>	()V
          //   4226: ldc_w 396
          //   4229: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   4232: aload_0
          //   4233: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   4236: getfield 392	com/ons/geofence/MyService:geofanceEndTime	Ljava/lang/String;
          //   4239: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   4242: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   4245: invokestatic 40	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
          //   4248: pop
          //   4249: aload_0
          //   4250: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   4253: getfield 61	com/ons/geofence/MyService:time	Ljava/text/SimpleDateFormat;
          //   4256: aload_0
          //   4257: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   4260: getfield 389	com/ons/geofence/MyService:geofanceBeginTime	Ljava/lang/String;
          //   4263: invokevirtual 87	java/text/SimpleDateFormat:parse	(Ljava/lang/String;)Ljava/util/Date;
          //   4266: astore 6
          //   4268: aload_0
          //   4269: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   4272: getfield 61	com/ons/geofence/MyService:time	Ljava/text/SimpleDateFormat;
          //   4275: aload_0
          //   4276: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   4279: getfield 392	com/ons/geofence/MyService:geofanceEndTime	Ljava/lang/String;
          //   4282: invokevirtual 87	java/text/SimpleDateFormat:parse	(Ljava/lang/String;)Ljava/util/Date;
          //   4285: astore 7
          //   4287: ldc_w 334
          //   4290: new 105	java/lang/StringBuilder
          //   4293: dup
          //   4294: invokespecial 106	java/lang/StringBuilder:<init>	()V
          //   4297: ldc_w 398
          //   4300: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   4303: aload 6
          //   4305: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
          //   4308: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   4311: invokestatic 40	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
          //   4314: pop
          //   4315: ldc_w 334
          //   4318: new 105	java/lang/StringBuilder
          //   4321: dup
          //   4322: invokespecial 106	java/lang/StringBuilder:<init>	()V
          //   4325: ldc_w 400
          //   4328: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   4331: aload 7
          //   4333: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
          //   4336: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   4339: invokestatic 40	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
          //   4342: pop
          //   4343: aload_0
          //   4344: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   4347: aload_0
          //   4348: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   4351: getfield 138	com/ons/geofence/MyService:pref	Landroid/content/SharedPreferences;
          //   4354: invokeinterface 151 1 0
          //   4359: putfield 155	com/ons/geofence/MyService:editor	Landroid/content/SharedPreferences$Editor;
          //   4362: aload_0
          //   4363: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   4366: getfield 155	com/ons/geofence/MyService:editor	Landroid/content/SharedPreferences$Editor;
          //   4369: ldc_w 307
          //   4372: new 105	java/lang/StringBuilder
          //   4375: dup
          //   4376: invokespecial 106	java/lang/StringBuilder:<init>	()V
          //   4379: ldc -115
          //   4381: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   4384: iload_1
          //   4385: invokevirtual 319	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
          //   4388: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   4391: invokeinterface 161 3 0
          //   4396: pop
          //   4397: aload_0
          //   4398: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   4401: getfield 155	com/ons/geofence/MyService:editor	Landroid/content/SharedPreferences$Editor;
          //   4404: invokeinterface 165 1 0
          //   4409: pop
          //   4410: aload_2
          //   4411: aload 6
          //   4413: invokevirtual 350	java/util/Date:after	(Ljava/util/Date;)Z
          //   4416: ifeq -3345 -> 1071
          //   4419: aload_2
          //   4420: aload 7
          //   4422: invokevirtual 353	java/util/Date:before	(Ljava/util/Date;)Z
          //   4425: ifeq -3354 -> 1071
          //   4428: ldc_w 334
          //   4431: new 105	java/lang/StringBuilder
          //   4434: dup
          //   4435: invokespecial 106	java/lang/StringBuilder:<init>	()V
          //   4438: ldc_w 428
          //   4441: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   4444: aload_2
          //   4445: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
          //   4448: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   4451: invokestatic 40	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
          //   4454: pop
          //   4455: aload_0
          //   4456: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   4459: getfield 244	com/ons/geofence/MyService:fenceMsgImage	Ljava/lang/String;
          //   4462: ifnull +110 -> 4572
          //   4465: aload_0
          //   4466: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   4469: getfield 244	com/ons/geofence/MyService:fenceMsgImage	Ljava/lang/String;
          //   4472: ldc_w 357
          //   4475: invokevirtual 361	java/lang/String:equals	(Ljava/lang/Object;)Z
          //   4478: ifne +94 -> 4572
          //   4481: new 363	com/ons/geofence/MyService$sendNotification
          //   4484: dup
          //   4485: aload_0
          //   4486: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   4489: aload_0
          //   4490: getfield 23	com/ons/geofence/MyService$2:val$con	Landroid/content/Context;
          //   4493: aload_0
          //   4494: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   4497: getfield 248	com/ons/geofence/MyService:fenceMsgSound	Ljava/lang/String;
          //   4500: aload_0
          //   4501: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   4504: getfield 232	com/ons/geofence/MyService:fenceMessage	Ljava/lang/String;
          //   4507: aload_0
          //   4508: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   4511: getfield 236	com/ons/geofence/MyService:fenceMsgUrl	Ljava/lang/String;
          //   4514: aload_0
          //   4515: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   4518: getfield 240	com/ons/geofence/MyService:fenceMsgInternalUrl	Ljava/lang/String;
          //   4521: aload_0
          //   4522: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   4525: getfield 244	com/ons/geofence/MyService:fenceMsgImage	Ljava/lang/String;
          //   4528: invokespecial 366	com/ons/geofence/MyService$sendNotification:<init>	(Lcom/ons/geofence/MyService;Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
          //   4531: iconst_1
          //   4532: anewarray 100	java/lang/String
          //   4535: dup
          //   4536: iconst_0
          //   4537: aload_0
          //   4538: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   4541: getfield 244	com/ons/geofence/MyService:fenceMsgImage	Ljava/lang/String;
          //   4544: aastore
          //   4545: invokevirtual 370	com/ons/geofence/MyService$sendNotification:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
          //   4548: pop
          //   4549: goto -3478 -> 1071
          //   4552: astore 6
          //   4554: aload 6
          //   4556: invokevirtual 373	java/lang/Exception:printStackTrace	()V
          //   4559: goto -3488 -> 1071
          //   4562: astore 8
          //   4564: aload 8
          //   4566: invokevirtual 373	java/lang/Exception:printStackTrace	()V
          //   4569: goto -159 -> 4410
          //   4572: aload_0
          //   4573: getfield 23	com/ons/geofence/MyService$2:val$con	Landroid/content/Context;
          //   4576: aload_0
          //   4577: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   4580: getfield 232	com/ons/geofence/MyService:fenceMessage	Ljava/lang/String;
          //   4583: aload_0
          //   4584: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   4587: getfield 248	com/ons/geofence/MyService:fenceMsgSound	Ljava/lang/String;
          //   4590: aload_0
          //   4591: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   4594: getfield 236	com/ons/geofence/MyService:fenceMsgUrl	Ljava/lang/String;
          //   4597: aload_0
          //   4598: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   4601: getfield 240	com/ons/geofence/MyService:fenceMsgInternalUrl	Ljava/lang/String;
          //   4604: aload_0
          //   4605: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   4608: getfield 244	com/ons/geofence/MyService:fenceMsgImage	Ljava/lang/String;
          //   4611: aconst_null
          //   4612: invokestatic 377	com/ons/geofence/MyService:access$000	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Landroid/graphics/Bitmap;)V
          //   4615: goto -3544 -> 1071
          //   4618: aload_0
          //   4619: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   4622: getfield 103	com/ons/geofence/MyService:day	Ljava/lang/String;
          //   4625: ldc_w 430
          //   4628: invokevirtual 316	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
          //   4631: ifeq +569 -> 5200
          //   4634: aload_0
          //   4635: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   4638: getfield 280	com/ons/geofence/MyService:pushScheduleSat	Ljava/lang/String;
          //   4641: ldc -115
          //   4643: if_acmpeq +557 -> 5200
          //   4646: aload_0
          //   4647: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   4650: getfield 280	com/ons/geofence/MyService:pushScheduleSat	Ljava/lang/String;
          //   4653: invokevirtual 330	java/lang/String:trim	()Ljava/lang/String;
          //   4656: invokevirtual 331	java/lang/String:length	()I
          //   4659: ifle +541 -> 5200
          //   4662: ldc 32
          //   4664: ldc_w 432
          //   4667: invokestatic 40	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
          //   4670: pop
          //   4671: aload_0
          //   4672: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   4675: getfield 280	com/ons/geofence/MyService:pushScheduleSat	Ljava/lang/String;
          //   4678: ldc_w 383
          //   4681: invokevirtual 386	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
          //   4684: ifeq -3613 -> 1071
          //   4687: ldc_w 334
          //   4690: new 105	java/lang/StringBuilder
          //   4693: dup
          //   4694: invokespecial 106	java/lang/StringBuilder:<init>	()V
          //   4697: ldc_w 408
          //   4700: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   4703: aload_0
          //   4704: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   4707: getfield 276	com/ons/geofence/MyService:pushScheduleFri	Ljava/lang/String;
          //   4710: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   4713: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   4716: invokestatic 40	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
          //   4719: pop
          //   4720: aload_0
          //   4721: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   4724: invokestatic 46	java/util/Calendar:getInstance	()Ljava/util/Calendar;
          //   4727: putfield 50	com/ons/geofence/MyService:c	Ljava/util/Calendar;
          //   4730: aload_0
          //   4731: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   4734: new 52	java/text/SimpleDateFormat
          //   4737: dup
          //   4738: ldc 54
          //   4740: invokespecial 57	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;)V
          //   4743: putfield 61	com/ons/geofence/MyService:time	Ljava/text/SimpleDateFormat;
          //   4746: aload_0
          //   4747: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   4750: getfield 280	com/ons/geofence/MyService:pushScheduleSat	Ljava/lang/String;
          //   4753: ldc_w 383
          //   4756: invokevirtual 340	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
          //   4759: astore 6
          //   4761: aload_0
          //   4762: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   4765: aload 6
          //   4767: iconst_0
          //   4768: aaload
          //   4769: putfield 389	com/ons/geofence/MyService:geofanceBeginTime	Ljava/lang/String;
          //   4772: aload_0
          //   4773: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   4776: aload 6
          //   4778: iconst_1
          //   4779: aaload
          //   4780: putfield 392	com/ons/geofence/MyService:geofanceEndTime	Ljava/lang/String;
          //   4783: ldc_w 334
          //   4786: new 105	java/lang/StringBuilder
          //   4789: dup
          //   4790: invokespecial 106	java/lang/StringBuilder:<init>	()V
          //   4793: ldc_w 394
          //   4796: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   4799: aload_0
          //   4800: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   4803: getfield 389	com/ons/geofence/MyService:geofanceBeginTime	Ljava/lang/String;
          //   4806: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   4809: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   4812: invokestatic 40	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
          //   4815: pop
          //   4816: ldc_w 334
          //   4819: new 105	java/lang/StringBuilder
          //   4822: dup
          //   4823: invokespecial 106	java/lang/StringBuilder:<init>	()V
          //   4826: ldc_w 396
          //   4829: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   4832: aload_0
          //   4833: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   4836: getfield 392	com/ons/geofence/MyService:geofanceEndTime	Ljava/lang/String;
          //   4839: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   4842: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   4845: invokestatic 40	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
          //   4848: pop
          //   4849: aload_0
          //   4850: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   4853: getfield 61	com/ons/geofence/MyService:time	Ljava/text/SimpleDateFormat;
          //   4856: aload_0
          //   4857: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   4860: getfield 389	com/ons/geofence/MyService:geofanceBeginTime	Ljava/lang/String;
          //   4863: invokevirtual 87	java/text/SimpleDateFormat:parse	(Ljava/lang/String;)Ljava/util/Date;
          //   4866: astore 6
          //   4868: aload_0
          //   4869: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   4872: getfield 61	com/ons/geofence/MyService:time	Ljava/text/SimpleDateFormat;
          //   4875: aload_0
          //   4876: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   4879: getfield 392	com/ons/geofence/MyService:geofanceEndTime	Ljava/lang/String;
          //   4882: invokevirtual 87	java/text/SimpleDateFormat:parse	(Ljava/lang/String;)Ljava/util/Date;
          //   4885: astore 7
          //   4887: ldc_w 334
          //   4890: new 105	java/lang/StringBuilder
          //   4893: dup
          //   4894: invokespecial 106	java/lang/StringBuilder:<init>	()V
          //   4897: ldc_w 398
          //   4900: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   4903: aload 6
          //   4905: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
          //   4908: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   4911: invokestatic 40	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
          //   4914: pop
          //   4915: ldc_w 334
          //   4918: new 105	java/lang/StringBuilder
          //   4921: dup
          //   4922: invokespecial 106	java/lang/StringBuilder:<init>	()V
          //   4925: ldc_w 400
          //   4928: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   4931: aload 7
          //   4933: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
          //   4936: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   4939: invokestatic 40	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
          //   4942: pop
          //   4943: aload_0
          //   4944: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   4947: aload_0
          //   4948: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   4951: getfield 138	com/ons/geofence/MyService:pref	Landroid/content/SharedPreferences;
          //   4954: invokeinterface 151 1 0
          //   4959: putfield 155	com/ons/geofence/MyService:editor	Landroid/content/SharedPreferences$Editor;
          //   4962: aload_0
          //   4963: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   4966: getfield 155	com/ons/geofence/MyService:editor	Landroid/content/SharedPreferences$Editor;
          //   4969: ldc_w 307
          //   4972: new 105	java/lang/StringBuilder
          //   4975: dup
          //   4976: invokespecial 106	java/lang/StringBuilder:<init>	()V
          //   4979: ldc -115
          //   4981: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   4984: iload_1
          //   4985: invokevirtual 319	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
          //   4988: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   4991: invokeinterface 161 3 0
          //   4996: pop
          //   4997: aload_0
          //   4998: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   5001: getfield 155	com/ons/geofence/MyService:editor	Landroid/content/SharedPreferences$Editor;
          //   5004: invokeinterface 165 1 0
          //   5009: pop
          //   5010: aload_2
          //   5011: aload 6
          //   5013: invokevirtual 350	java/util/Date:after	(Ljava/util/Date;)Z
          //   5016: ifeq -3945 -> 1071
          //   5019: aload_2
          //   5020: aload 7
          //   5022: invokevirtual 353	java/util/Date:before	(Ljava/util/Date;)Z
          //   5025: ifeq -3954 -> 1071
          //   5028: ldc 32
          //   5030: ldc_w 434
          //   5033: invokestatic 40	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
          //   5036: pop
          //   5037: aload_0
          //   5038: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   5041: getfield 244	com/ons/geofence/MyService:fenceMsgImage	Ljava/lang/String;
          //   5044: ifnull +110 -> 5154
          //   5047: aload_0
          //   5048: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   5051: getfield 244	com/ons/geofence/MyService:fenceMsgImage	Ljava/lang/String;
          //   5054: ldc_w 357
          //   5057: invokevirtual 361	java/lang/String:equals	(Ljava/lang/Object;)Z
          //   5060: ifne +94 -> 5154
          //   5063: new 363	com/ons/geofence/MyService$sendNotification
          //   5066: dup
          //   5067: aload_0
          //   5068: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   5071: aload_0
          //   5072: getfield 23	com/ons/geofence/MyService$2:val$con	Landroid/content/Context;
          //   5075: aload_0
          //   5076: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   5079: getfield 248	com/ons/geofence/MyService:fenceMsgSound	Ljava/lang/String;
          //   5082: aload_0
          //   5083: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   5086: getfield 232	com/ons/geofence/MyService:fenceMessage	Ljava/lang/String;
          //   5089: aload_0
          //   5090: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   5093: getfield 236	com/ons/geofence/MyService:fenceMsgUrl	Ljava/lang/String;
          //   5096: aload_0
          //   5097: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   5100: getfield 240	com/ons/geofence/MyService:fenceMsgInternalUrl	Ljava/lang/String;
          //   5103: aload_0
          //   5104: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   5107: getfield 244	com/ons/geofence/MyService:fenceMsgImage	Ljava/lang/String;
          //   5110: invokespecial 366	com/ons/geofence/MyService$sendNotification:<init>	(Lcom/ons/geofence/MyService;Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
          //   5113: iconst_1
          //   5114: anewarray 100	java/lang/String
          //   5117: dup
          //   5118: iconst_0
          //   5119: aload_0
          //   5120: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   5123: getfield 244	com/ons/geofence/MyService:fenceMsgImage	Ljava/lang/String;
          //   5126: aastore
          //   5127: invokevirtual 370	com/ons/geofence/MyService$sendNotification:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
          //   5130: pop
          //   5131: goto -4060 -> 1071
          //   5134: astore 6
          //   5136: aload 6
          //   5138: invokevirtual 373	java/lang/Exception:printStackTrace	()V
          //   5141: goto -4070 -> 1071
          //   5144: astore 8
          //   5146: aload 8
          //   5148: invokevirtual 373	java/lang/Exception:printStackTrace	()V
          //   5151: goto -141 -> 5010
          //   5154: aload_0
          //   5155: getfield 23	com/ons/geofence/MyService$2:val$con	Landroid/content/Context;
          //   5158: aload_0
          //   5159: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   5162: getfield 232	com/ons/geofence/MyService:fenceMessage	Ljava/lang/String;
          //   5165: aload_0
          //   5166: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   5169: getfield 248	com/ons/geofence/MyService:fenceMsgSound	Ljava/lang/String;
          //   5172: aload_0
          //   5173: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   5176: getfield 236	com/ons/geofence/MyService:fenceMsgUrl	Ljava/lang/String;
          //   5179: aload_0
          //   5180: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   5183: getfield 240	com/ons/geofence/MyService:fenceMsgInternalUrl	Ljava/lang/String;
          //   5186: aload_0
          //   5187: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   5190: getfield 244	com/ons/geofence/MyService:fenceMsgImage	Ljava/lang/String;
          //   5193: aconst_null
          //   5194: invokestatic 377	com/ons/geofence/MyService:access$000	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Landroid/graphics/Bitmap;)V
          //   5197: goto -4126 -> 1071
          //   5200: aload_0
          //   5201: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   5204: getfield 103	com/ons/geofence/MyService:day	Ljava/lang/String;
          //   5207: ldc_w 436
          //   5210: invokevirtual 316	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
          //   5213: ifeq -4142 -> 1071
          //   5216: aload_0
          //   5217: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   5220: getfield 284	com/ons/geofence/MyService:pushScheduleSun	Ljava/lang/String;
          //   5223: ldc -115
          //   5225: if_acmpeq -4154 -> 1071
          //   5228: aload_0
          //   5229: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   5232: getfield 284	com/ons/geofence/MyService:pushScheduleSun	Ljava/lang/String;
          //   5235: invokevirtual 330	java/lang/String:trim	()Ljava/lang/String;
          //   5238: invokevirtual 331	java/lang/String:length	()I
          //   5241: ifle -4170 -> 1071
          //   5244: ldc 32
          //   5246: ldc_w 438
          //   5249: invokestatic 40	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
          //   5252: pop
          //   5253: aload_0
          //   5254: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   5257: getfield 284	com/ons/geofence/MyService:pushScheduleSun	Ljava/lang/String;
          //   5260: ldc_w 383
          //   5263: invokevirtual 386	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
          //   5266: ifeq -4195 -> 1071
          //   5269: ldc_w 334
          //   5272: new 105	java/lang/StringBuilder
          //   5275: dup
          //   5276: invokespecial 106	java/lang/StringBuilder:<init>	()V
          //   5279: ldc_w 408
          //   5282: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   5285: aload_0
          //   5286: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   5289: getfield 276	com/ons/geofence/MyService:pushScheduleFri	Ljava/lang/String;
          //   5292: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   5295: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   5298: invokestatic 40	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
          //   5301: pop
          //   5302: aload_0
          //   5303: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   5306: invokestatic 46	java/util/Calendar:getInstance	()Ljava/util/Calendar;
          //   5309: putfield 50	com/ons/geofence/MyService:c	Ljava/util/Calendar;
          //   5312: aload_0
          //   5313: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   5316: new 52	java/text/SimpleDateFormat
          //   5319: dup
          //   5320: ldc 54
          //   5322: invokespecial 57	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;)V
          //   5325: putfield 61	com/ons/geofence/MyService:time	Ljava/text/SimpleDateFormat;
          //   5328: aload_0
          //   5329: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   5332: getfield 284	com/ons/geofence/MyService:pushScheduleSun	Ljava/lang/String;
          //   5335: ldc_w 383
          //   5338: invokevirtual 340	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
          //   5341: astore 6
          //   5343: aload_0
          //   5344: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   5347: aload 6
          //   5349: iconst_0
          //   5350: aaload
          //   5351: putfield 389	com/ons/geofence/MyService:geofanceBeginTime	Ljava/lang/String;
          //   5354: aload_0
          //   5355: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   5358: aload 6
          //   5360: iconst_1
          //   5361: aaload
          //   5362: putfield 392	com/ons/geofence/MyService:geofanceEndTime	Ljava/lang/String;
          //   5365: ldc_w 334
          //   5368: new 105	java/lang/StringBuilder
          //   5371: dup
          //   5372: invokespecial 106	java/lang/StringBuilder:<init>	()V
          //   5375: ldc_w 394
          //   5378: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   5381: aload_0
          //   5382: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   5385: getfield 389	com/ons/geofence/MyService:geofanceBeginTime	Ljava/lang/String;
          //   5388: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   5391: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   5394: invokestatic 40	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
          //   5397: pop
          //   5398: ldc_w 334
          //   5401: new 105	java/lang/StringBuilder
          //   5404: dup
          //   5405: invokespecial 106	java/lang/StringBuilder:<init>	()V
          //   5408: ldc_w 396
          //   5411: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   5414: aload_0
          //   5415: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   5418: getfield 392	com/ons/geofence/MyService:geofanceEndTime	Ljava/lang/String;
          //   5421: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   5424: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   5427: invokestatic 40	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
          //   5430: pop
          //   5431: aload_0
          //   5432: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   5435: getfield 61	com/ons/geofence/MyService:time	Ljava/text/SimpleDateFormat;
          //   5438: aload_0
          //   5439: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   5442: getfield 389	com/ons/geofence/MyService:geofanceBeginTime	Ljava/lang/String;
          //   5445: invokevirtual 87	java/text/SimpleDateFormat:parse	(Ljava/lang/String;)Ljava/util/Date;
          //   5448: astore 6
          //   5450: aload_0
          //   5451: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   5454: getfield 61	com/ons/geofence/MyService:time	Ljava/text/SimpleDateFormat;
          //   5457: aload_0
          //   5458: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   5461: getfield 392	com/ons/geofence/MyService:geofanceEndTime	Ljava/lang/String;
          //   5464: invokevirtual 87	java/text/SimpleDateFormat:parse	(Ljava/lang/String;)Ljava/util/Date;
          //   5467: astore 7
          //   5469: ldc_w 334
          //   5472: new 105	java/lang/StringBuilder
          //   5475: dup
          //   5476: invokespecial 106	java/lang/StringBuilder:<init>	()V
          //   5479: ldc_w 398
          //   5482: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   5485: aload 6
          //   5487: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
          //   5490: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   5493: invokestatic 40	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
          //   5496: pop
          //   5497: ldc_w 334
          //   5500: new 105	java/lang/StringBuilder
          //   5503: dup
          //   5504: invokespecial 106	java/lang/StringBuilder:<init>	()V
          //   5507: ldc_w 400
          //   5510: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   5513: aload 7
          //   5515: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
          //   5518: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   5521: invokestatic 40	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
          //   5524: pop
          //   5525: aload_0
          //   5526: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   5529: aload_0
          //   5530: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   5533: getfield 138	com/ons/geofence/MyService:pref	Landroid/content/SharedPreferences;
          //   5536: invokeinterface 151 1 0
          //   5541: putfield 155	com/ons/geofence/MyService:editor	Landroid/content/SharedPreferences$Editor;
          //   5544: aload_0
          //   5545: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   5548: getfield 155	com/ons/geofence/MyService:editor	Landroid/content/SharedPreferences$Editor;
          //   5551: ldc_w 307
          //   5554: new 105	java/lang/StringBuilder
          //   5557: dup
          //   5558: invokespecial 106	java/lang/StringBuilder:<init>	()V
          //   5561: ldc -115
          //   5563: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   5566: iload_1
          //   5567: invokevirtual 319	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
          //   5570: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   5573: invokeinterface 161 3 0
          //   5578: pop
          //   5579: aload_0
          //   5580: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   5583: getfield 155	com/ons/geofence/MyService:editor	Landroid/content/SharedPreferences$Editor;
          //   5586: invokeinterface 165 1 0
          //   5591: pop
          //   5592: aload_2
          //   5593: aload 6
          //   5595: invokevirtual 350	java/util/Date:after	(Ljava/util/Date;)Z
          //   5598: ifeq -4527 -> 1071
          //   5601: aload_2
          //   5602: aload 7
          //   5604: invokevirtual 353	java/util/Date:before	(Ljava/util/Date;)Z
          //   5607: ifeq -4536 -> 1071
          //   5610: ldc 32
          //   5612: ldc_w 440
          //   5615: invokestatic 40	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
          //   5618: pop
          //   5619: aload_0
          //   5620: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   5623: getfield 244	com/ons/geofence/MyService:fenceMsgImage	Ljava/lang/String;
          //   5626: ifnull +110 -> 5736
          //   5629: aload_0
          //   5630: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   5633: getfield 244	com/ons/geofence/MyService:fenceMsgImage	Ljava/lang/String;
          //   5636: ldc_w 357
          //   5639: invokevirtual 361	java/lang/String:equals	(Ljava/lang/Object;)Z
          //   5642: ifne +94 -> 5736
          //   5645: new 363	com/ons/geofence/MyService$sendNotification
          //   5648: dup
          //   5649: aload_0
          //   5650: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   5653: aload_0
          //   5654: getfield 23	com/ons/geofence/MyService$2:val$con	Landroid/content/Context;
          //   5657: aload_0
          //   5658: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   5661: getfield 248	com/ons/geofence/MyService:fenceMsgSound	Ljava/lang/String;
          //   5664: aload_0
          //   5665: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   5668: getfield 232	com/ons/geofence/MyService:fenceMessage	Ljava/lang/String;
          //   5671: aload_0
          //   5672: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   5675: getfield 236	com/ons/geofence/MyService:fenceMsgUrl	Ljava/lang/String;
          //   5678: aload_0
          //   5679: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   5682: getfield 240	com/ons/geofence/MyService:fenceMsgInternalUrl	Ljava/lang/String;
          //   5685: aload_0
          //   5686: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   5689: getfield 244	com/ons/geofence/MyService:fenceMsgImage	Ljava/lang/String;
          //   5692: invokespecial 366	com/ons/geofence/MyService$sendNotification:<init>	(Lcom/ons/geofence/MyService;Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
          //   5695: iconst_1
          //   5696: anewarray 100	java/lang/String
          //   5699: dup
          //   5700: iconst_0
          //   5701: aload_0
          //   5702: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   5705: getfield 244	com/ons/geofence/MyService:fenceMsgImage	Ljava/lang/String;
          //   5708: aastore
          //   5709: invokevirtual 370	com/ons/geofence/MyService$sendNotification:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
          //   5712: pop
          //   5713: goto -4642 -> 1071
          //   5716: astore 6
          //   5718: aload 6
          //   5720: invokevirtual 373	java/lang/Exception:printStackTrace	()V
          //   5723: goto -4652 -> 1071
          //   5726: astore 8
          //   5728: aload 8
          //   5730: invokevirtual 373	java/lang/Exception:printStackTrace	()V
          //   5733: goto -141 -> 5592
          //   5736: aload_0
          //   5737: getfield 23	com/ons/geofence/MyService$2:val$con	Landroid/content/Context;
          //   5740: aload_0
          //   5741: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   5744: getfield 232	com/ons/geofence/MyService:fenceMessage	Ljava/lang/String;
          //   5747: aload_0
          //   5748: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   5751: getfield 248	com/ons/geofence/MyService:fenceMsgSound	Ljava/lang/String;
          //   5754: aload_0
          //   5755: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   5758: getfield 236	com/ons/geofence/MyService:fenceMsgUrl	Ljava/lang/String;
          //   5761: aload_0
          //   5762: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   5765: getfield 240	com/ons/geofence/MyService:fenceMsgInternalUrl	Ljava/lang/String;
          //   5768: aload_0
          //   5769: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   5772: getfield 244	com/ons/geofence/MyService:fenceMsgImage	Ljava/lang/String;
          //   5775: aconst_null
          //   5776: invokestatic 377	com/ons/geofence/MyService:access$000	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Landroid/graphics/Bitmap;)V
          //   5779: goto -4708 -> 1071
          //   5782: ldc_w 297
          //   5785: ldc_w 442
          //   5788: invokestatic 40	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
          //   5791: pop
          //   5792: aload_0
          //   5793: getfield 19	com/ons/geofence/MyService$2:this$0	Lcom/ons/geofence/MyService;
          //   5796: getfield 305	com/ons/geofence/MyService:statusFence	[I
          //   5799: iload_1
          //   5800: iconst_1
          //   5801: iastore
          //   5802: goto -4731 -> 1071
          // Local variable table:
          //   start	length	slot	name	signature
          //   0	5805	0	this	2
          //   423	5377	1	i	int
          //   129	102	2	localDate1	java.util.Date
          //   1628	3974	2	localException1	Exception
          //   147	1347	3	localDate2	java.util.Date
          //   372	818	4	str1	String
          //   420	506	5	localJSONArray	org.json.JSONArray
          //   436	1059	6	localObject1	Object
          //   1608	3	6	localException2	Exception
          //   1618	3	6	localException3	Exception
          //   1798	253	6	localObject2	Object
          //   2173	3	6	localException4	Exception
          //   2380	253	6	localObject3	Object
          //   2755	3	6	localException5	Exception
          //   2962	253	6	localObject4	Object
          //   3337	3	6	localException6	Exception
          //   3544	253	6	localObject5	Object
          //   3919	3	6	localException7	Exception
          //   4159	253	6	localObject6	Object
          //   4552	3	6	localException8	Exception
          //   4759	253	6	localObject7	Object
          //   5134	3	6	localException9	Exception
          //   5341	253	6	localObject8	Object
          //   5716	3	6	localException10	Exception
          //   1319	59	7	str2	String
          //   1634	3	7	localException11	Exception
          //   1924	3679	7	localDate3	java.util.Date
          //   2183	3	8	localException12	Exception
          //   2765	3	8	localException13	Exception
          //   3347	3	8	localException14	Exception
          //   3929	3	8	localException15	Exception
          //   4562	3	8	localException16	Exception
          //   5144	3	8	localException17	Exception
          //   5726	3	8	localException18	Exception
          // Exception table:
          //   from	to	target	type
          //   1304	1417	1608	java/lang/Exception
          //   1484	1605	1608	java/lang/Exception
          //   1636	1641	1608	java/lang/Exception
          //   1644	1687	1608	java/lang/Exception
          //   1078	1304	1618	java/lang/Exception
          //   1610	1615	1618	java/lang/Exception
          //   1690	1743	1618	java/lang/Exception
          //   2175	2180	1618	java/lang/Exception
          //   2239	2292	1618	java/lang/Exception
          //   2757	2762	1618	java/lang/Exception
          //   2821	2874	1618	java/lang/Exception
          //   3339	3344	1618	java/lang/Exception
          //   3403	3456	1618	java/lang/Exception
          //   3921	3926	1618	java/lang/Exception
          //   3985	4071	1618	java/lang/Exception
          //   4554	4559	1618	java/lang/Exception
          //   4618	4671	1618	java/lang/Exception
          //   5136	5141	1618	java/lang/Exception
          //   5200	5253	1618	java/lang/Exception
          //   5718	5723	1618	java/lang/Exception
          //   0	356	1628	java/lang/Exception
          //   356	422	1628	java/lang/Exception
          //   424	983	1628	java/lang/Exception
          //   983	1071	1628	java/lang/Exception
          //   1620	1625	1628	java/lang/Exception
          //   5782	5802	1628	java/lang/Exception
          //   1417	1484	1634	java/lang/Exception
          //   1743	1982	2173	java/lang/Exception
          //   2049	2170	2173	java/lang/Exception
          //   2185	2190	2173	java/lang/Exception
          //   2193	2236	2173	java/lang/Exception
          //   1982	2049	2183	java/lang/Exception
          //   2292	2564	2755	java/lang/Exception
          //   2631	2752	2755	java/lang/Exception
          //   2767	2772	2755	java/lang/Exception
          //   2775	2818	2755	java/lang/Exception
          //   2564	2631	2765	java/lang/Exception
          //   2874	3146	3337	java/lang/Exception
          //   3213	3334	3337	java/lang/Exception
          //   3349	3354	3337	java/lang/Exception
          //   3357	3400	3337	java/lang/Exception
          //   3146	3213	3347	java/lang/Exception
          //   3456	3728	3919	java/lang/Exception
          //   3795	3916	3919	java/lang/Exception
          //   3931	3936	3919	java/lang/Exception
          //   3939	3982	3919	java/lang/Exception
          //   3728	3795	3929	java/lang/Exception
          //   4071	4343	4552	java/lang/Exception
          //   4410	4549	4552	java/lang/Exception
          //   4564	4569	4552	java/lang/Exception
          //   4572	4615	4552	java/lang/Exception
          //   4343	4410	4562	java/lang/Exception
          //   4671	4943	5134	java/lang/Exception
          //   5010	5131	5134	java/lang/Exception
          //   5146	5151	5134	java/lang/Exception
          //   5154	5197	5134	java/lang/Exception
          //   4943	5010	5144	java/lang/Exception
          //   5253	5525	5716	java/lang/Exception
          //   5592	5713	5716	java/lang/Exception
          //   5728	5733	5716	java/lang/Exception
          //   5736	5779	5716	java/lang/Exception
          //   5525	5592	5726	java/lang/Exception
        }
      }.start();
      return;
    }
    catch (Exception paramString) {}
  }
  
  public IBinder onBind(Intent paramIntent)
  {
    Log.v("amriteshlatlong", "onBind");
    getCurrentLocation("1", getApplicationContext());
    return null;
  }
  
  public void onCreate()
  {
    Log.v("amriteshlatlong", "onCreate");
    this.h = new Handler();
    this.r = new Runnable()
    {
      public void run()
      {
        MyService.this.gps = new GPSTracker(MyService.this.getApplicationContext());
        if (MyService.this.gps.canGetLocation())
        {
          MyService.this.latitudeAmritesh = MyService.this.gps.getLatitude();
          MyService.this.longitudeAmritesh = MyService.this.gps.getLongitude();
          Log.v("amriteshlatlong", "" + MyService.this.gps.getLatitude());
          Log.v("amriteshlatlong", "" + MyService.this.gps.getLongitude());
          Log.v("amriteshlatlong", "" + MyService.this.latitudeAmritesh);
          Log.v("amriteshlatlong", "" + MyService.this.longitudeAmritesh);
        }
        for (;;)
        {
          MyService.this.getCurrentLocation("2", MyService.this.getApplicationContext());
          MyService.this.h.postDelayed(this, 2000L);
          return;
          MyService.this.gps.showSettingsAlert();
        }
      }
    };
    this.h.postDelayed(this.r, 2000L);
  }
  
  public void onDestroy()
  {
    this.h.removeCallbacks(this.r);
    new Thread(this.r).interrupt();
    Process.killProcess(Process.myPid());
    super.onDestroy();
  }
  
  public void onLocationChanged(Location paramLocation)
  {
    Log.v("amriteshlatlong", "onLocationChanged");
  }
  
  public void onProviderDisabled(String paramString)
  {
    Log.v("amriteshlatlong", "onProviderDisabled");
  }
  
  public void onProviderEnabled(String paramString)
  {
    Log.v("amriteshlatlong", "onProviderEnabled");
  }
  
  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    Log.v("amriteshlatlong", "onStartCommand");
    return super.onStartCommand(paramIntent, paramInt1, paramInt2);
  }
  
  public void onStatusChanged(String paramString, int paramInt, Bundle paramBundle)
  {
    Log.v("amriteshlatlong", "onStatusChanged");
  }
  
  private class sendNotification
    extends AsyncTask<String, Void, Bitmap>
  {
    String alertSound;
    Context ctx;
    String fenceMsgImage;
    String imageUrl;
    String internalUrl;
    String message;
    String webUrl;
    
    public sendNotification(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
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
      this.fenceMsgImage = paramString5;
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
      MyService.generateNotification(this.ctx, this.message, this.alertSound, this.webUrl, this.internalUrl, this.fenceMsgImage, paramBitmap);
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ons\geofence\MyService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */