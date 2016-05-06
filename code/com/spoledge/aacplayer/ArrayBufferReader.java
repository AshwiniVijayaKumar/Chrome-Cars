package com.spoledge.aacplayer;

import android.util.Log;
import java.io.InputStream;

public class ArrayBufferReader
  implements Runnable
{
  private static String LOG = "ArrayBufferReader";
  private Buffer[] buffers;
  int capacity;
  private int indexBlocked;
  private int indexMine;
  private InputStream is;
  private boolean stopped;
  
  public ArrayBufferReader(int paramInt, InputStream paramInputStream)
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
    //   3: getfield 67	com/spoledge/aacplayer/ArrayBufferReader:indexBlocked	I
    //   6: iconst_1
    //   7: iadd
    //   8: aload_0
    //   9: getfield 60	com/spoledge/aacplayer/ArrayBufferReader:buffers	[Lcom/spoledge/aacplayer/ArrayBufferReader$Buffer;
    //   12: arraylength
    //   13: irem
    //   14: istore_1
    //   15: aload_0
    //   16: getfield 71	com/spoledge/aacplayer/ArrayBufferReader:stopped	Z
    //   19: ifne +41 -> 60
    //   22: iload_1
    //   23: aload_0
    //   24: getfield 65	com/spoledge/aacplayer/ArrayBufferReader:indexMine	I
    //   27: if_icmpne +33 -> 60
    //   30: getstatic 27	com/spoledge/aacplayer/ArrayBufferReader:LOG	Ljava/lang/String;
    //   33: ldc 77
    //   35: invokestatic 58	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   38: pop
    //   39: aload_0
    //   40: invokevirtual 80	java/lang/Object:wait	()V
    //   43: getstatic 27	com/spoledge/aacplayer/ArrayBufferReader:LOG	Ljava/lang/String;
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
    //   61: getfield 65	com/spoledge/aacplayer/ArrayBufferReader:indexMine	I
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
    //   78: putfield 67	com/spoledge/aacplayer/ArrayBufferReader:indexBlocked	I
    //   81: aload_0
    //   82: invokevirtual 85	java/lang/Object:notify	()V
    //   85: aload_0
    //   86: getfield 60	com/spoledge/aacplayer/ArrayBufferReader:buffers	[Lcom/spoledge/aacplayer/ArrayBufferReader$Buffer;
    //   89: aload_0
    //   90: getfield 67	com/spoledge/aacplayer/ArrayBufferReader:indexBlocked	I
    //   93: aaload
    //   94: astore_3
    //   95: goto -23 -> 72
    //   98: astore_3
    //   99: goto -56 -> 43
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	102	0	this	ArrayBufferReader
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
  
  /* Error */
  public void run()
  {
    // Byte code:
    //   0: getstatic 27	com/spoledge/aacplayer/ArrayBufferReader:LOG	Ljava/lang/String;
    //   3: ldc 90
    //   5: invokestatic 58	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   8: pop
    //   9: aload_0
    //   10: getfield 34	com/spoledge/aacplayer/ArrayBufferReader:capacity	I
    //   13: istore_1
    //   14: aload_0
    //   15: getfield 71	com/spoledge/aacplayer/ArrayBufferReader:stopped	Z
    //   18: ifne +285 -> 303
    //   21: aload_0
    //   22: getfield 60	com/spoledge/aacplayer/ArrayBufferReader:buffers	[Lcom/spoledge/aacplayer/ArrayBufferReader$Buffer;
    //   25: aload_0
    //   26: getfield 65	com/spoledge/aacplayer/ArrayBufferReader:indexMine	I
    //   29: aaload
    //   30: astore 5
    //   32: iconst_0
    //   33: istore_3
    //   34: aload 5
    //   36: astore 4
    //   38: iload_3
    //   39: istore_2
    //   40: iload_1
    //   41: aload 5
    //   43: invokestatic 94	com/spoledge/aacplayer/ArrayBufferReader$Buffer:access$000	(Lcom/spoledge/aacplayer/ArrayBufferReader$Buffer;)[B
    //   46: arraylength
    //   47: if_icmpeq +82 -> 129
    //   50: getstatic 27	com/spoledge/aacplayer/ArrayBufferReader:LOG	Ljava/lang/String;
    //   53: new 38	java/lang/StringBuilder
    //   56: dup
    //   57: invokespecial 39	java/lang/StringBuilder:<init>	()V
    //   60: ldc 96
    //   62: invokevirtual 45	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   65: aload 5
    //   67: invokestatic 94	com/spoledge/aacplayer/ArrayBufferReader$Buffer:access$000	(Lcom/spoledge/aacplayer/ArrayBufferReader$Buffer;)[B
    //   70: arraylength
    //   71: invokevirtual 48	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   74: ldc 98
    //   76: invokevirtual 45	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   79: iload_1
    //   80: invokevirtual 48	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   83: invokevirtual 52	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   86: invokestatic 58	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   89: pop
    //   90: aload_0
    //   91: getfield 60	com/spoledge/aacplayer/ArrayBufferReader:buffers	[Lcom/spoledge/aacplayer/ArrayBufferReader$Buffer;
    //   94: aload_0
    //   95: getfield 65	com/spoledge/aacplayer/ArrayBufferReader:indexMine	I
    //   98: aconst_null
    //   99: aastore
    //   100: aload_0
    //   101: getfield 60	com/spoledge/aacplayer/ArrayBufferReader:buffers	[Lcom/spoledge/aacplayer/ArrayBufferReader$Buffer;
    //   104: astore 5
    //   106: aload_0
    //   107: getfield 65	com/spoledge/aacplayer/ArrayBufferReader:indexMine	I
    //   110: istore_2
    //   111: new 8	com/spoledge/aacplayer/ArrayBufferReader$Buffer
    //   114: dup
    //   115: iload_1
    //   116: invokespecial 63	com/spoledge/aacplayer/ArrayBufferReader$Buffer:<init>	(I)V
    //   119: astore 4
    //   121: aload 5
    //   123: iload_2
    //   124: aload 4
    //   126: aastore
    //   127: iload_3
    //   128: istore_2
    //   129: aload_0
    //   130: getfield 71	com/spoledge/aacplayer/ArrayBufferReader:stopped	Z
    //   133: ifne +82 -> 215
    //   136: iload_2
    //   137: iload_1
    //   138: if_icmpge +77 -> 215
    //   141: aload_0
    //   142: getfield 36	com/spoledge/aacplayer/ArrayBufferReader:is	Ljava/io/InputStream;
    //   145: aload 4
    //   147: invokestatic 94	com/spoledge/aacplayer/ArrayBufferReader$Buffer:access$000	(Lcom/spoledge/aacplayer/ArrayBufferReader$Buffer;)[B
    //   150: iload_2
    //   151: iload_1
    //   152: iload_2
    //   153: isub
    //   154: invokevirtual 104	java/io/InputStream:read	([BII)I
    //   157: istore_3
    //   158: iload_3
    //   159: iconst_m1
    //   160: if_icmpne +48 -> 208
    //   163: aload_0
    //   164: iconst_1
    //   165: putfield 71	com/spoledge/aacplayer/ArrayBufferReader:stopped	Z
    //   168: goto -39 -> 129
    //   171: astore 5
    //   173: getstatic 27	com/spoledge/aacplayer/ArrayBufferReader:LOG	Ljava/lang/String;
    //   176: new 38	java/lang/StringBuilder
    //   179: dup
    //   180: invokespecial 39	java/lang/StringBuilder:<init>	()V
    //   183: ldc 106
    //   185: invokevirtual 45	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   188: aload 5
    //   190: invokevirtual 109	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   193: invokevirtual 52	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   196: invokestatic 112	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   199: pop
    //   200: aload_0
    //   201: iconst_1
    //   202: putfield 71	com/spoledge/aacplayer/ArrayBufferReader:stopped	Z
    //   205: goto -76 -> 129
    //   208: iload_2
    //   209: iload_3
    //   210: iadd
    //   211: istore_2
    //   212: goto -83 -> 129
    //   215: aload 4
    //   217: iload_2
    //   218: invokestatic 116	com/spoledge/aacplayer/ArrayBufferReader$Buffer:access$102	(Lcom/spoledge/aacplayer/ArrayBufferReader$Buffer;I)I
    //   221: pop
    //   222: aload_0
    //   223: monitorenter
    //   224: aload_0
    //   225: invokevirtual 85	java/lang/Object:notify	()V
    //   228: aload_0
    //   229: getfield 65	com/spoledge/aacplayer/ArrayBufferReader:indexMine	I
    //   232: iconst_1
    //   233: iadd
    //   234: aload_0
    //   235: getfield 60	com/spoledge/aacplayer/ArrayBufferReader:buffers	[Lcom/spoledge/aacplayer/ArrayBufferReader$Buffer;
    //   238: arraylength
    //   239: irem
    //   240: istore_1
    //   241: aload_0
    //   242: getfield 71	com/spoledge/aacplayer/ArrayBufferReader:stopped	Z
    //   245: ifne +43 -> 288
    //   248: iload_1
    //   249: aload_0
    //   250: getfield 67	com/spoledge/aacplayer/ArrayBufferReader:indexBlocked	I
    //   253: if_icmpne +35 -> 288
    //   256: getstatic 27	com/spoledge/aacplayer/ArrayBufferReader:LOG	Ljava/lang/String;
    //   259: ldc 118
    //   261: invokestatic 58	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   264: pop
    //   265: aload_0
    //   266: invokevirtual 80	java/lang/Object:wait	()V
    //   269: getstatic 27	com/spoledge/aacplayer/ArrayBufferReader:LOG	Ljava/lang/String;
    //   272: ldc 120
    //   274: invokestatic 58	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   277: pop
    //   278: goto -37 -> 241
    //   281: astore 4
    //   283: aload_0
    //   284: monitorexit
    //   285: aload 4
    //   287: athrow
    //   288: aload_0
    //   289: iload_1
    //   290: putfield 65	com/spoledge/aacplayer/ArrayBufferReader:indexMine	I
    //   293: aload_0
    //   294: getfield 34	com/spoledge/aacplayer/ArrayBufferReader:capacity	I
    //   297: istore_1
    //   298: aload_0
    //   299: monitorexit
    //   300: goto -286 -> 14
    //   303: getstatic 27	com/spoledge/aacplayer/ArrayBufferReader:LOG	Ljava/lang/String;
    //   306: ldc 122
    //   308: invokestatic 58	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   311: pop
    //   312: return
    //   313: astore 4
    //   315: goto -46 -> 269
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	318	0	this	ArrayBufferReader
    //   13	285	1	i	int
    //   39	179	2	j	int
    //   33	178	3	k	int
    //   36	180	4	localObject1	Object
    //   281	5	4	localObject2	Object
    //   313	1	4	localInterruptedException	InterruptedException
    //   30	92	5	localObject3	Object
    //   171	18	5	localIOException	java.io.IOException
    // Exception table:
    //   from	to	target	type
    //   141	158	171	java/io/IOException
    //   163	168	171	java/io/IOException
    //   224	241	281	finally
    //   241	265	281	finally
    //   265	269	281	finally
    //   269	278	281	finally
    //   283	285	281	finally
    //   288	300	281	finally
    //   265	269	313	java/lang/InterruptedException
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


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\spoledge\aacplayer\ArrayBufferReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */