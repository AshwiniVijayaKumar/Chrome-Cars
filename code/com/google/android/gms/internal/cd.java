package com.google.android.gms.internal;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class cd
  implements SafeParcelable
{
  public static final ce CREATOR = new ce();
  public final String adUnitId;
  public final ApplicationInfo applicationInfo;
  public final db kN;
  public final ab kQ;
  public final Bundle ob;
  public final z oc;
  public final PackageInfo od;
  public final String oe;
  public final String of;
  public final String og;
  public final Bundle oh;
  public final int versionCode;
  
  cd(int paramInt, Bundle paramBundle1, z paramz, ab paramab, String paramString1, ApplicationInfo paramApplicationInfo, PackageInfo paramPackageInfo, String paramString2, String paramString3, String paramString4, db paramdb, Bundle paramBundle2)
  {
    this.versionCode = paramInt;
    this.ob = paramBundle1;
    this.oc = paramz;
    this.kQ = paramab;
    this.adUnitId = paramString1;
    this.applicationInfo = paramApplicationInfo;
    this.od = paramPackageInfo;
    this.oe = paramString2;
    this.of = paramString3;
    this.og = paramString4;
    this.kN = paramdb;
    this.oh = paramBundle2;
  }
  
  public cd(Bundle paramBundle1, z paramz, ab paramab, String paramString1, ApplicationInfo paramApplicationInfo, PackageInfo paramPackageInfo, String paramString2, String paramString3, String paramString4, db paramdb, Bundle paramBundle2)
  {
    this(2, paramBundle1, paramz, paramab, paramString1, paramApplicationInfo, paramPackageInfo, paramString2, paramString3, paramString4, paramdb, paramBundle2);
  }
  
  public cd(a parama, String paramString)
  {
    this(parama.ob, parama.oc, parama.kQ, parama.adUnitId, parama.applicationInfo, parama.od, paramString, parama.of, parama.og, parama.kN, parama.oh);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    ce.a(this, paramParcel, paramInt);
  }
  
  public static final class a
  {
    public final String adUnitId;
    public final ApplicationInfo applicationInfo;
    public final db kN;
    public final ab kQ;
    public final Bundle ob;
    public final z oc;
    public final PackageInfo od;
    public final String of;
    public final String og;
    public final Bundle oh;
    
    public a(Bundle paramBundle1, z paramz, ab paramab, String paramString1, ApplicationInfo paramApplicationInfo, PackageInfo paramPackageInfo, String paramString2, String paramString3, db paramdb, Bundle paramBundle2)
    {
      this.ob = paramBundle1;
      this.oc = paramz;
      this.kQ = paramab;
      this.adUnitId = paramString1;
      this.applicationInfo = paramApplicationInfo;
      this.od = paramPackageInfo;
      this.of = paramString2;
      this.og = paramString3;
      this.kN = paramdb;
      this.oh = paramBundle2;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\cd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */