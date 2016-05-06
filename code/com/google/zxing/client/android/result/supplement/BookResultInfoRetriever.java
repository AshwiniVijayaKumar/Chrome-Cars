package com.google.zxing.client.android.result.supplement;

import android.content.Context;
import android.widget.TextView;
import com.google.zxing.client.android.R.string;
import com.google.zxing.client.android.history.HistoryManager;

final class BookResultInfoRetriever
  extends SupplementalInfoRetriever
{
  private final Context context;
  private final String isbn;
  private final String source;
  
  BookResultInfoRetriever(TextView paramTextView, String paramString, HistoryManager paramHistoryManager, Context paramContext)
  {
    super(paramTextView, paramHistoryManager);
    this.isbn = paramString;
    this.source = paramContext.getString(R.string.msg_google_books);
    this.context = paramContext;
  }
  
  /* Error */
  void retrieveSupplementalInfo()
    throws java.io.IOException
  {
    // Byte code:
    //   0: new 41	java/lang/StringBuilder
    //   3: dup
    //   4: invokespecial 43	java/lang/StringBuilder:<init>	()V
    //   7: ldc 45
    //   9: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   12: aload_0
    //   13: getfield 16	com/google/zxing/client/android/result/supplement/BookResultInfoRetriever:isbn	Ljava/lang/String;
    //   16: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   19: invokevirtual 53	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   22: getstatic 59	com/google/zxing/client/android/HttpHelper$ContentType:JSON	Lcom/google/zxing/client/android/HttpHelper$ContentType;
    //   25: invokestatic 65	com/google/zxing/client/android/HttpHelper:downloadViaHttp	(Ljava/lang/String;Lcom/google/zxing/client/android/HttpHelper$ContentType;)Ljava/lang/CharSequence;
    //   28: astore_2
    //   29: aload_2
    //   30: invokeinterface 71 1 0
    //   35: ifne +4 -> 39
    //   38: return
    //   39: aconst_null
    //   40: astore_3
    //   41: new 73	org/json/JSONTokener
    //   44: dup
    //   45: aload_2
    //   46: invokeinterface 74 1 0
    //   51: invokespecial 77	org/json/JSONTokener:<init>	(Ljava/lang/String;)V
    //   54: invokevirtual 81	org/json/JSONTokener:nextValue	()Ljava/lang/Object;
    //   57: checkcast 83	org/json/JSONObject
    //   60: ldc 85
    //   62: invokevirtual 89	org/json/JSONObject:optJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   65: astore_2
    //   66: aload_2
    //   67: ifnull -29 -> 38
    //   70: aload_2
    //   71: iconst_0
    //   72: invokevirtual 95	org/json/JSONArray:isNull	(I)Z
    //   75: ifne -37 -> 38
    //   78: aload_2
    //   79: iconst_0
    //   80: invokevirtual 99	org/json/JSONArray:get	(I)Ljava/lang/Object;
    //   83: checkcast 83	org/json/JSONObject
    //   86: ldc 101
    //   88: invokevirtual 105	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   91: astore_2
    //   92: aload_2
    //   93: ifnull -55 -> 38
    //   96: aload_2
    //   97: ldc 107
    //   99: invokevirtual 111	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   102: astore 5
    //   104: aload_2
    //   105: ldc 113
    //   107: invokevirtual 111	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   110: astore 4
    //   112: aload_2
    //   113: ldc 115
    //   115: invokevirtual 89	org/json/JSONObject:optJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   118: astore 6
    //   120: aload_3
    //   121: astore_2
    //   122: aload 6
    //   124: ifnull +71 -> 195
    //   127: aload_3
    //   128: astore_2
    //   129: aload 6
    //   131: iconst_0
    //   132: invokevirtual 95	org/json/JSONArray:isNull	(I)Z
    //   135: ifne +60 -> 195
    //   138: new 117	java/util/ArrayList
    //   141: dup
    //   142: aload 6
    //   144: invokevirtual 118	org/json/JSONArray:length	()I
    //   147: invokespecial 121	java/util/ArrayList:<init>	(I)V
    //   150: astore_2
    //   151: iconst_0
    //   152: istore_1
    //   153: iload_1
    //   154: aload 6
    //   156: invokevirtual 118	org/json/JSONArray:length	()I
    //   159: if_icmpge +36 -> 195
    //   162: aload_2
    //   163: aload 6
    //   165: iload_1
    //   166: invokevirtual 122	org/json/JSONArray:getString	(I)Ljava/lang/String;
    //   169: invokeinterface 128 2 0
    //   174: pop
    //   175: iload_1
    //   176: iconst_1
    //   177: iadd
    //   178: istore_1
    //   179: goto -26 -> 153
    //   182: astore_2
    //   183: new 37	java/io/IOException
    //   186: dup
    //   187: aload_2
    //   188: invokevirtual 129	org/json/JSONException:toString	()Ljava/lang/String;
    //   191: invokespecial 130	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   194: athrow
    //   195: new 117	java/util/ArrayList
    //   198: dup
    //   199: invokespecial 131	java/util/ArrayList:<init>	()V
    //   202: astore_3
    //   203: aload 5
    //   205: ifnull +20 -> 225
    //   208: aload 5
    //   210: invokevirtual 134	java/lang/String:length	()I
    //   213: ifle +12 -> 225
    //   216: aload_3
    //   217: aload 5
    //   219: invokeinterface 128 2 0
    //   224: pop
    //   225: aload_2
    //   226: ifnull +90 -> 316
    //   229: aload_2
    //   230: invokeinterface 138 1 0
    //   235: ifne +81 -> 316
    //   238: iconst_1
    //   239: istore_1
    //   240: new 41	java/lang/StringBuilder
    //   243: dup
    //   244: invokespecial 43	java/lang/StringBuilder:<init>	()V
    //   247: astore 5
    //   249: aload_2
    //   250: invokeinterface 142 1 0
    //   255: astore_2
    //   256: aload_2
    //   257: invokeinterface 147 1 0
    //   262: ifeq +42 -> 304
    //   265: aload_2
    //   266: invokeinterface 150 1 0
    //   271: checkcast 133	java/lang/String
    //   274: astore 6
    //   276: iload_1
    //   277: ifeq +16 -> 293
    //   280: iconst_0
    //   281: istore_1
    //   282: aload 5
    //   284: aload 6
    //   286: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   289: pop
    //   290: goto -34 -> 256
    //   293: aload 5
    //   295: ldc -104
    //   297: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   300: pop
    //   301: goto -19 -> 282
    //   304: aload_3
    //   305: aload 5
    //   307: invokevirtual 53	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   310: invokeinterface 128 2 0
    //   315: pop
    //   316: aload 4
    //   318: ifnull +38 -> 356
    //   321: aload 4
    //   323: invokevirtual 134	java/lang/String:length	()I
    //   326: ifle +30 -> 356
    //   329: aload_3
    //   330: new 41	java/lang/StringBuilder
    //   333: dup
    //   334: invokespecial 43	java/lang/StringBuilder:<init>	()V
    //   337: aload 4
    //   339: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   342: ldc -102
    //   344: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   347: invokevirtual 53	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   350: invokeinterface 128 2 0
    //   355: pop
    //   356: new 41	java/lang/StringBuilder
    //   359: dup
    //   360: invokespecial 43	java/lang/StringBuilder:<init>	()V
    //   363: ldc -100
    //   365: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   368: aload_0
    //   369: getfield 32	com/google/zxing/client/android/result/supplement/BookResultInfoRetriever:context	Landroid/content/Context;
    //   372: invokestatic 162	com/google/zxing/client/android/LocaleManager:getBookSearchCountryTLD	(Landroid/content/Context;)Ljava/lang/String;
    //   375: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   378: ldc -92
    //   380: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   383: invokevirtual 53	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   386: astore_2
    //   387: aload_0
    //   388: aload_0
    //   389: getfield 16	com/google/zxing/client/android/result/supplement/BookResultInfoRetriever:isbn	Ljava/lang/String;
    //   392: aload_0
    //   393: getfield 30	com/google/zxing/client/android/result/supplement/BookResultInfoRetriever:source	Ljava/lang/String;
    //   396: aload_3
    //   397: aload_3
    //   398: invokeinterface 167 1 0
    //   403: anewarray 133	java/lang/String
    //   406: invokeinterface 171 2 0
    //   411: checkcast 173	[Ljava/lang/String;
    //   414: new 41	java/lang/StringBuilder
    //   417: dup
    //   418: invokespecial 43	java/lang/StringBuilder:<init>	()V
    //   421: aload_2
    //   422: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   425: aload_0
    //   426: getfield 16	com/google/zxing/client/android/result/supplement/BookResultInfoRetriever:isbn	Ljava/lang/String;
    //   429: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   432: invokevirtual 53	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   435: invokevirtual 176	com/google/zxing/client/android/result/supplement/BookResultInfoRetriever:append	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)V
    //   438: return
    //   439: astore_2
    //   440: goto -257 -> 183
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	443	0	this	BookResultInfoRetriever
    //   152	130	1	i	int
    //   28	135	2	localObject1	Object
    //   182	68	2	localJSONException1	org.json.JSONException
    //   255	167	2	localObject2	Object
    //   439	1	2	localJSONException2	org.json.JSONException
    //   40	358	3	localArrayList	java.util.ArrayList
    //   110	228	4	str	String
    //   102	204	5	localObject3	Object
    //   118	167	6	localObject4	Object
    // Exception table:
    //   from	to	target	type
    //   41	66	182	org/json/JSONException
    //   70	92	182	org/json/JSONException
    //   96	120	182	org/json/JSONException
    //   129	151	182	org/json/JSONException
    //   153	175	439	org/json/JSONException
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\result\supplement\BookResultInfoRetriever.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */