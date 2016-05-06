package com.google.android.gms.internal;

import android.os.Handler;
import java.lang.ref.WeakReference;

public final class w
{
  private final Runnable kW;
  private z kX;
  private boolean kY = false;
  
  public w(final v paramv)
  {
    this.kW = new Runnable()
    {
      private final WeakReference<v> kZ = new WeakReference(paramv);
      
      public void run()
      {
        w.a(w.this, false);
        v localv = (v)this.kZ.get();
        if (localv != null) {
          localv.b(w.a(w.this));
        }
      }
    };
  }
  
  public void a(z paramz, long paramLong)
  {
    if (this.kY)
    {
      da.w("An ad refresh is already scheduled.");
      return;
    }
    da.u("Scheduling ad refresh " + paramLong + " milliseconds from now.");
    this.kX = paramz;
    this.kY = true;
    cz.pT.postDelayed(this.kW, paramLong);
  }
  
  public void cancel()
  {
    cz.pT.removeCallbacks(this.kW);
  }
  
  public void d(z paramz)
  {
    a(paramz, 60000L);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\w.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */