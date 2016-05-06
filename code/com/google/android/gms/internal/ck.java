package com.google.android.gms.internal;

import java.util.Map;

public final class ck
{
  private final Object mg = new Object();
  private int nX = -2;
  private dd ng;
  public final ar oA = new ar()
  {
    public void a(dd paramAnonymousdd, Map<String, String> paramAnonymousMap)
    {
      synchronized (ck.a(ck.this))
      {
        String str2 = (String)paramAnonymousMap.get("url");
        if (str2 == null)
        {
          da.w("URL missing in loadAdUrl GMSG.");
          return;
        }
        String str1 = str2;
        if (str2.contains("%40mediation_adapters%40"))
        {
          str1 = str2.replaceAll("%40mediation_adapters%40", cs.b(paramAnonymousdd.getContext(), (String)paramAnonymousMap.get("check_adapters"), ck.b(ck.this)));
          da.v("Ad request URL modified to " + str1);
        }
        ck.a(ck.this, str1);
        ck.a(ck.this).notify();
        return;
      }
    }
  };
  private String ox;
  private String oy;
  public final ar oz = new ar()
  {
    public void a(dd arg1, Map<String, String> paramAnonymousMap)
    {
      synchronized (ck.a(ck.this))
      {
        String str = (String)paramAnonymousMap.get("type");
        paramAnonymousMap = (String)paramAnonymousMap.get("errors");
        da.w("Invalid " + str + " request error: " + paramAnonymousMap);
        ck.a(ck.this, 1);
        ck.a(ck.this).notify();
        return;
      }
    }
  };
  
  public ck(String paramString)
  {
    this.ox = paramString;
  }
  
  public String aI()
  {
    synchronized (this.mg)
    {
      while (this.oy == null)
      {
        int i = this.nX;
        if (i == -2) {
          try
          {
            this.mg.wait();
          }
          catch (InterruptedException localInterruptedException)
          {
            da.w("Ad request service was interrupted.");
            return null;
          }
        }
      }
      String str = this.oy;
      return str;
    }
  }
  
  public void b(dd paramdd)
  {
    synchronized (this.mg)
    {
      this.ng = paramdd;
      return;
    }
  }
  
  public int getErrorCode()
  {
    synchronized (this.mg)
    {
      int i = this.nX;
      return i;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\ck.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */