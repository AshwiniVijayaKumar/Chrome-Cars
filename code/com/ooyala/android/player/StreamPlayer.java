package com.ooyala.android.player;

import android.os.Handler;
import android.os.Message;
import com.ooyala.android.DefaultPlayerInfo;
import com.ooyala.android.PlayerInfo;
import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

public abstract class StreamPlayer
  extends Player
{
  protected static final long TIMER_DELAY = 0L;
  protected static final long TIMER_PERIOD = 250L;
  public static PlayerInfo defaultPlayerInfo = new DefaultPlayerInfo();
  protected Timer _playheadUpdateTimer = null;
  private final Handler _playheadUpdateTimerHandler = new TimerHandler(this);
  private PlayerInfo customPlayerInfo;
  
  public PlayerInfo getPlayerInfo()
  {
    if (this.customPlayerInfo != null) {
      return this.customPlayerInfo;
    }
    return defaultPlayerInfo;
  }
  
  protected void notifyTimeChanged()
  {
    setChanged();
    notifyObservers("timeChanged");
  }
  
  public void setPlayerInfo(PlayerInfo paramPlayerInfo)
  {
    this.customPlayerInfo = paramPlayerInfo;
  }
  
  protected void startPlayheadTimer()
  {
    if (this._playheadUpdateTimer != null) {
      stopPlayheadTimer();
    }
    this._playheadUpdateTimer = new Timer();
    this._playheadUpdateTimer.scheduleAtFixedRate(new TimerTask()
    {
      public void run()
      {
        StreamPlayer.this._playheadUpdateTimerHandler.sendEmptyMessage(0);
      }
    }, 0L, 250L);
  }
  
  protected void stopPlayheadTimer()
  {
    if (this._playheadUpdateTimer != null)
    {
      this._playheadUpdateTimer.cancel();
      this._playheadUpdateTimer = null;
    }
  }
  
  private static class TimerHandler
    extends Handler
  {
    private int _lastPlayhead = -1;
    private WeakReference<StreamPlayer> _player;
    
    public TimerHandler(StreamPlayer paramStreamPlayer)
    {
      this._player = new WeakReference(paramStreamPlayer);
    }
    
    public void handleMessage(Message paramMessage)
    {
      paramMessage = (StreamPlayer)this._player.get();
      if ((paramMessage != null) && (this._lastPlayhead != paramMessage.currentTime()) && (paramMessage.isPlaying())) {
        paramMessage.notifyTimeChanged();
      }
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\player\StreamPlayer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */