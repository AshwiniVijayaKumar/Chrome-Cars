package com.example.example75f1799f07eb;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

public class M3uPlayer
  extends Activity
  implements MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener
{
  ProgressBar bar;
  VideoView mVideoView;
  Button startB;
  String url;
  
  public void onCompletion(MediaPlayer paramMediaPlayer)
  {
    this.bar.setVisibility(8);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903111);
    this.bar = ((ProgressBar)findViewById(2131558458));
    try
    {
      this.url = getIntent().getExtras().getString("url");
      this.mVideoView = ((VideoView)findViewById(2131558618));
      this.mVideoView.setOnPreparedListener(this);
      this.mVideoView.setVideoURI(Uri.parse(this.url));
      this.mVideoView.setMediaController(new MediaController(this));
      this.mVideoView.requestFocus();
      this.mVideoView.setOnCompletionListener(this);
      new Thread(new Runnable()
      {
        public void run()
        {
          M3uPlayer.this.mVideoView.start();
        }
      }).start();
      return;
    }
    catch (Exception paramBundle)
    {
      paramBundle.printStackTrace();
    }
  }
  
  public void onPrepared(MediaPlayer paramMediaPlayer)
  {
    this.bar.setVisibility(8);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\example\example75f1799f07eb\M3uPlayer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */