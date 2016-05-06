package com.ooyala.android.player;

import android.view.View;
import com.ooyala.android.OoyalaException;
import com.ooyala.android.OoyalaPlayer;
import com.ooyala.android.OoyalaPlayer.SeekStyle;
import com.ooyala.android.OoyalaPlayer.State;
import com.ooyala.android.item.Stream;
import com.ooyala.android.plugin.LifeCycleInterface;
import java.util.Observable;
import java.util.Set;

public class Player
  extends Observable
  implements PlayerInterface, LifeCycleInterface
{
  protected int _buffer = 0;
  protected OoyalaException _error = null;
  protected boolean _fullscreen = false;
  protected OoyalaPlayer _parent = null;
  protected boolean _pausable = true;
  protected boolean _resizeQueued = false;
  protected OoyalaPlayer.State _state = OoyalaPlayer.State.INIT;
  protected ControlSharingSurfaceView _view = null;
  
  public int buffer()
  {
    return 0;
  }
  
  public int currentTime()
  {
    return 0;
  }
  
  public void destroy() {}
  
  public int duration()
  {
    return 0;
  }
  
  public int getBufferPercentage()
  {
    return this._buffer;
  }
  
  public OoyalaException getError()
  {
    return this._error;
  }
  
  public OoyalaPlayer.SeekStyle getSeekStyle()
  {
    return OoyalaPlayer.SeekStyle.BASIC;
  }
  
  public OoyalaPlayer.State getState()
  {
    return this._state;
  }
  
  public View getView()
  {
    return this._view;
  }
  
  public void init(OoyalaPlayer paramOoyalaPlayer, Set<Stream> paramSet) {}
  
  public boolean isLiveClosedCaptionsAvailable()
  {
    return false;
  }
  
  public boolean isPlaying()
  {
    return false;
  }
  
  public int livePlayheadPercentage()
  {
    return 0;
  }
  
  public void pause() {}
  
  public void play() {}
  
  public void reset() {}
  
  public void resume() {}
  
  public void resume(int paramInt, OoyalaPlayer.State paramState) {}
  
  public void seekToPercentLive(int paramInt) {}
  
  public void seekToTime(int paramInt) {}
  
  public boolean seekable()
  {
    return false;
  }
  
  public void setClosedCaptionsLanguage(String paramString) {}
  
  public void setParent(OoyalaPlayer paramOoyalaPlayer)
  {
    this._parent = paramOoyalaPlayer;
  }
  
  protected void setState(OoyalaPlayer.State paramState)
  {
    this._state = paramState;
    super.setChanged();
    super.notifyObservers("stateChanged");
  }
  
  public void stop() {}
  
  public void suspend() {}
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\player\Player.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */