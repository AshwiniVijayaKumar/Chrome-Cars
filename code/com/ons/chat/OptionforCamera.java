package com.ons.chat;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class OptionforCamera
  extends Activity
{
  public static OptionforCamera ActivityContext = null;
  private static final int CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE = 200;
  public static final int MEDIA_TYPE_VIDEO = 2;
  public static boolean editcheck = false;
  public static ArrayList<String> name;
  AllNameList.CustomList adapter;
  private TextView appName;
  ImageView buttonRecording;
  File[] dirFiles;
  ArrayAdapter<String> directoryList;
  File f;
  private List<String> fileList = new ArrayList();
  String fileOutput;
  private Uri fileUri;
  String input;
  Intent intent;
  ListView lv;
  Button takevideo;
  
  public void close(View paramView)
  {
    finish();
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt1 == 200)
    {
      if (paramInt2 == -1)
      {
        final Object localObject = LayoutInflater.from(getApplicationContext()).inflate(2130903131, null);
        paramIntent = new AlertDialog.Builder(this);
        paramIntent.setView((View)localObject);
        localObject = (EditText)((View)localObject).findViewById(2131558710);
        ((EditText)localObject).setFilters(new InputFilter[] { new InputFilter()
        {
          public CharSequence filter(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, Spanned paramAnonymousSpanned, int paramAnonymousInt3, int paramAnonymousInt4)
          {
            while (paramAnonymousInt1 < paramAnonymousInt2)
            {
              if (!Character.isLetterOrDigit(paramAnonymousCharSequence.charAt(paramAnonymousInt1))) {
                return "";
              }
              paramAnonymousInt1 += 1;
            }
            return null;
          }
        } });
        paramIntent.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            paramAnonymousDialogInterface = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Appypie/");
            if (new File(paramAnonymousDialogInterface.getPath() + File.separator + "appy.mp4").renameTo(new File(OptionforCamera.this.f.getAbsoluteFile(), localObject.getText().toString() + ".mp4"))) {
              System.out.println("success");
            }
            for (;;)
            {
              OptionforCamera.this.dirFiles = OptionforCamera.this.f.listFiles();
              if (OptionforCamera.this.dirFiles.length == 0) {
                break;
              }
              paramAnonymousInt = 0;
              while (paramAnonymousInt < OptionforCamera.this.dirFiles.length)
              {
                OptionforCamera.this.fileOutput = OptionforCamera.this.dirFiles[paramAnonymousInt].toString();
                paramAnonymousDialogInterface = OptionforCamera.this.fileOutput;
                paramAnonymousDialogInterface = paramAnonymousDialogInterface.substring(paramAnonymousDialogInterface.lastIndexOf("/") + 1, paramAnonymousDialogInterface.length());
                OptionforCamera.this.fileList.add(paramAnonymousDialogInterface);
                paramAnonymousInt += 1;
              }
              System.out.println("error");
            }
            paramAnonymousDialogInterface = new Intent(OptionforCamera.this, AllNameList.class);
            OptionforCamera.this.startActivity(paramAnonymousDialogInterface);
          }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            paramAnonymousDialogInterface.cancel();
          }
        });
        paramIntent.create().show();
      }
    }
    else {
      return;
    }
    if (paramInt2 == 0)
    {
      System.out.println("RESULT_CANCELED");
      Toast.makeText(this, "User cancelled the video capture.", 1).show();
      return;
    }
    System.out.println("else");
    Toast.makeText(this, "Video capture failed.", 1).show();
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(7);
    setContentView(2130903118);
    getWindow().setFeatureInt(7, 2130903063);
    paramBundle = getResources().getString(2131230720);
    this.appName = ((TextView)findViewById(2131558500));
    this.appName.setText(paramBundle);
    ((ImageButton)findViewById(2131558504)).setVisibility(0);
    name = new ArrayList();
    ActivityContext = this;
    this.buttonRecording = ((ImageView)findViewById(2131558640));
    if (Environment.getExternalStorageState().equals("mounted")) {}
    for (this.f = new File(Environment.getExternalStorageDirectory() + "/Appypie");; this.f = getApplicationContext().getDir("Appypie", 0))
    {
      if (!this.f.exists()) {
        this.f.mkdir();
      }
      this.buttonRecording.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          paramAnonymousView = new Intent("android.media.action.VIDEO_CAPTURE");
          File localFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Appypie/");
          localFile = new File(localFile.getPath() + File.separator + "appy.mp4");
          OptionforCamera.access$002(OptionforCamera.this, Uri.fromFile(localFile));
          paramAnonymousView.putExtra("output", OptionforCamera.this.fileUri);
          OptionforCamera.this.startActivityForResult(paramAnonymousView, 200);
        }
      });
      return;
    }
  }
  
  public void openPlayList(View paramView)
  {
    startActivity(new Intent(this, AllNameList.class));
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ons\chat\OptionforCamera.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */