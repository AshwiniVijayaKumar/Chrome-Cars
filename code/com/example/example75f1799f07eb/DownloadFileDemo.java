package com.example.example75f1799f07eb;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadFileDemo
  extends Activity
{
  TextView cur_val;
  Dialog dialog;
  int downloadedSize = 0;
  String dwnload_file_path;
  File file;
  ProgressBar pb;
  int totalSize = 0;
  
  void downloadFile()
  {
    try
    {
      Object localObject1 = (HttpURLConnection)new URL(this.dwnload_file_path).openConnection();
      ((HttpURLConnection)localObject1).setRequestMethod("GET");
      ((HttpURLConnection)localObject1).setDoOutput(true);
      ((HttpURLConnection)localObject1).connect();
      Object localObject2 = this.dwnload_file_path.substring(this.dwnload_file_path.lastIndexOf("/") + 1);
      this.file = new File(Environment.getExternalStorageDirectory(), (String)localObject2);
      if (this.file.exists())
      {
        this.dialog.cancel();
        pdfView(this.file);
        return;
      }
      localObject2 = new FileOutputStream(this.file);
      InputStream localInputStream = ((HttpURLConnection)localObject1).getInputStream();
      this.totalSize = ((HttpURLConnection)localObject1).getContentLength();
      runOnUiThread(new Runnable()
      {
        public void run()
        {
          DownloadFileDemo.this.pb.setMax(DownloadFileDemo.this.totalSize);
        }
      });
      localObject1 = new byte['Ѐ'];
      for (;;)
      {
        int i = localInputStream.read((byte[])localObject1);
        if (i <= 0) {
          break;
        }
        ((FileOutputStream)localObject2).write((byte[])localObject1, 0, i);
        this.downloadedSize += i;
        runOnUiThread(new Runnable()
        {
          public void run()
          {
            DownloadFileDemo.this.pb.setProgress(DownloadFileDemo.this.downloadedSize);
            float f = DownloadFileDemo.this.downloadedSize / DownloadFileDemo.this.totalSize;
            DownloadFileDemo.this.cur_val.setText("Downloaded " + DownloadFileDemo.this.downloadedSize + "KB / " + DownloadFileDemo.this.totalSize + "KB (" + (int)(f * 100.0F) + "%)");
          }
        });
      }
      return;
    }
    catch (MalformedURLException localMalformedURLException)
    {
      showError("Error : MalformedURLException " + localMalformedURLException);
      localMalformedURLException.printStackTrace();
      return;
      ((FileOutputStream)localObject2).close();
      runOnUiThread(new Runnable()
      {
        public void run()
        {
          DownloadFileDemo.this.dialog.cancel();
          DownloadFileDemo.this.pdfView(DownloadFileDemo.this.file);
        }
      });
      return;
    }
    catch (IOException localIOException)
    {
      showError("Error : IOException " + localIOException);
      localIOException.printStackTrace();
      return;
    }
    catch (Exception localException)
    {
      showError("Error : Please check your internet connection " + localException);
    }
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.dwnload_file_path = getIntent().getExtras().getString("Url");
    this.dwnload_file_path = this.dwnload_file_path.replaceAll(" ", "%20");
    showProgress(this.dwnload_file_path);
    new Thread(new Runnable()
    {
      public void run()
      {
        DownloadFileDemo.this.downloadFile();
      }
    }).start();
  }
  
  void pdfView(File paramFile)
  {
    try
    {
      Intent localIntent = new Intent();
      localIntent.setDataAndType(Uri.fromFile(paramFile), "application/pdf");
      localIntent.setAction("android.intent.action.VIEW");
      localIntent.setFlags(67108864);
      startActivity(localIntent);
      finish();
      return;
    }
    catch (Exception paramFile)
    {
      startActivityForResult(new Intent("android.intent.action.VIEW", Uri.parse("https://docs.google.com/gview?embedded=true&url=" + this.dwnload_file_path)), 1);
    }
  }
  
  void showError(final String paramString)
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        Toast.makeText(DownloadFileDemo.this, paramString, 1).show();
      }
    });
  }
  
  void showProgress(String paramString)
  {
    this.dialog = new Dialog(this);
    this.dialog.requestWindowFeature(1);
    this.dialog.setContentView(2130903110);
    this.dialog.setTitle("Download Progress");
    ((TextView)this.dialog.findViewById(2131558615)).setText("Downloading file....");
    this.cur_val = ((TextView)this.dialog.findViewById(2131558616));
    this.cur_val.setText("Starting download...");
    this.dialog.show();
    this.pb = ((ProgressBar)this.dialog.findViewById(2131558617));
    this.pb.setProgress(0);
    this.pb.setProgressDrawable(getResources().getDrawable(2130837593));
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\example\example75f1799f07eb\DownloadFileDemo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */