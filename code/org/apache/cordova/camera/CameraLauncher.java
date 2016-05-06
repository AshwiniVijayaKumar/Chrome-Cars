package org.apache.cordova.camera;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Matrix;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.MediaScannerConnectionClient;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore.Images.Media;
import android.util.Base64;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.LOG;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;

public class CameraLauncher
  extends CordovaPlugin
  implements MediaScannerConnection.MediaScannerConnectionClient
{
  private static final int ALLMEDIA = 2;
  private static final int CAMERA = 1;
  private static final int CROP_CAMERA = 100;
  private static final int DATA_URL = 0;
  private static final int FILE_URI = 1;
  private static final String GET_All = "Get All";
  private static final String GET_PICTURE = "Get Picture";
  private static final String GET_VIDEO = "Get Video";
  private static final int JPEG = 0;
  private static final String LOG_TAG = "CameraLauncher";
  private static final int NATIVE_URI = 2;
  public static final int PERMISSION_DENIED_ERROR = 20;
  private static final int PHOTOLIBRARY = 0;
  private static final int PICTURE = 0;
  private static final int PNG = 1;
  private static final int SAVEDPHOTOALBUM = 2;
  public static final int SAVE_TO_ALBUM_SEC = 1;
  public static final int TAKE_PIC_SEC = 0;
  private static final int VIDEO = 1;
  protected static final String[] permissions = { "android.permission.READ_EXTERNAL_STORAGE" };
  private boolean allowEdit;
  public CallbackContext callbackContext;
  private MediaScannerConnection conn;
  private boolean correctOrientation;
  private Uri croppedUri;
  private int destType;
  private int encodingType;
  private Uri imageUri;
  private int mQuality;
  private int mediaType;
  private int numPics;
  private boolean orientationCorrected;
  private boolean saveToPhotoAlbum;
  private Uri scanMe;
  private int srcType;
  private int targetHeight;
  private int targetWidth;
  
  public static int calculateSampleSize(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (paramInt1 / paramInt2 > paramInt3 / paramInt4) {
      return paramInt1 / paramInt3;
    }
    return paramInt2 / paramInt4;
  }
  
  private void checkForDuplicateImage(int paramInt)
  {
    int j = 1;
    Uri localUri = whichContentStore();
    Cursor localCursor = queryImgDB(localUri);
    int k = localCursor.getCount();
    int i = j;
    if (paramInt == 1)
    {
      i = j;
      if (this.saveToPhotoAlbum) {
        i = 2;
      }
    }
    if (k - this.numPics == i)
    {
      localCursor.moveToLast();
      j = Integer.valueOf(localCursor.getString(localCursor.getColumnIndex("_id"))).intValue();
      paramInt = j;
      if (i == 2) {
        paramInt = j - 1;
      }
      localUri = Uri.parse(localUri + "/" + paramInt);
      this.cordova.getActivity().getContentResolver().delete(localUri, null, null);
      localCursor.close();
    }
  }
  
  private void cleanup(int paramInt, Uri paramUri1, Uri paramUri2, Bitmap paramBitmap)
  {
    if (paramBitmap != null) {
      paramBitmap.recycle();
    }
    new File(FileHelper.stripFileProtocol(paramUri1.toString())).delete();
    checkForDuplicateImage(paramInt);
    if ((this.saveToPhotoAlbum) && (paramUri2 != null)) {
      scanForGallery(paramUri2);
    }
    System.gc();
  }
  
  private File createCaptureFile(int paramInt)
  {
    return createCaptureFile(paramInt, "");
  }
  
  private File createCaptureFile(int paramInt, String paramString)
  {
    String str = paramString;
    if (paramString.isEmpty()) {
      str = ".Pic";
    }
    if (paramInt == 0) {}
    for (paramString = str + ".jpg";; paramString = str + ".png")
    {
      return new File(getTempDirectoryPath(), paramString);
      if (paramInt != 1) {
        break;
      }
    }
    throw new IllegalArgumentException("Invalid Encoding Type: " + paramInt);
  }
  
  private boolean fileWillBeModified()
  {
    return ((this.targetWidth > 0) && (this.targetHeight > 0)) || (this.correctOrientation) || (this.allowEdit);
  }
  
  private int getImageOrientation(Uri paramUri)
  {
    int k = 0;
    int j = 0;
    int i = k;
    try
    {
      paramUri = this.cordova.getActivity().getContentResolver().query(paramUri, new String[] { "orientation" }, null, null, null);
      if (paramUri != null)
      {
        i = k;
        paramUri.moveToPosition(0);
        i = k;
        j = paramUri.getInt(0);
        i = j;
        paramUri.close();
      }
      return j;
    }
    catch (Exception paramUri) {}
    return i;
  }
  
  private String getPicutresPath()
  {
    String str = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
    Object localObject = new StringBuilder().append("IMG_").append(str);
    if (this.encodingType == 0) {}
    for (str = ".jpg";; str = ".png")
    {
      str = str;
      localObject = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
      return ((File)localObject).getAbsolutePath() + "/" + str;
    }
  }
  
  private Bitmap getRotatedBitmap(int paramInt, Bitmap paramBitmap, ExifHelper paramExifHelper)
  {
    Matrix localMatrix = new Matrix();
    if (paramInt == 180) {
      localMatrix.setRotate(paramInt);
    }
    Bitmap localBitmap;
    for (;;)
    {
      localBitmap = paramBitmap;
      try
      {
        paramBitmap = Bitmap.createBitmap(paramBitmap, 0, 0, paramBitmap.getWidth(), paramBitmap.getHeight(), localMatrix, true);
        localBitmap = paramBitmap;
        paramExifHelper.resetOrientation();
        return paramBitmap;
      }
      catch (OutOfMemoryError paramBitmap) {}
      localMatrix.setRotate(paramInt, paramBitmap.getWidth() / 2.0F, paramBitmap.getHeight() / 2.0F);
    }
    return localBitmap;
  }
  
  /* Error */
  private Bitmap getScaledBitmap(String paramString)
    throws IOException
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aload_0
    //   4: getfield 240	org/apache/cordova/camera/CameraLauncher:targetWidth	I
    //   7: ifgt +76 -> 83
    //   10: aload_0
    //   11: getfield 242	org/apache/cordova/camera/CameraLauncher:targetHeight	I
    //   14: ifgt +69 -> 83
    //   17: aconst_null
    //   18: astore_2
    //   19: aload_1
    //   20: aload_0
    //   21: getfield 157	org/apache/cordova/camera/CameraLauncher:cordova	Lorg/apache/cordova/CordovaInterface;
    //   24: invokestatic 330	org/apache/cordova/camera/FileHelper:getInputStreamFromUriString	(Ljava/lang/String;Lorg/apache/cordova/CordovaInterface;)Ljava/io/InputStream;
    //   27: astore_3
    //   28: aload_3
    //   29: astore_2
    //   30: aload_3
    //   31: invokestatic 336	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;)Landroid/graphics/Bitmap;
    //   34: astore_1
    //   35: aload_1
    //   36: astore_2
    //   37: aload_3
    //   38: ifnull +9 -> 47
    //   41: aload_3
    //   42: invokevirtual 339	java/io/InputStream:close	()V
    //   45: aload_1
    //   46: astore_2
    //   47: aload_2
    //   48: areturn
    //   49: astore_2
    //   50: ldc 32
    //   52: ldc_w 341
    //   55: invokestatic 346	org/apache/cordova/LOG:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   58: aload_1
    //   59: areturn
    //   60: astore_1
    //   61: aload_2
    //   62: ifnull +7 -> 69
    //   65: aload_2
    //   66: invokevirtual 339	java/io/InputStream:close	()V
    //   69: aload_1
    //   70: athrow
    //   71: astore_2
    //   72: ldc 32
    //   74: ldc_w 341
    //   77: invokestatic 346	org/apache/cordova/LOG:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   80: goto -11 -> 69
    //   83: new 348	android/graphics/BitmapFactory$Options
    //   86: dup
    //   87: invokespecial 349	android/graphics/BitmapFactory$Options:<init>	()V
    //   90: astore 5
    //   92: aload 5
    //   94: iconst_1
    //   95: putfield 352	android/graphics/BitmapFactory$Options:inJustDecodeBounds	Z
    //   98: aconst_null
    //   99: astore_2
    //   100: aload_1
    //   101: aload_0
    //   102: getfield 157	org/apache/cordova/camera/CameraLauncher:cordova	Lorg/apache/cordova/CordovaInterface;
    //   105: invokestatic 330	org/apache/cordova/camera/FileHelper:getInputStreamFromUriString	(Ljava/lang/String;Lorg/apache/cordova/CordovaInterface;)Ljava/io/InputStream;
    //   108: astore_3
    //   109: aload_3
    //   110: astore_2
    //   111: aload_3
    //   112: aconst_null
    //   113: aload 5
    //   115: invokestatic 355	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;Landroid/graphics/Rect;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   118: pop
    //   119: aload_3
    //   120: ifnull +7 -> 127
    //   123: aload_3
    //   124: invokevirtual 339	java/io/InputStream:close	()V
    //   127: aload 4
    //   129: astore_2
    //   130: aload 5
    //   132: getfield 358	android/graphics/BitmapFactory$Options:outWidth	I
    //   135: ifeq -88 -> 47
    //   138: aload 4
    //   140: astore_2
    //   141: aload 5
    //   143: getfield 361	android/graphics/BitmapFactory$Options:outHeight	I
    //   146: ifeq -99 -> 47
    //   149: aload_0
    //   150: aload 5
    //   152: getfield 358	android/graphics/BitmapFactory$Options:outWidth	I
    //   155: aload 5
    //   157: getfield 361	android/graphics/BitmapFactory$Options:outHeight	I
    //   160: invokevirtual 365	org/apache/cordova/camera/CameraLauncher:calculateAspectRatio	(II)[I
    //   163: astore 6
    //   165: aload 5
    //   167: iconst_0
    //   168: putfield 352	android/graphics/BitmapFactory$Options:inJustDecodeBounds	Z
    //   171: aload 5
    //   173: aload 5
    //   175: getfield 358	android/graphics/BitmapFactory$Options:outWidth	I
    //   178: aload 5
    //   180: getfield 361	android/graphics/BitmapFactory$Options:outHeight	I
    //   183: aload_0
    //   184: getfield 240	org/apache/cordova/camera/CameraLauncher:targetWidth	I
    //   187: aload_0
    //   188: getfield 242	org/apache/cordova/camera/CameraLauncher:targetHeight	I
    //   191: invokestatic 367	org/apache/cordova/camera/CameraLauncher:calculateSampleSize	(IIII)I
    //   194: putfield 370	android/graphics/BitmapFactory$Options:inSampleSize	I
    //   197: aload_1
    //   198: aload_0
    //   199: getfield 157	org/apache/cordova/camera/CameraLauncher:cordova	Lorg/apache/cordova/CordovaInterface;
    //   202: invokestatic 330	org/apache/cordova/camera/FileHelper:getInputStreamFromUriString	(Ljava/lang/String;Lorg/apache/cordova/CordovaInterface;)Ljava/io/InputStream;
    //   205: astore_1
    //   206: aload_1
    //   207: astore_3
    //   208: aload_1
    //   209: aconst_null
    //   210: aload 5
    //   212: invokestatic 355	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;Landroid/graphics/Rect;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   215: astore 5
    //   217: aload_1
    //   218: ifnull +7 -> 225
    //   221: aload_1
    //   222: invokevirtual 339	java/io/InputStream:close	()V
    //   225: aload 4
    //   227: astore_2
    //   228: aload 5
    //   230: ifnull -183 -> 47
    //   233: aload 5
    //   235: aload 6
    //   237: iconst_0
    //   238: iaload
    //   239: aload 6
    //   241: iconst_1
    //   242: iaload
    //   243: iconst_1
    //   244: invokestatic 374	android/graphics/Bitmap:createScaledBitmap	(Landroid/graphics/Bitmap;IIZ)Landroid/graphics/Bitmap;
    //   247: areturn
    //   248: astore_2
    //   249: ldc 32
    //   251: ldc_w 341
    //   254: invokestatic 346	org/apache/cordova/LOG:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   257: goto -130 -> 127
    //   260: astore_1
    //   261: aload_2
    //   262: ifnull +7 -> 269
    //   265: aload_2
    //   266: invokevirtual 339	java/io/InputStream:close	()V
    //   269: aload_1
    //   270: athrow
    //   271: astore_2
    //   272: ldc 32
    //   274: ldc_w 341
    //   277: invokestatic 346	org/apache/cordova/LOG:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   280: goto -11 -> 269
    //   283: astore_1
    //   284: ldc 32
    //   286: ldc_w 341
    //   289: invokestatic 346	org/apache/cordova/LOG:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   292: goto -67 -> 225
    //   295: astore_1
    //   296: aload_3
    //   297: ifnull +7 -> 304
    //   300: aload_3
    //   301: invokevirtual 339	java/io/InputStream:close	()V
    //   304: aload_1
    //   305: athrow
    //   306: astore_2
    //   307: ldc 32
    //   309: ldc_w 341
    //   312: invokestatic 346	org/apache/cordova/LOG:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   315: goto -11 -> 304
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	318	0	this	CameraLauncher
    //   0	318	1	paramString	String
    //   18	30	2	localObject1	Object
    //   49	17	2	localIOException1	IOException
    //   71	1	2	localIOException2	IOException
    //   99	129	2	localObject2	Object
    //   248	18	2	localIOException3	IOException
    //   271	1	2	localIOException4	IOException
    //   306	1	2	localIOException5	IOException
    //   27	274	3	localObject3	Object
    //   1	225	4	localObject4	Object
    //   90	144	5	localObject5	Object
    //   163	77	6	arrayOfInt	int[]
    // Exception table:
    //   from	to	target	type
    //   41	45	49	java/io/IOException
    //   19	28	60	finally
    //   30	35	60	finally
    //   65	69	71	java/io/IOException
    //   123	127	248	java/io/IOException
    //   100	109	260	finally
    //   111	119	260	finally
    //   265	269	271	java/io/IOException
    //   221	225	283	java/io/IOException
    //   197	206	295	finally
    //   208	217	295	finally
    //   300	304	306	java/io/IOException
  }
  
  private String getTempDirectoryPath()
  {
    if (Environment.getExternalStorageState().equals("mounted")) {}
    for (File localFile = this.cordova.getActivity().getExternalCacheDir();; localFile = this.cordova.getActivity().getCacheDir())
    {
      localFile.mkdirs();
      return localFile.getAbsolutePath();
    }
  }
  
  private Uri getUriFromMediaStore()
  {
    Object localObject = new ContentValues();
    ((ContentValues)localObject).put("mime_type", "image/jpeg");
    try
    {
      Uri localUri = this.cordova.getActivity().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, (ContentValues)localObject);
      return localUri;
    }
    catch (RuntimeException localRuntimeException2)
    {
      LOG.d("CameraLauncher", "Can't write to external media storage.");
      try
      {
        localObject = this.cordova.getActivity().getContentResolver().insert(MediaStore.Images.Media.INTERNAL_CONTENT_URI, (ContentValues)localObject);
        return (Uri)localObject;
      }
      catch (RuntimeException localRuntimeException1)
      {
        LOG.d("CameraLauncher", "Can't write to internal media storage.");
      }
    }
    return null;
  }
  
  private String ouputModifiedBitmap(Bitmap paramBitmap, Uri paramUri)
    throws IOException
  {
    String str = FileHelper.getRealPath(paramUri, this.cordova);
    Object localObject;
    FileOutputStream localFileOutputStream;
    if (str != null)
    {
      paramUri = str.substring(str.lastIndexOf('/') + 1);
      localObject = getTempDirectoryPath() + "/" + paramUri;
      localFileOutputStream = new FileOutputStream((String)localObject);
      if (this.encodingType != 0) {
        break label198;
      }
    }
    label198:
    for (paramUri = Bitmap.CompressFormat.JPEG;; paramUri = Bitmap.CompressFormat.PNG)
    {
      paramBitmap.compress(paramUri, this.mQuality, localFileOutputStream);
      localFileOutputStream.close();
      if ((str != null) && (this.encodingType == 0)) {
        paramBitmap = new ExifHelper();
      }
      try
      {
        paramBitmap.createInFile(str);
        paramBitmap.readExifData();
        if ((this.correctOrientation) && (this.orientationCorrected)) {
          paramBitmap.resetOrientation();
        }
        paramBitmap.createOutFile((String)localObject);
        paramBitmap.writeExifData();
        return (String)localObject;
      }
      catch (IOException paramBitmap)
      {
        paramBitmap.printStackTrace();
      }
      localObject = new StringBuilder().append("modified.");
      if (this.encodingType == 0) {}
      for (paramUri = "jpg";; paramUri = "png")
      {
        paramUri = paramUri;
        break;
      }
    }
    return (String)localObject;
  }
  
  private void performCrop(Uri paramUri, int paramInt, Intent paramIntent)
  {
    try
    {
      Intent localIntent = new Intent("com.android.camera.action.CROP");
      localIntent.setDataAndType(paramUri, "image/*");
      localIntent.putExtra("crop", "true");
      if (this.targetWidth > 0) {
        localIntent.putExtra("outputX", this.targetWidth);
      }
      if (this.targetHeight > 0) {
        localIntent.putExtra("outputY", this.targetHeight);
      }
      if ((this.targetHeight > 0) && (this.targetWidth > 0) && (this.targetWidth == this.targetHeight))
      {
        localIntent.putExtra("aspectX", 1);
        localIntent.putExtra("aspectY", 1);
      }
      this.croppedUri = Uri.fromFile(createCaptureFile(this.encodingType, System.currentTimeMillis() + ""));
      localIntent.putExtra("output", this.croppedUri);
      if (this.cordova != null) {
        this.cordova.startActivityForResult(this, localIntent, paramInt + 100);
      }
      return;
    }
    catch (ActivityNotFoundException paramUri)
    {
      Log.e("CameraLauncher", "Crop operation not supported on this device");
      try
      {
        processResultFromCamera(paramInt, paramIntent);
        return;
      }
      catch (IOException paramUri)
      {
        paramUri.printStackTrace();
        Log.e("CameraLauncher", "Unable to write to file");
      }
    }
  }
  
  private void processResultFromCamera(int paramInt, Intent paramIntent)
    throws IOException
  {
    int j = 0;
    ExifHelper localExifHelper = new ExifHelper();
    int i;
    if ((this.allowEdit) && (this.croppedUri != null))
    {
      localObject1 = FileHelper.stripFileProtocol(this.croppedUri.toString());
      i = j;
      if (this.encodingType != 0) {}
    }
    for (;;)
    {
      try
      {
        localExifHelper.createInFile((String)localObject1);
        localExifHelper.readExifData();
        i = localExifHelper.getOrientation();
        localObject2 = null;
        Uri localUri = null;
        if (this.saveToPhotoAlbum)
        {
          localUri = Uri.fromFile(new File(getPicutresPath()));
          if ((this.allowEdit) && (this.croppedUri != null))
          {
            writeUncompressedImage(this.croppedUri, localUri);
            refreshGallery(localUri);
          }
        }
        else
        {
          if (paramInt != 0) {
            break label292;
          }
          localObject2 = getScaledBitmap((String)localObject1);
          localObject1 = localObject2;
          if (localObject2 == null) {
            localObject1 = (Bitmap)paramIntent.getExtras().get("data");
          }
          if (localObject1 != null) {
            continue;
          }
          Log.d("CameraLauncher", "I either have a null image path or bitmap");
          failPicture("Unable to create bitmap!");
          return;
          localObject1 = FileHelper.stripFileProtocol(this.imageUri.toString());
        }
      }
      catch (IOException localIOException)
      {
        localIOException.printStackTrace();
        i = j;
        continue;
        writeUncompressedImage(this.imageUri, localIOException);
        continue;
        paramIntent = (Intent)localObject1;
        if (i != 0)
        {
          paramIntent = (Intent)localObject1;
          if (this.correctOrientation) {
            paramIntent = getRotatedBitmap(i, (Bitmap)localObject1, localExifHelper);
          }
        }
        processPicture(paramIntent, this.encodingType);
        localObject1 = paramIntent;
        if (!this.saveToPhotoAlbum)
        {
          checkForDuplicateImage(0);
          localObject1 = paramIntent;
        }
        cleanup(1, this.imageUri, localIOException, (Bitmap)localObject1);
        return;
      }
      label292:
      if ((paramInt != 1) && (paramInt != 2)) {
        break label634;
      }
      if ((this.targetHeight != -1) || (this.targetWidth != -1) || (this.mQuality != 100) || (this.correctOrientation)) {
        break label446;
      }
      if (!this.saveToPhotoAlbum) {
        break label360;
      }
      this.callbackContext.success(localIOException.toString());
      localObject1 = localObject2;
    }
    label360:
    paramIntent = Uri.fromFile(createCaptureFile(this.encodingType, System.currentTimeMillis() + ""));
    if ((this.allowEdit) && (this.croppedUri != null)) {
      writeUncompressedImage(this.croppedUri, paramIntent);
    }
    for (;;)
    {
      this.callbackContext.success(paramIntent.toString());
      localObject1 = localObject2;
      break;
      writeUncompressedImage(this.imageUri, paramIntent);
    }
    label446:
    Object localObject2 = Uri.fromFile(createCaptureFile(this.encodingType, System.currentTimeMillis() + ""));
    Object localObject1 = getScaledBitmap((String)localObject1);
    if (localObject1 == null)
    {
      Log.d("CameraLauncher", "I either have a null image path or bitmap");
      failPicture("Unable to create bitmap!");
      return;
    }
    paramIntent = (Intent)localObject1;
    if (i != 0)
    {
      paramIntent = (Intent)localObject1;
      if (this.correctOrientation) {
        paramIntent = getRotatedBitmap(i, (Bitmap)localObject1, localExifHelper);
      }
    }
    OutputStream localOutputStream = this.cordova.getActivity().getContentResolver().openOutputStream((Uri)localObject2);
    if (this.encodingType == 0) {}
    for (localObject1 = Bitmap.CompressFormat.JPEG;; localObject1 = Bitmap.CompressFormat.PNG)
    {
      paramIntent.compress((Bitmap.CompressFormat)localObject1, this.mQuality, localOutputStream);
      localOutputStream.close();
      if (this.encodingType == 0)
      {
        localExifHelper.createOutFile(((Uri)localObject2).getPath());
        localExifHelper.writeExifData();
      }
      this.callbackContext.success(((Uri)localObject2).toString());
      localObject1 = paramIntent;
      break;
    }
    label634:
    throw new IllegalStateException();
  }
  
  private void processResultFromGallery(int paramInt, Intent paramIntent)
  {
    paramIntent = paramIntent.getData();
    localObject2 = paramIntent;
    if (paramIntent == null)
    {
      if (this.croppedUri != null) {
        localObject2 = this.croppedUri;
      }
    }
    else
    {
      str = FileHelper.getRealPath((Uri)localObject2, this.cordova);
      Log.d("CameraLauncher", "File locaton is: " + str);
      if (this.mediaType == 0) {
        break label88;
      }
      this.callbackContext.success(str);
      return;
    }
    failPicture("null data from photo library");
    return;
    label88:
    if ((this.targetHeight == -1) && (this.targetWidth == -1) && ((paramInt == 1) || (paramInt == 2)) && (!this.correctOrientation))
    {
      this.callbackContext.success(((Uri)localObject2).toString());
      return;
    }
    Object localObject1 = ((Uri)localObject2).toString();
    paramIntent = FileHelper.getMimeType((String)localObject1, this.cordova);
    if ((!"image/jpeg".equalsIgnoreCase(paramIntent)) && (!"image/png".equalsIgnoreCase(paramIntent)))
    {
      Log.d("CameraLauncher", "I either have a null image path or bitmap");
      failPicture("Unable to retrieve path to picture!");
      return;
    }
    paramIntent = null;
    try
    {
      localObject1 = getScaledBitmap((String)localObject1);
      paramIntent = (Intent)localObject1;
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        localIOException.printStackTrace();
      }
      localIntent = paramIntent;
      if (!this.correctOrientation) {
        break label304;
      }
      i = getImageOrientation((Uri)localObject2);
      localIntent = paramIntent;
      if (i == 0) {
        break label304;
      }
      localMatrix = new Matrix();
      localMatrix.setRotate(i);
      localIntent = paramIntent;
    }
    if (paramIntent == null)
    {
      Log.d("CameraLauncher", "I either have a null image path or bitmap");
      failPicture("Unable to create bitmap!");
      return;
    }
    try
    {
      int i;
      Matrix localMatrix;
      paramIntent = Bitmap.createBitmap(paramIntent, 0, 0, paramIntent.getWidth(), paramIntent.getHeight(), localMatrix, true);
      localIntent = paramIntent;
      this.orientationCorrected = true;
      localIntent = paramIntent;
    }
    catch (OutOfMemoryError paramIntent)
    {
      for (;;)
      {
        Intent localIntent;
        label304:
        this.orientationCorrected = false;
        continue;
        if ((paramInt == 1) || (paramInt == 2)) {
          if (((this.targetHeight > 0) && (this.targetWidth > 0)) || ((this.correctOrientation) && (this.orientationCorrected))) {
            try
            {
              paramIntent = ouputModifiedBitmap(localIntent, (Uri)localObject2);
              this.callbackContext.success("file://" + paramIntent + "?" + System.currentTimeMillis());
            }
            catch (Exception paramIntent)
            {
              paramIntent.printStackTrace();
              failPicture("Error retrieving image.");
            }
          } else {
            this.callbackContext.success(str);
          }
        }
      }
    }
    if (paramInt == 0)
    {
      processPicture(localIntent, this.encodingType);
      if (localIntent != null) {
        localIntent.recycle();
      }
      System.gc();
      return;
    }
  }
  
  private Cursor queryImgDB(Uri paramUri)
  {
    return this.cordova.getActivity().getContentResolver().query(paramUri, new String[] { "_id" }, null, null, null);
  }
  
  private void refreshGallery(Uri paramUri)
  {
    Intent localIntent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
    localIntent.setData(paramUri);
    this.cordova.getActivity().sendBroadcast(localIntent);
  }
  
  private void scanForGallery(Uri paramUri)
  {
    this.scanMe = paramUri;
    if (this.conn != null) {
      this.conn.disconnect();
    }
    this.conn = new MediaScannerConnection(this.cordova.getActivity().getApplicationContext(), this);
    this.conn.connect();
  }
  
  private Uri whichContentStore()
  {
    if (Environment.getExternalStorageState().equals("mounted")) {
      return MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
    }
    return MediaStore.Images.Media.INTERNAL_CONTENT_URI;
  }
  
  /* Error */
  private void writeUncompressedImage(Uri paramUri1, Uri paramUri2)
    throws java.io.FileNotFoundException, IOException
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 6
    //   3: aconst_null
    //   4: astore 5
    //   6: aconst_null
    //   7: astore 7
    //   9: new 674	java/io/FileInputStream
    //   12: dup
    //   13: aload_1
    //   14: invokevirtual 188	android/net/Uri:toString	()Ljava/lang/String;
    //   17: invokestatic 194	org/apache/cordova/camera/FileHelper:stripFileProtocol	(Ljava/lang/String;)Ljava/lang/String;
    //   20: invokespecial 675	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   23: astore 4
    //   25: aload 7
    //   27: astore_1
    //   28: aload_0
    //   29: getfield 157	org/apache/cordova/camera/CameraLauncher:cordova	Lorg/apache/cordova/CordovaInterface;
    //   32: invokeinterface 163 1 0
    //   37: invokevirtual 169	android/app/Activity:getContentResolver	()Landroid/content/ContentResolver;
    //   40: aload_2
    //   41: invokevirtual 603	android/content/ContentResolver:openOutputStream	(Landroid/net/Uri;)Ljava/io/OutputStream;
    //   44: astore_2
    //   45: aload_2
    //   46: astore_1
    //   47: sipush 4096
    //   50: newarray <illegal type>
    //   52: astore 5
    //   54: aload_2
    //   55: astore_1
    //   56: aload 4
    //   58: aload 5
    //   60: invokevirtual 679	java/io/FileInputStream:read	([B)I
    //   63: istore_3
    //   64: iload_3
    //   65: iconst_m1
    //   66: if_icmpeq +43 -> 109
    //   69: aload_2
    //   70: astore_1
    //   71: aload_2
    //   72: aload 5
    //   74: iconst_0
    //   75: iload_3
    //   76: invokevirtual 683	java/io/OutputStream:write	([BII)V
    //   79: goto -25 -> 54
    //   82: astore_2
    //   83: aload_1
    //   84: astore 5
    //   86: aload 4
    //   88: astore_1
    //   89: aload 5
    //   91: ifnull +8 -> 99
    //   94: aload 5
    //   96: invokevirtual 452	java/io/OutputStream:close	()V
    //   99: aload_1
    //   100: ifnull +7 -> 107
    //   103: aload_1
    //   104: invokevirtual 684	java/io/FileInputStream:close	()V
    //   107: aload_2
    //   108: athrow
    //   109: aload_2
    //   110: astore_1
    //   111: aload_2
    //   112: invokevirtual 687	java/io/OutputStream:flush	()V
    //   115: aload_2
    //   116: ifnull +7 -> 123
    //   119: aload_2
    //   120: invokevirtual 452	java/io/OutputStream:close	()V
    //   123: aload 4
    //   125: ifnull +8 -> 133
    //   128: aload 4
    //   130: invokevirtual 684	java/io/FileInputStream:close	()V
    //   133: return
    //   134: astore_1
    //   135: ldc 32
    //   137: ldc_w 689
    //   140: invokestatic 346	org/apache/cordova/LOG:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   143: goto -20 -> 123
    //   146: astore_1
    //   147: ldc 32
    //   149: ldc_w 341
    //   152: invokestatic 346	org/apache/cordova/LOG:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   155: return
    //   156: astore 4
    //   158: ldc 32
    //   160: ldc_w 689
    //   163: invokestatic 346	org/apache/cordova/LOG:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   166: goto -67 -> 99
    //   169: astore_1
    //   170: ldc 32
    //   172: ldc_w 341
    //   175: invokestatic 346	org/apache/cordova/LOG:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   178: goto -71 -> 107
    //   181: astore_2
    //   182: aload 6
    //   184: astore_1
    //   185: goto -96 -> 89
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	188	0	this	CameraLauncher
    //   0	188	1	paramUri1	Uri
    //   0	188	2	paramUri2	Uri
    //   63	13	3	i	int
    //   23	106	4	localFileInputStream	java.io.FileInputStream
    //   156	1	4	localIOException	IOException
    //   4	91	5	localObject1	Object
    //   1	182	6	localObject2	Object
    //   7	19	7	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   28	45	82	finally
    //   47	54	82	finally
    //   56	64	82	finally
    //   71	79	82	finally
    //   111	115	82	finally
    //   119	123	134	java/io/IOException
    //   128	133	146	java/io/IOException
    //   94	99	156	java/io/IOException
    //   103	107	169	java/io/IOException
    //   9	25	181	finally
  }
  
  public int[] calculateAspectRatio(int paramInt1, int paramInt2)
  {
    int j = this.targetWidth;
    int k = this.targetHeight;
    int i;
    if ((j <= 0) && (k <= 0))
    {
      j = paramInt1;
      i = paramInt2;
    }
    for (;;)
    {
      return new int[] { j, i };
      if ((j > 0) && (k <= 0))
      {
        i = j * paramInt2 / paramInt1;
      }
      else if ((j <= 0) && (k > 0))
      {
        j = k * paramInt1 / paramInt2;
        i = k;
      }
      else
      {
        double d1 = j / k;
        double d2 = paramInt1 / paramInt2;
        if (d2 > d1)
        {
          i = j * paramInt2 / paramInt1;
        }
        else
        {
          i = k;
          if (d2 < d1)
          {
            j = k * paramInt1 / paramInt2;
            i = k;
          }
        }
      }
    }
  }
  
  public void callTakePicture(int paramInt1, int paramInt2)
  {
    if (PermissionHelper.hasPermission(this, permissions[0]))
    {
      takePicture(paramInt1, paramInt2);
      return;
    }
    PermissionHelper.requestPermission(this, 0, "android.permission.READ_EXTERNAL_STORAGE");
  }
  
  public boolean execute(String paramString, JSONArray paramJSONArray, CallbackContext paramCallbackContext)
    throws JSONException
  {
    this.callbackContext = paramCallbackContext;
    if (paramString.equals("takePicture"))
    {
      this.srcType = 1;
      this.destType = 1;
      this.saveToPhotoAlbum = false;
      this.targetHeight = 0;
      this.targetWidth = 0;
      this.encodingType = 0;
      this.mediaType = 0;
      this.mQuality = 80;
      this.destType = paramJSONArray.getInt(1);
      this.srcType = paramJSONArray.getInt(2);
      this.mQuality = paramJSONArray.getInt(0);
      this.targetWidth = paramJSONArray.getInt(3);
      this.targetHeight = paramJSONArray.getInt(4);
      this.encodingType = paramJSONArray.getInt(5);
      this.mediaType = paramJSONArray.getInt(6);
      this.allowEdit = paramJSONArray.getBoolean(7);
      this.correctOrientation = paramJSONArray.getBoolean(8);
      this.saveToPhotoAlbum = paramJSONArray.getBoolean(9);
      if (this.targetWidth < 1) {
        this.targetWidth = -1;
      }
      if (this.targetHeight < 1) {
        this.targetHeight = -1;
      }
      if ((this.targetHeight == -1) && (this.targetWidth == -1) && (this.mQuality == 100) && (!this.correctOrientation) && (this.encodingType == 1) && (this.srcType == 1)) {
        this.encodingType = 0;
      }
      for (;;)
      {
        try
        {
          if (this.srcType == 1)
          {
            callTakePicture(this.destType, this.encodingType);
            paramString = new PluginResult(PluginResult.Status.NO_RESULT);
            paramString.setKeepCallback(true);
            paramCallbackContext.sendPluginResult(paramString);
            return true;
          }
          if ((this.srcType == 0) || (this.srcType == 2)) {
            if ((this.mediaType == 0) && ((this.destType == 1) || (this.destType == 2)) && (fileWillBeModified()) && (!PermissionHelper.hasPermission(this, permissions[0]))) {
              PermissionHelper.requestPermission(this, 1, "android.permission.READ_EXTERNAL_STORAGE");
            } else {
              getImage(this.srcType, this.destType, this.encodingType);
            }
          }
        }
        catch (IllegalArgumentException paramString)
        {
          paramCallbackContext.error("Illegal Argument Exception");
          paramCallbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR));
          return true;
        }
      }
    }
    return false;
  }
  
  public void failPicture(String paramString)
  {
    this.callbackContext.error(paramString);
  }
  
  public void getImage(int paramInt1, int paramInt2, int paramInt3)
  {
    Intent localIntent = new Intent();
    String str = "Get Picture";
    this.croppedUri = null;
    if (this.mediaType == 0)
    {
      localIntent.setType("image/*");
      if (this.allowEdit)
      {
        localIntent.setAction("android.intent.action.PICK");
        localIntent.putExtra("crop", "true");
        if (this.targetWidth > 0) {
          localIntent.putExtra("outputX", this.targetWidth);
        }
        if (this.targetHeight > 0) {
          localIntent.putExtra("outputY", this.targetHeight);
        }
        if ((this.targetHeight > 0) && (this.targetWidth > 0) && (this.targetWidth == this.targetHeight))
        {
          localIntent.putExtra("aspectX", 1);
          localIntent.putExtra("aspectY", 1);
        }
        this.croppedUri = Uri.fromFile(createCaptureFile(paramInt3));
        localIntent.putExtra("output", this.croppedUri);
      }
    }
    for (;;)
    {
      if (this.cordova != null)
      {
        com.example.example75f1799f07eb.MyPhoneGapActivity.activityResultCallback = this;
        this.cordova.startActivityForResult(this, Intent.createChooser(localIntent, new String(str)), (paramInt1 + 1) * 16 + paramInt2 + 1);
      }
      return;
      localIntent.setAction("android.intent.action.GET_CONTENT");
      localIntent.addCategory("android.intent.category.OPENABLE");
      continue;
      if (this.mediaType == 1)
      {
        localIntent.setType("video/*");
        str = "Get Video";
        localIntent.setAction("android.intent.action.GET_CONTENT");
        localIntent.addCategory("android.intent.category.OPENABLE");
      }
      else if (this.mediaType == 2)
      {
        localIntent.setType("*/*");
        str = "Get All";
        localIntent.setAction("android.intent.action.GET_CONTENT");
        localIntent.addCategory("android.intent.category.OPENABLE");
      }
    }
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, final Intent paramIntent)
  {
    int i = paramInt1 / 16 - 1;
    final int j = paramInt1 % 16 - 1;
    if (paramInt1 >= 100) {
      if (paramInt2 != -1) {}
    }
    do
    {
      try
      {
        processResultFromCamera(paramInt1 - 100, paramIntent);
        return;
      }
      catch (IOException paramIntent)
      {
        paramIntent.printStackTrace();
        Log.e("CameraLauncher", "Unable to write to file");
        return;
      }
      if (paramInt2 == 0)
      {
        failPicture("Camera cancelled.");
        return;
      }
      failPicture("Did not complete!");
      return;
      if (i == 1)
      {
        if (paramInt2 == -1)
        {
          try
          {
            if (this.allowEdit)
            {
              performCrop(Uri.fromFile(createCaptureFile(this.encodingType)), j, paramIntent);
              return;
            }
          }
          catch (IOException paramIntent)
          {
            paramIntent.printStackTrace();
            failPicture("Error capturing image.");
            return;
          }
          processResultFromCamera(j, paramIntent);
          return;
        }
        if (paramInt2 == 0)
        {
          failPicture("Camera cancelled.");
          return;
        }
        failPicture("Did not complete!");
        return;
      }
    } while ((i != 0) && (i != 2));
    if ((paramInt2 == -1) && (paramIntent != null))
    {
      this.cordova.getThreadPool().execute(new Runnable()
      {
        public void run()
        {
          CameraLauncher.this.processResultFromGallery(j, paramIntent);
        }
      });
      return;
    }
    if (paramInt2 == 0)
    {
      failPicture("Selection cancelled.");
      return;
    }
    failPicture("Selection did not complete!");
  }
  
  public void onMediaScannerConnected()
  {
    try
    {
      this.conn.scanFile(this.scanMe.toString(), "image/*");
      return;
    }
    catch (IllegalStateException localIllegalStateException)
    {
      LOG.e("CameraLauncher", "Can't scan file in MediaScanner after taking picture");
    }
  }
  
  public void onRequestPermissionResult(int paramInt, String[] paramArrayOfString, int[] paramArrayOfInt)
    throws JSONException
  {
    int j = paramArrayOfInt.length;
    int i = 0;
    while (i < j)
    {
      if (paramArrayOfInt[i] == -1)
      {
        this.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, 20));
        return;
      }
      i += 1;
    }
    switch (paramInt)
    {
    default: 
      return;
    case 0: 
      takePicture(this.destType, this.encodingType);
      return;
    }
    getImage(this.srcType, this.destType, this.encodingType);
  }
  
  public void onRestoreStateForActivityResult(Bundle paramBundle, CallbackContext paramCallbackContext)
  {
    this.destType = paramBundle.getInt("destType");
    this.srcType = paramBundle.getInt("srcType");
    this.mQuality = paramBundle.getInt("mQuality");
    this.targetWidth = paramBundle.getInt("targetWidth");
    this.targetHeight = paramBundle.getInt("targetHeight");
    this.encodingType = paramBundle.getInt("encodingType");
    this.mediaType = paramBundle.getInt("mediaType");
    this.numPics = paramBundle.getInt("numPics");
    this.allowEdit = paramBundle.getBoolean("allowEdit");
    this.correctOrientation = paramBundle.getBoolean("correctOrientation");
    this.saveToPhotoAlbum = paramBundle.getBoolean("saveToPhotoAlbum");
    if (paramBundle.containsKey("croppedUri")) {
      this.croppedUri = Uri.parse(paramBundle.getString("croppedUri"));
    }
    if (paramBundle.containsKey("imageUri")) {
      this.imageUri = Uri.parse(paramBundle.getString("imageUri"));
    }
    this.callbackContext = paramCallbackContext;
  }
  
  public Bundle onSaveInstanceState()
  {
    Bundle localBundle = new Bundle();
    localBundle.putInt("destType", this.destType);
    localBundle.putInt("srcType", this.srcType);
    localBundle.putInt("mQuality", this.mQuality);
    localBundle.putInt("targetWidth", this.targetWidth);
    localBundle.putInt("targetHeight", this.targetHeight);
    localBundle.putInt("encodingType", this.encodingType);
    localBundle.putInt("mediaType", this.mediaType);
    localBundle.putInt("numPics", this.numPics);
    localBundle.putBoolean("allowEdit", this.allowEdit);
    localBundle.putBoolean("correctOrientation", this.correctOrientation);
    localBundle.putBoolean("saveToPhotoAlbum", this.saveToPhotoAlbum);
    if (this.croppedUri != null) {
      localBundle.putString("croppedUri", this.croppedUri.toString());
    }
    if (this.imageUri != null) {
      localBundle.putString("imageUri", this.imageUri.toString());
    }
    return localBundle;
  }
  
  public void onScanCompleted(String paramString, Uri paramUri)
  {
    this.conn.disconnect();
  }
  
  public void processPicture(Bitmap paramBitmap, int paramInt)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    Bitmap.CompressFormat localCompressFormat;
    if (paramInt == 0) {
      localCompressFormat = Bitmap.CompressFormat.JPEG;
    }
    try
    {
      for (;;)
      {
        if (paramBitmap.compress(localCompressFormat, this.mQuality, localByteArrayOutputStream))
        {
          paramBitmap = new String(Base64.encode(localByteArrayOutputStream.toByteArray(), 2));
          this.callbackContext.success(paramBitmap);
        }
        return;
        localCompressFormat = Bitmap.CompressFormat.PNG;
      }
    }
    catch (Exception paramBitmap)
    {
      for (;;)
      {
        failPicture("Error compressing image.");
      }
    }
  }
  
  public void takePicture(int paramInt1, int paramInt2)
  {
    this.numPics = queryImgDB(whichContentStore()).getCount();
    Intent localIntent = new Intent("android.media.action.IMAGE_CAPTURE");
    File localFile = createCaptureFile(paramInt2);
    localIntent.putExtra("output", Uri.fromFile(localFile));
    this.imageUri = Uri.fromFile(localFile);
    if (this.cordova != null)
    {
      if (localIntent.resolveActivity(this.cordova.getActivity().getPackageManager()) != null) {
        this.cordova.startActivityForResult(this, localIntent, paramInt1 + 32 + 1);
      }
    }
    else {
      return;
    }
    LOG.d("CameraLauncher", "Error: You don't have a default camera.  Your device may not be CTS complaint.");
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\cordova\camera\CameraLauncher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */