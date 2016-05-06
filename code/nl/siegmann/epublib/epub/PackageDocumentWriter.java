package nl.siegmann.epublib.epub;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Guide;
import nl.siegmann.epublib.domain.GuideReference;
import nl.siegmann.epublib.domain.MediaType;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.domain.Resources;
import nl.siegmann.epublib.domain.Spine;
import nl.siegmann.epublib.domain.SpineReference;
import nl.siegmann.epublib.service.MediatypeService;
import nl.siegmann.epublib.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xmlpull.v1.XmlSerializer;

public class PackageDocumentWriter
  extends PackageDocumentBase
{
  private static final Logger log = LoggerFactory.getLogger(PackageDocumentWriter.class);
  
  private static void ensureCoverPageGuideReferenceWritten(Guide paramGuide, EpubWriter paramEpubWriter, XmlSerializer paramXmlSerializer)
    throws IllegalArgumentException, IllegalStateException, IOException
  {
    if (!paramGuide.getGuideReferencesByType(GuideReference.COVER).isEmpty()) {}
    while (paramGuide.getCoverPage() == null) {
      return;
    }
    writeGuideReference(new GuideReference(paramGuide.getCoverPage(), GuideReference.COVER, GuideReference.COVER), paramXmlSerializer);
  }
  
  private static List<Resource> getAllResourcesSortById(Book paramBook)
  {
    paramBook = new ArrayList(paramBook.getResources().getAll());
    Collections.sort(paramBook, new Comparator()
    {
      public int compare(Resource paramAnonymousResource1, Resource paramAnonymousResource2)
      {
        return paramAnonymousResource1.getId().compareToIgnoreCase(paramAnonymousResource2.getId());
      }
    });
    return paramBook;
  }
  
  public static void write(EpubWriter paramEpubWriter, XmlSerializer paramXmlSerializer, Book paramBook)
    throws IOException
  {
    try
    {
      paramXmlSerializer.startDocument("UTF-8", Boolean.valueOf(false));
      paramXmlSerializer.setPrefix("opf", "http://www.idpf.org/2007/opf");
      paramXmlSerializer.setPrefix("dc", "http://purl.org/dc/elements/1.1/");
      paramXmlSerializer.startTag("http://www.idpf.org/2007/opf", "package");
      paramXmlSerializer.attribute("", "version", "2.0");
      paramXmlSerializer.attribute("", "unique-identifier", "BookId");
      PackageDocumentMetadataWriter.writeMetaData(paramBook, paramXmlSerializer);
      writeManifest(paramBook, paramEpubWriter, paramXmlSerializer);
      writeSpine(paramBook, paramEpubWriter, paramXmlSerializer);
      writeGuide(paramBook, paramEpubWriter, paramXmlSerializer);
      paramXmlSerializer.endTag("http://www.idpf.org/2007/opf", "package");
      paramXmlSerializer.endDocument();
      paramXmlSerializer.flush();
      return;
    }
    catch (IOException paramEpubWriter)
    {
      paramEpubWriter.printStackTrace();
    }
  }
  
  private static void writeGuide(Book paramBook, EpubWriter paramEpubWriter, XmlSerializer paramXmlSerializer)
    throws IllegalArgumentException, IllegalStateException, IOException
  {
    paramXmlSerializer.startTag("http://www.idpf.org/2007/opf", "guide");
    ensureCoverPageGuideReferenceWritten(paramBook.getGuide(), paramEpubWriter, paramXmlSerializer);
    paramBook = paramBook.getGuide().getReferences().iterator();
    while (paramBook.hasNext()) {
      writeGuideReference((GuideReference)paramBook.next(), paramXmlSerializer);
    }
    paramXmlSerializer.endTag("http://www.idpf.org/2007/opf", "guide");
  }
  
  private static void writeGuideReference(GuideReference paramGuideReference, XmlSerializer paramXmlSerializer)
    throws IllegalArgumentException, IllegalStateException, IOException
  {
    if (paramGuideReference == null) {
      return;
    }
    paramXmlSerializer.startTag("http://www.idpf.org/2007/opf", "reference");
    paramXmlSerializer.attribute("", "type", paramGuideReference.getType());
    paramXmlSerializer.attribute("", "href", paramGuideReference.getCompleteHref());
    if (StringUtil.isNotBlank(paramGuideReference.getTitle())) {
      paramXmlSerializer.attribute("", "title", paramGuideReference.getTitle());
    }
    paramXmlSerializer.endTag("http://www.idpf.org/2007/opf", "reference");
  }
  
  private static void writeItem(Book paramBook, Resource paramResource, XmlSerializer paramXmlSerializer)
    throws IllegalArgumentException, IllegalStateException, IOException
  {
    if ((paramResource == null) || ((paramResource.getMediaType() == MediatypeService.NCX) && (paramBook.getSpine().getTocResource() != null))) {
      return;
    }
    if (StringUtil.isBlank(paramResource.getId()))
    {
      log.error("resource id must not be empty (href: " + paramResource.getHref() + ", mediatype:" + paramResource.getMediaType() + ")");
      return;
    }
    if (StringUtil.isBlank(paramResource.getHref()))
    {
      log.error("resource href must not be empty (id: " + paramResource.getId() + ", mediatype:" + paramResource.getMediaType() + ")");
      return;
    }
    if (paramResource.getMediaType() == null)
    {
      log.error("resource mediatype must not be empty (id: " + paramResource.getId() + ", href:" + paramResource.getHref() + ")");
      return;
    }
    paramXmlSerializer.startTag("http://www.idpf.org/2007/opf", "item");
    paramXmlSerializer.attribute("", "id", paramResource.getId());
    paramXmlSerializer.attribute("", "href", paramResource.getHref());
    paramXmlSerializer.attribute("", "media-type", paramResource.getMediaType().getName());
    paramXmlSerializer.endTag("http://www.idpf.org/2007/opf", "item");
  }
  
  private static void writeManifest(Book paramBook, EpubWriter paramEpubWriter, XmlSerializer paramXmlSerializer)
    throws IllegalArgumentException, IllegalStateException, IOException
  {
    paramXmlSerializer.startTag("http://www.idpf.org/2007/opf", "manifest");
    paramXmlSerializer.startTag("http://www.idpf.org/2007/opf", "item");
    paramXmlSerializer.attribute("", "id", paramEpubWriter.getNcxId());
    paramXmlSerializer.attribute("", "href", paramEpubWriter.getNcxHref());
    paramXmlSerializer.attribute("", "media-type", paramEpubWriter.getNcxMediaType());
    paramXmlSerializer.endTag("http://www.idpf.org/2007/opf", "item");
    paramEpubWriter = getAllResourcesSortById(paramBook).iterator();
    while (paramEpubWriter.hasNext()) {
      writeItem(paramBook, (Resource)paramEpubWriter.next(), paramXmlSerializer);
    }
    paramXmlSerializer.endTag("http://www.idpf.org/2007/opf", "manifest");
  }
  
  private static void writeSpine(Book paramBook, EpubWriter paramEpubWriter, XmlSerializer paramXmlSerializer)
    throws IllegalArgumentException, IllegalStateException, IOException
  {
    paramXmlSerializer.startTag("http://www.idpf.org/2007/opf", "spine");
    paramXmlSerializer.attribute("", "toc", paramBook.getSpine().getTocResource().getId());
    if ((paramBook.getCoverPage() != null) && (paramBook.getSpine().findFirstResourceById(paramBook.getCoverPage().getId()) < 0))
    {
      paramXmlSerializer.startTag("http://www.idpf.org/2007/opf", "itemref");
      paramXmlSerializer.attribute("", "idref", paramBook.getCoverPage().getId());
      paramXmlSerializer.attribute("", "linear", "no");
      paramXmlSerializer.endTag("http://www.idpf.org/2007/opf", "itemref");
    }
    writeSpineItems(paramBook.getSpine(), paramXmlSerializer);
    paramXmlSerializer.endTag("http://www.idpf.org/2007/opf", "spine");
  }
  
  private static void writeSpineItems(Spine paramSpine, XmlSerializer paramXmlSerializer)
    throws IllegalArgumentException, IllegalStateException, IOException
  {
    paramSpine = paramSpine.getSpineReferences().iterator();
    while (paramSpine.hasNext())
    {
      SpineReference localSpineReference = (SpineReference)paramSpine.next();
      paramXmlSerializer.startTag("http://www.idpf.org/2007/opf", "itemref");
      paramXmlSerializer.attribute("", "idref", localSpineReference.getResourceId());
      if (!localSpineReference.isLinear()) {
        paramXmlSerializer.attribute("", "linear", "no");
      }
      paramXmlSerializer.endTag("http://www.idpf.org/2007/opf", "itemref");
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\nl\siegmann\epublib\epub\PackageDocumentWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */