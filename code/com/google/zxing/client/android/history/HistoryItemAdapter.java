package com.google.zxing.client.android.history;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.zxing.Result;
import com.google.zxing.client.android.R.id;
import com.google.zxing.client.android.R.layout;
import com.google.zxing.client.android.R.string;
import java.util.ArrayList;

final class HistoryItemAdapter
  extends ArrayAdapter<HistoryItem>
{
  private final Activity activity;
  
  HistoryItemAdapter(Activity paramActivity)
  {
    super(paramActivity, R.layout.history_list_item, new ArrayList());
    this.activity = paramActivity;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    Object localObject;
    if ((paramView instanceof LinearLayout))
    {
      paramView = (LinearLayout)paramView;
      paramViewGroup = (HistoryItem)getItem(paramInt);
      localObject = paramViewGroup.getResult();
      if (localObject == null) {
        break label97;
      }
      localObject = ((Result)localObject).getText();
    }
    for (paramViewGroup = paramViewGroup.getDisplayAndDetails();; paramViewGroup = paramViewGroup.getString(R.string.history_empty_detail))
    {
      ((TextView)paramView.findViewById(R.id.history_title)).setText((CharSequence)localObject);
      ((TextView)paramView.findViewById(R.id.history_detail)).setText(paramViewGroup);
      return paramView;
      paramView = (LinearLayout)LayoutInflater.from(this.activity).inflate(R.layout.history_list_item, paramViewGroup, false);
      break;
      label97:
      paramViewGroup = getContext().getResources();
      localObject = paramViewGroup.getString(R.string.history_empty);
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\history\HistoryItemAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */