package com.newpedometer;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class PedometerSettings
{
  SharedPreferences mSettings;
  
  public PedometerSettings(SharedPreferences paramSharedPreferences)
  {
    this.mSettings = paramSharedPreferences;
  }
  
  public void clearServiceRunning()
  {
    SharedPreferences.Editor localEditor = this.mSettings.edit();
    localEditor.putBoolean("service_running", false);
    localEditor.putLong("last_seen", 0L);
    localEditor.commit();
  }
  
  public float getSensitivity()
  {
    return Float.valueOf(13.0F).floatValue();
  }
  
  public float getStepLength()
  {
    try
    {
      float f = Float.valueOf(20.0F).floatValue();
      return f;
    }
    catch (NumberFormatException localNumberFormatException) {}
    return 0.0F;
  }
  
  public String getpreferencevalue()
  {
    return this.mSettings.getString("Distance_type", "");
  }
  
  public boolean isMetric()
  {
    if (this.mSettings.getString("Distance_type", "") == null) {}
    while ((this.mSettings.getString("Distance_type", "").equals("Kilometer")) || (!this.mSettings.getString("Distance_type", "").equals("mile"))) {
      return true;
    }
    return false;
  }
  
  public boolean isNewStart()
  {
    return this.mSettings.getLong("last_seen", 0L) < Utils.currentTimeInMillis() - 600000L;
  }
  
  public boolean isServiceRunning()
  {
    return this.mSettings.getBoolean("service_running", false);
  }
  
  public boolean keepScreenOn()
  {
    return true;
  }
  
  public String privious_Matric_Type()
  {
    return this.mSettings.getString("privious_matric_type", "");
  }
  
  public void saveServiceRunningWithNullTimestamp(boolean paramBoolean)
  {
    SharedPreferences.Editor localEditor = this.mSettings.edit();
    localEditor.putBoolean("service_running", paramBoolean);
    localEditor.putLong("last_seen", 0L);
    localEditor.commit();
  }
  
  public void saveServiceRunningWithTimestamp(boolean paramBoolean)
  {
    SharedPreferences.Editor localEditor = this.mSettings.edit();
    localEditor.putBoolean("service_running", paramBoolean);
    localEditor.putLong("last_seen", Utils.currentTimeInMillis());
    localEditor.commit();
  }
  
  public void setKilometerOrMile(String paramString)
  {
    SharedPreferences.Editor localEditor = this.mSettings.edit();
    localEditor.putString("Distance_type", paramString);
    localEditor.putString("privious_matric_type", this.mSettings.getString("Distance_type", ""));
    localEditor.commit();
  }
  
  public boolean wakeAggressively()
  {
    return false;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\newpedometer\PedometerSettings.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */