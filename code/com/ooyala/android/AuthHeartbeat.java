package com.ooyala.android;

import android.os.Handler;
import java.util.Timer;
import java.util.TimerTask;

class AuthHeartbeat
{
  private final PlayerAPIClient _apiClient;
  private OnAuthHeartbeatErrorListener _authHeartbeatErrorListener;
  private final String _embedCode;
  private Handler _handler = new Handler();
  private Timer _timer = new Timer("AuthHeartbeat");
  
  public AuthHeartbeat(PlayerAPIClient paramPlayerAPIClient, String paramString)
  {
    this._apiClient = paramPlayerAPIClient;
    this._embedCode = paramString;
  }
  
  public void setAuthHeartbeatErrorListener(OnAuthHeartbeatErrorListener paramOnAuthHeartbeatErrorListener)
  {
    this._authHeartbeatErrorListener = paramOnAuthHeartbeatErrorListener;
  }
  
  public void start()
  {
    stop();
    this._timer = new Timer("AuthHeartbeat");
    this._timer.scheduleAtFixedRate(new AuthHeartbeatTimerTask(), 0L, this._apiClient.getHeartbeatInterval() * 1000);
  }
  
  public void stop()
  {
    if (this._timer != null) {
      this._timer.cancel();
    }
    this._timer = null;
  }
  
  class AuthHeartbeatTimerTask
    extends TimerTask
  {
    AuthHeartbeatTimerTask() {}
    
    private void sendError(final OoyalaException paramOoyalaException)
    {
      AuthHeartbeat.this._handler.post(new Runnable()
      {
        public void run()
        {
          AuthHeartbeat.OnAuthHeartbeatErrorListener localOnAuthHeartbeatErrorListener = AuthHeartbeat.this._authHeartbeatErrorListener;
          if (localOnAuthHeartbeatErrorListener != null) {
            localOnAuthHeartbeatErrorListener.onAuthHeartbeatError(paramOoyalaException);
          }
        }
      });
    }
    
    private void tryHeartbeat(int paramInt)
    {
      OoyalaException localOoyalaException1 = null;
      try
      {
        if (!AuthHeartbeat.this._apiClient.authorizeHeartbeat(AuthHeartbeat.this._embedCode)) {
          localOoyalaException1 = new OoyalaException(OoyalaException.OoyalaErrorCode.ERROR_AUTHORIZATION_HEARTBEAT_FAILED, "Unauthorized");
        }
        if (localOoyalaException1 != null)
        {
          if (paramInt > 0) {
            tryHeartbeat(paramInt - 1);
          }
        }
        else {
          return;
        }
      }
      catch (OoyalaException localOoyalaException2)
      {
        for (;;) {}
        sendError(localOoyalaException2);
      }
    }
    
    public void run()
    {
      tryHeartbeat(3);
    }
  }
  
  public static abstract interface OnAuthHeartbeatErrorListener
  {
    public abstract void onAuthHeartbeatError(OoyalaException paramOoyalaException);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\AuthHeartbeat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */