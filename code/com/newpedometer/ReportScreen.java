package com.newpedometer;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;

public class ReportScreen
  extends Activity
{
  TextView ScreenTitle;
  RecordAdapter adapter;
  ImageButton backbtn;
  ArrayList<HashMap<String, String>> freshList;
  ArrayList<HashMap<String, String>> listdata;
  ImageButton menuButton;
  PedoMeterData meterData;
  ListView reportlist;
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    setContentView(2130903137);
    this.reportlist = ((ListView)findViewById(2131558746));
    this.menuButton = ((ImageButton)findViewById(2131558542));
    this.backbtn = ((ImageButton)findViewById(2131558540));
    this.ScreenTitle = ((TextView)findViewById(2131558541));
    this.ScreenTitle.setText("Report");
    this.backbtn.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        ReportScreen.this.finish();
      }
    });
    this.menuButton.setVisibility(8);
    this.meterData = new PedoMeterData(this);
    this.listdata = new ArrayList();
    this.freshList = new ArrayList();
    this.listdata = this.meterData.getAllMessages();
    if (this.listdata.size() > 7)
    {
      int j = this.listdata.size();
      int i = this.listdata.size() - 1;
      while (i > j - 8)
      {
        this.freshList.add(this.listdata.get(i));
        i -= 1;
      }
    }
    for (this.adapter = new RecordAdapter(this.freshList);; this.adapter = new RecordAdapter(this.listdata))
    {
      this.reportlist.setAdapter(this.adapter);
      Log.e("list size in report screen is ", "" + this.listdata.size());
      Log.e("list size in report screen is ", "" + this.listdata.size());
      return;
    }
  }
  
  public class RecordAdapter
    extends BaseAdapter
  {
    ArrayList<HashMap<String, String>> datalist = new ArrayList();
    private LayoutInflater inflater;
    
    public RecordAdapter()
    {
      ArrayList localArrayList;
      this.datalist = localArrayList;
    }
    
    public int getCount()
    {
      return this.datalist.size();
    }
    
    public Object getItem(int paramInt)
    {
      return null;
    }
    
    public long getItemId(int paramInt)
    {
      return 0L;
    }
    
    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      if (paramView == null)
      {
        this.inflater = LayoutInflater.from(ReportScreen.this);
        paramView = this.inflater.inflate(2130903091, null);
        paramViewGroup = new ReportScreen.ViewHolder(ReportScreen.this);
        paramViewGroup.RecordDate = ((TextView)paramView.findViewById(2131558566));
        paramViewGroup.Record = ((TextView)paramView.findViewById(2131558567));
        paramView.setTag(paramViewGroup);
      }
      for (;;)
      {
        paramViewGroup.RecordDate.setText((CharSequence)((HashMap)this.datalist.get(paramInt)).get("recorddate"));
        paramViewGroup.Record.setText((CharSequence)((HashMap)this.datalist.get(paramInt)).get("record"));
        return paramView;
        paramViewGroup = (ReportScreen.ViewHolder)paramView.getTag();
      }
    }
  }
  
  public class ViewHolder
  {
    TextView Record;
    TextView RecordDate;
    
    public ViewHolder() {}
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\newpedometer\ReportScreen.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */