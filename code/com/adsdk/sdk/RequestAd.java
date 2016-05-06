package com.adsdk.sdk;

import java.io.IOException;
import java.io.InputStream;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpProtocolParams;

public abstract class RequestAd<T>
{
  InputStream is;
  
  abstract T parse(InputStream paramInputStream)
    throws RequestException;
  
  abstract T parseTestString()
    throws RequestException;
  
  public T sendRequest(AdRequest paramAdRequest)
    throws RequestException
  {
    if (this.is == null)
    {
      Log.d("Parse Real");
      String str = paramAdRequest.toString();
      Log.d("Ad RequestPerform HTTP Get Url: " + str);
      DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient();
      HttpConnectionParams.setSoTimeout(localDefaultHttpClient.getParams(), 10000);
      HttpConnectionParams.setConnectionTimeout(localDefaultHttpClient.getParams(), 10000);
      HttpProtocolParams.setUserAgent(localDefaultHttpClient.getParams(), paramAdRequest.getUserAgent());
      paramAdRequest = new HttpGet(str);
      try
      {
        paramAdRequest = localDefaultHttpClient.execute(paramAdRequest);
        int i = paramAdRequest.getStatusLine().getStatusCode();
        if (i == 200) {
          return (T)parse(paramAdRequest.getEntity().getContent());
        }
        throw new RequestException("Server Error. Response code:" + i);
      }
      catch (RequestException paramAdRequest)
      {
        throw paramAdRequest;
      }
      catch (ClientProtocolException paramAdRequest)
      {
        throw new RequestException("Error in HTTP request", paramAdRequest);
      }
      catch (IOException paramAdRequest)
      {
        throw new RequestException("Error in HTTP request", paramAdRequest);
      }
      catch (Throwable paramAdRequest)
      {
        throw new RequestException("Error in HTTP request", paramAdRequest);
      }
    }
    Log.d("Parse Injected");
    return (T)parseTestString();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\adsdk\sdk\RequestAd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */