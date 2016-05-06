package com.googlecode.androidannotations.api.sharedpreferences;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public abstract class SharedPreferencesHelper
{
  private final SharedPreferences sharedPreferences;
  
  public SharedPreferencesHelper(SharedPreferences paramSharedPreferences)
  {
    this.sharedPreferences = paramSharedPreferences;
  }
  
  protected BooleanPrefField booleanField(String paramString, boolean paramBoolean)
  {
    return new BooleanPrefField(this.sharedPreferences, paramString, paramBoolean);
  }
  
  public final void clear()
  {
    SharedPreferencesCompat.apply(this.sharedPreferences.edit().clear());
  }
  
  protected FloatPrefField floatField(String paramString, float paramFloat)
  {
    return new FloatPrefField(this.sharedPreferences, paramString, paramFloat);
  }
  
  public final SharedPreferences getSharedPreferences()
  {
    return this.sharedPreferences;
  }
  
  protected IntPrefField intField(String paramString, int paramInt)
  {
    return new IntPrefField(this.sharedPreferences, paramString, paramInt);
  }
  
  protected LongPrefField longField(String paramString, long paramLong)
  {
    return new LongPrefField(this.sharedPreferences, paramString, paramLong);
  }
  
  protected StringPrefField stringField(String paramString1, String paramString2)
  {
    return new StringPrefField(this.sharedPreferences, paramString1, paramString2);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\googlecode\androidannotations\api\sharedpreferences\SharedPreferencesHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */