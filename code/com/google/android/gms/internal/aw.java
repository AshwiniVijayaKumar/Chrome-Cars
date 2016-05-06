package com.google.android.gms.internal;

import android.content.Context;
import android.os.Handler;
import android.os.RemoteException;
import java.util.Iterator;
import java.util.List;

public final class aw
{
  private final bf kH;
  private final Context mContext;
  private final cd mf;
  private final Object mg = new Object();
  private final ay mh;
  private boolean mi = false;
  private bb mj;
  
  public aw(Context paramContext, cd paramcd, bf parambf, ay paramay)
  {
    this.mContext = paramContext;
    this.mf = paramcd;
    this.kH = parambf;
    this.mh = paramay;
  }
  
  public bc a(long paramLong1, long paramLong2)
  {
    da.s("Starting mediation.");
    Object localObject2 = this.mh.mr.iterator();
    while (((Iterator)localObject2).hasNext())
    {
      ax localax = (ax)((Iterator)localObject2).next();
      da.u("Trying mediation network: " + localax.mm);
      Iterator localIterator = localax.mn.iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        synchronized (this.mg)
        {
          if (this.mi)
          {
            localObject2 = new bc(-1);
            return (bc)localObject2;
          }
          this.mj = new bb(this.mContext, str, this.kH, this.mh, localax, this.mf.oc, this.mf.kQ, this.mf.kN);
          ??? = this.mj.b(paramLong1, paramLong2);
          if (((bc)???).mL == 0)
          {
            da.s("Adapter succeeded.");
            return (bc)???;
          }
        }
        if (((bc)???).mN != null) {
          cz.pT.post(new Runnable()
          {
            public void run()
            {
              try
              {
                localObject1.mN.destroy();
                return;
              }
              catch (RemoteException localRemoteException)
              {
                da.b("Could not destroy mediation adapter.", localRemoteException);
              }
            }
          });
        }
      }
    }
    return new bc(1);
  }
  
  public void cancel()
  {
    synchronized (this.mg)
    {
      this.mi = true;
      if (this.mj != null) {
        this.mj.cancel();
      }
      return;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\aw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */