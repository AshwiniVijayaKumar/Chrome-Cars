package net.roarsoftware.lastfm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.roarsoftware.xml.DomElement;

public class Venue
{
  private String city;
  private String country;
  private String id;
  private float latitude;
  private float longitude;
  private String name;
  private String postal;
  private String street;
  private String timezone;
  private String url;
  
  public static Collection<Event> getEvents(String paramString1, String paramString2)
  {
    paramString1 = Caller.getInstance().call("venue.getEvents", paramString2, new String[] { "venue", paramString1 });
    if (!paramString1.isSuccessful())
    {
      paramString1 = Collections.emptyList();
      return paramString1;
    }
    paramString1 = paramString1.getContentElement().getChildren("event");
    paramString2 = new ArrayList(paramString1.size());
    Iterator localIterator = paramString1.iterator();
    for (;;)
    {
      paramString1 = paramString2;
      if (!localIterator.hasNext()) {
        break;
      }
      paramString2.add(Event.eventFromElement((DomElement)localIterator.next()));
    }
  }
  
  public static PaginatedResult<Event> getPastEvents(String paramString1, int paramInt, String paramString2)
  {
    paramString1 = Caller.getInstance().call("venue.getPastEvents", paramString2, new String[] { "venue", paramString1, "page", String.valueOf(paramInt) });
    if (!paramString1.isSuccessful()) {
      return new PaginatedResult(0, 0, Collections.emptyList());
    }
    paramString1 = paramString1.getContentElement();
    Object localObject = paramString1.getChildren("event");
    paramString2 = new ArrayList(((Collection)localObject).size());
    localObject = ((Collection)localObject).iterator();
    while (((Iterator)localObject).hasNext()) {
      paramString2.add(Event.eventFromElement((DomElement)((Iterator)localObject).next()));
    }
    return new PaginatedResult(Integer.parseInt(paramString1.getAttribute("page")), Integer.parseInt(paramString1.getAttribute("totalPages")), paramString2);
  }
  
  public static PaginatedResult<Event> getPastEvents(String paramString1, String paramString2)
  {
    return getPastEvents(paramString1, 1, paramString2);
  }
  
  public static Collection<Venue> search(String paramString1, String paramString2)
  {
    return search(paramString1, null, paramString2);
  }
  
  public static Collection<Venue> search(String paramString1, String paramString2, String paramString3)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("venue", paramString1);
    if (paramString2 != null) {
      localHashMap.put("country", paramString2);
    }
    paramString1 = Caller.getInstance().call("venue.search", paramString3, localHashMap);
    if (!paramString1.isSuccessful())
    {
      paramString1 = Collections.emptyList();
      return paramString1;
    }
    paramString1 = paramString1.getContentElement().getChild("venuematches").getChildren("venue");
    paramString2 = new ArrayList(paramString1.size());
    paramString3 = paramString1.iterator();
    for (;;)
    {
      paramString1 = paramString2;
      if (!paramString3.hasNext()) {
        break;
      }
      paramString2.add(venueFromElement((DomElement)paramString3.next()));
    }
  }
  
  static Venue venueFromElement(DomElement paramDomElement)
  {
    Venue localVenue = new Venue();
    localVenue.id = paramDomElement.getChildText("id");
    localVenue.name = paramDomElement.getChildText("name");
    localVenue.url = paramDomElement.getChildText("url");
    paramDomElement = paramDomElement.getChild("location");
    localVenue.city = paramDomElement.getChildText("city");
    localVenue.country = paramDomElement.getChildText("country");
    localVenue.street = paramDomElement.getChildText("street");
    localVenue.postal = paramDomElement.getChildText("postalcode");
    localVenue.timezone = paramDomElement.getChildText("timezone");
    paramDomElement = paramDomElement.getChild("geo:point");
    if (paramDomElement.getChildText("geo:lat").length() != 0)
    {
      localVenue.latitude = Float.parseFloat(paramDomElement.getChildText("geo:lat"));
      localVenue.longitude = Float.parseFloat(paramDomElement.getChildText("geo:long"));
    }
    return localVenue;
  }
  
  public String getCity()
  {
    return this.city;
  }
  
  public String getCountry()
  {
    return this.country;
  }
  
  public String getId()
  {
    return this.id;
  }
  
  public float getLatitude()
  {
    return this.latitude;
  }
  
  public float getLongitude()
  {
    return this.longitude;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public String getPostal()
  {
    return this.postal;
  }
  
  public String getStreet()
  {
    return this.street;
  }
  
  public String getTimezone()
  {
    return this.timezone;
  }
  
  public String getUrl()
  {
    return this.url;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\net\roarsoftware\lastfm\Venue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */