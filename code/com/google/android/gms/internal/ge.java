package com.google.android.gms.internal;

import android.net.LocalSocket;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import com.google.android.gms.games.multiplayer.realtime.RealTimeSocket;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

final class ge
  implements RealTimeSocket
{
  private ParcelFileDescriptor AC;
  private final String GZ;
  private final LocalSocket HG;
  
  ge(LocalSocket paramLocalSocket, String paramString)
  {
    this.HG = paramLocalSocket;
    this.GZ = paramString;
  }
  
  public void close()
    throws IOException
  {
    this.HG.close();
  }
  
  public InputStream getInputStream()
    throws IOException
  {
    return this.HG.getInputStream();
  }
  
  public OutputStream getOutputStream()
    throws IOException
  {
    return this.HG.getOutputStream();
  }
  
  public ParcelFileDescriptor getParcelFileDescriptor()
    throws IOException
  {
    if ((this.AC == null) && (!isClosed()))
    {
      Parcel localParcel = Parcel.obtain();
      localParcel.writeFileDescriptor(this.HG.getFileDescriptor());
      localParcel.setDataPosition(0);
      this.AC = localParcel.readFileDescriptor();
    }
    return this.AC;
  }
  
  public boolean isClosed()
  {
    return (!this.HG.isConnected()) && (!this.HG.isBound());
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\ge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */