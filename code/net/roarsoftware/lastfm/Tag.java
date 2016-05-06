package net.roarsoftware.lastfm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import net.roarsoftware.xml.DomElement;

public class Tag
  implements Comparable<Tag>
{
  private int count;
  private String name;
  private String url;
  
  private Tag() {}
  
  Tag(String paramString)
  {
    this.name = paramString;
  }
  
  public static List<Tag> filter(Collection<Tag> paramCollection, double paramDouble)
  {
    ArrayList localArrayList = new ArrayList();
    double d = getTagCountSum(paramCollection) / 100.0D;
    paramCollection = paramCollection.iterator();
    while (paramCollection.hasNext())
    {
      Tag localTag = (Tag)paramCollection.next();
      if (localTag.count > d * paramDouble) {
        localArrayList.add(localTag);
      }
    }
    return localArrayList;
  }
  
  public static Collection<String> getSimilar(String paramString1, String paramString2)
  {
    paramString1 = Caller.getInstance().call("tag.getSimilar", paramString2, new String[] { "tag", paramString1 });
    if (!paramString1.isSuccessful())
    {
      paramString1 = Collections.emptyList();
      return paramString1;
    }
    paramString2 = new ArrayList();
    Iterator localIterator = paramString1.getContentElement().getChildren("tag").iterator();
    for (;;)
    {
      paramString1 = paramString2;
      if (!localIterator.hasNext()) {
        break;
      }
      paramString2.add(((DomElement)localIterator.next()).getChildText("name"));
    }
  }
  
  public static long getTagCountSum(Collection<Tag> paramCollection)
  {
    long l = 0L;
    paramCollection = paramCollection.iterator();
    while (paramCollection.hasNext()) {
      l += ((Tag)paramCollection.next()).count;
    }
    return l;
  }
  
  public static Collection<Album> getTopAlbums(String paramString1, String paramString2)
  {
    paramString1 = Caller.getInstance().call("tag.getTopAlbums", paramString2, new String[] { "tag", paramString1 });
    if (!paramString1.isSuccessful())
    {
      paramString1 = Collections.emptyList();
      return paramString1;
    }
    paramString2 = new ArrayList();
    Iterator localIterator = paramString1.getContentElement().getChildren("album").iterator();
    for (;;)
    {
      paramString1 = paramString2;
      if (!localIterator.hasNext()) {
        break;
      }
      paramString2.add(Album.albumFromElement((DomElement)localIterator.next()));
    }
  }
  
  public static Collection<Artist> getTopArtists(String paramString1, String paramString2)
  {
    paramString1 = Caller.getInstance().call("tag.getTopArtists", paramString2, new String[] { "tag", paramString1 });
    if (!paramString1.isSuccessful())
    {
      paramString1 = Collections.emptyList();
      return paramString1;
    }
    paramString2 = new ArrayList();
    Iterator localIterator = paramString1.getContentElement().getChildren("artist").iterator();
    for (;;)
    {
      paramString1 = paramString2;
      if (!localIterator.hasNext()) {
        break;
      }
      paramString2.add(Artist.artistFromElement((DomElement)localIterator.next()));
    }
  }
  
  public static List<Tag> getTopTags(String paramString)
  {
    paramString = Caller.getInstance().call("tag.getTopTags", paramString, new String[0]);
    if (!paramString.isSuccessful())
    {
      paramString = Collections.emptyList();
      return paramString;
    }
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = paramString.getContentElement().getChildren("tag").iterator();
    for (;;)
    {
      paramString = localArrayList;
      if (!localIterator.hasNext()) {
        break;
      }
      localArrayList.add(tagFromElement((DomElement)localIterator.next()));
    }
  }
  
  public static Collection<Track> getTopTracks(String paramString1, String paramString2)
  {
    paramString1 = Caller.getInstance().call("tag.getTopTracks", paramString2, new String[] { "tag", paramString1 });
    if (!paramString1.isSuccessful())
    {
      paramString1 = Collections.emptyList();
      return paramString1;
    }
    paramString2 = new ArrayList();
    Iterator localIterator = paramString1.getContentElement().getChildren("track").iterator();
    for (;;)
    {
      paramString1 = paramString2;
      if (!localIterator.hasNext()) {
        break;
      }
      paramString2.add(Track.trackFromElement((DomElement)localIterator.next()));
    }
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
    return Chart.getChart("tag.getWeeklyArtistChart", "tag", paramString1, "artist", paramString2, paramString3, paramInt, paramString4);
  }
  
  public static LinkedHashMap<String, String> getWeeklyChartList(String paramString1, String paramString2)
  {
    return Chart.getWeeklyChartList("tag", paramString1, paramString2);
  }
  
  public static Collection<Chart> getWeeklyChartListAsCharts(String paramString1, String paramString2)
  {
    return Chart.getWeeklyChartListAsCharts("tag", paramString1, paramString2);
  }
  
  public static Collection<String> search(String paramString1, int paramInt, String paramString2)
  {
    paramString2 = Caller.getInstance().call("tag.search", paramString2, new String[] { "tag", paramString1, "limit", String.valueOf(paramInt) });
    paramString1 = new ArrayList();
    paramString2 = paramString2.getContentElement().getChild("tagmatches").getChildren("tag").iterator();
    while (paramString2.hasNext()) {
      paramString1.add(((DomElement)paramString2.next()).getChildText("name"));
    }
    return paramString1;
  }
  
  public static Collection<String> search(String paramString1, String paramString2)
  {
    return search(paramString1, 30, paramString2);
  }
  
  static Tag tagFromElement(DomElement paramDomElement)
  {
    Tag localTag = new Tag(paramDomElement.getChildText("name"));
    localTag.url = paramDomElement.getChildText("url");
    if (paramDomElement.hasChild("count")) {
      localTag.count = Integer.parseInt(paramDomElement.getChildText("count"));
    }
    return localTag;
  }
  
  public int compareTo(Tag paramTag)
  {
    return Double.compare(paramTag.getCount(), getCount());
  }
  
  public int getCount()
  {
    return this.count;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public String getUrl()
  {
    return this.url;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\net\roarsoftware\lastfm\Tag.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */