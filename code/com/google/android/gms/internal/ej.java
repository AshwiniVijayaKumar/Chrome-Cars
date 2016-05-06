package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.IBinder;
import android.os.Message;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public final class ej
  implements Handler.Callback
{
  private static final Object BQ = new Object();
  private static ej BR;
  private final HashMap<String, a> BS = new HashMap();
  private final Handler mHandler = new Handler(paramContext.getMainLooper(), this);
  private final Context qe;
  
  private ej(Context paramContext)
  {
    this.qe = paramContext.getApplicationContext();
  }
  
  public static ej y(Context paramContext)
  {
    synchronized (BQ)
    {
      if (BR == null) {
        BR = new ej(paramContext.getApplicationContext());
      }
      return BR;
    }
  }
  
  public boolean a(String paramString, eh<?>.f parameh)
  {
    for (;;)
    {
      a locala;
      synchronized (this.BS)
      {
        locala = (a)this.BS.get(paramString);
        if (locala == null)
        {
          locala = new a(paramString);
          locala.a(parameh);
          parameh = new Intent(paramString).setPackage("com.google.android.gms");
          locala.w(this.qe.bindService(parameh, locala.ee(), 129));
          this.BS.put(paramString, locala);
          paramString = locala;
          boolean bool = paramString.isBound();
          return bool;
        }
        this.mHandler.removeMessages(0, locala);
        if (locala.c(parameh)) {
          throw new IllegalStateException("Trying to bind a GmsServiceConnection that was already connected before.  startServiceAction=" + paramString);
        }
      }
      locala.a(parameh);
      switch (locala.getState())
      {
      case 1: 
        parameh.onServiceConnected(locala.getComponentName(), locala.getBinder());
        paramString = locala;
        break;
      case 2: 
        paramString = new Intent(paramString).setPackage("com.google.android.gms");
        locala.w(this.qe.bindService(paramString, locala.ee(), 129));
        paramString = locala;
        break;
      default: 
        paramString = locala;
      }
    }
  }
  
  public void b(String paramString, eh<?>.f parameh)
  {
    a locala;
    synchronized (this.BS)
    {
      locala = (a)this.BS.get(paramString);
      if (locala == null) {
        throw new IllegalStateException("Nonexistent connection status for service action: " + paramString);
      }
    }
    if (!locala.c(parameh)) {
      throw new IllegalStateException("Trying to unbind a GmsServiceConnection  that was not bound before.  startServiceAction=" + paramString);
    }
    locala.b(parameh);
    if (locala.eg())
    {
      paramString = this.mHandler.obtainMessage(0, locala);
      this.mHandler.sendMessageDelayed(paramString, 5000L);
    }
  }
  
  public boolean handleMessage(Message arg1)
  {
    switch (???.what)
    {
    default: 
      return false;
    }
    a locala = (a)???.obj;
    synchronized (this.BS)
    {
      if (locala.eg())
      {
        this.qe.unbindService(locala.ee());
        this.BS.remove(locala.ef());
      }
      return true;
    }
  }
  
  final class a
  {
    private final String BT;
    private final a BU;
    private final HashSet<eh<?>.f> BV;
    private boolean BW;
    private IBinder BX;
    private ComponentName BY;
    private int mState;
    
    public a(String paramString)
    {
      this.BT = paramString;
      this.BU = new a();
      this.BV = new HashSet();
      this.mState = 0;
    }
    
    public void a(eh<?>.f parameh)
    {
      this.BV.add(parameh);
    }
    
    public void b(eh<?>.f parameh)
    {
      this.BV.remove(parameh);
    }
    
    public boolean c(eh<?>.f parameh)
    {
      return this.BV.contains(parameh);
    }
    
    public a ee()
    {
      return this.BU;
    }
    
    public String ef()
    {
      return this.BT;
    }
    
    public boolean eg()
    {
      return this.BV.isEmpty();
    }
    
    public IBinder getBinder()
    {
      return this.BX;
    }
    
    public ComponentName getComponentName()
    {
      return this.BY;
    }
    
    public int getState()
    {
      return this.mState;
    }
    
    public boolean isBound()
    {
      return this.BW;
    }
    
    public void w(boolean paramBoolean)
    {
      this.BW = paramBoolean;
    }
    
    public class a
      implements ServiceConnection
    {
      public a() {}
      
      public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
      {
        synchronized (ej.a(ej.this))
        {
          ej.a.a(ej.a.this, paramIBinder);
          ej.a.a(ej.a.this, paramComponentName);
          Iterator localIterator = ej.a.a(ej.a.this).iterator();
          if (localIterator.hasNext()) {
            ((eh.f)localIterator.next()).onServiceConnected(paramComponentName, paramIBinder);
          }
        }
        ej.a.a(ej.a.this, 1);
      }
      
      public void onServiceDisconnected(ComponentName paramComponentName)
      {
        synchronized (ej.a(ej.this))
        {
          ej.a.a(ej.a.this, null);
          ej.a.a(ej.a.this, paramComponentName);
          Iterator localIterator = ej.a.a(ej.a.this).iterator();
          if (localIterator.hasNext()) {
            ((eh.f)localIterator.next()).onServiceDisconnected(paramComponentName);
          }
        }
        ej.a.a(ej.a.this, 2);
      }
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\ej.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */