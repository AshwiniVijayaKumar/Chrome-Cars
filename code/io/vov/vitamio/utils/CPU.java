package io.vov.vitamio.utils;

import android.os.Build;
import java.util.HashMap;
import java.util.Map;

public class CPU
{
  public static final int FEATURE_ARM_NEON = 32;
  public static final int FEATURE_ARM_V5TE = 1;
  public static final int FEATURE_ARM_V6 = 2;
  public static final int FEATURE_ARM_V7A = 8;
  public static final int FEATURE_ARM_VFP = 4;
  public static final int FEATURE_ARM_VFPV3 = 16;
  public static final int FEATURE_MIPS = 128;
  public static final int FEATURE_X86 = 64;
  private static int cachedFeature = -1;
  private static String cachedFeatureString = null;
  private static final Map<String, String> cpuinfo = new HashMap();
  
  private static int getCachedFeature()
  {
    if (cachedFeatureString == null)
    {
      StringBuffer localStringBuffer = new StringBuffer();
      if ((cachedFeature & 0x1) > 0) {
        localStringBuffer.append("V5TE ");
      }
      if ((cachedFeature & 0x2) > 0) {
        localStringBuffer.append("V6 ");
      }
      if ((cachedFeature & 0x4) > 0) {
        localStringBuffer.append("VFP ");
      }
      if ((cachedFeature & 0x8) > 0) {
        localStringBuffer.append("V7A ");
      }
      if ((cachedFeature & 0x10) > 0) {
        localStringBuffer.append("VFPV3 ");
      }
      if ((cachedFeature & 0x20) > 0) {
        localStringBuffer.append("NEON ");
      }
      if ((cachedFeature & 0x40) > 0) {
        localStringBuffer.append("X86 ");
      }
      if ((cachedFeature & 0x80) > 0) {
        localStringBuffer.append("MIPS ");
      }
      cachedFeatureString = localStringBuffer.toString();
    }
    Log.d("GET CPU FATURE: %s", new Object[] { cachedFeatureString });
    return cachedFeature;
  }
  
  /* Error */
  public static int getFeature()
  {
    // Byte code:
    //   0: getstatic 38	io/vov/vitamio/utils/CPU:cachedFeature	I
    //   3: ifle +7 -> 10
    //   6: invokestatic 88	io/vov/vitamio/utils/CPU:getCachedFeature	()I
    //   9: ireturn
    //   10: iconst_1
    //   11: putstatic 38	io/vov/vitamio/utils/CPU:cachedFeature	I
    //   14: getstatic 36	io/vov/vitamio/utils/CPU:cpuinfo	Ljava/util/Map;
    //   17: invokeinterface 94 1 0
    //   22: ifeq +56 -> 78
    //   25: aconst_null
    //   26: astore 4
    //   28: aconst_null
    //   29: astore 6
    //   31: new 96	java/io/BufferedReader
    //   34: dup
    //   35: new 98	java/io/FileReader
    //   38: dup
    //   39: new 100	java/io/File
    //   42: dup
    //   43: ldc 102
    //   45: invokespecial 105	java/io/File:<init>	(Ljava/lang/String;)V
    //   48: invokespecial 108	java/io/FileReader:<init>	(Ljava/io/File;)V
    //   51: invokespecial 111	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   54: astore 5
    //   56: aload 5
    //   58: invokevirtual 114	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   61: astore 4
    //   63: aload 4
    //   65: ifnonnull +292 -> 357
    //   68: aload 5
    //   70: ifnull +8 -> 78
    //   73: aload 5
    //   75: invokevirtual 117	java/io/BufferedReader:close	()V
    //   78: getstatic 36	io/vov/vitamio/utils/CPU:cpuinfo	Ljava/util/Map;
    //   81: invokeinterface 94 1 0
    //   86: ifne +267 -> 353
    //   89: getstatic 36	io/vov/vitamio/utils/CPU:cpuinfo	Ljava/util/Map;
    //   92: invokeinterface 121 1 0
    //   97: invokeinterface 127 1 0
    //   102: astore 4
    //   104: aload 4
    //   106: invokeinterface 132 1 0
    //   111: ifne +378 -> 489
    //   114: iconst_0
    //   115: istore_0
    //   116: iconst_0
    //   117: istore_1
    //   118: getstatic 36	io/vov/vitamio/utils/CPU:cpuinfo	Ljava/util/Map;
    //   121: ldc -122
    //   123: invokeinterface 138 2 0
    //   128: checkcast 140	java/lang/String
    //   131: astore 4
    //   133: aload 4
    //   135: invokestatic 145	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   138: ifne +461 -> 599
    //   141: aload 4
    //   143: invokestatic 151	io/vov/vitamio/utils/StringUtils:convertToInt	(Ljava/lang/String;)I
    //   146: istore_2
    //   147: ldc -103
    //   149: iconst_1
    //   150: anewarray 4	java/lang/Object
    //   153: dup
    //   154: iconst_0
    //   155: iload_2
    //   156: invokestatic 159	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   159: aastore
    //   160: invokestatic 79	io/vov/vitamio/utils/Log:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   163: iload_2
    //   164: bipush 7
    //   166: if_icmplt +365 -> 531
    //   169: iconst_1
    //   170: istore_0
    //   171: iconst_1
    //   172: istore_1
    //   173: getstatic 36	io/vov/vitamio/utils/CPU:cpuinfo	Ljava/util/Map;
    //   176: ldc -95
    //   178: invokeinterface 138 2 0
    //   183: checkcast 140	java/lang/String
    //   186: astore 5
    //   188: aload 5
    //   190: astore 4
    //   192: aload 5
    //   194: invokestatic 145	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   197: ifeq +18 -> 215
    //   200: getstatic 36	io/vov/vitamio/utils/CPU:cpuinfo	Ljava/util/Map;
    //   203: ldc -93
    //   205: invokeinterface 138 2 0
    //   210: checkcast 140	java/lang/String
    //   213: astore 4
    //   215: iload_0
    //   216: istore_3
    //   217: iload_1
    //   218: istore_2
    //   219: aload 4
    //   221: ifnull +31 -> 252
    //   224: aload 4
    //   226: ldc -91
    //   228: invokevirtual 168	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   231: ifne +17 -> 248
    //   234: iload_0
    //   235: istore_3
    //   236: iload_1
    //   237: istore_2
    //   238: aload 4
    //   240: ldc -86
    //   242: invokevirtual 168	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   245: ifeq +7 -> 252
    //   248: iconst_1
    //   249: istore_3
    //   250: iconst_1
    //   251: istore_2
    //   252: iload_3
    //   253: istore_1
    //   254: iload_2
    //   255: istore_0
    //   256: aload 4
    //   258: ifnull +31 -> 289
    //   261: aload 4
    //   263: ldc -84
    //   265: invokevirtual 168	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   268: ifne +17 -> 285
    //   271: iload_3
    //   272: istore_1
    //   273: iload_2
    //   274: istore_0
    //   275: aload 4
    //   277: ldc -82
    //   279: invokevirtual 168	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   282: ifeq +7 -> 289
    //   285: iconst_1
    //   286: istore_1
    //   287: iconst_0
    //   288: istore_0
    //   289: iload_1
    //   290: ifeq +11 -> 301
    //   293: getstatic 38	io/vov/vitamio/utils/CPU:cachedFeature	I
    //   296: iconst_2
    //   297: ior
    //   298: putstatic 38	io/vov/vitamio/utils/CPU:cachedFeature	I
    //   301: iload_0
    //   302: ifeq +12 -> 314
    //   305: getstatic 38	io/vov/vitamio/utils/CPU:cachedFeature	I
    //   308: bipush 8
    //   310: ior
    //   311: putstatic 38	io/vov/vitamio/utils/CPU:cachedFeature	I
    //   314: getstatic 36	io/vov/vitamio/utils/CPU:cpuinfo	Ljava/util/Map;
    //   317: ldc -80
    //   319: invokeinterface 138 2 0
    //   324: checkcast 140	java/lang/String
    //   327: astore 4
    //   329: aload 4
    //   331: ifnull +22 -> 353
    //   334: aload 4
    //   336: ldc -78
    //   338: invokevirtual 168	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   341: ifeq +215 -> 556
    //   344: getstatic 38	io/vov/vitamio/utils/CPU:cachedFeature	I
    //   347: bipush 52
    //   349: ior
    //   350: putstatic 38	io/vov/vitamio/utils/CPU:cachedFeature	I
    //   353: invokestatic 88	io/vov/vitamio/utils/CPU:getCachedFeature	()I
    //   356: ireturn
    //   357: aload 4
    //   359: invokevirtual 181	java/lang/String:trim	()Ljava/lang/String;
    //   362: ldc -73
    //   364: invokevirtual 187	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   367: ifne -311 -> 56
    //   370: aload 4
    //   372: ldc -67
    //   374: invokevirtual 193	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   377: astore 4
    //   379: aload 4
    //   381: arraylength
    //   382: iconst_1
    //   383: if_icmple -327 -> 56
    //   386: getstatic 36	io/vov/vitamio/utils/CPU:cpuinfo	Ljava/util/Map;
    //   389: aload 4
    //   391: iconst_0
    //   392: aaload
    //   393: invokevirtual 181	java/lang/String:trim	()Ljava/lang/String;
    //   396: aload 4
    //   398: iconst_1
    //   399: aaload
    //   400: invokevirtual 181	java/lang/String:trim	()Ljava/lang/String;
    //   403: invokeinterface 197 3 0
    //   408: pop
    //   409: goto -353 -> 56
    //   412: astore 6
    //   414: aload 5
    //   416: astore 4
    //   418: ldc -57
    //   420: aload 6
    //   422: invokestatic 203	io/vov/vitamio/utils/Log:e	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   425: aload 5
    //   427: ifnull -349 -> 78
    //   430: aload 5
    //   432: invokevirtual 117	java/io/BufferedReader:close	()V
    //   435: goto -357 -> 78
    //   438: astore 4
    //   440: ldc -57
    //   442: aload 4
    //   444: invokestatic 203	io/vov/vitamio/utils/Log:e	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   447: goto -369 -> 78
    //   450: astore 5
    //   452: aload 4
    //   454: ifnull +8 -> 462
    //   457: aload 4
    //   459: invokevirtual 117	java/io/BufferedReader:close	()V
    //   462: aload 5
    //   464: athrow
    //   465: astore 4
    //   467: ldc -57
    //   469: aload 4
    //   471: invokestatic 203	io/vov/vitamio/utils/Log:e	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   474: goto -12 -> 462
    //   477: astore 4
    //   479: ldc -57
    //   481: aload 4
    //   483: invokestatic 203	io/vov/vitamio/utils/Log:e	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   486: goto -408 -> 78
    //   489: aload 4
    //   491: invokeinterface 207 1 0
    //   496: checkcast 140	java/lang/String
    //   499: astore 5
    //   501: ldc -47
    //   503: iconst_2
    //   504: anewarray 4	java/lang/Object
    //   507: dup
    //   508: iconst_0
    //   509: aload 5
    //   511: aastore
    //   512: dup
    //   513: iconst_1
    //   514: getstatic 36	io/vov/vitamio/utils/CPU:cpuinfo	Ljava/util/Map;
    //   517: aload 5
    //   519: invokeinterface 138 2 0
    //   524: aastore
    //   525: invokestatic 79	io/vov/vitamio/utils/Log:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   528: goto -424 -> 104
    //   531: iload_2
    //   532: bipush 6
    //   534: if_icmplt -361 -> 173
    //   537: iconst_1
    //   538: istore_0
    //   539: iconst_0
    //   540: istore_1
    //   541: goto -368 -> 173
    //   544: astore 4
    //   546: ldc -57
    //   548: aload 4
    //   550: invokestatic 203	io/vov/vitamio/utils/Log:e	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   553: goto -380 -> 173
    //   556: aload 4
    //   558: ldc -45
    //   560: invokevirtual 168	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   563: ifeq +15 -> 578
    //   566: getstatic 38	io/vov/vitamio/utils/CPU:cachedFeature	I
    //   569: bipush 20
    //   571: ior
    //   572: putstatic 38	io/vov/vitamio/utils/CPU:cachedFeature	I
    //   575: goto -222 -> 353
    //   578: aload 4
    //   580: ldc -43
    //   582: invokevirtual 168	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   585: ifeq -232 -> 353
    //   588: getstatic 38	io/vov/vitamio/utils/CPU:cachedFeature	I
    //   591: iconst_4
    //   592: ior
    //   593: putstatic 38	io/vov/vitamio/utils/CPU:cachedFeature	I
    //   596: goto -243 -> 353
    //   599: getstatic 36	io/vov/vitamio/utils/CPU:cpuinfo	Ljava/util/Map;
    //   602: ldc -41
    //   604: invokeinterface 138 2 0
    //   609: checkcast 140	java/lang/String
    //   612: astore 4
    //   614: getstatic 36	io/vov/vitamio/utils/CPU:cpuinfo	Ljava/util/Map;
    //   617: ldc -39
    //   619: invokeinterface 138 2 0
    //   624: checkcast 140	java/lang/String
    //   627: astore 5
    //   629: aload 4
    //   631: invokestatic 145	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   634: ifne +25 -> 659
    //   637: aload 4
    //   639: ldc -37
    //   641: invokevirtual 168	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   644: ifeq +15 -> 659
    //   647: getstatic 38	io/vov/vitamio/utils/CPU:cachedFeature	I
    //   650: bipush 64
    //   652: ior
    //   653: putstatic 38	io/vov/vitamio/utils/CPU:cachedFeature	I
    //   656: goto -303 -> 353
    //   659: aload 5
    //   661: invokestatic 145	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   664: ifne -311 -> 353
    //   667: aload 5
    //   669: ldc -35
    //   671: invokevirtual 168	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   674: ifeq -321 -> 353
    //   677: getstatic 38	io/vov/vitamio/utils/CPU:cachedFeature	I
    //   680: sipush 128
    //   683: ior
    //   684: putstatic 38	io/vov/vitamio/utils/CPU:cachedFeature	I
    //   687: goto -334 -> 353
    //   690: astore 6
    //   692: aload 5
    //   694: astore 4
    //   696: aload 6
    //   698: astore 5
    //   700: goto -248 -> 452
    //   703: astore 4
    //   705: aload 6
    //   707: astore 5
    //   709: aload 4
    //   711: astore 6
    //   713: goto -299 -> 414
    // Local variable table:
    //   start	length	slot	name	signature
    //   115	424	0	i	int
    //   117	424	1	j	int
    //   146	389	2	k	int
    //   216	56	3	m	int
    //   26	391	4	localObject1	Object
    //   438	20	4	localIOException1	java.io.IOException
    //   465	5	4	localIOException2	java.io.IOException
    //   477	13	4	localIOException3	java.io.IOException
    //   544	35	4	localNumberFormatException	NumberFormatException
    //   612	83	4	localObject2	Object
    //   703	7	4	localException1	Exception
    //   54	377	5	localObject3	Object
    //   450	13	5	localObject4	Object
    //   499	209	5	localObject5	Object
    //   29	1	6	localObject6	Object
    //   412	9	6	localException2	Exception
    //   690	16	6	localObject7	Object
    //   711	1	6	localException3	Exception
    // Exception table:
    //   from	to	target	type
    //   56	63	412	java/lang/Exception
    //   357	409	412	java/lang/Exception
    //   430	435	438	java/io/IOException
    //   31	56	450	finally
    //   418	425	450	finally
    //   457	462	465	java/io/IOException
    //   73	78	477	java/io/IOException
    //   141	163	544	java/lang/NumberFormatException
    //   56	63	690	finally
    //   357	409	690	finally
    //   31	56	703	java/lang/Exception
  }
  
  public static String getFeatureString()
  {
    getFeature();
    return cachedFeatureString;
  }
  
  public static boolean isDroidXDroid2()
  {
    return (Build.MODEL.trim().equalsIgnoreCase("DROIDX")) || (Build.MODEL.trim().equalsIgnoreCase("DROID2")) || (Build.FINGERPRINT.toLowerCase().contains("shadow")) || (Build.FINGERPRINT.toLowerCase().contains("droid2"));
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\io\vov\vitamio\utils\CPU.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */