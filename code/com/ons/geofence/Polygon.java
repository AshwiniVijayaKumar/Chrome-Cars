package com.ons.geofence;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Polygon
{
  private final BoundingBox _boundingBox;
  private final List<Line> _sides;
  
  private Polygon(List<Line> paramList, BoundingBox paramBoundingBox)
  {
    this._sides = paramList;
    this._boundingBox = paramBoundingBox;
  }
  
  public static Builder Builder()
  {
    return new Builder();
  }
  
  private Line createRay(Point paramPoint)
  {
    double d = (this._boundingBox.xMax - this._boundingBox.xMin) / 100.0D;
    return new Line(new Point(this._boundingBox.xMin - d, this._boundingBox.yMin), paramPoint);
  }
  
  private boolean inBoundingBox(Point paramPoint)
  {
    return (paramPoint.x >= this._boundingBox.xMin) && (paramPoint.x <= this._boundingBox.xMax) && (paramPoint.y >= this._boundingBox.yMin) && (paramPoint.y <= this._boundingBox.yMax);
  }
  
  private boolean intersect(Line paramLine1, Line paramLine2)
  {
    if ((!paramLine1.isVertical()) && (!paramLine2.isVertical())) {
      if (paramLine1.getA() - paramLine2.getA() != 0.0D) {}
    }
    label181:
    for (;;)
    {
      return false;
      double d = (paramLine2.getB() - paramLine1.getB()) / (paramLine1.getA() - paramLine2.getA());
      Point localPoint = new Point(d, paramLine2.getA() * d + paramLine2.getB());
      for (;;)
      {
        if ((!paramLine2.isInside(localPoint)) || (!paramLine1.isInside(localPoint))) {
          break label181;
        }
        return true;
        if ((paramLine1.isVertical()) && (!paramLine2.isVertical()))
        {
          d = paramLine1.getStart().x;
          localPoint = new Point(d, paramLine2.getA() * d + paramLine2.getB());
        }
        else
        {
          if ((paramLine1.isVertical()) || (!paramLine2.isVertical())) {
            break;
          }
          d = paramLine2.getStart().x;
          localPoint = new Point(d, paramLine1.getA() * d + paramLine1.getB());
        }
      }
    }
  }
  
  public boolean contains(Point paramPoint)
  {
    if (inBoundingBox(paramPoint))
    {
      paramPoint = createRay(paramPoint);
      int i = 0;
      Iterator localIterator = this._sides.iterator();
      while (localIterator.hasNext()) {
        if (intersect(paramPoint, (Line)localIterator.next())) {
          i += 1;
        }
      }
      if (i % 2 == 1) {
        return true;
      }
    }
    return false;
  }
  
  public List<Line> getSides()
  {
    return this._sides;
  }
  
  private static class BoundingBox
  {
    public double xMax = Double.NEGATIVE_INFINITY;
    public double xMin = Double.NEGATIVE_INFINITY;
    public double yMax = Double.NEGATIVE_INFINITY;
    public double yMin = Double.NEGATIVE_INFINITY;
  }
  
  public static class Builder
  {
    private Polygon.BoundingBox _boundingBox = null;
    private boolean _firstPoint = true;
    private boolean _isClosed = false;
    private List<Line> _sides = new ArrayList();
    private List<Point> _vertexes = new ArrayList();
    
    private void updateBoundingBox(Point paramPoint)
    {
      if (this._firstPoint)
      {
        this._boundingBox = new Polygon.BoundingBox(null);
        this._boundingBox.xMax = paramPoint.x;
        this._boundingBox.xMin = paramPoint.x;
        this._boundingBox.yMax = paramPoint.y;
        this._boundingBox.yMin = paramPoint.y;
        this._firstPoint = false;
      }
      do
      {
        return;
        if (paramPoint.x > this._boundingBox.xMax) {
          this._boundingBox.xMax = paramPoint.x;
        }
        while (paramPoint.y > this._boundingBox.yMax)
        {
          this._boundingBox.yMax = paramPoint.y;
          return;
          if (paramPoint.x < this._boundingBox.xMin) {
            this._boundingBox.xMin = paramPoint.x;
          }
        }
      } while (paramPoint.y >= this._boundingBox.yMin);
      this._boundingBox.yMin = paramPoint.y;
    }
    
    private void validate()
    {
      if (this._vertexes.size() < 3) {
        throw new RuntimeException("Polygon must have at least 3 points");
      }
    }
    
    public Builder addVertex(Point paramPoint)
    {
      if (this._isClosed)
      {
        this._vertexes = new ArrayList();
        this._isClosed = false;
      }
      updateBoundingBox(paramPoint);
      this._vertexes.add(paramPoint);
      if (this._vertexes.size() > 1)
      {
        paramPoint = new Line((Point)this._vertexes.get(this._vertexes.size() - 2), paramPoint);
        this._sides.add(paramPoint);
      }
      return this;
    }
    
    public Polygon build()
    {
      validate();
      if (!this._isClosed) {
        this._sides.add(new Line((Point)this._vertexes.get(this._vertexes.size() - 1), (Point)this._vertexes.get(0)));
      }
      return new Polygon(this._sides, this._boundingBox, null);
    }
    
    public Builder close()
    {
      validate();
      this._sides.add(new Line((Point)this._vertexes.get(this._vertexes.size() - 1), (Point)this._vertexes.get(0)));
      this._isClosed = true;
      return this;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ons\geofence\Polygon.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */