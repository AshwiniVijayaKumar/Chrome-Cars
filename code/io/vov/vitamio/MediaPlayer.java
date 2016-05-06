package io.vov.vitamio;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.media.AudioTrack;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.ParcelFileDescriptor;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.Surface;
import android.view.SurfaceHolder;
import io.vov.vitamio.utils.FileUtils;
import io.vov.vitamio.utils.Log;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class MediaPlayer
{
  public static final int CACHE_INFO_NO_SPACE = 1;
  public static final int CACHE_INFO_STREAM_NOT_SUPPORT = 2;
  public static final int CACHE_TYPE_COMPLETE = 5;
  public static final int CACHE_TYPE_NOT_AVAILABLE = 1;
  public static final int CACHE_TYPE_SPEED = 4;
  public static final int CACHE_TYPE_START = 2;
  public static final int CACHE_TYPE_UPDATE = 3;
  private static final int MEDIA_BUFFERING_UPDATE = 3;
  private static final int MEDIA_CACHE = 300;
  private static final String MEDIA_CACHING_INFO = "caching_info";
  private static final String MEDIA_CACHING_SEGMENTS = "caching_segment";
  private static final String MEDIA_CACHING_TYPE = "caching_type";
  private static final int MEDIA_CACHING_UPDATE = 2000;
  private static final int MEDIA_ERROR = 100;
  public static final int MEDIA_ERROR_IO = -5;
  public static final int MEDIA_ERROR_MALFORMED = -1007;
  public static final int MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK = 200;
  public static final int MEDIA_ERROR_TIMED_OUT = -110;
  public static final int MEDIA_ERROR_UNKNOWN = 1;
  public static final int MEDIA_ERROR_UNSUPPORTED = -1010;
  private static final int MEDIA_HW_ERROR = 400;
  private static final int MEDIA_INFO = 200;
  public static final int MEDIA_INFO_BUFFERING_END = 702;
  public static final int MEDIA_INFO_BUFFERING_START = 701;
  public static final int MEDIA_INFO_DOWNLOAD_RATE_CHANGED = 901;
  public static final int MEDIA_INFO_FILE_OPEN_OK = 704;
  public static final int MEDIA_INFO_GET_CODEC_INFO_ERROR = 1002;
  public static final int MEDIA_INFO_NOT_SEEKABLE = 801;
  public static final int MEDIA_INFO_UNKNOW_TYPE = 1001;
  public static final int MEDIA_INFO_VIDEO_TRACK_LAGGING = 700;
  private static final int MEDIA_NOP = 0;
  private static final int MEDIA_PLAYBACK_COMPLETE = 2;
  private static final int MEDIA_PREPARED = 1;
  private static final int MEDIA_SEEK_COMPLETE = 4;
  private static final int MEDIA_SET_VIDEO_SIZE = 5;
  private static final String MEDIA_SUBTITLE_BYTES = "sub_bytes";
  private static final String MEDIA_SUBTITLE_STRING = "sub_string";
  private static final String MEDIA_SUBTITLE_TYPE = "sub_type";
  private static final int MEDIA_TIMED_TEXT = 1000;
  private static AtomicBoolean NATIVE_OMX_LOADED;
  private static final int SUBTITLE_BITMAP = 1;
  public static final int SUBTITLE_EXTERNAL = 1;
  public static final int SUBTITLE_INTERNAL = 0;
  private static final int SUBTITLE_TEXT = 0;
  public static final String[] SUB_TYPES = { ".srt", ".ssa", ".smi", ".txt", ".sub", ".ass", ".webvtt" };
  public static final int VIDEOCHROMA_RGB565 = 0;
  public static final int VIDEOCHROMA_RGBA = 1;
  public static final int VIDEOQUALITY_HIGH = 16;
  public static final int VIDEOQUALITY_LOW = -16;
  public static final int VIDEOQUALITY_MEDIUM = 0;
  private static String path;
  int channels;
  private AudioTrack mAudioTrack;
  private int mAudioTrackBufferSize;
  private Bitmap mBitmap;
  private ByteBuffer mByteBuffer;
  private Context mContext;
  private EventHandler mEventHandler;
  private AssetFileDescriptor mFD = null;
  private boolean mInBuffering = false;
  private TrackInfo[] mInbandTracks;
  private Surface mLocalSurface;
  private Metadata mMeta;
  private boolean mNeedResume = false;
  private OnBufferingUpdateListener mOnBufferingUpdateListener;
  private OnCachingUpdateListener mOnCachingUpdateListener;
  private OnCompletionListener mOnCompletionListener;
  private OnErrorListener mOnErrorListener;
  private OnHWRenderFailedListener mOnHWRenderFailedListener;
  private OnInfoListener mOnInfoListener;
  private OnPreparedListener mOnPreparedListener;
  private OnSeekCompleteListener mOnSeekCompleteListener;
  private OnTimedTextListener mOnTimedTextListener;
  private OnVideoSizeChangedListener mOnVideoSizeChangedListener;
  private TrackInfo mOutOfBandTracks;
  private boolean mScreenOnWhilePlaying;
  private boolean mStayAwake;
  private Surface mSurface;
  private SurfaceHolder mSurfaceHolder;
  private PowerManager.WakeLock mWakeLock = null;
  int sampleRateInHz;
  
  static
  {
    NATIVE_OMX_LOADED = new AtomicBoolean(false);
    String str = Vitamio.getLibraryPath();
    try
    {
      System.load(str + "libstlport_shared.so");
      System.load(str + "libvplayer.so");
      loadFFmpeg_native(str + "libffmpeg.so");
      boolean bool;
      if (Build.VERSION.SDK_INT > 8) {
        bool = loadVVO_native(str + "libvvo.9.so");
      }
      for (;;)
      {
        if (!bool)
        {
          bool = loadVVO_native(str + "libvvo.j.so");
          Log.d("FALLBACK TO VVO JNI " + bool, new Object[0]);
        }
        loadVAO_native(str + "libvao.0.so");
        return;
        if (Build.VERSION.SDK_INT > 7) {
          bool = loadVVO_native(str + "libvvo.8.so");
        } else {
          bool = loadVVO_native(str + "libvvo.7.so");
        }
      }
      return;
    }
    catch (UnsatisfiedLinkError localUnsatisfiedLinkError)
    {
      Log.e("Error loading libs", localUnsatisfiedLinkError);
    }
  }
  
  public MediaPlayer(Context paramContext)
  {
    this(paramContext, false);
  }
  
  public MediaPlayer(Context paramContext, boolean paramBoolean)
  {
    this.mContext = paramContext;
    paramContext = Vitamio.getLibraryPath();
    if (paramBoolean) {
      if (!NATIVE_OMX_LOADED.get())
      {
        if (Build.VERSION.SDK_INT > 17)
        {
          loadOMX_native(paramContext + "libOMX.18.so");
          NATIVE_OMX_LOADED.set(true);
        }
      }
      else
      {
        paramContext = Looper.myLooper();
        if (paramContext == null) {
          break label243;
        }
        this.mEventHandler = new EventHandler(this, paramContext);
      }
    }
    for (;;)
    {
      native_init();
      return;
      if (Build.VERSION.SDK_INT > 13)
      {
        loadOMX_native(paramContext + "libOMX.14.so");
        break;
      }
      if (Build.VERSION.SDK_INT > 10)
      {
        loadOMX_native(paramContext + "libOMX.11.so");
        break;
      }
      loadOMX_native(paramContext + "libOMX.9.so");
      break;
      try
      {
        unloadOMX_native();
        NATIVE_OMX_LOADED.set(false);
      }
      catch (UnsatisfiedLinkError paramContext)
      {
        for (;;)
        {
          Log.e("unloadOMX failed %s", new Object[] { paramContext.toString() });
        }
      }
      label243:
      paramContext = Looper.getMainLooper();
      if (paramContext != null) {
        this.mEventHandler = new EventHandler(this, paramContext);
      } else {
        this.mEventHandler = null;
      }
    }
  }
  
  private native void _pause()
    throws IllegalStateException;
  
  private native void _release();
  
  private native void _reset();
  
  private native void _setDataSegmentsSource(String[] paramArrayOfString, String paramString);
  
  private native void _setDataSource(String paramString, String[] paramArrayOfString1, String[] paramArrayOfString2)
    throws IOException, IllegalArgumentException, IllegalStateException;
  
  private native void _setVideoSurface(Surface paramSurface);
  
  private native void _start()
    throws IllegalStateException;
  
  private native void _stop()
    throws IllegalStateException;
  
  @SuppressLint({"NewApi"})
  private int audioTrackInit(int paramInt1, int paramInt2)
  {
    this.sampleRateInHz = paramInt1;
    this.channels = paramInt2;
    return 0;
  }
  
  private void audioTrackPause()
  {
    if ((this.mAudioTrack != null) && (this.mAudioTrack.getState() == 1)) {
      this.mAudioTrack.pause();
    }
  }
  
  private void audioTrackRelease()
  {
    if (this.mAudioTrack != null)
    {
      if (this.mAudioTrack.getState() == 1) {
        this.mAudioTrack.stop();
      }
      this.mAudioTrack.release();
    }
    this.mAudioTrack = null;
  }
  
  private void audioTrackSetVolume(float paramFloat1, float paramFloat2)
  {
    if (this.mAudioTrack != null) {
      this.mAudioTrack.setStereoVolume(paramFloat1, paramFloat2);
    }
  }
  
  private void audioTrackStart()
  {
    if ((this.mAudioTrack != null) && (this.mAudioTrack.getState() == 1) && (this.mAudioTrack.getPlayState() != 3)) {
      this.mAudioTrack.play();
    }
  }
  
  private void audioTrackWrite(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if ((this.mAudioTrack != null) && (this.mAudioTrack.getPlayState() == 3))
    {
      if (paramInt2 > 0) {}
    }
    else {
      return;
    }
    if (paramInt2 > this.mAudioTrackBufferSize) {}
    for (int i = this.mAudioTrackBufferSize;; i = paramInt2)
    {
      this.mAudioTrack.write(paramArrayOfByte, paramInt1, i);
      paramInt2 -= i;
      paramInt1 += i;
      break;
    }
  }
  
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
      for (;;)
      {
        Log.e("closeFD", localIOException);
      }
    }
  }
  
  private TrackInfo[] getInbandTrackInfo(String paramString)
  {
    SparseArray localSparseArray;
    int j;
    int i;
    if (this.mInbandTracks == null)
    {
      localSparseArray = new SparseArray();
      if (!native_getTrackInfo(localSparseArray)) {
        return null;
      }
      j = localSparseArray.size();
      this.mInbandTracks = new TrackInfo[j];
      i = 0;
    }
    for (;;)
    {
      if (i >= j) {
        return this.mInbandTracks;
      }
      Object localObject = parseTrackInfo((byte[])localSparseArray.valueAt(i), paramString);
      localObject = new TrackInfo(localSparseArray.keyAt(i), (SparseArray)localObject);
      this.mInbandTracks[i] = localObject;
      i += 1;
    }
  }
  
  private native int getVideoHeight_a();
  
  private native int getVideoWidth_a();
  
  private static native boolean loadFFmpeg_native(String paramString);
  
  private static native boolean loadOMX_native(String paramString);
  
  private static native boolean loadVAO_native(String paramString);
  
  private static native boolean loadVVO_native(String paramString);
  
  private final native void native_finalize();
  
  private final native boolean native_getMetadata(Map<byte[], byte[]> paramMap);
  
  private final native boolean native_getTrackInfo(SparseArray<byte[]> paramSparseArray);
  
  private final native void native_init();
  
  private SparseArray<MediaFormat> parseTrackInfo(byte[] paramArrayOfByte, String paramString)
  {
    int i = 0;
    SparseArray localSparseArray = new SparseArray();
    try
    {
      paramString = new String(paramArrayOfByte, paramString);
      paramArrayOfByte = paramString;
    }
    catch (Exception paramString)
    {
      for (;;)
      {
        int j;
        Log.e("getTrackMap exception", new Object[0]);
        paramArrayOfByte = new String(paramArrayOfByte);
      }
      arrayOfString = paramString[i];
      paramArrayOfByte = null;
    }
    paramString = paramArrayOfByte.split("!#!");
    j = paramString.length;
    for (;;)
    {
      if (i >= j) {
        return localSparseArray;
      }
      try
      {
        String[] arrayOfString = arrayOfString.split("\\.");
        if (arrayOfString != null)
        {
          int k = Integer.parseInt(arrayOfString[0]);
          if (arrayOfString.length == 3) {
            paramArrayOfByte = MediaFormat.createSubtitleFormat(arrayOfString[2], arrayOfString[1]);
          }
          for (;;)
          {
            localSparseArray.put(k, paramArrayOfByte);
            break;
            if (arrayOfString.length == 2) {
              paramArrayOfByte = MediaFormat.createSubtitleFormat("", arrayOfString[1]);
            }
          }
        }
      }
      catch (NumberFormatException paramArrayOfByte)
      {
        for (;;) {}
      }
      i += 1;
    }
  }
  
  private static void postEventFromNative(Object paramObject1, int paramInt1, int paramInt2, int paramInt3, Object paramObject2)
  {
    paramObject1 = (MediaPlayer)paramObject1;
    if (paramObject1 == null) {}
    for (;;)
    {
      return;
      try
      {
        if (((MediaPlayer)paramObject1).mEventHandler != null)
        {
          paramObject2 = ((MediaPlayer)paramObject1).mEventHandler.obtainMessage(paramInt1, paramInt2, paramInt3, paramObject2);
          ((MediaPlayer)paramObject1).mEventHandler.sendMessage((Message)paramObject2);
          return;
        }
      }
      catch (Exception paramObject1)
      {
        Log.e("exception: " + paramObject1, new Object[0]);
      }
    }
  }
  
  private void selectOrDeselectBandTrack(int paramInt, boolean paramBoolean)
  {
    if (this.mOutOfBandTracks != null)
    {
      Object localObject = this.mOutOfBandTracks.getTrackInfoArray();
      int i = ((SparseArray)localObject).keyAt(0);
      localObject = (MediaFormat)((SparseArray)localObject).valueAt(0);
      if ((paramInt == i) && (paramBoolean))
      {
        addTimedTextSource(((MediaFormat)localObject).getString("path"));
        return;
      }
    }
    selectOrDeselectTrack(paramInt, paramBoolean);
  }
  
  private native void selectOrDeselectTrack(int paramInt, boolean paramBoolean);
  
  @SuppressLint({"Wakelock"})
  private void stayAwake(boolean paramBoolean)
  {
    if (this.mWakeLock != null)
    {
      if ((!paramBoolean) || (this.mWakeLock.isHeld())) {
        break label38;
      }
      this.mWakeLock.acquire();
    }
    for (;;)
    {
      this.mStayAwake = paramBoolean;
      updateSurfaceScreenOn();
      return;
      label38:
      if ((!paramBoolean) && (this.mWakeLock.isHeld())) {
        this.mWakeLock.release();
      }
    }
  }
  
  /* Error */
  private ByteBuffer surfaceInit()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_0
    //   4: getfield 612	io/vov/vitamio/MediaPlayer:mSurface	Landroid/view/Surface;
    //   7: putfield 614	io/vov/vitamio/MediaPlayer:mLocalSurface	Landroid/view/Surface;
    //   10: aload_0
    //   11: invokespecial 616	io/vov/vitamio/MediaPlayer:getVideoWidth_a	()I
    //   14: istore_1
    //   15: aload_0
    //   16: invokespecial 618	io/vov/vitamio/MediaPlayer:getVideoHeight_a	()I
    //   19: istore_2
    //   20: aload_0
    //   21: getfield 614	io/vov/vitamio/MediaPlayer:mLocalSurface	Landroid/view/Surface;
    //   24: ifnull +44 -> 68
    //   27: iload_1
    //   28: ifeq +40 -> 68
    //   31: iload_2
    //   32: ifeq +36 -> 68
    //   35: aload_0
    //   36: iload_1
    //   37: iload_2
    //   38: getstatic 624	android/graphics/Bitmap$Config:RGB_565	Landroid/graphics/Bitmap$Config;
    //   41: invokestatic 630	android/graphics/Bitmap:createBitmap	(IILandroid/graphics/Bitmap$Config;)Landroid/graphics/Bitmap;
    //   44: putfield 632	io/vov/vitamio/MediaPlayer:mBitmap	Landroid/graphics/Bitmap;
    //   47: aload_0
    //   48: iload_1
    //   49: iload_2
    //   50: imul
    //   51: iconst_2
    //   52: imul
    //   53: invokestatic 638	java/nio/ByteBuffer:allocateDirect	(I)Ljava/nio/ByteBuffer;
    //   56: putfield 640	io/vov/vitamio/MediaPlayer:mByteBuffer	Ljava/nio/ByteBuffer;
    //   59: aload_0
    //   60: getfield 640	io/vov/vitamio/MediaPlayer:mByteBuffer	Ljava/nio/ByteBuffer;
    //   63: astore_3
    //   64: aload_0
    //   65: monitorexit
    //   66: aload_3
    //   67: areturn
    //   68: aload_0
    //   69: aconst_null
    //   70: putfield 632	io/vov/vitamio/MediaPlayer:mBitmap	Landroid/graphics/Bitmap;
    //   73: aload_0
    //   74: aconst_null
    //   75: putfield 640	io/vov/vitamio/MediaPlayer:mByteBuffer	Ljava/nio/ByteBuffer;
    //   78: goto -19 -> 59
    //   81: astore_3
    //   82: aload_0
    //   83: monitorexit
    //   84: aload_3
    //   85: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	86	0	this	MediaPlayer
    //   14	37	1	i	int
    //   19	32	2	j	int
    //   63	4	3	localByteBuffer	ByteBuffer
    //   81	4	3	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	27	81	finally
    //   35	59	81	finally
    //   59	66	81	finally
    //   68	78	81	finally
    //   82	84	81	finally
  }
  
  private void surfaceRelease()
  {
    try
    {
      this.mLocalSurface = null;
      this.mBitmap = null;
      this.mByteBuffer = null;
      return;
    }
    finally {}
  }
  
  /* Error */
  private void surfaceRender()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 614	io/vov/vitamio/MediaPlayer:mLocalSurface	Landroid/view/Surface;
    //   6: ifnull +27 -> 33
    //   9: aload_0
    //   10: getfield 614	io/vov/vitamio/MediaPlayer:mLocalSurface	Landroid/view/Surface;
    //   13: invokevirtual 647	android/view/Surface:isValid	()Z
    //   16: ifeq +17 -> 33
    //   19: aload_0
    //   20: getfield 632	io/vov/vitamio/MediaPlayer:mBitmap	Landroid/graphics/Bitmap;
    //   23: ifnull +10 -> 33
    //   26: aload_0
    //   27: getfield 640	io/vov/vitamio/MediaPlayer:mByteBuffer	Ljava/nio/ByteBuffer;
    //   30: ifnonnull +6 -> 36
    //   33: aload_0
    //   34: monitorexit
    //   35: return
    //   36: aload_0
    //   37: getfield 614	io/vov/vitamio/MediaPlayer:mLocalSurface	Landroid/view/Surface;
    //   40: aconst_null
    //   41: invokevirtual 651	android/view/Surface:lockCanvas	(Landroid/graphics/Rect;)Landroid/graphics/Canvas;
    //   44: astore_1
    //   45: aload_0
    //   46: getfield 632	io/vov/vitamio/MediaPlayer:mBitmap	Landroid/graphics/Bitmap;
    //   49: aload_0
    //   50: getfield 640	io/vov/vitamio/MediaPlayer:mByteBuffer	Ljava/nio/ByteBuffer;
    //   53: invokevirtual 655	android/graphics/Bitmap:copyPixelsFromBuffer	(Ljava/nio/Buffer;)V
    //   56: aload_1
    //   57: aload_0
    //   58: getfield 632	io/vov/vitamio/MediaPlayer:mBitmap	Landroid/graphics/Bitmap;
    //   61: fconst_0
    //   62: fconst_0
    //   63: aconst_null
    //   64: invokevirtual 661	android/graphics/Canvas:drawBitmap	(Landroid/graphics/Bitmap;FFLandroid/graphics/Paint;)V
    //   67: aload_0
    //   68: getfield 614	io/vov/vitamio/MediaPlayer:mLocalSurface	Landroid/view/Surface;
    //   71: aload_1
    //   72: invokevirtual 665	android/view/Surface:unlockCanvasAndPost	(Landroid/graphics/Canvas;)V
    //   75: aload_0
    //   76: monitorexit
    //   77: return
    //   78: astore_1
    //   79: aload_0
    //   80: monitorexit
    //   81: aload_1
    //   82: athrow
    //   83: astore_1
    //   84: ldc_w 666
    //   87: aload_1
    //   88: invokestatic 291	io/vov/vitamio/utils/Log:e	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   91: goto -16 -> 75
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	94	0	this	MediaPlayer
    //   44	28	1	localCanvas	android.graphics.Canvas
    //   78	4	1	localObject	Object
    //   83	5	1	localException	Exception
    // Exception table:
    //   from	to	target	type
    //   2	33	78	finally
    //   33	35	78	finally
    //   36	75	78	finally
    //   75	77	78	finally
    //   79	81	78	finally
    //   84	91	78	finally
    //   36	75	83	java/lang/Exception
  }
  
  private static native void unloadOMX_native();
  
  private void updateCacheStatus(int paramInt1, int paramInt2, long[] paramArrayOfLong)
  {
    if (this.mEventHandler != null)
    {
      Message localMessage = this.mEventHandler.obtainMessage(2000);
      Bundle localBundle = localMessage.getData();
      localBundle.putInt("caching_type", paramInt1);
      localBundle.putInt("caching_info", paramInt2);
      localBundle.putLongArray("caching_segment", paramArrayOfLong);
      this.mEventHandler.sendMessage(localMessage);
    }
  }
  
  private void updateSub(int paramInt1, byte[] paramArrayOfByte, String paramString, int paramInt2, int paramInt3)
  {
    Message localMessage;
    Bundle localBundle;
    if (this.mEventHandler != null)
    {
      localMessage = this.mEventHandler.obtainMessage(1000, paramInt2, paramInt3);
      localBundle = localMessage.getData();
      if (paramInt1 != 0) {
        break label120;
      }
      localBundle.putInt("sub_type", 0);
      if (paramString != null) {
        break label72;
      }
      localBundle.putString("sub_string", new String(paramArrayOfByte));
    }
    for (;;)
    {
      this.mEventHandler.sendMessage(localMessage);
      return;
      try
      {
        label72:
        localBundle.putString("sub_string", new String(paramArrayOfByte, paramString.trim()));
      }
      catch (UnsupportedEncodingException paramString)
      {
        Log.e("updateSub", paramString);
        localBundle.putString("sub_string", new String(paramArrayOfByte));
      }
      continue;
      label120:
      if (paramInt1 == 1)
      {
        localBundle.putInt("sub_type", 1);
        localBundle.putByteArray("sub_bytes", paramArrayOfByte);
      }
    }
  }
  
  private void updateSurfaceScreenOn()
  {
    SurfaceHolder localSurfaceHolder;
    if (this.mSurfaceHolder != null)
    {
      localSurfaceHolder = this.mSurfaceHolder;
      if ((!this.mScreenOnWhilePlaying) || (!this.mStayAwake)) {
        break label36;
      }
    }
    label36:
    for (boolean bool = true;; bool = false)
    {
      localSurfaceHolder.setKeepScreenOn(bool);
      return;
    }
  }
  
  protected native void _releaseVideoSurface();
  
  public native void addTimedTextSource(String paramString);
  
  public native void audioInitedOk(long paramLong);
  
  public int audioTrackInit()
  {
    audioTrackRelease();
    int i;
    if (this.channels >= 2) {
      i = 12;
    }
    try
    {
      for (;;)
      {
        this.mAudioTrackBufferSize = AudioTrack.getMinBufferSize(this.sampleRateInHz, i, 2);
        this.mAudioTrack = new AudioTrack(3, this.sampleRateInHz, i, 2, this.mAudioTrackBufferSize, 1);
        return this.mAudioTrackBufferSize;
        i = 4;
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        this.mAudioTrackBufferSize = 0;
        Log.e("audioTrackInit", localException);
      }
    }
  }
  
  public void deselectTrack(int paramInt)
  {
    selectOrDeselectBandTrack(paramInt, false);
  }
  
  protected void finalize()
  {
    native_finalize();
  }
  
  public SparseArray<MediaFormat> findTrackFromTrackInfo(int paramInt, TrackInfo[] paramArrayOfTrackInfo)
  {
    int i = 0;
    for (;;)
    {
      if (i >= paramArrayOfTrackInfo.length) {
        return null;
      }
      if (paramArrayOfTrackInfo[i].getTrackType() == paramInt) {
        return paramArrayOfTrackInfo[i].getTrackInfoArray();
      }
      i += 1;
    }
  }
  
  public int getAudioSessionId()
  {
    return this.mAudioTrack.getAudioSessionId();
  }
  
  public native int getAudioTrack();
  
  public native int getBufferProgress();
  
  public native Bitmap getCurrentFrame();
  
  public native long getCurrentPosition();
  
  public native long getDuration();
  
  public native String getMetaEncoding();
  
  public Metadata getMetadata()
  {
    if (this.mMeta == null)
    {
      this.mMeta = new Metadata();
      HashMap localHashMap = new HashMap();
      if (!native_getMetadata(localHashMap)) {}
      while (!this.mMeta.parse(localHashMap, getMetaEncoding())) {
        return null;
      }
    }
    return this.mMeta;
  }
  
  public native int getTimedTextLocation();
  
  public native String getTimedTextPath();
  
  public native int getTimedTextTrack();
  
  public TrackInfo[] getTrackInfo()
  {
    return getTrackInfo(Charset.defaultCharset().name());
  }
  
  public TrackInfo[] getTrackInfo(String paramString)
  {
    Object localObject = getInbandTrackInfo(paramString);
    String str = getTimedTextPath();
    if (TextUtils.isEmpty(str)) {
      return (TrackInfo[])localObject;
    }
    paramString = new TrackInfo[localObject.length + 1];
    System.arraycopy(localObject, 0, paramString, 0, localObject.length);
    int i = localObject.length;
    SparseArray localSparseArray = new SparseArray();
    MediaFormat localMediaFormat = new MediaFormat();
    localMediaFormat.setString("title", str.substring(str.lastIndexOf("/")));
    localMediaFormat.setString("path", str);
    localObject = findTrackFromTrackInfo(3, (TrackInfo[])localObject);
    if ((localObject == null) || (((SparseArray)localObject).size() == 0)) {
      localSparseArray.put(((SparseArray)localObject).keyAt(0), localMediaFormat);
    }
    for (;;)
    {
      this.mOutOfBandTracks = new TrackInfo(4, localSparseArray);
      paramString[i] = this.mOutOfBandTracks;
      return paramString;
      localSparseArray.put(((SparseArray)localObject).keyAt(((SparseArray)localObject).size() - 1), localMediaFormat);
    }
  }
  
  public native float getVideoAspectRatio();
  
  public native int getVideoHeight();
  
  public native int getVideoTrack();
  
  public native int getVideoWidth();
  
  public native boolean isBuffering();
  
  public native boolean isLooping();
  
  public native boolean isPlaying();
  
  public void pause()
    throws IllegalStateException
  {
    stayAwake(false);
    this.mNeedResume = false;
    _pause();
  }
  
  public native void prepare()
    throws IOException, IllegalStateException;
  
  public native void prepareAsync()
    throws IllegalStateException;
  
  public void release()
  {
    stayAwake(false);
    updateSurfaceScreenOn();
    this.mOnPreparedListener = null;
    this.mOnBufferingUpdateListener = null;
    this.mOnCompletionListener = null;
    this.mOnSeekCompleteListener = null;
    this.mOnErrorListener = null;
    this.mOnInfoListener = null;
    this.mOnVideoSizeChangedListener = null;
    this.mOnCachingUpdateListener = null;
    this.mOnHWRenderFailedListener = null;
    if (this.mEventHandler != null) {
      this.mEventHandler.release();
    }
    _release();
    closeFD();
    this.mInBuffering = false;
    this.mNeedResume = false;
  }
  
  public void releaseDisplay()
  {
    _releaseVideoSurface();
    this.mSurfaceHolder = null;
    this.mSurface = null;
  }
  
  public void reset()
  {
    stayAwake(false);
    _reset();
    if (this.mEventHandler != null) {
      this.mEventHandler.removeCallbacksAndMessages(null);
    }
    closeFD();
    this.mInBuffering = false;
    this.mNeedResume = false;
  }
  
  public native void seekTo(long paramLong)
    throws IllegalStateException;
  
  public void selectTrack(int paramInt)
  {
    selectOrDeselectBandTrack(paramInt, true);
  }
  
  public native void setAdaptiveStream(boolean paramBoolean);
  
  public native void setAudioAmplify(float paramFloat);
  
  public native void setBufferSize(long paramLong);
  
  public native void setCacheDirectory(String paramString);
  
  public void setDataSegments(String[] paramArrayOfString, String paramString)
  {
    _setDataSegmentsSource(paramArrayOfString, paramString);
  }
  
  public void setDataSource(Context paramContext, Uri paramUri)
    throws IOException, IllegalArgumentException, SecurityException, IllegalStateException
  {
    setDataSource(paramContext, paramUri, null);
  }
  
  public void setDataSource(Context paramContext, Uri paramUri, Map<String, String> paramMap)
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
        setDataSource(paramUri.toString(), paramMap);
      }
    }
  }
  
  public native void setDataSource(FileDescriptor paramFileDescriptor)
    throws IOException, IllegalArgumentException, IllegalStateException;
  
  public void setDataSource(String paramString)
    throws IOException, IllegalArgumentException, SecurityException, IllegalStateException
  {
    _setDataSource(paramString, null, null);
  }
  
  public void setDataSource(String paramString, Map<String, String> paramMap)
    throws IOException, IllegalArgumentException, SecurityException, IllegalStateException
  {
    String[] arrayOfString1 = null;
    String[] arrayOfString2 = null;
    int i;
    if (paramMap != null)
    {
      arrayOfString1 = new String[paramMap.size()];
      arrayOfString2 = new String[paramMap.size()];
      i = 0;
      paramMap = paramMap.entrySet().iterator();
    }
    for (;;)
    {
      if (!paramMap.hasNext())
      {
        setDataSource(paramString, arrayOfString1, arrayOfString2);
        return;
      }
      Map.Entry localEntry = (Map.Entry)paramMap.next();
      arrayOfString1[i] = ((String)localEntry.getKey());
      arrayOfString2[i] = ((String)localEntry.getValue());
      i += 1;
    }
  }
  
  public void setDataSource(String paramString, String[] paramArrayOfString1, String[] paramArrayOfString2)
    throws IOException, IllegalArgumentException, SecurityException, IllegalStateException
  {
    Object localObject = Uri.parse(paramString);
    if ("file".equals(((Uri)localObject).getScheme())) {
      paramString = ((Uri)localObject).getPath();
    }
    localObject = new File(paramString);
    if (((File)localObject).exists())
    {
      paramString = new FileInputStream((File)localObject);
      setDataSource(paramString.getFD());
      paramString.close();
      return;
    }
    _setDataSource(paramString, paramArrayOfString1, paramArrayOfString2);
  }
  
  public native void setDeinterlace(boolean paramBoolean);
  
  public void setDisplay(SurfaceHolder paramSurfaceHolder)
  {
    if (paramSurfaceHolder == null)
    {
      releaseDisplay();
      return;
    }
    this.mSurfaceHolder = paramSurfaceHolder;
    this.mSurface = paramSurfaceHolder.getSurface();
    _setVideoSurface(this.mSurface);
    updateSurfaceScreenOn();
  }
  
  public native void setLooping(boolean paramBoolean);
  
  public native void setMetaEncoding(String paramString);
  
  public void setOnBufferingUpdateListener(OnBufferingUpdateListener paramOnBufferingUpdateListener)
  {
    this.mOnBufferingUpdateListener = paramOnBufferingUpdateListener;
  }
  
  public void setOnCachingUpdateListener(OnCachingUpdateListener paramOnCachingUpdateListener)
  {
    this.mOnCachingUpdateListener = paramOnCachingUpdateListener;
  }
  
  public void setOnCompletionListener(OnCompletionListener paramOnCompletionListener)
  {
    this.mOnCompletionListener = paramOnCompletionListener;
  }
  
  public void setOnErrorListener(OnErrorListener paramOnErrorListener)
  {
    this.mOnErrorListener = paramOnErrorListener;
  }
  
  public void setOnHWRenderFailedListener(OnHWRenderFailedListener paramOnHWRenderFailedListener)
  {
    this.mOnHWRenderFailedListener = paramOnHWRenderFailedListener;
  }
  
  public void setOnInfoListener(OnInfoListener paramOnInfoListener)
  {
    this.mOnInfoListener = paramOnInfoListener;
  }
  
  public void setOnPreparedListener(OnPreparedListener paramOnPreparedListener)
  {
    this.mOnPreparedListener = paramOnPreparedListener;
  }
  
  public void setOnSeekCompleteListener(OnSeekCompleteListener paramOnSeekCompleteListener)
  {
    this.mOnSeekCompleteListener = paramOnSeekCompleteListener;
  }
  
  public void setOnTimedTextListener(OnTimedTextListener paramOnTimedTextListener)
  {
    this.mOnTimedTextListener = paramOnTimedTextListener;
  }
  
  public void setOnVideoSizeChangedListener(OnVideoSizeChangedListener paramOnVideoSizeChangedListener)
  {
    this.mOnVideoSizeChangedListener = paramOnVideoSizeChangedListener;
  }
  
  public native void setPlaybackSpeed(float paramFloat);
  
  public void setScreenOnWhilePlaying(boolean paramBoolean)
  {
    if (this.mScreenOnWhilePlaying != paramBoolean)
    {
      this.mScreenOnWhilePlaying = paramBoolean;
      updateSurfaceScreenOn();
    }
  }
  
  public void setSurface(Surface paramSurface)
  {
    if (paramSurface == null)
    {
      releaseDisplay();
      return;
    }
    this.mSurfaceHolder = null;
    this.mSurface = paramSurface;
    _setVideoSurface(this.mSurface);
    updateSurfaceScreenOn();
  }
  
  public native void setTimedTextEncoding(String paramString);
  
  public native void setTimedTextShown(boolean paramBoolean);
  
  public native void setUseCache(boolean paramBoolean);
  
  public native void setVideoChroma(int paramInt);
  
  public native void setVideoQuality(int paramInt);
  
  public native void setVolume(float paramFloat1, float paramFloat2);
  
  @SuppressLint({"Wakelock"})
  public void setWakeMode(Context paramContext, int paramInt)
  {
    int i = 0;
    int j = 0;
    if (this.mWakeLock != null)
    {
      i = j;
      if (this.mWakeLock.isHeld())
      {
        i = 1;
        this.mWakeLock.release();
      }
      this.mWakeLock = null;
    }
    this.mWakeLock = ((PowerManager)paramContext.getSystemService("power")).newWakeLock(0x20000000 | paramInt, MediaPlayer.class.getName());
    this.mWakeLock.setReferenceCounted(false);
    if (i != 0) {
      this.mWakeLock.acquire();
    }
  }
  
  public void start()
    throws IllegalStateException
  {
    stayAwake(true);
    if (this.mInBuffering)
    {
      this.mNeedResume = true;
      return;
    }
    _start();
  }
  
  public void stop()
    throws IllegalStateException
  {
    stayAwake(false);
    _stop();
    this.mInBuffering = false;
    this.mNeedResume = false;
  }
  
  @SuppressLint({"HandlerLeak"})
  private class EventHandler
    extends Handler
  {
    private Bundle mData;
    private MediaPlayer mMediaPlayer;
    
    public EventHandler(MediaPlayer paramMediaPlayer, Looper paramLooper)
    {
      super();
      this.mMediaPlayer = paramMediaPlayer;
    }
    
    private void onBufferingUpdate(Message paramMessage)
    {
      int i = paramMessage.arg1;
      if (MediaPlayer.this.mOnBufferingUpdateListener != null) {
        MediaPlayer.this.mOnBufferingUpdateListener.onBufferingUpdate(this.mMediaPlayer, paramMessage.arg1);
      }
      if ((i >= 100) && (MediaPlayer.this.mInBuffering))
      {
        MediaPlayer.this.mInBuffering = false;
        if (MediaPlayer.this.mNeedResume)
        {
          MediaPlayer.this._start();
          MediaPlayer.this.mNeedResume = false;
        }
        if (MediaPlayer.this.mOnInfoListener != null) {
          MediaPlayer.this.mOnInfoListener.onInfo(this.mMediaPlayer, 702, i);
        }
      }
    }
    
    private void onInfo(Message paramMessage)
    {
      switch (paramMessage.arg1)
      {
      }
      for (;;)
      {
        if (MediaPlayer.this.mOnInfoListener != null) {
          MediaPlayer.this.mOnInfoListener.onInfo(this.mMediaPlayer, paramMessage.arg1, paramMessage.arg2);
        }
        return;
        MediaPlayer.this.mInBuffering = true;
        if (MediaPlayer.this.isPlaying())
        {
          MediaPlayer.this._pause();
          MediaPlayer.this.mNeedResume = true;
          continue;
          MediaPlayer.this.mInBuffering = false;
          if (MediaPlayer.this.mNeedResume)
          {
            MediaPlayer.this._start();
            MediaPlayer.this.mNeedResume = false;
          }
        }
      }
    }
    
    public void handleMessage(Message paramMessage)
    {
      if (this.mMediaPlayer == null) {}
      do
      {
        int i;
        do
        {
          do
          {
            do
            {
              do
              {
                do
                {
                  do
                  {
                    do
                    {
                      do
                      {
                        do
                        {
                          return;
                          switch (paramMessage.what)
                          {
                          case 0: 
                          case 300: 
                          default: 
                            Log.e("Unknown message type " + paramMessage.what, new Object[0]);
                            return;
                          }
                        } while (MediaPlayer.this.mOnPreparedListener == null);
                        MediaPlayer.this.mOnPreparedListener.onPrepared(this.mMediaPlayer);
                        return;
                        if (MediaPlayer.this.mOnCompletionListener != null) {
                          MediaPlayer.this.mOnCompletionListener.onCompletion(this.mMediaPlayer);
                        }
                        MediaPlayer.this.stayAwake(false);
                        return;
                        onBufferingUpdate(paramMessage);
                        return;
                        if (MediaPlayer.this.isPlaying()) {
                          MediaPlayer.this.stayAwake(true);
                        }
                      } while (MediaPlayer.this.mOnSeekCompleteListener == null);
                      MediaPlayer.this.mOnSeekCompleteListener.onSeekComplete(this.mMediaPlayer);
                      return;
                    } while (MediaPlayer.this.mOnVideoSizeChangedListener == null);
                    MediaPlayer.this.mOnVideoSizeChangedListener.onVideoSizeChanged(this.mMediaPlayer, paramMessage.arg1, paramMessage.arg2);
                    return;
                    Log.e("Error (%d, %d)", new Object[] { Integer.valueOf(paramMessage.arg1), Integer.valueOf(paramMessage.arg2) });
                    boolean bool = false;
                    if (MediaPlayer.this.mOnErrorListener != null) {
                      bool = MediaPlayer.this.mOnErrorListener.onError(this.mMediaPlayer, paramMessage.arg1, paramMessage.arg2);
                    }
                    if ((MediaPlayer.this.mOnCompletionListener != null) && (!bool)) {
                      MediaPlayer.this.mOnCompletionListener.onCompletion(this.mMediaPlayer);
                    }
                    MediaPlayer.this.stayAwake(false);
                    return;
                    Log.i("Info (%d, %d)", new Object[] { Integer.valueOf(paramMessage.arg1), Integer.valueOf(paramMessage.arg2) });
                  } while (MediaPlayer.this.mOnInfoListener == null);
                  MediaPlayer.this.mOnInfoListener.onInfo(this.mMediaPlayer, paramMessage.arg1, paramMessage.arg2);
                  return;
                  this.mData = paramMessage.getData();
                  if (this.mData.getInt("sub_type") != 0) {
                    break;
                  }
                  Log.i("Subtitle : %s", new Object[] { this.mData.getString("sub_string") });
                } while (MediaPlayer.this.mOnTimedTextListener == null);
                MediaPlayer.this.mOnTimedTextListener.onTimedText(this.mData.getString("sub_string"));
                return;
              } while (this.mData.getInt("sub_type") != 1);
              Log.i("Subtitle : bitmap", new Object[0]);
            } while (MediaPlayer.this.mOnTimedTextListener == null);
            MediaPlayer.this.mOnTimedTextListener.onTimedTextUpdate(this.mData.getByteArray("sub_bytes"), paramMessage.arg1, paramMessage.arg2);
            return;
          } while (MediaPlayer.this.mOnCachingUpdateListener == null);
          i = paramMessage.getData().getInt("caching_type");
          if (i == 1)
          {
            MediaPlayer.this.mOnCachingUpdateListener.onCachingNotAvailable(this.mMediaPlayer, paramMessage.getData().getInt("caching_info"));
            return;
          }
          if (i == 3)
          {
            MediaPlayer.this.mOnCachingUpdateListener.onCachingUpdate(this.mMediaPlayer, paramMessage.getData().getLongArray("caching_segment"));
            return;
          }
          if (i == 4)
          {
            MediaPlayer.this.mOnCachingUpdateListener.onCachingSpeed(this.mMediaPlayer, paramMessage.getData().getInt("caching_info"));
            return;
          }
          if (i == 2)
          {
            MediaPlayer.this.mOnCachingUpdateListener.onCachingStart(this.mMediaPlayer);
            return;
          }
        } while (i != 5);
        MediaPlayer.this.mOnCachingUpdateListener.onCachingComplete(this.mMediaPlayer);
        return;
      } while (MediaPlayer.this.mOnHWRenderFailedListener == null);
      MediaPlayer.this.mOnHWRenderFailedListener.onFailed();
    }
    
    public void release()
    {
      this.mMediaPlayer = null;
    }
  }
  
  public static abstract interface OnBufferingUpdateListener
  {
    public abstract void onBufferingUpdate(MediaPlayer paramMediaPlayer, int paramInt);
  }
  
  public static abstract interface OnCachingUpdateListener
  {
    public abstract void onCachingComplete(MediaPlayer paramMediaPlayer);
    
    public abstract void onCachingNotAvailable(MediaPlayer paramMediaPlayer, int paramInt);
    
    public abstract void onCachingSpeed(MediaPlayer paramMediaPlayer, int paramInt);
    
    public abstract void onCachingStart(MediaPlayer paramMediaPlayer);
    
    public abstract void onCachingUpdate(MediaPlayer paramMediaPlayer, long[] paramArrayOfLong);
  }
  
  public static abstract interface OnCompletionListener
  {
    public abstract void onCompletion(MediaPlayer paramMediaPlayer);
  }
  
  public static abstract interface OnErrorListener
  {
    public abstract boolean onError(MediaPlayer paramMediaPlayer, int paramInt1, int paramInt2);
  }
  
  public static abstract interface OnHWRenderFailedListener
  {
    public abstract void onFailed();
  }
  
  public static abstract interface OnInfoListener
  {
    public abstract boolean onInfo(MediaPlayer paramMediaPlayer, int paramInt1, int paramInt2);
  }
  
  public static abstract interface OnPreparedListener
  {
    public abstract void onPrepared(MediaPlayer paramMediaPlayer);
  }
  
  public static abstract interface OnSeekCompleteListener
  {
    public abstract void onSeekComplete(MediaPlayer paramMediaPlayer);
  }
  
  public static abstract interface OnTimedTextListener
  {
    public abstract void onTimedText(String paramString);
    
    public abstract void onTimedTextUpdate(byte[] paramArrayOfByte, int paramInt1, int paramInt2);
  }
  
  public static abstract interface OnVideoSizeChangedListener
  {
    public abstract void onVideoSizeChanged(MediaPlayer paramMediaPlayer, int paramInt1, int paramInt2);
  }
  
  public static class TrackInfo
  {
    public static final int MEDIA_TRACK_TYPE_AUDIO = 2;
    public static final int MEDIA_TRACK_TYPE_SUBTITLE = 4;
    public static final int MEDIA_TRACK_TYPE_TIMEDTEXT = 3;
    public static final int MEDIA_TRACK_TYPE_UNKNOWN = 0;
    public static final int MEDIA_TRACK_TYPE_VIDEO = 1;
    final SparseArray<MediaFormat> mTrackInfoArray;
    final int mTrackType;
    
    TrackInfo(int paramInt, SparseArray<MediaFormat> paramSparseArray)
    {
      this.mTrackType = paramInt;
      this.mTrackInfoArray = paramSparseArray;
    }
    
    public SparseArray<MediaFormat> getTrackInfoArray()
    {
      return this.mTrackInfoArray;
    }
    
    public int getTrackType()
    {
      return this.mTrackType;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\io\vov\vitamio\MediaPlayer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */