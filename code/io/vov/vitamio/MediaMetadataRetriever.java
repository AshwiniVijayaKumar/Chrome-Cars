package io.vov.vitamio;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import io.vov.vitamio.utils.FileUtils;
import java.io.FileDescriptor;
import java.io.IOException;

public class MediaMetadataRetriever
{
  public static final String METADATA_KEY_ALBUM = "album";
  public static final String METADATA_KEY_ALBUM_ARTIST = "album_artist";
  public static final String METADATA_KEY_ARTIST = "artist";
  public static final String METADATA_KEY_AUDIO_CODEC = "audio_codec";
  public static final String METADATA_KEY_AUTHOR = "author";
  public static final String METADATA_KEY_COMMENT = "comment";
  public static final String METADATA_KEY_COMPOSER = "composer";
  public static final String METADATA_KEY_COPYRIGHT = "copyright";
  public static final String METADATA_KEY_CREATION_TIME = "creation_time";
  public static final String METADATA_KEY_DATE = "date";
  public static final String METADATA_KEY_DISC = "disc";
  public static final String METADATA_KEY_DURATION = "duration";
  public static final String METADATA_KEY_ENCODED_BY = "encoded_by";
  public static final String METADATA_KEY_ENCODER = "encoder";
  public static final String METADATA_KEY_FILENAME = "filename";
  public static final String METADATA_KEY_GENRE = "genre";
  public static final String METADATA_KEY_HAS_AUDIO = "has_audio";
  public static final String METADATA_KEY_HAS_VIDEO = "has_video";
  public static final String METADATA_KEY_LANGUAGE = "language";
  public static final String METADATA_KEY_NUM_TRACKS = "num_tracks";
  public static final String METADATA_KEY_PERFORMER = "performer";
  public static final String METADATA_KEY_PUBLISHER = "publisher";
  public static final String METADATA_KEY_SERVICE_NAME = "service_name";
  public static final String METADATA_KEY_SERVICE_PROVIDER = "service_provider";
  public static final String METADATA_KEY_TITLE = "title";
  public static final String METADATA_KEY_TRACK = "track";
  public static final String METADATA_KEY_VARIANT_BITRATE = "bitrate";
  public static final String METADATA_KEY_VIDEO_CODEC = "video_codec";
  public static final String METADATA_KEY_VIDEO_HEIGHT = "height";
  public static final String METADATA_KEY_VIDEO_ROTATION = "rotate";
  public static final String METADATA_KEY_VIDEO_WIDTH = "width";
  private AssetFileDescriptor mFD = null;
  private int mNativeContext;
  
  static
  {
    String str = Vitamio.getLibraryPath();
    Log.i("LIB ROOT: %s", str);
    System.load(str + "libstlport_shared.so");
    System.load(str + "libvscanner.so");
    loadFFmpeg_native(str + "libffmpeg.so");
    native_init();
  }
  
  public MediaMetadataRetriever(Context paramContext)
  {
    native_setup();
  }
  
  private native void _release();
  
  private void closeFD()
  {
    if (this.mFD != null) {}
    try
    {
      this.mFD.close();
      this.mFD = null;
      return;
    }
    catch (IOException localIOException)
    {
      for (;;) {}
    }
  }
  
  private static native boolean loadFFmpeg_native(String paramString);
  
  private final native void native_finalize();
  
  private static final native void native_init();
  
  private native void native_setup();
  
  public native String extractMetadata(String paramString)
    throws IllegalStateException;
  
  protected void finalize()
    throws Throwable
  {
    try
    {
      native_finalize();
      return;
    }
    finally
    {
      super.finalize();
    }
  }
  
  public native byte[] getEmbeddedPicture()
    throws IllegalStateException;
  
  public native Bitmap getFrameAtTime(long paramLong)
    throws IllegalStateException;
  
  public void release()
  {
    _release();
    closeFD();
  }
  
  public void setDataSource(Context paramContext, Uri paramUri)
    throws IOException, IllegalArgumentException, SecurityException, IllegalStateException
  {
    if ((paramContext == null) || (paramUri == null)) {
      throw new IllegalArgumentException();
    }
    String str = paramUri.getScheme();
    if ((str == null) || (str.equals("file"))) {
      setDataSource(FileUtils.getPath(paramUri.toString()));
    }
    for (;;)
    {
      return;
      try
      {
        this.mFD = paramContext.getContentResolver().openAssetFileDescriptor(paramUri, "r");
        if (this.mFD != null)
        {
          setDataSource(this.mFD.getParcelFileDescriptor().getFileDescriptor());
          return;
        }
      }
      catch (Exception paramContext)
      {
        closeFD();
        Log.e("Couldn't open file on client side, trying server side %s", paramUri.toString());
        setDataSource(paramUri.toString());
      }
    }
  }
  
  public native void setDataSource(FileDescriptor paramFileDescriptor)
    throws IOException, IllegalArgumentException, IllegalStateException;
  
  public native void setDataSource(String paramString)
    throws IOException, IllegalArgumentException, IllegalStateException;
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\io\vov\vitamio\MediaMetadataRetriever.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */