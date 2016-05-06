package com.newpedometer;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Setting_Page
  extends Activity
{
  TextView Distance_title;
  TextView Screen_Title;
  ImageButton back;
  TextView daily_goal_text;
  EditText goal_edit;
  TextView goal_title;
  ImageButton kilometer;
  private PedometerSettings mPedometerSettings;
  private SharedPreferences mSettings;
  ImageButton menubtn;
  ImageButton mile;
  SharedPreferences pref;
  SharedPreferences.Editor preference_editor;
  SharedPreferences preferences;
  TextView stepstitle;
  
  public void init()
  {
    this.mSettings = getSharedPreferences("Pedometerpreference", 0);
    this.mPedometerSettings = new PedometerSettings(this.mSettings);
    this.preferences = getSharedPreferences("state", 0);
    this.preference_editor = this.preferences.edit();
    Typeface localTypeface = Typeface.createFromAsset(getAssets(), "main.ttf");
    this.goal_title = ((TextView)findViewById(2131558763));
    this.daily_goal_text = ((TextView)findViewById(2131558766));
    this.stepstitle = ((TextView)findViewById(2131558767));
    this.Distance_title = ((TextView)findViewById(2131558770));
    this.goal_edit = ((EditText)findViewById(2131558768));
    this.kilometer = ((ImageButton)findViewById(2131558771));
    this.mile = ((ImageButton)findViewById(2131558772));
    this.back = ((ImageButton)findViewById(2131558540));
    this.Screen_Title = ((TextView)findViewById(2131558541));
    this.menubtn = ((ImageButton)findViewById(2131558542));
    this.menubtn.setVisibility(8);
    this.Screen_Title.setText("Settings");
    this.back.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Setting_Page.this.finish();
      }
    });
    if (this.preferences.getString("Goalvalue", "") == null)
    {
      this.goal_edit.setText("10000");
      if (!this.mPedometerSettings.getpreferencevalue().trim().equals("Kilometer")) {
        break label419;
      }
      this.kilometer.setVisibility(0);
      this.mile.setVisibility(8);
    }
    for (;;)
    {
      this.goal_title.setTypeface(localTypeface, 1);
      this.daily_goal_text.setTypeface(localTypeface, 1);
      this.stepstitle.setTypeface(localTypeface, 1);
      this.Distance_title.setTypeface(localTypeface, 1);
      this.stepstitle.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (Setting_Page.this.goal_edit.getText().toString().length() == 0)
          {
            Setting_Page.this.goal_edit.setError("Please Enter Goal");
            return;
          }
          Setting_Page.this.preference_editor.putString("Goalvalue", Setting_Page.this.goal_edit.getText().toString());
          Setting_Page.this.preference_editor.commit();
          Toast.makeText(Setting_Page.this.getApplicationContext(), "Goal Is Now Set", 1).show();
        }
      });
      this.kilometer.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Setting_Page.this.kilometer.setVisibility(8);
          Setting_Page.this.mile.setVisibility(0);
          Setting_Page.this.mPedometerSettings.setKilometerOrMile("mile");
        }
      });
      this.mile.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Setting_Page.this.mile.setVisibility(8);
          Setting_Page.this.kilometer.setVisibility(0);
          Setting_Page.this.mPedometerSettings.setKilometerOrMile("Kilometer");
        }
      });
      return;
      if (this.preferences.getString("Goalvalue", "").length() > 0)
      {
        this.goal_edit.setText(this.preferences.getString("Goalvalue", ""));
        break;
      }
      this.goal_edit.setText("10000");
      break;
      label419:
      this.mile.setVisibility(0);
      this.kilometer.setVisibility(8);
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    setContentView(2130903147);
    init();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\newpedometer\Setting_Page.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */