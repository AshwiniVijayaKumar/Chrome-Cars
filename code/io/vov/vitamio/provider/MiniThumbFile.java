package io.vov.vitamio.provider;

import android.net.Uri;
import android.os.Environment;
import io.vov.vitamio.utils.Log;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Hashtable;
import java.util.List;

public class MiniThumbFile
{
  protected static final int BYTES_PER_MINTHUMB = 10000;
  private static final int HEADER_SIZE = 13;
  private static final int MINI_THUMB_DATA_FILE_VERSION = 7;
  private static Hashtable<String, MiniThumbFile> sThumbFiles = new Hashtable();
  private ByteBuffer mBuffer;
  private FileChannel mChannel;
  private RandomAccessFile mMiniThumbFile;
  private Uri mUri;
  
  public MiniThumbFile(Uri paramUri)
  {
    this.mUri = paramUri;
    this.mBuffer = ByteBuffer.allocateDirect(10000);
  }
  
  protected static MiniThumbFile instance(Uri paramUri)
  {
    try
    {
      String str = (String)paramUri.getPathSegments().get(0);
      MiniThumbFile localMiniThumbFile = (MiniThumbFile)sThumbFiles.get(str);
      paramUri = localMiniThumbFile;
      if (localMiniThumbFile == null)
      {
        paramUri = new MiniThumbFile(Uri.parse("content://me.abitno.vplayer.mediaprovider/" + str + "/media"));
        sThumbFiles.put(str, paramUri);
      }
      return paramUri;
    }
    finally {}
  }
  
  private RandomAccessFile miniThumbDataFile()
  {
    Object localObject;
    if (this.mMiniThumbFile == null)
    {
      removeOldFile();
      localObject = randomAccessFilePath(7);
      File localFile = new File((String)localObject).getParentFile();
      if ((!localFile.isDirectory()) && (!localFile.mkdirs())) {
        Log.e("Unable to create .thumbnails directory %s", new Object[] { localFile.toString() });
      }
      localObject = new File((String)localObject);
    }
    try
    {
      this.mMiniThumbFile = new RandomAccessFile((File)localObject, "rw");
      if (this.mMiniThumbFile != null) {
        this.mChannel = this.mMiniThumbFile.getChannel();
      }
      return this.mMiniThumbFile;
    }
    catch (IOException localIOException2)
    {
      for (;;)
      {
        try
        {
          this.mMiniThumbFile = new RandomAccessFile((File)localObject, "r");
        }
        catch (IOException localIOException1) {}
      }
    }
  }
  
  private String randomAccessFilePath(int paramInt)
  {
    return new StringBuilder(String.valueOf(Environment.getExternalStorageDirectory().toString())).append("/").append("Android/data/me.abitno.vplayer.t/thumbnails").toString() + "/.thumbdata" + paramInt + "-" + this.mUri.hashCode();
  }
  
  private void removeOldFile()
  {
    File localFile = new File(randomAccessFilePath(6));
    if (localFile.exists()) {}
    try
    {
      localFile.delete();
      return;
    }
    catch (SecurityException localSecurityException) {}
  }
  
  /* Error */
  protected static void reset()
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 31	io/vov/vitamio/provider/MiniThumbFile:sThumbFiles	Ljava/util/Hashtable;
    //   6: invokevirtual 178	java/util/Hashtable:values	()Ljava/util/Collection;
    //   9: invokeinterface 184 1 0
    //   14: astore_0
    //   15: aload_0
    //   16: invokeinterface 189 1 0
    //   21: ifne +13 -> 34
    //   24: getstatic 31	io/vov/vitamio/provider/MiniThumbFile:sThumbFiles	Ljava/util/Hashtable;
    //   27: invokevirtual 192	java/util/Hashtable:clear	()V
    //   30: ldc 2
    //   32: monitorexit
    //   33: return
    //   34: aload_0
    //   35: invokeinterface 196 1 0
    //   40: checkcast 2	io/vov/vitamio/provider/MiniThumbFile
    //   43: invokevirtual 199	io/vov/vitamio/provider/MiniThumbFile:deactivate	()V
    //   46: goto -31 -> 15
    //   49: astore_0
    //   50: ldc 2
    //   52: monitorexit
    //   53: aload_0
    //   54: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   14	21	0	localIterator	java.util.Iterator
    //   49	5	0	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   3	15	49	finally
    //   15	30	49	finally
    //   34	46	49	finally
  }
  
  /* Error */
  protected void deactivate()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 96	io/vov/vitamio/provider/MiniThumbFile:mMiniThumbFile	Ljava/io/RandomAccessFile;
    //   6: astore_1
    //   7: aload_1
    //   8: ifnull +15 -> 23
    //   11: aload_0
    //   12: getfield 96	io/vov/vitamio/provider/MiniThumbFile:mMiniThumbFile	Ljava/io/RandomAccessFile;
    //   15: invokevirtual 202	java/io/RandomAccessFile:close	()V
    //   18: aload_0
    //   19: aconst_null
    //   20: putfield 96	io/vov/vitamio/provider/MiniThumbFile:mMiniThumbFile	Ljava/io/RandomAccessFile;
    //   23: aload_0
    //   24: monitorexit
    //   25: return
    //   26: astore_1
    //   27: aload_0
    //   28: monitorexit
    //   29: aload_1
    //   30: athrow
    //   31: astore_1
    //   32: goto -9 -> 23
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	35	0	this	MiniThumbFile
    //   6	2	1	localRandomAccessFile	RandomAccessFile
    //   26	4	1	localObject	Object
    //   31	1	1	localIOException	IOException
    // Exception table:
    //   from	to	target	type
    //   2	7	26	finally
    //   11	23	26	finally
    //   11	23	31	java/io/IOException
  }
  
  /* Error */
  protected long getMagic(long paramLong)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokespecial 208	io/vov/vitamio/provider/MiniThumbFile:miniThumbDataFile	()Ljava/io/RandomAccessFile;
    //   6: astore 5
    //   8: aload 5
    //   10: ifnull +222 -> 232
    //   13: lload_1
    //   14: ldc2_w 209
    //   17: lmul
    //   18: lstore_3
    //   19: aconst_null
    //   20: astore 9
    //   22: aconst_null
    //   23: astore 10
    //   25: aconst_null
    //   26: astore 8
    //   28: aload 8
    //   30: astore 6
    //   32: aload 9
    //   34: astore 7
    //   36: aload 10
    //   38: astore 5
    //   40: aload_0
    //   41: getfield 44	io/vov/vitamio/provider/MiniThumbFile:mBuffer	Ljava/nio/ByteBuffer;
    //   44: invokevirtual 213	java/nio/ByteBuffer:clear	()Ljava/nio/Buffer;
    //   47: pop
    //   48: aload 8
    //   50: astore 6
    //   52: aload 9
    //   54: astore 7
    //   56: aload 10
    //   58: astore 5
    //   60: aload_0
    //   61: getfield 44	io/vov/vitamio/provider/MiniThumbFile:mBuffer	Ljava/nio/ByteBuffer;
    //   64: bipush 9
    //   66: invokevirtual 217	java/nio/ByteBuffer:limit	(I)Ljava/nio/Buffer;
    //   69: pop
    //   70: aload 8
    //   72: astore 6
    //   74: aload 9
    //   76: astore 7
    //   78: aload 10
    //   80: astore 5
    //   82: aload_0
    //   83: getfield 139	io/vov/vitamio/provider/MiniThumbFile:mChannel	Ljava/nio/channels/FileChannel;
    //   86: lload_3
    //   87: ldc2_w 218
    //   90: iconst_1
    //   91: invokevirtual 225	java/nio/channels/FileChannel:lock	(JJZ)Ljava/nio/channels/FileLock;
    //   94: astore 8
    //   96: aload 8
    //   98: astore 6
    //   100: aload 8
    //   102: astore 7
    //   104: aload 8
    //   106: astore 5
    //   108: aload_0
    //   109: getfield 139	io/vov/vitamio/provider/MiniThumbFile:mChannel	Ljava/nio/channels/FileChannel;
    //   112: aload_0
    //   113: getfield 44	io/vov/vitamio/provider/MiniThumbFile:mBuffer	Ljava/nio/ByteBuffer;
    //   116: lload_3
    //   117: invokevirtual 229	java/nio/channels/FileChannel:read	(Ljava/nio/ByteBuffer;J)I
    //   120: bipush 9
    //   122: if_icmpne +188 -> 310
    //   125: aload 8
    //   127: astore 6
    //   129: aload 8
    //   131: astore 7
    //   133: aload 8
    //   135: astore 5
    //   137: aload_0
    //   138: getfield 44	io/vov/vitamio/provider/MiniThumbFile:mBuffer	Ljava/nio/ByteBuffer;
    //   141: iconst_0
    //   142: invokevirtual 232	java/nio/ByteBuffer:position	(I)Ljava/nio/Buffer;
    //   145: pop
    //   146: aload 8
    //   148: astore 6
    //   150: aload 8
    //   152: astore 7
    //   154: aload 8
    //   156: astore 5
    //   158: aload_0
    //   159: getfield 44	io/vov/vitamio/provider/MiniThumbFile:mBuffer	Ljava/nio/ByteBuffer;
    //   162: invokevirtual 235	java/nio/ByteBuffer:get	()B
    //   165: iconst_1
    //   166: if_icmpne +144 -> 310
    //   169: aload 8
    //   171: astore 6
    //   173: aload 8
    //   175: astore 7
    //   177: aload 8
    //   179: astore 5
    //   181: aload_0
    //   182: getfield 44	io/vov/vitamio/provider/MiniThumbFile:mBuffer	Ljava/nio/ByteBuffer;
    //   185: invokevirtual 239	java/nio/ByteBuffer:getLong	()J
    //   188: lstore_3
    //   189: lload_3
    //   190: lstore_1
    //   191: lload_1
    //   192: lstore_3
    //   193: aload 8
    //   195: ifnull +10 -> 205
    //   198: aload 8
    //   200: invokevirtual 244	java/nio/channels/FileLock:release	()V
    //   203: lload_1
    //   204: lstore_3
    //   205: aload_0
    //   206: monitorexit
    //   207: lload_3
    //   208: lreturn
    //   209: astore 7
    //   211: aload 6
    //   213: astore 5
    //   215: ldc -10
    //   217: aload 7
    //   219: invokestatic 249	io/vov/vitamio/utils/Log:e	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   222: aload 6
    //   224: ifnull +8 -> 232
    //   227: aload 6
    //   229: invokevirtual 244	java/nio/channels/FileLock:release	()V
    //   232: lconst_0
    //   233: lstore_3
    //   234: goto -29 -> 205
    //   237: astore 6
    //   239: aload 7
    //   241: astore 5
    //   243: ldc -5
    //   245: iconst_2
    //   246: anewarray 4	java/lang/Object
    //   249: dup
    //   250: iconst_0
    //   251: lload_1
    //   252: invokestatic 256	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   255: aastore
    //   256: dup
    //   257: iconst_1
    //   258: aload 6
    //   260: invokevirtual 260	java/lang/Object:getClass	()Ljava/lang/Class;
    //   263: invokevirtual 263	java/lang/Class:toString	()Ljava/lang/String;
    //   266: aastore
    //   267: invokestatic 126	io/vov/vitamio/utils/Log:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   270: aload 7
    //   272: ifnull -40 -> 232
    //   275: aload 7
    //   277: invokevirtual 244	java/nio/channels/FileLock:release	()V
    //   280: goto -48 -> 232
    //   283: astore 5
    //   285: goto -53 -> 232
    //   288: astore 6
    //   290: aload 5
    //   292: ifnull +8 -> 300
    //   295: aload 5
    //   297: invokevirtual 244	java/nio/channels/FileLock:release	()V
    //   300: aload 6
    //   302: athrow
    //   303: astore 5
    //   305: aload_0
    //   306: monitorexit
    //   307: aload 5
    //   309: athrow
    //   310: aload 8
    //   312: ifnull -80 -> 232
    //   315: aload 8
    //   317: invokevirtual 244	java/nio/channels/FileLock:release	()V
    //   320: goto -88 -> 232
    //   323: astore 5
    //   325: goto -93 -> 232
    //   328: astore 5
    //   330: lload_1
    //   331: lstore_3
    //   332: goto -127 -> 205
    //   335: astore 5
    //   337: goto -105 -> 232
    //   340: astore 5
    //   342: goto -42 -> 300
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	345	0	this	MiniThumbFile
    //   0	345	1	paramLong	long
    //   18	314	3	l	long
    //   6	236	5	localObject1	Object
    //   283	13	5	localIOException1	IOException
    //   303	5	5	localObject2	Object
    //   323	1	5	localIOException2	IOException
    //   328	1	5	localIOException3	IOException
    //   335	1	5	localIOException4	IOException
    //   340	1	5	localIOException5	IOException
    //   30	198	6	localFileLock1	java.nio.channels.FileLock
    //   237	22	6	localRuntimeException	RuntimeException
    //   288	13	6	localObject3	Object
    //   34	142	7	localObject4	Object
    //   209	67	7	localIOException6	IOException
    //   26	290	8	localFileLock2	java.nio.channels.FileLock
    //   20	55	9	localObject5	Object
    //   23	56	10	localObject6	Object
    // Exception table:
    //   from	to	target	type
    //   40	48	209	java/io/IOException
    //   60	70	209	java/io/IOException
    //   82	96	209	java/io/IOException
    //   108	125	209	java/io/IOException
    //   137	146	209	java/io/IOException
    //   158	169	209	java/io/IOException
    //   181	189	209	java/io/IOException
    //   40	48	237	java/lang/RuntimeException
    //   60	70	237	java/lang/RuntimeException
    //   82	96	237	java/lang/RuntimeException
    //   108	125	237	java/lang/RuntimeException
    //   137	146	237	java/lang/RuntimeException
    //   158	169	237	java/lang/RuntimeException
    //   181	189	237	java/lang/RuntimeException
    //   275	280	283	java/io/IOException
    //   40	48	288	finally
    //   60	70	288	finally
    //   82	96	288	finally
    //   108	125	288	finally
    //   137	146	288	finally
    //   158	169	288	finally
    //   181	189	288	finally
    //   215	222	288	finally
    //   243	270	288	finally
    //   2	8	303	finally
    //   198	203	303	finally
    //   227	232	303	finally
    //   275	280	303	finally
    //   295	300	303	finally
    //   300	303	303	finally
    //   315	320	303	finally
    //   315	320	323	java/io/IOException
    //   198	203	328	java/io/IOException
    //   227	232	335	java/io/IOException
    //   295	300	340	java/io/IOException
  }
  
  /* Error */
  protected byte[] getMiniThumbFromFile(long paramLong, byte[] paramArrayOfByte)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokespecial 208	io/vov/vitamio/provider/MiniThumbFile:miniThumbDataFile	()Ljava/io/RandomAccessFile;
    //   6: astore 8
    //   8: aload 8
    //   10: ifnonnull +11 -> 21
    //   13: aconst_null
    //   14: astore 8
    //   16: aload_0
    //   17: monitorexit
    //   18: aload 8
    //   20: areturn
    //   21: lload_1
    //   22: ldc2_w 209
    //   25: lmul
    //   26: lstore 6
    //   28: aconst_null
    //   29: astore 12
    //   31: aconst_null
    //   32: astore 13
    //   34: aconst_null
    //   35: astore 11
    //   37: aload 11
    //   39: astore 9
    //   41: aload 12
    //   43: astore 10
    //   45: aload 13
    //   47: astore 8
    //   49: aload_0
    //   50: getfield 44	io/vov/vitamio/provider/MiniThumbFile:mBuffer	Ljava/nio/ByteBuffer;
    //   53: invokevirtual 213	java/nio/ByteBuffer:clear	()Ljava/nio/Buffer;
    //   56: pop
    //   57: aload 11
    //   59: astore 9
    //   61: aload 12
    //   63: astore 10
    //   65: aload 13
    //   67: astore 8
    //   69: aload_0
    //   70: getfield 139	io/vov/vitamio/provider/MiniThumbFile:mChannel	Ljava/nio/channels/FileChannel;
    //   73: lload 6
    //   75: ldc2_w 209
    //   78: iconst_1
    //   79: invokevirtual 225	java/nio/channels/FileChannel:lock	(JJZ)Ljava/nio/channels/FileLock;
    //   82: astore 11
    //   84: aload 11
    //   86: astore 9
    //   88: aload 11
    //   90: astore 10
    //   92: aload 11
    //   94: astore 8
    //   96: aload_0
    //   97: getfield 139	io/vov/vitamio/provider/MiniThumbFile:mChannel	Ljava/nio/channels/FileChannel;
    //   100: aload_0
    //   101: getfield 44	io/vov/vitamio/provider/MiniThumbFile:mBuffer	Ljava/nio/ByteBuffer;
    //   104: lload 6
    //   106: invokevirtual 229	java/nio/channels/FileChannel:read	(Ljava/nio/ByteBuffer;J)I
    //   109: istore 4
    //   111: iload 4
    //   113: bipush 13
    //   115: if_icmple +238 -> 353
    //   118: aload 11
    //   120: astore 9
    //   122: aload 11
    //   124: astore 10
    //   126: aload 11
    //   128: astore 8
    //   130: aload_0
    //   131: getfield 44	io/vov/vitamio/provider/MiniThumbFile:mBuffer	Ljava/nio/ByteBuffer;
    //   134: bipush 9
    //   136: invokevirtual 232	java/nio/ByteBuffer:position	(I)Ljava/nio/Buffer;
    //   139: pop
    //   140: aload 11
    //   142: astore 9
    //   144: aload 11
    //   146: astore 10
    //   148: aload 11
    //   150: astore 8
    //   152: aload_0
    //   153: getfield 44	io/vov/vitamio/provider/MiniThumbFile:mBuffer	Ljava/nio/ByteBuffer;
    //   156: invokevirtual 268	java/nio/ByteBuffer:getInt	()I
    //   159: istore 5
    //   161: iload 4
    //   163: iload 5
    //   165: bipush 13
    //   167: iadd
    //   168: if_icmplt +185 -> 353
    //   171: aload 11
    //   173: astore 9
    //   175: aload 11
    //   177: astore 10
    //   179: aload 11
    //   181: astore 8
    //   183: aload_3
    //   184: arraylength
    //   185: iload 5
    //   187: if_icmplt +166 -> 353
    //   190: aload 11
    //   192: astore 9
    //   194: aload 11
    //   196: astore 10
    //   198: aload 11
    //   200: astore 8
    //   202: aload_0
    //   203: getfield 44	io/vov/vitamio/provider/MiniThumbFile:mBuffer	Ljava/nio/ByteBuffer;
    //   206: aload_3
    //   207: iconst_0
    //   208: iload 5
    //   210: invokevirtual 271	java/nio/ByteBuffer:get	([BII)Ljava/nio/ByteBuffer;
    //   213: pop
    //   214: aload_3
    //   215: astore 8
    //   217: aload 11
    //   219: ifnull -203 -> 16
    //   222: aload 11
    //   224: invokevirtual 244	java/nio/channels/FileLock:release	()V
    //   227: aload_3
    //   228: astore 8
    //   230: goto -214 -> 16
    //   233: astore 8
    //   235: aload_3
    //   236: astore 8
    //   238: goto -222 -> 16
    //   241: astore_3
    //   242: aload 9
    //   244: astore 8
    //   246: ldc_w 273
    //   249: iconst_2
    //   250: anewarray 4	java/lang/Object
    //   253: dup
    //   254: iconst_0
    //   255: lload_1
    //   256: invokestatic 256	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   259: aastore
    //   260: dup
    //   261: iconst_1
    //   262: aload_3
    //   263: invokevirtual 276	java/io/IOException:getMessage	()Ljava/lang/String;
    //   266: aastore
    //   267: invokestatic 126	io/vov/vitamio/utils/Log:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   270: aload 9
    //   272: ifnull +8 -> 280
    //   275: aload 9
    //   277: invokevirtual 244	java/nio/channels/FileLock:release	()V
    //   280: aconst_null
    //   281: astore 8
    //   283: goto -267 -> 16
    //   286: astore_3
    //   287: aload 10
    //   289: astore 8
    //   291: ldc_w 278
    //   294: iconst_2
    //   295: anewarray 4	java/lang/Object
    //   298: dup
    //   299: iconst_0
    //   300: lload_1
    //   301: invokestatic 256	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   304: aastore
    //   305: dup
    //   306: iconst_1
    //   307: aload_3
    //   308: invokevirtual 260	java/lang/Object:getClass	()Ljava/lang/Class;
    //   311: invokevirtual 263	java/lang/Class:toString	()Ljava/lang/String;
    //   314: aastore
    //   315: invokestatic 126	io/vov/vitamio/utils/Log:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   318: aload 10
    //   320: ifnull -40 -> 280
    //   323: aload 10
    //   325: invokevirtual 244	java/nio/channels/FileLock:release	()V
    //   328: goto -48 -> 280
    //   331: astore_3
    //   332: goto -52 -> 280
    //   335: astore_3
    //   336: aload 8
    //   338: ifnull +8 -> 346
    //   341: aload 8
    //   343: invokevirtual 244	java/nio/channels/FileLock:release	()V
    //   346: aload_3
    //   347: athrow
    //   348: astore_3
    //   349: aload_0
    //   350: monitorexit
    //   351: aload_3
    //   352: athrow
    //   353: aload 11
    //   355: ifnull -75 -> 280
    //   358: aload 11
    //   360: invokevirtual 244	java/nio/channels/FileLock:release	()V
    //   363: goto -83 -> 280
    //   366: astore_3
    //   367: goto -87 -> 280
    //   370: astore_3
    //   371: goto -91 -> 280
    //   374: astore 8
    //   376: goto -30 -> 346
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	379	0	this	MiniThumbFile
    //   0	379	1	paramLong	long
    //   0	379	3	paramArrayOfByte	byte[]
    //   109	60	4	i	int
    //   159	50	5	j	int
    //   26	79	6	l	long
    //   6	223	8	localObject1	Object
    //   233	1	8	localIOException1	IOException
    //   236	106	8	localObject2	Object
    //   374	1	8	localIOException2	IOException
    //   39	237	9	localFileLock1	java.nio.channels.FileLock
    //   43	281	10	localObject3	Object
    //   35	324	11	localFileLock2	java.nio.channels.FileLock
    //   29	33	12	localObject4	Object
    //   32	34	13	localObject5	Object
    // Exception table:
    //   from	to	target	type
    //   222	227	233	java/io/IOException
    //   49	57	241	java/io/IOException
    //   69	84	241	java/io/IOException
    //   96	111	241	java/io/IOException
    //   130	140	241	java/io/IOException
    //   152	161	241	java/io/IOException
    //   183	190	241	java/io/IOException
    //   202	214	241	java/io/IOException
    //   49	57	286	java/lang/RuntimeException
    //   69	84	286	java/lang/RuntimeException
    //   96	111	286	java/lang/RuntimeException
    //   130	140	286	java/lang/RuntimeException
    //   152	161	286	java/lang/RuntimeException
    //   183	190	286	java/lang/RuntimeException
    //   202	214	286	java/lang/RuntimeException
    //   323	328	331	java/io/IOException
    //   49	57	335	finally
    //   69	84	335	finally
    //   96	111	335	finally
    //   130	140	335	finally
    //   152	161	335	finally
    //   183	190	335	finally
    //   202	214	335	finally
    //   246	270	335	finally
    //   291	318	335	finally
    //   2	8	348	finally
    //   222	227	348	finally
    //   275	280	348	finally
    //   323	328	348	finally
    //   341	346	348	finally
    //   346	348	348	finally
    //   358	363	348	finally
    //   358	363	366	java/io/IOException
    //   275	280	370	java/io/IOException
    //   341	346	374	java/io/IOException
  }
  
  /* Error */
  protected void saveMiniThumbToFile(byte[] paramArrayOfByte, long paramLong1, long paramLong2)
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokespecial 208	io/vov/vitamio/provider/MiniThumbFile:miniThumbDataFile	()Ljava/io/RandomAccessFile;
    //   6: astore 9
    //   8: aload 9
    //   10: ifnonnull +6 -> 16
    //   13: aload_0
    //   14: monitorexit
    //   15: return
    //   16: lload_2
    //   17: ldc2_w 209
    //   20: lmul
    //   21: lstore 7
    //   23: aconst_null
    //   24: astore 13
    //   26: aconst_null
    //   27: astore 14
    //   29: aconst_null
    //   30: astore 12
    //   32: aconst_null
    //   33: astore 9
    //   35: aload_1
    //   36: ifnull +221 -> 257
    //   39: aload 13
    //   41: astore 11
    //   43: aload 14
    //   45: astore 9
    //   47: aload 12
    //   49: astore 10
    //   51: aload_1
    //   52: arraylength
    //   53: istore 6
    //   55: iload 6
    //   57: sipush 9987
    //   60: if_icmple +19 -> 79
    //   63: iconst_0
    //   64: ifeq -51 -> 13
    //   67: new 282	java/lang/NullPointerException
    //   70: dup
    //   71: invokespecial 283	java/lang/NullPointerException:<init>	()V
    //   74: athrow
    //   75: astore_1
    //   76: goto -63 -> 13
    //   79: aload 13
    //   81: astore 11
    //   83: aload 14
    //   85: astore 9
    //   87: aload 12
    //   89: astore 10
    //   91: aload_0
    //   92: getfield 44	io/vov/vitamio/provider/MiniThumbFile:mBuffer	Ljava/nio/ByteBuffer;
    //   95: invokevirtual 213	java/nio/ByteBuffer:clear	()Ljava/nio/Buffer;
    //   98: pop
    //   99: aload 13
    //   101: astore 11
    //   103: aload 14
    //   105: astore 9
    //   107: aload 12
    //   109: astore 10
    //   111: aload_0
    //   112: getfield 44	io/vov/vitamio/provider/MiniThumbFile:mBuffer	Ljava/nio/ByteBuffer;
    //   115: iconst_1
    //   116: invokevirtual 286	java/nio/ByteBuffer:put	(B)Ljava/nio/ByteBuffer;
    //   119: pop
    //   120: aload 13
    //   122: astore 11
    //   124: aload 14
    //   126: astore 9
    //   128: aload 12
    //   130: astore 10
    //   132: aload_0
    //   133: getfield 44	io/vov/vitamio/provider/MiniThumbFile:mBuffer	Ljava/nio/ByteBuffer;
    //   136: lload 4
    //   138: invokevirtual 290	java/nio/ByteBuffer:putLong	(J)Ljava/nio/ByteBuffer;
    //   141: pop
    //   142: aload 13
    //   144: astore 11
    //   146: aload 14
    //   148: astore 9
    //   150: aload 12
    //   152: astore 10
    //   154: aload_0
    //   155: getfield 44	io/vov/vitamio/provider/MiniThumbFile:mBuffer	Ljava/nio/ByteBuffer;
    //   158: aload_1
    //   159: arraylength
    //   160: invokevirtual 293	java/nio/ByteBuffer:putInt	(I)Ljava/nio/ByteBuffer;
    //   163: pop
    //   164: aload 13
    //   166: astore 11
    //   168: aload 14
    //   170: astore 9
    //   172: aload 12
    //   174: astore 10
    //   176: aload_0
    //   177: getfield 44	io/vov/vitamio/provider/MiniThumbFile:mBuffer	Ljava/nio/ByteBuffer;
    //   180: aload_1
    //   181: invokevirtual 296	java/nio/ByteBuffer:put	([B)Ljava/nio/ByteBuffer;
    //   184: pop
    //   185: aload 13
    //   187: astore 11
    //   189: aload 14
    //   191: astore 9
    //   193: aload 12
    //   195: astore 10
    //   197: aload_0
    //   198: getfield 44	io/vov/vitamio/provider/MiniThumbFile:mBuffer	Ljava/nio/ByteBuffer;
    //   201: invokevirtual 299	java/nio/ByteBuffer:flip	()Ljava/nio/Buffer;
    //   204: pop
    //   205: aload 13
    //   207: astore 11
    //   209: aload 14
    //   211: astore 9
    //   213: aload 12
    //   215: astore 10
    //   217: aload_0
    //   218: getfield 139	io/vov/vitamio/provider/MiniThumbFile:mChannel	Ljava/nio/channels/FileChannel;
    //   221: lload 7
    //   223: ldc2_w 209
    //   226: iconst_0
    //   227: invokevirtual 225	java/nio/channels/FileChannel:lock	(JJZ)Ljava/nio/channels/FileLock;
    //   230: astore_1
    //   231: aload_1
    //   232: astore 11
    //   234: aload_1
    //   235: astore 9
    //   237: aload_1
    //   238: astore 10
    //   240: aload_0
    //   241: getfield 139	io/vov/vitamio/provider/MiniThumbFile:mChannel	Ljava/nio/channels/FileChannel;
    //   244: aload_0
    //   245: getfield 44	io/vov/vitamio/provider/MiniThumbFile:mBuffer	Ljava/nio/ByteBuffer;
    //   248: lload 7
    //   250: invokevirtual 302	java/nio/channels/FileChannel:write	(Ljava/nio/ByteBuffer;J)I
    //   253: pop
    //   254: aload_1
    //   255: astore 9
    //   257: aload 9
    //   259: ifnull -246 -> 13
    //   262: aload 9
    //   264: invokevirtual 244	java/nio/channels/FileLock:release	()V
    //   267: goto -254 -> 13
    //   270: astore_1
    //   271: goto -258 -> 13
    //   274: astore_1
    //   275: aload 11
    //   277: astore 9
    //   279: ldc_w 304
    //   282: iconst_2
    //   283: anewarray 4	java/lang/Object
    //   286: dup
    //   287: iconst_0
    //   288: lload_2
    //   289: invokestatic 256	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   292: aastore
    //   293: dup
    //   294: iconst_1
    //   295: aload_1
    //   296: invokevirtual 276	java/io/IOException:getMessage	()Ljava/lang/String;
    //   299: aastore
    //   300: invokestatic 126	io/vov/vitamio/utils/Log:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   303: aload 11
    //   305: astore 9
    //   307: aload_1
    //   308: athrow
    //   309: astore_1
    //   310: aload 9
    //   312: ifnull +8 -> 320
    //   315: aload 9
    //   317: invokevirtual 244	java/nio/channels/FileLock:release	()V
    //   320: aload_1
    //   321: athrow
    //   322: astore_1
    //   323: aload_0
    //   324: monitorexit
    //   325: aload_1
    //   326: athrow
    //   327: astore_1
    //   328: aload 10
    //   330: astore 9
    //   332: ldc_w 306
    //   335: iconst_2
    //   336: anewarray 4	java/lang/Object
    //   339: dup
    //   340: iconst_0
    //   341: lload_2
    //   342: invokestatic 256	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   345: aastore
    //   346: dup
    //   347: iconst_1
    //   348: aload_1
    //   349: invokevirtual 260	java/lang/Object:getClass	()Ljava/lang/Class;
    //   352: invokevirtual 263	java/lang/Class:toString	()Ljava/lang/String;
    //   355: aastore
    //   356: invokestatic 126	io/vov/vitamio/utils/Log:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   359: aload 10
    //   361: ifnull -348 -> 13
    //   364: aload 10
    //   366: invokevirtual 244	java/nio/channels/FileLock:release	()V
    //   369: goto -356 -> 13
    //   372: astore_1
    //   373: goto -360 -> 13
    //   376: astore 9
    //   378: goto -58 -> 320
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	381	0	this	MiniThumbFile
    //   0	381	1	paramArrayOfByte	byte[]
    //   0	381	2	paramLong1	long
    //   0	381	4	paramLong2	long
    //   53	8	6	i	int
    //   21	228	7	l	long
    //   6	325	9	localObject1	Object
    //   376	1	9	localIOException	IOException
    //   49	316	10	localObject2	Object
    //   41	263	11	localObject3	Object
    //   30	184	12	localObject4	Object
    //   24	182	13	localObject5	Object
    //   27	183	14	localObject6	Object
    // Exception table:
    //   from	to	target	type
    //   67	75	75	java/io/IOException
    //   262	267	270	java/io/IOException
    //   51	55	274	java/io/IOException
    //   91	99	274	java/io/IOException
    //   111	120	274	java/io/IOException
    //   132	142	274	java/io/IOException
    //   154	164	274	java/io/IOException
    //   176	185	274	java/io/IOException
    //   197	205	274	java/io/IOException
    //   217	231	274	java/io/IOException
    //   240	254	274	java/io/IOException
    //   51	55	309	finally
    //   91	99	309	finally
    //   111	120	309	finally
    //   132	142	309	finally
    //   154	164	309	finally
    //   176	185	309	finally
    //   197	205	309	finally
    //   217	231	309	finally
    //   240	254	309	finally
    //   279	303	309	finally
    //   307	309	309	finally
    //   332	359	309	finally
    //   2	8	322	finally
    //   67	75	322	finally
    //   262	267	322	finally
    //   315	320	322	finally
    //   320	322	322	finally
    //   364	369	322	finally
    //   51	55	327	java/lang/RuntimeException
    //   91	99	327	java/lang/RuntimeException
    //   111	120	327	java/lang/RuntimeException
    //   132	142	327	java/lang/RuntimeException
    //   154	164	327	java/lang/RuntimeException
    //   176	185	327	java/lang/RuntimeException
    //   197	205	327	java/lang/RuntimeException
    //   217	231	327	java/lang/RuntimeException
    //   240	254	327	java/lang/RuntimeException
    //   364	369	372	java/io/IOException
    //   315	320	376	java/io/IOException
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\io\vov\vitamio\provider\MiniThumbFile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */