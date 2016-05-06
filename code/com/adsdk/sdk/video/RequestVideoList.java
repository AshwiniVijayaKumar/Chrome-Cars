package com.adsdk.sdk.video;

import com.adsdk.sdk.AdRequest;
import com.adsdk.sdk.Log;
import com.adsdk.sdk.RequestException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpProtocolParams;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

public class RequestVideoList
{
  private HashMap<String, Long> parse(InputStream paramInputStream)
    throws RequestException
  {
    try
    {
      XMLReader localXMLReader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
      ResponseHandler localResponseHandler = new ResponseHandler();
      localXMLReader.setContentHandler(localResponseHandler);
      paramInputStream = new InputSource(paramInputStream);
      paramInputStream.setEncoding("ISO-8859-1");
      localXMLReader.parse(paramInputStream);
      paramInputStream = localResponseHandler.videoList;
      return paramInputStream;
    }
    catch (Exception paramInputStream)
    {
      throw new RequestException("Cannot parse Response:" + paramInputStream.getMessage(), paramInputStream);
    }
  }
  
  public HashMap<String, Long> sendRequest(AdRequest paramAdRequest)
    throws RequestException
  {
    String str = paramAdRequest.toString() + "&listads=1";
    Log.d("Video List RequestPerform HTTP Get Url: " + str);
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
        return parse(paramAdRequest.getEntity().getContent());
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
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\adsdk\sdk\video\RequestVideoList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */