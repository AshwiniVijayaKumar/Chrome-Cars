package org.vudroid.core.utils;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

public class PathFromUri
{
  public static String retrieve(ContentResolver paramContentResolver, Uri paramUri)
  {
    if (paramUri.getScheme().equals("file")) {
      return paramUri.getPath();
    }
    paramContentResolver = paramContentResolver.query(paramUri, new String[] { "_data" }, null, null, null);
    if (paramContentResolver.moveToFirst()) {
      return paramContentResolver.getString(0);
    }
    throw new RuntimeException("Can't retrieve path from uri: " + paramUri.toString());
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\vudroid\core\utils\PathFromUri.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */