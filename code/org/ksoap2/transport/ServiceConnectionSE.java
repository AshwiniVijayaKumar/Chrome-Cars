package org.ksoap2.transport;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ServiceConnectionSE
  implements ServiceConnection
{
  private HttpURLConnection connection;
  
  public ServiceConnectionSE(String paramString)
    throws IOException
  {
    this.connection = ((HttpURLConnection)new URL(paramString).openConnection());
    this.connection.setUseCaches(false);
    this.connection.setDoOutput(true);
    this.connection.setDoInput(true);
  }
  
  public void connect()
    throws IOException
  {
    this.connection.connect();
  }
  
  public void disconnect()
  {
    this.connection.disconnect();
  }
  
  public InputStream getErrorStream()
  {
    return this.connection.getErrorStream();
  }
  
  public InputStream openInputStream()
    throws IOException
  {
    return this.connection.getInputStream();
  }
  
  public OutputStream openOutputStream()
    throws IOException
  {
    return this.connection.getOutputStream();
  }
  
  public void setRequestMethod(String paramString)
    throws IOException
  {
    this.connection.setRequestMethod(paramString);
  }
  
  public void setRequestProperty(String paramString1, String paramString2)
  {
    this.connection.setRequestProperty(paramString1, paramString2);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\ksoap2\transport\ServiceConnectionSE.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */