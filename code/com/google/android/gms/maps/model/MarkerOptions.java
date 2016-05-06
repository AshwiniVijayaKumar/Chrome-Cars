package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.dynamic.b;
import com.google.android.gms.dynamic.b.a;
import com.google.android.gms.maps.internal.r;

public final class MarkerOptions
  implements SafeParcelable
{
  public static final MarkerOptionsCreator CREATOR = new MarkerOptionsCreator();
  private String CX;
  private boolean PQ = true;
  private float PY = 0.5F;
  private float PZ = 1.0F;
  private LatLng Qf;
  private String Qg;
  private BitmapDescriptor Qh;
  private boolean Qi;
  private boolean Qj = false;
  private float Qk = 0.0F;
  private float Ql = 0.5F;
  private float Qm = 0.0F;
  private float mAlpha = 1.0F;
  private final int wj;
  
  public MarkerOptions()
  {
    this.wj = 1;
  }
  
  MarkerOptions(int paramInt, LatLng paramLatLng, String paramString1, String paramString2, IBinder paramIBinder, float paramFloat1, float paramFloat2, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6)
  {
    this.wj = paramInt;
    this.Qf = paramLatLng;
    this.CX = paramString1;
    this.Qg = paramString2;
    if (paramIBinder == null) {}
    for (paramLatLng = null;; paramLatLng = new BitmapDescriptor(b.a.G(paramIBinder)))
    {
      this.Qh = paramLatLng;
      this.PY = paramFloat1;
      this.PZ = paramFloat2;
      this.Qi = paramBoolean1;
      this.PQ = paramBoolean2;
      this.Qj = paramBoolean3;
      this.Qk = paramFloat3;
      this.Ql = paramFloat4;
      this.Qm = paramFloat5;
      this.mAlpha = paramFloat6;
      return;
    }
  }
  
  public MarkerOptions alpha(float paramFloat)
  {
    this.mAlpha = paramFloat;
    return this;
  }
  
  public MarkerOptions anchor(float paramFloat1, float paramFloat2)
  {
    this.PY = paramFloat1;
    this.PZ = paramFloat2;
    return this;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public MarkerOptions draggable(boolean paramBoolean)
  {
    this.Qi = paramBoolean;
    return this;
  }
  
  public MarkerOptions flat(boolean paramBoolean)
  {
    this.Qj = paramBoolean;
    return this;
  }
  
  public float getAlpha()
  {
    return this.mAlpha;
  }
  
  public float getAnchorU()
  {
    return this.PY;
  }
  
  public float getAnchorV()
  {
    return this.PZ;
  }
  
  public BitmapDescriptor getIcon()
  {
    return this.Qh;
  }
  
  public float getInfoWindowAnchorU()
  {
    return this.Ql;
  }
  
  public float getInfoWindowAnchorV()
  {
    return this.Qm;
  }
  
  public LatLng getPosition()
  {
    return this.Qf;
  }
  
  public float getRotation()
  {
    return this.Qk;
  }
  
  public String getSnippet()
  {
    return this.Qg;
  }
  
  public String getTitle()
  {
    return this.CX;
  }
  
  int getVersionCode()
  {
    return this.wj;
  }
  
  IBinder hf()
  {
    if (this.Qh == null) {
      return null;
    }
    return this.Qh.gK().asBinder();
  }
  
  public MarkerOptions icon(BitmapDescriptor paramBitmapDescriptor)
  {
    this.Qh = paramBitmapDescriptor;
    return this;
  }
  
  public MarkerOptions infoWindowAnchor(float paramFloat1, float paramFloat2)
  {
    this.Ql = paramFloat1;
    this.Qm = paramFloat2;
    return this;
  }
  
  public boolean isDraggable()
  {
    return this.Qi;
  }
  
  public boolean isFlat()
  {
    return this.Qj;
  }
  
  public boolean isVisible()
  {
    return this.PQ;
  }
  
  public MarkerOptions position(LatLng paramLatLng)
  {
    this.Qf = paramLatLng;
    return this;
  }
  
  public MarkerOptions rotation(float paramFloat)
  {
    this.Qk = paramFloat;
    return this;
  }
  
  public MarkerOptions snippet(String paramString)
  {
    this.Qg = paramString;
    return this;
  }
  
  public MarkerOptions title(String paramString)
  {
    this.CX = paramString;
    return this;
  }
  
  public MarkerOptions visible(boolean paramBoolean)
  {
    this.PQ = paramBoolean;
    return this;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    if (r.hc())
    {
      f.a(this, paramParcel, paramInt);
      return;
    }
    MarkerOptionsCreator.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\maps\model\MarkerOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */