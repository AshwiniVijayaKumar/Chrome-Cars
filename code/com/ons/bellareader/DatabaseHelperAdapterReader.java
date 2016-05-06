package com.ons.bellareader;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.ons.model.BookMarkData;
import java.util.ArrayList;

public class DatabaseHelperAdapterReader
{
  public static final String AUTHOR_NAME = "authorname";
  public static final String BOOKURL = "bookurl";
  public static final String BOOK_NAME = "bookname";
  private static final String DATABASE_CREATE = "create table Bookmarkurls(_id integer primary key autoincrement, pageurl text not null, name text not null,pageno integer not null,authorname text not null,title text not null,bookurl text not null,xvalue integer not null,p_page_value text,bookname text,font_size integer not null);";
  private static final String DATABASE_CREATE1 = "create table Bookstatus(_id integer primary key autoincrement, pageurl text not null,pageno integer not null,authorname text not null,title text not null,bookurl text not null,xvalue integer not null,font_size integer not null);";
  private static final String DATABASE_NAME = "Reader";
  private static final String DATABASE_TABLE = "Bookmarkurls";
  private static final String DATABASE_TABLE1 = "Bookstatus";
  private static final int DATABASE_VERSION = 1;
  public static final String FONT_SIZE = "font_size";
  public static final String Id = "_id";
  public static final String NAME = "name";
  public static final String PAGEURL = "pageurl";
  public static final String PAGE_NO = "pageno";
  public static final String PRE_PAGE_Value = "p_page_value";
  private static final String TAG = "DatabaseHelper";
  public static final String TITLE = "title";
  public static final String XVALUE = "xvalue";
  String[] column = { "_id", "pageurl", "name", "pageno" };
  private Context contex;
  private SQLiteDatabase mDb;
  private DatabaseHelper mDbHelper;
  
  public DatabaseHelperAdapterReader(Context paramContext)
  {
    this.contex = paramContext;
  }
  
  public void addBookStatus(String paramString1, int paramInt1, String paramString2, String paramString3, String paramString4, int paramInt2, int paramInt3)
  {
    open();
    if (this.mDb.rawQuery("SELECT * FROM Bookstatus where bookurl='" + paramString4 + "'", null).getCount() > 0)
    {
      paramString2 = new ContentValues();
      paramString2.put("pageurl", paramString1);
      paramString2.put("pageno", Integer.valueOf(paramInt1));
      paramString2.put("xvalue", Integer.valueOf(paramInt2));
      paramString2.put("font_size", Integer.valueOf(paramInt3));
      this.mDb.update("Bookstatus", paramString2, "bookurl = '" + paramString4 + "'", null);
      close();
      return;
    }
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("pageurl", paramString1);
    localContentValues.put("pageno", Integer.valueOf(paramInt1));
    localContentValues.put("authorname", paramString2);
    localContentValues.put("title", paramString3);
    localContentValues.put("bookurl", paramString4);
    localContentValues.put("xvalue", Integer.valueOf(paramInt2));
    localContentValues.put("font_size", Integer.valueOf(paramInt3));
    this.mDb.insert("Bookstatus", null, localContentValues);
    close();
  }
  
  public boolean addValues(String paramString1, String paramString2, int paramInt1, String paramString3, String paramString4, String paramString5, int paramInt2, String paramString6, String paramString7, int paramInt3)
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
    localContentValues.put("p_page_value", paramString6);
    localContentValues.put("bookname", paramString7);
    localContentValues.put("font_size", Integer.valueOf(paramInt3));
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
  
  public Cursor getBookStatus(String paramString)
  {
    Log.e("book url>>>>>>>>>>>>>>", paramString);
    return this.mDb.rawQuery("SELECT * FROM Bookstatus where bookurl='" + paramString + "'", null);
  }
  
  public void getValues()
  {
    Bookmark.bookmarkList.clear();
    open();
    Cursor localCursor = this.mDb.rawQuery("SELECT * FROM Bookmarkurls", null);
    if (localCursor != null) {
      while (localCursor.moveToNext())
      {
        BookMarkData localBookMarkData = new BookMarkData();
        localBookMarkData.setId(localCursor.getInt(0));
        localBookMarkData.setBookmarkName(localCursor.getString(2));
        localBookMarkData.setUrl(localCursor.getString(1));
        localBookMarkData.setPageno(localCursor.getInt(3));
        localBookMarkData.setAuthorName(localCursor.getString(4));
        localBookMarkData.setTitle(localCursor.getString(5));
        localBookMarkData.setBookurl(localCursor.getString(6));
        localBookMarkData.setXvalue(localCursor.getInt(7));
        localBookMarkData.setP_page_value(localCursor.getString(8));
        localBookMarkData.setBookName(localCursor.getString(9));
        localBookMarkData.setFontsize(localCursor.getInt(10));
        localBookMarkData.setStatus(false);
        Bookmark.bookmarkList.add(localBookMarkData);
      }
    }
    close();
  }
  
  public DatabaseHelperAdapterReader open()
    throws SQLException
  {
    this.mDbHelper = new DatabaseHelper(this.contex);
    this.mDb = this.mDbHelper.getWritableDatabase();
    return this;
  }
  
  public void searchBookmarkByName(String paramString)
  {
    Bookmark.bookmarkList.clear();
    open();
    paramString = this.mDb.rawQuery("SELECT * FROM Bookmarkurls where name='" + paramString + "'", null);
    if (paramString != null) {
      while (paramString.moveToNext())
      {
        BookMarkData localBookMarkData = new BookMarkData();
        localBookMarkData.setId(paramString.getInt(0));
        localBookMarkData.setBookmarkName(paramString.getString(2));
        localBookMarkData.setUrl(paramString.getString(1));
        localBookMarkData.setPageno(paramString.getInt(3));
        localBookMarkData.setAuthorName(paramString.getString(4));
        localBookMarkData.setTitle(paramString.getString(5));
        localBookMarkData.setBookurl(paramString.getString(6));
        localBookMarkData.setStatus(false);
        Bookmark.bookmarkList.add(localBookMarkData);
      }
    }
    close();
  }
  
  private static class DatabaseHelper
    extends SQLiteOpenHelper
  {
    DatabaseHelper(Context paramContext)
    {
      super("Reader", null, 1);
    }
    
    public void onCreate(SQLiteDatabase paramSQLiteDatabase)
    {
      paramSQLiteDatabase.execSQL("create table Bookmarkurls(_id integer primary key autoincrement, pageurl text not null, name text not null,pageno integer not null,authorname text not null,title text not null,bookurl text not null,xvalue integer not null,p_page_value text,bookname text,font_size integer not null);");
      paramSQLiteDatabase.execSQL("create table Bookstatus(_id integer primary key autoincrement, pageurl text not null,pageno integer not null,authorname text not null,title text not null,bookurl text not null,xvalue integer not null,font_size integer not null);");
    }
    
    public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
    {
      Log.w("DatabaseHelper", "Upgrading database from version " + paramInt1 + " to " + paramInt2 + ", which will destroy all old data");
      paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS Bookmarkurls");
      paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS Bookstatus");
      onCreate(paramSQLiteDatabase);
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ons\bellareader\DatabaseHelperAdapterReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */