package com.google.android.gms.auth;

import android.accounts.AccountManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.R.string;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import java.io.IOException;
import java.net.URISyntaxException;

public final class GoogleAuthUtil
{
  public static final String GOOGLE_ACCOUNT_TYPE = "com.google";
  public static final String KEY_ANDROID_PACKAGE_NAME;
  public static final String KEY_CALLER_UID;
  public static final String KEY_REQUEST_ACTIONS = "request_visible_actions";
  @Deprecated
  public static final String KEY_REQUEST_VISIBLE_ACTIVITIES = "request_visible_actions";
  public static final String KEY_SUPPRESS_PROGRESS_SCREEN = "suppressProgressScreen";
  private static final ComponentName vo;
  private static final ComponentName vp;
  private static final Intent vq;
  private static final Intent vr;
  
  static
  {
    if (Build.VERSION.SDK_INT >= 11)
    {
      KEY_CALLER_UID = "callerUid";
      if (Build.VERSION.SDK_INT < 14) {
        break label100;
      }
    }
    label100:
    for (;;)
    {
      KEY_ANDROID_PACKAGE_NAME = "androidPackageName";
      vo = new ComponentName("com.google.android.gms", "com.google.android.gms.auth.GetToken");
      vp = new ComponentName("com.google.android.gms", "com.google.android.gms.recovery.RecoveryService");
      vq = new Intent().setPackage("com.google.android.gms").setComponent(vo);
      vr = new Intent().setPackage("com.google.android.gms").setComponent(vp);
      return;
      break;
    }
  }
  
  private static boolean K(String paramString)
  {
    return ("NetworkError".equals(paramString)) || ("ServiceUnavailable".equals(paramString)) || ("Timeout".equals(paramString));
  }
  
  private static boolean L(String paramString)
  {
    return ("BadAuthentication".equals(paramString)) || ("CaptchaRequired".equals(paramString)) || ("DeviceManagementRequiredOrSyncDisabled".equals(paramString)) || ("NeedPermission".equals(paramString)) || ("NeedsBrowser".equals(paramString)) || ("UserCancel".equals(paramString)) || ("AppDownloadRequired".equals(paramString));
  }
  
  private static String a(Context paramContext, String paramString1, String paramString2, Bundle paramBundle)
    throws IOException, UserRecoverableNotifiedException, GoogleAuthException
  {
    Object localObject = paramBundle;
    if (paramBundle == null) {
      localObject = new Bundle();
    }
    try
    {
      paramString1 = getToken(paramContext, paramString1, paramString2, (Bundle)localObject);
      return paramString1;
    }
    catch (GooglePlayServicesAvailabilityException paramBundle)
    {
      localObject = GooglePlayServicesUtil.getErrorPendingIntent(paramBundle.getConnectionStatusCode(), paramContext, 0);
      Resources localResources = paramContext.getResources();
      Notification localNotification = new Notification(17301642, localResources.getString(R.string.auth_client_play_services_err_notification_msg), System.currentTimeMillis());
      localNotification.flags |= 0x10;
      paramString2 = paramContext.getApplicationInfo().name;
      paramString1 = paramString2;
      PackageManager localPackageManager;
      if (TextUtils.isEmpty(paramString2))
      {
        paramString1 = paramContext.getPackageName();
        localPackageManager = paramContext.getApplicationContext().getPackageManager();
      }
      try
      {
        paramString2 = localPackageManager.getApplicationInfo(paramContext.getPackageName(), 0);
        if (paramString2 != null) {
          paramString1 = localPackageManager.getApplicationLabel(paramString2).toString();
        }
        paramString1 = localResources.getString(R.string.auth_client_requested_by_msg, new Object[] { paramString1 });
        switch (paramBundle.getConnectionStatusCode())
        {
        default: 
          i = R.string.auth_client_using_bad_version_title;
          localNotification.setLatestEventInfo(paramContext, localResources.getString(i), paramString1, (PendingIntent)localObject);
          ((NotificationManager)paramContext.getSystemService("notification")).notify(39789, localNotification);
          throw new UserRecoverableNotifiedException("User intervention required. Notification has been pushed.");
        }
      }
      catch (PackageManager.NameNotFoundException paramString2)
      {
        for (;;)
        {
          paramString2 = null;
          continue;
          int i = R.string.auth_client_needs_installation_title;
          continue;
          i = R.string.auth_client_needs_update_title;
          continue;
          i = R.string.auth_client_needs_enabling_title;
        }
      }
    }
    catch (UserRecoverableAuthException paramContext)
    {
      throw new UserRecoverableNotifiedException("User intervention required. Notification has been pushed.");
    }
  }
  
  private static void b(Intent paramIntent)
  {
    if (paramIntent == null) {
      throw new IllegalArgumentException("Callack cannot be null.");
    }
    paramIntent = paramIntent.toUri(1);
    try
    {
      Intent.parseUri(paramIntent, 1);
      return;
    }
    catch (URISyntaxException paramIntent)
    {
      throw new IllegalArgumentException("Parameter callback contains invalid data. It must be serializable using toUri() and parseUri().");
    }
  }
  
  /* Error */
  public static void clearToken(Context paramContext, String paramString)
    throws GooglePlayServicesAvailabilityException, GoogleAuthException, IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 188	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   4: astore_2
    //   5: ldc_w 271
    //   8: invokestatic 276	com/google/android/gms/internal/er:ad	(Ljava/lang/String;)V
    //   11: aload_2
    //   12: invokestatic 280	com/google/android/gms/auth/GoogleAuthUtil:s	(Landroid/content/Context;)V
    //   15: new 119	android/os/Bundle
    //   18: dup
    //   19: invokespecial 120	android/os/Bundle:<init>	()V
    //   22: astore_3
    //   23: aload_0
    //   24: invokevirtual 169	android/content/Context:getApplicationInfo	()Landroid/content/pm/ApplicationInfo;
    //   27: getfield 283	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   30: astore_0
    //   31: aload_3
    //   32: ldc_w 285
    //   35: aload_0
    //   36: invokevirtual 288	android/os/Bundle:putString	(Ljava/lang/String;Ljava/lang/String;)V
    //   39: aload_3
    //   40: getstatic 40	com/google/android/gms/auth/GoogleAuthUtil:KEY_ANDROID_PACKAGE_NAME	Ljava/lang/String;
    //   43: invokevirtual 291	android/os/Bundle:containsKey	(Ljava/lang/String;)Z
    //   46: ifne +11 -> 57
    //   49: aload_3
    //   50: getstatic 40	com/google/android/gms/auth/GoogleAuthUtil:KEY_ANDROID_PACKAGE_NAME	Ljava/lang/String;
    //   53: aload_0
    //   54: invokevirtual 288	android/os/Bundle:putString	(Ljava/lang/String;Ljava/lang/String;)V
    //   57: new 293	com/google/android/gms/common/a
    //   60: dup
    //   61: invokespecial 294	com/google/android/gms/common/a:<init>	()V
    //   64: astore_0
    //   65: aload_2
    //   66: getstatic 70	com/google/android/gms/auth/GoogleAuthUtil:vq	Landroid/content/Intent;
    //   69: aload_0
    //   70: iconst_1
    //   71: invokevirtual 298	android/content/Context:bindService	(Landroid/content/Intent;Landroid/content/ServiceConnection;I)Z
    //   74: ifeq +94 -> 168
    //   77: aload_0
    //   78: invokevirtual 302	com/google/android/gms/common/a:dm	()Landroid/os/IBinder;
    //   81: invokestatic 307	com/google/android/gms/internal/s$a:a	(Landroid/os/IBinder;)Lcom/google/android/gms/internal/s;
    //   84: aload_1
    //   85: aload_3
    //   86: invokeinterface 312 3 0
    //   91: astore_1
    //   92: aload_1
    //   93: getstatic 317	com/google/android/gms/internal/do:wf	Ljava/lang/String;
    //   96: invokevirtual 320	android/os/Bundle:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   99: astore_3
    //   100: aload_1
    //   101: ldc_w 322
    //   104: invokevirtual 325	android/os/Bundle:getBoolean	(Ljava/lang/String;)Z
    //   107: ifne +43 -> 150
    //   110: new 111	com/google/android/gms/auth/GoogleAuthException
    //   113: dup
    //   114: aload_3
    //   115: invokespecial 326	com/google/android/gms/auth/GoogleAuthException:<init>	(Ljava/lang/String;)V
    //   118: athrow
    //   119: astore_1
    //   120: ldc_w 328
    //   123: ldc_w 330
    //   126: aload_1
    //   127: invokestatic 336	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   130: pop
    //   131: new 107	java/io/IOException
    //   134: dup
    //   135: ldc_w 338
    //   138: invokespecial 339	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   141: athrow
    //   142: astore_1
    //   143: aload_2
    //   144: aload_0
    //   145: invokevirtual 343	android/content/Context:unbindService	(Landroid/content/ServiceConnection;)V
    //   148: aload_1
    //   149: athrow
    //   150: aload_2
    //   151: aload_0
    //   152: invokevirtual 343	android/content/Context:unbindService	(Landroid/content/ServiceConnection;)V
    //   155: return
    //   156: astore_1
    //   157: new 111	com/google/android/gms/auth/GoogleAuthException
    //   160: dup
    //   161: ldc_w 345
    //   164: invokespecial 326	com/google/android/gms/auth/GoogleAuthException:<init>	(Ljava/lang/String;)V
    //   167: athrow
    //   168: new 107	java/io/IOException
    //   171: dup
    //   172: ldc_w 347
    //   175: invokespecial 339	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   178: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	179	0	paramContext	Context
    //   0	179	1	paramString	String
    //   4	147	2	localContext	Context
    //   22	93	3	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   77	119	119	android/os/RemoteException
    //   77	119	142	finally
    //   120	142	142	finally
    //   157	168	142	finally
    //   77	119	156	java/lang/InterruptedException
  }
  
  public static String getToken(Context paramContext, String paramString1, String paramString2)
    throws IOException, UserRecoverableAuthException, GoogleAuthException
  {
    return getToken(paramContext, paramString1, paramString2, new Bundle());
  }
  
  /* Error */
  public static String getToken(Context paramContext, String paramString1, String paramString2, Bundle paramBundle)
    throws IOException, UserRecoverableAuthException, GoogleAuthException
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 188	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   4: astore 5
    //   6: ldc_w 271
    //   9: invokestatic 276	com/google/android/gms/internal/er:ad	(Ljava/lang/String;)V
    //   12: aload 5
    //   14: invokestatic 280	com/google/android/gms/auth/GoogleAuthUtil:s	(Landroid/content/Context;)V
    //   17: aload_3
    //   18: ifnonnull +109 -> 127
    //   21: new 119	android/os/Bundle
    //   24: dup
    //   25: invokespecial 120	android/os/Bundle:<init>	()V
    //   28: astore_3
    //   29: aload_0
    //   30: invokevirtual 169	android/content/Context:getApplicationInfo	()Landroid/content/pm/ApplicationInfo;
    //   33: getfield 283	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   36: astore_0
    //   37: aload_3
    //   38: ldc_w 285
    //   41: aload_0
    //   42: invokevirtual 288	android/os/Bundle:putString	(Ljava/lang/String;Ljava/lang/String;)V
    //   45: aload_3
    //   46: getstatic 40	com/google/android/gms/auth/GoogleAuthUtil:KEY_ANDROID_PACKAGE_NAME	Ljava/lang/String;
    //   49: invokevirtual 291	android/os/Bundle:containsKey	(Ljava/lang/String;)Z
    //   52: ifne +11 -> 63
    //   55: aload_3
    //   56: getstatic 40	com/google/android/gms/auth/GoogleAuthUtil:KEY_ANDROID_PACKAGE_NAME	Ljava/lang/String;
    //   59: aload_0
    //   60: invokevirtual 288	android/os/Bundle:putString	(Ljava/lang/String;Ljava/lang/String;)V
    //   63: new 293	com/google/android/gms/common/a
    //   66: dup
    //   67: invokespecial 294	com/google/android/gms/common/a:<init>	()V
    //   70: astore_0
    //   71: aload 5
    //   73: getstatic 70	com/google/android/gms/auth/GoogleAuthUtil:vq	Landroid/content/Intent;
    //   76: aload_0
    //   77: iconst_1
    //   78: invokevirtual 298	android/content/Context:bindService	(Landroid/content/Intent;Landroid/content/ServiceConnection;I)Z
    //   81: ifeq +163 -> 244
    //   84: aload_0
    //   85: invokevirtual 302	com/google/android/gms/common/a:dm	()Landroid/os/IBinder;
    //   88: invokestatic 307	com/google/android/gms/internal/s$a:a	(Landroid/os/IBinder;)Lcom/google/android/gms/internal/s;
    //   91: aload_1
    //   92: aload_2
    //   93: aload_3
    //   94: invokeinterface 351 4 0
    //   99: astore_1
    //   100: aload_1
    //   101: ldc_w 353
    //   104: invokevirtual 320	android/os/Bundle:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   107: astore_2
    //   108: aload_2
    //   109: invokestatic 180	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   112: istore 4
    //   114: iload 4
    //   116: ifne +23 -> 139
    //   119: aload 5
    //   121: aload_0
    //   122: invokevirtual 343	android/content/Context:unbindService	(Landroid/content/ServiceConnection;)V
    //   125: aload_2
    //   126: areturn
    //   127: new 119	android/os/Bundle
    //   130: dup
    //   131: aload_3
    //   132: invokespecial 356	android/os/Bundle:<init>	(Landroid/os/Bundle;)V
    //   135: astore_3
    //   136: goto -107 -> 29
    //   139: aload_1
    //   140: ldc_w 358
    //   143: invokevirtual 320	android/os/Bundle:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   146: astore_2
    //   147: aload_1
    //   148: ldc_w 360
    //   151: invokevirtual 364	android/os/Bundle:getParcelable	(Ljava/lang/String;)Landroid/os/Parcelable;
    //   154: checkcast 58	android/content/Intent
    //   157: astore_1
    //   158: aload_2
    //   159: invokestatic 366	com/google/android/gms/auth/GoogleAuthUtil:L	(Ljava/lang/String;)Z
    //   162: ifeq +45 -> 207
    //   165: new 115	com/google/android/gms/auth/UserRecoverableAuthException
    //   168: dup
    //   169: aload_2
    //   170: aload_1
    //   171: invokespecial 369	com/google/android/gms/auth/UserRecoverableAuthException:<init>	(Ljava/lang/String;Landroid/content/Intent;)V
    //   174: athrow
    //   175: astore_1
    //   176: ldc_w 328
    //   179: ldc_w 330
    //   182: aload_1
    //   183: invokestatic 336	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   186: pop
    //   187: new 107	java/io/IOException
    //   190: dup
    //   191: ldc_w 338
    //   194: invokespecial 339	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   197: athrow
    //   198: astore_1
    //   199: aload 5
    //   201: aload_0
    //   202: invokevirtual 343	android/content/Context:unbindService	(Landroid/content/ServiceConnection;)V
    //   205: aload_1
    //   206: athrow
    //   207: aload_2
    //   208: invokestatic 371	com/google/android/gms/auth/GoogleAuthUtil:K	(Ljava/lang/String;)Z
    //   211: ifeq +24 -> 235
    //   214: new 107	java/io/IOException
    //   217: dup
    //   218: aload_2
    //   219: invokespecial 339	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   222: athrow
    //   223: astore_1
    //   224: new 111	com/google/android/gms/auth/GoogleAuthException
    //   227: dup
    //   228: ldc_w 345
    //   231: invokespecial 326	com/google/android/gms/auth/GoogleAuthException:<init>	(Ljava/lang/String;)V
    //   234: athrow
    //   235: new 111	com/google/android/gms/auth/GoogleAuthException
    //   238: dup
    //   239: aload_2
    //   240: invokespecial 326	com/google/android/gms/auth/GoogleAuthException:<init>	(Ljava/lang/String;)V
    //   243: athrow
    //   244: new 107	java/io/IOException
    //   247: dup
    //   248: ldc_w 347
    //   251: invokespecial 339	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   254: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	255	0	paramContext	Context
    //   0	255	1	paramString1	String
    //   0	255	2	paramString2	String
    //   0	255	3	paramBundle	Bundle
    //   112	3	4	bool	boolean
    //   4	196	5	localContext	Context
    // Exception table:
    //   from	to	target	type
    //   84	114	175	android/os/RemoteException
    //   139	175	175	android/os/RemoteException
    //   207	223	175	android/os/RemoteException
    //   235	244	175	android/os/RemoteException
    //   84	114	198	finally
    //   139	175	198	finally
    //   176	198	198	finally
    //   207	223	198	finally
    //   224	235	198	finally
    //   235	244	198	finally
    //   84	114	223	java/lang/InterruptedException
    //   139	175	223	java/lang/InterruptedException
    //   207	223	223	java/lang/InterruptedException
    //   235	244	223	java/lang/InterruptedException
  }
  
  public static String getTokenWithNotification(Context paramContext, String paramString1, String paramString2, Bundle paramBundle)
    throws IOException, UserRecoverableNotifiedException, GoogleAuthException
  {
    Bundle localBundle = paramBundle;
    if (paramBundle == null) {
      localBundle = new Bundle();
    }
    localBundle.putBoolean("handle_notification", true);
    return a(paramContext, paramString1, paramString2, localBundle);
  }
  
  public static String getTokenWithNotification(Context paramContext, String paramString1, String paramString2, Bundle paramBundle, Intent paramIntent)
    throws IOException, UserRecoverableNotifiedException, GoogleAuthException
  {
    b(paramIntent);
    Bundle localBundle = paramBundle;
    if (paramBundle == null) {
      localBundle = new Bundle();
    }
    localBundle.putParcelable("callback_intent", paramIntent);
    localBundle.putBoolean("handle_notification", true);
    return a(paramContext, paramString1, paramString2, localBundle);
  }
  
  public static String getTokenWithNotification(Context paramContext, String paramString1, String paramString2, Bundle paramBundle1, String paramString3, Bundle paramBundle2)
    throws IOException, UserRecoverableNotifiedException, GoogleAuthException
  {
    if (TextUtils.isEmpty(paramString3)) {
      throw new IllegalArgumentException("Authority cannot be empty or null.");
    }
    Bundle localBundle = paramBundle1;
    if (paramBundle1 == null) {
      localBundle = new Bundle();
    }
    paramBundle1 = paramBundle2;
    if (paramBundle2 == null) {
      paramBundle1 = new Bundle();
    }
    ContentResolver.validateSyncExtrasBundle(paramBundle1);
    localBundle.putString("authority", paramString3);
    localBundle.putBundle("sync_extras", paramBundle1);
    localBundle.putBoolean("handle_notification", true);
    return a(paramContext, paramString1, paramString2, localBundle);
  }
  
  @Deprecated
  public static void invalidateToken(Context paramContext, String paramString)
  {
    AccountManager.get(paramContext).invalidateAuthToken("com.google", paramString);
  }
  
  private static void s(Context paramContext)
    throws GooglePlayServicesAvailabilityException, GoogleAuthException
  {
    try
    {
      GooglePlayServicesUtil.s(paramContext);
      return;
    }
    catch (GooglePlayServicesRepairableException paramContext)
    {
      throw new GooglePlayServicesAvailabilityException(paramContext.getConnectionStatusCode(), paramContext.getMessage(), paramContext.getIntent());
    }
    catch (GooglePlayServicesNotAvailableException paramContext)
    {
      throw new GoogleAuthException(paramContext.getMessage());
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\auth\GoogleAuthUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */