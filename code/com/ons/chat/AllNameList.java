package com.ons.chat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class AllNameList
  extends Activity
{
  public static AllNameList ActivityContext = null;
  private static final int CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE = 200;
  public static final int MEDIA_TYPE_VIDEO = 2;
  public static boolean editcheck = false;
  public static ArrayList<String> name;
  public static List<String> path1 = new ArrayList();
  CustomList adapter;
  private TextView appName;
  File[] dirFiles;
  ArrayAdapter<String> directoryList;
  File f;
  private List<String> fileList = new ArrayList();
  String fileOutput;
  private Uri fileUri;
  String input;
  Intent intent;
  ListView lv;
  int position1;
  Button takevideo;
  
  public void close(View paramView)
  {
    finish();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(7);
    setContentView(2130903050);
    getWindow().setFeatureInt(7, 2130903063);
    paramBundle = getResources().getString(2131230720);
    this.appName = ((TextView)findViewById(2131558500));
    this.appName.setText(paramBundle);
    ((ImageButton)findViewById(2131558504)).setVisibility(8);
    this.lv = ((ListView)findViewById(2131558459));
    name = new ArrayList();
    ActivityContext = this;
    this.f = new File(Environment.getExternalStorageDirectory() + "/Appypie");
    this.dirFiles = this.f.listFiles();
    if (this.dirFiles.length != 0)
    {
      path1.clear();
      int i = 0;
      while (i < this.dirFiles.length)
      {
        this.fileOutput = this.dirFiles[i].toString();
        this.input = this.fileOutput;
        this.input = this.input.substring(this.input.lastIndexOf("/") + 1, this.input.length());
        this.fileList.add(this.input);
        path1.add(this.fileOutput);
        i += 1;
      }
    }
    this.adapter = new CustomList(this, this.fileList);
    this.lv.setAdapter(this.adapter);
  }
  
  public void openPlayList(View paramView) {}
  
  class CustomList
    extends ArrayAdapter<String>
  {
    private final Activity context;
    private final List<String> web;
    
    public CustomList(List<String> paramList)
    {
      super(2130903095, localList);
      this.context = paramList;
      this.web = localList;
    }
    
    @SuppressLint({"ViewHolder"})
    public View getView(final int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      paramView = this.context.getLayoutInflater().inflate(2130903095, null, true);
      paramViewGroup = (TextView)paramView.findViewById(2131558572);
      ImageButton localImageButton1 = (ImageButton)paramView.findViewById(2131558573);
      ImageButton localImageButton2 = (ImageButton)paramView.findViewById(2131558574);
      paramViewGroup.setText((CharSequence)this.web.get(paramInt));
      final String str = (String)AllNameList.this.adapter.getItem(paramInt);
      str = Environment.getExternalStorageDirectory() + "/Appypie/" + str;
      View.OnClickListener local1 = new View.OnClickListener()
      {
        @SuppressLint({"NewApi"})
        public void onClick(View paramAnonymousView)
        {
          paramAnonymousView = LayoutInflater.from(AllNameList.this.getApplicationContext()).inflate(2130903130, null);
          AlertDialog.Builder localBuilder = new AlertDialog.Builder(AllNameList.this);
          localBuilder.setView(paramAnonymousView);
          localBuilder.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
            {
              new File(AllNameList.CustomList.1.this.val$path).delete();
              AllNameList.this.fileList.remove(AllNameList.CustomList.1.this.val$position);
              AllNameList.this.adapter.notifyDataSetChanged();
              AllNameList.this.lv.invalidate();
            }
          }).setNegativeButton("Cancel", new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
            {
              paramAnonymous2DialogInterface.cancel();
            }
          });
          localBuilder.create().show();
        }
      };
      View.OnClickListener local2 = new View.OnClickListener()
      {
        @SuppressLint({"NewApi"})
        public void onClick(View paramAnonymousView)
        {
          paramAnonymousView = new Intent(AllNameList.this, Playscreen.class);
          paramAnonymousView.putExtra("path", paramInt);
          System.out.println("position" + paramInt);
          AllNameList.this.startActivity(paramAnonymousView);
        }
      };
      localImageButton2.setOnClickListener(new View.OnClickListener()
      {
        @SuppressLint({"NewApi"})
        public void onClick(View paramAnonymousView)
        {
          try
          {
            paramAnonymousView = new File(str);
            Intent localIntent = new Intent("android.intent.action.SEND");
            localIntent.setType("image/*,video/*");
            localIntent.putExtra("android.intent.extra.STREAM", Uri.fromFile(paramAnonymousView));
            AllNameList.this.startActivity(Intent.createChooser(localIntent, "Share Video!"));
            return;
          }
          catch (Exception paramAnonymousView)
          {
            paramAnonymousView.printStackTrace();
          }
        }
      });
      localImageButton1.setOnClickListener(local1);
      paramViewGroup.setOnClickListener(local2);
      return paramView;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ons\chat\AllNameList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */