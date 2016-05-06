package com.example.example75f1799f07eb;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MenuItemComponent
  extends LinearLayout
{
  TextView menuItemDescriptionTextView;
  TextView menuItemPriceTextView;
  TextView menuItemTitleTextView;
  
  public MenuItemComponent(Context paramContext)
  {
    super(paramContext);
    ((LayoutInflater)paramContext.getSystemService("layout_inflater")).inflate(2130903102, this);
    this.menuItemTitleTextView = ((TextView)findViewById(2131558609));
    this.menuItemDescriptionTextView = ((TextView)findViewById(2131558610));
    this.menuItemPriceTextView = ((TextView)findViewById(2131558611));
  }
  
  public MenuItemComponent(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\example\example75f1799f07eb\MenuItemComponent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */