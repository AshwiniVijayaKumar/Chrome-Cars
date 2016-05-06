package com.google.zxing.client.android.share;

import android.app.ListActivity;
import android.content.Intent;
import android.view.View;
import android.widget.ListView;
import com.google.zxing.client.android.common.executor.AsyncTaskExecInterface;
import com.google.zxing.client.android.common.executor.AsyncTaskExecManager;
import java.util.ArrayList;
import java.util.List;

public final class AppPickerActivity
  extends ListActivity
{
  private LoadPackagesAsyncTask backgroundTask;
  private final List<String[]> labelsPackages = new ArrayList();
  private final AsyncTaskExecInterface taskExec = (AsyncTaskExecInterface)new AsyncTaskExecManager().build();
  
  protected void onListItemClick(ListView paramListView, View paramView, int paramInt, long paramLong)
  {
    if ((paramInt >= 0) && (paramInt < this.labelsPackages.size()))
    {
      paramListView = "market://details?id=" + ((String[])this.labelsPackages.get(paramInt))[1];
      paramView = new Intent();
      paramView.addFlags(524288);
      paramView.putExtra("url", paramListView);
      setResult(-1, paramView);
    }
    for (;;)
    {
      finish();
      return;
      setResult(0);
    }
  }
  
  protected void onPause()
  {
    LoadPackagesAsyncTask localLoadPackagesAsyncTask = this.backgroundTask;
    if (localLoadPackagesAsyncTask != null)
    {
      localLoadPackagesAsyncTask.cancel(true);
      this.backgroundTask = null;
    }
    super.onPause();
  }
  
  protected void onResume()
  {
    super.onResume();
    this.labelsPackages.clear();
    this.backgroundTask = new LoadPackagesAsyncTask(this);
    this.taskExec.execute(this.backgroundTask, new List[] { this.labelsPackages });
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\share\AppPickerActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */