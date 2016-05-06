package com.spoledge.aacplayer;

import android.util.Log;
import java.io.InputStream;

public class ArrayAACPlayer
  extends AACPlayer
{
  private static final String LOG = "ArrayAACPlayer";
  private ArrayDecoder decoder;
  
  public ArrayAACPlayer(ArrayDecoder paramArrayDecoder)
  {
    this(paramArrayDecoder, null);
  }
  
  public ArrayAACPlayer(ArrayDecoder paramArrayDecoder, PlayerCallback paramPlayerCallback)
  {
    this(paramArrayDecoder, paramPlayerCallback, 1500, 700);
  }
  
  public ArrayAACPlayer(ArrayDecoder paramArrayDecoder, PlayerCallback paramPlayerCallback, int paramInt1, int paramInt2)
  {
    super(paramPlayerCallback, paramInt1, paramInt2);
    this.decoder = paramArrayDecoder;
  }
  
  private ArrayPCMFeed createArrayPCMFeed(Decoder.Info paramInfo)
  {
    int i = PCMFeed.msToBytes(this.audioBufferCapacityMs, paramInfo.getSampleRate(), paramInfo.getChannels());
    return new ArrayPCMFeed(paramInfo.getSampleRate(), paramInfo.getChannels(), i, this.playerCallback);
  }
  
  private short[][] createDecodeBuffers(int paramInt, Decoder.Info paramInfo)
  {
    int i = PCMFeed.msToSamples(this.decodeBufferCapacityMs, paramInfo.getSampleRate(), paramInfo.getChannels());
    paramInfo = new short[paramInt][];
    paramInt = 0;
    while (paramInt < paramInfo.length)
    {
      paramInfo[paramInt] = new short[i];
      paramInt += 1;
    }
    return paramInfo;
  }
  
  protected void playImpl(InputStream paramInputStream, int paramInt)
    throws Exception
  {
    ArrayBufferReader localArrayBufferReader = new ArrayBufferReader(computeInputBufferSize(paramInt, this.decodeBufferCapacityMs), paramInputStream);
    new Thread(localArrayBufferReader).start();
    Object localObject1 = null;
    Decoder.Info localInfo3 = null;
    long l3 = 0L;
    long l4 = 0L;
    long l2 = 0L;
    int j = 0;
    int k = 0;
    int i = 0;
    paramInputStream = (InputStream)localObject1;
    long l1 = l2;
    long l5;
    try
    {
      Decoder.Info localInfo1 = this.decoder.start(localArrayBufferReader);
      paramInputStream = (InputStream)localObject1;
      l1 = l2;
      Log.d("ArrayAACPlayer", "play(): samplerate=" + localInfo1.getSampleRate() + ", channels=" + localInfo1.getChannels());
      paramInputStream = (InputStream)localObject1;
      l1 = l2;
      l5 = localInfo1.getSampleRate() * localInfo1.getChannels();
      paramInputStream = (InputStream)localObject1;
      l1 = l5;
      if (localInfo1.getChannels() > 2)
      {
        paramInputStream = (InputStream)localObject1;
        l1 = l5;
        throw new RuntimeException("Too many channels detected: " + localInfo1.getChannels());
      }
    }
    finally
    {
      l5 = l1;
      l2 = l3;
      localObject1 = localInfo3;
    }
    for (;;)
    {
      this.stopped = true;
      if (paramInputStream != null) {
        paramInputStream.stop();
      }
      this.decoder.stop();
      localArrayBufferReader.stop();
      paramInt = 0;
      if (i > 0) {
        Log.i("ArrayAACPlayer", "play(): average decoding time: " + l2 / i + " ms");
      }
      if (l2 > 0L)
      {
        paramInt = (int)((1000L * l4 / l2 - l5) * 100L / l5);
        Log.i("ArrayAACPlayer", "play(): average rate (samples/sec): audio=" + l5 + ", decoding=" + 1000L * l4 / l2 + ", audio/decoding= " + paramInt + " %  (the higher, the better; negative means that decoding is slower than needed by audio)");
      }
      if (localObject1 != null) {
        ((Thread)localObject1).join();
      }
      if (this.playerCallback != null) {
        this.playerCallback.playerStopped(paramInt);
      }
      throw localInfo2;
      paramInputStream = (InputStream)localObject1;
      l1 = l5;
      short[][] arrayOfShort1 = createDecodeBuffers(3, localInfo2);
      short[] arrayOfShort = arrayOfShort1[0];
      int m = 0;
      paramInputStream = (InputStream)localObject1;
      l1 = l5;
      localObject1 = createArrayPCMFeed(localInfo2);
      paramInputStream = (InputStream)localObject1;
      l1 = l5;
      Object localObject2 = new Thread((Runnable)localObject1);
      i = k;
      l2 = l3;
      l1 = l4;
      try
      {
        ((Thread)localObject2).start();
        k = paramInt;
        paramInt = j;
        paramInputStream = arrayOfShort;
        i = paramInt;
        l2 = l3;
        l1 = l4;
        long l6 = System.currentTimeMillis();
        i = paramInt;
        l2 = l3;
        l1 = l4;
        localInfo3 = this.decoder.decode(paramInputStream, paramInputStream.length);
        i = paramInt;
        l2 = l3;
        l1 = l4;
        int n = localInfo3.getRoundSamples();
        i = paramInt;
        l2 = l3;
        l1 = l4;
        l6 = l3 + (System.currentTimeMillis() - l6);
        long l7 = l4 + n;
        j = paramInt + 1;
        i = j;
        l2 = l6;
        l1 = l7;
        Log.d("ArrayAACPlayer", "play(): decoded " + n + " samples");
        boolean bool;
        if (n != 0)
        {
          i = j;
          l2 = l6;
          l1 = l7;
          bool = this.stopped;
          if (!bool) {
            break label811;
          }
        }
        for (;;)
        {
          this.stopped = true;
          if (localObject1 != null) {
            ((ArrayPCMFeed)localObject1).stop();
          }
          this.decoder.stop();
          localArrayBufferReader.stop();
          paramInt = 0;
          if (j > 0) {
            Log.i("ArrayAACPlayer", "play(): average decoding time: " + l6 / j + " ms");
          }
          if (l6 > 0L)
          {
            paramInt = (int)((1000L * l7 / l6 - l5) * 100L / l5);
            Log.i("ArrayAACPlayer", "play(): average rate (samples/sec): audio=" + l5 + ", decoding=" + 1000L * l7 / l6 + ", audio/decoding= " + paramInt + " %  (the higher, the better; negative means that decoding is slower than needed by audio)");
          }
          if (localObject2 != null) {
            ((Thread)localObject2).join();
          }
          if (this.playerCallback != null) {
            this.playerCallback.playerStopped(paramInt);
          }
          return;
          label811:
          i = j;
          l2 = l6;
          l1 = l7;
          if (((ArrayPCMFeed)localObject1).feed(paramInputStream, n))
          {
            i = j;
            l2 = l6;
            l1 = l7;
            if (!this.stopped)
            {
              i = j;
              l2 = l6;
              l1 = l7;
              paramInt = computeAvgKBitSecRate(localInfo3);
              n = k;
              i = j;
              l2 = l6;
              l1 = l7;
              if (Math.abs(k - paramInt) > 1)
              {
                i = j;
                l2 = l6;
                l1 = l7;
                Log.i("ArrayAACPlayer", "play(): changing kBitSecRate: " + k + " -> " + paramInt);
                i = j;
                l2 = l6;
                l1 = l7;
                localArrayBufferReader.setCapacity(computeInputBufferSize(paramInt, this.decodeBufferCapacityMs));
                n = paramInt;
              }
              m += 1;
              paramInputStream = arrayOfShort1[(m % 3)];
              i = j;
              l2 = l6;
              l1 = l7;
              bool = this.stopped;
              paramInt = j;
              l3 = l6;
              l4 = l7;
              k = n;
              if (!bool) {
                break;
              }
            }
          }
        }
      }
      finally
      {
        paramInputStream = (InputStream)localObject1;
        localObject1 = localObject2;
        l4 = l1;
        localObject2 = localObject3;
      }
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\spoledge\aacplayer\ArrayAACPlayer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */