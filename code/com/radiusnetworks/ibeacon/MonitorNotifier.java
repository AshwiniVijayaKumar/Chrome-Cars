package com.radiusnetworks.ibeacon;

public abstract interface MonitorNotifier
{
  public static final int INSIDE = 1;
  public static final int OUTSIDE = 0;
  
  public abstract void didDetermineStateForRegion(int paramInt, Region paramRegion);
  
  public abstract void didEnterRegion(Region paramRegion);
  
  public abstract void didExitRegion(Region paramRegion);
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\radiusnetworks\ibeacon\MonitorNotifier.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */