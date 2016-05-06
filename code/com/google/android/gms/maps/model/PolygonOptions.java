package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.internal.r;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public final class PolygonOptions
  implements SafeParcelable
{
  public static final PolygonOptionsCreator CREATOR = new PolygonOptionsCreator();
  private float PM = 10.0F;
  private int PN = -16777216;
  private int PO = 0;
  private float PP = 0.0F;
  private boolean PQ = true;
  private final List<LatLng> Qo;
  private final List<List<LatLng>> Qp;
  private boolean Qq = false;
  private final int wj;
  
  public PolygonOptions()
  {
    this.wj = 1;
    this.Qo = new ArrayList();
    this.Qp = new ArrayList();
  }
  
  PolygonOptions(int paramInt1, List<LatLng> paramList, List paramList1, float paramFloat1, int paramInt2, int paramInt3, float paramFloat2, boolean paramBoolean1, boolean paramBoolean2)
  {
    this.wj = paramInt1;
    this.Qo = paramList;
    this.Qp = paramList1;
    this.PM = paramFloat1;
    this.PN = paramInt2;
    this.PO = paramInt3;
    this.PP = paramFloat2;
    this.PQ = paramBoolean1;
    this.Qq = paramBoolean2;
  }
  
  public PolygonOptions add(LatLng paramLatLng)
  {
    this.Qo.add(paramLatLng);
    return this;
  }
  
  public PolygonOptions add(LatLng... paramVarArgs)
  {
    this.Qo.addAll(Arrays.asList(paramVarArgs));
    return this;
  }
  
  public PolygonOptions addAll(Iterable<LatLng> paramIterable)
  {
    paramIterable = paramIterable.iterator();
    while (paramIterable.hasNext())
    {
      LatLng localLatLng = (LatLng)paramIterable.next();
      this.Qo.add(localLatLng);
    }
    return this;
  }
  
  public PolygonOptions addHole(Iterable<LatLng> paramIterable)
  {
    ArrayList localArrayList = new ArrayList();
    paramIterable = paramIterable.iterator();
    while (paramIterable.hasNext()) {
      localArrayList.add((LatLng)paramIterable.next());
    }
    this.Qp.add(localArrayList);
    return this;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public PolygonOptions fillColor(int paramInt)
  {
    this.PO = paramInt;
    return this;
  }
  
  public PolygonOptions geodesic(boolean paramBoolean)
  {
    this.Qq = paramBoolean;
    return this;
  }
  
  public int getFillColor()
  {
    return this.PO;
  }
  
  public List<List<LatLng>> getHoles()
  {
    return this.Qp;
  }
  
  public List<LatLng> getPoints()
  {
    return this.Qo;
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
  
  List hg()
  {
    return this.Qp;
  }
  
  public boolean isGeodesic()
  {
    return this.Qq;
  }
  
  public boolean isVisible()
  {
    return this.PQ;
  }
  
  public PolygonOptions strokeColor(int paramInt)
  {
    this.PN = paramInt;
    return this;
  }
  
  public PolygonOptions strokeWidth(float paramFloat)
  {
    this.PM = paramFloat;
    return this;
  }
  
  public PolygonOptions visible(boolean paramBoolean)
  {
    this.PQ = paramBoolean;
    return this;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    if (r.hc())
    {
      g.a(this, paramParcel, paramInt);
      return;
    }
    PolygonOptionsCreator.a(this, paramParcel, paramInt);
  }
  
  public PolygonOptions zIndex(float paramFloat)
  {
    this.PP = paramFloat;
    return this;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\maps\model\PolygonOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */