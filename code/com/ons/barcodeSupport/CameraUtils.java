package com.ons.barcodeSupport;

import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import java.util.List;

public class CameraUtils
{
  public static Camera getCameraInstance()
  {
    return getCameraInstance(-1);
  }
  
  public static Camera getCameraInstance(int paramInt)
  {
    if (paramInt == -1) {}
    try
    {
      return Camera.open();
    }
    catch (Exception localException) {}
    Camera localCamera = Camera.open(paramInt);
    return localCamera;
    return null;
  }
  
  public static boolean isFlashSupported(Camera paramCamera)
  {
    if (paramCamera != null)
    {
      paramCamera = paramCamera.getParameters();
      if (paramCamera.getFlashMode() == null) {
        return false;
      }
      paramCamera = paramCamera.getSupportedFlashModes();
      if ((paramCamera == null) || (paramCamera.isEmpty()) || ((paramCamera.size() == 1) && (((String)paramCamera.get(0)).equals("off")))) {
        return false;
      }
    }
    else
    {
      return false;
    }
    return true;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ons\barcodeSupport\CameraUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */