package org.ksoap2.transport;

import java.io.IOException;

public class AndroidHttpTransport
  extends HttpTransportSE
{
  public AndroidHttpTransport(String paramString)
  {
    super(paramString);
  }
  
  protected ServiceConnection getServiceConnection()
    throws IOException
  {
    return new AndroidServiceConnection(this.url);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\ksoap2\transport\AndroidHttpTransport.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */