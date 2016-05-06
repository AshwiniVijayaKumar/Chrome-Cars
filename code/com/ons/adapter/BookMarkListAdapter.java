package com.ons.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.ons.bellareader.Bookmark;
import com.ons.model.BookMarkData;
import java.util.ArrayList;

public class BookMarkListAdapter
  extends BaseAdapter
{
  Context context;
  int displayWidth;
  
  public BookMarkListAdapter(Context paramContext)
  {
    this.context = paramContext;
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    ((Activity)paramContext).getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    this.displayWidth = localDisplayMetrics.widthPixels;
  }
  
  public int getCount()
  {
    return Bookmark.bookmarkList.size();
  }
  
  public Object getItem(int paramInt)
  {
    return Bookmark.bookmarkList.get(paramInt);
  }
  
  public long getItemId(int paramInt)
  {
    return paramInt;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    paramViewGroup = paramView;
    paramView = paramViewGroup;
    if (paramViewGroup == null) {
      paramView = ((LayoutInflater)this.context.getSystemService("layout_inflater")).inflate(2130903055, null);
    }
    paramViewGroup = (TextView)paramView.findViewById(2131558470);
    paramViewGroup.setWidth(this.displayWidth / 3);
    TextView localTextView1 = (TextView)paramView.findViewById(2131558471);
    localTextView1.setWidth(this.displayWidth / 3);
    TextView localTextView2 = (TextView)paramView.findViewById(2131558472);
    localTextView2.setWidth(this.displayWidth / 3);
    paramViewGroup.setText(((BookMarkData)Bookmark.bookmarkList.get(paramInt)).getBookmarkName());
    localTextView1.setText(((BookMarkData)Bookmark.bookmarkList.get(paramInt)).getAuthorName());
    localTextView2.setText(((BookMarkData)Bookmark.bookmarkList.get(paramInt)).getBookName());
    if (!((BookMarkData)Bookmark.bookmarkList.get(paramInt)).isStatus())
    {
      paramView.setBackgroundColor(this.context.getResources().getColor(17170445));
      return paramView;
    }
    paramView.setBackgroundColor(this.context.getResources().getColor(2131361839));
    return paramView;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ons\adapter\BookMarkListAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */