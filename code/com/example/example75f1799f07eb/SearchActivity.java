package com.example.example75f1799f07eb;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class SearchActivity
  extends Activity
{
  public static final String MyPREFERENCES = "MyPrefs";
  static int selectedIndex = 0;
  static SharedPreferences sharedpreferences;
  String Device_Oriantation = "";
  String HeaderBarbackgroundColor;
  String HeaderbarTextColor;
  String ImgURl_Land;
  String ImgURl_Port;
  int Oriantation_ID;
  File SDCardRoot;
  private SimpleAdapter adapter;
  TextView appName;
  private Document doc;
  File file;
  String foldername = "";
  RelativeLayout hideLayout;
  RelativeLayout loadUrlHeaderRelativeLayout;
  Preferences mpreferences;
  String navigationBarType;
  private String[] originalPage = { "formbuilder", "video", "chatroom", "contact", "tools", "about", "qrcode", "textpage", "callme", "googleplus", "education", "survey", "blog", "rss", "scheduling", "eecommerce", "socialnetwork", "codepage" };
  private String[] pageIds;
  private String[] pageIdsName;
  private int pageIds_length;
  private int[] pageIndex;
  private String[] replacePage = { "Formbuilder", "Video", "chat", "contactUs", "pocketTools", "aboutUs", "QR", "TextPage", "callMe", "Googleplus", "Education", "Survey", "Blog", "Rss", "Scheduling", "ecomm", "social", "codePage" };
  private ArrayList<HashMap<String, String>> searchResults;
  String search_text;
  
  private void Check_Device_Oriantation()
  {
    this.Oriantation_ID = getResources().getConfiguration().orientation;
    switch (this.Oriantation_ID)
    {
    default: 
      this.Device_Oriantation = "";
      Setheaderimage();
      return;
    case 2: 
      this.Device_Oriantation = "LANDSCAPE";
      Setheaderimage();
      return;
    case 1: 
      this.Device_Oriantation = "PORTRAIT";
      Setheaderimage();
      return;
    }
    this.Device_Oriantation = "UNDEFINED";
    Setheaderimage();
  }
  
  private void Setheaderimage()
  {
    try
    {
      Object localObject1 = getApplicationName();
      this.navigationBarType = sharedpreferences.getString("navigationBarType", "");
      this.HeaderBarbackgroundColor = sharedpreferences.getString("HeaderBarbackgroundColor", "");
      this.HeaderbarTextColor = sharedpreferences.getString("HeaderbarTextColor", "");
      this.ImgURl_Port = sharedpreferences.getString("ImgURl_Port", "");
      this.ImgURl_Land = sharedpreferences.getString("ImgURl_Land", "");
      System.out.println("navigationBarType " + this.navigationBarType + " , HeaderBarbackgroundColor " + this.HeaderBarbackgroundColor + " , HeaderbarTextColor " + this.HeaderbarTextColor);
      if (this.navigationBarType.equals("image"))
      {
        this.SDCardRoot = new File(Environment.getExternalStorageDirectory(), this.foldername + (String)localObject1);
        if (this.Device_Oriantation.equals("PORTRAIT"))
        {
          this.file = new File(this.SDCardRoot, "header_port_img.jpg");
          if (!this.file.exists()) {
            return;
          }
          localObject1 = BitmapFactory.decodeFile(this.file.getAbsolutePath());
          localObject1 = new BitmapDrawable(getResources(), (Bitmap)localObject1);
          this.loadUrlHeaderRelativeLayout.setBackground((Drawable)localObject1);
          this.appName.setText("");
          return;
        }
        this.file = new File(this.SDCardRoot, "header_land_img.jpg");
        if (!this.file.exists()) {
          return;
        }
        localObject1 = BitmapFactory.decodeFile(this.file.getAbsolutePath());
        localObject1 = new BitmapDrawable(getResources(), (Bitmap)localObject1);
        this.loadUrlHeaderRelativeLayout.setBackground((Drawable)localObject1);
        this.appName.setText("");
        return;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      return;
    }
    System.out.println("Headerbar color code : HeaderBarbackgroundColor  " + this.HeaderBarbackgroundColor + "  ,  HeaderbarTextColor  " + this.HeaderbarTextColor);
    this.appName.setTextColor(Color.parseColor(this.HeaderbarTextColor));
    Object localObject2 = getSharedPreferences("MyPrefs", 0).getString("HeaderBarbackgroundColor", "");
    System.out.println("krishna header color>>>" + (String)localObject2);
    if (!((String)localObject2).equals(""))
    {
      Object localObject3;
      String str;
      if (((String)localObject2).contains("rgba"))
      {
        localObject3 = ((String)localObject2).split(",");
        localObject3[0] = localObject3[0].split("\\(")[1];
        localObject3[3] = localObject3[3].split("\\)")[0];
        Integer.toHexString(Integer.parseInt(localObject3[3]));
        localObject2 = Integer.toHexString(Integer.parseInt(localObject3[0]));
        str = Integer.toHexString(Integer.parseInt(localObject3[1]));
        localObject3 = Integer.toHexString(Integer.parseInt(localObject3[2]));
        str = "#" + (String)localObject2 + str + (String)localObject3;
        localObject2 = str;
        if (str.equals("#000")) {
          localObject2 = "#000000";
        }
        this.loadUrlHeaderRelativeLayout.setBackgroundColor(Color.parseColor((String)localObject2));
        return;
      }
      if (((String)localObject2).contains("rgb"))
      {
        localObject3 = ((String)localObject2).split(",");
        localObject3[0] = localObject3[0].split("\\(")[1];
        localObject3[2] = localObject3[2].split("\\)")[0];
        localObject2 = Integer.toHexString(Integer.parseInt(localObject3[0]));
        str = Integer.toHexString(Integer.parseInt(localObject3[1]));
        localObject3 = Integer.toHexString(Integer.parseInt(localObject3[2]));
        str = "#" + (String)localObject2 + str + (String)localObject3;
        localObject2 = str;
        if (str.equals("#000")) {
          localObject2 = "#000000";
        }
        this.loadUrlHeaderRelativeLayout.setBackgroundColor(Color.parseColor((String)localObject2));
        return;
      }
      this.loadUrlHeaderRelativeLayout.setBackgroundColor(Color.parseColor((String)localObject2));
      return;
    }
    this.loadUrlHeaderRelativeLayout.setBackgroundColor(Color.parseColor("#33b5e5"));
  }
  
  private void calculatePageIndex()
  {
    Object localObject1 = DocumentBuilderFactory.newInstance();
    for (;;)
    {
      int n;
      int m;
      try
      {
        this.doc = ((DocumentBuilderFactory)localObject1).newDocumentBuilder().parse(new FileInputStream(new File(getFilesDir(), "appypie.xml")));
        this.doc.getDocumentElement().normalize();
        Object localObject2 = this.doc.getElementsByTagName("pageid").item(0);
        localObject1 = this.doc.getElementsByTagName("pageNewid").item(0);
        localObject2 = ((Node)localObject2).getTextContent().toString();
        localObject1 = ((Node)localObject1).getTextContent().toString();
        System.out.println("pageId->" + (String)localObject2);
        this.pageIds = ((String)localObject2).split(",");
        this.pageIdsName = ((String)localObject1).split(",");
        this.pageIds_length = this.pageIds.length;
        this.pageIndex = new int[this.pageIds_length];
        int k = 0;
        int j = 0;
        if (j < this.pageIds_length)
        {
          n = 0;
          i = 0;
          if (k > 0)
          {
            m = 0;
            n = i;
            if (m < k)
            {
              n = i;
              if (!this.pageIds[m].equals(this.pageIds[k])) {
                break label389;
              }
              n = i + 1;
              break label389;
            }
          }
          else
          {
            this.pageIndex[0] = 0;
          }
          this.pageIndex[k] = n;
          k += 1;
          j += 1;
        }
        else
        {
          k = this.originalPage.length;
          System.out.println("pageIds_length->" + this.pageIds_length + " originalPage_length->" + k);
          i = 0;
          if (i < this.pageIds_length)
          {
            j = 0;
            if (j < k)
            {
              if (this.pageIds[i].equals(this.originalPage[j])) {
                this.pageIds[i] = this.replacePage[j];
              }
            }
            else
            {
              i += 1;
              continue;
            }
            j += 1;
            continue;
          }
          return;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
      label389:
      m += 1;
      int i = n;
    }
  }
  
  public void close(View paramView)
  {
    finish();
  }
  
  public String getApplicationName()
  {
    return getString(getApplicationInfo().labelRes);
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    if (paramConfiguration.orientation == 2)
    {
      this.Device_Oriantation = "LANDSCAPE";
      Setheaderimage();
    }
    while (paramConfiguration.orientation != 1) {
      return;
    }
    this.Device_Oriantation = "PORTRAIT";
    Setheaderimage();
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    setContentView(2130903048);
    try
    {
      paramBundle = getResources().getString(2131230720);
      this.appName = ((TextView)findViewById(2131558451));
      this.appName.setText(paramBundle);
      sharedpreferences = getSharedPreferences("MyPrefs", 0);
      this.mpreferences = new Preferences(getApplicationContext());
      paramBundle = sharedpreferences.getString("headerBarTitle", "");
      if ((paramBundle != null) && (!paramBundle.equals(""))) {
        this.appName.setText(paramBundle);
      }
      this.loadUrlHeaderRelativeLayout = ((RelativeLayout)findViewById(2131558450));
    }
    catch (Exception paramBundle)
    {
      for (;;)
      {
        try
        {
          this.searchResults.clear();
          this.adapter.notifyDataSetChanged();
          int i = 0;
          int j = this.pageIds_length;
          if (i < j) {
            try
            {
              paramBundle = this.doc.getElementsByTagName(this.pageIds[i]).item(this.pageIndex[i]).getTextContent().toString();
              if (paramBundle.toLowerCase().contains(this.search_text.toLowerCase()))
              {
                System.out.println("i->" + i + " pageIds->" + this.pageIds[i] + " pageindex->" + this.pageIndex[i]);
                System.out.println("node_text" + paramBundle);
                paramBundle = new HashMap();
                paramBundle.put("pageIds", this.pageIds[i]);
                paramBundle.put("pageIdsName", this.pageIdsName[i]);
                paramBundle.put("pageIndex", "" + i);
                this.searchResults.add(paramBundle);
              }
              i += 1;
            }
            catch (NullPointerException paramBundle)
            {
              paramBundle.printStackTrace();
              continue;
            }
          }
          this.adapter.notifyDataSetChanged();
          return;
        }
        catch (Exception paramBundle) {}
        paramBundle = paramBundle;
      }
    }
    this.search_text = getIntent().getStringExtra("searchText");
    this.searchResults = new ArrayList();
    paramBundle = (ListView)findViewById(2131558455);
    this.adapter = new SimpleAdapter(this);
    paramBundle.setAdapter(this.adapter);
    calculatePageIndex();
    Check_Device_Oriantation();
    Setheaderimage();
  }
  
  private class SimpleAdapter
    extends BaseAdapter
  {
    Context context;
    
    public SimpleAdapter(Context paramContext)
    {
      this.context = paramContext;
    }
    
    public int getCount()
    {
      return SearchActivity.this.searchResults.size();
    }
    
    public Object getItem(int paramInt)
    {
      return null;
    }
    
    public long getItemId(int paramInt)
    {
      return paramInt;
    }
    
    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      if (paramView == null)
      {
        paramView = ((Activity)this.context).getLayoutInflater().inflate(2130903146, paramViewGroup, false);
        paramViewGroup = new SearchActivity.ViewHolder();
        paramViewGroup.textView1 = ((TextView)paramView.findViewById(2131558489));
        paramViewGroup.textView2 = ((TextView)paramView.findViewById(2131558552));
        paramViewGroup.search_list = ((LinearLayout)paramView.findViewById(2131558762));
        paramViewGroup.search_list.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            MyPhoneGapActivity.loadSearchPage(((TextView)paramAnonymousView.findViewById(2131558552)).getText().toString());
            SearchActivity.this.finish();
          }
        });
        paramView.setTag(paramViewGroup);
      }
      for (;;)
      {
        paramViewGroup.textView1.setText((CharSequence)((HashMap)SearchActivity.this.searchResults.get(paramInt)).get("pageIdsName"));
        paramViewGroup.textView2.setText((CharSequence)((HashMap)SearchActivity.this.searchResults.get(paramInt)).get("pageIndex"));
        return paramView;
        paramViewGroup = (SearchActivity.ViewHolder)paramView.getTag();
      }
    }
  }
  
  static class ViewHolder
  {
    LinearLayout search_list;
    TextView textView1;
    TextView textView2;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\example\example75f1799f07eb\SearchActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */