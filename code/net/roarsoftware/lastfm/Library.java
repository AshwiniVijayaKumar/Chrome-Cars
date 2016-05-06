package net.roarsoftware.lastfm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.roarsoftware.xml.DomElement;

public class Library
{
  public static Result addAlbum(String paramString1, String paramString2, Session paramSession)
  {
    return Caller.getInstance().call("library.addAlbum", paramSession, new String[] { "artist", paramString1, "album", paramString2 });
  }
  
  public static Result addArtist(String paramString, Session paramSession)
  {
    return Caller.getInstance().call("library.addArtist", paramSession, new String[] { "artist", paramString });
  }
  
  public static Result addTrack(String paramString1, String paramString2, Session paramSession)
  {
    return Caller.getInstance().call("library.addTrack", paramSession, new String[] { "artist", paramString1, "track", paramString2 });
  }
  
  public static PaginatedResult<Album> getAlbums(String paramString1, int paramInt1, int paramInt2, String paramString2)
  {
    Object localObject = new HashMap();
    ((Map)localObject).put("user", paramString1);
    ((Map)localObject).put("page", String.valueOf(paramInt1));
    ((Map)localObject).put("limit", String.valueOf(paramInt2));
    paramString1 = Caller.getInstance().call("library.getAlbums", paramString2, (Map)localObject);
    if (!paramString1.isSuccessful()) {
      return new PaginatedResult(0, 0, Collections.emptyList());
    }
    paramString1 = paramString1.getContentElement();
    paramString2 = new ArrayList();
    localObject = paramString1.getChildren("album").iterator();
    while (((Iterator)localObject).hasNext()) {
      paramString2.add(Album.albumFromElement((DomElement)((Iterator)localObject).next()));
    }
    return new PaginatedResult(Integer.valueOf(paramString1.getAttribute("page")).intValue(), Integer.valueOf(paramString1.getAttribute("totalPages")).intValue(), paramString2);
  }
  
  public static PaginatedResult<Album> getAlbums(String paramString1, int paramInt, String paramString2)
  {
    return getAlbums(paramString1, paramInt, 0, paramString2);
  }
  
  public static PaginatedResult<Album> getAlbums(String paramString1, String paramString2)
  {
    return getAlbums(paramString1, 1, 0, paramString2);
  }
  
  public static Collection<Album> getAllAlbums(String paramString1, String paramString2)
  {
    Object localObject1 = null;
    int i = 1;
    Object localObject2;
    int k;
    int j;
    do
    {
      localObject2 = getAlbums(paramString1, i, paramString2);
      k = ((PaginatedResult)localObject2).getTotalPages();
      Collection localCollection = ((PaginatedResult)localObject2).getPageResults();
      localObject2 = localObject1;
      if (localObject1 == null) {
        localObject2 = new ArrayList(localCollection.size() * k);
      }
      localObject1 = localCollection.iterator();
      while (((Iterator)localObject1).hasNext()) {
        ((Collection)localObject2).add((Album)((Iterator)localObject1).next());
      }
      j = i + 1;
      localObject1 = localObject2;
      i = j;
    } while (j <= k);
    return (Collection<Album>)localObject2;
  }
  
  public static Collection<Artist> getAllArtists(String paramString1, String paramString2)
  {
    Object localObject1 = null;
    int i = 1;
    Object localObject2;
    int k;
    int j;
    do
    {
      localObject2 = getArtists(paramString1, i, paramString2);
      k = ((PaginatedResult)localObject2).getTotalPages();
      Collection localCollection = ((PaginatedResult)localObject2).getPageResults();
      localObject2 = localObject1;
      if (localObject1 == null) {
        localObject2 = new ArrayList(localCollection.size() * k);
      }
      localObject1 = localCollection.iterator();
      while (((Iterator)localObject1).hasNext()) {
        ((Collection)localObject2).add((Artist)((Iterator)localObject1).next());
      }
      j = i + 1;
      localObject1 = localObject2;
      i = j;
    } while (j <= k);
    return (Collection<Artist>)localObject2;
  }
  
  public static Collection<Track> getAllTracks(String paramString1, String paramString2)
  {
    Object localObject1 = null;
    int i = 1;
    Object localObject2;
    int k;
    int j;
    do
    {
      localObject2 = getTracks(paramString1, i, paramString2);
      k = ((PaginatedResult)localObject2).getTotalPages();
      Collection localCollection = ((PaginatedResult)localObject2).getPageResults();
      localObject2 = localObject1;
      if (localObject1 == null) {
        localObject2 = new ArrayList(localCollection.size() * k);
      }
      localObject1 = localCollection.iterator();
      while (((Iterator)localObject1).hasNext()) {
        ((Collection)localObject2).add((Track)((Iterator)localObject1).next());
      }
      j = i + 1;
      i = j;
      localObject1 = localObject2;
    } while (j <= k);
    return (Collection<Track>)localObject2;
  }
  
  public static PaginatedResult<Artist> getArtists(String paramString1, int paramInt1, int paramInt2, String paramString2)
  {
    Object localObject = new HashMap();
    ((Map)localObject).put("user", paramString1);
    ((Map)localObject).put("page", String.valueOf(paramInt1));
    ((Map)localObject).put("limit", String.valueOf(paramInt2));
    paramString1 = Caller.getInstance().call("library.getArtists", paramString2, (Map)localObject);
    if (!paramString1.isSuccessful()) {
      return new PaginatedResult(0, 0, Collections.emptyList());
    }
    paramString1 = paramString1.getContentElement();
    paramString2 = new ArrayList();
    localObject = paramString1.getChildren("artist").iterator();
    while (((Iterator)localObject).hasNext()) {
      paramString2.add(Artist.artistFromElement((DomElement)((Iterator)localObject).next()));
    }
    return new PaginatedResult(Integer.valueOf(paramString1.getAttribute("page")).intValue(), Integer.valueOf(paramString1.getAttribute("totalPages")).intValue(), paramString2);
  }
  
  public static PaginatedResult<Artist> getArtists(String paramString1, int paramInt, String paramString2)
  {
    return getArtists(paramString1, paramInt, 0, paramString2);
  }
  
  public static PaginatedResult<Artist> getArtists(String paramString1, String paramString2)
  {
    return getArtists(paramString1, 1, 0, paramString2);
  }
  
  public static PaginatedResult<Track> getTracks(String paramString1, int paramInt1, int paramInt2, String paramString2)
  {
    Object localObject = new HashMap();
    ((Map)localObject).put("user", paramString1);
    ((Map)localObject).put("page", String.valueOf(paramInt1));
    ((Map)localObject).put("limit", String.valueOf(paramInt2));
    paramString1 = Caller.getInstance().call("library.getTracks", paramString2, (Map)localObject);
    if (!paramString1.isSuccessful()) {
      return new PaginatedResult(0, 0, Collections.emptyList());
    }
    paramString1 = paramString1.getContentElement();
    paramString2 = new ArrayList();
    localObject = paramString1.getChildren("track").iterator();
    while (((Iterator)localObject).hasNext()) {
      paramString2.add(Track.trackFromElement((DomElement)((Iterator)localObject).next()));
    }
    return new PaginatedResult(Integer.valueOf(paramString1.getAttribute("page")).intValue(), Integer.valueOf(paramString1.getAttribute("totalPages")).intValue(), paramString2);
  }
  
  public static PaginatedResult<Track> getTracks(String paramString1, int paramInt, String paramString2)
  {
    return getTracks(paramString1, paramInt, 0, paramString2);
  }
  
  public static PaginatedResult<Track> getTracks(String paramString1, String paramString2)
  {
    return getTracks(paramString1, 1, 0, paramString2);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\net\roarsoftware\lastfm\Library.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */