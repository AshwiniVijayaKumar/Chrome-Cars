package org.ksoap2.transport;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.ksoap2.SoapEnvelope;
import org.kxml2.io.KXmlParser;
import org.kxml2.io.KXmlSerializer;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

public abstract class Transport
{
  public boolean debug;
  public String requestDump;
  public String responseDump;
  protected String url;
  private String xmlVersionTag = "";
  
  public Transport() {}
  
  public Transport(String paramString)
  {
    this.url = paramString;
  }
  
  public abstract void call(String paramString, SoapEnvelope paramSoapEnvelope)
    throws IOException, XmlPullParserException;
  
  protected byte[] createRequestData(SoapEnvelope paramSoapEnvelope)
    throws IOException
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    localByteArrayOutputStream.write(this.xmlVersionTag.getBytes());
    KXmlSerializer localKXmlSerializer = new KXmlSerializer();
    localKXmlSerializer.setOutput(localByteArrayOutputStream, null);
    paramSoapEnvelope.write(localKXmlSerializer);
    localKXmlSerializer.flush();
    localByteArrayOutputStream.write(13);
    localByteArrayOutputStream.write(10);
    localByteArrayOutputStream.flush();
    return localByteArrayOutputStream.toByteArray();
  }
  
  protected void parseResponse(SoapEnvelope paramSoapEnvelope, InputStream paramInputStream)
    throws XmlPullParserException, IOException
  {
    KXmlParser localKXmlParser = new KXmlParser();
    localKXmlParser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
    localKXmlParser.setInput(paramInputStream, null);
    paramSoapEnvelope.parse(localKXmlParser);
  }
  
  public void reset() {}
  
  public void setUrl(String paramString)
  {
    this.url = paramString;
  }
  
  public void setXmlVersionTag(String paramString)
  {
    this.xmlVersionTag = paramString;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\ksoap2\transport\Transport.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */