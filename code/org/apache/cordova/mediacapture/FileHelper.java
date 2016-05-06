package org.apache.cordova.mediacapture;

import android.app.Activity;
import android.content.ContentResolver;
import android.net.Uri;
import android.webkit.MimeTypeMap;
import java.util.Locale;
import org.apache.cordova.CordovaInterface;

public class FileHelper
{
  public static String getMimeType(Uri paramUri, CordovaInterface paramCordovaInterface)
  {
    if ("content".equals(paramUri.getScheme())) {
      return paramCordovaInterface.getActivity().getContentResolver().getType(paramUri);
    }
    return getMimeTypeForExtension(paramUri.getPath());
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
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\cordova\mediacapture\FileHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */