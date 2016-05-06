package com.example.example75f1799f07eb;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy.Builder;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.DecodingType;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.DisplayImageOptions.Builder;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;

@SuppressLint({"NewApi"})
@TargetApi(16)
public class NotificationListActivity
  extends Activity
{
  public static final String MyPREFERENCES = "MyPrefs";
  static SharedPreferences sharedpreferences;
  public static String typeLanguage = "";
  String Device_Oriantation = "";
  String HeaderBarbackgroundColor;
  String HeaderbarTextColor;
  List<String> IdsList = new ArrayList();
  String ImgURl_Land;
  String ImgURl_Port;
  int Oriantation_ID;
  File SDCardRoot;
  View alphaForWhiteImage;
  private ImageView appBackgroundNotificationImageView;
  private String appId = "";
  TextView appName;
  String appTheme = "";
  String dateValue = "Date";
  File file;
  String foldername = "";
  String iconColor;
  private String imageBackground = "";
  List<String> imageList = new ArrayList();
  protected ImageLoader imageLoader = ImageLoader.getInstance();
  JSONArray json;
  View lineView2;
  ListView lv;
  Preferences mpreferences;
  String navigationBarType;
  TextView noNotificationTxt;
  String[] noti;
  TextView notificationHeader;
  String[] notificationIds;
  List<String> notificationList = new ArrayList();
  DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory().cacheOnDisc().decodingType(DecodingType.MEMORY_SAVING).build();
  RelativeLayout rlcustomeheader;
  String textColor;
  List<String> timeList = new ArrayList();
  RelativeLayout topView;
  List<String> urlList = new ArrayList();
  
  static
  {
    StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
  }
  
  private void Check_Device_Oriantation()
  {
    this.Oriantation_ID = getResources().getConfiguration().orientation;
    switch (this.Oriantation_ID)
    {
    default: 
      this.Device_Oriantation = "";
      Setheaderimage();
      Setbackgroundimage();
      return;
    case 2: 
      this.Device_Oriantation = "LANDSCAPE";
      Setbackgroundimage();
      Setheaderimage();
      return;
    case 1: 
      this.Device_Oriantation = "PORTRAIT";
      Setheaderimage();
      Setbackgroundimage();
      return;
    }
    this.Device_Oriantation = "UNDEFINED";
    Setheaderimage();
    Setbackgroundimage();
  }
  
  private void Setbackgroundimage()
  {
    Object localObject = getApplicationName();
    String str = sharedpreferences.getString("appbackgroundType", "");
    if (MyPhoneGapActivity.AppId.equalsIgnoreCase("ea50786091be")) {
      this.alphaForWhiteImage.setVisibility(0);
    }
    if (str.equals("custom_color"))
    {
      localObject = sharedpreferences.getString("appBarbackgroundColor", "");
      this.topView.setBackgroundColor(Color.parseColor((String)localObject));
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
      this.topView.setBackground((Drawable)localObject);
      return;
      this.file = new File(this.SDCardRoot, "appbg_land_img.jpg");
    } while (!this.file.exists());
    localObject = BitmapFactory.decodeFile(this.file.getAbsolutePath());
    localObject = new BitmapDrawable(getResources(), (Bitmap)localObject);
    this.topView.setBackground((Drawable)localObject);
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
          if (this.file.exists())
          {
            localObject1 = BitmapFactory.decodeFile(this.file.getAbsolutePath());
            localObject1 = new BitmapDrawable(getResources(), (Bitmap)localObject1);
            this.rlcustomeheader.setBackground((Drawable)localObject1);
            this.appName.setText("");
          }
        }
        else
        {
          this.file = new File(this.SDCardRoot, "header_land_img.jpg");
          if (this.file.exists())
          {
            localObject1 = BitmapFactory.decodeFile(this.file.getAbsolutePath());
            localObject1 = new BitmapDrawable(getResources(), (Bitmap)localObject1);
            this.rlcustomeheader.setBackground((Drawable)localObject1);
            this.appName.setText("");
          }
        }
      }
      else
      {
        System.out.println("Headerbar color code : HeaderBarbackgroundColor  " + this.HeaderBarbackgroundColor + "  ,  HeaderbarTextColor  " + this.HeaderbarTextColor);
        this.appName.setTextColor(Color.parseColor(this.HeaderbarTextColor));
        this.appName.setTextColor(Color.parseColor(this.HeaderbarTextColor));
        localObject1 = getSharedPreferences("MyPrefs", 0).getString("HeaderBarbackgroundColor", "");
        System.out.println("krishna header color>>>" + (String)localObject1);
        if (!((String)localObject1).equals(""))
        {
          Object localObject2;
          String str;
          if (((String)localObject1).contains("rgba"))
          {
            localObject2 = ((String)localObject1).split(",");
            localObject2[0] = localObject2[0].split("\\(")[1];
            localObject2[3] = localObject2[3].split("\\)")[0];
            Integer.toHexString(Integer.parseInt(localObject2[3]));
            localObject1 = Integer.toHexString(Integer.parseInt(localObject2[0]));
            str = Integer.toHexString(Integer.parseInt(localObject2[1]));
            localObject2 = Integer.toHexString(Integer.parseInt(localObject2[2]));
            str = "#" + (String)localObject1 + str + (String)localObject2;
            localObject1 = str;
            if (str.equals("#000")) {
              localObject1 = "#000000";
            }
            this.rlcustomeheader.setBackgroundColor(Color.parseColor((String)localObject1));
            return;
          }
          if (((String)localObject1).contains("rgb"))
          {
            localObject2 = ((String)localObject1).split(",");
            localObject2[0] = localObject2[0].split("\\(")[1];
            localObject2[2] = localObject2[2].split("\\)")[0];
            localObject1 = Integer.toHexString(Integer.parseInt(localObject2[0]));
            str = Integer.toHexString(Integer.parseInt(localObject2[1]));
            localObject2 = Integer.toHexString(Integer.parseInt(localObject2[2]));
            str = "#" + (String)localObject1 + str + (String)localObject2;
            localObject1 = str;
            if (str.equals("#000")) {
              localObject1 = "#000000";
            }
            this.rlcustomeheader.setBackgroundColor(Color.parseColor((String)localObject1));
            return;
          }
          this.rlcustomeheader.setBackgroundColor(Color.parseColor((String)localObject1));
          return;
        }
        this.rlcustomeheader.setBackgroundColor(Color.parseColor("#33b5e5"));
      }
      return;
    }
    catch (Exception localException) {}
  }
  
  private void changeTextColor(String paramString)
  {
    this.notificationHeader.setTextColor(Color.parseColor(paramString));
    this.noNotificationTxt.setTextColor(Color.parseColor(paramString));
    this.lineView2.setBackgroundColor(Color.parseColor(paramString));
  }
  
  public void close(View paramView)
  {
    MyPhoneGapActivity.clearNotifiaction();
    finish();
  }
  
  public String getApplicationName()
  {
    return getString(getApplicationInfo().labelRes);
  }
  
  public String notificationServiceCall(String paramString1, String paramString2)
  {
    System.out.println("mohsin->notificationServiceCall->" + paramString1);
    try
    {
      paramString2 = new SoapObject("http://" + MyPhoneGapActivity.appDomainValue + "/services/utility-soap/", "getNotification");
      AndroidHttpTransport localAndroidHttpTransport = new AndroidHttpTransport("http://" + MyPhoneGapActivity.appDomainValue + "/services/utility-soap/");
      paramString2.addProperty("appId", paramString1);
      paramString2.addProperty("type", "Android");
      paramString1 = new SoapSerializationEnvelope(110);
      paramString1.setOutputSoapObject(paramString2);
      localAndroidHttpTransport.call("http://schemas.xmlsoap.org/soap/envelope/", paramString1);
      paramString2 = (KvmSerializable)paramString1.bodyIn;
      paramString1 = (SoapObject)paramString1.bodyIn;
      paramString2.getProperty(0).toString();
      if (paramString2.getProperty(0).toString().contains("No Notification"))
      {
        System.out.println("mohsin->0 notification.");
        return "fail";
      }
      System.out.println("Your request sent successfully.");
      this.json = new JSONArray(paramString2.getProperty(0).toString());
      if (this.json.length() > 0)
      {
        System.out.println("mohsin->json->" + this.json);
        int i = 0;
        while (i < this.json.length())
        {
          this.notificationList.add(this.json.getJSONObject(i).getString("notification"));
          this.timeList.add(this.json.getJSONObject(i).getString("date"));
          paramString1 = this.json.getJSONObject(i);
          if (paramString1.has("url")) {
            this.urlList.add(this.json.getJSONObject(i).getString("url"));
          }
          if (paramString1.has("image")) {
            this.imageList.add(this.json.getJSONObject(i).getString("image"));
          }
          if (paramString1.has("id")) {
            this.IdsList.add(this.json.getJSONObject(i).getString("id"));
          }
          Log.v("urlStr===>>>", this.json.getJSONObject(i).getString("url"));
          Log.v("imageStr===>>>", "" + this.json.getJSONObject(i).getString("image"));
          i += 1;
        }
        this.lv.setAdapter(new NotificationListAdapter(this.notificationList, this.timeList, this.dateValue, this.urlList, this.imageList, this.IdsList, this.notificationIds, sharedpreferences, this, this.appId, this.textColor, this.iconColor));
      }
      else
      {
        System.out.println("mohsin->No result found");
      }
    }
    catch (Exception paramString1)
    {
      for (;;)
      {
        System.out.println(paramString1.toString() + "Exception SOAP-:" + paramString1.getMessage());
      }
    }
    return "success";
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    if (paramConfiguration.orientation == 2)
    {
      this.Device_Oriantation = "LANDSCAPE";
      Setheaderimage();
      Setbackgroundimage();
    }
    while (paramConfiguration.orientation != 1) {
      return;
    }
    this.Device_Oriantation = "PORTRAIT";
    Setheaderimage();
    Setbackgroundimage();
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    sharedpreferences = getSharedPreferences("MyPrefs", 0);
    typeLanguage = sharedpreferences.getString("appLanguage", "");
    System.out.println("===== applang is in notifiacation class : " + typeLanguage);
    if (typeLanguage.equalsIgnoreCase("en")) {
      setContentView(2130903115);
    }
    Object localObject2;
    for (;;)
    {
      this.topView = ((RelativeLayout)findViewById(2131558633));
      this.alphaForWhiteImage = findViewById(2131558634);
      this.mpreferences = new Preferences(getApplicationContext());
      AnimationUtils.loadAnimation(getApplicationContext(), 2130968582);
      this.rlcustomeheader = ((RelativeLayout)findViewById(2131558450));
      paramBundle = getResources().getString(2131230720);
      this.appName = ((TextView)findViewById(2131558451));
      this.appName.setText(paramBundle);
      Object localObject1 = MyPhoneGapActivity.sharedpreferences.getString("headerBarFont", "georgia");
      this.appBackgroundNotificationImageView = ((ImageView)findViewById(2131558635));
      this.notificationHeader = ((TextView)findViewById(2131558636));
      this.noNotificationTxt = ((TextView)findViewById(2131558639));
      this.lineView2 = findViewById(2131558637);
      this.textColor = sharedpreferences.getString("textColor", "");
      this.iconColor = sharedpreferences.getString("iconColor", "");
      paramBundle = sharedpreferences.getString("notificationIds", null);
      localObject2 = sharedpreferences.getString("headerBarTitle", "");
      if ((localObject2 != null) && (!((String)localObject2).equals(""))) {
        this.appName.setText((CharSequence)localObject2);
      }
      try
      {
        localObject1 = Typeface.createFromAsset(getAssets(), "www/fonts/" + (String)localObject1 + ".ttf");
        this.appName.setTypeface((Typeface)localObject1);
        if (paramBundle != null)
        {
          paramBundle = paramBundle.split(",");
          if (paramBundle.length <= 5) {
            break label599;
          }
          this.notificationIds = new String[5];
          int j = 0;
          i = paramBundle.length - 1;
          if (i >= 0)
          {
            this.notificationIds[j] = paramBundle[i];
            j += 1;
            if (j != 5) {
              break label611;
            }
          }
        }
        if (this.textColor.length() < 7) {
          this.textColor += "000";
        }
        this.lv = ((ListView)findViewById(2131558638));
        if ((getIntent().getStringExtra("frompushnotification") != null) && (getIntent().getStringExtra("frompushnotification").equals("true")))
        {
          notificationServiceCall(MyPhoneGapActivity.AppId, "Android");
          changeTextColor(this.textColor);
          Check_Device_Oriantation();
          Setheaderimage();
          Setbackgroundimage();
          return;
          setContentView(2130903116);
        }
      }
      catch (Exception localException)
      {
        for (;;)
        {
          int i;
          System.out.println("............." + localException.getMessage());
          continue;
          label599:
          this.notificationIds = new String[paramBundle.length];
          continue;
          label611:
          i -= 1;
        }
      }
    }
    for (;;)
    {
      String[] arrayOfString1;
      try
      {
        paramBundle = getIntent().getStringExtra("urlData");
        this.imageBackground = getIntent().getStringExtra("appBackground");
        this.appId = getIntent().getStringExtra("appId");
        this.appTheme = getIntent().getStringExtra("appTheme");
        this.dateValue = getIntent().getStringExtra("dateTextValue");
        arrayOfString1 = paramBundle.substring(14).split("%%%");
        paramBundle = new String[0];
        System.out.println("krishna22>>>>>>>" + arrayOfString1[0]);
        if (!arrayOfString1[0].equalsIgnoreCase("")) {
          break label1038;
        }
        localObject2 = arrayOfString1[1].split(",");
        String[] arrayOfString2 = arrayOfString1[8].split(",");
        String[] arrayOfString3 = arrayOfString1[9].split(",");
        String[] arrayOfString4 = arrayOfString1[10].split(",");
        Log.v("urlStr===>>>", arrayOfString1[8]);
        Log.v("ImageStr===>>>", arrayOfString1[9]);
        if (paramBundle.length <= 0) {
          break label1052;
        }
        System.out.println("krishna>>>>>>>" + paramBundle.length);
        this.notificationList = Arrays.asList(paramBundle);
        this.timeList = Arrays.asList((Object[])localObject2);
        this.urlList = Arrays.asList(arrayOfString2);
        this.imageList = Arrays.asList(arrayOfString3);
        this.IdsList = Arrays.asList(arrayOfString4);
        Log.v("ImageStr==imageList  ===>>>", this.urlList.toString());
        Log.v("urlStr==urlList===>>>", this.imageList.toString());
        Log.v("ImageStr==imageList  ===>>>", this.urlList.toString());
        Log.v("urlStr==urlList===>>>", this.imageList.toString());
        this.lv.setAdapter(new NotificationListAdapter(this.notificationList, this.timeList, this.dateValue, this.urlList, this.imageList, this.IdsList, this.notificationIds, sharedpreferences, this, this.appId, this.textColor, this.iconColor));
        this.notificationHeader.setText(getIntent().getStringExtra("notificationTextValue"));
      }
      catch (Exception paramBundle)
      {
        paramBundle.printStackTrace();
      }
      break;
      label1038:
      paramBundle = arrayOfString1[0].split(",");
      continue;
      label1052:
      this.noNotificationTxt.setVisibility(0);
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\example\example75f1799f07eb\NotificationListActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */