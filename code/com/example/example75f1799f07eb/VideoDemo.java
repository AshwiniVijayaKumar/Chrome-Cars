package com.example.example75f1799f07eb;

import android.annotation.TargetApi;
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
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;
import java.io.File;
import java.io.PrintStream;

@TargetApi(11)
public class VideoDemo
  extends YouTubeBaseActivity
  implements YouTubePlayer.OnInitializedListener
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
  AlertDialogManager alert;
  TextView appName;
  File file;
  String foldername = "";
  RelativeLayout headerBackground;
  String navigationBarType;
  private String videoId;
  private String videoInfo;
  TextView videodata;
  
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
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(7);
    setContentView(2130903159);
    getWindow().setFeatureInt(7, 2130903063);
    try
    {
      paramBundle = getResources().getString(2131230720);
      sharedpreferences = getSharedPreferences("MyPrefs", 0);
      this.headerBackground = ((RelativeLayout)findViewById(2131558499));
      this.appName = ((TextView)findViewById(2131558500));
      paramBundle = sharedpreferences.getString("headerBarTitle", paramBundle);
      this.appName.setText(paramBundle);
      this.videoId = getIntent().getStringExtra("videoId");
      this.videoInfo = getIntent().getStringExtra("videoInfo");
      this.videodata = ((TextView)findViewById(2131558796));
      this.videodata.setText(this.videoInfo);
      this.alert = new AlertDialogManager();
      Check_Device_Oriantation();
      Setheaderimage();
      if ((this.videoId.trim().equalsIgnoreCase("")) || (this.videoId.trim() == null))
      {
        this.alert.showAlertDialog(this, MyApplicationName.APP_NAME, "Please install Youtube app from play store.", 2);
        return;
      }
      ((YouTubePlayerView)findViewById(2131558795)).initialize("AIzaSyCA4uFxSabaAXUf59yTVZtX_JTJ3PJsPlk", this);
      return;
    }
    catch (Exception paramBundle) {}
  }
  
  public void onInitializationFailure(YouTubePlayer.Provider paramProvider, YouTubeInitializationResult paramYouTubeInitializationResult)
  {
    this.alert.showAlertDialog(this, MyApplicationName.APP_NAME, "Video can't be play.Video id not found", 2);
  }
  
  public void onInitializationSuccess(YouTubePlayer.Provider paramProvider, YouTubePlayer paramYouTubePlayer, boolean paramBoolean)
  {
    if (!paramBoolean) {
      paramYouTubePlayer.loadVideo(this.videoId);
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\example\example75f1799f07eb\VideoDemo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */