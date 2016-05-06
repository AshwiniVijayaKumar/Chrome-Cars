package com.google.android.gms.internal;

import android.os.SystemClock;
import org.json.JSONObject;

public final class dy
{
  private static final du xE = new du("RequestTracker");
  public static final Object yD = new Object();
  private long yA;
  private long yB;
  private dx yC;
  private long yz;
  
  public dy(long paramLong)
  {
    this.yz = paramLong;
    this.yA = -1L;
    this.yB = 0L;
  }
  
  private void dk()
  {
    this.yA = -1L;
    this.yC = null;
    this.yB = 0L;
  }
  
  public void a(long paramLong, dx paramdx)
  {
    synchronized (yD)
    {
      dx localdx = this.yC;
      long l = this.yA;
      this.yA = paramLong;
      this.yC = paramdx;
      this.yB = SystemClock.elapsedRealtime();
      if (localdx != null) {
        localdx.k(l);
      }
      return;
    }
  }
  
  public boolean b(long paramLong, int paramInt, JSONObject paramJSONObject)
  {
    boolean bool = true;
    dx localdx = null;
    for (;;)
    {
      synchronized (yD)
      {
        if ((this.yA != -1L) && (this.yA == paramLong))
        {
          xE.b("request %d completed", new Object[] { Long.valueOf(this.yA) });
          localdx = this.yC;
          dk();
          if (localdx != null) {
            localdx.a(paramLong, paramInt, paramJSONObject);
          }
          return bool;
        }
      }
      bool = false;
    }
  }
  
  public boolean c(long paramLong, int paramInt)
  {
    return b(paramLong, paramInt, null);
  }
  
  public void clear()
  {
    synchronized (yD)
    {
      if (this.yA != -1L) {
        dk();
      }
      return;
    }
  }
  
  public boolean d(long paramLong, int paramInt)
  {
    boolean bool = true;
    long l = 0L;
    for (;;)
    {
      synchronized (yD)
      {
        if ((this.yA != -1L) && (paramLong - this.yB >= this.yz))
        {
          xE.b("request %d timed out", new Object[] { Long.valueOf(this.yA) });
          paramLong = this.yA;
          dx localdx = this.yC;
          dk();
          if (localdx != null) {
            localdx.a(paramLong, paramInt, null);
          }
          return bool;
        }
      }
      bool = false;
      Object localObject2 = null;
      paramLong = l;
    }
  }
  
  public boolean dl()
  {
    for (;;)
    {
      synchronized (yD)
      {
        if (this.yA != -1L)
        {
          bool = true;
          return bool;
        }
      }
      boolean bool = false;
    }
  }
  
  public boolean m(long paramLong)
  {
    for (;;)
    {
      synchronized (yD)
      {
        if ((this.yA != -1L) && (this.yA == paramLong))
        {
          bool = true;
          return bool;
        }
      }
      boolean bool = false;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\dy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */