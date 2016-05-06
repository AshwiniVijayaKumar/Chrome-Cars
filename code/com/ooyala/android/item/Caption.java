package com.ooyala.android.item;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Caption
{
  private static final String TAG = Caption.class.getName();
  protected double _begin = 0.0D;
  protected double _end = 0.0D;
  protected String _text = null;
  
  Caption() {}
  
  Caption(Element paramElement)
  {
    if (!paramElement.getTagName().equals("p")) {}
    label98:
    label281:
    for (;;)
    {
      return;
      Object localObject = paramElement.getAttribute("begin");
      String str1 = paramElement.getAttribute("dur");
      String str2 = paramElement.getAttribute("end");
      if (!ItemUtils.isNullOrEmpty((String)localObject))
      {
        this._begin = ItemUtils.secondsFromTimeString((String)localObject);
        int i;
        if (!ItemUtils.isNullOrEmpty(str2))
        {
          this._end = ItemUtils.secondsFromTimeString(str2);
          this._text = "";
          i = 0;
        }
        for (;;)
        {
          if (i >= paramElement.getChildNodes().getLength()) {
            break label281;
          }
          localObject = paramElement.getChildNodes().item(i).getTextContent().split("[\r\n]");
          int k = localObject.length;
          int j = 0;
          for (;;)
          {
            if (j < k)
            {
              str1 = localObject[j];
              this._text += str1.trim();
              j += 1;
              continue;
              if (ItemUtils.isNullOrEmpty(str1)) {
                break;
              }
              this._end = (this._begin + ItemUtils.secondsFromTimeString(str1));
              break label98;
            }
          }
          if (paramElement.getChildNodes().item(i).getNodeName().equals("br")) {
            this._text += "\n";
          }
          i += 1;
        }
      }
    }
  }
  
  void append(Caption paramCaption)
  {
    this._text += "\n";
    this._text += paramCaption.getText();
    this._end = Math.max(this._end, paramCaption.getEnd());
  }
  
  public double getBegin()
  {
    return this._begin;
  }
  
  public double getEnd()
  {
    return this._end;
  }
  
  public String getText()
  {
    return this._text;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\item\Caption.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */