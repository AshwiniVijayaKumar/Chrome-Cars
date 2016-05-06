package com.googlecode.androidannotations.api.sharedpreferences;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public final class IntPrefField
  extends AbstractPrefField
{
  private final int defaultValue;
  
  IntPrefField(SharedPreferences paramSharedPreferences, String paramString, int paramInt)
  {
    super(paramSharedPreferences, paramString);
    this.defaultValue = paramInt;
  }
  
  public int get()
  {
    return getOr(this.defaultValue);
  }
  
  public int getOr(int paramInt)
  {
    return this.sharedPreferences.getInt(this.key, paramInt);
  }
  
  public void put(int paramInt)
  {
    apply(edit().putInt(this.key, paramInt));
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\googlecode\androidannotations\api\sharedpreferences\IntPrefField.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */