package io.vov.vitamio.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.view.Display;
import android.view.WindowManager;
import java.lang.reflect.Method;

public class ScreenResolution
{
  @TargetApi(17)
  private static Pair<Integer, Integer> getRealResolution(Context paramContext)
  {
    paramContext = ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay();
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    paramContext.getRealMetrics(localDisplayMetrics);
    return new Pair(Integer.valueOf(localDisplayMetrics.widthPixels), Integer.valueOf(localDisplayMetrics.heightPixels));
  }
  
  private static Pair<Integer, Integer> getRealResolutionOnOldDevice(Context paramContext)
  {
    try
    {
      Object localObject = ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay();
      Method localMethod1 = Display.class.getMethod("getRawWidth", new Class[0]);
      Method localMethod2 = Display.class.getMethod("getRawHeight", new Class[0]);
      localObject = new Pair((Integer)localMethod1.invoke(localObject, new Object[0]), (Integer)localMethod2.invoke(localObject, new Object[0]));
      return (Pair<Integer, Integer>)localObject;
    }
    catch (Exception localException)
    {
      paramContext = paramContext.getResources().getDisplayMetrics();
    }
    return new Pair(Integer.valueOf(paramContext.widthPixels), Integer.valueOf(paramContext.heightPixels));
  }
  
  public static Pair<Integer, Integer> getResolution(Context paramContext)
  {
    if (Build.VERSION.SDK_INT >= 17) {
      return getRealResolution(paramContext);
    }
    return getRealResolutionOnOldDevice(paramContext);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\io\vov\vitamio\utils\ScreenResolution.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */