package com.example.example75f1799f07eb;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Locale;
import java.util.TimeZone;

public class DatabaseHelper
  extends SQLiteOpenHelper
{
  private static String DB_NAME = "demoDB.db";
  public static String DB_PATH = "/data/data/com.example.developer.sample/databases/";
  private static final String METHOD_NAME = "serviceJobDsipatchWebService";
  private static final String NAMESPACE = "http://ofbiz.apache.org/service/";
  private static final String SOAP_ACTION = "http://180.151.3.6:8080/webtools/control/SOAPService/";
  private static final String URL = "http://180.151.3.6:8080/webtools/control/SOAPService";
  String CURRENT_TECHY_ID = "";
  Calendar cal;
  HashSet<String> collectionJobId = new HashSet();
  HashSet<String> collectionParty = new HashSet();
  String[] job = { "J1", "JOB2", "JOB3" };
  String[] joblocation = { "JOB Location1", "JOB Location2", "JOB Location3" };
  private final Context myContext;
  private SQLiteDatabase myDataBase;
  
  public DatabaseHelper(Context paramContext)
  {
    super(paramContext, DB_NAME, null, 1);
    this.myContext = paramContext;
  }
  
  private boolean checkDataBase()
  {
    Object localObject = null;
    try
    {
      SQLiteDatabase localSQLiteDatabase = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, 1);
      localObject = localSQLiteDatabase;
    }
    catch (SQLiteException localSQLiteException)
    {
      for (;;) {}
    }
    if (localObject != null) {
      ((SQLiteDatabase)localObject).close();
    }
    return localObject != null;
  }
  
  private void copyDataBase()
    throws IOException
  {
    InputStream localInputStream = this.myContext.getAssets().open(DB_NAME);
    FileOutputStream localFileOutputStream = new FileOutputStream(DB_PATH + DB_NAME);
    byte[] arrayOfByte = new byte['Ѐ'];
    for (;;)
    {
      int i = localInputStream.read(arrayOfByte);
      if (i <= 0) {
        break;
      }
      localFileOutputStream.write(arrayOfByte, 0, i);
    }
    localFileOutputStream.flush();
    localFileOutputStream.close();
    localInputStream.close();
  }
  
  public void createDataBase()
    throws IOException
  {
    if (checkDataBase()) {
      return;
    }
    getReadableDatabase();
    try
    {
      copyDataBase();
      return;
    }
    catch (IOException localIOException)
    {
      throw new Error("Error copying database");
    }
  }
  
  public String getDate()
  {
    this.cal = Calendar.getInstance(TimeZone.getTimeZone("UTS"), Locale.US);
    return "" + (this.cal.get(2) + 1) + "/" + this.cal.get(5) + "/" + this.cal.get(1);
  }
  
  public void onCreate(SQLiteDatabase paramSQLiteDatabase) {}
  
  public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2) {}
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\example\example75f1799f07eb\DatabaseHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */