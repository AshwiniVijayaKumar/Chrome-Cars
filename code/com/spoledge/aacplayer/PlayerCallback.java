package com.spoledge.aacplayer;

public abstract interface PlayerCallback
{
  public abstract void playerException(Throwable paramThrowable);
  
  public abstract void playerStarted();
  
  public abstract void playerStopped(int paramInt);
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\spoledge\aacplayer\PlayerCallback.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */