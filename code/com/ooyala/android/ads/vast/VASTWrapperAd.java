package com.ooyala.android.ads.vast;

import java.io.PrintStream;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

class VASTWrapperAd
  extends VASTAd
{
  private Node _childAdXML;
  
  VASTWrapperAd(Element paramElement)
  {
    super(paramElement);
  }
  
  Node getChildAdXML()
  {
    return this._childAdXML;
  }
  
  boolean update(Element paramElement)
  {
    paramElement = paramElement.getFirstChild();
    while (paramElement != null) {
      if (!(paramElement instanceof Element))
      {
        paramElement = paramElement.getNextSibling();
      }
      else
      {
        Object localObject1 = paramElement.getFirstChild();
        Object localObject2 = null;
        while (localObject1 != null) {
          if (!(localObject1 instanceof Element))
          {
            localObject1 = ((Node)localObject1).getNextSibling();
          }
          else
          {
            Object localObject3 = ((Node)localObject1).getTextContent().trim();
            int i;
            if (localObject3 != null)
            {
              i = 1;
              label77:
              if ((i == 0) || (!((Element)localObject1).getTagName().equals("AdSystem"))) {
                break label142;
              }
              this._system = ((String)localObject3);
              this._systemVersion = ((Element)localObject1).getAttribute("version");
              localObject3 = localObject2;
            }
            for (;;)
            {
              localObject1 = ((Node)localObject1).getNextSibling();
              localObject2 = localObject3;
              break;
              i = 0;
              break label77;
              label142:
              if ((i != 0) && (((Element)localObject1).getTagName().equals("AdTitle")))
              {
                this._title = ((String)localObject3);
                localObject3 = localObject2;
              }
              else if ((i != 0) && (((Element)localObject1).getTagName().equals("Description")))
              {
                this._description = ((String)localObject3);
                localObject3 = localObject2;
              }
              else if ((i != 0) && (((Element)localObject1).getTagName().equals("Survey")))
              {
                this._surveyURLs.add(localObject3);
                localObject3 = localObject2;
              }
              else if ((i != 0) && (((Element)localObject1).getTagName().equals("Error")))
              {
                this._errorURLs.add(localObject3);
                localObject3 = localObject2;
              }
              else if ((i != 0) && (((Element)localObject1).getTagName().equals("Impression")))
              {
                this._impressionURLs.add(localObject3);
                localObject3 = localObject2;
              }
              else if (((Element)localObject1).getTagName().equals("Extensions"))
              {
                this._extensions = ((Element)localObject1);
                localObject3 = localObject2;
              }
              else if (!((Element)localObject1).getTagName().equals("VASTAdTagURI"))
              {
                localObject3 = localObject2;
                if (((Element)localObject1).getTagName().equals("Creatives"))
                {
                  for (localObject3 = ((Node)localObject1).getFirstChild(); localObject3 != null; localObject3 = ((Node)localObject3).getNextSibling()) {
                    if ((localObject3 instanceof Element)) {
                      addCreative((Element)localObject3);
                    }
                  }
                  Collections.sort(this._sequence);
                  localObject3 = localObject2;
                }
              }
            }
          }
        }
        if (localObject2 != null) {
          try
          {
            localObject1 = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new URL((String)localObject2).openStream())).getDocumentElement();
            if (!((Element)localObject1).getTagName().equals("VAST")) {
              return false;
            }
            if (Double.parseDouble(((Element)localObject1).getAttribute("version")) < 2.0D) {
              return false;
            }
            for (localObject1 = ((Element)localObject1).getFirstChild(); localObject1 != null; localObject1 = ((Node)localObject1).getNextSibling()) {
              if (((localObject1 instanceof Element)) && (((Element)localObject1).getTagName().equals("Ad"))) {
                this._childAdXML = ((Node)localObject1);
              }
            }
            paramElement = paramElement.getNextSibling();
          }
          catch (Exception paramElement)
          {
            System.out.println("ERROR: Unable to fetch VAST ad tag info: " + paramElement);
            return false;
          }
        }
      }
    }
    return true;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\ads\vast\VASTWrapperAd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */