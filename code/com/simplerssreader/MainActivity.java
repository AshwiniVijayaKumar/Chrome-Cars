package com.simplerssreader;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.io.File;
import java.io.PrintStream;
import java.lang.reflect.Field;

public class MainActivity
  extends FragmentActivity
{
  public static final String MyPREFERENCES = "MyPrefs";
  public static String rssURL;
  static SharedPreferences sharedpreferences;
  String Device_Oriantation = "";
  String HeaderBarbackgroundColor;
  String HeaderbarTextColor;
  String ImgURl_Land;
  String ImgURl_Port;
  int Oriantation_ID;
  File SDCardRoot;
  TextView appName;
  File file;
  String foldername = "";
  FrameLayout fragment_container;
  RelativeLayout headerBackground;
  String navigationBarType;
  
  private void Check_Device_Oriantation()
  {
    this.Oriantation_ID = getResources().getConfiguration().orientation;
    switch (this.Oriantation_ID)
    {
    default: 
      this.Device_Oriantation = "";
      Setheaderimage();
      return;
    case 2: 
      this.Device_Oriantation = "LANDSCAPE";
      Setheaderimage();
      return;
    case 1: 
      this.Device_Oriantation = "PORTRAIT";
      Setheaderimage();
      return;
    }
    this.Device_Oriantation = "UNDEFINED";
    Setheaderimage();
  }
  
  private void Setbackgroundimage()
  {
    Object localObject = getApplicationName();
    if (sharedpreferences.getString("appbackgroundType", "").equals("custom_color"))
    {
      localObject = sharedpreferences.getString("appBarbackgroundColor", "");
      this.fragment_container.setBackgroundColor(Color.parseColor((String)localObject));
    }
    do
    {
      do
      {
        return;
        this.SDCardRoot = new File(Environment.getExternalStorageDirectory(), this.foldername + (String)localObject);
        if (!this.Device_Oriantation.equals("PORTRAIT")) {
          break;
        }
        this.file = new File(this.SDCardRoot, "appbg_port_img.jpg");
      } while (!this.file.exists());
      localObject = BitmapFactory.decodeFile(this.file.getAbsolutePath());
      localObject = new BitmapDrawable(getResources(), (Bitmap)localObject);
      this.fragment_container.setBackground((Drawable)localObject);
      return;
      this.file = new File(this.SDCardRoot, "appbg_land_img.jpg");
    } while (!this.file.exists());
    localObject = BitmapFactory.decodeFile(this.file.getAbsolutePath());
    localObject = new BitmapDrawable(getResources(), (Bitmap)localObject);
    this.fragment_container.setBackground((Drawable)localObject);
  }
  
  private void Setheaderimage()
  {
    try
    {
      Object localObject1 = getApplicationName();
      this.navigationBarType = sharedpreferences.getString("navigationBarType", "");
      this.HeaderBarbackgroundColor = sharedpreferences.getString("HeaderBarbackgroundColor", "");
      this.HeaderbarTextColor = sharedpreferences.getString("HeaderbarTextColor", "");
      this.ImgURl_Port = sharedpreferences.getString("ImgURl_Port", "");
      this.ImgURl_Land = sharedpreferences.getString("ImgURl_Land", "");
      System.out.println("navigationBarType " + this.navigationBarType + " , HeaderBarbackgroundColor " + this.HeaderBarbackgroundColor + " , HeaderbarTextColor " + this.HeaderbarTextColor);
      if (this.navigationBarType.equals("image"))
      {
        this.SDCardRoot = new File(Environment.getExternalStorageDirectory(), this.foldername + (String)localObject1);
        if (this.Device_Oriantation.equals("PORTRAIT"))
        {
          this.file = new File(this.SDCardRoot, "header_port_img.jpg");
          if (!this.file.exists()) {
            return;
          }
          localObject1 = BitmapFactory.decodeFile(this.file.getAbsolutePath());
          localObject1 = new BitmapDrawable(getResources(), (Bitmap)localObject1);
          this.headerBackground.setBackground((Drawable)localObject1);
          this.appName.setText("");
          return;
        }
        this.file = new File(this.SDCardRoot, "header_land_img.jpg");
        if (!this.file.exists()) {
          return;
        }
        localObject1 = BitmapFactory.decodeFile(this.file.getAbsolutePath());
        localObject1 = new BitmapDrawable(getResources(), (Bitmap)localObject1);
        this.headerBackground.setBackground((Drawable)localObject1);
        this.appName.setText("");
        return;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      return;
    }
    System.out.println("Headerbar color code : HeaderBarbackgroundColor  " + this.HeaderBarbackgroundColor + "  ,  HeaderbarTextColor  " + this.HeaderbarTextColor);
    this.appName.setTextColor(Color.parseColor(this.HeaderbarTextColor));
    Object localObject2 = getSharedPreferences("MyPrefs", 0).getString("HeaderBarbackgroundColor", "");
    System.out.println("krishna header color>>>" + (String)localObject2);
    if (!((String)localObject2).equals(""))
    {
      Object localObject3;
      String str;
      if (((String)localObject2).contains("rgba"))
      {
        localObject3 = ((String)localObject2).split(",");
        localObject3[0] = localObject3[0].split("\\(")[1];
        localObject3[3] = localObject3[3].split("\\)")[0];
        Integer.toHexString(Integer.parseInt(localObject3[3]));
        localObject2 = Integer.toHexString(Integer.parseInt(localObject3[0]));
        str = Integer.toHexString(Integer.parseInt(localObject3[1]));
        localObject3 = Integer.toHexString(Integer.parseInt(localObject3[2]));
        str = "#" + (String)localObject2 + str + (String)localObject3;
        localObject2 = str;
        if (str.equals("#000")) {
          localObject2 = "#000000";
        }
        this.headerBackground.setBackgroundColor(Color.parseColor((String)localObject2));
        return;
      }
      if (((String)localObject2).contains("rgb"))
      {
        localObject3 = ((String)localObject2).split(",");
        localObject3[0] = localObject3[0].split("\\(")[1];
        localObject3[2] = localObject3[2].split("\\)")[0];
        localObject2 = Integer.toHexString(Integer.parseInt(localObject3[0]));
        str = Integer.toHexString(Integer.parseInt(localObject3[1]));
        localObject3 = Integer.toHexString(Integer.parseInt(localObject3[2]));
        str = "#" + (String)localObject2 + str + (String)localObject3;
        localObject2 = str;
        if (str.equals("#000")) {
          localObject2 = "#000000";
        }
        this.headerBackground.setBackgroundColor(Color.parseColor((String)localObject2));
        return;
      }
      this.headerBackground.setBackgroundColor(Color.parseColor((String)localObject2));
      return;
    }
    this.headerBackground.setBackgroundColor(Color.parseColor("#33b5e5"));
  }
  
  private void addRssFragment()
  {
    FragmentTransaction localFragmentTransaction = getSupportFragmentManager().beginTransaction();
    localFragmentTransaction.add(2131558756, new RssFragment());
    localFragmentTransaction.commit();
  }
  
  public static int getId(String paramString, Class<?> paramClass)
  {
    try
    {
      Field localField = paramClass.getDeclaredField(paramString);
      int i = localField.getInt(localField);
      return i;
    }
    catch (Exception localException)
    {
      throw new RuntimeException("No resource ID found for: " + paramString + " / " + paramClass, localException);
    }
  }
  
  public void close(View paramView)
  {
    finish();
  }
  
  public String getApplicationName()
  {
    return getString(getApplicationInfo().labelRes);
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(7);
    setContentView(2130903142);
    this.fragment_container = ((FrameLayout)findViewById(2131558756));
    try
    {
      getWindow().setFeatureInt(7, 2130903063);
      String str = getResources().getString(2131230720);
      sharedpreferences = getSharedPreferences("MyPrefs", 0);
      this.headerBackground = ((RelativeLayout)findViewById(2131558499));
      this.appName = ((TextView)findViewById(2131558500));
      str = sharedpreferences.getString("headerBarTitle", str);
      this.appName.setText(str);
      rssURL = getIntent().getStringExtra("url");
      if (paramBundle == null) {
        addRssFragment();
      }
      Check_Device_Oriantation();
      Setheaderimage();
      Setbackgroundimage();
      return;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
  }
  
  protected void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    paramBundle.putBoolean("fragment_added", true);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\simplerssreader\MainActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */