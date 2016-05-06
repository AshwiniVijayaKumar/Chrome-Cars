package com.adsdk.sdk;

import com.adsdk.sdk.data.ClickType;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class RequestBannerAd
  extends RequestAd<BannerAd>
{
  public RequestBannerAd() {}
  
  public RequestBannerAd(InputStream paramInputStream)
  {
    this.is = paramInputStream;
    paramInputStream = new StringBuilder("Parse is null");
    if (this.is == null) {}
    for (boolean bool = true;; bool = false)
    {
      Log.d(bool);
      return;
    }
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
  
  private String getAttribute(Document paramDocument, String paramString1, String paramString2)
  {
    paramDocument = (Element)paramDocument.getElementsByTagName(paramString1).item(0);
    if (paramDocument != null)
    {
      paramDocument = paramDocument.getAttribute(paramString2);
      if (paramDocument.length() != 0) {
        return paramDocument;
      }
    }
    return null;
  }
  
  private int getInteger(String paramString)
  {
    if (paramString == null) {
      return 0;
    }
    try
    {
      int i = Integer.parseInt(paramString);
      return i;
    }
    catch (NumberFormatException paramString) {}
    return 0;
  }
  
  private String getValue(Document paramDocument, String paramString)
  {
    paramDocument = (Element)paramDocument.getElementsByTagName(paramString).item(0);
    if (paramDocument != null)
    {
      paramDocument = paramDocument.getChildNodes();
      if (paramDocument.getLength() > 0) {
        return paramDocument.item(0).getNodeValue();
      }
    }
    return null;
  }
  
  private boolean getValueAsBoolean(Document paramDocument, String paramString)
  {
    return "yes".equalsIgnoreCase(getValue(paramDocument, paramString));
  }
  
  private int getValueAsInt(Document paramDocument, String paramString)
  {
    return getInteger(getValue(paramDocument, paramString));
  }
  
  BannerAd parse(InputStream paramInputStream)
    throws RequestException
  {
    Object localObject1 = DocumentBuilderFactory.newInstance();
    BannerAd localBannerAd = new BannerAd();
    Object localObject2;
    try
    {
      localObject2 = ((DocumentBuilderFactory)localObject1).newDocumentBuilder();
      localObject1 = new InputSource(paramInputStream);
      if (Log.LOGGING_ENABLED)
      {
        paramInputStream = convertStreamToString(paramInputStream);
        Log.d("Ad RequestPerform HTTP Response: " + paramInputStream);
        localObject1 = new InputSource(new ByteArrayInputStream(paramInputStream.getBytes("ISO-8859-1")));
      }
      ((InputSource)localObject1).setEncoding("ISO-8859-1");
      paramInputStream = ((DocumentBuilder)localObject2).parse((InputSource)localObject1);
      localObject1 = paramInputStream.getDocumentElement();
      if (localObject1 == null) {
        throw new RequestException("Cannot parse Response, document is not an xml");
      }
    }
    catch (ParserConfigurationException paramInputStream)
    {
      throw new RequestException("Cannot parse Response", paramInputStream);
      localObject2 = getValue(paramInputStream, "error");
      if (localObject2 != null) {
        throw new RequestException("Error Response received: " + (String)localObject2);
      }
    }
    catch (SAXException paramInputStream)
    {
      throw new RequestException("Cannot parse Response", paramInputStream);
      localObject2 = ((Element)localObject1).getAttribute("type");
      ((Element)localObject1).normalize();
      if ("imageAd".equalsIgnoreCase((String)localObject2))
      {
        localBannerAd.setType(0);
        localBannerAd.setBannerWidth(getValueAsInt(paramInputStream, "bannerwidth"));
        localBannerAd.setBannerHeight(getValueAsInt(paramInputStream, "bannerheight"));
        localBannerAd.setClickType(ClickType.getValue(getValue(paramInputStream, "clicktype")));
        localBannerAd.setClickUrl(getValue(paramInputStream, "clickurl"));
        localBannerAd.setImageUrl(getValue(paramInputStream, "imageurl"));
        localBannerAd.setRefresh(getValueAsInt(paramInputStream, "refresh"));
        localBannerAd.setScale(getValueAsBoolean(paramInputStream, "scale"));
        localBannerAd.setSkipPreflight(getValueAsBoolean(paramInputStream, "skippreflight"));
        return localBannerAd;
      }
      if ("textAd".equalsIgnoreCase((String)localObject2))
      {
        localBannerAd.setType(1);
        localBannerAd.setText(getValue(paramInputStream, "htmlString"));
        localObject1 = getAttribute(paramInputStream, "htmlString", "skipoverlaybutton");
        Log.i("PARSER", "SkipOverlay: " + (String)localObject1);
        if (localObject1 != null) {
          localBannerAd.setSkipOverlay(Integer.parseInt((String)localObject1));
        }
        localBannerAd.setClickType(ClickType.getValue(getValue(paramInputStream, "clicktype")));
        localBannerAd.setClickUrl(getValue(paramInputStream, "clickurl"));
        localBannerAd.setRefresh(getValueAsInt(paramInputStream, "refresh"));
        localBannerAd.setScale(getValueAsBoolean(paramInputStream, "scale"));
        localBannerAd.setSkipPreflight(getValueAsBoolean(paramInputStream, "skippreflight"));
        return localBannerAd;
      }
    }
    catch (IOException paramInputStream)
    {
      throw new RequestException("Cannot read Response", paramInputStream);
      if ("mraidAd".equalsIgnoreCase((String)localObject2))
      {
        localBannerAd.setType(7);
        localBannerAd.setText(getValue(paramInputStream, "htmlString"));
        localObject1 = getAttribute(paramInputStream, "htmlString", "skipoverlaybutton");
        Log.i("PARSER", "SkipOverlay: " + (String)localObject1);
        if (localObject1 != null) {
          localBannerAd.setSkipOverlay(Integer.parseInt((String)localObject1));
        }
        localBannerAd.setClickType(ClickType.getValue(getValue(paramInputStream, "clicktype")));
        localBannerAd.setClickUrl(getValue(paramInputStream, "clickurl"));
        localBannerAd.setUrlType(getValue(paramInputStream, "urltype"));
        localBannerAd.setRefresh(0);
        localBannerAd.setScale(getValueAsBoolean(paramInputStream, "scale"));
        localBannerAd.setSkipPreflight(getValueAsBoolean(paramInputStream, "skippreflight"));
        return localBannerAd;
      }
    }
    catch (Throwable paramInputStream)
    {
      throw new RequestException("Cannot read Response", paramInputStream);
    }
    if ("noAd".equalsIgnoreCase((String)localObject2))
    {
      localBannerAd.setType(2);
      return localBannerAd;
    }
    throw new RequestException("Unknown response type " + (String)localObject2);
  }
  
  BannerAd parseTestString()
    throws RequestException
  {
    return parse(this.is);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\adsdk\sdk\RequestBannerAd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */