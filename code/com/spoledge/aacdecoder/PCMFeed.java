package com.spoledge.aacdecoder;

import android.media.AudioTrack;
import android.media.AudioTrack.OnPlaybackPositionUpdateListener;
import android.util.Log;

public class PCMFeed
  implements AudioTrack.OnPlaybackPositionUpdateListener, Runnable
{
  private static final String LOG = "PCMFeed";
  public static final int MARKER_REACHED_ACTION_IGNORE = -1;
  public static final int MARKER_REACHED_ACTION_PAUSE = 1;
  protected AudioTrack audioTrack;
  protected int bufferSizeInBytes;
  protected int bufferSizeInMs;
  protected int channels;
  protected boolean isPlaying;
  protected short[] lsamples;
  protected int markerReachedAction = -1;
  protected PlayerCallback playerCallback;
  protected int sampleRate;
  protected short[] samples;
  protected int samplesCount;
  protected boolean stopped;
  protected boolean stoppedByEOF;
  protected int writtenTotal = 0;
  
  protected PCMFeed(int paramInt1, int paramInt2, int paramInt3, PlayerCallback paramPlayerCallback)
  {
    this.sampleRate = paramInt1;
    this.channels = paramInt2;
    this.bufferSizeInBytes = paramInt3;
    this.bufferSizeInMs = bytesToMs(paramInt3, paramInt1, paramInt2);
    this.playerCallback = paramPlayerCallback;
  }
  
  public static int bytesToMs(int paramInt1, int paramInt2, int paramInt3)
  {
    return (int)(500L * paramInt1 / (paramInt2 * paramInt3));
  }
  
  public static int msToBytes(int paramInt1, int paramInt2, int paramInt3)
  {
    return (int)(paramInt1 * paramInt2 * paramInt3 / 500L);
  }
  
  public static int msToSamples(int paramInt1, int paramInt2, int paramInt3)
  {
    return (int)(paramInt1 * paramInt2 * paramInt3 / 1000L);
  }
  
  public static int samplesToMs(int paramInt1, int paramInt2, int paramInt3)
  {
    return (int)(1000L * paramInt1 / (paramInt2 * paramInt3));
  }
  
  protected int acquireSamples()
  {
    try
    {
      while ((this.samplesCount == 0) && (!this.stopped))
      {
        boolean bool = this.stoppedByEOF;
        if (bool) {
          break;
        }
        try
        {
          wait();
        }
        catch (InterruptedException localInterruptedException) {}
      }
      this.lsamples = this.samples;
      int i = this.samplesCount;
      this.samples = null;
      this.samplesCount = 0;
      notify();
      return i;
    }
    finally {}
  }
  
  /* Error */
  public boolean feed(short[] paramArrayOfShort, int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 81	com/spoledge/aacdecoder/PCMFeed:samples	[S
    //   6: ifnull +24 -> 30
    //   9: aload_0
    //   10: getfield 74	com/spoledge/aacdecoder/PCMFeed:stopped	Z
    //   13: istore_3
    //   14: iload_3
    //   15: ifne +15 -> 30
    //   18: aload_0
    //   19: invokevirtual 79	java/lang/Object:wait	()V
    //   22: goto -20 -> 2
    //   25: astore 4
    //   27: goto -25 -> 2
    //   30: aload_0
    //   31: aload_1
    //   32: putfield 81	com/spoledge/aacdecoder/PCMFeed:samples	[S
    //   35: aload_0
    //   36: iload_2
    //   37: putfield 72	com/spoledge/aacdecoder/PCMFeed:samplesCount	I
    //   40: aload_0
    //   41: invokevirtual 86	java/lang/Object:notify	()V
    //   44: aload_0
    //   45: getfield 74	com/spoledge/aacdecoder/PCMFeed:stopped	Z
    //   48: istore_3
    //   49: iload_3
    //   50: ifne +9 -> 59
    //   53: iconst_1
    //   54: istore_3
    //   55: aload_0
    //   56: monitorexit
    //   57: iload_3
    //   58: ireturn
    //   59: iconst_0
    //   60: istore_3
    //   61: goto -6 -> 55
    //   64: astore_1
    //   65: aload_0
    //   66: monitorexit
    //   67: aload_1
    //   68: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	69	0	this	PCMFeed
    //   0	69	1	paramArrayOfShort	short[]
    //   0	69	2	paramInt	int
    //   13	48	3	bool	boolean
    //   25	1	4	localInterruptedException	InterruptedException
    // Exception table:
    //   from	to	target	type
    //   18	22	25	java/lang/InterruptedException
    //   2	14	64	finally
    //   18	22	64	finally
    //   30	49	64	finally
  }
  
  public final int getBufferSizeInBytes()
  {
    return this.bufferSizeInBytes;
  }
  
  public final int getBufferSizeInMs()
  {
    return this.bufferSizeInMs;
  }
  
  public final int getChannels()
  {
    return this.channels;
  }
  
  public final int getSampleRate()
  {
    return this.sampleRate;
  }
  
  public void onMarkerReached(AudioTrack paramAudioTrack)
  {
    Log.d("PCMFeed", "onMarkerReached()");
    if (this.markerReachedAction == 1) {
      paramAudioTrack.pause();
    }
  }
  
  public void onPeriodicNotification(AudioTrack paramAudioTrack)
  {
    if (this.playerCallback != null) {}
    try
    {
      int i = this.writtenTotal;
      int j = paramAudioTrack.getPlaybackHeadPosition();
      int k = this.channels;
      i = samplesToMs(i - j * k, this.sampleRate, this.channels);
      this.playerCallback.playerPCMFeedBuffer(this.isPlaying, i, this.bufferSizeInMs);
      return;
    }
    catch (IllegalStateException localIllegalStateException)
    {
      Log.e("PCMFeed", "onPeriodicNotification(): illegal state=" + paramAudioTrack.getPlayState());
    }
  }
  
  protected void releaseSamples() {}
  
  public void run()
  {
    int i = 2;
    Log.d("PCMFeed", "run(): sampleRate=" + this.sampleRate + ", channels=" + this.channels + ", bufferSizeInBytes=" + this.bufferSizeInBytes + " (" + this.bufferSizeInMs + " ms)");
    this.isPlaying = false;
    for (;;)
    {
      AudioTrack localAudioTrack1;
      try
      {
        j = this.sampleRate;
        if (this.channels == 1) {
          localAudioTrack1 = new AudioTrack(3, j, i, 2, this.bufferSizeInBytes, 1);
        }
      }
      catch (Throwable localThrowable1)
      {
        localAudioTrack1 = null;
      }
      try
      {
        localAudioTrack1.setPlaybackPositionUpdateListener(this);
        localAudioTrack1.setPositionNotificationPeriod(msToSamples(200, this.sampleRate, this.channels));
        if (this.playerCallback != null) {
          this.playerCallback.playerAudioTrackCreated(localAudioTrack1);
        }
        this.audioTrack = localAudioTrack1;
        localAudioTrack2 = localAudioTrack1;
        if (!this.stopped)
        {
          i = acquireSamples();
          if ((!this.stopped) && (i != 0)) {
            break label311;
          }
          releaseSamples();
        }
        if ((!this.stopped) && (this.stoppedByEOF)) {
          waitForLastTone();
        }
        if (this.isPlaying) {
          localAudioTrack2.pause();
        }
        localAudioTrack2.flush();
        localAudioTrack2.release();
        this.stopped = true;
        Log.d("PCMFeed", "run() stopped.");
        return;
      }
      catch (Throwable localThrowable2)
      {
        for (;;) {}
      }
      i = 3;
      continue;
      Log.e("PCMFeed", "Cannot create AudioTrack: " + localThrowable1);
      stop();
      AudioTrack localAudioTrack2 = localAudioTrack1;
      if (this.playerCallback == null) {
        continue;
      }
      this.playerCallback.playerException(localThrowable1);
      localAudioTrack2 = localAudioTrack1;
      continue;
      label311:
      int j = 0;
      if (j != 0) {
        Log.d("PCMFeed", "too fast for playback, sleeping...");
      }
      try
      {
        Thread.sleep(50L);
        int k = localAudioTrack2.write(this.lsamples, j, i);
        if (k < 0)
        {
          Log.e("PCMFeed", "error in playback feed: " + k);
          this.stopped = true;
          label377:
          releaseSamples();
          continue;
        }
        this.writtenTotal += k;
        int m = this.writtenTotal - localAudioTrack2.getPlaybackHeadPosition() * this.channels;
        if ((!this.stopped) && (!this.isPlaying))
        {
          if (m * 2 < this.bufferSizeInBytes) {
            break label499;
          }
          Log.d("PCMFeed", "start of AudioTrack - buffered " + m + " samples");
          localAudioTrack2.play();
          this.isPlaying = true;
        }
        for (;;)
        {
          j += k;
          i -= k;
          if (i <= 0) {
            break label377;
          }
          if (!this.stopped) {
            break;
          }
          break label377;
          label499:
          Log.d("PCMFeed", "start buffer not filled enough - AudioTrack not started yet");
        }
      }
      catch (InterruptedException localInterruptedException)
      {
        for (;;) {}
      }
    }
  }
  
  public void stop()
  {
    try
    {
      stop(false);
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void stop(boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (;;)
    {
      try
      {
        this.stoppedByEOF = true;
        notify();
        return;
      }
      finally {}
      this.stopped = true;
      if (this.isPlaying) {
        this.audioTrack.pause();
      }
    }
  }
  
  protected void waitForLastTone()
  {
    if (!this.isPlaying)
    {
      i = msToSamples(2000, this.sampleRate, this.channels);
      if (this.writtenTotal < i)
      {
        Log.d("PCMFeed", "Filling dummy data to audio buffer");
        short[] arrayOfShort = new short[i - this.writtenTotal];
        this.audioTrack.write(arrayOfShort, 0, arrayOfShort.length);
        this.audioTrack.setNotificationMarkerPosition(this.writtenTotal / this.channels);
        this.markerReachedAction = 1;
      }
      Log.d("PCMFeed", "start of AudioTrack");
      this.audioTrack.play();
      this.isPlaying = true;
    }
    Log.i("PCMFeed", "Waiting for the end of the music");
    int j = this.writtenTotal;
    int i = this.writtenTotal;
    int k = 5;
    try
    {
      Thread.sleep(100L);
      for (;;)
      {
        try
        {
          i = this.writtenTotal;
          int m = this.audioTrack.getPlaybackHeadPosition();
          int n = this.channels;
          m = i - m * n;
          n = j - m;
          if (n == 0)
          {
            i = k - 1;
            if (i > 0)
            {
              k = i;
              j = m;
              if (m > 0) {
                break;
              }
              k = i;
              j = m;
              if (n > 0) {
                break;
              }
            }
            return;
          }
        }
        catch (IllegalStateException localIllegalStateException)
        {
          Log.e("PCMFeed", "waitForLastTone(): illegal state=" + this.audioTrack.getPlayState());
          return;
        }
        i = 5;
      }
    }
    catch (InterruptedException localInterruptedException)
    {
      for (;;) {}
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\spoledge\aacdecoder\PCMFeed.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */