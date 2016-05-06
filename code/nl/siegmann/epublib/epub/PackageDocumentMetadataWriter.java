package nl.siegmann.epublib.epub;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.xml.namespace.QName;
import nl.siegmann.epublib.domain.Author;
import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Date;
import nl.siegmann.epublib.domain.Date.Event;
import nl.siegmann.epublib.domain.Identifier;
import nl.siegmann.epublib.domain.Metadata;
import nl.siegmann.epublib.domain.Relator;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.util.StringUtil;
import org.xmlpull.v1.XmlSerializer;

public class PackageDocumentMetadataWriter
  extends PackageDocumentBase
{
  private static void writeIdentifiers(List<Identifier> paramList, XmlSerializer paramXmlSerializer)
    throws IllegalArgumentException, IllegalStateException, IOException
  {
    Identifier localIdentifier1 = Identifier.getBookIdIdentifier(paramList);
    if (localIdentifier1 == null) {}
    for (;;)
    {
      return;
      paramXmlSerializer.startTag("http://purl.org/dc/elements/1.1/", "identifier");
      paramXmlSerializer.attribute("", "id", "BookId");
      paramXmlSerializer.attribute("http://www.idpf.org/2007/opf", "scheme", localIdentifier1.getScheme());
      paramXmlSerializer.text(localIdentifier1.getValue());
      paramXmlSerializer.endTag("http://purl.org/dc/elements/1.1/", "identifier");
      paramList = paramList.subList(1, paramList.size()).iterator();
      while (paramList.hasNext())
      {
        Identifier localIdentifier2 = (Identifier)paramList.next();
        if (localIdentifier2 != localIdentifier1)
        {
          paramXmlSerializer.startTag("http://purl.org/dc/elements/1.1/", "identifier");
          paramXmlSerializer.attribute("http://www.idpf.org/2007/opf", "scheme", localIdentifier2.getScheme());
          paramXmlSerializer.text(localIdentifier2.getValue());
          paramXmlSerializer.endTag("http://purl.org/dc/elements/1.1/", "identifier");
        }
      }
    }
  }
  
  public static void writeMetaData(Book paramBook, XmlSerializer paramXmlSerializer)
    throws IllegalArgumentException, IllegalStateException, IOException
  {
    paramXmlSerializer.startTag("http://www.idpf.org/2007/opf", "metadata");
    paramXmlSerializer.setPrefix("dc", "http://purl.org/dc/elements/1.1/");
    paramXmlSerializer.setPrefix("opf", "http://www.idpf.org/2007/opf");
    writeIdentifiers(paramBook.getMetadata().getIdentifiers(), paramXmlSerializer);
    writeSimpleMetdataElements("title", paramBook.getMetadata().getTitles(), paramXmlSerializer);
    writeSimpleMetdataElements("subject", paramBook.getMetadata().getSubjects(), paramXmlSerializer);
    writeSimpleMetdataElements("description", paramBook.getMetadata().getDescriptions(), paramXmlSerializer);
    writeSimpleMetdataElements("publisher", paramBook.getMetadata().getPublishers(), paramXmlSerializer);
    writeSimpleMetdataElements("type", paramBook.getMetadata().getTypes(), paramXmlSerializer);
    writeSimpleMetdataElements("rights", paramBook.getMetadata().getRights(), paramXmlSerializer);
    Iterator localIterator = paramBook.getMetadata().getAuthors().iterator();
    Object localObject;
    while (localIterator.hasNext())
    {
      localObject = (Author)localIterator.next();
      paramXmlSerializer.startTag("http://purl.org/dc/elements/1.1/", "creator");
      paramXmlSerializer.attribute("http://www.idpf.org/2007/opf", "role", ((Author)localObject).getRelator().getCode());
      paramXmlSerializer.attribute("http://www.idpf.org/2007/opf", "file-as", ((Author)localObject).getLastname() + ", " + ((Author)localObject).getFirstname());
      paramXmlSerializer.text(((Author)localObject).getFirstname() + " " + ((Author)localObject).getLastname());
      paramXmlSerializer.endTag("http://purl.org/dc/elements/1.1/", "creator");
    }
    localIterator = paramBook.getMetadata().getContributors().iterator();
    while (localIterator.hasNext())
    {
      localObject = (Author)localIterator.next();
      paramXmlSerializer.startTag("http://purl.org/dc/elements/1.1/", "contributor");
      paramXmlSerializer.attribute("http://www.idpf.org/2007/opf", "role", ((Author)localObject).getRelator().getCode());
      paramXmlSerializer.attribute("http://www.idpf.org/2007/opf", "file-as", ((Author)localObject).getLastname() + ", " + ((Author)localObject).getFirstname());
      paramXmlSerializer.text(((Author)localObject).getFirstname() + " " + ((Author)localObject).getLastname());
      paramXmlSerializer.endTag("http://purl.org/dc/elements/1.1/", "contributor");
    }
    localIterator = paramBook.getMetadata().getDates().iterator();
    while (localIterator.hasNext())
    {
      localObject = (Date)localIterator.next();
      paramXmlSerializer.startTag("http://purl.org/dc/elements/1.1/", "date");
      if (((Date)localObject).getEvent() != null) {
        paramXmlSerializer.attribute("http://www.idpf.org/2007/opf", "event", ((Date)localObject).getEvent().toString());
      }
      paramXmlSerializer.text(((Date)localObject).getValue());
      paramXmlSerializer.endTag("http://purl.org/dc/elements/1.1/", "date");
    }
    if (StringUtil.isNotBlank(paramBook.getMetadata().getLanguage()))
    {
      paramXmlSerializer.startTag("http://purl.org/dc/elements/1.1/", "language");
      paramXmlSerializer.text(paramBook.getMetadata().getLanguage());
      paramXmlSerializer.endTag("http://purl.org/dc/elements/1.1/", "language");
    }
    if (paramBook.getMetadata().getOtherProperties() != null)
    {
      localIterator = paramBook.getMetadata().getOtherProperties().entrySet().iterator();
      while (localIterator.hasNext())
      {
        localObject = (Map.Entry)localIterator.next();
        paramXmlSerializer.startTag(((QName)((Map.Entry)localObject).getKey()).getNamespaceURI(), ((QName)((Map.Entry)localObject).getKey()).getLocalPart());
        paramXmlSerializer.text((String)((Map.Entry)localObject).getValue());
        paramXmlSerializer.endTag(((QName)((Map.Entry)localObject).getKey()).getNamespaceURI(), ((QName)((Map.Entry)localObject).getKey()).getLocalPart());
      }
    }
    if (paramBook.getCoverImage() != null)
    {
      paramXmlSerializer.startTag("http://www.idpf.org/2007/opf", "meta");
      paramXmlSerializer.attribute("", "name", "cover");
      paramXmlSerializer.attribute("", "content", paramBook.getCoverImage().getId());
      paramXmlSerializer.endTag("http://www.idpf.org/2007/opf", "meta");
    }
    paramXmlSerializer.startTag("http://www.idpf.org/2007/opf", "meta");
    paramXmlSerializer.attribute("", "name", "generator");
    paramXmlSerializer.attribute("", "content", "EPUBLib version 3.0");
    paramXmlSerializer.endTag("http://www.idpf.org/2007/opf", "meta");
    paramXmlSerializer.endTag("http://www.idpf.org/2007/opf", "metadata");
  }
  
  private static void writeSimpleMetdataElements(String paramString, List<String> paramList, XmlSerializer paramXmlSerializer)
    throws IllegalArgumentException, IllegalStateException, IOException
  {
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      String str = (String)paramList.next();
      if (!StringUtil.isBlank(str))
      {
        paramXmlSerializer.startTag("http://purl.org/dc/elements/1.1/", paramString);
        paramXmlSerializer.text(str);
        paramXmlSerializer.endTag("http://purl.org/dc/elements/1.1/", paramString);
      }
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\nl\siegmann\epublib\epub\PackageDocumentMetadataWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */