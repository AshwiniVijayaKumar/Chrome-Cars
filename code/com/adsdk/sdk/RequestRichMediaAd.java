package com.adsdk.sdk;

import com.adsdk.sdk.video.ResponseHandler;
import com.adsdk.sdk.video.RichMediaAd;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.util.Scanner;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

public class RequestRichMediaAd
  extends RequestAd<RichMediaAd>
{
  public RequestRichMediaAd()
  {
    this.is = null;
  }
  
  public RequestRichMediaAd(InputStream paramInputStream)
  {
    this.is = paramInputStream;
  }
  
  private String convertStreamToString(InputStream paramInputStream)
  {
    try
    {
      paramInputStream = new Scanner(paramInputStream).useDelimiter("\\A").next();
      return paramInputStream;
    }
    catch (NoSuchElementException paramInputStream) {}
    return "";
  }
  
  RichMediaAd parse(InputStream paramInputStream)
    throws RequestException
  {
    try
    {
      XMLReader localXMLReader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
      ResponseHandler localResponseHandler = new ResponseHandler();
      localXMLReader.setContentHandler(localResponseHandler);
      if (Log.LOGGING_ENABLED)
      {
        paramInputStream = convertStreamToString(paramInputStream);
        Log.d("Ad RequestPerform HTTP Response: " + paramInputStream);
        paramInputStream = new InputSource(new ByteArrayInputStream(paramInputStream.getBytes("ISO-8859-1")));
        paramInputStream.setEncoding("ISO-8859-1");
        localXMLReader.parse(paramInputStream);
        return localResponseHandler.getRichMediaAd();
      }
      paramInputStream = new InputSource(paramInputStream);
      paramInputStream.setEncoding("ISO-8859-1");
      localXMLReader.parse(paramInputStream);
      paramInputStream = localResponseHandler.getRichMediaAd();
      return paramInputStream;
    }
    catch (Exception paramInputStream)
    {
      throw new RequestException("Cannot parse Response:" + paramInputStream.getMessage(), paramInputStream);
    }
  }
  
  RichMediaAd parseTestString()
    throws RequestException
  {
    try
    {
      Object localObject = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
      ResponseHandler localResponseHandler = new ResponseHandler();
      ((XMLReader)localObject).setContentHandler(localResponseHandler);
      ((XMLReader)localObject).parse(new InputSource(new InputStreamReader(this.is, "UTF-8")));
      localObject = localResponseHandler.getRichMediaAd();
      return (RichMediaAd)localObject;
    }
    catch (Exception localException)
    {
      throw new RequestException("Cannot parse Response:" + localException.getMessage(), localException);
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\adsdk\sdk\RequestRichMediaAd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */