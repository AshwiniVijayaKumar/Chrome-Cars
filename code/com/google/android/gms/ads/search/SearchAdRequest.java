package com.google.android.gms.ads.search;

import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.internal.aj;
import com.google.android.gms.internal.aj.a;

public final class SearchAdRequest
{
  public static final int BORDER_TYPE_DASHED = 1;
  public static final int BORDER_TYPE_DOTTED = 2;
  public static final int BORDER_TYPE_NONE = 0;
  public static final int BORDER_TYPE_SOLID = 3;
  public static final int CALL_BUTTON_COLOR_DARK = 2;
  public static final int CALL_BUTTON_COLOR_LIGHT = 0;
  public static final int CALL_BUTTON_COLOR_MEDIUM = 1;
  public static final String DEVICE_ID_EMULATOR = aj.DEVICE_ID_EMULATOR;
  public static final int ERROR_CODE_INTERNAL_ERROR = 0;
  public static final int ERROR_CODE_INVALID_REQUEST = 1;
  public static final int ERROR_CODE_NETWORK_ERROR = 2;
  public static final int ERROR_CODE_NO_FILL = 3;
  private final aj kA;
  private final int qA;
  private final int qB;
  private final String qC;
  private final int qD;
  private final String qE;
  private final int qF;
  private final int qG;
  private final String qH;
  private final int qu;
  private final int qv;
  private final int qw;
  private final int qx;
  private final int qy;
  private final int qz;
  
  private SearchAdRequest(Builder paramBuilder)
  {
    this.qu = Builder.a(paramBuilder);
    this.qv = Builder.b(paramBuilder);
    this.qw = Builder.c(paramBuilder);
    this.qx = Builder.d(paramBuilder);
    this.qy = Builder.e(paramBuilder);
    this.qz = Builder.f(paramBuilder);
    this.qA = Builder.g(paramBuilder);
    this.qB = Builder.h(paramBuilder);
    this.qC = Builder.i(paramBuilder);
    this.qD = Builder.j(paramBuilder);
    this.qE = Builder.k(paramBuilder);
    this.qF = Builder.l(paramBuilder);
    this.qG = Builder.m(paramBuilder);
    this.qH = Builder.n(paramBuilder);
    this.kA = new aj(Builder.o(paramBuilder), this);
  }
  
  aj N()
  {
    return this.kA;
  }
  
  public int getAnchorTextColor()
  {
    return this.qu;
  }
  
  public int getBackgroundColor()
  {
    return this.qv;
  }
  
  public int getBackgroundGradientBottom()
  {
    return this.qw;
  }
  
  public int getBackgroundGradientTop()
  {
    return this.qx;
  }
  
  public int getBorderColor()
  {
    return this.qy;
  }
  
  public int getBorderThickness()
  {
    return this.qz;
  }
  
  public int getBorderType()
  {
    return this.qA;
  }
  
  public int getCallButtonColor()
  {
    return this.qB;
  }
  
  public String getCustomChannels()
  {
    return this.qC;
  }
  
  public int getDescriptionTextColor()
  {
    return this.qD;
  }
  
  public String getFontFace()
  {
    return this.qE;
  }
  
  public int getHeaderTextColor()
  {
    return this.qF;
  }
  
  public int getHeaderTextSize()
  {
    return this.qG;
  }
  
  public Location getLocation()
  {
    return this.kA.getLocation();
  }
  
  public <T extends NetworkExtras> T getNetworkExtras(Class<T> paramClass)
  {
    return this.kA.getNetworkExtras(paramClass);
  }
  
  public String getQuery()
  {
    return this.qH;
  }
  
  public boolean isTestDevice(Context paramContext)
  {
    return this.kA.isTestDevice(paramContext);
  }
  
  public static final class Builder
  {
    private final aj.a kB = new aj.a();
    private int qA = 0;
    private int qB;
    private String qC;
    private int qD;
    private String qE;
    private int qF;
    private int qG;
    private String qH;
    private int qu;
    private int qv;
    private int qw;
    private int qx;
    private int qy;
    private int qz;
    
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
    
    public SearchAdRequest build()
    {
      return new SearchAdRequest(this, null);
    }
    
    public Builder setAnchorTextColor(int paramInt)
    {
      this.qu = paramInt;
      return this;
    }
    
    public Builder setBackgroundColor(int paramInt)
    {
      this.qv = paramInt;
      this.qw = Color.argb(0, 0, 0, 0);
      this.qx = Color.argb(0, 0, 0, 0);
      return this;
    }
    
    public Builder setBackgroundGradient(int paramInt1, int paramInt2)
    {
      this.qv = Color.argb(0, 0, 0, 0);
      this.qw = paramInt2;
      this.qx = paramInt1;
      return this;
    }
    
    public Builder setBorderColor(int paramInt)
    {
      this.qy = paramInt;
      return this;
    }
    
    public Builder setBorderThickness(int paramInt)
    {
      this.qz = paramInt;
      return this;
    }
    
    public Builder setBorderType(int paramInt)
    {
      this.qA = paramInt;
      return this;
    }
    
    public Builder setCallButtonColor(int paramInt)
    {
      this.qB = paramInt;
      return this;
    }
    
    public Builder setCustomChannels(String paramString)
    {
      this.qC = paramString;
      return this;
    }
    
    public Builder setDescriptionTextColor(int paramInt)
    {
      this.qD = paramInt;
      return this;
    }
    
    public Builder setFontFace(String paramString)
    {
      this.qE = paramString;
      return this;
    }
    
    public Builder setHeaderTextColor(int paramInt)
    {
      this.qF = paramInt;
      return this;
    }
    
    public Builder setHeaderTextSize(int paramInt)
    {
      this.qG = paramInt;
      return this;
    }
    
    public Builder setLocation(Location paramLocation)
    {
      this.kB.a(paramLocation);
      return this;
    }
    
    public Builder setQuery(String paramString)
    {
      this.qH = paramString;
      return this;
    }
    
    public Builder tagForChildDirectedTreatment(boolean paramBoolean)
    {
      this.kB.e(paramBoolean);
      return this;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\ads\search\SearchAdRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */