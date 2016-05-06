package com.spoledge.aacplayer;

public class ArrayDecoder
  extends Decoder
{
  private static boolean libLoaded = false;
  private int aacdw;
  private int decoder;
  private State state = State.IDLE;
  
  private ArrayDecoder(int paramInt)
  {
    this.decoder = paramInt;
  }
  
  public static ArrayDecoder create(int paramInt)
  {
    try
    {
      loadLibrary();
      ArrayDecoder localArrayDecoder = new ArrayDecoder(paramInt);
      return localArrayDecoder;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public static int getFeatures()
  {
    loadLibrary();
    return nativeGetFeatures();
  }
  
  private static void loadLibrary()
  {
    try
    {
      if (!libLoaded)
      {
        System.loadLibrary("aacarray");
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
  
  private native int nativeDecode(int paramInt1, short[] paramArrayOfShort, int paramInt2);
  
  private static native int nativeGetFeatures();
  
  private native int nativeStart(int paramInt, ArrayBufferReader paramArrayBufferReader, Decoder.Info paramInfo);
  
  private native void nativeStop(int paramInt);
  
  public Decoder.Info decode(short[] paramArrayOfShort, int paramInt)
  {
    if (this.state != State.RUNNING) {
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
  
  public int getDecoder()
  {
    return this.decoder;
  }
  
  public Decoder.Info start(ArrayBufferReader paramArrayBufferReader)
  {
    if (this.state != State.IDLE) {
      throw new IllegalStateException();
    }
    this.info = new Decoder.Info();
    this.aacdw = nativeStart(this.decoder, paramArrayBufferReader, this.info);
    if (this.aacdw == 0) {
      throw new RuntimeException("Cannot start native decoder");
    }
    this.state = State.RUNNING;
    return this.info;
  }
  
  public void stop()
  {
    if (this.aacdw != 0)
    {
      nativeStop(this.aacdw);
      this.aacdw = 0;
    }
    this.state = State.IDLE;
  }
  
  private static enum State
  {
    IDLE,  RUNNING;
    
    private State() {}
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\spoledge\aacplayer\ArrayDecoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */