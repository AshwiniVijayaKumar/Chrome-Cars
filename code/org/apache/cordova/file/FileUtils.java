package org.apache.cordova.file;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaPreferences;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FileUtils
  extends CordovaPlugin
{
  public static int ABORT_ERR = 0;
  public static int ENCODING_ERR = 0;
  public static int INVALID_MODIFICATION_ERR = 0;
  public static int INVALID_STATE_ERR = 0;
  private static final String LOG_TAG = "FileUtils";
  public static int NOT_FOUND_ERR = 1;
  public static int NOT_READABLE_ERR = 0;
  public static int NO_MODIFICATION_ALLOWED_ERR = 0;
  public static int PATH_EXISTS_ERR = 12;
  public static int QUOTA_EXCEEDED_ERR = 0;
  public static final int READ_PERM = 0;
  public static int SECURITY_ERR = 2;
  public static int SYNTAX_ERR = 0;
  public static int TYPE_MISMATCH_ERR = 0;
  public static int UNKNOWN_ERR = 1000;
  public static final int WRITE_PERM = 1;
  private static FileUtils filePlugin;
  private CallbackContext callback;
  private boolean configured = false;
  private ArrayList<Filesystem> filesystems;
  private String lastRawArgs;
  private String[] permissions = { "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE" };
  
  static
  {
    ABORT_ERR = 3;
    NOT_READABLE_ERR = 4;
    ENCODING_ERR = 5;
    NO_MODIFICATION_ALLOWED_ERR = 6;
    INVALID_STATE_ERR = 7;
    SYNTAX_ERR = 8;
    INVALID_MODIFICATION_ERR = 9;
    QUOTA_EXCEEDED_ERR = 10;
    TYPE_MISMATCH_ERR = 11;
  }
  
  private Filesystem filesystemForName(String paramString)
  {
    Iterator localIterator = this.filesystems.iterator();
    while (localIterator.hasNext())
    {
      Filesystem localFilesystem = (Filesystem)localIterator.next();
      if ((localFilesystem != null) && (localFilesystem.name != null) && (localFilesystem.name.equals(paramString))) {
        return localFilesystem;
      }
    }
    return null;
  }
  
  private Filesystem filesystemForURL(LocalFilesystemURL paramLocalFilesystemURL)
  {
    if (paramLocalFilesystemURL == null) {
      return null;
    }
    return filesystemForName(paramLocalFilesystemURL.fsName);
  }
  
  @Deprecated
  public static JSONObject getEntry(File paramFile)
    throws JSONException
  {
    if (getFilePlugin() != null) {
      return getFilePlugin().getEntryForFile(paramFile);
    }
    return null;
  }
  
  private JSONObject getFile(String paramString1, String paramString2, JSONObject paramJSONObject, boolean paramBoolean)
    throws FileExistsException, IOException, TypeMismatchException, EncodingException, JSONException
  {
    Filesystem localFilesystem;
    try
    {
      paramString1 = LocalFilesystemURL.parse(paramString1);
      localFilesystem = filesystemForURL(paramString1);
      if (localFilesystem == null) {
        throw new MalformedURLException("No installed handlers for this URL");
      }
    }
    catch (IllegalArgumentException paramString1)
    {
      throw new MalformedURLException("Unrecognized filesystem URL");
    }
    paramString1 = localFilesystem.getFileForLocalURL(paramString1, paramString2, paramJSONObject, paramBoolean);
    return paramString1;
  }
  
  private JSONObject getFileMetadata(String paramString)
    throws FileNotFoundException, JSONException, MalformedURLException
  {
    Filesystem localFilesystem;
    try
    {
      paramString = LocalFilesystemURL.parse(paramString);
      localFilesystem = filesystemForURL(paramString);
      if (localFilesystem == null) {
        throw new MalformedURLException("No installed handlers for this URL");
      }
    }
    catch (IllegalArgumentException paramString)
    {
      throw new MalformedURLException("Unrecognized filesystem URL");
    }
    paramString = localFilesystem.getFileMetadataForLocalURL(paramString);
    return paramString;
  }
  
  public static FileUtils getFilePlugin()
  {
    return filePlugin;
  }
  
  private JSONObject getParent(String paramString)
    throws JSONException, IOException
  {
    Filesystem localFilesystem;
    try
    {
      paramString = LocalFilesystemURL.parse(paramString);
      localFilesystem = filesystemForURL(paramString);
      if (localFilesystem == null) {
        throw new MalformedURLException("No installed handlers for this URL");
      }
    }
    catch (IllegalArgumentException paramString)
    {
      throw new MalformedURLException("Unrecognized filesystem URL");
    }
    paramString = localFilesystem.getParentForLocalURL(paramString);
    return paramString;
  }
  
  private void getReadPermission()
  {
    PermissionHelper.requestPermission(this, 0, "android.permission.READ_EXTERNAL_STORAGE");
  }
  
  private void getWritePermission()
  {
    PermissionHelper.requestPermission(this, 1, "android.permission.WRITE_EXTERNAL_STORAGE");
  }
  
  private boolean hasReadPermission()
  {
    return PermissionHelper.hasPermission(this, "android.permission.READ_EXTERNAL_STORAGE");
  }
  
  private boolean hasWritePermission()
  {
    return PermissionHelper.hasPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE");
  }
  
  private JSONArray readEntries(String paramString)
    throws FileNotFoundException, JSONException, MalformedURLException
  {
    Filesystem localFilesystem;
    try
    {
      paramString = LocalFilesystemURL.parse(paramString);
      localFilesystem = filesystemForURL(paramString);
      if (localFilesystem == null) {
        throw new MalformedURLException("No installed handlers for this URL");
      }
    }
    catch (IllegalArgumentException paramString)
    {
      throw new MalformedURLException("Unrecognized filesystem URL");
    }
    paramString = localFilesystem.readEntriesAtLocalURL(paramString);
    return paramString;
  }
  
  private boolean remove(String paramString)
    throws NoModificationAllowedException, InvalidModificationException, MalformedURLException
  {
    try
    {
      paramString = LocalFilesystemURL.parse(paramString);
      if (("".equals(paramString.path)) || ("/".equals(paramString.path))) {
        throw new NoModificationAllowedException("You can't delete the root directory");
      }
    }
    catch (IllegalArgumentException paramString)
    {
      throw new MalformedURLException("Unrecognized filesystem URL");
    }
    Filesystem localFilesystem = filesystemForURL(paramString);
    if (localFilesystem == null) {
      throw new MalformedURLException("No installed handlers for this URL");
    }
    boolean bool = localFilesystem.removeFileAtLocalURL(paramString);
    return bool;
  }
  
  private boolean removeRecursively(String paramString)
    throws FileExistsException, NoModificationAllowedException, MalformedURLException
  {
    try
    {
      paramString = LocalFilesystemURL.parse(paramString);
      if (("".equals(paramString.path)) || ("/".equals(paramString.path))) {
        throw new NoModificationAllowedException("You can't delete the root directory");
      }
    }
    catch (IllegalArgumentException paramString)
    {
      throw new MalformedURLException("Unrecognized filesystem URL");
    }
    Filesystem localFilesystem = filesystemForURL(paramString);
    if (localFilesystem == null) {
      throw new MalformedURLException("No installed handlers for this URL");
    }
    boolean bool = localFilesystem.recursiveRemoveFileAtLocalURL(paramString);
    return bool;
  }
  
  private JSONArray requestAllFileSystems()
    throws IOException, JSONException
  {
    JSONArray localJSONArray = new JSONArray();
    Iterator localIterator = this.filesystems.iterator();
    while (localIterator.hasNext()) {
      localJSONArray.put(((Filesystem)localIterator.next()).getRootEntry());
    }
    return localJSONArray;
  }
  
  private JSONObject requestAllPaths()
    throws JSONException
  {
    Activity localActivity = this.cordova.getActivity();
    JSONObject localJSONObject = new JSONObject();
    localJSONObject.put("applicationDirectory", "file:///android_asset/");
    localJSONObject.put("applicationStorageDirectory", toDirUrl(localActivity.getFilesDir().getParentFile()));
    localJSONObject.put("dataDirectory", toDirUrl(localActivity.getFilesDir()));
    localJSONObject.put("cacheDirectory", toDirUrl(localActivity.getCacheDir()));
    if (Environment.getExternalStorageState().equals("mounted")) {}
    try
    {
      localJSONObject.put("externalApplicationStorageDirectory", toDirUrl(localActivity.getExternalFilesDir(null).getParentFile()));
      localJSONObject.put("externalDataDirectory", toDirUrl(localActivity.getExternalFilesDir(null)));
      localJSONObject.put("externalCacheDirectory", toDirUrl(localActivity.getExternalCacheDir()));
      localJSONObject.put("externalRootDirectory", toDirUrl(Environment.getExternalStorageDirectory()));
      return localJSONObject;
    }
    catch (NullPointerException localNullPointerException)
    {
      Log.d("FileUtils", "Unable to access these paths, most liklely due to USB storage");
    }
    return localJSONObject;
  }
  
  private void requestFileSystem(int paramInt, long paramLong, CallbackContext paramCallbackContext)
    throws JSONException
  {
    Object localObject1 = null;
    try
    {
      localObject2 = (Filesystem)this.filesystems.get(paramInt);
      localObject1 = localObject2;
    }
    catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException)
    {
      Object localObject2;
      long l;
      for (;;) {}
    }
    if (localObject1 == null)
    {
      paramCallbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, NOT_FOUND_ERR));
      return;
    }
    l = 0L;
    if (paramLong > 0L) {
      l = ((Filesystem)localObject1).getFreeSpaceInBytes();
    }
    if (l < paramLong)
    {
      paramCallbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, QUOTA_EXCEEDED_ERR));
      return;
    }
    localObject2 = new JSONObject();
    ((JSONObject)localObject2).put("name", ((Filesystem)localObject1).name);
    ((JSONObject)localObject2).put("root", ((Filesystem)localObject1).getRootEntry());
    paramCallbackContext.success((JSONObject)localObject2);
  }
  
  private JSONObject resolveLocalFileSystemURI(String paramString)
    throws IOException, JSONException
  {
    if (paramString == null) {
      throw new MalformedURLException("Unrecognized filesystem URL");
    }
    Uri localUri = Uri.parse(paramString);
    Object localObject = LocalFilesystemURL.parse(localUri);
    paramString = (String)localObject;
    if (localObject == null) {
      paramString = resolveNativeUri(localUri);
    }
    try
    {
      localObject = filesystemForURL(paramString);
      if (localObject == null) {
        throw new MalformedURLException("No installed handlers for this URL");
      }
    }
    catch (IllegalArgumentException paramString)
    {
      throw new MalformedURLException("Unrecognized filesystem URL");
    }
    if (((Filesystem)localObject).exists(paramString))
    {
      paramString = ((Filesystem)localObject).getEntryForLocalURL(paramString);
      return paramString;
    }
    throw new FileNotFoundException();
  }
  
  private void threadhelper(final FileOp paramFileOp, final String paramString, final CallbackContext paramCallbackContext)
  {
    this.cordova.getThreadPool().execute(new Runnable()
    {
      public void run()
      {
        try
        {
          JSONArray localJSONArray = new JSONArray(paramString);
          paramFileOp.run(localJSONArray);
          return;
        }
        catch (Exception localException)
        {
          if ((localException instanceof EncodingException))
          {
            paramCallbackContext.error(FileUtils.ENCODING_ERR);
            return;
          }
          if ((localException instanceof FileNotFoundException))
          {
            paramCallbackContext.error(FileUtils.NOT_FOUND_ERR);
            return;
          }
          if ((localException instanceof FileExistsException))
          {
            paramCallbackContext.error(FileUtils.PATH_EXISTS_ERR);
            return;
          }
          if ((localException instanceof NoModificationAllowedException))
          {
            paramCallbackContext.error(FileUtils.NO_MODIFICATION_ALLOWED_ERR);
            return;
          }
          if ((localException instanceof InvalidModificationException))
          {
            paramCallbackContext.error(FileUtils.INVALID_MODIFICATION_ERR);
            return;
          }
          if ((localException instanceof MalformedURLException))
          {
            paramCallbackContext.error(FileUtils.ENCODING_ERR);
            return;
          }
          if ((localException instanceof IOException))
          {
            paramCallbackContext.error(FileUtils.INVALID_MODIFICATION_ERR);
            return;
          }
          if ((localException instanceof EncodingException))
          {
            paramCallbackContext.error(FileUtils.ENCODING_ERR);
            return;
          }
          if ((localException instanceof TypeMismatchException))
          {
            paramCallbackContext.error(FileUtils.TYPE_MISMATCH_ERR);
            return;
          }
          if ((localException instanceof JSONException))
          {
            paramCallbackContext.sendPluginResult(new PluginResult(PluginResult.Status.JSON_EXCEPTION));
            return;
          }
          localException.printStackTrace();
          paramCallbackContext.error(FileUtils.UNKNOWN_ERR);
        }
      }
    });
  }
  
  private static String toDirUrl(File paramFile)
  {
    return Uri.fromFile(paramFile).toString() + '/';
  }
  
  private JSONObject transferTo(String paramString1, String paramString2, String paramString3, boolean paramBoolean)
    throws JSONException, NoModificationAllowedException, IOException, InvalidModificationException, EncodingException, FileExistsException
  {
    if ((paramString1 == null) || (paramString2 == null)) {
      throw new FileNotFoundException();
    }
    paramString1 = LocalFilesystemURL.parse(paramString1);
    paramString2 = LocalFilesystemURL.parse(paramString2);
    Filesystem localFilesystem1 = filesystemForURL(paramString1);
    Filesystem localFilesystem2 = filesystemForURL(paramString2);
    if ((paramString3 != null) && (paramString3.contains(":"))) {
      throw new EncodingException("Bad file name");
    }
    return localFilesystem2.copyFileToURL(paramString2, paramString3, localFilesystem1, paramString1, paramBoolean);
  }
  
  private long truncateFile(String paramString, long paramLong)
    throws FileNotFoundException, IOException, NoModificationAllowedException
  {
    Filesystem localFilesystem;
    try
    {
      paramString = LocalFilesystemURL.parse(paramString);
      localFilesystem = filesystemForURL(paramString);
      if (localFilesystem == null) {
        throw new MalformedURLException("No installed handlers for this URL");
      }
    }
    catch (IllegalArgumentException paramString)
    {
      throw new MalformedURLException("Unrecognized filesystem URL");
    }
    paramLong = localFilesystem.truncateFileAtURL(paramString, paramLong);
    return paramLong;
  }
  
  public boolean execute(String paramString1, String paramString2, final CallbackContext paramCallbackContext)
  {
    this.callback = paramCallbackContext;
    this.lastRawArgs = paramString2;
    if (!this.configured)
    {
      paramCallbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, "File plugin is not configured. Please see the README.md file for details on how to update config.xml"));
      return true;
    }
    if (paramString1.equals("testSaveLocationExists"))
    {
      threadhelper(new FileOp()
      {
        public void run(JSONArray paramAnonymousJSONArray)
        {
          boolean bool = DirectoryManager.testSaveLocationExists();
          paramCallbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, bool));
        }
      }, paramString2, paramCallbackContext);
      return true;
    }
    if (paramString1.equals("getFreeDiskSpace"))
    {
      threadhelper(new FileOp()
      {
        public void run(JSONArray paramAnonymousJSONArray)
        {
          long l = DirectoryManager.getFreeExternalStorageSpace();
          paramCallbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, (float)l));
        }
      }, paramString2, paramCallbackContext);
      return true;
    }
    if (paramString1.equals("testFileExists"))
    {
      threadhelper(new FileOp()
      {
        public void run(JSONArray paramAnonymousJSONArray)
          throws JSONException
        {
          boolean bool = DirectoryManager.testFileExists(paramAnonymousJSONArray.getString(0));
          paramCallbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, bool));
        }
      }, paramString2, paramCallbackContext);
      return true;
    }
    if (paramString1.equals("testDirectoryExists"))
    {
      threadhelper(new FileOp()
      {
        public void run(JSONArray paramAnonymousJSONArray)
          throws JSONException
        {
          boolean bool = DirectoryManager.testFileExists(paramAnonymousJSONArray.getString(0));
          paramCallbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, bool));
        }
      }, paramString2, paramCallbackContext);
      return true;
    }
    if (paramString1.equals("readAsText"))
    {
      threadhelper(new FileOp()
      {
        public void run(JSONArray paramAnonymousJSONArray)
          throws JSONException, MalformedURLException
        {
          String str = paramAnonymousJSONArray.getString(1);
          int i = paramAnonymousJSONArray.getInt(2);
          int j = paramAnonymousJSONArray.getInt(3);
          paramAnonymousJSONArray = paramAnonymousJSONArray.getString(0);
          FileUtils.this.readFileAs(paramAnonymousJSONArray, i, j, paramCallbackContext, str, 1);
        }
      }, paramString2, paramCallbackContext);
      return true;
    }
    if (paramString1.equals("readAsDataURL"))
    {
      threadhelper(new FileOp()
      {
        public void run(JSONArray paramAnonymousJSONArray)
          throws JSONException, MalformedURLException
        {
          int i = paramAnonymousJSONArray.getInt(1);
          int j = paramAnonymousJSONArray.getInt(2);
          paramAnonymousJSONArray = paramAnonymousJSONArray.getString(0);
          FileUtils.this.readFileAs(paramAnonymousJSONArray, i, j, paramCallbackContext, null, -1);
        }
      }, paramString2, paramCallbackContext);
      return true;
    }
    if (paramString1.equals("readAsArrayBuffer"))
    {
      threadhelper(new FileOp()
      {
        public void run(JSONArray paramAnonymousJSONArray)
          throws JSONException, MalformedURLException
        {
          int i = paramAnonymousJSONArray.getInt(1);
          int j = paramAnonymousJSONArray.getInt(2);
          paramAnonymousJSONArray = paramAnonymousJSONArray.getString(0);
          FileUtils.this.readFileAs(paramAnonymousJSONArray, i, j, paramCallbackContext, null, 6);
        }
      }, paramString2, paramCallbackContext);
      return true;
    }
    if (paramString1.equals("readAsBinaryString"))
    {
      threadhelper(new FileOp()
      {
        public void run(JSONArray paramAnonymousJSONArray)
          throws JSONException, MalformedURLException
        {
          int i = paramAnonymousJSONArray.getInt(1);
          int j = paramAnonymousJSONArray.getInt(2);
          paramAnonymousJSONArray = paramAnonymousJSONArray.getString(0);
          FileUtils.this.readFileAs(paramAnonymousJSONArray, i, j, paramCallbackContext, null, 7);
        }
      }, paramString2, paramCallbackContext);
      return true;
    }
    if (paramString1.equals("write"))
    {
      threadhelper(new FileOp()
      {
        public void run(JSONArray paramAnonymousJSONArray)
          throws JSONException, FileNotFoundException, IOException, NoModificationAllowedException
        {
          String str1 = paramAnonymousJSONArray.getString(0);
          String str2 = paramAnonymousJSONArray.getString(1);
          int i = paramAnonymousJSONArray.getInt(2);
          boolean bool = paramAnonymousJSONArray.getBoolean(3);
          if ((str1.contains(FileUtils.this.cordova.getActivity().getPackageName())) || (FileUtils.this.hasReadPermission()))
          {
            long l = FileUtils.this.write(str1, str2, i, Boolean.valueOf(bool).booleanValue());
            paramCallbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, (float)l));
            return;
          }
          FileUtils.this.getWritePermission();
        }
      }, paramString2, paramCallbackContext);
      return true;
    }
    if (paramString1.equals("truncate"))
    {
      threadhelper(new FileOp()
      {
        public void run(JSONArray paramAnonymousJSONArray)
          throws JSONException, FileNotFoundException, IOException, NoModificationAllowedException
        {
          String str = paramAnonymousJSONArray.getString(0);
          int i = paramAnonymousJSONArray.getInt(1);
          long l = FileUtils.this.truncateFile(str, i);
          paramCallbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, (float)l));
        }
      }, paramString2, paramCallbackContext);
      return true;
    }
    if (paramString1.equals("requestAllFileSystems"))
    {
      threadhelper(new FileOp()
      {
        public void run(JSONArray paramAnonymousJSONArray)
          throws IOException, JSONException
        {
          paramCallbackContext.success(FileUtils.this.requestAllFileSystems());
        }
      }, paramString2, paramCallbackContext);
      return true;
    }
    if (paramString1.equals("requestAllPaths"))
    {
      this.cordova.getThreadPool().execute(new Runnable()
      {
        public void run()
        {
          try
          {
            paramCallbackContext.success(FileUtils.this.requestAllPaths());
            return;
          }
          catch (JSONException localJSONException)
          {
            localJSONException.printStackTrace();
          }
        }
      });
      return true;
    }
    if (paramString1.equals("requestFileSystem"))
    {
      threadhelper(new FileOp()
      {
        public void run(JSONArray paramAnonymousJSONArray)
          throws JSONException
        {
          int i = paramAnonymousJSONArray.getInt(0);
          long l = paramAnonymousJSONArray.optLong(1);
          FileUtils.this.requestFileSystem(i, l, paramCallbackContext);
        }
      }, paramString2, paramCallbackContext);
      return true;
    }
    if (paramString1.equals("resolveLocalFileSystemURI"))
    {
      threadhelper(new FileOp()
      {
        public void run(JSONArray paramAnonymousJSONArray)
          throws IOException, JSONException
        {
          paramAnonymousJSONArray = paramAnonymousJSONArray.getString(0);
          paramAnonymousJSONArray = FileUtils.this.resolveLocalFileSystemURI(paramAnonymousJSONArray);
          paramCallbackContext.success(paramAnonymousJSONArray);
        }
      }, paramString2, paramCallbackContext);
      return true;
    }
    if (paramString1.equals("getFileMetadata"))
    {
      threadhelper(new FileOp()
      {
        public void run(JSONArray paramAnonymousJSONArray)
          throws FileNotFoundException, JSONException, MalformedURLException
        {
          paramAnonymousJSONArray = paramAnonymousJSONArray.getString(0);
          paramAnonymousJSONArray = FileUtils.this.getFileMetadata(paramAnonymousJSONArray);
          paramCallbackContext.success(paramAnonymousJSONArray);
        }
      }, paramString2, paramCallbackContext);
      return true;
    }
    if (paramString1.equals("getParent"))
    {
      threadhelper(new FileOp()
      {
        public void run(JSONArray paramAnonymousJSONArray)
          throws JSONException, IOException
        {
          paramAnonymousJSONArray = paramAnonymousJSONArray.getString(0);
          paramAnonymousJSONArray = FileUtils.this.getParent(paramAnonymousJSONArray);
          paramCallbackContext.success(paramAnonymousJSONArray);
        }
      }, paramString2, paramCallbackContext);
      return true;
    }
    if (paramString1.equals("getDirectory"))
    {
      threadhelper(new FileOp()
      {
        public void run(JSONArray paramAnonymousJSONArray)
          throws FileExistsException, IOException, TypeMismatchException, EncodingException, JSONException
        {
          String str1 = paramAnonymousJSONArray.getString(0);
          String str2 = paramAnonymousJSONArray.getString(1);
          paramAnonymousJSONArray = FileUtils.this.getFile(str1, str2, paramAnonymousJSONArray.optJSONObject(2), true);
          paramCallbackContext.success(paramAnonymousJSONArray);
        }
      }, paramString2, paramCallbackContext);
      return true;
    }
    if (paramString1.equals("getFile"))
    {
      threadhelper(new FileOp()
      {
        public void run(JSONArray paramAnonymousJSONArray)
          throws FileExistsException, IOException, TypeMismatchException, EncodingException, JSONException
        {
          String str1 = paramAnonymousJSONArray.getString(0);
          if ((str1.contains(FileUtils.this.cordova.getActivity().getPackageName())) || (FileUtils.this.hasReadPermission()))
          {
            String str2 = paramAnonymousJSONArray.getString(1);
            paramAnonymousJSONArray = FileUtils.this.getFile(str1, str2, paramAnonymousJSONArray.optJSONObject(2), false);
            paramCallbackContext.success(paramAnonymousJSONArray);
            return;
          }
          FileUtils.this.getReadPermission();
        }
      }, paramString2, paramCallbackContext);
      return true;
    }
    if (paramString1.equals("remove"))
    {
      threadhelper(new FileOp()
      {
        public void run(JSONArray paramAnonymousJSONArray)
          throws JSONException, NoModificationAllowedException, InvalidModificationException, MalformedURLException
        {
          paramAnonymousJSONArray = paramAnonymousJSONArray.getString(0);
          if (FileUtils.this.remove(paramAnonymousJSONArray))
          {
            paramCallbackContext.success();
            return;
          }
          paramCallbackContext.error(FileUtils.NO_MODIFICATION_ALLOWED_ERR);
        }
      }, paramString2, paramCallbackContext);
      return true;
    }
    if (paramString1.equals("removeRecursively"))
    {
      threadhelper(new FileOp()
      {
        public void run(JSONArray paramAnonymousJSONArray)
          throws JSONException, FileExistsException, MalformedURLException, NoModificationAllowedException
        {
          paramAnonymousJSONArray = paramAnonymousJSONArray.getString(0);
          if (FileUtils.this.removeRecursively(paramAnonymousJSONArray))
          {
            paramCallbackContext.success();
            return;
          }
          paramCallbackContext.error(FileUtils.NO_MODIFICATION_ALLOWED_ERR);
        }
      }, paramString2, paramCallbackContext);
      return true;
    }
    if (paramString1.equals("moveTo"))
    {
      threadhelper(new FileOp()
      {
        public void run(JSONArray paramAnonymousJSONArray)
          throws JSONException, NoModificationAllowedException, IOException, InvalidModificationException, EncodingException, FileExistsException
        {
          String str1 = paramAnonymousJSONArray.getString(0);
          String str2 = paramAnonymousJSONArray.getString(1);
          paramAnonymousJSONArray = paramAnonymousJSONArray.getString(2);
          paramAnonymousJSONArray = FileUtils.this.transferTo(str1, str2, paramAnonymousJSONArray, true);
          paramCallbackContext.success(paramAnonymousJSONArray);
        }
      }, paramString2, paramCallbackContext);
      return true;
    }
    if (paramString1.equals("copyTo"))
    {
      threadhelper(new FileOp()
      {
        public void run(JSONArray paramAnonymousJSONArray)
          throws JSONException, NoModificationAllowedException, IOException, InvalidModificationException, EncodingException, FileExistsException
        {
          String str1 = paramAnonymousJSONArray.getString(0);
          String str2 = paramAnonymousJSONArray.getString(1);
          paramAnonymousJSONArray = paramAnonymousJSONArray.getString(2);
          paramAnonymousJSONArray = FileUtils.this.transferTo(str1, str2, paramAnonymousJSONArray, false);
          paramCallbackContext.success(paramAnonymousJSONArray);
        }
      }, paramString2, paramCallbackContext);
      return true;
    }
    if (paramString1.equals("readEntries"))
    {
      threadhelper(new FileOp()
      {
        public void run(JSONArray paramAnonymousJSONArray)
          throws FileNotFoundException, JSONException, MalformedURLException
        {
          paramAnonymousJSONArray = paramAnonymousJSONArray.getString(0);
          paramAnonymousJSONArray = FileUtils.this.readEntries(paramAnonymousJSONArray);
          paramCallbackContext.success(paramAnonymousJSONArray);
        }
      }, paramString2, paramCallbackContext);
      return true;
    }
    if (paramString1.equals("_getLocalFilesystemPath"))
    {
      threadhelper(new FileOp()
      {
        public void run(JSONArray paramAnonymousJSONArray)
          throws FileNotFoundException, JSONException, MalformedURLException
        {
          paramAnonymousJSONArray = paramAnonymousJSONArray.getString(0);
          paramAnonymousJSONArray = FileUtils.this.filesystemPathForURL(paramAnonymousJSONArray);
          paramCallbackContext.success(paramAnonymousJSONArray);
        }
      }, paramString2, paramCallbackContext);
      return true;
    }
    return false;
  }
  
  public String filesystemPathForURL(String paramString)
    throws MalformedURLException
  {
    Filesystem localFilesystem;
    try
    {
      paramString = LocalFilesystemURL.parse(paramString);
      localFilesystem = filesystemForURL(paramString);
      if (localFilesystem == null) {
        throw new MalformedURLException("No installed handlers for this URL");
      }
    }
    catch (IllegalArgumentException paramString)
    {
      throw new MalformedURLException("Unrecognized filesystem URL");
    }
    paramString = localFilesystem.filesystemPathForURL(paramString);
    return paramString;
  }
  
  public LocalFilesystemURL filesystemURLforLocalPath(String paramString)
  {
    Object localObject = null;
    int i = 0;
    Iterator localIterator = this.filesystems.iterator();
    while (localIterator.hasNext())
    {
      LocalFilesystemURL localLocalFilesystemURL = ((Filesystem)localIterator.next()).URLforFilesystemPath(paramString);
      if ((localLocalFilesystemURL != null) && ((localObject == null) || (localLocalFilesystemURL.path.length() < i)))
      {
        localObject = localLocalFilesystemURL;
        i = localLocalFilesystemURL.path.length();
      }
    }
    return (LocalFilesystemURL)localObject;
  }
  
  protected HashMap<String, String> getAvailableFileSystems(Activity paramActivity)
  {
    Context localContext = paramActivity.getApplicationContext();
    paramActivity = new HashMap();
    paramActivity.put("files", localContext.getFilesDir().getAbsolutePath());
    paramActivity.put("documents", new File(localContext.getFilesDir(), "Documents").getAbsolutePath());
    paramActivity.put("cache", localContext.getCacheDir().getAbsolutePath());
    paramActivity.put("root", "/");
    if (Environment.getExternalStorageState().equals("mounted")) {}
    try
    {
      paramActivity.put("files-external", localContext.getExternalFilesDir(null).getAbsolutePath());
      paramActivity.put("sdcard", Environment.getExternalStorageDirectory().getAbsolutePath());
      paramActivity.put("cache-external", localContext.getExternalCacheDir().getAbsolutePath());
      return paramActivity;
    }
    catch (NullPointerException localNullPointerException)
    {
      Log.d("FileUtils", "External storage unavailable, check to see if USB Mass Storage Mode is on");
    }
    return paramActivity;
  }
  
  public JSONObject getEntryForFile(File paramFile)
    throws JSONException
  {
    Iterator localIterator = this.filesystems.iterator();
    while (localIterator.hasNext())
    {
      JSONObject localJSONObject = ((Filesystem)localIterator.next()).makeEntryForFile(paramFile);
      if (localJSONObject != null) {
        return localJSONObject;
      }
    }
    return null;
  }
  
  protected String[] getExtraFileSystemsPreference(Activity paramActivity)
  {
    return this.preferences.getString("androidextrafilesystems", "files,files-external,documents,sdcard,cache,cache-external,root").split(",");
  }
  
  public void initialize(CordovaInterface paramCordovaInterface, CordovaWebView paramCordovaWebView)
  {
    super.initialize(paramCordovaInterface, paramCordovaWebView);
    this.filesystems = new ArrayList();
    Object localObject2 = null;
    Activity localActivity = paramCordovaInterface.getActivity();
    String str1 = localActivity.getPackageName();
    String str2 = this.preferences.getString("androidpersistentfilelocation", "internal");
    Object localObject1 = localActivity.getCacheDir().getAbsolutePath();
    if ("internal".equalsIgnoreCase(str2))
    {
      paramCordovaInterface = localActivity.getFilesDir().getAbsolutePath() + "/files/";
      this.configured = true;
      localObject2 = localObject1;
    }
    while (this.configured)
    {
      localObject1 = new File((String)localObject2);
      paramCordovaInterface = new File(paramCordovaInterface);
      ((File)localObject1).mkdirs();
      paramCordovaInterface.mkdirs();
      registerFilesystem(new LocalFilesystem("temporary", paramCordovaWebView.getContext(), paramCordovaWebView.getResourceApi(), (File)localObject1));
      registerFilesystem(new LocalFilesystem("persistent", paramCordovaWebView.getContext(), paramCordovaWebView.getResourceApi(), paramCordovaInterface));
      registerFilesystem(new ContentFilesystem(paramCordovaWebView.getContext(), paramCordovaWebView.getResourceApi()));
      registerFilesystem(new AssetFilesystem(paramCordovaWebView.getContext().getAssets(), paramCordovaWebView.getResourceApi()));
      registerExtraFileSystems(getExtraFileSystemsPreference(localActivity), getAvailableFileSystems(localActivity));
      if (filePlugin == null) {
        filePlugin = this;
      }
      return;
      paramCordovaInterface = (CordovaInterface)localObject2;
      localObject2 = localObject1;
      if ("compatibility".equalsIgnoreCase(str2))
      {
        if (Environment.getExternalStorageState().equals("mounted"))
        {
          paramCordovaInterface = Environment.getExternalStorageDirectory().getAbsolutePath();
          localObject1 = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/data/" + str1 + "/cache/";
        }
        for (;;)
        {
          this.configured = true;
          localObject2 = localObject1;
          break;
          paramCordovaInterface = "/data/data/" + str1;
        }
      }
    }
    Log.e("FileUtils", "File plugin configuration error: Please set AndroidPersistentFileLocation in config.xml to one of \"internal\" (for new applications) or \"compatibility\" (for compatibility with previous versions)");
    localActivity.finish();
  }
  
  public void onRequestPermissionResult(int paramInt, String[] paramArrayOfString, int[] paramArrayOfInt)
    throws JSONException
  {
    int j = paramArrayOfInt.length;
    int i = 0;
    while (i < j)
    {
      if (paramArrayOfInt[i] == -1) {
        this.callback.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, SECURITY_ERR));
      }
      i += 1;
    }
    switch (paramInt)
    {
    default: 
      return;
    case 0: 
      threadhelper(new FileOp()
      {
        public void run(JSONArray paramAnonymousJSONArray)
          throws FileExistsException, IOException, TypeMismatchException, EncodingException, JSONException
        {
          String str1 = paramAnonymousJSONArray.getString(0);
          String str2 = paramAnonymousJSONArray.getString(1);
          paramAnonymousJSONArray = FileUtils.this.getFile(str1, str2, paramAnonymousJSONArray.optJSONObject(2), false);
          FileUtils.this.callback.success(paramAnonymousJSONArray);
        }
      }, this.lastRawArgs, this.callback);
      return;
    }
    threadhelper(new FileOp()
    {
      public void run(JSONArray paramAnonymousJSONArray)
        throws JSONException, FileNotFoundException, IOException, NoModificationAllowedException
      {
        String str1 = paramAnonymousJSONArray.getString(0);
        String str2 = paramAnonymousJSONArray.getString(1);
        int i = paramAnonymousJSONArray.getInt(2);
        boolean bool = paramAnonymousJSONArray.getBoolean(3);
        long l = FileUtils.this.write(str1, str2, i, Boolean.valueOf(bool).booleanValue());
        FileUtils.this.callback.sendPluginResult(new PluginResult(PluginResult.Status.OK, (float)l));
      }
    }, this.lastRawArgs, this.callback);
  }
  
  public void readFileAs(String paramString1, int paramInt1, int paramInt2, final CallbackContext paramCallbackContext, final String paramString2, final int paramInt3)
    throws MalformedURLException
  {
    try
    {
      paramString1 = LocalFilesystemURL.parse(paramString1);
      localFilesystem = filesystemForURL(paramString1);
      if (localFilesystem == null) {
        throw new MalformedURLException("No installed handlers for this URL");
      }
    }
    catch (IllegalArgumentException paramString1)
    {
      Filesystem localFilesystem;
      throw new MalformedURLException("Unrecognized filesystem URL");
      long l1 = paramInt1;
      long l2 = paramInt2;
      localFilesystem.readFileAtURL(paramString1, l1, l2, new Filesystem.ReadFileCallback()
      {
        public void handleData(InputStream paramAnonymousInputStream, String paramAnonymousString)
        {
          for (;;)
          {
            ByteArrayOutputStream localByteArrayOutputStream;
            try
            {
              localByteArrayOutputStream = new ByteArrayOutputStream();
              byte[] arrayOfByte = new byte[' '];
              int i = paramAnonymousInputStream.read(arrayOfByte, 0, 8192);
              if (i <= 0) {}
              switch (paramInt3)
              {
              case 1: 
                paramAnonymousInputStream = Base64.encode(localByteArrayOutputStream.toByteArray(), 2);
                paramAnonymousInputStream = "data:" + paramAnonymousString + ";base64," + new String(paramAnonymousInputStream, "US-ASCII");
                paramAnonymousInputStream = new PluginResult(PluginResult.Status.OK, paramAnonymousInputStream);
                paramCallbackContext.sendPluginResult(paramAnonymousInputStream);
                return;
                localByteArrayOutputStream.write(arrayOfByte, 0, i);
                continue;
                paramAnonymousInputStream = new PluginResult(PluginResult.Status.OK, localByteArrayOutputStream.toString(paramString2));
              }
            }
            catch (IOException paramAnonymousInputStream)
            {
              Log.d("FileUtils", paramAnonymousInputStream.getLocalizedMessage());
              paramCallbackContext.sendPluginResult(new PluginResult(PluginResult.Status.IO_EXCEPTION, FileUtils.NOT_READABLE_ERR));
              return;
            }
            continue;
            paramAnonymousInputStream = new PluginResult(PluginResult.Status.OK, localByteArrayOutputStream.toByteArray());
            continue;
            paramAnonymousInputStream = new PluginResult(PluginResult.Status.OK, localByteArrayOutputStream.toByteArray(), true);
          }
        }
      });
      return;
    }
    catch (FileNotFoundException paramString1)
    {
      paramCallbackContext.sendPluginResult(new PluginResult(PluginResult.Status.IO_EXCEPTION, NOT_FOUND_ERR));
      return;
    }
    catch (IOException paramString1)
    {
      Log.d("FileUtils", paramString1.getLocalizedMessage());
      paramCallbackContext.sendPluginResult(new PluginResult(PluginResult.Status.IO_EXCEPTION, NOT_READABLE_ERR));
    }
  }
  
  protected void registerExtraFileSystems(String[] paramArrayOfString, HashMap<String, String> paramHashMap)
  {
    HashSet localHashSet = new HashSet();
    int j = paramArrayOfString.length;
    int i = 0;
    if (i < j)
    {
      String str = paramArrayOfString[i];
      if (!localHashSet.contains(str))
      {
        Object localObject = (String)paramHashMap.get(str);
        if (localObject == null) {
          break label163;
        }
        localObject = new File((String)localObject);
        if ((!((File)localObject).mkdirs()) && (!((File)localObject).isDirectory())) {
          break label127;
        }
        registerFilesystem(new LocalFilesystem(str, this.webView.getContext(), this.webView.getResourceApi(), (File)localObject));
        localHashSet.add(str);
      }
      for (;;)
      {
        i += 1;
        break;
        label127:
        Log.d("FileUtils", "Unable to create root dir for filesystem \"" + str + "\", skipping");
        continue;
        label163:
        Log.d("FileUtils", "Unrecognized extra filesystem identifier: " + str);
      }
    }
  }
  
  public void registerFilesystem(Filesystem paramFilesystem)
  {
    if ((paramFilesystem != null) && (filesystemForName(paramFilesystem.name) == null)) {
      this.filesystems.add(paramFilesystem);
    }
  }
  
  public Uri remapUri(Uri paramUri)
  {
    if (!"cdvfile".equals(paramUri.getScheme())) {}
    for (;;)
    {
      return null;
      try
      {
        paramUri = LocalFilesystemURL.parse(paramUri);
        Filesystem localFilesystem = filesystemForURL(paramUri);
        if ((localFilesystem != null) && (localFilesystem.filesystemPathForURL(paramUri) != null))
        {
          paramUri = Uri.parse("file://" + localFilesystem.filesystemPathForURL(paramUri));
          return paramUri;
        }
      }
      catch (IllegalArgumentException paramUri) {}
    }
    return null;
  }
  
  public LocalFilesystemURL resolveNativeUri(Uri paramUri)
  {
    Object localObject = null;
    Iterator localIterator = this.filesystems.iterator();
    while (localIterator.hasNext())
    {
      LocalFilesystemURL localLocalFilesystemURL = ((Filesystem)localIterator.next()).toLocalUri(paramUri);
      if ((localLocalFilesystemURL != null) && ((localObject == null) || (localLocalFilesystemURL.uri.toString().length() < ((LocalFilesystemURL)localObject).toString().length()))) {
        localObject = localLocalFilesystemURL;
      }
    }
    return (LocalFilesystemURL)localObject;
  }
  
  public long write(String paramString1, String paramString2, int paramInt, boolean paramBoolean)
    throws FileNotFoundException, IOException, NoModificationAllowedException
  {
    LocalFilesystemURL localLocalFilesystemURL;
    Filesystem localFilesystem;
    try
    {
      localLocalFilesystemURL = LocalFilesystemURL.parse(paramString1);
      localFilesystem = filesystemForURL(localLocalFilesystemURL);
      if (localFilesystem == null) {
        throw new MalformedURLException("No installed handlers for this URL");
      }
    }
    catch (IllegalArgumentException paramString1)
    {
      throw new MalformedURLException("Unrecognized filesystem URL");
    }
    long l = localFilesystem.writeToFileAtURL(localLocalFilesystemURL, paramString2, paramInt, paramBoolean);
    Log.d("TEST", paramString1 + ": " + l);
    return l;
  }
  
  private static abstract interface FileOp
  {
    public abstract void run(JSONArray paramJSONArray)
      throws Exception;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\cordova\file\FileUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */