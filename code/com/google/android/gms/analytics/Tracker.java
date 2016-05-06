package com.google.android.gms.analytics;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.internal.er;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class Tracker
{
  private final TrackerHandler up;
  private final Map<String, String> uq = new HashMap();
  private ad ur;
  private final h us;
  private final ae ut;
  private final g uu;
  private boolean uv;
  private a uw;
  private aj ux;
  
  Tracker(String paramString, TrackerHandler paramTrackerHandler)
  {
    this(paramString, paramTrackerHandler, h.bu(), ae.cs(), g.bt(), new z("tracking"));
  }
  
  Tracker(String paramString, TrackerHandler paramTrackerHandler, h paramh, ae paramae, g paramg, ad paramad)
  {
    this.up = paramTrackerHandler;
    if (paramString != null) {
      this.uq.put("&tid", paramString);
    }
    this.uq.put("useSecure", "1");
    this.us = paramh;
    this.ut = paramae;
    this.uu = paramg;
    this.ur = paramad;
    this.uw = new a();
  }
  
  void a(Context paramContext, aj paramaj)
  {
    aa.v("Loading Tracker config values.");
    this.ux = paramaj;
    if (this.ux.cB())
    {
      paramaj = this.ux.cC();
      set("&tid", paramaj);
      aa.v("[Tracker] trackingId loaded: " + paramaj);
    }
    if (this.ux.cD())
    {
      paramaj = Double.toString(this.ux.cE());
      set("&sf", paramaj);
      aa.v("[Tracker] sample frequency loaded: " + paramaj);
    }
    if (this.ux.cF())
    {
      setSessionTimeout(this.ux.getSessionTimeout());
      aa.v("[Tracker] session timeout loaded: " + cu());
    }
    if (this.ux.cG())
    {
      enableAutoActivityTracking(this.ux.cH());
      aa.v("[Tracker] auto activity tracking loaded: " + cv());
    }
    if (this.ux.cI())
    {
      if (this.ux.cJ())
      {
        set("&aip", "1");
        aa.v("[Tracker] anonymize ip loaded: true");
      }
      aa.v("[Tracker] anonymize ip loaded: false");
    }
    this.uv = this.ux.cK();
    if (this.ux.cK())
    {
      Thread.setDefaultUncaughtExceptionHandler(new ExceptionReporter(this, Thread.getDefaultUncaughtExceptionHandler(), paramContext));
      aa.v("[Tracker] report uncaught exceptions loaded: " + this.uv);
    }
  }
  
  long cu()
  {
    return this.uw.cu();
  }
  
  boolean cv()
  {
    return this.uw.cv();
  }
  
  public void enableAdvertisingIdCollection(boolean paramBoolean)
  {
    if (!paramBoolean)
    {
      this.uq.put("&ate", null);
      this.uq.put("&adid", null);
    }
    do
    {
      return;
      if (this.uq.containsKey("&ate")) {
        this.uq.remove("&ate");
      }
    } while (!this.uq.containsKey("&adid"));
    this.uq.remove("&adid");
  }
  
  public void enableAutoActivityTracking(boolean paramBoolean)
  {
    this.uw.enableAutoActivityTracking(paramBoolean);
  }
  
  public String get(String paramString)
  {
    u.bR().a(u.a.su);
    if (TextUtils.isEmpty(paramString)) {}
    do
    {
      return null;
      if (this.uq.containsKey(paramString)) {
        return (String)this.uq.get(paramString);
      }
      if (paramString.equals("&ul")) {
        return ak.a(Locale.getDefault());
      }
      if ((this.us != null) && (this.us.x(paramString))) {
        return this.us.getValue(paramString);
      }
      if ((this.ut != null) && (this.ut.x(paramString))) {
        return this.ut.getValue(paramString);
      }
    } while ((this.uu == null) || (!this.uu.x(paramString)));
    return this.uu.getValue(paramString);
  }
  
  public void send(Map<String, String> paramMap)
  {
    u.bR().a(u.a.sw);
    HashMap localHashMap = new HashMap();
    localHashMap.putAll(this.uq);
    if (paramMap != null) {
      localHashMap.putAll(paramMap);
    }
    if (TextUtils.isEmpty((CharSequence)localHashMap.get("&tid"))) {
      aa.w(String.format("Missing tracking id (%s) parameter.", new Object[] { "&tid" }));
    }
    String str = (String)localHashMap.get("&t");
    paramMap = str;
    if (TextUtils.isEmpty(str))
    {
      aa.w(String.format("Missing hit type (%s) parameter.", new Object[] { "&t" }));
      paramMap = "";
    }
    if (this.uw.cw()) {
      localHashMap.put("&sc", "start");
    }
    if ((!paramMap.equals("transaction")) && (!paramMap.equals("item")) && (!this.ur.cl()))
    {
      aa.w("Too many hits sent too quickly, rate limiting invoked.");
      return;
    }
    this.up.n(localHashMap);
  }
  
  public void set(String paramString1, String paramString2)
  {
    er.b(paramString1, "Key should be non-null");
    u.bR().a(u.a.sv);
    this.uq.put(paramString1, paramString2);
  }
  
  public void setAnonymizeIp(boolean paramBoolean)
  {
    set("&aip", ak.s(paramBoolean));
  }
  
  public void setAppId(String paramString)
  {
    set("&aid", paramString);
  }
  
  public void setAppInstallerId(String paramString)
  {
    set("&aiid", paramString);
  }
  
  public void setAppName(String paramString)
  {
    set("&an", paramString);
  }
  
  public void setAppVersion(String paramString)
  {
    set("&av", paramString);
  }
  
  public void setClientId(String paramString)
  {
    set("&cid", paramString);
  }
  
  public void setEncoding(String paramString)
  {
    set("&de", paramString);
  }
  
  public void setHostname(String paramString)
  {
    set("&dh", paramString);
  }
  
  public void setLanguage(String paramString)
  {
    set("&ul", paramString);
  }
  
  public void setLocation(String paramString)
  {
    set("&dl", paramString);
  }
  
  public void setPage(String paramString)
  {
    set("&dp", paramString);
  }
  
  public void setReferrer(String paramString)
  {
    set("&dr", paramString);
  }
  
  public void setSampleRate(double paramDouble)
  {
    set("&sf", Double.toHexString(paramDouble));
  }
  
  public void setScreenColors(String paramString)
  {
    set("&sd", paramString);
  }
  
  public void setScreenName(String paramString)
  {
    set("&cd", paramString);
  }
  
  public void setScreenResolution(int paramInt1, int paramInt2)
  {
    if ((paramInt1 < 0) && (paramInt2 < 0))
    {
      aa.w("Invalid width or height. The values should be non-negative.");
      return;
    }
    set("&sr", paramInt1 + "x" + paramInt2);
  }
  
  public void setSessionTimeout(long paramLong)
  {
    this.uw.setSessionTimeout(1000L * paramLong);
  }
  
  public void setTitle(String paramString)
  {
    set("&dt", paramString);
  }
  
  public void setUseSecure(boolean paramBoolean)
  {
    set("useSecure", ak.s(paramBoolean));
  }
  
  public void setViewportSize(String paramString)
  {
    set("&vp", paramString);
  }
  
  private class a
    implements GoogleAnalytics.a
  {
    private i rJ = new i()
    {
      public long currentTimeMillis()
      {
        return System.currentTimeMillis();
      }
    };
    private boolean uA = false;
    private boolean uB = false;
    private int uC = 0;
    private long uD = -1L;
    private boolean uE = false;
    private long uF;
    private Timer uy;
    private TimerTask uz;
    
    public a() {}
    
    private void cx()
    {
      GoogleAnalytics localGoogleAnalytics = GoogleAnalytics.cf();
      if (localGoogleAnalytics == null)
      {
        aa.t("GoogleAnalytics isn't initialized for the Tracker!");
        return;
      }
      if ((this.uD >= 0L) || (this.uB))
      {
        localGoogleAnalytics.a(Tracker.b(Tracker.this));
        return;
      }
      localGoogleAnalytics.b(Tracker.b(Tracker.this));
    }
    
    private void cy()
    {
      try
      {
        if (this.uy != null)
        {
          this.uy.cancel();
          this.uy = null;
        }
        return;
      }
      finally
      {
        localObject = finally;
        throw ((Throwable)localObject);
      }
    }
    
    public long cu()
    {
      return this.uD;
    }
    
    public boolean cv()
    {
      return this.uB;
    }
    
    public boolean cw()
    {
      boolean bool = this.uE;
      this.uE = false;
      return bool;
    }
    
    boolean cz()
    {
      return (this.uD == 0L) || ((this.uD > 0L) && (this.rJ.currentTimeMillis() > this.uF + this.uD));
    }
    
    public void enableAutoActivityTracking(boolean paramBoolean)
    {
      this.uB = paramBoolean;
      cx();
    }
    
    public void f(Activity paramActivity)
    {
      u.bR().a(u.a.tt);
      cy();
      if ((!this.uA) && (this.uC == 0) && (cz())) {
        this.uE = true;
      }
      this.uA = true;
      this.uC += 1;
      HashMap localHashMap;
      Tracker localTracker;
      if (this.uB)
      {
        localHashMap = new HashMap();
        localHashMap.put("&t", "appview");
        u.bR().r(true);
        localTracker = Tracker.this;
        if (Tracker.c(Tracker.this) == null) {
          break label137;
        }
      }
      label137:
      for (paramActivity = Tracker.c(Tracker.this).h(paramActivity);; paramActivity = paramActivity.getClass().getCanonicalName())
      {
        localTracker.set("&cd", paramActivity);
        Tracker.this.send(localHashMap);
        u.bR().r(false);
        return;
      }
    }
    
    public void g(Activity paramActivity)
    {
      u.bR().a(u.a.tu);
      this.uC -= 1;
      this.uC = Math.max(0, this.uC);
      this.uF = this.rJ.currentTimeMillis();
      if (this.uC == 0)
      {
        cy();
        this.uz = new a(null);
        this.uy = new Timer("waitForActivityStart");
        this.uy.schedule(this.uz, 1000L);
      }
    }
    
    public void setSessionTimeout(long paramLong)
    {
      this.uD = paramLong;
      cx();
    }
    
    private class a
      extends TimerTask
    {
      private a() {}
      
      public void run()
      {
        Tracker.a.a(Tracker.a.this, false);
      }
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\analytics\Tracker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */