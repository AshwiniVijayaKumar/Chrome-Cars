package com.adsdk.sdk.video;

import java.io.Serializable;

public class TrackerData
  implements Serializable
{
  public static final int TYPE_COMPLETE = 1;
  public static final int TYPE_FIRSTQUARTILE = 3;
  public static final int TYPE_MIDPOINT = 2;
  public static final int TYPE_MUTE = 8;
  public static final int TYPE_PAUSE = 6;
  public static final int TYPE_REPLAY = 11;
  public static final int TYPE_SKIP = 10;
  public static final int TYPE_START = 0;
  public static final int TYPE_THIRDQUARTILE = 4;
  public static final int TYPE_TIME = 5;
  public static final int TYPE_UNMUTE = 9;
  public static final int TYPE_UNPAUSE = 7;
  private static final long serialVersionUID = -8986291192914098710L;
  int time;
  int type;
  String url;
  
  public void reset()
  {
    this.url = null;
    this.type = -1;
    this.time = -1;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\adsdk\sdk\video\TrackerData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */