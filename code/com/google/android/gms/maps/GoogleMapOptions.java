package com.google.android.gms.maps;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.util.AttributeSet;
import com.google.android.gms.R.styleable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.internal.r;
import com.google.android.gms.maps.model.CameraPosition;

public final class GoogleMapOptions
  implements SafeParcelable
{
  public static final GoogleMapOptionsCreator CREATOR = new GoogleMapOptionsCreator();
  private Boolean Pc;
  private Boolean Pd;
  private int Pe = -1;
  private CameraPosition Pf;
  private Boolean Pg;
  private Boolean Ph;
  private Boolean Pi;
  private Boolean Pj;
  private Boolean Pk;
  private Boolean Pl;
  private final int wj;
  
  public GoogleMapOptions()
  {
    this.wj = 1;
  }
  
  GoogleMapOptions(int paramInt1, byte paramByte1, byte paramByte2, int paramInt2, CameraPosition paramCameraPosition, byte paramByte3, byte paramByte4, byte paramByte5, byte paramByte6, byte paramByte7, byte paramByte8)
  {
    this.wj = paramInt1;
    this.Pc = com.google.android.gms.maps.internal.a.a(paramByte1);
    this.Pd = com.google.android.gms.maps.internal.a.a(paramByte2);
    this.Pe = paramInt2;
    this.Pf = paramCameraPosition;
    this.Pg = com.google.android.gms.maps.internal.a.a(paramByte3);
    this.Ph = com.google.android.gms.maps.internal.a.a(paramByte4);
    this.Pi = com.google.android.gms.maps.internal.a.a(paramByte5);
    this.Pj = com.google.android.gms.maps.internal.a.a(paramByte6);
    this.Pk = com.google.android.gms.maps.internal.a.a(paramByte7);
    this.Pl = com.google.android.gms.maps.internal.a.a(paramByte8);
  }
  
  public static GoogleMapOptions createFromAttributes(Context paramContext, AttributeSet paramAttributeSet)
  {
    if (paramAttributeSet == null) {
      return null;
    }
    TypedArray localTypedArray = paramContext.getResources().obtainAttributes(paramAttributeSet, R.styleable.MapAttrs);
    GoogleMapOptions localGoogleMapOptions = new GoogleMapOptions();
    if (localTypedArray.hasValue(0)) {
      localGoogleMapOptions.mapType(localTypedArray.getInt(0, -1));
    }
    if (localTypedArray.hasValue(13)) {
      localGoogleMapOptions.zOrderOnTop(localTypedArray.getBoolean(13, false));
    }
    if (localTypedArray.hasValue(12)) {
      localGoogleMapOptions.useViewLifecycleInFragment(localTypedArray.getBoolean(12, false));
    }
    if (localTypedArray.hasValue(6)) {
      localGoogleMapOptions.compassEnabled(localTypedArray.getBoolean(6, true));
    }
    if (localTypedArray.hasValue(7)) {
      localGoogleMapOptions.rotateGesturesEnabled(localTypedArray.getBoolean(7, true));
    }
    if (localTypedArray.hasValue(8)) {
      localGoogleMapOptions.scrollGesturesEnabled(localTypedArray.getBoolean(8, true));
    }
    if (localTypedArray.hasValue(9)) {
      localGoogleMapOptions.tiltGesturesEnabled(localTypedArray.getBoolean(9, true));
    }
    if (localTypedArray.hasValue(11)) {
      localGoogleMapOptions.zoomGesturesEnabled(localTypedArray.getBoolean(11, true));
    }
    if (localTypedArray.hasValue(10)) {
      localGoogleMapOptions.zoomControlsEnabled(localTypedArray.getBoolean(10, true));
    }
    localGoogleMapOptions.camera(CameraPosition.createFromAttributes(paramContext, paramAttributeSet));
    localTypedArray.recycle();
    return localGoogleMapOptions;
  }
  
  public GoogleMapOptions camera(CameraPosition paramCameraPosition)
  {
    this.Pf = paramCameraPosition;
    return this;
  }
  
  public GoogleMapOptions compassEnabled(boolean paramBoolean)
  {
    this.Ph = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  byte gN()
  {
    return com.google.android.gms.maps.internal.a.c(this.Pc);
  }
  
  byte gO()
  {
    return com.google.android.gms.maps.internal.a.c(this.Pd);
  }
  
  byte gP()
  {
    return com.google.android.gms.maps.internal.a.c(this.Pg);
  }
  
  byte gQ()
  {
    return com.google.android.gms.maps.internal.a.c(this.Ph);
  }
  
  byte gR()
  {
    return com.google.android.gms.maps.internal.a.c(this.Pi);
  }
  
  byte gS()
  {
    return com.google.android.gms.maps.internal.a.c(this.Pj);
  }
  
  byte gT()
  {
    return com.google.android.gms.maps.internal.a.c(this.Pk);
  }
  
  byte gU()
  {
    return com.google.android.gms.maps.internal.a.c(this.Pl);
  }
  
  public CameraPosition getCamera()
  {
    return this.Pf;
  }
  
  public Boolean getCompassEnabled()
  {
    return this.Ph;
  }
  
  public int getMapType()
  {
    return this.Pe;
  }
  
  public Boolean getRotateGesturesEnabled()
  {
    return this.Pl;
  }
  
  public Boolean getScrollGesturesEnabled()
  {
    return this.Pi;
  }
  
  public Boolean getTiltGesturesEnabled()
  {
    return this.Pk;
  }
  
  public Boolean getUseViewLifecycleInFragment()
  {
    return this.Pd;
  }
  
  int getVersionCode()
  {
    return this.wj;
  }
  
  public Boolean getZOrderOnTop()
  {
    return this.Pc;
  }
  
  public Boolean getZoomControlsEnabled()
  {
    return this.Pg;
  }
  
  public Boolean getZoomGesturesEnabled()
  {
    return this.Pj;
  }
  
  public GoogleMapOptions mapType(int paramInt)
  {
    this.Pe = paramInt;
    return this;
  }
  
  public GoogleMapOptions rotateGesturesEnabled(boolean paramBoolean)
  {
    this.Pl = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public GoogleMapOptions scrollGesturesEnabled(boolean paramBoolean)
  {
    this.Pi = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public GoogleMapOptions tiltGesturesEnabled(boolean paramBoolean)
  {
    this.Pk = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public GoogleMapOptions useViewLifecycleInFragment(boolean paramBoolean)
  {
    this.Pd = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    if (r.hc())
    {
      a.a(this, paramParcel, paramInt);
      return;
    }
    GoogleMapOptionsCreator.a(this, paramParcel, paramInt);
  }
  
  public GoogleMapOptions zOrderOnTop(boolean paramBoolean)
  {
    this.Pc = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public GoogleMapOptions zoomControlsEnabled(boolean paramBoolean)
  {
    this.Pg = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public GoogleMapOptions zoomGesturesEnabled(boolean paramBoolean)
  {
    this.Pj = Boolean.valueOf(paramBoolean);
    return this;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\maps\GoogleMapOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */