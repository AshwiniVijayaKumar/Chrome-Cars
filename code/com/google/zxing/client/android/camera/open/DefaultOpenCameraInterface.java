package com.google.zxing.client.android.camera.open;

import android.hardware.Camera;

final class DefaultOpenCameraInterface
  implements OpenCameraInterface
{
  public Camera open()
  {
    return Camera.open();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\camera\open\DefaultOpenCameraInterface.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */