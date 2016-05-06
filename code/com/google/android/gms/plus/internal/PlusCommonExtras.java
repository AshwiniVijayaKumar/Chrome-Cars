package com.google.android.gms.plus.internal;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.ep;
import com.google.android.gms.internal.ep.a;

public class PlusCommonExtras
  implements SafeParcelable
{
  public static final f CREATOR = new f();
  public static String TAG = "PlusCommonExtras";
  private String Rj;
  private String Rk;
  private final int wj;
  
  public PlusCommonExtras()
  {
    this.wj = 1;
    this.Rj = "";
    this.Rk = "";
  }
  
  PlusCommonExtras(int paramInt, String paramString1, String paramString2)
  {
    this.wj = paramInt;
    this.Rj = paramString1;
    this.Rk = paramString2;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof PlusCommonExtras)) {}
    do
    {
      return false;
      paramObject = (PlusCommonExtras)paramObject;
    } while ((this.wj != ((PlusCommonExtras)paramObject).wj) || (!ep.equal(this.Rj, ((PlusCommonExtras)paramObject).Rj)) || (!ep.equal(this.Rk, ((PlusCommonExtras)paramObject).Rk)));
    return true;
  }
  
  public int getVersionCode()
  {
    return this.wj;
  }
  
  public int hashCode()
  {
    return ep.hashCode(new Object[] { Integer.valueOf(this.wj), this.Rj, this.Rk });
  }
  
  public String ho()
  {
    return this.Rj;
  }
  
  public String hp()
  {
    return this.Rk;
  }
  
  public void m(Bundle paramBundle) {}
  
  public String toString()
  {
    return ep.e(this).a("versionCode", Integer.valueOf(this.wj)).a("Gpsrc", this.Rj).a("ClientCallingPackage", this.Rk).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    f.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\plus\internal\PlusCommonExtras.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */