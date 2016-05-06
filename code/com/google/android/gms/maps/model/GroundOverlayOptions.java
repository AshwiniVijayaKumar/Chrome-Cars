package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.dynamic.b;
import com.google.android.gms.dynamic.b.a;
import com.google.android.gms.internal.er;
import com.google.android.gms.maps.internal.r;

public final class GroundOverlayOptions
  implements SafeParcelable
{
  public static final GroundOverlayOptionsCreator CREATOR = new GroundOverlayOptionsCreator();
  public static final float NO_DIMENSION = -1.0F;
  private float PI;
  private float PP;
  private boolean PQ = true;
  private BitmapDescriptor PS;
  private LatLng PT;
  private float PU;
  private float PV;
  private LatLngBounds PW;
  private float PX = 0.0F;
  private float PY = 0.5F;
  private float PZ = 0.5F;
  private final int wj;
  
  public GroundOverlayOptions()
  {
    this.wj = 1;
  }
  
  GroundOverlayOptions(int paramInt, IBinder paramIBinder, LatLng paramLatLng, float paramFloat1, float paramFloat2, LatLngBounds paramLatLngBounds, float paramFloat3, float paramFloat4, boolean paramBoolean, float paramFloat5, float paramFloat6, float paramFloat7)
  {
    this.wj = paramInt;
    this.PS = new BitmapDescriptor(b.a.G(paramIBinder));
    this.PT = paramLatLng;
    this.PU = paramFloat1;
    this.PV = paramFloat2;
    this.PW = paramLatLngBounds;
    this.PI = paramFloat3;
    this.PP = paramFloat4;
    this.PQ = paramBoolean;
    this.PX = paramFloat5;
    this.PY = paramFloat6;
    this.PZ = paramFloat7;
  }
  
  private GroundOverlayOptions a(LatLng paramLatLng, float paramFloat1, float paramFloat2)
  {
    this.PT = paramLatLng;
    this.PU = paramFloat1;
    this.PV = paramFloat2;
    return this;
  }
  
  public GroundOverlayOptions anchor(float paramFloat1, float paramFloat2)
  {
    this.PY = paramFloat1;
    this.PZ = paramFloat2;
    return this;
  }
  
  public GroundOverlayOptions bearing(float paramFloat)
  {
    this.PI = ((paramFloat % 360.0F + 360.0F) % 360.0F);
    return this;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public float getAnchorU()
  {
    return this.PY;
  }
  
  public float getAnchorV()
  {
    return this.PZ;
  }
  
  public float getBearing()
  {
    return this.PI;
  }
  
  public LatLngBounds getBounds()
  {
    return this.PW;
  }
  
  public float getHeight()
  {
    return this.PV;
  }
  
  public BitmapDescriptor getImage()
  {
    return this.PS;
  }
  
  public LatLng getLocation()
  {
    return this.PT;
  }
  
  public float getTransparency()
  {
    return this.PX;
  }
  
  int getVersionCode()
  {
    return this.wj;
  }
  
  public float getWidth()
  {
    return this.PU;
  }
  
  public float getZIndex()
  {
    return this.PP;
  }
  
  IBinder he()
  {
    return this.PS.gK().asBinder();
  }
  
  public GroundOverlayOptions image(BitmapDescriptor paramBitmapDescriptor)
  {
    this.PS = paramBitmapDescriptor;
    return this;
  }
  
  public boolean isVisible()
  {
    return this.PQ;
  }
  
  public GroundOverlayOptions position(LatLng paramLatLng, float paramFloat)
  {
    boolean bool2 = true;
    if (this.PW == null)
    {
      bool1 = true;
      er.a(bool1, "Position has already been set using positionFromBounds");
      if (paramLatLng == null) {
        break label59;
      }
      bool1 = true;
      label24:
      er.b(bool1, "Location must be specified");
      if (paramFloat < 0.0F) {
        break label64;
      }
    }
    label59:
    label64:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      er.b(bool1, "Width must be non-negative");
      return a(paramLatLng, paramFloat, -1.0F);
      bool1 = false;
      break;
      bool1 = false;
      break label24;
    }
  }
  
  public GroundOverlayOptions position(LatLng paramLatLng, float paramFloat1, float paramFloat2)
  {
    boolean bool2 = true;
    if (this.PW == null)
    {
      bool1 = true;
      er.a(bool1, "Position has already been set using positionFromBounds");
      if (paramLatLng == null) {
        break label81;
      }
      bool1 = true;
      label27:
      er.b(bool1, "Location must be specified");
      if (paramFloat1 < 0.0F) {
        break label87;
      }
      bool1 = true;
      label43:
      er.b(bool1, "Width must be non-negative");
      if (paramFloat2 < 0.0F) {
        break label93;
      }
    }
    label81:
    label87:
    label93:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      er.b(bool1, "Height must be non-negative");
      return a(paramLatLng, paramFloat1, paramFloat2);
      bool1 = false;
      break;
      bool1 = false;
      break label27;
      bool1 = false;
      break label43;
    }
  }
  
  public GroundOverlayOptions positionFromBounds(LatLngBounds paramLatLngBounds)
  {
    if (this.PT == null) {}
    for (boolean bool = true;; bool = false)
    {
      er.a(bool, "Position has already been set using position: " + this.PT);
      this.PW = paramLatLngBounds;
      return this;
    }
  }
  
  public GroundOverlayOptions transparency(float paramFloat)
  {
    if ((paramFloat >= 0.0F) && (paramFloat <= 1.0F)) {}
    for (boolean bool = true;; bool = false)
    {
      er.b(bool, "Transparency must be in the range [0..1]");
      this.PX = paramFloat;
      return this;
    }
  }
  
  public GroundOverlayOptions visible(boolean paramBoolean)
  {
    this.PQ = paramBoolean;
    return this;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    if (r.hc())
    {
      c.a(this, paramParcel, paramInt);
      return;
    }
    GroundOverlayOptionsCreator.a(this, paramParcel, paramInt);
  }
  
  public GroundOverlayOptions zIndex(float paramFloat)
  {
    this.PP = paramFloat;
    return this;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\maps\model\GroundOverlayOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */