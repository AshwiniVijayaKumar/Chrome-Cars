package com.google.android.gms.analytics;

import android.content.Context;
import com.google.android.gms.internal.di;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

class s
  implements ag, c.b, c.c
{
  private final Context mContext;
  private final GoogleAnalytics rA;
  private final Queue<d> rB = new ConcurrentLinkedQueue();
  private volatile int rC;
  private volatile Timer rD;
  private volatile Timer rE;
  private volatile Timer rF;
  private boolean rG;
  private boolean rH;
  private boolean rI;
  private i rJ;
  private long rK = 300000L;
  private d rj;
  private final f rk;
  private boolean rm;
  private volatile long rw;
  private volatile a rx;
  private volatile b ry;
  private d rz;
  
  s(Context paramContext, f paramf)
  {
    this(paramContext, paramf, null, GoogleAnalytics.getInstance(paramContext));
  }
  
  s(Context paramContext, f paramf, d paramd, GoogleAnalytics paramGoogleAnalytics)
  {
    this.rz = paramd;
    this.mContext = paramContext;
    this.rk = paramf;
    this.rA = paramGoogleAnalytics;
    this.rJ = new i()
    {
      public long currentTimeMillis()
      {
        return System.currentTimeMillis();
      }
    };
    this.rC = 0;
    this.rx = a.rT;
  }
  
  private Timer a(Timer paramTimer)
  {
    if (paramTimer != null) {
      paramTimer.cancel();
    }
    return null;
  }
  
  private void aD()
  {
    try
    {
      if ((this.ry != null) && (this.rx == a.rO))
      {
        this.rx = a.rS;
        this.ry.disconnect();
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  private void bH()
  {
    this.rD = a(this.rD);
    this.rE = a(this.rE);
    this.rF = a(this.rF);
  }
  
  private void bJ()
  {
    for (;;)
    {
      try
      {
        if (!Thread.currentThread().equals(this.rk.getThread()))
        {
          this.rk.bs().add(new Runnable()
          {
            public void run()
            {
              s.a(s.this);
            }
          });
          return;
        }
        if (this.rG) {
          bk();
        }
        switch (3.rM[this.rx.ordinal()])
        {
        case 1: 
          if (!this.rB.isEmpty())
          {
            d locald1 = (d)this.rB.poll();
            aa.v("Sending hit to store  " + locald1);
            this.rj.a(locald1.bO(), locald1.bP(), locald1.getPath(), locald1.bQ());
            continue;
          }
          if (!this.rm) {
            continue;
          }
        }
      }
      finally {}
      bK();
      continue;
      if (!this.rB.isEmpty())
      {
        d locald2 = (d)this.rB.peek();
        aa.v("Sending hit to service   " + locald2);
        if (!this.rA.isDryRunEnabled()) {
          this.ry.a(locald2.bO(), locald2.bP(), locald2.getPath(), locald2.bQ());
        }
        for (;;)
        {
          this.rB.poll();
          break;
          aa.v("Dry run enabled. Hit not actually sent to service.");
        }
      }
      this.rw = this.rJ.currentTimeMillis();
      continue;
      aa.v("Need to reconnect");
      if (!this.rB.isEmpty()) {
        bM();
      }
    }
  }
  
  private void bK()
  {
    this.rj.bp();
    this.rm = false;
  }
  
  private void bL()
  {
    for (;;)
    {
      try
      {
        a locala1 = this.rx;
        a locala2 = a.rP;
        if (locala1 == locala2) {
          return;
        }
        bH();
        aa.v("falling back to local store");
        if (this.rz != null)
        {
          this.rj = this.rz;
          this.rx = a.rP;
          bJ();
          continue;
        }
        localr = r.bB();
      }
      finally {}
      r localr;
      localr.a(this.mContext, this.rk);
      this.rj = localr.bE();
    }
  }
  
  private void bM()
  {
    for (;;)
    {
      try
      {
        if ((!this.rI) && (this.ry != null))
        {
          a locala1 = this.rx;
          a locala2 = a.rP;
          if (locala1 != locala2) {
            try
            {
              this.rC += 1;
              a(this.rE);
              this.rx = a.rN;
              this.rE = new Timer("Failed Connect");
              this.rE.schedule(new c(null), 3000L);
              aa.v("connecting to Analytics service");
              this.ry.connect();
              return;
            }
            catch (SecurityException localSecurityException)
            {
              aa.w("security exception on connectToService");
              bL();
              continue;
            }
          }
        }
        aa.w("client not initialized.");
      }
      finally {}
      bL();
    }
  }
  
  private void bN()
  {
    this.rD = a(this.rD);
    this.rD = new Timer("Service Reconnect");
    this.rD.schedule(new e(null), 5000L);
  }
  
  /* Error */
  public void a(int paramInt, android.content.Intent paramIntent)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getstatic 340	com/google/android/gms/analytics/s$a:rR	Lcom/google/android/gms/analytics/s$a;
    //   6: putfield 107	com/google/android/gms/analytics/s:rx	Lcom/google/android/gms/analytics/s$a;
    //   9: aload_0
    //   10: getfield 102	com/google/android/gms/analytics/s:rC	I
    //   13: iconst_2
    //   14: if_icmpge +39 -> 53
    //   17: new 190	java/lang/StringBuilder
    //   20: dup
    //   21: invokespecial 191	java/lang/StringBuilder:<init>	()V
    //   24: ldc_w 342
    //   27: invokevirtual 197	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   30: iload_1
    //   31: invokevirtual 345	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   34: ldc_w 347
    //   37: invokevirtual 197	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   40: invokevirtual 204	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   43: invokestatic 315	com/google/android/gms/analytics/aa:w	(Ljava/lang/String;)V
    //   46: aload_0
    //   47: invokespecial 349	com/google/android/gms/analytics/s:bN	()V
    //   50: aload_0
    //   51: monitorexit
    //   52: return
    //   53: new 190	java/lang/StringBuilder
    //   56: dup
    //   57: invokespecial 191	java/lang/StringBuilder:<init>	()V
    //   60: ldc_w 342
    //   63: invokevirtual 197	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   66: iload_1
    //   67: invokevirtual 345	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   70: ldc_w 351
    //   73: invokevirtual 197	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   76: invokevirtual 204	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   79: invokestatic 315	com/google/android/gms/analytics/aa:w	(Ljava/lang/String;)V
    //   82: aload_0
    //   83: invokespecial 317	com/google/android/gms/analytics/s:bL	()V
    //   86: goto -36 -> 50
    //   89: astore_2
    //   90: aload_0
    //   91: monitorexit
    //   92: aload_2
    //   93: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	94	0	this	s
    //   0	94	1	paramInt	int
    //   0	94	2	paramIntent	android.content.Intent
    // Exception table:
    //   from	to	target	type
    //   2	50	89	finally
    //   53	86	89	finally
  }
  
  public void b(Map<String, String> paramMap, long paramLong, String paramString, List<di> paramList)
  {
    aa.v("putHit called");
    this.rB.add(new d(paramMap, paramLong, paramString, paramList));
    bJ();
  }
  
  public void bI()
  {
    if (this.ry != null) {
      return;
    }
    this.ry = new c(this.mContext, this, this);
    bM();
  }
  
  public void bk()
  {
    aa.v("clearHits called");
    this.rB.clear();
    switch (3.rM[this.rx.ordinal()])
    {
    default: 
      this.rG = true;
      return;
    case 1: 
      this.rj.i(0L);
      this.rG = false;
      return;
    }
    this.ry.bk();
    this.rG = false;
  }
  
  public void bp()
  {
    switch (3.rM[this.rx.ordinal()])
    {
    default: 
      this.rm = true;
    case 2: 
      return;
    }
    bK();
  }
  
  public void br()
  {
    for (;;)
    {
      try
      {
        boolean bool = this.rI;
        if (bool) {
          return;
        }
        aa.v("setForceLocalDispatch called.");
        this.rI = true;
        switch (3.rM[this.rx.ordinal()])
        {
        case 1: 
        case 4: 
        case 5: 
        case 6: 
        case 2: 
          aD();
          break;
        case 3: 
          this.rH = true;
        }
      }
      finally {}
    }
  }
  
  /* Error */
  public void onConnected()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_0
    //   4: aload_0
    //   5: getfield 138	com/google/android/gms/analytics/s:rE	Ljava/util/Timer;
    //   8: invokespecial 136	com/google/android/gms/analytics/s:a	(Ljava/util/Timer;)Ljava/util/Timer;
    //   11: putfield 138	com/google/android/gms/analytics/s:rE	Ljava/util/Timer;
    //   14: aload_0
    //   15: iconst_0
    //   16: putfield 102	com/google/android/gms/analytics/s:rC	I
    //   19: ldc_w 381
    //   22: invokestatic 210	com/google/android/gms/analytics/aa:v	(Ljava/lang/String;)V
    //   25: aload_0
    //   26: getstatic 122	com/google/android/gms/analytics/s$a:rO	Lcom/google/android/gms/analytics/s$a;
    //   29: putfield 107	com/google/android/gms/analytics/s:rx	Lcom/google/android/gms/analytics/s$a;
    //   32: aload_0
    //   33: getfield 378	com/google/android/gms/analytics/s:rH	Z
    //   36: ifeq +15 -> 51
    //   39: aload_0
    //   40: invokespecial 334	com/google/android/gms/analytics/s:aD	()V
    //   43: aload_0
    //   44: iconst_0
    //   45: putfield 378	com/google/android/gms/analytics/s:rH	Z
    //   48: aload_0
    //   49: monitorexit
    //   50: return
    //   51: aload_0
    //   52: invokespecial 116	com/google/android/gms/analytics/s:bJ	()V
    //   55: aload_0
    //   56: aload_0
    //   57: aload_0
    //   58: getfield 140	com/google/android/gms/analytics/s:rF	Ljava/util/Timer;
    //   61: invokespecial 136	com/google/android/gms/analytics/s:a	(Ljava/util/Timer;)Ljava/util/Timer;
    //   64: putfield 140	com/google/android/gms/analytics/s:rF	Ljava/util/Timer;
    //   67: aload_0
    //   68: new 110	java/util/Timer
    //   71: dup
    //   72: ldc_w 383
    //   75: invokespecial 296	java/util/Timer:<init>	(Ljava/lang/String;)V
    //   78: putfield 140	com/google/android/gms/analytics/s:rF	Ljava/util/Timer;
    //   81: aload_0
    //   82: getfield 140	com/google/android/gms/analytics/s:rF	Ljava/util/Timer;
    //   85: new 21	com/google/android/gms/analytics/s$b
    //   88: dup
    //   89: aload_0
    //   90: aconst_null
    //   91: invokespecial 384	com/google/android/gms/analytics/s$b:<init>	(Lcom/google/android/gms/analytics/s;Lcom/google/android/gms/analytics/s$1;)V
    //   94: aload_0
    //   95: getfield 87	com/google/android/gms/analytics/s:rK	J
    //   98: invokevirtual 305	java/util/Timer:schedule	(Ljava/util/TimerTask;J)V
    //   101: goto -53 -> 48
    //   104: astore_1
    //   105: aload_0
    //   106: monitorexit
    //   107: aload_1
    //   108: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	109	0	this	s
    //   104	4	1	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	48	104	finally
    //   51	101	104	finally
  }
  
  public void onDisconnected()
  {
    for (;;)
    {
      try
      {
        if (this.rx == a.rS)
        {
          aa.v("Disconnected from service");
          bH();
          this.rx = a.rT;
          return;
        }
        aa.v("Unexpected disconnect.");
        this.rx = a.rR;
        if (this.rC < 2) {
          bN();
        } else {
          bL();
        }
      }
      finally {}
    }
  }
  
  private static enum a
  {
    private a() {}
  }
  
  private class b
    extends TimerTask
  {
    private b() {}
    
    public void run()
    {
      if ((s.b(s.this) == s.a.rO) && (s.e(s.this).isEmpty()) && (s.f(s.this) + s.g(s.this) < s.h(s.this).currentTimeMillis()))
      {
        aa.v("Disconnecting due to inactivity");
        s.i(s.this);
        return;
      }
      s.j(s.this).schedule(new b(s.this), s.g(s.this));
    }
  }
  
  private class c
    extends TimerTask
  {
    private c() {}
    
    public void run()
    {
      if (s.b(s.this) == s.a.rN) {
        s.c(s.this);
      }
    }
  }
  
  private static class d
  {
    private final Map<String, String> rV;
    private final long rW;
    private final String rX;
    private final List<di> rY;
    
    public d(Map<String, String> paramMap, long paramLong, String paramString, List<di> paramList)
    {
      this.rV = paramMap;
      this.rW = paramLong;
      this.rX = paramString;
      this.rY = paramList;
    }
    
    public Map<String, String> bO()
    {
      return this.rV;
    }
    
    public long bP()
    {
      return this.rW;
    }
    
    public List<di> bQ()
    {
      return this.rY;
    }
    
    public String getPath()
    {
      return this.rX;
    }
    
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("PATH: ");
      localStringBuilder.append(this.rX);
      if (this.rV != null)
      {
        localStringBuilder.append("  PARAMS: ");
        Iterator localIterator = this.rV.entrySet().iterator();
        while (localIterator.hasNext())
        {
          Map.Entry localEntry = (Map.Entry)localIterator.next();
          localStringBuilder.append((String)localEntry.getKey());
          localStringBuilder.append("=");
          localStringBuilder.append((String)localEntry.getValue());
          localStringBuilder.append(",  ");
        }
      }
      return localStringBuilder.toString();
    }
  }
  
  private class e
    extends TimerTask
  {
    private e() {}
    
    public void run()
    {
      s.d(s.this);
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\analytics\s.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */