package com.google.zxing.client.android.book;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import com.google.zxing.client.android.HttpHelper;
import com.google.zxing.client.android.HttpHelper.ContentType;
import com.google.zxing.client.android.LocaleManager;
import com.google.zxing.client.android.R.id;
import com.google.zxing.client.android.R.layout;
import com.google.zxing.client.android.R.string;
import com.google.zxing.client.android.common.executor.AsyncTaskExecInterface;
import com.google.zxing.client.android.common.executor.AsyncTaskExecManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class SearchBookContentsActivity
  extends Activity
{
  private static final Pattern GT_ENTITY_PATTERN = Pattern.compile("&gt;");
  private static final Pattern LT_ENTITY_PATTERN;
  private static final Pattern QUOTE_ENTITY_PATTERN = Pattern.compile("&#39;");
  private static final Pattern QUOT_ENTITY_PATTERN = Pattern.compile("&quot;");
  private static final String TAG = SearchBookContentsActivity.class.getSimpleName();
  private static final Pattern TAG_PATTERN = Pattern.compile("\\<.*?\\>");
  private final View.OnClickListener buttonListener = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      SearchBookContentsActivity.this.launchSearch();
    }
  };
  private TextView headerView;
  private String isbn;
  private final View.OnKeyListener keyListener = new View.OnKeyListener()
  {
    public boolean onKey(View paramAnonymousView, int paramAnonymousInt, KeyEvent paramAnonymousKeyEvent)
    {
      if ((paramAnonymousInt == 66) && (paramAnonymousKeyEvent.getAction() == 0))
      {
        SearchBookContentsActivity.this.launchSearch();
        return true;
      }
      return false;
    }
  };
  private NetworkTask networkTask;
  private Button queryButton;
  private EditText queryTextView;
  private ListView resultListView;
  private final AsyncTaskExecInterface taskExec = (AsyncTaskExecInterface)new AsyncTaskExecManager().build();
  
  static
  {
    LT_ENTITY_PATTERN = Pattern.compile("&lt;");
  }
  
  private void launchSearch()
  {
    String str = this.queryTextView.getText().toString();
    if ((str != null) && (str.length() > 0))
    {
      NetworkTask localNetworkTask = this.networkTask;
      if (localNetworkTask != null) {
        localNetworkTask.cancel(true);
      }
      this.networkTask = new NetworkTask(null);
      this.taskExec.execute(this.networkTask, new String[] { str, this.isbn });
      this.headerView.setText(R.string.msg_sbc_searching_book);
      this.resultListView.setAdapter(null);
      this.queryTextView.setEnabled(false);
      this.queryButton.setEnabled(false);
    }
  }
  
  String getISBN()
  {
    return this.isbn;
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    CookieSyncManager.createInstance(this);
    CookieManager.getInstance().removeExpiredCookie();
    paramBundle = getIntent();
    if ((paramBundle == null) || (!paramBundle.getAction().equals("com.google.zxing.client.android.SEARCH_BOOK_CONTENTS")))
    {
      finish();
      return;
    }
    this.isbn = paramBundle.getStringExtra("ISBN");
    if (LocaleManager.isBookSearchUrl(this.isbn)) {
      setTitle(getString(R.string.sbc_name));
    }
    for (;;)
    {
      setContentView(R.layout.search_book_contents);
      this.queryTextView = ((EditText)findViewById(R.id.query_text_view));
      paramBundle = paramBundle.getStringExtra("QUERY");
      if ((paramBundle != null) && (paramBundle.length() > 0)) {
        this.queryTextView.setText(paramBundle);
      }
      this.queryTextView.setOnKeyListener(this.keyListener);
      this.queryButton = ((Button)findViewById(R.id.query_button));
      this.queryButton.setOnClickListener(this.buttonListener);
      this.resultListView = ((ListView)findViewById(R.id.result_list_view));
      this.headerView = ((TextView)LayoutInflater.from(this).inflate(R.layout.search_book_contents_header, this.resultListView, false));
      this.resultListView.addHeaderView(this.headerView);
      return;
      setTitle(getString(R.string.sbc_name) + ": ISBN " + this.isbn);
    }
  }
  
  protected void onPause()
  {
    NetworkTask localNetworkTask = this.networkTask;
    if (localNetworkTask != null)
    {
      localNetworkTask.cancel(true);
      this.networkTask = null;
    }
    super.onPause();
  }
  
  protected void onResume()
  {
    super.onResume();
    this.queryTextView.selectAll();
  }
  
  private final class NetworkTask
    extends AsyncTask<String, Object, JSONObject>
  {
    private NetworkTask() {}
    
    private void handleSearchResults(JSONObject paramJSONObject)
    {
      try
      {
        int j = paramJSONObject.getInt("number_of_results");
        SearchBookContentsActivity.this.headerView.setText(SearchBookContentsActivity.this.getString(R.string.msg_sbc_results) + " : " + j);
        if (j > 0)
        {
          paramJSONObject = paramJSONObject.getJSONArray("search_results");
          SearchBookContentsResult.setQuery(SearchBookContentsActivity.this.queryTextView.getText().toString());
          ArrayList localArrayList = new ArrayList(j);
          int i = 0;
          while (i < j)
          {
            localArrayList.add(parseResult(paramJSONObject.getJSONObject(i)));
            i += 1;
          }
          SearchBookContentsActivity.this.resultListView.setOnItemClickListener(new BrowseBookListener(SearchBookContentsActivity.this, localArrayList));
          SearchBookContentsActivity.this.resultListView.setAdapter(new SearchBookContentsAdapter(SearchBookContentsActivity.this, localArrayList));
          return;
        }
        if ("false".equals(paramJSONObject.optString("searchable"))) {
          SearchBookContentsActivity.this.headerView.setText(R.string.msg_sbc_book_not_searchable);
        }
        SearchBookContentsActivity.this.resultListView.setAdapter(null);
        return;
      }
      catch (JSONException paramJSONObject)
      {
        Log.w(SearchBookContentsActivity.TAG, "Bad JSON from book search", paramJSONObject);
        SearchBookContentsActivity.this.resultListView.setAdapter(null);
        SearchBookContentsActivity.this.headerView.setText(R.string.msg_sbc_failed);
      }
    }
    
    private SearchBookContentsResult parseResult(JSONObject paramJSONObject)
    {
      try
      {
        String str2 = paramJSONObject.getString("page_id");
        String str1 = paramJSONObject.getString("page_number");
        boolean bool;
        if (str1.length() > 0)
        {
          str1 = SearchBookContentsActivity.this.getString(R.string.msg_sbc_page) + ' ' + str1;
          paramJSONObject = paramJSONObject.optString("snippet_text");
          bool = true;
          if (paramJSONObject.length() <= 0) {
            break label163;
          }
          paramJSONObject = SearchBookContentsActivity.TAG_PATTERN.matcher(paramJSONObject).replaceAll("");
          paramJSONObject = SearchBookContentsActivity.LT_ENTITY_PATTERN.matcher(paramJSONObject).replaceAll("<");
          paramJSONObject = SearchBookContentsActivity.GT_ENTITY_PATTERN.matcher(paramJSONObject).replaceAll(">");
          paramJSONObject = SearchBookContentsActivity.QUOTE_ENTITY_PATTERN.matcher(paramJSONObject).replaceAll("'");
          paramJSONObject = SearchBookContentsActivity.QUOT_ENTITY_PATTERN.matcher(paramJSONObject).replaceAll("\"");
        }
        for (;;)
        {
          return new SearchBookContentsResult(str2, str1, paramJSONObject, bool);
          str1 = SearchBookContentsActivity.this.getString(R.string.msg_sbc_unknown_page);
          break;
          label163:
          paramJSONObject = '(' + SearchBookContentsActivity.this.getString(R.string.msg_sbc_snippet_unavailable) + ')';
          bool = false;
        }
        return new SearchBookContentsResult(SearchBookContentsActivity.this.getString(R.string.msg_sbc_no_page_returned), "", "", false);
      }
      catch (JSONException paramJSONObject) {}
    }
    
    protected JSONObject doInBackground(String... paramVarArgs)
    {
      String str = paramVarArgs[0];
      paramVarArgs = paramVarArgs[1];
      try
      {
        if (LocaleManager.isBookSearchUrl(paramVarArgs)) {
          paramVarArgs = paramVarArgs.substring(paramVarArgs.indexOf('=') + 1);
        }
        for (paramVarArgs = "http://www.google.com/books?id=" + paramVarArgs + "&jscmd=SearchWithinVolume2&q=" + str;; paramVarArgs = "http://www.google.com/books?vid=isbn" + paramVarArgs + "&jscmd=SearchWithinVolume2&q=" + str) {
          return new JSONObject(HttpHelper.downloadViaHttp(paramVarArgs, HttpHelper.ContentType.JSON).toString());
        }
        return null;
      }
      catch (IOException paramVarArgs)
      {
        Log.w(SearchBookContentsActivity.TAG, "Error accessing book search", paramVarArgs);
        return null;
      }
      catch (JSONException paramVarArgs)
      {
        Log.w(SearchBookContentsActivity.TAG, "Error accessing book search", paramVarArgs);
      }
    }
    
    protected void onPostExecute(JSONObject paramJSONObject)
    {
      if (paramJSONObject == null) {
        SearchBookContentsActivity.this.headerView.setText(R.string.msg_sbc_failed);
      }
      for (;;)
      {
        SearchBookContentsActivity.this.queryTextView.setEnabled(true);
        SearchBookContentsActivity.this.queryTextView.selectAll();
        SearchBookContentsActivity.this.queryButton.setEnabled(true);
        return;
        handleSearchResults(paramJSONObject);
      }
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\book\SearchBookContentsActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */