package com.google.zxing.client.android.share;

import android.app.ListActivity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Browser;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

public final class BookmarkPickerActivity
  extends ListActivity
{
  private static final String[] BOOKMARK_PROJECTION = { "title", "url" };
  private static final String BOOKMARK_SELECTION = "bookmark = 1";
  private static final String TAG = BookmarkPickerActivity.class.getSimpleName();
  static final int TITLE_COLUMN = 0;
  static final int URL_COLUMN = 1;
  private Cursor cursor = null;
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.cursor = getContentResolver().query(Browser.BOOKMARKS_URI, BOOKMARK_PROJECTION, "bookmark = 1", null, null);
    if (this.cursor == null)
    {
      Log.w(TAG, "No cursor returned for bookmark query");
      finish();
      return;
    }
    startManagingCursor(this.cursor);
    setListAdapter(new BookmarkAdapter(this, this.cursor));
  }
  
  protected void onListItemClick(ListView paramListView, View paramView, int paramInt, long paramLong)
  {
    if ((!this.cursor.isClosed()) && (this.cursor.moveToPosition(paramInt)))
    {
      paramListView = new Intent();
      paramListView.addFlags(524288);
      paramListView.putExtra("title", this.cursor.getString(0));
      paramListView.putExtra("url", this.cursor.getString(1));
      setResult(-1, paramListView);
    }
    for (;;)
    {
      finish();
      return;
      setResult(0);
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\share\BookmarkPickerActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */