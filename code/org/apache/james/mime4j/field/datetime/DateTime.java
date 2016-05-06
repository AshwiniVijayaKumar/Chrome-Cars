package org.apache.james.mime4j.field.datetime;

import java.io.PrintStream;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DateTime
{
  private final Date date;
  private final int day;
  private final int hour;
  private final int minute;
  private final int month;
  private final int second;
  private final int timeZone;
  private final int year = convertToYear(paramString);
  
  public DateTime(String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
  {
    this.date = convertToDate(this.year, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6);
    this.month = paramInt1;
    this.day = paramInt2;
    this.hour = paramInt3;
    this.minute = paramInt4;
    this.second = paramInt5;
    this.timeZone = paramInt6;
  }
  
  public static Date convertToDate(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7)
  {
    GregorianCalendar localGregorianCalendar = new GregorianCalendar(TimeZone.getTimeZone("GMT+0"));
    localGregorianCalendar.set(paramInt1, paramInt2 - 1, paramInt3, paramInt4, paramInt5, paramInt6);
    localGregorianCalendar.set(14, 0);
    if (paramInt7 != Integer.MIN_VALUE) {
      localGregorianCalendar.add(12, (paramInt7 / 100 * 60 + paramInt7 % 100) * -1);
    }
    return localGregorianCalendar.getTime();
  }
  
  private int convertToYear(String paramString)
  {
    int i = Integer.parseInt(paramString);
    switch (paramString.length())
    {
    default: 
      return i;
    case 1: 
    case 2: 
      if ((i >= 0) && (i < 50)) {
        return i + 2000;
      }
      return i + 1900;
    }
    return i + 1900;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (paramObject == null) {
        return false;
      }
      if (getClass() != paramObject.getClass()) {
        return false;
      }
      paramObject = (DateTime)paramObject;
      if (this.date == null)
      {
        if (((DateTime)paramObject).date != null) {
          return false;
        }
      }
      else if (!this.date.equals(((DateTime)paramObject).date)) {
        return false;
      }
      if (this.day != ((DateTime)paramObject).day) {
        return false;
      }
      if (this.hour != ((DateTime)paramObject).hour) {
        return false;
      }
      if (this.minute != ((DateTime)paramObject).minute) {
        return false;
      }
      if (this.month != ((DateTime)paramObject).month) {
        return false;
      }
      if (this.second != ((DateTime)paramObject).second) {
        return false;
      }
      if (this.timeZone != ((DateTime)paramObject).timeZone) {
        return false;
      }
    } while (this.year == ((DateTime)paramObject).year);
    return false;
  }
  
  public Date getDate()
  {
    return this.date;
  }
  
  public int getDay()
  {
    return this.day;
  }
  
  public int getHour()
  {
    return this.hour;
  }
  
  public int getMinute()
  {
    return this.minute;
  }
  
  public int getMonth()
  {
    return this.month;
  }
  
  public int getSecond()
  {
    return this.second;
  }
  
  public int getTimeZone()
  {
    return this.timeZone;
  }
  
  public int getYear()
  {
    return this.year;
  }
  
  public int hashCode()
  {
    if (this.date == null) {}
    for (int i = 0;; i = this.date.hashCode()) {
      return (((((((i + 31) * 31 + this.day) * 31 + this.hour) * 31 + this.minute) * 31 + this.month) * 31 + this.second) * 31 + this.timeZone) * 31 + this.year;
    }
  }
  
  public void print()
  {
    System.out.println(toString());
  }
  
  public String toString()
  {
    return getYear() + " " + getMonth() + " " + getDay() + "; " + getHour() + " " + getMinute() + " " + getSecond() + " " + getTimeZone();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\field\datetime\DateTime.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */