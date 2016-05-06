package com.radiusnetworks.ibeacon;

public abstract interface IBeaconData
{
  public abstract String get(String paramString);
  
  public abstract Double getLatitude();
  
  public abstract Double getLongitude();
  
  public abstract boolean isDirty();
  
  public abstract void set(String paramString1, String paramString2);
  
  public abstract void setLatitude(Double paramDouble);
  
  public abstract void setLongitude(Double paramDouble);
  
  public abstract void sync(IBeaconDataNotifier paramIBeaconDataNotifier);
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\radiusnetworks\ibeacon\IBeaconData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */