package com.example.example75f1799f07eb;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import java.io.PrintStream;

public class M3U8Player
  extends Activity
{
  TextView appName;
  private String videoId;
  
  private boolean appInstalledOrNot(String paramString)
  {
    PackageManager localPackageManager = getPackageManager();
    try
    {
      localPackageManager.getPackageInfo(paramString, 1);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramString) {}
    return false;
  }
  
  public void close(View paramView)
  {
    finish();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(7);
    setContentView(2130903098);
    getWindow().setFeatureInt(7, 2130903063);
    paramBundle = getResources().getString(2131230720);
    this.appName = ((TextView)findViewById(2131558500));
    this.appName.setText(paramBundle);
    this.videoId = getIntent().getStringExtra("m3u8id");
    this.videoId = this.videoId.trim();
    System.out.println("m3u8 url>>>>>>>" + this.videoId);
    if (appInstalledOrNot("com.mxtech.videoplayer.ad"))
    {
      paramBundle = new Intent();
      paramBundle.setAction("android.intent.action.VIEW");
      paramBundle.setData(Uri.parse(this.videoId));
      paramBundle.setPackage("com.mxtech.videoplayer.ad");
      startActivity(paramBundle);
      finish();
      return;
    }
    if (appInstalledOrNot("com.mxtech.videoplayer.pro"))
    {
      paramBundle = new Intent();
      paramBundle.setAction("android.intent.action.VIEW");
      paramBundle.setData(Uri.parse(this.videoId));
      paramBundle.setPackage("com.mxtech.videoplayer.pro");
      startActivity(paramBundle);
      finish();
      return;
    }
    try
    {
      paramBundle = new Intent("android.intent.action.VIEW");
      paramBundle.setDataAndType(Uri.parse(this.videoId), "video/*");
      finish();
      startActivity(paramBundle);
      return;
    }
    catch (Exception paramBundle) {}
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\example\example75f1799f07eb\M3U8Player.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */