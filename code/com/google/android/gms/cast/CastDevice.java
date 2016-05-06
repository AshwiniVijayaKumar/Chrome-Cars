package com.google.android.gms.cast;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.dr;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class CastDevice
  implements SafeParcelable
{
  public static final Parcelable.Creator<CastDevice> CREATOR = new b();
  private String wC;
  String wD;
  private Inet4Address wE;
  private String wF;
  private String wG;
  private String wH;
  private int wI;
  private List<WebImage> wJ;
  private final int wj;
  
  private CastDevice()
  {
    this(1, null, null, null, null, null, -1, new ArrayList());
  }
  
  CastDevice(int paramInt1, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, int paramInt2, List<WebImage> paramList)
  {
    this.wj = paramInt1;
    this.wC = paramString1;
    this.wD = paramString2;
    if (this.wD != null) {}
    try
    {
      paramString1 = InetAddress.getByName(this.wD);
      if ((paramString1 instanceof Inet4Address)) {
        this.wE = ((Inet4Address)paramString1);
      }
      this.wF = paramString3;
      this.wG = paramString4;
      this.wH = paramString5;
      this.wI = paramInt2;
      this.wJ = paramList;
      return;
    }
    catch (UnknownHostException paramString1)
    {
      for (;;)
      {
        this.wE = null;
      }
    }
  }
  
  public static CastDevice getFromBundle(Bundle paramBundle)
  {
    if (paramBundle == null) {
      return null;
    }
    paramBundle.setClassLoader(CastDevice.class.getClassLoader());
    return (CastDevice)paramBundle.getParcelable("com.google.android.gms.cast.EXTRA_CAST_DEVICE");
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == this) {}
    do
    {
      do
      {
        return true;
        if (!(paramObject instanceof CastDevice)) {
          return false;
        }
        paramObject = (CastDevice)paramObject;
        if (getDeviceId() != null) {
          break;
        }
      } while (((CastDevice)paramObject).getDeviceId() == null);
      return false;
    } while ((dr.a(this.wC, ((CastDevice)paramObject).wC)) && (dr.a(this.wE, ((CastDevice)paramObject).wE)) && (dr.a(this.wG, ((CastDevice)paramObject).wG)) && (dr.a(this.wF, ((CastDevice)paramObject).wF)) && (dr.a(this.wH, ((CastDevice)paramObject).wH)) && (this.wI == ((CastDevice)paramObject).wI) && (dr.a(this.wJ, ((CastDevice)paramObject).wJ)));
    return false;
  }
  
  public String getDeviceId()
  {
    return this.wC;
  }
  
  public String getDeviceVersion()
  {
    return this.wH;
  }
  
  public String getFriendlyName()
  {
    return this.wF;
  }
  
  public WebImage getIcon(int paramInt1, int paramInt2)
  {
    Object localObject1 = null;
    if (this.wJ.isEmpty()) {
      return null;
    }
    if ((paramInt1 <= 0) || (paramInt2 <= 0)) {
      return (WebImage)this.wJ.get(0);
    }
    Iterator localIterator = this.wJ.iterator();
    Object localObject2 = null;
    WebImage localWebImage;
    int i;
    int j;
    if (localIterator.hasNext())
    {
      localWebImage = (WebImage)localIterator.next();
      i = localWebImage.getWidth();
      j = localWebImage.getHeight();
      if ((i >= paramInt1) && (j >= paramInt2))
      {
        if ((localObject2 != null) && ((((WebImage)localObject2).getWidth() <= i) || (((WebImage)localObject2).getHeight() <= j))) {
          break label210;
        }
        localObject2 = localWebImage;
      }
    }
    label210:
    for (;;)
    {
      break;
      if ((i < paramInt1) && (j < paramInt2) && ((localObject1 == null) || ((((WebImage)localObject1).getWidth() < i) && (((WebImage)localObject1).getHeight() < j))))
      {
        localObject1 = localWebImage;
        continue;
        if (localObject2 != null) {}
        for (;;)
        {
          return (WebImage)localObject2;
          if (localObject1 != null) {
            localObject2 = localObject1;
          } else {
            localObject2 = (WebImage)this.wJ.get(0);
          }
        }
      }
    }
  }
  
  public List<WebImage> getIcons()
  {
    return Collections.unmodifiableList(this.wJ);
  }
  
  public Inet4Address getIpAddress()
  {
    return this.wE;
  }
  
  public String getModelName()
  {
    return this.wG;
  }
  
  public int getServicePort()
  {
    return this.wI;
  }
  
  int getVersionCode()
  {
    return this.wj;
  }
  
  public boolean hasIcons()
  {
    return !this.wJ.isEmpty();
  }
  
  public int hashCode()
  {
    if (this.wC == null) {
      return 0;
    }
    return this.wC.hashCode();
  }
  
  public boolean isSameDevice(CastDevice paramCastDevice)
  {
    if (paramCastDevice == null) {}
    do
    {
      return false;
      if (getDeviceId() != null) {
        break;
      }
    } while (paramCastDevice.getDeviceId() != null);
    return true;
    return dr.a(getDeviceId(), paramCastDevice.getDeviceId());
  }
  
  public void putInBundle(Bundle paramBundle)
  {
    if (paramBundle == null) {
      return;
    }
    paramBundle.putParcelable("com.google.android.gms.cast.EXTRA_CAST_DEVICE", this);
  }
  
  public String toString()
  {
    return String.format("\"%s\" (%s)", new Object[] { this.wF, this.wC });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    b.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\cast\CastDevice.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */