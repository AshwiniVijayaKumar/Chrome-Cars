package com.ons.musicplayer;

public class Utilities
{
  public int getProgressPercentage(long paramLong1, long paramLong2)
  {
    paramLong1 = (int)(paramLong1 / 1000L);
    paramLong2 = (int)(paramLong2 / 1000L);
    return Double.valueOf(paramLong1 / paramLong2 * 100.0D).intValue();
  }
  
  public String milliSecondsToTimer(long paramLong)
  {
    String str1 = "";
    int i = (int)(paramLong / 3600000L);
    int j = (int)(paramLong % 3600000L) / 60000;
    int k = (int)(paramLong % 3600000L % 60000L / 1000L);
    if (i > 0) {
      str1 = i + ":";
    }
    if (k < 10) {}
    for (String str2 = "0" + k;; str2 = "" + k) {
      return str1 + j + ":" + str2;
    }
  }
  
  public int progressToTimer(int paramInt1, int paramInt2)
  {
    paramInt2 /= 1000;
    return (int)(paramInt1 / 100.0D * paramInt2) * 1000;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ons\musicplayer\Utilities.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */