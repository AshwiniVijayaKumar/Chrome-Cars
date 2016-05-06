package com.google.android.gms.internal;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.ads.mediation.admob.AdMobExtras;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

public final class z
  implements SafeParcelable
{
  public static final aa CREATOR = new aa();
  public final Bundle extras;
  public final long le;
  public final int lf;
  public final List<String> lg;
  public final boolean lh;
  public final boolean li;
  public final String lj;
  public final am lk;
  public final Location ll;
  public final String lm;
  public final int tagForChildDirectedTreatment;
  public final int versionCode;
  
  z(int paramInt1, long paramLong, Bundle paramBundle, int paramInt2, List<String> paramList, boolean paramBoolean1, int paramInt3, boolean paramBoolean2, String paramString1, am paramam, Location paramLocation, String paramString2)
  {
    this.versionCode = paramInt1;
    this.le = paramLong;
    this.extras = paramBundle;
    this.lf = paramInt2;
    this.lg = paramList;
    this.lh = paramBoolean1;
    this.tagForChildDirectedTreatment = paramInt3;
    this.li = paramBoolean2;
    this.lj = paramString1;
    this.lk = paramam;
    this.ll = paramLocation;
    this.lm = paramString2;
  }
  
  public z(Context paramContext, aj paramaj)
  {
    this.versionCode = 3;
    Object localObject1 = paramaj.getBirthday();
    long l;
    if (localObject1 != null)
    {
      l = ((Date)localObject1).getTime();
      this.le = l;
      this.lm = paramaj.getContentUrl();
      this.lf = paramaj.getGender();
      localObject1 = paramaj.getKeywords();
      if (((Set)localObject1).isEmpty()) {
        break label185;
      }
      localObject1 = Collections.unmodifiableList(new ArrayList((Collection)localObject1));
      label80:
      this.lg = ((List)localObject1);
      this.lh = paramaj.isTestDevice(paramContext);
      this.tagForChildDirectedTreatment = paramaj.al();
      this.ll = paramaj.getLocation();
      paramContext = (AdMobExtras)paramaj.getNetworkExtras(AdMobExtras.class);
      if (paramContext == null) {
        break label191;
      }
    }
    label185:
    label191:
    for (paramContext = paramContext.getExtras();; paramContext = null)
    {
      this.extras = paramContext;
      this.li = paramaj.getManualImpressionsEnabled();
      this.lj = paramaj.getPublisherProvidedId();
      paramaj = paramaj.aj();
      paramContext = (Context)localObject2;
      if (paramaj != null) {
        paramContext = new am(paramaj);
      }
      this.lk = paramContext;
      return;
      l = -1L;
      break;
      localObject1 = null;
      break label80;
    }
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    aa.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\z.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */