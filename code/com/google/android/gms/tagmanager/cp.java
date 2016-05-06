package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.c.j;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

class cp
  implements o.e
{
  private final String TM;
  private String Ui;
  private bg<c.j> Wi;
  private r Wj;
  private final ScheduledExecutorService Wl;
  private final a Wm;
  private ScheduledFuture<?> Wn;
  private boolean mClosed;
  private final Context mContext;
  
  public cp(Context paramContext, String paramString, r paramr)
  {
    this(paramContext, paramString, paramr, null, null);
  }
  
  cp(Context paramContext, String paramString, r paramr, b paramb, a parama)
  {
    this.Wj = paramr;
    this.mContext = paramContext;
    this.TM = paramString;
    paramContext = paramb;
    if (paramb == null) {
      paramContext = new b()
      {
        public ScheduledExecutorService jB()
        {
          return Executors.newSingleThreadScheduledExecutor();
        }
      };
    }
    this.Wl = paramContext.jB();
    if (parama == null)
    {
      this.Wm = new a()
      {
        public co a(r paramAnonymousr)
        {
          return new co(cp.a(cp.this), cp.b(cp.this), paramAnonymousr);
        }
      };
      return;
    }
    this.Wm = parama;
  }
  
  private co bv(String paramString)
  {
    co localco = this.Wm.a(this.Wj);
    localco.a(this.Wi);
    localco.bf(this.Ui);
    localco.bu(paramString);
    return localco;
  }
  
  private void jA()
  {
    try
    {
      if (this.mClosed) {
        throw new IllegalStateException("called method after closed");
      }
    }
    finally {}
  }
  
  public void a(bg<c.j> parambg)
  {
    try
    {
      jA();
      this.Wi = parambg;
      return;
    }
    finally
    {
      parambg = finally;
      throw parambg;
    }
  }
  
  public void bf(String paramString)
  {
    try
    {
      jA();
      this.Ui = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void d(long paramLong, String paramString)
  {
    try
    {
      bh.v("loadAfterDelay: containerId=" + this.TM + " delay=" + paramLong);
      jA();
      if (this.Wi == null) {
        throw new IllegalStateException("callback must be set before loadAfterDelay() is called.");
      }
    }
    finally {}
    if (this.Wn != null) {
      this.Wn.cancel(false);
    }
    this.Wn = this.Wl.schedule(bv(paramString), paramLong, TimeUnit.MILLISECONDS);
  }
  
  public void release()
  {
    try
    {
      jA();
      if (this.Wn != null) {
        this.Wn.cancel(false);
      }
      this.Wl.shutdown();
      this.mClosed = true;
      return;
    }
    finally {}
  }
  
  static abstract interface a
  {
    public abstract co a(r paramr);
  }
  
  static abstract interface b
  {
    public abstract ScheduledExecutorService jB();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\tagmanager\cp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */