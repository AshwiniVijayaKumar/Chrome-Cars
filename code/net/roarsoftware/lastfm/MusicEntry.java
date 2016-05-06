package net.roarsoftware.lastfm;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import net.roarsoftware.xml.DomElement;

public abstract class MusicEntry
  extends ImageHolder
{
  private static final DateFormat DATE_FORMAT = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss ZZZZ", Locale.ENGLISH);
  protected int listeners;
  protected String mbid;
  protected String name;
  protected int playcount;
  protected boolean streamable;
  protected Collection<String> tags = new ArrayList();
  protected String url;
  private Date wikiLastChanged;
  private String wikiSummary;
  private String wikiText;
  
  protected MusicEntry(String paramString1, String paramString2)
  {
    this(paramString1, paramString2, null, -1, -1, false);
  }
  
  protected MusicEntry(String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    this.name = paramString1;
    this.url = paramString2;
    this.mbid = paramString3;
    this.playcount = paramInt1;
    this.listeners = paramInt2;
    this.streamable = paramBoolean;
  }
  
  protected static void loadStandardInfo(MusicEntry paramMusicEntry, DomElement paramDomElement)
  {
    Object localObject1 = paramDomElement.getChild("stats");
    int i;
    label46:
    int j;
    if (localObject1 != null)
    {
      localObject2 = ((DomElement)localObject1).getChildText("playcount");
      localObject1 = ((DomElement)localObject1).getChildText("listeners");
      if ((localObject2 != null) && (((String)localObject2).length() != 0)) {
        break label245;
      }
      i = -1;
      if ((localObject1 != null) && (((String)localObject1).length() != 0)) {
        break label254;
      }
      j = -1;
      label61:
      localObject1 = paramDomElement.getChildText("streamable");
      if ((localObject1 == null) || (((String)localObject1).length() == 0) || (Integer.parseInt((String)localObject1) != 1)) {
        break label263;
      }
    }
    label245:
    label254:
    label263:
    for (boolean bool = true;; bool = false)
    {
      paramMusicEntry.name = paramDomElement.getChildText("name");
      paramMusicEntry.url = paramDomElement.getChildText("url");
      paramMusicEntry.mbid = paramDomElement.getChildText("mbid");
      paramMusicEntry.playcount = i;
      paramMusicEntry.listeners = j;
      paramMusicEntry.streamable = bool;
      localObject2 = paramDomElement.getChild("tags");
      localObject1 = localObject2;
      if (localObject2 == null) {
        localObject1 = paramDomElement.getChild("toptags");
      }
      if (localObject1 == null) {
        break label269;
      }
      localObject1 = ((DomElement)localObject1).getChildren("tag").iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (DomElement)((Iterator)localObject1).next();
        paramMusicEntry.tags.add(((DomElement)localObject2).getChildText("name"));
      }
      localObject2 = paramDomElement.getChildText("playcount");
      localObject1 = paramDomElement.getChildText("listeners");
      break;
      i = Integer.parseInt((String)localObject2);
      break label46;
      j = Integer.parseInt((String)localObject1);
      break label61;
    }
    label269:
    Object localObject2 = paramDomElement.getChild("bio");
    localObject1 = localObject2;
    if (localObject2 == null) {
      localObject1 = paramDomElement.getChild("wiki");
    }
    if (localObject1 != null) {
      localObject2 = ((DomElement)localObject1).getChildText("published");
    }
    try
    {
      paramMusicEntry.wikiLastChanged = DATE_FORMAT.parse((String)localObject2);
      paramMusicEntry.wikiSummary = ((DomElement)localObject1).getChildText("summary");
      paramMusicEntry.wikiText = ((DomElement)localObject1).getChildText("content");
      ImageHolder.loadImages(paramMusicEntry, paramDomElement);
      return;
    }
    catch (ParseException localParseException2)
    {
      for (;;)
      {
        try
        {
          paramMusicEntry.wikiLastChanged = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss ZZZZ", Locale.getDefault()).parse((String)localObject2);
        }
        catch (ParseException localParseException1) {}
      }
    }
  }
  
  public int getListeners()
  {
    return this.listeners;
  }
  
  public String getMbid()
  {
    return this.mbid;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public int getPlaycount()
  {
    return this.playcount;
  }
  
  public Collection<String> getTags()
  {
    return this.tags;
  }
  
  public String getUrl()
  {
    return this.url;
  }
  
  public Date getWikiLastChanged()
  {
    return this.wikiLastChanged;
  }
  
  public String getWikiSummary()
  {
    return this.wikiSummary;
  }
  
  public String getWikiText()
  {
    return this.wikiText;
  }
  
  public boolean isStreamable()
  {
    return this.streamable;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\net\roarsoftware\lastfm\MusicEntry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */