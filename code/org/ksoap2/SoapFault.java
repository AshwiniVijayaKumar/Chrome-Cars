package org.ksoap2;

import java.io.IOException;
import org.kxml2.kdom.Node;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

public class SoapFault
  extends IOException
{
  public Node detail;
  public String faultactor;
  public String faultcode;
  public String faultstring;
  
  public void parse(XmlPullParser paramXmlPullParser)
    throws IOException, XmlPullParserException
  {
    paramXmlPullParser.require(2, "http://schemas.xmlsoap.org/soap/envelope/", "Fault");
    while (paramXmlPullParser.nextTag() == 2)
    {
      String str = paramXmlPullParser.getName();
      if (str.equals("detail"))
      {
        this.detail = new Node();
        this.detail.parse(paramXmlPullParser);
      }
      else
      {
        if (str.equals("faultcode")) {
          this.faultcode = paramXmlPullParser.nextText();
        }
        for (;;)
        {
          paramXmlPullParser.require(3, null, str);
          break;
          if (str.equals("faultstring"))
          {
            this.faultstring = paramXmlPullParser.nextText();
          }
          else
          {
            if (!str.equals("faultactor")) {
              break label134;
            }
            this.faultactor = paramXmlPullParser.nextText();
          }
        }
        label134:
        throw new RuntimeException("unexpected tag:" + str);
      }
    }
  }
  
  public String toString()
  {
    return "SoapFault - faultcode: '" + this.faultcode + "' faultstring: '" + this.faultstring + "' faultactor: '" + this.faultactor + "' detail: " + this.detail;
  }
  
  public void write(XmlSerializer paramXmlSerializer)
    throws IOException
  {
    paramXmlSerializer.startTag("http://schemas.xmlsoap.org/soap/envelope/", "Fault");
    paramXmlSerializer.startTag(null, "faultcode");
    paramXmlSerializer.text("" + this.faultcode);
    paramXmlSerializer.endTag(null, "faultcode");
    paramXmlSerializer.startTag(null, "faultstring");
    paramXmlSerializer.text("" + this.faultstring);
    paramXmlSerializer.endTag(null, "faultstring");
    paramXmlSerializer.startTag(null, "detail");
    if (this.detail != null) {
      this.detail.write(paramXmlSerializer);
    }
    paramXmlSerializer.endTag(null, "detail");
    paramXmlSerializer.endTag("http://schemas.xmlsoap.org/soap/envelope/", "Fault");
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\ksoap2\SoapFault.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */