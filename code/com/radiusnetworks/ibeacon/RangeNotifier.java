package com.radiusnetworks.ibeacon;

import java.util.Collection;

public abstract interface RangeNotifier
{
  public abstract void didRangeBeaconsInRegion(Collection<IBeacon> paramCollection, Region paramRegion);
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\radiusnetworks\ibeacon\RangeNotifier.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */