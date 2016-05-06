package com.google.android.gms.internal;

import android.content.Context;
import android.location.Location;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.search.SearchAdRequest;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class aj
{
  public static final String DEVICE_ID_EMULATOR = cz.r("emulator");
  private final Date d;
  private final Set<String> f;
  private final int lA;
  private final Set<String> lB;
  private final String lt;
  private final int lu;
  private final Location lv;
  private final boolean lw;
  private final Map<Class<? extends NetworkExtras>, NetworkExtras> lx;
  private final String ly;
  private final SearchAdRequest lz;
  
  public aj(a parama)
  {
    this(parama, null);
  }
  
  public aj(a parama, SearchAdRequest paramSearchAdRequest)
  {
    this.d = a.a(parama);
    this.lt = a.b(parama);
    this.lu = a.c(parama);
    this.f = Collections.unmodifiableSet(a.d(parama));
    this.lv = a.e(parama);
    this.lw = a.f(parama);
    this.lx = Collections.unmodifiableMap(a.g(parama));
    this.ly = a.h(parama);
    this.lz = paramSearchAdRequest;
    this.lA = a.i(parama);
    this.lB = Collections.unmodifiableSet(a.j(parama));
  }
  
  public SearchAdRequest aj()
  {
    return this.lz;
  }
  
  public Map<Class<? extends NetworkExtras>, NetworkExtras> ak()
  {
    return this.lx;
  }
  
  public int al()
  {
    return this.lA;
  }
  
  public Date getBirthday()
  {
    return this.d;
  }
  
  public String getContentUrl()
  {
    return this.lt;
  }
  
  public int getGender()
  {
    return this.lu;
  }
  
  public Set<String> getKeywords()
  {
    return this.f;
  }
  
  public Location getLocation()
  {
    return this.lv;
  }
  
  public boolean getManualImpressionsEnabled()
  {
    return this.lw;
  }
  
  public <T extends NetworkExtras> T getNetworkExtras(Class<T> paramClass)
  {
    return (NetworkExtras)this.lx.get(paramClass);
  }
  
  public String getPublisherProvidedId()
  {
    return this.ly;
  }
  
  public boolean isTestDevice(Context paramContext)
  {
    return this.lB.contains(cz.l(paramContext));
  }
  
  public static final class a
  {
    private Date d;
    private int lA = -1;
    private final HashSet<String> lC = new HashSet();
    private final HashMap<Class<? extends NetworkExtras>, NetworkExtras> lD = new HashMap();
    private final HashSet<String> lE = new HashSet();
    private String lt;
    private int lu = -1;
    private Location lv;
    private boolean lw = false;
    private String ly;
    
    public void a(Location paramLocation)
    {
      this.lv = paramLocation;
    }
    
    public void a(NetworkExtras paramNetworkExtras)
    {
      this.lD.put(paramNetworkExtras.getClass(), paramNetworkExtras);
    }
    
    public void a(Date paramDate)
    {
      this.d = paramDate;
    }
    
    public void d(int paramInt)
    {
      this.lu = paramInt;
    }
    
    public void d(boolean paramBoolean)
    {
      this.lw = paramBoolean;
    }
    
    public void e(boolean paramBoolean)
    {
      if (paramBoolean) {}
      for (int i = 1;; i = 0)
      {
        this.lA = i;
        return;
      }
    }
    
    public void g(String paramString)
    {
      this.lC.add(paramString);
    }
    
    public void h(String paramString)
    {
      this.lE.add(paramString);
    }
    
    public void i(String paramString)
    {
      this.lt = paramString;
    }
    
    public void j(String paramString)
    {
      this.ly = paramString;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\aj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */