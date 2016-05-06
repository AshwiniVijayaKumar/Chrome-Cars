package nl.siegmann.epublib.epub;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

public class EpubProcessorSupport
{
  protected static DocumentBuilderFactory documentBuilderFactory;
  private static final Logger log = LoggerFactory.getLogger(EpubProcessorSupport.class);
  
  static
  {
    init();
  }
  
  public static DocumentBuilder createDocumentBuilder()
  {
    Object localObject = null;
    try
    {
      DocumentBuilder localDocumentBuilder = documentBuilderFactory.newDocumentBuilder();
      localObject = localDocumentBuilder;
      localDocumentBuilder.setEntityResolver(getEntityResolver());
      return localDocumentBuilder;
    }
    catch (ParserConfigurationException localParserConfigurationException)
    {
      log.error(localParserConfigurationException.getMessage());
    }
    return (DocumentBuilder)localObject;
  }
  
  public static XmlSerializer createXmlSerializer(OutputStream paramOutputStream)
    throws UnsupportedEncodingException
  {
    return createXmlSerializer(new OutputStreamWriter(paramOutputStream, "UTF-8"));
  }
  
  public static XmlSerializer createXmlSerializer(Writer paramWriter)
  {
    XmlSerializer localXmlSerializer2 = null;
    XmlSerializer localXmlSerializer1 = localXmlSerializer2;
    try
    {
      XmlPullParserFactory localXmlPullParserFactory = XmlPullParserFactory.newInstance();
      localXmlSerializer1 = localXmlSerializer2;
      localXmlPullParserFactory.setValidating(true);
      localXmlSerializer1 = localXmlSerializer2;
      localXmlSerializer2 = localXmlPullParserFactory.newSerializer();
      localXmlSerializer1 = localXmlSerializer2;
      localXmlSerializer2.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
      localXmlSerializer1 = localXmlSerializer2;
      localXmlSerializer2.setOutput(paramWriter);
      return localXmlSerializer2;
    }
    catch (Exception paramWriter)
    {
      log.error("When creating XmlSerializer: " + paramWriter.getClass().getName() + ": " + paramWriter.getMessage());
    }
    return localXmlSerializer1;
  }
  
  public static EntityResolver getEntityResolver()
  {
    return new EntityResolverImpl();
  }
  
  private static void init()
  {
    documentBuilderFactory = DocumentBuilderFactory.newInstance();
    documentBuilderFactory.setNamespaceAware(true);
    documentBuilderFactory.setValidating(false);
  }
  
  public DocumentBuilderFactory getDocumentBuilderFactory()
  {
    return documentBuilderFactory;
  }
  
  static class EntityResolverImpl
    implements EntityResolver
  {
    private String previousLocation;
    
    public InputSource resolveEntity(String paramString1, String paramString2)
      throws SAXException, IOException
    {
      if (paramString2.startsWith("http:"))
      {
        paramString1 = new URL(paramString2);
        paramString1 = "dtd/" + paramString1.getHost() + paramString1.getPath();
        this.previousLocation = paramString1.substring(0, paramString1.lastIndexOf('/'));
      }
      while (getClass().getClassLoader().getResource(paramString1) == null)
      {
        throw new RuntimeException("remote resource is not cached : [" + paramString2 + "] cannot continue");
        paramString1 = this.previousLocation + paramString2.substring(paramString2.lastIndexOf('/'));
      }
      return new InputSource(EpubProcessorSupport.class.getClassLoader().getResourceAsStream(paramString1));
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\nl\siegmann\epublib\epub\EpubProcessorSupport.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */