package com.example.example75f1799f07eb;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;

public class ListAudio
  extends ListActivity
{
  private static final int STEP_VALUE = 4000;
  private static final int UPDATE_FREQUENCY = 500;
  TextView appName;
  
  public void close(View paramView)
  {
    finish();
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(7);
    setContentView(2130903094);
    getWindow().setFeatureInt(7, 2130903063);
    paramBundle = getResources().getString(2131230720);
    this.appName = ((TextView)findViewById(2131558500));
    this.appName.setText(paramBundle);
    paramBundle = getIntent().getStringExtra("path");
    System.out.println("jjjjjjjjjjjjjjjjjjjjj-" + paramBundle);
    Log.e("path", "" + new File(paramBundle).getParentFile().toString());
    Object localObject = new File(new File(paramBundle).getParentFile().toString()).listFiles();
    Log.d("Files", "Size: " + localObject.length);
    paramBundle = new ArrayList();
    int i = 0;
    while (i < localObject.length)
    {
      paramBundle.add(localObject[i].getName());
      System.out.println(i + "---" + localObject[i].getName());
      i += 1;
    }
    localObject = getListView();
    setListAdapter(new AudioListAdapter(paramBundle, this));
    ((ListView)localObject).setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      @SuppressLint({"ResourceAsColor"})
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        paramAnonymousView.setSelected(true);
        paramAnonymousInt = 0;
        while (paramAnonymousInt < paramAnonymousAdapterView.getChildCount())
        {
          paramAnonymousAdapterView.getChildAt(paramAnonymousInt).setBackgroundColor(0);
          paramAnonymousInt += 1;
        }
        paramAnonymousView.setBackgroundColor(2131361829);
        paramAnonymousAdapterView = ((TextView)paramAnonymousView.findViewById(2131558440)).getText().toString();
        paramAnonymousView = Environment.getExternalStorageDirectory().getPath();
        paramAnonymousAdapterView = paramAnonymousView + "/AudioRecorder/" + paramAnonymousAdapterView;
        paramAnonymousView = new Intent(ListAudio.this, RecordPlay.class);
        paramAnonymousView.putExtra("path", paramAnonymousAdapterView);
        ListAudio.this.startActivity(paramAnonymousView);
      }
    });
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\example\example75f1799f07eb\ListAudio.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */