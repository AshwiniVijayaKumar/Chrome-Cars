package net.roarsoftware.lastfm.cache;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class DatabaseCache
  extends Cache
{
  protected static final String TABLE_NAME = "LASTFM_CACHE";
  protected Connection conn;
  
  public DatabaseCache(Connection paramConnection)
    throws SQLException
  {
    this.conn = paramConnection;
    if (!this.conn.getMetaData().getTables(null, null, "LASTFM_CACHE", null).next()) {
      createTable();
    }
  }
  
  public void clear()
  {
    try
    {
      PreparedStatement localPreparedStatement = this.conn.prepareStatement("DELETE FROM LASTFM_CACHE;");
      localPreparedStatement.execute();
      localPreparedStatement.close();
      return;
    }
    catch (SQLException localSQLException) {}
  }
  
  public boolean contains(String paramString)
  {
    try
    {
      PreparedStatement localPreparedStatement = this.conn.prepareStatement("SELECT key FROM LASTFM_CACHE WHERE key = ?;");
      localPreparedStatement.setString(1, paramString);
      boolean bool = localPreparedStatement.executeQuery().next();
      localPreparedStatement.close();
      return bool;
    }
    catch (SQLException paramString) {}
    return false;
  }
  
  protected void createTable()
    throws SQLException
  {
    PreparedStatement localPreparedStatement = this.conn.prepareStatement("CREATE TABLE LASTFM_CACHE (key varchar, expiration_date timestamp, response longvarchar);");
    localPreparedStatement.execute();
    localPreparedStatement.close();
  }
  
  public boolean isExpired(String paramString)
  {
    try
    {
      PreparedStatement localPreparedStatement = this.conn.prepareStatement("SELECT expiration_date FROM LASTFM_CACHE WHERE key = ?;");
      localPreparedStatement.setString(1, paramString);
      paramString = localPreparedStatement.executeQuery();
      if (paramString.next())
      {
        long l1 = paramString.getTimestamp("expiration_date").getTime();
        localPreparedStatement.close();
        long l2 = System.currentTimeMillis();
        return l1 < l2;
      }
    }
    catch (SQLException paramString) {}
    return false;
  }
  
  public InputStream load(String paramString)
  {
    try
    {
      PreparedStatement localPreparedStatement = this.conn.prepareStatement("SELECT response FROM LASTFM_CACHE WHERE key = ?;");
      localPreparedStatement.setString(1, paramString);
      paramString = localPreparedStatement.executeQuery();
      if (paramString.next())
      {
        paramString = paramString.getString("response");
        localPreparedStatement.close();
        return new ByteArrayInputStream(paramString.getBytes("UTF-8"));
      }
      localPreparedStatement.close();
    }
    catch (UnsupportedEncodingException paramString)
    {
      for (;;) {}
    }
    catch (SQLException paramString)
    {
      for (;;) {}
    }
    return null;
  }
  
  public void remove(String paramString)
  {
    try
    {
      PreparedStatement localPreparedStatement = this.conn.prepareStatement("DELETE FROM LASTFM_CACHE WHERE key = ?;");
      localPreparedStatement.setString(1, paramString);
      localPreparedStatement.execute();
      localPreparedStatement.close();
      return;
    }
    catch (SQLException paramString) {}
  }
  
  public void store(String paramString, InputStream paramInputStream, long paramLong)
  {
    try
    {
      Object localObject = new InputStreamReader(paramInputStream);
      paramInputStream = new StringBuilder(paramInputStream.available());
      char[] arrayOfChar = new char['ࠀ'];
      for (;;)
      {
        int i = ((InputStreamReader)localObject).read(arrayOfChar, 0, arrayOfChar.length);
        if (i == -1) {
          break;
        }
        paramInputStream.append(arrayOfChar, 0, i);
      }
      return;
    }
    catch (SQLException paramString)
    {
      paramString.printStackTrace();
      return;
      localObject = this.conn.prepareStatement("INSERT INTO LASTFM_CACHE (key, expiration_date, response) VALUES(?, ?, ?);");
      ((PreparedStatement)localObject).setString(1, paramString);
      ((PreparedStatement)localObject).setTimestamp(2, new Timestamp(paramLong));
      ((PreparedStatement)localObject).setString(3, paramInputStream.toString());
      ((PreparedStatement)localObject).execute();
      ((PreparedStatement)localObject).close();
      return;
    }
    catch (UnsupportedEncodingException paramString)
    {
      paramString.printStackTrace();
      return;
    }
    catch (IOException paramString)
    {
      paramString.printStackTrace();
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\net\roarsoftware\lastfm\cache\DatabaseCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */