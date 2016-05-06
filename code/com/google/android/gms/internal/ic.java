package com.google.android.gms.internal;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ic
{
  private static int OB = 10000;
  private static int OC = 1000;
  private final hl<hg> Lk;
  private final String OD;
  private final BlockingQueue<hx.a> OE;
  private int OF;
  private final int Ou;
  private final Object mg = new Object();
  
  public ic(hl<hg> paramhl, String paramString, int paramInt)
  {
    this.Lk = paramhl;
    this.OD = paramString;
    this.Ou = paramInt;
    this.OE = new LinkedBlockingQueue(OB);
    this.OF = OC;
  }
  
  public void a(hx.a.a parama)
  {
    parama.aK(this.OD);
    parama.bv(this.Ou);
    this.OE.offer(parama.gJ());
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\ic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */