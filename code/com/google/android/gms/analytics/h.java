package com.google.android.gms.analytics;

import android.content.Context;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

class h
  implements m
{
  private static final Object qI = new Object();
  private static h qW;
  private final Context mContext;
  private String qX;
  private boolean qY = false;
  private final Object qZ = new Object();
  
  protected h(Context paramContext)
  {
    this.mContext = paramContext;
    bx();
  }
  
  public static h bu()
  {
    synchronized (qI)
    {
      h localh = qW;
      return localh;
    }
  }
  
  private String bv()
  {
    if (!this.qY) {}
    synchronized (this.qZ)
    {
      if (!this.qY) {
        aa.v("Waiting for clientId to load");
      }
      try
      {
        do
        {
          this.qZ.wait();
        } while (!this.qY);
        aa.v("Loaded clientId");
        return this.qX;
      }
      catch (InterruptedException localInterruptedException)
      {
        for (;;)
        {
          aa.t("Exception while waiting for clientId: " + localInterruptedException);
        }
      }
    }
  }
  
  private void bx()
  {
    new Thread("client_id_fetcher")
    {
      public void run()
      {
        synchronized (h.a(h.this))
        {
          h.a(h.this, h.this.by());
          h.a(h.this, true);
          h.a(h.this).notifyAll();
          return;
        }
      }
    }.start();
  }
  
  public static void n(Context paramContext)
  {
    synchronized (qI)
    {
      if (qW == null) {
        qW = new h(paramContext);
      }
      return;
    }
  }
  
  private boolean y(String paramString)
  {
    try
    {
      aa.v("Storing clientId.");
      FileOutputStream localFileOutputStream = this.mContext.openFileOutput("gaClientId", 0);
      localFileOutputStream.write(paramString.getBytes());
      localFileOutputStream.close();
      return true;
    }
    catch (FileNotFoundException paramString)
    {
      aa.t("Error creating clientId file.");
      return false;
    }
    catch (IOException paramString)
    {
      aa.t("Error writing to clientId file.");
    }
    return false;
  }
  
  protected String bw()
  {
    String str2 = UUID.randomUUID().toString().toLowerCase();
    String str1 = str2;
    try
    {
      if (!y(str2)) {
        str1 = "0";
      }
      return str1;
    }
    catch (Exception localException) {}
    return null;
  }
  
  String by()
  {
    localObject4 = null;
    Object localObject3 = null;
    for (;;)
    {
      try
      {
        localFileInputStream = this.mContext.openFileInput("gaClientId");
        localObject1 = new byte[''];
        i = localFileInputStream.read((byte[])localObject1, 0, 128);
        if (localFileInputStream.available() <= 0) {
          continue;
        }
        aa.t("clientId file seems corrupted, deleting it.");
        localFileInputStream.close();
        this.mContext.deleteFile("gaClientId");
        localObject1 = localObject3;
      }
      catch (IOException localIOException1)
      {
        try
        {
          FileInputStream localFileInputStream;
          Object localObject1;
          int i;
          localFileInputStream.close();
        }
        catch (IOException localIOException2)
        {
          Object localObject2;
          continue;
        }
        catch (FileNotFoundException localFileNotFoundException3) {}
        localIOException1 = localIOException1;
        localObject2 = localObject4;
        aa.t("Error reading clientId file, deleting it.");
        this.mContext.deleteFile("gaClientId");
        continue;
        continue;
      }
      catch (FileNotFoundException localFileNotFoundException1)
      {
        FileNotFoundException localFileNotFoundException2 = localFileNotFoundException3;
        continue;
      }
      localObject3 = localObject1;
      if (localObject1 == null) {
        localObject3 = bw();
      }
      return (String)localObject3;
      if (i > 0) {
        continue;
      }
      aa.t("clientId file seems empty, deleting it.");
      localFileInputStream.close();
      this.mContext.deleteFile("gaClientId");
      localObject1 = localObject3;
    }
    localObject1 = new String((byte[])localObject1, 0, i);
  }
  
  public String getValue(String paramString)
  {
    if ("&cid".equals(paramString)) {
      return bv();
    }
    return null;
  }
  
  public boolean x(String paramString)
  {
    return "&cid".equals(paramString);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\analytics\h.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */