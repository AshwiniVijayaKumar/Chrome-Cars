package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;

public abstract class cb
  extends ct
{
  private final cd mf;
  private final ca.a nY;
  
  public cb(cd paramcd, ca.a parama)
  {
    this.mf = paramcd;
    this.nY = parama;
  }
  
  private static cf a(ch paramch, cd paramcd)
  {
    try
    {
      paramch = paramch.b(paramcd);
      return paramch;
    }
    catch (RemoteException paramch)
    {
      da.b("Could not fetch ad response from ad request service.", paramch);
    }
    return null;
  }
  
  /* Error */
  public final void aB()
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 44	com/google/android/gms/internal/cb:aE	()Lcom/google/android/gms/internal/ch;
    //   4: astore_1
    //   5: aload_1
    //   6: ifnonnull +27 -> 33
    //   9: new 46	com/google/android/gms/internal/cf
    //   12: dup
    //   13: iconst_0
    //   14: invokespecial 49	com/google/android/gms/internal/cf:<init>	(I)V
    //   17: astore_1
    //   18: aload_0
    //   19: invokevirtual 52	com/google/android/gms/internal/cb:aD	()V
    //   22: aload_0
    //   23: getfield 23	com/google/android/gms/internal/cb:nY	Lcom/google/android/gms/internal/ca$a;
    //   26: aload_1
    //   27: invokeinterface 57 2 0
    //   32: return
    //   33: aload_1
    //   34: aload_0
    //   35: getfield 21	com/google/android/gms/internal/cb:mf	Lcom/google/android/gms/internal/cd;
    //   38: invokestatic 59	com/google/android/gms/internal/cb:a	(Lcom/google/android/gms/internal/ch;Lcom/google/android/gms/internal/cd;)Lcom/google/android/gms/internal/cf;
    //   41: astore_2
    //   42: aload_2
    //   43: astore_1
    //   44: aload_2
    //   45: ifnonnull -27 -> 18
    //   48: new 46	com/google/android/gms/internal/cf
    //   51: dup
    //   52: iconst_0
    //   53: invokespecial 49	com/google/android/gms/internal/cf:<init>	(I)V
    //   56: astore_1
    //   57: goto -39 -> 18
    //   60: astore_1
    //   61: aload_0
    //   62: invokevirtual 52	com/google/android/gms/internal/cb:aD	()V
    //   65: aload_1
    //   66: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	67	0	this	cb
    //   4	53	1	localObject1	Object
    //   60	6	1	localObject2	Object
    //   41	4	2	localcf	cf
    // Exception table:
    //   from	to	target	type
    //   0	5	60	finally
    //   9	18	60	finally
    //   33	42	60	finally
    //   48	57	60	finally
  }
  
  public abstract void aD();
  
  public abstract ch aE();
  
  public final void onStop()
  {
    aD();
  }
  
  public static final class a
    extends cb
  {
    private final Context mContext;
    
    public a(Context paramContext, cd paramcd, ca.a parama)
    {
      super(parama);
      this.mContext = paramContext;
    }
    
    public void aD() {}
    
    public ch aE()
    {
      return ci.a(this.mContext, new av());
    }
  }
  
  public static final class b
    extends cb
    implements GooglePlayServicesClient.ConnectionCallbacks, GooglePlayServicesClient.OnConnectionFailedListener
  {
    private final Object mg = new Object();
    private final ca.a nY;
    private final cc nZ;
    
    public b(Context paramContext, cd paramcd, ca.a parama)
    {
      super(parama);
      this.nY = parama;
      this.nZ = new cc(paramContext, this, this, paramcd.kN.pW);
      this.nZ.connect();
    }
    
    public void aD()
    {
      synchronized (this.mg)
      {
        if ((this.nZ.isConnected()) || (this.nZ.isConnecting())) {
          this.nZ.disconnect();
        }
        return;
      }
    }
    
    public ch aE()
    {
      synchronized (this.mg)
      {
        try
        {
          ch localch = this.nZ.aH();
          return localch;
        }
        catch (IllegalStateException localIllegalStateException)
        {
          return null;
        }
      }
    }
    
    public void onConnected(Bundle paramBundle)
    {
      start();
    }
    
    public void onConnectionFailed(ConnectionResult paramConnectionResult)
    {
      this.nY.a(new cf(0));
    }
    
    public void onDisconnected()
    {
      da.s("Disconnected from remote ad request service.");
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\cb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */