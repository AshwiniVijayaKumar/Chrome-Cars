package com.ons.barcodeSupport;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;
import android.os.Build.VERSION;
import android.view.Display;
import android.view.WindowManager;

public class DisplayUtils
{
  public static int getScreenOrientation(Context paramContext)
  {
    paramContext = ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay();
    if (paramContext.getWidth() == paramContext.getHeight()) {
      return 3;
    }
    if (paramContext.getWidth() < paramContext.getHeight()) {
      return 1;
    }
    return 2;
  }
  
  @SuppressLint({"NewApi"})
  public static Point getScreenResolution(Context paramContext)
  {
    paramContext = ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay();
    Point localPoint = new Point();
    if (Build.VERSION.SDK_INT >= 13)
    {
      paramContext.getSize(localPoint);
      return localPoint;
    }
    localPoint.set(paramContext.getWidth(), paramContext.getHeight());
    return localPoint;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ons\barcodeSupport\DisplayUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */