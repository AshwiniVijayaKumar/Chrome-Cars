package com.googlecode.androidannotations.api.sharedpreferences;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public final class StringPrefField
  extends AbstractPrefField
{
  private final String defaultValue;
  
  StringPrefField(SharedPreferences paramSharedPreferences, String paramString1, String paramString2)
  {
    super(paramSharedPreferences, paramString1);
    this.defaultValue = paramString2;
  }
  
  public String get()
  {
    return getOr(this.defaultValue);
  }
  
  public String getOr(String paramString)
  {
    return this.sharedPreferences.getString(this.key, paramString);
  }
  
  public void put(String paramString)
  {
    apply(edit().putString(this.key, paramString));
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\googlecode\androidannotations\api\sharedpreferences\StringPrefField.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */