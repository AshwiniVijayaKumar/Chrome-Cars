package com.ooyala.android.plugin;

import com.ooyala.android.OoyalaPlayer.State;

public abstract interface LifeCycleInterface
{
  public abstract void destroy();
  
  public abstract void reset();
  
  public abstract void resume();
  
  public abstract void resume(int paramInt, OoyalaPlayer.State paramState);
  
  public abstract void suspend();
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\plugin\LifeCycleInterface.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */