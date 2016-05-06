package com.appyjump.video.sdk;

import java.util.TimerTask;

class ReloadTask
  extends TimerTask
{
  private final AdserveView mAdserveView;
  
  public ReloadTask(AdserveView paramAdserveView)
  {
    this.mAdserveView = paramAdserveView;
  }
  
  public void run()
  {
    this.mAdserveView.loadNextAd();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\appyjump\video\sdk\ReloadTask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */