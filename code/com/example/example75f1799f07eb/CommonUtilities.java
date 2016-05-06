package com.example.example75f1799f07eb;

import android.content.Context;
import android.content.Intent;

public final class CommonUtilities
{
  static final String DISPLAY_MESSAGE_ACTION = "com.example.pushnotification.DISPLAY_MESSAGE";
  static final String EXTRA_MESSAGE = "message";
  static final String SENDER_ID = "755323621607";
  static final String SERVER_URL = "http://abhinav.pbodev.info/android/register.php";
  static final String TAG = "AndroidHive GCM";
  
  static void displayMessage(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent("com.example.pushnotification.DISPLAY_MESSAGE");
    localIntent.putExtra("message", paramString);
    paramContext.sendBroadcast(localIntent);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\example\example75f1799f07eb\CommonUtilities.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */