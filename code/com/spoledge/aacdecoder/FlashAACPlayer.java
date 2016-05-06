package com.spoledge.aacdecoder;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class FlashAACPlayer
  extends AACPlayer
{
  private InputStream istream = null;
  
  public FlashAACPlayer()
  {
    this(null);
  }
  
  public FlashAACPlayer(PlayerCallback paramPlayerCallback)
  {
    this(paramPlayerCallback, 1500, 700);
  }
  
  public FlashAACPlayer(PlayerCallback paramPlayerCallback, int paramInt1, int paramInt2)
  {
    setPlayerCallback(paramPlayerCallback);
    setAudioBufferCapacityMs(paramInt1);
    setDecodeBufferCapacityMs(paramInt2);
    this.decoder = createDecoder();
  }
  
  public void play(String paramString, int paramInt)
    throws Exception
  {
    if (paramString.indexOf(':') > 0)
    {
      paramString = new URL(paramString).openConnection();
      paramString.connect();
      dumpHeaders(paramString);
      this.istream = paramString.getInputStream();
      play(new FlashAACInputStream(this.istream), paramInt);
      return;
    }
    play(new FileInputStream(paramString), paramInt);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\spoledge\aacdecoder\FlashAACPlayer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */