package com.google.zxing.client.android.history;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.preference.PreferenceManager;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.google.zxing.client.android.result.ResultHandler;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class HistoryManager
{
  private static final String[] COLUMNS;
  private static final String[] COUNT_COLUMN;
  private static final DateFormat EXPORT_DATE_TIME_FORMAT = DateFormat.getDateTimeInstance(2, 2);
  private static final String[] ID_COL_PROJECTION;
  private static final String[] ID_DETAIL_COL_PROJECTION;
  private static final int MAX_ITEMS = 500;
  private static final String TAG = HistoryManager.class.getSimpleName();
  private final Activity activity;
  
  static
  {
    COLUMNS = new String[] { "text", "display", "format", "timestamp", "details" };
    COUNT_COLUMN = new String[] { "COUNT(1)" };
    ID_COL_PROJECTION = new String[] { "id" };
    ID_DETAIL_COL_PROJECTION = new String[] { "id", "details" };
  }
  
  public HistoryManager(Activity paramActivity)
  {
    this.activity = paramActivity;
  }
  
  private static void close(Cursor paramCursor, SQLiteDatabase paramSQLiteDatabase)
  {
    if (paramCursor != null) {
      paramCursor.close();
    }
    if (paramSQLiteDatabase != null) {
      paramSQLiteDatabase.close();
    }
  }
  
  private void deletePrevious(String paramString)
  {
    Object localObject2 = new DBHelper(this.activity);
    Object localObject1 = null;
    try
    {
      localObject2 = ((SQLiteOpenHelper)localObject2).getWritableDatabase();
      localObject1 = localObject2;
      ((SQLiteDatabase)localObject2).delete("history", "text=?", new String[] { paramString });
      return;
    }
    finally
    {
      close(null, (SQLiteDatabase)localObject1);
    }
  }
  
  private static String massageHistoryField(String paramString)
  {
    if (paramString == null) {
      return "";
    }
    return paramString.replace("\"", "\"\"");
  }
  
  /* Error */
  static android.net.Uri saveHistory(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: new 117	java/io/File
    //   5: dup
    //   6: new 117	java/io/File
    //   9: dup
    //   10: invokestatic 123	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   13: ldc 125
    //   15: invokespecial 128	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   18: ldc -126
    //   20: invokespecial 128	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   23: astore_1
    //   24: aload_1
    //   25: invokevirtual 134	java/io/File:exists	()Z
    //   28: ifne +40 -> 68
    //   31: aload_1
    //   32: invokevirtual 137	java/io/File:mkdirs	()Z
    //   35: ifne +33 -> 68
    //   38: getstatic 28	com/google/zxing/client/android/history/HistoryManager:TAG	Ljava/lang/String;
    //   41: new 139	java/lang/StringBuilder
    //   44: dup
    //   45: invokespecial 140	java/lang/StringBuilder:<init>	()V
    //   48: ldc -114
    //   50: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   53: aload_1
    //   54: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   57: invokevirtual 152	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   60: invokestatic 158	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   63: pop
    //   64: aload_3
    //   65: astore_1
    //   66: aload_1
    //   67: areturn
    //   68: new 117	java/io/File
    //   71: dup
    //   72: aload_1
    //   73: new 139	java/lang/StringBuilder
    //   76: dup
    //   77: invokespecial 140	java/lang/StringBuilder:<init>	()V
    //   80: ldc -96
    //   82: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   85: invokestatic 166	java/lang/System:currentTimeMillis	()J
    //   88: invokevirtual 169	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   91: ldc -85
    //   93: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   96: invokevirtual 152	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   99: invokespecial 128	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   102: astore 5
    //   104: aconst_null
    //   105: astore_1
    //   106: aconst_null
    //   107: astore 4
    //   109: new 173	java/io/OutputStreamWriter
    //   112: dup
    //   113: new 175	java/io/FileOutputStream
    //   116: dup
    //   117: aload 5
    //   119: invokespecial 178	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   122: ldc -76
    //   124: invokestatic 186	java/nio/charset/Charset:forName	(Ljava/lang/String;)Ljava/nio/charset/Charset;
    //   127: invokespecial 189	java/io/OutputStreamWriter:<init>	(Ljava/io/OutputStream;Ljava/nio/charset/Charset;)V
    //   130: astore_2
    //   131: aload_2
    //   132: aload_0
    //   133: invokevirtual 192	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
    //   136: new 139	java/lang/StringBuilder
    //   139: dup
    //   140: invokespecial 140	java/lang/StringBuilder:<init>	()V
    //   143: ldc -62
    //   145: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   148: aload 5
    //   150: invokevirtual 197	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   153: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   156: invokevirtual 152	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   159: invokestatic 202	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   162: astore_0
    //   163: aload_0
    //   164: astore_1
    //   165: aload_2
    //   166: ifnull -100 -> 66
    //   169: aload_2
    //   170: invokevirtual 203	java/io/OutputStreamWriter:close	()V
    //   173: aload_0
    //   174: areturn
    //   175: astore_1
    //   176: aload_0
    //   177: areturn
    //   178: astore_2
    //   179: aload 4
    //   181: astore_0
    //   182: aload_0
    //   183: astore_1
    //   184: getstatic 28	com/google/zxing/client/android/history/HistoryManager:TAG	Ljava/lang/String;
    //   187: new 139	java/lang/StringBuilder
    //   190: dup
    //   191: invokespecial 140	java/lang/StringBuilder:<init>	()V
    //   194: ldc -51
    //   196: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   199: aload 5
    //   201: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   204: ldc -49
    //   206: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   209: aload_2
    //   210: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   213: invokevirtual 152	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   216: invokestatic 158	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   219: pop
    //   220: aload_3
    //   221: astore_1
    //   222: aload_0
    //   223: ifnull -157 -> 66
    //   226: aload_0
    //   227: invokevirtual 203	java/io/OutputStreamWriter:close	()V
    //   230: aconst_null
    //   231: areturn
    //   232: astore_0
    //   233: aconst_null
    //   234: areturn
    //   235: astore_0
    //   236: aload_1
    //   237: ifnull +7 -> 244
    //   240: aload_1
    //   241: invokevirtual 203	java/io/OutputStreamWriter:close	()V
    //   244: aload_0
    //   245: athrow
    //   246: astore_1
    //   247: goto -3 -> 244
    //   250: astore_0
    //   251: aload_2
    //   252: astore_1
    //   253: goto -17 -> 236
    //   256: astore_1
    //   257: aload_2
    //   258: astore_0
    //   259: aload_1
    //   260: astore_2
    //   261: goto -79 -> 182
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	264	0	paramString	String
    //   23	142	1	localObject1	Object
    //   175	1	1	localIOException1	java.io.IOException
    //   183	58	1	localObject2	Object
    //   246	1	1	localIOException2	java.io.IOException
    //   252	1	1	localObject3	Object
    //   256	4	1	localIOException3	java.io.IOException
    //   130	40	2	localOutputStreamWriter	java.io.OutputStreamWriter
    //   178	80	2	localIOException4	java.io.IOException
    //   260	1	2	localIOException5	java.io.IOException
    //   1	220	3	localObject4	Object
    //   107	73	4	localObject5	Object
    //   102	98	5	localFile	java.io.File
    // Exception table:
    //   from	to	target	type
    //   169	173	175	java/io/IOException
    //   109	131	178	java/io/IOException
    //   226	230	232	java/io/IOException
    //   109	131	235	finally
    //   184	220	235	finally
    //   240	244	246	java/io/IOException
    //   131	163	250	finally
    //   131	163	256	java/io/IOException
  }
  
  public void addHistoryItem(Result paramResult, ResultHandler paramResultHandler)
  {
    if ((!this.activity.getIntent().getBooleanExtra("SAVE_HISTORY", true)) || (paramResultHandler.areContentsSecure())) {
      return;
    }
    if (!PreferenceManager.getDefaultSharedPreferences(this.activity).getBoolean("preferences_remember_duplicates", false)) {
      deletePrevious(paramResult.getText());
    }
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("text", paramResult.getText());
    localContentValues.put("format", paramResult.getBarcodeFormat().toString());
    localContentValues.put("display", paramResultHandler.getDisplayContents().toString());
    localContentValues.put("timestamp", Long.valueOf(System.currentTimeMillis()));
    paramResultHandler = new DBHelper(this.activity);
    paramResult = null;
    try
    {
      paramResultHandler = paramResultHandler.getWritableDatabase();
      paramResult = paramResultHandler;
      paramResultHandler.insert("history", "timestamp", localContentValues);
      return;
    }
    finally
    {
      close(null, paramResult);
    }
  }
  
  public void addHistoryItemDetails(String paramString1, String paramString2)
  {
    Object localObject4 = new DBHelper(this.activity);
    Object localObject1 = null;
    Object localObject3 = null;
    Object localObject2 = localObject3;
    for (;;)
    {
      try
      {
        localObject4 = ((SQLiteOpenHelper)localObject4).getWritableDatabase();
        localObject1 = localObject4;
        localObject2 = localObject3;
        localCursor = ((SQLiteDatabase)localObject4).query("history", ID_DETAIL_COL_PROJECTION, "text=?", new String[] { paramString1 }, null, null, "timestamp DESC", "1");
        paramString1 = null;
        localObject3 = null;
        localObject1 = localObject4;
        localObject2 = localCursor;
        if (!localCursor.moveToNext()) {
          break label236;
        }
        localObject1 = localObject4;
        localObject2 = localCursor;
        paramString1 = localCursor.getString(0);
        localObject1 = localObject4;
        localObject2 = localCursor;
        localObject3 = localCursor.getString(1);
      }
      finally
      {
        Cursor localCursor;
        close((Cursor)localObject2, (SQLiteDatabase)localObject1);
      }
      localObject1 = localObject4;
      localObject2 = localCursor;
      localObject3 = new ContentValues();
      localObject1 = localObject4;
      localObject2 = localCursor;
      ((ContentValues)localObject3).put("details", paramString2);
      localObject1 = localObject4;
      localObject2 = localCursor;
      ((SQLiteDatabase)localObject4).update("history", (ContentValues)localObject3, "id=?", new String[] { paramString1 });
      close(localCursor, (SQLiteDatabase)localObject4);
      return;
      localObject1 = localObject4;
      localObject2 = localCursor;
      paramString2 = (String)localObject3 + " : " + paramString2;
      continue;
      label236:
      if (paramString1 != null) {
        if (localObject3 != null) {}
      }
    }
  }
  
  CharSequence buildHistory()
  {
    StringBuilder localStringBuilder = new StringBuilder(1000);
    Object localObject2 = new DBHelper(this.activity);
    Object localObject1 = null;
    Cursor localCursor2 = null;
    Cursor localCursor1 = localCursor2;
    try
    {
      localObject2 = ((SQLiteOpenHelper)localObject2).getWritableDatabase();
      localObject1 = localObject2;
      localCursor1 = localCursor2;
      localCursor2 = ((SQLiteDatabase)localObject2).query("history", COLUMNS, null, null, null, null, "timestamp DESC");
      for (;;)
      {
        localObject1 = localObject2;
        localCursor1 = localCursor2;
        if (!localCursor2.moveToNext()) {
          break;
        }
        localObject1 = localObject2;
        localCursor1 = localCursor2;
        localStringBuilder.append('"').append(massageHistoryField(localCursor2.getString(0))).append("\",");
        localObject1 = localObject2;
        localCursor1 = localCursor2;
        localStringBuilder.append('"').append(massageHistoryField(localCursor2.getString(1))).append("\",");
        localObject1 = localObject2;
        localCursor1 = localCursor2;
        localStringBuilder.append('"').append(massageHistoryField(localCursor2.getString(2))).append("\",");
        localObject1 = localObject2;
        localCursor1 = localCursor2;
        localStringBuilder.append('"').append(massageHistoryField(localCursor2.getString(3))).append("\",");
        localObject1 = localObject2;
        localCursor1 = localCursor2;
        long l = localCursor2.getLong(3);
        localObject1 = localObject2;
        localCursor1 = localCursor2;
        localStringBuilder.append('"').append(massageHistoryField(EXPORT_DATE_TIME_FORMAT.format(new Date(l)))).append("\",");
        localObject1 = localObject2;
        localCursor1 = localCursor2;
        localStringBuilder.append('"').append(massageHistoryField(localCursor2.getString(4))).append("\"\r\n");
      }
    }
    finally
    {
      close(localCursor1, (SQLiteDatabase)localObject1);
    }
    return localStringBuilder;
  }
  
  public HistoryItem buildHistoryItem(int paramInt)
  {
    Object localObject2 = new DBHelper(this.activity);
    Object localObject1 = null;
    Cursor localCursor2 = null;
    Cursor localCursor1 = localCursor2;
    try
    {
      localObject2 = ((SQLiteOpenHelper)localObject2).getReadableDatabase();
      localObject1 = localObject2;
      localCursor1 = localCursor2;
      localCursor2 = ((SQLiteDatabase)localObject2).query("history", COLUMNS, null, null, null, null, "timestamp DESC");
      localObject1 = localObject2;
      localCursor1 = localCursor2;
      localCursor2.move(paramInt + 1);
      localObject1 = localObject2;
      localCursor1 = localCursor2;
      Object localObject4 = localCursor2.getString(0);
      localObject1 = localObject2;
      localCursor1 = localCursor2;
      String str1 = localCursor2.getString(1);
      localObject1 = localObject2;
      localCursor1 = localCursor2;
      String str2 = localCursor2.getString(2);
      localObject1 = localObject2;
      localCursor1 = localCursor2;
      long l = localCursor2.getLong(3);
      localObject1 = localObject2;
      localCursor1 = localCursor2;
      String str3 = localCursor2.getString(4);
      localObject1 = localObject2;
      localCursor1 = localCursor2;
      localObject4 = new HistoryItem(new Result((String)localObject4, null, null, BarcodeFormat.valueOf(str2), l), str1, str3);
      return (HistoryItem)localObject4;
    }
    finally
    {
      close(localCursor1, (SQLiteDatabase)localObject1);
    }
  }
  
  public List<HistoryItem> buildHistoryItems()
  {
    Object localObject2 = new DBHelper(this.activity);
    ArrayList localArrayList = new ArrayList();
    Object localObject1 = null;
    Cursor localCursor2 = null;
    Cursor localCursor1 = localCursor2;
    try
    {
      localObject2 = ((SQLiteOpenHelper)localObject2).getReadableDatabase();
      localObject1 = localObject2;
      localCursor1 = localCursor2;
      localCursor2 = ((SQLiteDatabase)localObject2).query("history", COLUMNS, null, null, null, null, "timestamp DESC");
      for (;;)
      {
        localObject1 = localObject2;
        localCursor1 = localCursor2;
        if (!localCursor2.moveToNext()) {
          break;
        }
        localObject1 = localObject2;
        localCursor1 = localCursor2;
        String str1 = localCursor2.getString(0);
        localObject1 = localObject2;
        localCursor1 = localCursor2;
        String str2 = localCursor2.getString(1);
        localObject1 = localObject2;
        localCursor1 = localCursor2;
        String str3 = localCursor2.getString(2);
        localObject1 = localObject2;
        localCursor1 = localCursor2;
        long l = localCursor2.getLong(3);
        localObject1 = localObject2;
        localCursor1 = localCursor2;
        String str4 = localCursor2.getString(4);
        localObject1 = localObject2;
        localCursor1 = localCursor2;
        localArrayList.add(new HistoryItem(new Result(str1, null, null, BarcodeFormat.valueOf(str3), l), str2, str4));
      }
      close(localCursor2, localSQLiteDatabase);
    }
    finally
    {
      close(localCursor1, (SQLiteDatabase)localObject1);
    }
    return localArrayList;
  }
  
  void clearHistory()
  {
    Object localObject2 = new DBHelper(this.activity);
    Object localObject1 = null;
    try
    {
      localObject2 = ((SQLiteOpenHelper)localObject2).getWritableDatabase();
      localObject1 = localObject2;
      ((SQLiteDatabase)localObject2).delete("history", null, null);
      return;
    }
    finally
    {
      close(null, (SQLiteDatabase)localObject1);
    }
  }
  
  public void deleteHistoryItem(int paramInt)
  {
    Object localObject3 = new DBHelper(this.activity);
    Object localObject1 = null;
    Cursor localCursor2 = null;
    Cursor localCursor1 = localCursor2;
    try
    {
      localObject3 = ((SQLiteOpenHelper)localObject3).getWritableDatabase();
      localObject1 = localObject3;
      localCursor1 = localCursor2;
      localCursor2 = ((SQLiteDatabase)localObject3).query("history", ID_COL_PROJECTION, null, null, null, null, "timestamp DESC");
      localObject1 = localObject3;
      localCursor1 = localCursor2;
      localCursor2.move(paramInt + 1);
      localObject1 = localObject3;
      localCursor1 = localCursor2;
      ((SQLiteDatabase)localObject3).delete("history", "id=" + localCursor2.getString(0), null);
      return;
    }
    finally
    {
      close(localCursor1, (SQLiteDatabase)localObject1);
    }
  }
  
  /* Error */
  public boolean hasHistoryItems()
  {
    // Byte code:
    //   0: new 80	com/google/zxing/client/android/history/DBHelper
    //   3: dup
    //   4: aload_0
    //   5: getfield 67	com/google/zxing/client/android/history/HistoryManager:activity	Landroid/app/Activity;
    //   8: invokespecial 83	com/google/zxing/client/android/history/DBHelper:<init>	(Landroid/content/Context;)V
    //   11: astore 6
    //   13: aconst_null
    //   14: astore_3
    //   15: aconst_null
    //   16: astore 5
    //   18: aload 5
    //   20: astore 4
    //   22: aload 6
    //   24: invokevirtual 339	android/database/sqlite/SQLiteOpenHelper:getReadableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   27: astore 6
    //   29: aload 6
    //   31: astore_3
    //   32: aload 5
    //   34: astore 4
    //   36: aload 6
    //   38: ldc 91
    //   40: getstatic 46	com/google/zxing/client/android/history/HistoryManager:COUNT_COLUMN	[Ljava/lang/String;
    //   43: aconst_null
    //   44: aconst_null
    //   45: aconst_null
    //   46: aconst_null
    //   47: aconst_null
    //   48: invokevirtual 313	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   51: astore 5
    //   53: aload 6
    //   55: astore_3
    //   56: aload 5
    //   58: astore 4
    //   60: aload 5
    //   62: invokeinterface 375 1 0
    //   67: pop
    //   68: aload 6
    //   70: astore_3
    //   71: aload 5
    //   73: astore 4
    //   75: aload 5
    //   77: iconst_0
    //   78: invokeinterface 379 2 0
    //   83: istore_1
    //   84: iload_1
    //   85: ifle +14 -> 99
    //   88: iconst_1
    //   89: istore_2
    //   90: aload 5
    //   92: aload 6
    //   94: invokestatic 99	com/google/zxing/client/android/history/HistoryManager:close	(Landroid/database/Cursor;Landroid/database/sqlite/SQLiteDatabase;)V
    //   97: iload_2
    //   98: ireturn
    //   99: iconst_0
    //   100: istore_2
    //   101: goto -11 -> 90
    //   104: astore 5
    //   106: aload 4
    //   108: aload_3
    //   109: invokestatic 99	com/google/zxing/client/android/history/HistoryManager:close	(Landroid/database/Cursor;Landroid/database/sqlite/SQLiteDatabase;)V
    //   112: aload 5
    //   114: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	115	0	this	HistoryManager
    //   83	2	1	i	int
    //   89	12	2	bool	boolean
    //   14	95	3	localObject1	Object
    //   20	87	4	localCursor1	Cursor
    //   16	75	5	localCursor2	Cursor
    //   104	9	5	localObject2	Object
    //   11	82	6	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   22	29	104	finally
    //   36	53	104	finally
    //   60	68	104	finally
    //   75	84	104	finally
  }
  
  /* Error */
  public void trimHistory()
  {
    // Byte code:
    //   0: new 80	com/google/zxing/client/android/history/DBHelper
    //   3: dup
    //   4: aload_0
    //   5: getfield 67	com/google/zxing/client/android/history/HistoryManager:activity	Landroid/app/Activity;
    //   8: invokespecial 83	com/google/zxing/client/android/history/DBHelper:<init>	(Landroid/content/Context;)V
    //   11: astore 5
    //   13: aconst_null
    //   14: astore_3
    //   15: aconst_null
    //   16: astore_1
    //   17: aconst_null
    //   18: astore 7
    //   20: aconst_null
    //   21: astore 6
    //   23: aload 6
    //   25: astore_2
    //   26: aload 7
    //   28: astore 4
    //   30: aload 5
    //   32: invokevirtual 89	android/database/sqlite/SQLiteOpenHelper:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   35: astore 5
    //   37: aload 5
    //   39: astore_1
    //   40: aload 6
    //   42: astore_2
    //   43: aload 5
    //   45: astore_3
    //   46: aload 7
    //   48: astore 4
    //   50: aload 5
    //   52: ldc 91
    //   54: getstatic 50	com/google/zxing/client/android/history/HistoryManager:ID_COL_PROJECTION	[Ljava/lang/String;
    //   57: aconst_null
    //   58: aconst_null
    //   59: aconst_null
    //   60: aconst_null
    //   61: ldc_w 285
    //   64: invokevirtual 313	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   67: astore 6
    //   69: aload 5
    //   71: astore_1
    //   72: aload 6
    //   74: astore_2
    //   75: aload 5
    //   77: astore_3
    //   78: aload 6
    //   80: astore 4
    //   82: aload 6
    //   84: sipush 500
    //   87: invokeinterface 343 2 0
    //   92: pop
    //   93: aload 5
    //   95: astore_1
    //   96: aload 6
    //   98: astore_2
    //   99: aload 5
    //   101: astore_3
    //   102: aload 6
    //   104: astore 4
    //   106: aload 6
    //   108: invokeinterface 294 1 0
    //   113: ifeq +77 -> 190
    //   116: aload 5
    //   118: astore_1
    //   119: aload 6
    //   121: astore_2
    //   122: aload 5
    //   124: astore_3
    //   125: aload 6
    //   127: astore 4
    //   129: aload 5
    //   131: ldc 91
    //   133: new 139	java/lang/StringBuilder
    //   136: dup
    //   137: invokespecial 140	java/lang/StringBuilder:<init>	()V
    //   140: ldc_w 371
    //   143: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   146: aload 6
    //   148: iconst_0
    //   149: invokeinterface 298 2 0
    //   154: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   157: invokevirtual 152	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   160: aconst_null
    //   161: invokevirtual 97	android/database/sqlite/SQLiteDatabase:delete	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
    //   164: pop
    //   165: goto -72 -> 93
    //   168: astore 5
    //   170: aload_1
    //   171: astore_3
    //   172: aload_2
    //   173: astore 4
    //   175: getstatic 28	com/google/zxing/client/android/history/HistoryManager:TAG	Ljava/lang/String;
    //   178: aload 5
    //   180: invokestatic 385	android/util/Log:w	(Ljava/lang/String;Ljava/lang/Throwable;)I
    //   183: pop
    //   184: aload_2
    //   185: aload_1
    //   186: invokestatic 99	com/google/zxing/client/android/history/HistoryManager:close	(Landroid/database/Cursor;Landroid/database/sqlite/SQLiteDatabase;)V
    //   189: return
    //   190: aload 6
    //   192: aload 5
    //   194: invokestatic 99	com/google/zxing/client/android/history/HistoryManager:close	(Landroid/database/Cursor;Landroid/database/sqlite/SQLiteDatabase;)V
    //   197: return
    //   198: astore_1
    //   199: aload 4
    //   201: aload_3
    //   202: invokestatic 99	com/google/zxing/client/android/history/HistoryManager:close	(Landroid/database/Cursor;Landroid/database/sqlite/SQLiteDatabase;)V
    //   205: aload_1
    //   206: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	207	0	this	HistoryManager
    //   16	170	1	localObject1	Object
    //   198	8	1	localObject2	Object
    //   25	160	2	localCursor1	Cursor
    //   14	188	3	localObject3	Object
    //   28	172	4	localObject4	Object
    //   11	119	5	localObject5	Object
    //   168	25	5	localSQLiteException	android.database.sqlite.SQLiteException
    //   21	170	6	localCursor2	Cursor
    //   18	29	7	localObject6	Object
    // Exception table:
    //   from	to	target	type
    //   30	37	168	android/database/sqlite/SQLiteException
    //   50	69	168	android/database/sqlite/SQLiteException
    //   82	93	168	android/database/sqlite/SQLiteException
    //   106	116	168	android/database/sqlite/SQLiteException
    //   129	165	168	android/database/sqlite/SQLiteException
    //   30	37	198	finally
    //   50	69	198	finally
    //   82	93	198	finally
    //   106	116	198	finally
    //   129	165	198	finally
    //   175	184	198	finally
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\history\HistoryManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */