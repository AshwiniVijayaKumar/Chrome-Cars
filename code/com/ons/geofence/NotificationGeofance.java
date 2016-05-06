package com.ons.geofence;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.example75f1799f07eb.ImageGalleryActivity;
import com.example.example75f1799f07eb.MyPhoneGapActivity;
import java.net.URL;
import org.apache.cordova.engine.SystemWebView;

public class NotificationGeofance
  extends Activity
{
  TextView appName;
  String fenceMsgImage = "";
  String frompushnotification = "";
  String internalUrl = "";
  boolean isImageFitToScreen;
  ImageView ivImage;
  ProgressDialog mProgressDialog;
  String message = "";
  TextView tvInternalUrl;
  TextView tvMessage;
  TextView tvUrl;
  String webUrl = "";
  WebView wevViewUrl;
  
  public void close(View paramView)
  {
    MyPhoneGapActivity.clearNotifiaction();
    finish();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    setContentView(2130903114);
    paramBundle = getResources().getString(2131230720);
    this.appName = ((TextView)findViewById(2131558628));
    this.appName.setText(paramBundle);
    this.tvMessage = ((TextView)findViewById(2131558629));
    this.tvUrl = ((TextView)findViewById(2131558630));
    this.ivImage = ((ImageView)findViewById(2131558632));
    this.tvInternalUrl = ((TextView)findViewById(2131558631));
    paramBundle = getIntent().getExtras();
    this.message = paramBundle.getString("message");
    this.webUrl = paramBundle.getString("webUrl");
    this.internalUrl = paramBundle.getString("internalUrl");
    this.fenceMsgImage = paramBundle.getString("fenceMsgImage");
    this.frompushnotification = paramBundle.getString("frompushnotification");
    if ((this.message != "") && (this.message != null))
    {
      this.tvMessage.setText(this.message);
      Log.v("amriteshlatlong", "NotificationGeofance : message : " + this.message);
    }
    if ((this.webUrl != "") && (this.webUrl.trim().length() > 0)) {
      this.tvUrl.setText(this.webUrl);
    }
    Log.v("amriteshlatlong", "NotificationGeofance : internalUrl : " + this.internalUrl);
    if ((this.internalUrl != "") && (this.internalUrl.trim().length() > 0))
    {
      this.tvInternalUrl.setText("Open Internal Page");
      Log.v("amriteshlatlong", "NotificationGeofance : fenceMsgImage : " + this.fenceMsgImage);
    }
    if ((this.fenceMsgImage != "") && (this.fenceMsgImage.trim().length() > 0)) {
      new DownloadImage(null).execute(new String[] { this.fenceMsgImage });
    }
    this.tvUrl.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView = NotificationGeofance.this.webUrl;
        try
        {
          paramAnonymousView = new Intent("android.intent.action.VIEW", Uri.parse(paramAnonymousView));
          NotificationGeofance.this.startActivity(paramAnonymousView);
          return;
        }
        catch (Exception paramAnonymousView)
        {
          paramAnonymousView.printStackTrace();
        }
      }
    });
    this.tvInternalUrl.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        MyPhoneGapActivity.cwv.loadUrl("javascript:openpagefromnotification('" + NotificationGeofance.this.internalUrl + "');");
        NotificationGeofance.this.finish();
      }
    });
    this.ivImage.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        try
        {
          Intent localIntent = new Intent(NotificationGeofance.this.getApplicationContext(), ImageGalleryActivity.class);
          localIntent.putExtra("geofance", "geofanceAmritesh");
          localIntent.putExtra("position", "0");
          localIntent.putExtra("bigImageUrls", NotificationGeofance.this.fenceMsgImage + ",");
          paramAnonymousView.getContext().startActivity(localIntent);
          return;
        }
        catch (Exception paramAnonymousView)
        {
          paramAnonymousView.printStackTrace();
        }
      }
    });
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
  }
  
  private class DownloadImage
    extends AsyncTask<String, Void, Bitmap>
  {
    private DownloadImage() {}
    
    protected Bitmap doInBackground(String... paramVarArgs)
    {
      paramVarArgs = paramVarArgs[0];
      try
      {
        paramVarArgs = BitmapFactory.decodeStream(new URL(paramVarArgs).openStream());
        return paramVarArgs;
      }
      catch (Exception paramVarArgs)
      {
        paramVarArgs.printStackTrace();
      }
      return null;
    }
    
    protected void onPostExecute(Bitmap paramBitmap)
    {
      NotificationGeofance.this.ivImage.setImageBitmap(paramBitmap);
    }
    
    protected void onPreExecute()
    {
      super.onPreExecute();
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ons\geofence\NotificationGeofance.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */