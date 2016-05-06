package net.roarsoftware.lastfm;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import net.roarsoftware.xml.DomElement;

public class Event
  extends ImageHolder
{
  private static final DateFormat DATE_FORMAT = new SimpleDateFormat("EEE, dd MMM yyyy", Locale.ENGLISH);
  private static final DateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
  private Collection<String> artists;
  private int attendance;
  private String description;
  private String headliner;
  private int id;
  private int reviews;
  private Date startDate;
  private Collection<TicketSupplier> tickets;
  private String title;
  private String url;
  private Venue venue;
  private String website;
  
  public static Result attend(String paramString, AttendanceStatus paramAttendanceStatus, Session paramSession)
  {
    return Caller.getInstance().call("event.attend", paramSession, new String[] { "event", paramString, "status", String.valueOf(paramAttendanceStatus.getId()) });
  }
  
  static Event eventFromElement(DomElement paramDomElement)
  {
    if (paramDomElement == null) {
      return null;
    }
    Event localEvent = new Event();
    ImageHolder.loadImages(localEvent, paramDomElement);
    localEvent.id = Integer.parseInt(paramDomElement.getChildText("id"));
    localEvent.title = paramDomElement.getChildText("title");
    localEvent.description = paramDomElement.getChildText("description");
    localEvent.url = paramDomElement.getChildText("url");
    if (paramDomElement.hasChild("attendance")) {
      localEvent.attendance = Integer.parseInt(paramDomElement.getChildText("attendance"));
    }
    if (paramDomElement.hasChild("reviews")) {
      localEvent.reviews = Integer.parseInt(paramDomElement.getChildText("reviews"));
    }
    try
    {
      localEvent.startDate = DATE_FORMAT.parse(paramDomElement.getChildText("startDate"));
      if (paramDomElement.hasChild("startTime"))
      {
        localObject1 = TIME_FORMAT.parse(paramDomElement.getChildText("startTime"));
        localObject2 = GregorianCalendar.getInstance();
        ((Calendar)localObject2).setTime(localEvent.startDate);
        Calendar localCalendar = GregorianCalendar.getInstance();
        localCalendar.setTime((Date)localObject1);
        ((Calendar)localObject2).set(11, localCalendar.get(11));
        ((Calendar)localObject2).set(12, localCalendar.get(12));
        localEvent.startDate = ((Calendar)localObject2).getTime();
      }
      localEvent.headliner = paramDomElement.getChild("artists").getChildText("headliner");
      localEvent.artists = new ArrayList();
      Object localObject1 = paramDomElement.getChild("artists").getChildren("artist").iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (DomElement)((Iterator)localObject1).next();
        localEvent.artists.add(((DomElement)localObject2).getText());
      }
    }
    catch (ParseException localParseException)
    {
      Object localObject2;
      for (;;)
      {
        localParseException.printStackTrace();
      }
      localEvent.website = paramDomElement.getChildText("website");
      localEvent.tickets = new ArrayList();
      Iterator localIterator = paramDomElement.getChild("tickets").getChildren("ticket").iterator();
      while (localIterator.hasNext())
      {
        localObject2 = (DomElement)localIterator.next();
        localEvent.tickets.add(new TicketSupplier(((DomElement)localObject2).getAttribute("supplier"), ((DomElement)localObject2).getText()));
      }
      localEvent.venue = Venue.venueFromElement(paramDomElement.getChild("venue"));
    }
    return localEvent;
  }
  
  public static Collection<User> getAttendees(String paramString1, String paramString2)
  {
    paramString2 = Caller.getInstance().call("event.getAttendees", paramString2, new String[] { "event", paramString1 }).getContentElement();
    paramString1 = new ArrayList(Integer.parseInt(paramString2.getAttribute("total")));
    paramString2 = paramString2.getChildren("user").iterator();
    while (paramString2.hasNext()) {
      paramString1.add(User.userFromElement((DomElement)paramString2.next()));
    }
    return paramString1;
  }
  
  public static Event getInfo(String paramString1, String paramString2)
  {
    return eventFromElement(Caller.getInstance().call("event.getInfo", paramString2, new String[] { "event", paramString1 }).getContentElement());
  }
  
  public static Result share(String paramString1, String paramString2, String paramString3, Session paramSession)
  {
    return Caller.getInstance().call("event.share", paramSession, new String[] { "event", paramString1, "recipient", paramString2, "message", paramString3 });
  }
  
  public Collection<String> getArtists()
  {
    return this.artists;
  }
  
  public int getAttendance()
  {
    return this.attendance;
  }
  
  public String getDescription()
  {
    return this.description;
  }
  
  public String getHeadliner()
  {
    return this.headliner;
  }
  
  public int getId()
  {
    return this.id;
  }
  
  public int getReviews()
  {
    return this.reviews;
  }
  
  public Date getStartDate()
  {
    return this.startDate;
  }
  
  public Collection<TicketSupplier> getTicketSuppliers()
  {
    return this.tickets;
  }
  
  public String getTitle()
  {
    return this.title;
  }
  
  public String getUrl()
  {
    return this.url;
  }
  
  public Venue getVenue()
  {
    return this.venue;
  }
  
  public String getWebsite()
  {
    return this.website;
  }
  
  public static enum AttendanceStatus
  {
    ATTENDING(0),  MAYBE_ATTENDING(1),  NOT_ATTENDING(2);
    
    private int id;
    
    private AttendanceStatus(int paramInt)
    {
      this.id = paramInt;
    }
    
    public int getId()
    {
      return this.id;
    }
  }
  
  public static class TicketSupplier
  {
    private String name;
    private String website;
    
    public TicketSupplier(String paramString1, String paramString2)
    {
      this.name = paramString1;
      this.website = paramString2;
    }
    
    public String getName()
    {
      return this.name;
    }
    
    public String getWebsite()
    {
      return this.website;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\net\roarsoftware\lastfm\Event.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */