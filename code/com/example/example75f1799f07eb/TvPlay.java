package com.example.example75f1799f07eb;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.MediaPlayer.OnCompletionListener;
import io.vov.vitamio.MediaPlayer.OnErrorListener;
import io.vov.vitamio.MediaPlayer.OnPreparedListener;
import io.vov.vitamio.widget.CenterLayout;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;
import java.io.PrintStream;

public class TvPlay
  extends Activity
  implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener
{
  ActionBar ab;
  AlertDialogManager alert = new AlertDialogManager();
  private TextView appName;
  private TextView appName2;
  private ImageButton bookmark;
  CenterLayout centerLayout;
  private TextView empty;
  private RelativeLayout headerbackground;
  private boolean isVedioLoad;
  RelativeLayout.LayoutParams layoutParams;
  private ProgressBar load;
  private VideoView mVideoView;
  private MediaController miController;
  private ImageButton playlistButton;
  private ImageButton recordingList;
  private ImageButton shareButton;
  private ImageButton tv_toc;
  private String url;
  
  private void error(String paramString)
  {
    this.load.setVisibility(8);
    this.mVideoView.setVisibility(8);
    this.empty.setVisibility(0);
    if (paramString != null) {
      this.empty.setText(paramString);
    }
  }
  
  private void loadComplete(MediaPlayer paramMediaPlayer)
  {
    this.load.setVisibility(8);
    this.empty.setVisibility(8);
    this.mVideoView.start();
    this.mVideoView.resume();
    this.isVedioLoad = true;
  }
  
  private void loading()
  {
    this.load.setVisibility(0);
    this.empty.setVisibility(8);
  }
  
  public void HeandalUI()
  {
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        if (TvPlay.this.mVideoView.isPlaying()) {}
      }
    }, 'ஸ');
  }
  
  public void close(View paramView)
  {
    finish();
  }
  
  public void init()
  {
    this.load = ((ProgressBar)findViewById(2131558792));
    this.empty = ((TextView)findViewById(2131558793));
    this.mVideoView = ((VideoView)findViewById(2131558618));
    Object localObject = new MediaController(this);
    this.mVideoView.setMediaController((MediaController)localObject);
    this.mVideoView.setOnCompletionListener(this);
    this.mVideoView.setOnPreparedListener(this);
    this.mVideoView.setOnErrorListener(this);
    localObject = Uri.parse(this.url);
    this.mVideoView.setVideoURI((Uri)localObject);
    this.mVideoView.requestFocus();
    loading();
  }
  
  public void onCompletion(MediaPlayer paramMediaPlayer)
  {
    Log.d("ONLINE TV", "Complete");
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    if (paramConfiguration.orientation == 2)
    {
      if (this.isVedioLoad) {
        this.mVideoView.isPortrait(true);
      }
      this.mVideoView.setVideoLayout(2, 0.0F);
      return;
    }
    if (this.isVedioLoad) {
      this.mVideoView.isPortrait(true);
    }
    this.mVideoView.setVideoLayout(1, 0.0F);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    if (!LibsChecker.checkVitamioLibs(this)) {
      return;
    }
    setContentView(2130903156);
    this.headerbackground = ((RelativeLayout)findViewById(2131558499));
    this.appName = ((TextView)findViewById(2131558500));
    this.appName.setText(2131230720);
    this.playlistButton = ((ImageButton)findViewById(2131558504));
    this.playlistButton.setVisibility(8);
    this.tv_toc = ((ImageButton)findViewById(2131558501));
    this.tv_toc.setVisibility(8);
    this.bookmark = ((ImageButton)findViewById(2131558502));
    this.bookmark.setVisibility(8);
    this.recordingList = ((ImageButton)findViewById(2131558503));
    this.recordingList.setVisibility(8);
    this.shareButton = ((ImageButton)findViewById(2131558505));
    this.shareButton.setVisibility(8);
    this.centerLayout = ((CenterLayout)findViewById(2131558791));
    this.layoutParams = new RelativeLayout.LayoutParams(-1, -1);
    paramBundle = getSharedPreferences("MyPrefs", 0).getString("HeaderBarbackgroundColor", "");
    System.out.println("krishna header color>>>" + paramBundle);
    Object localObject;
    String str;
    if (!paramBundle.equals("")) {
      if (paramBundle.contains("rgba"))
      {
        localObject = paramBundle.split(",");
        localObject[0] = localObject[0].split("\\(")[1];
        localObject[3] = localObject[3].split("\\)")[0];
        Integer.toHexString(Integer.parseInt(localObject[3]));
        paramBundle = Integer.toHexString(Integer.parseInt(localObject[0]));
        str = Integer.toHexString(Integer.parseInt(localObject[1]));
        localObject = Integer.toHexString(Integer.parseInt(localObject[2]));
        paramBundle = "#" + paramBundle + str + (String)localObject;
        this.headerbackground.setBackgroundColor(Color.parseColor(paramBundle.trim()));
      }
    }
    for (;;)
    {
      this.url = ((String)getIntent().getExtras().get("url"));
      init();
      try
      {
        this.mVideoView.setOnTouchListener(new View.OnTouchListener()
        {
          public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
          {
            boolean bool = true;
            switch (paramAnonymousMotionEvent.getAction())
            {
            default: 
              bool = false;
            case 1: 
              return bool;
            }
            TvPlay.this.HeandalUI();
            return true;
          }
        });
        return;
      }
      catch (Exception paramBundle)
      {
        return;
      }
      if (paramBundle.contains("rgb"))
      {
        localObject = paramBundle.split(",");
        localObject[0] = localObject[0].split("\\(")[1];
        localObject[2] = localObject[2].split("\\)")[0];
        paramBundle = Integer.toHexString(Integer.parseInt(localObject[0]));
        str = Integer.toHexString(Integer.parseInt(localObject[1]));
        localObject = Integer.toHexString(Integer.parseInt(localObject[2]));
        paramBundle = "#" + paramBundle + str + (String)localObject;
        this.headerbackground.setBackgroundColor(Color.parseColor(paramBundle.trim()));
      }
      else
      {
        this.headerbackground.setBackgroundColor(Color.parseColor(paramBundle.trim()));
        continue;
        this.headerbackground.setBackgroundColor(Color.parseColor("#33b5e5"));
      }
    }
  }
  
  public boolean onError(MediaPlayer paramMediaPlayer, int paramInt1, int paramInt2)
  {
    Log.d("ONLINE TV", "Error");
    error("Unable to play this channel.");
    return false;
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (paramMenuItem.getItemId() == 16908332) {
      finish();
    }
    return super.onOptionsItemSelected(paramMenuItem);
  }
  
  public void onPrepared(MediaPlayer paramMediaPlayer)
  {
    Log.d("ONLINE TV", "Prepared");
    loadComplete(paramMediaPlayer);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\example\example75f1799f07eb\TvPlay.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */