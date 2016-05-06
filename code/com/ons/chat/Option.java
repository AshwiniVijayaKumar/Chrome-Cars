package com.ons.chat;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings.Secure;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import java.io.File;
import java.io.FileInputStream;

public class Option
  extends Activity
{
  private static final int PICK_IMAGE = 1;
  static final int REQUEST_IMAGE_CAPTURE = 2;
  public static String android_id;
  public static Bitmap bitmap;
  public static String imagePath;
  Button b1;
  Button b2;
  TextView tv;
  
  public void close(View paramView)
  {
    setResult(0);
    finish();
  }
  
  public void decodeFile(String paramString)
  {
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    localOptions.inJustDecodeBounds = true;
    BitmapFactory.decodeFile(paramString, localOptions);
    int k = localOptions.outWidth;
    int j = localOptions.outHeight;
    int i = 1;
    for (;;)
    {
      if ((k < 1024) && (j < 1024))
      {
        localOptions = new BitmapFactory.Options();
        localOptions.inSampleSize = i;
        bitmap = BitmapFactory.decodeFile(paramString, localOptions);
        bitmap = ThumbnailUtils.extractThumbnail(bitmap, 250, 250);
        setResult(-1);
        finish();
        return;
      }
      k /= 2;
      j /= 2;
      i *= 2;
    }
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if ((paramInt1 == 1) && (paramInt2 == -1) && (paramIntent != null))
    {
      Object localObject = paramIntent.getData();
      paramIntent = new String[1];
      paramIntent[0] = "_data";
      localObject = getContentResolver().query((Uri)localObject, paramIntent, null, null, null);
      ((Cursor)localObject).moveToFirst();
      paramIntent = ((Cursor)localObject).getString(((Cursor)localObject).getColumnIndex(paramIntent[0]));
      ((Cursor)localObject).close();
      decodeFile(paramIntent);
      imagePath = paramIntent;
    }
    if ((paramInt1 == 2) && (paramInt2 == -1)) {}
    try
    {
      paramIntent = new File(Environment.getExternalStorageDirectory().toString() + "/" + "appypie_chat.png");
      imagePath = paramIntent.getAbsolutePath();
      bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeStream(new FileInputStream(paramIntent.getAbsolutePath())), 250, 250, false);
      setResult(-1);
      finish();
      return;
    }
    catch (Exception paramIntent) {}
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903058);
    android_id = Settings.Secure.getString(getContentResolver(), "android_id");
    this.b1 = ((Button)findViewById(2131558445));
    this.b2 = ((Button)findViewById(2131558490));
    this.tv = ((TextView)findViewById(2131558489));
    this.tv.setText(Html.fromHtml("<u>Select Image</u>"));
    this.b1.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Option.this.selectImageFromGallery();
      }
    });
    this.b2.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Option.this.selectImageFromCamera();
      }
    });
  }
  
  public void selectImageFromCamera()
  {
    Intent localIntent = new Intent("android.media.action.IMAGE_CAPTURE");
    File localFile = new File(Environment.getExternalStorageDirectory(), "appypie_chat.png");
    if (localFile.exists()) {
      localFile.delete();
    }
    localIntent.putExtra("output", Uri.fromFile(localFile));
    startActivityForResult(localIntent, 2);
  }
  
  public void selectImageFromGallery()
  {
    Intent localIntent = new Intent();
    localIntent.setType("image/*");
    localIntent.setAction("android.intent.action.GET_CONTENT");
    startActivityForResult(Intent.createChooser(localIntent, "Select Picture"), 1);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ons\chat\Option.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */