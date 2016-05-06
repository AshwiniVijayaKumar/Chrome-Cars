package io.vov.vitamio.utils;

import java.util.MissingFormatArgumentException;

public class Log
{
  public static final String TAG = "Vitamio[Player]";
  
  public static void d(String paramString, Object... paramVarArgs)
  {
    try
    {
      android.util.Log.d("Vitamio[Player]", String.format(paramString, paramVarArgs));
      return;
    }
    catch (MissingFormatArgumentException paramVarArgs)
    {
      android.util.Log.e("Vitamio[Player]", "vitamio.Log", paramVarArgs);
      android.util.Log.d("Vitamio[Player]", paramString);
    }
  }
  
  public static void e(String paramString, Throwable paramThrowable)
  {
    android.util.Log.e("Vitamio[Player]", paramString, paramThrowable);
  }
  
  public static void e(String paramString, Object... paramVarArgs)
  {
    try
    {
      android.util.Log.e("Vitamio[Player]", String.format(paramString, paramVarArgs));
      return;
    }
    catch (MissingFormatArgumentException paramVarArgs)
    {
      android.util.Log.e("Vitamio[Player]", "vitamio.Log", paramVarArgs);
      android.util.Log.e("Vitamio[Player]", paramString);
    }
  }
  
  public static void i(String paramString, Object... paramVarArgs)
  {
    try
    {
      android.util.Log.i("Vitamio[Player]", String.format(paramString, paramVarArgs));
      return;
    }
    catch (MissingFormatArgumentException paramVarArgs)
    {
      android.util.Log.e("Vitamio[Player]", "vitamio.Log", paramVarArgs);
      android.util.Log.i("Vitamio[Player]", paramString);
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\io\vov\vitamio\utils\Log.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */