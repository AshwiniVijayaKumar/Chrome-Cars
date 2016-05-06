package nl.siegmann.epublib.epub;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import nl.siegmann.epublib.domain.Author;
import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Identifier;
import nl.siegmann.epublib.domain.Metadata;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.domain.Resources;
import nl.siegmann.epublib.domain.Spine;
import nl.siegmann.epublib.domain.TOCReference;
import nl.siegmann.epublib.domain.TableOfContents;
import nl.siegmann.epublib.service.MediatypeService;
import nl.siegmann.epublib.util.ResourceUtil;
import nl.siegmann.epublib.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xmlpull.v1.XmlSerializer;

public class NCXDocument
{
  public static final String DEFAULT_NCX_HREF = "toc.ncx";
  public static final String NAMESPACE_NCX = "http://www.daisy.org/z3986/2005/ncx/";
  public static final String NCX_ITEM_ID = "ncx";
  public static final String PREFIX_DTB = "dtb";
  public static final String PREFIX_NCX = "ncx";
  private static final Logger log = LoggerFactory.getLogger(NCXDocument.class);
  
  public static Resource createNCXResource(List<Identifier> paramList, String paramString, List<Author> paramList1, TableOfContents paramTableOfContents)
    throws IllegalArgumentException, IllegalStateException, IOException
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    write(EpubProcessorSupport.createXmlSerializer(localByteArrayOutputStream), paramList, paramString, paramList1, paramTableOfContents);
    return new Resource("ncx", localByteArrayOutputStream.toByteArray(), "toc.ncx", MediatypeService.NCX);
  }
  
  public static Resource createNCXResource(Book paramBook)
    throws IllegalArgumentException, IllegalStateException, IOException
  {
    return createNCXResource(paramBook.getMetadata().getIdentifiers(), paramBook.getTitle(), paramBook.getMetadata().getAuthors(), paramBook.getTableOfContents());
  }
  
  public static Resource read(Book paramBook, EpubReader paramEpubReader)
  {
    paramEpubReader = null;
    if (paramBook.getSpine().getTocResource() == null)
    {
      log.error("Book does not contain a table of contents file");
      return null;
    }
    try
    {
      Resource localResource = paramBook.getSpine().getTocResource();
      if (localResource == null) {
        return localResource;
      }
      paramEpubReader = localResource;
      paramBook.setTableOfContents(new TableOfContents(readTOCReferences(DOMUtil.getFirstElementByTagNameNS(ResourceUtil.getAsDocument(localResource).getDocumentElement(), "http://www.daisy.org/z3986/2005/ncx/", "navMap").getChildNodes(), paramBook)));
      paramEpubReader = localResource;
    }
    catch (Exception paramBook)
    {
      for (;;)
      {
        log.error(paramBook.getMessage(), paramBook);
      }
    }
    return paramEpubReader;
  }
  
  private static String readNavLabel(Element paramElement)
  {
    return DOMUtil.getTextChildrenContent(DOMUtil.getFirstElementByTagNameNS(DOMUtil.getFirstElementByTagNameNS(paramElement, "http://www.daisy.org/z3986/2005/ncx/", "navLabel"), "http://www.daisy.org/z3986/2005/ncx/", "text"));
  }
  
  private static String readNavReference(Element paramElement)
  {
    paramElement = DOMUtil.getAttribute(DOMUtil.getFirstElementByTagNameNS(paramElement, "http://www.daisy.org/z3986/2005/ncx/", "content"), "http://www.daisy.org/z3986/2005/ncx/", "src");
    try
    {
      String str = URLDecoder.decode(paramElement, "UTF-8");
      return str;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      log.error(localUnsupportedEncodingException.getMessage());
    }
    return paramElement;
  }
  
  private static TOCReference readTOCReference(Element paramElement, Book paramBook)
  {
    Object localObject = readNavLabel(paramElement);
    String str2 = readNavReference(paramElement);
    String str1 = StringUtil.substringBefore(str2, '#');
    str2 = StringUtil.substringAfter(str2, '#');
    Resource localResource = paramBook.getResources().getByHref(str1);
    if (localResource == null) {
      log.error("Resource with href " + str1 + " in NCX document not found");
    }
    localObject = new TOCReference((String)localObject, localResource, str2);
    readTOCReferences(paramElement.getChildNodes(), paramBook);
    ((TOCReference)localObject).setChildren(readTOCReferences(paramElement.getChildNodes(), paramBook));
    return (TOCReference)localObject;
  }
  
  private static List<TOCReference> readTOCReferences(NodeList paramNodeList, Book paramBook)
  {
    if (paramNodeList == null) {
      localObject = new ArrayList();
    }
    ArrayList localArrayList;
    int i;
    do
    {
      return (List<TOCReference>)localObject;
      localArrayList = new ArrayList(paramNodeList.getLength());
      i = 0;
      localObject = localArrayList;
    } while (i >= paramNodeList.getLength());
    Object localObject = paramNodeList.item(i);
    if (((Node)localObject).getNodeType() != 1) {}
    for (;;)
    {
      i += 1;
      break;
      if (((Node)localObject).getLocalName().equals("navPoint")) {
        localArrayList.add(readTOCReference((Element)localObject, paramBook));
      }
    }
  }
  
  public static void write(EpubWriter paramEpubWriter, Book paramBook, ZipOutputStream paramZipOutputStream)
    throws IOException
  {
    paramZipOutputStream.putNextEntry(new ZipEntry(paramBook.getSpine().getTocResource().getHref()));
    paramEpubWriter = EpubProcessorSupport.createXmlSerializer(paramZipOutputStream);
    write(paramEpubWriter, paramBook);
    paramEpubWriter.flush();
  }
  
  public static void write(XmlSerializer paramXmlSerializer, List<Identifier> paramList, String paramString, List<Author> paramList1, TableOfContents paramTableOfContents)
    throws IllegalArgumentException, IllegalStateException, IOException
  {
    paramXmlSerializer.startDocument("UTF-8", Boolean.valueOf(false));
    paramXmlSerializer.setPrefix("", "http://www.daisy.org/z3986/2005/ncx/");
    paramXmlSerializer.startTag("http://www.daisy.org/z3986/2005/ncx/", "ncx");
    paramXmlSerializer.attribute("", "version", "2005-1");
    paramXmlSerializer.startTag("http://www.daisy.org/z3986/2005/ncx/", "head");
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      Identifier localIdentifier = (Identifier)paramList.next();
      writeMetaElement(localIdentifier.getScheme(), localIdentifier.getValue(), paramXmlSerializer);
    }
    writeMetaElement("generator", "EPUBLib version 3.0", paramXmlSerializer);
    writeMetaElement("depth", String.valueOf(paramTableOfContents.calculateDepth()), paramXmlSerializer);
    writeMetaElement("totalPageCount", "0", paramXmlSerializer);
    writeMetaElement("maxPageNumber", "0", paramXmlSerializer);
    paramXmlSerializer.endTag("http://www.daisy.org/z3986/2005/ncx/", "head");
    paramXmlSerializer.startTag("http://www.daisy.org/z3986/2005/ncx/", "docTitle");
    paramXmlSerializer.startTag("http://www.daisy.org/z3986/2005/ncx/", "text");
    paramXmlSerializer.text(StringUtil.defaultIfNull(paramString));
    paramXmlSerializer.endTag("http://www.daisy.org/z3986/2005/ncx/", "text");
    paramXmlSerializer.endTag("http://www.daisy.org/z3986/2005/ncx/", "docTitle");
    paramList = paramList1.iterator();
    while (paramList.hasNext())
    {
      paramString = (Author)paramList.next();
      paramXmlSerializer.startTag("http://www.daisy.org/z3986/2005/ncx/", "docAuthor");
      paramXmlSerializer.startTag("http://www.daisy.org/z3986/2005/ncx/", "text");
      paramXmlSerializer.text(paramString.getLastname() + ", " + paramString.getFirstname());
      paramXmlSerializer.endTag("http://www.daisy.org/z3986/2005/ncx/", "text");
      paramXmlSerializer.endTag("http://www.daisy.org/z3986/2005/ncx/", "docAuthor");
    }
    paramXmlSerializer.startTag("http://www.daisy.org/z3986/2005/ncx/", "navMap");
    writeNavPoints(paramTableOfContents.getTocReferences(), 1, paramXmlSerializer);
    paramXmlSerializer.endTag("http://www.daisy.org/z3986/2005/ncx/", "navMap");
    paramXmlSerializer.endTag("http://www.daisy.org/z3986/2005/ncx/", "ncx");
    paramXmlSerializer.endDocument();
  }
  
  public static void write(XmlSerializer paramXmlSerializer, Book paramBook)
    throws IllegalArgumentException, IllegalStateException, IOException
  {
    write(paramXmlSerializer, paramBook.getMetadata().getIdentifiers(), paramBook.getTitle(), paramBook.getMetadata().getAuthors(), paramBook.getTableOfContents());
  }
  
  private static void writeMetaElement(String paramString1, String paramString2, XmlSerializer paramXmlSerializer)
    throws IllegalArgumentException, IllegalStateException, IOException
  {
    paramXmlSerializer.startTag("http://www.daisy.org/z3986/2005/ncx/", "meta");
    paramXmlSerializer.attribute("", "name", "dtb:" + paramString1);
    paramXmlSerializer.attribute("", "content", paramString2);
    paramXmlSerializer.endTag("http://www.daisy.org/z3986/2005/ncx/", "meta");
  }
  
  private static void writeNavPointEnd(TOCReference paramTOCReference, XmlSerializer paramXmlSerializer)
    throws IllegalArgumentException, IllegalStateException, IOException
  {
    paramXmlSerializer.endTag("http://www.daisy.org/z3986/2005/ncx/", "navPoint");
  }
  
  private static void writeNavPointStart(TOCReference paramTOCReference, int paramInt, XmlSerializer paramXmlSerializer)
    throws IllegalArgumentException, IllegalStateException, IOException
  {
    paramXmlSerializer.startTag("http://www.daisy.org/z3986/2005/ncx/", "navPoint");
    paramXmlSerializer.attribute("", "id", "navPoint-" + paramInt);
    paramXmlSerializer.attribute("", "playOrder", String.valueOf(paramInt));
    paramXmlSerializer.attribute("", "class", "chapter");
    paramXmlSerializer.startTag("http://www.daisy.org/z3986/2005/ncx/", "navLabel");
    paramXmlSerializer.startTag("http://www.daisy.org/z3986/2005/ncx/", "text");
    paramXmlSerializer.text(paramTOCReference.getTitle());
    paramXmlSerializer.endTag("http://www.daisy.org/z3986/2005/ncx/", "text");
    paramXmlSerializer.endTag("http://www.daisy.org/z3986/2005/ncx/", "navLabel");
    paramXmlSerializer.startTag("http://www.daisy.org/z3986/2005/ncx/", "content");
    paramXmlSerializer.attribute("", "src", paramTOCReference.getCompleteHref());
    paramXmlSerializer.endTag("http://www.daisy.org/z3986/2005/ncx/", "content");
  }
  
  private static int writeNavPoints(List<TOCReference> paramList, int paramInt, XmlSerializer paramXmlSerializer)
    throws IllegalArgumentException, IllegalStateException, IOException
  {
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      TOCReference localTOCReference = (TOCReference)paramList.next();
      if (localTOCReference.getResource() == null)
      {
        paramInt = writeNavPoints(localTOCReference.getChildren(), paramInt, paramXmlSerializer);
      }
      else
      {
        writeNavPointStart(localTOCReference, paramInt, paramXmlSerializer);
        int i = paramInt + 1;
        paramInt = i;
        if (!localTOCReference.getChildren().isEmpty()) {
          paramInt = writeNavPoints(localTOCReference.getChildren(), i, paramXmlSerializer);
        }
        writeNavPointEnd(localTOCReference, paramXmlSerializer);
      }
    }
    return paramInt;
  }
  
  private static abstract interface NCXAttributeValues
  {
    public static final String chapter = "chapter";
    public static final String version = "2005-1";
  }
  
  private static abstract interface NCXAttributes
  {
    public static final String clazz = "class";
    public static final String content = "content";
    public static final String id = "id";
    public static final String name = "name";
    public static final String playOrder = "playOrder";
    public static final String src = "src";
    public static final String version = "version";
  }
  
  private static abstract interface NCXTags
  {
    public static final String content = "content";
    public static final String docAuthor = "docAuthor";
    public static final String docTitle = "docTitle";
    public static final String head = "head";
    public static final String meta = "meta";
    public static final String navLabel = "navLabel";
    public static final String navMap = "navMap";
    public static final String navPoint = "navPoint";
    public static final String ncx = "ncx";
    public static final String text = "text";
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\nl\siegmann\epublib\epub\NCXDocument.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */