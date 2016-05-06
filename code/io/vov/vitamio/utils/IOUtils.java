package io.vov.vitamio.utils;

import android.database.Cursor;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import java.io.Closeable;

public class IOUtils
{
  private static final String TAG = "IOUtils";
  
  public static void closeSilently(Cursor paramCursor)
  {
    if (paramCursor != null) {}
    try
    {
      paramCursor.close();
      return;
    }
    catch (Throwable paramCursor)
    {
      Log.w("IOUtils", "fail to close", paramCursor);
    }
  }
  
  public static void closeSilently(ParcelFileDescriptor paramParcelFileDescriptor)
  {
    if (paramParcelFileDescriptor == null) {
      return;
    }
    try
    {
      paramParcelFileDescriptor.close();
      return;
    }
    catch (Throwable paramParcelFileDescriptor)
    {
      Log.w("IOUtils", "fail to close", paramParcelFileDescriptor);
    }
  }
  
  public static void closeSilently(Closeable paramCloseable)
  {
    if (paramCloseable == null) {
      return;
    }
    try
    {
      paramCloseable.close();
      return;
    }
    catch (Throwable paramCloseable)
    {
      Log.w("IOUtils", "fail to close", paramCloseable);
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\io\vov\vitamio\utils\IOUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */