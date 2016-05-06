package nl.siegmann.epublib.epub;

import java.util.ArrayList;
import java.util.List;
import nl.siegmann.epublib.util.StringUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

class DOMUtil
{
  public static String getAttribute(Element paramElement, String paramString1, String paramString2)
  {
    String str = paramElement.getAttributeNS(paramString1, paramString2);
    paramString1 = str;
    if (StringUtil.isEmpty(str)) {
      paramString1 = paramElement.getAttribute(paramString2);
    }
    return paramString1;
  }
  
  public static List<String> getElementsTextChild(Element paramElement, String paramString1, String paramString2)
  {
    paramElement = paramElement.getElementsByTagNameNS(paramString1, paramString2);
    paramString1 = new ArrayList(paramElement.getLength());
    int i = 0;
    while (i < paramElement.getLength())
    {
      paramString1.add(getTextChildrenContent((Element)paramElement.item(i)));
      i += 1;
    }
    return paramString1;
  }
  
  public static String getFindAttributeValue(Document paramDocument, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    paramDocument = paramDocument.getElementsByTagNameNS(paramString1, paramString2);
    int i = 0;
    while (i < paramDocument.getLength())
    {
      paramString1 = (Element)paramDocument.item(i);
      if ((paramString4.equalsIgnoreCase(paramString1.getAttribute(paramString3))) && (StringUtil.isNotBlank(paramString1.getAttribute(paramString5)))) {
        return paramString1.getAttribute(paramString5);
      }
      i += 1;
    }
    return null;
  }
  
  public static Element getFirstElementByTagNameNS(Element paramElement, String paramString1, String paramString2)
  {
    paramElement = paramElement.getElementsByTagNameNS(paramString1, paramString2);
    if (paramElement.getLength() == 0) {
      return null;
    }
    return (Element)paramElement.item(0);
  }
  
  public static String getTextChildrenContent(Element paramElement)
  {
    if (paramElement == null) {
      return null;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    paramElement = paramElement.getChildNodes();
    int i = 0;
    if (i < paramElement.getLength())
    {
      Node localNode = paramElement.item(i);
      if ((localNode == null) || (localNode.getNodeType() != 3)) {}
      for (;;)
      {
        i += 1;
        break;
        localStringBuilder.append(((Text)localNode).getData());
      }
    }
    return localStringBuilder.toString().trim();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\nl\siegmann\epublib\epub\DOMUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */