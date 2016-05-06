package com.googlecode.androidannotations.api.sharedpreferences;

import android.content.SharedPreferences.Editor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

abstract class SharedPreferencesCompat
{
  private static final Method sApplyMethod = ;
  
  public static void apply(SharedPreferences.Editor paramEditor)
  {
    if (sApplyMethod != null) {}
    try
    {
      sApplyMethod.invoke(paramEditor, new Object[0]);
      return;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      paramEditor.commit();
      return;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      for (;;) {}
    }
  }
  
  private static Method findApplyMethod()
  {
    try
    {
      Method localMethod = SharedPreferences.Editor.class.getMethod("apply", new Class[0]);
      return localMethod;
    }
    catch (NoSuchMethodException localNoSuchMethodException) {}
    return null;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\googlecode\androidannotations\api\sharedpreferences\SharedPreferencesCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */