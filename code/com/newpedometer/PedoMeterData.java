package com.newpedometer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class PedoMeterData
  extends SQLiteOpenHelper
{
  public static final String CREATE_TABLE_DATABASE = "create table pedometer(Date  TEXT PRIMARY KEY,Record TEXT)";
  public static final String DATABASE_NAME = "pedometer.db";
  public static final String DATA_TABLE = "pedometer";
  public static final String DATE_ID = "Date";
  public static final String RECORD_BODY = "Record";
  public static String path = new File(Environment.getExternalStorageDirectory() + "/pedometer/database/").getAbsolutePath();
  
  public PedoMeterData(Context paramContext)
  {
    super(paramContext, "pedometer.db", null, 1);
  }
  
  public ArrayList<HashMap<String, String>> getAllMessages()
  {
    SQLiteDatabase localSQLiteDatabase = getReadableDatabase();
    ArrayList localArrayList = new ArrayList();
    Log.e("query is : ", "select * from pedometer");
    Log.e("query is : ", "select * from pedometer");
    Log.e("query is : ", "select * from pedometer");
    Cursor localCursor = localSQLiteDatabase.rawQuery("select * from pedometer", null);
    Log.e("cursor size is : ", "" + localCursor.getCount());
    Log.e("cursor size is : ", "" + localCursor.getCount());
    while (localCursor.moveToNext())
    {
      Log.e("message  : ", localCursor.getString(0));
      Log.e("message text : ", localCursor.getString(1));
      HashMap localHashMap = new HashMap();
      String str1 = localCursor.getString(0);
      String str2 = localCursor.getString(1);
      localHashMap.put("recorddate", str1);
      localHashMap.put("record", str2);
      localArrayList.add(localHashMap);
    }
    localCursor.close();
    localSQLiteDatabase.close();
    return localArrayList;
  }
  
  public void insertIntoChatTable(String paramString1, String paramString2)
  {
    SQLiteDatabase localSQLiteDatabase = getWritableDatabase();
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("Date", paramString1);
    localContentValues.put("Record", paramString2);
    Log.e("Record date is : ", paramString1);
    Log.e("record value is : ", paramString2);
    localSQLiteDatabase.insert("pedometer", null, localContentValues);
  }
  
  public void onCreate(SQLiteDatabase paramSQLiteDatabase)
  {
    paramSQLiteDatabase.execSQL("create table pedometer(Date  TEXT PRIMARY KEY,Record TEXT)");
  }
  
  public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
  {
    paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS pedometer");
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\newpedometer\PedoMeterData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */