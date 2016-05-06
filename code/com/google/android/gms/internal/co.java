package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.SystemClock;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class co
{
  private final Object mg = new Object();
  private boolean oI = false;
  private final LinkedList<a> pj;
  private final String pk;
  private final String pl;
  private long pm = -1L;
  private long pn = -1L;
  private long po = -1L;
  private long pp = 0L;
  private long pq = -1L;
  private long pr = -1L;
  
  public co(String paramString1, String paramString2)
  {
    this.pk = paramString1;
    this.pl = paramString2;
    this.pj = new LinkedList();
  }
  
  public void aJ()
  {
    synchronized (this.mg)
    {
      if ((this.pr != -1L) && (this.pn != -1L))
      {
        this.pn = SystemClock.elapsedRealtime();
        cp.aQ().aJ();
        cp.a(this);
      }
      return;
    }
  }
  
  public void aK()
  {
    synchronized (this.mg)
    {
      if (this.pr != -1L)
      {
        a locala = new a();
        locala.aO();
        this.pj.add(locala);
        this.pp += 1L;
        cp.aQ().aK();
        cp.a(this);
      }
      return;
    }
  }
  
  public void aL()
  {
    synchronized (this.mg)
    {
      if ((this.pr != -1L) && (!this.pj.isEmpty()))
      {
        a locala = (a)this.pj.getLast();
        if (locala.aM() == -1L)
        {
          locala.aN();
          cp.a(this);
        }
      }
      return;
    }
  }
  
  public void f(z paramz)
  {
    synchronized (this.mg)
    {
      this.pq = SystemClock.elapsedRealtime();
      cp.aQ().b(paramz, this.pq);
      return;
    }
  }
  
  public void g(long paramLong)
  {
    synchronized (this.mg)
    {
      this.pr = paramLong;
      if (this.pr != -1L) {
        cp.a(this);
      }
      return;
    }
  }
  
  public void h(long paramLong)
  {
    synchronized (this.mg)
    {
      if (this.pr != -1L)
      {
        this.pm = paramLong;
        cp.a(this);
      }
      return;
    }
  }
  
  public void k(boolean paramBoolean)
  {
    synchronized (this.mg)
    {
      if (this.pr != -1L)
      {
        this.po = SystemClock.elapsedRealtime();
        if (!paramBoolean)
        {
          this.pn = this.po;
          cp.a(this);
        }
      }
      return;
    }
  }
  
  public void l(boolean paramBoolean)
  {
    synchronized (this.mg)
    {
      if (this.pr != -1L)
      {
        this.oI = paramBoolean;
        cp.a(this);
      }
      return;
    }
  }
  
  public Bundle toBundle()
  {
    ArrayList localArrayList;
    synchronized (this.mg)
    {
      Bundle localBundle1 = new Bundle();
      localBundle1.putString("seqnum", this.pk);
      localBundle1.putString("slotid", this.pl);
      localBundle1.putBoolean("ismediation", this.oI);
      localBundle1.putLong("treq", this.pq);
      localBundle1.putLong("tresponse", this.pr);
      localBundle1.putLong("timp", this.pn);
      localBundle1.putLong("tload", this.po);
      localBundle1.putLong("pcc", this.pp);
      localBundle1.putLong("tfetch", this.pm);
      localArrayList = new ArrayList();
      Iterator localIterator = this.pj.iterator();
      if (localIterator.hasNext()) {
        localArrayList.add(((a)localIterator.next()).toBundle());
      }
    }
    localBundle2.putParcelableArrayList("tclick", localArrayList);
    return localBundle2;
  }
  
  private static final class a
  {
    private long ps = -1L;
    private long pt = -1L;
    
    public long aM()
    {
      return this.pt;
    }
    
    public void aN()
    {
      this.pt = SystemClock.elapsedRealtime();
    }
    
    public void aO()
    {
      this.ps = SystemClock.elapsedRealtime();
    }
    
    public Bundle toBundle()
    {
      Bundle localBundle = new Bundle();
      localBundle.putLong("topen", this.ps);
      localBundle.putLong("tclose", this.pt);
      return localBundle;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\co.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */