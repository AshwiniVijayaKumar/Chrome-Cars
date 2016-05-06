package com.google.android.gms.common.api;

import android.os.DeadObjectException;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.internal.er;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class a
{
  public static abstract class a<R extends Result, A extends Api.a>
    implements PendingResult<R>, a.c<R>, b.c<A>
  {
    private final Api.b<A> zc;
    private final Object zd = new Object();
    private a.b<R> ze;
    private final CountDownLatch zf = new CountDownLatch(1);
    private final ArrayList<PendingResult.a> zg = new ArrayList();
    private ResultCallback<R> zh;
    private volatile R zi;
    private volatile boolean zj;
    private boolean zk;
    private boolean zl;
    private b.a zm;
    
    protected a()
    {
      this((Api.b)null);
    }
    
    protected a(Api.b<A> paramb)
    {
      this.zc = paramb;
    }
    
    private void a(RemoteException paramRemoteException)
    {
      a(d(new Status(8, paramRemoteException.getLocalizedMessage(), null)));
    }
    
    private R ds()
    {
      for (;;)
      {
        synchronized (this.zd)
        {
          if (!this.zj)
          {
            bool = true;
            er.a(bool, "Result has already been consumed.");
            er.a(isReady(), "Result is not ready.");
            Result localResult = this.zi;
            dt();
            return localResult;
          }
        }
        boolean bool = false;
      }
    }
    
    private void dv()
    {
      if ((this.zi != null) && ((this instanceof Releasable))) {}
      try
      {
        ((Releasable)this).release();
        return;
      }
      catch (Exception localException)
      {
        Log.w("GoogleApi", "Unable to release " + this, localException);
      }
    }
    
    protected abstract void a(A paramA)
      throws RemoteException;
    
    public final void a(R paramR)
    {
      boolean bool2 = true;
      for (;;)
      {
        synchronized (this.zd)
        {
          if (this.zl)
          {
            if ((paramR instanceof Releasable)) {
              ((Releasable)paramR).release();
            }
            return;
          }
          if (!isReady())
          {
            bool1 = true;
            er.a(bool1, "Results have already been set");
            if (this.zj) {
              break label99;
            }
            bool1 = bool2;
            er.a(bool1, "Result has already been consumed");
            this.zi = paramR;
            if (!this.zk) {
              break;
            }
            dv();
            return;
          }
        }
        boolean bool1 = false;
        continue;
        label99:
        bool1 = false;
      }
      this.zf.countDown();
      paramR = this.zi.getStatus();
      if (this.zh != null)
      {
        this.ze.dw();
        this.ze.a(this.zh, ds());
      }
      Iterator localIterator = this.zg.iterator();
      while (localIterator.hasNext()) {
        ((PendingResult.a)localIterator.next()).k(paramR);
      }
      this.zg.clear();
    }
    
    public void a(b.a parama)
    {
      this.zm = parama;
    }
    
    public final R await()
    {
      boolean bool2 = false;
      if (!this.zj) {}
      for (boolean bool1 = true;; bool1 = false)
      {
        er.a(bool1, "Results has already been consumed");
        if (!isReady())
        {
          bool1 = bool2;
          if (Looper.myLooper() == Looper.getMainLooper()) {}
        }
        else
        {
          bool1 = true;
        }
        er.a(bool1, "await must not be called on the UI thread");
        try
        {
          this.zf.await();
          er.a(isReady(), "Result is not ready.");
          return ds();
        }
        catch (InterruptedException localInterruptedException)
        {
          synchronized (this.zd)
          {
            a(d(Status.zR));
            this.zl = true;
          }
        }
      }
    }
    
    /* Error */
    public final R await(long paramLong, TimeUnit arg3)
    {
      // Byte code:
      //   0: iconst_0
      //   1: istore 5
      //   3: aload_0
      //   4: getfield 90	com/google/android/gms/common/api/a$a:zj	Z
      //   7: ifne +94 -> 101
      //   10: iconst_1
      //   11: istore 4
      //   13: iload 4
      //   15: ldc 92
      //   17: invokestatic 97	com/google/android/gms/internal/er:a	(ZLjava/lang/Object;)V
      //   20: aload_0
      //   21: invokevirtual 101	com/google/android/gms/common/api/a$a:isReady	()Z
      //   24: ifne +16 -> 40
      //   27: iload 5
      //   29: istore 4
      //   31: invokestatic 211	android/os/Looper:myLooper	()Landroid/os/Looper;
      //   34: invokestatic 214	android/os/Looper:getMainLooper	()Landroid/os/Looper;
      //   37: if_acmpeq +6 -> 43
      //   40: iconst_1
      //   41: istore 4
      //   43: iload 4
      //   45: ldc -40
      //   47: invokestatic 97	com/google/android/gms/internal/er:a	(ZLjava/lang/Object;)V
      //   50: aload_0
      //   51: getfield 58	com/google/android/gms/common/api/a$a:zf	Ljava/util/concurrent/CountDownLatch;
      //   54: lload_1
      //   55: aload_3
      //   56: invokevirtual 226	java/util/concurrent/CountDownLatch:await	(JLjava/util/concurrent/TimeUnit;)Z
      //   59: ifne +28 -> 87
      //   62: aload_0
      //   63: getfield 51	com/google/android/gms/common/api/a$a:zd	Ljava/lang/Object;
      //   66: astore_3
      //   67: aload_3
      //   68: monitorenter
      //   69: aload_0
      //   70: aload_0
      //   71: getstatic 229	com/google/android/gms/common/api/Status:zS	Lcom/google/android/gms/common/api/Status;
      //   74: invokevirtual 83	com/google/android/gms/common/api/a$a:d	(Lcom/google/android/gms/common/api/Status;)Lcom/google/android/gms/common/api/Result;
      //   77: invokevirtual 86	com/google/android/gms/common/api/a$a:a	(Lcom/google/android/gms/common/api/Result;)V
      //   80: aload_0
      //   81: iconst_1
      //   82: putfield 143	com/google/android/gms/common/api/a$a:zl	Z
      //   85: aload_3
      //   86: monitorexit
      //   87: aload_0
      //   88: invokevirtual 101	com/google/android/gms/common/api/a$a:isReady	()Z
      //   91: ldc 103
      //   93: invokestatic 97	com/google/android/gms/internal/er:a	(ZLjava/lang/Object;)V
      //   96: aload_0
      //   97: invokespecial 171	com/google/android/gms/common/api/a$a:ds	()Lcom/google/android/gms/common/api/Result;
      //   100: areturn
      //   101: iconst_0
      //   102: istore 4
      //   104: goto -91 -> 13
      //   107: astore 6
      //   109: aload_3
      //   110: monitorexit
      //   111: aload 6
      //   113: athrow
      //   114: astore_3
      //   115: aload_0
      //   116: getfield 51	com/google/android/gms/common/api/a$a:zd	Ljava/lang/Object;
      //   119: astore_3
      //   120: aload_3
      //   121: monitorenter
      //   122: aload_0
      //   123: aload_0
      //   124: getstatic 222	com/google/android/gms/common/api/Status:zR	Lcom/google/android/gms/common/api/Status;
      //   127: invokevirtual 83	com/google/android/gms/common/api/a$a:d	(Lcom/google/android/gms/common/api/Status;)Lcom/google/android/gms/common/api/Result;
      //   130: invokevirtual 86	com/google/android/gms/common/api/a$a:a	(Lcom/google/android/gms/common/api/Result;)V
      //   133: aload_0
      //   134: iconst_1
      //   135: putfield 143	com/google/android/gms/common/api/a$a:zl	Z
      //   138: aload_3
      //   139: monitorexit
      //   140: goto -53 -> 87
      //   143: astore 6
      //   145: aload_3
      //   146: monitorexit
      //   147: aload 6
      //   149: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	150	0	this	a
      //   0	150	1	paramLong	long
      //   11	92	4	bool1	boolean
      //   1	27	5	bool2	boolean
      //   107	5	6	localObject1	Object
      //   143	5	6	localObject2	Object
      // Exception table:
      //   from	to	target	type
      //   69	87	107	finally
      //   109	111	107	finally
      //   50	69	114	java/lang/InterruptedException
      //   111	114	114	java/lang/InterruptedException
      //   122	140	143	finally
      //   145	147	143	finally
    }
    
    public final void b(A paramA)
      throws DeadObjectException
    {
      this.ze = new a.b(paramA.getLooper());
      try
      {
        a(paramA);
        return;
      }
      catch (DeadObjectException paramA)
      {
        a(paramA);
        throw paramA;
      }
      catch (RemoteException paramA)
      {
        a(paramA);
      }
    }
    
    protected abstract R d(Status paramStatus);
    
    public final Api.b<A> dp()
    {
      return this.zc;
    }
    
    public int dr()
    {
      return 0;
    }
    
    void dt()
    {
      this.zj = true;
      this.zi = null;
      if (this.zm != null) {
        this.zm.b(this);
      }
    }
    
    public void du()
    {
      dv();
      this.zk = true;
    }
    
    public final boolean isReady()
    {
      return this.zf.getCount() == 0L;
    }
    
    public final void setResultCallback(ResultCallback<R> paramResultCallback)
    {
      if (!this.zj) {}
      for (boolean bool = true;; bool = false)
      {
        er.a(bool, "Result has already been consumed.");
        synchronized (this.zd)
        {
          if (isReady())
          {
            this.ze.a(paramResultCallback, ds());
            return;
          }
          this.zh = paramResultCallback;
        }
      }
    }
    
    public final void setResultCallback(ResultCallback<R> paramResultCallback, long paramLong, TimeUnit paramTimeUnit)
    {
      if (!this.zj) {}
      for (boolean bool = true;; bool = false)
      {
        er.a(bool, "Result has already been consumed.");
        synchronized (this.zd)
        {
          if (isReady())
          {
            this.ze.a(paramResultCallback, ds());
            return;
          }
          this.zh = paramResultCallback;
          this.ze.a(this, paramTimeUnit.toMillis(paramLong));
        }
      }
    }
  }
  
  public static class b<R extends Result>
    extends Handler
  {
    public b()
    {
      this(Looper.getMainLooper());
    }
    
    public b(Looper paramLooper)
    {
      super();
    }
    
    public void a(ResultCallback<R> paramResultCallback, R paramR)
    {
      sendMessage(obtainMessage(1, new Pair(paramResultCallback, paramR)));
    }
    
    public void a(a.a<R, ?> parama, long paramLong)
    {
      sendMessageDelayed(obtainMessage(2, parama), paramLong);
    }
    
    protected void b(ResultCallback<R> paramResultCallback, R paramR)
    {
      paramResultCallback.onResult(paramR);
    }
    
    public void dw()
    {
      removeMessages(2);
    }
    
    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default: 
        Log.wtf("GoogleApi", "Don't know how to handle this message.");
        return;
      case 1: 
        paramMessage = (Pair)paramMessage.obj;
        b((ResultCallback)paramMessage.first, (Result)paramMessage.second);
        return;
      }
      paramMessage = (a.a)paramMessage.obj;
      paramMessage.a(paramMessage.d(Status.zS));
    }
  }
  
  public static abstract interface c<R>
  {
    public abstract void b(R paramR);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\common\api\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */