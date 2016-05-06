package io.vov.vitamio;

import android.util.Log;
import android.util.SparseArray;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class Metadata
{
  public static final int ALBUM = 4;
  public static final int ALBUM_ART = 14;
  public static final int ANY = 0;
  public static final int ARTIST = 5;
  public static final int AUDIO_BIT_RATE = 18;
  public static final int AUDIO_CODEC = 23;
  public static final int AUDIO_SAMPLE_RATE = 20;
  public static final int AUTHOR = 6;
  public static final int BIT_RATE = 17;
  public static final int CD_TRACK_MAX = 12;
  public static final int CD_TRACK_NUM = 11;
  public static final int COMMENT = 2;
  public static final int COMPOSER = 7;
  public static final int COPYRIGHT = 3;
  public static final int DATE = 9;
  public static final int DRM_CRIPPLED = 28;
  public static final int DURATION = 10;
  private static final int FIRST_CUSTOM = 8192;
  public static final int GENRE = 8;
  private static final int LAST_SYSTEM = 32;
  public static final int LENGTH = 16;
  public static final int MIME_TYPE = 22;
  public static final int NUM_TRACKS = 27;
  public static final int PAUSE_AVAILABLE = 29;
  public static final int RATING = 13;
  public static final int SEEK_AVAILABLE = 32;
  public static final int SEEK_BACKWARD_AVAILABLE = 30;
  public static final int SEEK_FORWARD_AVAILABLE = 31;
  public static final int TITLE = 1;
  public static final int VIDEO_BIT_RATE = 19;
  public static final int VIDEO_CODEC = 24;
  public static final int VIDEO_FRAME = 15;
  public static final int VIDEO_FRAME_RATE = 21;
  public static final int VIDEO_HEIGHT = 25;
  public static final int VIDEO_WIDTH = 26;
  private String mEncoding = "UTF-8";
  private SparseArray<byte[]> mMeta = new SparseArray();
  
  private boolean checkMetadataId(int paramInt)
  {
    return (paramInt > 0) && ((32 >= paramInt) || (paramInt >= 8192));
  }
  
  public boolean getBoolean(int paramInt)
  {
    try
    {
      boolean bool = Boolean.parseBoolean(getString(paramInt));
      return bool;
    }
    catch (Exception localException) {}
    return false;
  }
  
  public byte[] getByteArray(int paramInt)
  {
    return (byte[])this.mMeta.get(paramInt);
  }
  
  public double getDouble(int paramInt)
  {
    try
    {
      double d = Double.parseDouble(getString(paramInt));
      return d;
    }
    catch (Exception localException) {}
    return -1.0D;
  }
  
  public int getInt(int paramInt)
  {
    try
    {
      paramInt = Integer.parseInt(getString(paramInt));
      return paramInt;
    }
    catch (Exception localException) {}
    return -1;
  }
  
  public long getLong(int paramInt)
  {
    try
    {
      long l = Long.parseLong(getString(paramInt));
      return l;
    }
    catch (Exception localException) {}
    return -1L;
  }
  
  public String getString(int paramInt)
  {
    byte[] arrayOfByte = (byte[])this.mMeta.get(paramInt);
    if (arrayOfByte == null) {
      return null;
    }
    try
    {
      String str = new String(arrayOfByte, this.mEncoding);
      return str;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException) {}
    return new String(arrayOfByte);
  }
  
  public boolean has(int paramInt)
  {
    if (!checkMetadataId(paramInt)) {
      throw new IllegalArgumentException("Invalid key: " + paramInt);
    }
    return this.mMeta.indexOfKey(paramInt) >= 0;
  }
  
  public boolean parse(Map<byte[], byte[]> paramMap, String paramString)
  {
    this.mEncoding = paramString;
    Iterator localIterator = paramMap.keySet().iterator();
    for (;;)
    {
      if (!localIterator.hasNext())
      {
        Log.i("Vitamio[Metadata]", "title:\t\t" + getString(1));
        Log.i("Vitamio[Metadata]", "comment:\t\t" + getString(2));
        Log.i("Vitamio[Metadata]", "copyright:\t\t" + getString(3));
        Log.i("Vitamio[Metadata]", "album:\t\t" + getString(4));
        Log.i("Vitamio[Metadata]", "artist:\t\t" + getString(5));
        Log.i("Vitamio[Metadata]", "composer:\t\t" + getString(7));
        Log.i("Vitamio[Metadata]", "genre:\t\t" + getString(8));
        Log.i("Vitamio[Metadata]", "date:\t\t" + getString(9));
        Log.i("Vitamio[Metadata]", "duration:\t\t" + getLong(10));
        Log.i("Vitamio[Metadata]", "length:\t\t" + getLong(16));
        Log.i("Vitamio[Metadata]", "bit_rate:\t\t" + getInt(17));
        Log.i("Vitamio[Metadata]", "audio_bit_rate:\t" + getInt(18));
        Log.i("Vitamio[Metadata]", "video_bit_rate:\t" + getInt(19));
        Log.i("Vitamio[Metadata]", "audio_sample_rate:\t" + getInt(20));
        Log.i("Vitamio[Metadata]", "video_frame_rate:\t" + getDouble(21));
        Log.i("Vitamio[Metadata]", "format:\t\t" + getString(22));
        Log.i("Vitamio[Metadata]", "audio_codec:\t" + getString(23));
        Log.i("Vitamio[Metadata]", "video_codec:\t" + getString(24));
        Log.i("Vitamio[Metadata]", "video_height:\t" + getInt(25));
        Log.i("Vitamio[Metadata]", "video_width:\t" + getInt(26));
        Log.i("Vitamio[Metadata]", "num_tracks:\t\t" + getInt(27));
        Log.i("Vitamio[Metadata]", "cap_pause:\t\t" + getBoolean(29));
        Log.i("Vitamio[Metadata]", "cap_seek:\t\t" + getBoolean(32));
        return true;
      }
      byte[] arrayOfByte = (byte[])localIterator.next();
      try
      {
        paramString = new String(arrayOfByte, this.mEncoding).trim().toLowerCase(Locale.US);
        arrayOfByte = (byte[])paramMap.get(arrayOfByte);
        if (paramString.equals("title")) {
          this.mMeta.put(1, arrayOfByte);
        }
      }
      catch (UnsupportedEncodingException paramString)
      {
        for (;;)
        {
          paramString = new String(arrayOfByte).trim().toLowerCase(Locale.US);
        }
        if (paramString.equals("comment"))
        {
          this.mMeta.put(2, arrayOfByte);
          continue;
        }
        if (paramString.equals("copyright"))
        {
          this.mMeta.put(3, arrayOfByte);
          continue;
        }
        if (paramString.equals("album"))
        {
          this.mMeta.put(4, arrayOfByte);
          continue;
        }
        if (paramString.equals("artist"))
        {
          this.mMeta.put(5, arrayOfByte);
          continue;
        }
        if (paramString.equals("author"))
        {
          this.mMeta.put(6, arrayOfByte);
          continue;
        }
        if (paramString.equals("composer"))
        {
          this.mMeta.put(7, arrayOfByte);
          continue;
        }
        if (paramString.equals("genre"))
        {
          this.mMeta.put(8, arrayOfByte);
          continue;
        }
        if ((paramString.equals("creation_time")) || (paramString.equals("date")))
        {
          this.mMeta.put(9, arrayOfByte);
          continue;
        }
        if (paramString.equals("duration"))
        {
          this.mMeta.put(10, arrayOfByte);
          continue;
        }
        if (paramString.equals("length"))
        {
          this.mMeta.put(16, arrayOfByte);
          continue;
        }
        if (paramString.equals("bit_rate"))
        {
          this.mMeta.put(17, arrayOfByte);
          continue;
        }
        if (paramString.equals("audio_bit_rate"))
        {
          this.mMeta.put(18, arrayOfByte);
          continue;
        }
        if (paramString.equals("video_bit_rate"))
        {
          this.mMeta.put(19, arrayOfByte);
          continue;
        }
        if (paramString.equals("audio_sample_rate"))
        {
          this.mMeta.put(20, arrayOfByte);
          continue;
        }
        if (paramString.equals("video_frame_rate"))
        {
          this.mMeta.put(21, arrayOfByte);
          continue;
        }
        if (paramString.equals("format"))
        {
          this.mMeta.put(22, arrayOfByte);
          continue;
        }
        if (paramString.equals("audio_codec"))
        {
          this.mMeta.put(23, arrayOfByte);
          continue;
        }
        if (paramString.equals("video_codec"))
        {
          this.mMeta.put(24, arrayOfByte);
          continue;
        }
        if (paramString.equals("video_height"))
        {
          this.mMeta.put(25, arrayOfByte);
          continue;
        }
        if (paramString.equals("video_width"))
        {
          this.mMeta.put(26, arrayOfByte);
          continue;
        }
        if (paramString.equals("num_tracks"))
        {
          this.mMeta.put(27, arrayOfByte);
          continue;
        }
        if (paramString.equals("cap_pause")) {
          this.mMeta.put(29, arrayOfByte);
        }
      }
      if (paramString.equals("cap_seek")) {
        this.mMeta.put(32, arrayOfByte);
      }
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\io\vov\vitamio\Metadata.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */