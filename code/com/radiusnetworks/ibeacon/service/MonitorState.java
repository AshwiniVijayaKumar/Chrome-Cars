package com.radiusnetworks.ibeacon.service;

import android.util.Log;
import java.util.Date;

public class MonitorState
{
  public static long INSIDE_EXPIRATION_MILLIS = 10000L;
  private static final String TAG = "MonitorState";
  private Callback callback;
  private boolean inside = false;
  private long lastSeenTime = 0L;
  
  public MonitorState(Callback paramCallback)
  {
    this.callback = paramCallback;
  }
  
  public Callback getCallback()
  {
    return this.callback;
  }
  
  public boolean isInside()
  {
    return (this.inside) && (!isNewlyOutside());
  }
  
  public boolean isNewlyOutside()
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (this.inside)
    {
      bool1 = bool2;
      if (this.lastSeenTime > 0L)
      {
        bool1 = bool2;
        if (new Date().getTime() - this.lastSeenTime > INSIDE_EXPIRATION_MILLIS)
        {
          this.inside = false;
          Log.d("MonitorState", "We are newly outside the region because the lastSeenTime of " + this.lastSeenTime + " was " + (new Date().getTime() - this.lastSeenTime) + " seconds ago, and that is over the expiration duration of  " + INSIDE_EXPIRATION_MILLIS);
          this.lastSeenTime = 0L;
          bool1 = true;
        }
      }
    }
    return bool1;
  }
  
  public boolean markInside()
  {
    this.lastSeenTime = new Date().getTime();
    if (!this.inside)
    {
      this.inside = true;
      return true;
    }
    return false;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\radiusnetworks\ibeacon\service\MonitorState.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */