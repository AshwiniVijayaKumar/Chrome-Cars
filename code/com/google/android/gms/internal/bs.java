package com.google.android.gms.internal;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Handler;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.MediaController;
import android.widget.VideoView;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public final class bs
  extends FrameLayout
  implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener
{
  private final MediaController nB;
  private final a nC;
  private final VideoView nD;
  private long nE;
  private String nF;
  private final dd ng;
  
  public bs(Context paramContext, dd paramdd)
  {
    super(paramContext);
    this.ng = paramdd;
    this.nD = new VideoView(paramContext);
    paramdd = new FrameLayout.LayoutParams(-1, -1, 17);
    addView(this.nD, paramdd);
    this.nB = new MediaController(paramContext);
    this.nC = new a(this);
    this.nC.aA();
    this.nD.setOnCompletionListener(this);
    this.nD.setOnPreparedListener(this);
    this.nD.setOnErrorListener(this);
  }
  
  private static void a(dd paramdd, String paramString)
  {
    a(paramdd, paramString, new HashMap(1));
  }
  
  public static void a(dd paramdd, String paramString1, String paramString2)
  {
    int i;
    if (paramString2 == null)
    {
      i = 1;
      if (i == 0) {
        break label60;
      }
    }
    label60:
    for (int j = 2;; j = 3)
    {
      HashMap localHashMap = new HashMap(j);
      localHashMap.put("what", paramString1);
      if (i == 0) {
        localHashMap.put("extra", paramString2);
      }
      a(paramdd, "error", localHashMap);
      return;
      i = 0;
      break;
    }
  }
  
  private static void a(dd paramdd, String paramString1, String paramString2, String paramString3)
  {
    HashMap localHashMap = new HashMap(2);
    localHashMap.put(paramString2, paramString3);
    a(paramdd, paramString1, localHashMap);
  }
  
  private static void a(dd paramdd, String paramString, Map<String, String> paramMap)
  {
    paramMap.put("event", paramString);
    paramdd.a("onVideoEvent", paramMap);
  }
  
  public void ay()
  {
    if (!TextUtils.isEmpty(this.nF))
    {
      this.nD.setVideoPath(this.nF);
      return;
    }
    a(this.ng, "no_src", null);
  }
  
  public void az()
  {
    long l = this.nD.getCurrentPosition();
    if (this.nE != l)
    {
      float f = (float)l / 1000.0F;
      a(this.ng, "timeupdate", "time", String.valueOf(f));
      this.nE = l;
    }
  }
  
  public void b(MotionEvent paramMotionEvent)
  {
    this.nD.dispatchTouchEvent(paramMotionEvent);
  }
  
  public void destroy()
  {
    this.nC.cancel();
    this.nD.stopPlayback();
  }
  
  public void i(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.nD.setMediaController(this.nB);
      return;
    }
    this.nB.hide();
    this.nD.setMediaController(null);
  }
  
  public void o(String paramString)
  {
    this.nF = paramString;
  }
  
  public void onCompletion(MediaPlayer paramMediaPlayer)
  {
    a(this.ng, "ended");
  }
  
  public boolean onError(MediaPlayer paramMediaPlayer, int paramInt1, int paramInt2)
  {
    a(this.ng, String.valueOf(paramInt1), String.valueOf(paramInt2));
    return true;
  }
  
  public void onPrepared(MediaPlayer paramMediaPlayer)
  {
    float f = this.nD.getDuration() / 1000.0F;
    a(this.ng, "canplaythrough", "duration", String.valueOf(f));
  }
  
  public void pause()
  {
    this.nD.pause();
  }
  
  public void play()
  {
    this.nD.start();
  }
  
  public void seekTo(int paramInt)
  {
    this.nD.seekTo(paramInt);
  }
  
  private static final class a
  {
    private final Runnable kW;
    private volatile boolean nG = false;
    
    public a(final bs parambs)
    {
      this.kW = new Runnable()
      {
        private final WeakReference<bs> nH = new WeakReference(parambs);
        
        public void run()
        {
          bs localbs = (bs)this.nH.get();
          if ((!bs.a.a(bs.a.this)) && (localbs != null))
          {
            localbs.az();
            bs.a.this.aA();
          }
        }
      };
    }
    
    public void aA()
    {
      cz.pT.postDelayed(this.kW, 250L);
    }
    
    public void cancel()
    {
      this.nG = true;
      cz.pT.removeCallbacks(this.kW);
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\bs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */