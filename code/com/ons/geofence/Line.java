package com.ons.geofence;

public class Line
{
  private double _a = 0.0D;
  private double _b = 0.0D;
  private final Point _end;
  private final Point _start;
  private boolean _vertical = false;
  
  public Line(Point paramPoint1, Point paramPoint2)
  {
    this._start = paramPoint1;
    this._end = paramPoint2;
    if (this._end.x - this._start.x != 0.0D)
    {
      this._a = ((this._end.y - this._start.y) / (this._end.x - this._start.x));
      this._b = (this._start.y - this._a * this._start.x);
      return;
    }
    this._vertical = true;
  }
  
  public double getA()
  {
    return this._a;
  }
  
  public double getB()
  {
    return this._b;
  }
  
  public Point getEnd()
  {
    return this._end;
  }
  
  public Point getStart()
  {
    return this._start;
  }
  
  public boolean isInside(Point paramPoint)
  {
    double d1;
    double d2;
    label53:
    double d3;
    if (this._start.x > this._end.x)
    {
      d1 = this._start.x;
      if (this._start.x >= this._end.x) {
        break label159;
      }
      d2 = this._start.x;
      if (this._start.y <= this._end.y) {
        break label171;
      }
      d3 = this._start.y;
      label80:
      if (this._start.y >= this._end.y) {
        break label183;
      }
    }
    label159:
    label171:
    label183:
    for (double d4 = this._start.y;; d4 = this._end.y)
    {
      if ((paramPoint.x < d2) || (paramPoint.x > d1) || (paramPoint.y < d4) || (paramPoint.y > d3)) {
        break label195;
      }
      return true;
      d1 = this._end.x;
      break;
      d2 = this._end.x;
      break label53;
      d3 = this._end.y;
      break label80;
    }
    label195:
    return false;
  }
  
  public boolean isVertical()
  {
    return this._vertical;
  }
  
  public String toString()
  {
    return String.format("%s-%s", new Object[] { this._start.toString(), this._end.toString() });
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ons\geofence\Line.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */