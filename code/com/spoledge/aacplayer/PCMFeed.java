package com.spoledge.aacplayer;

import android.media.AudioTrack;
import android.media.AudioTrack.OnPlaybackPositionUpdateListener;
import android.util.Log;

public abstract class PCMFeed
  implements AudioTrack.OnPlaybackPositionUpdateListener, Runnable
{
  private static final String LOG = "PCMFeed";
  protected int bufferSizeInBytes;
  protected int bufferSizeInMs;
  protected int channels;
  protected boolean isPlaying;
  protected short[] lsamples;
  protected PlayerCallback playerCallback;
  protected int sampleRate;
  protected boolean stopped;
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
  
  protected abstract int acquireSamples();
  
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
  
  public void onMarkerReached(AudioTrack paramAudioTrack) {}
  
  public void onPeriodicNotification(AudioTrack paramAudioTrack)
  {
    if (this.playerCallback != null) {
      samplesToMs(this.writtenTotal - paramAudioTrack.getPlaybackHeadPosition() * this.channels, this.sampleRate, this.channels);
    }
  }
  
  protected abstract void releaseSamples();
  
  public void run()
  {
    Log.d("PCMFeed", "run(): sampleRate=" + this.sampleRate + ", channels=" + this.channels + ", bufferSizeInBytes=" + this.bufferSizeInBytes + " (" + this.bufferSizeInMs + " ms)");
    int j = this.sampleRate;
    int i;
    AudioTrack localAudioTrack;
    if (this.channels == 1)
    {
      i = 2;
      localAudioTrack = new AudioTrack(3, j, i, 2, this.bufferSizeInBytes, 1);
      localAudioTrack.setPlaybackPositionUpdateListener(this);
      localAudioTrack.setPositionNotificationPeriod(msToSamples(200, this.sampleRate, this.channels));
      this.isPlaying = false;
    }
    for (;;)
    {
      if (!this.stopped)
      {
        i = acquireSamples();
        if (this.stopped) {
          releaseSamples();
        }
      }
      else
      {
        if (this.isPlaying) {
          localAudioTrack.stop();
        }
        localAudioTrack.flush();
        localAudioTrack.release();
        Log.d("PCMFeed", "run() stopped.");
        return;
        i = 3;
        break;
      }
      j = 0;
      if (j != 0) {
        Log.d("PCMFeed", "too fast for playback, sleeping...");
      }
      try
      {
        Thread.sleep(50L);
        int k = localAudioTrack.write(this.lsamples, j, i);
        if (k < 0)
        {
          Log.e("PCMFeed", "error in playback feed: " + k);
          this.stopped = true;
          label258:
          releaseSamples();
          continue;
        }
        this.writtenTotal += k;
        int m = this.writtenTotal - localAudioTrack.getPlaybackHeadPosition() * this.channels;
        if (!this.isPlaying)
        {
          if (m * 2 < this.bufferSizeInBytes) {
            break label368;
          }
          Log.d("PCMFeed", "start of AudioTrack - buffered " + m + " samples");
          localAudioTrack.play();
          this.isPlaying = true;
        }
        for (;;)
        {
          j += k;
          k = i - k;
          i = k;
          if (k > 0) {
            break;
          }
          break label258;
          label368:
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
      this.stopped = true;
      notify();
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\spoledge\aacplayer\PCMFeed.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */