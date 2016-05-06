package com.example.example75f1799f07eb;

import android.content.Context;
import android.os.Debug;

public class ReportHandler
{
  private static final String TAG = ReportHandler.class.getSimpleName();
  private static volatile boolean mCrashing = false;
  private static Context sAppContext;
  
  public static void install(Context paramContext, String paramString)
  {
    sAppContext = paramContext.getApplicationContext();
    if ((!Debug.waitingForDebugger()) && (!Debug.isDebuggerConnected())) {
      Thread.setDefaultUncaughtExceptionHandler(new UncaughtHandler(null));
    }
  }
  
  private static class UncaughtHandler
    implements Thread.UncaughtExceptionHandler
  {
    /* Error */
    public void uncaughtException(Thread paramThread, Throwable paramThrowable)
    {
      // Byte code:
      //   0: invokestatic 26	com/example/example75f1799f07eb/ReportHandler:access$100	()Z
      //   3: istore 4
      //   5: iload 4
      //   7: ifeq +15 -> 22
      //   10: invokestatic 32	android/os/Process:myPid	()I
      //   13: invokestatic 36	android/os/Process:killProcess	(I)V
      //   16: bipush 10
      //   18: invokestatic 41	java/lang/System:exit	(I)V
      //   21: return
      //   22: iconst_1
      //   23: invokestatic 45	com/example/example75f1799f07eb/ReportHandler:access$102	(Z)Z
      //   26: pop
      //   27: invokestatic 49	com/example/example75f1799f07eb/ReportHandler:access$200	()Ljava/lang/String;
      //   30: new 51	java/lang/StringBuilder
      //   33: dup
      //   34: invokespecial 52	java/lang/StringBuilder:<init>	()V
      //   37: ldc 54
      //   39: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   42: aload_1
      //   43: invokevirtual 63	java/lang/Thread:getName	()Ljava/lang/String;
      //   46: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   49: invokevirtual 66	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   52: aload_2
      //   53: invokestatic 72	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
      //   56: pop
      //   57: new 74	java/io/StringWriter
      //   60: dup
      //   61: invokespecial 75	java/io/StringWriter:<init>	()V
      //   64: astore_1
      //   65: new 77	java/io/PrintWriter
      //   68: dup
      //   69: aload_1
      //   70: invokespecial 80	java/io/PrintWriter:<init>	(Ljava/io/Writer;)V
      //   73: astore 5
      //   75: aload 5
      //   77: ldc 82
      //   79: invokevirtual 86	java/io/PrintWriter:println	(Ljava/lang/String;)V
      //   82: invokestatic 90	com/example/example75f1799f07eb/ReportHandler:access$300	()Landroid/content/Context;
      //   85: invokevirtual 96	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
      //   88: invokestatic 90	com/example/example75f1799f07eb/ReportHandler:access$300	()Landroid/content/Context;
      //   91: invokevirtual 99	android/content/Context:getPackageName	()Ljava/lang/String;
      //   94: iconst_0
      //   95: invokevirtual 105	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
      //   98: astore 6
      //   100: aload 5
      //   102: ldc 107
      //   104: invokevirtual 86	java/io/PrintWriter:println	(Ljava/lang/String;)V
      //   107: aload 5
      //   109: ldc 109
      //   111: iconst_3
      //   112: anewarray 4	java/lang/Object
      //   115: dup
      //   116: iconst_0
      //   117: aload 6
      //   119: getfield 115	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
      //   122: aastore
      //   123: dup
      //   124: iconst_1
      //   125: aload 6
      //   127: getfield 118	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
      //   130: aastore
      //   131: dup
      //   132: iconst_2
      //   133: aload 6
      //   135: getfield 122	android/content/pm/PackageInfo:versionCode	I
      //   138: invokestatic 128	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
      //   141: aastore
      //   142: invokevirtual 132	java/io/PrintWriter:printf	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/io/PrintWriter;
      //   145: pop
      //   146: aload_2
      //   147: invokevirtual 135	java/lang/Throwable:getMessage	()Ljava/lang/String;
      //   150: ifnull +32 -> 182
      //   153: aload_2
      //   154: invokevirtual 135	java/lang/Throwable:getMessage	()Ljava/lang/String;
      //   157: invokevirtual 140	java/lang/String:length	()I
      //   160: ifeq +22 -> 182
      //   163: aload 5
      //   165: ldc -114
      //   167: iconst_1
      //   168: anewarray 4	java/lang/Object
      //   171: dup
      //   172: iconst_0
      //   173: aload_2
      //   174: invokevirtual 135	java/lang/Throwable:getMessage	()Ljava/lang/String;
      //   177: aastore
      //   178: invokevirtual 132	java/io/PrintWriter:printf	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/io/PrintWriter;
      //   181: pop
      //   182: aload_2
      //   183: invokevirtual 146	java/lang/Throwable:getStackTrace	()[Ljava/lang/StackTraceElement;
      //   186: astore_2
      //   187: aload_2
      //   188: arraylength
      //   189: ifle +33 -> 222
      //   192: aload 5
      //   194: ldc -108
      //   196: invokevirtual 86	java/io/PrintWriter:println	(Ljava/lang/String;)V
      //   199: iconst_0
      //   200: istore_3
      //   201: iload_3
      //   202: aload_2
      //   203: arraylength
      //   204: if_icmpge +18 -> 222
      //   207: aload 5
      //   209: aload_2
      //   210: iload_3
      //   211: aaload
      //   212: invokevirtual 151	java/io/PrintWriter:println	(Ljava/lang/Object;)V
      //   215: iload_3
      //   216: iconst_1
      //   217: iadd
      //   218: istore_3
      //   219: goto -18 -> 201
      //   222: new 51	java/lang/StringBuilder
      //   225: dup
      //   226: invokespecial 52	java/lang/StringBuilder:<init>	()V
      //   229: ldc -103
      //   231: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   234: aload_1
      //   235: invokevirtual 154	java/io/StringWriter:toString	()Ljava/lang/String;
      //   238: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   241: invokevirtual 66	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   244: invokestatic 159	com/example/example75f1799f07eb/MyPhoneGapActivity:getStackValues	(Ljava/lang/String;)V
      //   247: invokestatic 32	android/os/Process:myPid	()I
      //   250: invokestatic 36	android/os/Process:killProcess	(I)V
      //   253: bipush 10
      //   255: invokestatic 41	java/lang/System:exit	(I)V
      //   258: return
      //   259: astore_1
      //   260: invokestatic 49	com/example/example75f1799f07eb/ReportHandler:access$200	()Ljava/lang/String;
      //   263: ldc -95
      //   265: aload_1
      //   266: invokestatic 72	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
      //   269: pop
      //   270: invokestatic 32	android/os/Process:myPid	()I
      //   273: invokestatic 36	android/os/Process:killProcess	(I)V
      //   276: bipush 10
      //   278: invokestatic 41	java/lang/System:exit	(I)V
      //   281: return
      //   282: astore_1
      //   283: invokestatic 32	android/os/Process:myPid	()I
      //   286: invokestatic 36	android/os/Process:killProcess	(I)V
      //   289: bipush 10
      //   291: invokestatic 41	java/lang/System:exit	(I)V
      //   294: aload_1
      //   295: athrow
      //   296: astore_1
      //   297: goto -27 -> 270
      //   300: astore 6
      //   302: goto -156 -> 146
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	305	0	this	UncaughtHandler
      //   0	305	1	paramThread	Thread
      //   0	305	2	paramThrowable	Throwable
      //   200	19	3	i	int
      //   3	3	4	bool	boolean
      //   73	135	5	localPrintWriter	java.io.PrintWriter
      //   98	36	6	localPackageInfo	android.content.pm.PackageInfo
      //   300	1	6	localNameNotFoundException	android.content.pm.PackageManager.NameNotFoundException
      // Exception table:
      //   from	to	target	type
      //   0	5	259	java/lang/Throwable
      //   22	82	259	java/lang/Throwable
      //   82	146	259	java/lang/Throwable
      //   146	182	259	java/lang/Throwable
      //   182	199	259	java/lang/Throwable
      //   201	215	259	java/lang/Throwable
      //   222	247	259	java/lang/Throwable
      //   0	5	282	finally
      //   22	82	282	finally
      //   82	146	282	finally
      //   146	182	282	finally
      //   182	199	282	finally
      //   201	215	282	finally
      //   222	247	282	finally
      //   260	270	282	finally
      //   260	270	296	java/lang/Throwable
      //   82	146	300	android/content/pm/PackageManager$NameNotFoundException
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\example\example75f1799f07eb\ReportHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */