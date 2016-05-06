package com.spoledge.aacdecoder;

import android.util.Log;
import java.io.IOException;
import java.io.InputStream;

public class BufferReader
  implements Runnable
{
  private static String LOG = "BufferReader";
  private Buffer[] buffers;
  int capacity;
  private int indexBlocked;
  private int indexMine;
  private InputStream is;
  private boolean stopped;
  
  public BufferReader(int paramInt, InputStream paramInputStream)
  {
    this.capacity = paramInt;
    this.is = paramInputStream;
    Log.d(LOG, "init(): capacity=" + paramInt);
    this.buffers = new Buffer[3];
    int i = 0;
    while (i < this.buffers.length)
    {
      this.buffers[i] = new Buffer(paramInt);
      i += 1;
    }
    this.indexMine = 0;
    this.indexBlocked = (this.buffers.length - 1);
  }
  
  public boolean isStopped()
  {
    return this.stopped;
  }
  
  /* Error */
  public Buffer next()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 67	com/spoledge/aacdecoder/BufferReader:indexBlocked	I
    //   6: iconst_1
    //   7: iadd
    //   8: aload_0
    //   9: getfield 60	com/spoledge/aacdecoder/BufferReader:buffers	[Lcom/spoledge/aacdecoder/BufferReader$Buffer;
    //   12: arraylength
    //   13: irem
    //   14: istore_1
    //   15: aload_0
    //   16: getfield 71	com/spoledge/aacdecoder/BufferReader:stopped	Z
    //   19: ifne +41 -> 60
    //   22: iload_1
    //   23: aload_0
    //   24: getfield 65	com/spoledge/aacdecoder/BufferReader:indexMine	I
    //   27: if_icmpne +33 -> 60
    //   30: getstatic 27	com/spoledge/aacdecoder/BufferReader:LOG	Ljava/lang/String;
    //   33: ldc 77
    //   35: invokestatic 58	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   38: pop
    //   39: aload_0
    //   40: invokevirtual 80	java/lang/Object:wait	()V
    //   43: getstatic 27	com/spoledge/aacdecoder/BufferReader:LOG	Ljava/lang/String;
    //   46: ldc 82
    //   48: invokestatic 58	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   51: pop
    //   52: goto -37 -> 15
    //   55: astore_3
    //   56: aload_0
    //   57: monitorexit
    //   58: aload_3
    //   59: athrow
    //   60: aload_0
    //   61: getfield 65	com/spoledge/aacdecoder/BufferReader:indexMine	I
    //   64: istore_2
    //   65: iload_1
    //   66: iload_2
    //   67: if_icmpne +9 -> 76
    //   70: aconst_null
    //   71: astore_3
    //   72: aload_0
    //   73: monitorexit
    //   74: aload_3
    //   75: areturn
    //   76: aload_0
    //   77: iload_1
    //   78: putfield 67	com/spoledge/aacdecoder/BufferReader:indexBlocked	I
    //   81: aload_0
    //   82: invokevirtual 85	java/lang/Object:notify	()V
    //   85: aload_0
    //   86: getfield 60	com/spoledge/aacdecoder/BufferReader:buffers	[Lcom/spoledge/aacdecoder/BufferReader$Buffer;
    //   89: aload_0
    //   90: getfield 67	com/spoledge/aacdecoder/BufferReader:indexBlocked	I
    //   93: aaload
    //   94: astore_3
    //   95: goto -23 -> 72
    //   98: astore_3
    //   99: goto -56 -> 43
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	102	0	this	BufferReader
    //   14	64	1	i	int
    //   64	4	2	j	int
    //   55	4	3	localObject	Object
    //   71	24	3	localBuffer	Buffer
    //   98	1	3	localInterruptedException	InterruptedException
    // Exception table:
    //   from	to	target	type
    //   2	15	55	finally
    //   15	39	55	finally
    //   39	43	55	finally
    //   43	52	55	finally
    //   60	65	55	finally
    //   76	95	55	finally
    //   39	43	98	java/lang/InterruptedException
  }
  
  public void run()
  {
    Log.d(LOG, "run() started....");
    int i = this.capacity;
    while (!this.stopped)
    {
      Object localObject3 = this.buffers[this.indexMine];
      int k = 0;
      Object localObject1 = localObject3;
      int j = k;
      if (i != ((Buffer)localObject3).data.length)
      {
        Log.d(LOG, "run() capacity changed: " + ((Buffer)localObject3).data.length + " -> " + i);
        this.buffers[this.indexMine] = null;
        localObject3 = this.buffers;
        j = this.indexMine;
        localObject1 = new Buffer(i);
        localObject3[j] = localObject1;
        j = k;
      }
      while ((!this.stopped) && (j < i))
      {
        try
        {
          k = this.is.read(((Buffer)localObject1).data, j, i - j);
          if (k != -1) {
            break label208;
          }
          this.stopped = true;
        }
        catch (IOException localIOException)
        {
          Log.e(LOG, "Exception when reading: " + localIOException);
          this.stopped = true;
        }
        continue;
        label208:
        j += k;
      }
      Buffer.access$102((Buffer)localObject1, j);
      try
      {
        notify();
        i = (this.indexMine + 1) % this.buffers.length;
        while (!this.stopped)
        {
          j = this.indexBlocked;
          if (i != j) {
            break;
          }
          try
          {
            wait();
          }
          catch (InterruptedException localInterruptedException) {}
        }
        this.indexMine = i;
        i = this.capacity;
      }
      finally {}
    }
    Log.d(LOG, "run() stopped.");
  }
  
  public void setCapacity(int paramInt)
  {
    try
    {
      Log.d(LOG, "setCapacity(): " + paramInt);
      this.capacity = paramInt;
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void stop()
  {
    try
    {
      this.stopped = true;
      notify();
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public static class Buffer
  {
    private byte[] data;
    private int size;
    
    Buffer(int paramInt)
    {
      this.data = new byte[paramInt];
    }
    
    public final byte[] getData()
    {
      return this.data;
    }
    
    public final int getSize()
    {
      return this.size;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\spoledge\aacdecoder\BufferReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */