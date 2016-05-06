package com.googlecode.androidannotations.api.sharedpreferences;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public final class FloatPrefField
  extends AbstractPrefField
{
  private final float defaultValue;
  
  FloatPrefField(SharedPreferences paramSharedPreferences, String paramString, float paramFloat)
  {
    super(paramSharedPreferences, paramString);
    this.defaultValue = paramFloat;
  }
  
  public float get()
  {
    return getOr(this.defaultValue);
  }
  
  public float getOr(float paramFloat)
  {
    return this.sharedPreferences.getFloat(this.key, paramFloat);
  }
  
  public void put(float paramFloat)
  {
    apply(edit().putFloat(this.key, paramFloat));
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\googlecode\androidannotations\api\sharedpreferences\FloatPrefField.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */