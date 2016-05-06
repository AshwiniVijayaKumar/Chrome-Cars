package com.google.android.gms.internal;

import android.content.Context;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Locale;

public class id
  implements SafeParcelable
{
  public static final ie CREATOR = new ie();
  public final String OG;
  public final String OH;
  public final int versionCode;
  
  public id(int paramInt, String paramString1, String paramString2)
  {
    this.versionCode = paramInt;
    this.OG = paramString1;
    this.OH = paramString2;
  }
  
  public id(Context paramContext, Locale paramLocale)
  {
    this.versionCode = 0;
    this.OG = paramContext.getPackageName();
    this.OH = paramLocale.toString();
  }
  
  public int describeContents()
  {
    ie localie = CREATOR;
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if ((paramObject == null) || (!(paramObject instanceof id))) {
        return false;
      }
      paramObject = (id)paramObject;
    } while ((this.OH.equals(((id)paramObject).OH)) && (this.OG.equals(((id)paramObject).OG)));
    return false;
  }
  
  public int hashCode()
  {
    return ep.hashCode(new Object[] { this.OG, this.OH });
  }
  
  public String toString()
  {
    return ep.e(this).a("clientPackageName", this.OG).a("locale", this.OH).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    ie localie = CREATOR;
    ie.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\id.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */