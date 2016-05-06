package com.example.example75f1799f07eb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.ons.adapter.TocAdapter;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TOC
  extends Activity
{
  protected static ImageView iconCloseView1;
  TextView appName;
  String data;
  String dirLocation;
  ListView lv_toc;
  List<File> textFiles = new ArrayList();
  List<String> toc = new ArrayList();
  List<String> tocUrl = new ArrayList();
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    setContentView(2130903154);
    this.lv_toc = ((ListView)findViewById(2131558786));
    iconCloseView1 = (ImageView)findViewById(2131558452);
    this.toc.clear();
    this.tocUrl.clear();
    this.data = getIntent().getExtras().getString("data");
    setData(this.data);
    this.lv_toc.setAdapter(new TocAdapter(this, this.toc));
    iconCloseView1.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        TOC.this.finish();
      }
    });
    this.lv_toc.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        paramAnonymousAdapterView = (String)TOC.this.tocUrl.get(paramAnonymousInt);
        paramAnonymousView = new Intent();
        paramAnonymousView.putExtra("text", paramAnonymousAdapterView);
        TOC.this.setResult(-1, paramAnonymousView);
        TOC.this.finish();
      }
    });
  }
  
  public void setData(String paramString)
  {
    try
    {
      paramString = new JSONArray(paramString);
      int i = 0;
      while (i < paramString.length())
      {
        JSONObject localJSONObject = paramString.getJSONObject(i);
        this.toc.add(localJSONObject.optString("label").replaceAll("\n", "").replaceAll("\t", ""));
        this.tocUrl.add(localJSONObject.optString("href"));
        i += 1;
      }
      return;
    }
    catch (JSONException paramString)
    {
      paramString.printStackTrace();
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\example\example75f1799f07eb\TOC.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */