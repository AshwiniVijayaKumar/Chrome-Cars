package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;

public class cq
{
  private final Object mg = new Object();
  private int pA = 0;
  private long pB = -1L;
  private long pC = -1L;
  private int pD = 0;
  private int pE = -1;
  private final String pz;
  
  public cq(String paramString)
  {
    this.pz = paramString;
  }
  
  /* Error */
  public void aJ()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 22	com/google/android/gms/internal/cq:mg	Ljava/lang/Object;
    //   6: astore_1
    //   7: aload_1
    //   8: monitorenter
    //   9: aload_0
    //   10: aload_0
    //   11: getfield 32	com/google/android/gms/internal/cq:pD	I
    //   14: iconst_1
    //   15: iadd
    //   16: putfield 32	com/google/android/gms/internal/cq:pD	I
    //   19: aload_1
    //   20: monitorexit
    //   21: aload_0
    //   22: monitorexit
    //   23: return
    //   24: astore_2
    //   25: aload_1
    //   26: monitorexit
    //   27: aload_2
    //   28: athrow
    //   29: astore_1
    //   30: aload_0
    //   31: monitorexit
    //   32: aload_1
    //   33: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	34	0	this	cq
    //   29	4	1	localObject2	Object
    //   24	4	2	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   9	21	24	finally
    //   25	27	24	finally
    //   2	9	29	finally
    //   27	29	29	finally
  }
  
  public void aK()
  {
    synchronized (this.mg)
    {
      this.pA += 1;
      return;
    }
  }
  
  public Bundle b(String paramString, Context paramContext)
  {
    synchronized (this.mg)
    {
      Bundle localBundle = new Bundle();
      localBundle.putString("session_id", this.pz);
      localBundle.putLong("basets", this.pC);
      localBundle.putLong("currts", this.pB);
      localBundle.putString("seq_num", paramString);
      localBundle.putInt("preqs", this.pE);
      localBundle.putInt("pclick", this.pA);
      localBundle.putInt("pimp", this.pD);
      paramString = new cm(paramContext);
      localBundle.putInt("gnt", paramString.oY);
      if (paramString.oX == 1)
      {
        localBundle.putString("net", "wi");
        return localBundle;
      }
      localBundle.putString("net", "ed");
    }
  }
  
  public void b(z paramz, long paramLong)
  {
    synchronized (this.mg)
    {
      if (this.pC == -1L)
      {
        this.pC = paramLong;
        this.pB = this.pC;
        if ((paramz.extras == null) || (paramz.extras.getInt("gw", 2) != 1)) {}
      }
      else
      {
        this.pB = paramLong;
      }
    }
    this.pE += 1;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\cq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */