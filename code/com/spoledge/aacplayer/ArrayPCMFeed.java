package com.spoledge.aacplayer;

public class ArrayPCMFeed
  extends PCMFeed
{
  private int n;
  private short[] samples;
  
  public ArrayPCMFeed(int paramInt1, int paramInt2, int paramInt3)
  {
    this(paramInt1, paramInt2, paramInt3, null);
  }
  
  public ArrayPCMFeed(int paramInt1, int paramInt2, int paramInt3, PlayerCallback paramPlayerCallback)
  {
    super(paramInt1, paramInt2, paramInt3, paramPlayerCallback);
  }
  
  protected int acquireSamples()
  {
    try
    {
      while (this.n == 0)
      {
        boolean bool = this.stopped;
        if (bool) {
          break;
        }
        try
        {
          wait();
        }
        catch (InterruptedException localInterruptedException) {}
      }
      this.lsamples = this.samples;
      int i = this.n;
      this.samples = null;
      this.n = 0;
      notify();
      return i;
    }
    finally {}
  }
  
  /* Error */
  public boolean feed(short[] paramArrayOfShort, int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 33	com/spoledge/aacplayer/ArrayPCMFeed:samples	[S
    //   6: ifnull +24 -> 30
    //   9: aload_0
    //   10: getfield 25	com/spoledge/aacplayer/ArrayPCMFeed:stopped	Z
    //   13: istore_3
    //   14: iload_3
    //   15: ifne +15 -> 30
    //   18: aload_0
    //   19: invokevirtual 31	java/lang/Object:wait	()V
    //   22: goto -20 -> 2
    //   25: astore 4
    //   27: goto -25 -> 2
    //   30: aload_0
    //   31: aload_1
    //   32: putfield 33	com/spoledge/aacplayer/ArrayPCMFeed:samples	[S
    //   35: aload_0
    //   36: iload_2
    //   37: putfield 21	com/spoledge/aacplayer/ArrayPCMFeed:n	I
    //   40: aload_0
    //   41: invokevirtual 39	java/lang/Object:notify	()V
    //   44: aload_0
    //   45: getfield 25	com/spoledge/aacplayer/ArrayPCMFeed:stopped	Z
    //   48: istore_3
    //   49: iload_3
    //   50: ifne +9 -> 59
    //   53: iconst_1
    //   54: istore_3
    //   55: aload_0
    //   56: monitorexit
    //   57: iload_3
    //   58: ireturn
    //   59: iconst_0
    //   60: istore_3
    //   61: goto -6 -> 55
    //   64: astore_1
    //   65: aload_0
    //   66: monitorexit
    //   67: aload_1
    //   68: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	69	0	this	ArrayPCMFeed
    //   0	69	1	paramArrayOfShort	short[]
    //   0	69	2	paramInt	int
    //   13	48	3	bool	boolean
    //   25	1	4	localInterruptedException	InterruptedException
    // Exception table:
    //   from	to	target	type
    //   18	22	25	java/lang/InterruptedException
    //   2	14	64	finally
    //   18	22	64	finally
    //   30	49	64	finally
  }
  
  protected void releaseSamples() {}
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\spoledge\aacplayer\ArrayPCMFeed.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */