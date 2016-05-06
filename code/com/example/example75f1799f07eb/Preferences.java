package com.example.example75f1799f07eb;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class Preferences
{
  private String EMAIL = "email";
  private String Facebook_login = "fb_login";
  private String NAME = "name";
  private SharedPreferences mSharedPreferences;
  
  public Preferences(Context paramContext)
  {
    this.mSharedPreferences = paramContext.getSharedPreferences("appypie", 0);
  }
  
  public String getEMAIL()
  {
    return this.mSharedPreferences.getString(this.EMAIL, "");
  }
  
  public int getFacebook_login()
  {
    return this.mSharedPreferences.getInt(this.Facebook_login, 0);
  }
  
  public String getNAME()
  {
    return this.mSharedPreferences.getString(this.NAME, "");
  }
  
  public void setEMAIL(String paramString)
  {
    SharedPreferences.Editor localEditor = this.mSharedPreferences.edit();
    localEditor.putString(this.EMAIL, paramString);
    localEditor.commit();
  }
  
  public void setFacebook_login(int paramInt)
  {
    SharedPreferences.Editor localEditor = this.mSharedPreferences.edit();
    localEditor.putInt(this.Facebook_login, paramInt);
    localEditor.commit();
  }
  
  public void setNAME(String paramString)
  {
    SharedPreferences.Editor localEditor = this.mSharedPreferences.edit();
    localEditor.putString(this.NAME, paramString);
    localEditor.commit();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\example\example75f1799f07eb\Preferences.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */