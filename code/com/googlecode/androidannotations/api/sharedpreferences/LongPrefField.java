package com.googlecode.androidannotations.api.sharedpreferences;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public final class LongPrefField
  extends AbstractPrefField
{
  private final long defaultValue;
  
  LongPrefField(SharedPreferences paramSharedPreferences, String paramString, long paramLong)
  {
    super(paramSharedPreferences, paramString);
    this.defaultValue = paramLong;
  }
  
  public long get()
  {
    return getOr(this.defaultValue);
  }
  
  public long getOr(long paramLong)
  {
    return this.sharedPreferences.getLong(this.key, paramLong);
  }
  
  public void put(long paramLong)
  {
    apply(edit().putLong(this.key, paramLong));
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\googlecode\androidannotations\api\sharedpreferences\LongPrefField.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */