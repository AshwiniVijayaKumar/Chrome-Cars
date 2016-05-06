package org.apache.cordova.camera;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.CursorLoader;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build.VERSION;
import android.provider.DocumentsContract;
import android.provider.MediaStore.Images.Media;
import android.webkit.MimeTypeMap;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import org.apache.cordova.CordovaInterface;

public class FileHelper
{
  private static final String LOG_TAG = "FileUtils";
  private static final String _DATA = "_data";
  
  public static InputStream getInputStreamFromUriString(String paramString, CordovaInterface paramCordovaInterface)
    throws IOException
  {
    Object localObject;
    if (paramString.startsWith("content"))
    {
      paramString = Uri.parse(paramString);
      localObject = paramCordovaInterface.getActivity().getContentResolver().openInputStream(paramString);
    }
    for (;;)
    {
      return (InputStream)localObject;
      if (paramString.startsWith("file://"))
      {
        int i = paramString.indexOf("?");
        String str = paramString;
        if (i > -1) {
          str = paramString.substring(0, i);
        }
        if (str.startsWith("file:///android_asset/"))
        {
          paramString = Uri.parse(str).getPath().substring(15);
          return paramCordovaInterface.getActivity().getAssets().open(paramString);
        }
        try
        {
          paramString = paramCordovaInterface.getActivity().getContentResolver().openInputStream(Uri.parse(str));
          localObject = paramString;
          if (paramString == null) {
            return new FileInputStream(getRealPath(str, paramCordovaInterface));
          }
        }
        catch (Exception paramString)
        {
          for (;;)
          {
            paramString = null;
          }
        }
      }
    }
    return new FileInputStream(paramString);
  }
  
  public static String getMimeType(String paramString, CordovaInterface paramCordovaInterface)
  {
    Uri localUri = Uri.parse(paramString);
    if (paramString.startsWith("content://")) {
      return paramCordovaInterface.getActivity().getContentResolver().getType(localUri);
    }
    return getMimeTypeForExtension(localUri.getPath());
  }
  
  public static String getMimeTypeForExtension(String paramString)
  {
    int i = paramString.lastIndexOf('.');
    String str = paramString;
    if (i != -1) {
      str = paramString.substring(i + 1);
    }
    paramString = str.toLowerCase(Locale.getDefault());
    if (paramString.equals("3ga")) {
      return "audio/3gpp";
    }
    return MimeTypeMap.getSingleton().getMimeTypeFromExtension(paramString);
  }
  
  public static String getRealPath(Uri paramUri, CordovaInterface paramCordovaInterface)
  {
    if (Build.VERSION.SDK_INT < 11) {
      return getRealPathFromURI_BelowAPI11(paramCordovaInterface.getActivity(), paramUri);
    }
    if (Build.VERSION.SDK_INT < 19) {
      return getRealPathFromURI_API11to18(paramCordovaInterface.getActivity(), paramUri);
    }
    return getRealPathFromURI_API19(paramCordovaInterface.getActivity(), paramUri);
  }
  
  public static String getRealPath(String paramString, CordovaInterface paramCordovaInterface)
  {
    return getRealPath(Uri.parse(paramString), paramCordovaInterface);
  }
  
  @SuppressLint({"NewApi"})
  public static String getRealPathFromURI_API11to18(Context paramContext, Uri paramUri)
  {
    Object localObject = null;
    try
    {
      paramUri = new CursorLoader(paramContext, paramUri, new String[] { "_data" }, null, null, null).loadInBackground();
      paramContext = (Context)localObject;
      if (paramUri != null)
      {
        int i = paramUri.getColumnIndexOrThrow("_data");
        paramUri.moveToFirst();
        paramContext = paramUri.getString(i);
      }
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  @SuppressLint({"NewApi"})
  public static String getRealPathFromURI_API19(Context paramContext, Uri paramUri)
  {
    String str = "";
    try
    {
      paramUri = DocumentsContract.getDocumentId(paramUri);
      if (paramUri.indexOf(":") > -1) {
        paramUri = paramUri.split(":")[1];
      }
      for (;;)
      {
        String[] arrayOfString = new String[1];
        arrayOfString[0] = "_data";
        paramUri = paramContext.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, arrayOfString, "_id=?", new String[] { paramUri }, null);
        int i = paramUri.getColumnIndex(arrayOfString[0]);
        paramContext = str;
        if (paramUri.moveToFirst()) {
          paramContext = paramUri.getString(i);
        }
        paramUri.close();
        return paramContext;
        if (paramUri.indexOf(";") > -1) {
          paramUri = paramUri.split(";")[1];
        }
      }
      return "";
    }
    catch (Exception paramContext) {}
  }
  
  public static String getRealPathFromURI_BelowAPI11(Context paramContext, Uri paramUri)
  {
    try
    {
      paramContext = paramContext.getContentResolver().query(paramUri, new String[] { "_data" }, null, null, null);
      int i = paramContext.getColumnIndexOrThrow("_data");
      paramContext.moveToFirst();
      paramContext = paramContext.getString(i);
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  public static String stripFileProtocol(String paramString)
  {
    String str = paramString;
    if (paramString.startsWith("file://")) {
      str = paramString.substring(7);
    }
    return str;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\cordova\camera\FileHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */