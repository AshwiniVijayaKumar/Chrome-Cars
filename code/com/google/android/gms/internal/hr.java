package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.location.LocationRequest;

public final class hr
  implements SafeParcelable
{
  public static final hs CREATOR = new hs();
  private final LocationRequest LF;
  private final hn LG;
  final int wj;
  
  public hr(int paramInt, LocationRequest paramLocationRequest, hn paramhn)
  {
    this.wj = paramInt;
    this.LF = paramLocationRequest;
    this.LG = paramhn;
  }
  
  public int describeContents()
  {
    hs localhs = CREATOR;
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (!(paramObject instanceof hr)) {
        return false;
      }
      paramObject = (hr)paramObject;
    } while ((this.LF.equals(((hr)paramObject).LF)) && (this.LG.equals(((hr)paramObject).LG)));
    return false;
  }
  
  public LocationRequest gu()
  {
    return this.LF;
  }
  
  public hn gv()
  {
    return this.LG;
  }
  
  public int hashCode()
  {
    return ep.hashCode(new Object[] { this.LF, this.LG });
  }
  
  public String toString()
  {
    return ep.e(this).a("locationRequest", this.LF).a("filter", this.LG).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    hs localhs = CREATOR;
    hs.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\hr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */