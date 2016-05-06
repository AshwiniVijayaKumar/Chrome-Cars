package com.appyjump.video.sdk;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.VideoView;
import java.io.PrintStream;

public class Interstitial
  extends Activity
{
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    getWindow().setFlags(1024, 1024);
    setContentView(R.layout.interstitial);
    final Object localObject = getIntent();
    paramBundle = ((Intent)localObject).getStringExtra("videoURL");
    localObject = ((Intent)localObject).getStringExtra("clickURL");
    System.out.println("videoURL:" + paramBundle);
    System.out.println("clickURL:" + (String)localObject);
    final VideoView localVideoView = (VideoView)findViewById(R.id.videoView);
    final ImageView localImageView = (ImageView)findViewById(R.id.closeAds);
    final ProgressBar localProgressBar = (ProgressBar)findViewById(R.id.progressBar1);
    FrameLayout localFrameLayout = (FrameLayout)findViewById(R.id.adLayout);
    localImageView.setVisibility(4);
    localVideoView.setVideoURI(Uri.parse(paramBundle));
    localVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener()
    {
      public void onPrepared(MediaPlayer paramAnonymousMediaPlayer)
      {
        localVideoView.start();
        localImageView.setVisibility(0);
        localProgressBar.setVisibility(8);
      }
    });
    localVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
    {
      public void onCompletion(MediaPlayer paramAnonymousMediaPlayer)
      {
        Interstitial.this.finish();
      }
    });
    localFrameLayout.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView = new Intent("android.intent.action.VIEW", Uri.parse(localObject));
        Interstitial.this.startActivity(paramAnonymousView);
      }
    });
    localImageView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Interstitial.this.finish();
      }
    });
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\appyjump\video\sdk\Interstitial.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */