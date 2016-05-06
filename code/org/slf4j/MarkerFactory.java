package org.slf4j;

import org.slf4j.helpers.BasicMarkerFactory;
import org.slf4j.helpers.Util;
import org.slf4j.impl.StaticMarkerBinder;

public class MarkerFactory
{
  static IMarkerFactory markerFactory;
  
  static
  {
    try
    {
      markerFactory = StaticMarkerBinder.SINGLETON.getMarkerFactory();
      return;
    }
    catch (NoClassDefFoundError localNoClassDefFoundError)
    {
      markerFactory = new BasicMarkerFactory();
      return;
    }
    catch (Exception localException)
    {
      Util.report("Unexpected failure while binding MarkerFactory", localException);
    }
  }
  
  public static Marker getDetachedMarker(String paramString)
  {
    return markerFactory.getDetachedMarker(paramString);
  }
  
  public static IMarkerFactory getIMarkerFactory()
  {
    return markerFactory;
  }
  
  public static Marker getMarker(String paramString)
  {
    return markerFactory.getMarker(paramString);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\slf4j\MarkerFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */