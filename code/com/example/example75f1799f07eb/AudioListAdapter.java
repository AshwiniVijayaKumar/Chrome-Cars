package com.example.example75f1799f07eb;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.File;
import java.util.ArrayList;

public class AudioListAdapter
  extends BaseAdapter
{
  ArrayList<String> appointmentDetail = new ArrayList();
  Context context;
  String filepath = Environment.getExternalStorageDirectory().getPath();
  
  AudioListAdapter(ArrayList<String> paramArrayList, Context paramContext)
  {
    this.context = paramContext;
    this.appointmentDetail.clear();
    this.appointmentDetail = paramArrayList;
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
  
  public View getView(int paramInt, View paramView, final ViewGroup paramViewGroup)
  {
    paramView = ((LayoutInflater)this.context.getSystemService("layout_inflater")).inflate(2130903093, paramViewGroup, false);
    paramViewGroup = (TextView)paramView.findViewById(2131558440);
    final Object localObject = (ImageView)paramView.findViewById(2131558570);
    ImageView localImageView = (ImageView)paramView.findViewById(2131558571);
    ((ImageView)localObject).setTag(Integer.valueOf(paramInt));
    localImageView.setTag(Integer.valueOf(paramInt));
    ((ImageView)localObject).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        try
        {
          new File(AudioListAdapter.this.filepath + "/AudioRecorder/" + paramViewGroup.getText().toString().trim()).delete();
          AudioListAdapter.this.appointmentDetail.remove(Integer.parseInt(localObject.getTag().toString()));
          AudioListAdapter.this.notifyDataSetInvalidated();
          return;
        }
        catch (Exception paramAnonymousView)
        {
          paramAnonymousView.printStackTrace();
        }
      }
    });
    localImageView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        try
        {
          paramAnonymousView = Uri.fromFile(new File(AudioListAdapter.this.filepath + "/AudioRecorder/" + paramViewGroup.getText().toString().trim()));
          Intent localIntent = new Intent("android.intent.action.SEND");
          localIntent.putExtra("android.intent.extra.EMAIL", new String[0]);
          localIntent.putExtra("android.intent.extra.SUBJECT", "Subject");
          localIntent.putExtra("android.intent.extra.TEXT", "Please take a moment to listen to my new audio.");
          localIntent.setType("message/rfc822");
          localIntent.putExtra("android.intent.extra.STREAM", paramAnonymousView);
          AudioListAdapter.this.context.startActivity(Intent.createChooser(localIntent, "Choose an Email client :"));
          return;
        }
        catch (Exception paramAnonymousView)
        {
          paramAnonymousView.printStackTrace();
        }
      }
    });
    localObject = (String)this.appointmentDetail.get(paramInt);
    paramViewGroup.setText("" + (String)localObject);
    return paramView;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\example\example75f1799f07eb\AudioListAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */