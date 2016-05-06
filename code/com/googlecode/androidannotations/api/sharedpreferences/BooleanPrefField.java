package com.googlecode.androidannotations.api.sharedpreferences;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public final class BooleanPrefField
  extends AbstractPrefField
{
  private final boolean defaultValue;
  
  BooleanPrefField(SharedPreferences paramSharedPreferences, String paramString, boolean paramBoolean)
  {
    super(paramSharedPreferences, paramString);
    this.defaultValue = paramBoolean;
  }
  
  public boolean get()
  {
    return getOr(this.defaultValue);
  }
  
  public boolean getOr(boolean paramBoolean)
  {
    return this.sharedPreferences.getBoolean(this.key, paramBoolean);
  }
  
  public void put(boolean paramBoolean)
  {
    apply(edit().putBoolean(this.key, paramBoolean));
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\googlecode\androidannotations\api\sharedpreferences\BooleanPrefField.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */