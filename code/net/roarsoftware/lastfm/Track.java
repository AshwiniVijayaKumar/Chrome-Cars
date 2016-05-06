package net.roarsoftware.lastfm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.roarsoftware.util.StringUtilities;
import net.roarsoftware.xml.DomElement;

public class Track
  extends MusicEntry
{
  public static final String ALBUM_PAGE = "albumpage";
  public static final String ARTIST_PAGE = "artistpage";
  public static final String TRACK_PAGE = "trackpage";
  protected String album;
  protected String albumMbid;
  protected String artist;
  protected String artistMbid;
  protected int duration;
  protected boolean fullTrackAvailable;
  protected Map<String, String> lastFmExtensionInfos = new HashMap();
  protected String location;
  protected boolean nowPlaying;
  protected Date playedWhen;
  protected int position = -1;
  
  protected Track(String paramString1, String paramString2, String paramString3)
  {
    super(paramString1, paramString2);
    this.artist = paramString3;
  }
  
  protected Track(String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2, boolean paramBoolean1, String paramString4, String paramString5, boolean paramBoolean2, boolean paramBoolean3)
  {
    super(paramString1, paramString2, paramString3, paramInt1, paramInt2, paramBoolean1);
    this.artist = paramString4;
    this.artistMbid = paramString5;
    this.fullTrackAvailable = paramBoolean2;
    this.nowPlaying = paramBoolean3;
  }
  
  public static Result addTags(String paramString1, String paramString2, String paramString3, Session paramSession)
  {
    return Caller.getInstance().call("track.addTags", paramSession, new String[] { "artist", paramString1, "track", paramString2, "tags", paramString3 });
  }
  
  public static Result ban(String paramString1, String paramString2, Session paramSession)
  {
    return Caller.getInstance().call("track.ban", paramSession, new String[] { "artist", paramString1, "track", paramString2 });
  }
  
  public static Track getInfo(String paramString1, String paramString2, String paramString3)
  {
    HashMap localHashMap = new HashMap();
    if (StringUtilities.isMbid(paramString2)) {
      localHashMap.put("mbid", paramString2);
    }
    for (;;)
    {
      paramString3 = Caller.getInstance().call("artist.getInfo", paramString3, localHashMap);
      paramString2 = paramString3.getContentElement().getChild("track");
      paramString1 = paramString2;
      if (paramString2 == null) {
        paramString1 = paramString3.getContentElement();
      }
      return trackFromElement(paramString1);
      localHashMap.put("artist", paramString1);
      localHashMap.put("track", paramString2);
    }
  }
  
  public static Collection<Track> getSimilar(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    HashMap localHashMap = new HashMap();
    if ((paramString1 != null) && (paramString2 != null))
    {
      localHashMap.put("artist", paramString1);
      localHashMap.put("track", paramString2);
    }
    if (paramString3 != null) {
      localHashMap.put("mbid", paramString3);
    }
    paramString2 = Caller.getInstance().call("track.getSimilar", paramString4, localHashMap);
    paramString1 = new ArrayList();
    paramString2 = paramString2.getContentElement().getChildren("track").iterator();
    while (paramString2.hasNext()) {
      paramString1.add(trackFromElement((DomElement)paramString2.next()));
    }
    return paramString1;
  }
  
  public static Collection<String> getTags(String paramString1, String paramString2, Session paramSession)
  {
    paramString2 = Caller.getInstance().call("track.getTags", paramSession, new String[] { "artist", paramString1, "track", paramString2 }).getContentElement();
    paramString1 = new ArrayList();
    paramString2 = paramString2.getChildren("tag").iterator();
    while (paramString2.hasNext()) {
      paramString1.add(((DomElement)paramString2.next()).getChildText("name"));
    }
    return paramString1;
  }
  
  public static Collection<User> getTopFans(String paramString1, String paramString2, String paramString3)
  {
    HashMap localHashMap = new HashMap();
    if (StringUtilities.isMbid(paramString2)) {
      localHashMap.put("mbid", paramString2);
    }
    for (;;)
    {
      paramString2 = Caller.getInstance().call("track.getTopFans", paramString3, localHashMap).getContentElement();
      paramString1 = new ArrayList();
      paramString2 = paramString2.getChildren("user").iterator();
      while (paramString2.hasNext()) {
        paramString1.add(User.userFromElement((DomElement)paramString2.next()));
      }
      localHashMap.put("artist", paramString1);
      localHashMap.put("track", paramString2);
    }
    return paramString1;
  }
  
  public static List<Tag> getTopTags(String paramString1, String paramString2, String paramString3)
  {
    HashMap localHashMap = new HashMap();
    if (StringUtilities.isMbid(paramString2)) {
      localHashMap.put("mbid", paramString2);
    }
    for (;;)
    {
      paramString2 = Caller.getInstance().call("track.getTopTags", paramString3, localHashMap).getContentElement();
      paramString1 = new ArrayList();
      paramString2 = paramString2.getChildren("tag").iterator();
      while (paramString2.hasNext()) {
        paramString1.add(Tag.tagFromElement((DomElement)paramString2.next()));
      }
      localHashMap.put("artist", paramString1);
      localHashMap.put("track", paramString2);
    }
    return paramString1;
  }
  
  public static Result love(String paramString1, String paramString2, Session paramSession)
  {
    return Caller.getInstance().call("track.love", paramSession, new String[] { "artist", paramString1, "track", paramString2 });
  }
  
  public static Result removeTag(String paramString1, String paramString2, String paramString3, Session paramSession)
  {
    return Caller.getInstance().call("track.removeTag", paramSession, new String[] { "artist", paramString1, "track", paramString2, "tag", paramString3 });
  }
  
  public static Collection<Track> search(String paramString1, String paramString2)
  {
    return search(null, paramString1, 30, paramString2);
  }
  
  public static Collection<Track> search(String paramString1, String paramString2, int paramInt, String paramString3)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("track", paramString2);
    localHashMap.put("limit", String.valueOf(paramInt));
    if (paramString1 != null) {
      localHashMap.put("artist", paramString1);
    }
    paramString2 = Caller.getInstance().call("track.search", paramString3, localHashMap).getContentElement().getChild("trackmatches");
    paramString1 = new ArrayList();
    paramString2 = paramString2.getChildren("track").iterator();
    while (paramString2.hasNext()) {
      paramString1.add(trackFromElement((DomElement)paramString2.next()));
    }
    return paramString1;
  }
  
  public static Result share(String paramString1, String paramString2, String paramString3, String paramString4, Session paramSession)
  {
    paramString1 = StringUtilities.map(new String[] { "artist", paramString1, "track", paramString2, "recipient", paramString4 });
    if (paramString3 != null) {
      paramString1.put("message", paramString3);
    }
    return Caller.getInstance().call("track.share", paramSession, paramString1);
  }
  
  public static Track trackFromElement(DomElement paramDomElement)
  {
    return trackFromElement(paramDomElement, null);
  }
  
  static Track trackFromElement(DomElement paramDomElement, String paramString)
  {
    if (paramDomElement == null) {
      paramString = null;
    }
    Track localTrack;
    do
    {
      return paramString;
      localTrack = new Track(null, null, paramString);
      MusicEntry.loadStandardInfo(localTrack, paramDomElement);
      if (paramDomElement.hasChild("artist"))
      {
        localTrack.artist = paramDomElement.getChild("artist").getChildText("name");
        if (localTrack.artist == null) {
          localTrack.artist = paramDomElement.getChildText("artist");
        }
      }
      if (paramDomElement.hasChild("album"))
      {
        localTrack.album = paramDomElement.getChild("album").getChildText("title");
        if (localTrack.album == null) {
          localTrack.album = paramDomElement.getChildText("album");
        }
      }
      paramString = localTrack;
    } while (!paramDomElement.hasChild("toptags"));
    paramDomElement = paramDomElement.getChild("toptags").getChildren("tag").iterator();
    for (;;)
    {
      paramString = localTrack;
      if (!paramDomElement.hasNext()) {
        break;
      }
      paramString = (DomElement)paramDomElement.next();
      localTrack.tags.add(paramString.getChildText("name"));
    }
  }
  
  public String getAlbum()
  {
    return this.album;
  }
  
  public String getAlbumMbid()
  {
    return this.albumMbid;
  }
  
  public String getArtist()
  {
    return this.artist;
  }
  
  public String getArtistMbid()
  {
    return this.artistMbid;
  }
  
  public int getDuration()
  {
    return this.duration;
  }
  
  public String getLastFmInfo(String paramString)
  {
    return (String)this.lastFmExtensionInfos.get(paramString);
  }
  
  public String getLocation()
  {
    return this.location;
  }
  
  public Date getPlayedWhen()
  {
    return this.playedWhen;
  }
  
  public int getPosition()
  {
    return this.position;
  }
  
  public boolean isFullTrackAvailable()
  {
    return this.fullTrackAvailable;
  }
  
  public boolean isNowPlaying()
  {
    return this.nowPlaying;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\net\roarsoftware\lastfm\Track.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */