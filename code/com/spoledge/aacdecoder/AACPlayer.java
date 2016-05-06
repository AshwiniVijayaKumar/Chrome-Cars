package com.spoledge.aacdecoder;

import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class AACPlayer
{
  public static final int DEFAULT_AUDIO_BUFFER_CAPACITY_MS = 1500;
  public static final int DEFAULT_DECODE_BUFFER_CAPACITY_MS = 700;
  public static final int DEFAULT_EXPECTED_KBITSEC_RATE = 64;
  private static final String LOG = "AACPlayer";
  protected int audioBufferCapacityMs;
  private int avgKBitSecRate = 0;
  private int countKBitSecRate = 0;
  protected int declaredBitRate = -1;
  protected int decodeBufferCapacityMs;
  protected Decoder decoder;
  protected String metadataCharEnc;
  protected boolean metadataEnabled = true;
  protected PlayerCallback playerCallback;
  protected boolean responseCodeCheckEnabled = true;
  protected boolean stopped;
  private int sumKBitSecRate = 0;
  
  public AACPlayer()
  {
    this(null);
  }
  
  public AACPlayer(PlayerCallback paramPlayerCallback)
  {
    this(paramPlayerCallback, 1500, 700);
  }
  
  public AACPlayer(PlayerCallback paramPlayerCallback, int paramInt1, int paramInt2)
  {
    setPlayerCallback(paramPlayerCallback);
    setAudioBufferCapacityMs(paramInt1);
    setDecodeBufferCapacityMs(paramInt2);
    this.decoder = createDecoder();
  }
  
  protected static int computeInputBufferSize(int paramInt1, int paramInt2)
  {
    return paramInt1 * paramInt2 / 8;
  }
  
  protected static int computeInputBufferSize(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    return (int)(paramInt1 * paramInt4 * paramInt3 * paramInt5 / (1000L * paramInt2));
  }
  
  protected static int computeInputBufferSize(Decoder.Info paramInfo, int paramInt)
  {
    return computeInputBufferSize(paramInfo.getRoundBytesConsumed(), paramInfo.getRoundSamples(), paramInfo.getSampleRate(), paramInfo.getChannels(), paramInt);
  }
  
  protected static int computeKBitSecRate(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    return ((int)(8L * paramInt1 * paramInt4 * paramInt3 / paramInt2) + 500) / 1000;
  }
  
  protected static int computeKBitSecRate(Decoder.Info paramInfo)
  {
    if (paramInfo.getRoundSamples() <= 0) {
      return -1;
    }
    return computeKBitSecRate(paramInfo.getRoundBytesConsumed(), paramInfo.getRoundSamples(), paramInfo.getSampleRate(), paramInfo.getChannels());
  }
  
  protected void checkResponseCode(URLConnection paramURLConnection)
    throws Exception
  {
    int i;
    if ((paramURLConnection instanceof HttpURLConnection))
    {
      paramURLConnection = (HttpURLConnection)paramURLConnection;
      i = paramURLConnection.getResponseCode();
      if (i == -1) {
        Log.w("AACPlayer", "Empty response code: " + i + " " + paramURLConnection.getResponseMessage());
      }
    }
    else
    {
      return;
    }
    if ((i < 200) || (i > 299))
    {
      Log.e("AACPlayer", "Error response code: " + i + " " + paramURLConnection.getResponseMessage());
      throw new IOException("Error response: " + i + " " + paramURLConnection.getResponseMessage());
    }
    Log.d("AACPlayer", "Response: " + i + " " + paramURLConnection.getResponseMessage());
  }
  
  protected int computeAvgKBitSecRate(Decoder.Info paramInfo)
  {
    if (this.countKBitSecRate < 64)
    {
      int i = computeKBitSecRate(paramInfo);
      int j = paramInfo.getRoundFrames();
      this.sumKBitSecRate += i * j;
      this.countKBitSecRate += j;
      this.avgKBitSecRate = (this.sumKBitSecRate / this.countKBitSecRate);
    }
    return this.avgKBitSecRate;
  }
  
  protected short[][] createDecodeBuffers(int paramInt, Decoder.Info paramInfo)
  {
    int i = PCMFeed.msToSamples(this.decodeBufferCapacityMs, paramInfo.getSampleRate(), paramInfo.getChannels());
    paramInfo = new short[paramInt][];
    paramInt = 0;
    while (paramInt < paramInfo.length)
    {
      paramInfo[paramInt] = new short[i];
      paramInt += 1;
    }
    return paramInfo;
  }
  
  protected Decoder createDecoder()
  {
    return Decoder.create();
  }
  
  protected PCMFeed createPCMFeed(Decoder.Info paramInfo)
  {
    int i = PCMFeed.msToBytes(this.audioBufferCapacityMs, paramInfo.getSampleRate(), paramInfo.getChannels());
    return new PCMFeed(paramInfo.getSampleRate(), paramInfo.getChannels(), i, this.playerCallback);
  }
  
  protected void dumpHeaders(URLConnection paramURLConnection)
  {
    if (paramURLConnection.getHeaderFields() == null)
    {
      Log.d("AACPlayer", "No headers - not an HTTP response ?");
      return;
    }
    paramURLConnection = paramURLConnection.getHeaderFields().entrySet().iterator();
    while (paramURLConnection.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)paramURLConnection.next();
      Iterator localIterator = ((List)localEntry.getValue()).iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        Log.d("AACPlayer", "header: key=" + (String)localEntry.getKey() + ", val=" + str);
      }
    }
  }
  
  public int getAudioBufferCapacityMs()
  {
    return this.audioBufferCapacityMs;
  }
  
  public int getDeclaredBitRate()
  {
    return this.declaredBitRate;
  }
  
  public int getDecodeBufferCapacityMs()
  {
    return this.decodeBufferCapacityMs;
  }
  
  public Decoder getDecoder()
  {
    return this.decoder;
  }
  
  protected InputStream getInputStream(URLConnection paramURLConnection)
    throws Exception
  {
    String str = paramURLConnection.getHeaderField("icy-metaint");
    paramURLConnection = paramURLConnection.getInputStream();
    if (!this.metadataEnabled) {
      Log.i("AACPlayer", "Metadata not enabled");
    }
    do
    {
      return paramURLConnection;
      if (str == null) {
        break label142;
      }
      int i = -1;
      try
      {
        int j = Integer.parseInt(str);
        i = j;
      }
      catch (Exception localException)
      {
        for (;;)
        {
          Log.e("AACPlayer", "The icy-metaint '" + str + "' cannot be parsed: '" + localException);
        }
      }
    } while (i <= 0);
    Log.i("AACPlayer", "The dynamic metainfo is sent every " + i + " bytes");
    return new IcyInputStream(paramURLConnection, i, this.playerCallback, this.metadataCharEnc);
    label142:
    Log.i("AACPlayer", "This stream does not provide dynamic metainfo");
    return paramURLConnection;
  }
  
  public boolean getMetadataEnabled()
  {
    return this.metadataEnabled;
  }
  
  public PlayerCallback getPlayerCallback()
  {
    return this.playerCallback;
  }
  
  public boolean getResponseCodeCheckEnabled()
  {
    return this.responseCodeCheckEnabled;
  }
  
  /* Error */
  protected URLConnection openConnection(String paramString)
    throws IOException
  {
    // Byte code:
    //   0: new 294	java/net/URL
    //   3: dup
    //   4: aload_1
    //   5: invokespecial 295	java/net/URL:<init>	(Ljava/lang/String;)V
    //   8: invokevirtual 298	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   11: astore_2
    //   12: aload_0
    //   13: aload_2
    //   14: invokevirtual 301	com/spoledge/aacdecoder/AACPlayer:prepareConnection	(Ljava/net/URLConnection;)V
    //   17: aload_2
    //   18: invokevirtual 304	java/net/URLConnection:connect	()V
    //   21: aload_2
    //   22: instanceof 107
    //   25: ifeq +275 -> 300
    //   28: aload_2
    //   29: checkcast 107	java/net/HttpURLConnection
    //   32: astore_3
    //   33: aload_3
    //   34: invokevirtual 110	java/net/HttpURLConnection:getResponseCode	()I
    //   37: iconst_m1
    //   38: if_icmpne +172 -> 210
    //   41: aload_0
    //   42: getfield 47	com/spoledge/aacdecoder/AACPlayer:responseCodeCheckEnabled	Z
    //   45: ifne +53 -> 98
    //   48: ldc 17
    //   50: new 112	java/lang/StringBuilder
    //   53: dup
    //   54: invokespecial 113	java/lang/StringBuilder:<init>	()V
    //   57: ldc_w 306
    //   60: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   63: aload_1
    //   64: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   67: invokevirtual 131	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   70: invokestatic 137	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   73: pop
    //   74: aload_2
    //   75: astore_1
    //   76: iconst_0
    //   77: ifeq +19 -> 96
    //   80: aload_2
    //   81: instanceof 107
    //   84: ifeq +10 -> 94
    //   87: aload_2
    //   88: checkcast 107	java/net/HttpURLConnection
    //   91: invokevirtual 309	java/net/HttpURLConnection:disconnect	()V
    //   94: aconst_null
    //   95: astore_1
    //   96: aload_1
    //   97: areturn
    //   98: ldc 17
    //   100: new 112	java/lang/StringBuilder
    //   103: dup
    //   104: invokespecial 113	java/lang/StringBuilder:<init>	()V
    //   107: ldc_w 311
    //   110: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   113: aload_1
    //   114: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   117: invokevirtual 131	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   120: invokestatic 137	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   123: pop
    //   124: aload_1
    //   125: ldc_w 313
    //   128: invokevirtual 317	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   131: ifeq +234 -> 365
    //   134: new 112	java/lang/StringBuilder
    //   137: dup
    //   138: invokespecial 113	java/lang/StringBuilder:<init>	()V
    //   141: ldc_w 319
    //   144: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   147: aload_1
    //   148: iconst_4
    //   149: invokevirtual 323	java/lang/String:substring	(I)Ljava/lang/String;
    //   152: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   155: invokevirtual 131	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   158: astore_3
    //   159: ldc 17
    //   161: new 112	java/lang/StringBuilder
    //   164: dup
    //   165: invokespecial 113	java/lang/StringBuilder:<init>	()V
    //   168: ldc_w 325
    //   171: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   174: aload_3
    //   175: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   178: invokevirtual 131	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   181: invokestatic 258	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   184: pop
    //   185: aload_3
    //   186: astore_1
    //   187: iconst_1
    //   188: ifeq -188 -> 0
    //   191: aload_2
    //   192: instanceof 107
    //   195: ifeq +10 -> 205
    //   198: aload_2
    //   199: checkcast 107	java/net/HttpURLConnection
    //   202: invokevirtual 309	java/net/HttpURLConnection:disconnect	()V
    //   205: aload_3
    //   206: astore_1
    //   207: goto -207 -> 0
    //   210: aload_2
    //   211: astore_1
    //   212: iconst_0
    //   213: ifeq -117 -> 96
    //   216: aload_2
    //   217: instanceof 107
    //   220: ifeq -126 -> 94
    //   223: aload_2
    //   224: checkcast 107	java/net/HttpURLConnection
    //   227: astore_1
    //   228: aload_1
    //   229: invokevirtual 309	java/net/HttpURLConnection:disconnect	()V
    //   232: goto -138 -> 94
    //   235: astore_1
    //   236: goto -142 -> 94
    //   239: astore_3
    //   240: ldc 17
    //   242: new 112	java/lang/StringBuilder
    //   245: dup
    //   246: invokespecial 113	java/lang/StringBuilder:<init>	()V
    //   249: ldc_w 327
    //   252: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   255: aload_1
    //   256: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   259: ldc_w 329
    //   262: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   265: aload_3
    //   266: invokevirtual 282	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   269: invokevirtual 131	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   272: invokestatic 137	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   275: pop
    //   276: goto -152 -> 124
    //   279: astore_1
    //   280: iconst_1
    //   281: ifeq +17 -> 298
    //   284: aload_2
    //   285: instanceof 107
    //   288: ifeq +10 -> 298
    //   291: aload_2
    //   292: checkcast 107	java/net/HttpURLConnection
    //   295: invokevirtual 309	java/net/HttpURLConnection:disconnect	()V
    //   298: aload_1
    //   299: athrow
    //   300: aload_2
    //   301: invokevirtual 197	java/net/URLConnection:getHeaderFields	()Ljava/util/Map;
    //   304: ifnonnull +32 -> 336
    //   307: ldc 17
    //   309: new 112	java/lang/StringBuilder
    //   312: dup
    //   313: invokespecial 113	java/lang/StringBuilder:<init>	()V
    //   316: ldc_w 331
    //   319: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   322: aload_1
    //   323: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   326: invokevirtual 131	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   329: invokestatic 137	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   332: pop
    //   333: goto -209 -> 124
    //   336: aload_2
    //   337: astore_1
    //   338: iconst_0
    //   339: ifeq -243 -> 96
    //   342: aload_2
    //   343: instanceof 107
    //   346: ifeq -252 -> 94
    //   349: aload_2
    //   350: checkcast 107	java/net/HttpURLConnection
    //   353: astore_1
    //   354: aload_1
    //   355: invokevirtual 309	java/net/HttpURLConnection:disconnect	()V
    //   358: goto -264 -> 94
    //   361: astore_1
    //   362: goto -268 -> 94
    //   365: new 144	java/io/IOException
    //   368: dup
    //   369: ldc_w 333
    //   372: invokespecial 149	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   375: athrow
    //   376: astore_1
    //   377: goto -283 -> 94
    //   380: astore_1
    //   381: goto -287 -> 94
    //   384: astore_1
    //   385: goto -291 -> 94
    //   388: astore_1
    //   389: goto -184 -> 205
    //   392: astore_2
    //   393: goto -95 -> 298
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	396	0	this	AACPlayer
    //   0	396	1	paramString	String
    //   11	339	2	localURLConnection	URLConnection
    //   392	1	2	localThrowable	Throwable
    //   32	174	3	localObject	Object
    //   239	27	3	localException	Exception
    // Exception table:
    //   from	to	target	type
    //   228	232	235	java/lang/Throwable
    //   33	74	239	java/lang/Exception
    //   98	124	239	java/lang/Exception
    //   21	33	279	finally
    //   33	74	279	finally
    //   98	124	279	finally
    //   124	185	279	finally
    //   240	276	279	finally
    //   300	333	279	finally
    //   365	376	279	finally
    //   354	358	361	java/lang/Throwable
    //   349	354	376	java/lang/Throwable
    //   223	228	380	java/lang/Throwable
    //   87	94	384	java/lang/Throwable
    //   198	205	388	java/lang/Throwable
    //   291	298	392	java/lang/Throwable
  }
  
  public void play(InputStream paramInputStream)
    throws Exception
  {
    play(paramInputStream, -1);
  }
  
  public final void play(InputStream paramInputStream, int paramInt)
    throws Exception
  {
    this.stopped = false;
    if (this.playerCallback != null) {
      this.playerCallback.playerStarted();
    }
    int i = paramInt;
    if (paramInt <= 0) {
      i = 64;
    }
    this.sumKBitSecRate = 0;
    this.countKBitSecRate = 0;
    playImpl(paramInputStream, i);
  }
  
  public void play(String paramString)
    throws Exception
  {
    play(paramString, -1);
  }
  
  /* Error */
  public void play(String paramString, int paramInt)
    throws Exception
  {
    // Byte code:
    //   0: aload_0
    //   1: iconst_m1
    //   2: putfield 49	com/spoledge/aacdecoder/AACPlayer:declaredBitRate	I
    //   5: aload_1
    //   6: bipush 58
    //   8: invokevirtual 355	java/lang/String:indexOf	(I)I
    //   11: ifle +113 -> 124
    //   14: aload_0
    //   15: aload_1
    //   16: invokevirtual 357	com/spoledge/aacdecoder/AACPlayer:openConnection	(Ljava/lang/String;)Ljava/net/URLConnection;
    //   19: astore 4
    //   21: aconst_null
    //   22: astore_3
    //   23: aload_3
    //   24: astore_1
    //   25: aload_0
    //   26: getfield 47	com/spoledge/aacdecoder/AACPlayer:responseCodeCheckEnabled	Z
    //   29: ifeq +11 -> 40
    //   32: aload_3
    //   33: astore_1
    //   34: aload_0
    //   35: aload 4
    //   37: invokevirtual 359	com/spoledge/aacdecoder/AACPlayer:checkResponseCode	(Ljava/net/URLConnection;)V
    //   40: aload_3
    //   41: astore_1
    //   42: aload_0
    //   43: aload 4
    //   45: invokevirtual 362	com/spoledge/aacdecoder/AACPlayer:processHeaders	(Ljava/net/URLConnection;)V
    //   48: aload_3
    //   49: astore_1
    //   50: aload_0
    //   51: aload 4
    //   53: invokevirtual 364	com/spoledge/aacdecoder/AACPlayer:getInputStream	(Ljava/net/URLConnection;)Ljava/io/InputStream;
    //   56: astore_3
    //   57: iload_2
    //   58: iconst_m1
    //   59: if_icmpeq +32 -> 91
    //   62: aload_3
    //   63: astore_1
    //   64: aload_0
    //   65: aload_3
    //   66: iload_2
    //   67: invokevirtual 338	com/spoledge/aacdecoder/AACPlayer:play	(Ljava/io/InputStream;I)V
    //   70: aload_3
    //   71: invokevirtual 369	java/io/InputStream:close	()V
    //   74: aload 4
    //   76: instanceof 107
    //   79: ifeq +11 -> 90
    //   82: aload 4
    //   84: checkcast 107	java/net/HttpURLConnection
    //   87: invokevirtual 309	java/net/HttpURLConnection:disconnect	()V
    //   90: return
    //   91: aload_3
    //   92: astore_1
    //   93: aload_0
    //   94: getfield 49	com/spoledge/aacdecoder/AACPlayer:declaredBitRate	I
    //   97: istore_2
    //   98: goto -36 -> 62
    //   101: astore_3
    //   102: aload_1
    //   103: invokevirtual 369	java/io/InputStream:close	()V
    //   106: aload 4
    //   108: instanceof 107
    //   111: ifeq +11 -> 122
    //   114: aload 4
    //   116: checkcast 107	java/net/HttpURLConnection
    //   119: invokevirtual 309	java/net/HttpURLConnection:disconnect	()V
    //   122: aload_3
    //   123: athrow
    //   124: aload_0
    //   125: aload_1
    //   126: invokevirtual 372	com/spoledge/aacdecoder/AACPlayer:processFileType	(Ljava/lang/String;)V
    //   129: new 374	java/io/FileInputStream
    //   132: dup
    //   133: aload_1
    //   134: invokespecial 375	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   137: astore_3
    //   138: aload_0
    //   139: aload_3
    //   140: iload_2
    //   141: invokevirtual 338	com/spoledge/aacdecoder/AACPlayer:play	(Ljava/io/InputStream;I)V
    //   144: aload_3
    //   145: invokevirtual 369	java/io/InputStream:close	()V
    //   148: return
    //   149: astore_1
    //   150: return
    //   151: astore_1
    //   152: aload_3
    //   153: invokevirtual 369	java/io/InputStream:close	()V
    //   156: aload_1
    //   157: athrow
    //   158: astore_1
    //   159: goto -85 -> 74
    //   162: astore_1
    //   163: goto -57 -> 106
    //   166: astore_3
    //   167: goto -11 -> 156
    //   170: astore_1
    //   171: goto -49 -> 122
    //   174: astore_1
    //   175: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	176	0	this	AACPlayer
    //   0	176	1	paramString	String
    //   0	176	2	paramInt	int
    //   22	70	3	localInputStream	InputStream
    //   101	22	3	localObject	Object
    //   137	16	3	localFileInputStream	java.io.FileInputStream
    //   166	1	3	localThrowable	Throwable
    //   19	96	4	localURLConnection	URLConnection
    // Exception table:
    //   from	to	target	type
    //   25	32	101	finally
    //   34	40	101	finally
    //   42	48	101	finally
    //   50	57	101	finally
    //   64	70	101	finally
    //   93	98	101	finally
    //   144	148	149	java/lang/Throwable
    //   138	144	151	finally
    //   70	74	158	java/lang/Throwable
    //   102	106	162	java/lang/Throwable
    //   152	156	166	java/lang/Throwable
    //   114	122	170	java/lang/Throwable
    //   82	90	174	java/lang/Throwable
  }
  
  public void playAsync(String paramString)
  {
    playAsync(paramString, -1);
  }
  
  public void playAsync(final String paramString, final int paramInt)
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        try
        {
          AACPlayer.this.play(paramString, paramInt);
          return;
        }
        catch (Exception localException)
        {
          do
          {
            Log.e("AACPlayer", "playAsync():", localException);
          } while (AACPlayer.this.playerCallback == null);
          AACPlayer.this.playerCallback.playerException(localException);
        }
      }
    }).start();
  }
  
  protected void playImpl(InputStream paramInputStream, int paramInt)
    throws Exception
  {
    BufferReader localBufferReader = new BufferReader(computeInputBufferSize(paramInt, this.decodeBufferCapacityMs), paramInputStream);
    new Thread(localBufferReader).start();
    Object localObject1 = null;
    Decoder.Info localInfo1 = null;
    long l3 = 0L;
    long l6 = 0L;
    long l2 = 0L;
    int i1 = 0;
    int i2 = 0;
    int i = 0;
    paramInputStream = (InputStream)localObject1;
    long l1 = l2;
    Decoder.Info localInfo2;
    long l7;
    try
    {
      localInfo2 = this.decoder.start(localBufferReader);
      paramInputStream = (InputStream)localObject1;
      l1 = l2;
      Log.d("AACPlayer", "play(): samplerate=" + localInfo2.getSampleRate() + ", channels=" + localInfo2.getChannels());
      paramInputStream = (InputStream)localObject1;
      l1 = l2;
      l7 = localInfo2.getSampleRate() * localInfo2.getChannels();
      paramInputStream = (InputStream)localObject1;
      l1 = l7;
      if (localInfo2.getChannels() > 2)
      {
        paramInputStream = (InputStream)localObject1;
        l1 = l7;
        throw new RuntimeException("Too many channels detected: " + localInfo2.getChannels());
      }
    }
    finally
    {
      l7 = l1;
      l2 = l3;
      localObject1 = localInfo1;
    }
    for (;;)
    {
      boolean bool = this.stopped;
      this.stopped = true;
      short[][] arrayOfShort1;
      short[] arrayOfShort;
      int n;
      Object localObject3;
      if (paramInputStream != null)
      {
        if (!bool)
        {
          bool = true;
          paramInputStream.stop(bool);
        }
      }
      else
      {
        this.decoder.stop();
        localBufferReader.stop();
        paramInt = 0;
        if (i > 0) {
          Log.i("AACPlayer", "play(): average decoding time: " + l2 / i + " ms");
        }
        if (l2 > 0L)
        {
          paramInt = (int)((1000L * l6 / l2 - l7) * 100L / l7);
          Log.i("AACPlayer", "play(): average rate (samples/sec): audio=" + l7 + ", decoding=" + 1000L * l6 / l2 + ", audio/decoding= " + paramInt + " %  (the higher, the better; negative means that decoding is slower than needed by audio)");
        }
        if (localObject1 != null) {
          ((Thread)localObject1).join();
        }
        if (this.playerCallback != null) {
          this.playerCallback.playerStopped(paramInt);
        }
        throw ((Throwable)localObject2);
        paramInputStream = (InputStream)localObject1;
        l1 = l7;
        arrayOfShort1 = createDecodeBuffers(3, localInfo2);
        arrayOfShort = arrayOfShort1[0];
        n = 0;
        paramInputStream = (InputStream)localObject1;
        l1 = l7;
        localObject1 = createPCMFeed(localInfo2);
        paramInputStream = (InputStream)localObject1;
        l1 = l7;
        localObject3 = new Thread((Runnable)localObject1);
        i = i2;
        l2 = l3;
        l1 = l6;
      }
      try
      {
        ((Thread)localObject3).start();
        paramInputStream = arrayOfShort;
        int k = n;
        int j = i1;
        long l4 = l3;
        long l5 = l6;
        int m = paramInt;
        i = i2;
        l2 = l3;
        l1 = l6;
        if (localInfo2.getFirstSamples() != null)
        {
          i = i2;
          l2 = l3;
          l1 = l6;
          paramInputStream = localInfo2.getFirstSamples();
          i = i2;
          l2 = l3;
          l1 = l6;
          Log.d("AACPlayer", "First samples length: " + paramInputStream.length);
          i = i2;
          l2 = l3;
          l1 = l6;
          ((PCMFeed)localObject1).feed(paramInputStream, paramInputStream.length);
          i = i2;
          l2 = l3;
          l1 = l6;
          localInfo2.setFirstSamples(null);
          m = paramInt;
          l5 = l6;
          l4 = l3;
          j = i1;
          k = n;
          paramInputStream = arrayOfShort;
        }
        label643:
        i = j;
        l2 = l4;
        l1 = l5;
        l3 = System.currentTimeMillis();
        i = j;
        l2 = l4;
        l1 = l5;
        localInfo1 = this.decoder.decode(paramInputStream, paramInputStream.length);
        i = j;
        l2 = l4;
        l1 = l5;
        n = localInfo1.getRoundSamples();
        i = j;
        l2 = l4;
        l1 = l5;
        l3 = l4 + (System.currentTimeMillis() - l3);
        l6 = l5 + n;
        paramInt = j + 1;
        i = paramInt;
        l2 = l3;
        l1 = l6;
        Log.d("AACPlayer", "play(): decoded " + n + " samples");
        if (n != 0)
        {
          i = paramInt;
          l2 = l3;
          l1 = l6;
          bool = this.stopped;
          if (!bool) {}
        }
        else
        {
          label804:
          bool = this.stopped;
          this.stopped = true;
          if (localObject1 != null) {
            if (bool) {
              break label1225;
            }
          }
        }
        label1225:
        for (bool = true;; bool = false)
        {
          ((PCMFeed)localObject1).stop(bool);
          this.decoder.stop();
          localBufferReader.stop();
          i = 0;
          if (paramInt > 0) {
            Log.i("AACPlayer", "play(): average decoding time: " + l3 / paramInt + " ms");
          }
          paramInt = i;
          if (l3 > 0L)
          {
            paramInt = (int)((1000L * l6 / l3 - l7) * 100L / l7);
            Log.i("AACPlayer", "play(): average rate (samples/sec): audio=" + l7 + ", decoding=" + 1000L * l6 / l3 + ", audio/decoding= " + paramInt + " %  (the higher, the better; negative means that decoding is slower than needed by audio)");
          }
          if (localObject3 != null) {
            ((Thread)localObject3).join();
          }
          if (this.playerCallback != null) {
            this.playerCallback.playerStopped(paramInt);
          }
          return;
          i = paramInt;
          l2 = l3;
          l1 = l6;
          if (!((PCMFeed)localObject1).feed(paramInputStream, n)) {
            break label804;
          }
          i = paramInt;
          l2 = l3;
          l1 = l6;
          if (this.stopped) {
            break label804;
          }
          i = paramInt;
          l2 = l3;
          l1 = l6;
          j = computeAvgKBitSecRate(localInfo1);
          n = m;
          i = paramInt;
          l2 = l3;
          l1 = l6;
          if (Math.abs(m - j) > 1)
          {
            i = paramInt;
            l2 = l3;
            l1 = l6;
            Log.i("AACPlayer", "play(): changing kBitSecRate: " + m + " -> " + j);
            i = paramInt;
            l2 = l3;
            l1 = l6;
            localBufferReader.setCapacity(computeInputBufferSize(j, this.decodeBufferCapacityMs));
            n = j;
          }
          k += 1;
          paramInputStream = arrayOfShort1[(k % 3)];
          i = paramInt;
          l2 = l3;
          l1 = l6;
          bool = this.stopped;
          j = paramInt;
          l4 = l3;
          l5 = l6;
          m = n;
          if (!bool) {
            break label643;
          }
          break label804;
          bool = false;
          break;
        }
      }
      finally
      {
        paramInputStream = (InputStream)localObject1;
        localObject1 = localObject3;
        l6 = l1;
        localObject3 = localObject4;
      }
    }
  }
  
  protected void prepareConnection(URLConnection paramURLConnection)
  {
    if (this.metadataEnabled) {
      paramURLConnection.setRequestProperty("Icy-MetaData", "1");
    }
  }
  
  protected void processFileType(String paramString) {}
  
  protected void processHeaders(URLConnection paramURLConnection)
  {
    dumpHeaders(paramURLConnection);
    Object localObject = paramURLConnection.getHeaderField("icy-br");
    if (localObject != null) {}
    try
    {
      this.declaredBitRate = Integer.parseInt((String)localObject);
      if (this.declaredBitRate > 7) {
        Log.d("AACPlayer", "Declared bitrate is " + this.declaredBitRate + " kb/s");
      }
      while (this.playerCallback != null)
      {
        paramURLConnection = paramURLConnection.getHeaderFields().entrySet().iterator();
        while (paramURLConnection.hasNext())
        {
          localObject = (Map.Entry)paramURLConnection.next();
          Iterator localIterator = ((List)((Map.Entry)localObject).getValue()).iterator();
          while (localIterator.hasNext())
          {
            String str = (String)localIterator.next();
            this.playerCallback.playerMetadata((String)((Map.Entry)localObject).getKey(), str);
          }
        }
        Log.w("AACPlayer", "Declared bitrate is too low - ignoring: " + this.declaredBitRate + " kb/s");
        this.declaredBitRate = -1;
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Log.w("AACPlayer", "Cannot parse declared bit-rate '" + (String)localObject + "'");
      }
    }
  }
  
  public void setAudioBufferCapacityMs(int paramInt)
  {
    this.audioBufferCapacityMs = paramInt;
  }
  
  public void setDecodeBufferCapacityMs(int paramInt)
  {
    this.decodeBufferCapacityMs = paramInt;
  }
  
  public void setDecoder(Decoder paramDecoder)
  {
    this.decoder = paramDecoder;
  }
  
  public void setMetadataCharEnc(String paramString)
  {
    this.metadataCharEnc = paramString;
  }
  
  public void setMetadataEnabled(boolean paramBoolean)
  {
    this.metadataEnabled = paramBoolean;
  }
  
  public void setPlayerCallback(PlayerCallback paramPlayerCallback)
  {
    this.playerCallback = paramPlayerCallback;
  }
  
  public void setResponseCodeCheckEnabled(boolean paramBoolean)
  {
    this.responseCodeCheckEnabled = paramBoolean;
  }
  
  public void stop()
  {
    this.stopped = true;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\spoledge\aacdecoder\AACPlayer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */