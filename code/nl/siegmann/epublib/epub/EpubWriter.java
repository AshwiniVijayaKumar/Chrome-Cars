package nl.siegmann.epublib.epub;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Collection;
import java.util.Iterator;
import java.util.zip.CRC32;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.MediaType;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.domain.Resources;
import nl.siegmann.epublib.domain.Spine;
import nl.siegmann.epublib.service.MediatypeService;
import nl.siegmann.epublib.util.IOUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xmlpull.v1.XmlSerializer;

public class EpubWriter
{
  static final String EMPTY_NAMESPACE_PREFIX = "";
  private static final Logger log = LoggerFactory.getLogger(EpubWriter.class);
  private BookProcessor bookProcessor = BookProcessor.IDENTITY_BOOKPROCESSOR;
  
  public EpubWriter()
  {
    this(BookProcessor.IDENTITY_BOOKPROCESSOR);
  }
  
  public EpubWriter(BookProcessor paramBookProcessor)
  {
    this.bookProcessor = paramBookProcessor;
  }
  
  private long calculateCrc(byte[] paramArrayOfByte)
  {
    CRC32 localCRC32 = new CRC32();
    localCRC32.update(paramArrayOfByte);
    return localCRC32.getValue();
  }
  
  private void initTOCResource(Book paramBook)
  {
    try
    {
      Resource localResource1 = NCXDocument.createNCXResource(paramBook);
      Resource localResource2 = paramBook.getSpine().getTocResource();
      if (localResource2 != null) {
        paramBook.getResources().remove(localResource2.getHref());
      }
      paramBook.getSpine().setTocResource(localResource1);
      paramBook.getResources().add(localResource1);
      return;
    }
    catch (Exception paramBook)
    {
      log.error("Error writing table of contents: " + paramBook.getClass().getName() + ": " + paramBook.getMessage());
    }
  }
  
  private Book processBook(Book paramBook)
  {
    Book localBook = paramBook;
    if (this.bookProcessor != null) {
      localBook = this.bookProcessor.processBook(paramBook);
    }
    return localBook;
  }
  
  private void writeContainer(ZipOutputStream paramZipOutputStream)
    throws IOException
  {
    paramZipOutputStream.putNextEntry(new ZipEntry("META-INF/container.xml"));
    paramZipOutputStream = new OutputStreamWriter(paramZipOutputStream);
    paramZipOutputStream.write("<?xml version=\"1.0\"?>\n");
    paramZipOutputStream.write("<container version=\"1.0\" xmlns=\"urn:oasis:names:tc:opendocument:xmlns:container\">\n");
    paramZipOutputStream.write("\t<rootfiles>\n");
    paramZipOutputStream.write("\t\t<rootfile full-path=\"OEBPS/content.opf\" media-type=\"application/oebps-package+xml\"/>\n");
    paramZipOutputStream.write("\t</rootfiles>\n");
    paramZipOutputStream.write("</container>");
    paramZipOutputStream.flush();
  }
  
  private void writeMimeType(ZipOutputStream paramZipOutputStream)
    throws IOException
  {
    ZipEntry localZipEntry = new ZipEntry("mimetype");
    localZipEntry.setMethod(0);
    byte[] arrayOfByte = MediatypeService.EPUB.getName().getBytes();
    localZipEntry.setSize(arrayOfByte.length);
    localZipEntry.setCrc(calculateCrc(arrayOfByte));
    paramZipOutputStream.putNextEntry(localZipEntry);
    paramZipOutputStream.write(arrayOfByte);
  }
  
  private void writePackageDocument(Book paramBook, ZipOutputStream paramZipOutputStream)
    throws IOException
  {
    paramZipOutputStream.putNextEntry(new ZipEntry("OEBPS/content.opf"));
    paramZipOutputStream = EpubProcessorSupport.createXmlSerializer(paramZipOutputStream);
    PackageDocumentWriter.write(this, paramZipOutputStream, paramBook);
    paramZipOutputStream.flush();
  }
  
  private void writeResource(Resource paramResource, ZipOutputStream paramZipOutputStream)
    throws IOException
  {
    if (paramResource == null) {
      return;
    }
    try
    {
      paramZipOutputStream.putNextEntry(new ZipEntry("OEBPS/" + paramResource.getHref()));
      paramResource = paramResource.getInputStream();
      IOUtil.copy(paramResource, paramZipOutputStream);
      paramResource.close();
      return;
    }
    catch (Exception paramResource)
    {
      log.error(paramResource.getMessage(), paramResource);
    }
  }
  
  private void writeResources(Book paramBook, ZipOutputStream paramZipOutputStream)
    throws IOException
  {
    paramBook = paramBook.getResources().getAll().iterator();
    while (paramBook.hasNext()) {
      writeResource((Resource)paramBook.next(), paramZipOutputStream);
    }
  }
  
  public BookProcessor getBookProcessor()
  {
    return this.bookProcessor;
  }
  
  String getNcxHref()
  {
    return "toc.ncx";
  }
  
  String getNcxId()
  {
    return "ncx";
  }
  
  String getNcxMediaType()
  {
    return "application/x-dtbncx+xml";
  }
  
  public void setBookProcessor(BookProcessor paramBookProcessor)
  {
    this.bookProcessor = paramBookProcessor;
  }
  
  public void write(Book paramBook, OutputStream paramOutputStream)
    throws IOException
  {
    paramBook = processBook(paramBook);
    paramOutputStream = new ZipOutputStream(paramOutputStream);
    writeMimeType(paramOutputStream);
    writeContainer(paramOutputStream);
    initTOCResource(paramBook);
    writeResources(paramBook, paramOutputStream);
    writePackageDocument(paramBook, paramOutputStream);
    paramOutputStream.close();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\nl\siegmann\epublib\epub\EpubWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */