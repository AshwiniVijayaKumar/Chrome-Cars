package com.ons.fileexplorer;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import java.io.File;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileChooser
  extends ListActivity
{
  private FileArrayAdapter adapter;
  private File currentDir;
  
  private void fill(File paramFile)
  {
    File[] arrayOfFile = paramFile.listFiles();
    setTitle("Current Dir: " + paramFile.getName());
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    for (;;)
    {
      int i;
      try
      {
        int k = arrayOfFile.length;
        i = 0;
        if (i < k)
        {
          File localFile = arrayOfFile[i];
          Object localObject = new java.sql.Date(localFile.lastModified());
          String str = DateFormat.getDateTimeInstance().format((java.util.Date)localObject);
          if (localFile.isDirectory())
          {
            localObject = localFile.listFiles();
            if (localObject == null) {
              break label356;
            }
            j = localObject.length;
            localObject = String.valueOf(j);
            if (j == 0)
            {
              localObject = (String)localObject + " item";
              localArrayList1.add(new Item(localFile.getName(), (String)localObject, str, localFile.getAbsolutePath(), "directory_icon"));
            }
            else
            {
              localObject = (String)localObject + " items";
              continue;
            }
          }
          else
          {
            localArrayList2.add(new Item(localFile.getName(), localFile.length() + " Byte", str, localFile.getAbsolutePath(), "file_icon"));
          }
        }
      }
      catch (Exception localException)
      {
        Collections.sort(localArrayList1);
        Collections.sort(localArrayList2);
        localArrayList1.addAll(localArrayList2);
        if (!paramFile.getName().equalsIgnoreCase("sdcard")) {
          localArrayList1.add(0, new Item("..", "Parent Directory", "", paramFile.getParent(), "directory_up"));
        }
        this.adapter = new FileArrayAdapter(this, 2130903074, localArrayList1);
        setListAdapter(this.adapter);
        return;
      }
      i += 1;
      continue;
      label356:
      int j = 0;
    }
  }
  
  private void onFileClick(Item paramItem)
  {
    Intent localIntent = new Intent();
    localIntent.putExtra("GetPath", this.currentDir.toString());
    localIntent.putExtra("GetFileName", paramItem.getName());
    setResult(-1, localIntent);
    finish();
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.currentDir = new File("/sdcard/");
    fill(this.currentDir);
  }
  
  protected void onListItemClick(ListView paramListView, View paramView, int paramInt, long paramLong)
  {
    super.onListItemClick(paramListView, paramView, paramInt, paramLong);
    paramListView = this.adapter.getItem(paramInt);
    if ((paramListView.getImage().equalsIgnoreCase("directory_icon")) || (paramListView.getImage().equalsIgnoreCase("directory_up")))
    {
      this.currentDir = new File(paramListView.getPath());
      fill(this.currentDir);
      return;
    }
    onFileClick(paramListView);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ons\fileexplorer\FileChooser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */