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
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class MenuScreen
  extends Activity
  implements View.OnClickListener
{
  ImageView imageBackToHome;
  Map<Integer, String> mapMenuDescriptionList;
  Map<Integer, String> mapMenuHeaderList;
  Map<MenuItemComponent, Integer> mapMenuItemComponentList;
  Map<Integer, String> mapMenuPriceList;
  LinearLayout menuBody;
  MenuItemComponent menuItem;
  TextView textHeader;
  TextView txtSaparetor;
  
  public String getMenuDataFromXML()
  {
    Object localObject1 = null;
    try
    {
      Object localObject3 = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new FileInputStream(new File(getFilesDir(), "appypie.xml")));
      ((Document)localObject3).getDocumentElement().normalize();
      System.out.println("getThemeFromXML()=");
      Object localObject2 = ((Document)localObject3).getElementsByTagName("menu").item(0);
      ((Document)localObject3).getElementsByTagName("aboutUs");
      localObject3 = ((Element)localObject2).getElementsByTagName("MenuItems");
      System.out.println(((NodeList)localObject3).getLength());
      int i = 0;
      while (i < ((NodeList)localObject3).getLength())
      {
        Element localElement = (Element)((NodeList)localObject3).item(i);
        this.mapMenuHeaderList.put(Integer.valueOf(i), localElement.getElementsByTagName("itemHeader").item(0).getTextContent());
        this.mapMenuDescriptionList.put(Integer.valueOf(i), localElement.getElementsByTagName("itemDescription").item(0).getTextContent());
        this.mapMenuPriceList.put(Integer.valueOf(i), localElement.getElementsByTagName("itemPrice").item(0).getTextContent());
        i += 1;
      }
      localObject2 = ((Node)localObject2).getTextContent().toString();
      localObject1 = localObject2;
    }
    catch (DOMException localDOMException)
    {
      for (;;)
      {
        localDOMException.printStackTrace();
      }
    }
    catch (ParserConfigurationException localParserConfigurationException)
    {
      for (;;)
      {
        localParserConfigurationException.printStackTrace();
      }
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        localIOException.printStackTrace();
      }
    }
    catch (SAXException localSAXException)
    {
      for (;;)
      {
        localSAXException.printStackTrace();
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
      }
    }
    if (localObject1 != null) {
      return (String)localObject1;
    }
    return "NoTheme";
  }
  
  public void onClick(View paramView)
  {
    if (paramView == this.imageBackToHome)
    {
      finish();
      return;
    }
    Intent localIntent = new Intent(this, MenuItemScreen.class);
    localIntent.putExtra("MENU_TITLE", (String)this.mapMenuHeaderList.get(this.mapMenuItemComponentList.get((MenuItemComponent)paramView)));
    localIntent.putExtra("MENU_DESCRIPTION", (String)this.mapMenuDescriptionList.get(this.mapMenuItemComponentList.get((MenuItemComponent)paramView)));
    localIntent.putExtra("MENU_PRICE", (String)this.mapMenuPriceList.get(this.mapMenuItemComponentList.get((MenuItemComponent)paramView)));
    localIntent.setFlags(67108864);
    startActivity(localIntent);
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
    this.mapMenuHeaderList = new HashMap();
    this.mapMenuDescriptionList = new HashMap();
    this.mapMenuPriceList = new HashMap();
    this.mapMenuItemComponentList = new HashMap();
    getMenuDataFromXML();
    int i = 0;
    while (i < this.mapMenuHeaderList.size())
    {
      this.txtSaparetor = new TextView(this);
      this.txtSaparetor.setBackgroundColor(-1);
      this.txtSaparetor.setHeight(3);
      this.menuBody.addView(this.txtSaparetor);
      this.menuItem = new MenuItemComponent(this);
      this.menuItem.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
      this.menuItem.setPadding(10, 20, 10, 20);
      this.menuItem.menuItemTitleTextView.setText((CharSequence)this.mapMenuHeaderList.get(Integer.valueOf(i)));
      this.menuItem.menuItemDescriptionTextView.setText((CharSequence)this.mapMenuDescriptionList.get(Integer.valueOf(i)));
      this.menuItem.menuItemPriceTextView.append((CharSequence)this.mapMenuPriceList.get(Integer.valueOf(i)));
      this.menuItem.setOnClickListener(this);
      this.mapMenuItemComponentList.put(this.menuItem, Integer.valueOf(i));
      this.menuBody.addView(this.menuItem);
      i += 1;
    }
    this.txtSaparetor = new TextView(this);
    this.txtSaparetor.setBackgroundColor(-1);
    this.txtSaparetor.setHeight(3);
    this.menuBody.addView(this.txtSaparetor);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\example\example75f1799f07eb\MenuScreen.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */