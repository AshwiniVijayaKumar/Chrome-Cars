package com.ons.bellareader;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy.Builder;
import android.util.Log;
import android.widget.Toast;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import nl.siegmann.epublib.domain.Author;
import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Metadata;
import nl.siegmann.epublib.epub.EpubReader;

public class Test
  extends Activity
{
  String authorname;
  boolean b;
  private Book book;
  private EpubManipulator book1;
  String filePath;
  public ProgressDialog loadingdialog;
  File mFile;
  String url;
  
  @SuppressLint({"NewApi"})
  public boolean downloadFile(String paramString, File paramFile)
  {
    try
    {
      StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
      paramString = new URL(paramString);
      int i = paramString.openConnection().getContentLength();
      if (i > 0)
      {
        DataInputStream localDataInputStream = new DataInputStream(paramString.openStream());
        paramString = new byte[i];
        localDataInputStream.readFully(paramString);
        localDataInputStream.close();
        paramFile = new DataOutputStream(new FileOutputStream(paramFile));
        paramFile.write(paramString);
        paramFile.flush();
        paramFile.close();
        this.filePath = this.mFile.getAbsolutePath().substring(6);
        this.filePath = this.filePath.substring(2);
        this.filePath = ("/storage" + this.filePath);
        paramString = new FileInputStream(this.filePath);
        this.book = new EpubReader().readEpub(paramString);
        paramString = this.book.getMetadata().getAuthors();
        if (paramString.size() > 0) {
          this.authorname = (((Author)paramString.get(0)).getFirstname() + " " + ((Author)paramString.get(0)).getLastname());
        }
        try
        {
          this.book1 = new EpubManipulator(this.filePath, this.mFile.getName());
          paramString = new Intent(this, EpubReaderMain.class);
          paramString.putExtra("url", this.filePath);
          paramString.putExtra("info", this.mFile.getName());
          paramString.putExtra("activityName", "Test");
          paramString.putExtra("title", this.mFile.getName());
          paramString.putExtra("authorname", this.authorname);
          startActivity(paramString);
          finish();
          return true;
        }
        catch (Exception paramString)
        {
          return false;
        }
      }
      return false;
    }
    catch (FileNotFoundException paramString)
    {
      return false;
    }
    catch (IOException paramString) {}
    return false;
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903152);
    paramBundle = getIntent().getData();
    this.url = paramBundle.toString();
    System.out.println("*******************" + this.url);
    System.out.println("??????????????????" + paramBundle.getEncodedPath());
    paramBundle = this.url.substring(7).replace("/", "");
    System.out.println("55555555555" + paramBundle);
    this.mFile = new File(Environment.getExternalStorageDirectory(), "Downloads/" + paramBundle);
    populateData();
  }
  
  public void populateData()
  {
    this.loadingdialog = ProgressDialog.show(this, "Please wait", "", true);
    new Thread()
    {
      public void run()
      {
        Test.this.b = Test.this.downloadFile(Test.this.url, Test.this.mFile);
        try
        {
          Test.this.runOnUiThread(new Runnable()
          {
            public void run()
            {
              if (Test.this.b == true)
              {
                Test.this.loadingdialog.dismiss();
                return;
              }
              Toast.makeText(Test.this.getApplicationContext(), "Loading error", 0).show();
              Test.this.loadingdialog.dismiss();
            }
          });
          return;
        }
        catch (Exception localException)
        {
          Log.i("---", "Exception in thread");
        }
      }
    }.start();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ons\bellareader\Test.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */