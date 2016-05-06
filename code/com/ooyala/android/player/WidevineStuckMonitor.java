package com.ooyala.android.player;

import com.ooyala.android.OoyalaPlayer;
import com.ooyala.android.item.Video;
import com.ooyala.android.util.DebugMode;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.atomic.AtomicBoolean;

final class WidevineStuckMonitor
  implements Observer
{
  private static final int END_TIME_WINDOW_MILLISECONDS = 15000;
  private static final int MAX_FREEZE_MILLISECONDS = 2000;
  private static final String TAG = "WidevineStuckMonitor";
  private final Player drmPlayer;
  private VideoAtWallMsec lastRecord;
  private final Listener listener;
  private final int monitorAfterMsec;
  private final AtomicBoolean onFrozenSent;
  private final OoyalaPlayer ooyalaPlayer;
  
  public WidevineStuckMonitor(OoyalaPlayer paramOoyalaPlayer, Player paramPlayer, Listener paramListener)
  {
    this.ooyalaPlayer = paramOoyalaPlayer;
    this.drmPlayer = paramPlayer;
    this.listener = paramListener;
    this.onFrozenSent = new AtomicBoolean();
    paramOoyalaPlayer = calculateMonitorAfterMsec(paramOoyalaPlayer.getCurrentItem());
    if (paramOoyalaPlayer != null)
    {
      this.ooyalaPlayer.addObserver(this);
      this.monitorAfterMsec = paramOoyalaPlayer.intValue();
      DebugMode.logV("WidevineStuckMonitor", "Constructor(): enabled, monitorAfterMsec=" + this.monitorAfterMsec);
      return;
    }
    this.monitorAfterMsec = Integer.MAX_VALUE;
    DebugMode.logV("WidevineStuckMonitor", "Constructor(): disabled, monitorAfterMsec=" + this.monitorAfterMsec);
  }
  
  private Integer calculateMonitorAfterMsec(Video paramVideo)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (paramVideo != null)
    {
      int i = paramVideo.getDuration();
      localObject1 = localObject2;
      if (i > 15000) {
        localObject1 = Integer.valueOf(Math.max(0, i - 15000));
      }
    }
    DebugMode.logV("WidevineStuckMonitor", "calculaeMonitorAfterMsec(): duration=" + paramVideo.getDuration() + ", oi=" + localObject1);
    return (Integer)localObject1;
  }
  
  private void checkFrozen()
  {
    if ((this.lastRecord != null) && (System.currentTimeMillis() - this.lastRecord.wallMsec >= 2000L))
    {
      DebugMode.logV("WidevineStuckMonitor", "doFreezeCheck(): looks frozen to me!");
      sendOnFrozen();
    }
  }
  
  private void checkInWindow(int paramInt)
  {
    if ((this.lastRecord == null) || (paramInt != this.lastRecord.videoMsec))
    {
      updateLastRecord(paramInt);
      return;
    }
    checkFrozen();
  }
  
  private void checkWhilePlaying()
  {
    int i = this.drmPlayer.currentTime();
    if (i >= this.monitorAfterMsec) {
      checkInWindow(i);
    }
  }
  
  private void sendOnFrozen()
  {
    if (this.onFrozenSent.compareAndSet(false, true))
    {
      DebugMode.logV("WidevineStuckMonitor", "sendOnFrozen(): sending");
      this.ooyalaPlayer.deleteObserver(this);
      this.listener.onFrozen();
    }
  }
  
  private void updateLastRecord(int paramInt)
  {
    this.lastRecord = new VideoAtWallMsec(paramInt);
  }
  
  public void destroy()
  {
    this.ooyalaPlayer.deleteObserver(this);
  }
  
  public void reset()
  {
    DebugMode.logV("WidevineStuckMonitor", "reset");
    this.ooyalaPlayer.addObserver(this);
    this.onFrozenSent.set(false);
  }
  
  public void update(Observable paramObservable, Object paramObject)
  {
    paramObservable = paramObject.toString();
    if ((this.drmPlayer.isPlaying()) && (paramObservable.equals("timeChanged"))) {
      checkWhilePlaying();
    }
  }
  
  public static abstract interface Listener
  {
    public abstract void onFrozen();
  }
  
  private static final class VideoAtWallMsec
  {
    public final int videoMsec;
    public final long wallMsec;
    
    public VideoAtWallMsec(int paramInt)
    {
      this.videoMsec = paramInt;
      this.wallMsec = System.currentTimeMillis();
    }
    
    public String toString()
    {
      return "[" + getClass().getSimpleName() + ":videoMsec=" + this.videoMsec + ",wallMsec=" + this.wallMsec + "]";
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\player\WidevineStuckMonitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */