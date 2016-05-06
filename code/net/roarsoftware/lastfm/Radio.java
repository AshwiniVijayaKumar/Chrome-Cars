package net.roarsoftware.lastfm;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import net.roarsoftware.xml.DomElement;

public class Radio
{
  private int expiry = -1;
  private Session session;
  private String stationName;
  private String stationUrl;
  private boolean supportsDiscovery;
  private String type;
  
  private Radio(Session paramSession)
  {
    this.session = paramSession;
  }
  
  public static Radio tune(String paramString, Locale paramLocale, Session paramSession)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("station", paramString);
    if ((paramLocale != null) && (paramLocale.getLanguage().length() != 0)) {
      localHashMap.put("lang", paramLocale.getLanguage());
    }
    paramString = Caller.getInstance().call("radio.tune", paramSession, localHashMap);
    if (!paramString.isSuccessful()) {
      return null;
    }
    paramString = paramString.getContentElement();
    paramLocale = new Radio(paramSession);
    paramLocale.type = paramString.getChildText("type");
    paramLocale.stationName = paramString.getChildText("name");
    paramLocale.stationUrl = paramString.getChildText("url");
    paramLocale.supportsDiscovery = "1".equals(paramString.getChildText("type"));
    return paramLocale;
  }
  
  public static Radio tune(RadioStation paramRadioStation, Locale paramLocale, Session paramSession)
  {
    return tune(paramRadioStation.getUrl(), paramLocale, paramSession);
  }
  
  public static Radio tune(RadioStation paramRadioStation, Session paramSession)
  {
    return tune(paramRadioStation, Locale.getDefault(), paramSession);
  }
  
  public Playlist getPlaylist()
  {
    return getPlaylist(false, true);
  }
  
  public Playlist getPlaylist(boolean paramBoolean1, boolean paramBoolean2)
  {
    Object localObject = Caller.getInstance().call("radio.getPlaylist", this.session, new String[] { "discovery", String.valueOf(paramBoolean1), "rtp", String.valueOf(paramBoolean2) });
    if (!((Result)localObject).isSuccessful()) {
      return null;
    }
    localObject = ((Result)localObject).getContentElement();
    Iterator localIterator = ((DomElement)localObject).getChildren("link").iterator();
    while (localIterator.hasNext())
    {
      DomElement localDomElement = (DomElement)localIterator.next();
      if ("http://www.last.fm/expiry".equals(localDomElement.getAttribute("rel"))) {
        this.expiry = Integer.parseInt(localDomElement.getText());
      }
    }
    return Playlist.playlistFromElement((DomElement)localObject);
  }
  
  public String getStationName()
  {
    return this.stationName;
  }
  
  public String getStationUrl()
  {
    return this.stationUrl;
  }
  
  public String getType()
  {
    return this.type;
  }
  
  public int playlistExpiresIn()
  {
    return this.expiry;
  }
  
  public boolean supportsDiscovery()
  {
    return this.supportsDiscovery;
  }
  
  public static class RadioStation
  {
    private String url;
    
    private RadioStation(String paramString)
    {
      this.url = paramString;
    }
    
    public static RadioStation artistFans(String paramString)
    {
      return new RadioStation("lastfm://artist/" + paramString + "/fans");
    }
    
    public static RadioStation library(String paramString)
    {
      return new RadioStation("lastfm://user/" + paramString + "/library");
    }
    
    public static RadioStation lovedTracks(String paramString)
    {
      return new RadioStation("lastfm://user/" + paramString + "/loved");
    }
    
    public static RadioStation neighbours(String paramString)
    {
      return new RadioStation("lastfm://user/" + paramString + "/neighbours");
    }
    
    public static RadioStation personalTag(String paramString1, String paramString2)
    {
      return new RadioStation("lastfm://usertags/" + paramString1 + "/" + paramString2);
    }
    
    public static RadioStation playlist(String paramString)
    {
      return new RadioStation("lastfm://playlist/" + paramString);
    }
    
    public static RadioStation recommended(String paramString)
    {
      return new RadioStation("lastfm://user/" + paramString + "/recommended");
    }
    
    public static RadioStation similarArtists(String paramString)
    {
      return new RadioStation("lastfm://artist/" + paramString + "/similarartists");
    }
    
    public static RadioStation tagged(String paramString)
    {
      return new RadioStation("lastfm://globaltags/" + paramString);
    }
    
    public String getUrl()
    {
      return this.url;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\net\roarsoftware\lastfm\Radio.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */