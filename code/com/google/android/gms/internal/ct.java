package com.google.android.gms.internal;

public abstract class ct
{
  private final Runnable kW = new Runnable()
  {
    public final void run()
    {
      ct.a(ct.this, Thread.currentThread());
      ct.this.aB();
    }
  };
  private volatile Thread pI;
  
  public abstract void aB();
  
  public final void cancel()
  {
    onStop();
    if (this.pI != null) {
      this.pI.interrupt();
    }
  }
  
  public abstract void onStop();
  
  public final void start()
  {
    cu.execute(this.kW);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\ct.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */