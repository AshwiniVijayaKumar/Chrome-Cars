package com.google.android.gms.internal;

import android.content.Context;
import android.os.Handler;
import android.os.RemoteException;
import android.os.SystemClock;
import com.google.android.gms.dynamic.c;

public final class bb
  implements bc.a
{
  private final bf kH;
  private final z kX;
  private final String mC;
  private final Context mContext;
  private final long mD;
  private final ax mE;
  private final ab mF;
  private final db mG;
  private bg mH;
  private int mI = -2;
  private final Object mg = new Object();
  
  public bb(Context paramContext, String paramString, bf parambf, ay paramay, ax paramax, z paramz, ab paramab, db paramdb)
  {
    this.mContext = paramContext;
    this.mC = paramString;
    this.kH = parambf;
    if (paramay.ms != -1L) {}
    for (long l = paramay.ms;; l = 10000L)
    {
      this.mD = l;
      this.mE = paramax;
      this.kX = paramz;
      this.mF = paramab;
      this.mG = paramdb;
      return;
    }
  }
  
  private void a(long paramLong1, long paramLong2, long paramLong3, long paramLong4)
  {
    for (;;)
    {
      if (this.mI != -2) {
        return;
      }
      b(paramLong1, paramLong2, paramLong3, paramLong4);
    }
  }
  
  private void a(ba paramba)
  {
    try
    {
      if (this.mG.pW < 4100000)
      {
        if (this.mF.lo)
        {
          this.mH.a(c.h(this.mContext), this.kX, this.mE.mq, paramba);
          return;
        }
        this.mH.a(c.h(this.mContext), this.mF, this.kX, this.mE.mq, paramba);
        return;
      }
    }
    catch (RemoteException paramba)
    {
      da.b("Could not request ad from mediation adapter.", paramba);
      f(5);
      return;
    }
    if (this.mF.lo)
    {
      this.mH.a(c.h(this.mContext), this.kX, this.mE.mq, this.mE.adJson, paramba);
      return;
    }
    this.mH.a(c.h(this.mContext), this.mF, this.kX, this.mE.mq, this.mE.adJson, paramba);
  }
  
  private bg ao()
  {
    da.u("Instantiating mediation adapter: " + this.mC);
    try
    {
      bg localbg = this.kH.m(this.mC);
      return localbg;
    }
    catch (RemoteException localRemoteException)
    {
      da.a("Could not instantiate mediation adapter: " + this.mC, localRemoteException);
    }
    return null;
  }
  
  private void b(long paramLong1, long paramLong2, long paramLong3, long paramLong4)
  {
    long l = SystemClock.elapsedRealtime();
    paramLong1 = paramLong2 - (l - paramLong1);
    paramLong2 = paramLong4 - (l - paramLong3);
    if ((paramLong1 <= 0L) || (paramLong2 <= 0L))
    {
      da.u("Timed out waiting for adapter.");
      this.mI = 3;
      return;
    }
    try
    {
      this.mg.wait(Math.min(paramLong1, paramLong2));
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      this.mI = -1;
    }
  }
  
  public bc b(long paramLong1, long paramLong2)
  {
    synchronized (this.mg)
    {
      long l = SystemClock.elapsedRealtime();
      final Object localObject2 = new ba();
      cz.pT.post(new Runnable()
      {
        public void run()
        {
          synchronized (bb.a(bb.this))
          {
            if (bb.b(bb.this) != -2) {
              return;
            }
            bb.a(bb.this, bb.c(bb.this));
            if (bb.d(bb.this) == null)
            {
              bb.this.f(4);
              return;
            }
          }
          localObject2.a(bb.this);
          bb.a(bb.this, localObject2);
        }
      });
      a(l, this.mD, paramLong1, paramLong2);
      localObject2 = new bc(this.mE, this.mH, this.mC, (ba)localObject2, this.mI);
      return (bc)localObject2;
    }
  }
  
  public void cancel()
  {
    synchronized (this.mg)
    {
      try
      {
        if (this.mH != null) {
          this.mH.destroy();
        }
        this.mI = -1;
        this.mg.notify();
        return;
      }
      catch (RemoteException localRemoteException)
      {
        for (;;)
        {
          da.b("Could not destroy mediation adapter.", localRemoteException);
        }
      }
    }
  }
  
  public void f(int paramInt)
  {
    synchronized (this.mg)
    {
      this.mI = paramInt;
      this.mg.notify();
      return;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\bb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */