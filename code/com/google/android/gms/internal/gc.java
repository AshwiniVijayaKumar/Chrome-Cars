package com.google.android.gms.internal;

import android.os.ParcelFileDescriptor;
import android.os.ParcelFileDescriptor.AutoCloseInputStream;
import android.os.ParcelFileDescriptor.AutoCloseOutputStream;
import com.google.android.gms.games.multiplayer.realtime.RealTimeSocket;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class gc
  implements RealTimeSocket
{
  private static final String TAG = gc.class.getSimpleName();
  private final ParcelFileDescriptor AC;
  private final OutputStream HA;
  private final InputStream Hz;
  
  gc(ParcelFileDescriptor paramParcelFileDescriptor)
  {
    this.AC = paramParcelFileDescriptor;
    this.Hz = new ParcelFileDescriptor.AutoCloseInputStream(paramParcelFileDescriptor);
    this.HA = new ParcelFileDescriptor.AutoCloseOutputStream(paramParcelFileDescriptor);
  }
  
  public void close()
    throws IOException
  {
    this.AC.close();
  }
  
  public InputStream getInputStream()
    throws IOException
  {
    return this.Hz;
  }
  
  public OutputStream getOutputStream()
    throws IOException
  {
    return this.HA;
  }
  
  public ParcelFileDescriptor getParcelFileDescriptor()
    throws IOException
  {
    return this.AC;
  }
  
  public boolean isClosed()
  {
    try
    {
      this.Hz.available();
      return false;
    }
    catch (IOException localIOException) {}
    return true;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\gc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */