package com.google.android.gms.tagmanager;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Pair;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.PendingResult.a;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.er;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

abstract class ca<R extends Result>
  implements PendingResult<R>
{
  private a<R> VK;
  private final Object zd = new Object();
  private final CountDownLatch zf = new CountDownLatch(1);
  private final ArrayList<PendingResult.a> zg = new ArrayList();
  private ResultCallback<R> zh;
  private volatile R zi;
  private volatile boolean zj;
  
  public ca(Looper paramLooper)
  {
    this.VK = new a(paramLooper);
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
  
  public final void a(R paramR)
  {
    for (;;)
    {
      synchronized (this.zd)
      {
        if (isReady()) {
          return;
        }
        if (!this.zj)
        {
          bool = true;
          er.a(bool, "Result has already been consumed");
          this.zi = paramR;
          this.zf.countDown();
          paramR = this.zi.getStatus();
          if (this.zh != null)
          {
            this.VK.dw();
            this.VK.a(this.zh, ds());
          }
          Iterator localIterator = this.zg.iterator();
          if (!localIterator.hasNext()) {
            break;
          }
          ((PendingResult.a)localIterator.next()).k(paramR);
        }
      }
      boolean bool = false;
    }
    this.zg.clear();
  }
  
  public R await()
  {
    boolean bool;
    if (!this.zj) {
      bool = true;
    }
    for (;;)
    {
      er.a(bool, "Results has already been consumed");
      try
      {
        this.zf.await();
        er.a(isReady(), "Result is not ready.");
        return ds();
        bool = false;
      }
      catch (InterruptedException localInterruptedException)
      {
        for (;;)
        {
          a(d(Status.zR));
        }
      }
    }
  }
  
  public R await(long paramLong, TimeUnit paramTimeUnit)
  {
    boolean bool;
    if (!this.zj) {
      bool = true;
    }
    for (;;)
    {
      er.a(bool, "Result has already been consumed.");
      try
      {
        if (!this.zf.await(paramLong, paramTimeUnit)) {
          a(d(Status.zS));
        }
        er.a(isReady(), "Result is not ready.");
        return ds();
        bool = false;
      }
      catch (InterruptedException paramTimeUnit)
      {
        for (;;)
        {
          a(d(Status.zR));
        }
      }
    }
  }
  
  protected abstract R d(Status paramStatus);
  
  void dt()
  {
    this.zj = true;
    this.zi = null;
  }
  
  public boolean isReady()
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
          this.VK.a(paramResultCallback, ds());
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
          this.VK.a(paramResultCallback, ds());
          return;
        }
        this.zh = paramResultCallback;
        this.VK.a(this, paramTimeUnit.toMillis(paramLong));
      }
    }
  }
  
  public static class a<R extends Result>
    extends Handler
  {
    public a()
    {
      this(Looper.getMainLooper());
    }
    
    public a(Looper paramLooper)
    {
      super();
    }
    
    public void a(ResultCallback<R> paramResultCallback, R paramR)
    {
      sendMessage(obtainMessage(1, new Pair(paramResultCallback, paramR)));
    }
    
    public void a(ca<R> paramca, long paramLong)
    {
      sendMessageDelayed(obtainMessage(2, paramca), paramLong);
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
        bh.t("Don't know how to handle this message.");
        return;
      case 1: 
        paramMessage = (Pair)paramMessage.obj;
        b((ResultCallback)paramMessage.first, (Result)paramMessage.second);
        return;
      }
      paramMessage = (ca)paramMessage.obj;
      paramMessage.a(paramMessage.d(Status.zS));
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\tagmanager\ca.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */