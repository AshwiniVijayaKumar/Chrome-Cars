package net.roarsoftware.lastfm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import net.roarsoftware.xml.DomElement;

public class User
  extends ImageHolder
{
  private int age = -1;
  private String country;
  private String gender;
  private String language;
  private String name;
  private int numPlaylists;
  private int playcount;
  private String realname;
  private boolean subscriber;
  private String url;
  
  private User(String paramString1, String paramString2)
  {
    this.name = paramString1;
    this.url = paramString2;
  }
  
  public static Collection<Event> getEvents(String paramString1, String paramString2)
  {
    paramString1 = Caller.getInstance().call("user.getEvents", paramString2, new String[] { "user", paramString1 });
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
  
  public static Collection<User> getFriends(String paramString1, String paramString2)
  {
    return getFriends(paramString1, false, 100, paramString2);
  }
  
  public static Collection<User> getFriends(String paramString1, boolean paramBoolean, int paramInt, String paramString2)
  {
    int i = 1;
    Object localObject = Caller.getInstance();
    if (paramBoolean) {}
    for (;;)
    {
      paramString1 = ((Caller)localObject).call("user.getFriends", paramString2, new String[] { "user", paramString1, "recenttracks", String.valueOf(i), "limit", String.valueOf(paramInt) });
      if (paramString1.isSuccessful()) {
        break;
      }
      paramString1 = Collections.emptyList();
      return paramString1;
      i = 0;
    }
    paramString1 = paramString1.getContentElement();
    paramString2 = new ArrayList();
    localObject = paramString1.getChildren("user").iterator();
    for (;;)
    {
      paramString1 = paramString2;
      if (!((Iterator)localObject).hasNext()) {
        break;
      }
      paramString2.add(userFromElement((DomElement)((Iterator)localObject).next()));
    }
  }
  
  public static User getInfo(Session paramSession)
  {
    paramSession = Caller.getInstance().call("user.getInfo", paramSession, new String[0]);
    if (!paramSession.isSuccessful()) {
      return null;
    }
    return userFromElement(paramSession.getContentElement());
  }
  
  public static PaginatedResult<Track> getLovedTracks(String paramString1, int paramInt, String paramString2)
  {
    paramString1 = Caller.getInstance().call("user.getLovedTracks", paramString2, new String[] { "user", paramString1, "page", String.valueOf(paramInt) });
    if (!paramString1.isSuccessful()) {
      return new PaginatedResult(0, 0, Collections.emptyList());
    }
    paramString1 = paramString1.getContentElement();
    Object localObject = paramString1.getChildren("track");
    paramString2 = new ArrayList(((Collection)localObject).size());
    localObject = ((Collection)localObject).iterator();
    while (((Iterator)localObject).hasNext()) {
      paramString2.add(Track.trackFromElement((DomElement)((Iterator)localObject).next()));
    }
    return new PaginatedResult(Integer.valueOf(paramString1.getAttribute("page")).intValue(), Integer.valueOf(paramString1.getAttribute("totalPages")).intValue(), paramString2);
  }
  
  public static PaginatedResult<Track> getLovedTracks(String paramString1, String paramString2)
  {
    return getLovedTracks(paramString1, 1, paramString2);
  }
  
  public static Collection<User> getNeighbours(String paramString1, int paramInt, String paramString2)
  {
    paramString1 = Caller.getInstance().call("user.getNeighbours", paramString2, new String[] { "user", paramString1, "limit", String.valueOf(paramInt) });
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
      paramString2.add(userFromElement((DomElement)localIterator.next()));
    }
  }
  
  public static Collection<User> getNeighbours(String paramString1, String paramString2)
  {
    return getNeighbours(paramString1, 100, paramString2);
  }
  
  public static PaginatedResult<Event> getPastEvents(String paramString1, int paramInt1, int paramInt2, String paramString2)
  {
    paramString1 = Caller.getInstance().call("user.getPastEvents", paramString2, new String[] { "user", paramString1, "page", String.valueOf(paramInt1), "limit", String.valueOf(paramInt2) });
    if (!paramString1.isSuccessful()) {
      return new PaginatedResult(0, 0, Collections.emptyList());
    }
    paramString1 = paramString1.getContentElement();
    paramString2 = new ArrayList();
    Iterator localIterator = paramString1.getChildren("event").iterator();
    while (localIterator.hasNext()) {
      paramString2.add(Event.eventFromElement((DomElement)localIterator.next()));
    }
    return new PaginatedResult(Integer.valueOf(paramString1.getAttribute("page")).intValue(), Integer.valueOf(paramString1.getAttribute("totalPages")).intValue(), paramString2);
  }
  
  public static PaginatedResult<Event> getPastEvents(String paramString1, String paramString2)
  {
    return getPastEvents(paramString1, 1, 0, paramString2);
  }
  
  public static Collection<Playlist> getPlaylists(String paramString1, String paramString2)
  {
    paramString1 = Caller.getInstance().call("user.getPlaylists", paramString2, new String[] { "user", paramString1 });
    if (!paramString1.isSuccessful())
    {
      paramString1 = Collections.emptyList();
      return paramString1;
    }
    paramString2 = new ArrayList();
    Iterator localIterator = paramString1.getContentElement().getChildren("playlist").iterator();
    for (;;)
    {
      paramString1 = paramString2;
      if (!localIterator.hasNext()) {
        break;
      }
      paramString2.add(Playlist.playlistFromElement((DomElement)localIterator.next()));
    }
  }
  
  public static Collection<Track> getRecentTracks(String paramString1, int paramInt, String paramString2)
  {
    paramString1 = Caller.getInstance().call("user.getRecentTracks", paramString2, new String[] { "user", paramString1, "limit", String.valueOf(paramInt) });
    if (!paramString1.isSuccessful())
    {
      paramString1 = Collections.emptyList();
      return paramString1;
    }
    paramString1 = paramString1.getContentElement();
    paramString2 = new ArrayList();
    Iterator localIterator = paramString1.getChildren("track").iterator();
    for (;;)
    {
      paramString1 = paramString2;
      if (!localIterator.hasNext()) {
        break;
      }
      paramString2.add(Track.trackFromElement((DomElement)localIterator.next()));
    }
  }
  
  public static Collection<Track> getRecentTracks(String paramString1, String paramString2)
  {
    return getRecentTracks(paramString1, 10, paramString2);
  }
  
  public static PaginatedResult<Artist> getRecommendedArtists(int paramInt, Session paramSession)
  {
    paramSession = Caller.getInstance().call("user.getRecommendedArtists", paramSession, new String[] { "page", String.valueOf(paramInt) });
    if (!paramSession.isSuccessful()) {
      return new PaginatedResult(0, 0, Collections.emptyList());
    }
    paramSession = paramSession.getContentElement();
    Object localObject = paramSession.getChildren("artist");
    ArrayList localArrayList = new ArrayList(((Collection)localObject).size());
    localObject = ((Collection)localObject).iterator();
    while (((Iterator)localObject).hasNext()) {
      localArrayList.add(Artist.artistFromElement((DomElement)((Iterator)localObject).next()));
    }
    return new PaginatedResult(Integer.parseInt(paramSession.getAttribute("page")), Integer.parseInt(paramSession.getAttribute("totalPages")), localArrayList);
  }
  
  public static PaginatedResult<Artist> getRecommendedArtists(Session paramSession)
  {
    return getRecommendedArtists(1, paramSession);
  }
  
  public static PaginatedResult<Event> getRecommendedEvents(int paramInt1, int paramInt2, Session paramSession)
  {
    paramSession = Caller.getInstance().call("user.getRecommendedEvents", paramSession, new String[] { "page", String.valueOf(paramInt1), "limit", String.valueOf(paramInt2), "user", paramSession.getUsername() });
    if (!paramSession.isSuccessful()) {
      return new PaginatedResult(0, 0, Collections.emptyList());
    }
    paramSession = paramSession.getContentElement();
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = paramSession.getChildren("event").iterator();
    while (localIterator.hasNext()) {
      localArrayList.add(Event.eventFromElement((DomElement)localIterator.next()));
    }
    return new PaginatedResult(Integer.valueOf(paramSession.getAttribute("page")).intValue(), Integer.valueOf(paramSession.getAttribute("totalPages")).intValue(), localArrayList);
  }
  
  public static PaginatedResult<Event> getRecommendedEvents(int paramInt, Session paramSession)
  {
    return getRecommendedEvents(0, 0, paramSession);
  }
  
  public static PaginatedResult<Event> getRecommendedEvents(Session paramSession)
  {
    return getRecommendedEvents(0, paramSession);
  }
  
  public static Collection<Album> getTopAlbums(String paramString1, String paramString2)
  {
    return getTopAlbums(paramString1, Period.OVERALL, paramString2);
  }
  
  public static Collection<Album> getTopAlbums(String paramString1, Period paramPeriod, String paramString2)
  {
    paramString1 = Caller.getInstance().call("user.getTopAlbums", paramString2, new String[] { "user", paramString1, "period", paramPeriod.getString() });
    if (!paramString1.isSuccessful())
    {
      paramString1 = Collections.emptyList();
      return paramString1;
    }
    paramString1 = paramString1.getContentElement();
    paramPeriod = new ArrayList();
    paramString2 = paramString1.getChildren("album").iterator();
    for (;;)
    {
      paramString1 = paramPeriod;
      if (!paramString2.hasNext()) {
        break;
      }
      paramPeriod.add(Album.albumFromElement((DomElement)paramString2.next()));
    }
  }
  
  public static Collection<Artist> getTopArtists(String paramString1, String paramString2)
  {
    return getTopArtists(paramString1, Period.OVERALL, paramString2);
  }
  
  public static Collection<Artist> getTopArtists(String paramString1, Period paramPeriod, String paramString2)
  {
    paramString1 = Caller.getInstance().call("user.getTopArtists", paramString2, new String[] { "user", paramString1, "period", paramPeriod.getString() });
    if (!paramString1.isSuccessful())
    {
      paramString1 = Collections.emptyList();
      return paramString1;
    }
    paramString1 = paramString1.getContentElement();
    paramPeriod = new ArrayList();
    paramString2 = paramString1.getChildren("artist").iterator();
    for (;;)
    {
      paramString1 = paramPeriod;
      if (!paramString2.hasNext()) {
        break;
      }
      paramPeriod.add(Artist.artistFromElement((DomElement)paramString2.next()));
    }
  }
  
  public static Collection<String> getTopTags(String paramString1, int paramInt, String paramString2)
  {
    Object localObject = new HashMap();
    ((Map)localObject).put("user", paramString1);
    if (paramInt != -1) {
      ((Map)localObject).put("limit", String.valueOf(paramInt));
    }
    paramString1 = Caller.getInstance().call("user.getTopTags", paramString2, (Map)localObject);
    if (!paramString1.isSuccessful())
    {
      paramString1 = Collections.emptyList();
      return paramString1;
    }
    paramString1 = paramString1.getContentElement();
    paramString2 = new ArrayList();
    localObject = paramString1.getChildren("tag").iterator();
    for (;;)
    {
      paramString1 = paramString2;
      if (!((Iterator)localObject).hasNext()) {
        break;
      }
      paramString2.add(((DomElement)((Iterator)localObject).next()).getChildText("name"));
    }
  }
  
  public static Collection<String> getTopTags(String paramString1, String paramString2)
  {
    return getTopTags(paramString1, -1, paramString2);
  }
  
  public static Collection<Track> getTopTracks(String paramString1, String paramString2)
  {
    return getTopTracks(paramString1, Period.OVERALL, paramString2);
  }
  
  public static Collection<Track> getTopTracks(String paramString1, Period paramPeriod, String paramString2)
  {
    paramString1 = Caller.getInstance().call("user.getTopTracks", paramString2, new String[] { "user", paramString1, "period", paramPeriod.getString() });
    if (!paramString1.isSuccessful())
    {
      paramString1 = Collections.emptyList();
      return paramString1;
    }
    paramString1 = paramString1.getContentElement();
    paramPeriod = new ArrayList();
    paramString2 = paramString1.getChildren("track").iterator();
    for (;;)
    {
      paramString1 = paramPeriod;
      if (!paramString2.hasNext()) {
        break;
      }
      paramPeriod.add(Track.trackFromElement((DomElement)paramString2.next()));
    }
  }
  
  public static Chart<Album> getWeeklyAlbumChart(String paramString1, int paramInt, String paramString2)
  {
    return getWeeklyAlbumChart(paramString1, null, null, paramInt, paramString2);
  }
  
  public static Chart<Album> getWeeklyAlbumChart(String paramString1, String paramString2)
  {
    return getWeeklyAlbumChart(paramString1, null, null, -1, paramString2);
  }
  
  public static Chart<Album> getWeeklyAlbumChart(String paramString1, String paramString2, String paramString3, int paramInt, String paramString4)
  {
    return Chart.getChart("user.getWeeklyAlbumChart", "user", paramString1, "album", paramString2, paramString3, paramInt, paramString4);
  }
  
  public static Chart<Artist> getWeeklyArtistChart(String paramString1, int paramInt, String paramString2)
  {
    return getWeeklyArtistChart(paramString1, null, null, paramInt, paramString2);
  }
  
  public static Chart<Artist> getWeeklyArtistChart(String paramString1, String paramString2)
  {
    return getWeeklyArtistChart(paramString1, null, null, -1, paramString2);
  }
  
  public static Chart<Artist> getWeeklyArtistChart(String paramString1, String paramString2, String paramString3, int paramInt, String paramString4)
  {
    return Chart.getChart("user.getWeeklyArtistChart", "user", paramString1, "artist", paramString2, paramString3, paramInt, paramString4);
  }
  
  public static LinkedHashMap<String, String> getWeeklyChartList(String paramString1, String paramString2)
  {
    return Chart.getWeeklyChartList("user", paramString1, paramString2);
  }
  
  public static Collection<Chart> getWeeklyChartListAsCharts(String paramString1, String paramString2)
  {
    return Chart.getWeeklyChartListAsCharts("user", paramString1, paramString2);
  }
  
  public static Chart<Track> getWeeklyTrackChart(String paramString1, int paramInt, String paramString2)
  {
    return getWeeklyTrackChart(paramString1, null, null, paramInt, paramString2);
  }
  
  public static Chart<Track> getWeeklyTrackChart(String paramString1, String paramString2)
  {
    return getWeeklyTrackChart(paramString1, null, null, -1, paramString2);
  }
  
  public static Chart<Track> getWeeklyTrackChart(String paramString1, String paramString2, String paramString3, int paramInt, String paramString4)
  {
    return Chart.getChart("user.getWeeklyTrackChart", "user", paramString1, "track", paramString2, paramString3, paramInt, paramString4);
  }
  
  public static Result shout(String paramString1, String paramString2, Session paramSession)
  {
    return Caller.getInstance().call("user.shout", paramSession, new String[] { "user", paramString1, "message", paramString2 });
  }
  
  static User userFromElement(DomElement paramDomElement)
  {
    User localUser = new User(paramDomElement.getChildText("name"), paramDomElement.getChildText("url"));
    if (paramDomElement.hasChild("realname")) {
      localUser.realname = paramDomElement.getChildText("realname");
    }
    ImageHolder.loadImages(localUser, paramDomElement);
    localUser.language = paramDomElement.getChildText("lang");
    localUser.country = paramDomElement.getChildText("country");
    if (paramDomElement.hasChild("age")) {}
    try
    {
      localUser.age = Integer.parseInt(paramDomElement.getChildText("age"));
      localUser.gender = paramDomElement.getChildText("gender");
      localUser.subscriber = "1".equals(paramDomElement.getChildText("subscriber"));
      if (paramDomElement.hasChild("playcount")) {}
      try
      {
        localUser.playcount = Integer.parseInt(paramDomElement.getChildText("playcount"));
        if (paramDomElement.hasChild("playlists")) {}
        try
        {
          localUser.numPlaylists = Integer.parseInt(paramDomElement.getChildText("playlists"));
          return localUser;
        }
        catch (NumberFormatException paramDomElement)
        {
          return localUser;
        }
      }
      catch (NumberFormatException localNumberFormatException1)
      {
        for (;;) {}
      }
    }
    catch (NumberFormatException localNumberFormatException2)
    {
      for (;;) {}
    }
  }
  
  public int getAge()
  {
    return this.age;
  }
  
  public String getCountry()
  {
    return this.country;
  }
  
  public String getGender()
  {
    return this.gender;
  }
  
  public String getImageURL()
  {
    return getImageURL(ImageSize.MEDIUM);
  }
  
  public String getLanguage()
  {
    return this.language;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public int getNumPlaylists()
  {
    return this.numPlaylists;
  }
  
  public int getPlaycount()
  {
    return this.playcount;
  }
  
  public String getRealname()
  {
    return this.realname;
  }
  
  public String getUrl()
  {
    return this.url;
  }
  
  public boolean isSubscriber()
  {
    return this.subscriber;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\net\roarsoftware\lastfm\User.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */