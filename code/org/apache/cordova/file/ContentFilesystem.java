package org.apache.cordova.file;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.net.Uri.Builder;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.cordova.CordovaResourceApi;
import org.apache.cordova.CordovaResourceApi.OpenForReadResult;
import org.json.JSONException;
import org.json.JSONObject;

public class ContentFilesystem
  extends Filesystem
{
  private final Context context;
  
  public ContentFilesystem(Context paramContext, CordovaResourceApi paramCordovaResourceApi)
  {
    super(Uri.parse("content://"), "content", paramCordovaResourceApi);
    this.context = paramContext;
  }
  
  private Long resourceSizeForCursor(Cursor paramCursor)
  {
    int i = paramCursor.getColumnIndex("_size");
    if (i != -1)
    {
      paramCursor = paramCursor.getString(i);
      if (paramCursor != null) {
        return Long.valueOf(Long.parseLong(paramCursor));
      }
    }
    return null;
  }
  
  public LocalFilesystemURL URLforFilesystemPath(String paramString)
  {
    return null;
  }
  
  public boolean canRemoveFileAtLocalURL(LocalFilesystemURL paramLocalFilesystemURL)
  {
    return true;
  }
  
  public String filesystemPathForURL(LocalFilesystemURL paramLocalFilesystemURL)
  {
    paramLocalFilesystemURL = this.resourceApi.mapUriToFile(toNativeUri(paramLocalFilesystemURL));
    if (paramLocalFilesystemURL == null) {
      return null;
    }
    return paramLocalFilesystemURL.getAbsolutePath();
  }
  
  public JSONObject getFileForLocalURL(LocalFilesystemURL paramLocalFilesystemURL, String paramString, JSONObject paramJSONObject, boolean paramBoolean)
    throws IOException, TypeMismatchException, JSONException
  {
    throw new UnsupportedOperationException("getFile() not supported for content:. Use resolveLocalFileSystemURL instead.");
  }
  
  public JSONObject getFileMetadataForLocalURL(LocalFilesystemURL paramLocalFilesystemURL)
    throws FileNotFoundException
  {
    long l1 = 0L;
    Object localObject2 = toNativeUri(paramLocalFilesystemURL);
    String str = this.resourceApi.getMimeType((Uri)localObject2);
    Object localObject1 = openCursorForURL((Uri)localObject2);
    if (localObject1 != null) {}
    for (;;)
    {
      try
      {
        if (((Cursor)localObject1).moveToFirst())
        {
          long l3 = resourceSizeForCursor((Cursor)localObject1).longValue();
          localObject2 = lastModifiedDateForCursor((Cursor)localObject1);
          l2 = l3;
          if (localObject2 != null)
          {
            l1 = ((Long)localObject2).longValue();
            l2 = l3;
          }
          if (localObject1 != null) {
            ((Cursor)localObject1).close();
          }
          localObject1 = new JSONObject();
        }
      }
      catch (IOException paramLocalFilesystemURL)
      {
        long l2;
        throw new FileNotFoundException();
      }
      finally
      {
        if (localObject1 != null) {
          ((Cursor)localObject1).close();
        }
      }
      try
      {
        ((JSONObject)localObject1).put("size", l2);
        ((JSONObject)localObject1).put("type", str);
        ((JSONObject)localObject1).put("name", this.name);
        ((JSONObject)localObject1).put("fullPath", paramLocalFilesystemURL.path);
        ((JSONObject)localObject1).put("lastModifiedDate", l1);
        return (JSONObject)localObject1;
      }
      catch (JSONException paramLocalFilesystemURL) {}
      l2 = this.resourceApi.openForRead((Uri)localObject2).length;
    }
    return null;
  }
  
  protected Long lastModifiedDateForCursor(Cursor paramCursor)
  {
    int j = paramCursor.getColumnIndex("date_modified");
    int i = j;
    if (j == -1) {
      i = paramCursor.getColumnIndex("last_modified");
    }
    if (i != -1)
    {
      paramCursor = paramCursor.getString(i);
      if (paramCursor != null) {
        return Long.valueOf(Long.parseLong(paramCursor));
      }
    }
    return null;
  }
  
  public LocalFilesystemURL[] listChildren(LocalFilesystemURL paramLocalFilesystemURL)
    throws FileNotFoundException
  {
    throw new UnsupportedOperationException("readEntriesAtLocalURL() not supported for content:. Use resolveLocalFileSystemURL instead.");
  }
  
  protected Cursor openCursorForURL(Uri paramUri)
  {
    ContentResolver localContentResolver = this.context.getContentResolver();
    try
    {
      paramUri = localContentResolver.query(paramUri, null, null, null, null);
      return paramUri;
    }
    catch (UnsupportedOperationException paramUri) {}
    return null;
  }
  
  public boolean recursiveRemoveFileAtLocalURL(LocalFilesystemURL paramLocalFilesystemURL)
    throws NoModificationAllowedException
  {
    throw new NoModificationAllowedException("Cannot remove content url");
  }
  
  public boolean removeFileAtLocalURL(LocalFilesystemURL paramLocalFilesystemURL)
    throws NoModificationAllowedException
  {
    paramLocalFilesystemURL = toNativeUri(paramLocalFilesystemURL);
    try
    {
      this.context.getContentResolver().delete(paramLocalFilesystemURL, null, null);
      return true;
    }
    catch (UnsupportedOperationException localUnsupportedOperationException)
    {
      throw new NoModificationAllowedException("Deleting not supported for content uri: " + paramLocalFilesystemURL);
    }
  }
  
  public LocalFilesystemURL toLocalUri(Uri paramUri)
  {
    if (!"content".equals(paramUri.getScheme())) {
      return null;
    }
    Object localObject2 = paramUri.getEncodedPath();
    Object localObject1 = localObject2;
    if (((String)localObject2).length() > 0) {
      localObject1 = ((String)localObject2).substring(1);
    }
    localObject2 = new Uri.Builder().scheme("cdvfile").authority("localhost").path(this.name).appendPath(paramUri.getAuthority());
    if (((String)localObject1).length() > 0) {
      ((Uri.Builder)localObject2).appendEncodedPath((String)localObject1);
    }
    return LocalFilesystemURL.parse(((Uri.Builder)localObject2).encodedQuery(paramUri.getEncodedQuery()).encodedFragment(paramUri.getEncodedFragment()).build());
  }
  
  public Uri toNativeUri(LocalFilesystemURL paramLocalFilesystemURL)
  {
    Object localObject = paramLocalFilesystemURL.uri.getEncodedPath().substring(this.name.length() + 2);
    if (((String)localObject).length() < 2) {
      return null;
    }
    String str1 = "content://" + (String)localObject;
    String str2 = paramLocalFilesystemURL.uri.getEncodedQuery();
    localObject = str1;
    if (str2 != null) {
      localObject = str1 + '?' + str2;
    }
    str1 = paramLocalFilesystemURL.uri.getEncodedFragment();
    paramLocalFilesystemURL = (LocalFilesystemURL)localObject;
    if (str1 != null) {
      paramLocalFilesystemURL = (String)localObject + '#' + str1;
    }
    return Uri.parse(paramLocalFilesystemURL);
  }
  
  public long truncateFileAtURL(LocalFilesystemURL paramLocalFilesystemURL, long paramLong)
    throws NoModificationAllowedException
  {
    throw new NoModificationAllowedException("Couldn't truncate file given its content URI");
  }
  
  public long writeToFileAtURL(LocalFilesystemURL paramLocalFilesystemURL, String paramString, int paramInt, boolean paramBoolean)
    throws NoModificationAllowedException
  {
    throw new NoModificationAllowedException("Couldn't write to file given its content URI");
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\cordova\file\ContentFilesystem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */