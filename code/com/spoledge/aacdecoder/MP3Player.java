package com.spoledge.aacdecoder;

import android.util.Log;

public class MP3Player
  extends AACPlayer
{
  private static final String LOG = "MP3Player";
  
  public MP3Player()
  {
    this(null);
  }
  
  public MP3Player(PlayerCallback paramPlayerCallback)
  {
    this(paramPlayerCallback, 1500, 700);
  }
  
  public MP3Player(PlayerCallback paramPlayerCallback, int paramInt1, int paramInt2)
  {
    super(paramPlayerCallback, paramInt1, paramInt2);
  }
  
  protected Decoder createDecoder()
  {
    Decoder localDecoder = Decoder.createByName("OpenCORE-MP3");
    if (localDecoder == null)
    {
      Log.e("MP3Player", "Cannot find decoder by name '" + "OpenCORE-MP3" + "'");
      throw new RuntimeException("MP3 Decoder not found");
    }
    return localDecoder;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\spoledge\aacdecoder\MP3Player.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */