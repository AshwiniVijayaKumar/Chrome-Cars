package com.ooyala.android.item;

import android.annotation.SuppressLint;
import com.ooyala.android.util.DebugMode;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class ClosedCaptions
  implements JSONUpdatableItem
{
  static final String ATTRIBUTE_BEGIN = "begin";
  static final String ATTRIBUTE_DUR = "dur";
  static final String ATTRIBUTE_END = "end";
  static final String ATTRIBUTE_XML_LANG = "xml:lang";
  static final String ELEMENT_BODY = "body";
  static final String ELEMENT_BR = "br";
  static final String ELEMENT_DIV = "div";
  static final String ELEMENT_HEAD = "head";
  static final String ELEMENT_P = "p";
  static final String ELEMENT_SPAN = "span";
  static final String ELEMENT_STYLE = "style";
  static final String ELEMENT_STYLING = "styling";
  static final String ELEMENT_TT = "tt";
  static final String KEY_DEFAULT_LANGUAGE = "default_language";
  static final String KEY_LANGUAGES = "languages";
  static final String KEY_URL = "url";
  protected Map<String, List<Caption>> _captions = new HashMap();
  protected String _defaultLanguage = null;
  protected Set<String> _languages = new HashSet();
  protected Map<String, String> _styles = new HashMap();
  protected URL _url = null;
  
  ClosedCaptions() {}
  
  ClosedCaptions(JSONObject paramJSONObject)
  {
    update(paramJSONObject);
  }
  
  private boolean parseBodyXML(Element paramElement)
  {
    if (!paramElement.getTagName().equals("body")) {
      return false;
    }
    NodeList localNodeList1 = paramElement.getElementsByTagName("div");
    int i = 0;
    if (i < localNodeList1.getLength())
    {
      Object localObject1 = (Element)localNodeList1.item(i);
      paramElement = ((Element)localObject1).getAttribute("xml:lang");
      if (ItemUtils.isNullOrEmpty(paramElement)) {}
      for (paramElement = null;; paramElement = (List)this._captions.get(paramElement))
      {
        if ((ItemUtils.isNullOrEmpty(((Element)localObject1).getAttribute("begin"))) && (paramElement != null)) {
          break label114;
        }
        i += 1;
        break;
      }
      label114:
      NodeList localNodeList2 = ((Element)localObject1).getElementsByTagName("p");
      localObject1 = null;
      int j = 0;
      label130:
      Caption localCaption;
      if (j < localNodeList2.getLength())
      {
        localCaption = new Caption((Element)localNodeList2.item(j));
        localObject2 = localObject1;
        if (localCaption != null)
        {
          if ((localObject1 == null) || (((Caption)localObject1).getBegin() < localCaption.getBegin())) {
            break label211;
          }
          ((Caption)localObject1).append(localCaption);
        }
      }
      for (Object localObject2 = localObject1;; localObject2 = localCaption)
      {
        j += 1;
        localObject1 = localObject2;
        break label130;
        break;
        label211:
        paramElement.add(localCaption);
      }
    }
    return true;
  }
  
  private boolean parseHeadXML(Element paramElement)
  {
    return paramElement.getTagName().equals("head");
  }
  
  private boolean update(Element paramElement)
  {
    if (!paramElement.getTagName().equals("tt")) {
      return false;
    }
    NodeList localNodeList = paramElement.getElementsByTagName("head");
    if ((localNodeList != null) && (localNodeList.getLength() > 0) && (!parseHeadXML((Element)localNodeList.item(0)))) {
      return false;
    }
    paramElement = paramElement.getElementsByTagName("body");
    return (paramElement == null) || (paramElement.getLength() <= 0) || (parseBodyXML((Element)paramElement.item(0)));
  }
  
  @SuppressLint({"DefaultLocale"})
  public List<Caption> closedCaptionsForLanguage(String paramString)
  {
    return (List)this._captions.get(paramString.toLowerCase());
  }
  
  public boolean fetchClosedCaptionsInfo()
  {
    try
    {
      boolean bool = update(DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(this._url.openStream())).getDocumentElement());
      return bool;
    }
    catch (Exception localException)
    {
      DebugMode.logE(getClass().getName(), "ERROR: Unable to fetch closed captions info: " + localException);
    }
    return false;
  }
  
  public Caption getCaption(String paramString, double paramDouble)
  {
    paramString = closedCaptionsForLanguage(paramString);
    if ((paramString == null) || (paramString.size() == 0)) {}
    int m;
    int i;
    int j;
    int k;
    Caption localCaption;
    do
    {
      int i1;
      do
      {
        while (n == 0)
        {
          return null;
          m = -1;
          i = paramString.size() / 2;
          j = paramString.size() - 1;
          k = 0;
          i1 = 0;
          n = i1;
          if (0 == 0)
          {
            n = i1;
            if (j >= 0)
            {
              n = i1;
              if (k < paramString.size())
              {
                localCaption = (Caption)paramString.get(i);
                if ((localCaption.getBegin() > paramDouble) || (paramDouble >= localCaption.getEnd())) {
                  break;
                }
                n = 1;
              }
            }
          }
        }
        return (Caption)paramString.get(i);
        n = i1;
      } while (m == i);
      int n = i1;
    } while (j == k);
    if (paramDouble < localCaption.getBegin()) {
      j = i - 1;
    }
    for (;;)
    {
      m = i;
      i = k + (j - k) / 2;
      break;
      k = i + 1;
    }
  }
  
  public String getDefaultLanguage()
  {
    return this._defaultLanguage;
  }
  
  public Set<String> getLanguages()
  {
    return this._languages;
  }
  
  public URL getURL()
  {
    return this._url;
  }
  
  boolean testUpdate(String paramString, Element paramElement)
  {
    this._languages.add(paramString);
    this._captions.put(paramString, new ArrayList());
    return update(paramElement);
  }
  
  JSONUpdatableItem.ReturnState update(JSONObject paramJSONObject)
  {
    if (paramJSONObject == null) {
      return JSONUpdatableItem.ReturnState.STATE_FAIL;
    }
    try
    {
      if (paramJSONObject.isNull("languages"))
      {
        System.out.println("ERROR: Fail to update closed captions because no languages exist!");
        return JSONUpdatableItem.ReturnState.STATE_FAIL;
      }
      JSONArray localJSONArray = paramJSONObject.getJSONArray("languages");
      if (paramJSONObject.isNull("url"))
      {
        System.out.println("ERROR: Fail to update closed captions because no url exists!");
        return JSONUpdatableItem.ReturnState.STATE_FAIL;
      }
      String str1 = paramJSONObject.getString("url");
      this._languages.clear();
      int i = 0;
      while (i < localJSONArray.length())
      {
        String str2 = localJSONArray.getString(i);
        this._languages.add(str2);
        this._captions.put(str2, new ArrayList());
        i += 1;
      }
      try
      {
        this._url = new URL(str1);
        if (!paramJSONObject.isNull("default_language")) {
          this._defaultLanguage = paramJSONObject.getString("default_language");
        }
        return JSONUpdatableItem.ReturnState.STATE_MATCHED;
      }
      catch (MalformedURLException paramJSONObject)
      {
        System.out.println("ERROR: Fail to update closed captions because url is invalid: " + str1);
        paramJSONObject = JSONUpdatableItem.ReturnState.STATE_FAIL;
        return paramJSONObject;
      }
      return JSONUpdatableItem.ReturnState.STATE_FAIL;
    }
    catch (JSONException paramJSONObject)
    {
      System.out.println("JSONException: " + paramJSONObject);
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\item\ClosedCaptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */