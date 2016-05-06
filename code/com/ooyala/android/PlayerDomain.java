package com.ooyala.android;

import java.net.MalformedURLException;
import java.net.URL;

public class PlayerDomain
{
  private static final String[] schemes = { "http://", "https://" };
  private URL _domainUrl = null;
  
  public PlayerDomain(String paramString)
  {
    if (!isValid(paramString)) {
      throw new RuntimeException("Invalid Domain String: " + paramString);
    }
    try
    {
      this._domainUrl = new URL(paramString);
      return;
    }
    catch (MalformedURLException localMalformedURLException)
    {
      throw new RuntimeException("Domain is malformed:" + paramString);
    }
  }
  
  public static boolean isValid(String paramString)
  {
    boolean bool2 = false;
    String[] arrayOfString = schemes;
    int j = arrayOfString.length;
    int i = 0;
    for (;;)
    {
      boolean bool1 = bool2;
      if (i < j)
      {
        if (paramString.startsWith(arrayOfString[i])) {
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      i += 1;
    }
  }
  
  public String toString()
  {
    return this._domainUrl.toString();
  }
  
  public URL url()
  {
    return this._domainUrl;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\PlayerDomain.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */