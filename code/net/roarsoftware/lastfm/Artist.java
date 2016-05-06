package net.roarsoftware.lastfm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import net.roarsoftware.util.StringUtilities;
import net.roarsoftware.xml.DomElement;

public class Artist
  extends MusicEntry
{
  private Collection<Artist> similar = new ArrayList();
  private float similarityMatch;
  
  protected Artist(String paramString1, String paramString2)
  {
    super(paramString1, paramString2);
  }
  
  protected Artist(String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    super(paramString1, paramString2, paramString3, paramInt1, paramInt2, paramBoolean);
  }
  
  public static Result addTags(String paramString1, String paramString2, Session paramSession)
  {
    return Caller.getInstance().call("artist.addTags", paramSession, new String[] { "artist", paramString1, "tags", paramString2 });
  }
  
  static Artist artistFromElement(DomElement paramDomElement)
  {
    Artist localArtist = new Artist(null, null);
    MusicEntry.loadStandardInfo(localArtist, paramDomElement);
    if (paramDomElement.hasChild("match")) {
      localArtist.similarityMatch = Float.parseFloat(paramDomElement.getChildText("match"));
    }
    paramDomElement = paramDomElement.getChild("similar");
    if (paramDomElement != null)
    {
      paramDomElement = paramDomElement.getChildren("artist").iterator();
      while (paramDomElement.hasNext())
      {
        DomElement localDomElement = (DomElement)paramDomElement.next();
        localArtist.similar.add(artistFromElement(localDomElement));
      }
    }
    return localArtist;
  }
  
  public static Collection<Event> getEvents(String paramString1, String paramString2)
  {
    paramString1 = Caller.getInstance().call("artist.getEvents", paramString2, new String[] { "artist", paramString1 });
    if (!paramString1.isSuccessful())
    {
      paramString1 = Collections.emptyList();
      return paramString1;
    }
    paramString1 = paramString1.getContentElement();
    paramString2 = new ArrayList();
    Iterator localIterator = paramString1.getChildren("event").iterator();
    for (;;)
    {
      paramString1 = paramString2;
      if (!localIterator.hasNext()) {
        break;
      }
      paramString2.add(Event.eventFromElement((DomElement)localIterator.next()));
    }
  }
  
  public static PaginatedResult<Image> getImages(String paramString1, int paramInt1, int paramInt2, String paramString2)
  {
    paramString1 = Caller.getInstance().call("artist.getImages", paramString2, new String[] { "artist", paramString1, "page", String.valueOf(paramInt1), "limit", String.valueOf(paramInt2) }).getContentElement();
    paramString2 = new ArrayList(paramInt2);
    Iterator localIterator = paramString1.getChildren("image").iterator();
    while (localIterator.hasNext()) {
      paramString2.add(Image.imageFromElement((DomElement)localIterator.next()));
    }
    return new PaginatedResult(Integer.parseInt(paramString1.getAttribute("page")), Integer.parseInt(paramString1.getAttribute("totalpages")), paramString2);
  }
  
  public static PaginatedResult<Image> getImages(String paramString1, String paramString2)
  {
    return getImages(paramString1, 1, 50, paramString2);
  }
  
  public static Artist getInfo(String paramString1, String paramString2)
  {
    return getInfo(paramString1, Locale.getDefault(), paramString2);
  }
  
  public static Artist getInfo(String paramString1, Locale paramLocale, String paramString2)
  {
    HashMap localHashMap = new HashMap();
    if (StringUtilities.isMbid(paramString1)) {
      localHashMap.put("mbid", paramString1);
    }
    for (;;)
    {
      if ((paramLocale != null) && (paramLocale.getLanguage().length() != 0)) {
        localHashMap.put("lang", paramLocale.getLanguage());
      }
      paramString1 = Caller.getInstance().call("artist.getInfo", paramString2, localHashMap);
      if (paramString1.isSuccessful()) {
        break;
      }
      return null;
      localHashMap.put("artist", paramString1);
    }
    return artistFromElement(paramString1.getContentElement());
  }
  
  public static Collection<Artist> getSimilar(String paramString1, int paramInt, String paramString2)
  {
    paramString1 = Caller.getInstance().call("artist.getSimilar", paramString2, new String[] { "artist", paramString1, "limit", String.valueOf(paramInt) });
    if (!paramString1.isSuccessful())
    {
      paramString1 = Collections.emptyList();
      return paramString1;
    }
    paramString1 = paramString1.getContentElement();
    paramString2 = new ArrayList();
    Iterator localIterator = paramString1.getChildren("artist").iterator();
    for (;;)
    {
      paramString1 = paramString2;
      if (!localIterator.hasNext()) {
        break;
      }
      paramString2.add(artistFromElement((DomElement)localIterator.next()));
    }
  }
  
  public static Collection<Artist> getSimilar(String paramString1, String paramString2)
  {
    return getSimilar(paramString1, 100, paramString2);
  }
  
  public static Collection<String> getTags(String paramString, Session paramSession)
  {
    paramString = Caller.getInstance().call("artist.getTags", paramSession, new String[] { "artist", paramString });
    if (!paramString.isSuccessful())
    {
      paramString = Collections.emptyList();
      return paramString;
    }
    paramString = paramString.getContentElement();
    paramSession = new ArrayList();
    Iterator localIterator = paramString.getChildren("tag").iterator();
    for (;;)
    {
      paramString = paramSession;
      if (!localIterator.hasNext()) {
        break;
      }
      paramSession.add(((DomElement)localIterator.next()).getChildText("name"));
    }
  }
  
  public static Collection<Album> getTopAlbums(String paramString1, String paramString2)
  {
    paramString1 = Caller.getInstance().call("artist.getTopAlbums", paramString2, new String[] { "artist", paramString1 });
    if (!paramString1.isSuccessful())
    {
      paramString1 = Collections.emptyList();
      return paramString1;
    }
    paramString1 = paramString1.getContentElement();
    String str = paramString1.getAttribute("artist");
    paramString2 = new ArrayList();
    Iterator localIterator = paramString1.getChildren("album").iterator();
    for (;;)
    {
      paramString1 = paramString2;
      if (!localIterator.hasNext()) {
        break;
      }
      paramString2.add(Album.albumFromElement((DomElement)localIterator.next(), str));
    }
  }
  
  public static Collection<User> getTopFans(String paramString1, String paramString2)
  {
    paramString1 = Caller.getInstance().call("artist.getTopFans", paramString2, new String[] { "artist", paramString1 });
    if (!paramString1.isSuccessful())
    {
      paramString1 = Collections.emptyList();
      return paramString1;
    }
    paramString1 = paramString1.getContentElement();
    paramString2 = new ArrayList();
    Iterator localIterator = paramString1.getChildren("user").iterator();
    for (;;)
    {
      paramString1 = paramString2;
      if (!localIterator.hasNext()) {
        break;
      }
      paramString2.add(User.userFromElement((DomElement)localIterator.next()));
    }
  }
  
  public static Collection<String> getTopTags(String paramString1, String paramString2)
  {
    paramString1 = Caller.getInstance().call("artist.getTopTags", paramString2, new String[] { "artist", paramString1 });
    if (!paramString1.isSuccessful())
    {
      paramString1 = Collections.emptyList();
      return paramString1;
    }
    paramString1 = paramString1.getContentElement();
    paramString2 = new ArrayList();
    Iterator localIterator = paramString1.getChildren("tag").iterator();
    for (;;)
    {
      paramString1 = paramString2;
      if (!localIterator.hasNext()) {
        break;
      }
      paramString2.add(((DomElement)localIterator.next()).getChildText("name"));
    }
  }
  
  public static Collection<Track> getTopTracks(String paramString1, String paramString2)
  {
    paramString1 = Caller.getInstance().call("artist.getTopTracks", paramString2, new String[] { "artist", paramString1 });
    if (!paramString1.isSuccessful())
    {
      paramString1 = Collections.emptyList();
      return paramString1;
    }
    paramString1 = paramString1.getContentElement();
    String str = paramString1.getAttribute("artist");
    paramString2 = new ArrayList();
    Iterator localIterator = paramString1.getChildren("track").iterator();
    for (;;)
    {
      paramString1 = paramString2;
      if (!localIterator.hasNext()) {
        break;
      }
      paramString2.add(Track.trackFromElement((DomElement)localIterator.next(), str));
    }
  }
  
  public static Result removeTag(String paramString1, String paramString2, Session paramSession)
  {
    return Caller.getInstance().call("artist.removeTag", paramSession, new String[] { "artist", paramString1, "tag", paramString2 });
  }
  
  public static Collection<Artist> search(String paramString1, String paramString2)
  {
    paramString2 = Caller.getInstance().call("artist.search", paramString2, new String[] { "artist", paramString1 }).getContentElement().getChild("artistmatches").getChildren("artist");
    paramString1 = new ArrayList(paramString2.size());
    paramString2 = paramString2.iterator();
    while (paramString2.hasNext()) {
      paramString1.add(artistFromElement((DomElement)paramString2.next()));
    }
    return paramString1;
  }
  
  public static Result share(String paramString1, String paramString2, String paramString3, Session paramSession)
  {
    return Caller.getInstance().call("artist.share", paramSession, new String[] { "artist", paramString1, "recipient", paramString2, "message", paramString3 });
  }
  
  public static Result shout(String paramString1, String paramString2, Session paramSession)
  {
    return Caller.getInstance().call("artist.shout", paramSession, new String[] { "artist", paramString1, "message", paramString2 });
  }
  
  public Collection<Artist> getSimilar()
  {
    return this.similar;
  }
  
  public float getSimilarityMatch()
  {
    return this.similarityMatch;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\net\roarsoftware\lastfm\Artist.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */