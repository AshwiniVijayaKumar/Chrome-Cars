package com.ons.geofence;

public class Point
{
  public double x;
  public double y;
  
  public Point(double paramDouble1, double paramDouble2)
  {
    this.x = paramDouble1;
    this.y = paramDouble2;
  }
  
  public String toString()
  {
    return String.format("(%.2f,%.2f)", new Object[] { Double.valueOf(this.x), Double.valueOf(this.y) });
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ons\geofence\Point.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */