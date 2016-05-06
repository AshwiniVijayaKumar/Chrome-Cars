package com.google.android.gms.ads.doubleclick;

import android.content.Context;
import android.location.Location;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.internal.aj;
import com.google.android.gms.internal.aj.a;
import java.util.Date;
import java.util.Set;

public final class PublisherAdRequest
{
  public static final String DEVICE_ID_EMULATOR = aj.DEVICE_ID_EMULATOR;
  public static final int ERROR_CODE_INTERNAL_ERROR = 0;
  public static final int ERROR_CODE_INVALID_REQUEST = 1;
  public static final int ERROR_CODE_NETWORK_ERROR = 2;
  public static final int ERROR_CODE_NO_FILL = 3;
  public static final int GENDER_FEMALE = 2;
  public static final int GENDER_MALE = 1;
  public static final int GENDER_UNKNOWN = 0;
  private final aj kA;
  
  private PublisherAdRequest(Builder paramBuilder)
  {
    this.kA = new aj(Builder.a(paramBuilder));
  }
  
  aj N()
  {
    return this.kA;
  }
  
  public Date getBirthday()
  {
    return this.kA.getBirthday();
  }
  
  public String getContentUrl()
  {
    return this.kA.getContentUrl();
  }
  
  public int getGender()
  {
    return this.kA.getGender();
  }
  
  public Set<String> getKeywords()
  {
    return this.kA.getKeywords();
  }
  
  public Location getLocation()
  {
    return this.kA.getLocation();
  }
  
  public boolean getManualImpressionsEnabled()
  {
    return this.kA.getManualImpressionsEnabled();
  }
  
  public <T extends NetworkExtras> T getNetworkExtras(Class<T> paramClass)
  {
    return this.kA.getNetworkExtras(paramClass);
  }
  
  public String getPublisherProvidedId()
  {
    return this.kA.getPublisherProvidedId();
  }
  
  public boolean isTestDevice(Context paramContext)
  {
    return this.kA.isTestDevice(paramContext);
  }
  
  public static final class Builder
  {
    private final aj.a kB = new aj.a();
    
    public Builder addKeyword(String paramString)
    {
      this.kB.g(paramString);
      return this;
    }
    
    public Builder addNetworkExtras(NetworkExtras paramNetworkExtras)
    {
      this.kB.a(paramNetworkExtras);
      return this;
    }
    
    public Builder addTestDevice(String paramString)
    {
      this.kB.h(paramString);
      return this;
    }
    
    public PublisherAdRequest build()
    {
      return new PublisherAdRequest(this, null);
    }
    
    public Builder setBirthday(Date paramDate)
    {
      this.kB.a(paramDate);
      return this;
    }
    
    public Builder setContentUrl(String paramString)
    {
      this.kB.i(paramString);
      return this;
    }
    
    public Builder setGender(int paramInt)
    {
      this.kB.d(paramInt);
      return this;
    }
    
    public Builder setLocation(Location paramLocation)
    {
      this.kB.a(paramLocation);
      return this;
    }
    
    public Builder setManualImpressionsEnabled(boolean paramBoolean)
    {
      this.kB.d(paramBoolean);
      return this;
    }
    
    public Builder setPublisherProvidedId(String paramString)
    {
      this.kB.j(paramString);
      return this;
    }
    
    public Builder tagForChildDirectedTreatment(boolean paramBoolean)
    {
      this.kB.e(paramBoolean);
      return this;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\ads\doubleclick\PublisherAdRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */