package com.example.example75f1799f07eb;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class MenuItemScreen
  extends Activity
  implements View.OnClickListener
{
  String MenuDescription;
  String MenuPrice;
  String MenuTitle;
  ImageView imageBackToHome;
  LinearLayout menuBody;
  MenuItemComponent menuItem;
  TextView textHeader;
  TextView txtSaparetor;
  
  public void onClick(View paramView)
  {
    if (paramView == this.imageBackToHome) {
      finish();
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    setContentView(2130903103);
    this.textHeader = ((TextView)findViewById(2131558520));
    this.textHeader.setText(getResources().getString(2131230720));
    this.textHeader.setOnClickListener(this);
    this.imageBackToHome = ((ImageView)findViewById(2131558521));
    this.imageBackToHome.setOnClickListener(this);
    this.menuBody = ((LinearLayout)findViewById(2131558612));
    paramBundle = getIntent().getExtras();
    if (paramBundle != null)
    {
      this.MenuTitle = paramBundle.getString("MENU_TITLE");
      this.MenuDescription = paramBundle.getString("MENU_DESCRIPTION");
      this.MenuPrice = paramBundle.getString("MENU_PRICE");
    }
    this.txtSaparetor = new TextView(this);
    this.txtSaparetor.setBackgroundColor(-1);
    this.txtSaparetor.setHeight(3);
    this.menuBody.addView(this.txtSaparetor);
    this.menuItem = new MenuItemComponent(this);
    this.menuItem.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
    this.menuItem.setPadding(10, 20, 10, 20);
    this.menuItem.menuItemTitleTextView.setText(this.MenuTitle);
    this.menuItem.menuItemDescriptionTextView.setText(this.MenuDescription);
    this.menuItem.menuItemPriceTextView.setText(this.MenuPrice);
    this.menuBody.addView(this.menuItem);
    this.txtSaparetor = new TextView(this);
    this.txtSaparetor.setBackgroundColor(-1);
    this.txtSaparetor.setHeight(3);
    this.menuBody.addView(this.txtSaparetor);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\example\example75f1799f07eb\MenuItemScreen.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */