package com.google.android.gms.internal;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.dynamic.b;
import com.google.android.gms.dynamic.b.a;
import com.google.android.gms.dynamic.c;

public final class bq
  implements SafeParcelable
{
  public static final bp CREATOR = new bp();
  public final db kN;
  public final String mZ;
  public final int nA;
  public final bn nr;
  public final u ns;
  public final br nt;
  public final dd nu;
  public final ap nv;
  public final String nw;
  public final boolean nx;
  public final String ny;
  public final bu nz;
  public final int orientation;
  public final int versionCode;
  
  bq(int paramInt1, bn parambn, IBinder paramIBinder1, IBinder paramIBinder2, IBinder paramIBinder3, IBinder paramIBinder4, String paramString1, boolean paramBoolean, String paramString2, IBinder paramIBinder5, int paramInt2, int paramInt3, String paramString3, db paramdb)
  {
    this.versionCode = paramInt1;
    this.nr = parambn;
    this.ns = ((u)c.b(b.a.G(paramIBinder1)));
    this.nt = ((br)c.b(b.a.G(paramIBinder2)));
    this.nu = ((dd)c.b(b.a.G(paramIBinder3)));
    this.nv = ((ap)c.b(b.a.G(paramIBinder4)));
    this.nw = paramString1;
    this.nx = paramBoolean;
    this.ny = paramString2;
    this.nz = ((bu)c.b(b.a.G(paramIBinder5)));
    this.orientation = paramInt2;
    this.nA = paramInt3;
    this.mZ = paramString3;
    this.kN = paramdb;
  }
  
  public bq(bn parambn, u paramu, br parambr, bu parambu, db paramdb)
  {
    this.versionCode = 1;
    this.nr = parambn;
    this.ns = paramu;
    this.nt = parambr;
    this.nu = null;
    this.nv = null;
    this.nw = null;
    this.nx = false;
    this.ny = null;
    this.nz = parambu;
    this.orientation = -1;
    this.nA = 4;
    this.mZ = null;
    this.kN = paramdb;
  }
  
  public bq(u paramu, br parambr, ap paramap, bu parambu, dd paramdd, boolean paramBoolean, int paramInt, String paramString, db paramdb)
  {
    this.versionCode = 1;
    this.nr = null;
    this.ns = paramu;
    this.nt = parambr;
    this.nu = paramdd;
    this.nv = paramap;
    this.nw = null;
    this.nx = paramBoolean;
    this.ny = null;
    this.nz = parambu;
    this.orientation = paramInt;
    this.nA = 3;
    this.mZ = paramString;
    this.kN = paramdb;
  }
  
  public bq(u paramu, br parambr, ap paramap, bu parambu, dd paramdd, boolean paramBoolean, int paramInt, String paramString1, String paramString2, db paramdb)
  {
    this.versionCode = 1;
    this.nr = null;
    this.ns = paramu;
    this.nt = parambr;
    this.nu = paramdd;
    this.nv = paramap;
    this.nw = paramString2;
    this.nx = paramBoolean;
    this.ny = paramString1;
    this.nz = parambu;
    this.orientation = paramInt;
    this.nA = 3;
    this.mZ = null;
    this.kN = paramdb;
  }
  
  public bq(u paramu, br parambr, bu parambu, dd paramdd, int paramInt, db paramdb)
  {
    this.versionCode = 1;
    this.nr = null;
    this.ns = paramu;
    this.nt = parambr;
    this.nu = paramdd;
    this.nv = null;
    this.nw = null;
    this.nx = false;
    this.ny = null;
    this.nz = parambu;
    this.orientation = paramInt;
    this.nA = 1;
    this.mZ = null;
    this.kN = paramdb;
  }
  
  public bq(u paramu, br parambr, bu parambu, dd paramdd, boolean paramBoolean, int paramInt, db paramdb)
  {
    this.versionCode = 1;
    this.nr = null;
    this.ns = paramu;
    this.nt = parambr;
    this.nu = paramdd;
    this.nv = null;
    this.nw = null;
    this.nx = paramBoolean;
    this.ny = null;
    this.nz = parambu;
    this.orientation = paramInt;
    this.nA = 2;
    this.mZ = null;
    this.kN = paramdb;
  }
  
  public static bq a(Intent paramIntent)
  {
    try
    {
      paramIntent = paramIntent.getBundleExtra("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo");
      paramIntent.setClassLoader(bq.class.getClassLoader());
      paramIntent = (bq)paramIntent.getParcelable("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo");
      return paramIntent;
    }
    catch (Exception paramIntent) {}
    return null;
  }
  
  public static void a(Intent paramIntent, bq parambq)
  {
    Bundle localBundle = new Bundle(1);
    localBundle.putParcelable("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo", parambq);
    paramIntent.putExtra("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo", localBundle);
  }
  
  IBinder at()
  {
    return c.h(this.ns).asBinder();
  }
  
  IBinder au()
  {
    return c.h(this.nt).asBinder();
  }
  
  IBinder av()
  {
    return c.h(this.nu).asBinder();
  }
  
  IBinder aw()
  {
    return c.h(this.nv).asBinder();
  }
  
  IBinder ax()
  {
    return c.h(this.nz).asBinder();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    bp.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\bq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */