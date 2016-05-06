package com.spoledge.aacdecoder;

public class Decoder
{
  protected static int STATE_IDLE = 0;
  protected static int STATE_RUNNING = 1;
  private static boolean libLoaded = false;
  protected int aacdw;
  protected int decoder;
  protected Info info;
  protected int state = STATE_IDLE;
  
  protected Decoder(int paramInt)
  {
    this.decoder = paramInt;
  }
  
  public static Decoder create()
  {
    return create(0);
  }
  
  public static Decoder create(int paramInt)
  {
    try
    {
      loadLibrary();
      Decoder localDecoder = new Decoder(paramInt);
      return localDecoder;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public static Decoder createByName(String paramString)
  {
    loadLibrary();
    int i = nativeDecoderGetByName(paramString);
    if (i != 0) {
      return create(i);
    }
    return null;
  }
  
  public static void loadLibrary()
  {
    try
    {
      if (!libLoaded)
      {
        System.loadLibrary("aacdecoder");
        libLoaded = true;
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  protected static native int nativeDecoderGetByName(String paramString);
  
  public Info decode(short[] paramArrayOfShort, int paramInt)
  {
    if (this.state != STATE_RUNNING) {
      throw new IllegalStateException();
    }
    nativeDecode(this.aacdw, paramArrayOfShort, paramInt);
    return this.info;
  }
  
  protected void finalize()
  {
    try
    {
      stop();
      return;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
  }
  
  protected native int nativeDecode(int paramInt1, short[] paramArrayOfShort, int paramInt2);
  
  protected native int nativeStart(int paramInt, BufferReader paramBufferReader, Info paramInfo);
  
  protected native void nativeStop(int paramInt);
  
  public Info start(BufferReader paramBufferReader)
  {
    if (this.state != STATE_IDLE) {
      throw new IllegalStateException();
    }
    this.info = new Info();
    this.aacdw = nativeStart(this.decoder, paramBufferReader, this.info);
    if (this.aacdw == 0) {
      throw new RuntimeException("Cannot start native decoder");
    }
    this.state = STATE_RUNNING;
    return this.info;
  }
  
  public void stop()
  {
    if (this.aacdw != 0)
    {
      nativeStop(this.aacdw);
      this.aacdw = 0;
    }
    this.state = STATE_IDLE;
  }
  
  public static final class Info
  {
    private int channels;
    private short[] firstSamples;
    private int frameMaxBytesConsumed;
    private int frameSamples;
    private int roundBytesConsumed;
    private int roundFrames;
    private int roundSamples;
    private int sampleRate;
    
    public int getChannels()
    {
      return this.channels;
    }
    
    public short[] getFirstSamples()
    {
      return this.firstSamples;
    }
    
    public int getFrameMaxBytesConsumed()
    {
      return this.frameMaxBytesConsumed;
    }
    
    public int getFrameSamples()
    {
      return this.frameSamples;
    }
    
    public int getRoundBytesConsumed()
    {
      return this.roundBytesConsumed;
    }
    
    public int getRoundFrames()
    {
      return this.roundFrames;
    }
    
    public int getRoundSamples()
    {
      return this.roundSamples;
    }
    
    public int getSampleRate()
    {
      return this.sampleRate;
    }
    
    public void setFirstSamples(short[] paramArrayOfShort)
    {
      this.firstSamples = paramArrayOfShort;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\spoledge\aacdecoder\Decoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */