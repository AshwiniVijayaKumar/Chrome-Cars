package nl.siegmann.epublib.epub;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.xml.parsers.ParserConfigurationException;
import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Guide;
import nl.siegmann.epublib.domain.GuideReference;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.domain.Resources;
import nl.siegmann.epublib.domain.Spine;
import nl.siegmann.epublib.domain.SpineReference;
import nl.siegmann.epublib.service.MediatypeService;
import nl.siegmann.epublib.util.ResourceUtil;
import nl.siegmann.epublib.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class PackageDocumentReader
  extends PackageDocumentBase
{
  private static final String[] POSSIBLE_NCX_ITEM_IDS = { "toc", "ncx" };
  private static final Logger log = LoggerFactory.getLogger(PackageDocumentReader.class);
  
  static Set<String> findCoverHrefs(Document paramDocument)
  {
    HashSet localHashSet = new HashSet();
    String str1 = DOMUtil.getFindAttributeValue(paramDocument, "http://www.idpf.org/2007/opf", "meta", "name", "cover", "content");
    if (StringUtil.isNotBlank(str1))
    {
      String str2 = DOMUtil.getFindAttributeValue(paramDocument, "http://www.idpf.org/2007/opf", "item", "id", str1, "href");
      if (!StringUtil.isNotBlank(str2)) {
        break label91;
      }
      localHashSet.add(str2);
    }
    for (;;)
    {
      paramDocument = DOMUtil.getFindAttributeValue(paramDocument, "http://www.idpf.org/2007/opf", "reference", "type", "cover", "href");
      if (StringUtil.isNotBlank(paramDocument)) {
        localHashSet.add(paramDocument);
      }
      return localHashSet;
      label91:
      localHashSet.add(str1);
    }
  }
  
  private static Resource findTableOfContentsResource(Element paramElement, Resources paramResources)
  {
    String str = DOMUtil.getAttribute(paramElement, "http://www.idpf.org/2007/opf", "toc");
    paramElement = null;
    if (StringUtil.isNotBlank(str)) {
      paramElement = paramResources.getByIdOrHref(str);
    }
    if (paramElement != null) {
      return paramElement;
    }
    int i = 0;
    while (i < POSSIBLE_NCX_ITEM_IDS.length)
    {
      paramElement = paramResources.getByIdOrHref(POSSIBLE_NCX_ITEM_IDS[i]);
      if (paramElement != null) {
        return paramElement;
      }
      paramElement = paramResources.getByIdOrHref(POSSIBLE_NCX_ITEM_IDS[i].toUpperCase());
      if (paramElement != null) {
        return paramElement;
      }
      i += 1;
    }
    paramElement = paramResources.findFirstResourceByMediaType(MediatypeService.NCX);
    if (paramElement == null) {
      log.error("Could not find table of contents resource. Tried resource with id '" + str + "', " + "toc" + ", " + "toc".toUpperCase() + " and any NCX resource.");
    }
    return paramElement;
  }
  
  private static Resources fixHrefs(String paramString, Resources paramResources)
  {
    int i = paramString.lastIndexOf('/');
    if (i < 0) {
      return paramResources;
    }
    paramString = new Resources();
    paramResources = paramResources.getAll().iterator();
    while (paramResources.hasNext())
    {
      Resource localResource = (Resource)paramResources.next();
      if ((StringUtil.isNotBlank(localResource.getHref())) || (localResource.getHref().length() > i)) {
        localResource.setHref(localResource.getHref().substring(i + 1));
      }
      paramString.add(localResource);
    }
    return paramString;
  }
  
  private static Spine generateSpineFromResources(Resources paramResources)
  {
    Spine localSpine = new Spine();
    Object localObject = new ArrayList();
    ((List)localObject).addAll(paramResources.getAllHrefs());
    Collections.sort((List)localObject, String.CASE_INSENSITIVE_ORDER);
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      Resource localResource = paramResources.getByHref((String)((Iterator)localObject).next());
      if (localResource.getMediaType() == MediatypeService.NCX) {
        localSpine.setTocResource(localResource);
      } else if (localResource.getMediaType() == MediatypeService.XHTML) {
        localSpine.addSpineReference(new SpineReference(localResource));
      }
    }
    return localSpine;
  }
  
  public static void read(Resource paramResource, EpubReader paramEpubReader, Book paramBook, Resources paramResources)
    throws UnsupportedEncodingException, SAXException, IOException, ParserConfigurationException
  {
    Document localDocument = ResourceUtil.getAsDocument(paramResource);
    paramResource = paramResource.getHref();
    paramResources = fixHrefs(paramResource, paramResources);
    readGuide(localDocument, paramEpubReader, paramBook, paramResources);
    HashMap localHashMap = new HashMap();
    paramBook.setResources(readManifest(localDocument, paramResource, paramEpubReader, paramResources, localHashMap));
    readCover(localDocument, paramBook);
    paramBook.setMetadata(PackageDocumentMetadataReader.readMetadata(localDocument, paramBook.getResources()));
    paramBook.setSpine(readSpine(localDocument, paramEpubReader, paramBook.getResources(), localHashMap));
    if ((paramBook.getCoverPage() == null) && (paramBook.getSpine().size() > 0)) {
      paramBook.setCoverPage(paramBook.getSpine().getResource(0));
    }
  }
  
  private static void readCover(Document paramDocument, Book paramBook)
  {
    paramDocument = findCoverHrefs(paramDocument).iterator();
    while (paramDocument.hasNext())
    {
      String str = (String)paramDocument.next();
      Resource localResource = paramBook.getResources().getByHref(str);
      if (localResource == null) {
        log.error("Cover resource " + str + " not found");
      } else if (localResource.getMediaType() == MediatypeService.XHTML) {
        paramBook.setCoverPage(localResource);
      } else if (MediatypeService.isBitmapImage(localResource.getMediaType())) {
        paramBook.setCoverImage(localResource);
      }
    }
  }
  
  private static void readGuide(Document paramDocument, EpubReader paramEpubReader, Book paramBook, Resources paramResources)
  {
    paramEpubReader = DOMUtil.getFirstElementByTagNameNS(paramDocument.getDocumentElement(), "http://www.idpf.org/2007/opf", "guide");
    if (paramEpubReader == null) {
      return;
    }
    paramDocument = paramBook.getGuide();
    paramEpubReader = paramEpubReader.getElementsByTagNameNS("http://www.idpf.org/2007/opf", "reference");
    int i = 0;
    label39:
    Object localObject;
    if (i < paramEpubReader.getLength())
    {
      localObject = (Element)paramEpubReader.item(i);
      paramBook = DOMUtil.getAttribute((Element)localObject, "http://www.idpf.org/2007/opf", "href");
      if (!StringUtil.isBlank(paramBook)) {
        break label89;
      }
    }
    for (;;)
    {
      i += 1;
      break label39;
      break;
      label89:
      Resource localResource = paramResources.getByHref(StringUtil.substringBefore(paramBook, '#'));
      if (localResource == null)
      {
        log.error("Guide is referencing resource with href " + paramBook + " which could not be found");
      }
      else
      {
        String str = DOMUtil.getAttribute((Element)localObject, "http://www.idpf.org/2007/opf", "type");
        if (StringUtil.isBlank(str))
        {
          log.error("Guide is referencing resource with href " + paramBook + " which is missing the 'type' attribute");
        }
        else
        {
          localObject = DOMUtil.getAttribute((Element)localObject, "http://www.idpf.org/2007/opf", "title");
          if (!GuideReference.COVER.equalsIgnoreCase(str)) {
            paramDocument.addReference(new GuideReference(localResource, str, (String)localObject, StringUtil.substringAfter(paramBook, '#')));
          }
        }
      }
    }
  }
  
  private static Resources readManifest(Document paramDocument, String paramString, EpubReader paramEpubReader, Resources paramResources, Map<String, String> paramMap)
  {
    paramDocument = DOMUtil.getFirstElementByTagNameNS(paramDocument.getDocumentElement(), "http://www.idpf.org/2007/opf", "manifest");
    paramEpubReader = new Resources();
    if (paramDocument == null) {
      log.error("Package document does not contain element manifest");
    }
    for (;;)
    {
      return paramEpubReader;
      NodeList localNodeList = paramDocument.getElementsByTagNameNS("http://www.idpf.org/2007/opf", "item");
      int i = 0;
      while (i < localNodeList.getLength())
      {
        localObject = (Element)localNodeList.item(i);
        str = DOMUtil.getAttribute((Element)localObject, "http://www.idpf.org/2007/opf", "id");
        paramDocument = DOMUtil.getAttribute((Element)localObject, "http://www.idpf.org/2007/opf", "href");
        try
        {
          paramString = URLDecoder.decode(paramDocument, "UTF-8");
          paramDocument = paramString;
        }
        catch (UnsupportedEncodingException paramString)
        {
          for (;;)
          {
            log.error(paramString.getMessage());
            continue;
            paramString.setId(str);
            paramDocument = MediatypeService.getMediaTypeByName((String)localObject);
            if (paramDocument != null) {
              paramString.setMediaType(paramDocument);
            }
            paramEpubReader.add(paramString);
            paramMap.put(str, paramString.getId());
          }
        }
        localObject = DOMUtil.getAttribute((Element)localObject, "http://www.idpf.org/2007/opf", "media-type");
        paramString = paramResources.remove(paramDocument);
        if (paramString != null) {
          break label193;
        }
        log.error("resource with href '" + paramDocument + "' not found");
        i += 1;
      }
    }
  }
  
  private static Spine readSpine(Document paramDocument, EpubReader paramEpubReader, Resources paramResources, Map<String, String> paramMap)
  {
    paramEpubReader = DOMUtil.getFirstElementByTagNameNS(paramDocument.getDocumentElement(), "http://www.idpf.org/2007/opf", "spine");
    if (paramEpubReader == null)
    {
      log.error("Element spine not found in package document, generating one automatically");
      return generateSpineFromResources(paramResources);
    }
    Spine localSpine = new Spine();
    localSpine.setTocResource(findTableOfContentsResource(paramEpubReader, paramResources));
    NodeList localNodeList = paramDocument.getElementsByTagNameNS("http://www.idpf.org/2007/opf", "itemref");
    ArrayList localArrayList = new ArrayList(localNodeList.getLength());
    int i = 0;
    if (i < localNodeList.getLength())
    {
      Element localElement = (Element)localNodeList.item(i);
      paramEpubReader = DOMUtil.getAttribute(localElement, "http://www.idpf.org/2007/opf", "idref");
      if (StringUtil.isBlank(paramEpubReader)) {
        log.error("itemref with missing or empty idref");
      }
      for (;;)
      {
        i += 1;
        break;
        String str = (String)paramMap.get(paramEpubReader);
        paramDocument = str;
        if (str == null) {
          paramDocument = paramEpubReader;
        }
        paramEpubReader = paramResources.getByIdOrHref(paramDocument);
        if (paramEpubReader == null)
        {
          log.error("resource with id '" + paramDocument + "' not found");
        }
        else
        {
          paramDocument = new SpineReference(paramEpubReader);
          if ("no".equalsIgnoreCase(DOMUtil.getAttribute(localElement, "http://www.idpf.org/2007/opf", "linear"))) {
            paramDocument.setLinear(false);
          }
          localArrayList.add(paramDocument);
        }
      }
    }
    localSpine.setSpineReferences(localArrayList);
    return localSpine;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\nl\siegmann\epublib\epub\PackageDocumentReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */