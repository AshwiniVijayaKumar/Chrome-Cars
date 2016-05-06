package com.ons.bellareader;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Spanned;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.ons.adapter.BookMarkListAdapter;
import com.ons.model.BookMarkData;
import java.util.ArrayList;
import java.util.List;

public class Bookmark
  extends Activity
  implements AdapterView.OnItemClickListener
{
  protected static ImageButton bookmarkButton;
  public static ArrayList<BookMarkData> bookmarkList = new ArrayList();
  protected static ImageButton iconCloseView1;
  public static Spanned span;
  TextView appName;
  String[] authorNameString;
  BookMarkData bookMarkData;
  Button btn_delete;
  List<Integer> deleteRowPosition = new ArrayList();
  int deleteStatus = 0;
  int headerstatus = 0;
  String[] idString;
  ListView listView;
  DatabaseHelperAdapterReader mydb;
  String[] nameString;
  String[] pagenoString;
  TextView tv_bookmarks;
  TextView tv_nobookmark;
  String[] urlString;
  
  public void deleteBookmark(View paramView)
  {
    if (this.btn_delete.getText().toString().equalsIgnoreCase("delete"))
    {
      this.deleteStatus = 1;
      this.btn_delete.setText("Done");
      this.tv_bookmarks.setText("Select bookmarks to delete");
      return;
    }
    if (this.deleteRowPosition.size() > 0)
    {
      int i = 0;
      while (i < this.deleteRowPosition.size())
      {
        int j = ((Integer)this.deleteRowPosition.get(i)).intValue();
        this.mydb.deleteRow(j);
        i += 1;
      }
      this.mydb.getValues();
      setListData();
    }
    this.deleteStatus = 0;
    this.btn_delete.setText("DELETE");
    this.tv_bookmarks.setText("All Bookmarks");
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(7);
    setContentView(2130903054);
    getWindow().setFeatureInt(7, 2130903063);
    this.mydb = new DatabaseHelperAdapterReader(this);
    this.mydb.getValues();
    paramBundle = getResources().getString(2131230720);
    this.appName = ((TextView)findViewById(2131558500));
    this.appName.setText(paramBundle);
    iconCloseView1 = (ImageButton)findViewById(2131558452);
    bookmarkButton = (ImageButton)findViewById(2131558502);
    bookmarkButton.setVisibility(4);
    iconCloseView1.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Bookmark.this.finish();
      }
    });
    this.btn_delete = ((Button)findViewById(2131558469));
    this.tv_nobookmark = ((TextView)findViewById(2131558468));
    this.tv_bookmarks = ((TextView)findViewById(2131558463));
    this.listView = ((ListView)findViewById(2131558467));
    setListData();
    this.listView.setOnItemClickListener(this);
  }
  
  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    if (this.deleteStatus == 0)
    {
      EpubReaderMain.bookurl = ((BookMarkData)bookmarkList.get(paramInt)).getBookurl();
      paramAdapterView = new Intent(this, EpubReaderMain.class);
      paramAdapterView.putExtra("url", ((BookMarkData)bookmarkList.get(paramInt)).getUrl());
      paramAdapterView.putExtra("info", ((BookMarkData)bookmarkList.get(paramInt)).getTitle());
      paramAdapterView.putExtra("title", ((BookMarkData)bookmarkList.get(paramInt)).getTitle());
      paramAdapterView.putExtra("authorname", ((BookMarkData)bookmarkList.get(paramInt)).getAuthorName());
      paramAdapterView.putExtra("xvalue", ((BookMarkData)bookmarkList.get(paramInt)).getXvalue());
      paramAdapterView.putExtra("pageno", ((BookMarkData)bookmarkList.get(paramInt)).getPageno());
      paramAdapterView.putExtra("p_page_value", ((BookMarkData)bookmarkList.get(paramInt)).getP_page_value());
      paramAdapterView.putExtra("activityName", "Bookmark");
      paramAdapterView.putExtra("fontsize", ((BookMarkData)bookmarkList.get(paramInt)).getFontsize());
      startActivity(paramAdapterView);
    }
    int m;
    int n;
    int i1;
    int i;
    if (this.deleteStatus == 1)
    {
      m = 0;
      n = -1;
      i1 = ((BookMarkData)this.listView.getAdapter().getItem(paramInt)).getId();
      i = 0;
    }
    int k;
    for (;;)
    {
      k = n;
      int j = m;
      if (i < this.deleteRowPosition.size())
      {
        if (i1 == ((Integer)this.deleteRowPosition.get(i)).intValue())
        {
          j = 1;
          k = i;
        }
      }
      else
      {
        if (j != 0) {
          break;
        }
        this.deleteRowPosition.add(Integer.valueOf(i1));
        ((BookMarkData)bookmarkList.get(paramInt)).setStatus(true);
        return;
      }
      i += 1;
    }
    paramView.setBackgroundColor(getResources().getColor(17170445));
    this.deleteRowPosition.remove(k);
    ((BookMarkData)bookmarkList.get(paramInt)).setStatus(false);
  }
  
  public void setListData()
  {
    if (bookmarkList.size() > 0)
    {
      this.listView.setVisibility(0);
      this.tv_nobookmark.setVisibility(4);
      this.listView.setAdapter(new BookMarkListAdapter(this));
      return;
    }
    this.listView.setVisibility(4);
    this.tv_nobookmark.setVisibility(0);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ons\bellareader\Bookmark.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */