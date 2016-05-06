package com.spoledge.aacdecoder;

import android.media.AudioTrack;

public abstract interface PlayerCallback
{
  public abstract void playerAudioTrackCreated(AudioTrack paramAudioTrack);
  
  public abstract void playerException(Throwable paramThrowable);
  
  public abstract void playerMetadata(String paramString1, String paramString2);
  
  public abstract void playerPCMFeedBuffer(boolean paramBoolean, int paramInt1, int paramInt2);
  
  public abstract void playerStarted();
  
  public abstract void playerStopped(int paramInt);
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\spoledge\aacdecoder\PlayerCallback.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */