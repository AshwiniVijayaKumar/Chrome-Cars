package com.ons.chat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;
import java.io.PrintStream;
import java.util.List;

public class Playscreen
  extends Activity
{
  public static int position = 0;
  private TextView appName;
  Intent intent;
  MediaController media_Controller;
  DisplayMetrics metrics;
  SurfaceView sur_View;
  VideoView video_player_view;
  
  public void close(View paramView)
  {
    finish();
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(7);
    setContentView(2130903160);
    getWindow().setFeatureInt(7, 2130903063);
    paramBundle = getResources().getString(2131230720);
    this.appName = ((TextView)findViewById(2131558500));
    this.appName.setText(paramBundle);
    this.intent = getIntent();
    position = this.intent.getIntExtra("path", 0);
    this.video_player_view = ((VideoView)findViewById(2131558797));
    this.media_Controller = new MediaController(this);
    this.metrics = getApplicationContext().getResources().getDisplayMetrics();
    int i = this.metrics.widthPixels;
    int j = this.metrics.heightPixels;
    this.video_player_view.setMinimumWidth(i);
    this.video_player_view.setMinimumHeight(j);
    new BackgroundAsyncTask().execute(new String[] { (String)AllNameList.path1.get(position) });
    System.out.println("playscreen path" + (String)AllNameList.path1.get(position));
  }
  
  public class BackgroundAsyncTask
    extends AsyncTask<String, Uri, Void>
  {
    Integer track = Integer.valueOf(0);
    
    public BackgroundAsyncTask() {}
    
    protected Void doInBackground(String... paramVarArgs)
    {
      try
      {
        publishProgress(new Uri[] { Uri.parse(paramVarArgs[0]) });
        return null;
      }
      catch (Exception paramVarArgs)
      {
        for (;;)
        {
          paramVarArgs.printStackTrace();
        }
      }
    }
    
    protected void onPreExecute() {}
    
    protected void onProgressUpdate(Uri... paramVarArgs)
    {
      try
      {
        Playscreen.this.media_Controller = new MediaController(Playscreen.this);
        Playscreen.this.video_player_view.setMediaController(Playscreen.this.media_Controller);
        Playscreen.this.media_Controller.setPrevNextListeners(new View.OnClickListener()new View.OnClickListener
        {
          public void onClick(View paramAnonymousView)
          {
            if (Playscreen.position < AllNameList.path1.size() - 1)
            {
              paramAnonymousView = AllNameList.path1;
              int i = Playscreen.position;
              Playscreen.position = i + 1;
              paramAnonymousView.get(i);
              new Playscreen.BackgroundAsyncTask(Playscreen.this).execute(new String[] { (String)AllNameList.path1.get(Playscreen.position) });
              System.out.println("position next--" + Playscreen.position);
            }
          }
        }, new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            if (Playscreen.position > 0)
            {
              paramAnonymousView = AllNameList.path1;
              int i = Playscreen.position;
              Playscreen.position = i - 1;
              paramAnonymousView.get(i);
              new Playscreen.BackgroundAsyncTask(Playscreen.this).execute(new String[] { (String)AllNameList.path1.get(Playscreen.position) });
              System.out.println("position back--" + Playscreen.position);
            }
          }
        });
        Playscreen.this.video_player_view.setVideoURI(paramVarArgs[0]);
        Playscreen.this.media_Controller.show(1000);
        Playscreen.this.video_player_view.setOnPreparedListener(new MediaPlayer.OnPreparedListener()
        {
          public void onPrepared(MediaPlayer paramAnonymousMediaPlayer)
          {
            Playscreen.this.video_player_view.start();
          }
        });
        return;
      }
      catch (IllegalArgumentException paramVarArgs)
      {
        paramVarArgs.printStackTrace();
        return;
      }
      catch (IllegalStateException paramVarArgs)
      {
        paramVarArgs.printStackTrace();
        return;
      }
      catch (SecurityException paramVarArgs)
      {
        paramVarArgs.printStackTrace();
      }
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ons\chat\Playscreen.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */