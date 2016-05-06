package com.spoledge.aacdecoder;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

public class IcyURLStreamHandler
  extends URLStreamHandler
{
  protected int getDefaultPort()
  {
    return 80;
  }
  
  protected URLConnection openConnection(URL paramURL)
    throws IOException
  {
    return new IcyURLConnection(paramURL);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\spoledge\aacdecoder\IcyURLStreamHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */