package com.example.example75f1799f07eb;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.DecodingType;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.DisplayImageOptions.Builder;
import com.nostra13.universalimageloader.core.FailReason;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration.Builder;
import com.nostra13.universalimageloader.core.ImageLoadingListener;
import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class NotificationListAdapter
  extends BaseAdapter
{
  List<String> IdsList = new ArrayList();
  String appId;
  List<String> appointmentDetail = new ArrayList();
  Context context;
  String dateValue = "Date";
  String iconColor;
  List<String> imageList = new ArrayList();
  public ImageLoader imageLoader;
  public String[] notificationIds;
  DisplayImageOptions options;
  SharedPreferences sharedPreferences;
  String textColor;
  List<String> timeDetail = new ArrayList();
  List<String> urlList = new ArrayList();
  
  NotificationListAdapter(List<String> paramList1, List<String> paramList2, String paramString1, List<String> paramList3, List<String> paramList4, List<String> paramList5, String[] paramArrayOfString, SharedPreferences paramSharedPreferences, Context paramContext, String paramString2, String paramString3, String paramString4)
  {
    this.appId = paramString2;
    this.context = paramContext;
    this.appointmentDetail.clear();
    this.appointmentDetail = paramList1;
    this.dateValue = paramString1;
    this.timeDetail.clear();
    this.timeDetail = paramList2;
    this.urlList.clear();
    this.urlList = paramList3;
    this.imageList.clear();
    this.imageList = paramList4;
    this.IdsList.clear();
    this.IdsList = paramList5;
    this.textColor = paramString3;
    this.notificationIds = paramArrayOfString;
    this.sharedPreferences = paramSharedPreferences;
    this.iconColor = paramString4;
    paramList1 = new DisplayImageOptions.Builder().cacheOnDisc().cacheInMemory().build();
    paramList1 = new ImageLoaderConfiguration.Builder(this.context).defaultDisplayImageOptions(paramList1).build();
    ImageLoader.getInstance().init(paramList1);
    this.imageLoader = ImageLoader.getInstance();
    this.options = new DisplayImageOptions.Builder().cacheInMemory().cacheOnDisc().decodingType(DecodingType.MEMORY_SAVING).build();
  }
  
  public static Bitmap getBitmapFromURL(String paramString)
  {
    try
    {
      paramString = (HttpURLConnection)new URL(paramString).openConnection();
      paramString.setDoInput(true);
      paramString.connect();
      paramString = BitmapFactory.decodeStream(paramString.getInputStream());
      return paramString;
    }
    catch (IOException paramString) {}
    return null;
  }
  
  private void showDialog(String paramString1, String paramString2)
  {
    AlertDialog localAlertDialog = new AlertDialog.Builder(this.context).create();
    localAlertDialog.setTitle("");
    localAlertDialog.setMessage("" + paramString2 + "\n" + paramString1);
    localAlertDialog.setCanceledOnTouchOutside(false);
    localAlertDialog.setButton("OK", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.dismiss();
      }
    });
    localAlertDialog.show();
  }
  
  public void display(ImageView paramImageView, String paramString)
  {
    this.imageLoader.displayImage(paramString, paramImageView, this.options, new ImageLoadingListener()
    {
      public void onLoadingComplete() {}
      
      public void onLoadingFailed(FailReason paramAnonymousFailReason) {}
      
      public void onLoadingStarted() {}
    });
  }
  
  public int getCount()
  {
    return this.appointmentDetail.size();
  }
  
  public String getItem(int paramInt)
  {
    return null;
  }
  
  public long getItemId(int paramInt)
  {
    return paramInt;
  }
  
  public View getView(final int paramInt, View paramView, final ViewGroup paramViewGroup)
  {
    if (paramView == null)
    {
      paramView = ((Activity)this.context).getLayoutInflater();
      if (NotificationListActivity.typeLanguage.equalsIgnoreCase("en")) {
        paramView.inflate(2130903117, paramViewGroup, false);
      }
    }
    for (;;)
    {
      paramView = paramView.inflate(2130903117, paramViewGroup, false);
      paramViewGroup = new ViewHolder();
      paramViewGroup.RelativeLayout1 = ((RelativeLayout)paramView.findViewById(2131558569));
      paramViewGroup.commenttxt1 = ((TextView)paramView.findViewById(2131558440));
      paramViewGroup.commenttxt2 = ((TextView)paramView.findViewById(2131558622));
      paramViewGroup.commenttxt3 = ((TextView)paramView.findViewById(2131558623));
      paramViewGroup.commentImg4 = ((ImageView)paramView.findViewById(2131558625));
      paramViewGroup.envelope = ((ImageView)paramView.findViewById(2131558621));
      paramViewGroup.commenttxt2.setTextColor(Color.parseColor(this.textColor));
      paramViewGroup.commenttxt1.setTextColor(Color.parseColor(this.textColor));
      paramViewGroup.commenttxt3.setTextColor(Color.parseColor(this.textColor));
      paramViewGroup.envelope.setImageResource(2130837534);
      paramViewGroup.envelope.setColorFilter(Color.parseColor(this.iconColor));
      System.out.println("mohsin->Ids->" + Arrays.toString(this.notificationIds));
      System.out.println("mohsin->IdList->" + (String)this.IdsList.get(paramInt));
      int i;
      if (this.notificationIds != null)
      {
        i = 0;
        label284:
        if (i < this.notificationIds.length)
        {
          if (!this.notificationIds[i].trim().equals(((String)this.IdsList.get(paramInt)).trim())) {
            break label771;
          }
          paramViewGroup.RelativeLayout1.setAlpha(0.5F);
          paramViewGroup.envelope.setImageResource(2130837665);
          paramViewGroup.envelope.setColorFilter(Color.parseColor(this.iconColor));
          paramViewGroup.commenttxt1.setTypeface(null, 0);
          paramViewGroup.commenttxt2.setTypeface(null, 0);
          paramViewGroup.commenttxt3.setTypeface(null, 0);
        }
      }
      paramView.setTag(paramViewGroup);
      try
      {
        for (;;)
        {
          paramView.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymousView)
            {
              for (;;)
              {
                int i;
                try
                {
                  NotificationListAdapter.this.showDialog(paramViewGroup.commenttxt1.getText().toString(), paramViewGroup.commenttxt2.getText().toString());
                  int k = 0;
                  System.out.println("mohsin->Ids->" + Arrays.toString(NotificationListAdapter.this.notificationIds));
                  System.out.println("mohsin->IdList->" + (String)NotificationListAdapter.this.IdsList.get(paramInt));
                  int j = k;
                  if (NotificationListAdapter.this.notificationIds != null)
                  {
                    i = 0;
                    j = k;
                    if (i < NotificationListAdapter.this.notificationIds.length)
                    {
                      if (!NotificationListAdapter.this.notificationIds[i].trim().equals(((String)NotificationListAdapter.this.IdsList.get(paramInt)).trim())) {
                        break label436;
                      }
                      j = 1;
                    }
                  }
                  if (j == 0)
                  {
                    paramViewGroup.RelativeLayout1.setAlpha(0.5F);
                    paramViewGroup.envelope.setImageResource(2130837665);
                    paramViewGroup.envelope.setColorFilter(Color.parseColor(NotificationListAdapter.this.iconColor));
                    paramViewGroup.commenttxt1.setTypeface(null, 0);
                    paramViewGroup.commenttxt2.setTypeface(null, 0);
                    paramViewGroup.commenttxt3.setTypeface(null, 0);
                    paramAnonymousView = NotificationListAdapter.this.sharedPreferences.getString("notificationIds", null);
                    System.out.println("mohsin->notificationAllIds->" + paramAnonymousView);
                    if (paramAnonymousView == null)
                    {
                      NotificationListAdapter.this.sharedPreferences.edit().putString("notificationIds", (String)NotificationListAdapter.this.IdsList.get(paramInt)).commit();
                      return;
                    }
                    NotificationListAdapter.this.sharedPreferences.edit().putString("notificationIds", paramAnonymousView + "," + (String)NotificationListAdapter.this.IdsList.get(paramInt)).commit();
                    return;
                  }
                }
                catch (Exception paramAnonymousView)
                {
                  paramAnonymousView.printStackTrace();
                }
                return;
                label436:
                i += 1;
              }
            }
          });
          paramViewGroup.commenttxt3.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymousView) {}
          });
          String str2 = (String)this.appointmentDetail.get(paramInt);
          System.out.println("position>>>>>>>" + paramInt + "name>>>>>" + str2);
          String str1 = str2;
          if (str2.contains("$1234$")) {
            str1 = str2.replace("$1234$", ",");
          }
          paramViewGroup.commenttxt1.setText("" + str1);
          str1 = (String)this.timeDetail.get(paramInt);
          if (str1.length() > 0) {
            paramViewGroup.commenttxt2.setText(this.dateValue + ": " + str1);
          }
          if (!this.imageList.isEmpty()) {
            display(paramViewGroup.commentImg4, (String)this.imageList.get(paramInt));
          }
          paramViewGroup.commentImg4.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymousView)
            {
              try
              {
                Intent localIntent = new Intent((Activity)NotificationListAdapter.this.context, ImageGalleryActivity.class);
                localIntent.putExtra("position", "0");
                localIntent.putExtra("bigImageUrls", (String)NotificationListAdapter.this.imageList.get(paramInt));
                paramAnonymousView.getContext().startActivity(localIntent);
                return;
              }
              catch (Exception paramAnonymousView)
              {
                paramAnonymousView.printStackTrace();
              }
            }
          });
          if (this.urlList.isEmpty()) {
            break label791;
          }
          str1 = (String)this.urlList.get(paramInt);
          paramViewGroup.commenttxt3.setText("" + str1);
          Log.v("notificationAmritesh", "Url===message : " + str1);
          Log.v("notificationAmritesh", "Url===messagePosition : " + (String)this.urlList.get(paramInt));
          return paramView;
          paramView.inflate(2130903113, paramViewGroup, false);
          break;
          label771:
          i += 1;
          break label284;
          paramViewGroup = (ViewHolder)paramView.getTag();
        }
        label791:
        paramViewGroup.commenttxt3.setText("");
        return paramView;
      }
      catch (Exception paramViewGroup)
      {
        paramViewGroup.printStackTrace();
      }
    }
    return paramView;
  }
  
  static class ViewHolder
  {
    RelativeLayout RelativeLayout1;
    ImageView commentImg4;
    TextView commenttxt1;
    TextView commenttxt2;
    TextView commenttxt3;
    ImageView envelope;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\example\example75f1799f07eb\NotificationListAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */