package com.example.example75f1799f07eb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelperAdapter
{
  public static final String AUTHOR_NAME = "authorname";
  public static final String BOOKURL = "bookurl";
  private static final String DATABASE_CREATE = "create table Bookmarkurls(_id integer primary key autoincrement, pageurl text not null, name text not null,pageno integer not null,authorname text not null,title text not null,bookurl text not null,xvalue integer not null);";
  private static final String DATABASE_CREATE_APPLOGIN = "create table AppLoginTable(_id integer primary key autoincrement, username text not null);";
  private static final String DATABASE_CREATE_ECOMMLOGIN = "create table EcommLoginTable(_id integer primary key autoincrement, username text not null, password text not null);";
  private static final String DATABASE_CREATE_LOGIN = "create table LoginTable(_id integer primary key autoincrement, username text not null, password text not null);";
  private static final String DATABASE_NAME = "Bookmark";
  private static final String DATABASE_TABLE = "Bookmarkurls";
  private static final int DATABASE_VERSION = 12;
  public static final String Id = "_id";
  public static final String NAME = "name";
  public static final String PAGEURL = "pageurl";
  public static final String PAGE_NO = "pageno";
  private static final String TAG = "DatabaseHelper";
  public static final String TITLE = "title";
  public static final String XVALUE = "xvalue";
  private Context contex;
  private SQLiteDatabase mDb;
  private DatabaseHelper mDbHelper;
  
  public DatabaseHelperAdapter(Context paramContext)
  {
    this.contex = paramContext;
  }
  
  public boolean addValues(String paramString1, String paramString2, int paramInt1, String paramString3, String paramString4, String paramString5, int paramInt2)
  {
    open();
    if (this.mDb.rawQuery("SELECT * FROM Bookmarkurls where pageurl='" + paramString2 + "' and pageno=" + paramInt1, null).getCount() > 0)
    {
      close();
      return true;
    }
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("pageurl", paramString2);
    localContentValues.put("name", paramString1);
    localContentValues.put("pageno", Integer.valueOf(paramInt1));
    localContentValues.put("authorname", paramString3);
    localContentValues.put("title", paramString4);
    localContentValues.put("bookurl", paramString5);
    localContentValues.put("xvalue", Integer.valueOf(paramInt2));
    this.mDb.insert("Bookmarkurls", null, localContentValues);
    close();
    return false;
  }
  
  public void close()
  {
    this.mDbHelper.close();
  }
  
  public void deleteBookmarkByBookname(String paramString)
  {
    open();
    this.mDb.delete("Bookmarkurls", "title='" + paramString + "'", null);
    close();
  }
  
  public void deleteRow(int paramInt)
  {
    open();
    this.mDb.delete("Bookmarkurls", "_id='" + paramInt + "'", null);
    close();
  }
  
  public String insertIntoAppLoginTable(String paramString)
  {
    open();
    Object localObject = "";
    Cursor localCursor = this.mDb.rawQuery("SELECT * FROM AppLoginTable", null);
    if (localCursor.getCount() > 0)
    {
      for (paramString = (String)localObject; localCursor.moveToNext(); paramString = paramString + "" + localCursor.getString(1)) {}
      close();
      return paramString;
    }
    localObject = new ContentValues();
    if (!paramString.equals(""))
    {
      ((ContentValues)localObject).put("username", paramString);
      this.mDb.insert("AppLoginTable", null, (ContentValues)localObject);
    }
    close();
    return "";
  }
  
  public String insertIntoEcommLoginTable(String paramString1, String paramString2)
  {
    open();
    Object localObject = "";
    Cursor localCursor = this.mDb.rawQuery("SELECT * FROM EcommLoginTable", null);
    if (localCursor.getCount() > 0)
    {
      for (paramString1 = (String)localObject; localCursor.moveToNext(); paramString1 = paramString1 + "" + localCursor.getString(1) + "/" + localCursor.getString(2)) {}
      close();
      return paramString1;
    }
    localObject = new ContentValues();
    if ((!paramString1.equals("")) && (!paramString2.equals("")))
    {
      ((ContentValues)localObject).put("username", paramString1);
      ((ContentValues)localObject).put("password", paramString2);
      this.mDb.insert("EcommLoginTable", null, (ContentValues)localObject);
    }
    close();
    return "";
  }
  
  public String insertIntoFoodLoginTable(String paramString1, String paramString2)
  {
    open();
    Object localObject = "";
    Cursor localCursor = this.mDb.rawQuery("SELECT * FROM LoginTable", null);
    if (localCursor.getCount() > 0)
    {
      for (paramString1 = (String)localObject; localCursor.moveToNext(); paramString1 = paramString1 + "" + localCursor.getString(1) + "/" + localCursor.getString(2)) {}
      close();
      return paramString1;
    }
    localObject = new ContentValues();
    if ((!paramString1.equals("")) && (!paramString2.equals("")))
    {
      ((ContentValues)localObject).put("username", paramString1);
      ((ContentValues)localObject).put("password", paramString2);
      this.mDb.insert("LoginTable", null, (ContentValues)localObject);
    }
    close();
    return "";
  }
  
  public void logout(String paramString)
  {
    if (paramString.equalsIgnoreCase("food"))
    {
      open();
      this.mDb.delete("LoginTable", null, null);
      close();
    }
    while (!paramString.equalsIgnoreCase("ecomm")) {
      return;
    }
    open();
    this.mDb.delete("EcommLoginTable", null, null);
    close();
  }
  
  public DatabaseHelperAdapter open()
    throws SQLException
  {
    this.mDbHelper = new DatabaseHelper(this.contex);
    this.mDb = this.mDbHelper.getWritableDatabase();
    return this;
  }
  
  private static class DatabaseHelper
    extends SQLiteOpenHelper
  {
    DatabaseHelper(Context paramContext)
    {
      super("Bookmark", null, 12);
    }
    
    public void onCreate(SQLiteDatabase paramSQLiteDatabase)
    {
      paramSQLiteDatabase.execSQL("create table LoginTable(_id integer primary key autoincrement, username text not null, password text not null);");
      paramSQLiteDatabase.execSQL("create table AppLoginTable(_id integer primary key autoincrement, username text not null);");
      paramSQLiteDatabase.execSQL("create table EcommLoginTable(_id integer primary key autoincrement, username text not null, password text not null);");
    }
    
    public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
    {
      Log.w("DatabaseHelper", "Upgrading database from version " + paramInt1 + " to " + paramInt2 + ", which will destroy all old data");
      paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS Bookmarkurls");
      paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS LoginTable");
      paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS AppLoginTable");
      paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS EcommLoginTable");
      onCreate(paramSQLiteDatabase);
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\example\example75f1799f07eb\DatabaseHelperAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */