package com.ons.musicplayer;

import android.app.ListActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ListView;
import android.widget.TextView;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

public class PlayListActivity
  extends ListActivity
{
  ArrayAdapter adapter;
  TextView appName;
  TextView autoCompleteTextView1;
  String[] playerInfo;
  public ArrayList<HashMap<String, String>> songsList = new ArrayList();
  private String typeLanguage;
  
  public void close(View paramView)
  {
    finish();
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(7);
    setContentView(2130903126);
    this.typeLanguage = getIntent().getStringExtra("applanguage");
    System.out.println("===== type language is in PlayListActivioty : " + this.typeLanguage);
    if (this.typeLanguage.equalsIgnoreCase("en")) {
      getWindow().setFeatureInt(7, 2130903063);
    }
    for (;;)
    {
      paramBundle = getResources().getString(2131230720);
      this.appName = ((TextView)findViewById(2131558500));
      this.appName.setText(paramBundle);
      this.autoCompleteTextView1 = ((TextView)findViewById(2131558697));
      paramBundle = new ArrayList();
      SongsManager localSongsManager = new SongsManager();
      this.playerInfo = getIntent().getStringExtra("songInfo").split("@@--@@");
      this.songsList = localSongsManager.getPlayList(this.playerInfo);
      paramBundle.clear();
      int i = 0;
      while (i < this.songsList.size())
      {
        paramBundle.add(((HashMap)this.songsList.get(i)).get("songTitle"));
        i += 1;
      }
      getWindow().setFeatureInt(7, 2130903065);
    }
    this.adapter = new ArrayAdapter(this, 2130903127, 2131558698, paramBundle);
    setListAdapter(this.adapter);
    getListView().setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        paramAnonymousAdapterView = new Intent(PlayListActivity.this.getApplicationContext(), MainActivity.class);
        paramAnonymousAdapterView.putExtra("songIndex", paramAnonymousInt);
        PlayListActivity.this.setResult(100, paramAnonymousAdapterView);
        PlayListActivity.this.finish();
      }
    });
    this.autoCompleteTextView1.addTextChangedListener(new TextWatcher()
    {
      public void afterTextChanged(Editable paramAnonymousEditable) {}
      
      public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
      
      public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
        PlayListActivity.this.adapter.getFilter().filter(paramAnonymousCharSequence);
      }
    });
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ons\musicplayer\PlayListActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */