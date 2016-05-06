package com.phonegap.plugins.ebookViewer;

import android.app.ProgressDialog;
import android.content.Intent;
import com.ons.bellareader.EpubManipulator;
import com.ons.bellareader.EpubReaderMain;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.List;
import nl.siegmann.epublib.domain.Author;
import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Metadata;
import nl.siegmann.epublib.epub.EpubReader;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class EbookUnzip
  extends CordovaPlugin
{
  String authorname;
  private Book book;
  private EpubManipulator book1;
  File file;
  String filePath;
  public ProgressDialog loadingdialog;
  
  private void doSendIntent(String paramString1, String paramString2)
  {
    System.out.println("unzip----------url=" + paramString1 + "----info=" + paramString2);
    this.file = new File(paramString1);
    this.filePath = this.file.getAbsolutePath().substring(6);
    try
    {
      paramString1 = new FileInputStream(this.filePath);
      this.book = new EpubReader().readEpub(paramString1);
      paramString1 = this.book.getMetadata().getAuthors();
      if (paramString1.size() > 0) {
        this.authorname = (((Author)paramString1.get(0)).getFirstname() + " " + ((Author)paramString1.get(0)).getLastname());
      }
      System.out.println("filepath=" + this.filePath + "---filename=" + this.file.getName());
      this.book1 = new EpubManipulator(this.filePath, this.file.getName());
      EpubReaderMain.bookurl = this.filePath;
      paramString1 = new Intent(this.cordova.getActivity(), EpubReaderMain.class);
      System.out.println("url=" + this.filePath + " info = " + this.file.getName());
      paramString1.putExtra("url", this.filePath);
      paramString1.putExtra("info", this.file.getName());
      paramString1.putExtra("activityName", "Test");
      paramString1.putExtra("title", this.file.getName());
      paramString1.putExtra("authorname", this.authorname);
      this.cordova.startActivityForResult(this, paramString1, 1);
      return;
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
  }
  
  public boolean execute(String paramString, JSONArray paramJSONArray, CallbackContext paramCallbackContext)
  {
    boolean bool = false;
    paramString = PluginResult.Status.OK;
    try
    {
      paramString = paramJSONArray.getJSONObject(0);
      doSendIntent(paramString.getString("url"), paramString.getString("info"));
      paramString = PluginResult.Status.OK;
      if (paramString == PluginResult.Status.OK) {
        bool = true;
      }
      return bool;
    }
    catch (JSONException paramString)
    {
      for (;;)
      {
        paramString = PluginResult.Status.JSON_EXCEPTION;
      }
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\phonegap\plugins\ebookViewer\EbookUnzip.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */