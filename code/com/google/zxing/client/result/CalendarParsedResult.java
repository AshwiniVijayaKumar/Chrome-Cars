package com.google.zxing.client.result;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class CalendarParsedResult
  extends ParsedResult
{
  private static final DateFormat DATE_FORMAT;
  private static final Pattern DATE_TIME = Pattern.compile("[0-9]{8}(T[0-9]{6}Z?)?");
  private static final DateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyyMMdd'T'HHmmss", Locale.ENGLISH);
  private final String[] attendees;
  private final String description;
  private final Date end;
  private final boolean endAllDay;
  private final double latitude;
  private final String location;
  private final double longitude;
  private final String organizer;
  private final Date start;
  private final boolean startAllDay;
  private final String summary;
  
  static
  {
    DATE_FORMAT = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);
    DATE_FORMAT.setTimeZone(TimeZone.getTimeZone("GMT"));
  }
  
  public CalendarParsedResult(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String[] paramArrayOfString, String paramString6, double paramDouble1, double paramDouble2)
  {
    super(ParsedResultType.CALENDAR);
    this.summary = paramString1;
    for (;;)
    {
      try
      {
        this.start = parseDate(paramString2);
        if (paramString3 == null)
        {
          paramString1 = null;
          this.end = paramString1;
          if (paramString2.length() == 8)
          {
            bool = true;
            this.startAllDay = bool;
            if ((paramString3 == null) || (paramString3.length() != 8)) {
              break label135;
            }
            bool = true;
            this.endAllDay = bool;
            this.location = paramString4;
            this.organizer = paramString5;
            this.attendees = paramArrayOfString;
            this.description = paramString6;
            this.latitude = paramDouble1;
            this.longitude = paramDouble2;
          }
        }
        else
        {
          paramString1 = parseDate(paramString3);
          continue;
        }
        bool = false;
      }
      catch (ParseException paramString1)
      {
        throw new IllegalArgumentException(paramString1.toString());
      }
      continue;
      label135:
      boolean bool = false;
    }
  }
  
  private static String format(boolean paramBoolean, Date paramDate)
  {
    if (paramDate == null) {
      return null;
    }
    if (paramBoolean) {}
    for (DateFormat localDateFormat = DateFormat.getDateInstance(2);; localDateFormat = DateFormat.getDateTimeInstance(2, 2)) {
      return localDateFormat.format(paramDate);
    }
  }
  
  private static Date parseDate(String paramString)
    throws ParseException
  {
    if (!DATE_TIME.matcher(paramString).matches()) {
      throw new ParseException(paramString, 0);
    }
    if (paramString.length() == 8) {
      return DATE_FORMAT.parse(paramString);
    }
    if ((paramString.length() == 16) && (paramString.charAt(15) == 'Z'))
    {
      paramString = DATE_TIME_FORMAT.parse(paramString.substring(0, 15));
      GregorianCalendar localGregorianCalendar = new GregorianCalendar();
      long l = paramString.getTime() + localGregorianCalendar.get(15);
      localGregorianCalendar.setTime(new Date(l));
      return new Date(l + localGregorianCalendar.get(16));
    }
    return DATE_TIME_FORMAT.parse(paramString);
  }
  
  public String[] getAttendees()
  {
    return this.attendees;
  }
  
  public String getDescription()
  {
    return this.description;
  }
  
  public String getDisplayResult()
  {
    StringBuilder localStringBuilder = new StringBuilder(100);
    maybeAppend(this.summary, localStringBuilder);
    maybeAppend(format(this.startAllDay, this.start), localStringBuilder);
    maybeAppend(format(this.endAllDay, this.end), localStringBuilder);
    maybeAppend(this.location, localStringBuilder);
    maybeAppend(this.organizer, localStringBuilder);
    maybeAppend(this.attendees, localStringBuilder);
    maybeAppend(this.description, localStringBuilder);
    return localStringBuilder.toString();
  }
  
  public Date getEnd()
  {
    return this.end;
  }
  
  public double getLatitude()
  {
    return this.latitude;
  }
  
  public String getLocation()
  {
    return this.location;
  }
  
  public double getLongitude()
  {
    return this.longitude;
  }
  
  public String getOrganizer()
  {
    return this.organizer;
  }
  
  public Date getStart()
  {
    return this.start;
  }
  
  public String getSummary()
  {
    return this.summary;
  }
  
  public boolean isEndAllDay()
  {
    return this.endAllDay;
  }
  
  public boolean isStartAllDay()
  {
    return this.startAllDay;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\result\CalendarParsedResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */