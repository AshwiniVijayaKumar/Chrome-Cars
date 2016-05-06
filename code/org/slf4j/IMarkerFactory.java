package org.slf4j;

public abstract interface IMarkerFactory
{
  public abstract boolean detachMarker(String paramString);
  
  public abstract boolean exists(String paramString);
  
  public abstract Marker getDetachedMarker(String paramString);
  
  public abstract Marker getMarker(String paramString);
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\slf4j\IMarkerFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */