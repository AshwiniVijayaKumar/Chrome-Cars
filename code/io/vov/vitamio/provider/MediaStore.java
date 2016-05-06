package io.vov.vitamio.provider;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.ParcelFileDescriptor;
import android.provider.BaseColumns;
import io.vov.vitamio.utils.Log;
import java.io.FileNotFoundException;
import java.io.IOException;

public final class MediaStore
{
  public static final String AUTHORITY = "me.abitno.vplayer.mediaprovider";
  private static final String BASE_SQL_FIELDS = "_id INTEGER PRIMARY KEY,_data TEXT NOT NULL,_directory TEXT NOT NULL,_directory_name TEXT NOT NULL,_size INTEGER,_display_name TEXT,title TEXT,title_key TEXT,date_added INTEGER,date_modified INTEGER,mime_type TEXT,available_size INTEGER default 0,play_status INTEGER ,";
  public static final String CONTENT_AUTHORITY_SLASH = "content://me.abitno.vplayer.mediaprovider/";
  public static final Uri CONTENT_URI = Uri.parse("content://me.abitno.vplayer.mediaprovider/");
  public static final String MEDIA_SCANNER_VOLUME = "volume";
  
  public static Uri getMediaScannerUri()
  {
    return Uri.parse("content://me.abitno.vplayer.mediaprovider/media_scanner");
  }
  
  public static Uri getVolumeUri()
  {
    return Uri.parse("content://me.abitno.vplayer.mediaprovider/volume");
  }
  
  public static final class Audio
  {
    public static abstract interface AudioColumns
      extends MediaStore.MediaColumns
    {
      public static final String ALBUM = "album";
      public static final String ARTIST = "artist";
      public static final String BOOKMARK = "bookmark";
      public static final String COMPOSER = "composer";
      public static final String DURATION = "duration";
      public static final String TRACK = "track";
      public static final String YEAR = "year";
    }
    
    public static final class Media
      implements MediaStore.Audio.AudioColumns
    {
      public static final String CONTENT_TYPE = "vnd.android.cursor.dir/audio";
      public static final Uri CONTENT_URI = Uri.parse("content://me.abitno.vplayer.mediaprovider/audios/media");
    }
  }
  
  private static class InternalThumbnails
    implements BaseColumns
  {
    static final int DEFAULT_GROUP_ID = 0;
    private static final int MICRO_KIND = 3;
    private static final int MINI_KIND = 1;
    private static final String[] PROJECTION = { "_id", "_data" };
    private static byte[] sThumbBuf;
    private static final Object sThumbBufLock = new Object();
    
    static void cancelThumbnailRequest(ContentResolver paramContentResolver, long paramLong1, Uri paramUri, long paramLong2)
    {
      paramUri = paramUri.buildUpon().appendQueryParameter("cancel", "1").appendQueryParameter("orig_id", String.valueOf(paramLong1)).appendQueryParameter("group_id", String.valueOf(paramLong2)).build();
      try
      {
        paramContentResolver = paramContentResolver.query(paramUri, PROJECTION, null, null, null);
        if (paramContentResolver != null) {
          paramContentResolver.close();
        }
        return;
      }
      finally
      {
        if (0 != 0) {
          throw new NullPointerException();
        }
      }
    }
    
    private static Bitmap getMiniThumbFromFile(Cursor paramCursor, Uri paramUri, ContentResolver paramContentResolver, BitmapFactory.Options paramOptions)
    {
      Object localObject5 = null;
      Object localObject6 = null;
      Object localObject4 = null;
      Object localObject1 = localObject4;
      Object localObject2 = localObject5;
      Object localObject3 = localObject6;
      try
      {
        paramUri = paramContentResolver.openFileDescriptor(ContentUris.withAppendedId(paramUri, paramCursor.getLong(0)), "r");
        localObject1 = localObject4;
        localObject2 = localObject5;
        localObject3 = localObject6;
        paramCursor = BitmapFactory.decodeFileDescriptor(paramUri.getFileDescriptor(), null, paramOptions);
        localObject1 = paramCursor;
        localObject2 = paramCursor;
        localObject3 = paramCursor;
        paramUri.close();
        return paramCursor;
      }
      catch (FileNotFoundException paramCursor)
      {
        Log.e("getMiniThumbFromFile", paramCursor);
        return (Bitmap)localObject1;
      }
      catch (IOException paramCursor)
      {
        Log.e("getMiniThumbFromFile", paramCursor);
        return (Bitmap)localObject2;
      }
      catch (OutOfMemoryError paramCursor)
      {
        Log.e("getMiniThumbFromFile", paramCursor);
      }
      return (Bitmap)localObject3;
    }
    
    static Bitmap getThumbnail(Context paramContext, ContentResolver paramContentResolver, long paramLong1, long paramLong2, int paramInt, BitmapFactory.Options arg7, Uri paramUri)
    {
      localObject2 = null;
      localCursor1 = null;
      localObject1 = null;
      MiniThumbFile localMiniThumbFile = MiniThumbFile.instance(paramUri);
      paramContext = localCursor1;
      if (localMiniThumbFile.getMagic(paramLong1) != 0L)
      {
        if (paramInt == 3) {
          synchronized (sThumbBufLock)
          {
            if (sThumbBuf == null) {
              sThumbBuf = new byte['✐'];
            }
            paramContext = (Context)localObject1;
            if (localMiniThumbFile.getMiniThumbFromFile(paramLong1, sThumbBuf) != null)
            {
              paramContentResolver = BitmapFactory.decodeByteArray(sThumbBuf, 0, sThumbBuf.length);
              paramContext = paramContentResolver;
              if (paramContentResolver == null)
              {
                Log.d("couldn't decode byte array.", new Object[0]);
                paramContext = paramContentResolver;
              }
            }
            return paramContext;
          }
        }
        paramContext = localCursor1;
        if (paramInt == 1)
        {
          paramContext = null;
          try
          {
            localCursor1 = paramContentResolver.query(paramUri, PROJECTION, "video_id=" + paramLong1, null, null);
            localObject1 = localObject2;
            if (localCursor1 != null)
            {
              paramContext = localCursor1;
              localObject1 = localObject2;
              if (localCursor1.moveToFirst())
              {
                paramContext = localCursor1;
                localObject1 = getMiniThumbFromFile(localCursor1, paramUri, paramContentResolver, ???);
                paramContext = (Context)localObject1;
                localObject1 = paramContext;
                if (paramContext != null) {
                  return paramContext;
                }
              }
            }
          }
          finally
          {
            if (paramContext != null) {
              paramContext.close();
            }
          }
          paramContext = (Context)localObject1;
          if (localCursor1 != null)
          {
            localCursor1.close();
            paramContext = (Context)localObject1;
          }
        }
      }
      localObject3 = null;
      localCursor2 = null;
      localObject2 = paramContext;
      for (;;)
      {
        try
        {
          localCursor1 = paramContentResolver.query(paramUri.buildUpon().appendQueryParameter("blocking", "1").appendQueryParameter("orig_id", String.valueOf(paramLong1)).appendQueryParameter("group_id", String.valueOf(paramLong2)).build(), PROJECTION, null, null, null);
          if (localCursor1 == null)
          {
            if (localCursor1 != null) {
              localCursor1.close();
            }
            return null;
          }
          if (paramInt != 3) {
            continue;
          }
          localObject2 = paramContext;
          localCursor2 = localCursor1;
          localObject3 = localCursor1;
          ??? = sThumbBufLock;
          localObject2 = paramContext;
          localCursor2 = localCursor1;
          localObject3 = localCursor1;
          paramContentResolver = paramContext;
        }
        catch (SQLiteException paramContext)
        {
          localObject3 = localCursor2;
          Log.e("getThumbnail", paramContext);
          paramContext = (Context)localObject2;
          if (localCursor2 == null) {
            continue;
          }
          localCursor2.close();
          paramContext = (Context)localObject2;
          continue;
          if (paramInt != 1) {
            continue;
          }
          localObject1 = paramContext;
          localObject2 = paramContext;
          localCursor2 = localCursor1;
          localObject3 = localCursor1;
          if (!localCursor1.moveToFirst()) {
            continue;
          }
          localObject2 = paramContext;
          localCursor2 = localCursor1;
          localObject3 = localCursor1;
          localObject1 = getMiniThumbFromFile(localCursor1, paramUri, paramContentResolver, ???);
          continue;
          localObject2 = paramContext;
          localCursor2 = localCursor1;
          localObject3 = localCursor1;
          throw new IllegalArgumentException("Unsupported kind: " + paramInt);
        }
        finally
        {
          if (localObject3 == null) {
            continue;
          }
          ((Cursor)localObject3).close();
        }
        try
        {
          if (sThumbBuf == null)
          {
            paramContentResolver = paramContext;
            sThumbBuf = new byte['✐'];
          }
          localObject1 = paramContext;
          paramContentResolver = paramContext;
          if (localMiniThumbFile.getMiniThumbFromFile(paramLong1, sThumbBuf) != null)
          {
            paramContentResolver = paramContext;
            paramContext = BitmapFactory.decodeByteArray(sThumbBuf, 0, sThumbBuf.length);
            localObject1 = paramContext;
            if (paramContext == null)
            {
              paramContentResolver = paramContext;
              Log.d("couldn't decode byte array.", new Object[0]);
              localObject1 = paramContext;
            }
          }
          paramContentResolver = (ContentResolver)localObject1;
          paramContext = (Context)localObject1;
          if (localCursor1 != null)
          {
            localCursor1.close();
            paramContext = (Context)localObject1;
          }
          return paramContext;
        }
        finally
        {
          localObject2 = paramContentResolver;
          localCursor2 = localCursor1;
          localObject3 = localCursor1;
        }
      }
    }
    
    static String getThumbnailPath(Context paramContext, ContentResolver paramContentResolver, long paramLong, Uri paramUri)
    {
      String str = "";
      paramContext = null;
      try
      {
        paramUri = paramContentResolver.query(paramUri, PROJECTION, "video_id=" + paramLong, null, null);
        paramContentResolver = str;
        if (paramUri != null)
        {
          paramContentResolver = str;
          paramContext = paramUri;
          if (paramUri.moveToFirst())
          {
            paramContext = paramUri;
            paramContentResolver = paramUri.getString(paramUri.getColumnIndex("_data"));
          }
        }
        return paramContentResolver;
      }
      finally
      {
        if (paramContext != null) {
          paramContext.close();
        }
      }
    }
  }
  
  public static abstract interface MediaColumns
    extends BaseColumns
  {
    public static final String AVAILABLE_SIZE = "available_size";
    public static final String DATA = "_data";
    public static final String DATE_ADDED = "date_added";
    public static final String DATE_MODIFIED = "date_modified";
    public static final String DIRECTORY = "_directory";
    public static final String DIRECTORY_NAME = "_directory_name";
    public static final String DISPLAY_NAME = "_display_name";
    public static final String MIME_TYPE = "mime_type";
    public static final String PLAY_STATUS = "play_status";
    public static final String SIZE = "_size";
    public static final String TITLE = "title";
    public static final String TITLE_KEY = "title_key";
  }
  
  public static final class Video
  {
    public static final class Media
      implements MediaStore.Video.VideoColumns
    {
      public static final String CONTENT_TYPE = "vnd.android.cursor.dir/video";
      public static final Uri CONTENT_URI = Uri.parse("content://me.abitno.vplayer.mediaprovider/videos/media");
      protected static final String SQL_FIELDS = "_id INTEGER PRIMARY KEY,_data TEXT NOT NULL,_directory TEXT NOT NULL,_directory_name TEXT NOT NULL,_size INTEGER,_display_name TEXT,title TEXT,title_key TEXT,date_added INTEGER,date_modified INTEGER,mime_type TEXT,available_size INTEGER default 0,play_status INTEGER ,duration INTEGER,artist TEXT,album TEXT,width INTEGER,height INTEGER,description TEXT,language TEXT,latitude DOUBLE,longitude DOUBLE,datetaken INTEGER,bookmark INTEGER,mini_thumb_magic INTEGER,hidden INTEGER default 0,sub_track TEXT,audio_track INTEGER";
      protected static final String SQL_TRIGGER_VIDEO_CLEANUP = "CREATE TRIGGER IF NOT EXISTS video_cleanup AFTER DELETE ON videos BEGIN SELECT _DELETE_FILE(old._data);SELECT _DELETE_FILE(old._data || '.ssi');END";
      protected static final String SQL_TRIGGER_VIDEO_UPDATE = "CREATE TRIGGER IF NOT EXISTS video_update AFTER UPDATE ON videos WHEN new._data <> old._data BEGIN SELECT _DELETE_FILE(old._data || '.ssi');END";
      protected static final String TABLE_NAME = "videos";
    }
    
    public static class Thumbnails
      implements BaseColumns
    {
      public static final Uri CONTENT_URI = Uri.parse("content://me.abitno.vplayer.mediaprovider/videos/thumbnails");
      public static final String DATA = "_data";
      public static final String HEIGHT = "height";
      public static final String KIND = "kind";
      public static final int MICRO_KIND = 3;
      public static final int MINI_KIND = 1;
      protected static final String SQL_FIELDS = "_id INTEGER PRIMARY KEY,_data TEXT,video_id INTEGER,kind INTEGER,width INTEGER,height INTEGER";
      protected static final String SQL_INDEX_VIDEO_ID = "CREATE INDEX IF NOT EXISTS video_id_index on videothumbnails(video_id);";
      protected static final String SQL_TRIGGER_VIDEO_THUMBNAILS_CLEANUP = "CREATE TRIGGER IF NOT EXISTS videothumbnails_cleanup DELETE ON videothumbnails BEGIN SELECT _DELETE_FILE(old._data);END";
      protected static final String TABLE_NAME = "videothumbnails";
      public static final String THUMBNAILS_DIRECTORY = "Android/data/me.abitno.vplayer.t/thumbnails";
      public static final String VIDEO_ID = "video_id";
      public static final String WIDTH = "width";
      
      public static void cancelThumbnailRequest(ContentResolver paramContentResolver, long paramLong)
      {
        MediaStore.InternalThumbnails.cancelThumbnailRequest(paramContentResolver, paramLong, CONTENT_URI, 0L);
      }
      
      public static void cancelThumbnailRequest(ContentResolver paramContentResolver, long paramLong1, long paramLong2)
      {
        MediaStore.InternalThumbnails.cancelThumbnailRequest(paramContentResolver, paramLong1, CONTENT_URI, paramLong2);
      }
      
      public static Bitmap getThumbnail(Context paramContext, ContentResolver paramContentResolver, long paramLong, int paramInt, BitmapFactory.Options paramOptions)
      {
        return MediaStore.InternalThumbnails.getThumbnail(paramContext, paramContentResolver, paramLong, 0L, paramInt, paramOptions, CONTENT_URI);
      }
      
      public static Bitmap getThumbnail(Context paramContext, ContentResolver paramContentResolver, long paramLong1, long paramLong2, int paramInt, BitmapFactory.Options paramOptions)
      {
        return MediaStore.InternalThumbnails.getThumbnail(paramContext, paramContentResolver, paramLong1, paramLong2, paramInt, paramOptions, CONTENT_URI);
      }
      
      public static String getThumbnailPath(Context paramContext, ContentResolver paramContentResolver, long paramLong)
      {
        return MediaStore.InternalThumbnails.getThumbnailPath(paramContext, paramContentResolver, paramLong, CONTENT_URI);
      }
    }
    
    public static abstract interface VideoColumns
      extends MediaStore.MediaColumns
    {
      public static final String ALBUM = "album";
      public static final String ARTIST = "artist";
      public static final String AUDIO_TRACK = "audio_track";
      public static final String BOOKMARK = "bookmark";
      public static final String DATE_TAKEN = "datetaken";
      public static final String DESCRIPTION = "description";
      public static final String DURATION = "duration";
      public static final String HEIGHT = "height";
      public static final String HIDDEN = "hidden";
      public static final String LANGUAGE = "language";
      public static final String LATITUDE = "latitude";
      public static final String LONGITUDE = "longitude";
      public static final String MINI_THUMB_MAGIC = "mini_thumb_magic";
      public static final String SUBTRACK = "sub_track";
      public static final String WIDTH = "width";
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\io\vov\vitamio\provider\MediaStore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */