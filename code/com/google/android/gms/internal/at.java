package com.google.android.gms.internal;

import android.content.Context;
import android.content.res.Resources;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import java.util.Map;

public final class at
  implements ar
{
  private static int a(DisplayMetrics paramDisplayMetrics, Map<String, String> paramMap, String paramString, int paramInt)
  {
    paramMap = (String)paramMap.get(paramString);
    int i = paramInt;
    if (paramMap != null) {}
    try
    {
      i = cz.a(paramDisplayMetrics, Integer.parseInt(paramMap));
      return i;
    }
    catch (NumberFormatException paramDisplayMetrics)
    {
      da.w("Could not parse " + paramString + " in a video GMSG: " + paramMap);
    }
    return paramInt;
  }
  
  public void a(dd paramdd, Map<String, String> paramMap)
  {
    String str = (String)paramMap.get("action");
    if (str == null)
    {
      da.w("Action missing from video GMSG.");
      return;
    }
    Object localObject = paramdd.ba();
    if (localObject == null)
    {
      da.w("Could not get ad overlay for a video GMSG.");
      return;
    }
    boolean bool1 = "new".equalsIgnoreCase(str);
    boolean bool2 = "position".equalsIgnoreCase(str);
    int i;
    int j;
    if ((bool1) || (bool2))
    {
      paramdd = paramdd.getContext().getResources().getDisplayMetrics();
      i = a(paramdd, paramMap, "x", 0);
      j = a(paramdd, paramMap, "y", 0);
      int k = a(paramdd, paramMap, "w", -1);
      int m = a(paramdd, paramMap, "h", -1);
      if ((bool1) && (((bo)localObject).ap() == null))
      {
        ((bo)localObject).c(i, j, k, m);
        return;
      }
      ((bo)localObject).b(i, j, k, m);
      return;
    }
    localObject = ((bo)localObject).ap();
    if (localObject == null)
    {
      bs.a(paramdd, "no_video_view", null);
      return;
    }
    if ("click".equalsIgnoreCase(str))
    {
      paramdd = paramdd.getContext().getResources().getDisplayMetrics();
      i = a(paramdd, paramMap, "x", 0);
      j = a(paramdd, paramMap, "y", 0);
      long l = SystemClock.uptimeMillis();
      paramdd = MotionEvent.obtain(l, l, 0, i, j, 0);
      ((bs)localObject).b(paramdd);
      paramdd.recycle();
      return;
    }
    if ("controls".equalsIgnoreCase(str))
    {
      paramdd = (String)paramMap.get("enabled");
      if (paramdd == null)
      {
        da.w("Enabled parameter missing from controls video GMSG.");
        return;
      }
      ((bs)localObject).i(Boolean.parseBoolean(paramdd));
      return;
    }
    if ("currentTime".equalsIgnoreCase(str))
    {
      paramdd = (String)paramMap.get("time");
      if (paramdd == null)
      {
        da.w("Time parameter missing from currentTime video GMSG.");
        return;
      }
      try
      {
        ((bs)localObject).seekTo((int)(Float.parseFloat(paramdd) * 1000.0F));
        return;
      }
      catch (NumberFormatException paramMap)
      {
        da.w("Could not parse time parameter from currentTime video GMSG: " + paramdd);
        return;
      }
    }
    if ("hide".equalsIgnoreCase(str))
    {
      ((bs)localObject).setVisibility(4);
      return;
    }
    if ("load".equalsIgnoreCase(str))
    {
      ((bs)localObject).ay();
      return;
    }
    if ("pause".equalsIgnoreCase(str))
    {
      ((bs)localObject).pause();
      return;
    }
    if ("play".equalsIgnoreCase(str))
    {
      ((bs)localObject).play();
      return;
    }
    if ("show".equalsIgnoreCase(str))
    {
      ((bs)localObject).setVisibility(0);
      return;
    }
    if ("src".equalsIgnoreCase(str))
    {
      ((bs)localObject).o((String)paramMap.get("src"));
      return;
    }
    da.w("Unknown video action: " + str);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\at.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */