package com.google.zxing.client.android.history;

import android.app.AlertDialog.Builder;
import android.app.ListActivity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;
import com.google.zxing.client.android.CaptureActivity;
import com.google.zxing.client.android.R.id;
import com.google.zxing.client.android.R.menu;
import com.google.zxing.client.android.R.string;
import java.util.Iterator;
import java.util.List;

public final class HistoryActivity
  extends ListActivity
{
  private static final String TAG = HistoryActivity.class.getSimpleName();
  private HistoryItemAdapter adapter;
  private HistoryManager historyManager;
  
  private void reloadHistoryItems()
  {
    Object localObject = this.historyManager.buildHistoryItems();
    this.adapter.clear();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      HistoryItem localHistoryItem = (HistoryItem)((Iterator)localObject).next();
      this.adapter.add(localHistoryItem);
    }
    if (this.adapter.isEmpty()) {
      this.adapter.add(new HistoryItem(null, null, null));
    }
  }
  
  public boolean onContextItemSelected(MenuItem paramMenuItem)
  {
    int i = paramMenuItem.getItemId();
    this.historyManager.deleteHistoryItem(i);
    reloadHistoryItems();
    return true;
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.historyManager = new HistoryManager(this);
    this.adapter = new HistoryItemAdapter(this);
    setListAdapter(this.adapter);
    registerForContextMenu(getListView());
  }
  
  public void onCreateContextMenu(ContextMenu paramContextMenu, View paramView, ContextMenu.ContextMenuInfo paramContextMenuInfo)
  {
    int i = ((AdapterView.AdapterContextMenuInfo)paramContextMenuInfo).position;
    if ((i >= this.adapter.getCount()) || (((HistoryItem)this.adapter.getItem(i)).getResult() != null)) {
      paramContextMenu.add(0, i, i, R.string.history_clear_one_history_text);
    }
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    if (this.historyManager.hasHistoryItems()) {
      getMenuInflater().inflate(R.menu.history, paramMenu);
    }
    return super.onCreateOptionsMenu(paramMenu);
  }
  
  protected void onListItemClick(ListView paramListView, View paramView, int paramInt, long paramLong)
  {
    if (((HistoryItem)this.adapter.getItem(paramInt)).getResult() != null)
    {
      paramListView = new Intent(this, CaptureActivity.class);
      paramListView.putExtra("ITEM_NUMBER", paramInt);
      setResult(-1, paramListView);
      finish();
    }
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    int i = paramMenuItem.getItemId();
    if (i == R.id.menu_history_send)
    {
      paramMenuItem = HistoryManager.saveHistory(this.historyManager.buildHistory().toString());
      if (paramMenuItem == null)
      {
        paramMenuItem = new AlertDialog.Builder(this);
        paramMenuItem.setMessage(R.string.msg_unmount_usb);
        paramMenuItem.setPositiveButton(R.string.button_ok, null);
        paramMenuItem.show();
        return true;
      }
      Intent localIntent = new Intent("android.intent.action.SEND", Uri.parse("mailto:"));
      localIntent.addFlags(524288);
      String str = getResources().getString(R.string.history_email_title);
      localIntent.putExtra("android.intent.extra.SUBJECT", str);
      localIntent.putExtra("android.intent.extra.TEXT", str);
      localIntent.putExtra("android.intent.extra.STREAM", paramMenuItem);
      localIntent.setType("text/csv");
      try
      {
        startActivity(localIntent);
        return true;
      }
      catch (ActivityNotFoundException paramMenuItem)
      {
        Log.w(TAG, paramMenuItem.toString());
        return true;
      }
    }
    if (i == R.id.menu_history_clear_text)
    {
      paramMenuItem = new AlertDialog.Builder(this);
      paramMenuItem.setMessage(R.string.msg_sure);
      paramMenuItem.setCancelable(true);
      paramMenuItem.setPositiveButton(R.string.button_ok, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          HistoryActivity.this.historyManager.clearHistory();
          paramAnonymousDialogInterface.dismiss();
          HistoryActivity.this.finish();
        }
      });
      paramMenuItem.setNegativeButton(R.string.button_cancel, null);
      paramMenuItem.show();
      return true;
    }
    return super.onOptionsItemSelected(paramMenuItem);
  }
  
  protected void onResume()
  {
    super.onResume();
    reloadHistoryItems();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\history\HistoryActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */