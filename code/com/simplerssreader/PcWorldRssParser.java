package com.simplerssreader;

import android.util.Xml;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class PcWorldRssParser
{
  private final String authorCheckString = "author Author AUTHOR";
  private final String baseCheckString = "rss feed RSS Rss Feed FEED";
  private final String dateCheckString = "date Date DATE pubDate dc:date published";
  private final String descriptionCheckString = "description Description DESCRIPTION summary Summary SUMMARY content Content CONTENT";
  private final String imageCheckString = "image Image IMAGE media:thumbnail";
  private final String itemCheckString = "item ITEM Item entry ENTRY Entry";
  private final String ns = null;
  
  private String readAttributeValue(XmlPullParser paramXmlPullParser)
    throws IOException, XmlPullParserException
  {
    String str = "";
    if (paramXmlPullParser.next() == 4)
    {
      str = paramXmlPullParser.getText();
      paramXmlPullParser.nextTag();
    }
    return str;
  }
  
  private String readDescription(XmlPullParser paramXmlPullParser)
    throws XmlPullParserException, IOException
  {
    paramXmlPullParser.require(2, this.ns, "description");
    String str = readText(paramXmlPullParser);
    paramXmlPullParser.require(3, this.ns, "description");
    return str;
  }
  
  private List<RssItem> readFeed(XmlPullParser paramXmlPullParser)
    throws XmlPullParserException, IOException
  {
    Object localObject1;
    Object localObject2;
    Object localObject3;
    Object localObject4;
    Object localObject5;
    Object localObject6;
    int j;
    ArrayList localArrayList;
    if (paramXmlPullParser.getName() != null)
    {
      localObject1 = paramXmlPullParser.getName();
      paramXmlPullParser.require(2, null, (String)localObject1);
      localObject1 = null;
      localObject2 = null;
      localObject3 = null;
      localObject4 = null;
      localObject5 = null;
      localObject6 = null;
      j = 0;
      localArrayList = new ArrayList();
    }
    for (;;)
    {
      label56:
      if (paramXmlPullParser.next() == 1) {
        break label625;
      }
      if ((paramXmlPullParser.getName() != null) && (paramXmlPullParser.getName().equals("summary"))) {
        paramXmlPullParser.getName();
      }
      Object localObject7;
      Object localObject8;
      Object localObject9;
      Object localObject10;
      Object localObject11;
      Object localObject12;
      int i;
      if (paramXmlPullParser.getEventType() == 2)
      {
        localObject7 = localObject1;
        localObject8 = localObject2;
        localObject9 = localObject3;
        localObject10 = localObject4;
        localObject11 = localObject5;
        localObject12 = localObject6;
        i = j;
        if (paramXmlPullParser.getName() != null)
        {
          localObject7 = localObject1;
          localObject8 = localObject2;
          localObject9 = localObject3;
          localObject10 = localObject4;
          localObject11 = localObject5;
          localObject12 = localObject6;
          i = j;
          if ("item ITEM Item entry ENTRY Entry".contains(paramXmlPullParser.getName()))
          {
            i = 1;
            localObject12 = localObject6;
            localObject11 = localObject5;
            localObject10 = localObject4;
            localObject9 = localObject3;
            localObject8 = localObject2;
            localObject7 = localObject1;
          }
        }
      }
      String str;
      label342:
      do
      {
        for (;;)
        {
          str = paramXmlPullParser.getName();
          if (!str.equals("title")) {
            break label381;
          }
          localObject1 = readTitle(paramXmlPullParser);
          localObject2 = localObject8;
          localObject3 = localObject9;
          localObject4 = localObject10;
          localObject5 = localObject11;
          localObject6 = localObject12;
          j = i;
          break label56;
          localObject1 = "rss";
          break;
          if (paramXmlPullParser.getEventType() != 3) {
            break label342;
          }
          if ((!"item ITEM Item entry ENTRY Entry".contains(paramXmlPullParser.getName())) || (j == 0)) {
            break label56;
          }
          localArrayList.add(new RssItem((String)localObject1, (String)localObject2, (String)localObject3, (String)localObject4, (String)localObject5, (String)localObject6));
          localObject7 = null;
          localObject8 = null;
          localObject9 = null;
          localObject10 = null;
          localObject11 = null;
          localObject12 = null;
          i = 0;
        }
        localObject7 = localObject1;
        localObject8 = localObject2;
        localObject9 = localObject3;
        localObject10 = localObject4;
        localObject11 = localObject5;
        localObject12 = localObject6;
        i = j;
      } while (paramXmlPullParser.getEventType() == 2);
      continue;
      label381:
      if (str.equals("link"))
      {
        localObject2 = readLink(paramXmlPullParser);
        localObject1 = localObject7;
        localObject3 = localObject9;
        localObject4 = localObject10;
        localObject5 = localObject11;
        localObject6 = localObject12;
        j = i;
      }
      else if ("description Description DESCRIPTION summary Summary SUMMARY content Content CONTENT".contains(str))
      {
        localObject3 = readTag(paramXmlPullParser, str);
        localObject1 = localObject7;
        localObject2 = localObject8;
        localObject4 = localObject10;
        localObject5 = localObject11;
        localObject6 = localObject12;
        j = i;
      }
      else if ("image Image IMAGE media:thumbnail".contains(str))
      {
        localObject4 = readImage(paramXmlPullParser, str);
        localObject1 = localObject7;
        localObject2 = localObject8;
        localObject3 = localObject9;
        localObject5 = localObject11;
        localObject6 = localObject12;
        j = i;
      }
      else if ("date Date DATE pubDate dc:date published".contains(str))
      {
        localObject5 = readTag(paramXmlPullParser, str);
        localObject1 = localObject7;
        localObject2 = localObject8;
        localObject3 = localObject9;
        localObject4 = localObject10;
        localObject6 = localObject12;
        j = i;
      }
      else
      {
        localObject1 = localObject7;
        localObject2 = localObject8;
        localObject3 = localObject9;
        localObject4 = localObject10;
        localObject5 = localObject11;
        localObject6 = localObject12;
        j = i;
        if ("author Author AUTHOR".contains(str))
        {
          localObject6 = readTag(paramXmlPullParser, str);
          localObject1 = localObject7;
          localObject2 = localObject8;
          localObject3 = localObject9;
          localObject4 = localObject10;
          localObject5 = localObject11;
          j = i;
        }
      }
    }
    label625:
    return localArrayList;
  }
  
  private String readImage(XmlPullParser paramXmlPullParser, String paramString)
    throws XmlPullParserException, IOException
  {
    if (paramXmlPullParser.getEventType() == 2) {
      paramXmlPullParser.require(2, this.ns, paramString);
    }
    String str1 = null;
    if (paramXmlPullParser.getAttributeCount() > 0) {
      str1 = paramXmlPullParser.getAttributeValue(null, "url");
    }
    String str2 = str1;
    if (str1 == null)
    {
      str1 = readText(paramXmlPullParser);
      str2 = str1;
      if (paramXmlPullParser.getName().equals(paramString))
      {
        paramXmlPullParser.require(3, this.ns, paramString);
        str2 = str1;
      }
    }
    return str2;
  }
  
  private String readLink(XmlPullParser paramXmlPullParser)
    throws XmlPullParserException, IOException
  {
    paramXmlPullParser.require(2, this.ns, "link");
    String str3 = readText(paramXmlPullParser);
    try
    {
      if (str3.trim() != null)
      {
        str1 = str3;
        if (str3.trim().length() >= 1) {}
      }
      else
      {
        str1 = paramXmlPullParser.getAttributeValue(null, "href");
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        String str1;
        String str2 = paramXmlPullParser.getAttributeValue(null, "href");
      }
    }
    paramXmlPullParser.require(3, this.ns, "link");
    return str1;
  }
  
  private String readTag(XmlPullParser paramXmlPullParser, String paramString)
    throws XmlPullParserException, IOException
  {
    paramXmlPullParser.require(2, this.ns, paramString);
    String str = readText(paramXmlPullParser);
    if (paramXmlPullParser.getName().equals(paramString)) {
      paramXmlPullParser.require(3, this.ns, paramString);
    }
    return str;
  }
  
  private String readText(XmlPullParser paramXmlPullParser)
    throws IOException, XmlPullParserException
  {
    String str2 = "";
    String str1 = str2;
    Object localObject = str2;
    try
    {
      if (paramXmlPullParser.next() == 4)
      {
        localObject = str2;
        str1 = paramXmlPullParser.getText();
        localObject = str1;
        paramXmlPullParser.nextTag();
      }
      localObject = str1;
      if (str1 == null)
      {
        localObject = str1;
        paramXmlPullParser = paramXmlPullParser.getAttributeValue("link", "href");
        localObject = paramXmlPullParser;
      }
      return (String)localObject;
    }
    catch (Exception paramXmlPullParser)
    {
      System.err.println("readText error: " + paramXmlPullParser.toString());
    }
    return (String)localObject;
  }
  
  private String readTitle(XmlPullParser paramXmlPullParser)
    throws XmlPullParserException, IOException
  {
    paramXmlPullParser.require(2, this.ns, "title");
    String str = readText(paramXmlPullParser);
    paramXmlPullParser.require(3, this.ns, "title");
    return str;
  }
  
  public List<RssItem> parse(InputStream paramInputStream)
    throws XmlPullParserException, IOException
  {
    try
    {
      Object localObject1 = Xml.newPullParser();
      ((XmlPullParser)localObject1).setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", false);
      ((XmlPullParser)localObject1).setInput(paramInputStream, null);
      ((XmlPullParser)localObject1).nextTag();
      localObject1 = readFeed((XmlPullParser)localObject1);
      return (List<RssItem>)localObject1;
    }
    finally
    {
      if (paramInputStream != null) {
        paramInputStream.close();
      }
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\simplerssreader\PcWorldRssParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */