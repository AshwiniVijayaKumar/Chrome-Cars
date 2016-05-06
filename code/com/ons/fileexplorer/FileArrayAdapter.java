package com.ons.fileexplorer;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class FileArrayAdapter
  extends ArrayAdapter<Item>
{
  private Context c;
  private int id;
  private List<Item> items;
  
  public FileArrayAdapter(Context paramContext, int paramInt, List<Item> paramList)
  {
    super(paramContext, paramInt, paramList);
    this.c = paramContext;
    this.id = paramInt;
    this.items = paramList;
  }
  
  public Item getItem(int paramInt)
  {
    return (Item)this.items.get(paramInt);
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    paramViewGroup = paramView;
    paramView = paramViewGroup;
    if (paramViewGroup == null) {
      paramView = ((LayoutInflater)this.c.getSystemService("layout_inflater")).inflate(this.id, null);
    }
    paramViewGroup = (Item)this.items.get(paramInt);
    if (paramViewGroup != null)
    {
      TextView localTextView1 = (TextView)paramView.findViewById(2131558526);
      TextView localTextView2 = (TextView)paramView.findViewById(2131558527);
      TextView localTextView3 = (TextView)paramView.findViewById(2131558528);
      ImageView localImageView = (ImageView)paramView.findViewById(2131558525);
      String str = "drawable/" + paramViewGroup.getImage();
      paramInt = this.c.getResources().getIdentifier(str, null, this.c.getPackageName());
      localImageView.setImageDrawable(this.c.getResources().getDrawable(paramInt));
      if (localTextView1 != null) {
        localTextView1.setText(paramViewGroup.getName());
      }
      if (localTextView2 != null) {
        localTextView2.setText(paramViewGroup.getData());
      }
      if (localTextView3 != null) {
        localTextView3.setText(paramViewGroup.getDate());
      }
    }
    return paramView;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ons\fileexplorer\FileArrayAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */