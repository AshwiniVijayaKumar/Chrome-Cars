package nl.siegmann.epublib.epub;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.MediaType;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.domain.Resources;
import nl.siegmann.epublib.service.MediatypeService;
import nl.siegmann.epublib.util.ResourceUtil;
import nl.siegmann.epublib.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class EpubReader
{
  private static final Logger log = LoggerFactory.getLogger(EpubReader.class);
  private BookProcessor bookProcessor = BookProcessor.IDENTITY_BOOKPROCESSOR;
  
  private String getPackageResourceHref(Resources paramResources)
  {
    Object localObject = "OEBPS/content.opf";
    paramResources = paramResources.remove("META-INF/container.xml");
    if (paramResources == null) {
      return (String)localObject;
    }
    try
    {
      paramResources = ((Element)((Element)ResourceUtil.getAsDocument(paramResources).getDocumentElement().getElementsByTagName("rootfiles").item(0)).getElementsByTagName("rootfile").item(0)).getAttribute("full-path");
      localObject = paramResources;
      if (StringUtil.isBlank(paramResources)) {
        localObject = "OEBPS/content.opf";
      }
      return (String)localObject;
    }
    catch (Exception paramResources)
    {
      for (;;)
      {
        log.error(paramResources.getMessage(), paramResources);
        paramResources = (Resources)localObject;
      }
    }
  }
  
  private void handleMimeType(Book paramBook, Resources paramResources)
  {
    paramResources.remove("mimetype");
  }
  
  private Book postProcessBook(Book paramBook)
  {
    Book localBook = paramBook;
    if (this.bookProcessor != null) {
      localBook = this.bookProcessor.processBook(paramBook);
    }
    return localBook;
  }
  
  private Resource processNcxResource(Resource paramResource, Book paramBook)
  {
    return NCXDocument.read(paramBook, this);
  }
  
  private Resource processPackageResource(String paramString, Book paramBook, Resources paramResources)
  {
    paramString = paramResources.remove(paramString);
    try
    {
      PackageDocumentReader.read(paramString, this, paramBook, paramResources);
      return paramString;
    }
    catch (Exception paramBook)
    {
      log.error(paramBook.getMessage(), paramBook);
    }
    return paramString;
  }
  
  private Resources readLazyResources(String paramString1, String paramString2, List<MediaType> paramList)
    throws IOException
  {
    ZipInputStream localZipInputStream = new ZipInputStream(new FileInputStream(paramString1));
    Resources localResources = new Resources();
    Object localObject = localZipInputStream.getNextEntry();
    while (localObject != null) {
      if (((ZipEntry)localObject).isDirectory())
      {
        localObject = localZipInputStream.getNextEntry();
      }
      else
      {
        String str = ((ZipEntry)localObject).getName();
        if (paramList.contains(MediatypeService.determineMediaType(str))) {}
        for (localObject = new Resource(paramString1, ((ZipEntry)localObject).getSize(), str);; localObject = new Resource(localZipInputStream, str))
        {
          if (((Resource)localObject).getMediaType() == MediatypeService.XHTML) {
            ((Resource)localObject).setInputEncoding(paramString2);
          }
          localResources.add((Resource)localObject);
          break;
        }
      }
    }
    return localResources;
  }
  
  private Resources readResources(ZipInputStream paramZipInputStream, String paramString)
    throws IOException
  {
    Resources localResources = new Resources();
    Object localObject = paramZipInputStream.getNextEntry();
    if (localObject != null)
    {
      if (((ZipEntry)localObject).isDirectory()) {}
      for (;;)
      {
        localObject = paramZipInputStream.getNextEntry();
        break;
        localObject = ResourceUtil.createResource((ZipEntry)localObject, paramZipInputStream);
        if (((Resource)localObject).getMediaType() == MediatypeService.XHTML) {
          ((Resource)localObject).setInputEncoding(paramString);
        }
        localResources.add((Resource)localObject);
      }
    }
    return localResources;
  }
  
  public Book readEpub(InputStream paramInputStream)
    throws IOException
  {
    return readEpub(paramInputStream, "UTF-8");
  }
  
  public Book readEpub(InputStream paramInputStream, String paramString)
    throws IOException
  {
    return readEpub(new ZipInputStream(paramInputStream), paramString);
  }
  
  public Book readEpub(ZipInputStream paramZipInputStream)
    throws IOException
  {
    return readEpub(paramZipInputStream, "UTF-8");
  }
  
  public Book readEpub(ZipInputStream paramZipInputStream, String paramString)
    throws IOException
  {
    Book localBook = new Book();
    paramZipInputStream = readResources(paramZipInputStream, paramString);
    handleMimeType(localBook, paramZipInputStream);
    paramZipInputStream = processPackageResource(getPackageResourceHref(paramZipInputStream), localBook, paramZipInputStream);
    localBook.setOpfResource(paramZipInputStream);
    localBook.setNcxResource(processNcxResource(paramZipInputStream, localBook));
    return postProcessBook(localBook);
  }
  
  public Book readEpubLazy(String paramString1, String paramString2)
    throws IOException
  {
    return readEpubLazy(paramString1, paramString2, Arrays.asList(MediatypeService.mediatypes));
  }
  
  public Book readEpubLazy(String paramString1, String paramString2, List<MediaType> paramList)
    throws IOException
  {
    Book localBook = new Book();
    paramString1 = readLazyResources(paramString1, paramString2, paramList);
    handleMimeType(localBook, paramString1);
    paramString1 = processPackageResource(getPackageResourceHref(paramString1), localBook, paramString1);
    localBook.setOpfResource(paramString1);
    localBook.setNcxResource(processNcxResource(paramString1, localBook));
    return postProcessBook(localBook);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\nl\siegmann\epublib\epub\EpubReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */