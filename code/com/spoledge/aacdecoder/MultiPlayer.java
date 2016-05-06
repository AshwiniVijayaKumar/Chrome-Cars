package com.spoledge.aacdecoder;

import android.util.Log;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class MultiPlayer
  extends AACPlayer
{
  private static final String LOG = "MultiPlayer";
  private Decoder aacDecoder;
  private Decoder mp3Decoder;
  
  public MultiPlayer()
  {
    this(null);
  }
  
  public MultiPlayer(PlayerCallback paramPlayerCallback)
  {
    this(paramPlayerCallback, 1500, 700);
  }
  
  public MultiPlayer(PlayerCallback paramPlayerCallback, int paramInt1, int paramInt2)
  {
    super(paramPlayerCallback, paramInt1, paramInt2);
  }
  
  protected Decoder createDecoder()
  {
    this.aacDecoder = super.createDecoder();
    this.mp3Decoder = Decoder.createByName("OpenCORE-MP3");
    if (this.mp3Decoder == null)
    {
      Log.e("MultiPlayer", "Cannot find decoder by name '" + "OpenCORE-MP3" + "'");
      throw new RuntimeException("MP3 Decoder not found");
    }
    return this.aacDecoder;
  }
  
  protected void processFileType(String paramString)
  {
    boolean bool = paramString.toLowerCase().endsWith(".mp3");
    StringBuilder localStringBuilder = new StringBuilder().append("Setting ");
    String str;
    if (bool)
    {
      str = "MP3";
      Log.i("MultiPlayer", str + " decoder for file " + paramString);
      if (!bool) {
        break label76;
      }
    }
    label76:
    for (paramString = this.mp3Decoder;; paramString = this.aacDecoder)
    {
      setDecoder(paramString);
      return;
      str = "AAC";
      break;
    }
  }
  
  protected void processHeaders(URLConnection paramURLConnection)
  {
    super.processHeaders(paramURLConnection);
    paramURLConnection = paramURLConnection.getHeaderFields().entrySet().iterator();
    while (paramURLConnection.hasNext())
    {
      Object localObject = (Map.Entry)paramURLConnection.next();
      if ("content-type".equalsIgnoreCase((String)((Map.Entry)localObject).getKey()))
      {
        localObject = ((List)((Map.Entry)localObject).getValue()).iterator();
        while (((Iterator)localObject).hasNext())
        {
          String str = (String)((Iterator)localObject).next();
          if (!str.startsWith("audio/"))
          {
            Log.w("MultiPlayer", "Content type not audio: " + str);
          }
          else
          {
            localObject = str.substring("audio/".length());
            paramURLConnection = (URLConnection)localObject;
            if (((String)localObject).startsWith("x-")) {
              paramURLConnection = ((String)localObject).substring("x-".length());
            }
            int i;
            if ((paramURLConnection.startsWith("mp3")) || (paramURLConnection.startsWith("mpeg")) || (paramURLConnection.startsWith("mpg")))
            {
              i = 1;
              localObject = new StringBuilder().append("Setting ");
              if (i == 0) {
                break label255;
              }
              paramURLConnection = "MP3";
              label211:
              Log.i("MultiPlayer", paramURLConnection + " decoder for content type " + str);
              if (i == 0) {
                break label261;
              }
            }
            label255:
            label261:
            for (paramURLConnection = this.mp3Decoder;; paramURLConnection = this.aacDecoder)
            {
              setDecoder(paramURLConnection);
              return;
              i = 0;
              break;
              paramURLConnection = "AAC";
              break label211;
            }
          }
        }
        Log.w("MultiPlayer", "Content type not recognized");
      }
    }
    Log.e("MultiPlayer", "Could not recognize the type of the stream.");
    throw new RuntimeException("Could not recognize the type of the stream.");
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\spoledge\aacdecoder\MultiPlayer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */