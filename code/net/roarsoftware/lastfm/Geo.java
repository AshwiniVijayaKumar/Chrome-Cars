package net.roarsoftware.lastfm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.roarsoftware.xml.DomElement;

public class Geo
{
  public static Collection<Event> getAllEvents(String paramString1, String paramString2, String paramString3)
  {
    Object localObject1 = null;
    int i = 1;
    Object localObject2;
    int k;
    int j;
    do
    {
      localObject2 = getEvents(paramString1, paramString2, i, paramString3);
      k = ((PaginatedResult)localObject2).getTotalPages();
      Collection localCollection = ((PaginatedResult)localObject2).getPageResults();
      localObject2 = localObject1;
      if (localObject1 == null) {
        localObject2 = new ArrayList(localCollection.size() * k);
      }
      localObject1 = localCollection.iterator();
      while (((Iterator)localObject1).hasNext()) {
        ((Collection)localObject2).add((Event)((Iterator)localObject1).next());
      }
      j = i + 1;
      localObject1 = localObject2;
      i = j;
    } while (j <= k);
    return (Collection<Event>)localObject2;
  }
  
  public static PaginatedResult<Event> getEvents(double paramDouble1, double paramDouble2, int paramInt, String paramString)
  {
    Object localObject = new HashMap();
    ((Map)localObject).put("page", String.valueOf(paramInt));
    ((Map)localObject).put("lat", String.valueOf(paramDouble1));
    ((Map)localObject).put("long", String.valueOf(paramDouble2));
    paramString = Caller.getInstance().call("geo.getEvents", paramString, (Map)localObject);
    if (!paramString.isSuccessful()) {
      return new PaginatedResult(0, 0, Collections.emptyList());
    }
    paramString = paramString.getContentElement();
    localObject = new ArrayList();
    Iterator localIterator = paramString.getChildren("event").iterator();
    while (localIterator.hasNext()) {
      ((List)localObject).add(Event.eventFromElement((DomElement)localIterator.next()));
    }
    Integer.valueOf(paramString.getAttribute("page")).intValue();
    return new PaginatedResult(paramInt, Integer.valueOf(paramString.getAttribute("totalpages")).intValue(), (Collection)localObject);
  }
  
  public static PaginatedResult<Event> getEvents(String paramString1, String paramString2, int paramInt, String paramString3)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("page", String.valueOf(paramInt));
    if (paramString1 != null) {
      localHashMap.put("location", paramString1);
    }
    if (paramString2 != null) {
      localHashMap.put("distance", paramString2);
    }
    paramString1 = Caller.getInstance().call("geo.getEvents", paramString3, localHashMap);
    if (!paramString1.isSuccessful()) {
      return new PaginatedResult(0, 0, Collections.emptyList());
    }
    paramString1 = paramString1.getContentElement();
    paramString2 = new ArrayList();
    paramString3 = paramString1.getChildren("event").iterator();
    while (paramString3.hasNext()) {
      paramString2.add(Event.eventFromElement((DomElement)paramString3.next()));
    }
    Integer.valueOf(paramString1.getAttribute("page")).intValue();
    return new PaginatedResult(paramInt, Integer.valueOf(paramString1.getAttribute("totalpages")).intValue(), paramString2);
  }
  
  public static PaginatedResult<Event> getEvents(String paramString1, String paramString2, String paramString3)
  {
    return getEvents(paramString1, paramString2, 1, paramString3);
  }
  
  public static Collection<Artist> getTopArtists(String paramString1, String paramString2)
  {
    paramString1 = Caller.getInstance().call("geo.getTopArtists", paramString2, new String[] { "country", paramString1 });
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
  
  public static Collection<Track> getTopTracks(String paramString1, String paramString2)
  {
    paramString1 = Caller.getInstance().call("geo.getTopTracks", paramString2, new String[] { "country", paramString1 });
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
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\net\roarsoftware\lastfm\Geo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */