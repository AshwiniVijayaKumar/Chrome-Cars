package com.google.zxing.client.android.camera.open;

import android.annotation.TargetApi;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.util.Log;

@TargetApi(9)
public final class GingerbreadOpenCameraInterface
  implements OpenCameraInterface
{
  private static final String TAG = "GingerbreadOpenCamera";
  
  public Camera open()
  {
    int j = Camera.getNumberOfCameras();
    if (j == 0)
    {
      Log.w("GingerbreadOpenCamera", "No cameras!");
      return null;
    }
    int i = 0;
    for (;;)
    {
      if (i >= j) {}
      Camera.CameraInfo localCameraInfo;
      do
      {
        if (i >= j) {
          break;
        }
        Log.i("GingerbreadOpenCamera", "Opening camera #" + i);
        return Camera.open(i);
        localCameraInfo = new Camera.CameraInfo();
        Camera.getCameraInfo(i, localCameraInfo);
      } while (localCameraInfo.facing == 0);
      i += 1;
    }
    Log.i("GingerbreadOpenCamera", "No camera facing back; returning camera #0");
    return Camera.open(0);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\camera\open\GingerbreadOpenCameraInterface.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */