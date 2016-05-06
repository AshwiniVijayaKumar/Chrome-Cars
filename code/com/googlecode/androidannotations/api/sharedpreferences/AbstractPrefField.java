package com.googlecode.androidannotations.api.sharedpreferences;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public abstract class AbstractPrefField
{
  protected final String key;
  protected final SharedPreferences sharedPreferences;
  
  public AbstractPrefField(SharedPreferences paramSharedPreferences, String paramString)
  {
    this.sharedPreferences = paramSharedPreferences;
    this.key = paramString;
  }
  
  protected final void apply(SharedPreferences.Editor paramEditor)
  {
    SharedPreferencesCompat.apply(paramEditor);
  }
  
  protected SharedPreferences.Editor edit()
  {
    return this.sharedPreferences.edit();
  }
  
  public final boolean exists()
  {
    return this.sharedPreferences.contains(this.key);
  }
  
  public final void remove()
  {
    apply(edit().remove(this.key));
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\googlecode\androidannotations\api\sharedpreferences\AbstractPrefField.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */