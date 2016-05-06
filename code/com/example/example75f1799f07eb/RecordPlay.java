package com.example.example75f1799f07eb;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import java.io.IOException;
import java.math.BigDecimal;

public class RecordPlay
  extends Activity
{
  private static final int STEP_VALUE = 4000;
  private static final int UPDATE_FREQUENCY = 500;
  private static String mSelectedPath;
  TextView appName;
  private final Handler handler = new Handler();
  private boolean isMoveingSeekBar = false;
  private boolean isStarted = true;
  private MediaCursorAdapter mediaAdapter = null;
  private ImageButton nextButton = null;
  private View.OnClickListener onButtonClick = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      switch (paramAnonymousView.getId())
      {
      default: 
        return;
      case 2131558717: 
        if (RecordPlay.this.player.isPlaying())
        {
          RecordPlay.this.handler.removeCallbacks(RecordPlay.this.updatePositionRunnable);
          RecordPlay.this.player.pause();
          RecordPlay.this.playButton.setImageResource(17301540);
          return;
        }
        if (RecordPlay.this.isStarted)
        {
          RecordPlay.this.player.start();
          RecordPlay.this.playButton.setImageResource(17301539);
          RecordPlay.this.updatePosition();
          return;
        }
        RecordPlay.this.startPlay(RecordPlay.mSelectedPath);
        return;
      case 2131558718: 
        j = RecordPlay.this.player.getCurrentPosition() + 4000;
        i = j;
        if (j > RecordPlay.this.player.getDuration()) {
          i = RecordPlay.this.player.getDuration();
        }
        RecordPlay.this.player.pause();
        RecordPlay.this.player.seekTo(i);
        RecordPlay.this.player.start();
        return;
      }
      int j = RecordPlay.this.player.getCurrentPosition() - 4000;
      int i = j;
      if (j < 0) {
        i = 0;
      }
      RecordPlay.this.player.pause();
      RecordPlay.this.player.seekTo(i);
      RecordPlay.this.player.start();
    }
  };
  private MediaPlayer.OnCompletionListener onCompletion = new MediaPlayer.OnCompletionListener()
  {
    public void onCompletion(MediaPlayer paramAnonymousMediaPlayer)
    {
      RecordPlay.this.stopPlay();
    }
  };
  private MediaPlayer.OnErrorListener onError = new MediaPlayer.OnErrorListener()
  {
    public boolean onError(MediaPlayer paramAnonymousMediaPlayer, int paramAnonymousInt1, int paramAnonymousInt2)
    {
      return false;
    }
  };
  private ImageButton playButton = null;
  private MediaPlayer player = null;
  private ImageButton prevButton = null;
  private SeekBar.OnSeekBarChangeListener seekBarChanged = new SeekBar.OnSeekBarChangeListener()
  {
    public void onProgressChanged(SeekBar paramAnonymousSeekBar, int paramAnonymousInt, boolean paramAnonymousBoolean)
    {
      if (RecordPlay.this.isMoveingSeekBar)
      {
        RecordPlay.this.player.seekTo(paramAnonymousInt);
        Log.i("OnSeekBarChangeListener", "onProgressChanged");
      }
    }
    
    public void onStartTrackingTouch(SeekBar paramAnonymousSeekBar)
    {
      RecordPlay.access$902(RecordPlay.this, true);
    }
    
    public void onStopTrackingTouch(SeekBar paramAnonymousSeekBar)
    {
      RecordPlay.access$902(RecordPlay.this, false);
    }
  };
  private SeekBar seekbar = null;
  private final Runnable updatePositionRunnable = new Runnable()
  {
    public void run()
    {
      RecordPlay.this.updatePosition();
    }
  };
  
  private void startPlay(String paramString)
  {
    Log.i("Selected: ", paramString);
    this.seekbar.setProgress(0);
    this.player.stop();
    this.player.reset();
    try
    {
      this.player.setDataSource(paramString);
      this.player.prepare();
      this.player.start();
      this.seekbar.setMax(this.player.getDuration());
      this.playButton.setImageResource(17301539);
      updatePosition();
      this.isStarted = true;
      return;
    }
    catch (IllegalArgumentException paramString)
    {
      for (;;)
      {
        paramString.printStackTrace();
      }
    }
    catch (IllegalStateException paramString)
    {
      for (;;)
      {
        paramString.printStackTrace();
      }
    }
    catch (IOException paramString)
    {
      for (;;)
      {
        paramString.printStackTrace();
      }
    }
  }
  
  private void stopPlay()
  {
    this.player.stop();
    this.player.reset();
    this.playButton.setImageResource(17301540);
    this.handler.removeCallbacks(this.updatePositionRunnable);
    this.seekbar.setProgress(0);
    this.isStarted = false;
  }
  
  private void updatePosition()
  {
    this.handler.removeCallbacks(this.updatePositionRunnable);
    this.seekbar.setProgress(this.player.getCurrentPosition());
    this.handler.postDelayed(this.updatePositionRunnable, 500L);
  }
  
  public void close(View paramView)
  {
    finish();
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(7);
    setContentView(2130903134);
    getWindow().setFeatureInt(7, 2130903063);
    paramBundle = getResources().getString(2131230720);
    this.appName = ((TextView)findViewById(2131558500));
    this.appName.setText(paramBundle);
    this.seekbar = ((SeekBar)findViewById(2131558715));
    this.playButton = ((ImageButton)findViewById(2131558717));
    this.prevButton = ((ImageButton)findViewById(2131558716));
    this.nextButton = ((ImageButton)findViewById(2131558718));
    this.player = new MediaPlayer();
    this.player.setOnCompletionListener(this.onCompletion);
    this.player.setOnErrorListener(this.onError);
    this.seekbar.setOnSeekBarChangeListener(this.seekBarChanged);
    mSelectedPath = getIntent().getStringExtra("path");
    this.playButton.setOnClickListener(this.onButtonClick);
    this.nextButton.setOnClickListener(this.onButtonClick);
    this.prevButton.setOnClickListener(this.onButtonClick);
    startPlay(getIntent().getStringExtra("path"));
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    this.handler.removeCallbacks(this.updatePositionRunnable);
    this.player.stop();
    this.player.reset();
    this.player.release();
    this.player = null;
  }
  
  private class MediaCursorAdapter
    extends SimpleCursorAdapter
  {
    public MediaCursorAdapter(Context paramContext, int paramInt, Cursor paramCursor)
    {
      super(paramInt, paramCursor, new String[] { "_display_name", "title", "duration" }, new int[0]);
    }
    
    public void bindView(View paramView, Context paramContext, Cursor paramCursor)
    {
      paramContext = paramCursor.getString(paramCursor.getColumnIndex("_data"));
      Log.e("cursor=", "" + paramContext);
      if (paramContext.contains("AudioRecorder"))
      {
        Log.d("matched cursor=", "" + paramContext);
        ((TextView)paramView.findViewById(2131558568)).setText(paramCursor.getString(paramCursor.getColumnIndex("title")));
        new BigDecimal(Double.toString(Long.parseLong(paramCursor.getString(paramCursor.getColumnIndex("duration"))) / 1000.0D / 60.0D)).setScale(2, 0).doubleValue();
        paramView.setTag(paramCursor.getString(paramCursor.getColumnIndex("_data")));
      }
    }
    
    public View newView(Context paramContext, Cursor paramCursor, ViewGroup paramViewGroup)
    {
      paramViewGroup = LayoutInflater.from(paramContext).inflate(2130903093, paramViewGroup, false);
      bindView(paramViewGroup, paramContext, paramCursor);
      return paramViewGroup;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\example\example75f1799f07eb\RecordPlay.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */