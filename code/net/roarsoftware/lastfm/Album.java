package net.roarsoftware.lastfm;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import net.roarsoftware.util.StringUtilities;
import net.roarsoftware.xml.DomElement;

public class Album
  extends MusicEntry
{
  private static final DateFormat RELEASE_DATE_FORMAT = new SimpleDateFormat("d MMM yyyy, HH:mm", Locale.ENGLISH);
  private String artist;
  private String id;
  private Date releaseDate;
  
  protected Album(String paramString1, String paramString2, String paramString3)
  {
    super(paramString1, paramString2);
    this.artist = paramString3;
  }
  
  protected Album(String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2, boolean paramBoolean, String paramString4)
  {
    super(paramString1, paramString2, paramString3, paramInt1, paramInt2, paramBoolean);
    this.artist = paramString4;
  }
  
  public static Result addTags(String paramString1, String paramString2, String paramString3, Session paramSession)
  {
    return Caller.getInstance().call("album.addTags", paramSession, new String[] { "artist", paramString1, "album", paramString2, "tags", paramString3 });
  }
  
  static Album albumFromElement(DomElement paramDomElement)
  {
    return albumFromElement(paramDomElement, null);
  }
  
  static Album albumFromElement(DomElement paramDomElement, String paramString)
  {
    if (paramDomElement == null) {
      paramString = null;
    }
    for (;;)
    {
      return paramString;
      Album localAlbum = new Album(null, null, paramString);
      MusicEntry.loadStandardInfo(localAlbum, paramDomElement);
      if (paramDomElement.hasChild("id")) {
        localAlbum.id = paramDomElement.getChildText("id");
      }
      if (paramDomElement.hasChild("artist"))
      {
        localAlbum.artist = paramDomElement.getChild("artist").getChildText("name");
        if (localAlbum.artist == null) {
          localAlbum.artist = paramDomElement.getChildText("artist");
        }
      }
      if (paramDomElement.hasChild("releasedate")) {}
      try
      {
        localAlbum.releaseDate = RELEASE_DATE_FORMAT.parse(paramDomElement.getChildText("releasedate"));
        paramString = localAlbum;
        if (!paramDomElement.hasChild("toptags")) {
          continue;
        }
        paramDomElement = paramDomElement.getChild("toptags").getChildren("tag").iterator();
        for (;;)
        {
          paramString = localAlbum;
          if (!paramDomElement.hasNext()) {
            break;
          }
          paramString = (DomElement)paramDomElement.next();
          localAlbum.tags.add(paramString.getChildText("name"));
        }
      }
      catch (ParseException paramString)
      {
        for (;;) {}
      }
    }
  }
  
  public static Album getInfo(String paramString1, String paramString2, String paramString3)
  {
    HashMap localHashMap = new HashMap();
    if (StringUtilities.isMbid(paramString2)) {
      localHashMap.put("mbid", paramString2);
    }
    for (;;)
    {
      return albumFromElement(Caller.getInstance().call("album.getInfo", paramString3, localHashMap).getContentElement().getChild("album"));
      localHashMap.put("artist", paramString1);
      localHashMap.put("album", paramString2);
    }
  }
  
  public static Collection<String> getTags(String paramString1, String paramString2, Session paramSession)
  {
    paramString1 = Caller.getInstance().call("album.getTags", paramSession, new String[] { "artist", paramString1, "album", paramString2 });
    if (!paramString1.isSuccessful())
    {
      paramString1 = Collections.emptyList();
      return paramString1;
    }
    paramString1 = paramString1.getContentElement();
    paramString2 = new ArrayList();
    paramSession = paramString1.getChildren("tag").iterator();
    for (;;)
    {
      paramString1 = paramString2;
      if (!paramSession.hasNext()) {
        break;
      }
      paramString2.add(((DomElement)paramSession.next()).getChildText("name"));
    }
  }
  
  public static Result removeTag(String paramString1, String paramString2, String paramString3, Session paramSession)
  {
    return Caller.getInstance().call("album.removeTag", paramSession, new String[] { "artist", paramString1, "album", paramString2, "tag", paramString3 });
  }
  
  public static Collection<Album> search(String paramString1, String paramString2)
  {
    paramString2 = Caller.getInstance().call("album.search", paramString2, new String[] { "album", paramString1 }).getContentElement().getChild("albummatches").getChildren("album");
    paramString1 = new ArrayList(paramString2.size());
    paramString2 = paramString2.iterator();
    while (paramString2.hasNext()) {
      paramString1.add(albumFromElement((DomElement)paramString2.next()));
    }
    return paramString1;
  }
  
  public String getArtist()
  {
    return this.artist;
  }
  
  public String getId()
  {
    return this.id;
  }
  
  public Date getReleaseDate()
  {
    return this.releaseDate;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\net\roarsoftware\lastfm\Album.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */