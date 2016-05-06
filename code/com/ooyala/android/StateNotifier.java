package com.ooyala.android;

import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class StateNotifier
{
  private Set<StateNotifierListener> _listeners;
  private WeakReference<OoyalaPlayer> _player;
  private OoyalaPlayer.State _state;
  
  StateNotifier(OoyalaPlayer paramOoyalaPlayer)
  {
    this._player = new WeakReference(paramOoyalaPlayer);
    this._listeners = new HashSet();
  }
  
  public void addListener(StateNotifierListener paramStateNotifierListener)
  {
    this._listeners.add(paramStateNotifierListener);
  }
  
  public OoyalaPlayer.State getState()
  {
    return this._state;
  }
  
  public void notifyAdSkipped()
  {
    if (this._player.get() != null) {
      ((OoyalaPlayer)this._player.get()).notifyPluginEvent(this, "adSkipped");
    }
  }
  
  public void notifyBufferChange()
  {
    if (this._player.get() != null) {
      ((OoyalaPlayer)this._player.get()).notifyPluginEvent(this, "bufferChanged");
    }
  }
  
  public void notifyPlayheadChange()
  {
    if (this._player.get() != null) {
      ((OoyalaPlayer)this._player.get()).notifyPluginEvent(this, "timeChanged");
    }
  }
  
  public void removeListener(StateNotifierListener paramStateNotifierListener)
  {
    this._listeners.remove(paramStateNotifierListener);
  }
  
  public void setState(OoyalaPlayer.State paramState)
  {
    OoyalaPlayer.State localState = this._state;
    this._state = paramState;
    Iterator localIterator = this._listeners.iterator();
    while (localIterator.hasNext()) {
      ((StateNotifierListener)localIterator.next()).onStateChange(this);
    }
    if (this._player.get() != null) {
      ((OoyalaPlayer)this._player.get()).notifyPluginStateChange(this, localState, paramState);
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\StateNotifier.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */