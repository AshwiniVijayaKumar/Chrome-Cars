package com.ons.bellareader;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.ons.adapter.TocAdapter;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class TOC
  extends Activity
{
  TextView appName;
  ImageView closeback;
  String dirLocation;
  String dirName;
  ListView lv_toc;
  DatabaseHelperAdapterReader mydb;
  List<File> textFiles = new ArrayList();
  List<String> toc = new ArrayList();
  List<String> tocUrl = new ArrayList();
  
  List<File> ncxFiles(File paramFile)
  {
    paramFile = paramFile.listFiles();
    int j = paramFile.length;
    int i = 0;
    if (i < j)
    {
      File localFile = paramFile[i];
      if (localFile.isDirectory()) {
        ncxFiles(localFile);
      }
      for (;;)
      {
        i += 1;
        break;
        if (localFile.getName().endsWith(".ncx")) {
          this.textFiles.add(localFile);
        }
      }
    }
    return this.textFiles;
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(7);
    setContentView(2130903154);
    getWindow().setFeatureInt(7, 2130903063);
    this.mydb = new DatabaseHelperAdapterReader(this);
    paramBundle = getResources().getString(2131230720);
    this.appName = ((TextView)findViewById(2131558500));
    this.appName.setText(paramBundle);
    this.closeback = ((ImageView)findViewById(2131558784));
    this.toc.clear();
    this.tocUrl.clear();
    this.dirName = getIntent().getExtras().getString("dirName");
    this.dirLocation = (Environment.getExternalStorageDirectory() + "/epubtemp/" + this.dirName);
    parseXml((File)ncxFiles(new File(this.dirLocation)).get(0));
    this.lv_toc = ((ListView)findViewById(2131558786));
    this.lv_toc.setAdapter(new TocAdapter(this, this.toc));
    this.closeback.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        TOC.this.saveBookStatus();
      }
    });
    this.lv_toc.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        String[] arrayOfString = ((String)TOC.this.tocUrl.get(paramAnonymousInt)).split("#");
        paramAnonymousAdapterView = "";
        if (arrayOfString.length > 1)
        {
          paramAnonymousInt = 0;
          for (;;)
          {
            paramAnonymousView = paramAnonymousAdapterView;
            if (paramAnonymousInt >= arrayOfString.length - 1) {
              break;
            }
            paramAnonymousAdapterView = paramAnonymousAdapterView + arrayOfString[paramAnonymousInt];
            paramAnonymousInt += 1;
          }
        }
        paramAnonymousView = (String)TOC.this.tocUrl.get(paramAnonymousInt);
        paramAnonymousAdapterView = new Intent(TOC.this, EpubReaderMain.class);
        paramAnonymousAdapterView.putExtra("url", TOC.this.getIntent().getExtras().getString("currentPageUrl") + paramAnonymousView);
        paramAnonymousAdapterView.putExtra("info", TOC.this.getIntent().getExtras().getString("infoValue"));
        paramAnonymousAdapterView.putExtra("title", TOC.this.dirName);
        paramAnonymousAdapterView.putExtra("authorname", TOC.this.getIntent().getExtras().getString("authorName"));
        paramAnonymousAdapterView.putExtra("xvalue", "");
        paramAnonymousAdapterView.putExtra("pageno", "");
        paramAnonymousAdapterView.putExtra("p_page_value", "");
        paramAnonymousAdapterView.putExtra("activityName", "toc");
        paramAnonymousAdapterView.putExtra("fontsize", 15);
        TOC.this.startActivity(paramAnonymousAdapterView);
        TOC.this.finish();
      }
    });
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
    {
      saveBookStatus();
      return true;
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
  
  public void parseXml(File paramFile)
  {
    try
    {
      paramFile = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(paramFile);
      paramFile.getDocumentElement().normalize();
      NodeList localNodeList = paramFile.getElementsByTagName("navPoint");
      int i = 0;
      while (i < localNodeList.getLength())
      {
        Object localObject = localNodeList.item(i);
        if (((Node)localObject).getNodeType() == 1)
        {
          localObject = (Element)localObject;
          NamedNodeMap localNamedNodeMap = ((Element)paramFile.getElementsByTagName("content").item(i)).getAttributes();
          this.toc.add(((Element)localObject).getElementsByTagName("text").item(0).getTextContent());
          this.tocUrl.add(localNamedNodeMap.item(0).getNodeValue());
        }
        i += 1;
      }
      return;
    }
    catch (Exception paramFile)
    {
      paramFile.printStackTrace();
    }
  }
  
  public void saveBookStatus()
  {
    this.mydb.addBookStatus(getIntent().getExtras().getString("currentpageurl1"), getIntent().getExtras().getInt("currentpageno"), getIntent().getExtras().getString("authorName"), getIntent().getExtras().getString("dirName"), getIntent().getExtras().getString("bookurl"), getIntent().getExtras().getInt("currentx"), getIntent().getExtras().getInt("fontsize"));
    finish();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ons\bellareader\TOC.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */