package nl.siegmann.epublib.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import nl.siegmann.epublib.domain.MediaType;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.epub.EpubProcessorSupport;
import nl.siegmann.epublib.service.MediatypeService;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ResourceUtil
{
  public static Resource createResource(File paramFile)
    throws IOException
  {
    if (paramFile == null) {
      return null;
    }
    MediaType localMediaType = MediatypeService.determineMediaType(paramFile.getName());
    return new Resource(IOUtil.toByteArray(new FileInputStream(paramFile)), localMediaType);
  }
  
  public static Resource createResource(String paramString1, String paramString2)
  {
    return new Resource(null, ("<html><head><title>" + paramString1 + "</title></head><body><h1>" + paramString1 + "</h1></body></html>").getBytes(), paramString2, MediatypeService.XHTML, "UTF-8");
  }
  
  public static Resource createResource(ZipEntry paramZipEntry, ZipInputStream paramZipInputStream)
    throws IOException
  {
    return new Resource(paramZipInputStream, paramZipEntry.getName());
  }
  
  public static Document getAsDocument(Resource paramResource)
    throws UnsupportedEncodingException, SAXException, IOException, ParserConfigurationException
  {
    return getAsDocument(paramResource, EpubProcessorSupport.createDocumentBuilder());
  }
  
  public static Document getAsDocument(Resource paramResource, DocumentBuilder paramDocumentBuilder)
    throws UnsupportedEncodingException, SAXException, IOException, ParserConfigurationException
  {
    paramResource = getInputSource(paramResource);
    if (paramResource == null) {
      return null;
    }
    return paramDocumentBuilder.parse(paramResource);
  }
  
  public static InputSource getInputSource(Resource paramResource)
    throws IOException
  {
    if (paramResource == null) {}
    do
    {
      return null;
      paramResource = paramResource.getReader();
    } while (paramResource == null);
    return new InputSource(paramResource);
  }
  
  public static byte[] recode(String paramString1, String paramString2, byte[] paramArrayOfByte)
    throws UnsupportedEncodingException
  {
    return new String(paramArrayOfByte, paramString1).getBytes(paramString2);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\nl\siegmann\epublib\util\ResourceUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */