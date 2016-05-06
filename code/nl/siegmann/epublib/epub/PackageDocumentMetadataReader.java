package nl.siegmann.epublib.epub;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.namespace.QName;
import nl.siegmann.epublib.domain.Author;
import nl.siegmann.epublib.domain.Date;
import nl.siegmann.epublib.domain.Identifier;
import nl.siegmann.epublib.domain.Metadata;
import nl.siegmann.epublib.domain.Resources;
import nl.siegmann.epublib.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

class PackageDocumentMetadataReader
  extends PackageDocumentBase
{
  private static final Logger log = LoggerFactory.getLogger(PackageDocumentMetadataReader.class);
  
  private static Author createAuthor(Element paramElement)
  {
    Object localObject = DOMUtil.getTextChildrenContent(paramElement);
    if (StringUtil.isBlank((String)localObject)) {
      return null;
    }
    int i = ((String)localObject).lastIndexOf(' ');
    if (i < 0) {}
    for (localObject = new Author((String)localObject);; localObject = new Author(((String)localObject).substring(0, i), ((String)localObject).substring(i + 1)))
    {
      ((Author)localObject).setRole(paramElement.getAttributeNS("http://www.idpf.org/2007/opf", "role"));
      return (Author)localObject;
    }
  }
  
  private static String getBookIdId(Document paramDocument)
  {
    paramDocument = DOMUtil.getFirstElementByTagNameNS(paramDocument.getDocumentElement(), "http://www.idpf.org/2007/opf", "package");
    if (paramDocument == null) {
      return null;
    }
    return paramDocument.getAttributeNS("http://www.idpf.org/2007/opf", "unique-identifier");
  }
  
  private static List<Author> readAuthors(String paramString, Element paramElement)
  {
    paramString = paramElement.getElementsByTagNameNS("http://purl.org/dc/elements/1.1/", paramString);
    paramElement = new ArrayList(paramString.getLength());
    int i = 0;
    while (i < paramString.getLength())
    {
      Author localAuthor = createAuthor((Element)paramString.item(i));
      if (localAuthor != null) {
        paramElement.add(localAuthor);
      }
      i += 1;
    }
    return paramElement;
  }
  
  private static List<Author> readContributors(Element paramElement)
  {
    return readAuthors("contributor", paramElement);
  }
  
  private static List<Author> readCreators(Element paramElement)
  {
    return readAuthors("creator", paramElement);
  }
  
  private static List<Date> readDates(Element paramElement)
  {
    paramElement = paramElement.getElementsByTagNameNS("http://purl.org/dc/elements/1.1/", "date");
    ArrayList localArrayList = new ArrayList(paramElement.getLength());
    int i = 0;
    for (;;)
    {
      if (i < paramElement.getLength())
      {
        Element localElement = (Element)paramElement.item(i);
        try
        {
          localArrayList.add(new Date(DOMUtil.getTextChildrenContent(localElement), localElement.getAttributeNS("http://www.idpf.org/2007/opf", "event")));
          i += 1;
        }
        catch (IllegalArgumentException localIllegalArgumentException)
        {
          for (;;)
          {
            log.error(localIllegalArgumentException.getMessage());
          }
        }
      }
    }
    return localArrayList;
  }
  
  private static List<Identifier> readIdentifiers(Element paramElement)
  {
    NodeList localNodeList = paramElement.getElementsByTagNameNS("http://purl.org/dc/elements/1.1/", "identifier");
    if (localNodeList.getLength() == 0)
    {
      log.error("Package does not contain element identifier");
      paramElement = new ArrayList();
    }
    String str1;
    ArrayList localArrayList;
    int i;
    do
    {
      return paramElement;
      str1 = getBookIdId(paramElement.getOwnerDocument());
      localArrayList = new ArrayList(localNodeList.getLength());
      i = 0;
      paramElement = localArrayList;
    } while (i >= localNodeList.getLength());
    paramElement = (Element)localNodeList.item(i);
    Object localObject = paramElement.getAttributeNS("http://www.idpf.org/2007/opf", "scheme");
    String str2 = DOMUtil.getTextChildrenContent(paramElement);
    if (StringUtil.isBlank(str2)) {}
    for (;;)
    {
      i += 1;
      break;
      localObject = new Identifier((String)localObject, str2);
      if (paramElement.getAttribute("id").equals(str1)) {
        ((Identifier)localObject).setBookId(true);
      }
      localArrayList.add(localObject);
    }
  }
  
  public static Metadata readMetadata(Document paramDocument, Resources paramResources)
  {
    paramResources = new Metadata();
    paramDocument = DOMUtil.getFirstElementByTagNameNS(paramDocument.getDocumentElement(), "http://www.idpf.org/2007/opf", "metadata");
    if (paramDocument == null) {
      log.error("Package does not contain element metadata");
    }
    do
    {
      return paramResources;
      paramResources.setTitles(DOMUtil.getElementsTextChild(paramDocument, "http://purl.org/dc/elements/1.1/", "title"));
      paramResources.setPublishers(DOMUtil.getElementsTextChild(paramDocument, "http://purl.org/dc/elements/1.1/", "publisher"));
      paramResources.setDescriptions(DOMUtil.getElementsTextChild(paramDocument, "http://purl.org/dc/elements/1.1/", "description"));
      paramResources.setRights(DOMUtil.getElementsTextChild(paramDocument, "http://purl.org/dc/elements/1.1/", "rights"));
      paramResources.setTypes(DOMUtil.getElementsTextChild(paramDocument, "http://purl.org/dc/elements/1.1/", "type"));
      paramResources.setSubjects(DOMUtil.getElementsTextChild(paramDocument, "http://purl.org/dc/elements/1.1/", "subject"));
      paramResources.setIdentifiers(readIdentifiers(paramDocument));
      paramResources.setAuthors(readCreators(paramDocument));
      paramResources.setContributors(readContributors(paramDocument));
      paramResources.setDates(readDates(paramDocument));
      paramResources.setOtherProperties(readOtherProperties(paramDocument));
      paramDocument = DOMUtil.getFirstElementByTagNameNS(paramDocument, "http://purl.org/dc/elements/1.1/", "language");
    } while (paramDocument == null);
    paramResources.setLanguage(DOMUtil.getTextChildrenContent(paramDocument));
    return paramResources;
  }
  
  private static Map<QName, String> readOtherProperties(Element paramElement)
  {
    HashMap localHashMap = new HashMap();
    paramElement = paramElement.getElementsByTagNameNS("http://www.idpf.org/2007/opf", "meta");
    int i = 0;
    while (i < paramElement.getLength())
    {
      Object localObject1 = paramElement.item(i);
      Object localObject2 = ((Node)localObject1).getAttributes().getNamedItem("property");
      if (localObject2 != null)
      {
        localObject2 = ((Node)localObject2).getNodeValue();
        localObject1 = ((Node)localObject1).getTextContent();
        localHashMap.put(new QName((String)localObject2), localObject1);
      }
      i += 1;
    }
    return localHashMap;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\nl\siegmann\epublib\epub\PackageDocumentMetadataReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */