package com.google.android.gms.internal;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

public final class hx
  extends hm
  implements SafeParcelable
{
  public static final hy CREATOR = new hy();
  private final Bundle Od;
  private final hz Oe;
  private final LatLng Of;
  private final float Og;
  private final LatLngBounds Oh;
  private final String Oi;
  private final Uri Oj;
  private final boolean Ok;
  private final float Ol;
  private final int Om;
  private final long On;
  private final List<ht> Oo;
  private final Map<ht, String> Op;
  private final TimeZone Oq;
  private Locale Or;
  private ic Os;
  private final String uS;
  final int wj;
  
  hx(int paramInt1, String paramString1, List<ht> paramList, Bundle paramBundle, hz paramhz, LatLng paramLatLng, float paramFloat1, LatLngBounds paramLatLngBounds, String paramString2, Uri paramUri, boolean paramBoolean, float paramFloat2, int paramInt2, long paramLong)
  {
    this.wj = paramInt1;
    this.uS = paramString1;
    this.Oo = Collections.unmodifiableList(paramList);
    this.Od = paramBundle;
    this.Oe = paramhz;
    this.Of = paramLatLng;
    this.Og = paramFloat1;
    this.Oh = paramLatLngBounds;
    this.Oi = paramString2;
    this.Oj = paramUri;
    this.Ok = paramBoolean;
    this.Ol = paramFloat2;
    this.Om = paramInt2;
    this.On = paramLong;
    paramString1 = new HashMap();
    paramList = paramBundle.keySet().iterator();
    while (paramList.hasNext())
    {
      paramhz = (String)paramList.next();
      paramString1.put(ht.aI(paramhz), paramBundle.getString(paramhz));
    }
    this.Op = Collections.unmodifiableMap(paramString1);
    this.Oq = TimeZone.getTimeZone(this.Oi);
    this.Or = null;
    this.Os = null;
  }
  
  private void aJ(String paramString)
  {
    if (this.Os != null) {
      this.Os.a(new hx.a.a(this.uS, paramString));
    }
  }
  
  public int describeContents()
  {
    hy localhy = CREATOR;
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (!(paramObject instanceof hx)) {
        return false;
      }
      paramObject = (hx)paramObject;
    } while ((this.uS.equals(((hx)paramObject).uS)) && (ep.equal(this.Or, ((hx)paramObject).Or)) && (this.On == ((hx)paramObject).On));
    return false;
  }
  
  public Uri gA()
  {
    aJ("getWebsiteUri");
    return this.Oj;
  }
  
  public boolean gB()
  {
    aJ("isPermanentlyClosed");
    return this.Ok;
  }
  
  public int gC()
  {
    aJ("getPriceLevel");
    return this.Om;
  }
  
  public long gD()
  {
    return this.On;
  }
  
  public Bundle gE()
  {
    return this.Od;
  }
  
  public hz gF()
  {
    return this.Oe;
  }
  
  public String gG()
  {
    return this.Oi;
  }
  
  public String getId()
  {
    aJ("getId");
    return this.uS;
  }
  
  public float getRating()
  {
    aJ("getRating");
    return this.Ol;
  }
  
  public List<ht> gw()
  {
    aJ("getTypes");
    return this.Oo;
  }
  
  public LatLng gx()
  {
    aJ("getLatLng");
    return this.Of;
  }
  
  public float gy()
  {
    aJ("getLevelNumber");
    return this.Og;
  }
  
  public LatLngBounds gz()
  {
    aJ("getViewport");
    return this.Oh;
  }
  
  public int hashCode()
  {
    return ep.hashCode(new Object[] { this.uS, this.Or, Long.valueOf(this.On) });
  }
  
  public String toString()
  {
    return ep.e(this).a("id", this.uS).a("localization", this.Oe).a("locale", this.Or).a("latlng", this.Of).a("levelNumber", Float.valueOf(this.Og)).a("viewport", this.Oh).a("timeZone", this.Oi).a("websiteUri", this.Oj).a("isPermanentlyClosed", Boolean.valueOf(this.Ok)).a("priceLevel", Integer.valueOf(this.Om)).a("timestampSecs", Long.valueOf(this.On)).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    hy localhy = CREATOR;
    hy.a(this, paramParcel, paramInt);
  }
  
  public static final class a
    implements SafeParcelable
  {
    public static final hw CREATOR = new hw();
    private final String LE;
    private final String Ot;
    private final int Ou;
    private final String mTag;
    final int wj;
    
    a(int paramInt1, String paramString1, String paramString2, String paramString3, int paramInt2)
    {
      this.wj = paramInt1;
      this.LE = paramString1;
      this.mTag = paramString2;
      this.Ot = paramString3;
      this.Ou = paramInt2;
    }
    
    public int describeContents()
    {
      hw localhw = CREATOR;
      return 0;
    }
    
    public boolean equals(Object paramObject)
    {
      if (this == paramObject) {}
      do
      {
        return true;
        if (!(paramObject instanceof a)) {
          return false;
        }
        paramObject = (a)paramObject;
      } while ((this.LE.equals(((a)paramObject).LE)) && (ep.equal(this.mTag, ((a)paramObject).mTag)));
      return false;
    }
    
    public String gH()
    {
      return this.Ot;
    }
    
    public int gI()
    {
      return this.Ou;
    }
    
    public String getTag()
    {
      return this.mTag;
    }
    
    public String gt()
    {
      return this.LE;
    }
    
    public int hashCode()
    {
      return ep.hashCode(new Object[] { this.LE, this.mTag, this.Ot, Integer.valueOf(this.Ou) });
    }
    
    public String toString()
    {
      return ep.e(this).a("placeId", this.LE).a("tag", this.mTag).a("callingAppPackageName", this.Ot).a("callingAppVersionCode", Integer.valueOf(this.Ou)).toString();
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      hw localhw = CREATOR;
      hw.a(this, paramParcel, paramInt);
    }
    
    public static class a
    {
      private final String LE;
      private String Ot;
      private int Ou;
      private final String mTag;
      
      public a(String paramString1, String paramString2)
      {
        this.LE = paramString1;
        this.mTag = paramString2;
      }
      
      public a aK(String paramString)
      {
        this.Ot = paramString;
        return this;
      }
      
      public a bv(int paramInt)
      {
        this.Ou = paramInt;
        return this;
      }
      
      public hx.a gJ()
      {
        return new hx.a(0, this.LE, this.mTag, this.Ot, this.Ou);
      }
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\hx.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */