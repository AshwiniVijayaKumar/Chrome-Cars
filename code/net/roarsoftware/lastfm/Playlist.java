package net.roarsoftware.lastfm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import net.roarsoftware.xml.DomElement;

public class Playlist
{
  private String annotation;
  private String creator;
  private int id;
  private int size;
  private String title;
  private Collection<Track> tracks = new ArrayList();
  
  public static Result addTrack(int paramInt, String paramString1, String paramString2, Session paramSession)
  {
    return Caller.getInstance().call("playlist.addTrack", paramSession, new String[] { "playlistID", String.valueOf(paramInt), "artist", paramString1, "track", paramString2 });
  }
  
  public static Playlist create(String paramString1, String paramString2, Session paramSession)
  {
    paramString1 = Caller.getInstance().call("Playlist.create", paramSession, new String[] { "title", paramString1, "description", paramString2 });
    if (!paramString1.isSuccessful()) {
      return null;
    }
    return playlistFromElement(paramString1.getContentElement().getChild("playlist"));
  }
  
  public static Playlist fetch(String paramString1, String paramString2)
  {
    return playlistFromElement(Caller.getInstance().call("playlist.fetch", paramString2, new String[] { "playlistURL", paramString1 }).getContentElement());
  }
  
  public static Playlist fetchAlbumPlaylist(String paramString1, String paramString2)
  {
    return fetch("lastfm://playlist/album/" + paramString1, paramString2);
  }
  
  public static Playlist fetchTagPlaylist(String paramString1, String paramString2)
  {
    return fetch("lastfm://playlist/tag/" + paramString1 + "/freetracks", paramString2);
  }
  
  public static Playlist fetchUserPlaylist(int paramInt, String paramString)
  {
    return fetch("lastfm://playlist/" + paramInt, paramString);
  }
  
  static Playlist playlistFromElement(DomElement paramDomElement)
  {
    if (paramDomElement == null) {
      paramDomElement = null;
    }
    Playlist localPlaylist;
    do
    {
      Object localObject1;
      do
      {
        return paramDomElement;
        localPlaylist = new Playlist();
        if (paramDomElement.hasChild("id")) {
          localPlaylist.id = Integer.parseInt(paramDomElement.getChildText("id"));
        }
        localPlaylist.title = paramDomElement.getChildText("title");
        if (paramDomElement.hasChild("size")) {
          localPlaylist.size = Integer.parseInt(paramDomElement.getChildText("size"));
        }
        localPlaylist.creator = paramDomElement.getChildText("creator");
        localPlaylist.annotation = paramDomElement.getChildText("annotation");
        localObject1 = paramDomElement.getChild("trackList");
        paramDomElement = localPlaylist;
      } while (localObject1 == null);
      paramDomElement = ((DomElement)localObject1).getChildren("track").iterator();
      while (paramDomElement.hasNext())
      {
        Object localObject2 = (DomElement)paramDomElement.next();
        localObject1 = new Track(((DomElement)localObject2).getChildText("title"), ((DomElement)localObject2).getChildText("identifier"), ((DomElement)localObject2).getChildText("creator"));
        ((Track)localObject1).album = ((DomElement)localObject2).getChildText("album");
        ((Track)localObject1).duration = (Integer.parseInt(((DomElement)localObject2).getChildText("duration")) / 1000);
        ((Track)localObject1).imageUrls.put(ImageSize.LARGE, ((DomElement)localObject2).getChildText("image"));
        ((Track)localObject1).imageUrls.put(ImageSize.ORIGINAL, ((DomElement)localObject2).getChildText("image"));
        ((Track)localObject1).location = ((DomElement)localObject2).getChildText("location");
        localObject2 = ((DomElement)localObject2).getChildren("extension").iterator();
        while (((Iterator)localObject2).hasNext())
        {
          Object localObject3 = (DomElement)((Iterator)localObject2).next();
          if ("http://www.last.fm".equals(((DomElement)localObject3).getAttribute("application")))
          {
            localObject3 = ((DomElement)localObject3).getChildren().iterator();
            while (((Iterator)localObject3).hasNext())
            {
              DomElement localDomElement = (DomElement)((Iterator)localObject3).next();
              ((Track)localObject1).lastFmExtensionInfos.put(localDomElement.getTagName(), localDomElement.getText());
            }
          }
        }
        localPlaylist.tracks.add(localObject1);
      }
      paramDomElement = localPlaylist;
    } while (localPlaylist.size != 0);
    localPlaylist.size = localPlaylist.tracks.size();
    return localPlaylist;
  }
  
  public String getAnnotation()
  {
    return this.annotation;
  }
  
  public String getCreator()
  {
    return this.creator;
  }
  
  public int getId()
  {
    return this.id;
  }
  
  public int getSize()
  {
    return this.size;
  }
  
  public String getTitle()
  {
    return this.title;
  }
  
  public Collection<Track> getTracks()
  {
    return this.tracks;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\net\roarsoftware\lastfm\Playlist.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */