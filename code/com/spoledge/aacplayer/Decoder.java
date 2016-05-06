package com.spoledge.aacplayer;

public abstract class Decoder
{
  public static final int DECODER_FAAD2 = 1;
  public static final int DECODER_FFMPEG = 2;
  public static final int DECODER_OPENCORE = 4;
  private static boolean libLoaded = false;
  protected Info info;
  
  public static final class Info
  {
    private int channels;
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
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\spoledge\aacplayer\Decoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */