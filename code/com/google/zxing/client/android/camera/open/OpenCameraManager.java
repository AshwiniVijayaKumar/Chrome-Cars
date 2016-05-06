package com.google.zxing.client.android.camera.open;

import com.google.zxing.client.android.common.PlatformSupportManager;

public final class OpenCameraManager
  extends PlatformSupportManager<OpenCameraInterface>
{
  public OpenCameraManager()
  {
    super(OpenCameraInterface.class, new DefaultOpenCameraInterface());
    addImplementationClass(9, "com.google.zxing.client.android.camera.open.GingerbreadOpenCameraInterface");
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\camera\open\OpenCameraManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */