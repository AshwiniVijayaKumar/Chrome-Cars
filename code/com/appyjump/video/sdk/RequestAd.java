package com.appyjump.video.sdk;

import com.appyjump.video.sdk.data.ClickType;
import com.appyjump.video.sdk.data.Request;
import com.appyjump.video.sdk.data.Response;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class RequestAd
{
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
  
  private Response parse(InputStream paramInputStream)
    throws RequestException
  {
    Object localObject = DocumentBuilderFactory.newInstance();
    Response localResponse = new Response();
    try
    {
      localObject = ((DocumentBuilderFactory)localObject).newDocumentBuilder();
      paramInputStream = new InputSource(paramInputStream);
      paramInputStream.setEncoding("ISO-8859-1");
      paramInputStream = ((DocumentBuilder)localObject).parse(paramInputStream);
      if (paramInputStream.getDocumentElement() == null) {
        throw new RequestException("Cannot parse Response, document is not an xml");
      }
    }
    catch (ParserConfigurationException paramInputStream)
    {
      throw new RequestException("Cannot parse Response", paramInputStream);
      localResponse.setType(AdType.VIDEO);
      localResponse.setClickType(ClickType.getValue("browser"));
      localResponse.setClickUrl(getValue(paramInputStream, "ClickThrough"));
      localResponse.setImageUrl(getValue(paramInputStream, "MediaFile"));
      localResponse.setSkipPreflight(false);
      localResponse.setRefresh(2000);
      return localResponse;
    }
    catch (SAXException paramInputStream)
    {
      throw new RequestException("Cannot parse Response", paramInputStream);
    }
    catch (IOException paramInputStream)
    {
      throw new RequestException("Cannot read Response", paramInputStream);
    }
    catch (Throwable paramInputStream)
    {
      throw new RequestException("Cannot read Response", paramInputStream);
    }
  }
  
  /* Error */
  private Response sendGetRequest(Request paramRequest)
    throws RequestException
  {
    // Byte code:
    //   0: ldc -88
    //   2: iconst_3
    //   3: invokestatic 174	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
    //   6: ifeq +11 -> 17
    //   9: ldc -88
    //   11: ldc -80
    //   13: invokestatic 180	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   16: pop
    //   17: new 182	java/lang/StringBuilder
    //   20: dup
    //   21: aload_1
    //   22: invokevirtual 187	com/appyjump/video/sdk/data/Request:getServerUrl	()Ljava/lang/String;
    //   25: invokespecial 188	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   28: astore_2
    //   29: aload_2
    //   30: ldc -66
    //   32: invokevirtual 194	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   35: pop
    //   36: aload_2
    //   37: aload_1
    //   38: invokevirtual 197	com/appyjump/video/sdk/data/Request:getPublisherId	()Ljava/lang/String;
    //   41: ldc -57
    //   43: invokestatic 205	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   46: invokevirtual 194	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   49: pop
    //   50: aload_2
    //   51: ldc -49
    //   53: invokevirtual 194	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   56: pop
    //   57: aload_2
    //   58: aload_1
    //   59: invokevirtual 210	com/appyjump/video/sdk/data/Request:getUserAgent	()Ljava/lang/String;
    //   62: ldc -57
    //   64: invokestatic 205	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   67: invokevirtual 194	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   70: pop
    //   71: aload_1
    //   72: invokevirtual 214	com/appyjump/video/sdk/data/Request:getLatitude	()D
    //   75: dconst_0
    //   76: dcmpl
    //   77: ifeq +72 -> 149
    //   80: aload_1
    //   81: invokevirtual 217	com/appyjump/video/sdk/data/Request:getLongitude	()D
    //   84: dconst_0
    //   85: dcmpl
    //   86: ifeq +63 -> 149
    //   89: aload_2
    //   90: ldc -37
    //   92: invokevirtual 194	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   95: pop
    //   96: aload_2
    //   97: aload_1
    //   98: invokevirtual 214	com/appyjump/video/sdk/data/Request:getLatitude	()D
    //   101: invokevirtual 222	java/lang/StringBuilder:append	(D)Ljava/lang/StringBuilder;
    //   104: pop
    //   105: aload_2
    //   106: ldc -32
    //   108: invokevirtual 194	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   111: pop
    //   112: aload_2
    //   113: aload_1
    //   114: invokevirtual 217	com/appyjump/video/sdk/data/Request:getLongitude	()D
    //   117: invokevirtual 222	java/lang/StringBuilder:append	(D)Ljava/lang/StringBuilder;
    //   120: pop
    //   121: aload_1
    //   122: invokevirtual 227	com/appyjump/video/sdk/data/Request:getLocation	()Ljava/lang/String;
    //   125: ifnull +24 -> 149
    //   128: aload_2
    //   129: ldc -27
    //   131: invokevirtual 194	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   134: pop
    //   135: aload_2
    //   136: aload_1
    //   137: invokevirtual 227	com/appyjump/video/sdk/data/Request:getLocation	()Ljava/lang/String;
    //   140: ldc -57
    //   142: invokestatic 205	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   145: invokevirtual 194	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   148: pop
    //   149: ldc -88
    //   151: iconst_3
    //   152: invokestatic 174	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
    //   155: ifeq +28 -> 183
    //   158: ldc -88
    //   160: new 182	java/lang/StringBuilder
    //   163: dup
    //   164: invokespecial 230	java/lang/StringBuilder:<init>	()V
    //   167: ldc -24
    //   169: invokevirtual 194	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   172: aload_2
    //   173: invokevirtual 235	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   176: invokevirtual 238	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   179: invokestatic 180	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   182: pop
    //   183: getstatic 244	java/lang/System:out	Ljava/io/PrintStream;
    //   186: new 182	java/lang/StringBuilder
    //   189: dup
    //   190: invokespecial 230	java/lang/StringBuilder:<init>	()V
    //   193: ldc -10
    //   195: invokevirtual 194	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   198: aload_2
    //   199: invokevirtual 235	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   202: invokevirtual 238	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   205: invokevirtual 251	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   208: new 253	org/apache/http/impl/client/DefaultHttpClient
    //   211: dup
    //   212: invokespecial 254	org/apache/http/impl/client/DefaultHttpClient:<init>	()V
    //   215: astore_1
    //   216: aload_1
    //   217: invokevirtual 258	org/apache/http/impl/client/DefaultHttpClient:getParams	()Lorg/apache/http/params/HttpParams;
    //   220: sipush 15000
    //   223: invokestatic 264	org/apache/http/params/HttpConnectionParams:setSoTimeout	(Lorg/apache/http/params/HttpParams;I)V
    //   226: aload_1
    //   227: invokevirtual 258	org/apache/http/impl/client/DefaultHttpClient:getParams	()Lorg/apache/http/params/HttpParams;
    //   230: sipush 15000
    //   233: invokestatic 267	org/apache/http/params/HttpConnectionParams:setConnectionTimeout	(Lorg/apache/http/params/HttpParams;I)V
    //   236: new 269	org/apache/http/client/methods/HttpGet
    //   239: dup
    //   240: aload_2
    //   241: invokevirtual 238	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   244: invokespecial 270	org/apache/http/client/methods/HttpGet:<init>	(Ljava/lang/String;)V
    //   247: astore_2
    //   248: aload_0
    //   249: aload_1
    //   250: aload_2
    //   251: invokevirtual 274	org/apache/http/impl/client/DefaultHttpClient:execute	(Lorg/apache/http/client/methods/HttpUriRequest;)Lorg/apache/http/HttpResponse;
    //   254: invokeinterface 280 1 0
    //   259: invokeinterface 286 1 0
    //   264: invokespecial 288	com/appyjump/video/sdk/RequestAd:parse	(Ljava/io/InputStream;)Lcom/appyjump/video/sdk/data/Response;
    //   267: astore_1
    //   268: aload_1
    //   269: areturn
    //   270: astore_1
    //   271: new 68	com/appyjump/video/sdk/RequestException
    //   274: dup
    //   275: ldc_w 290
    //   278: aload_1
    //   279: invokespecial 118	com/appyjump/video/sdk/RequestException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   282: athrow
    //   283: astore_1
    //   284: new 68	com/appyjump/video/sdk/RequestException
    //   287: dup
    //   288: ldc_w 292
    //   291: aload_1
    //   292: invokespecial 118	com/appyjump/video/sdk/RequestException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   295: athrow
    //   296: astore_1
    //   297: new 68	com/appyjump/video/sdk/RequestException
    //   300: dup
    //   301: ldc_w 292
    //   304: aload_1
    //   305: invokespecial 118	com/appyjump/video/sdk/RequestException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   308: athrow
    //   309: astore_1
    //   310: new 68	com/appyjump/video/sdk/RequestException
    //   313: dup
    //   314: ldc_w 292
    //   317: aload_1
    //   318: invokespecial 118	com/appyjump/video/sdk/RequestException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   321: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	322	0	this	RequestAd
    //   0	322	1	paramRequest	Request
    //   28	223	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   29	149	270	java/io/UnsupportedEncodingException
    //   248	268	283	org/apache/http/client/ClientProtocolException
    //   248	268	296	java/io/IOException
    //   248	268	309	java/lang/Throwable
  }
  
  public Response sendRequest(Request paramRequest)
    throws RequestException
  {
    return sendGetRequest(paramRequest);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\appyjump\video\sdk\RequestAd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */