package com.spoledge.aacplayer;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Handler;

public class AACPlayerActivity
  implements PlayerCallback
{
  private AACFileChunkPlayer aacFileChunkPlayer;
  private AACPlayer aacPlayer;
  String mStreamUrl = "";
  Context mcontext;
  private boolean playerStarted;
  private Handler uiHandler;
  
  public AACPlayerActivity(Context paramContext, String paramString)
  {
    this.mcontext = paramContext;
    this.mStreamUrl = paramString;
    startAudioStreaming();
  }
  
  public void playerException(Throwable paramThrowable)
  {
    this.uiHandler.post(new Runnable()
    {
      public void run()
      {
        new AlertDialog.Builder(AACPlayerActivity.this.mcontext).setTitle("Error").setMessage("Unable To play").setNeutralButton("OK", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            paramAnonymous2DialogInterface.cancel();
          }
        }).show();
        if (AACPlayerActivity.this.playerStarted) {
          AACPlayerActivity.this.playerStopped(0);
        }
      }
    });
  }
  
  public void playerStarted()
  {
    this.uiHandler.post(new Runnable()
    {
      public void run()
      {
        AACPlayerActivity.access$002(AACPlayerActivity.this, true);
      }
    });
  }
  
  public void playerStopped(int paramInt)
  {
    this.uiHandler.post(new Runnable()
    {
      public void run()
      {
        AACPlayerActivity.access$002(AACPlayerActivity.this, false);
      }
    });
  }
  
  public void start(int paramInt)
  {
    stop();
    this.aacPlayer = new ArrayAACPlayer(ArrayDecoder.create(paramInt), this, 500, 500);
    this.aacPlayer.playAsync(this.mStreamUrl);
  }
  
  public void startAudioStreaming()
  {
    start(1);
    this.uiHandler = new Handler();
  }
  
  public void stop()
  {
    if (this.aacFileChunkPlayer != null)
    {
      this.aacFileChunkPlayer.stop();
      this.aacFileChunkPlayer = null;
    }
    if (this.aacPlayer != null)
    {
      this.aacPlayer.stop();
      this.aacPlayer = null;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\spoledge\aacplayer\AACPlayerActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */