package com.simplerssreader;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.example.example75f1799f07eb.PdfView;
import java.util.List;

public class RssFragment
  extends Fragment
  implements AdapterView.OnItemClickListener
{
  private boolean isNetworkAvailable = true;
  private ListView listView;
  private ProgressBar progressBar;
  private final ResultReceiver resultReceiver = new ResultReceiver(new Handler())
  {
    protected void onReceiveResult(int paramAnonymousInt, Bundle paramAnonymousBundle)
    {
      RssFragment.this.progressBar.setVisibility(8);
      paramAnonymousBundle = (List)paramAnonymousBundle.getSerializable("items");
      if (paramAnonymousBundle != null)
      {
        paramAnonymousBundle = new RssAdapter(RssFragment.this.getActivity(), paramAnonymousBundle);
        RssFragment.this.listView.setAdapter(paramAnonymousBundle);
        return;
      }
      Toast.makeText(RssFragment.this.getActivity(), "An error occured while downloading the rss feed.", 1).show();
    }
  };
  private View view;
  
  private void startService()
  {
    Intent localIntent = new Intent(getActivity(), RssService.class);
    localIntent.putExtra("receiver", this.resultReceiver);
    getActivity().startService(localIntent);
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setRetainInstance(true);
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    if (this.view == null)
    {
      this.view = paramLayoutInflater.inflate(2130903140, paramViewGroup, false);
      this.progressBar = ((ProgressBar)this.view.findViewById(2131558458));
      this.listView = ((ListView)this.view.findViewById(2131558459));
      this.listView.setOnItemClickListener(this);
      if (this.isNetworkAvailable) {
        startService();
      }
    }
    for (;;)
    {
      return this.view;
      this.progressBar.setVisibility(8);
      Toast.makeText(getActivity(), "Internet Not Working", 1).show();
      continue;
      ((ViewGroup)this.view.getParent()).removeView(this.view);
    }
  }
  
  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    paramAdapterView = (RssItem)((RssAdapter)paramAdapterView.getAdapter()).getItem(paramInt);
    if ((!paramAdapterView.getDescription().equals("")) || (paramAdapterView.getDescription().length() > 0))
    {
      paramView = new Bundle();
      paramView.putString("Link", paramAdapterView.getLink());
      paramView.putString("Description", paramAdapterView.getDescription());
      paramView.putString("BigImage", paramAdapterView.getImage());
      paramView.putString("Title", paramAdapterView.getTitle());
      paramView.putString("Date", paramAdapterView.getDate());
      paramAdapterView = new Intent(getActivity(), DetailActivity.class);
      paramAdapterView.putExtras(paramView);
      startActivity(paramAdapterView);
      return;
    }
    paramView = new Intent(getActivity(), PdfView.class);
    paramView.putExtra("pdfurl", paramAdapterView.getLink());
    startActivity(paramView);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\simplerssreader\RssFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */