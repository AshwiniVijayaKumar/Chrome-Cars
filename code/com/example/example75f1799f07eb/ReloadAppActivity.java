package com.example.example75f1799f07eb;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
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
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.nostra13.universalimageloader.core.DecodingType;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.DisplayImageOptions.Builder;
import com.nostra13.universalimageloader.core.FailReason;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoadingListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

public class ReloadAppActivity
  extends Activity
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
  private ImageView appBackgroundImageView;
  String appId = "";
  TextView appName;
  String appTheme = "";
  String appUpdateAvailStr = "App Updates Available.";
  String appUpdateNotAvailStr = "There are no App Updates Available.";
  ToggleButton autoUpdateButton;
  boolean autoUpdateStatus = false;
  Button b1;
  Button b2;
  Button closeBtn;
  String currentHomePackage;
  String customURL;
  String dateValue = "Date ";
  String enableShare = "";
  File file;
  String foldername = "";
  RelativeLayout hideLayout;
  String imageBackground = "";
  protected ImageLoader imageLoader = ImageLoader.getInstance();
  String jsondata;
  View lineView;
  View lineView2;
  LinearLayout linearLayout1;
  RelativeLayout loadUrlHeaderRelativeLayout;
  ImageView logoutButton;
  private Handler mHandler;
  Preferences mpreferences;
  String navigationBarType;
  ImageView notifcationButton;
  List<String> notificationList = new ArrayList();
  String notificationValue = "Last 5 Notifications";
  DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory().cacheOnDisc().decodingType(DecodingType.MEMORY_SAVING).build();
  String profileEmail = "";
  String profileNameStr = "";
  String profileNumber = "";
  TextView profileView;
  ProgressBar progress;
  ScrollView scrollLayout;
  Button searchBtn;
  EditText searchText;
  String shareStr = "";
  String shareUrl = "";
  RelativeLayout showhidlayouttt;
  TextView textView1;
  List<String> timeList = new ArrayList();
  Button updateButton;
  TextView updateText;
  TextView updateeHeaderText;
  TextView updatetextView1;
  TextView updatetextView2;
  TextView updatetextViewSmall;
  TextView userLogout;
  LinearLayout user_layout;
  
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
    if (sharedpreferences.getString("appbackgroundType", "").equals("custom_color"))
    {
      localObject = sharedpreferences.getString("appBarbackgroundColor", "");
      this.scrollLayout.setBackgroundColor(Color.parseColor((String)localObject));
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
      this.scrollLayout.setBackground((Drawable)localObject);
      return;
      this.file = new File(this.SDCardRoot, "appbg_land_img.jpg");
    } while (!this.file.exists());
    localObject = BitmapFactory.decodeFile(this.file.getAbsolutePath());
    localObject = new BitmapDrawable(getResources(), (Bitmap)localObject);
    this.scrollLayout.setBackground((Drawable)localObject);
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
            this.loadUrlHeaderRelativeLayout.setBackground((Drawable)localObject1);
            this.appName.setText("");
          }
        }
        for (;;)
        {
          this.notifcationButton.setBackgroundResource(2130837662);
          return;
          this.file = new File(this.SDCardRoot, "header_land_img.jpg");
          if (this.file.exists())
          {
            localObject1 = BitmapFactory.decodeFile(this.file.getAbsolutePath());
            localObject1 = new BitmapDrawable(getResources(), (Bitmap)localObject1);
            this.loadUrlHeaderRelativeLayout.setBackground((Drawable)localObject1);
            this.appName.setText("");
          }
        }
      }
      System.out.println("Headerbar color code : HeaderBarbackgroundColor  " + this.HeaderBarbackgroundColor + "  ,  HeaderbarTextColor  " + this.HeaderbarTextColor);
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      return;
    }
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
        this.loadUrlHeaderRelativeLayout.setBackgroundColor(Color.parseColor((String)localObject2));
        this.linearLayout1.setBackgroundColor(Color.parseColor((String)localObject2));
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
        this.loadUrlHeaderRelativeLayout.setBackgroundColor(Color.parseColor((String)localObject2));
        this.linearLayout1.setBackgroundColor(Color.parseColor((String)localObject2));
        return;
      }
      this.loadUrlHeaderRelativeLayout.setBackgroundColor(Color.parseColor((String)localObject2));
      if (this.HeaderBarbackgroundColor.equalsIgnoreCase("#000000")) {
        this.notifcationButton.setBackgroundResource(2130837662);
      }
      for (;;)
      {
        this.linearLayout1.setBackgroundColor(Color.parseColor((String)localObject2));
        return;
        this.notifcationButton.setBackgroundResource(2130837661);
      }
    }
    this.loadUrlHeaderRelativeLayout.setBackgroundColor(Color.parseColor("#33b5e5"));
    this.linearLayout1.setBackgroundColor(Color.parseColor("#33b5e5"));
  }
  
  private void changeTextColor(String paramString)
  {
    try
    {
      this.updatetextView1.setTextColor(Color.parseColor(paramString));
      this.updateeHeaderText.setTextColor(Color.parseColor(paramString));
      this.updateText.setTextColor(Color.parseColor(paramString));
      this.lineView.setBackgroundColor(Color.parseColor(paramString));
      this.lineView2.setBackgroundColor(Color.parseColor(paramString));
      this.updatetextViewSmall.setTextColor(Color.parseColor(paramString));
      this.updatetextView2.setTextColor(Color.parseColor(paramString));
      this.textView1.setTextColor(Color.parseColor(paramString));
      return;
    }
    catch (Exception paramString) {}
  }
  
  private void logoutApp()
  {
    MyPhoneGapActivity.logoutFromApp();
    finish();
  }
  
  private void rateApp()
  {
    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName())));
  }
  
  private void shareApp()
  {
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.setType("text/plain");
    localIntent.putExtra("android.intent.extra.SUBJECT", getResources().getString(2131230720) + " (Open it in Google Play Store to Download the Application)");
    localIntent.putExtra("android.intent.extra.TEXT", this.shareUrl);
    startActivity(Intent.createChooser(localIntent, "Share via"));
  }
  
  private void showDialog(String paramString, int paramInt)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setTitle("Message");
    localBuilder.setMessage(paramString);
    localBuilder.setCancelable(false);
    if (paramInt == 0)
    {
      localBuilder.setPositiveButton("YES", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          paramAnonymousDialogInterface.dismiss();
          ReloadAppActivity.this.logoutApp();
        }
      });
      localBuilder.setNegativeButton("No", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          paramAnonymousDialogInterface.dismiss();
        }
      });
    }
    for (;;)
    {
      localBuilder.show();
      return;
      localBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          paramAnonymousDialogInterface.dismiss();
        }
      });
    }
  }
  
  public void close(View paramView)
  {
    finish();
  }
  
  public void exit(View paramView)
  {
    finish();
    paramView = new Intent("android.intent.action.MAIN");
    paramView.addCategory("android.intent.category.HOME");
    paramView.setFlags(268435456);
    startActivity(paramView);
  }
  
  public String getApplicationName()
  {
    return getString(getApplicationInfo().labelRes);
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
    this.mpreferences = new Preferences(getApplicationContext());
    this.enableShare = MyPhoneGapActivity.sharedpreferences.getString("enableShare", "");
    paramBundle = MyPhoneGapActivity.sharedpreferences.getString("headerBarFont", "georgia");
    String str = sharedpreferences.getString("appLanguage", "");
    System.out.println("===== type language is in imagegalleryActivity : " + str);
    if (str.equalsIgnoreCase("en")) {
      setContentView(2130903135);
    }
    for (;;)
    {
      this.hideLayout = ((RelativeLayout)findViewById(2131558724));
      MyPhoneGapActivity.sharedpreferences.getString("appBackground", "");
      str = MyPhoneGapActivity.sharedpreferences.getString("textColor", "#ffffff");
      Object localObject1 = getResources().getString(2131230720);
      this.appName = ((TextView)findViewById(2131558451));
      this.appName.setText((CharSequence)localObject1);
      this.mHandler = new Handler();
      localObject1 = sharedpreferences.getString("headerBarTitle", "");
      if ((localObject1 != null) && (!((String)localObject1).equals(""))) {
        this.appName.setText((CharSequence)localObject1);
      }
      try
      {
        paramBundle = Typeface.createFromAsset(getAssets(), "www/fonts/" + paramBundle + ".ttf");
        this.appName.setTypeface(paramBundle);
        this.appBackgroundImageView = ((ImageView)findViewById(2131558721));
        this.loadUrlHeaderRelativeLayout = ((RelativeLayout)findViewById(2131558450));
        this.closeBtn = ((Button)findViewById(2131558740));
        this.closeBtn.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            ReloadAppActivity.this.finish();
          }
        });
        this.linearLayout1 = ((LinearLayout)findViewById(2131558453));
        this.user_layout = ((LinearLayout)findViewById(2131558743));
        this.updateText = ((TextView)findViewById(2131558731));
        this.updateButton = ((Button)findViewById(2131558704));
        this.logoutButton = ((ImageView)findViewById(2131558720));
        this.userLogout = ((TextView)findViewById(2131558745));
        this.profileView = ((TextView)findViewById(2131558744));
        this.notifcationButton = ((ImageView)findViewById(2131558719));
        this.autoUpdateButton = ((ToggleButton)findViewById(2131558729));
        this.scrollLayout = ((ScrollView)findViewById(2131558722));
        this.profileView.setText("Change Password");
        this.showhidlayouttt = ((RelativeLayout)findViewById(2131558735));
        this.showhidlayouttt.setVisibility(8);
        this.scrollLayout.setOnTouchListener(new View.OnTouchListener()
        {
          public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
          {
            ReloadAppActivity.this.user_layout.setVisibility(8);
            return false;
          }
        });
        this.updatetextView1 = ((TextView)findViewById(2131558728));
        this.updateeHeaderText = ((TextView)findViewById(2131558725));
        this.lineView = findViewById(2131558701);
        this.lineView2 = findViewById(2131558637);
        this.updatetextViewSmall = ((TextView)findViewById(2131558730));
        this.updatetextView2 = ((TextView)findViewById(2131558736));
        this.textView1 = ((TextView)findViewById(2131558489));
        this.b1 = ((Button)findViewById(2131558445));
        this.b2 = ((Button)findViewById(2131558490));
        this.searchText = ((EditText)findViewById(2131558741));
        this.searchBtn = ((Button)findViewById(2131558742));
        this.searchBtn.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            paramAnonymousView = new Intent(ReloadAppActivity.this, SearchActivity.class).putExtra("searchText", ReloadAppActivity.this.searchText.getText().toString());
            ReloadAppActivity.this.startActivity(paramAnonymousView);
            ReloadAppActivity.this.finish();
          }
        });
      }
      catch (Exception paramBundle)
      {
        try
        {
          paramBundle = sharedpreferences.getString("buttonTextColor", "");
          localObject1 = sharedpreferences.getString("primaryButtonBgColor", "");
          Object localObject2 = sharedpreferences.getString("secondaryButtonBgColor", "");
          this.updateButton.setBackgroundColor(Color.parseColor((String)localObject1));
          this.b1.setBackgroundColor(Color.parseColor((String)localObject1));
          ((LinearLayout)findViewById(2131558738)).setBackgroundColor(Color.parseColor((String)localObject2));
          this.b2.setBackgroundColor(Color.parseColor((String)localObject2));
          this.b1.setTextColor(Color.parseColor(paramBundle));
          this.b2.setTextColor(Color.parseColor(paramBundle));
          ((TextView)findViewById(2131558739)).setTextColor(Color.parseColor(paramBundle));
          try
          {
            paramBundle = new JSONObject(getIntent().getStringExtra("screenData"));
            if (paramBundle.optString("appUpdate").trim().length() > 1) {
              this.updateeHeaderText.setText(paramBundle.optString("appUpdate"));
            }
            if (paramBundle.optString("noUpdateAbailable").trim().length() > 1) {
              this.appUpdateNotAvailStr = paramBundle.optString("noUpdateAbailable");
            }
            if (paramBundle.optString("appUpdateAvailable").trim().length() > 1) {
              this.appUpdateAvailStr = paramBundle.optString("appUpdateAvailable");
            }
            if (paramBundle.optString("autoUpdate").trim().length() > 1) {
              this.updatetextView1.setText(paramBundle.optString("autoUpdate"));
            }
            if (paramBundle.optString("appUpdateAvailableDesc").trim().length() > 1) {
              this.updatetextViewSmall.setText(paramBundle.optString("appUpdateAvailableDesc"));
            }
            if (paramBundle.optString("rateAndShare").trim().length() > 1) {
              this.updatetextView2.setText(paramBundle.optString("rateAndShare"));
            }
            if ((paramBundle.has("rateAndShareDesc")) && (!paramBundle.optString("rateAndShareDesc").equalsIgnoreCase("Rate & Share Description")) && (paramBundle.optString("rateAndShareDesc").trim().length() > 1)) {
              this.textView1.setText(paramBundle.optString("rateAndShareDesc"));
            }
            if (paramBundle.optString("updateButton").trim().length() > 1) {
              this.updateButton.setText(paramBundle.optString("updateButton"));
            }
            if (paramBundle.optString("lastNotification").trim().length() > 1) {
              this.notificationValue = paramBundle.optString("lastNotification");
            }
            if (paramBundle.optString("date").trim().length() > 1) {
              this.dateValue = paramBundle.optString("date");
            }
            if (paramBundle.optString("rateNow").trim().length() > 1) {
              this.b1.setText(paramBundle.optString("rateNow"));
            }
            if (paramBundle.optString("rateNow").trim().length() > 1) {
              this.b2.setText(paramBundle.optString("shareNow"));
            }
            if (paramBundle.optString("appBackground").trim().length() > 1)
            {
              this.imageBackground = paramBundle.optString("appBackground");
              this.appId = paramBundle.optString("appId").trim();
              this.appTheme = paramBundle.optString("appTheme").trim();
              if ((paramBundle.optString("appId").trim().length() > 1) && (paramBundle.optString("appId").trim().equals("98740ab4f91c")))
              {
                this.textView1.setTextColor(getResources().getColor(17170443));
                if ((paramBundle.optString("appTheme").trim().length() > 1) && (!paramBundle.optString("appTheme").trim().equalsIgnoreCase("Transparent"))) {
                  this.appBackgroundImageView.setAlpha(0.3F);
                }
                this.imageLoader.displayImage(this.imageBackground, this.appBackgroundImageView, this.options, new ImageLoadingListener()
                {
                  public void onLoadingComplete() {}
                  
                  public void onLoadingFailed(FailReason paramAnonymousFailReason)
                  {
                    switch (ReloadAppActivity.14.$SwitchMap$com$nostra13$universalimageloader$core$FailReason[paramAnonymousFailReason.ordinal()])
                    {
                    default: 
                      return;
                    }
                    ReloadAppActivity.this.imageLoader.clearMemoryCache();
                  }
                  
                  public void onLoadingStarted() {}
                });
              }
            }
            if (paramBundle.optString("appId").trim().length() > 1) {
              paramBundle.optString("appId");
            }
          }
          catch (JSONException paramBundle)
          {
            for (;;)
            {
              paramBundle.printStackTrace();
              continue;
              this.showhidlayouttt.setVisibility(8);
              continue;
              paramBundle = localObject1[0].split(",");
              continue;
              this.hideLayout.setVisibility(8);
            }
          }
          this.b1.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymousView)
            {
              ReloadAppActivity.this.rateApp();
            }
          });
          this.b2.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymousView)
            {
              ReloadAppActivity.this.shareApp();
            }
          });
          this.currentHomePackage = getApplicationContext().getPackageName();
          this.customURL = ("https://play.google.com/store/apps/details?id=" + this.currentHomePackage);
          if (this.enableShare.equalsIgnoreCase("On"))
          {
            new JSONAsyncTask().execute(new String[0]);
            this.logoutButton.setOnClickListener(new View.OnClickListener()
            {
              public void onClick(View paramAnonymousView)
              {
                if (ReloadAppActivity.this.user_layout.isShown())
                {
                  ReloadAppActivity.this.user_layout.setVisibility(8);
                  return;
                }
                ReloadAppActivity.this.user_layout.setVisibility(0);
              }
            });
            this.userLogout.setOnClickListener(new View.OnClickListener()
            {
              public void onClick(View paramAnonymousView)
              {
                ReloadAppActivity.this.user_layout.setVisibility(8);
                ReloadAppActivity.this.showDialog("Logout now ?", 0);
              }
            });
            this.profileView.setOnClickListener(new View.OnClickListener()
            {
              public void onClick(View paramAnonymousView)
              {
                ReloadAppActivity.this.user_layout.setVisibility(8);
                ReloadAppActivity.this.startActivity(new Intent(ReloadAppActivity.this, Profile.class).putExtra("profileNameStr", ReloadAppActivity.this.profileNameStr).putExtra("profileNumber", ReloadAppActivity.this.profileNumber).putExtra("profileEmail", ReloadAppActivity.this.profileEmail));
              }
            });
            this.progress = ((ProgressBar)findViewById(2131558732));
          }
          try
          {
            localObject1 = getIntent().getStringExtra("urlData").substring(14).split("%%%");
            paramBundle = new String[0];
            System.out.println("krishna22>>>>>>>" + localObject1[0]);
            if (!localObject1[0].equalsIgnoreCase("")) {
              break label2268;
            }
            localObject2 = localObject1[1].split(",");
            this.shareStr = localObject1[3];
            System.out.println("auto update value123 >>>>>>>>" + localObject1[4]);
            System.out.println("update avail value123 >>>>>>>>" + localObject1[2]);
            this.autoUpdateStatus = Boolean.parseBoolean(localObject1[4].trim());
            if ((!localObject1[2].equalsIgnoreCase(" true")) || (this.autoUpdateStatus != true)) {
              break label2281;
            }
            this.updateText.setText(this.appUpdateAvailStr);
            if (paramBundle.length > 0)
            {
              System.out.println("krishna>>>>>>>" + paramBundle.length);
              this.notificationList = Arrays.asList(paramBundle);
              this.timeList = Arrays.asList((Object[])localObject2);
            }
            System.out.println("share length >>>>>>>>" + this.shareStr.length());
            if (this.shareStr.trim().equalsIgnoreCase("")) {
              this.shareStr = "";
            }
            System.out.println("auto update value >>>>>>>>" + this.autoUpdateStatus);
            if (!this.autoUpdateStatus) {
              break label2301;
            }
            this.autoUpdateButton.setChecked(true);
          }
          catch (Exception paramBundle)
          {
            for (;;)
            {
              paramBundle.printStackTrace();
              continue;
              this.autoUpdateButton.setChecked(false);
              continue;
              this.shareStr += " ";
              continue;
              this.logoutButton.setVisibility(0);
            }
          }
          paramBundle = getApplicationContext().getPackageName();
          if ((this.shareStr.equalsIgnoreCase("")) || (this.shareStr == null) || (this.shareStr.equals("")) || (this.shareStr.equals(null)) || ((this.shareStr.contains("_DEVICE_")) && (this.shareStr.contains("_APPNAME_"))))
          {
            this.shareStr = ("Download our app exclusively from the Android Apps Store by searching " + getResources().getString(2131230720) + "  or by visiting the below link. ");
            this.profileNameStr = localObject1[5].trim();
            this.profileEmail = localObject1[6].trim();
            this.profileNumber = localObject1[7].trim();
            localObject1 = MyPhoneGapActivity.sharedpreferences.getString("fooduserid", "");
            if ((!((String)localObject1).equalsIgnoreCase("")) && (localObject1 != null)) {
              break label2342;
            }
            this.logoutButton.setVisibility(4);
            localObject1 = new RelativeLayout.LayoutParams(-2, -2);
            ((RelativeLayout.LayoutParams)localObject1).addRule(11);
            this.notifcationButton.setLayoutParams((ViewGroup.LayoutParams)localObject1);
            this.shareUrl = ("" + this.shareStr + "https://play.google.com/store/apps/details?id=" + paramBundle);
            this.updateButton.setOnClickListener(new View.OnClickListener()
            {
              public void onClick(View paramAnonymousView)
              {
                ReloadAppActivity.this.updateText.setText("Please Wait...");
                ReloadAppActivity.this.progress.setVisibility(0);
                MyPhoneGapActivity.updateApp();
                ReloadAppActivity.this.updateButton.setVisibility(8);
                ReloadAppActivity.this.mHandler.postDelayed(new Runnable()
                {
                  public void run()
                  {
                    ReloadAppActivity.this.progress.setVisibility(4);
                    ReloadAppActivity.this.updateText.setText("Application Updated.");
                  }
                }, 5000L);
              }
            });
            changeTextColor(str);
            Check_Device_Oriantation();
            Setheaderimage();
            Setbackgroundimage();
            return;
            setContentView(2130903136);
            continue;
            paramBundle = paramBundle;
            System.out.println("............." + paramBundle.getMessage());
          }
        }
        catch (Exception paramBundle)
        {
          label2268:
          label2281:
          label2301:
          label2342:
          for (;;) {}
        }
      }
    }
  }
  
  public void onToggleClicked(View paramView)
  {
    MyPhoneGapActivity.autoUpdateCheck(((ToggleButton)paramView).isChecked());
  }
  
  public void openNotification(View paramView)
  {
    startActivity(new Intent(this, NotificationListActivity.class).putExtra("urlData", getIntent().getStringExtra("urlData")).putExtra("notificationTextValue", this.notificationValue).putExtra("dateTextValue", this.dateValue).putExtra("appBackground", this.imageBackground).putExtra("appId", this.appId).putExtra("appTheme", this.appTheme));
    overridePendingTransition(2130968585, 2130968587);
  }
  
  class JSONAsyncTask
    extends AsyncTask<String, Void, Boolean>
  {
    JSONAsyncTask() {}
    
    protected Boolean doInBackground(String... paramVarArgs)
    {
      try
      {
        paramVarArgs = new HttpGet(ReloadAppActivity.this.customURL);
        if (new DefaultHttpClient().execute(paramVarArgs).getStatusLine().getStatusCode() == 200) {
          return Boolean.valueOf(true);
        }
      }
      catch (IOException paramVarArgs)
      {
        paramVarArgs.printStackTrace();
        return Boolean.valueOf(false);
      }
      catch (Exception paramVarArgs)
      {
        for (;;)
        {
          paramVarArgs.printStackTrace();
        }
      }
    }
    
    protected void onPostExecute(Boolean paramBoolean)
    {
      if (paramBoolean.booleanValue())
      {
        ReloadAppActivity.this.showhidlayouttt.setVisibility(0);
        return;
      }
      ReloadAppActivity.this.showhidlayouttt.setVisibility(8);
    }
    
    protected void onPreExecute()
    {
      super.onPreExecute();
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\example\example75f1799f07eb\ReloadAppActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */