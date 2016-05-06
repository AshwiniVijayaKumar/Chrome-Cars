package com.example.example75f1799f07eb;

import android.content.Context;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;

public abstract class WakeLocker
{
  private static PowerManager.WakeLock wakeLock;
  
  public static void acquire(Context paramContext)
  {
    if (wakeLock != null) {
      wakeLock.release();
    }
    wakeLock = ((PowerManager)paramContext.getSystemService("power")).newWakeLock(805306394, "WakeLock");
    wakeLock.acquire();
  }
  
  public static void release()
  {
    if (wakeLock != null) {
      wakeLock.release();
    }
    wakeLock = null;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\example\example75f1799f07eb\WakeLocker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */