package net.roarsoftware.xml;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomElement
{
  private Element e;
  
  public DomElement(Element paramElement)
  {
    this.e = paramElement;
  }
  
  public String getAttribute(String paramString)
  {
    if (this.e.hasAttribute(paramString)) {
      return this.e.getAttribute(paramString);
    }
    return null;
  }
  
  public DomElement getChild(String paramString)
  {
    paramString = this.e.getElementsByTagName(paramString);
    if (paramString.getLength() == 0) {}
    for (;;)
    {
      return null;
      int i = 0;
      int j = paramString.getLength();
      while (i < j)
      {
        Node localNode = paramString.item(i);
        if (localNode.getParentNode() == this.e) {
          return new DomElement((Element)localNode);
        }
        i += 1;
      }
    }
  }
  
  public String getChildText(String paramString)
  {
    paramString = getChild(paramString);
    if (paramString != null) {
      return paramString.getText();
    }
    return null;
  }
  
  public Collection<DomElement> getChildren()
  {
    return getChildren("*");
  }
  
  public Collection<DomElement> getChildren(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    paramString = this.e.getElementsByTagName(paramString);
    int i = 0;
    while (i < paramString.getLength())
    {
      Node localNode = paramString.item(i);
      if (localNode.getParentNode() == this.e) {
        localArrayList.add(new DomElement((Element)localNode));
      }
      i += 1;
    }
    return localArrayList;
  }
  
  public Element getElement()
  {
    return this.e;
  }
  
  public String getTagName()
  {
    return this.e.getTagName();
  }
  
  public String getText()
  {
    if (this.e.hasChildNodes()) {
      return this.e.getFirstChild().getNodeValue();
    }
    return null;
  }
  
  public boolean hasChild(String paramString)
  {
    paramString = this.e.getElementsByTagName(paramString);
    int i = 0;
    int j = paramString.getLength();
    while (i < j)
    {
      if (paramString.item(i).getParentNode() == this.e) {
        return true;
      }
      i += 1;
    }
    return false;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\net\roarsoftware\xml\DomElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */