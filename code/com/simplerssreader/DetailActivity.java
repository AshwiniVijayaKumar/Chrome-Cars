package com.simplerssreader;

import android.app.Activity;
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
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.example75f1799f07eb.PdfView;
import com.nostra13.universalimageloader.core.DecodingType;
import com.nostra13.universalimageloader.core.DisplayImageOptions.Builder;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.io.File;
import java.io.PrintStream;

public class DetailActivity
  extends Activity
  implements View.OnClickListener
{
  public static final String MyPREFERENCES = "MyPrefs";
  static SharedPreferences sharedpreferences;
  String Device_Oriantation = "";
  String HeaderBarbackgroundColor;
  String HeaderbarTextColor;
  String ImgURl_Land;
  String ImgURl_Port;
  int Oriantation_ID;
  File SDCardRoot;
  TextView appName;
  private String bigImage;
  private ImageView bigImageView;
  private String descp;
  File file;
  String foldername = "";
  RelativeLayout headerBackground;
  private TextView htmlTextView;
  protected ImageLoader imageLoader;
  private String link;
  private TextView linkClickableTextView;
  private RelativeLayout mRelativeLayout;
  String navigationBarType;
  private String title;
  private TextView titleTextView;
  private WebView webView;
  
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
  
  public void close(View paramView)
  {
    finish();
  }
  
  public String getApplicationName()
  {
    return getString(getApplicationInfo().labelRes);
  }
  
  public void onClick(View paramView)
  {
    if (paramView.getId() == 2131558751)
    {
      paramView = new Intent(this, PdfView.class);
      paramView.putExtra("pdfurl", this.link.trim());
      startActivity(paramView);
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(7);
    setContentView(2130903139);
    try
    {
      getWindow().setFeatureInt(7, 2130903063);
      paramBundle = getResources().getString(2131230720);
      sharedpreferences = getSharedPreferences("MyPrefs", 0);
      this.headerBackground = ((RelativeLayout)findViewById(2131558499));
      this.appName = ((TextView)findViewById(2131558500));
      paramBundle = sharedpreferences.getString("headerBarTitle", paramBundle);
      this.appName.setText(paramBundle);
      this.imageLoader = ImageLoader.getInstance();
      paramBundle = getIntent().getExtras();
      this.descp = paramBundle.getString("Description");
      System.out.println("desc1->" + paramBundle.getString("Description"));
      this.link = paramBundle.getString("Link");
      System.err.println("Rss Link: " + paramBundle.getString("Link"));
      this.title = paramBundle.getString("Title").trim().replaceAll("[\n]", "");
      this.bigImage = paramBundle.getString("BigImage");
      System.out.println("ImageURL->" + this.bigImage);
      this.mRelativeLayout = ((RelativeLayout)findViewById(2131558750));
      this.titleTextView = ((TextView)findViewById(2131558747));
      this.bigImageView = ((ImageView)findViewById(2131558748));
      if (this.link == null)
      {
        this.mRelativeLayout.setVisibility(8);
        if (this.title == null) {
          break label522;
        }
        this.titleTextView.setText(this.title);
        label340:
        if (this.bigImage == null) {
          break label534;
        }
        System.out.println("ImageURL->" + this.bigImage);
        paramBundle = new DisplayImageOptions.Builder().cacheInMemory().cacheOnDisc().decodingType(DecodingType.MEMORY_SAVING).build();
        this.imageLoader.displayImage(this.bigImage, this.bigImageView, paramBundle);
        label415:
        this.htmlTextView = ((TextView)findViewById(2131558749));
        this.webView = ((WebView)findViewById(2131558498));
        if (this.descp == null) {
          break label546;
        }
        this.webView.loadData(this.descp, "text/html; charset=UTF-8", null);
        this.htmlTextView.setVisibility(8);
      }
      for (;;)
      {
        Check_Device_Oriantation();
        Setheaderimage();
        return;
        if (this.link.trim().length() <= 1) {
          break;
        }
        this.linkClickableTextView = ((TextView)findViewById(2131558751));
        this.linkClickableTextView.setOnClickListener(this);
        break;
        label522:
        this.titleTextView.setVisibility(8);
        break label340;
        label534:
        this.bigImageView.setVisibility(8);
        break label415;
        label546:
        if (this.link != null)
        {
          this.htmlTextView.setVisibility(8);
          this.webView.loadUrl(this.link);
        }
      }
    }
    catch (Exception paramBundle)
    {
      for (;;) {}
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\simplerssreader\DetailActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */