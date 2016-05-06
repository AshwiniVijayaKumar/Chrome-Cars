package com.google.zxing.client.android.share;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.zxing.client.android.R.id;
import com.google.zxing.client.android.R.layout;

final class BookmarkAdapter
  extends BaseAdapter
{
  private final Context context;
  private final Cursor cursor;
  
  BookmarkAdapter(Context paramContext, Cursor paramCursor)
  {
    this.context = paramContext;
    this.cursor = paramCursor;
  }
  
  public int getCount()
  {
    return this.cursor.getCount();
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
    if ((paramView instanceof LinearLayout)) {}
    for (paramView = (LinearLayout)paramView;; paramView = (LinearLayout)LayoutInflater.from(this.context).inflate(R.layout.bookmark_picker_list_item, paramViewGroup, false))
    {
      if (!this.cursor.isClosed())
      {
        this.cursor.moveToPosition(paramInt);
        paramViewGroup = this.cursor.getString(0);
        ((TextView)paramView.findViewById(R.id.bookmark_title)).setText(paramViewGroup);
        paramViewGroup = this.cursor.getString(1);
        ((TextView)paramView.findViewById(R.id.bookmark_url)).setText(paramViewGroup);
      }
      return paramView;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\share\BookmarkAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */