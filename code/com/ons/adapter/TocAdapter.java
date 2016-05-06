package com.ons.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

public class TocAdapter
  extends BaseAdapter
{
  Context context;
  List<String> toc_list;
  
  public TocAdapter(Context paramContext, List<String> paramList)
  {
    this.context = paramContext;
    this.toc_list = paramList;
  }
  
  public int getCount()
  {
    return this.toc_list.size();
  }
  
  public Object getItem(int paramInt)
  {
    return this.toc_list.get(paramInt);
  }
  
  public long getItemId(int paramInt)
  {
    return paramInt;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    paramViewGroup = paramView;
    if (paramView == null) {
      paramViewGroup = ((LayoutInflater)this.context.getSystemService("layout_inflater")).inflate(2130903155, null);
    }
    ((TextView)paramViewGroup.findViewById(2131558787)).setText((CharSequence)this.toc_list.get(paramInt));
    return paramViewGroup;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ons\adapter\TocAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */