package com.ooyala.android.player;

import android.widget.FrameLayout;
import com.ooyala.android.AdsLearnMoreInterface;
import com.ooyala.android.OoyalaPlayer;
import com.ooyala.android.OoyalaPlayer.State;
import com.ooyala.android.StateNotifier;
import com.ooyala.android.item.AdSpot;
import java.net.URL;
import java.util.Observable;

public abstract class AdMoviePlayer
  extends MoviePlayer
  implements AdsLearnMoreInterface
{
  private StateNotifier _notifier;
  
  public static void ping(URL paramURL)
  {
    if (paramURL == null) {
      return;
    }
    new PingTask().execute(new URL[] { paramURL });
  }
  
  public abstract AdSpot getAd();
  
  public StateNotifier getNotifier()
  {
    return this._notifier;
  }
  
  public void init(OoyalaPlayer paramOoyalaPlayer, AdSpot paramAdSpot, StateNotifier paramStateNotifier)
  {
    this._notifier = paramStateNotifier;
  }
  
  protected void setState(OoyalaPlayer.State paramState)
  {
    if (this._notifier != null) {
      this._notifier.setState(paramState);
    }
    super.setState(paramState);
  }
  
  public void update(Observable paramObservable, Object paramObject)
  {
    if (this._notifier == null) {
      super.update(paramObservable, paramObject);
    }
    do
    {
      return;
      paramObservable = (String)paramObject;
      if (paramObservable == "stateChanged")
      {
        this._notifier.setState(getState());
        return;
      }
    } while (paramObservable != "timeChanged");
    this._notifier.notifyPlayheadChange();
  }
  
  public void updateLearnMoreButton(FrameLayout paramFrameLayout, int paramInt) {}
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\player\AdMoviePlayer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */