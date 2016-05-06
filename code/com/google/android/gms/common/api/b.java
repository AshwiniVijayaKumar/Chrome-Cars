package com.google.android.gms.common.api;

import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.internal.ee;
import com.google.android.gms.internal.ei;
import com.google.android.gms.internal.ei.b;
import com.google.android.gms.internal.er;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

final class b
  implements GoogleApiClient
{
  private int zA;
  private int zB = 4;
  private int zC = 0;
  private boolean zD = false;
  private int zE;
  private long zF = 5000L;
  final Handler zG;
  private final Bundle zH = new Bundle();
  private final Map<Api.b<?>, Api.a> zI = new HashMap();
  private boolean zJ;
  final Set<c> zK = new HashSet();
  final GoogleApiClient.ConnectionCallbacks zL = new GoogleApiClient.ConnectionCallbacks()
  {
    public void onConnected(Bundle paramAnonymousBundle)
    {
      b.a(b.this).lock();
      try
      {
        if (b.b(b.this) == 1)
        {
          if (paramAnonymousBundle != null) {
            b.c(b.this).putAll(paramAnonymousBundle);
          }
          b.d(b.this);
        }
        return;
      }
      finally
      {
        b.a(b.this).unlock();
      }
    }
    
    public void onConnectionSuspended(int paramAnonymousInt)
    {
      b.a(b.this).lock();
      for (;;)
      {
        try
        {
          b.a(b.this, paramAnonymousInt);
          switch (paramAnonymousInt)
          {
          default: 
            return;
          }
        }
        finally
        {
          b.a(b.this).unlock();
        }
        b.this.connect();
        continue;
        boolean bool = b.e(b.this);
        if (bool)
        {
          b.a(b.this).unlock();
          return;
        }
        b.b(b.this, 2);
        b.this.zG.sendMessageDelayed(b.this.zG.obtainMessage(1), b.f(b.this));
      }
    }
  };
  private final ei.b zM = new ei.b()
  {
    public Bundle cY()
    {
      return null;
    }
    
    public boolean dC()
    {
      return b.g(b.this);
    }
    
    public boolean isConnected()
    {
      return b.this.isConnected();
    }
  };
  private final a zm = new a()
  {
    public void b(b.c paramAnonymousc)
    {
      b.a(b.this).lock();
      try
      {
        b.this.zK.remove(paramAnonymousc);
        return;
      }
      finally
      {
        b.a(b.this).unlock();
      }
    }
  };
  private final Lock zv = new ReentrantLock();
  private final Condition zw = this.zv.newCondition();
  private final ei zx = new ei(paramContext, paramLooper, this.zM);
  final Queue<c<?>> zy = new LinkedList();
  private ConnectionResult zz;
  
  public b(Context paramContext, Looper paramLooper, ee paramee, Map<Api, GoogleApiClient.ApiOptions> paramMap, Set<GoogleApiClient.ConnectionCallbacks> paramSet, final Set<GoogleApiClient.OnConnectionFailedListener> paramSet1)
  {
    this.zG = new b(paramLooper);
    paramSet = paramSet.iterator();
    Object localObject;
    while (paramSet.hasNext())
    {
      localObject = (GoogleApiClient.ConnectionCallbacks)paramSet.next();
      this.zx.registerConnectionCallbacks((GoogleApiClient.ConnectionCallbacks)localObject);
    }
    paramSet = paramSet1.iterator();
    while (paramSet.hasNext())
    {
      paramSet1 = (GoogleApiClient.OnConnectionFailedListener)paramSet.next();
      this.zx.registerConnectionFailedListener(paramSet1);
    }
    paramSet = paramMap.keySet().iterator();
    while (paramSet.hasNext())
    {
      localObject = (Api)paramSet.next();
      paramSet1 = ((Api)localObject).dp();
      localObject = (GoogleApiClient.ApiOptions)paramMap.get(localObject);
      this.zI.put(paramSet1, paramSet1.b(paramContext, paramLooper, paramee, (GoogleApiClient.ApiOptions)localObject, this.zL, new GoogleApiClient.OnConnectionFailedListener()
      {
        public void onConnectionFailed(ConnectionResult paramAnonymousConnectionResult)
        {
          b.a(b.this).lock();
          try
          {
            if ((b.h(b.this) == null) || (paramSet1.getPriority() < b.i(b.this)))
            {
              b.a(b.this, paramAnonymousConnectionResult);
              b.c(b.this, paramSet1.getPriority());
            }
            b.d(b.this);
            return;
          }
          finally
          {
            b.a(b.this).unlock();
          }
        }
      }));
    }
  }
  
  private void G(int paramInt)
  {
    this.zv.lock();
    try
    {
      if (this.zB == 3) {
        break label317;
      }
      if (paramInt != -1) {
        break label136;
      }
      if (isConnecting())
      {
        Iterator localIterator1 = this.zy.iterator();
        while (localIterator1.hasNext()) {
          if (((c)localIterator1.next()).dr() != 1) {
            localIterator1.remove();
          }
        }
      }
      this.zy.clear();
    }
    finally
    {
      this.zv.unlock();
    }
    if ((this.zz == null) && (!this.zy.isEmpty()))
    {
      this.zD = true;
      this.zv.unlock();
      return;
    }
    label136:
    boolean bool1 = isConnecting();
    boolean bool2 = isConnected();
    this.zB = 3;
    if (bool1)
    {
      if (paramInt == -1) {
        this.zz = null;
      }
      this.zw.signalAll();
    }
    Iterator localIterator2 = this.zK.iterator();
    while (localIterator2.hasNext()) {
      ((c)localIterator2.next()).du();
    }
    this.zK.clear();
    this.zJ = false;
    localIterator2 = this.zI.values().iterator();
    while (localIterator2.hasNext())
    {
      Api.a locala = (Api.a)localIterator2.next();
      if (locala.isConnected()) {
        locala.disconnect();
      }
    }
    this.zJ = true;
    this.zB = 4;
    if (bool2)
    {
      if (paramInt != -1) {
        this.zx.P(paramInt);
      }
      this.zJ = false;
    }
    label317:
    this.zv.unlock();
  }
  
  /* Error */
  private <A extends Api.a> void a(c<A> paramc)
    throws DeadObjectException
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 70	com/google/android/gms/common/api/b:zv	Ljava/util/concurrent/locks/Lock;
    //   4: invokeinterface 197 1 0
    //   9: aload_0
    //   10: invokevirtual 224	com/google/android/gms/common/api/b:isConnected	()Z
    //   13: ldc_w 258
    //   16: invokestatic 263	com/google/android/gms/internal/er:a	(ZLjava/lang/Object;)V
    //   19: aload_1
    //   20: invokeinterface 264 1 0
    //   25: ifnull +66 -> 91
    //   28: iconst_1
    //   29: istore_2
    //   30: iload_2
    //   31: ldc_w 266
    //   34: invokestatic 263	com/google/android/gms/internal/er:a	(ZLjava/lang/Object;)V
    //   37: aload_1
    //   38: instanceof 268
    //   41: ifeq +24 -> 65
    //   44: aload_0
    //   45: getfield 108	com/google/android/gms/common/api/b:zK	Ljava/util/Set;
    //   48: aload_1
    //   49: invokeinterface 272 2 0
    //   54: pop
    //   55: aload_1
    //   56: aload_0
    //   57: getfield 113	com/google/android/gms/common/api/b:zm	Lcom/google/android/gms/common/api/b$a;
    //   60: invokeinterface 275 2 0
    //   65: aload_1
    //   66: aload_0
    //   67: aload_1
    //   68: invokeinterface 264 1 0
    //   73: invokevirtual 278	com/google/android/gms/common/api/b:a	(Lcom/google/android/gms/common/api/Api$b;)Lcom/google/android/gms/common/api/Api$a;
    //   76: invokeinterface 281 2 0
    //   81: aload_0
    //   82: getfield 70	com/google/android/gms/common/api/b:zv	Ljava/util/concurrent/locks/Lock;
    //   85: invokeinterface 213 1 0
    //   90: return
    //   91: iconst_0
    //   92: istore_2
    //   93: goto -63 -> 30
    //   96: astore_1
    //   97: aload_0
    //   98: getfield 70	com/google/android/gms/common/api/b:zv	Ljava/util/concurrent/locks/Lock;
    //   101: invokeinterface 213 1 0
    //   106: aload_1
    //   107: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	108	0	this	b
    //   0	108	1	paramc	c<A>
    //   29	64	2	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   9	28	96	finally
    //   30	65	96	finally
    //   65	81	96	finally
  }
  
  /* Error */
  private boolean dA()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 70	com/google/android/gms/common/api/b:zv	Ljava/util/concurrent/locks/Lock;
    //   4: invokeinterface 197 1 0
    //   9: aload_0
    //   10: getfield 87	com/google/android/gms/common/api/b:zC	I
    //   13: istore_1
    //   14: iload_1
    //   15: ifeq +16 -> 31
    //   18: iconst_1
    //   19: istore_2
    //   20: aload_0
    //   21: getfield 70	com/google/android/gms/common/api/b:zv	Ljava/util/concurrent/locks/Lock;
    //   24: invokeinterface 213 1 0
    //   29: iload_2
    //   30: ireturn
    //   31: iconst_0
    //   32: istore_2
    //   33: goto -13 -> 20
    //   36: astore_3
    //   37: aload_0
    //   38: getfield 70	com/google/android/gms/common/api/b:zv	Ljava/util/concurrent/locks/Lock;
    //   41: invokeinterface 213 1 0
    //   46: aload_3
    //   47: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	48	0	this	b
    //   13	2	1	i	int
    //   19	14	2	bool	boolean
    //   36	11	3	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   9	14	36	finally
  }
  
  private void dB()
  {
    this.zv.lock();
    try
    {
      this.zC = 0;
      this.zG.removeMessages(1);
      return;
    }
    finally
    {
      this.zv.unlock();
    }
  }
  
  private void dy()
  {
    this.zv.lock();
    for (;;)
    {
      try
      {
        this.zE -= 1;
        if (this.zE == 0)
        {
          if (this.zz == null) {
            break label128;
          }
          this.zD = false;
          G(3);
          if (dA()) {
            this.zC -= 1;
          }
          if (dA())
          {
            this.zG.sendMessageDelayed(this.zG.obtainMessage(1), this.zF);
            this.zJ = false;
          }
        }
        else
        {
          return;
        }
        this.zx.a(this.zz);
        continue;
        this.zB = 2;
      }
      finally
      {
        this.zv.unlock();
      }
      label128:
      dB();
      this.zw.signalAll();
      dz();
      if (!this.zD) {
        break;
      }
      this.zD = false;
      G(-1);
    }
    if (this.zH.isEmpty()) {}
    for (Bundle localBundle = null;; localBundle = this.zH)
    {
      this.zx.b(localBundle);
      break;
    }
  }
  
  private void dz()
  {
    er.a(isConnected(), "GoogleApiClient is not connected yet.");
    this.zv.lock();
    try
    {
      for (;;)
      {
        boolean bool = this.zy.isEmpty();
        if (bool) {
          break;
        }
        try
        {
          a((c)this.zy.remove());
        }
        catch (DeadObjectException localDeadObjectException)
        {
          Log.w("GoogleApiClientImpl", "Service died while flushing queue", localDeadObjectException);
        }
      }
    }
    finally
    {
      this.zv.unlock();
    }
  }
  
  public <C extends Api.a> C a(Api.b<C> paramb)
  {
    paramb = (Api.a)this.zI.get(paramb);
    er.b(paramb, "Appropriate Api was not requested.");
    return paramb;
  }
  
  /* Error */
  public <A extends Api.a, T extends a.a<? extends Result, A>> T a(T paramT)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 70	com/google/android/gms/common/api/b:zv	Ljava/util/concurrent/locks/Lock;
    //   4: invokeinterface 197 1 0
    //   9: aload_0
    //   10: invokevirtual 224	com/google/android/gms/common/api/b:isConnected	()Z
    //   13: ifeq +20 -> 33
    //   16: aload_0
    //   17: aload_1
    //   18: invokevirtual 356	com/google/android/gms/common/api/b:b	(Lcom/google/android/gms/common/api/a$a;)Lcom/google/android/gms/common/api/a$a;
    //   21: pop
    //   22: aload_0
    //   23: getfield 70	com/google/android/gms/common/api/b:zv	Ljava/util/concurrent/locks/Lock;
    //   26: invokeinterface 213 1 0
    //   31: aload_1
    //   32: areturn
    //   33: aload_0
    //   34: getfield 83	com/google/android/gms/common/api/b:zy	Ljava/util/Queue;
    //   37: aload_1
    //   38: invokeinterface 357 2 0
    //   43: pop
    //   44: goto -22 -> 22
    //   47: astore_1
    //   48: aload_0
    //   49: getfield 70	com/google/android/gms/common/api/b:zv	Ljava/util/concurrent/locks/Lock;
    //   52: invokeinterface 213 1 0
    //   57: aload_1
    //   58: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	59	0	this	b
    //   0	59	1	paramT	T
    // Exception table:
    //   from	to	target	type
    //   9	22	47	finally
    //   33	44	47	finally
  }
  
  public <A extends Api.a, T extends a.a<? extends Result, A>> T b(T paramT)
  {
    er.a(isConnected(), "GoogleApiClient is not connected yet.");
    dz();
    try
    {
      a(paramT);
      return paramT;
    }
    catch (DeadObjectException localDeadObjectException)
    {
      G(1);
    }
    return paramT;
  }
  
  public ConnectionResult blockingConnect(long paramLong, TimeUnit paramTimeUnit)
  {
    boolean bool;
    if (Looper.myLooper() != Looper.getMainLooper()) {
      bool = true;
    }
    for (;;)
    {
      er.a(bool, "blockingConnect must not be called on the UI thread");
      this.zv.lock();
      try
      {
        connect();
        paramLong = paramTimeUnit.toNanos(paramLong);
        for (;;)
        {
          bool = isConnecting();
          if (!bool) {
            break;
          }
          try
          {
            long l = this.zw.awaitNanos(paramLong);
            paramLong = l;
            if (l <= 0L)
            {
              paramTimeUnit = new ConnectionResult(14, null);
              return paramTimeUnit;
            }
          }
          catch (InterruptedException paramTimeUnit)
          {
            Thread.currentThread().interrupt();
            paramTimeUnit = new ConnectionResult(15, null);
            return paramTimeUnit;
          }
        }
        bool = false;
        continue;
        if (isConnected())
        {
          paramTimeUnit = ConnectionResult.yI;
          return paramTimeUnit;
        }
        if (this.zz != null)
        {
          paramTimeUnit = this.zz;
          return paramTimeUnit;
        }
        paramTimeUnit = new ConnectionResult(13, null);
        return paramTimeUnit;
      }
      finally
      {
        this.zv.unlock();
      }
    }
  }
  
  public void connect()
  {
    this.zv.lock();
    try
    {
      this.zD = false;
      if (!isConnected())
      {
        boolean bool = isConnecting();
        if (!bool) {}
      }
      else
      {
        return;
      }
      this.zJ = true;
      this.zz = null;
      this.zB = 1;
      this.zH.clear();
      this.zE = this.zI.size();
      Iterator localIterator = this.zI.values().iterator();
      while (localIterator.hasNext()) {
        ((Api.a)localIterator.next()).connect();
      }
    }
    finally
    {
      this.zv.unlock();
    }
  }
  
  public void disconnect()
  {
    dB();
    G(-1);
  }
  
  /* Error */
  public boolean isConnected()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 70	com/google/android/gms/common/api/b:zv	Ljava/util/concurrent/locks/Lock;
    //   4: invokeinterface 197 1 0
    //   9: aload_0
    //   10: getfield 85	com/google/android/gms/common/api/b:zB	I
    //   13: istore_1
    //   14: iload_1
    //   15: iconst_2
    //   16: if_icmpne +16 -> 32
    //   19: iconst_1
    //   20: istore_2
    //   21: aload_0
    //   22: getfield 70	com/google/android/gms/common/api/b:zv	Ljava/util/concurrent/locks/Lock;
    //   25: invokeinterface 213 1 0
    //   30: iload_2
    //   31: ireturn
    //   32: iconst_0
    //   33: istore_2
    //   34: goto -13 -> 21
    //   37: astore_3
    //   38: aload_0
    //   39: getfield 70	com/google/android/gms/common/api/b:zv	Ljava/util/concurrent/locks/Lock;
    //   42: invokeinterface 213 1 0
    //   47: aload_3
    //   48: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	49	0	this	b
    //   13	4	1	i	int
    //   20	14	2	bool	boolean
    //   37	11	3	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   9	14	37	finally
  }
  
  /* Error */
  public boolean isConnecting()
  {
    // Byte code:
    //   0: iconst_1
    //   1: istore_2
    //   2: aload_0
    //   3: getfield 70	com/google/android/gms/common/api/b:zv	Ljava/util/concurrent/locks/Lock;
    //   6: invokeinterface 197 1 0
    //   11: aload_0
    //   12: getfield 85	com/google/android/gms/common/api/b:zB	I
    //   15: istore_1
    //   16: iload_1
    //   17: iconst_1
    //   18: if_icmpne +14 -> 32
    //   21: aload_0
    //   22: getfield 70	com/google/android/gms/common/api/b:zv	Ljava/util/concurrent/locks/Lock;
    //   25: invokeinterface 213 1 0
    //   30: iload_2
    //   31: ireturn
    //   32: iconst_0
    //   33: istore_2
    //   34: goto -13 -> 21
    //   37: astore_3
    //   38: aload_0
    //   39: getfield 70	com/google/android/gms/common/api/b:zv	Ljava/util/concurrent/locks/Lock;
    //   42: invokeinterface 213 1 0
    //   47: aload_3
    //   48: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	49	0	this	b
    //   15	4	1	i	int
    //   1	33	2	bool	boolean
    //   37	11	3	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   11	16	37	finally
  }
  
  public boolean isConnectionCallbacksRegistered(GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    return this.zx.isConnectionCallbacksRegistered(paramConnectionCallbacks);
  }
  
  public boolean isConnectionFailedListenerRegistered(GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    return this.zx.isConnectionFailedListenerRegistered(paramOnConnectionFailedListener);
  }
  
  public void reconnect()
  {
    disconnect();
    connect();
  }
  
  public void registerConnectionCallbacks(GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    this.zx.registerConnectionCallbacks(paramConnectionCallbacks);
  }
  
  public void registerConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    this.zx.registerConnectionFailedListener(paramOnConnectionFailedListener);
  }
  
  public void unregisterConnectionCallbacks(GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    this.zx.unregisterConnectionCallbacks(paramConnectionCallbacks);
  }
  
  public void unregisterConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    this.zx.unregisterConnectionFailedListener(paramOnConnectionFailedListener);
  }
  
  static abstract interface a
  {
    public abstract void b(b.c paramc);
  }
  
  class b
    extends Handler
  {
    b(Looper paramLooper)
    {
      super();
    }
    
    public void handleMessage(Message paramMessage)
    {
      if (paramMessage.what == 1)
      {
        b.a(b.this).lock();
        try
        {
          if ((!b.this.isConnected()) && (!b.this.isConnecting())) {
            b.this.connect();
          }
          return;
        }
        finally
        {
          b.a(b.this).unlock();
        }
      }
      Log.wtf("GoogleApiClientImpl", "Don't know how to handle this message.");
    }
  }
  
  static abstract interface c<A extends Api.a>
  {
    public abstract void a(b.a parama);
    
    public abstract void b(A paramA)
      throws DeadObjectException;
    
    public abstract Api.b<A> dp();
    
    public abstract int dr();
    
    public abstract void du();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\common\api\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */