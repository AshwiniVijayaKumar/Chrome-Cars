package net.roarsoftware.lastfm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import net.roarsoftware.xml.DomElement;

public class Chart<T extends MusicEntry>
{
  private Collection<T> entries;
  private Date from;
  private Date to;
  
  public Chart(Date paramDate1, Date paramDate2, Collection<T> paramCollection)
  {
    this.from = paramDate1;
    this.to = paramDate2;
    this.entries = paramCollection;
  }
  
  static <T extends MusicEntry> Chart<T> getChart(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, int paramInt, String paramString7)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put(paramString2, paramString3);
    if ((paramString5 != null) && (paramString6 != null))
    {
      localHashMap.put("from", paramString5);
      localHashMap.put("to", paramString6);
    }
    if (paramInt != -1) {
      localHashMap.put("limit", String.valueOf(paramInt));
    }
    paramString1 = Caller.getInstance().call(paramString1, paramString7, localHashMap);
    if (!paramString1.isSuccessful()) {
      return null;
    }
    paramString1 = paramString1.getContentElement();
    paramString3 = paramString1.getChildren(paramString4);
    paramString2 = new ArrayList(paramString3.size());
    boolean bool1 = "artist".equals(paramString4);
    boolean bool2 = "track".equals(paramString4);
    boolean bool3 = "album".equals(paramString4);
    paramString3 = paramString3.iterator();
    while (paramString3.hasNext())
    {
      paramString4 = (DomElement)paramString3.next();
      if (bool1) {
        paramString2.add(Artist.artistFromElement(paramString4));
      }
      if (bool2) {
        paramString2.add(Track.trackFromElement(paramString4));
      }
      if (bool3) {
        paramString2.add(Album.albumFromElement(paramString4));
      }
    }
    long l1 = Long.parseLong(paramString1.getAttribute("from"));
    long l2 = Long.parseLong(paramString1.getAttribute("to"));
    return new Chart(new Date(1000L * l1), new Date(1000L * l2), paramString2);
  }
  
  static LinkedHashMap<String, String> getWeeklyChartList(String paramString1, String paramString2, String paramString3)
  {
    paramString1 = Caller.getInstance().call(paramString1 + ".getWeeklyChartList", paramString3, new String[] { paramString1, paramString2 });
    if (!paramString1.isSuccessful())
    {
      paramString1 = new LinkedHashMap(0);
      return paramString1;
    }
    paramString1 = paramString1.getContentElement();
    paramString2 = new LinkedHashMap();
    paramString3 = paramString1.getChildren("chart").iterator();
    for (;;)
    {
      paramString1 = paramString2;
      if (!paramString3.hasNext()) {
        break;
      }
      paramString1 = (DomElement)paramString3.next();
      paramString2.put(paramString1.getAttribute("from"), paramString1.getAttribute("to"));
    }
  }
  
  static Collection<Chart> getWeeklyChartListAsCharts(String paramString1, String paramString2, String paramString3)
  {
    paramString1 = Caller.getInstance().call(paramString1 + ".getWeeklyChartList", paramString3, new String[] { paramString1, paramString2 });
    if (!paramString1.isSuccessful())
    {
      paramString1 = Collections.emptyList();
      return paramString1;
    }
    paramString1 = paramString1.getContentElement();
    paramString2 = new ArrayList();
    paramString3 = paramString1.getChildren("chart").iterator();
    for (;;)
    {
      paramString1 = paramString2;
      if (!paramString3.hasNext()) {
        break;
      }
      paramString1 = (DomElement)paramString3.next();
      long l1 = Long.parseLong(paramString1.getAttribute("from"));
      long l2 = Long.parseLong(paramString1.getAttribute("to"));
      paramString2.add(new Chart(new Date(1000L * l1), new Date(1000L * l2), null));
    }
  }
  
  public Collection<T> getEntries()
  {
    return this.entries;
  }
  
  public Date getFrom()
  {
    return this.from;
  }
  
  public Date getTo()
  {
    return this.to;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\net\roarsoftware\lastfm\Chart.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */