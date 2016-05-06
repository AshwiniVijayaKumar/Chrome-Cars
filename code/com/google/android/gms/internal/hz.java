package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

public final class hz
  implements SafeParcelable
{
  public static final ia CREATOR = new ia();
  public final String Ov;
  public final String Ow;
  public final String Ox;
  public final List<String> Oy;
  public final String name;
  public final int versionCode;
  
  public hz(int paramInt, String paramString1, String paramString2, String paramString3, String paramString4, List<String> paramList)
  {
    this.versionCode = paramInt;
    this.name = paramString1;
    this.Ov = paramString2;
    this.Ow = paramString3;
    this.Ox = paramString4;
    this.Oy = paramList;
  }
  
  public int describeContents()
  {
    ia localia = CREATOR;
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (!(paramObject instanceof hz)) {
        return false;
      }
      paramObject = (hz)paramObject;
    } while ((ep.equal(this.name, ((hz)paramObject).name)) && (ep.equal(this.Ov, ((hz)paramObject).Ov)) && (ep.equal(this.Ow, ((hz)paramObject).Ow)) && (ep.equal(this.Ox, ((hz)paramObject).Ox)) && (ep.equal(this.Oy, ((hz)paramObject).Oy)));
    return false;
  }
  
  public int hashCode()
  {
    return ep.hashCode(new Object[] { this.name, this.Ov, this.Ow, this.Ox });
  }
  
  public String toString()
  {
    return ep.e(this).a("name", this.name).a("address", this.Ov).a("internationalPhoneNumber", this.Ow).a("regularOpenHours", this.Ox).a("attributions", this.Oy).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    ia localia = CREATOR;
    ia.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\hz.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */