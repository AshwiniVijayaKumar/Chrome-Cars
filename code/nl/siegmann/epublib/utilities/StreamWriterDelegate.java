package nl.siegmann.epublib.utilities;

import javax.xml.namespace.NamespaceContext;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public abstract class StreamWriterDelegate
  implements XMLStreamWriter
{
  protected XMLStreamWriter out;
  
  protected StreamWriterDelegate(XMLStreamWriter paramXMLStreamWriter)
  {
    this.out = paramXMLStreamWriter;
  }
  
  public void close()
    throws XMLStreamException
  {
    this.out.close();
  }
  
  public void flush()
    throws XMLStreamException
  {
    this.out.flush();
  }
  
  public NamespaceContext getNamespaceContext()
  {
    return this.out.getNamespaceContext();
  }
  
  public String getPrefix(String paramString)
    throws XMLStreamException
  {
    return this.out.getPrefix(paramString);
  }
  
  public Object getProperty(String paramString)
    throws IllegalArgumentException
  {
    return this.out.getProperty(paramString);
  }
  
  public void setDefaultNamespace(String paramString)
    throws XMLStreamException
  {
    this.out.setDefaultNamespace(paramString);
  }
  
  public void setNamespaceContext(NamespaceContext paramNamespaceContext)
    throws XMLStreamException
  {
    this.out.setNamespaceContext(paramNamespaceContext);
  }
  
  public void setPrefix(String paramString1, String paramString2)
    throws XMLStreamException
  {
    this.out.setPrefix(paramString1, paramString2);
  }
  
  public void writeAttribute(String paramString1, String paramString2)
    throws XMLStreamException
  {
    this.out.writeAttribute(paramString1, paramString2);
  }
  
  public void writeAttribute(String paramString1, String paramString2, String paramString3)
    throws XMLStreamException
  {
    this.out.writeAttribute(paramString1, paramString2, paramString3);
  }
  
  public void writeAttribute(String paramString1, String paramString2, String paramString3, String paramString4)
    throws XMLStreamException
  {
    this.out.writeAttribute(paramString1, paramString2, paramString3, paramString4);
  }
  
  public void writeCData(String paramString)
    throws XMLStreamException
  {
    this.out.writeCData(paramString);
  }
  
  public void writeCharacters(String paramString)
    throws XMLStreamException
  {
    this.out.writeCharacters(paramString);
  }
  
  public void writeCharacters(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws XMLStreamException
  {
    this.out.writeCharacters(paramArrayOfChar, paramInt1, paramInt2);
  }
  
  public void writeComment(String paramString)
    throws XMLStreamException
  {
    this.out.writeComment(paramString);
  }
  
  public void writeDTD(String paramString)
    throws XMLStreamException
  {
    this.out.writeDTD(paramString);
  }
  
  public void writeDefaultNamespace(String paramString)
    throws XMLStreamException
  {
    this.out.writeDefaultNamespace(paramString);
  }
  
  public void writeEmptyElement(String paramString)
    throws XMLStreamException
  {
    this.out.writeEmptyElement(paramString);
  }
  
  public void writeEmptyElement(String paramString1, String paramString2)
    throws XMLStreamException
  {
    this.out.writeEmptyElement(paramString1, paramString2);
  }
  
  public void writeEmptyElement(String paramString1, String paramString2, String paramString3)
    throws XMLStreamException
  {
    this.out.writeEmptyElement(paramString1, paramString2, paramString3);
  }
  
  public void writeEndDocument()
    throws XMLStreamException
  {
    this.out.writeEndDocument();
  }
  
  public void writeEndElement()
    throws XMLStreamException
  {
    this.out.writeEndElement();
  }
  
  public void writeEntityRef(String paramString)
    throws XMLStreamException
  {
    this.out.writeEntityRef(paramString);
  }
  
  public void writeNamespace(String paramString1, String paramString2)
    throws XMLStreamException
  {
    this.out.writeNamespace(paramString1, paramString2);
  }
  
  public void writeProcessingInstruction(String paramString)
    throws XMLStreamException
  {
    this.out.writeProcessingInstruction(paramString);
  }
  
  public void writeProcessingInstruction(String paramString1, String paramString2)
    throws XMLStreamException
  {
    this.out.writeProcessingInstruction(paramString1, paramString2);
  }
  
  public void writeStartDocument()
    throws XMLStreamException
  {
    this.out.writeStartDocument();
  }
  
  public void writeStartDocument(String paramString)
    throws XMLStreamException
  {
    this.out.writeStartDocument(paramString);
  }
  
  public void writeStartDocument(String paramString1, String paramString2)
    throws XMLStreamException
  {
    this.out.writeStartDocument(paramString1, paramString2);
  }
  
  public void writeStartElement(String paramString)
    throws XMLStreamException
  {
    this.out.writeStartElement(paramString);
  }
  
  public void writeStartElement(String paramString1, String paramString2)
    throws XMLStreamException
  {
    this.out.writeStartElement(paramString1, paramString2);
  }
  
  public void writeStartElement(String paramString1, String paramString2, String paramString3)
    throws XMLStreamException
  {
    this.out.writeStartElement(paramString1, paramString2, paramString3);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\nl\siegmann\epublib\utilities\StreamWriterDelegate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */