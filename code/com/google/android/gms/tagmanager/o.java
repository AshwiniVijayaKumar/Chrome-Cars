package com.google.android.gms.tagmanager;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.c.f;
import com.google.android.gms.internal.c.j;
import com.google.android.gms.internal.fl;
import com.google.android.gms.internal.fn;
import com.google.android.gms.internal.jd.a;

class o
  extends ca<ContainerHolder>
{
  private final String TM;
  private long TR;
  private final TagManager TY;
  private final fl Ty;
  private final d Ub;
  private final cg Uc;
  private final int Ud;
  private f Ue;
  private volatile n Uf;
  private volatile boolean Ug;
  private c.j Uh;
  private String Ui;
  private e Uj;
  private a Uk;
  private final Context mContext;
  private final Looper zs;
  
  o(Context paramContext, TagManager paramTagManager, Looper paramLooper, String paramString, int paramInt, f paramf, e parame, fl paramfl, cg paramcg) {}
  
  public o(Context paramContext, TagManager paramTagManager, Looper paramLooper, String paramString, int paramInt, r paramr)
  {
    this(paramContext, paramTagManager, paramLooper, paramString, paramInt, new cq(paramContext, paramString), new cp(paramContext, paramString, paramr), fn.eI(), new bf(30, 900000L, 5000L, "refreshing", fn.eI()));
  }
  
  private void a(c.j paramj)
  {
    try
    {
      if (this.Ue != null)
      {
        jd.a locala = new jd.a();
        locala.Yb = this.TR;
        locala.fV = new c.f();
        locala.Yc = paramj;
        this.Ue.b(locala);
      }
      return;
    }
    finally
    {
      paramj = finally;
      throw paramj;
    }
  }
  
  private void a(c.j paramj, long paramLong, boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (;;)
    {
      try
      {
        paramBoolean = this.Ug;
        if (paramBoolean) {
          return;
        }
        if ((isReady()) && (this.Uf == null)) {}
        this.Uh = paramj;
        this.TR = paramLong;
        s(Math.max(0L, Math.min(43200000L, this.TR + 43200000L - this.Ty.currentTimeMillis())));
        paramj = new Container(this.mContext, this.TY.getDataLayer(), this.TM, paramLong, paramj);
        if (this.Uf == null)
        {
          this.Uf = new n(this.TY, this.zs, paramj, this.Ub);
          if ((!isReady()) && (this.Uk.b(paramj))) {
            a(this.Uf);
          }
        }
        else
        {
          this.Uf.a(paramj);
        }
      }
      finally {}
    }
  }
  
  private boolean iL()
  {
    ce localce = ce.ju();
    return ((localce.jv() == ce.a.VX) || (localce.jv() == ce.a.VY)) && (this.TM.equals(localce.getContainerId()));
  }
  
  /* Error */
  private void s(long paramLong)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 83	com/google/android/gms/tagmanager/o:Uj	Lcom/google/android/gms/tagmanager/o$e;
    //   6: ifnonnull +12 -> 18
    //   9: ldc_w 264
    //   12: invokestatic 269	com/google/android/gms/tagmanager/bh:w	(Ljava/lang/String;)V
    //   15: aload_0
    //   16: monitorexit
    //   17: return
    //   18: aload_0
    //   19: getfield 83	com/google/android/gms/tagmanager/o:Uj	Lcom/google/android/gms/tagmanager/o$e;
    //   22: lload_1
    //   23: aload_0
    //   24: getfield 95	com/google/android/gms/tagmanager/o:Uh	Lcom/google/android/gms/internal/c$j;
    //   27: getfield 272	com/google/android/gms/internal/c$j:fW	Ljava/lang/String;
    //   30: invokeinterface 275 4 0
    //   35: goto -20 -> 15
    //   38: astore_3
    //   39: aload_0
    //   40: monitorexit
    //   41: aload_3
    //   42: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	43	0	this	o
    //   0	43	1	paramLong	long
    //   38	4	3	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	15	38	finally
    //   18	35	38	finally
  }
  
  private void z(final boolean paramBoolean)
  {
    this.Ue.a(new b(null));
    this.Uj.a(new c(null));
    cr.c localc = this.Ue.bP(this.Ud);
    if (localc != null) {
      this.Uf = new n(this.TY, this.zs, new Container(this.mContext, this.TY.getDataLayer(), this.TM, 0L, localc), this.Ub);
    }
    this.Uk = new a()
    {
      public boolean b(Container paramAnonymousContainer)
      {
        if (paramBoolean) {
          if (paramAnonymousContainer.getLastRefreshTime() + 43200000L < o.a(o.this).currentTimeMillis()) {}
        }
        while (!paramAnonymousContainer.isDefault())
        {
          return true;
          return false;
        }
        return false;
      }
    };
    if (iL())
    {
      this.Uj.d(0L, "");
      return;
    }
    this.Ue.iN();
  }
  
  protected ContainerHolder O(Status paramStatus)
  {
    if (this.Uf != null) {
      return this.Uf;
    }
    if (paramStatus == Status.zS) {
      bh.t("timer expired: setting result to failure");
    }
    return new n(paramStatus);
  }
  
  void bc(String paramString)
  {
    try
    {
      this.Ui = paramString;
      if (this.Uj != null) {
        this.Uj.bf(paramString);
      }
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  String iF()
  {
    try
    {
      String str = this.Ui;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void iI()
  {
    Object localObject = this.Ue.bP(this.Ud);
    if (localObject != null)
    {
      localObject = new Container(this.mContext, this.TY.getDataLayer(), this.TM, 0L, (cr.c)localObject);
      a(new n(this.TY, this.zs, (Container)localObject, new n.a()
      {
        public void bc(String paramAnonymousString)
        {
          o.this.bc(paramAnonymousString);
        }
        
        public String iF()
        {
          return o.this.iF();
        }
        
        public void iH()
        {
          bh.w("Refresh ignored: container loaded as default only.");
        }
      }));
    }
    for (;;)
    {
      this.Uj = null;
      this.Ue = null;
      return;
      bh.t("Default was requested, but no default container was found");
      a(O(new Status(10, "Default was requested, but no default container was found", null)));
    }
  }
  
  public void iJ()
  {
    z(false);
  }
  
  public void iK()
  {
    z(true);
  }
  
  static abstract interface a
  {
    public abstract boolean b(Container paramContainer);
  }
  
  private class b
    implements bg<jd.a>
  {
    private b() {}
    
    public void a(jd.a parama)
    {
      c.j localj;
      if (parama.Yc != null) {
        localj = parama.Yc;
      }
      for (;;)
      {
        o.a(o.this, localj, parama.Yb, true);
        return;
        c.f localf = parama.fV;
        localj = new c.j();
        localj.fV = localf;
        localj.fU = null;
        localj.fW = localf.fr;
      }
    }
    
    public void a(bg.a parama)
    {
      if (!o.b(o.this)) {
        o.a(o.this, 0L);
      }
    }
    
    public void iM() {}
  }
  
  private class c
    implements bg<c.j>
  {
    private c() {}
    
    public void a(bg.a parama)
    {
      if (o.f(o.this) != null) {
        o.this.a(o.f(o.this));
      }
      for (;;)
      {
        o.a(o.this, 3600000L);
        return;
        o.this.a(o.this.O(Status.zS));
      }
    }
    
    public void b(c.j paramj)
    {
      synchronized (o.this)
      {
        if (paramj.fV == null)
        {
          if (o.c(o.this).fV == null)
          {
            bh.t("Current resource is null; network resource is also null");
            o.a(o.this, 3600000L);
            return;
          }
          paramj.fV = o.c(o.this).fV;
        }
        o.a(o.this, paramj, o.a(o.this).currentTimeMillis(), false);
        bh.v("setting refresh time to current time: " + o.d(o.this));
        if (!o.e(o.this)) {
          o.a(o.this, paramj);
        }
        return;
      }
    }
    
    public void iM() {}
  }
  
  private class d
    implements n.a
  {
    private d() {}
    
    public void bc(String paramString)
    {
      o.this.bc(paramString);
    }
    
    public String iF()
    {
      return o.this.iF();
    }
    
    public void iH()
    {
      if (o.g(o.this).cl()) {
        o.a(o.this, 0L);
      }
    }
  }
  
  static abstract interface e
    extends Releasable
  {
    public abstract void a(bg<c.j> parambg);
    
    public abstract void bf(String paramString);
    
    public abstract void d(long paramLong, String paramString);
  }
  
  static abstract interface f
    extends Releasable
  {
    public abstract void a(bg<jd.a> parambg);
    
    public abstract void b(jd.a parama);
    
    public abstract cr.c bP(int paramInt);
    
    public abstract void iN();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\tagmanager\o.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */