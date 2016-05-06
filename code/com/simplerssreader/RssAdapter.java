package com.simplerssreader;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.DecodingType;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.DisplayImageOptions.Builder;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.io.PrintStream;
import java.util.List;

public class RssAdapter
  extends BaseAdapter
{
  private final Context context;
  protected ImageLoader imageLoader = ImageLoader.getInstance();
  private final List<RssItem> items;
  
  public RssAdapter(Context paramContext, List<RssItem> paramList)
  {
    this.items = paramList;
    this.context = paramContext;
  }
  
  public int getCount()
  {
    return this.items.size();
  }
  
  public Object getItem(int paramInt)
  {
    return this.items.get(paramInt);
  }
  
  public long getItemId(int paramInt)
  {
    return paramInt;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    Object localObject;
    if (paramView == null)
    {
      paramView = View.inflate(this.context, 2130903141, null);
      paramViewGroup = new ViewHolder();
      paramViewGroup.itemTitle = ((TextView)paramView.findViewById(2131558753));
      paramViewGroup.itemDescription = ((TextView)paramView.findViewById(2131558755));
      paramViewGroup.itemDate = ((TextView)paramView.findViewById(2131558752));
      paramViewGroup.itemAuthor = ((TextView)paramView.findViewById(2131558754));
      paramViewGroup.itemImageView = ((ImageView)paramView.findViewById(2131558556));
      paramView.setTag(paramViewGroup);
      RssItem localRssItem = (RssItem)this.items.get(paramInt);
      if (localRssItem.getTitle() == null) {
        break label603;
      }
      paramViewGroup.itemTitle.setVisibility(0);
      if (((RssItem)this.items.get(paramInt)).getTitle().trim() == null) {
        break label596;
      }
      localObject = Html.fromHtml(((RssItem)this.items.get(paramInt)).getTitle(), null, null).toString().trim().replaceAll("[-+.^:￼\n,]", "");
      label182:
      paramViewGroup.itemTitle.setText((CharSequence)localObject);
      System.out.println("Rss Title: " + (String)localObject);
      label217:
      if (localRssItem.getDate() == null) {
        break label615;
      }
      paramViewGroup.itemDate.setVisibility(0);
      paramViewGroup.itemDate.setText(localRssItem.getDate());
      System.out.println("Rss Date: " + localRssItem.getDate());
      label274:
      if (localRssItem.getAuthor() == null) {
        break label627;
      }
      paramViewGroup.itemAuthor.setVisibility(0);
      paramViewGroup.itemAuthor.setText(localRssItem.getAuthor());
      paramViewGroup.itemAuthor.setVisibility(8);
      System.out.println("Rss Author: " + localRssItem.getAuthor());
      label340:
      if (localRssItem.getDescription() == null) {
        break label646;
      }
      if (((RssItem)this.items.get(paramInt)).getDescription() == null) {
        break label639;
      }
      localObject = Html.fromHtml(((RssItem)this.items.get(paramInt)).getDescription(), null, null).toString().trim().replaceAll("[-+.^:￼\n,]", "");
      label403:
      paramViewGroup.itemDescription.setVisibility(0);
      paramViewGroup.itemDescription.setText((CharSequence)localObject);
      System.out.println("Rss Description: " + localRssItem.getDescription());
      label449:
      if (((RssItem)this.items.get(paramInt)).getDescription() == null) {
        break label658;
      }
      Html.fromHtml(((RssItem)this.items.get(paramInt)).getDescription(), null, null).toString().trim().replaceAll("[-+.^:￼\n,]", "");
    }
    label596:
    label603:
    label615:
    label627:
    label639:
    label646:
    label658:
    for (;;)
    {
      if (((RssItem)this.items.get(paramInt)).getImage() == null) {
        break label661;
      }
      paramViewGroup.itemImageView.setVisibility(0);
      localObject = new DisplayImageOptions.Builder().cacheInMemory().cacheOnDisc().decodingType(DecodingType.MEMORY_SAVING).build();
      this.imageLoader.displayImage(((RssItem)this.items.get(paramInt)).getImage(), paramViewGroup.itemImageView, (DisplayImageOptions)localObject);
      return paramView;
      paramViewGroup = (ViewHolder)paramView.getTag();
      break;
      localObject = "";
      break label182;
      paramViewGroup.itemTitle.setVisibility(8);
      break label217;
      paramViewGroup.itemDate.setVisibility(8);
      break label274;
      paramViewGroup.itemAuthor.setVisibility(8);
      break label340;
      localObject = "";
      break label403;
      paramViewGroup.itemDescription.setVisibility(8);
      break label449;
    }
    label661:
    paramViewGroup.itemImageView.setVisibility(8);
    paramViewGroup.itemImageView.setBackgroundResource(2130837684);
    return paramView;
  }
  
  static class ViewHolder
  {
    TextView itemAuthor;
    TextView itemDate;
    TextView itemDescription;
    ImageView itemImageView;
    TextView itemTitle;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\simplerssreader\RssAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */