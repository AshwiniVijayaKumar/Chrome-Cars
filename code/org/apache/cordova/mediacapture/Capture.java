package org.apache.cordova.mediacapture;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Environment;
import android.provider.MediaStore.Images.Media;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.concurrent.ExecutorService;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaResourceApi;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginManager;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.apache.cordova.file.FileUtils;
import org.apache.cordova.file.LocalFilesystemURL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Capture
  extends CordovaPlugin
{
  private static final String AUDIO_3GPP = "audio/3gpp";
  private static final int CAPTURE_AUDIO = 0;
  private static final int CAPTURE_IMAGE = 1;
  private static final int CAPTURE_INTERNAL_ERR = 0;
  private static final int CAPTURE_NO_MEDIA_FILES = 3;
  private static final int CAPTURE_VIDEO = 2;
  private static final String IMAGE_JPEG = "image/jpeg";
  private static final String LOG_TAG = "Capture";
  private static final String VIDEO_3GPP = "video/3gpp";
  private static final String VIDEO_MP4 = "video/mp4";
  private CallbackContext callbackContext;
  private int duration;
  private long limit;
  private int numPics;
  private int quality;
  private JSONArray results;
  
  private void captureAudio()
  {
    Intent localIntent = new Intent("android.provider.MediaStore.RECORD_SOUND");
    this.cordova.startActivityForResult(this, localIntent, 0);
  }
  
  private void captureImage()
  {
    this.numPics = queryImgDB(whichContentStore()).getCount();
    Intent localIntent = new Intent("android.media.action.IMAGE_CAPTURE");
    File localFile = new File(getTempDirectoryPath(), "Capture.jpg");
    try
    {
      createWritableFile(localFile);
      localIntent.putExtra("output", Uri.fromFile(localFile));
      com.example.example75f1799f07eb.MyPhoneGapActivity.activityResultCallback = this;
      this.cordova.startActivityForResult(this, localIntent, 1);
      return;
    }
    catch (IOException localIOException)
    {
      fail(createErrorObject(0, localIOException.toString()));
    }
  }
  
  private void captureVideo(int paramInt1, int paramInt2)
  {
    Intent localIntent = new Intent("android.media.action.VIDEO_CAPTURE");
    if (Build.VERSION.SDK_INT > 7)
    {
      localIntent.putExtra("android.intent.extra.durationLimit", paramInt1);
      localIntent.putExtra("android.intent.extra.videoQuality", paramInt2);
    }
    this.cordova.startActivityForResult(this, localIntent, 2);
  }
  
  private void checkForDuplicateImage()
  {
    Uri localUri = whichContentStore();
    Cursor localCursor = queryImgDB(localUri);
    if (localCursor.getCount() - this.numPics == 2)
    {
      localCursor.moveToLast();
      int i = Integer.valueOf(localCursor.getString(localCursor.getColumnIndex("_id"))).intValue();
      localUri = Uri.parse(localUri + "/" + (i - 1));
      this.cordova.getActivity().getContentResolver().delete(localUri, null, null);
    }
  }
  
  private JSONObject createErrorObject(int paramInt, String paramString)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("code", paramInt);
      localJSONObject.put("message", paramString);
      return localJSONObject;
    }
    catch (JSONException paramString) {}
    return localJSONObject;
  }
  
  private JSONObject createMediaFile(Uri paramUri)
  {
    File localFile = this.webView.getResourceApi().mapUriToFile(paramUri);
    JSONObject localJSONObject = new JSONObject();
    Class localClass = this.webView.getClass();
    Object localObject1 = null;
    try
    {
      localObject2 = (PluginManager)localClass.getMethod("getPluginManager", new Class[0]).invoke(this.webView, new Object[0]);
      localObject1 = localObject2;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      Object localObject2;
      for (;;) {}
    }
    catch (IllegalAccessException localIllegalAccessException2)
    {
      for (;;) {}
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      for (;;) {}
    }
    localObject2 = localObject1;
    if (localObject1 == null) {}
    try
    {
      localObject2 = (PluginManager)localClass.getField("pluginManager").get(this.webView);
      localObject1 = ((FileUtils)((PluginManager)localObject2).getPlugin("File")).filesystemURLforLocalPath(localFile.getAbsolutePath());
      for (;;)
      {
        try
        {
          localJSONObject.put("name", localFile.getName());
          localJSONObject.put("fullPath", localFile.toURI().toString());
          if (localObject1 != null) {
            localJSONObject.put("localURL", ((LocalFilesystemURL)localObject1).toString());
          }
          if ((localFile.getAbsoluteFile().toString().endsWith(".3gp")) || (localFile.getAbsoluteFile().toString().endsWith(".3gpp")))
          {
            if (paramUri.toString().contains("/audio/"))
            {
              localJSONObject.put("type", "audio/3gpp");
              localJSONObject.put("lastModifiedDate", localFile.lastModified());
              localJSONObject.put("size", localFile.length());
              return localJSONObject;
            }
            localJSONObject.put("type", "video/3gpp");
            continue;
          }
          localJSONObject.put("type", FileHelper.getMimeType(Uri.fromFile(localFile), this.cordova));
        }
        catch (JSONException paramUri)
        {
          paramUri.printStackTrace();
          return localJSONObject;
        }
      }
    }
    catch (IllegalAccessException localIllegalAccessException1)
    {
      for (;;)
      {
        Object localObject3 = localObject1;
      }
    }
    catch (NoSuchFieldException localNoSuchFieldException)
    {
      for (;;)
      {
        Object localObject4 = localObject1;
      }
    }
  }
  
  private static void createWritableFile(File paramFile)
    throws IOException
  {
    paramFile.createNewFile();
    paramFile.setWritable(true, false);
  }
  
  private JSONObject getAudioVideoData(String paramString, JSONObject paramJSONObject, boolean paramBoolean)
    throws JSONException
  {
    MediaPlayer localMediaPlayer = new MediaPlayer();
    try
    {
      localMediaPlayer.setDataSource(paramString);
      localMediaPlayer.prepare();
      paramJSONObject.put("duration", localMediaPlayer.getDuration() / 1000);
      if (paramBoolean)
      {
        paramJSONObject.put("height", localMediaPlayer.getVideoHeight());
        paramJSONObject.put("width", localMediaPlayer.getVideoWidth());
      }
      return paramJSONObject;
    }
    catch (IOException paramString)
    {
      Log.d("Capture", "Error: loading video file");
    }
    return paramJSONObject;
  }
  
  private JSONObject getFormatData(String paramString1, String paramString2)
    throws JSONException
  {
    Uri localUri;
    JSONObject localJSONObject;
    String str;
    if (paramString1.startsWith("file:"))
    {
      localUri = Uri.parse(paramString1);
      localJSONObject = new JSONObject();
      localJSONObject.put("height", 0);
      localJSONObject.put("width", 0);
      localJSONObject.put("bitrate", 0);
      localJSONObject.put("duration", 0);
      localJSONObject.put("codecs", "");
      if ((paramString2 != null) && (!paramString2.equals("")))
      {
        str = paramString2;
        if (!"null".equals(paramString2)) {}
      }
      else
      {
        str = FileHelper.getMimeType(localUri, this.cordova);
      }
      Log.d("Capture", "Mime type = " + str);
      if ((!str.equals("image/jpeg")) && (!paramString1.endsWith(".jpg"))) {
        break label185;
      }
      paramString2 = getImageData(localUri, localJSONObject);
    }
    label185:
    do
    {
      return paramString2;
      localUri = Uri.fromFile(new File(paramString1));
      break;
      if (str.endsWith("audio/3gpp")) {
        return getAudioVideoData(paramString1, localJSONObject, false);
      }
      if (str.equals("video/3gpp")) {
        break label227;
      }
      paramString2 = localJSONObject;
    } while (!str.equals("video/mp4"));
    label227:
    return getAudioVideoData(paramString1, localJSONObject, true);
  }
  
  private JSONObject getImageData(Uri paramUri, JSONObject paramJSONObject)
    throws JSONException
  {
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    localOptions.inJustDecodeBounds = true;
    BitmapFactory.decodeFile(paramUri.getPath(), localOptions);
    paramJSONObject.put("height", localOptions.outHeight);
    paramJSONObject.put("width", localOptions.outWidth);
    return paramJSONObject;
  }
  
  private String getTempDirectoryPath()
  {
    File localFile = this.cordova.getActivity().getCacheDir();
    localFile.mkdirs();
    return localFile.getAbsolutePath();
  }
  
  private Cursor queryImgDB(Uri paramUri)
  {
    return this.cordova.getActivity().getContentResolver().query(paramUri, new String[] { "_id" }, null, null, null);
  }
  
  private Uri whichContentStore()
  {
    if (Environment.getExternalStorageState().equals("mounted")) {
      return MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
    }
    return MediaStore.Images.Media.INTERNAL_CONTENT_URI;
  }
  
  public boolean execute(String paramString, JSONArray paramJSONArray, CallbackContext paramCallbackContext)
    throws JSONException
  {
    this.callbackContext = paramCallbackContext;
    this.limit = 1L;
    this.duration = 0;
    this.results = new JSONArray();
    this.quality = 1;
    JSONObject localJSONObject = paramJSONArray.optJSONObject(0);
    if (localJSONObject != null)
    {
      this.limit = localJSONObject.optLong("limit", 1L);
      this.duration = localJSONObject.optInt("duration", 0);
      this.quality = localJSONObject.optInt("quality", 1);
    }
    if (paramString.equals("getFormatData"))
    {
      paramCallbackContext.success(getFormatData(paramJSONArray.getString(0), paramJSONArray.getString(1)));
      return true;
    }
    if (paramString.equals("captureAudio"))
    {
      captureAudio();
      return true;
    }
    if (paramString.equals("captureImage"))
    {
      captureImage();
      return true;
    }
    if (paramString.equals("captureVideo"))
    {
      captureVideo(this.duration, this.quality);
      return true;
    }
    return false;
  }
  
  public void fail(JSONObject paramJSONObject)
  {
    this.callbackContext.error(paramJSONObject);
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, final Intent paramIntent)
  {
    if (paramInt2 == -1)
    {
      if (paramInt1 == 0)
      {
        paramIntent = new Runnable()
        {
          public void run()
          {
            Uri localUri = paramIntent.getData();
            Capture.this.results.put(Capture.this.createMediaFile(localUri));
            if (Capture.this.results.length() >= Capture.this.limit)
            {
              jdField_this.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, Capture.this.results));
              return;
            }
            Capture.this.captureAudio();
          }
        };
        this.cordova.getThreadPool().execute(paramIntent);
      }
      do
      {
        return;
        if (paramInt1 == 1)
        {
          paramIntent = new Runnable()
          {
            /* Error */
            public void run()
            {
              // Byte code:
              //   0: new 31	android/content/ContentValues
              //   3: dup
              //   4: invokespecial 32	android/content/ContentValues:<init>	()V
              //   7: astore_3
              //   8: aload_3
              //   9: ldc 34
              //   11: ldc 36
              //   13: invokevirtual 40	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
              //   16: aload_0
              //   17: getfield 20	org/apache/cordova/mediacapture/Capture$2:val$that	Lorg/apache/cordova/mediacapture/Capture;
              //   20: getfield 44	org/apache/cordova/mediacapture/Capture:cordova	Lorg/apache/cordova/CordovaInterface;
              //   23: invokeinterface 50 1 0
              //   28: invokevirtual 56	android/app/Activity:getContentResolver	()Landroid/content/ContentResolver;
              //   31: getstatic 62	android/provider/MediaStore$Images$Media:EXTERNAL_CONTENT_URI	Landroid/net/Uri;
              //   34: aload_3
              //   35: invokevirtual 68	android/content/ContentResolver:insert	(Landroid/net/Uri;Landroid/content/ContentValues;)Landroid/net/Uri;
              //   38: astore_2
              //   39: new 70	java/io/FileInputStream
              //   42: dup
              //   43: new 72	java/lang/StringBuilder
              //   46: dup
              //   47: invokespecial 73	java/lang/StringBuilder:<init>	()V
              //   50: aload_0
              //   51: getfield 18	org/apache/cordova/mediacapture/Capture$2:this$0	Lorg/apache/cordova/mediacapture/Capture;
              //   54: invokestatic 77	org/apache/cordova/mediacapture/Capture:access$600	(Lorg/apache/cordova/mediacapture/Capture;)Ljava/lang/String;
              //   57: invokevirtual 81	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
              //   60: ldc 83
              //   62: invokevirtual 81	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
              //   65: invokevirtual 87	java/lang/StringBuilder:toString	()Ljava/lang/String;
              //   68: invokespecial 90	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
              //   71: astore_3
              //   72: aload_0
              //   73: getfield 20	org/apache/cordova/mediacapture/Capture$2:val$that	Lorg/apache/cordova/mediacapture/Capture;
              //   76: getfield 44	org/apache/cordova/mediacapture/Capture:cordova	Lorg/apache/cordova/CordovaInterface;
              //   79: invokeinterface 50 1 0
              //   84: invokevirtual 56	android/app/Activity:getContentResolver	()Landroid/content/ContentResolver;
              //   87: aload_2
              //   88: invokevirtual 94	android/content/ContentResolver:openOutputStream	(Landroid/net/Uri;)Ljava/io/OutputStream;
              //   91: astore 4
              //   93: sipush 4096
              //   96: newarray <illegal type>
              //   98: astore 5
              //   100: aload_3
              //   101: aload 5
              //   103: invokevirtual 98	java/io/FileInputStream:read	([B)I
              //   106: istore_1
              //   107: iload_1
              //   108: iconst_m1
              //   109: if_icmpeq +98 -> 207
              //   112: aload 4
              //   114: aload 5
              //   116: iconst_0
              //   117: iload_1
              //   118: invokevirtual 104	java/io/OutputStream:write	([BII)V
              //   121: goto -21 -> 100
              //   124: astore_2
              //   125: aload_2
              //   126: invokevirtual 107	java/io/IOException:printStackTrace	()V
              //   129: aload_0
              //   130: getfield 20	org/apache/cordova/mediacapture/Capture$2:val$that	Lorg/apache/cordova/mediacapture/Capture;
              //   133: aload_0
              //   134: getfield 18	org/apache/cordova/mediacapture/Capture$2:this$0	Lorg/apache/cordova/mediacapture/Capture;
              //   137: iconst_0
              //   138: ldc 109
              //   140: invokestatic 113	org/apache/cordova/mediacapture/Capture:access$500	(Lorg/apache/cordova/mediacapture/Capture;ILjava/lang/String;)Lorg/json/JSONObject;
              //   143: invokevirtual 117	org/apache/cordova/mediacapture/Capture:fail	(Lorg/json/JSONObject;)V
              //   146: return
              //   147: astore_2
              //   148: ldc 119
              //   150: ldc 121
              //   152: invokestatic 126	org/apache/cordova/LOG:d	(Ljava/lang/String;Ljava/lang/String;)V
              //   155: aload_0
              //   156: getfield 20	org/apache/cordova/mediacapture/Capture$2:val$that	Lorg/apache/cordova/mediacapture/Capture;
              //   159: getfield 44	org/apache/cordova/mediacapture/Capture:cordova	Lorg/apache/cordova/CordovaInterface;
              //   162: invokeinterface 50 1 0
              //   167: invokevirtual 56	android/app/Activity:getContentResolver	()Landroid/content/ContentResolver;
              //   170: getstatic 129	android/provider/MediaStore$Images$Media:INTERNAL_CONTENT_URI	Landroid/net/Uri;
              //   173: aload_3
              //   174: invokevirtual 68	android/content/ContentResolver:insert	(Landroid/net/Uri;Landroid/content/ContentValues;)Landroid/net/Uri;
              //   177: astore_2
              //   178: goto -139 -> 39
              //   181: astore_2
              //   182: ldc 119
              //   184: ldc -125
              //   186: invokestatic 126	org/apache/cordova/LOG:d	(Ljava/lang/String;Ljava/lang/String;)V
              //   189: aload_0
              //   190: getfield 20	org/apache/cordova/mediacapture/Capture$2:val$that	Lorg/apache/cordova/mediacapture/Capture;
              //   193: aload_0
              //   194: getfield 18	org/apache/cordova/mediacapture/Capture$2:this$0	Lorg/apache/cordova/mediacapture/Capture;
              //   197: iconst_0
              //   198: ldc -123
              //   200: invokestatic 113	org/apache/cordova/mediacapture/Capture:access$500	(Lorg/apache/cordova/mediacapture/Capture;ILjava/lang/String;)Lorg/json/JSONObject;
              //   203: invokevirtual 117	org/apache/cordova/mediacapture/Capture:fail	(Lorg/json/JSONObject;)V
              //   206: return
              //   207: aload 4
              //   209: invokevirtual 136	java/io/OutputStream:flush	()V
              //   212: aload 4
              //   214: invokevirtual 139	java/io/OutputStream:close	()V
              //   217: aload_3
              //   218: invokevirtual 140	java/io/FileInputStream:close	()V
              //   221: aload_0
              //   222: getfield 18	org/apache/cordova/mediacapture/Capture$2:this$0	Lorg/apache/cordova/mediacapture/Capture;
              //   225: invokestatic 144	org/apache/cordova/mediacapture/Capture:access$100	(Lorg/apache/cordova/mediacapture/Capture;)Lorg/json/JSONArray;
              //   228: aload_0
              //   229: getfield 18	org/apache/cordova/mediacapture/Capture$2:this$0	Lorg/apache/cordova/mediacapture/Capture;
              //   232: aload_2
              //   233: invokestatic 148	org/apache/cordova/mediacapture/Capture:access$000	(Lorg/apache/cordova/mediacapture/Capture;Landroid/net/Uri;)Lorg/json/JSONObject;
              //   236: invokevirtual 153	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
              //   239: pop
              //   240: aload_0
              //   241: getfield 18	org/apache/cordova/mediacapture/Capture$2:this$0	Lorg/apache/cordova/mediacapture/Capture;
              //   244: invokestatic 157	org/apache/cordova/mediacapture/Capture:access$700	(Lorg/apache/cordova/mediacapture/Capture;)V
              //   247: aload_0
              //   248: getfield 18	org/apache/cordova/mediacapture/Capture$2:this$0	Lorg/apache/cordova/mediacapture/Capture;
              //   251: invokestatic 144	org/apache/cordova/mediacapture/Capture:access$100	(Lorg/apache/cordova/mediacapture/Capture;)Lorg/json/JSONArray;
              //   254: invokevirtual 161	org/json/JSONArray:length	()I
              //   257: i2l
              //   258: aload_0
              //   259: getfield 18	org/apache/cordova/mediacapture/Capture$2:this$0	Lorg/apache/cordova/mediacapture/Capture;
              //   262: invokestatic 165	org/apache/cordova/mediacapture/Capture:access$200	(Lorg/apache/cordova/mediacapture/Capture;)J
              //   265: lcmp
              //   266: iflt +31 -> 297
              //   269: aload_0
              //   270: getfield 20	org/apache/cordova/mediacapture/Capture$2:val$that	Lorg/apache/cordova/mediacapture/Capture;
              //   273: invokestatic 169	org/apache/cordova/mediacapture/Capture:access$300	(Lorg/apache/cordova/mediacapture/Capture;)Lorg/apache/cordova/CallbackContext;
              //   276: new 171	org/apache/cordova/PluginResult
              //   279: dup
              //   280: getstatic 177	org/apache/cordova/PluginResult$Status:OK	Lorg/apache/cordova/PluginResult$Status;
              //   283: aload_0
              //   284: getfield 18	org/apache/cordova/mediacapture/Capture$2:this$0	Lorg/apache/cordova/mediacapture/Capture;
              //   287: invokestatic 144	org/apache/cordova/mediacapture/Capture:access$100	(Lorg/apache/cordova/mediacapture/Capture;)Lorg/json/JSONArray;
              //   290: invokespecial 180	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;Lorg/json/JSONArray;)V
              //   293: invokevirtual 186	org/apache/cordova/CallbackContext:sendPluginResult	(Lorg/apache/cordova/PluginResult;)V
              //   296: return
              //   297: aload_0
              //   298: getfield 18	org/apache/cordova/mediacapture/Capture$2:this$0	Lorg/apache/cordova/mediacapture/Capture;
              //   301: invokestatic 189	org/apache/cordova/mediacapture/Capture:access$800	(Lorg/apache/cordova/mediacapture/Capture;)V
              //   304: return
              // Local variable table:
              //   start	length	slot	name	signature
              //   0	305	0	this	2
              //   106	12	1	i	int
              //   38	50	2	localUri1	Uri
              //   124	2	2	localIOException	IOException
              //   147	1	2	localUnsupportedOperationException1	UnsupportedOperationException
              //   177	1	2	localUri2	Uri
              //   181	52	2	localUnsupportedOperationException2	UnsupportedOperationException
              //   7	211	3	localObject	Object
              //   91	122	4	localOutputStream	java.io.OutputStream
              //   98	17	5	arrayOfByte	byte[]
              // Exception table:
              //   from	to	target	type
              //   0	16	124	java/io/IOException
              //   16	39	124	java/io/IOException
              //   39	100	124	java/io/IOException
              //   100	107	124	java/io/IOException
              //   112	121	124	java/io/IOException
              //   148	155	124	java/io/IOException
              //   155	178	124	java/io/IOException
              //   182	206	124	java/io/IOException
              //   207	296	124	java/io/IOException
              //   297	304	124	java/io/IOException
              //   16	39	147	java/lang/UnsupportedOperationException
              //   155	178	181	java/lang/UnsupportedOperationException
            }
          };
          this.cordova.getThreadPool().execute(paramIntent);
          return;
        }
      } while (paramInt1 != 2);
      paramIntent = new Runnable()
      {
        public void run()
        {
          Uri localUri1 = null;
          if (paramIntent != null) {
            localUri1 = paramIntent.getData();
          }
          Uri localUri2 = localUri1;
          if (localUri1 == null) {
            localUri2 = Uri.fromFile(new File(Capture.this.getTempDirectoryPath(), "Capture.avi"));
          }
          if (localUri2 == null)
          {
            jdField_this.fail(Capture.this.createErrorObject(3, "Error: data is null"));
            return;
          }
          Capture.this.results.put(Capture.this.createMediaFile(localUri2));
          if (Capture.this.results.length() >= Capture.this.limit)
          {
            jdField_this.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, Capture.this.results));
            return;
          }
          Capture.this.captureVideo(Capture.this.duration, Capture.this.quality);
        }
      };
      this.cordova.getThreadPool().execute(paramIntent);
      return;
    }
    if (paramInt2 == 0)
    {
      if (this.results.length() > 0)
      {
        this.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, this.results));
        return;
      }
      fail(createErrorObject(3, "Canceled."));
      return;
    }
    if (this.results.length() > 0)
    {
      this.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, this.results));
      return;
    }
    fail(createErrorObject(3, "Did not complete!"));
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\cordova\mediacapture\Capture.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */