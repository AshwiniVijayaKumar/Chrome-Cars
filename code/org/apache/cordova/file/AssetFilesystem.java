package org.apache.cordova.file;

import android.content.res.AssetManager;
import android.net.Uri;
import android.net.Uri.Builder;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import org.apache.cordova.CordovaResourceApi;
import org.apache.cordova.CordovaResourceApi.OpenForReadResult;
import org.json.JSONException;
import org.json.JSONObject;

public class AssetFilesystem
  extends Filesystem
{
  private static Map<String, Long> lengthCache;
  private static Map<String, String[]> listCache;
  private static boolean listCacheFromFile;
  private static Object listCacheLock = new Object();
  private final AssetManager assetManager;
  
  public AssetFilesystem(AssetManager paramAssetManager, CordovaResourceApi paramCordovaResourceApi)
  {
    super(Uri.parse("file:///android_asset/"), "assets", paramCordovaResourceApi);
    this.assetManager = paramAssetManager;
  }
  
  private long getAssetSize(String paramString)
    throws FileNotFoundException
  {
    String str2 = paramString;
    if (paramString.startsWith("/")) {
      str2 = paramString.substring(1);
    }
    lazyInitCaches();
    long l2;
    if (lengthCache != null)
    {
      paramString = (Long)lengthCache.get(str2);
      if (paramString == null) {
        throw new FileNotFoundException("Asset not found: " + str2);
      }
      l2 = paramString.longValue();
    }
    for (;;)
    {
      return l2;
      Object localObject = null;
      paramString = null;
      try
      {
        CordovaResourceApi.OpenForReadResult localOpenForReadResult = this.resourceApi.openForRead(nativeUriForFullPath(str2));
        paramString = localOpenForReadResult;
        localObject = localOpenForReadResult;
        l2 = localOpenForReadResult.length;
        long l1 = l2;
        if (l2 < 0L)
        {
          paramString = localOpenForReadResult;
          localObject = localOpenForReadResult;
          int i = localOpenForReadResult.inputStream.available();
          l1 = i;
        }
        l2 = l1;
        if (localOpenForReadResult == null) {
          continue;
        }
        try
        {
          localOpenForReadResult.inputStream.close();
          return l1;
        }
        catch (IOException paramString)
        {
          return l1;
        }
        try
        {
          String str1;
          str1.inputStream.close();
          throw paramString;
        }
        catch (IOException localIOException2)
        {
          for (;;) {}
        }
      }
      catch (IOException localIOException1)
      {
        str1 = paramString;
        throw new FileNotFoundException("File not found: " + str2);
      }
      finally
      {
        if (str1 == null) {}
      }
    }
  }
  
  private boolean isDirectory(String paramString)
  {
    boolean bool = false;
    try
    {
      int i = listAssets(paramString).length;
      if (i != 0) {
        bool = true;
      }
      return bool;
    }
    catch (IOException paramString) {}
    return false;
  }
  
  /* Error */
  private void lazyInitCaches()
  {
    // Byte code:
    //   0: getstatic 24	org/apache/cordova/file/AssetFilesystem:listCacheLock	Ljava/lang/Object;
    //   3: astore 5
    //   5: aload 5
    //   7: monitorenter
    //   8: getstatic 136	org/apache/cordova/file/AssetFilesystem:listCache	Ljava/util/Map;
    //   11: astore_1
    //   12: aload_1
    //   13: ifnonnull +83 -> 96
    //   16: aconst_null
    //   17: astore 4
    //   19: aconst_null
    //   20: astore_1
    //   21: aconst_null
    //   22: astore_3
    //   23: new 138	java/io/ObjectInputStream
    //   26: dup
    //   27: aload_0
    //   28: getfield 41	org/apache/cordova/file/AssetFilesystem:assetManager	Landroid/content/res/AssetManager;
    //   31: ldc -116
    //   33: invokevirtual 146	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   36: invokespecial 149	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   39: astore_2
    //   40: aload_2
    //   41: invokevirtual 153	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
    //   44: checkcast 66	java/util/Map
    //   47: putstatic 136	org/apache/cordova/file/AssetFilesystem:listCache	Ljava/util/Map;
    //   50: aload_2
    //   51: invokevirtual 153	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
    //   54: checkcast 66	java/util/Map
    //   57: putstatic 64	org/apache/cordova/file/AssetFilesystem:lengthCache	Ljava/util/Map;
    //   60: iconst_1
    //   61: putstatic 155	org/apache/cordova/file/AssetFilesystem:listCacheFromFile	Z
    //   64: aload_2
    //   65: ifnull +123 -> 188
    //   68: aload_2
    //   69: invokevirtual 156	java/io/ObjectInputStream:close	()V
    //   72: getstatic 136	org/apache/cordova/file/AssetFilesystem:listCache	Ljava/util/Map;
    //   75: ifnonnull +21 -> 96
    //   78: ldc -98
    //   80: ldc -96
    //   82: invokestatic 166	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   85: pop
    //   86: new 168	java/util/HashMap
    //   89: dup
    //   90: invokespecial 169	java/util/HashMap:<init>	()V
    //   93: putstatic 136	org/apache/cordova/file/AssetFilesystem:listCache	Ljava/util/Map;
    //   96: aload 5
    //   98: monitorexit
    //   99: return
    //   100: astore_1
    //   101: goto -29 -> 72
    //   104: astore_1
    //   105: aload_3
    //   106: astore_2
    //   107: aload_1
    //   108: astore_3
    //   109: aload_2
    //   110: astore_1
    //   111: aload_3
    //   112: invokevirtual 172	java/lang/ClassNotFoundException:printStackTrace	()V
    //   115: aload_2
    //   116: ifnull -44 -> 72
    //   119: aload_2
    //   120: invokevirtual 156	java/io/ObjectInputStream:close	()V
    //   123: goto -51 -> 72
    //   126: astore_1
    //   127: goto -55 -> 72
    //   130: astore_1
    //   131: aload 4
    //   133: astore_1
    //   134: aload_1
    //   135: ifnull -63 -> 72
    //   138: aload_1
    //   139: invokevirtual 156	java/io/ObjectInputStream:close	()V
    //   142: goto -70 -> 72
    //   145: astore_1
    //   146: goto -74 -> 72
    //   149: astore_2
    //   150: aload_1
    //   151: ifnull +7 -> 158
    //   154: aload_1
    //   155: invokevirtual 156	java/io/ObjectInputStream:close	()V
    //   158: aload_2
    //   159: athrow
    //   160: astore_1
    //   161: aload 5
    //   163: monitorexit
    //   164: aload_1
    //   165: athrow
    //   166: astore_1
    //   167: goto -9 -> 158
    //   170: astore_3
    //   171: aload_2
    //   172: astore_1
    //   173: aload_3
    //   174: astore_2
    //   175: goto -25 -> 150
    //   178: astore_1
    //   179: aload_2
    //   180: astore_1
    //   181: goto -47 -> 134
    //   184: astore_3
    //   185: goto -76 -> 109
    //   188: goto -116 -> 72
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	191	0	this	AssetFilesystem
    //   11	10	1	localMap	Map
    //   100	1	1	localIOException1	IOException
    //   104	4	1	localClassNotFoundException1	ClassNotFoundException
    //   110	1	1	localObject1	Object
    //   126	1	1	localIOException2	IOException
    //   130	1	1	localIOException3	IOException
    //   133	6	1	localObject2	Object
    //   145	10	1	localIOException4	IOException
    //   160	5	1	localObject3	Object
    //   166	1	1	localIOException5	IOException
    //   172	1	1	localObject4	Object
    //   178	1	1	localIOException6	IOException
    //   180	1	1	localObject5	Object
    //   39	81	2	localObject6	Object
    //   149	23	2	localObject7	Object
    //   174	6	2	localObject8	Object
    //   22	90	3	localClassNotFoundException2	ClassNotFoundException
    //   170	4	3	localObject9	Object
    //   184	1	3	localClassNotFoundException3	ClassNotFoundException
    //   17	115	4	localObject10	Object
    //   3	159	5	localObject11	Object
    // Exception table:
    //   from	to	target	type
    //   68	72	100	java/io/IOException
    //   23	40	104	java/lang/ClassNotFoundException
    //   119	123	126	java/io/IOException
    //   23	40	130	java/io/IOException
    //   138	142	145	java/io/IOException
    //   23	40	149	finally
    //   111	115	149	finally
    //   8	12	160	finally
    //   68	72	160	finally
    //   72	96	160	finally
    //   96	99	160	finally
    //   119	123	160	finally
    //   138	142	160	finally
    //   154	158	160	finally
    //   158	160	160	finally
    //   161	164	160	finally
    //   154	158	166	java/io/IOException
    //   40	64	170	finally
    //   40	64	178	java/io/IOException
    //   40	64	184	java/lang/ClassNotFoundException
  }
  
  private String[] listAssets(String paramString)
    throws IOException
  {
    Object localObject = paramString;
    if (paramString.startsWith("/")) {
      localObject = paramString.substring(1);
    }
    paramString = (String)localObject;
    if (((String)localObject).endsWith("/")) {
      paramString = ((String)localObject).substring(0, ((String)localObject).length() - 1);
    }
    lazyInitCaches();
    String[] arrayOfString = (String[])listCache.get(paramString);
    localObject = arrayOfString;
    if (arrayOfString == null)
    {
      if (listCacheFromFile) {
        localObject = new String[0];
      }
    }
    else {
      return (String[])localObject;
    }
    localObject = this.assetManager.list(paramString);
    listCache.put(paramString, localObject);
    return (String[])localObject;
  }
  
  LocalFilesystemURL URLforFilesystemPath(String paramString)
  {
    return null;
  }
  
  public boolean canRemoveFileAtLocalURL(LocalFilesystemURL paramLocalFilesystemURL)
  {
    return false;
  }
  
  String filesystemPathForURL(LocalFilesystemURL paramLocalFilesystemURL)
  {
    return null;
  }
  
  public JSONObject getFileForLocalURL(LocalFilesystemURL paramLocalFilesystemURL, String paramString, JSONObject paramJSONObject, boolean paramBoolean)
    throws FileExistsException, IOException, TypeMismatchException, EncodingException, JSONException
  {
    if ((paramJSONObject != null) && (paramJSONObject.optBoolean("create"))) {
      throw new UnsupportedOperationException("Assets are read-only");
    }
    paramJSONObject = paramString;
    if (paramBoolean)
    {
      paramJSONObject = paramString;
      if (!paramString.endsWith("/")) {
        paramJSONObject = paramString + "/";
      }
    }
    if (paramJSONObject.startsWith("/")) {}
    boolean bool;
    for (paramLocalFilesystemURL = localUrlforFullPath(normalizePath(paramJSONObject));; paramLocalFilesystemURL = localUrlforFullPath(normalizePath(paramLocalFilesystemURL.path + "/" + paramJSONObject)))
    {
      getFileMetadataForLocalURL(paramLocalFilesystemURL);
      bool = isDirectory(paramLocalFilesystemURL.path);
      if ((!paramBoolean) || (bool)) {
        break;
      }
      throw new TypeMismatchException("path doesn't exist or is file");
    }
    if ((!paramBoolean) && (bool)) {
      throw new TypeMismatchException("path doesn't exist or is directory");
    }
    return makeEntryForURL(paramLocalFilesystemURL);
  }
  
  public JSONObject getFileMetadataForLocalURL(LocalFilesystemURL paramLocalFilesystemURL)
    throws FileNotFoundException
  {
    JSONObject localJSONObject = new JSONObject();
    long l;
    if (paramLocalFilesystemURL.isDirectory) {
      l = 0L;
    }
    try
    {
      localJSONObject.put("size", l);
      if (paramLocalFilesystemURL.isDirectory) {}
      for (String str = "text/directory";; str = this.resourceApi.getMimeType(toNativeUri(paramLocalFilesystemURL)))
      {
        localJSONObject.put("type", str);
        localJSONObject.put("name", new File(paramLocalFilesystemURL.path).getName());
        localJSONObject.put("fullPath", paramLocalFilesystemURL.path);
        localJSONObject.put("lastModifiedDate", 0);
        return localJSONObject;
        l = getAssetSize(paramLocalFilesystemURL.path);
        break;
      }
      return null;
    }
    catch (JSONException paramLocalFilesystemURL) {}
  }
  
  public LocalFilesystemURL[] listChildren(LocalFilesystemURL paramLocalFilesystemURL)
    throws FileNotFoundException
  {
    localObject2 = paramLocalFilesystemURL.path.substring(1);
    Object localObject1 = localObject2;
    if (((String)localObject2).endsWith("/")) {
      localObject1 = ((String)localObject2).substring(0, ((String)localObject2).length() - 1);
    }
    try
    {
      localObject1 = listAssets((String)localObject1);
      localObject2 = new LocalFilesystemURL[localObject1.length];
      int i = 0;
      while (i < localObject1.length)
      {
        localObject2[i] = localUrlforFullPath(new File(paramLocalFilesystemURL.path, localObject1[i]).getPath());
        i += 1;
      }
      return (LocalFilesystemURL[])localObject2;
    }
    catch (IOException paramLocalFilesystemURL)
    {
      throw new FileNotFoundException();
    }
  }
  
  boolean recursiveRemoveFileAtLocalURL(LocalFilesystemURL paramLocalFilesystemURL)
    throws NoModificationAllowedException
  {
    throw new NoModificationAllowedException("Assets are read-only");
  }
  
  boolean removeFileAtLocalURL(LocalFilesystemURL paramLocalFilesystemURL)
    throws InvalidModificationException, NoModificationAllowedException
  {
    throw new NoModificationAllowedException("Assets are read-only");
  }
  
  public LocalFilesystemURL toLocalUri(Uri paramUri)
  {
    if (!"file".equals(paramUri.getScheme())) {}
    do
    {
      return null;
      localObject1 = Uri.fromFile(new File(paramUri.getPath()));
      localObject2 = this.rootUri.getEncodedPath();
      localObject2 = ((String)localObject2).substring(0, ((String)localObject2).length() - 1);
    } while (!((Uri)localObject1).getEncodedPath().startsWith((String)localObject2));
    Object localObject2 = ((Uri)localObject1).getEncodedPath().substring(((String)localObject2).length());
    Object localObject1 = localObject2;
    if (!((String)localObject2).isEmpty()) {
      localObject1 = ((String)localObject2).substring(1);
    }
    localObject2 = new Uri.Builder().scheme("cdvfile").authority("localhost").path(this.name);
    if (!((String)localObject1).isEmpty()) {
      ((Uri.Builder)localObject2).appendEncodedPath((String)localObject1);
    }
    if ((isDirectory((String)localObject1)) || (paramUri.getPath().endsWith("/"))) {
      ((Uri.Builder)localObject2).appendEncodedPath("");
    }
    return LocalFilesystemURL.parse(((Uri.Builder)localObject2).build());
  }
  
  public Uri toNativeUri(LocalFilesystemURL paramLocalFilesystemURL)
  {
    return nativeUriForFullPath(paramLocalFilesystemURL.path);
  }
  
  long truncateFileAtURL(LocalFilesystemURL paramLocalFilesystemURL, long paramLong)
    throws IOException, NoModificationAllowedException
  {
    throw new NoModificationAllowedException("Assets are read-only");
  }
  
  long writeToFileAtURL(LocalFilesystemURL paramLocalFilesystemURL, String paramString, int paramInt, boolean paramBoolean)
    throws NoModificationAllowedException, IOException
  {
    throw new NoModificationAllowedException("Assets are read-only");
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\cordova\file\AssetFilesystem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */