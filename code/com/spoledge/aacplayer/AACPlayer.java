package com.spoledge.aacplayer;

import android.util.Log;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public abstract class AACPlayer
{
  public static final int DEFAULT_AUDIO_BUFFER_CAPACITY_MS = 1500;
  public static final int DEFAULT_DECODE_BUFFER_CAPACITY_MS = 700;
  public static final int DEFAULT_EXPECTED_KBITSEC_RATE = 64;
  private static final String LOG = "AACPlayer";
  protected int audioBufferCapacityMs;
  private int avgKBitSecRate = 0;
  private int countKBitSecRate = 0;
  protected int decodeBufferCapacityMs;
  protected PlayerCallback playerCallback;
  protected boolean stopped;
  private int sumKBitSecRate = 0;
  
  protected AACPlayer()
  {
    this(null);
  }
  
  protected AACPlayer(PlayerCallback paramPlayerCallback)
  {
    this(paramPlayerCallback, 1500, 700);
  }
  
  protected AACPlayer(PlayerCallback paramPlayerCallback, int paramInt1, int paramInt2)
  {
    setPlayerCallback(paramPlayerCallback);
    setAudioBufferCapacityMs(paramInt1);
    setDecodeBufferCapacityMs(paramInt2);
  }
  
  protected static int computeInputBufferSize(int paramInt1, int paramInt2)
  {
    return paramInt1 * paramInt2 / 8;
  }
  
  protected static int computeInputBufferSize(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    return (int)(paramInt1 * paramInt4 * paramInt3 * paramInt5 / (1000L * paramInt2));
  }
  
  protected static int computeInputBufferSize(Decoder.Info paramInfo, int paramInt)
  {
    return computeInputBufferSize(paramInfo.getRoundBytesConsumed(), paramInfo.getRoundSamples(), paramInfo.getSampleRate(), paramInfo.getChannels(), paramInt);
  }
  
  protected static int computeKBitSecRate(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    return ((int)(8L * paramInt1 * paramInt4 * paramInt3 / paramInt2) + 500) / 1000;
  }
  
  protected static int computeKBitSecRate(Decoder.Info paramInfo)
  {
    if (paramInfo.getRoundSamples() <= 0) {
      return -1;
    }
    return computeKBitSecRate(paramInfo.getRoundBytesConsumed(), paramInfo.getRoundSamples(), paramInfo.getSampleRate(), paramInfo.getChannels());
  }
  
  protected int computeAvgKBitSecRate(Decoder.Info paramInfo)
  {
    if (this.countKBitSecRate < 64)
    {
      int i = computeKBitSecRate(paramInfo);
      int j = paramInfo.getRoundFrames();
      this.sumKBitSecRate += i * j;
      this.countKBitSecRate += j;
      this.avgKBitSecRate = (this.sumKBitSecRate / this.countKBitSecRate);
    }
    return this.avgKBitSecRate;
  }
  
  protected void dumpHeaders(URLConnection paramURLConnection)
  {
    paramURLConnection = paramURLConnection.getHeaderFields().entrySet().iterator();
    while (paramURLConnection.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)paramURLConnection.next();
      Iterator localIterator = ((List)localEntry.getValue()).iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        Log.d("AACPlayer", "header: key=" + (String)localEntry.getKey() + ", val=" + str);
      }
    }
  }
  
  public int getAudioBufferCapacityMs()
  {
    return this.audioBufferCapacityMs;
  }
  
  public int getDecodeBufferCapacityMs()
  {
    return this.decodeBufferCapacityMs;
  }
  
  public PlayerCallback getPlayerCallback()
  {
    return this.playerCallback;
  }
  
  public void play(InputStream paramInputStream)
    throws Exception
  {
    play(paramInputStream, -1);
  }
  
  public final void play(InputStream paramInputStream, int paramInt)
    throws Exception
  {
    this.stopped = false;
    if (this.playerCallback != null) {
      this.playerCallback.playerStarted();
    }
    int i = paramInt;
    if (paramInt <= 0) {
      i = 64;
    }
    this.sumKBitSecRate = 0;
    this.countKBitSecRate = 0;
    playImpl(paramInputStream, i);
  }
  
  public void play(String paramString)
    throws Exception
  {
    play(paramString, -1);
  }
  
  public void play(String paramString, int paramInt)
    throws Exception
  {
    if (paramString.indexOf(':') > 0)
    {
      paramString = new URL(paramString).openConnection();
      paramString.connect();
      dumpHeaders(paramString);
      play(paramString.getInputStream(), paramInt);
      return;
    }
    play(new FileInputStream(paramString), paramInt);
  }
  
  public void playAsync(String paramString)
  {
    playAsync(paramString, -1);
  }
  
  public void playAsync(final String paramString, final int paramInt)
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        try
        {
          AACPlayer.this.play(paramString, paramInt);
          return;
        }
        catch (Exception localException)
        {
          do
          {
            Log.e("AACPlayer", "playAsync():", localException);
          } while (AACPlayer.this.playerCallback == null);
          AACPlayer.this.playerCallback.playerException(localException);
        }
      }
    }).start();
  }
  
  protected abstract void playImpl(InputStream paramInputStream, int paramInt)
    throws Exception;
  
  public void setAudioBufferCapacityMs(int paramInt)
  {
    this.audioBufferCapacityMs = paramInt;
  }
  
  public void setDecodeBufferCapacityMs(int paramInt)
  {
    this.decodeBufferCapacityMs = paramInt;
  }
  
  public void setPlayerCallback(PlayerCallback paramPlayerCallback)
  {
    this.playerCallback = paramPlayerCallback;
  }
  
  public void stop()
  {
    this.stopped = true;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\spoledge\aacplayer\AACPlayer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */