package com.newpedometer;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Pedometer
  extends Activity
  implements AdapterView.OnItemClickListener
{
  private static final int DISTANCE_MSG = 3;
  private static final int STEPS_MSG = 1;
  long Goal_Steps;
  TextView Goal_steps;
  ImageButton Menu_button;
  LinearLayout Menu_list_layout;
  ImageButton back;
  String[] countryArray = { "Pause", "Reset", "Quit" };
  String[] countryArray1 = { "Resume", "Reset", "Quit" };
  TextView datetext;
  TextView distance_units;
  TextView distancetitle;
  IntentFilter filter = new IntentFilter("Com.android.reset.value.broadcast");
  TextView goaltitle;
  boolean islistopen = false;
  boolean isresume = false;
  private StepService.ICallback mCallback = new StepService.ICallback()
  {
    public void distanceChanged(float paramAnonymousFloat)
    {
      Pedometer.this.mHandler.sendMessage(Pedometer.this.mHandler.obtainMessage(3, (int)(1000.0F * paramAnonymousFloat), 0));
    }
    
    public void stepsChanged(int paramAnonymousInt)
    {
      Pedometer.this.mHandler.sendMessage(Pedometer.this.mHandler.obtainMessage(1, paramAnonymousInt, 0));
    }
  };
  private ServiceConnection mConnection = new ServiceConnection()
  {
    public void onServiceConnected(ComponentName paramAnonymousComponentName, IBinder paramAnonymousIBinder)
    {
      Pedometer.access$002(Pedometer.this, ((StepService.StepBinder)paramAnonymousIBinder).getService());
      Pedometer.this.mService.registerCallback(Pedometer.this.mCallback);
      Pedometer.this.mService.reloadSettings();
    }
    
    public void onServiceDisconnected(ComponentName paramAnonymousComponentName)
    {
      Pedometer.access$002(Pedometer.this, null);
    }
  };
  private float mDistanceValue;
  TextView mDistanceValueView;
  private Handler mHandler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      switch (paramAnonymousMessage.what)
      {
      case 2: 
      default: 
        super.handleMessage(paramAnonymousMessage);
      case 1: 
        do
        {
          return;
          Pedometer.access$302(Pedometer.this, paramAnonymousMessage.arg1);
          Pedometer.this.mStepValueView.setText("" + Pedometer.this.mStepValue);
          Pedometer.this.preference_editor.putInt("steps", Pedometer.this.mStepValue);
          Pedometer.this.preference_editor.commit();
        } while ((Pedometer.this.mStepValue != Pedometer.this.Goal_Steps) || (Pedometer.this.mService == null) || (!Pedometer.this.mIsRunning));
        Pedometer.this.mService.showNotificationOnGoal();
        return;
      }
      Pedometer.access$502(Pedometer.this, paramAnonymousMessage.arg1 / 1000.0F);
      if (Pedometer.this.mDistanceValue <= 0.0F)
      {
        Pedometer.this.mDistanceValueView.setText("0");
        Pedometer.this.preference_editor.putFloat("distance", 0.0F);
        Pedometer.this.preference_editor.commit();
        return;
      }
      Pedometer.this.mDistanceValueView.setText(("" + (Pedometer.this.mDistanceValue + 1.0E-6F)).substring(0, 5));
      Pedometer.this.preference_editor.putFloat("distance", Float.valueOf(("" + (Pedometer.this.mDistanceValue + 1.0E-6F)).substring(0, 5)).floatValue());
      Pedometer.this.preference_editor.commit();
    }
  };
  private boolean mIsMetric;
  private boolean mIsRunning;
  private PedometerSettings mPedometerSettings;
  private boolean mQuitting = false;
  private StepService mService;
  private SharedPreferences mSettings;
  private int mStepValue;
  TextView mStepValueView;
  ListView menus_List;
  private PendingIntent pendingIntent;
  SharedPreferences.Editor preference_editor;
  SharedPreferences preferences;
  BroadcastReceiver receiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      Pedometer.this.resetValues(true);
      paramAnonymousContext = Calendar.getInstance();
      paramAnonymousContext = new SimpleDateFormat("EEE,dd MMM yyyy").format(paramAnonymousContext.getTime());
      Pedometer.this.datetext.setText(paramAnonymousContext);
    }
  };
  RelativeLayout relative;
  TextView screenTitle;
  TextView stepstitle;
  TextView totalsteps;
  
  private void bindStepService()
  {
    bindService(new Intent(this, StepService.class), this.mConnection, 3);
  }
  
  private void startStepService()
  {
    if (!this.mIsRunning)
    {
      this.mIsRunning = true;
      startService(new Intent(this, StepService.class));
    }
  }
  
  private void stopStepService()
  {
    if (this.mService != null) {
      stopService(new Intent(this, StepService.class));
    }
    this.mIsRunning = false;
  }
  
  private void unbindStepService()
  {
    unbindService(this.mConnection);
  }
  
  public void ReportScreen(View paramView)
  {
    startActivity(new Intent(this, ReportScreen.class));
  }
  
  public void SettingPage(View paramView)
  {
    startActivity(new Intent(this, Setting_Page.class));
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    setContentView(2130903122);
    this.mStepValue = 0;
  }
  
  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    if (!this.isresume)
    {
      paramAdapterView = this.countryArray[paramInt];
      if (!paramAdapterView.trim().equals("Pause")) {
        break label118;
      }
      paramAdapterView = new ArrayAdapter(this, 2130903044, this.countryArray1);
      this.menus_List.setAdapter(paramAdapterView);
      this.menus_List.setOnItemClickListener(this);
      this.isresume = true;
      unbindStepService();
      stopStepService();
    }
    for (;;)
    {
      if (this.islistopen)
      {
        this.islistopen = false;
        this.Menu_list_layout.setVisibility(8);
        this.Menu_list_layout.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), 2130968578));
      }
      return;
      paramAdapterView = this.countryArray1[paramInt];
      break;
      label118:
      if (paramAdapterView.trim().equals("Reset"))
      {
        resetValues(true);
      }
      else if (paramAdapterView.trim().equals("Quit"))
      {
        resetValues(false);
        try
        {
          unbindStepService();
          stopStepService();
          this.mQuitting = true;
          finish();
        }
        catch (IllegalArgumentException paramAdapterView)
        {
          for (;;)
          {
            Log.e("caught in illlegal state exception", "yes done");
          }
        }
      }
      else if (paramAdapterView.trim().equals("Resume"))
      {
        paramAdapterView = new ArrayAdapter(this, 2130903044, this.countryArray);
        this.menus_List.setAdapter(paramAdapterView);
        this.menus_List.setOnItemClickListener(this);
        this.isresume = false;
        startStepService();
        bindStepService();
      }
    }
  }
  
  protected void onPause()
  {
    unregisterReceiver(this.receiver);
    if (this.mIsRunning) {
      unbindStepService();
    }
    if (this.mQuitting) {
      this.mPedometerSettings.saveServiceRunningWithNullTimestamp(this.mIsRunning);
    }
    for (;;)
    {
      super.onPause();
      return;
      this.mPedometerSettings.saveServiceRunningWithTimestamp(this.mIsRunning);
    }
  }
  
  protected void onResume()
  {
    super.onResume();
    Object localObject = Typeface.createFromAsset(getAssets(), "main.ttf");
    registerReceiver(this.receiver, this.filter);
    this.mSettings = getSharedPreferences("Pedometerpreference", 0);
    this.mPedometerSettings = new PedometerSettings(this.mSettings);
    this.preferences = getSharedPreferences("state", 0);
    this.preference_editor = this.preferences.edit();
    this.mIsRunning = this.mPedometerSettings.isServiceRunning();
    int i;
    if ((!this.mIsRunning) && (this.mPedometerSettings.isNewStart()))
    {
      startStepService();
      bindStepService();
      this.mPedometerSettings.clearServiceRunning();
      this.screenTitle = ((TextView)findViewById(2131558541));
      this.screenTitle.setText("PedoMeter");
      this.mStepValueView = ((TextView)findViewById(2131558660));
      this.mDistanceValueView = ((TextView)findViewById(2131558666));
      this.datetext = ((TextView)findViewById(2131558659));
      this.totalsteps = ((TextView)findViewById(2131558661));
      this.goaltitle = ((TextView)findViewById(2131558662));
      this.stepstitle = ((TextView)findViewById(2131558664));
      this.distance_units = ((TextView)findViewById(2131558667));
      this.distancetitle = ((TextView)findViewById(2131558668));
      this.Goal_steps = ((TextView)findViewById(2131558663));
      this.back = ((ImageButton)findViewById(2131558540));
      this.Menu_button = ((ImageButton)findViewById(2131558542));
      this.Menu_list_layout = ((LinearLayout)findViewById(2131558657));
      this.menus_List = ((ListView)findViewById(2131558658));
      this.relative = ((RelativeLayout)findViewById(2131558652));
      this.datetext.setTypeface((Typeface)localObject);
      this.mStepValueView.setTypeface((Typeface)localObject, 1);
      this.mDistanceValueView.setTypeface((Typeface)localObject);
      this.totalsteps.setTypeface((Typeface)localObject);
      this.goaltitle.setTypeface((Typeface)localObject);
      this.stepstitle.setTypeface((Typeface)localObject);
      this.distance_units.setTypeface((Typeface)localObject);
      this.distancetitle.setTypeface((Typeface)localObject);
      this.Goal_steps.setTypeface((Typeface)localObject);
      this.relative.setOnTouchListener(new View.OnTouchListener()
      {
        public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
        {
          if (Pedometer.this.islistopen)
          {
            Pedometer.this.islistopen = false;
            Pedometer.this.Menu_list_layout.setVisibility(8);
            Pedometer.this.Menu_list_layout.startAnimation(AnimationUtils.loadAnimation(Pedometer.this.getApplicationContext(), 2130968578));
          }
          return false;
        }
      });
      this.mIsMetric = this.mPedometerSettings.isMetric();
      localObject = (TextView)findViewById(2131558667);
      if (!this.mIsMetric) {
        break label768;
      }
      i = 2131230890;
      label464:
      ((TextView)localObject).setText(getString(i));
      localObject = Calendar.getInstance();
      localObject = new SimpleDateFormat("EEE,dd MMM yyyy").format(((Calendar)localObject).getTime());
      this.datetext.setText((CharSequence)localObject);
      this.mStepValueView.setText("" + this.preferences.getInt("steps", 0));
      float f = this.preferences.getFloat("distance", 0.0F);
      this.mDistanceValueView.setText(String.valueOf(f + 1.0E-6F).substring(0, 5));
      if (this.preferences.getString("Goalvalue", "") != null) {
        break label775;
      }
      this.Goal_steps.setText("10000");
      this.Goal_Steps = 10000L;
    }
    for (;;)
    {
      localObject = Calendar.getInstance();
      ((Calendar)localObject).set(11, 11);
      ((Calendar)localObject).set(12, 59);
      ((Calendar)localObject).set(13, 59);
      ((Calendar)localObject).set(9, 1);
      this.pendingIntent = PendingIntent.getBroadcast(this, 0, new Intent(this, DateChangedBroadcast.class), 0);
      ((AlarmManager)getSystemService("alarm")).setRepeating(1, ((Calendar)localObject).getTimeInMillis(), 86400000L, this.pendingIntent);
      this.back.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Pedometer.this.finish();
        }
      });
      localObject = new ArrayAdapter(this, 2130903044, this.countryArray);
      this.menus_List.setAdapter((ListAdapter)localObject);
      this.menus_List.setOnItemClickListener(this);
      this.Menu_button.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (!Pedometer.this.islistopen)
          {
            Pedometer.this.islistopen = true;
            Pedometer.this.Menu_list_layout.setVisibility(0);
            Pedometer.this.Menu_list_layout.startAnimation(AnimationUtils.loadAnimation(Pedometer.this.getApplicationContext(), 2130968583));
            return;
          }
          Pedometer.this.islistopen = false;
          Pedometer.this.Menu_list_layout.setVisibility(8);
          Pedometer.this.Menu_list_layout.startAnimation(AnimationUtils.loadAnimation(Pedometer.this.getApplicationContext(), 2130968578));
        }
      });
      return;
      if (!this.mIsRunning) {
        break;
      }
      bindStepService();
      break;
      label768:
      i = 2131230891;
      break label464;
      label775:
      if (this.preferences.getString("Goalvalue", "").length() > 0)
      {
        this.Goal_steps.setText(this.preferences.getString("Goalvalue", ""));
        this.Goal_Steps = Long.parseLong(this.preferences.getString("Goalvalue", ""));
      }
      else
      {
        this.Goal_steps.setText("10000");
        this.Goal_Steps = 10000L;
      }
    }
  }
  
  public void resetValues(boolean paramBoolean)
  {
    if ((this.mService != null) && (this.mIsRunning)) {
      this.mService.resetValues();
    }
    SharedPreferences.Editor localEditor;
    do
    {
      return;
      this.mStepValueView.setText("0");
      this.mDistanceValueView.setText("0");
      localEditor = getSharedPreferences("state", 0).edit();
    } while (!paramBoolean);
    localEditor.putInt("steps", 0);
    localEditor.putFloat("distance", 0.0F);
    localEditor.commit();
  }
  
  public void resetvaluefromreciever(Context paramContext, boolean paramBoolean)
  {
    if ((this.mService != null) && (this.mIsRunning)) {
      this.mService.resetValues();
    }
    do
    {
      return;
      paramContext = paramContext.getSharedPreferences("state", 0).edit();
    } while (!paramBoolean);
    paramContext.putInt("steps", 0);
    paramContext.putFloat("distance", 0.0F);
    paramContext.commit();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\newpedometer\Pedometer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */