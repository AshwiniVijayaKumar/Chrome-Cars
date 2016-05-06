package com.newpedometer;

import android.text.format.Time;

public class Utils
{
  private static Utils instance = null;
  
  public static long currentTimeInMillis()
  {
    Time localTime = new Time();
    localTime.setToNow();
    return localTime.toMillis(false);
  }
  
  public static Utils getInstance()
  {
    if (instance == null) {
      instance = new Utils();
    }
    return instance;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\newpedometer\Utils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */