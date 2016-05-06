package com.phonegap.plugins.ebookViewer;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import com.ons.bellareader.DatabaseHelperAdapterReader;
import com.ons.bellareader.EpubReaderMain;
import java.io.FileInputStream;
import java.io.InputStream;
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

public class EbookViewer
  extends CordovaPlugin
{
  private Book book;
  
  private void doSendIntent(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    System.out.println("unzip----------url=" + paramString1 + "----info=" + paramString2);
    Object localObject1 = paramString1.substring(16);
    localObject2 = ("/mnt/sdcard" + (String)localObject1).replace("++++", " ");
    try
    {
      localObject1 = new FileInputStream((String)localObject2);
      this.book = new EpubReader().readEpub((InputStream)localObject1);
      List localList = this.book.getMetadata().getAuthors();
      localObject1 = paramString3;
      if (localList.size() > 0) {
        localObject1 = ((Author)localList.get(0)).getFirstname() + " " + ((Author)localList.get(0)).getLastname();
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
        String str = paramString3;
        continue;
        localObject2 = new Intent(this.cordova.getActivity(), EpubReaderMain.class);
        ((Intent)localObject2).setFlags(67108864);
        Log.e("url ", paramString1);
        ((Intent)localObject2).putExtra("url", paramString1);
        ((Intent)localObject2).putExtra("info", paramString2);
        ((Intent)localObject2).putExtra("authorname", str);
        ((Intent)localObject2).putExtra("title", paramString4);
        ((Intent)localObject2).putExtra("activityName", "EbookViewer");
        ((Intent)localObject2).putExtra("fontsize", 15);
        this.cordova.startActivityForResult(this, (Intent)localObject2, 1);
      }
    }
    EpubReaderMain.bookurl = (String)localObject2;
    paramString3 = new DatabaseHelperAdapterReader(this.cordova.getActivity());
    paramString3.open();
    localObject2 = paramString3.getBookStatus(EpubReaderMain.bookurl);
    Log.e("book url>>>>>>>>>>>>>>", "" + ((Cursor)localObject2).getCount());
    if (((Cursor)localObject2).getCount() > 0)
    {
      ((Cursor)localObject2).moveToFirst();
      paramString1 = new Intent(this.cordova.getActivity(), EpubReaderMain.class);
      paramString1.putExtra("url", ((Cursor)localObject2).getString(1));
      paramString1.putExtra("info", ((Cursor)localObject2).getString(4));
      paramString1.putExtra("title", ((Cursor)localObject2).getString(4));
      paramString1.putExtra("authorname", ((Cursor)localObject2).getString(3));
      paramString1.putExtra("xvalue", ((Cursor)localObject2).getInt(6));
      paramString1.putExtra("pageno", ((Cursor)localObject2).getInt(2));
      paramString1.putExtra("p_page_value", "");
      paramString1.putExtra("activityName", "Bookmark");
      paramString1.putExtra("fontsize", ((Cursor)localObject2).getInt(7));
      this.cordova.getActivity().startActivity(paramString1);
      paramString3.close();
      return;
    }
  }
  
  public boolean execute(String paramString, JSONArray paramJSONArray, CallbackContext paramCallbackContext)
  {
    boolean bool = false;
    paramString = PluginResult.Status.OK;
    try
    {
      paramString = paramJSONArray.getJSONObject(0);
      doSendIntent(paramString.getString("url"), paramString.getString("info"), paramString.getString("authorname"), paramString.getString("title"));
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


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\phonegap\plugins\ebookViewer\EbookViewer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */