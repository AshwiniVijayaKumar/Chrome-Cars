package org.ksoap2.serialization;

import java.io.IOException;
import java.math.BigDecimal;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

public class MarshalFloat
  implements Marshal
{
  public Object readInstance(XmlPullParser paramXmlPullParser, String paramString1, String paramString2, PropertyInfo paramPropertyInfo)
    throws IOException, XmlPullParserException
  {
    paramXmlPullParser = paramXmlPullParser.nextText();
    if (paramString2.equals("float")) {
      return new Float(paramXmlPullParser);
    }
    if (paramString2.equals("double")) {
      return new Double(paramXmlPullParser);
    }
    if (paramString2.equals("decimal")) {
      return new BigDecimal(paramXmlPullParser);
    }
    throw new RuntimeException("float, double, or decimal expected");
  }
  
  public void register(SoapSerializationEnvelope paramSoapSerializationEnvelope)
  {
    paramSoapSerializationEnvelope.addMapping(paramSoapSerializationEnvelope.xsd, "float", Float.class, this);
    paramSoapSerializationEnvelope.addMapping(paramSoapSerializationEnvelope.xsd, "double", Double.class, this);
    paramSoapSerializationEnvelope.addMapping(paramSoapSerializationEnvelope.xsd, "decimal", BigDecimal.class, this);
  }
  
  public void writeInstance(XmlSerializer paramXmlSerializer, Object paramObject)
    throws IOException
  {
    paramXmlSerializer.text(paramObject.toString());
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\ksoap2\serialization\MarshalFloat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */