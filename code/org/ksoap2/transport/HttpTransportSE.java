package org.ksoap2.transport;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.ksoap2.SoapEnvelope;
import org.xmlpull.v1.XmlPullParserException;

public class HttpTransportSE
  extends Transport
{
  public HttpTransportSE(String paramString)
  {
    super(paramString);
  }
  
  public void call(String paramString, SoapEnvelope paramSoapEnvelope)
    throws IOException, XmlPullParserException
  {
    Object localObject1 = paramString;
    if (paramString == null) {
      localObject1 = "\"\"";
    }
    byte[] arrayOfByte = createRequestData(paramSoapEnvelope);
    Object localObject2;
    if (this.debug)
    {
      paramString = new String(arrayOfByte);
      this.requestDump = paramString;
      this.responseDump = null;
      localObject2 = getServiceConnection();
      ((ServiceConnection)localObject2).setRequestProperty("User-Agent", "kSOAP/2.0");
      ((ServiceConnection)localObject2).setRequestProperty("SOAPAction", (String)localObject1);
      ((ServiceConnection)localObject2).setRequestProperty("Content-Type", "text/xml");
      ((ServiceConnection)localObject2).setRequestProperty("Connection", "close");
      ((ServiceConnection)localObject2).setRequestProperty("Content-Length", "" + arrayOfByte.length);
      ((ServiceConnection)localObject2).setRequestMethod("POST");
      ((ServiceConnection)localObject2).connect();
      paramString = ((ServiceConnection)localObject2).openOutputStream();
      paramString.write(arrayOfByte, 0, arrayOfByte.length);
      paramString.flush();
      paramString.close();
    }
    for (;;)
    {
      int i;
      try
      {
        ((ServiceConnection)localObject2).connect();
        paramString = ((ServiceConnection)localObject2).openInputStream();
        localObject1 = paramString;
        if (this.debug)
        {
          localObject1 = new ByteArrayOutputStream();
          localObject2 = new byte['Ā'];
          i = paramString.read((byte[])localObject2, 0, 256);
          if (i == -1)
          {
            ((ByteArrayOutputStream)localObject1).flush();
            localObject1 = ((ByteArrayOutputStream)localObject1).toByteArray();
            this.responseDump = new String((byte[])localObject1);
            paramString.close();
            localObject1 = new ByteArrayInputStream((byte[])localObject1);
          }
        }
        else
        {
          parseResponse(paramSoapEnvelope, (InputStream)localObject1);
          return;
          paramString = null;
        }
      }
      catch (IOException localIOException)
      {
        localObject1 = ((ServiceConnection)localObject2).getErrorStream();
        paramString = (String)localObject1;
        if (localObject1 != null) {
          continue;
        }
        ((ServiceConnection)localObject2).disconnect();
        throw localIOException;
      }
      ((ByteArrayOutputStream)localObject1).write((byte[])localObject2, 0, i);
    }
  }
  
  protected ServiceConnection getServiceConnection()
    throws IOException
  {
    return new ServiceConnectionSE(this.url);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\ksoap2\transport\HttpTransportSE.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */