package net.roarsoftware.lastfm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import net.roarsoftware.xml.DomElement;

public class Group
{
  public static PaginatedResult<User> getMembers(String paramString1, int paramInt, String paramString2)
  {
    paramString1 = Caller.getInstance().call("group.getMembers", paramString2, new String[] { "group", paramString1, "page", String.valueOf(paramInt) });
    if (!paramString1.isSuccessful()) {
      return new PaginatedResult(0, 0, Collections.emptyList());
    }
    paramString1 = paramString1.getContentElement();
    Object localObject = paramString1.getChildren("user");
    paramString2 = new ArrayList(((Collection)localObject).size());
    localObject = ((Collection)localObject).iterator();
    while (((Iterator)localObject).hasNext()) {
      paramString2.add(User.userFromElement((DomElement)((Iterator)localObject).next()));
    }
    return new PaginatedResult(Integer.parseInt(paramString1.getAttribute("page")), Integer.parseInt(paramString1.getAttribute("totalPages")), paramString2);
  }
  
  public static PaginatedResult<User> getMembers(String paramString1, String paramString2)
  {
    return getMembers(paramString1, 1, paramString2);
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
    return Chart.getChart("group.getWeeklyAlbumChart", "group", paramString1, "album", paramString2, paramString3, paramInt, paramString4);
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
    return Chart.getChart("group.getWeeklyArtistChart", "group", paramString1, "artist", paramString2, paramString3, paramInt, paramString4);
  }
  
  public static LinkedHashMap<String, String> getWeeklyChartList(String paramString1, String paramString2)
  {
    return Chart.getWeeklyChartList("group", paramString1, paramString2);
  }
  
  public static Collection<Chart> getWeeklyChartListAsCharts(String paramString1, String paramString2)
  {
    return Chart.getWeeklyChartListAsCharts("group", paramString1, paramString2);
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
    return Chart.getChart("group.getWeeklyTrackChart", "group", paramString1, "track", paramString2, paramString3, paramInt, paramString4);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\net\roarsoftware\lastfm\Group.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */