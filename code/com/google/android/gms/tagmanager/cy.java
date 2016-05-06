package com.google.android.gms.tagmanager;

import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;

class cy
  extends cx
{
  private static cy Xp;
  private static final Object ri = new Object();
  private Context Xf;
  private at Xg;
  private volatile ar Xh;
  private int Xi = 1800000;
  private boolean Xj = true;
  private boolean Xk = false;
  private boolean Xl = true;
  private au Xm = new au()
  {
    public void p(boolean paramAnonymousBoolean)
    {
      cy.this.a(paramAnonymousBoolean, cy.a(cy.this));
    }
  };
  private bn Xn;
  private boolean Xo = false;
  private boolean connected = true;
  private Handler handler;
  
  private void bC()
  {
    this.Xn = new bn(this);
    this.Xn.o(this.Xf);
  }
  
  private void bD()
  {
    this.handler = new Handler(this.Xf.getMainLooper(), new Handler.Callback()
    {
      public boolean handleMessage(Message paramAnonymousMessage)
      {
        if ((1 == paramAnonymousMessage.what) && (cy.bG().equals(paramAnonymousMessage.obj)))
        {
          cy.this.bp();
          if ((cy.b(cy.this) > 0) && (!cy.c(cy.this))) {
            cy.d(cy.this).sendMessageDelayed(cy.d(cy.this).obtainMessage(1, cy.bG()), cy.b(cy.this));
          }
        }
        return true;
      }
    });
    if (this.Xi > 0) {
      this.handler.sendMessageDelayed(this.handler.obtainMessage(1, ri), this.Xi);
    }
  }
  
  public static cy kh()
  {
    if (Xp == null) {
      Xp = new cy();
    }
    return Xp;
  }
  
  /* Error */
  void a(Context paramContext, ar paramar)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 77	com/google/android/gms/tagmanager/cy:Xf	Landroid/content/Context;
    //   6: astore_3
    //   7: aload_3
    //   8: ifnull +6 -> 14
    //   11: aload_0
    //   12: monitorexit
    //   13: return
    //   14: aload_0
    //   15: aload_1
    //   16: invokevirtual 123	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   19: putfield 77	com/google/android/gms/tagmanager/cy:Xf	Landroid/content/Context;
    //   22: aload_0
    //   23: getfield 125	com/google/android/gms/tagmanager/cy:Xh	Lcom/google/android/gms/tagmanager/ar;
    //   26: ifnonnull -15 -> 11
    //   29: aload_0
    //   30: aload_2
    //   31: putfield 125	com/google/android/gms/tagmanager/cy:Xh	Lcom/google/android/gms/tagmanager/ar;
    //   34: goto -23 -> 11
    //   37: astore_1
    //   38: aload_0
    //   39: monitorexit
    //   40: aload_1
    //   41: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	42	0	this	cy
    //   0	42	1	paramContext	Context
    //   0	42	2	paramar	ar
    //   6	2	3	localContext	Context
    // Exception table:
    //   from	to	target	type
    //   2	7	37	finally
    //   14	34	37	finally
  }
  
  void a(boolean paramBoolean1, boolean paramBoolean2)
  {
    for (;;)
    {
      StringBuilder localStringBuilder;
      String str1;
      try
      {
        if (this.Xo == paramBoolean1)
        {
          boolean bool = this.connected;
          if (bool == paramBoolean2) {
            return;
          }
        }
        if (((paramBoolean1) || (!paramBoolean2)) && (this.Xi > 0)) {
          this.handler.removeMessages(1, ri);
        }
        if ((!paramBoolean1) && (paramBoolean2) && (this.Xi > 0)) {
          this.handler.sendMessageDelayed(this.handler.obtainMessage(1, ri), this.Xi);
        }
        localStringBuilder = new StringBuilder().append("PowerSaveMode ");
        if (paramBoolean1) {
          break label153;
        }
        if (paramBoolean2) {
          break label146;
        }
      }
      finally {}
      bh.v(str1);
      this.Xo = paramBoolean1;
      this.connected = paramBoolean2;
      continue;
      label146:
      String str2 = "terminated.";
      continue;
      label153:
      str2 = "initiated.";
    }
  }
  
  void bF()
  {
    try
    {
      if ((!this.Xo) && (this.connected) && (this.Xi > 0))
      {
        this.handler.removeMessages(1, ri);
        this.handler.sendMessage(this.handler.obtainMessage(1, ri));
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /* Error */
  public void bp()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 52	com/google/android/gms/tagmanager/cy:Xk	Z
    //   6: ifne +16 -> 22
    //   9: ldc -95
    //   11: invokestatic 149	com/google/android/gms/tagmanager/bh:v	(Ljava/lang/String;)V
    //   14: aload_0
    //   15: iconst_1
    //   16: putfield 50	com/google/android/gms/tagmanager/cy:Xj	Z
    //   19: aload_0
    //   20: monitorexit
    //   21: return
    //   22: aload_0
    //   23: getfield 125	com/google/android/gms/tagmanager/cy:Xh	Lcom/google/android/gms/tagmanager/ar;
    //   26: new 10	com/google/android/gms/tagmanager/cy$3
    //   29: dup
    //   30: aload_0
    //   31: invokespecial 162	com/google/android/gms/tagmanager/cy$3:<init>	(Lcom/google/android/gms/tagmanager/cy;)V
    //   34: invokeinterface 167 2 0
    //   39: goto -20 -> 19
    //   42: astore_1
    //   43: aload_0
    //   44: monitorexit
    //   45: aload_1
    //   46: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	47	0	this	cy
    //   42	4	1	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	19	42	finally
    //   22	39	42	finally
  }
  
  at ki()
  {
    try
    {
      if (this.Xg != null) {
        break label50;
      }
      if (this.Xf == null) {
        throw new IllegalStateException("Cant get a store unless we have a context");
      }
    }
    finally {}
    this.Xg = new cb(this.Xm, this.Xf);
    label50:
    if (this.handler == null) {
      bD();
    }
    this.Xk = true;
    if (this.Xj)
    {
      bp();
      this.Xj = false;
    }
    if ((this.Xn == null) && (this.Xl)) {
      bC();
    }
    at localat = this.Xg;
    return localat;
  }
  
  void q(boolean paramBoolean)
  {
    try
    {
      a(this.Xo, paramBoolean);
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\tagmanager\cy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */