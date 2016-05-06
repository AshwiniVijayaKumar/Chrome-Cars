package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.internal.r;

public final class CircleOptions
  implements SafeParcelable
{
  public static final CircleOptionsCreator CREATOR = new CircleOptionsCreator();
  private LatLng PK = null;
  private double PL = 0.0D;
  private float PM = 10.0F;
  private int PN = -16777216;
  private int PO = 0;
  private float PP = 0.0F;
  private boolean PQ = true;
  private final int wj;
  
  public CircleOptions()
  {
    this.wj = 1;
  }
  
  CircleOptions(int paramInt1, LatLng paramLatLng, double paramDouble, float paramFloat1, int paramInt2, int paramInt3, float paramFloat2, boolean paramBoolean)
  {
    this.wj = paramInt1;
    this.PK = paramLatLng;
    this.PL = paramDouble;
    this.PM = paramFloat1;
    this.PN = paramInt2;
    this.PO = paramInt3;
    this.PP = paramFloat2;
    this.PQ = paramBoolean;
  }
  
  public CircleOptions center(LatLng paramLatLng)
  {
    this.PK = paramLatLng;
    return this;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public CircleOptions fillColor(int paramInt)
  {
    this.PO = paramInt;
    return this;
  }
  
  public LatLng getCenter()
  {
    return this.PK;
  }
  
  public int getFillColor()
  {
    return this.PO;
  }
  
  public double getRadius()
  {
    return this.PL;
  }
  
  public int getStrokeColor()
  {
    return this.PN;
  }
  
  public float getStrokeWidth()
  {
    return this.PM;
  }
  
  int getVersionCode()
  {
    return this.wj;
  }
  
  public float getZIndex()
  {
    return this.PP;
  }
  
  public boolean isVisible()
  {
    return this.PQ;
  }
  
  public CircleOptions radius(double paramDouble)
  {
    this.PL = paramDouble;
    return this;
  }
  
  public CircleOptions strokeColor(int paramInt)
  {
    this.PN = paramInt;
    return this;
  }
  
  public CircleOptions strokeWidth(float paramFloat)
  {
    this.PM = paramFloat;
    return this;
  }
  
  public CircleOptions visible(boolean paramBoolean)
  {
    this.PQ = paramBoolean;
    return this;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    if (r.hc())
    {
      b.a(this, paramParcel, paramInt);
      return;
    }
    CircleOptionsCreator.a(this, paramParcel, paramInt);
  }
  
  public CircleOptions zIndex(float paramFloat)
  {
    this.PP = paramFloat;
    return this;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\maps\model\CircleOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */